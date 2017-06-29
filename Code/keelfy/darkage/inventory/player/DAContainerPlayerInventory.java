/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.inventory.player;

import keelfy.darkage.constants.EnumSwordMaterial;
import keelfy.darkage.inventory.slot.SlotArmor;
import keelfy.darkage.inventory.slot.SlotHot;
import keelfy.darkage.inventory.slot.SlotOther;
import keelfy.darkage.inventory.slot.SlotSword;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * @author keelfy
 */
public class DAContainerPlayerInventory extends Container {

	public DAContainerPlayerInventory(EntityPlayer player, DAPlayerInventory inventoryCustom) {
		InventoryPlayer inventoryPlayer = player.inventory;
		
		this.addSlotToContainer(new SlotSword(inventoryPlayer, 1, 210, 52, EnumSwordMaterial.STEEL));
		this.addSlotToContainer(new SlotSword(inventoryPlayer, 2, 240, 52, EnumSwordMaterial.SILVER));

		this.addSlotToContainer(new SlotArmor(inventoryPlayer, 38, 270, 52, 0));
		this.addSlotToContainer(new SlotArmor(inventoryPlayer, 39, 299, 52, 1));
		this.addSlotToContainer(new SlotArmor(inventoryPlayer, 37, 270, 104, 2));
		this.addSlotToContainer(new SlotArmor(inventoryPlayer, 36, 299, 104, 3));

		this.addSlotToContainer(new SlotHot(inventoryPlayer, 8, 209, 147));
		this.addSlotToContainer(new SlotHot(inventoryPlayer, 9, 241, 147));
		this.addSlotToContainer(new SlotHot(inventoryPlayer, 10, 270, 147));
		this.addSlotToContainer(new SlotHot(inventoryPlayer, 11, 299, 147));
		
		this.addSlotToContainer(new SlotOther(inventoryPlayer, 6, 210, 104));
		this.addSlotToContainer(new SlotOther(inventoryPlayer, 3, 241, 104));

		for (int j = 0; j < 6; ++j) {
			for (int k = 0; k < 4; ++k) {
				this.addSlotToContainer(new Slot(inventoryPlayer, 12 + j + k * 6, 21 * j + 30, 21 * k + 46));
			}
		}
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int s) {
		ItemStack itemstack = null;
		Slot slot = (Slot) this.inventorySlots.get(s);
		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
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

		return null;
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}
}
