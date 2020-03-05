/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.constants;

/**
 * @author keelfy
 */
public enum EnumPlayerClass {

	WITCHER("Ведьмак"),
	HUMAN("Человек");

	private String localized;

	private EnumPlayerClass(final String loc) {
		localized = loc;
	}

	public String getLocalizedName() {
		return localized;
	}
}
