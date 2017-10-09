/*
 *  Copyright (c) 2016-2017, Rubedo
 */

package keelfy.fahrenheit.client;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.fahrenheit.common.player.FPlayerData;
import keelfy.fahrenheit.common.player.managers.FPlayerManager;
import keelfyutils.KUtils;
import keelfyutils.client.KColors;
import keelfyutils.client.KGL;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.common.MinecraftForge;

/**
 * @author keelfy
 * @created 18 сент. 2017 г.
 */
@SideOnly(Side.CLIENT)
public enum FClientEvents {
	INSTANCE;

	private static final Minecraft mc = KGL.mc();

	public void register() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void overlay(RenderGameOverlayEvent event) {
		if (KUtils.PROTECT_CLIENT) {

			if (event.type != ElementType.TEXT || mc.thePlayer == null) {
				return;
			}

			final int w = event.resolution.getScaledWidth();
			final int h = event.resolution.getScaledHeight();
			final FPlayerData fpd = FPlayerManager.getPlayer(mc.thePlayer);
			String s = "";

			s = "Temperature: " + formatFloat(2, fpd.temperature.get()) + "°C";
			KGL.string(s, 4, h / 2 - KGL.stringHeight() / 2, KColors.HUD_COLOR);

			s = "Thrist: " + formatFloat(2, fpd.thrist.get()) + "/" + (int) fpd.thrist.MAX;
			KGL.string(s, 4, h / 2 - KGL.stringHeight() / 2 + 10, KColors.HUD_COLOR);

			s = "Bleeding: " + formatFloat(2, fpd.bleeding.get()) + "/" + (int) fpd.bleeding.MAX;
			KGL.string(s, 4, h / 2 - KGL.stringHeight() / 2 + 20, KColors.HUD_COLOR);

		}
	}

	private final String formatFloat(int number, float value) {
		String n = "0.";

		for (int i = 0; i < number; i++) {
			n += "0";
		}

		NumberFormat numberFormat = new DecimalFormat("0.00");
		numberFormat.setRoundingMode(RoundingMode.DOWN);
		return numberFormat.format(value);
	}

}
