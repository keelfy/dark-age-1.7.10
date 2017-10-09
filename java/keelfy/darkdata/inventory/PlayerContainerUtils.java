package keelfy.darkdata.inventory;

import keelfy.darkdata.inventory.slot.DASlot;
import keelfy.darkdata.inventory.slot.DASlotIcon;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

/**
 * @author keelfy
 * @created 11 авг. 2017 г.
 */
public class PlayerContainerUtils {

	public static final void addPlayerInventorySlots(Container container, InventoryPlayer inventory) {
		for (int j = 0; j < 3; ++j) {
			for (int k = 0; k < 9; ++k) {
				final int id = k + j * 9 + 9;

				Slot slot = null;
				
				if (id == 9 || id == 10 || id == 11) {
					continue;
				} else if (id == 17) {
					slot = new DASlot(inventory, id, 18 * 0, 18 * 0, 64, DASlotIcon.DEFAULT, false);
				} else if (id == 26) {
					slot = new DASlot(inventory, id, 18 * 1, 18 * 0, 64, DASlotIcon.DEFAULT, false);
				} else if (id == 35) {
					slot = new DASlot(inventory, id, 18 * 2, 18 * 0, 64, DASlotIcon.DEFAULT, false);
				} else {
					slot = new DASlot(inventory, id, 18 * k, 18 * j, 64, DASlotIcon.DEFAULT, false);
				}
				
				addSlotToContainer(container, slot);
			}
		}
	}

	public static final void addPlayerInventorySlots(Container container, InventoryPlayer inventory, int startX, int startY) {
		for (int j = 0; j < 3; ++j) {
			for (int k = 0; k < 9; ++k) {
				final int id = k + j * 9 + 9;
				
				Slot slot = null;
				
				if (id == 9 || id == 10 || id == 11) {
					continue;
				} else if (id == 17) {
					slot = new DASlot(inventory, id, startX + 18 * 0, startY + 18 * 0, 64, DASlotIcon.DEFAULT, false);
				} else if (id == 26) {
					slot = new DASlot(inventory, id, startX + 18 * 1, startY + 18 * 0, 64, DASlotIcon.DEFAULT, false);
				} else if (id == 35) {
					slot = new DASlot(inventory, id, startX + 18 * 2, startY + 18 * 0, 64, DASlotIcon.DEFAULT, false);
				} else {
					slot = new DASlot(inventory, id, startX + 18 * k, startY + 18 * j, 64, DASlotIcon.DEFAULT, false);
				}
				
				addSlotToContainer(container, slot);
			}
		}
	}
	
	public static final void addSlotToContainer(Container container, Slot slot) {
		slot.slotNumber = container.inventorySlots.size();
		container.inventorySlots.add(slot);
		container.inventoryItemStacks.add((Object) null);
	}

}
