/*
 *  Copyright (c) 2016-2017, Rubedo
 */

package keelfy.fahrenheit.common.tileentities;

import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.fahrenheit.common.player.managers.FPlayerManager;
import keelfyutils.KUtils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

/**
 * @author keelfy
 * @created 6 окт. 2017 г.
 */
public class TileEntityCampfire extends TileEntity implements ISidedInventory {

	private static final int[] slotsTop = new int[] { 0 };
	private static final int[] slotsBottom = new int[] { 2, 1 };
	private static final int[] slotsSides = new int[] { 1 };
	/** The ItemStacks that hold the items currently being used in the furnace */
	private ItemStack[] furnaceItemStacks = new ItemStack[4];
	/** The number of ticks that the furnace will keep burning */
	public int furnaceBurnTime;
	/**
	 * The number of ticks that a fresh copy of the currently-burning item would
	 * keep the furnace burning for
	 */
	public int currentItemBurnTime;
	/** The number of ticks that the current item has been cooking for */
	public int furnaceCookTime;
	public boolean furnaceStarted = false;

	/**
	 * Returns the number of slots in the inventory.
	 */
	@Override
	public int getSizeInventory() {
		return this.furnaceItemStacks.length;
	}

	/**
	 * Returns the stack in slot i
	 */
	@Override
	public ItemStack getStackInSlot(int slot) {
		return this.furnaceItemStacks[slot];
	}

	/**
	 * Removes from an inventory slot (first arg) up to a specified number (second
	 * arg) of items and returns them in a new stack.
	 */
	@Override
	public ItemStack decrStackSize(int slot, int p_70298_2_) {
		if (this.furnaceItemStacks[slot] != null) {
			ItemStack itemstack;

			if (this.furnaceItemStacks[slot].stackSize <= p_70298_2_) {
				itemstack = this.furnaceItemStacks[slot];
				this.furnaceItemStacks[slot] = null;
				return itemstack;
			} else {
				itemstack = this.furnaceItemStacks[slot].splitStack(p_70298_2_);

				if (this.furnaceItemStacks[slot].stackSize == 0) {
					this.furnaceItemStacks[slot] = null;
				}

				return itemstack;
			}
		} else {
			return null;
		}
	}

