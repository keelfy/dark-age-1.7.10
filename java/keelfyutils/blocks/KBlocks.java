package keelfyutils.blocks;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * @author keelfy
 * @created 13 июл. 2017 г.
 */
public enum KBlocks {
	Instance;

	public static final int getBlockMetadata(final World world, final Point3D pos) {
		return world.getBlockMetadata(pos.x, pos.y, pos.z);
	}
	
	public static final void setBlockMetadata(final World world, final Point3D pos, final int metadata,
			final int flag) {
		world.setBlockMetadataWithNotify(pos.x, pos.y, pos.z, metadata, flag);
	}

	public static final void setBlock(final World world, final Block block, final Point3D pos) {
		world.setBlock(pos.x, pos.y, pos.z, block);
	}

	public static final void setTileEntity(final World world, final Point3D pos, final TileEntity te) {
		world.setTileEntity(pos.x, pos.y, pos.z, te);
	}

	public static final TileEntity getTileEntity(final World world, final Point3D pos) {
		return world.getTileEntity(pos.x, pos.y, pos.z);
	}

	public static final void setBlockToAir(final World world, final Point3D pos) {
		world.setBlockToAir(pos.x, pos.y, pos.z);
	}
}
