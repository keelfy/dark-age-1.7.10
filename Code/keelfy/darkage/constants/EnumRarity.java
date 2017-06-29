/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.constants;

import keelfytools.LocalizationUtils;
import keelfytools.log.Brush;
import net.minecraft.util.StatCollector;

/**
 * @author keelfy
 * @created 6 июн. 2017 г.
 */
public enum EnumRarity {
	USUAL(StatCollector.translateToLocal(LocalizationUtils.rarity_usual)),
	UNCOMMON(StatCollector.translateToLocal(LocalizationUtils.rarity_uncommon)),
	RARE(StatCollector.translateToLocal(LocalizationUtils.rarity_rare)),
	LEGENDARY(StatCollector.translateToLocal(LocalizationUtils.rarity_legendary)),
	STUFF(StatCollector.translateToLocal(LocalizationUtils.rarity_stuff)),
	NONE(""),
	NOTENTERED(Brush.RED + "В геймплее не участвует"),
	QUESTONLY(Brush.LIGHT_PURPLE + "Квестовый предмет");
	
	private String localized;
	private EnumRarity(String loc) {
		this.localized = loc;
	}
	
	public String getLocalizedName() {
		return localized.replace('&', '§');
	}
}