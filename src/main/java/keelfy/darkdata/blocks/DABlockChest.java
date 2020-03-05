package keelfy.darkdata.blocks;

import static net.minecraftforge.common.util.ForgeDirection.*;

import java.util.Iterator;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfyutils.KUtils;
import keelfyutils.blocks.Point3D;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * @author keelfy
 * @created 29 июл. 2017 г.
 */
public final class DABlockChest extends DABlock {

	private final Random rand = new Random();
	public final int chestType;

	protected DABlockChest(final int chestType) {
		super(Material.wood, true);

		this.chestType = chestType;
		this.setCreativeTab(CreativeTabs.tabDecorations);
		this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
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
		return 22;
	}

	@Override
	public void setBlockBoundsBasedOnState(final IBlockAccess access, final int x, final int y, final int z) {
		if (access.getBlock(x, y, z - 1) == this) {
			this.setBlockBounds(0.0625F, 0.0F, 0.0F, 0.9375F, 0.875F, 0.9375F);
		} else if (access.getBlock(x, y, z + 1) == this) {
			this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 1.0F);
		} else if (access.getBlock(x - 1, y, z) == this) {
			this.setBlockBounds(0.0F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
		} else if (access.getBlock(x + 1, y, z) == this) {
			this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 1.0F, 0.875F, 0.9375F);
		} else {
			this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
		}
	}

	@Override
	public void onBlockAdded(final World world, final int x, final int y, final int z) {
		super.onBlockAdded(world, x, y, z);
		this.func_149954_e(world, x, y, z);
		Block block = world.getBlock(x, y, z - 1);
		Block block1 = world.getBlock(x, y, z + 1);
		Block block2 = world.getBlock(x - 1, y, z);
		Block block3 = world.getBlock(x + 1, y, z);

		if (block == this) {
			this.func_149954_e(world, x, y, z - 1);
		}

		if (block1 == this) {
			this.func_149954_e(world, x, y, z + 1);
		}

		if (block2 == this) {
			this.func_149954_e(world, x - 1, y, z);
		}

		if (block3 == this) {
			this.func_149954_e(world, x + 1, y, z);
		}
	}

	@Override
	public void onBlockPlacedBy(final World world, final Point3D pos, final EntityLivingBase entity,
			final ItemStack stack) {
		Block block = world.getBlock(pos.x, pos.y, pos.z - 1);
		Block block1 = world.getBlock(pos.x, pos.y, pos.z + 1);
		Block block2 = world.getBlock(pos.x - 1, pos.y, pos.z);
		Block block3 = world.getBlock(pos.x + 1, pos.y, pos.z);
		byte b0 = 0;
		int l = MathHelper.floor_double(entity.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

		if (l == 0) {
			b0 = 2;
		}

		if (l == 1) {
			b0 = 5;
		}

		if (l == 2) {
			b0 = 3;
		}

		if (l == 3) {
			b0 = 4;
		}

		if (block != this && block1 != this && block2 != this && block3 != this) {
			world.setBlockMetadataWithNotify(pos.x, pos.y, pos.z, b0, 3);
		} else {
			if ((block == this || block1 == this) && (b0 == 4 || b0 == 5)) {
				if (block == this) {
					world.setBlockMetadataWithNotify(pos.x, pos.y, pos.z - 1, b0, 3);
				} else {
					world.setBlockMetadataWithNotify(pos.x, pos.y, pos.z + 1, b0, 3);
				}

				world.setBlockMetadataWithNotify(pos.x, pos.y, pos.z, b0, 3);
			}

			if ((block2 == this || block3 == this) && (b0 == 2 || b0 == 3)) {
				if (block2 == this) {
					world.setBlockMetadataWithNotify(pos.x - 1, pos.y, pos.z, b0, 3);
				} else {
					world.setBlockMetadataWithNotify(pos.x + 1, pos.y, pos.z, b0, 3);
				}

				world.setBlockMetadataWithNotify(pos.x, pos.y, pos.z, b0, 3);
			}
		}

		if (stack.hasDisplayName()) {
			((TileEntityChest) world.getTileEntity(pos.x, pos.y, pos.z)).func_145976_a(stack.getDisplayName());
		}
	}

	public void func_149954_e(final World world, final int x, final int y, final int z) {
		if (KUtils.PROTECT_SERVER) {
			if (!world.isRemote) {
				Block block = world.getBlock(x, y, z - 1);
				Block block1 = world.getBlock(x, y, z + 1);
				Block block2 = world.getBlock(x - 1, y, z);
				Block block3 = world.getBlock(x + 1, y, z);
				boolean flag = true;
				int l;
				Block block4;
				int i1;
				Block block5;
				boolean flag1;
				byte b0;
				int j1;

				if (block != this && block1 != this) {
					if (block2 != this && block3 != this) {
						b0 = 3;

						if (block.func_149730_j() && !block1.func_149730_j()) {
							b0 = 3;
						}

						if (block1.func_149730_j() && !block.func_149730_j()) {
							b0 = 2;
						}

						if (block2.func_149730_j() && !block3.func_149730_j()) {
							b0 = 5;
						}

						if (block3.func_149730_j() && !block2.func_149730_j()) {
							b0 = 4;
						}
					} else {
						l = block2 == this ? x - 1 : x + 1;
						block4 = world.getBlock(l, y, z - 1);
						i1 = block2 == this ? x - 1 : x + 1;
						block5 = world.getBlock(i1, y, z + 1);
						b0 = 3;
						flag1 = true;

						if (block2 == this) {
							j1 = world.getBlockMetadata(x - 1, y, z);
						} else {
							j1 = world.getBlockMetadata(x + 1, y, z);
						}

						if (j1 == 2) {
							b0 = 2;
						}

						if ((block.func_149730_j() || block4.func_149730_j()) && !block1.func_149730_j()
								&& !block5.func_149730_j()) {
							b0 = 3;
						}

						if ((block1.func_149730_j() || block5.func_149730_j()) && !block.func_149730_j()
								&& !block4.func_149730_j()) {
							b0 = 2;
						}
					}
				} else {
					l = block == this ? z - 1 : z + 1;
					block4 = world.getBlock(x - 1, y, l);
					i1 = block == this ? z - 1 : z + 1;
					block5 = world.getBlock(x + 1, y, i1);
					b0 = 5;
					flag1 = true;

					if (block == this) {
						j1 = world.getBlockMetadata(x, y, z - 1);
					} else {
						j1 = world.getBlockMetadata(x, y, z + 1);
					}

					if (j1 == 4) {
						b0 = 4;
					}

					if ((block2.func_149730_j() || block4.func_149730_j()) && !block3.func_149730_j()
							&& !block5.func_149730_j()) {
						b0 = 5;
					}

					if ((block3.func_149730_j() || block5.func_149730_j()) && !block2.func_149730_j()
							&& !block4.func_149730_j()) {
						b0 = 4;
					}
				}

				world.setBlockMetadataWithNotify(x, y, z, b0, 3);
			}
		}
	}

	@Override
	public boolean canPlaceBlockAt(final World world, final int x, final int y, final int z) {
		int l = 0;

		if (world.getBlock(x - 1, y, z) == this) {
			++l;
		}

		if (world.getBlock(x + 1, y, z) == this) {
			++l;
		}

		if (world.getBlock(x, y, z - 1) == this) {
			++l;
		}

		if (world.getBlock(x, y, z + 1) == this) {
			++l;
		}

		return l > 1 ? false
				: (this.blockEqualsThis(world, x - 1, y, z) ? false
						: (this.blockEqualsThis(world, x + 1, y, z) ? false
								: (this.blockEqualsThis(world, x, y, z - 1) ? false
										: !this.blockEqualsThis(world, x, y, z + 1))));
	}

	private boolean blockEqualsThis(final World world, final int x, final int y, final int z) {
		return world.getBlock(x, y, z) != this ? false
				: (world.getBlock(x - 1, y, z) == this ? true
						: (world.getBlock(x + 1, y, z) == this ? true
								: (world.getBlock(x, y, z - 1) == this ? true : world.getBlock(x, y, z + 1) == this)));
	}

	@Override
	public void onNeighborBlockChange(final World world, final int x, final int y, final int z, final Block block) {
		super.onNeighborBlockChange(world, x, y, z, block);
		TileEntityChest tileentitychest = (TileEntityChest) world.getTileEntity(x, y, z);

		if (tileentitychest != null) {
			tileentitychest.updateContainingBlockInfo();
		}
	}

	@Override
	public void breakBlock(final World world, final int x, final int y, final int z, final Block block,
			final int meta) {
		TileEntityChest tileentitychest = (TileEntityChest) world.getTileEntity(x, y, z);

		if (tileentitychest != null) {
			for (int i1 = 0; i1 < tileentitychest.getSizeInventory(); ++i1) {
				ItemStack itemstack = tileentitychest.getStackInSlot(i1);

				if (itemstack != null) {
					float f = this.rand.nextFloat() * 0.8F + 0.1F;
					float f1 = this.rand.nextFloat() * 0.8F + 0.1F;
					EntityItem entityitem;

					for (float f2 = this.rand.nextFloat() * 0.8F + 0.1F; itemstack.stackSize > 0; world
							.spawnEntityInWorld(entityitem)) {
						int j1 = this.rand.nextInt(21) + 10;

						if (j1 > itemstack.stackSize) {
							j1 = itemstack.stackSize;
						}

						itemstack.stackSize -= j1;
						entityitem = new EntityItem(world, x + f, y + f1, z + f2,
								new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));
						float f3 = 0.05F;
						entityitem.motionX = (float) this.rand.nextGaussian() * f3;
						entityitem.motionY = (float) this.rand.nextGaussian() * f3 + 0.2F;
						entityitem.motionZ = (float) this.rand.nextGaussian() * f3;

						if (itemstack.hasTagCompound()) {
							entityitem.getEntityItem()
									.setTagCompound((NBTTagCompound) itemstack.getTagCompound().copy());
						}
					}
				}
			}

			world.func_147453_f(x, y, z, block);
		}

		super.breakBlock(world, x, y, z, block, meta);
	}

	@Override
	public boolean onBlockActivated(final World world, final Point3D pos, final EntityPlayer player) {
		if (world.isRemote)
			return true;

		if (KUtils.PROTECT_SERVER) {
			IInventory iinventory = this.getChestInventory(world, pos.x, pos.y, pos.z);

			if (iinventory != null) {
				player.displayGUIChest(iinventory);
			}
		}
		return true;
	}

	public IInventory getChestInventory(final World world, final int x, final int y, final int z) {
		Object object = world.getTileEntity(x, y, z);

		if (object == null)
			return null;
		else if (world.isSideSolid(x, y + 1, z, DOWN))
			return null;
		else if (func_149953_o(world, x, y, z))
			return null;
		else if (world.getBlock(x - 1, y, z) == this
				&& (world.isSideSolid(x - 1, y + 1, z, DOWN) || func_149953_o(world, x - 1, y, z)))
			return null;
		else if (world.getBlock(x + 1, y, z) == this
				&& (world.isSideSolid(x + 1, y + 1, z, DOWN) || func_149953_o(world, x + 1, y, z)))
			return null;
		else if (world.getBlock(x, y, z - 1) == this
				&& (world.isSideSolid(x, y + 1, z - 1, DOWN) || func_149953_o(world, x, y, z - 1)))
			return null;
		else if (world.getBlock(x, y, z + 1) == this
				&& (world.isSideSolid(x, y + 1, z + 1, DOWN) || func_149953_o(world, x, y, z + 1)))
			return null;
		else {
			if (world.getBlock(x - 1, y, z) == this) {
				object = new InventoryLargeChest("container.chestDouble",
						(TileEntityChest) world.getTileEntity(x - 1, y, z), (IInventory) object);
			}

			if (world.getBlock(x + 1, y, z) == this) {
				object = new InventoryLargeChest("container.chestDouble", (IInventory) object,
						(TileEntityChest) world.getTileEntity(x + 1, y, z));
			}

			if (world.getBlock(x, y, z - 1) == this) {
				object = new InventoryLargeChest("container.chestDouble",
						(TileEntityChest) world.getTileEntity(x, y, z - 1), (IInventory) object);
			}

			if (world.getBlock(x, y, z + 1) == this) {
				object = new InventoryLargeChest("container.chestDouble", (IInventory) object,
						(TileEntityChest) world.getTileEntity(x, y, z + 1));
			}

			return (IInventory) object;
		}
	}

	@Override
	public TileEntity createNewTileEntity(final World world, final int meta) {
		return new TileEntityChest();
	}

	@Override
	public boolean canProvidePower() {
		return this.chestType == 1;
	}

	@Override
	public int isProvidingWeakPower(final IBlockAccess access, final int x, final int y, final int z, final int meta) {
		if (!this.canProvidePower())
			return 0;
		else {
			int i1 = ((TileEntityChest) access.getTileEntity(x, y, z)).numPlayersUsing;
			return MathHelper.clamp_int(i1, 0, 15);
		}
	}

	@Override
	public int isProvidingStrongPower(final IBlockAccess access, final int x, final int y, final int z,
			final int meta) {
		return meta == 1 ? this.isProvidingWeakPower(access, x, y, z, meta) : 0;
	}

	private static boolean func_149953_o(final World world, final int x, final int y, final int z) {

		Iterator iterator = world.getEntitiesWithinAABB(EntityOcelot.class,
				AxisAlignedBB.getBoundingBox(x, y + 1, z, x + 1, y + 2, z + 1)).iterator();
		EntityOcelot entityocelot;

		do {
			if (!iterator.hasNext())
				return false;

			Entity entity = (Entity) iterator.next();
			entityocelot = (EntityOcelot) entity;
		} while (!entityocelot.isSitting());

		return true;
	}

	@Override
	public boolean hasComparatorInputOverride() {
		return true;
	}

	@Override
	public int getComparatorInputOverride(final World world, final int x, final int y, final int z, final int meta) {
		return Container.calcRedstoneFromInventory(this.getChestInventory(world, x, y, z));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(final IIconRegister registry) {
		this.blockIcon = registry.registerIcon("planks_oak");
	}
}
