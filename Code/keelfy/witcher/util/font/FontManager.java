package keelfy.witcher.util.font;

import java.awt.Font;
import java.util.HashMap;
import java.util.Map;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;

/**
 * @author keelfy
 */
@SideOnly(Side.CLIENT)
public class FontManager {
	
	private static FontManager instance;
	private Map<String, RenderCustomFont> fontMap;
	
	private FontManager() {
		fontMap = new HashMap<String, RenderCustomFont>();
	}
	
	public static FontManager getInstance() {
		if(instance == null) {
			instance = new FontManager();
		}
		return instance;
	}
	
	public Map<String, RenderCustomFont> getFontMap() {
		return fontMap;
	}
	
	public RenderCustomFont getFont(String font) {
		return fontMap.get(font);
	}
	
	public void registerFonts() {
		this.fontMap.put("din_pro_cond_light", new RenderCustomFont(new Font("DIN Pro Cond Light", Font.PLAIN, 35), true, false));
		this.fontMap.put("verdana", new RenderCustomFont(new Font("Verdana", Font.PLAIN, 18), true, false));
	}
	
	public static void renderString(DAFont twmFont, String text, int x, int y, int color) {
		RenderCustomFont font = FontManager.getInstance().getFont(twmFont.getFontName());
		
		if(font == null) {
			Minecraft.getMinecraft().fontRenderer.drawString(text, x, y, color);
		} else {
			font.drawString(text, x, y, color);
		}
	}
	
	public static float getStringWidth(DAFont twmFont, String text) {
		RenderCustomFont font = FontManager.getInstance().getFont(twmFont.getFontName());
		
		if(font == null) {
			return Minecraft.getMinecraft().fontRenderer.getStringWidth(text);
		} else {
			return font.getStringWidth(text);
		}
	}
	
	public static float getStringHeight(DAFont twmFont, String text) {
		RenderCustomFont font = FontManager.getInstance().getFont(twmFont.getFontName());
		
		if(font == null) {
			return Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT;
		} else {
			return font.fontHeight;
		}
	}
}
