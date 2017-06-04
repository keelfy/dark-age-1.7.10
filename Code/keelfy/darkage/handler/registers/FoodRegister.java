package keelfy.darkage.handler.registers;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import cpw.mods.fml.common.registry.GameRegistry;
import keelfy.api.registry.ItemRegistry;
import keelfy.darkage.item.Food;
import keelfy.darkage.item.Food.SaturationLevel;
import keelfy.darkage.util.DAUtil;
import keelfy.darkage.util.LanguageUtil;
import keelfy.darkage.util.LanguageUtil.ItemLocalizationType;
import keelfy.darkage.util.PotionUtil;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

/**
 * @author keelfy
 * TODO: Десериалайзер для еды
 */
public class FoodRegister {
	
	private final String path = "food";
	
	//Raw Food
	public static Item rawMeat1, rawMeat2, rawFish1, water, appleJuice, mors;
	
	//Nosh
	public static Item pepper, honeycomb, apple1, egg1, meatSandwich1, bread1, blueberries, driedfruits1, grapes, goatMilk;

	//Nutritious
	public static Item meat1, meat2, fish1, goulash, melon, bread2;
	
	//Nourishing
	public static Item meat3, meat4, meat5, meat6, fish2, driedfruits2;
	
	public FoodRegister() {
//		try {
//			GsonBuilder gsonBuilder = new GsonBuilder();
//			gsonBuilder.registerTypeAdapter(Food[].class, new FoodDeserializer());
//			Gson gson = gsonBuilder.create();
//			
//			Reader reader = new InputStreamReader(new FileInputStream(DarkAge.instance.fileHandler.getItemDAIFile("Foods")));
//			
//			gson.fromJson(reader, Food[].class);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
		init();
	}
	
	// TODO: Доделать десериализацию еды после фикса багов
	public static class FoodDeserializer implements JsonDeserializer<Food[]> {
		
		@Override
		public Food[] deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {	
			JsonArray jsonArray = json.getAsJsonArray();
			Food[] foods = new Food[jsonArray.size()];
				
			for(int i = 0; i < jsonArray.size(); i++) {
				JsonObject element = jsonArray.get(i).getAsJsonObject();
					
				Food food = new Food();
				food.setUnlocalizedName(element.get("UnlocalizedName").getAsString());
					
				if(element.has("Weight"))
					food.setWeight(element.get("Weight").getAsFloat());
					
				if(element.has("MaxStackSize")) 
					food.setMaxStackSize(element.get("MaxStackSize").getAsInt());
					
				if(element.has("SaturationLevel"))
					food.setSaturationLevel(SaturationLevel.valueOf(element.get("Saturation").getAsString().toUpperCase()));
				
				if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
					if(element.has("Saturation"))
						food.setSaturation(element.get("Saturation").getAsFloat());
					
					if(element.has("Effects")) {
						JsonArray effectsArray = element.get("Effects").getAsJsonArray();
						
						if(effectsArray.size() % 3 == 0) {
							int size = effectsArray.size() / 3;
							PotionEffect[] effects = new PotionEffect[size];
							
							for(int j = 1; j < size + 1; j++) {
								Potion potion = PotionUtil.valueOf(effectsArray.get(j * 3 - 3).getAsString().toUpperCase()).getEffect();
								int duration = effectsArray.get(j * 3 - 2).getAsInt();
								int amplifier = effectsArray.get(j * 3 - 1).getAsInt();
								effects[j - 1] = new PotionEffect(potion.id, duration, amplifier);
							}
							
							food.setEffects(effects);
						}
					}
				}
				
				food.setTextureName(DAUtil.MODID + ":food/" + element.get("Texture").getAsString());
					
				if(element.has("Description")) {
					JsonArray addInfoJArray = element.get("Description").getAsJsonArray();
					if(addInfoJArray.size() > 0) {
						String[] addInfo = new String[addInfoJArray.size()];
							
						for (int j = 0; j < addInfoJArray.size(); j++) {
							JsonElement element1 = addInfoJArray.get(j);
							addInfo[j] = element1.getAsString();
						}
							
						food.setAddInfo(addInfo);
					}
				}
				
				foods[i] = food;
			}

			for(Food food : foods) {
				GameRegistry.registerItem(food, food.getUnlocalizedName());
				LanguageUtil.addNameToItem(food, ItemLocalizationType.FOOD);
			}
			
