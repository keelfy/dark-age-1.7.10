/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.items;

import keelfy.darkage.constants.EnumRarity;
import keelfy.darkage.constants.EnumTabs;
import net.minecraft.item.Item;

/**
 * @author keelfy
 */
public class Material extends DAItem {
	
	public Material() {
		this.setCreativeTab(EnumTabs.MATERIAL);
		this.setWeight(1F);
		this.setMaxStackSize(10);
		this.setRarity(EnumRarity.STUFF);
	}
	
	@Override
	public Item setUnlocalizedName(String name) {
		unlocalizedName = "item." + name;
		return this;
	}
}
