/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfyutils.network;

import net.minecraft.entity.player.EntityPlayer;

/**
 * @author keelfy
 * @created 14 июл. 2017 г.
 */
public final class KDataWatcher {

	public static final void addf(final EntityPlayer player, final int id, final float value) {
		player.getDataWatcher().addObjectByDataType(id, 3);
		updatef(player, id, Float.valueOf(value));
	}

	public static final void addi(final EntityPlayer player, final int id, final int value) {
		player.getDataWatcher().addObjectByDataType(id, 2);
		updatei(player, id, Integer.valueOf(value));
	}

	public static final void updatef(final EntityPlayer player, final int id, final float value) {
		player.getDataWatcher().updateObject(id, Float.valueOf(value));
	}

	public static final void updatei(final EntityPlayer player, final int id, final int value) {
		player.getDataWatcher().updateObject(id, Integer.valueOf(value));
	}

	public static final float getf(final EntityPlayer player, final int id) {
		return player.getDataWatcher().getWatchableObjectFloat(id);
	}

	public static final int geti(final EntityPlayer player, final int id) {
		return player.getDataWatcher().getWatchableObjectInt(id);
	}
}