			return foods;
		}
	}
	
	private final void init() {
		rawMeat1 = ItemRegistry.of("rawMeat1".toUpperCase(), new Food(0.5F, SaturationLevel.RAW).setTextureName("01")).registerWithoutTexture();
		rawMeat2 = ItemRegistry.of("rawMeat2".toUpperCase(), new Food(0.5F, SaturationLevel.RAW).setTextureName("02")).registerWithoutTexture();
		rawFish1 = ItemRegistry.of("rawFish1".toUpperCase(), new Food(0.5F, SaturationLevel.RAW).setTextureName("03")).registerWithoutTexture();
		water = ItemRegistry.of("water".toUpperCase(), new Food(1, SaturationLevel.RAW).setTextureName("04")).registerWithoutTexture();
		appleJuice = ItemRegistry.of("appleJuice".toUpperCase(), new Food(1, SaturationLevel.RAW).setTextureName("05")).registerWithoutTexture();
		mors = ItemRegistry.of("mors".toUpperCase(), new Food(1, SaturationLevel.RAW).setTextureName("06")).registerWithoutTexture();
		
		pepper = ItemRegistry.of("pepper".toUpperCase(), new Food(0.1F, SaturationLevel.NOSH).setTextureName("07")).registerWithoutTexture();
		honeycomb = ItemRegistry.of("honeycomb".toUpperCase(), new Food(0.1F, SaturationLevel.NOSH).setTextureName("08")).registerWithoutTexture();
		apple1 = ItemRegistry.of("apple1".toUpperCase(), new Food(0.1F, SaturationLevel.NOSH).setTextureName("09")).registerWithoutTexture();
		egg1 = ItemRegistry.of("egg1".toUpperCase(), new Food(0.1F, SaturationLevel.NOSH).setTextureName(10)).registerWithoutTexture();
		meatSandwich1 = ItemRegistry.of("meatSandwich1".toUpperCase(), new Food(0.1F, SaturationLevel.NOSH).setTextureName(11)).registerWithoutTexture();
		bread1 = ItemRegistry.of("bread1".toUpperCase(), new Food(0.1F, SaturationLevel.NOSH).setTextureName(12)).registerWithoutTexture();
		blueberries = ItemRegistry.of("blueberries".toUpperCase(), new Food(0.1F, SaturationLevel.NOSH).setTextureName(13)).registerWithoutTexture();
		driedfruits1 = ItemRegistry.of("driedfruits1".toUpperCase(), new Food(0.1F, SaturationLevel.NOSH).setTextureName(14)).registerWithoutTexture();
		grapes = ItemRegistry.of("grapes".toUpperCase(), new Food(0.1F, SaturationLevel.NOSH).setTextureName(15)).registerWithoutTexture();
		goatMilk = ItemRegistry.of("goatMilk".toUpperCase(), new Food(1, SaturationLevel.NOSH).setTextureName(16)).registerWithoutTexture();
		
		meat1 = ItemRegistry.of("meat1".toUpperCase(), new Food(0.5F, SaturationLevel.NUTRITIOUS).setTextureName(17)).registerWithoutTexture();
		meat2 = ItemRegistry.of("meat2".toUpperCase(), new Food(0.5F, SaturationLevel.NUTRITIOUS).setTextureName(18)).registerWithoutTexture();
		fish1 = ItemRegistry.of("fish1".toUpperCase(), new Food(0.5F, SaturationLevel.NUTRITIOUS).setTextureName(19)).registerWithoutTexture();
		goulash = ItemRegistry.of("goulash".toUpperCase(), new Food(0.5F, SaturationLevel.NUTRITIOUS).setTextureName(20)).registerWithoutTexture();
		melon = ItemRegistry.of("melon".toUpperCase(), new Food(0.5F, SaturationLevel.NUTRITIOUS).setTextureName(21)).registerWithoutTexture();
		bread2 = ItemRegistry.of("bread2".toUpperCase(), new Food(0.3F, SaturationLevel.NUTRITIOUS).setTextureName(22)).registerWithoutTexture();
		
		meat3 = ItemRegistry.of("meat3".toUpperCase(), new Food(0.5F, SaturationLevel.NOURISHING).setTextureName(23)).registerWithoutTexture();
		meat4 = ItemRegistry.of("meat4".toUpperCase(), new Food(0.5F, SaturationLevel.NOURISHING).setTextureName(24)).registerWithoutTexture();
		meat5 = ItemRegistry.of("meat5".toUpperCase(), new Food(0.5F, SaturationLevel.NOURISHING).setTextureName(25)).registerWithoutTexture();
		meat6 = ItemRegistry.of("meat6".toUpperCase(), new Food(0.5F, SaturationLevel.NOURISHING).setTextureName(26)).registerWithoutTexture();
		fish2 = ItemRegistry.of("fish2".toUpperCase(), new Food(0.5F, SaturationLevel.NOURISHING).setTextureName(27)).registerWithoutTexture();
		driedfruits2 = ItemRegistry.of("driedfruits2".toUpperCase(), new Food(0.5F, SaturationLevel.NOURISHING).setTextureName(28)).registerWithoutTexture();
	}
}
