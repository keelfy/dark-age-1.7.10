package keelfy.darkdata.inventory.loot;

import keelfy.darkdata.inventory.PlayerContainerUtils;
import keelfy.darkdata.inventory.slot.DASlot;
import keelfy.darkdata.inventory.slot.DASlotIcon;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

/**
 * @author keelfy
 * @created 29 июл. 2017 г.
 */
public class DAContainerChest extends ContainerChest {

	private IInventory lowerChestInventory;
	private int numRows;
	private boolean isVanilla;

	public DAContainerChest(final InventoryPlayer inventoryPlayer, final IInventory inventoryChest,
			boolean isVanillaContainer) {
		super(inventoryPlayer, inventoryChest);

		this.lowerChestInventory = inventoryChest;
		this.numRows = inventoryChest.getSizeInventory() / 9;

		if (isVanilla = isVanillaContainer)
			return;

		this.inventorySlots.clear();
		this.inventoryItemStacks.clear();

		inventoryChest.openInventory();
		int i = (this.numRows - 4) * 18;
		int j;
		int k;

		for (j = 0; j < this.numRows; ++j) {
			for (k = 0; k < 9; ++k) {
				this.addSlotToContainer(
						new DASlot(inventoryChest, k + j * 9, 8 + k * 18, 18 + j * 18, 64, DASlotIcon.DEFAULT, false));
			}
		}

		PlayerContainerUtils.addPlayerInventorySlots(this, inventoryPlayer, 17, (numRows > 3 ? 139 : 85));
	}

	@Override
	public boolean canInteractWith(final EntityPlayer player) {
		return isVanilla ? super.canInteractWith(player) : this.lowerChestInventory.isUseableByPlayer(player);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int meta) {
		if (isVanilla) {
			return super.transferStackInSlot(player, meta);
		}

		return null;
	}
}
