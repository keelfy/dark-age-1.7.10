/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.blocks;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkdata.constants.EnumGui;
import keelfy.darkdata.handlers.DAGuiHandler;
import keelfy.darkdata.handlers.DATabsHandler;
import keelfyutils.KUtils;
import keelfyutils.blocks.KBlocks;
import keelfyutils.blocks.Point3D;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * @author keelfy
 * @created 12 июл. 2017 г.
 */
public abstract class DABlock extends BlockContainer {

	public DABlock(final Material material, final boolean isBlockContainer) {
		super(material);

		this.isBlockContainer = isBlockContainer;
		this.setBlockUnbreakable();
		this.setCreativeTab(DATabsHandler.blocks);
	}

	public EnumGui getGuiToOpen() {
		return null;
	}

	public boolean onBlockActivated(final World world, final Point3D pos, final EntityPlayer player) {
		if (KUtils.PROTECT_CLIENT) {
			if (world.isRemote && getGuiToOpen() != null) {
				DAGuiHandler.openGui(getGuiToOpen());
			}
		}
		return false;
	}

	protected void onBlockPlaced(final World world, final Point3D pos) {}

	@Override
	public final int onBlockPlaced(final World world, final int x, final int y, final int z, final int side,
			final float subX, final float subY, final float subZ, final int meta) {
		this.onBlockPlaced(world, Point3D.from(x, y, z));
		return super.onBlockPlaced(world, x, y, z, side, subX, subY, subZ, meta);
	}


	@SideOnly(Side.CLIENT)
	public final void randomDisplayTick(World world, int x, int y, int z, Random rand) {	
		super.randomDisplayTick(world, x, y, z, rand);
		
		this.renderTick(world, Point3D.from(x, y, z), rand);
	}
	
	@SideOnly(Side.CLIENT)
	protected void renderTick(World world, Point3D pos, Random rand) {}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z,
			EntityLivingBase living, ItemStack stack) {
		super.onBlockPlacedBy(world, x, y, z, living, stack);
		this.onBlockPlacedBy(world, Point3D.from(x, y, z), living, stack);
	}
	
	protected void onBlockPlacedBy(final World world, Point3D pos,
			final EntityLivingBase living, final ItemStack itemStack) {
		final int l = MathHelper.floor_double(living.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
		final ForgeDirection dir = ForgeDirection.getOrientation(l);
		
		switch (dir) {
			case SOUTH:
				KBlocks.setBlockMetadata(world, pos, 0, 2);
				break;
			case WEST:
				KBlocks.setBlockMetadata(world, pos, 1, 2);
				break;
			case NORTH:
				KBlocks.setBlockMetadata(world, pos, 2, 2);
				break;
			case EAST:
				KBlocks.setBlockMetadata(world, pos, 3, 2);
				break;
			default:
				break;
		}
	}

	@Override
	public final void updateTick(final World world, final int x, final int y, final int z, final Random rand) {
		this.updateTick(world, Point3D.from(x, y, z), rand);
	}

	protected void updateTick(final World world, final Point3D pos, final Random rand) {}

	public final boolean isFullBlock() {
		return this.func_149730_j();
	}

	@Override
	public final boolean onBlockActivated(final World world, final int x, final int y, final int z,
			final EntityPlayer player, final int meta, final float sideX, final float sideY, final float sideZ) {
		this.onBlockActivated(world, Point3D.from(x, y, z), player);
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(final World worldIn, final int meta) {
		return null;
	}
}
