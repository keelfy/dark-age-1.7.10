/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.constants;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author keelfy
 * @created 6 июн. 2017 г.
 */
@SideOnly(Side.CLIENT)
public enum EnumTexturePath {

	Armor("textures/armor"),
	Rarity("textures/gui/inventory/rarity"),
	Sword("textures/items/melee"),
	Scabbard("textures/items/scabbard"),
	Sabre("textures/items/melee/sabre"),
	GUI("textures/gui"),
	Inventory("textures/gui/inventory"),
	Sign("textures/gui/signs/graph"),
	Skin("textures/models/skins"),
	Entity("textures/entity"),
	Particle("textures/particle"),
	Medalion("textures/models/medalion");

	private String path;

	private EnumTexturePath(final String path) {
		this.path = path + "/";
	}

	public String getPath() {
		return path;
	}
}
