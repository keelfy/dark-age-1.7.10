/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.handlers.registerers;

import keelfy.darkdata.constants.EnumArmorClass;
import keelfy.darkdata.constants.EnumRarity;
import keelfy.darkdata.items.Armor;
import keelfyutils.registry.KItemRegistry;
import net.minecraft.item.Item;

/**
 * @author keelfy
 */
public enum ArmorRegister {
	Instance;
	
	public static Item alebardistChestplate, ellanderChestplate, temerArmyPants, leatherPants, cidarianArmyPants, redanCavalrymanChestplate, hindarsfjallChestplate, cidarianCavalrymanChestplate, infantryChestplate, cidarianChestplate, cloth;
	public static Item temerChestplate, temerGloves, temerPants, temerBoots;
	public static Item talgarChestplate, talgarGloves, talgarPants, talgarBoots;
	public static Item bearSchoolChestplate, bearSchoolGloves, bearSchoolPants, bearSchoolBoots;
	public static Item bearSchoolChestplate2, bearSchoolGloves2, bearSchoolPants2, bearSchoolBoots2;
	public static Item catSchoolChestplate, catSchoolGloves, catSchoolPants, catSchoolBoots;
	public static Item griffinSchoolChestplate, griffinSchoolGloves, griffinSchoolPants, griffinSchoolBoots;
	public static Item wolfSchoolChestplate, wolfSchoolGloves, wolfSchoolPants, wolfSchoolBoots;
	public static Item viperSchoolChestplate, viperSchoolGloves, viperSchoolPants, viperSchoolBoots;
	public static Item hunterChestplate, hunterGloves, hunterPants, hunterBoots;

