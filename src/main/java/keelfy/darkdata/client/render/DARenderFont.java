/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.client.render;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkdata.client.DAFontHandler.CustomFont;
import keelfyutils.KUtils;
import keelfyutils.str.KString;
import net.minecraft.client.renderer.texture.DynamicTexture;

@SideOnly(Side.CLIENT)
public class DARenderFont extends CustomFont {

	protected CustomFont.CharData[] boldChars = new CustomFont.CharData[2048];
	protected CustomFont.CharData[] italicChars = new CustomFont.CharData[2048];
	protected CustomFont.CharData[] boldItalicChars = new CustomFont.CharData[2048];

	private final int[] colorCode = new int[32];
	private final String colorcodeIdentifiers = "0123456789abcdefklmnor";

	public DARenderFont(final Font font, final boolean antiAlias, final boolean fractionalMetrics) {
		super(font, antiAlias, fractionalMetrics);
		setupMinecraftColorcodes();
		setupBoldItalicIDs();
	}

	public float drawStringWithShadow(final String text, final double x, final double y, final int color) {
		float shadowWidth = drawString(text, x + 1D, y + 1D, color, true);
		return Math.max(shadowWidth, drawString(text, x, y, color, false));
	}

	public float drawString(final String text, final float x, final float y, final int color) {
		return drawString(text, x, y, color, false);
	}

	public float drawCenteredStringWithShadow(final String text, final float x, final float y, final int color) {
		return drawStringWithShadow(text, x - getStringWidth(text) / 2, y, color);
	}

	public float drawCenteredString(final String text, final float x, final float y, final int color) {
		return drawString(text, x - getStringWidth(text) / 2, y, color);
	}

	public float drawString(final String text, double x, double y, int color, final boolean shadow) {
		if (KUtils.PROTECT_CLIENT) {
			x -= 1;
			y -= 2;
			if (text == null)
				return 0.0F;
			if (color == 553648127) {
				color = 16777215;
			}
			if ((color & 0xFC000000) == 0) {
				color |= -16777216;
			}

			if (shadow) {
				color = (color & 0xFCFCFC) >> 2 | color & 0xFF000000;
			}

			CustomFont.CharData[] currentData = this.charData;
			float alpha = (color >> 24 & 0xFF) / 255.0F;
			boolean randomCase = false;
			boolean bold = false;
			boolean italic = false;
			boolean strikethrough = false;
			boolean underline = false;
			boolean render = true;
			x *= 2.0D;
			y *= 2.0D;
			if (render) {
				GL11.glPushMatrix();
				GL11.glScalef(0.5f, 0.5f, 0.5f);
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glBlendFunc(770, 771);
				GL11.glColor4f((color >> 16 & 0xFF) / 255.0F, (color >> 8 & 0xFF) / 255.0F, (color & 0xFF) / 255.0F,
						alpha);
				int size = text.length();
				GL11.glEnable(GL11.GL_TEXTURE_2D);
				GL11.glBindTexture(GL11.GL_TEXTURE_2D, tex.getGlTextureId());
				for (int i = 0; i < size; i++) {
					char character = text.charAt(i);

					if ((character == '§') && (i < size)) {
						int colorIndex = 21;
						try {
							colorIndex = "0123456789abcdefklmnor".indexOf(text.charAt(i + 1));
						} catch (Exception e) {
							e.printStackTrace();
						}
						if (colorIndex < 16) {
							bold = false;
							italic = false;
							randomCase = false;
							underline = false;
							strikethrough = false;
							GL11.glBindTexture(GL11.GL_TEXTURE_2D, tex.getGlTextureId());
							currentData = this.charData;
							if ((colorIndex < 0) || (colorIndex > 15)) {
								colorIndex = 15;
							}
							if (shadow) {
								colorIndex += 16;
							}
							int colorcode = this.colorCode[colorIndex];
							GL11.glColor4f((colorcode >> 16 & 0xFF) / 255.0F, (colorcode >> 8 & 0xFF) / 255.0F,
									(colorcode & 0xFF) / 255.0F, alpha);
						} else if (colorIndex == 16) {
							randomCase = true;
						} else if (colorIndex == 17) {
							bold = true;
							if (italic) {
								GL11.glBindTexture(GL11.GL_TEXTURE_2D, texItalicBold.getGlTextureId());
								currentData = this.boldItalicChars;
							} else {
								GL11.glBindTexture(GL11.GL_TEXTURE_2D, texBold.getGlTextureId());
								currentData = this.boldChars;
							}
						} else if (colorIndex == 18) {
							strikethrough = true;
						} else if (colorIndex == 19) {
							underline = true;
						} else if (colorIndex == 20) {
							italic = true;
							if (bold) {
								GL11.glBindTexture(GL11.GL_TEXTURE_2D, texItalicBold.getGlTextureId());
								currentData = this.boldItalicChars;
							} else {
								GL11.glBindTexture(GL11.GL_TEXTURE_2D, texItalic.getGlTextureId());
								currentData = this.italicChars;
							}
						} else if (colorIndex == 21) {
							bold = false;
							italic = false;
							randomCase = false;
							underline = false;
							strikethrough = false;
							GL11.glColor4f((color >> 16 & 0xFF) / 255.0F, (color >> 8 & 0xFF) / 255.0F,
									(color & 0xFF) / 255.0F, alpha);
							GL11.glBindTexture(GL11.GL_TEXTURE_2D, tex.getGlTextureId());
							currentData = this.charData;
						}
						i++;
					} else if ((character < currentData.length) && (character >= 0)) {
						GL11.glBegin(4);
						drawChar(currentData, character, (float) x, (float) y);
						GL11.glEnd();
						if (strikethrough) {
							drawLine(x, y + currentData[character].height / 2, x + currentData[character].width - 8.0D,
									y + currentData[character].height / 2, 1.0F);
						}
						if (underline) {
							drawLine(x, y + currentData[character].height - 2.0D,
									x + currentData[character].width - 8.0D, y + currentData[character].height - 2.0D,
									1.0F);
						}
						x += currentData[character].width - 8 + this.charOffset;
					}
				}
				GL11.glHint(3155, 4352);
				GL11.glPopMatrix();
			}
			return (float) x / 2.0F;
		}
		return 0;
	}

