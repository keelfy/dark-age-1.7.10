/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkcore.common.player;

import keelfyutils.KUtils;
import net.minecraft.entity.player.EntityPlayer;

/**
 * @author keelfy
 * @created 13 июл. 2017 г.
 */
public final class DADataManager {

	public static final void cunstruct(final EntityPlayer player) {
		getPlayer(player);
	}

	public static final void clone(final EntityPlayer newPlayer, final EntityPlayer oldPlayer) {
		if (KUtils.PROTECT_SERVER) {
			DADataManager.getPlayer(newPlayer).copy(oldPlayer);
		}
	}

	public static final void update(final EntityPlayer player) {
		if (KUtils.PROTECT_SERVER) {
			DADataManager.getPlayer(player).onUpdate();
		}
	}

	public static final DAPlayerData getPlayer(final EntityPlayer player) {
		DAPlayerData dap = (DAPlayerData) player.getExtendedProperties(DAPlayerData.NBT_Name);
		if (dap == null) {
			player.registerExtendedProperties(DAPlayerData.NBT_Name, dap = new DAPlayerData(player));
		}
		return dap;
	}
}
