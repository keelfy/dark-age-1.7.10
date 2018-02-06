/*
 *  Copyright (c) 2016-2017, Rubedo
 */

package keelfy.darkfactions.server;

import keelfyutils.KUtils;
import keelfyutils.commands.KCommand;
import net.minecraft.entity.player.EntityPlayer;

/**
 * @author keelfy
 * @created 20 нояб. 2017 г.
 */
public class DFCommands {

	@KCommand(onlyByPlayer = true)
	public static final void df(EntityPlayer player, String[] args) {
		if (KUtils.PROTECT_SERVER) {

		}
	}

}
