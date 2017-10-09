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
import keelfy.darkdata.items.Money;
import keelfyutils.KUtils;

/**
 * @author keelfy
 * @created 6 июл. 2017 г.
 */
public final class MoneyDeserializer extends DADeserializer implements JsonDeserializer<Money[]> {

	@Override
	public final Money[] deserialize(final JsonElement json, final Type typeOfT,
			final JsonDeserializationContext context) throws JsonParseException {
		final JsonArray jsonArray = json.getAsJsonArray();
		final Money[] items = new Money[jsonArray.size()];

		for (int i = 0; i < jsonArray.size(); i++) {
			final JsonObject element = jsonArray.get(i).getAsJsonObject();

			final Money item = new Money();

			setUnlocalizedNameFromDAI(item, element);
			setWeightFromDAI(item, element);
			setMaxStackSizeFromDAI(item, element);

			if (KUtils.PROTECT_CLIENT) {
				if (KUtils.isClient()) {
					setTextureFromDAI(item, element, "money");
					setDescriptionFromDAI(item, element);
				}
			}
			item.setValueInOrenes(element.get("OrenesValue").getAsFloat());

			items[i] = item;
		}

		registerDetectedItems(items, EnumItemType.MONEY);

		return items;
	}
}
