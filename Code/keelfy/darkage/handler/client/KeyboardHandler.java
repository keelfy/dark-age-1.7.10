package keelfy.darkage.handler.client;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import keelfy.darkage.entity.player.DAPlayer;
import keelfy.darkage.entity.player.DAPlayerUtil.Property;
import keelfy.darkage.entity.sign.ISign;
import keelfy.darkage.network.client.ClientPacketHandler;
import keelfy.darkage.network.server.CustomServerMessage.PacketForServer;
import keelfy.darkage.util.DAUtil;
import keelfy.darkage.util.LanguageUtil;
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
			StatCollector.translateToLocal(LanguageUtil.key_hotslot_1), 
			StatCollector.translateToLocal(LanguageUtil.key_hotslot_2), 
			StatCollector.translateToLocal(LanguageUtil.key_hotslot_3), 
			StatCollector.translateToLocal(LanguageUtil.key_hotslot_4), 
			StatCollector.translateToLocal(LanguageUtil.key_usesign), 
			StatCollector.translateToLocal(LanguageUtil.key_questlist)
			};
	private static final int[] keyValues = {
			Keyboard.KEY_R, 
			Keyboard.KEY_T, 
			Keyboard.KEY_F, 
			Keyboard.KEY_G, 
			Keyboard.KEY_Q, 
			Keyboard.KEY_L
			};
	public static final KeyBinding[] keys = new KeyBinding[desc.length];

	public KeyboardHandler() {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			FMLCommonHandler.instance().bus().register(this);	
			
			for (int i = 0; i < desc.length; ++i) {
				keys[i] = new KeyBinding(desc[i], keyValues[i], StatCollector.translateToLocal(DAUtil.MODNAME));
				ClientRegistry.registerKeyBinding(keys[i]);
			}
		}
	}

	@SubscribeEvent
	public void onKeyInput(KeyInputEvent event) {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			if(mc.inGameHasFocus && !mc.thePlayer.capabilities.isCreativeMode) {
				GameSettings gs = mc.gameSettings;

				if(keys[0].isPressed()) {
					ClientPacketHandler.sendToServer(PacketForServer.HOTSLOT, 8);
				} else if(keys[1].isPressed()) {
					ClientPacketHandler.sendToServer(PacketForServer.HOTSLOT, 9);
				} else if(keys[2].isPressed()) {
					ClientPacketHandler.sendToServer(PacketForServer.HOTSLOT, 10);
				} else if(keys[3].isPressed()) {
					ClientPacketHandler.sendToServer(PacketForServer.HOTSLOT, 11);
				} else if(keys[4].isPressed()) {
					EntityLiving e = ISign.getLookingEntity();
					int id = -1;
					if(e != null) id = e.getEntityId();
					ClientPacketHandler.sendToServer(PacketForServer.USESIGN, id);
				} else if(keys[5].isPressed()) {
					mc.displayGuiScreen(new GuiQuestLog(mc.thePlayer));
				}
				
				if(gs.keyBindJump.isPressed()) {
					if(DAPlayer.get(mc.thePlayer).get(Property.ENERGY) <= 10F) {
						unpress(gs.keyBindJump);
					} else ClientPacketHandler.sendToServer(PacketForServer.PLAYERJUMP);
				} else if(gs.keyBindSprint.isPressed()) {
					if(DAPlayer.get(mc.thePlayer) != null && DAPlayer.get(mc.thePlayer).get(Property.ENERGY) <= 10) {
						unpress(gs.keyBindSprint);
					}
				} else if(gs.keyBindsHotbar[0].isPressed()) {
					unpress(gs.keyBindsHotbar[0]);
					mc.thePlayer.inventory.currentItem = 1;
				} else if(gs.keyBindsHotbar[1].isPressed()) {
					unpress(gs.keyBindsHotbar[1]);
					mc.thePlayer.inventory.currentItem = 2;
				}else if(gs.keyBindsHotbar[2].isPressed()) {
					unpress(gs.keyBindsHotbar[2]);
				} else if(gs.keyBindsHotbar[3].isPressed()) {
					unpress(gs.keyBindsHotbar[3]);
				} else if(gs.keyBindsHotbar[4].isPressed()) {
					unpress(gs.keyBindsHotbar[4]);
				} else if(gs.keyBindsHotbar[5].isPressed()) {
					unpress(gs.keyBindsHotbar[5]);
				} else if(gs.keyBindsHotbar[6].isPressed()) {
					unpress(gs.keyBindsHotbar[6]);
				} else if(gs.keyBindsHotbar[7].isPressed()) {
					unpress(gs.keyBindsHotbar[7]);
				} else if(gs.keyBindsHotbar[8].isPressed()) {
					unpress(gs.keyBindsHotbar[8]);
				} else if(gs.keyBindDrop.isPressed()) {
					unpress(gs.keyBindDrop);
				} else if(gs.keyBindAttack.isPressed()) {
					if(mc.thePlayer.getHeldItem() == null) {
						unpress(gs.keyBindAttack);
					}
				}
			}
		}
	}
	
	private final void unpress(KeyBinding key) {
		KeyBinding.setKeyBindState(key.getKeyCode(), false);
	}
}
