package keelfyutils.server;

import org.apache.commons.lang3.ArrayUtils;

import keelfyutils.KUtils;
import keelfyutils.str.Brush;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;

/**
 * @author keelfy
 * @created 8 июн. 2017 г.
 */
public final class KServer {

	public static final boolean isAdmin(final ICommandSender sender) {
		if (KUtils.PROTECT_SERVER)
			return ArrayUtils.contains(MinecraftServer.getServer().getConfigurationManager().func_152606_n(),
					sender.getCommandSenderName());
		return false;
	}

	public static final void teleport(final EntityPlayerMP player, final EntityPlayerMP target) {
		if (KUtils.PROTECT_SERVER) {
			player.mountEntity((Entity) null);
			player.playerNetServerHandler.setPlayerLocation(target.posX, target.posY, target.posZ, target.rotationYaw,
					target.rotationPitch);
		}
	}

	public static final void teleport(final EntityPlayerMP player, final double x, final double y, final double z,
			final float yaw, final float pitch) {
		if (KUtils.PROTECT_SERVER) {
			player.mountEntity((Entity) null);
			player.playerNetServerHandler.setPlayerLocation(x, y, z, yaw, pitch);
		}
	}

	public static final void teleport(final EntityPlayerMP player, final double x, final double y, final double z,
			final float yaw, final float pitch, final int dim) {
		if (KUtils.PROTECT_SERVER) {
			player.mountEntity((Entity) null);
			player.travelToDimension(dim);
			player.playerNetServerHandler.setPlayerLocation(x, y, z, yaw, pitch);
		}
	}

	public static final EntityPlayerMP getPlayerByUsername(final String username) {
		if (KUtils.PROTECT_SERVER)
			return username == null ? null
					: MinecraftServer.getServer().getConfigurationManager().func_152612_a(username);
		return null;
	}

	public static final EntityPlayerMP getPlayerBestMatch(final String username) {
		if (KUtils.PROTECT_SERVER) {
			String bestMatch = null;
			for (final String name : MinecraftServer.getServer().getAllUsernames()) {
				if (name.equalsIgnoreCase(username)) {
					bestMatch = name;
					break;
				} else {
					if (name.toLowerCase().startsWith(username.toLowerCase())) {
						bestMatch = name;
					}
				}
			}
			return getPlayerByUsername(bestMatch);
		}
		return null;
	}

	public static final void sendBroadcastMessage(final String message) {
		if (KUtils.PROTECT_SERVER) {
			for (final String name : MinecraftServer.getServer().getAllUsernames()) {
				getPlayerByUsername(name).addChatMessage(new ChatComponentText(message));
			}
		}
	}

	public static final void sendMessage(final ICommandSender sender, final String message) {
		if (KUtils.PROTECT_SERVER) {
			sender.addChatMessage(new ChatComponentText(message));
		}
	}

	public static final void sendFineMessage(final ICommandSender sender, final String message) {
		if (KUtils.PROTECT_SERVER) {
			sendMessage(sender, Brush.GREEN + message);
		}
	}

	public static final void sendErrMessage(final ICommandSender sender, final String message) {
		if (KUtils.PROTECT_SERVER) {
			sendMessage(sender, Brush.RED + message);
		}
	}

	public static final void sendInfoMessage(final ICommandSender sender, final String message) {
		if (KUtils.PROTECT_SERVER) {
			sendMessage(sender, Brush.GRAY + message);
		}
	}
}
