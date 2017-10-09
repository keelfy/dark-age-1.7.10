/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.constants;

import keelfy.darkdata.utils.DALocalization;
import keelfyutils.client.KLocalization;

/**
 * @author keelfy
 * @created 12 июл. 2017 г.
 */
public enum EnumEffect {

	Heal(KLocalization.localize(DALocalization.Effect_Heal)),
	Resistance(KLocalization.localize(DALocalization.Effect_Resistance)),
	Strength(KLocalization.localize(DALocalization.Effect_Strength)),
	NightVision(KLocalization.localize(DALocalization.Effect_NightVision)),
	WaterBreathing(KLocalization.localize(DALocalization.Effect_WaterBreathing)),
	SecondBreath(KLocalization.localize(DALocalization.Effect_SecondBreath)),
	MoveSpeed(KLocalization.localize(DALocalization.Effect_MoveSpeed));

	public String localized;

	private EnumEffect(final String localized) {
		this.localized = localized;
	}

}
