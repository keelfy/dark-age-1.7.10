/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.inventory.slot;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfyutils.KUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;

/**
 * @author keelfy
 */
public class DASlot extends Slot {

	private final int limit;

	private boolean trackItem;

	private DASlotIcon icon;

	public DASlot(final IInventory inventory, final int id, final int posX, final int posY, final int stackLimit,
			DASlotIcon icon) {
		super(inventory, id, posX, posY);

		this.icon = icon;
		trackItem = true;
		limit = stackLimit;
	}

	public DASlot(final IInventory inventory, final int id, final int posX, final int posY, final int stackLimit,
			DASlotIcon icon, boolean track) {
		super(inventory, id, posX, posY);

		this.icon = icon;
		trackItem = track;
		limit = stackLimit;
	}

	public int getPosX() {
		return xDisplayPosition;
	}

	public int getPosY() {
		return yDisplayPosition;
	}

	public DASlotIcon getIcon() {
		return icon;
	}

	@Override
	public final int getSlotStackLimit() {
		return limit;
	}

	@Override
	public void putStack(final ItemStack stack) {
		if (KUtils.PROTECT_SERVER) {
			if (stack == null)
				return;

			if (!stack.hasTagCompound()) {
				stack.stackTagCompound = new NBTTagCompound();
			}

			if (trackItem) {
				stack.getTagCompound().setBoolean("inSlot", true);
			} else {
				if(stack.getTagCompound().hasKey("inSlot")) {
					stack.getTagCompound().removeTag("inSlot");
				}
			}
		}
		super.putStack(stack);
	}

	@Override
	public void onPickupFromSlot(final EntityPlayer player, final ItemStack stack) {
		if (KUtils.PROTECT_SERVER) {
			if (stack == null)
				return;

			if (!stack.hasTagCompound()) {
				stack.stackTagCompound = new NBTTagCompound();
			}

			if(stack.getTagCompound().hasKey("inSlot")) {
				stack.getTagCompound().removeTag("inSlot");
			}
			
			
		}
		super.onPickupFromSlot(player, stack);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public final IIcon getBackgroundIconIndex() {
		return null;
	}
}
