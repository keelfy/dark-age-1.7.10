/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.items.deserialization;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import keelfy.darkdata.constants.EnumFood;
import keelfy.darkdata.constants.EnumItemType;
import keelfy.darkdata.constants.EnumPotion;
import keelfy.darkdata.items.Food;
import keelfyutils.KUtils;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

/**
 * @author keelfy
 * @created 6 июл. 2017 г.
 */
public final class FoodDeserializer extends DADeserializer implements JsonDeserializer<Food[]> {
	@Override
	public final Food[] deserialize(final JsonElement json, final Type typeOfT,
			final JsonDeserializationContext context) throws JsonParseException {
		final JsonArray jsonArray = json.getAsJsonArray();
		final Food[] foods = new Food[jsonArray.size()];

		for (int i = 0; i < jsonArray.size(); i++) {
			final JsonObject element = jsonArray.get(i).getAsJsonObject();

			final Food item = new Food();

			setUnlocalizedNameFromDAI(item, element);
			setWeightFromDAI(item, element);
			setMaxStackSizeFromDAI(item, element);

			if (KUtils.PROTECT_CLIENT) {
				if (KUtils.isClient()) {
					setTextureFromDAI(item, element, "food");
					setDescriptionFromDAI(item, element);
				}
			}

			if (element.has("SaturationLevel")) {
				item.setSaturationLevel(EnumFood.valueOf(getString("SaturationLevel", element, true)));
			}

			if (KUtils.PROTECT_SERVER) {
				if (element.has("Saturation")) {
					item.setSaturation(element.get("Saturation").getAsFloat());
				}

				if (element.has("Effects")) {
					JsonArray effectsArray = element.get("Effects").getAsJsonArray();

					if (effectsArray.size() % 3 == 0) {
						int size = effectsArray.size() / 3;
						PotionEffect[] effects = new PotionEffect[size];

						for (int j = 1; j < size + 1; j++) {
							Potion potion = EnumPotion.valueOf(effectsArray.get(j * 3 - 3).getAsString().toUpperCase())
									.getEffect();
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
