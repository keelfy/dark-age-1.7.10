/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.inventory.slot;

import keelfy.darkage.items.Armor;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

/**
 * @author keelfy
 */
public class SlotArmor extends DASlot {

	private int type;
	public SlotArmor(IInventory inv, int id, int x, int y, int type) {
		super(inv, id, x, y, 1);
		this.type = type;
	}

	@Override
	public boolean isItemValid(ItemStack is) {
		return is.getItem() instanceof Armor && ((Armor)is.getItem()).getPart() == type;
	}
}
