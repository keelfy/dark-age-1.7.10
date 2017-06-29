/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.entities.player.weight;

import keelfytools.KeelfyUtils;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

/**
 * @author keelfy
 */
public class VanillaItem {
	public static float getVanilaItem(Item item, int metadata) {
		if(KeelfyUtils.isServerSide()) {
			if (item == Items.apple) return IVanilaItem.apple;
			else if (item == Items.porkchop) return IVanilaItem.porkchop;
			else if (item == Items.cooked_porkchop) return IVanilaItem.cooked_porkchop;
			else return 0.0F;
		}
		return 0;
	}
}
