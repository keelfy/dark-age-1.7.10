/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.entities.player.weight;

import keelfy.darkage.entities.player.DAPlayer;
import keelfy.darkage.items.Armor;
import keelfy.darkage.items.DAItem;
import keelfy.darkage.items.Sword;
import keelfytools.KeelfyUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * @author keelfy
 */
public class PlayerWeight {
	
	public static float inventory(EntityPlayer player) {
		if(KeelfyUtils.isServerSide()) {
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
		if(KeelfyUtils.isServerSide()) {
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
