package keelfy.darkdata.handlers;

import cpw.mods.fml.common.registry.LanguageRegistry;
import keelfy.darkdata.handlers.registerers.ArmorRegister;
import keelfy.darkdata.handlers.registerers.ItemRegister;
import keelfy.darkdata.handlers.registerers.PotionRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * @author keelfy
 * @created 2 авг. 2017 г.
 */
public final class DATabsHandler {

	private static final String Silver_Swords = "silverSwords";
	private static final String Steel_Swords = "steelSwords";
	private static final String Repair_Kits = "repairKits";
	private static final String Armors = "armors";
	private static final String Foods = "foods";
	private static final String Materials = "materials";
	private static final String Elixirs = "elixirs";
	private static final String Money = "money";
	private static final String Blocks = "blocks";
	private static final String Nature = "nature";

	public static final CreativeTabs silverSwords = new CreativeTabs(CreativeTabs.getNextID(), Silver_Swords) {

		@Override
		public Item getTabIconItem() {
			return ItemRegister.swordsSilver.get("zirael");
		}
	};

	public static final CreativeTabs steelSwords = new CreativeTabs(CreativeTabs.getNextID(), Steel_Swords) {

		@Override
		public Item getTabIconItem() {
			return ItemRegister.swordsSteel.get("irony");
		}
	};

	public static final CreativeTabs repairKits = new CreativeTabs(CreativeTabs.getNextID(), Repair_Kits) {

		@Override
		public Item getTabIconItem() {
			return ItemRegister.repairKits.get("wrp1");
		}
	};

	public static final CreativeTabs armors = new CreativeTabs(CreativeTabs.getNextID(), Armors) {

		@Override
		public Item getTabIconItem() {
			return ArmorRegister.alebardistChestplate;
		}
	};

	public static final CreativeTabs foods = new CreativeTabs(CreativeTabs.getNextID(), Foods) {

		@Override
		public Item getTabIconItem() {
			return ItemRegister.foods.get("meat3");
		}
	};

	public static final CreativeTabs materials = new CreativeTabs(CreativeTabs.getNextID(), Materials) {

		@Override
		public Item getTabIconItem() {
			return ItemRegister.materials.get("rubedo");
		}
	};

	public static final CreativeTabs elixirs = new CreativeTabs(CreativeTabs.getNextID(), Elixirs) {

		@Override
		public Item getTabIconItem() {
			return PotionRegister.swallow;
		}
	};

	public static final CreativeTabs money = new CreativeTabs(CreativeTabs.getNextID(), Money) {

		@Override
		public Item getTabIconItem() {
			return ItemRegister.money.get("oren");
		}
	};

	public static final CreativeTabs blocks = new CreativeTabs(CreativeTabs.getNextID(), Blocks) {

		@Override
		public Item getTabIconItem() {
			return Item.getItemFromBlock(net.minecraft.init.Blocks.bed);
		}
	};

	public static final CreativeTabs nature = new CreativeTabs(CreativeTabs.getNextID(), Nature) {

		@Override
		public Item getTabIconItem() {
			return Item.getItemFromBlock(net.minecraft.init.Blocks.grass);
		}
	};

	public static final void init() {
		LanguageRegistry.instance().addStringLocalization("itemGroup." + Silver_Swords, "Серебряные мечи");
		LanguageRegistry.instance().addStringLocalization("itemGroup." + Steel_Swords, "Стальные мечи");
		LanguageRegistry.instance().addStringLocalization("itemGroup." + Armors, "Броня");
		LanguageRegistry.instance().addStringLocalization("itemGroup." + Foods, "Еда");
		LanguageRegistry.instance().addStringLocalization("itemGroup." + Materials, "Материалы");
		LanguageRegistry.instance().addStringLocalization("itemGroup." + Elixirs, "Эликсиры");
		LanguageRegistry.instance().addStringLocalization("itemGroup." + Money, "Валюта");
		LanguageRegistry.instance().addStringLocalization("itemGroup." + Blocks, "Блоки");
		LanguageRegistry.instance().addStringLocalization("itemGroup." + Nature, "Природа");
	}

}
