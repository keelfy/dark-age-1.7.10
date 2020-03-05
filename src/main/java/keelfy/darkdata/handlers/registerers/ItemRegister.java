/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.handlers.registerers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cpw.mods.fml.common.FMLCommonHandler;
import keelfy.darkdata.constants.EnumSwordMaterial;
import keelfy.darkdata.handlers.DACommonFiles;
import keelfy.darkdata.items.Armor;
import keelfy.darkdata.items.Food;
import keelfy.darkdata.items.Material;
import keelfy.darkdata.items.Money;
import keelfy.darkdata.items.RepairKit;
import keelfy.darkdata.items.StoryBook;
import keelfy.darkdata.items.Sword;
import keelfy.darkdata.items.deserialization.ArmorDeserializer;
import keelfy.darkdata.items.deserialization.FoodDeserializer;
import keelfy.darkdata.items.deserialization.MaterialDeserializer;
import keelfy.darkdata.items.deserialization.MoneyDeserializer;
import keelfy.darkdata.items.deserialization.RepairKitDeserializer;
import keelfy.darkdata.items.deserialization.SwordDeserializer;
import keelfyutils.registry.KItemRegistry;
import keelfyutils.str.KString;
import net.minecraft.item.Item;

/**
 * @author keelfy
 */
public enum ItemRegister {
	Instance;

	public static Item writtenBook;

	public static final Map<String, Material> materials = new HashMap();
	public static final Map<String, Money> money = new HashMap();
	public static final Map<String, RepairKit> repairKits = new HashMap();
	public static final Map<String, Food> foods = new HashMap();
	public static final Map<String, Sword> swordsSilver = new HashMap();
	public static final Map<String, Sword> swordsSteel = new HashMap();
	public static final Map<String, Armor> armors = new HashMap();

	private final GsonBuilder gsonBuilder = new GsonBuilder();
	private DACommonFiles fh;

	public final void init() {
		fh = DACommonFiles.Instance;

		ArmorRegister.init();
		PotionRegister.init();

		try {
			loadMoney();
			loadMaterials();
			loadRepairKits();
			loadFoods();
			loadSwordsSilver();
			loadSwordsSteel();
		} catch (final FileNotFoundException e) {
			e.printStackTrace();
			FMLCommonHandler.instance().exitJava(0, true);
		}

		writtenBook = KItemRegistry.of("writtenbook", new StoryBook()).registerWithoutTexture();
	}

	private final void loadArmors() throws FileNotFoundException {
		gsonBuilder.registerTypeAdapter(Armor[].class, new ArmorDeserializer());
		final Gson gson = gsonBuilder.create();
		final Reader reader = new InputStreamReader(new FileInputStream(fh.getDAIFile("Armors")));
		final Armor[] array = gson.fromJson(reader, Armor[].class);

		for (final Armor item : array) {
			armors.put(item.getUnlocalizedName().toLowerCase(), item);
		}
	}

	private final void loadSwordsSilver() throws FileNotFoundException {
		gsonBuilder.registerTypeAdapter(Sword[].class, new SwordDeserializer(EnumSwordMaterial.SILVER));
		final Gson gson = gsonBuilder.create();
		final Reader reader = new InputStreamReader(new FileInputStream(fh.getDAIFile("SilverSwords")));
		final Sword[] array = gson.fromJson(reader, Sword[].class);

		for (final Sword item : array) {
			swordsSilver.put(item.getUnlocalizedName().toLowerCase(), item);
		}
	}

	private final void loadSwordsSteel() throws FileNotFoundException {
		gsonBuilder.registerTypeAdapter(Sword[].class, new SwordDeserializer(EnumSwordMaterial.STEEL));
		final Gson gson = gsonBuilder.create();
		final Reader reader = new InputStreamReader(new FileInputStream(fh.getDAIFile("SteelSwords")));
		final Sword[] array = gson.fromJson(reader, Sword[].class);

		for (final Sword item : array) {
			swordsSteel.put(item.getUnlocalizedName().toLowerCase(), item);
		}
	}

	private final void loadFoods() throws FileNotFoundException {
		gsonBuilder.registerTypeAdapter(Food[].class, new FoodDeserializer());
		final Gson gson = gsonBuilder.create();
		final Reader reader = new InputStreamReader(new FileInputStream(fh.getDAIFile("Foods")));
		final Food[] foodsArray = gson.fromJson(reader, Food[].class);

		for (final Food m : foodsArray) {
			foods.put(m.getUnlocalizedName().toLowerCase(), m);
		}
	}

	private final void loadMoney() throws FileNotFoundException {
		gsonBuilder.registerTypeAdapter(Money[].class, new MoneyDeserializer());
		final Gson gson = gsonBuilder.create();
		final Reader reader = new InputStreamReader(new FileInputStream(fh.getDAIFile("Money")));
		final Money[] moneyArray = gson.fromJson(reader, Money[].class);

		for (final Money m : moneyArray) {
			money.put(m.getUnlocalizedName().toLowerCase(), m);
		}
	}

	private final void loadMaterials() throws FileNotFoundException {
		gsonBuilder.registerTypeAdapter(Material[].class, new MaterialDeserializer());
		final Gson gson = gsonBuilder.create();
		final Reader reader = new InputStreamReader(new FileInputStream(fh.getDAIFile("Materials")));
		final Material[] materialsArray = gson.fromJson(reader, Material[].class);

		for (final Material m : materialsArray) {
			materials.put(m.getUnlocalizedName().replaceAll("item.", KString.EMPTY).toLowerCase(), m);
		}
	}

	private final void loadRepairKits() throws FileNotFoundException {
		gsonBuilder.registerTypeAdapter(RepairKit[].class, new RepairKitDeserializer());
		final Gson gson = gsonBuilder.create();
		final Reader reader = new InputStreamReader(new FileInputStream(fh.getDAIFile("RepairKits")));
		final RepairKit[] repairKitsArray = gson.fromJson(reader, RepairKit[].class);

		for (final RepairKit rk : repairKitsArray) {
			repairKits.put(rk.getUnlocalizedName().toLowerCase(), rk);
		}
	}
}
