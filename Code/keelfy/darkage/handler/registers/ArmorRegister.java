package keelfy.darkage.handler.registers;

import keelfy.api.registry.ItemRegistry;
import keelfy.darkage.item.Armor;
import keelfy.darkage.item.Armor.ArmorType;
import keelfy.darkage.item.DAItem.ItemRarity;
import net.minecraft.item.Item;

/**
 * @author keelfy
 */
public class ArmorRegister {

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

	public ArmorRegister() {

		//Bear School
		String set = "9801";
		int setDur = 1700;
		bearSchoolChestplate = ItemRegistry.of(set + "Chestplate".toUpperCase(), new Armor(ArmorType.HEAVY, ItemRarity.UNCOMMON, 12, 0, 19, setDur).setTextureName(set, "Chestplate")).registerWithoutTexture();
		bearSchoolGloves = ItemRegistry.of(set + "Gloves".toUpperCase(), new Armor(ArmorType.HEAVY, ItemRarity.UNCOMMON, 4, 1, 5, setDur).setTextureName(set, "Gloves")).registerWithoutTexture();
		bearSchoolPants = ItemRegistry.of(set + "Pants".toUpperCase(), new Armor(ArmorType.HEAVY, ItemRarity.UNCOMMON, 6, 2, 9, setDur).setTextureName(set, "Pants")).registerWithoutTexture();
		bearSchoolBoots = ItemRegistry.of(set + "Boots".toUpperCase(), new Armor(ArmorType.HEAVY, ItemRarity.UNCOMMON, 4, 3, 5, setDur).setTextureName(set, "Boots")).registerWithoutTexture();
		
		setDur = 2000;
		bearSchoolChestplate2 = ItemRegistry.of(set + "Chestplate2".toUpperCase(), new Armor(ArmorType.HEAVY, ItemRarity.UNCOMMON, 11, 0, 20, setDur).setTextureName(set, "Chestplate2")).registerWithoutTexture();
		bearSchoolGloves2 = ItemRegistry.of(set + "Gloves2".toUpperCase(), new Armor(ArmorType.HEAVY, ItemRarity.UNCOMMON, 4, 1, 5, setDur).setTextureName(set, "Gloves2")).registerWithoutTexture();
		bearSchoolPants2 = ItemRegistry.of(set + "Pants2".toUpperCase(), new Armor(ArmorType.HEAVY, ItemRarity.UNCOMMON, 5, 2, 10, setDur).setTextureName(set, "Pants2")).registerWithoutTexture();
		bearSchoolBoots2 = ItemRegistry.of(set + "Boots2".toUpperCase(), new Armor(ArmorType.HEAVY, ItemRarity.UNCOMMON, 4, 3, 5, setDur).setTextureName(set, "Boots2")).registerWithoutTexture();
		
		//Cat School
		set = "9802";
		setDur = 1500;
		catSchoolChestplate = ItemRegistry.of(set + "Chestplate".toUpperCase(), new Armor(ArmorType.LIGHT, ItemRarity.UNCOMMON, 9, 0, 12, setDur).setTextureName(set, "Chestplate")).registerWithoutTexture();
		catSchoolGloves = ItemRegistry.of(set + "Gloves".toUpperCase(), new Armor(ArmorType.LIGHT, ItemRarity.UNCOMMON, 4, 1, 3, setDur).setTextureName(set, "Gloves")).registerWithoutTexture();
		catSchoolPants = ItemRegistry.of(set + "Pants".toUpperCase(), new Armor(ArmorType.LIGHT, ItemRarity.UNCOMMON, 4, 2, 5, setDur).setTextureName(set, "Pants")).registerWithoutTexture();
		catSchoolBoots = ItemRegistry.of(set + "Boots".toUpperCase(), new Armor(ArmorType.LIGHT, ItemRarity.UNCOMMON, 3, 3, 3, setDur).setTextureName(set, "Boots")).registerWithoutTexture();
		
		//Griffin School
		set = "9803";
		setDur = 1700;
		griffinSchoolChestplate = ItemRegistry.of(set + "Chestplate".toUpperCase(), new Armor(ArmorType.MIDDLE, ItemRarity.UNCOMMON, 12, 0, 14, setDur).setTextureName(set, "Chestplate")).registerWithoutTexture();
		griffinSchoolGloves = ItemRegistry.of(set + "Gloves".toUpperCase(), new Armor(ArmorType.MIDDLE, ItemRarity.UNCOMMON, 4, 1, 4, setDur).setTextureName(set, "Gloves")).registerWithoutTexture();
		griffinSchoolPants = ItemRegistry.of(set + "Pants".toUpperCase(), new Armor(ArmorType.MIDDLE, ItemRarity.UNCOMMON, 6, 2, 7, setDur).setTextureName(set, "Pants")).registerWithoutTexture();
		griffinSchoolBoots = ItemRegistry.of(set + "Boots".toUpperCase(), new Armor(ArmorType.MIDDLE, ItemRarity.UNCOMMON, 4, 3, 4, setDur).setTextureName(set, "Boots")).registerWithoutTexture();

		//Wolf School
		set = "9804";
		setDur = 1700;
		wolfSchoolChestplate = ItemRegistry.of(set + "Chestplate".toUpperCase(), new Armor(ArmorType.MIDDLE, ItemRarity.UNCOMMON, 12, 0, 14, setDur).setTextureName(set, "Chestplate")).registerWithoutTexture();
		wolfSchoolGloves = ItemRegistry.of(set + "Gloves".toUpperCase(), new Armor(ArmorType.MIDDLE, ItemRarity.UNCOMMON, 5, 1, 4, setDur).setTextureName(set, "Gloves")).registerWithoutTexture();
		wolfSchoolPants = ItemRegistry.of(set + "Pants".toUpperCase(), new Armor(ArmorType.MIDDLE, ItemRarity.UNCOMMON, 6, 2, 8, setDur).setTextureName(set, "Pants")).registerWithoutTexture();
		wolfSchoolBoots = ItemRegistry.of(set + "Boots".toUpperCase(), new Armor(ArmorType.MIDDLE, ItemRarity.UNCOMMON, 5, 3, 4, setDur).setTextureName(set, "Boots")).registerWithoutTexture();
		
		//Temer
		set = "9805";
		setDur = 1300;
		temerChestplate = ItemRegistry.of(set + "Chestplate".toUpperCase(), new Armor(ArmorType.HEAVY, ItemRarity.UNCOMMON, 8, 0, 14, setDur).setTextureName(set, "Chestplate")).registerWithoutTexture();
		temerGloves = ItemRegistry.of(set + "Gloves".toUpperCase(), new Armor(ArmorType.HEAVY, ItemRarity.UNCOMMON, 3, 1, 4, setDur).setTextureName(set, "Gloves")).registerWithoutTexture();
		temerPants = ItemRegistry.of(set + "Pants".toUpperCase(), new Armor(ArmorType.HEAVY, ItemRarity.UNCOMMON, 4, 2, 5, setDur).setTextureName(set, "Pants")).registerWithoutTexture();
		temerBoots = ItemRegistry.of(set + "Boots".toUpperCase(), new Armor(ArmorType.HEAVY, ItemRarity.UNCOMMON, 3, 3, 4, setDur).setTextureName(set, "Boots")).registerWithoutTexture();
		
		//Viper School
		set = "9806";
		setDur = 1500;
		viperSchoolChestplate = ItemRegistry.of(set + "Chestplate".toUpperCase(), new Armor(ArmorType.LIGHT, ItemRarity.UNCOMMON, 8, 0, 12, setDur).setTextureName(set, "Chestplate")).registerWithoutTexture();
		viperSchoolGloves = ItemRegistry.of(set + "Gloves".toUpperCase(), new Armor(ArmorType.LIGHT, ItemRarity.UNCOMMON, 3, 1, 3, setDur).setTextureName(set, "Gloves")).registerWithoutTexture();
		viperSchoolPants = ItemRegistry.of(set + "Pants".toUpperCase(), new Armor(ArmorType.LIGHT, ItemRarity.UNCOMMON, 4, 2, 5, setDur).setTextureName(set, "Pants")).registerWithoutTexture();
		viperSchoolBoots = ItemRegistry.of(set + "Boots".toUpperCase(), new Armor(ArmorType.LIGHT, ItemRarity.UNCOMMON, 3, 3, 3, setDur).setTextureName(set, "Boots")).registerWithoutTexture();
	   
		//Talgar
		set = "9807";
		setDur = 1300;
		talgarChestplate = ItemRegistry.of(set + "Chestplate".toUpperCase(), new Armor(ArmorType.HEAVY, ItemRarity.UNCOMMON, 12, 0, 15, setDur).setTextureName(set, "Chestplate")).registerWithoutTexture();
		talgarGloves = ItemRegistry.of(set + "Gloves".toUpperCase(), new Armor(ArmorType.HEAVY, ItemRarity.UNCOMMON, 5, 1, 4, setDur).setTextureName(set, "Gloves")).registerWithoutTexture();
		talgarPants = ItemRegistry.of(set + "Pants".toUpperCase(), new Armor(ArmorType.HEAVY, ItemRarity.UNCOMMON, 6, 2, 6, setDur).setTextureName(set, "Pants")).registerWithoutTexture();
		talgarBoots = ItemRegistry.of(set + "Boots".toUpperCase(), new Armor(ArmorType.HEAVY, ItemRarity.UNCOMMON, 5, 3, 4, setDur).setTextureName(set, "Boots")).registerWithoutTexture();
		
		//Hunter
		set = "9808";
		hunterChestplate = ItemRegistry.of(set + "Chestplate".toUpperCase(), new Armor(ArmorType.MIDDLE, ItemRarity.NOTENTERED, 0, 0, 0, 0).setTextureName(set, "Chestplate")).registerWithoutTexture();
		hunterGloves = ItemRegistry.of(set + "Gloves".toUpperCase(), new Armor(ArmorType.MIDDLE, ItemRarity.NOTENTERED, 0, 1, 0, 0).setTextureName(set, "Gloves")).registerWithoutTexture();
		hunterPants = ItemRegistry.of(set + "Pants".toUpperCase(), new Armor(ArmorType.MIDDLE, ItemRarity.NOTENTERED, 0, 2, 0, 0).setTextureName(set, "Pants")).registerWithoutTexture();
		hunterBoots = ItemRegistry.of(set + "Boots".toUpperCase(), new Armor(ArmorType.MIDDLE, ItemRarity.NOTENTERED, 0, 3, 0, 0).setTextureName(set, "Boots")).registerWithoutTexture();
		
		//Single
		set = "9800";
		alebardistChestplate = ItemRegistry.of("alebardistChestplate".toUpperCase(), new Armor(ArmorType.HEAVY, ItemRarity.RARE, 7, 0, 11, 700).setTextureName(set, "01")).registerWithoutTexture();
		ellanderChestplate = ItemRegistry.of("ellanderChestplate".toUpperCase(), new Armor(ArmorType.HEAVY, ItemRarity.LEGENDARY, 7, 0, 10, 700).setTextureName(set, "02")).registerWithoutTexture();
		redanCavalrymanChestplate = ItemRegistry.of("redanCavalrymanChestplate".toUpperCase(), new Armor(ArmorType.HEAVY, ItemRarity.RARE, 10, 0, 12, 900).setTextureName(set, "03")).registerWithoutTexture();
		hindarsfjallChestplate = ItemRegistry.of("hindarsfjallChestplate".toUpperCase(), new Armor(ArmorType.HEAVY, ItemRarity.LEGENDARY, 14, 0, 13, 900).setTextureName(set, "04")).registerWithoutTexture();
		cidarianCavalrymanChestplate = ItemRegistry.of("cidarianCavalrymanChestplate".toUpperCase(), new Armor(ArmorType.HEAVY, ItemRarity.RARE, 8, 0, 12, 850).setTextureName(set, "05")).registerWithoutTexture();
		infantryChestplate = ItemRegistry.of("infantryChestplate".toUpperCase(), new Armor(ArmorType.MIDDLE, ItemRarity.LEGENDARY, 4, 0, 8, 380).setTextureName(set, "06")).registerWithoutTexture();
		cidarianChestplate = ItemRegistry.of("cidarianChestplate".toUpperCase(), new Armor(ArmorType.MIDDLE, ItemRarity.LEGENDARY, 5, 0, 9, 450).setTextureName(set, "07")).registerWithoutTexture();
		temerArmyPants = ItemRegistry.of("temerArmyPants".toUpperCase(), new Armor(ArmorType.MIDDLE, ItemRarity.USUAL, 2, 2, 3, 400).setTextureName(set, "08")).registerWithoutTexture();
		leatherPants = ItemRegistry.of("leatherPants".toUpperCase(), new Armor(ArmorType.LIGHT, ItemRarity.RARE, 1.5F, 2, 2, 300).setTextureName(set, "09")).registerWithoutTexture();
		cidarianArmyPants = ItemRegistry.of("cidarianArmyPants".toUpperCase(), new Armor(ArmorType.MIDDLE, ItemRarity.RARE, 2, 2, 4, 450).setTextureName(set, "10")).registerWithoutTexture();
		cloth = ItemRegistry.of("cloth".toUpperCase(), new Armor(ArmorType.LIGHT, ItemRarity.NONE, 0, 0, 0, 0).setTextureName(set, "11")).registerWithoutTexture();
	}
}
