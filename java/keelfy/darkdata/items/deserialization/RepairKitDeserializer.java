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
import keelfy.darkdata.constants.EnumRepairKit;
import keelfy.darkdata.items.RepairKit;
import keelfyutils.KUtils;

/**
 * @author keelfy
 * @created 6 июл. 2017 г.
 */
public final class RepairKitDeserializer extends DADeserializer implements JsonDeserializer<RepairKit[]> {

	@Override
	public final RepairKit[] deserialize(final JsonElement json, final Type typeOfT,
			final JsonDeserializationContext context) throws JsonParseException {
		final JsonArray jsonArray = json.getAsJsonArray();
		final RepairKit[] items = new RepairKit[jsonArray.size()];

		for (int i = 0; i < jsonArray.size(); i++) {
			final JsonObject element = jsonArray.get(i).getAsJsonObject();

			final RepairKit item = new RepairKit();

			setUnlocalizedNameFromDAI(item, element);
			setWeightFromDAI(item, element);
			setMaxStackSizeFromDAI(item, element);
			setRarityFromDAI(item, element);

			if (KUtils.PROTECT_CLIENT) {
				if (KUtils.isClient()) {
					setTextureFromDAI(item, element, "repairkit");
					setDescriptionFromDAI(item, element);
				}
			}

			item.setRepairPercent(element.get("RepairPercentage").getAsFloat());
			item.setType(EnumRepairKit.valueOf(getString("Type", element, true)));

			items[i] = item;
		}

		registerDetectedItems(items, EnumItemType.REPAIRKIT);

		return items;
	}
}
