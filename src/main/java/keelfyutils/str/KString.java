package keelfyutils.str;

import java.util.List;

/**
 * @author keelfy
 * @created 6 июл. 2017 г.
 */
public final class KString {

	public static final String EMPTY = "";
	public static final String SPACE = " ";
	public static final String RESOURCE_SPLITTER = ":";
	public static final String APOSTROPHE = "'/";

	public static final boolean isEmpty(final String str) {
		return str == null || EMPTY.equals(str);
	}

	public static final String formatFloat(float f, int i) {
		return f % 1 == 0 ? String.valueOf((int) f) : String.format("%." + i + "f", f);
	}

	public static final boolean reformat(final String string, final String format, final List<String> output) {
		int formatIndex = 0;
		int stringIndex = 0;

		while (formatIndex < format.length()) {
			final char formatChar = format.charAt(formatIndex);

			if ((formatChar == '%') && (formatIndex + 1 < format.length()) && ((format.charAt(formatIndex + 1) == 's') || (format.charAt(formatIndex + 1) == 'd') || (format.charAt(formatIndex + 1) == 'i')
					|| (format.charAt(formatIndex + 1) == 'f') || (format.charAt(formatIndex + 1) == 'c'))) {

				formatIndex += 2;
				String input = "";

				final char endChar = formatIndex < format.length() ? format.charAt(formatIndex) : '\0';

				while (stringIndex < string.length()) {
					final char messageChar = string.charAt(stringIndex);

					if (messageChar == endChar) {
						break;
					} else {
						input += messageChar;
						stringIndex++;
					}
				}

				output.add(input);
			} else if (stringIndex < string.length()) {
				final char messageChar = string.charAt(stringIndex);

				if (messageChar == formatChar) {
					formatIndex++;
					stringIndex++;
				} else {
					break;
				}
			} else {
				break;
			}
		}

		return (stringIndex >= string.length()) && (formatIndex >= format.length());
	}
}
