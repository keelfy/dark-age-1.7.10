/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.blocks;

import keelfy.darkdata.blocks.tileentities.TileEntityLoot;
import keelfy.darkdata.constants.EnumGui;
import keelfy.darkdata.handlers.DAGuiHandler;
import keelfy.darkdata.handlers.DATabsHandler;
import keelfyutils.KUtils;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

/**
 * @author keelfy
 */
public final class BlockLootBag extends BlockContainer {

	public BlockLootBag() {
		super(Material.cloth);

		setBlockBounds(0.3F, 0.0F, 0.3F, 0.7F, 0.6F, 0.7F);
		setCreativeTab(DATabsHandler.blocks);
		setBlockUnbreakable();
	}

	@Override
	public boolean onBlockActivated(final World worldIn, final int x, final int y, final int z,
			final EntityPlayer player, final int side, final float subX, final float subY, final float subZ) {
		if (KUtils.PROTECT_CLIENT) {
			DAGuiHandler.openGui(EnumGui.LootBag);
		}
		return false;
	}

	@Override
	public void onBlockPlacedBy(final World worldIn, final int x, final int y, final int z,
			final EntityLivingBase placer, final ItemStack itemIn) {
		if (KUtils.PROTECT_SERVER) {
			final TileEntityLoot te = getTile(worldIn, x, y, z);

			te.setInventorySlotContents(0, new ItemStack(Items.apple));
			te.setInventorySlotContents(1, new ItemStack(Items.book, 64));
			te.setInventorySlotContents(2, new ItemStack(Items.book, 64));
			te.setInventorySlotContents(3, new ItemStack(Items.book, 64));
			//
			// int l = MathHelper.floor_double(placer.rotationYaw * 4.0F / 360.0F + 0.5D) &
			// 3;
			// l %= 4;
			// TileEntityLoot tile = (TileEntityLoot) worldIn.getTileEntity(x, y, z);
			// tile.rotation = l;
		}
	}

	private TileEntityLoot getTile(final World worldIn, final int x, final int y, final int z) {
		if (KUtils.PROTECT_SERVER) {
			final TileEntity tile = worldIn.getTileEntity(x, y, z);
			if (tile != null && tile instanceof TileEntityLoot)
				return (TileEntityLoot) tile;
		}
		return null;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(final World worldIn, final int x, final int y, final int z) {
		return null;
	}

	// @Override
	// public int getRenderType() {
	// return -1;
	// }
	//
	// @Override
	// public boolean isOpaqueCube() {
	// return false;
	// }
	//
	// @Override
	// public boolean renderAsNormalBlock() {
	// return true;
	// }

	@Override
	public TileEntity createNewTileEntity(final World worldIn, final int meta) {
		return new TileEntityLoot();
	}
}
