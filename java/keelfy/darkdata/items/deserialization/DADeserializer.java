/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.items.deserialization;

import com.google.gson.JsonObject;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkdata.DarkData;
import keelfy.darkdata.constants.EnumItemType;
import keelfy.darkdata.constants.EnumModelFormat;
import keelfy.darkdata.constants.EnumRarity;
import keelfy.darkdata.items.Armor;
import keelfy.darkdata.items.DAItem;
import keelfy.darkdata.items.ItemWithRender;
import keelfy.darkdata.items.Sword;
import keelfy.darkdata.utils.DALocalization;
import keelfyutils.KUtils;

/**
 * @author keelfy
 * @created 6 июл. 2017 г.
 */
public class DADeserializer {

	protected final String getString(final String str, final JsonObject object, final boolean upper) {
		String s = object.get(str).getAsString();
		return upper ? s.toUpperCase() : s;
	}

	protected final float getFloat(final String str, final JsonObject object) {
		return object.get(str).getAsFloat();
	}

	protected final void setPersistenceFromDAI(final Sword sword, final JsonObject object) {
		if (KUtils.PROTECT_SERVER) {
			if (object.has("Persistence")) {
				sword.setPersistence(getFloat("Persistence", object));
			}
		}
	}

	protected final void setPersistenceFromDAI(final Armor armor, final JsonObject object) {
		if (KUtils.PROTECT_SERVER) {
			if (object.has("Persistence")) {
				armor.setPersistence(getFloat("Persistence", object));
			}
		}
	}

	protected final void registerDetectedItems(final DAItem[] items, final EnumItemType locType) {
		for (final DAItem item : items) {
			GameRegistry.registerItem(item, item.getUnlocalizedName());
			DALocalization.addNameToItem(item, locType);
		}
	}

	@SideOnly(Side.CLIENT)
	protected final void setSetFromDAI(final ItemWithRender item, final JsonObject object) {
		if (KUtils.PROTECT_CLIENT) {
			if (object.has("Set")) {
				item.setSet(object.get("Set").getAsString());
			} else {
				item.setSet("9800");
			}
		}
	}

	@SideOnly(Side.CLIENT)
	protected final void setIconFromDAI(final ItemWithRender item, final JsonObject object) {
		if (KUtils.PROTECT_CLIENT) {
			if (object.has("Icon")) {
				item.setIconName(object.get("Icon").getAsString());
			} else {
				item.setIconName(item.getTextureName());
			}
		}
	}

	@SideOnly(Side.CLIENT)
	protected final void setModelFileFromDAI(final ItemWithRender item, final JsonObject object) {
		if (KUtils.PROTECT_CLIENT) {
			if (object.has("Model")) {
				item.setModelName(object.get("Model").getAsString());
			} else {
				item.setModelName(item.getTextureName());
			}
		}
	}

	@SideOnly(Side.CLIENT)
	protected final void setModelClassFromDAI(final ItemWithRender item, final JsonObject object) {
		if (KUtils.PROTECT_CLIENT) {
			if (object.has("ModelClass")) {
				item.setModel(EnumModelFormat.Techne, getString("ModelClass", object, false));
			}
		}
	}

	protected final void setMaxDurabilityFromDAI(final DAItem item, final JsonObject object) {
		item.setMaxDurability(object.get("MaxDurability").getAsFloat());
	}

	protected final void setUnlocalizedNameFromDAI(final DAItem item, final JsonObject object) {
		item.setUnlocalizedName(object.get("UnlocalizedName").getAsString());
	}

	protected final boolean setWeightFromDAI(final DAItem item, final JsonObject object) {
		if (object.has("Weight")) {
			item.setWeight(object.get("Weight").getAsFloat());
			return true;
		}
		return false;
	}

	protected final boolean setMaxStackSizeFromDAI(final DAItem item, final JsonObject object) {
		if (object.has("MaxStackSize")) {
			item.setMaxStackSize(object.get("MaxStackSize").getAsInt());
			return true;
		}
		return false;
	}

	protected final boolean setRarityFromDAI(final DAItem item, final JsonObject object) {
		if (object.has("Rarity")) {
			item.setRarity(EnumRarity.valueOf(getString("Rarity", object, true)));
			return true;
		}
		return false;
	}

	@SideOnly(Side.CLIENT)
	protected final void setTextureFromDAI(final DAItem item, final JsonObject object, final String path) {
		if (KUtils.PROTECT_CLIENT) {
			item.setTextureName(DarkData.MOD_ID + ":" + path + "/" + object.get("Texture").getAsString());
		}
	}

	@SideOnly(Side.CLIENT)
	protected final void setTextureFromDAI(final ItemWithRender item, final JsonObject object) {
		if (KUtils.PROTECT_CLIENT) {
			item.setTextureName(item.getSet(), object.get("Texture").getAsString());
		}
	}

	@SideOnly(Side.CLIENT)
	protected final boolean setDescriptionFromDAI(final DAItem item, final JsonObject object) {
		if (KUtils.PROTECT_CLIENT) {
			if (object.has("Description")) {
				item.setAddInfo(object.get("Description").getAsString());
				return true;
			}
			return false;
		}
		return false;
	}
}
