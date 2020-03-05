/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author keelfy
 * @created 15 июл. 2017 г.
 */
public interface IItemWithRenderer {

	@SideOnly(Side.CLIENT)
	public void initRenderer();
}
