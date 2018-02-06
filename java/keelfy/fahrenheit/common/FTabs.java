/*

 *  Copyright (c) 2016-2017, Rubedo
 */

package keelfy.fahrenheit.common;

import cpw.mods.fml.common.registry.LanguageRegistry;
import keelfyutils.KUtils;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

/**
 * @author keelfy
 * @created 4 окт. 2017 г.
 */
public class FTabs {

	public static final CreativeTabs tabFahrenheit = new CreativeTabs(CreativeTabs.getNextID(), "tabFahrenheit") {
		@Override
		public Item getTabIconItem() {
			return Item.getItemFromBlock(Blocks.ice);
		}
	};

	public static final void register() {
		if (KUtils.PROTECT_CLIENT) {
			LanguageRegistry.instance().addStringLocalization("itemGroup.tabFahrenheit", "Fahrenheit");
		}
	}

}
