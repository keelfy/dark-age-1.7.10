/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.inventory.slot;

import keelfy.darkage.items.Armor;
import keelfy.darkage.items.Food;
import keelfy.darkage.items.Sword;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

/**
 * @author keelfy
 */
public class SlotOther extends DASlot {

	public SlotOther(IInventory inventory, int id, int posX, int posY) {
		super(inventory, id, posX, posY, 64);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		boolean result = false;
		if(!(stack.getItem() instanceof Armor) && !(stack.getItem() instanceof Food) && !(stack.getItem() instanceof Sword) && !(stack.getItem() instanceof ItemBlock)) {
			result = true;
		}
		return result;
	}
}
