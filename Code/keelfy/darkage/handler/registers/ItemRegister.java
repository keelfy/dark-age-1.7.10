package keelfy.darkage.handler.registers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import cpw.mods.fml.common.registry.GameRegistry;
import keelfy.api.registry.ItemRegistry;
import keelfy.darkage.DarkAge;
import keelfy.darkage.item.DAItem;
import keelfy.darkage.item.DAItem.ItemRarity;
import keelfy.darkage.item.Material;
import keelfy.darkage.item.Money;
import keelfy.darkage.item.RepairKit;
import keelfy.darkage.item.RepairKit.RepairKitType;
import keelfy.darkage.item.smartlib.DAWrittenBook;
import keelfy.darkage.item.smartlib.SmartLibrary;
import keelfy.darkage.util.DATab.Tab;
import keelfy.darkage.util.DAUtil;
import keelfy.darkage.util.LanguageUtil;
import keelfy.darkage.util.LanguageUtil.ItemLocalizationType;
import net.minecraft.item.Item;

/**
 * @author keelfy
 */
public class ItemRegister {
	public static Item writtenBook, smartLibrary;
	
	public static Item[] materials;
	public static Item[] money;
	public static Item[] repairKits;
	
	public ItemRegister() {
		new ArmorRegister();
		new FoodRegister();
		new SwordRegister();
		new PotionRegister();
		
		loadMoney();
		loadMaterials();
		loadRepairKits();
		
		String path;
		writtenBook = ItemRegistry.of("writtenbook", new DAWrittenBook()).registerWithoutTexture();
		smartLibrary = ItemRegistry.of("smartlibrary", new SmartLibrary()).registerWithoutTexture("Смарт-Библиотека");
		
//		path = "repairkit";
//		repairAKit1 = ItemRegistry.of("repairAKit1".toUpperCase(), new RepairKit(ItemRarity.USUAL, 1, 15, RepairKitType.ARMOR, "Восстанавливает 15% прочности доспеха")).register(path);
//		repairAKit2 = ItemRegistry.of("repairAKit2".toUpperCase(), new RepairKit(ItemRarity.UNCOMMON, 2, 40, RepairKitType.ARMOR, "Восстанавливает 40% прочности доспеха")).register(path);
//		repairAKit3 = ItemRegistry.of("repairAKit3".toUpperCase(), new RepairKit(ItemRarity.RARE, 3, 90, RepairKitType.ARMOR, "Восстанавливает 90% прочности доспеха")).register(path);
//		
//		repairWKit1 = ItemRegistry.of("repairWKit1".toUpperCase(), new RepairKit(ItemRarity.USUAL, 1, 15, RepairKitType.WEAPON, "Восстанавливает 15% прочности оружия")).register(path);
//		repairWKit2 = ItemRegistry.of("repairWKit2".toUpperCase(), new RepairKit(ItemRarity.UNCOMMON, 2, 40, RepairKitType.WEAPON, "Восстанавливает 40% прочности оружия")).register(path);
//		repairWKit3 = ItemRegistry.of("repairWKit3".toUpperCase(), new RepairKit(ItemRarity.RARE, 3, 90, RepairKitType.WEAPON, "Восстанавливает 90% прочности оружия")).register(path);
	}
	