	/**
	 * When some containers are closed they call this on each slot, then drop
	 * whatever it returns as an EntityItem - like when you close a workbench GUI.
	 */
	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		if (this.furnaceItemStacks[slot] != null) {
			ItemStack itemstack = this.furnaceItemStacks[slot];
			this.furnaceItemStacks[slot] = null;
			return itemstack;
		} else {
			return null;
		}
	}

	/**
	 * Sets the given item stack to the specified slot in the inventory (can be
	 * crafting or armor sections).
	 */
	@Override
	public void setInventorySlotContents(int slotId, ItemStack stack) {
		this.furnaceItemStacks[slotId] = stack;

		if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
			stack.stackSize = this.getInventoryStackLimit();
		}
	}

	/**
	 * Returns the name of the inventory
	 */
	@Override
	public String getInventoryName() {
		return "Campfire";
	}

	/**
	 * Returns if the inventory is named
	 */
	@Override
	public boolean hasCustomInventoryName() {
		return true;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		NBTTagList nbttaglist = compound.getTagList("Items", 10);
		this.furnaceItemStacks = new ItemStack[this.getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			byte b0 = nbttagcompound1.getByte("Slot");

			if (b0 >= 0 && b0 < this.furnaceItemStacks.length) {
				this.furnaceItemStacks[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}

		this.furnaceBurnTime = compound.getShort("BurnTime");
		this.furnaceCookTime = compound.getShort("CookTime");
		this.currentItemBurnTime = getItemBurnTime(this.furnaceItemStacks[1]);
		this.furnaceStarted = compound.getBoolean("Started");
	}

	@Override
	public void writeToNBT(NBTTagCompound compound) {
		if (KUtils.PROTECT_SERVER) {
			super.writeToNBT(compound);
			compound.setShort("BurnTime", (short) this.furnaceBurnTime);
			compound.setShort("CookTime", (short) this.furnaceCookTime);
			NBTTagList nbttaglist = new NBTTagList();

			for (int i = 0; i < this.furnaceItemStacks.length; ++i) {
				if (this.furnaceItemStacks[i] != null) {
					NBTTagCompound nbttagcompound1 = new NBTTagCompound();
					nbttagcompound1.setByte("Slot", (byte) i);
					this.furnaceItemStacks[i].writeToNBT(nbttagcompound1);
					nbttaglist.appendTag(nbttagcompound1);
				}
			}

			compound.setTag("Items", nbttaglist);
			compound.setBoolean("Started", furnaceStarted);
		}
	}

	/**
	 * Returns the maximum stack size for a inventory slot.
	 */
	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	/**
	 * Returns an integer between 0 and the passed value representing how close the
	 * current item is to being completely cooked
	 */
	@SideOnly(Side.CLIENT)
	public int getCookProgressScaled(int scale) {
		return this.furnaceCookTime * scale / 200;
	}

	/**
	 * Returns an integer between 0 and the passed value representing how much burn
	 * time is left on the current fuel item, where 0 means that the item is
	 * exhausted and the passed value means that the item is fresh
	 */
	@SideOnly(Side.CLIENT)
	public int getBurnTimeRemainingScaled(int scale) {
		if (this.currentItemBurnTime == 0) {
			this.currentItemBurnTime = 200;
		}

		return this.furnaceBurnTime * scale / this.currentItemBurnTime;
	}

	/**
	 * Furnace isBurning
	 */
	public boolean isBurning() {
		return this.furnaceBurnTime > 0;
	}

	@Override
	public void updateEntity() {
		boolean flag = this.furnaceBurnTime > 0;
		boolean flag1 = false;

		if (this.furnaceBurnTime > 0) {
			--this.furnaceBurnTime;
		}

		if (KUtils.PROTECT_SERVER) {
			if (!this.worldObj.isRemote) {
				if (this.furnaceStarted && this.furnaceBurnTime == 0 && this.furnaceCookTime == 0 && this.furnaceItemStacks[1] == null) {
					this.furnaceStarted = false;
				}

				if (this.furnaceItemStacks[0] == null && this.furnaceCookTime != 0) {
					this.furnaceCookTime = 0;
				}

				if (this.furnaceStarted && this.furnaceBurnTime != 0 || this.furnaceItemStacks[1] != null) {

					AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(xCoord - 4, yCoord - 4, zCoord - 4, xCoord + 4, yCoord + 4, zCoord + 4);
					List<EntityPlayer> players = worldObj.getEntitiesWithinAABB(EntityPlayer.class, aabb);

					for (EntityPlayer player : players)
						FPlayerManager.getPlayer(player).temperature.change(0.0052F);

					if (this.furnaceBurnTime == 0 && this.canSmelt()) {
						this.currentItemBurnTime = this.furnaceBurnTime = getItemBurnTime(this.furnaceItemStacks[1]);

						if (this.furnaceBurnTime > 0) {
							flag1 = true;

							if (this.furnaceItemStacks[1] != null) {
								--this.furnaceItemStacks[1].stackSize;

								if (this.furnaceItemStacks[1].stackSize == 0) {
									this.furnaceItemStacks[1] = furnaceItemStacks[1].getItem().getContainerItem(furnaceItemStacks[1]);
								}
							}
						}
					}

					if (this.isBurning() && this.canSmelt()) {
						++this.furnaceCookTime;

						if (this.furnaceCookTime == 200) {
							this.furnaceCookTime = 0;
							this.smeltItem();
							flag1 = true;
						}
					} else {
						this.furnaceCookTime = 0;
					}
				}

				if (flag != this.furnaceBurnTime > 0) {
					flag1 = true;
					// BlockFurnace.updateFurnaceBlockState(this.furnaceBurnTime > 0, this.worldObj,
					// this.xCoord, this.yCoord, this.zCoord);
				}
			}
		}
		if (flag1) {
			this.markDirty();
		}
	}

	/**
	 * Returns true if the furnace can smelt an item, i.e. has a source item,
	 * destination stack isn't full, etc.
	 */
	private boolean canSmelt() {
		if (!furnaceStarted) {
			return false;
		} else if (this.furnaceItemStacks[0] == null) {
			return true;
		} else {
			ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.furnaceItemStacks[0]);
			if (itemstack == null)
				return false;
			if (this.furnaceItemStacks[2] == null)
				return true;
			if (!this.furnaceItemStacks[2].isItemEqual(itemstack))
				return false;
			int result = furnaceItemStacks[2].stackSize + itemstack.stackSize;
			return result <= getInventoryStackLimit() && result <= this.furnaceItemStacks[2].getMaxStackSize(); // Forge BugFix: Make it respect stack sizes properly.
		}
	}

	/**
	 * Turn one item from the furnace source stack into the appropriate smelted item
	 * in the furnace result stack
	 */
	public void smeltItem() {
		if (this.canSmelt() && this.furnaceItemStacks[0] != null) {
			ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.furnaceItemStacks[0]);

			if (this.furnaceItemStacks[2] == null) {
				this.furnaceItemStacks[2] = itemstack.copy();
			} else if (this.furnaceItemStacks[2].getItem() == itemstack.getItem()) {
				this.furnaceItemStacks[2].stackSize += itemstack.stackSize; // Forge BugFix: Results may have multiple items
			}

			--this.furnaceItemStacks[0].stackSize;

			if (this.furnaceItemStacks[0].stackSize <= 0) {
				this.furnaceItemStacks[0] = null;
			}
		}
	}

	/**
	 * Returns the number of ticks that the supplied fuel item will keep the furnace
	 * burning, or 0 if the item isn't fuel
	 */
	public static int getItemBurnTime(ItemStack stack) {
		if (stack == null) {
			return 0;
		} else {
			Item item = stack.getItem();

			if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.air) {
				Block block = Block.getBlockFromItem(item);

				if (block == Blocks.wooden_slab) {
					return 150;
				}

				if (block.getMaterial() == Material.wood) {
					return 300;
				}

				if (block == Blocks.coal_block) {
					return 16000;
				}
			}

			if (item instanceof ItemTool && ((ItemTool) item).getToolMaterialName().equals("WOOD"))
				return 200;
			if (item instanceof ItemSword && ((ItemSword) item).getToolMaterialName().equals("WOOD"))
				return 200;
			if (item instanceof ItemHoe && ((ItemHoe) item).getToolMaterialName().equals("WOOD"))
				return 200;
			if (item == Items.stick)
				return 100;
			if (item == Items.coal)
				return 1600;
			if (item == Items.lava_bucket)
				return 20000;
			if (item == Item.getItemFromBlock(Blocks.sapling))
				return 100;
			if (item == Items.blaze_rod)
				return 2400;
			return GameRegistry.getFuelValue(stack);
		}
	}

	public static boolean isItemFuel(ItemStack stack) {
		/**
		 * Returns the number of ticks that the supplied fuel item will keep the furnace
		 * burning, or 0 if the item isn't fuel
		 */
		return getItemBurnTime(stack) > 0;
	}

	/**
	 * Do not make give this method the name canInteractWith because it clashes with
	 * Container
	 */
	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : player.getDistanceSq(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public void openInventory() {}

	@Override
	public void closeInventory() {}

	/**
	 * Returns true if automation is allowed to insert the given stack (ignoring
	 * stack size) into the given slot.
	 */
	@Override
	public boolean isItemValidForSlot(int slotId, ItemStack stack) {
		return slotId == 2 ? false : (slotId == 1 ? isItemFuel(stack) : true);
	}

	/**
	 * Returns an array containing the indices of the slots that can be accessed by
	 * automation on the given side of this block.
	 */
	@Override
	public int[] getAccessibleSlotsFromSide(int slotId) {
		return slotId == 0 ? slotsBottom : (slotId == 1 ? slotsTop : slotsSides);
	}

	/**
	 * Returns true if automation can insert the given item in the given slot from
	 * the given side. Args: Slot, item, side
	 */
	@Override
	public boolean canInsertItem(int slotId, ItemStack stack, int meta) {
		return this.isItemValidForSlot(slotId, stack);
	}

	/**
	 * Returns true if automation can extract the given item in the given slot from
	 * the given side. Args: Slot, item, side
	 */
	@Override
	public boolean canExtractItem(int slotId, ItemStack stack, int meta) {
		return meta != 0 || slotId != 1 || stack.getItem() == Items.bucket;
	}
}