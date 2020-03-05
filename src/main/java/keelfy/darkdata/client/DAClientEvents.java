/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.client;

import java.util.Map.Entry;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkcore.common.player.DADataManager;
import keelfy.darkcore.common.player.DAPlayerData;
import keelfy.darkcore.common.player.managers.EffectsManager.PlayerEffect;
import keelfy.darkdata.client.gui.DAIngameGui;
import keelfy.darkdata.client.renderer.DARendererSword;
import keelfy.darkdata.constants.EnumEffect;
import keelfy.darkdata.constants.EnumGui;
import keelfy.darkdata.handlers.DAGuiHandler;
import keelfy.darkdata.items.Sword;
import keelfyutils.KUtils;
import keelfyutils.client.KClient;
import keelfyutils.client.KGL;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.MinecraftForge;

/**
 * @author keelfy
 */
@SideOnly(Side.CLIENT)
public enum DAClientEvents {
	Instance;

	private final Minecraft MC = KGL.mc();

	public final void register() {
		if (KUtils.PROTECT_CLIENT) {
			MinecraftForge.EVENT_BUS.register(this);
			FMLCommonHandler.instance().bus().register(this);
		}
	}

	@SubscribeEvent
	public final void onClientTick(final TickEvent.ClientTickEvent event) {
		if (KUtils.PROTECT_CLIENT) {
			if (!KUtils.DEBUG) {
				if (KUtils.isClient()) {
					if (FMLClientHandler.instance().getClient().isSingleplayer()) {
						FMLCommonHandler.instance().exitJava(0, true);
					}
				}
			}

			if (event.phase != Phase.END)
				return;

			if (MC.thePlayer != null) {
				final DAPlayerData dap = DADataManager.getPlayer(MC.thePlayer);
				final ItemStack stack = MC.thePlayer.getCurrentEquippedItem();

				if (stack != null && stack.getItem() instanceof Sword) {
					final DARendererSword renderer = ((Sword) stack.getItem()).getRenderer();

					if (renderer != null) {
						renderer.moveObjects();
					}

					if (MC.gameSettings.keyBindAttack.isPressed()) {
						Sword sword = (Sword) stack.getItem();

						if (sword.getItemUse(stack) == 0) {
							stack.getTagCompound().setInteger("Using", 20);
						} else {
							KClient.unpressKey(MC.gameSettings.keyBindAttack);
						}
					}
				}

				for (Entry<EnumEffect, PlayerEffect> pair : dap.effects.map.entrySet()) {
					pair.getValue().updateDurationClient();
				}

				float gammaSetting = 0;
				boolean status = false;
				if (MC.thePlayer.isPotionActive(Potion.nightVision) && !status) {
					status = true;
					gammaSetting = MC.gameSettings.gammaSetting;
					MC.gameSettings.gammaSetting = 1500F;
				} else if (!MC.thePlayer.isPotionActive(Potion.nightVision) && status) {
					status = false;
					MC.gameSettings.gammaSetting = gammaSetting;
				}
			}
		}
	}

	@SubscribeEvent
	public final void renderWorldPost(final RenderWorldLastEvent event) {
		if (MC == null || MC.thePlayer == null)
			return;

		if (MC.ingameGUI != null && !(MC.ingameGUI instanceof DAIngameGui)) {
			MC.ingameGUI = new DAIngameGui(MC);
		}
	}

	@SubscribeEvent
	public final void onMouseEvent(final MouseEvent event) {
		if (KUtils.PROTECT_CLIENT) {
			if (event.dwheel != 0 && !MC.thePlayer.capabilities.isCreativeMode) {
				event.setCanceled(true);

				final DAPlayerData dap = DADataManager.getPlayer(MC.thePlayer);

				if (event.dwheel > 0) {
					dap.signs.next();
				} else {
					dap.signs.previous();
				}
			}
		}
	}

	@SubscribeEvent
	public final void openGui(final GuiOpenEvent event) {
		if (KUtils.PROTECT_CLIENT) {
			if (event.gui instanceof GuiInventory && !MC.thePlayer.capabilities.isCreativeMode) {
				event.setCanceled(true);

				DAGuiHandler.openGui(EnumGui.Inventory);
			}
		}
	}
}
