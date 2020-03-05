/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.items;

import keelfy.darkdata.constants.EnumRarity;
import keelfy.darkdata.handlers.DATabsHandler;
import net.minecraft.item.Item;

/**
 * @author keelfy
 */
public final class Material extends DAItem {

	public Material() {
		this.setCreativeTab(DATabsHandler.materials);
		this.setWeight(1F);
		this.setMaxStackSize(10);
		this.setRarity(EnumRarity.STUFF);
	}

	@Override
	public final Item setUnlocalizedName(final String name) {
		unlocalizedName = "item." + name;
		return this;
	}
}
