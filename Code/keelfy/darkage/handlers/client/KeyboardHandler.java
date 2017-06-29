/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.handlers.client;

import org.apache.commons.lang3.ArrayUtils;
import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import keelfy.darkage.constants.EnumProperty;
import keelfy.darkage.constants.EnumServerPacket;
import keelfy.darkage.entities.player.DAPlayer;
import keelfy.darkage.network.ClientPacketHandler;
import keelfy.darkage.utils.DAUtils;
import keelfytools.KeelfyUtils;
import keelfytools.KeelfyUtilsClient;
import keelfytools.LocalizationUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.StatCollector;
import noppes.npcs.client.gui.player.GuiQuestLog;

/**
 * @author keelfy
 */
public class KeyboardHandler {
	private final Minecraft mc = Minecraft.getMinecraft();
	
	private static final String[] desc = {
			StatCollector.translateToLocal(LocalizationUtils.key_hotslot_1), 
			StatCollector.translateToLocal(LocalizationUtils.key_hotslot_2), 
			StatCollector.translateToLocal(LocalizationUtils.key_hotslot_3), 
			StatCollector.translateToLocal(LocalizationUtils.key_hotslot_4), 
			StatCollector.translateToLocal(LocalizationUtils.key_usesign), 
			StatCollector.translateToLocal(LocalizationUtils.key_questlist),
			StatCollector.translateToLocal(LocalizationUtils.key_sword_steel),
			StatCollector.translateToLocal(LocalizationUtils.key_sword_silver)
			};
	private static final int[] keyValues = {
			Keyboard.KEY_R, 
			Keyboard.KEY_T, 
			Keyboard.KEY_F, 
			Keyboard.KEY_G, 
			Keyboard.KEY_Q, 
			Keyboard.KEY_L,
			Keyboard.KEY_1,
			Keyboard.KEY_2
			};
	public static final KeyBinding[] keys = new KeyBinding[desc.length];

	public KeyboardHandler() {
		if(KeelfyUtils.isClientSide()) {
			FMLCommonHandler.instance().bus().register(this);	
			
			for (int i = 0; i < desc.length; ++i) {
				keys[i] = new KeyBinding(desc[i], keyValues[i], DAUtils.MODNAME);
				ClientRegistry.registerKeyBinding(keys[i]);
			}
			
			removeVanillaKeybinds();
		}
	}

	private final void removeVanillaKeybinds() {
		Minecraft mc = Minecraft.getMinecraft();
		
		mc.gameSettings.keyBindings = ArrayUtils.removeElement(mc.gameSettings.keyBindings, mc.gameSettings.keyBindDrop);
		mc.gameSettings.keyBindings = ArrayUtils.removeElement(mc.gameSettings.keyBindings, mc.gameSettings.keyBindChat);
		
		for (int i = 0; i < 9; i++) {
			mc.gameSettings.keyBindings = ArrayUtils.removeElement(mc.gameSettings.keyBindings, mc.gameSettings.keyBindsHotbar[i]);
		}
	}

	@SubscribeEvent
	public void onKeyInput(KeyInputEvent event) {
		if(KeelfyUtils.isClientSide()) {
			
			
			if(mc.inGameHasFocus) {
				if(keys[5].isPressed()) {
					mc.displayGuiScreen(new GuiQuestLog(mc.thePlayer));
				}
				
				if(!mc.thePlayer.capabilities.isCreativeMode) {
					GameSettings gs = mc.gameSettings;
	
					if(keys[0].isPressed()) {
						ClientPacketHandler.sendToServer(EnumServerPacket.HOTSLOT, 8);
					} else if(keys[1].isPressed()) {
						ClientPacketHandler.sendToServer(EnumServerPacket.HOTSLOT, 9);
					} else if(keys[2].isPressed()) {
						ClientPacketHandler.sendToServer(EnumServerPacket.HOTSLOT, 10);
					} else if(keys[3].isPressed()) {
						ClientPacketHandler.sendToServer(EnumServerPacket.HOTSLOT, 11);
					} else if(keys[4].isPressed()) {
						EntityLiving e = KeelfyUtilsClient.getLookingEntity();
						int id = -1;
						if(e != null) id = e.getEntityId();
						ClientPacketHandler.sendToServer(EnumServerPacket.USESIGN, id);
					} else if(keys[6].isPressed()) {
						mc.thePlayer.inventory.currentItem = 1;
					} else if(keys[7].isPressed()) {
						mc.thePlayer.inventory.currentItem = 2;
					}
					
					if(gs.keyBindJump.isPressed()) {
						if(DAPlayer.get(mc.thePlayer).get(EnumProperty.ENERGY) <= 10F) {
							unpress(gs.keyBindJump);
						} else ClientPacketHandler.sendToServer(EnumServerPacket.PLAYERJUMP);
					} else if(gs.keyBindSprint.isPressed()) {
						if(DAPlayer.get(mc.thePlayer) != null && DAPlayer.get(mc.thePlayer).get(EnumProperty.ENERGY) <= 10) {
							unpress(gs.keyBindSprint);
						}
					}
				}
			}
		}
	}
	
	private final void unpress(KeyBinding key) {
		KeyBinding.setKeyBindState(key.getKeyCode(), false);
	}
}
