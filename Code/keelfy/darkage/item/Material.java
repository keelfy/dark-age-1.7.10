package keelfy.darkage.item;

import keelfy.darkage.util.DATab;
import net.minecraft.item.Item;

/**
 * @author keelfy
 */
public class Material extends DAItem {
	
	public Material() {
		this.setCreativeTab(DATab.tabMaterial);
	}
	
	@Override
	public Item setUnlocalizedName(String name) {
		unlocalizedName = "item." + name;
		return this;
	}
}
