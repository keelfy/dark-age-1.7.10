/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfytools;

import org.apache.commons.lang3.StringUtils;

import cpw.mods.fml.common.registry.LanguageRegistry;
import keelfy.darkage.constants.EnumItemType;
import keelfy.darkage.items.DAItem;
import net.minecraft.util.StatCollector;

/**
 * @author keelfy
 * @created 30 мая 2017 г.
 */
public final class LocalizationUtils {
	
	public static final String desc_sword_silver = "desc.sword.silver";
	public static final String desc_sword_steel = "desc.sword.steel";
	public static final String desc_weight = "desc.weight";
	public static final String desc_durability = "desc.durability";
	public static final String desc_repairing_menu = "desc.repairing.menu";
	public static final String desc_money_key1 = "desc.money.key1";
	public static final String desc_money_key2 = "desc.money.key2";
	
	public static final String rarity_usual = "rarity.usual";
	public static final String rarity_uncommon = "rarity.uncommon";
	public static final String rarity_rare = "rarity.rare";
	public static final String rarity_legendary = "rarity.legendary";
	public static final String rarity_stuff = "rarity.stuff";
	
	public static final String gui_saturation = "gui.saturation";
	public static final String gui_nickname = "gui.nickname";
	public static final String gui_health = "gui.health";
	public static final String gui_armor = "gui.armor";
	public static final String gui_repairing_1 = "gui.repairing.1";
	public static final String gui_repairing_2 = "gui.repairing.2";
	
	public static final String key_hotslot_1 = "key.hotslot.1";
	public static final String key_hotslot_2 = "key.hotslot.2";
	public static final String key_hotslot_3 = "key.hotslot.3";
	public static final String key_hotslot_4 = "key.hotslot.4";
	public static final String key_usesign = "key.usesign";
	public static final String key_questlist = "key.questlist";
	public static final String key_sword_steel = "key.sword.steel";
	public static final String key_sword_silver = "key.sword.silver";
	
	
	public static final String localize(String s) {
		return StatCollector.translateToLocal(s).replace('&', '§').replace("'", "\'");
	}
	
	public static final String withColors(String s) {
		return s.replace('&', '§').replace("'", "\'");
	}
	
	public static final void addNameToItem(DAItem item, EnumItemType type) {
		if(item.getUnlocalizedName().contains("item")) 
			LanguageRegistry.addName(item, localize(item.getUnlocalizedName().replaceAll("item", "darkage." + type.name().toLowerCase())));
		else
			LanguageRegistry.addName(item, localize("darkage." + type.name().toLowerCase() + "." + item.getUnlocalizedName()));
	}
	
	public static String replaceLastChars(String s, String toReplace, int number) {
		int length = s.length();
		if (length < number) 
			return StringUtils.EMPTY;
		return s.substring(0, length - number) + toReplace;
	}
}
