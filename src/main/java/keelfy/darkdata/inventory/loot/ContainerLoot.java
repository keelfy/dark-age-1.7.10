/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.inventory.loot;

import keelfy.darkdata.blocks.tileentities.TileEntityLoot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * @author keelfy
 */
public final class ContainerLoot extends Container {

	public TileEntityLoot inventory;

	public ContainerLoot(final TileEntityLoot inventory) {
		this.inventory = inventory;
	}

	@Override
	public final boolean canDragIntoSlot(final Slot slot) {
		return false;
	}

	@Override
	public final ItemStack transferStackInSlot(final EntityPlayer player, final int s) {
		return null;
	}

	@Override
	public final boolean canInteractWith(final EntityPlayer player) {
		return true;
	}
}
