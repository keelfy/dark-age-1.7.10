package keelfy.witcherBlocks.block;

import java.util.Random;

import org.lwjgl.input.Mouse;

import keelfy.darkage.util.DAUtil;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

/**
 * @author keelfy
 */
public class BlockSpecial extends Block {

	private int id;
	public BlockSpecial(Material material, int particleid) {
	      super(material);
	      this.id = particleid;
	      
	      this.setHarvestLevel("pickaxe", 3);
	      this.setStepSound(soundTypeGlass);
	}

	@Override
	public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			if (Mouse.isButtonDown(1)) {
				int l;
				float f;
				float f1;
				float f2;
				if (this.id == 1) {
					for (l = 0; (double) l < 0.1D; ++l) {
						f = (float) par2 + par5Random.nextFloat() * 0.4F;
						f1 = (float) par3 + par5Random.nextFloat();
						f2 = (float) par4 + par5Random.nextFloat();
						par1World.spawnParticle("reddust", (double) f, (double) f1, (double) f2, 0.0D, 0.1D, 0.0D);
					}
				}
	
				if (this.id == 0) {
					for (l = 0; (double) l < 0.1D; ++l) {
						f = (float) par2 + par5Random.nextFloat();
						f1 = (float) par3 + par5Random.nextFloat() * 3.0F;
						f2 = (float) par4 + par5Random.nextFloat();
						par1World.spawnParticle("reddust", (double) f, (double) f1, (double) f2, 0.0D, 0.1D, 0.0D);
					}
	
					for (l = 0; (double) l < 0.1D; ++l) {
						f = (float) par2 + 0.3F;
						f1 = (float) par3 + 2.001F;
						f2 = (float) par4 + 0.3F;
						par1World.spawnParticle("footstep", (double) f, (double) f1, (double) f2, 0.0D, 0.1D, 0.0D);
					}
				}
			}
		}
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int par2, int par3, int par4, Entity entity) {
		entity.setAir(1);
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int par2, int par3, int par4) {
		return null;
	}

	@Override
	public int getRenderType() {
		return 1;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public Item getItemDropped(int par1, Random rand, int par3) {
		return Items.string;
	}

	@Override
	protected boolean canSilkHarvest() {
		return true;
	}
}