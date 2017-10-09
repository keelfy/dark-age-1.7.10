/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.inventory.slot;

import keelfy.darkdata.items.IFastUsable;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

/**
 * @author keelfy
 */
public final class DASlotHot extends DASlot {

	public DASlotHot(final IInventory inventory, final int par3, final int par4, final int par5) {
		super(inventory, par3, par4, par5, 64, DASlotIcon.HOT, false);
	}

	@Override
	public final boolean isItemValid(final ItemStack itemstack) {
		return itemstack.getItem() instanceof IFastUsable;
	}
}
