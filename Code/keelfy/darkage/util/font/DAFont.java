package keelfy.darkage.util.font;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author keelfy
 * TODO: Доделать кастомные шрифты
 */
@SideOnly(Side.CLIENT)
public enum DAFont {
	DINProCondLight("din_pro_cond_light"),
	Verdana("verdana");
	
	private String name;
	
	private DAFont(String name) {
		this.name = name;
	}
	
	public String getFontName() {
		return name;
	}
}
