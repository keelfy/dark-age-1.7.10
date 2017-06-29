/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.handlers.client;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkage.client.render.RenderCustomFont;
import keelfy.darkage.constants.EnumFont;
import keelfytools.KeelfyUtils;
import net.minecraft.client.renderer.texture.DynamicTexture;

/**
 * @author keelfy
 */
@SideOnly(Side.CLIENT)
public enum FontHandler {
	Instance;
	
	private Map<String, RenderCustomFont> fontMap;
	
	private FontHandler() {
		fontMap = new HashMap();
	}
	
	public Map<String, RenderCustomFont> getFontMap() {
		return fontMap;
	}
	
	public RenderCustomFont getFont(String font) {
		return fontMap.get(font);
	}
	
	public void init() {
		this.fontMap.put(EnumFont.DINProCondLight.getFontName(), new RenderCustomFont(new Font("DIN Pro Cond Light", Font.PLAIN, 35), true, false));
		this.fontMap.put(EnumFont.Verdana.getFontName(), new RenderCustomFont(new Font("Verdana", Font.PLAIN, 18), true, false));
	}
	
	@SideOnly(Side.CLIENT)
	public static class CustomFont {

	    private float imgSize = 512;
	    protected CharData[] charData = new CharData[256];
	    protected Font font;
	    protected boolean antiAlias;
	    protected boolean fractionalMetrics;
	    protected int fontHeight = -1;
	    protected int charOffset = 0;
	    protected DynamicTexture tex;

	    public CustomFont(Font font, boolean antiAlias, boolean fractionalMetrics) {
	    	if(KeelfyUtils.isClientSide()) {
		        this.font = font;
		        this.antiAlias = antiAlias;
		        this.fractionalMetrics = fractionalMetrics;
		        tex = setupTexture(font, antiAlias, fractionalMetrics, this.charData);
	    	}
	    }

	    protected DynamicTexture setupTexture(Font font, boolean antiAlias, boolean fractionalMetrics, CharData[] chars) {
	    	if(KeelfyUtils.isClientSide()) {
		        BufferedImage img = generateFontImage(font, antiAlias, fractionalMetrics, chars);
		        try {
		            return new DynamicTexture(img);
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
	    	}
	        return null;
	    }

	    protected BufferedImage generateFontImage(Font font, boolean antiAlias, boolean fractionalMetrics, CharData[] chars) {
	    	if(KeelfyUtils.isClientSide()) {
		        int imgSize = (int) this.imgSize;
		        BufferedImage bufferedImage = new BufferedImage(imgSize, imgSize, BufferedImage.TYPE_INT_ARGB);
		        Graphics2D g = (Graphics2D) bufferedImage.getGraphics();
		        g.setFont(font);
		        g.setColor(new Color(255, 255, 255, 0));
		        g.fillRect(0, 0, imgSize, imgSize);
		        g.setColor(Color.WHITE);
		        g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, fractionalMetrics ? RenderingHints.VALUE_FRACTIONALMETRICS_ON : RenderingHints.VALUE_FRACTIONALMETRICS_OFF);
		        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, antiAlias ? RenderingHints.VALUE_TEXT_ANTIALIAS_ON : RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
		        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, antiAlias ? RenderingHints.VALUE_ANTIALIAS_ON : RenderingHints.VALUE_ANTIALIAS_OFF);
		        FontMetrics fontMetrics = g.getFontMetrics();
		        int charHeight = 0;
		        int positionX = 0;
		        int positionY = 1;
		        for (int i = 0; i < chars.length; i++) {
		            char ch = (char) i;
		            CharData charData = new CharData();
		            Rectangle2D dimensions = fontMetrics.getStringBounds(String.valueOf(ch), g);
		            charData.width = (dimensions.getBounds().width + 8);
		            charData.height = dimensions.getBounds().height;
		            if (positionX + charData.width >= imgSize) {
		                positionX = 0;
		                positionY += charHeight;
		                charHeight = 0;
		            }
		            if (charData.height > charHeight) {
		                charHeight = charData.height;
		            }
		            charData.storedX = positionX;
		            charData.storedY = positionY;
		            if (charData.height > this.fontHeight) {
		                this.fontHeight = charData.height;
		            }
		            chars[i] = charData;
		            g.drawString(String.valueOf(ch), positionX + 2, positionY + fontMetrics.getAscent());
		            positionX += charData.width;
		        }
		        return bufferedImage;
	    	} else return null;
	    }

	    public void drawChar(CharData[] chars, char c, float x, float y) throws ArrayIndexOutOfBoundsException {
	    	if(KeelfyUtils.isClientSide()) {
		        try {
		            drawQuad(x, y, chars[c].width, chars[c].height, chars[c].storedX, chars[c].storedY, chars[c].width, chars[c].height);
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
	    	}
	    }

	    protected void drawQuad(float x, float y, float width, float height, float srcX, float srcY, float srcWidth, float srcHeight) {
	    	if(KeelfyUtils.isClientSide()) {
		        float renderSRCX = srcX / imgSize;
		        float renderSRCY = srcY / imgSize;
		        float renderSRCWidth = srcWidth / imgSize;
		        float renderSRCHeight = srcHeight / imgSize;
		        GL11.glTexCoord2f(renderSRCX + renderSRCWidth, renderSRCY);
		        GL11.glVertex2d(x + width, y);
		        GL11.glTexCoord2f(renderSRCX, renderSRCY);
		        GL11.glVertex2d(x, y);
		        GL11.glTexCoord2f(renderSRCX, renderSRCY + renderSRCHeight);
		        GL11.glVertex2d(x, y + height);
		        GL11.glTexCoord2f(renderSRCX, renderSRCY + renderSRCHeight);
		        GL11.glVertex2d(x, y + height);
		        GL11.glTexCoord2f(renderSRCX + renderSRCWidth, renderSRCY + renderSRCHeight);
		        GL11.glVertex2d(x + width, y + height);
		        GL11.glTexCoord2f(renderSRCX + renderSRCWidth, renderSRCY);
		        GL11.glVertex2d(x + width, y);
	    	}
	    }

	    public int getStringHeight() {
	        return (this.fontHeight - 8) / 2;
	    }

	    public int getStringWidth(String text) {
	        int width = 0;
	        for (char c : text.toCharArray()) {
	            if ((c < this.charData.length) && (c >= 0)) width += this.charData[c].width - 8 + this.charOffset;
	        }
	        return width / 2;
	    }

	    public boolean isAntiAlias() {
	        return this.antiAlias;
	    }

	    public void setAntiAlias(boolean antiAlias) {
	        if (this.antiAlias != antiAlias) {
	            this.antiAlias = antiAlias;
	            tex = setupTexture(this.font, antiAlias, this.fractionalMetrics, this.charData);
	        }
	    }

	    public boolean isFractionalMetrics() {
	        return this.fractionalMetrics;
	    }

	    public void setFractionalMetrics(boolean fractionalMetrics) {
	        if (this.fractionalMetrics != fractionalMetrics) {
	            this.fractionalMetrics = fractionalMetrics;
	            tex = setupTexture(this.font, this.antiAlias, fractionalMetrics, this.charData);
	        }
	    }

	    public Font getFont() {
	        return this.font;
	    }

	    public void setFont(Font font) {
	        this.font = font;
	        tex = setupTexture(font, this.antiAlias, this.fractionalMetrics, this.charData);
	    }

	    protected class CharData {
	        public int width;
	        public int height;
	        public int storedX;
	        public int storedY;
	    }
	}
}
