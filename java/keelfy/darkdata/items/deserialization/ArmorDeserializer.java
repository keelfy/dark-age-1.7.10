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

import keelfy.darkdata.client.renderer.DARendererItem;
import keelfy.darkdata.constants.EnumArmorClass;
import keelfy.darkdata.constants.EnumArmorPart;
import keelfy.darkdata.constants.EnumItemType;
import keelfy.darkdata.items.Armor;
import keelfyutils.KUtils;

/**
 * @author keelfy
 * @created 6 июл. 2017 г.
 */
public final class ArmorDeserializer extends DADeserializer implements JsonDeserializer<Armor[]> {

	@Override
	public final Armor[] deserialize(final JsonElement json, final Type typeOfT,
			final JsonDeserializationContext context) throws JsonParseException {
		final JsonArray jsonArray = json.getAsJsonArray();
		final Armor[] items = new Armor[jsonArray.size()];

		for (int i = 0; i < jsonArray.size(); i++) {
			final JsonObject object = jsonArray.get(i).getAsJsonObject();

			final Armor item = new Armor();

			setUnlocalizedNameFromDAI(item, object);
			setWeightFromDAI(item, object);
			setRarityFromDAI(item, object);
			setMaxDurabilityFromDAI(item, object);

			item.setType(EnumArmorClass.valueOf(getString("ArmorClass", object, true)));
			item.setPart(EnumArmorPart.valueOf(getString("Part", object, true)));
			item.setBlockingPercent(getFloat("BlockingPercent", object));

			if (KUtils.PROTECT_SERVER) {
				setPersistenceFromDAI(item, object);
			}

			if (KUtils.PROTECT_CLIENT) {
				if (KUtils.isClient()) {
					setSetFromDAI(item, object);
					setTextureFromDAI(item, object);
					setIconFromDAI(item, object);
					setDescriptionFromDAI(item, object);
					item.setRenderer(new DARendererItem(EnumItemType.ARMOR));
				}
			}

			items[i] = item;
		}

		registerDetectedItems(items, EnumItemType.ARMOR);

		return items;
	}
}