	public static void init() {

		//Bear School
		String set = "9801";
		int setDur = 1700;
		bearSchoolChestplate = 
				KItemRegistry.of(set + "Chestplate".toUpperCase(), new Armor(EnumArmorClass.HEAVY, EnumRarity.UNCOMMON, 12, 0, 19, setDur).setTextureName(set, "Chestplate")).registerWithoutTexture();
		bearSchoolGloves = KItemRegistry.of(set + "Gloves".toUpperCase(), new Armor(EnumArmorClass.HEAVY, EnumRarity.UNCOMMON, 4, 1, 5, setDur).setTextureName(set, "Gloves")).registerWithoutTexture();
		bearSchoolPants = KItemRegistry.of(set + "Pants".toUpperCase(), new Armor(EnumArmorClass.HEAVY, EnumRarity.UNCOMMON, 6, 2, 9, setDur).setTextureName(set, "Pants")).registerWithoutTexture();
		bearSchoolBoots = KItemRegistry.of(set + "Boots".toUpperCase(), new Armor(EnumArmorClass.HEAVY, EnumRarity.UNCOMMON, 4, 3, 5, setDur).setTextureName(set, "Boots")).registerWithoutTexture();
		
		setDur = 2000;
		bearSchoolChestplate2 = KItemRegistry.of(set + "Chestplate2".toUpperCase(), new Armor(EnumArmorClass.HEAVY, EnumRarity.UNCOMMON, 11, 0, 20, setDur).setTextureName(set, "Chestplate2")).registerWithoutTexture();
		bearSchoolGloves2 = KItemRegistry.of(set + "Gloves2".toUpperCase(), new Armor(EnumArmorClass.HEAVY, EnumRarity.UNCOMMON, 4, 1, 5, setDur).setTextureName(set, "Gloves2")).registerWithoutTexture();
		bearSchoolPants2 = KItemRegistry.of(set + "Pants2".toUpperCase(), new Armor(EnumArmorClass.HEAVY, EnumRarity.UNCOMMON, 5, 2, 10, setDur).setTextureName(set, "Pants2")).registerWithoutTexture();
		bearSchoolBoots2 = KItemRegistry.of(set + "Boots2".toUpperCase(), new Armor(EnumArmorClass.HEAVY, EnumRarity.UNCOMMON, 4, 3, 5, setDur).setTextureName(set, "Boots2")).registerWithoutTexture();
		
		//Cat School
		set = "9802";
		setDur = 1500;
		catSchoolChestplate = KItemRegistry.of(set + "Chestplate".toUpperCase(), new Armor(EnumArmorClass.LIGHT, EnumRarity.UNCOMMON, 9, 0, 12, setDur).setTextureName(set, "Chestplate")).registerWithoutTexture();
		catSchoolGloves = KItemRegistry.of(set + "Gloves".toUpperCase(), new Armor(EnumArmorClass.LIGHT, EnumRarity.UNCOMMON, 4, 1, 3, setDur).setTextureName(set, "Gloves")).registerWithoutTexture();
		catSchoolPants = KItemRegistry.of(set + "Pants".toUpperCase(), new Armor(EnumArmorClass.LIGHT, EnumRarity.UNCOMMON, 4, 2, 5, setDur).setTextureName(set, "Pants")).registerWithoutTexture();
		catSchoolBoots = KItemRegistry.of(set + "Boots".toUpperCase(), new Armor(EnumArmorClass.LIGHT, EnumRarity.UNCOMMON, 3, 3, 3, setDur).setTextureName(set, "Boots")).registerWithoutTexture();
		
		//Griffin School
		set = "9803";
		setDur = 1700;
		griffinSchoolChestplate = KItemRegistry.of(set + "Chestplate".toUpperCase(), new Armor(EnumArmorClass.MIDDLE, EnumRarity.UNCOMMON, 12, 0, 14, setDur).setTextureName(set, "Chestplate")).registerWithoutTexture();
		griffinSchoolGloves = KItemRegistry.of(set + "Gloves".toUpperCase(), new Armor(EnumArmorClass.MIDDLE, EnumRarity.UNCOMMON, 4, 1, 4, setDur).setTextureName(set, "Gloves")).registerWithoutTexture();
		griffinSchoolPants = KItemRegistry.of(set + "Pants".toUpperCase(), new Armor(EnumArmorClass.MIDDLE, EnumRarity.UNCOMMON, 6, 2, 7, setDur).setTextureName(set, "Pants")).registerWithoutTexture();
		griffinSchoolBoots = KItemRegistry.of(set + "Boots".toUpperCase(), new Armor(EnumArmorClass.MIDDLE, EnumRarity.UNCOMMON, 4, 3, 4, setDur).setTextureName(set, "Boots")).registerWithoutTexture();

		//Wolf School
		set = "9804";
		setDur = 1700;
		wolfSchoolChestplate = KItemRegistry.of(set + "Chestplate".toUpperCase(), new Armor(EnumArmorClass.MIDDLE, EnumRarity.UNCOMMON, 12, 0, 14, setDur).setTextureName(set, "Chestplate")).registerWithoutTexture();
		wolfSchoolGloves = KItemRegistry.of(set + "Gloves".toUpperCase(), new Armor(EnumArmorClass.MIDDLE, EnumRarity.UNCOMMON, 5, 1, 4, setDur).setTextureName(set, "Gloves")).registerWithoutTexture();
		wolfSchoolPants = KItemRegistry.of(set + "Pants".toUpperCase(), new Armor(EnumArmorClass.MIDDLE, EnumRarity.UNCOMMON, 6, 2, 8, setDur).setTextureName(set, "Pants")).registerWithoutTexture();
		wolfSchoolBoots = KItemRegistry.of(set + "Boots".toUpperCase(), new Armor(EnumArmorClass.MIDDLE, EnumRarity.UNCOMMON, 5, 3, 4, setDur).setTextureName(set, "Boots")).registerWithoutTexture();
		
		//Temer
		set = "9805";
		setDur = 1300;
		temerChestplate = KItemRegistry.of(set + "Chestplate".toUpperCase(), new Armor(EnumArmorClass.HEAVY, EnumRarity.UNCOMMON, 8, 0, 14, setDur).setTextureName(set, "Chestplate")).registerWithoutTexture();
		temerGloves = KItemRegistry.of(set + "Gloves".toUpperCase(), new Armor(EnumArmorClass.HEAVY, EnumRarity.UNCOMMON, 3, 1, 4, setDur).setTextureName(set, "Gloves")).registerWithoutTexture();
		temerPants = KItemRegistry.of(set + "Pants".toUpperCase(), new Armor(EnumArmorClass.HEAVY, EnumRarity.UNCOMMON, 4, 2, 5, setDur).setTextureName(set, "Pants")).registerWithoutTexture();
		temerBoots = KItemRegistry.of(set + "Boots".toUpperCase(), new Armor(EnumArmorClass.HEAVY, EnumRarity.UNCOMMON, 3, 3, 4, setDur).setTextureName(set, "Boots")).registerWithoutTexture();
		
		//Viper School
		set = "9806";
		setDur = 1500;
		viperSchoolChestplate = KItemRegistry.of(set + "Chestplate".toUpperCase(), new Armor(EnumArmorClass.LIGHT, EnumRarity.UNCOMMON, 8, 0, 12, setDur).setTextureName(set, "Chestplate")).registerWithoutTexture();
		viperSchoolGloves = KItemRegistry.of(set + "Gloves".toUpperCase(), new Armor(EnumArmorClass.LIGHT, EnumRarity.UNCOMMON, 3, 1, 3, setDur).setTextureName(set, "Gloves")).registerWithoutTexture();
		viperSchoolPants = KItemRegistry.of(set + "Pants".toUpperCase(), new Armor(EnumArmorClass.LIGHT, EnumRarity.UNCOMMON, 4, 2, 5, setDur).setTextureName(set, "Pants")).registerWithoutTexture();
		viperSchoolBoots = KItemRegistry.of(set + "Boots".toUpperCase(), new Armor(EnumArmorClass.LIGHT, EnumRarity.UNCOMMON, 3, 3, 3, setDur).setTextureName(set, "Boots")).registerWithoutTexture();
	   
		//Talgar
		set = "9807";
		setDur = 1300;
		talgarChestplate = KItemRegistry.of(set + "Chestplate".toUpperCase(), new Armor(EnumArmorClass.HEAVY, EnumRarity.UNCOMMON, 12, 0, 15, setDur).setTextureName(set, "Chestplate")).registerWithoutTexture();
		talgarGloves = KItemRegistry.of(set + "Gloves".toUpperCase(), new Armor(EnumArmorClass.HEAVY, EnumRarity.UNCOMMON, 5, 1, 4, setDur).setTextureName(set, "Gloves")).registerWithoutTexture();
		talgarPants = KItemRegistry.of(set + "Pants".toUpperCase(), new Armor(EnumArmorClass.HEAVY, EnumRarity.UNCOMMON, 6, 2, 6, setDur).setTextureName(set, "Pants")).registerWithoutTexture();
		talgarBoots = KItemRegistry.of(set + "Boots".toUpperCase(), new Armor(EnumArmorClass.HEAVY, EnumRarity.UNCOMMON, 5, 3, 4, setDur).setTextureName(set, "Boots")).registerWithoutTexture();
		
		//Hunter
		set = "9808";
		hunterChestplate = KItemRegistry.of(set + "Chestplate".toUpperCase(), new Armor(EnumArmorClass.MIDDLE, EnumRarity.NOTENTERED, 0, 0, 0, 0).setTextureName(set, "Chestplate")).registerWithoutTexture();
		hunterGloves = KItemRegistry.of(set + "Gloves".toUpperCase(), new Armor(EnumArmorClass.MIDDLE, EnumRarity.NOTENTERED, 0, 1, 0, 0).setTextureName(set, "Gloves")).registerWithoutTexture();
		hunterPants = KItemRegistry.of(set + "Pants".toUpperCase(), new Armor(EnumArmorClass.MIDDLE, EnumRarity.NOTENTERED, 0, 2, 0, 0).setTextureName(set, "Pants")).registerWithoutTexture();
		hunterBoots = KItemRegistry.of(set + "Boots".toUpperCase(), new Armor(EnumArmorClass.MIDDLE, EnumRarity.NOTENTERED, 0, 3, 0, 0).setTextureName(set, "Boots")).registerWithoutTexture();
		
		//Single
		set = "9800";
		alebardistChestplate = KItemRegistry.of("alebardistChestplate".toUpperCase(), new Armor(EnumArmorClass.HEAVY, EnumRarity.RARE, 7, 0, 11, 700).setTextureName(set, "01")).registerWithoutTexture();
		ellanderChestplate = KItemRegistry.of("ellanderChestplate".toUpperCase(), new Armor(EnumArmorClass.HEAVY, EnumRarity.LEGENDARY, 7, 0, 10, 700).setTextureName(set, "02")).registerWithoutTexture();
		redanCavalrymanChestplate = KItemRegistry.of("redanCavalrymanChestplate".toUpperCase(), new Armor(EnumArmorClass.HEAVY, EnumRarity.RARE, 10, 0, 12, 900).setTextureName(set, "03")).registerWithoutTexture();
		hindarsfjallChestplate = KItemRegistry.of("hindarsfjallChestplate".toUpperCase(), new Armor(EnumArmorClass.HEAVY, EnumRarity.LEGENDARY, 14, 0, 13, 900).setTextureName(set, "04")).registerWithoutTexture();
		cidarianCavalrymanChestplate = KItemRegistry.of("cidarianCavalrymanChestplate".toUpperCase(), new Armor(EnumArmorClass.HEAVY, EnumRarity.RARE, 8, 0, 12, 850).setTextureName(set, "05")).registerWithoutTexture();
		infantryChestplate = KItemRegistry.of("infantryChestplate".toUpperCase(), new Armor(EnumArmorClass.MIDDLE, EnumRarity.LEGENDARY, 4, 0, 8, 380).setTextureName(set, "06")).registerWithoutTexture();
		cidarianChestplate = KItemRegistry.of("cidarianChestplate".toUpperCase(), new Armor(EnumArmorClass.MIDDLE, EnumRarity.LEGENDARY, 5, 0, 9, 450).setTextureName(set, "07")).registerWithoutTexture();
		temerArmyPants = KItemRegistry.of("temerArmyPants".toUpperCase(), new Armor(EnumArmorClass.MIDDLE, EnumRarity.USUAL, 2, 2, 3, 400).setTextureName(set, "08")).registerWithoutTexture();
		leatherPants = KItemRegistry.of("leatherPants".toUpperCase(), new Armor(EnumArmorClass.LIGHT, EnumRarity.RARE, 1.5F, 2, 2, 300).setTextureName(set, "09")).registerWithoutTexture();
		cidarianArmyPants = KItemRegistry.of("cidarianArmyPants".toUpperCase(), new Armor(EnumArmorClass.MIDDLE, EnumRarity.RARE, 2, 2, 4, 450).setTextureName(set, "10")).registerWithoutTexture();
		cloth = KItemRegistry.of("cloth".toUpperCase(), new Armor(EnumArmorClass.LIGHT, EnumRarity.NONE, 0, 0, 0, 0).setTextureName(set, "11")).registerWithoutTexture();
	}
}
