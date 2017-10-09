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

import keelfy.darkdata.client.renderer.DARendererSword;
import keelfy.darkdata.constants.EnumItemType;
import keelfy.darkdata.constants.EnumSwordMaterial;
import keelfy.darkdata.handlers.DATabsHandler;
import keelfy.darkdata.items.Sword;
import keelfyutils.KUtils;

/**
 * @author keelfy
 * @created 6 июл. 2017 г.
 */
public final class SwordDeserializer extends DADeserializer implements JsonDeserializer<Sword[]> {

	private EnumSwordMaterial type;

	public SwordDeserializer(final EnumSwordMaterial type) {
		this.type = type;
	}

	@Override
	public final Sword[] deserialize(final JsonElement json, final Type typeOfT,
			final JsonDeserializationContext context) throws JsonParseException {
		final JsonArray jsonArray = json.getAsJsonArray();
		final Sword[] items = new Sword[jsonArray.size()];

		for (int i = 0; i < jsonArray.size(); i++) {
			final JsonObject element = jsonArray.get(i).getAsJsonObject();

			final Sword item = new Sword();

			setUnlocalizedNameFromDAI(item, element);
			setWeightFromDAI(item, element);
			setRarityFromDAI(item, element);
			setMaxDurabilityFromDAI(item, element);
			item.setType(type);
			item.setDamageVsEntity(element.get("DamageVsEntities").getAsFloat());

			if (KUtils.PROTECT_SERVER) {
				if (element.has("Persistence")) {
					item.setPersistence(element.get("Persistence").getAsFloat());
				}
			}

			if (item.getType() == EnumSwordMaterial.SILVER) {
				item.setCreativeTab(DATabsHandler.silverSwords);
			} else if (item.getType() == EnumSwordMaterial.STEEL) {
				item.setCreativeTab(DATabsHandler.steelSwords);
			}

			if (KUtils.PROTECT_CLIENT) {
				if (KUtils.isClient()) {
					final DARendererSword renderer = new DARendererSword();

					if (element.has("Set")) {
						item.setSet(element.get("Set").getAsString());
					} else {
						item.setSet("9800");
					}
					item.setTextureName(item.getSet(), element.get("Texture").getAsString());

					if (element.has("Icon")) {
						item.setIconName(element.get("Icon").getAsString());
					} else {
						item.setIconName(item.getTextureName());
					}

					if (element.has("Model")) {
						item.setModelName(element.get("Model").getAsString());
					} else {
						item.setModelName(item.getTextureName());
					}

					setDescriptionFromDAI(item, element);

					if (element.has("EntityRender")) {
						final JsonObject ers = element.get("EntityRender").getAsJsonObject();

						if (ers.has("Position")) {
							final JsonArray pos = ers.get("Position").getAsJsonArray();
							renderer.entTPosX += pos.get(0).getAsFloat();
							renderer.entTPosY += pos.get(1).getAsFloat();
							renderer.entTPosZ += pos.get(2).getAsFloat();
						}

						if (ers.has("Scale")) {
							renderer.entScale = ers.get("Scale").getAsFloat();
						}
					}
					if (element.has("EquippedRender")) {
						final JsonObject ers = element.get("EquippedRender").getAsJsonObject();

						if (ers.has("Position")) {
							final JsonArray pos = ers.get("Position").getAsJsonArray();
							renderer.eqTPosX += pos.get(0).getAsFloat();
							renderer.eqTPosY += pos.get(1).getAsFloat();
							renderer.eqTPosZ += pos.get(2).getAsFloat();
						}

						if (ers.has("Scale")) {
							renderer.eqScale = ers.get("Scale").getAsFloat();
						}
					}
					if (element.has("EFPRender")) {
						final JsonObject ers = element.get("EFPRender").getAsJsonObject();

						if (ers.has("Position")) {
							final JsonArray pos = ers.get("Position").getAsJsonArray();
							renderer.efpTPosX += pos.get(0).getAsFloat();
							renderer.efpTPosY += pos.get(1).getAsFloat();
							renderer.efpTPosZ += pos.get(2).getAsFloat();
						}

						if (ers.has("Scale")) {
							renderer.efpScale = ers.get("Scale").getAsFloat();
						}
					}
					if (element.has("InScabbardRender")) {
						final JsonObject ers = element.get("InScabbardRender").getAsJsonObject();

						if (ers.has("Position")) {
							final JsonArray pos = ers.get("Position").getAsJsonArray();
							renderer.behindTPosX = pos.get(0).getAsFloat();
							renderer.behindTPosY = pos.get(1).getAsFloat();
							renderer.behindTPosZ = pos.get(2).getAsFloat();
						}

						if (ers.has("Scale")) {
							renderer.behindScale = ers.get("Scale").getAsFloat();
						}

						if (ers.has("Rotation")) {
							final JsonArray rot = ers.get("Rotation").getAsJsonArray();
							renderer.behindRotX = rot.get(0).getAsFloat();
							renderer.behindRotY = rot.get(1).getAsFloat();
							renderer.behindRotZ = rot.get(2).getAsFloat();
						}
					}
					renderer.genResources(item);
					item.setRenderer(renderer);
				}
			}

			items[i] = item;
		}

		registerDetectedItems(items, EnumItemType.WEAPON);

		return items;
	}
}
