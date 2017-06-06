package keelfy.darkage.network.client;

import keelfy.api.network.PacketDispatcher;
import keelfy.darkage.client.gui.GuiDAInventory;
import keelfy.darkage.entity.player.DAPlayer;
import keelfy.darkage.entity.player.effect.DAEffect;
import keelfy.darkage.handler.GuiHandler.GUI;
import keelfy.darkage.network.client.CustomClientMessage.PacketForClient;
import keelfy.darkage.network.server.CustomServerMessage;
import keelfy.darkage.network.server.CustomServerMessage.PacketForServer;
import keelfy.darkage.util.DAUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;

/**
 * @author keelfy
 * @created 5 июн. 2017 г.
 */
public class ClientPacketHandler {
	public static final void sendToServer(PacketForServer packet, Object...objects) {
		PacketDispatcher.getInstance().sendToServer(new CustomServerMessage(packet, objects));
	}
	
	public static final void openGUI(GUI gui) {
		sendToServer(PacketForServer.OPENGUI, (byte)gui.ordinal());
	}

	public static void handleOnClient(PacketForClient packetType, EntityPlayer player, Object[] args) {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			DAPlayer dap = DAPlayer.get(player);
			DAEffect dae = DAEffect.get(player);
			
			if(dap != null && dae != null) {
				switch(packetType) {
				case REPAIRREQUEST: handleRepairRequest(player, dap, args); break;
				case SYNCEFFETCS: handleSyncEffects(player, dae, args); break;
				case SYNCPLAYER: handleSyncPlayer(player, dap, args); break;
				case KILLPLAYER: handleKillPlayer(player, dap, args); break;
				}
			}
		}
	}

	private static void handleKillPlayer(EntityPlayer player, DAPlayer dap, Object[] args) {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			player.attackEntityFrom(DamageSource.outOfWorld, Integer.MAX_VALUE);
		}
	}

	private static void handleSyncPlayer(EntityPlayer player, DAPlayer dap, Object[] args) {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			NBTTagCompound data = (NBTTagCompound) args[0];
			
			if(dap != null) {
				dap.loadNBTData(data);
			}
		}
	}

	private static void handleSyncEffects(EntityPlayer player, DAEffect dae, Object[] args) {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			NBTTagCompound data = (NBTTagCompound) args[0];
			
			if(dae != null) {
				dae.loadNBTData(data);
			}
		}
	}

	private static void handleRepairRequest(EntityPlayer player, DAPlayer dap, Object[] args) {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			Minecraft mc = Minecraft.getMinecraft();
			
			if(mc.currentScreen instanceof GuiDAInventory) {
				GuiDAInventory gui = (GuiDAInventory)mc.currentScreen;
				gui.disableRepairing();
			}
		}
	}
}
