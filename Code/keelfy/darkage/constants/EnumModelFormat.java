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
public enum EnumModelFormat {
	OBJ("obj"),
	KA("ka");
	
	private String type;
	private EnumModelFormat(String type) {
		this.type = type;
	}
	
	public String type() {
		return type;
	}
}