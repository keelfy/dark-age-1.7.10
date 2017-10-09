/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.inventory.slot;

import keelfy.darkdata.constants.EnumArmorPart;
import keelfy.darkdata.items.Armor;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

/**
 * @author keelfy
 */
public final class DASlotArmor extends DASlot {

	private EnumArmorPart type;

	public DASlotArmor(final IInventory inv, final int id, final int x, final int y, DASlotIcon type) {
		super(inv, id, x, y, 1, type);

		this.type = EnumArmorPart.valueOf(type.name());
	}

	@Override
	public final boolean isItemValid(final ItemStack is) {
		return is.getItem() instanceof Armor && ((Armor) is.getItem()).getPartEnum() == type;
	}
}
