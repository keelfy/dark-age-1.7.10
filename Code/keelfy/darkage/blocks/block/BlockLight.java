/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.blocks.block;

import java.util.Random;

import keelfytools.KeelfyUtils;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

/**
 * @author keelfy
 */
public class BlockLight extends BlockContainer {

	public BlockLight(int par1) {
		super(Material.air);
		
		this.setBlockUnbreakable();
		this.setTickRandomly(true);
	}

	@Override
	public void updateTick(World w, int x, int y, int z, Random par5Random) {
		w.setBlockToAir(x, y, z);
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
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_) {
		return null;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int par2) {
		return new BlockLightTile();
	}
	
	public class BlockLightTile extends TileEntity {

		public byte timer = 0;
		@Override
		public void updateEntity() {
			if(KeelfyUtils.isServerSide()) {
				if(!super.worldObj.isRemote) {
					if(super.worldObj.getBlockMetadata(super.xCoord, super.yCoord, super.zCoord) != 4016 || super.worldObj.getBlockMetadata(super.xCoord, super.yCoord, super.zCoord) != 4017 || super.worldObj.getBlockMetadata(super.xCoord, super.yCoord, super.zCoord) != 4018 || super.worldObj.getBlockMetadata(super.xCoord, super.yCoord, super.zCoord) != 4019) {
						super.worldObj.setTileEntity(super.xCoord, super.yCoord, super.zCoord, (TileEntity)null);
					}
	
					if(this.timer > 1) {
						super.worldObj.setTileEntity(super.xCoord, super.yCoord, super.zCoord, (TileEntity)null);
						super.worldObj.setBlockToAir(super.xCoord, super.yCoord, super.zCoord);
					}
					++this.timer;
				}
			}
		}
	}
}
