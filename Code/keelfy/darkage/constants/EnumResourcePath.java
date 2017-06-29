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
public enum EnumResourcePath {
	MUSIC("sounds/music"),
	SOUND("sounds");
	
	private String path;
	private EnumResourcePath(String path) {
		this.path = path + "/";
	}
	
	public String getPath() {
		return path;
	}
}
