/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.constants;

/**
 * @author keelfy
 */
public enum EnumPlayerClass {
	WITCHER("Ведьмак"),
	HUMAN("Человек");
	
	private String localized;
	private EnumPlayerClass(String loc) {
		localized = loc;
	}
	
	public String getLocalizedName() {
		return localized;
	}
}
