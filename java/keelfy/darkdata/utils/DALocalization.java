/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.utils;

import cpw.mods.fml.common.registry.LanguageRegistry;
import keelfy.darkdata.constants.EnumItemType;
import keelfy.darkdata.items.DAItem;
import keelfyutils.client.KLocalization;

/**
 * @author keelfy
 * @created 8 июл. 2017 г.
 */
public final class DALocalization {

	public static final String Desc_Sword_Silver = "desc.sword.silver";
	public static final String Desc_Sword_Steel = "desc.sword.steel";
	public static final String Desc_Weight = "desc.weight";
	public static final String Desc_Durability = "desc.durability";
	public static final String Desc_Repairing_Menu = "desc.repairing.menu";
	public static final String Desc_Money_Key1 = "desc.money.key1";
	public static final String Desc_Money_Key2 = "desc.money.key2";

	public static final String Rarity_Usual = "rarity.usual";
	public static final String Rarity_Uncommon = "rarity.uncommon";
	public static final String Rarity_Rare = "rarity.rare";
	public static final String Rarity_Legendary = "rarity.legendary";
	public static final String Rarity_Stuff = "rarity.stuff";

	public static final String Gui_Saturation = "gui.saturation";
	public static final String Gui_Nickname = "gui.nickname";
	public static final String Gui_Health = "gui.health";
	public static final String Gui_Armor = "gui.armor";
	public static final String Gui_Repairing_1 = "gui.repairing.1";
	public static final String Gui_Repairing_2 = "gui.repairing.2";

	public static final String Key_Hotslot_1 = "key.hotslot.1";
	public static final String Key_Hotslot_2 = "key.hotslot.2";
	public static final String Key_Hotslot_3 = "key.hotslot.3";
	public static final String Key_Hotslot_4 = "key.hotslot.4";
	public static final String Key_Use_Sign = "key.usesign";
	public static final String Key_Quest_List = "key.questlist";
	public static final String Key_Sword_Steel = "key.sword.steel";
	public static final String Key_Sword_Silver = "key.sword.silver";
	public static final String Key_Otherslot_1 = "key.otherslot.1";
	public static final String Key_Otherslot_2 = "key.otherslot.2";
	public static final String Key_Select_Sign = "key.select.sign";

	public static final String Effect_Heal = "effect.heal";
	public static final String Effect_Resistance = "effect.resistance";
	public static final String Effect_Strength = "effect.strength";
	public static final String Effect_NightVision = "effect.nightvision";
	public static final String Effect_WaterBreathing = "effect.waterbreathing";
	public static final String Effect_SecondBreath = "effect.secondbreath";
	public static final String Effect_MoveSpeed = "effect.movespeed";

	public static final void addNameToItem(final DAItem item, final EnumItemType type) {
		if (item.getUnlocalizedName().contains("item")) {
			LanguageRegistry.addName(item, KLocalization
					.localize(item.getUnlocalizedName().replaceAll("item", "darkage." + type.name().toLowerCase())));
		} else {
			LanguageRegistry.addName(item,
					KLocalization.localize("darkage." + type.name().toLowerCase() + "." + item.getUnlocalizedName()));
		}
	}
}
