/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.blocks;

import keelfy.darkdata.handlers.DATabsHandler;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

/**
 * @author keelfy
 */
public final class BlockLightMushroom extends Block implements IPlantable {

	public BlockLightMushroom() {
		super(Material.plants);

		this.setTickRandomly(true);
		this.setLightLevel(3F);
		this.setLightOpacity(0);
		this.setCreativeTab(DATabsHandler.blocks);
		final float f = 0.2F;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(final World p_149668_1_, final int p_149668_2_,
			final int p_149668_3_, final int p_149668_4_) {
		return null;
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
		return 1;
	}

	@Override
	public EnumPlantType getPlantType(final IBlockAccess world, final int x, final int y, final int z) {

		return EnumPlantType.Plains;
	}

	@Override
	public Block getPlant(final IBlockAccess world, final int x, final int y, final int z) {

		return this;
	}

	@Override
	public int getPlantMetadata(final IBlockAccess world, final int x, final int y, final int z) {
		return 0;
	}
}
