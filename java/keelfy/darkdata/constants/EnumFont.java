/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.constants;

import java.awt.Font;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author keelfy
 */
@SideOnly(Side.CLIENT)
public enum EnumFont {

	DINProCondLight_18("din_pro_cond_light", 18, Font.PLAIN),
	Verdana_18("verdana 18", 18, Font.PLAIN),
	Verdana_15("verdana 15", 15, Font.PLAIN),
	TrebuchetMS_15("Trebuchet MS 15", 15, Font.PLAIN),
	TrebuchetMS_18("Trebuchet MS 18", 18, Font.PLAIN),
	TrebuchetMS_10("Trebuchet MS 10", 10, Font.PLAIN);

	private String name;
	private int size;
	private int type;

	private EnumFont(String name, int size, int type) {
		this.name = name;
		this.size = size;
		this.type = type;
	}

	public int getType() {
		return type;
	}

	public String getFontName() {
		return name;
	}

	public int getSize() {
		return size;
	}
}
