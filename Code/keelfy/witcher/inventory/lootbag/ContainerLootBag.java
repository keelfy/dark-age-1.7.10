package keelfy.witcher.inventory.lootbag;

import keelfy.witcherBlocks.tileentity.TileEntityLootBag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * @author keelfy
 */
public class ContainerLootBag extends Container {
	
	public TileEntityLootBag inventory;
	public ContainerLootBag(TileEntityLootBag inventoryCustom) {
		inventory = inventoryCustom;
	}
	
	@Override
	public boolean canDragIntoSlot(Slot p_94531_1_) {
		return false;
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int s) {
		return null;
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}
}
