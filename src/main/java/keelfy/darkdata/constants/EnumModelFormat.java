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
public enum EnumModelFormat {
	OBJ("obj"),
	KA("ka"),
	Techne("");

	private String type;

	private EnumModelFormat(final String type) {
		this.type = type;
	}

	public String type() {
		return type;
	}
}