	private final void loadMoney() {
		try {
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.registerTypeAdapter(Money[].class, new MoneyDeserializer());
			Gson gson = gsonBuilder.create();
			Reader reader = new InputStreamReader(new FileInputStream(DarkAge.instance.fileHandler.getItemDAIFile("Money")));
			money = gson.fromJson(reader, Money[].class);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private final void loadMaterials() {
		try {
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.registerTypeAdapter(Material[].class, new MaterialDeserializer());
			Gson gson = gsonBuilder.create();
			Reader reader = new InputStreamReader(new FileInputStream(DarkAge.instance.fileHandler.getItemDAIFile("Materials")));
			materials = gson.fromJson(reader, Material[].class);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private final void loadRepairKits() {
		try {
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.registerTypeAdapter(RepairKit[].class, new RepairKitDeserializer());
			Gson gson = gsonBuilder.create();
			Reader reader = new InputStreamReader(new FileInputStream(DarkAge.instance.fileHandler.getItemDAIFile("RepairKits")));
			repairKits = gson.fromJson(reader, RepairKit[].class);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static class MoneyDeserializer implements JsonDeserializer<Money[]> {
		
		@Override
		public Money[] deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {	
			JsonArray jsonArray = json.getAsJsonArray();
			Money[] items = new Money[jsonArray.size()];
				
			for(int i = 0; i < jsonArray.size(); i++) {
				JsonObject element = jsonArray.get(i).getAsJsonObject();
				
				Money item = new Money();
				
				setUnlocalizedNameFromDAI(item, element);	
				if(!setWeightFromDAI(item, element))
					item.setWeight(0F);
				if(!setMaxStackSizeFromDAI(item, element))
					item.setMaxStackSize(50);
				setTextureFromDAI(item, element, "money");
				setDescriptionFromDAI(item, element);
				item.setValueInOrenes(element.get("OrenesValue").getAsFloat());
				
				items[i] = item;
			}
			
			registerDetectedItems(items, ItemLocalizationType.MONEY);
			
			return items;
		}
	}
	
	public static class MaterialDeserializer implements JsonDeserializer<Material[]> {
		
		@Override
		public Material[] deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {	
			JsonArray jsonArray = json.getAsJsonArray();
			Material[] materials = new Material[jsonArray.size()];
				
			for(int i = 0; i < jsonArray.size(); i++) {
				JsonObject element = jsonArray.get(i).getAsJsonObject();
				
				Material item = new Material();
				
				setUnlocalizedNameFromDAI(item, element);
				if(!setWeightFromDAI(item, element))
					item.setWeight(1.0F);
				if(!setMaxStackSizeFromDAI(item, element))
					item.setMaxStackSize(10);
				if(!setRarityFromDAI(item, element))
					item.setRarity(ItemRarity.STUFF);
				setTextureFromDAI(item, element, "material");
				setDescriptionFromDAI(item, element);
				
				materials[i] = item;
			}
			
			registerDetectedItems(materials, ItemLocalizationType.MATERIAL);
			
			return materials;
		}
	}
	
	public static class RepairKitDeserializer implements JsonDeserializer<RepairKit[]> {
		
		@Override
		public RepairKit[] deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {	
			JsonArray jsonArray = json.getAsJsonArray();
			RepairKit[] items = new RepairKit[jsonArray.size()];
				
			for(int i = 0; i < jsonArray.size(); i++) {
				JsonObject element = jsonArray.get(i).getAsJsonObject();
				
				RepairKit item = new RepairKit();
				
				setUnlocalizedNameFromDAI(item, element);
				if(setWeightFromDAI(item, element)) 
					item.setWeight(1F);
				if(!setMaxStackSizeFromDAI(item, element))
					item.setMaxStackSize(3);
				if(!setRarityFromDAI(item, element)) 
					item.setRarity(ItemRarity.NONE);
				setTextureFromDAI(item, element, "repairkit");
				setDescriptionFromDAI(item, element);
				item.setRepairPercent(element.get("RepairPercentage").getAsFloat());
				item.setType(RepairKitType.valueOf(element.get("Type").getAsString().toUpperCase()));
				
				items[i] = item;
			}
			
			registerDetectedItems(items, ItemLocalizationType.REPAIRKIT);
			
			return items;
		}
	}
	
	static final void registerDetectedItems(DAItem[] items, ItemLocalizationType locType) {
		for(DAItem item : items) {
			GameRegistry.registerItem(item, item.getUnlocalizedName());
			LanguageUtil.addNameToItem(item, locType);
		}
	}
	
	static final boolean setCreativeTabFromDAI(DAItem item, JsonObject element) {
		if(element.has("CreativeTab")) {
			item.setCreativeTab(Tab.valueOf(element.get("CreativeTab").getAsString().toUpperCase()).getCreativeTab());
			return true;
		}
		return false;
	}
	
	static final void setUnlocalizedNameFromDAI(DAItem item, JsonObject element) {
		item.setUnlocalizedName(element.get("UnlocalizedName").getAsString());
	}
	
	static final boolean setWeightFromDAI(DAItem item, JsonObject element) {
	 	if(element.has("Weight")) {
			item.setWeight(element.get("Weight").getAsFloat());
			return true;
	 	}
	 	return false;
	}
	
	static final boolean setMaxStackSizeFromDAI(DAItem item, JsonObject element) {
		if(element.has("MaxStackSize")) {
			item.setMaxStackSize(element.get("MaxStackSize").getAsInt());
			return true;
		}
		return false;
	}
	
	static final boolean setRarityFromDAI(DAItem item, JsonObject element) {
		if(element.has("Rarity")) {
			item.setRarity(ItemRarity.valueOf(element.get("Rarity").getAsString().toUpperCase()));
			return true;
		}
		return false;
	}
	
	static final void setTextureFromDAI(DAItem item, JsonObject element, String path) {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE)
			item.setTextureName(DAUtil.MODID + ":" + path + "/" + element.get("Texture").getAsString());
	}
	
	static final boolean setDescriptionFromDAI(DAItem item, JsonObject element) {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			if(element.has("Description")) {
				JsonArray addInfoJArray = element.get("Description").getAsJsonArray();
				if(addInfoJArray.size() > 0) {
					String[] addInfo = new String[addInfoJArray.size()];
						
					for (int j = 0; j < addInfoJArray.size(); j++) {
						JsonElement element1 = addInfoJArray.get(j);
						addInfo[j] = element1.getAsString().replace('&', '§');
					}
					item.setAddInfo(addInfo);
				}
				return true;
			}
			return false;
		}
		return false;
	}
}
