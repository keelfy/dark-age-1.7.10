/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.inventory.slot;

import keelfy.darkdata.items.Armor;
import keelfy.darkdata.items.Sword;
import net.minecraft.block.Block;
import net.minecraft.block.BlockTorch;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

/**
 * @author keelfy
 */
public final class DASlotOther extends DASlot {

	public DASlotOther(final IInventory inventory, final int id, final int posX, final int posY) {
		super(inventory, id, posX, posY, 64, DASlotIcon.OTHER);
	}

	@Override
	public final boolean isItemValid(final ItemStack stack) {
		final Item i = stack.getItem();
		final Block block = stack.getItem() instanceof ItemBlock ? Block.getBlockFromItem(stack.getItem()) : null;

		if (block != null)
			return block instanceof BlockTorch;

		return (!(i instanceof Armor) && !(i instanceof Sword)) && !(i instanceof ItemArmor)
				&& !(i instanceof ItemSword) && !(i instanceof ItemPotion);
	}
}
