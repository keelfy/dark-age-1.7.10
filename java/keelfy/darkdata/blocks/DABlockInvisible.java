/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

/**
 * @author keelfy
 * @created 12 июл. 2017 г.
 */
public abstract class DABlockInvisible extends DABlock {

	public DABlockInvisible(final Material material, final boolean isBlockContainer) {
		super(material, isBlockContainer);
	}

	@Override
	public final AxisAlignedBB getCollisionBoundingBoxFromPool(final World world, final int x, final int y,
			final int z) {
		return null;
	}

	@Override
	public final int getRenderBlockPass() {
		return 1;
	}

	@Override
	public final boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public final boolean isOpaqueCube() {
		return false;
	}
}
