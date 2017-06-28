/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.inventory.slot;

import keelfy.darkage.constants.EnumSwordMaterial;
import keelfy.darkage.items.Sword;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

/**
 * @author keelfy
 */
public class SlotSword extends DASlot {

	private EnumSwordMaterial swordType;
	public SlotSword(IInventory inventory, int par3, int par4, int par5, EnumSwordMaterial par6) {
		super(inventory, par3, par4, par5, 1);
		this.swordType = par6;
	}

	@Override
	public boolean isItemValid(ItemStack itemstack) {
		if(itemstack.getItem() instanceof Sword) {
			return ((Sword)itemstack.getItem()).getType() == swordType; 
		}
		return false;
	}
}