	@Override
	public int getStringWidth(final String text) {
		if (KUtils.PROTECT_CLIENT) {
			if (text == null)
				return 0;
			int width = 0;
			CustomFont.CharData[] currentData = this.charData;
			boolean bold = false;
			boolean italic = false;
			int size = text.length();

			for (int i = 0; i < size; i++) {
				char character = text.charAt(i);
				if ((character == '§') && (i < size)) {
					int colorIndex = "0123456789abcdefklmnor".indexOf(character);
					if (colorIndex < 16) {
						bold = false;
						italic = false;
					} else if (colorIndex == 17) {
						bold = true;
						if (italic) {
							currentData = this.boldItalicChars;
						} else {
							currentData = this.boldChars;
						}
					} else if (colorIndex == 20) {
						italic = true;
						if (bold) {
							currentData = this.boldItalicChars;
						} else {
							currentData = this.italicChars;
						}
					} else if (colorIndex == 21) {
						bold = false;
						italic = false;
						currentData = this.charData;
					}
					i++;
				} else if ((character < currentData.length) && (character >= 0)) {
					width += currentData[character].width - 8 + this.charOffset;
				}
			}

			return width / 2;
		} else
			return 0;
	}

	@Override
	public void setFont(final Font font) {
		super.setFont(font);
		setupBoldItalicIDs();
	}

	@Override
	public void setAntiAlias(final boolean antiAlias) {
		super.setAntiAlias(antiAlias);
		setupBoldItalicIDs();
	}

	@Override
	public void setFractionalMetrics(final boolean fractionalMetrics) {
		super.setFractionalMetrics(fractionalMetrics);
		setupBoldItalicIDs();
	}

	protected DynamicTexture texBold;
	protected DynamicTexture texItalic;
	protected DynamicTexture texItalicBold;

	private void setupBoldItalicIDs() {
		texBold = setupTexture(this.font.deriveFont(1), this.antiAlias, this.fractionalMetrics, this.boldChars);
		texItalic = setupTexture(this.font.deriveFont(2), this.antiAlias, this.fractionalMetrics, this.italicChars);
		texItalicBold = setupTexture(this.font.deriveFont(3), this.antiAlias, this.fractionalMetrics,
				this.boldItalicChars);
	}

	private void drawLine(final double x, final double y, final double x1, final double y1, final float width) {
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glLineWidth(width);
		GL11.glBegin(1);
		GL11.glVertex2d(x, y);
		GL11.glVertex2d(x1, y1);
		GL11.glEnd();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
	}

	public List<String> wrapWords(final String text, final double width) {
		List finalWords = new ArrayList();
		if (getStringWidth(text) > width) {
			String[] words = text.split(KString.SPACE);
			String currentWord = KString.EMPTY;
			char lastColorCode = 65535;

			for (String word : words) {
				for (int i = 0; i < word.toCharArray().length; i++) {
					char c = word.toCharArray()[i];

					if ((c == '§') && (i < word.toCharArray().length - 1)) {
						lastColorCode = word.toCharArray()[(i + 1)];
					}
				}
				if (getStringWidth(currentWord + word + KString.SPACE) < width) {
					currentWord = currentWord + word + KString.SPACE;
				} else {
					finalWords.add(currentWord);
					currentWord = "§" + lastColorCode + word + KString.SPACE;
				}
			}
			if (currentWord.length() > 0)
				if (getStringWidth(currentWord) < width) {
					finalWords.add("§" + lastColorCode + currentWord + KString.SPACE);
					currentWord = KString.EMPTY;
				} else {
					for (String s : formatString(currentWord, width)) {
						finalWords.add(s);
					}
				}
		} else {
			finalWords.add(text);
		}
		return finalWords;
	}

	public List<String> formatString(final String string, final double width) {
		List finalWords = new ArrayList();
		String currentWord = KString.EMPTY;
		char lastColorCode = 65535;
		char[] chars = string.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			char c = chars[i];

			if ((c == '§') && (i < chars.length - 1)) {
				lastColorCode = chars[(i + 1)];
			}

			if (getStringWidth(currentWord + c) < width) {
				currentWord = currentWord + c;
			} else {
				finalWords.add(currentWord);
				currentWord = "§" + lastColorCode + String.valueOf(c);
			}
		}

		if (currentWord.length() > 0) {
			finalWords.add(currentWord);
		}

		return finalWords;
	}

	private void setupMinecraftColorcodes() {
		for (int index = 0; index < 32; index++) {
			int noClue = (index >> 3 & 0x1) * 85;
			int red = (index >> 2 & 0x1) * 170 + noClue;
			int green = (index >> 1 & 0x1) * 170 + noClue;
			int blue = (index >> 0 & 0x1) * 170 + noClue;

			if (index == 6) {
				red += 85;
			}

			if (index >= 16) {
				red /= 4;
				green /= 4;
				blue /= 4;
			}

			this.colorCode[index] = ((red & 0xFF) << 16 | (green & 0xFF) << 8 | blue & 0xFF);
		}
	}
}
