/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.constants;

import keelfy.darkdata.utils.DALocalization;
import keelfyutils.str.Brush;
import keelfyutils.str.KString;
import net.minecraft.util.StatCollector;

/**
 * @author keelfy
 * @created 6 июн. 2017 г.
 */
public enum EnumRarity {

	USUAL(StatCollector.translateToLocal(DALocalization.Rarity_Usual)),
	UNCOMMON(StatCollector.translateToLocal(DALocalization.Rarity_Uncommon)),
	RARE(StatCollector.translateToLocal(DALocalization.Rarity_Rare)),
	LEGENDARY(StatCollector.translateToLocal(DALocalization.Rarity_Legendary)),
	STUFF(StatCollector.translateToLocal(DALocalization.Rarity_Stuff)),
	NONE(KString.EMPTY),
	NOTENTERED(Brush.RED + "В геймплее не участвует");

	private String localized;

	private EnumRarity(final String loc) {
		this.localized = loc;
	}

	public String getLocalizedName() {
		return localized.replace('&', '§');
	}
}
