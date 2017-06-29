/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.constants;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author keelfy
 */
@SideOnly(Side.CLIENT)
public enum EnumFont {
	DINProCondLight("din_pro_cond_light"),
	Verdana("verdana");
	
	private String name;
	
	private EnumFont(String name) {
		this.name = name;
	}
	
	public String getFontName() {
		return name;
	}
}
