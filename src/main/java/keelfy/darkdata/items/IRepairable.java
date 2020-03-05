/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.items;

import keelfy.darkdata.constants.EnumRepairKit;

/**
 * @author keelfy
 */
public interface IRepairable {
	public static final String DURABILITY = "durability".toLowerCase();
	
	public EnumRepairKit getRepairKitType();
}
