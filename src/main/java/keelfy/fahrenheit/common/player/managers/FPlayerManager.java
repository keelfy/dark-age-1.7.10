/*
 *  Copyright (c) 2016-2017, Rubedo
 */

package keelfy.fahrenheit.common.player.managers;

import keelfy.fahrenheit.common.player.FPlayerData;
import keelfyutils.KUtils;
import net.minecraft.entity.player.EntityPlayer;

/**
 * @author keelfy
 * @created 18 сент. 2017 г.
 */
public class FPlayerManager {

	public static final void cunstruct(final EntityPlayer player) {
		getPlayer(player);
	}

	public static final void clone(final EntityPlayer newPlayer, final EntityPlayer oldPlayer) {
		if (KUtils.PROTECT_SERVER) {
			final FPlayerData fpd = FPlayerManager.getPlayer(newPlayer);

			fpd.copy(oldPlayer);
		}
	}

	public static final void update(final EntityPlayer player) {
		if (KUtils.PROTECT_SERVER) {
			FPlayerManager.getPlayer(player).onUpdate();
		}
	}

	public static final FPlayerData getPlayer(final EntityPlayer player) {
		FPlayerData dap = (FPlayerData) player.getExtendedProperties(FPlayerData.NBT_Name);
		if (dap == null) {
			player.registerExtendedProperties(FPlayerData.NBT_Name, dap = new FPlayerData(player));
		}
		return dap;
	}

}
