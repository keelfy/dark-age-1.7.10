/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.network;

import keelfy.darkage.client.gui.DAGuiInventory;
import keelfy.darkage.constants.EnumClientPacket;
import keelfy.darkage.constants.EnumGui;
import keelfy.darkage.constants.EnumRarity;
import keelfy.darkage.constants.EnumServerPacket;
import keelfy.darkage.entities.player.DAPlayer;
import keelfy.darkage.entities.player.effect.DAEffect;
import keelfy.darkage.items.DAItem;
import keelfytools.KeelfyUtils;
import keelfytools.network.PacketDispatcher;
import keelfytools.network.server.CustomServerMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * @author keelfy
 * @created 5 июн. 2017 г.
 */
public class ClientPacketHandler {
	public static final void sendToServer(EnumServerPacket packet, Object...objects) {
		PacketDispatcher.getInstance().sendToServer(new CustomServerMessage(packet, objects));
	}
	
	public static final void openGUI(EnumGui gui) {
		sendToServer(EnumServerPacket.OPENGUI, (byte)gui.ordinal());
	}

	public static void handleOnClient(EnumClientPacket packetType, EntityPlayer player, Object[] args) {
		if(KeelfyUtils.isClientSide()) {
			DAPlayer dap = DAPlayer.get(player);
			DAEffect dae = DAEffect.get(player);
			
			if(dap != null && dae != null) {
				switch(packetType) {
				case REPAIRREQUEST: handleRepairRequest(player, dap, args); break;
				case SYNCEFFETCS: handleSyncEffects(player, dae, args); break;
				case SYNCPLAYER: handleSyncPlayer(player, dap, args); break;
				case ITEMRARITY: handleItemRarity(player, dap, args); break;
				}
			}
		}
	}

	private static void handleItemRarity(EntityPlayer player, DAPlayer dap, Object[] args) {
		
		EnumRarity rarity = EnumRarity.values() [ (Integer)args[0] ]; 
		
		if(player.getCurrentEquippedItem() != null) {
			ItemStack stack = player.getCurrentEquippedItem();
			if(stack.getItem() instanceof DAItem) {
				((DAItem)stack.getItem()).setRarity(rarity);
			}
		}
	}

	private static void handleSyncPlayer(EntityPlayer player, DAPlayer dap, Object[] args) {
		if(KeelfyUtils.isClientSide()) {
			NBTTagCompound data = (NBTTagCompound) args[0];
			
			if(dap != null) {
				dap.loadNBTData(data);
			}
		}
	}

	private static void handleSyncEffects(EntityPlayer player, DAEffect dae, Object[] args) {
		if(KeelfyUtils.isClientSide()) {
			NBTTagCompound data = (NBTTagCompound) args[0];
			
			if(dae != null) {
				dae.loadNBTData(data);
			}
		}
	}

	private static void handleRepairRequest(EntityPlayer player, DAPlayer dap, Object[] args) {
		if(KeelfyUtils.isClientSide()) {
			Minecraft mc = Minecraft.getMinecraft();
			
			if(mc.currentScreen instanceof DAGuiInventory) {
				DAGuiInventory gui = (DAGuiInventory)mc.currentScreen;
				gui.disableRepairing();
			}
		}
	}
}
