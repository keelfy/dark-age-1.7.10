/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.constants;

/**
 * @author keelfy
 * @created 6 июл. 2017 г.
 */
public enum EnumArmorPart {

	CHESTPLATE(1),
	GLOVES(0),
	PANTS(2),
	BOOTS(3);

	private int vanillaPartId;

	private EnumArmorPart(final int vanillaPartId) {
		this.vanillaPartId = vanillaPartId;
	}

	public final boolean isValid(final int vanillaId) {
		switch (vanillaId) {
			case 0:
				return ordinal() == GLOVES.ordinal();
			case 1:
				return ordinal() == CHESTPLATE.ordinal();
			case 2:
				return ordinal() == PANTS.ordinal();
			case 3:
				return ordinal() == BOOTS.ordinal();
			default:
				return false;
		}
	}

	public static final EnumArmorPart getPartByVanillaId(final int vanillaId) {
		switch (vanillaId) {
			case 0:
				return GLOVES;
			case 1:
				return CHESTPLATE;
			case 2:
				return PANTS;
			case 3:
				return BOOTS;
		}
		return null;
	}

}
