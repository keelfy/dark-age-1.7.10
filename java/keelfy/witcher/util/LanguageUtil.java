package keelfy.witcher.util;

import cpw.mods.fml.common.registry.LanguageRegistry;
import keelfy.witcher.item.DAItem;
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
		return StatCollector.translateToLocal(s).replace('&', '§');
	}
	
	public static final void addNameToItem(DAItem item, ItemLocalizationType type) {
		LanguageRegistry.addName(item, StatCollector.translateToLocal(item.getUnlocalizedName().replaceAll("item", "darkage." + type.name().toLowerCase())));
	}
	
	public static enum ItemLocalizationType {
		MATERIAL, ARMOR, WEAPON, FOOD, DRINK, ELIXIR, MONEY, REPAIRKIT;
	}
}
