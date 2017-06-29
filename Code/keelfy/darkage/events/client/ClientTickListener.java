/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.events.client;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkage.entities.player.effect.DAEffect;
import keelfy.darkage.items.Sword;
import keelfytools.KeelfyUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;

/**
 * @author keelfy
 */
@SideOnly(Side.CLIENT)
public class ClientTickListener {

	private final Minecraft mc = Minecraft.getMinecraft();

	public ClientTickListener() {
		if (KeelfyUtils.isClientSide())
			FMLCommonHandler.instance().bus().register(this);
	}

	@SubscribeEvent
	public void onClientTick(TickEvent.ClientTickEvent event) {
		if (KeelfyUtils.isClientSide()) {
			if (mc.thePlayer != null) {
				if (mc.thePlayer.getCurrentEquippedItem() != null
						&& mc.thePlayer.getCurrentEquippedItem().getItem() instanceof Sword) {
					Sword sword = (Sword) mc.thePlayer.getCurrentEquippedItem().getItem();

					if (sword.renderer != null) {
						sword.renderer.moveObjects();
					}
				}

				float gammaSetting = 0;
				boolean status = false;
				if (mc.thePlayer.isPotionActive(Potion.nightVision) && !status) {
					status = true;
					gammaSetting = mc.gameSettings.gammaSetting;
					mc.gameSettings.gammaSetting = 1500F;
				} else if (!mc.thePlayer.isPotionActive(Potion.nightVision) && status) {
					status = false;
					mc.gameSettings.gammaSetting = gammaSetting;
				}
			}
		}
	}

	@SubscribeEvent
	public void onRenderTick(TickEvent.RenderTickEvent event) {
		if (KeelfyUtils.isClientSide()) {
			if (mc.thePlayer != null) {
				if (mc.inGameHasFocus && DAEffect.get(mc.thePlayer) != null)
					DAEffect.get(mc.thePlayer).render();

			}
		}
	}
}
