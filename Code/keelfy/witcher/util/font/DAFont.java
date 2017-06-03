package keelfy.witcher.util.font;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author keelfy
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
