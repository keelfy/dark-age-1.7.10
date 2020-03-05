/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.client;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkcore.common.player.DADataManager;
import keelfy.darkcore.common.player.DAPlayerData;
import keelfy.darkcore.network.DACNetwork;
import keelfy.darkdata.DarkData;
import keelfy.darkdata.constants.EnumGui;
import keelfy.darkdata.constants.EnumSign;
import keelfy.darkdata.handlers.DAGuiHandler;
import keelfy.darkdata.network.EnumSPackets;
import keelfy.darkdata.utils.DALocalization;
import keelfyutils.KUtils;
import keelfyutils.client.KClient;
import keelfyutils.client.KLocalization;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

/**
 * @author keelfy
 */
@SideOnly(Side.CLIENT)
public enum DAKeyHandler {
	Instance;

	private static final Minecraft MC = Minecraft.getMinecraft();

	private final String[] descriptions = { KLocalization.localize(DALocalization.Key_Hotslot_1), KLocalization.localize(DALocalization.Key_Hotslot_2), KLocalization.localize(DALocalization.Key_Hotslot_3),
			KLocalization.localize(DALocalization.Key_Hotslot_4), KLocalization.localize(DALocalization.Key_Use_Sign), KLocalization.localize(DALocalization.Key_Quest_List),
			KLocalization.localize(DALocalization.Key_Sword_Steel), KLocalization.localize(DALocalization.Key_Sword_Silver), KLocalization.localize(DALocalization.Key_Otherslot_1),
			KLocalization.localize(DALocalization.Key_Otherslot_2), KLocalization.localize(DALocalization.Key_Skills) };

	private final int[] values = { Keyboard.KEY_R, Keyboard.KEY_T, Keyboard.KEY_F, Keyboard.KEY_G, Keyboard.KEY_Q, Keyboard.KEY_L, Keyboard.KEY_1, Keyboard.KEY_2, Keyboard.KEY_3, Keyboard.KEY_4, Keyboard.KEY_K };

	public final KeyBinding[] keys = new KeyBinding[descriptions.length];

	public final void register() {
		if (KUtils.PROTECT_CLIENT) {
			FMLCommonHandler.instance().bus().register(this);

			for (int i = 0; i < descriptions.length; ++i) {
				keys[i] = new KeyBinding(descriptions[i], values[i], DarkData.MOD_NAME);
				ClientRegistry.registerKeyBinding(keys[i]);
			}

			KClient.removeVanillaKeybind(MC.gameSettings, MC.gameSettings.keyBindDrop);
			KClient.removeVanillaKeybind(MC.gameSettings, MC.gameSettings.keyBindChat);

			for (int i = 0; i < 9; i++) {
				KClient.removeVanillaKeybind(MC.gameSettings, MC.gameSettings.keyBindsHotbar[i]);
			}
		}
	}

	@SubscribeEvent
	public final void onKeyInput(final KeyInputEvent event) {
		if (KUtils.PROTECT_CLIENT) {

			if (MC.inGameHasFocus) {
				if (keys[5].isPressed()) {
					DAGuiHandler.openGui(EnumGui.Quests);
				}

				if (!MC.thePlayer.capabilities.isCreativeMode) {
					final GameSettings gs = MC.gameSettings;
					final DAPlayerData dap = DADataManager.getPlayer(MC.thePlayer);

					if (keys[0].isPressed()) {
						DACNetwork.sendToServer(EnumSPackets.HotSlot, 7);
					} else if (keys[1].isPressed()) {
						DACNetwork.sendToServer(EnumSPackets.HotSlot, 8);
					} else if (keys[2].isPressed()) {
						DACNetwork.sendToServer(EnumSPackets.HotSlot, 9);
					} else if (keys[3].isPressed()) {
						DACNetwork.sendToServer(EnumSPackets.HotSlot, 10);
					} else if (keys[4].isPressed()) {

						final Entity entity = KClient.getLookingEntity(MC.objectMouseOver);
						if (entity != null && entity instanceof EntityLivingBase) {
							DACNetwork.sendToServer(EnumSPackets.UseSign, entity.getEntityId(), dap.signs.getCurrent());
						} else if (dap.signs.getCurrentSign() == EnumSign.Irden || dap.signs.getCurrentSign() == EnumSign.Kven) {
							DACNetwork.sendToServer(EnumSPackets.UseSign, -1, dap.signs.getCurrent());
						}
					} else if (keys[6].isPressed()) {
						if (MC.thePlayer.inventory.currentItem == 1) {
							MC.thePlayer.inventory.currentItem = 0;
						} else {
							MC.thePlayer.inventory.currentItem = 1;
						}
					} else if (keys[7].isPressed()) {
						if (MC.thePlayer.inventory.currentItem == 2) {
							MC.thePlayer.inventory.currentItem = 0;
						} else {
							MC.thePlayer.inventory.currentItem = 2;
						}
					} else if (keys[8].isPressed()) {
						if (MC.thePlayer.inventory.currentItem == 6) {
							MC.thePlayer.inventory.currentItem = 0;
						} else {
							MC.thePlayer.inventory.currentItem = 6;
						}
					} else if (keys[9].isPressed()) {
						if (MC.thePlayer.inventory.currentItem == 3) {
							MC.thePlayer.inventory.currentItem = 0;
						} else {
							MC.thePlayer.inventory.currentItem = 3;
						}
					} else if (keys[10].isPressed()) {
						DAGuiHandler.openGui(EnumGui.Skills);
					} else if (gs.keyBindsHotbar[4].isPressed()) {
						KClient.unpressKey(gs.keyBindsHotbar[4]);
					} else if (gs.keyBindsHotbar[5].isPressed()) {
						KClient.unpressKey(gs.keyBindsHotbar[5]);
					} else if (gs.keyBindsHotbar[6].isPressed()) {
						KClient.unpressKey(gs.keyBindsHotbar[6]);
					} else if (gs.keyBindsHotbar[7].isPressed()) {
						KClient.unpressKey(gs.keyBindsHotbar[7]);
					} else if (gs.keyBindsHotbar[8].isPressed()) {
						KClient.unpressKey(gs.keyBindsHotbar[8]);
					} else if (gs.keyBindInventory.isPressed()) {
						KClient.unpressKey(gs.keyBindInventory);
						DAGuiHandler.openGui(EnumGui.Inventory);
					}

					if (gs.keyBindJump.isPressed()) {
						if (dap.energy.get() <= dap.energy.getMin()) {
							KClient.unpressKey(gs.keyBindJump);
						}
					} else if (gs.keyBindSprint.isPressed()) {
						if (dap.energy.get() <= dap.energy.getMin()) {
							KClient.unpressKey(gs.keyBindSprint);
						}
					}

				} else if (keys[6].isPressed()) {
					MC.thePlayer.inventory.currentItem = 0;
				} else if (keys[7].isPressed()) {
					MC.thePlayer.inventory.currentItem = 1;
				} else if (keys[8].isPressed()) {
					MC.thePlayer.inventory.currentItem = 2;
				} else if (keys[9].isPressed()) {
					MC.thePlayer.inventory.currentItem = 3;
				}
			}
		}
	}
}
