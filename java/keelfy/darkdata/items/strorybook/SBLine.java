/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.items.strorybook;

import java.util.Arrays;
import java.util.List;

import keelfyutils.str.KString;

public class SBLine {

	public static final int BOOK_TEXT_WIDTH = 116;
	public static final char SPLIT_CHAR = '\u1337';

	public String wrappedFormatting = KString.EMPTY;
	public String text = KString.EMPTY;

	public String addText(int charPos, final String strAdd) {
		if (charPos < 0) {
			charPos = 0;
		} else if (charPos > this.text.length()) {
			charPos = this.text.length();
		}

		String newText = this.text.substring(0, charPos) + strAdd + this.text.substring(charPos);
		List<String> newLines = listFormattedStringToWidth(newText, this.wrappedFormatting);

		this.text = newLines.get(0);
		String overflow = KString.EMPTY;
		for (int i = 1; i < newLines.size(); i++) {
			overflow += newLines.get(i);
		}

		return overflow;

	}

	public static int sizeStringToApproxWidthBlind(final String str, final int lenPixels) {
		if (getStringWidth(str) <= lenPixels)
			return str.length();
		String outStr = str.substring(0, sizeStringToWidthBlind(str, lenPixels));
		float partialCharWidth = lenPixels - getStringWidth(outStr);
		if (partialCharWidth / 4 > 0.5)
			return outStr.length() + 1;
		else
			return outStr.length();
	}

	public static int sizeStringToWidthBlind(final String str, final int maxLenPixels) {

		if (getStringWidth(str) <= maxLenPixels)
			return str.length();
		String outStr = KString.EMPTY;
		for (int i = 0; i < str.length(); i++) {
			if (getStringWidth(outStr + str.charAt(i)) > maxLenPixels)
				return outStr.length();
			outStr = outStr + str.charAt(i);
		}
		return outStr.length();

	}

	public String getTextWithWrappedFormatting() {
		return this.wrappedFormatting + this.text;

	}

	private static String getFormatFromString(final String par0Str) {
		String s1 = KString.EMPTY;
		int i = -1;
		int j = par0Str.length();

		while ((i = par0Str.indexOf(167, i + 1)) != -1) {
			if (i < j - 1) {
				char c0 = par0Str.charAt(i + 1);

				if (isFormatColor(c0)) {
					s1 = "\u00a7" + c0;
				} else if (isFormatSpecial(c0)) {
					s1 = s1 + "\u00a7" + c0;
				}
			}
		}
		return s1;
	}

	protected static boolean isFormatSpecial(final char par0) {
		return par0 >= 107 && par0 <= 111 || par0 >= 75 && par0 <= 79 || par0 == 114 || par0 == 82;

	}

	protected static boolean isFormatColor(final char par0) {
		return par0 >= 48 && par0 <= 57 || par0 >= 97 && par0 <= 102 || par0 >= 65 && par0 <= 70;

	}

	public static int sizeStringToWidth(final String par1Str, final int par2) {
		int j = par1Str.length();
		int k = 0;
		int l = 0;
		int i1 = -1;

		for (boolean flag = false; l < j; ++l) {
			char c0 = par1Str.charAt(l);

			switch (c0) {
				case 10:
					--l;
					break;
				case 167:
					if (l < j - 1) {
						++l;
						char c1 = par1Str.charAt(l);

						if (c1 != 108 && c1 != 76) {
							if (c1 == 114 || c1 == 82 || isFormatColor(c1)) {
								flag = false;
							}
						} else {
							flag = true;
						}
					}

					break;
				case 32:
					i1 = l;
				default:
					k += 4;

					if (flag) {
						++k;
					}
			}

			if (c0 == 10) {
				++l;
				i1 = l;
				break;
			}

			if (k > par2) {
				break;
			}
		}

		return l != j && i1 != -1 && i1 < l ? i1 : l;

	}

	private static String wrapStringToWidth(final String strIn, final int maxWidth, String wrappedFormatting) {

		int maxCharsInWidth = sizeStringToWidth(wrappedFormatting + strIn, maxWidth) - wrappedFormatting.length();

		if (strIn.length() <= maxCharsInWidth)
			return strIn;
		else {
			String s1 = strIn.substring(0, maxCharsInWidth);
			char c0 = strIn.charAt(maxCharsInWidth);
			boolean newlineOrSpace = c0 == 32 || c0 == 10;
			String s2 = strIn.substring(maxCharsInWidth + (newlineOrSpace ? 1 : 0));
			if (newlineOrSpace) {
				s1 += c0;
			}
			wrappedFormatting = getActiveFormatting(wrappedFormatting + s1);
			return s1 + SPLIT_CHAR + wrapStringToWidth(s2, maxWidth, wrappedFormatting);
		}

	}

	public static List<String> listFormattedStringToWidth(final String str, final String wrappedFormatting) {
		return Arrays
				.asList(wrapStringToWidth(str, BOOK_TEXT_WIDTH, wrappedFormatting).split(KString.EMPTY + SPLIT_CHAR));
	}

	public String getActiveFormatting() {
		return getActiveFormatting(this.wrappedFormatting + this.text);

	}

	private static String getActiveFormatting(final String strIn) {
		String activeFormatCode = KString.EMPTY;
		String activeColorCode = KString.EMPTY;
		for (int i = 0; i < strIn.length() - 1; i++) {
			if (strIn.charAt(i) == '\u00a7') {
				char formatChar = strIn.charAt(i + 1);
				if (formatChar == 'r' || formatChar == 'R') {
					activeFormatCode = KString.EMPTY;
					activeColorCode = KString.EMPTY;
				} else if ((formatChar >= 30 && formatChar <= 39) || (formatChar >= 65 && formatChar <= 70)
						|| (formatChar >= 97 && formatChar <= 102)) {
					activeFormatCode = KString.EMPTY;
					activeColorCode = "\u00a7" + formatChar;
				} else if ((formatChar >= 75 && formatChar <= 79) || (formatChar >= 107 && formatChar <= 111)) {
					activeFormatCode = "\u00a7" + formatChar;
				}
			}
		}
		return activeColorCode + activeFormatCode;

	}

	public static int getStringWidth(final String strIn) {
		String unfucked = strIn.replaceAll("\u00a7[A-Fa-f0-9]", "\u00a7r$0");
		return getStringWidthMC(unfucked);

	}

	private static int getStringWidthMC(final String p_78256_1_) {
		if (p_78256_1_ == null)
			return 0;
		else {
			int i = 0;
			boolean flag = false;

			for (int j = 0; j < p_78256_1_.length(); ++j) {
				char c0 = p_78256_1_.charAt(j);
				int k = 4;

				if (k < 0 && j < p_78256_1_.length() - 1) {
					++j;
					c0 = p_78256_1_.charAt(j);

					if (c0 != 108 && c0 != 76) {
						if (c0 == 114 || c0 == 82) {
							flag = false;
						}
					} else {
						flag = true;
					}

					k = 0;
				}

				i += k;

				if (flag && k > 0) {
					++i;
				}
			}

			return i;
		}
	}
}
