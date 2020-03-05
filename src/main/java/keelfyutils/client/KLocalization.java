package keelfyutils.client;

import keelfyutils.str.KString;
import net.minecraft.util.StatCollector;

/**
 * @author keelfy
 * @created 30 мая 2017 г.
 */
public final class KLocalization {

	public static final String localize(final String s) {
		return StatCollector.translateToLocal(s).replace('&', '§').replace("'", "\'");
	}

	public static final String withColors(final String s) {
		return s.replace('&', '§').replace("'", "\'");
	}

	public static final String replaceLastChars(final String s, final String toReplace, final int number) {
		final int length = s.length();
		if (length < number)
			return KString.EMPTY;
		return s.substring(0, length - number) + toReplace;
	}
}
