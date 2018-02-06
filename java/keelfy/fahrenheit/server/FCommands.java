/*
 *  Copyright (c) 2016-2017, Rubedo
 */

package keelfy.fahrenheit.server;

import keelfy.fahrenheit.Fahrenheit;
import keelfy.fahrenheit.common.player.managers.FPlayerManager;
import keelfyutils.KUtils;
import keelfyutils.commands.KCommand;
import keelfyutils.server.KServer;
import keelfyutils.str.Brush;
import keelfyutils.str.KLog;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

/**
 * @author keelfy
 * @created 21 сент. 2017 г.
 */
public class FCommands {

	@KCommand(usage = "Устанавливает температуру игроку")
	public static final void setTemperature(final ICommandSender sender, final String[] args) {
		if (KUtils.PROTECT_SERVER) {
			if (args.length == 2) {
				EntityPlayerMP target = KServer.getPlayerByUsername(args[0]);

				if (target == null) {
					if (sender instanceof EntityPlayer) {
						KServer.sendMessage(sender, Fahrenheit.MOD_PREFIX + Brush.RED + "Указанный игрок не в онлайн.");
					} else {
						KLog.err("[Fahrenheit] Указанный игрок не в онлайн.");
					}
				}

				float value;

				try {
					value = Float.parseFloat(args[1]);
				} catch (NumberFormatException e) {
					e.printStackTrace();
					return;
				}

				FPlayerManager.getPlayer(target).temperature.set(value);
			} else {
				if (sender instanceof EntityPlayer) {
					KServer.sendMessage(sender, Fahrenheit.MOD_PREFIX + Brush.RED
							+ "Наверно указаны параметры. Используйте /setTemperature [player] [value]");
				} else {
					KLog.err("[Fahrenheit] Неверно указаны параметры.");
				}
			}
		}
	}

}
