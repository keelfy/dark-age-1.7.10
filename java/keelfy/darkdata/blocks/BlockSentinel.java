/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.blocks;

import java.util.List;

import keelfy.darkcore.common.player.DADataManager;
import keelfy.darkdata.blocks.tileentities.DATileEntity;
import keelfy.darkdata.constants.EnumGui;
import keelfy.darkdata.handlers.DAGuiHandler;
import keelfy.darkdata.server.DAServerConfig;
import keelfyutils.KUtils;
import keelfyutils.blocks.Point3D;
import keelfyutils.server.KServer;
import keelfyutils.str.Brush;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import noppes.npcs.controllers.FactionController;
import noppes.npcs.controllers.PlayerData;
import noppes.npcs.controllers.PlayerDataController;

/**
 * @author keelfy
 * @created 12 июл. 2017 г.
 */
public final class BlockSentinel extends DABlockInvisible {

	public BlockSentinel() {
		super(Material.cloth, false);
	}

	@Override
	protected void onBlockPlaced(final World world, final Point3D pos) {

	}

	@Override
	public final boolean onBlockActivated(final World world, final Point3D pos, final EntityPlayer player) {
		if (KUtils.PROTECT_CLIENT) {
			if (world.isRemote) {
				DAGuiHandler.openGui(EnumGui.Sentinel, pos);
			}
		}
		return super.onBlockActivated(world, pos, player);
	}

	@Override
	public final TileEntity createNewTileEntity(final World worldIn, final int meta) {
		return null;
	}

	public static final class TileEntitySentinel extends DATileEntity {

		public double radius;
		public int factionId;

		public TileEntitySentinel() {
			super(false);

			this.radius = 0;
			this.factionId = -1;
		}

		public final double getRadius() {
			return radius;
		}

		public final int getFactionId() {
			return factionId;
		}

		@Override
		public void updateEntity() {
			if (KUtils.PROTECT_SERVER) {
				super.updateEntity();

				final int cfi = DAServerConfig.Instance.carrerasFactionId;
				final double r = DAServerConfig.Instance.radiusOfReputationDecreasing;

				if (!FactionController.getInstance().factions.containsKey(cfi))
					return;

				final List<EntityPlayer> players = this.getWorldObj().getEntitiesWithinAABB(EntityPlayer.class, this.getRenderBoundingBox().expand(radius, radius, radius));

				for (final EntityPlayer player : players) {
					if (DADataManager.getPlayer(player).health.get() <= 0 && player.getLastAttacker() != null && player.getLastAttacker() instanceof EntityPlayer && player.getLastAttackerTime() <= 100) {
						final PlayerData data = PlayerDataController.instance.getPlayerData((EntityPlayer) player.getLastAttacker());
						data.factionData.increasePoints(factionId, -2000);
						data.saveNBTData(null);
						KServer.sendMessage((EntityPlayer) player.getLastAttacker(), Brush.RED + "Вы убили игрока на глазах у стражи!");

					}
				}
			}
		}

		@Override
		public final void readFromNBT(final NBTTagCompound compound) {
			super.readFromNBT(compound);

			this.radius = compound.getDouble("Radius");
			this.factionId = compound.getInteger("FactionId");
		}

		@Override
		public final void writeToNBT(final NBTTagCompound compound) {
			if (KUtils.PROTECT_SERVER) {
				super.writeToNBT(compound);

				compound.setDouble("Radius", radius);
				compound.setInteger("FactionId", factionId);
			}
		}
	}
}
