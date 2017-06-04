package keelfy.darkage.entity.player.weight;

import keelfy.darkage.entity.player.DAPlayer;
import keelfy.darkage.item.Armor;
import keelfy.darkage.item.DAItem;
import keelfy.darkage.item.IFastUsable;
import keelfy.darkage.item.Sword;
import keelfy.darkage.util.DAUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * @author keelfy
 */
public class PlayerWeight {
	
	public static float inventory(EntityPlayer player) {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			float weight = 0.0F;
	
			for (ItemStack slot : player.inventory.mainInventory)
				weight += check(slot);
		
			for(ItemStack slot : DAPlayer.get(player).inventory.inventoryStacks)
				weight += check(slot);
			
			for(int i = 0; i <4; i++) {
				if(player.getCurrentArmor(i) != null && player.getCurrentArmor(i).getItem() instanceof Armor)
					weight += ((Armor)player.getCurrentArmor(i).getItem()).getWeight();
			} return weight;
		} else
			return 0;
	}
	
	public static float check(ItemStack slot) {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			float weighting = 0.0F;
			if (slot != null) {
				Item item = slot.getItem();
				
				if (item instanceof DAItem) 
					weighting += ((DAItem)item).getWeight() * slot.stackSize;
				else if(item instanceof Sword)
					weighting += ((Sword)item).getWeight() * slot.stackSize;
				else if (VanillaItem.getVanilaItem(item, slot.getItemDamage()) != 0.0F)
					weighting += VanillaItem.getVanilaItem(item, slot.getItemDamage()) * slot.stackSize;
			}
			return weighting >= 0f ? weighting : 0f;
		} else
			return 0;
	}
}
