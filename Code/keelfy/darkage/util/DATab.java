package keelfy.darkage.util;

import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkage.handler.registers.ArmorRegister;
import keelfy.darkage.handler.registers.FoodRegister;
import keelfy.darkage.handler.registers.ItemRegister;
import keelfy.darkage.handler.registers.PotionRegister;
import keelfy.darkage.handler.registers.SwordRegister;
import keelfy.witcherBlocks.register.LightingRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

/**
 * @author keelfy
 */
public final class DATab {
	
	public static enum Tab {
		MAIN(DATab.tabDA),
		ARMOR(DATab.tabArmor),
		SWORDSILVER(DATab.tabSwordSilver),
		SWORDSTEEL(DATab.tabSwordSteel),
		MATERIAL(DATab.tabMaterial),
		FOOD(DATab.tabFood),
		POTION(DATab.tabPotion),
		MONEY(DATab.tabMoney),
		DRINK(DATab.tabDrink),
		BLOCK(DATab.tabBlock),
		REPAIRKIT(DATab.tabRepairKit);
		
		private CreativeTabs tab;
		private Tab(CreativeTabs tab) {
			this.tab = tab;
		}
		
		public CreativeTabs getCreativeTab() {
			return tab;
		}
	}
	
	public static final CreativeTabs tabDA = new CreativeTabs(CreativeTabs.getNextID(), "tabDarkAge") {
	    @Override
		@SideOnly(Side.CLIENT)
	    public Item getTabIconItem() {
	        return ItemRegister.smartLibrary;
	    }
	};
	
	public static final CreativeTabs tabRepairKit = new CreativeTabs(CreativeTabs.getNextID(), "tabRepairKit") {
	    @Override
		@SideOnly(Side.CLIENT)
	    public Item getTabIconItem() {
	        return ItemRegister.repairKits[0];
	    }
	};
	
	public static final CreativeTabs tabArmor = new CreativeTabs(CreativeTabs.getNextID(), "tabDAArmor") {
	    @Override
		@SideOnly(Side.CLIENT)
	    public Item getTabIconItem() {
	        return ArmorRegister.alebardistChestplate;
	    }
	};
	
	public static final CreativeTabs tabSwordSilver = new CreativeTabs(CreativeTabs.getNextID(), "tabSwordSilver") {
	    @Override
		@SideOnly(Side.CLIENT)
	    public Item getTabIconItem() {
	        return SwordRegister.addanDeidth;
	    }
	};
	
	public static final CreativeTabs tabSwordSteel = new CreativeTabs(CreativeTabs.getNextID(), "tabSwordSteel") {
	    @Override
		@SideOnly(Side.CLIENT)
	    public Item getTabIconItem() {
	        return SwordRegister.bearSilver1;
	    }
	};
	
	public static final CreativeTabs tabMaterial = new CreativeTabs(CreativeTabs.getNextID(), "tabDAMaterial") {
	    @Override
		@SideOnly(Side.CLIENT)
	    public Item getTabIconItem() {
	    	return ItemRegister.materials[0];
	    }
	};
	
	public static final CreativeTabs tabFood = new CreativeTabs(CreativeTabs.getNextID(), "tabDAFood") {
	    @Override
		@SideOnly(Side.CLIENT)
	    public Item getTabIconItem() {
	        return FoodRegister.grapes;
	    }
	};
	
	public static final CreativeTabs tabPotion = new CreativeTabs(CreativeTabs.getNextID(), "tabDAPotion") {
	    @Override
		@SideOnly(Side.CLIENT)
	    public Item getTabIconItem() {
	        return PotionRegister.swallow;
	    }
	};
	
	public static final CreativeTabs tabDrink = new CreativeTabs(CreativeTabs.getNextID(), "tabDADrink") {
	    @Override
		@SideOnly(Side.CLIENT)
	    public Item getTabIconItem() {
	        return Items.glass_bottle;
	    }
	};
	
	public static final CreativeTabs tabMoney = new CreativeTabs(CreativeTabs.getNextID(), "tabDAMoney") {
	    @Override
		@SideOnly(Side.CLIENT)
	    public Item getTabIconItem() {
	        return ItemRegister.money[0];
	    }
	};
	
	public static final CreativeTabs tabBlock = new CreativeTabs(CreativeTabs.getNextID(), "tabDABlock") {
	    @Override
		@SideOnly(Side.CLIENT)
	    public Item getTabIconItem() {
	        return Item.getItemFromBlock(LightingRegister.lightMushroom1);
	    }
	};
	
	public DATab() {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			LanguageRegistry.instance().addStringLocalization("itemGroup.tabDarkAge", "Dark Age");
			LanguageRegistry.instance().addStringLocalization("itemGroup.tabSwordSilver", "Серебряные мечи");
			LanguageRegistry.instance().addStringLocalization("itemGroup.tabSwordSteel", "Стальные мечи");
			LanguageRegistry.instance().addStringLocalization("itemGroup.tabDAArmor", "Броня");
			LanguageRegistry.instance().addStringLocalization("itemGroup.tabDAMaterial", "Материалы");
			LanguageRegistry.instance().addStringLocalization("itemGroup.tabDAFood", "Еда");
			LanguageRegistry.instance().addStringLocalization("itemGroup.tabDADrink", "Напитки");
			LanguageRegistry.instance().addStringLocalization("itemGroup.tabDABlock", "Блоки");
			LanguageRegistry.instance().addStringLocalization("itemGroup.tabDAMoney", "Деньги");
			LanguageRegistry.instance().addStringLocalization("itemGroup.tabDAPotion", "Эликсиры");
			LanguageRegistry.instance().addStringLocalization("itemGroup.tabRepairKit", "Ремонтные наборы");
		}
	}
}
