/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.inventory.player;

import keelfy.darkdata.constants.EnumSwordMaterial;
import keelfy.darkdata.inventory.slot.DASlot;
import keelfy.darkdata.inventory.slot.DASlotArmor;
import keelfy.darkdata.inventory.slot.DASlotHot;
import keelfy.darkdata.inventory.slot.DASlotIcon;
import keelfy.darkdata.inventory.slot.DASlotOther;
import keelfy.darkdata.inventory.slot.DASlotSword;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * @author keelfy
 */
public final class DAContainerPlayerInventory extends Container {

	private DAPlayerInventory inventory;
	
	public static final int SID_SWORD_STEEL = 1;
	public static final int SID_SWORD_SILVER = 2;
	public static final int SID_GLOVES = 39;
	public static final int SID_CHESTPLATE = 38;
	public static final int SID_PANTS = 37;
	public static final int SID_BOOTS = 36;
	public static final int SIDs_HOT[] = new int[] { 8, 9, 10, 11 };
	public static final int SIDs_OTHER[] = new int[] { 6, 3 };
	public static final int SID_INVENTORY = 12;
	
	public DAContainerPlayerInventory(final EntityPlayer player, final DAPlayerInventory inventory) {
		this.inventory = inventory;
		
		final InventoryPlayer inv = player.inventory;

		int posX = 200;
		int posY = 50;

		int firstX = posX + 11;
		int secondX = firstX + 29; // + 40
		int thirdX = secondX + 29; // + 70
		int fourthX = thirdX + 29; // + 99
		
		int firstY = posY + 2;
		int secondY = firstY + 52; // + 54
		int thirdY = secondY + 43; // + 97
		
		this.addSlotToContainer(new DASlotSword(inv, SID_SWORD_STEEL, firstX, firstY, EnumSwordMaterial.STEEL));
		this.addSlotToContainer(new DASlotSword(inv, SID_SWORD_SILVER, secondX, firstY, EnumSwordMaterial.SILVER));

		this.addSlotToContainer(new DASlotArmor(inv, SID_GLOVES, fourthX, firstY, DASlotIcon.GLOVES));
		this.addSlotToContainer(new DASlotArmor(inv, SID_CHESTPLATE, thirdX, firstY, DASlotIcon.CHESTPLATE));
		this.addSlotToContainer(new DASlotArmor(inv, SID_PANTS, thirdX, secondY, DASlotIcon.PANTS));
		this.addSlotToContainer(new DASlotArmor(inv, SID_BOOTS, fourthX, secondY, DASlotIcon.BOOTS));

		this.addSlotToContainer(new DASlotHot(inv, SIDs_HOT[0], firstX, thirdY));
		this.addSlotToContainer(new DASlotHot(inv, SIDs_HOT[1], secondX, thirdY));
		this.addSlotToContainer(new DASlotHot(inv, SIDs_HOT[2], thirdX, thirdY));
		this.addSlotToContainer(new DASlotHot(inv, SIDs_HOT[3], fourthX, thirdY));

		this.addSlotToContainer(new DASlotOther(inv, SIDs_OTHER[0], firstX, secondY));
		this.addSlotToContainer(new DASlotOther(inv, SIDs_OTHER[1], secondX, secondY));

		posX = posX - 170;
		posY = posY - 4;

		for (int j = 0; j < 6; ++j) {
			for (int k = 0; k < 4; ++k) {
				Slot slot = new DASlot(inv, SID_INVENTORY + j + k * 6, 21 * j + posX, 21 * k + posY, 64, DASlotIcon.DEFAULT,
						false);
				this.addSlotToContainer(slot);
			}
		}
	}
	
	@Override
	public final boolean canInteractWith(final EntityPlayer player) {
		return true;
	}
}
