/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfyutils.client;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfyutils.KUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;

/**
 * @author keelfy
 * @created 14 июл. 2017 г.
 */
@SideOnly(Side.CLIENT)
public final class KGL {

	private static final Minecraft MC = mc();

	public static final Minecraft mc() {
		return Minecraft.getMinecraft();
	}

	public static final FontRenderer font() {
		if (MC != null)
			return MC.fontRenderer;
		else
			return null;
	}

	public static final TextureManager textureManager() {
		if (MC != null)
			return MC.getTextureManager();
		else
			return null;
	}

	public static final void colorRGBA(final int rgba) {
		final float red = (float) ((rgba >> 24) & 0xFF) / 0xFF;
		final float green = (float) ((rgba >> 16) & 0xFF) / 0xFF;
		final float blue = (float) ((rgba >> 8) & 0xFF) / 0xFF;
		final float alpha = (float) ((rgba >> 0) & 0xFF) / 0xFF;

		GL11.glColor4f(red, green, blue, alpha);
	}

	public static final int fontColor(final int rgba) {
		final int alpha = (rgba >> 0) & 0xFF;
		final int red = (rgba >> 24) & 0xFF;
		final int blue = (rgba >> 8) & 0xFF;
		final int green = (rgba >> 16) & 0xFF;

		return (alpha << 24) | (red << 16) | (blue << 8) | (green << 0);
	}

	public static final void string(final FontRenderer font, final String string, final int x, final int y,
			final int argb, final boolean shadow) {
		if (font != null) {
			font.drawString(string, x, y, fontColor(argb), shadow);
		}
	}

	public static final void string(final FontRenderer font, final String string, final int x, final int y,
			final int argb) {
		string(font, string, x, y, argb, false);
	}

	public static final void string(final String string, final int x, final int y, final int argb,
			final boolean shadow) {
		string(font(), string, x, y, argb, shadow);
	}

	public static final void string(final String string, final int x, final int y, final int argb) {
		string(string, x, y, argb, false);
	}

	public static final void string(final String string, final int x, final int y) {
		string(string, x, y, KColors.FONT_COLOR);
	}

	public static final int stringWidth(final FontRenderer font, final String string) {
		if (font != null)
			return font.getStringWidth(string);
		else
			return 0;
	}

	public static final int stringWidth(final String string) {
		return stringWidth(font(), string);
	}

	public static final int stringHeight(final FontRenderer font) {
		if (font != null)
			return font.FONT_HEIGHT;
		else
			return 0;
	}

	public static final int stringHeight() {
		return stringHeight(font());
	}

	public static final void bindTexture(final Minecraft mc, final ResourceLocation location) {
		if (mc.getTextureManager() != null) {
			mc.getTextureManager().bindTexture(location);
		}
	}

	public static final void bindTexture(final ResourceLocation location) {
		if (textureManager() != null) {
			textureManager().bindTexture(location);
		}
	}

	public static final void texturedRect(final int x, final int y, final float z, final int width, final int height,
			final int srcX, final int srcY, final int srcWidth, final int srcHeight) {
		float f = 0.00390625F;
		float f1 = 0.00390625F;
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(x + 0, y + height, z, (srcX + 0) * f, (srcY + srcHeight) * f1);
		tessellator.addVertexWithUV(x + width, y + height, z, (srcX + srcWidth) * f, (srcY + srcHeight) * f1);
		tessellator.addVertexWithUV(x + width, y + 0, z, (srcX + srcWidth) * f, (srcY + 0) * f1);
		tessellator.addVertexWithUV(x + 0, y + 0, z, (srcX + 0) * f, (srcY + 0) * f1);
		tessellator.draw();
	}

	public static final void texturedRect(final int x, final int y, final float z, final int srcX, final int srcY,
			final int width, final int height) {
		texturedRect(x, y, z, width, height, srcX, srcY, width, height);
	}

	public static final void texturedRect(final int x, final int y, final int width, final int height, final int srcX,
			final int srcY, final int srcWidth, final int srcHeight) {
		texturedRect(x, y, 0, width, height, srcX, srcY, srcWidth, srcHeight);
	}

	public static final void texturedRect(final int x, final int y, final int srcX, final int srcY, final int width,
			final int height) {
		texturedRect(x, y, 0, srcX, srcY, width, height);
	}

	public static void rect(final int x, final int y, final int width, final int height) {
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.addVertex(x + 0, y + height, 0.0D);
		tessellator.addVertex(x + width, y + height, 0.0D);
		tessellator.addVertex(x + width, y + 0, 0.0D);
		tessellator.addVertex(x + 0, y + 0, 0.0D);
		tessellator.draw();
	}

	public static final void drawFullscreenTexturedRectZ(final int width, final int height, final int zLevel) {
		if (KUtils.PROTECT_CLIENT) {
			final Tessellator tess = Tessellator.instance;
			tess.startDrawingQuads();
			tess.addVertexWithUV(0.0D, height, zLevel, 0.0D, 1.0D);
			tess.addVertexWithUV(width, height, zLevel, 1.0D, 1.0D);
			tess.addVertexWithUV(width, 0.0D, zLevel, 1.0D, 0.0D);
			tess.addVertexWithUV(0.0D, 0.0D, zLevel, 0.0D, 0.0D);
			tess.draw();
		}
	}

	public static final void drawTexturedRectZ(final int posXScreen, final int posYScreen, final int posXTexture,
			final int posYTexture, final int allocX, final int allocY, final float zLevel) {
		if (KUtils.PROTECT_CLIENT) {
			final float f = 0.00390625F;
			final float f1 = 0.00390625F;
			final Tessellator tessellator = Tessellator.instance;
			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(posXScreen + 0, posYScreen + allocY, zLevel, (posXTexture + 0) * f,
					(posYTexture + allocY) * f1);
			tessellator.addVertexWithUV(posXScreen + allocX, posYScreen + allocY, zLevel, (posXTexture + allocX) * f,
					(posYTexture + allocY) * f1);
			tessellator.addVertexWithUV(posXScreen + allocX, posYScreen + 0, zLevel, (posXTexture + allocX) * f,
					(posYTexture + 0) * f1);
			tessellator.addVertexWithUV(posXScreen + 0, posYScreen + 0, zLevel, (posXTexture + 0) * f,
					(posYTexture + 0) * f1);
			tessellator.draw();
		}
	}

}
