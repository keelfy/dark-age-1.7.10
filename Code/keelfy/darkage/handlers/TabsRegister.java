/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.handlers;

import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkage.handlers.registers.ArmorRegister;
import keelfy.darkage.handlers.registers.ItemRegister;
import keelfy.darkage.handlers.registers.PotionRegister;
import keelfy.darkage.handlers.registers.SwordRegister;
import keelfytools.KeelfyUtils;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

/**
 * @author keelfy
 */
public enum TabsRegister {
	Instance;
	
	public static final CreativeTabs tabDA = new CreativeTabs(CreativeTabs.getNextID(), "tabDarkAge") {
	    @Override
		@SideOnly(Side.CLIENT)
	    public Item getTabIconItem() {
	        return ItemRegister.writtenBook;
	    }
	};
	
	public static final CreativeTabs tabRepairKit = new CreativeTabs(CreativeTabs.getNextID(), "tabRepairKits") {
	    @Override
		@SideOnly(Side.CLIENT)
	    public Item getTabIconItem() {
	        return ItemRegister.repairKits.get("wrp1");
	    }
	};
	
	public static final CreativeTabs tabArmor = new CreativeTabs(CreativeTabs.getNextID(), "tabDAArmors") {
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
	
	public static final CreativeTabs tabMaterial = new CreativeTabs(CreativeTabs.getNextID(), "tabDAMaterials") {
	    @Override
		@SideOnly(Side.CLIENT)
	    public Item getTabIconItem() {
	    	return ItemRegister.materials.get("rubedo");
	    }
	};
	
	public static final CreativeTabs tabFood = new CreativeTabs(CreativeTabs.getNextID(), "tabDAFoods") {
	    @Override
		@SideOnly(Side.CLIENT)
	    public Item getTabIconItem() {
	        return ItemRegister.foods.get("meat3");
	    }
	};
	
	public static final CreativeTabs tabPotion = new CreativeTabs(CreativeTabs.getNextID(), "tabDAPotions") {
	    @Override
		@SideOnly(Side.CLIENT)
	    public Item getTabIconItem() {
	        return PotionRegister.swallow;
	    }
	};
	
	public static final CreativeTabs tabDrink = new CreativeTabs(CreativeTabs.getNextID(), "tabDADrinks") {
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
	        return ItemRegister.money.get("oren");
	    }
	};
	
	public static final CreativeTabs tabBlock = new CreativeTabs(CreativeTabs.getNextID(), "tabDABlocks") {
	    @Override
		@SideOnly(Side.CLIENT)
	    public Item getTabIconItem() {
	        return Item.getItemFromBlock(Blocks.brick_block);
	    }
	};
	
	private static void createNewTab(String name, String localized) {
		if(KeelfyUtils.isClientSide()) {
			LanguageRegistry.instance().addStringLocalization("itemGroup." + name, localized);
		}
	}
	
	public final void init() {
		createNewTab("tabDarkAge", "Dark Age");
		createNewTab("tabRepairKits", "Ремонтные наборы");
		createNewTab("tabDAArmors", "Броня");
		createNewTab("tabSwordSilver", "Серебряные мечи");
		createNewTab("tabSwordSteel", "Стальные мечи");
		createNewTab("tabDAMaterials", "Материалы");
		createNewTab("tabDAFoods", "Еда");
		createNewTab("tabDAPotions", "Эликсиры");
		createNewTab("tabDADrinks", "Напитки");
		createNewTab("tabDAMoney", "Деньги");
		createNewTab("tabDABlocks", "Блоки");
	}
}
