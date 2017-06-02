package keelfy.witcher.item;

import keelfy.witcher.util.DATab;

/**
 * @author keelfy
 */
public class Material extends DAItem {
	
	public Material() {
		this.setCreativeTab(DATab.wcMaterial);
		this.setMaxStackSize(10);
		this.setRarity(ItemRarity.STUFF);
		this.setWeight(1F);
	}
}
