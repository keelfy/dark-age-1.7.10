package keelfy.witcher.handler.registers;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import keelfy.api.Brush;
import keelfy.api.registry.ItemRegistry;
import keelfy.witcher.item.DAItem;
import keelfy.witcher.item.DAItem.ItemRarity;
import keelfy.witcher.item.Money;
import keelfy.witcher.item.RepairKit;
import keelfy.witcher.item.RepairKit.RepairKitType;
import keelfy.witcher.item.smartlib.DAWrittenBook;
import keelfy.witcher.item.smartlib.SmartLibrary;
import keelfy.witcher.util.DATab.Tab;
import keelfy.witcher.util.DAUtil;
import net.minecraft.item.Item;
import net.minecraft.util.StatCollector;

/**
 * @author keelfy
 */
public class ItemRegister {
	public static Item writtenBook, smartLibrary;
	public static Item oren, krone, ducats, silver, gold;
	public static Item repairAKit1, repairAKit2, repairAKit3;
	public static Item repairWKit1, repairWKit2, repairWKit3;
	
	public ItemRegister() {
		new ArmorRegister();
		new FoodRegister();
		new MaterialRegister();
		new SwordRegister();
		new PotionRegister();
		
		String path;
		writtenBook = ItemRegistry.of("writtenbook", new DAWrittenBook()).registerWithoutTexture();
		smartLibrary = ItemRegistry.of("smartlibrary", new SmartLibrary()).registerWithoutTexture("Смарт-Библиотека");
		
		path = "money";
		oren = ItemRegistry.of("oren".toUpperCase(), new Money(Brush.YELLOW + "Темериский орен, одна из самых ходовых валют Севера.")).register(path);
		krone = ItemRegistry.of("krone".toUpperCase(), new Money()).register(path);
		ducats = ItemRegistry.of("ducat".toUpperCase(), new Money()).register(path);
		silver = ItemRegistry.of("silver".toUpperCase(), new Money()).register(path); 
		gold = ItemRegistry.of("gold".toUpperCase(), new Money()).register(path);
		
		path = "repairkit";
		repairAKit1 = ItemRegistry.of("repairAKit1".toUpperCase(), new RepairKit(ItemRarity.USUAL, 1, 15, RepairKitType.ARMOR, "Восстанавливает 15% прочности доспеха")).register(path);
		repairAKit2 = ItemRegistry.of("repairAKit2".toUpperCase(), new RepairKit(ItemRarity.UNCOMMON, 2, 40, RepairKitType.ARMOR, "Восстанавливает 40% прочности доспеха")).register(path);
		repairAKit3 = ItemRegistry.of("repairAKit3".toUpperCase(), new RepairKit(ItemRarity.RARE, 3, 90, RepairKitType.ARMOR, "Восстанавливает 90% прочности доспеха")).register(path);
		
		repairWKit1 = ItemRegistry.of("repairWKit1".toUpperCase(), new RepairKit(ItemRarity.USUAL, 1, 15, RepairKitType.WEAPON, "Восстанавливает 15% прочности оружия")).register(path);
		repairWKit2 = ItemRegistry.of("repairWKit2".toUpperCase(), new RepairKit(ItemRarity.UNCOMMON, 2, 40, RepairKitType.WEAPON, "Восстанавливает 40% прочности оружия")).register(path);
		repairWKit3 = ItemRegistry.of("repairWKit3".toUpperCase(), new RepairKit(ItemRarity.RARE, 3, 90, RepairKitType.WEAPON, "Восстанавливает 90% прочности оружия")).register(path);
	}
	
	public static class DAItemDeserializer implements JsonDeserializer<DAItem[]> {
		
		private String texturePath;
		public DAItemDeserializer(String texturePath) {
			this.texturePath = texturePath;
		}
		
		@Override
		public DAItem[] deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {	
			JsonArray jsonArray = json.getAsJsonArray();
			DAItem[] items = new DAItem[jsonArray.size()];
				
			for(int i = 0; i < jsonArray.size(); i++) {
				JsonObject element = jsonArray.get(i).getAsJsonObject();
					
				DAItem dai = new DAItem();
				dai.setUnlocalizedName(element.get("UnlocalizedName").getAsString());
					
				if(element.has("Weight"))
					dai.setWeight(element.get("Weight").getAsFloat());
					
				if(element.has("MaxStackSize")) 
					dai.setMaxStackSize(element.get("MaxStackSize").getAsInt());
					
				if(element.has("Rarity"))
					dai.setRarity(ItemRarity.valueOf(element.get("Rarity").getAsString()));
				
				if(element.has("CreativeTab"))
					dai.setCreativeTab(Tab.valueOf(element.get("CreativeTab").getAsString()).getCreativeTab());
				
				dai.setTextureName(DAUtil.MODID + ":" + texturePath + "/" + element.get("Texture").getAsString());
					
				if(element.has("Description")) {
					JsonArray addInfoJArray = element.get("Description").getAsJsonArray();
					if(addInfoJArray.size() > 0) {
						String[] addInfo = new String[addInfoJArray.size()];
							
						for (int j = 0; j < addInfoJArray.size(); j++) {
							JsonElement element1 = addInfoJArray.get(j);
							addInfo[j] = element1.getAsString();
						}
							
						dai.setAddInfo(addInfo);
					}
				}
				
				items[i] = dai;
			}
				
			for(DAItem item : items) {
				GameRegistry.registerItem(item, item.getUnlocalizedName());
				LanguageRegistry.addName(item, StatCollector.translateToLocal(item.getUnlocalizedName().toLowerCase()));
			}
			
			return items;
		}
	}
}
