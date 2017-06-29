/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.handlers.registers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import cpw.mods.fml.common.registry.GameRegistry;
import keelfy.darkage.DarkAge;
import keelfy.darkage.constants.EnumFood;
import keelfy.darkage.constants.EnumItemType;
import keelfy.darkage.constants.EnumPotion;
import keelfy.darkage.constants.EnumRarity;
import keelfy.darkage.constants.EnumRepairKit;
import keelfy.darkage.constants.EnumTabs;
import keelfy.darkage.handlers.FileHandler;
import keelfy.darkage.items.DAItem;
import keelfy.darkage.items.Food;
import keelfy.darkage.items.Material;
import keelfy.darkage.items.Money;
import keelfy.darkage.items.RepairKit;
import keelfy.darkage.items.storybook.StoryBook;
import keelfy.darkage.utils.DAUtils;
import keelfytools.KeelfyUtils;
import keelfytools.LocalizationUtils;
import keelfytools.registry.ItemRegistry;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

/**
 * @author keelfy
 */
public enum ItemRegister {
	Instance;
	
	public static Item writtenBook;
	
	public static Map<String, Material> materials = new HashMap();
	public static Map<String, Money> money = new HashMap();
	public static Map<String, RepairKit> repairKits = new HashMap();
	public static Map<String, Food> foods = new HashMap();
	
	private final GsonBuilder gsonBuilder = new GsonBuilder();
	private FileHandler fh;
	
	public void init() {
		fh = DarkAge.instance.fileHandler;
		
		ArmorRegister.init();
		SwordRegister.initSilver();
		SwordRegister.initSteel();
		PotionRegister.init();
		
		loadMoney();
		loadMaterials();
		loadRepairKits();
		loadFoods();
		
		writtenBook = ItemRegistry.of("writtenbook", new StoryBook()).registerWithoutTexture();
	}
	
