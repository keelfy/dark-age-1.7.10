/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.inventory.slot;

import keelfy.darkdata.constants.EnumSwordMaterial;
import keelfy.darkdata.items.Sword;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

/**
 * @author keelfy
 */
public final class DASlotSword extends DASlot {

	private EnumSwordMaterial swordType;

	public DASlotSword(final IInventory inventory, final int par3, final int par4, final int par5,
			final EnumSwordMaterial par6) {
		super(inventory, par3, par4, par5, 1,
				par6 == EnumSwordMaterial.SILVER ? DASlotIcon.SWORD_SILVER : DASlotIcon.SWORD_STEEL);
		this.swordType = par6;
	}

	@Override
	public final boolean isItemValid(final ItemStack itemstack) {
		return itemstack.getItem() instanceof Sword ? ((Sword) itemstack.getItem()).getType() == swordType : false;
	}
}
