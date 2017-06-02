package keelfy.witcher.handler.registers;

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
import keelfy.witcher.DarkAge;
import keelfy.witcher.item.DAItem.ItemRarity;
import keelfy.witcher.item.Material;
import keelfy.witcher.util.DAUtil;
import keelfy.witcher.util.LanguageUtil;
import keelfy.witcher.util.LanguageUtil.ItemLocalizationType;

/**
 * @author keelfy
 */
public class MaterialRegister {

	public MaterialRegister() {	
		try {
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.registerTypeAdapter(Material[].class, new MaterialDeserializer());
			Gson gson = gsonBuilder.create();
			
			Reader reader = new InputStreamReader(new FileInputStream(DarkAge.instance.fileHandler.getItemDAIFile("Materials")));
			
			gson.fromJson(reader, Material[].class);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static class MaterialDeserializer implements JsonDeserializer<Material[]> {
		
		@Override
		public Material[] deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {	
			JsonArray jsonArray = json.getAsJsonArray();
			Material[] materials = new Material[jsonArray.size()];
				
			for(int i = 0; i < jsonArray.size(); i++) {
				JsonObject element = jsonArray.get(i).getAsJsonObject();
					
				Material wcm = new Material();
				wcm.setUnlocalizedName(element.get("UnlocalizedName").getAsString());
					
				if(element.has("Weight"))
					wcm.setWeight(element.get("Weight").getAsFloat());
					
				if(element.has("MaxStackSize")) 
					wcm.setMaxStackSize(element.get("MaxStackSize").getAsInt());
					
				if(element.has("Rarity"))
					wcm.setRarity(ItemRarity.valueOf(element.get("Rarity").getAsString().toUpperCase()));
				
				wcm.setTextureName(DAUtil.MODID + ":material/" + element.get("Texture").getAsString());
					
				if(element.has("Description")) {
					JsonArray addInfoJArray = element.get("Description").getAsJsonArray();
					if(addInfoJArray.size() > 0) {
						String[] addInfo = new String[addInfoJArray.size()];
							
						for (int j = 0; j < addInfoJArray.size(); j++) {
							JsonElement element1 = addInfoJArray.get(j);
							addInfo[j] = element1.getAsString();
						}
							
						wcm.setAddInfo(addInfo);
					}
				}
				
				materials[i] = wcm;
			}

			for(Material material : materials) {
				GameRegistry.registerItem(material, material.getUnlocalizedName());
				LanguageUtil.addNameToItem(material, ItemLocalizationType.MATERIAL);
			}
			
			return materials;
		}
	}
}