	private final void loadFoods() {
		try {
			gsonBuilder.registerTypeAdapter(Food[].class, new FoodDeserializer());
			Gson gson = gsonBuilder.create();
			Reader reader = new InputStreamReader(new FileInputStream(fh.getDAIFile("Foods")));
			Food[] foodsArray = gson.fromJson(reader, Food[].class);
			
			for(Food m : foodsArray) {
				foods.put(m.getUnlocalizedName().toLowerCase(), m);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private final void loadMoney() {
		try {
			gsonBuilder.registerTypeAdapter(Money[].class, new MoneyDeserializer());
			Gson gson = gsonBuilder.create();
			Reader reader = new InputStreamReader(new FileInputStream(fh.getDAIFile("Money")));
			Money[] moneyArray = gson.fromJson(reader, Money[].class);
			
			for(Money m : moneyArray) {
				money.put(m.getUnlocalizedName().toLowerCase(), m);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private final void loadMaterials() {
		try {
			gsonBuilder.registerTypeAdapter(Material[].class, new MaterialDeserializer());
			Gson gson = gsonBuilder.create();
			Reader reader = new InputStreamReader(new FileInputStream(fh.getDAIFile("Materials")));
			Material[] materialsArray = gson.fromJson(reader, Material[].class);
			
			for(Material m : materialsArray) {
				materials.put(m.getUnlocalizedName().replaceAll("item.", "").toLowerCase(), m);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private final void loadRepairKits() {
		try {
			gsonBuilder.registerTypeAdapter(RepairKit[].class, new RepairKitDeserializer());
			Gson gson = gsonBuilder.create();
			Reader reader = new InputStreamReader(new FileInputStream(fh.getDAIFile("RepairKits")));
			RepairKit[] repairKitsArray = gson.fromJson(reader, RepairKit[].class);
			
			for(RepairKit rk : repairKitsArray) {
				repairKits.put(rk.getUnlocalizedName().toLowerCase(), rk);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public class FoodDeserializer implements JsonDeserializer<Food[]> {
		@Override
		public Food[] deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {	
			JsonArray jsonArray = json.getAsJsonArray();
			Food[] foods = new Food[jsonArray.size()];
				
			for(int i = 0; i < jsonArray.size(); i++) {
				JsonObject element = jsonArray.get(i).getAsJsonObject();
					
				Food item = new Food();
				
				setUnlocalizedNameFromDAI(item, element);
				setWeightFromDAI(item, element);
				setMaxStackSizeFromDAI(item, element);
				setTextureFromDAI(item, element, "food");
				setDescriptionFromDAI(item, element);	
				
				if(element.has("SaturationLevel"))
					item.setSaturationLevel(EnumFood.valueOf(element.get("SaturationLevel").getAsString().toUpperCase()));
				
				if(KeelfyUtils.isServerSide()) {
					if(element.has("Saturation"))
						item.setSaturation(element.get("Saturation").getAsFloat());
					
					if(element.has("Effects")) {
						JsonArray effectsArray = element.get("Effects").getAsJsonArray();
						
						if(effectsArray.size() % 3 == 0) {
							int size = effectsArray.size() / 3;
							PotionEffect[] effects = new PotionEffect[size];
							
							for(int j = 1; j < size + 1; j++) {
								Potion potion = EnumPotion.valueOf(effectsArray.get(j * 3 - 3).getAsString().toUpperCase()).getEffect();
								int duration = effectsArray.get(j * 3 - 2).getAsInt();
								int amplifier = effectsArray.get(j * 3 - 1).getAsInt();
								effects[j - 1] = new PotionEffect(potion.id, duration, amplifier);
							}
							
							item.setEffects(effects);
						}
					}
				}
				
				foods[i] = item;
			}

			registerDetectedItems(foods, EnumItemType.FOOD);
			
			return foods;
		}
	}
	
	private class MoneyDeserializer implements JsonDeserializer<Money[]> {
		
		@Override
		public Money[] deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {	
			JsonArray jsonArray = json.getAsJsonArray();
			Money[] items = new Money[jsonArray.size()];
				
			for(int i = 0; i < jsonArray.size(); i++) {
				JsonObject element = jsonArray.get(i).getAsJsonObject();
				
				Money item = new Money();
				
				setUnlocalizedNameFromDAI(item, element);	
				setWeightFromDAI(item, element);
				setMaxStackSizeFromDAI(item, element);
				setTextureFromDAI(item, element, "money");
				setDescriptionFromDAI(item, element);
				
				item.setValueInOrenes(element.get("OrenesValue").getAsFloat());
				
				items[i] = item;
			}
			
			registerDetectedItems(items, EnumItemType.MONEY);
			
			return items;
		}
	}
	
	private class MaterialDeserializer implements JsonDeserializer<Material[]> {
		
		@Override
		public Material[] deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {	
			JsonArray jsonArray = json.getAsJsonArray();
			Material[] materials = new Material[jsonArray.size()];
				
			for(int i = 0; i < jsonArray.size(); i++) {
				JsonObject element = jsonArray.get(i).getAsJsonObject();
				
				Material item = new Material();
				
				setUnlocalizedNameFromDAI(item, element);
				setWeightFromDAI(item, element);
				setMaxStackSizeFromDAI(item, element);
				setRarityFromDAI(item, element);
				setTextureFromDAI(item, element, "material");
				setDescriptionFromDAI(item, element);
				
				materials[i] = item;
			}
			
			registerDetectedItems(materials, EnumItemType.MATERIAL);
			
			return materials;
		}
	}
	
	private class RepairKitDeserializer implements JsonDeserializer<RepairKit[]> {
		
		@Override
		public RepairKit[] deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {	
			JsonArray jsonArray = json.getAsJsonArray();
			RepairKit[] items = new RepairKit[jsonArray.size()];
				
			for(int i = 0; i < jsonArray.size(); i++) {
				JsonObject element = jsonArray.get(i).getAsJsonObject();
				
				RepairKit item = new RepairKit();
				
				setUnlocalizedNameFromDAI(item, element);
				setWeightFromDAI(item, element);
				setMaxStackSizeFromDAI(item, element);
				setRarityFromDAI(item, element); 
				setTextureFromDAI(item, element, "repairkit");
				setDescriptionFromDAI(item, element);
				
				item.setRepairPercent(element.get("RepairPercentage").getAsFloat());
				item.setType(EnumRepairKit.valueOf(element.get("Type").getAsString().toUpperCase()));
				
				items[i] = item;
			}
			
			registerDetectedItems(items, EnumItemType.REPAIRKIT);
			
			return items;
		}
	}
	
	private final void registerDetectedItems(DAItem[] items, EnumItemType locType) {
		for(DAItem item : items) {
			GameRegistry.registerItem(item, item.getUnlocalizedName());
			LocalizationUtils.addNameToItem(item, locType);
		}
	}
	
	private final boolean setCreativeTabFromDAI(DAItem item, JsonObject element) {
		if(element.has("CreativeTab")) {
			item.setCreativeTab(EnumTabs.valueOf(element.get("CreativeTab").getAsString().toUpperCase()).getCreativeTab());
			return true;
		}
		return false;
	}
	
	private final void setUnlocalizedNameFromDAI(DAItem item, JsonObject element) {
		item.setUnlocalizedName(element.get("UnlocalizedName").getAsString());
	}
	
	private final boolean setWeightFromDAI(DAItem item, JsonObject element) {
	 	if(element.has("Weight")) {
			item.setWeight(element.get("Weight").getAsFloat());
			return true;
	 	}
	 	return false;
	}
	
	private final boolean setMaxStackSizeFromDAI(DAItem item, JsonObject element) {
		if(element.has("MaxStackSize")) {
			item.setMaxStackSize(element.get("MaxStackSize").getAsInt());
			return true;
		}
		return false;
	}
	
	private final boolean setRarityFromDAI(DAItem item, JsonObject element) {
		if(element.has("Rarity")) {
			item.setRarity(EnumRarity.valueOf(element.get("Rarity").getAsString().toUpperCase()));
			return true;
		}
		return false;
	}
	
	private final void setTextureFromDAI(DAItem item, JsonObject element, String path) {
		if(KeelfyUtils.isClientSide())
			item.setTextureName(DAUtils.MODID + ":" + path + "/" + element.get("Texture").getAsString());
	}
	
	private final boolean setDescriptionFromDAI(DAItem item, JsonObject element) {
		if(KeelfyUtils.isClientSide()) {
			if(element.has("Description")) {
				item.setAddInfo(element.get("Description").getAsString());
				return true;
			}
			return false;
		}
		return false;
	}
}
