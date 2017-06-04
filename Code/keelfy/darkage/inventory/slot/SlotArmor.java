package keelfy.darkage.inventory.slot;

import keelfy.darkage.item.Armor;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

/**
 * @author keelfy
 */
public class SlotArmor extends DASlot {

	private int type;
	public SlotArmor(IInventory inv, int id, int x, int y, int type) {
		super(inv, id, x, y, 1);
		this.type = type;
	}

	public boolean isItemValid(ItemStack is) {
		return is.getItem() instanceof Armor && ((Armor)is.getItem()).getPart() == type;
	}
}
