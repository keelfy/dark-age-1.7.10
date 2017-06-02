package keelfy.witcher.util;

import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.witcher.handler.registers.ArmorRegister;
import keelfy.witcher.handler.registers.FoodRegister;
import keelfy.witcher.handler.registers.ItemRegister;
import keelfy.witcher.handler.registers.PotionRegister;
import keelfy.witcher.handler.registers.SwordRegister;
import keelfy.witcherBlocks.register.LightingRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

/**
 * @author keelfy
 */
public final class DATab {
	
	public static enum Tab {
		MAIN(DATab.wcMain),
		ARMOR(DATab.wcArmor),
		SWORDSILVER(DATab.wcSwordSilver),
		SWORDSTEEL(DATab.wcSwordSteel),
		MATERIAL(DATab.wcMaterial),
		FOOD(DATab.wcFood),
		POTION(DATab.wcPotion),
		MONEY(DATab.wcMoney),
		DRINK(DATab.wcDrink),
		BLOCK(DATab.wcBlock);
		
		private CreativeTabs tab;
		private Tab(CreativeTabs tab) {
			this.tab = tab;
		}
		
		public CreativeTabs getCreativeTab() {
			return tab;
		}
	}
	
	public static final CreativeTabs wcMain = new CreativeTabs(CreativeTabs.getNextID(), "tabWC") {
	    @Override
		@SideOnly(Side.CLIENT)
	    public Item getTabIconItem() {
	        return ItemRegister.smartLibrary;
	    }
	};
	
	public static final CreativeTabs wcArmor = new CreativeTabs(CreativeTabs.getNextID(), "tabWCArmor") {
	    @Override
		@SideOnly(Side.CLIENT)
	    public Item getTabIconItem() {
	        return ArmorRegister.alebardistChestplate;
	    }
	};
	
	public static final CreativeTabs wcSwordSilver = new CreativeTabs(CreativeTabs.getNextID(), "tabSwordSilver") {
	    @Override
		@SideOnly(Side.CLIENT)
	    public Item getTabIconItem() {
	        return SwordRegister.addanDeidth;
	    }
	};
	
	public static final CreativeTabs wcSwordSteel = new CreativeTabs(CreativeTabs.getNextID(), "tabSwordSteel") {
	    @Override
		@SideOnly(Side.CLIENT)
	    public Item getTabIconItem() {
	        return SwordRegister.bearSilver1;
	    }
	};
	
	public static final CreativeTabs wcMaterial = new CreativeTabs(CreativeTabs.getNextID(), "tabWCMaterial") {
	    @Override
		@SideOnly(Side.CLIENT)
	    public Item getTabIconItem() {
	    	return Items.leather;
	    }
	};
	
	public static final CreativeTabs wcFood = new CreativeTabs(CreativeTabs.getNextID(), "tabWCFood") {
	    @Override
		@SideOnly(Side.CLIENT)
	    public Item getTabIconItem() {
	        return FoodRegister.grapes;
	    }
	};
	
	public static final CreativeTabs wcPotion = new CreativeTabs(CreativeTabs.getNextID(), "tabWCPotion") {
	    @Override
		@SideOnly(Side.CLIENT)
	    public Item getTabIconItem() {
	        return PotionRegister.swallow;
	    }
	};
	
	public static final CreativeTabs wcDrink = new CreativeTabs(CreativeTabs.getNextID(), "tabWCDrink") {
	    @Override
		@SideOnly(Side.CLIENT)
	    public Item getTabIconItem() {
	        return Items.glass_bottle;
	    }
	};
	
	public static final CreativeTabs wcMoney = new CreativeTabs(CreativeTabs.getNextID(), "tabWCMoney") {
	    @Override
		@SideOnly(Side.CLIENT)
	    public Item getTabIconItem() {
	        return ItemRegister.oren;
	    }
	};
	
	public static final CreativeTabs wcBlock = new CreativeTabs(CreativeTabs.getNextID(), "tabWCBlock") {
	    @Override
		@SideOnly(Side.CLIENT)
	    public Item getTabIconItem() {
	        return Item.getItemFromBlock(LightingRegister.lightMushroom1);
	    }
	};
	
	public DATab() {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			LanguageRegistry.instance().addStringLocalization("itemGroup.tabWC", "Witcher Core");
			LanguageRegistry.instance().addStringLocalization("itemGroup.tabSwordSilver", "Серебряные мечи");
			LanguageRegistry.instance().addStringLocalization("itemGroup.tabSwordSteel", "Стальные мечи");
			LanguageRegistry.instance().addStringLocalization("itemGroup.tabWCArmor", "Броня");
			LanguageRegistry.instance().addStringLocalization("itemGroup.tabWCMaterial", "Материалы");
			LanguageRegistry.instance().addStringLocalization("itemGroup.tabWCFood", "Еда");
			LanguageRegistry.instance().addStringLocalization("itemGroup.tabWCDrink", "Напитки");
			LanguageRegistry.instance().addStringLocalization("itemGroup.tabWCBlock", "Блоки");
			LanguageRegistry.instance().addStringLocalization("itemGroup.tabWCMoney", "Деньги");
			LanguageRegistry.instance().addStringLocalization("itemGroup.tabWCPotion", "Эликсиры");
		}
	}
}
