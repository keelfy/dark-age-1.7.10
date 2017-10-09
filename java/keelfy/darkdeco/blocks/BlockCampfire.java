/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdeco.blocks;

import java.util.*;

import cpw.mods.fml.relauncher.*;
import keelfy.darkdata.blocks.*;
import keelfy.darkdata.blocks.tileentities.*;
import keelfy.darkdata.handlers.*;
import keelfyutils.blocks.*;
import net.minecraft.block.material.*;
import net.minecraft.tileentity.*;
import net.minecraft.util.*;
import net.minecraft.world.*;

/**
 * @author keelfy
 * @created 6 сент. 2017 г.
 */
public class BlockCampfire extends DABlock {

	public BlockCampfire() {
		super(Material.wood, false);

		this.setLightLevel(100);
		this.setBlockBounds(0, 0, 0, 1F, 0.41F, 1F);
		this.setBlockUnbreakable();
		this.setCreativeTab(DATabsHandler.blocks);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		return AxisAlignedBB.getBoundingBox((double)x, (double)y, (double)z, (double)x + 1F, (double)y + 0.41F, (double)z + 1F);
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public int getRenderType() {
		return -1;
	}
	
	@SideOnly(Side.CLIENT)
	public void renderTick(World world, Point3D pos, Random rand) {
		int l = KBlocks.getBlockMetadata(world, pos);
		double d0 = (double) ((float) pos.x + 0.5F);
		double d1 = (double) ((float) pos.y + 0.3F);
		double d2 = (double) ((float) pos.z + 0.5F);
		double d3 = 0.2199999988079071D;

		int i = 0;
		
		for (i = 0; i < 3; i++) {
			world.spawnParticle("smoke", d0, d1 + d3, d2, 0.0D, 0.0D, 0.0D);
			world.spawnParticle("flame", d0, d1 + d3, d2, 0.0D, 0.0D, 0.0D);
		}
		
		d1 -= 0.3F;
		
		d0 += (rand.nextFloat() - 0.5F) / 1.5F;
		d2 += (rand.nextFloat() - 0.5F) / 1.5F;
		
		for (i = 0; i < 3; i++) {
			world.spawnParticle("smoke", d0, d1 + d3, d2, 0.0D, 0.0D, 0.0D);
			world.spawnParticle("flame", d0, d1 + d3, d2, 0.0D, 0.0D, 0.0D);
		}
		
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityCampfire();
	}

	public static final class TileEntityCampfire extends TileEntity {}

}