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

import keelfy.darkdata.constants.EnumItemType;
import keelfy.darkdata.items.Material;
import keelfyutils.KUtils;

/**
 * @author keelfy
 * @created 6 июл. 2017 г.
 */
public final class MaterialDeserializer extends DADeserializer implements JsonDeserializer<Material[]> {

	@Override
	public final Material[] deserialize(final JsonElement json, final Type typeOfT,
			final JsonDeserializationContext context) throws JsonParseException {
		final JsonArray jsonArray = json.getAsJsonArray();
		final Material[] materials = new Material[jsonArray.size()];

		for (int i = 0; i < jsonArray.size(); i++) {
			final JsonObject element = jsonArray.get(i).getAsJsonObject();

			final Material item = new Material();

			setUnlocalizedNameFromDAI(item, element);
			setWeightFromDAI(item, element);
			setMaxStackSizeFromDAI(item, element);
			setRarityFromDAI(item, element);

			if (KUtils.PROTECT_CLIENT) {
				if (KUtils.isClient()) {
					setTextureFromDAI(item, element, "material");
					setDescriptionFromDAI(item, element);
				}
			}

			materials[i] = item;
		}

		registerDetectedItems(materials, EnumItemType.MATERIAL);

		return materials;
	}
}
