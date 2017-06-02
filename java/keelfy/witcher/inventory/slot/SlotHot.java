package keelfy.witcher.inventory.slot;

import keelfy.witcher.item.IFastUsable;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

/**
 * @author keelfy
 */
public class SlotHot extends DASlot {

	public SlotHot(IInventory inventory, int par3, int par4, int par5) {
		super(inventory, par3, par4, par5, 64);
	}

	@Override
	public boolean isItemValid(ItemStack itemstack) {
		return itemstack.getItem() instanceof IFastUsable;
	}
}
