package keelfy.witcher.entity.player.weight;

import keelfy.witcher.util.DAUtil;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

/**
 * @author keelfy
 */
public class VanillaItem {
	public static float getVanilaItem(Item item, int metadata) {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			if (item == Items.apple) return IVanilaItem.apple;
			else if (item == Items.porkchop) return IVanilaItem.porkchop;
			else if (item == Items.cooked_porkchop) return IVanilaItem.cooked_porkchop;
			else return 0.0F;
		}
		return 0;
	}
}
