/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfytools;

import org.apache.commons.lang3.ArrayUtils;

import keelfytools.log.Brush;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;

/**
 * @author keelfy
 * @created 8 июн. 2017 г.
 */
public enum KeelfyUtilsServer {
	Instance;
	
	public static final String PREFIX = Brush.DARK_AQUA + "[KeelfyUtils] ";
	
	public static boolean isAdmin(ICommandSender sender) {
		return ArrayUtils.contains(MinecraftServer.getServer().getConfigurationManager().func_152606_n(), sender.getCommandSenderName());
	}

	public static EntityPlayerMP getPlayerExact(String username) {
		return username == null ? null : MinecraftServer.getServer().getConfigurationManager().func_152612_a(username);
	}

	public static EntityPlayerMP getPlayerBestMatch(String username) {
		String bestMatch = null;
		for(String name : MinecraftServer.getServer().getAllUsernames()) {
			if(name.equalsIgnoreCase(username)) {
				bestMatch = name;
				break;
			} else {
				if(name.toLowerCase().startsWith(username.toLowerCase())) {
					bestMatch = name;
				}
			}
		}
		return getPlayerExact(bestMatch);
	}

	public static void sendBroadcastMessage(String message) {
		for(String name : MinecraftServer.getServer().getAllUsernames()) {
			getPlayerExact(name).addChatMessage(new ChatComponentText(message));
		}
	}
	
	public static void sendMessage(EntityPlayer sender, String message) {
		sender.addChatMessage(new ChatComponentText(PREFIX + message));
	}
	
	public static void sendFineMessage(EntityPlayer sender, String message) {
		sendMessage(sender, Brush.GREEN + message);
	}
	
	public static void sendErrMessage(EntityPlayer sender, String message) {
		sendMessage(sender, Brush.RED + message);
	}
	
	public static void sendInfoMessage(EntityPlayer sender, String message) {
		sendMessage(sender, Brush.GRAY + message);
	}
}
