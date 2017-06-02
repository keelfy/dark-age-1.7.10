package keelfy.witcher.inventory.slot;

import keelfy.witcher.item.SwordSilver;
import keelfy.witcher.item.SwordSteel;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

/**
 * @author keelfy
 */
public class SlotSword extends DASlot {

	private int swordType;
	public SlotSword(IInventory inventory, int par3, int par4, int par5, int par6) {
		super(inventory, par3, par4, par5, 1);
		this.swordType = par6;
	}

	@Override
	public boolean isItemValid(ItemStack itemstack) {
		 return swordType == 0 && itemstack.getItem() instanceof SwordSilver ? true : 
			 swordType == 1 && itemstack.getItem() instanceof SwordSteel;
	}
}
