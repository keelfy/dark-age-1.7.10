package noppes.npcs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.eventhandler.Event.Result;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.event.ForgeEventFactory;
import noppes.npcs.controllers.SpawnController;
import noppes.npcs.controllers.SpawnData;
import noppes.npcs.entity.EntityCustomNpc;
import noppes.npcs.entity.EntityNPCInterface;

public class NPCSpawning {

	private static HashMap<ChunkCoordIntPair, Boolean> eligibleChunksForSpawning = new HashMap();

	public static void findChunksForSpawning(final WorldServer world) {

		if (SpawnController.instance == null || SpawnController.instance.data.isEmpty()
				|| world.getWorldInfo().getWorldTotalTime() % 400L != 0L)
			return;

		eligibleChunksForSpawning.clear();
		for (int i = 0; i < world.playerEntities.size(); ++i) {
			EntityPlayer entityplayer = (EntityPlayer) world.playerEntities.get(i);
			int j = MathHelper.floor_double(entityplayer.posX / 16.0D);
			int k = MathHelper.floor_double(entityplayer.posZ / 16.0D);
			byte size = 8;

			for (int x = -size; x <= size; ++x) {
				for (int z = -size; z <= size; ++z) {
					boolean flag3 = x == -size || x == size || z == -size || z == size;
					ChunkCoordIntPair chunkcoordintpair = new ChunkCoordIntPair(x + j, z + k);

					if (!flag3) {
						eligibleChunksForSpawning.put(chunkcoordintpair, false);
					} else if (!eligibleChunksForSpawning.containsKey(chunkcoordintpair)) {
						eligibleChunksForSpawning.put(chunkcoordintpair, true);
					}
				}
			}
		}

		if (countNPCs(world) > eligibleChunksForSpawning.size() / 20)
			return;

		ArrayList<ChunkCoordIntPair> tmp = new ArrayList(eligibleChunksForSpawning.keySet());
		Collections.shuffle(tmp);
		Iterator<ChunkCoordIntPair> iterator = tmp.iterator();

		while (iterator.hasNext()) {
			ChunkCoordIntPair chunkcoordintpair1 = iterator.next();

			if (eligibleChunksForSpawning.get(chunkcoordintpair1)) {
				continue;
			}
			ChunkPosition chunkposition = getChunk(world, chunkcoordintpair1.chunkXPos, chunkcoordintpair1.chunkZPos);
			int j1 = chunkposition.chunkPosX;
			int k1 = chunkposition.chunkPosY;
			int l1 = chunkposition.chunkPosZ;

			for (int i = 0; i < 3; i++) {
				int x = j1;
				int y = k1;
				int z = l1;
				byte b1 = 6;

				x += world.rand.nextInt(b1) - world.rand.nextInt(b1);
				y += world.rand.nextInt(1) - world.rand.nextInt(1);
				z += world.rand.nextInt(b1) - world.rand.nextInt(b1);

				Block block = world.getBlock(x, y, z);

				String name = world.getBiomeGenForCoords(x, z).biomeName;
				SpawnData data = SpawnController.instance.getRandomSpawnData(name, block.getMaterial() == Material.air);

				if (data == null || !canCreatureTypeSpawnAtLocation(data, world, x, y, z)
						|| world.getClosestPlayer(x, y, z, 24.0D) != null) {
					continue;
				}

				spawnData(data, world, x, y, z);
			}
		}
	}

	public static int countNPCs(final World world) {
		int count = 0;
		List<Entity> list = world.loadedEntityList;
		for (Entity entity : list) {
			if (entity instanceof EntityNPCInterface) {
				count++;
			}
		}
		return count;
	}

	protected static ChunkPosition getChunk(final World world, final int x, final int z) {
		Chunk chunk = world.getChunkFromChunkCoords(x, z);
		int k = x * 16 + world.rand.nextInt(16);
		int l = z * 16 + world.rand.nextInt(16);
		int i1 = world.rand.nextInt(chunk == null ? world.getActualHeight() : chunk.getTopFilledSegment() + 16 - 1);
		return new ChunkPosition(k, i1, l);
	}

	public static void performWorldGenSpawning(final World world, final int x, final int z, final Random rand) {
		BiomeGenBase biome = world.getBiomeGenForCoords(x + 8, z + 8);
		while (rand.nextFloat() < biome.getSpawningChance()) {
			SpawnData data = SpawnController.instance.getRandomSpawnData(biome.biomeName, true);
			if (data == null) {
				continue;
			}

			int size = 16;

			int j1 = x + rand.nextInt(size);
			int k1 = z + rand.nextInt(size);
			int l1 = j1;
			int i2 = k1;

			for (int k2 = 0; k2 < 4; ++k2) {
				int l2 = world.getTopSolidOrLiquidBlock(j1, k1);

				if (!canCreatureTypeSpawnAtLocation(data, world, j1, l2, k1)) {
					j1 += rand.nextInt(5) - rand.nextInt(5);

					for (k1 += rand.nextInt(5) - rand.nextInt(5); j1 < x || j1 >= x + size || k1 < z
							|| k1 >= z + size; k1 = i2 + rand.nextInt(5) - rand.nextInt(5)) {
						j1 = l1 + rand.nextInt(5) - rand.nextInt(5);
					}
				} else if (spawnData(data, world, j1, l2, k1)) {
					break;
				}

			}
		}
	}

	private static boolean spawnData(final SpawnData data, final World world, final int x, final int y, final int z) {
		EntityLiving entityliving;

		try {
			Entity entity = EntityList.createEntityFromNBT(data.compound1, world);
			if (entity == null || !(entity instanceof EntityLiving))
				return false;
			entityliving = (EntityLiving) entity;

			if (entity instanceof EntityCustomNpc) {
				EntityCustomNpc npc = (EntityCustomNpc) entity;
				npc.stats.spawnCycle = 3;
				npc.ai.returnToStart = false;
				npc.ai.startPos = new int[] { x, y, z };
			}
			entity.setLocationAndAngles(x + 0.5, y, z + 0.5, world.rand.nextFloat() * 360.0F, 0.0F);
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}

		Result canSpawn = ForgeEventFactory.canEntitySpawn(entityliving, world, x + 0.5f, y, z + 0.5f);
		if (canSpawn == Result.DENY || (canSpawn == Result.DEFAULT && !entityliving.getCanSpawnHere()))
			return false;

		world.spawnEntityInWorld(entityliving);

		return true;
	}

	public static boolean canCreatureTypeSpawnAtLocation(final SpawnData data, final World world, final int x,
			final int y, final int z) {
		if (data.liquid)
			return world.getBlock(x, y, z).getMaterial().isLiquid()
					&& world.getBlock(x, y - 1, z).getMaterial().isLiquid()
					&& !world.getBlock(x, y + 1, z).isNormalCube();
		else if (!World.doesBlockHaveSolidTopSurface(world, x, y - 1, z))
			return false;
		else {
			Block block = world.getBlock(x, y - 1, z);
			boolean spawnBlock = block.canCreatureSpawn(EnumCreatureType.creature, world, x, y - 1, z);
			return spawnBlock && !world.getBlock(x, y, z).isNormalCube()
					&& !world.getBlock(x, y, z).getMaterial().isLiquid() && !world.getBlock(x, y + 1, z).isNormalCube();
		}
	}
}
