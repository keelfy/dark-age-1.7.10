package keelfy.darkage.util;

import cpw.mods.fml.common.registry.LanguageRegistry;
import keelfy.darkage.item.DAItem;
import net.minecraft.util.StatCollector;

/**
 * @author keelfy
 * @created 30 мая 2017 г.
 */
public final class LanguageUtil {
	
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
	
	public static final String localize(String s) {
		return StatCollector.translateToLocal(s).replace('&', '§').replace("'", "\'");
	}
	
	public static final void addNameToItem(DAItem item, ItemLocalizationType type) {
		if(item.getUnlocalizedName().contains("item")) 
			LanguageRegistry.addName(item, localize(item.getUnlocalizedName().replaceAll("item", "darkage." + type.name().toLowerCase())));
		else
			LanguageRegistry.addName(item, localize("darkage." + type.name().toLowerCase() + "." + item.getUnlocalizedName()));
	}
	
	public static enum ItemLocalizationType {
		MATERIAL, ARMOR, WEAPON, FOOD, DRINK, ELIXIR, MONEY, REPAIRKIT, COMMON;
	}
}
