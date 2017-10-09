/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.blocks;

import java.util.Random;

import keelfy.darkdata.blocks.tileentities.DATileEntity;
import keelfyutils.KUtils;
import keelfyutils.blocks.Point3D;
import keelfyutils.blocks.KBlocks;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * @author keelfy
 */
public final class BlockLight extends DABlockInvisible {

	public BlockLight() {
		super(Material.air, true);

		this.setTickRandomly(true);
	}

	@Override
	public final void updateTick(final World world, final Point3D pos, final Random rand) {
		KBlocks.setBlockToAir(world, pos);
	}

	@Override
	public final TileEntity createNewTileEntity(final World world, final int meta) {
		return new BlockLightTile();
	}

	public final class BlockLightTile extends DATileEntity {

		public BlockLightTile() {
			super(false);
		}

		public byte timer = 0;

		@Override
		public final void updateEntity() {
			if (KUtils.PROTECT_SERVER) {
				if (!super.getWorldObj().isRemote) {

					final int meta = super.getBlockMetadata();
					if (meta != 4016 || meta != 4017 || meta != 4018 || meta != 4019) {
						super.setTileEntity((TileEntity) null);
					}

					if (this.timer > 1) {
						super.setTileEntity((TileEntity) null);
						super.setBlockToAir();
					}
					++this.timer;
				}
			}
		}
	}
}
