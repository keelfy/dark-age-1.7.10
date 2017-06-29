/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.constants;

import keelfy.darkage.handlers.TabsRegister;
import net.minecraft.creativetab.CreativeTabs;

/**
 * @author keelfy
 * @created 6 июн. 2017 г.
 */
public enum EnumTabs {
	MAIN(TabsRegister.tabDA),
	ARMOR(TabsRegister.tabArmor),
	SWORDSILVER(TabsRegister.tabSwordSilver),
	SWORDSTEEL(TabsRegister.tabSwordSteel),
	MATERIAL(TabsRegister.tabMaterial),
	FOOD(TabsRegister.tabFood),
	POTION(TabsRegister.tabPotion),
	MONEY(TabsRegister.tabMoney),
	DRINK(TabsRegister.tabDrink),
	BLOCK(TabsRegister.tabBlock),
	REPAIRKIT(TabsRegister.tabRepairKit);
	
	private CreativeTabs tab;
	private EnumTabs(CreativeTabs par1) {
		this.tab = par1;
	}
	
	public CreativeTabs getCreativeTab() {
		return tab;
	}
}
