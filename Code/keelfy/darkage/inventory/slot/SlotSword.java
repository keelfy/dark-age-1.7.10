package keelfy.darkage.inventory.slot;

import keelfy.darkage.item.Sword;
import keelfy.darkage.item.Sword.SwordType;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

/**
 * @author keelfy
 */
public class SlotSword extends DASlot {

	private SwordType swordType;
	public SlotSword(IInventory inventory, int par3, int par4, int par5, SwordType par6) {
		super(inventory, par3, par4, par5, 1);
		this.swordType = par6;
	}

	@Override
	public boolean isItemValid(ItemStack itemstack) {
		 return itemstack.getItem() instanceof Sword ? ((Sword)itemstack.getItem()).getType() == swordType : false; 
	}
}
