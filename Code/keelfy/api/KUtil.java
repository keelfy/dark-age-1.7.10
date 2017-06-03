package keelfy.api;

import java.time.Duration;
import java.time.temporal.Temporal;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.api.java.ArrayHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;

/**
 * @author keelfy
 */
public enum KUtil {
	
	INSTANCE;
	
	public static boolean isClient() {
		return FMLCommonHandler.instance().getSide().isClient();
	}
	
	public static boolean isSever() {
		return FMLCommonHandler.instance().getSide().isServer();
	}
	
	@SideOnly(Side.SERVER)
	public static boolean isAdmin(ICommandSender sender) {
		return ArrayHelper.arrayContains(MinecraftServer.getServer().getConfigurationManager().func_152606_n(), sender.getCommandSenderName());
	}

	@SideOnly(Side.SERVER)
	public static EntityPlayerMP getPlayerExact(String username) {
		return username == null ? null : MinecraftServer.getServer().getConfigurationManager().func_152612_a(username);
	}

	@SideOnly(Side.SERVER)
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

	@SideOnly(Side.SERVER)
	public static void broadcastMessage(String message) {
		for(String name : MinecraftServer.getServer().getAllUsernames()) {
			getPlayerExact(name).addChatMessage(new ChatComponentText(message));
		}
	}
	
	public static void send(EntityPlayer sender, String message) {
		sender.addChatMessage(new ChatComponentText(message));
	}
	
	public static long getTimeBetween(Temporal start, Temporal end) {
		return Duration.between(start, end).toMillis();
	}
	
	@SideOnly(Side.CLIENT)
	public static final void print(String str) {
		Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(str));
	}
}
