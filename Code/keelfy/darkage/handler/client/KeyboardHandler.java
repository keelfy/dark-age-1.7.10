package keelfy.darkage.handler.client;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import keelfy.api.network.PacketDispatcher;
import keelfy.darkage.entity.player.DAPlayer;
import keelfy.darkage.entity.player.DAPlayerUtil.Property;
import keelfy.darkage.network.server.HotSlotMessage;
import keelfy.darkage.network.server.PlayerJumpMessage;
import keelfy.darkage.network.server.UseSignMessage;
import keelfy.darkage.util.DAUtil;
import keelfy.darkage.util.LanguageUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
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
				if(keys[0].isPressed()) {
					PacketDispatcher.getInstance().sendToServer(new HotSlotMessage(8));
				} else if(keys[1].isPressed()) {
					PacketDispatcher.getInstance().sendToServer(new HotSlotMessage(9));
				} else if(keys[2].isPressed()) {
					PacketDispatcher.getInstance().sendToServer(new HotSlotMessage(10));
				} else if(keys[3].isPressed()) {
					PacketDispatcher.getInstance().sendToServer(new HotSlotMessage(11));
				} else if(keys[4].isPressed()) {
					PacketDispatcher.getInstance().sendToServer(new UseSignMessage());
				} else if(keys[5].isPressed()) {
					mc.displayGuiScreen(new GuiQuestLog(mc.thePlayer));
				}
				
				if(mc.gameSettings.keyBindJump.isPressed()) {
					if(DAPlayer.get(mc.thePlayer).get(Property.ENERGY) <= 10F) {
						mc.gameSettings.keyBindJump.setKeyBindState(mc.gameSettings.keyBindJump.getKeyCode(), false);
					} else PacketDispatcher.getInstance().sendToServer(new PlayerJumpMessage());
				} else if(mc.gameSettings.keyBindSprint.isPressed()) {
					if(DAPlayer.get(mc.thePlayer) != null && DAPlayer.get(mc.thePlayer).get(Property.ENERGY) <= 10) {
						mc.gameSettings.keyBindSprint.setKeyBindState(mc.gameSettings.keyBindSprint.getKeyCode(), false);
					}
				} else if(mc.gameSettings.keyBindsHotbar[0].isPressed()) {
					mc.gameSettings.keyBindsHotbar[0].setKeyBindState(mc.gameSettings.keyBindsHotbar[0].getKeyCode(), false);
					mc.thePlayer.inventory.currentItem = 1;
				} else if(mc.gameSettings.keyBindsHotbar[1].isPressed()) {
					mc.gameSettings.keyBindsHotbar[1].setKeyBindState(mc.gameSettings.keyBindsHotbar[1].getKeyCode(), false);
					mc.thePlayer.inventory.currentItem = 2;
				}else if(mc.gameSettings.keyBindsHotbar[2].isPressed()) {
					mc.gameSettings.keyBindsHotbar[2].setKeyBindState(mc.gameSettings.keyBindsHotbar[2].getKeyCode(), false);
				} else if(mc.gameSettings.keyBindsHotbar[3].isPressed()) {
					mc.gameSettings.keyBindsHotbar[3].setKeyBindState(mc.gameSettings.keyBindsHotbar[3].getKeyCode(), false);
				} else if(mc.gameSettings.keyBindsHotbar[4].isPressed()) {
					mc.gameSettings.keyBindsHotbar[4].setKeyBindState(mc.gameSettings.keyBindsHotbar[4].getKeyCode(), false);
				} else if(mc.gameSettings.keyBindsHotbar[5].isPressed()) {
					mc.gameSettings.keyBindsHotbar[5].setKeyBindState(mc.gameSettings.keyBindsHotbar[5].getKeyCode(), false);
				} else if(mc.gameSettings.keyBindsHotbar[6].isPressed()) {
					mc.gameSettings.keyBindsHotbar[6].setKeyBindState(mc.gameSettings.keyBindsHotbar[6].getKeyCode(), false);
				} else if(mc.gameSettings.keyBindsHotbar[7].isPressed()) {
					mc.gameSettings.keyBindsHotbar[7].setKeyBindState(mc.gameSettings.keyBindsHotbar[7].getKeyCode(), false);
				} else if(mc.gameSettings.keyBindsHotbar[8].isPressed()) {
					mc.gameSettings.keyBindsHotbar[8].setKeyBindState(mc.gameSettings.keyBindsHotbar[8].getKeyCode(), false);
				} else if(mc.gameSettings.keyBindDrop.isPressed()) {
					mc.gameSettings.keyBindDrop.setKeyBindState(mc.gameSettings.keyBindDrop.getKeyCode(), false);
				}
			}
		}
	}
}
