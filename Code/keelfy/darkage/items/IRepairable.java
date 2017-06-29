/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.items;

import keelfy.darkage.constants.EnumRepairKit;

/**
 * @author keelfy
 */
public interface IRepairable {
	public static final String DURABILITY = "durability".toLowerCase();
	
	public EnumRepairKit getRepairKitType();
}
