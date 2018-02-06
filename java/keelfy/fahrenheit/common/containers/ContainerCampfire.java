/*
 *  Copyright (c) 2016-2017, Rubedo
 */

package keelfy.fahrenheit.common.containers;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.fahrenheit.common.FRegisterer;
import keelfy.fahrenheit.common.tileentities.TileEntityCampfire;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;

/**
 * @author keelfy
 * @created 6 окт. 2017 г.
 */
public class ContainerCampfire extends Container {

	private TileEntityCampfire tileFurnace;
	private int lastCookTime;
	private int lastBurnTime;
	private int lastItemBurnTime;

	public ContainerCampfire(InventoryPlayer inventory, TileEntityCampfire tile) {
		this.tileFurnace = tile;
		this.addSlotToContainer(new Slot(tile, 0, 41, 17));
		this.addSlotToContainer(new Slot(tile, 1, 41, 53));
		this.addSlotToContainer(new SlotFurnace(inventory.player, tile, 2, 101, 35));
		this.addSlotToContainer(new Slot(tile, 3, 11, 35) {
			@Override
			public boolean isItemValid(ItemStack stack) {
				return stack != null && stack.getItem() == FRegisterer.gas;
			}

			@Override
			public int getSlotStackLimit() {
				return 1;
			}
		});
		int i;

		for (i = 0; i < 3; ++i) {
			for (int j = 0; j < 9; ++j) {
				this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for (i = 0; i < 9; ++i) {
			this.addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 142));
		}
	}

	@Override
	public void addCraftingToCrafters(ICrafting crafting) {
		super.addCraftingToCrafters(crafting);
		crafting.sendProgressBarUpdate(this, 0, this.tileFurnace.furnaceCookTime);
		crafting.sendProgressBarUpdate(this, 1, this.tileFurnace.furnaceBurnTime);
		crafting.sendProgressBarUpdate(this, 2, this.tileFurnace.currentItemBurnTime);
	}

	/**
	 * Looks for changes made in the container, sends them to every listener.
	 */
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();

		for (int i = 0; i < this.crafters.size(); ++i) {
			ICrafting icrafting = (ICrafting) this.crafters.get(i);

			if (this.lastCookTime != this.tileFurnace.furnaceCookTime) {
				icrafting.sendProgressBarUpdate(this, 0, this.tileFurnace.furnaceCookTime);
			}

			if (this.lastBurnTime != this.tileFurnace.furnaceBurnTime) {
				icrafting.sendProgressBarUpdate(this, 1, this.tileFurnace.furnaceBurnTime);
			}

			if (this.lastItemBurnTime != this.tileFurnace.currentItemBurnTime) {
				icrafting.sendProgressBarUpdate(this, 2, this.tileFurnace.currentItemBurnTime);
			}
		}

		this.lastCookTime = this.tileFurnace.furnaceCookTime;
		this.lastBurnTime = this.tileFurnace.furnaceBurnTime;
		this.lastItemBurnTime = this.tileFurnace.currentItemBurnTime;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int p_75137_1_, int p_75137_2_) {
		if (p_75137_1_ == 0) {
			this.tileFurnace.furnaceCookTime = p_75137_2_;
		}

		if (p_75137_1_ == 1) {
			this.tileFurnace.furnaceBurnTime = p_75137_2_;
		}

		if (p_75137_1_ == 2) {
			this.tileFurnace.currentItemBurnTime = p_75137_2_;
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_) {
		return this.tileFurnace.isUseableByPlayer(p_75145_1_);
	}

	/**
	 * Called when a player shift-clicks on a slot. You must override this or you
	 * will crash when someone does that.
	 */
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int p_82846_2_) {
		ItemStack itemstack = null;
		Slot slot = (Slot) this.inventorySlots.get(p_82846_2_);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (p_82846_2_ == 2) {
				if (!this.mergeItemStack(itemstack1, 3, 39, true)) {
					return null;
				}

				slot.onSlotChange(itemstack1, itemstack);
			} else if (p_82846_2_ != 1 && p_82846_2_ != 0) {
				if (FurnaceRecipes.smelting().getSmeltingResult(itemstack1) != null) {
					if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
						return null;
					}
				} else if (TileEntityFurnace.isItemFuel(itemstack1)) {
					if (!this.mergeItemStack(itemstack1, 1, 2, false)) {
						return null;
					}
				} else if (p_82846_2_ >= 3 && p_82846_2_ < 30) {
					if (!this.mergeItemStack(itemstack1, 30, 39, false)) {
						return null;
					}
				} else if (p_82846_2_ >= 30 && p_82846_2_ < 39 && !this.mergeItemStack(itemstack1, 3, 30, false)) {
					return null;
				}
			} else if (!this.mergeItemStack(itemstack1, 3, 39, false)) {
				return null;
			}

			if (itemstack1.stackSize == 0) {
				slot.putStack((ItemStack) null);
			} else {
				slot.onSlotChanged();
			}

			if (itemstack1.stackSize == itemstack.stackSize) {
				return null;
			}

			slot.onPickupFromSlot(player, itemstack1);
		}

		return itemstack;
	}
}