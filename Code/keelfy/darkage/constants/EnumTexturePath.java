/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.constants;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author keelfy
 * @created 6 июн. 2017 г.
 */
@SideOnly(Side.CLIENT)
public enum EnumTexturePath {
	ARMOR("textures/armor"),
	RARITY("textures/gui/inventory/rarity"),
	SWORD("textures/items/melee"),
	SCABBARD("textures/items/scabbard"),
	SABRE("textures/items/melee/sabre"),
	GUI("textures/gui"),
	INVENTORY("textures/gui/inventory"),
	SIGN("textures/gui/signs/graph"),
	SKIN("textures/models/skins"),
	ENTITY("textures/entity"),
	PARTICLE("textures/particle"),
	MEDALION("textures/models/medalion");
	
	private String path;
	private EnumTexturePath(String path) {
		this.path = path + "/";
	}
	
	public String getPath() {
		return path;
	}
}
