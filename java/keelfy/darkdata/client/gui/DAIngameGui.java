/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.client.gui;

import java.awt.Color;
import java.util.List;
import java.util.Map.Entry;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkcore.common.player.DADataManager;
import keelfy.darkcore.common.player.DAPlayerData;
import keelfy.darkcore.common.player.EffectsManager.PlayerEffect;
import keelfy.darkdata.DarkData;
import keelfy.darkdata.client.DAFontHandler;
import keelfy.darkdata.client.DAResources;
import keelfy.darkdata.client.DAResources.Texture;
import keelfy.darkdata.client.render.DARenderFont;
import keelfy.darkdata.constants.EnumEffect;
import keelfy.darkdata.constants.EnumFont;
import keelfy.darkdata.constants.EnumPlayerClass;
import keelfy.darkdata.constants.EnumTexturePath;
import keelfy.darkdata.items.Sword;
import keelfy.darkdata.utils.DALocalization;
import keelfyutils.KUtils;
import keelfyutils.client.KClient;
import keelfyutils.client.KColors;
import keelfyutils.client.KGL;
import keelfyutils.client.KGui;
import keelfyutils.client.KLocalization;
import keelfyutils.str.Brush;
import keelfyutils.str.KString;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.GuiPlayerInfo;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

/**
 * @author keelfy
 */
@SideOnly(Side.CLIENT)
public final class DAIngameGui extends GuiIngame {

	private static final ResourceLocation hudTexture = Texture.get(EnumTexturePath.GUI, "ingame");
	private static final RenderItem itemRender = new RenderItem();

	private static final float BAR_ANIMATION_FACTOR = 0.075F;
	private static final float BAR_ANIMATION_FRAME = BAR_ANIMATION_FACTOR * 60;

	private float smoothHealth;
	private float smoothIntox;
	private float smoothEnergy;

	public boolean loaded = false;

	private String desc_sword_silver, desc_sword_steel, gui_saturation;

	public DAIngameGui(final Minecraft mc) {
		super(mc);

		if (KUtils.PROTECT_CLIENT) {
			if (mc != null && mc.thePlayer != null) {
				final DAPlayerData dap = DADataManager.getPlayer(mc.thePlayer);

				smoothHealth = dap.health.get();
				smoothIntox = dap.intox.get();
				smoothEnergy = dap.energy.get();
			} else {
				smoothHealth = -1;
				smoothIntox = -1;
				smoothEnergy = -1;
			}

			desc_sword_silver = String.valueOf(KLocalization.localize(DALocalization.Desc_Sword_Silver));
			desc_sword_steel = String.valueOf(KLocalization.localize(DALocalization.Desc_Sword_Steel));
			gui_saturation = String.valueOf(KLocalization.localize(DALocalization.Gui_Saturation));
		}
	}

	private ScaledResolution sr;
	private int w, h;

	private final void updateResolution() {
		if (KUtils.PROTECT_CLIENT) {
			sr = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
			w = sr.getScaledWidth();
			h = sr.getScaledHeight();
		}
	}

	@Override
	public void renderGameOverlay(final float time, final boolean p_73830_2_, final int mouseX, final int mouseY) {
		updateResolution();
		DARenderFont font = DAFontHandler.Instance.getFont(EnumFont.Verdana_18);
		int fheight = font.getStringHeight();

		if (mc.thePlayer.capabilities.isCreativeMode) {
			super.renderGameOverlay(time, p_73830_2_, mouseX, mouseY);

			String s1 = String.valueOf(Brush.YELLOW + "ЗБТ Dark Age - " + DarkData.MOD_VERSION);
			font.drawStringWithShadow(s1, w - 10 - font.getStringWidth(s1), 2, KColors.HUD_COLOR);
			return;
		}

		FontRenderer fr = this.mc.fontRenderer;

		this.mc.entityRenderer.setupOverlayRendering();

		GL11.glColor4f(1, 1, 1, 1);

		final DAPlayerData dap = DADataManager.getPlayer(mc.thePlayer);

		if (smoothHealth == -1) {
			dap.health.get();
		}

		if (smoothIntox == -1) {
			dap.intox.get();
		}

		if (smoothEnergy == -1) {
			dap.energy.get();
		}

		String s;

		GL11.glEnable(GL11.GL_BLEND);
		if (Minecraft.isFancyGraphicsEnabled()) {
			this.renderVignette(this.mc.thePlayer.getBrightness(time), w, h);
		} else {
			OpenGlHelper.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
		}

		GL11.glPushMatrix();

		s = String.valueOf(Brush.YELLOW + "ЗБТ " + DarkData.MOD_NAME + " - " + DarkData.MOD_VERSION);
		font.drawStringWithShadow(s, w - font.getStringWidth(s) - 4, 2, KColors.HUD_COLOR);
		GL11.glPopMatrix();

		s = String.valueOf(Brush.YELLOW + "Команда \'" + Brush.RED + "Р" + Brush.WHITE + "УБЕДО" + Brush.YELLOW + "\'");
		font.drawStringWithShadow(s, w - font.getStringWidth(s) - 4, 5 + fheight, KColors.HUD_COLOR);

		if (mc.gameSettings.showDebugInfo) {
			s = String.valueOf(Brush.YELLOW + "Мод создали keelfy & RedSokol");
			font.drawStringWithShadow(s, w - font.getStringWidth(s) - 4, 15 + fheight, KColors.HUD_COLOR);
		}

		GL11.glPushMatrix();

		DARenderFont verdana15 = DAFontHandler.Instance.getFont(EnumFont.Verdana_15);
		s = String.valueOf(
				gui_saturation + KString.SPACE + (int) dap.saturation.get() + "/" + (int) dap.saturation.getMax());
		verdana15.drawStringWithShadow(s, w - verdana15.getStringWidth(s) - 4, h - 15 - verdana15.getStringHeight(),
				KColors.HUD_COLOR);

		GL11.glTranslatef(-20, -5, 0);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		KGL.bindTexture(hudTexture);
		final byte b = 4, c = 4;
		KGL.drawTexturedRectZ(b, c, 0, 0, 75, 50, 0);
		KGL.drawTexturedRectZ(b, c, 0, 50, 75, 50, -2);
		final int energy = Math.round((getEnergy(mc, dap, time) / dap.energy.getMax() * 38));
		KGL.drawTexturedRectZ(b - 2, 42 + c, 0, 120, 21 + energy, 20, 1);

		final int value = (int) (getHealth(mc, dap, time) / dap.health.getMax() * 141.0F);
		KGL.drawTexturedRectZ(b + 65, 19 - c, 110, 29, value, 20, -1);
		KGL.drawTexturedRectZ(b + 65, 16 - c, 110, 0, 141, 25, -2);

		if (dap.playerClass.equals(EnumPlayerClass.WITCHER)) {
			KGL.drawTexturedRectZ(b - 2, 44 + c, 0, 100, 100, 20, 0);
			final int intox = Math.round((int) getIntox(mc, dap, time));
			KGL.drawTexturedRectZ(b + 96, 47 - c, 163, 72, intox, 10, 1);
			KGL.drawTexturedRectZ(b + 80, 42 - c, 132, 62, 18, 20, 2);
			KGL.drawTexturedRectZ(b + 96, 47 - c, 163, 60, 100, 10, 0);
		}

		renderSign(dap);
		renderEffects(dap);
		GL11.glPopMatrix();

		renderCurrentSwordMaterial(fr);
		renderHotSlotItem(4.0F);
		GL11.glEnable(GL11.GL_ALPHA_TEST);

		if (!this.mc.thePlayer.isPotionActive(Potion.confusion)) {
			float f1 = this.mc.thePlayer.prevTimeInPortal
					+ (this.mc.thePlayer.timeInPortal - this.mc.thePlayer.prevTimeInPortal) * time;

			if (f1 > 0.0F) {
				this.func_130015_b(f1, w, h);
			}
		}

		int i1;
		int j1;
		int k1;
		int l4;

		if (this.mc.thePlayer.getSleepTimer() > 0) {
			this.mc.mcProfiler.startSection("sleep");
			GL11.glDisable(GL11.GL_DEPTH_TEST);
			GL11.glDisable(GL11.GL_ALPHA_TEST);
			l4 = this.mc.thePlayer.getSleepTimer();
			float f2 = l4 / 100.0F;

			if (f2 > 1.0F) {
				f2 = 1.0F - (l4 - 100) / 10.0F;
			}

			j1 = (int) (220.0F * f2) << 24 | 1052704;
			drawRect(0, 0, w, h, j1);
			GL11.glEnable(GL11.GL_ALPHA_TEST);
			GL11.glEnable(GL11.GL_DEPTH_TEST);
			this.mc.mcProfiler.endSection();
		}

		l4 = 16777215;
		i1 = w / 2 - 91;
		int l1;
		int i2;
		int j2;
		int k2;
		float f3;
		short short1;

		if (this.mc.thePlayer.isRidingHorse()) {
			this.mc.mcProfiler.startSection("jumpBar");
			KGL.bindTexture(mc, Gui.icons);
			f3 = this.mc.thePlayer.getHorseJumpPower();
			short1 = 182;
			l1 = (int) (f3 * (short1 + 1));
			i2 = h - 32 + 3;
			this.drawTexturedModalRect(i1, i2, 0, 84, short1, 5);

			if (l1 > 0) {
				this.drawTexturedModalRect(i1, i2, 0, 89, l1, 5);
			}

			this.mc.mcProfiler.endSection();
		}

		String s2;

		int i3;
		int j3;
		int k3;

		if (this.mc.gameSettings.showDebugInfo) {
			this.mc.mcProfiler.startSection("debug");
			GL11.glPushMatrix();
			GL11.glTranslatef(0, 0, 10);
			s = Brush.BOLD + "Дебаг: " + mc.debug;
			verdana15.drawString(s, 4, 65, KColors.HUD_COLOR);
			long i5 = Runtime.getRuntime().maxMemory();
			long j5 = Runtime.getRuntime().totalMemory();
			long k5 = Runtime.getRuntime().freeMemory();
			long l5 = j5 - k5;
			s = Brush.BOLD + "Память: " + l5 * 100L / i5 + "% (" + l5 / 1024L / 1024L + "МБ) из " + i5 / 1024L / 1024L
					+ "МБ";
			i3 = 14737632;
			verdana15.drawString(s, 4, 65 + verdana15.getStringHeight() + 2, KColors.HUD_COLOR);
			GL11.glPopMatrix();
			this.mc.mcProfiler.endSection();
		}

		if (this.recordPlayingUpFor > 0) {
			this.mc.mcProfiler.startSection("overlayMessage");
			f3 = this.recordPlayingUpFor - time;
			k1 = (int) (f3 * 255.0F / 20.0F);

			if (k1 > 255) {
				k1 = 255;
			}

			if (k1 > 8) {
				GL11.glPushMatrix();
				GL11.glTranslatef(w / 2, h - 68, 0.0F);
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
				l1 = 16777215;

				if (this.recordIsPlaying) {
					l1 = Color.HSBtoRGB(f3 / 50.0F, 0.7F, 0.6F) & 16777215;
				}

				fr.drawString(this.recordPlaying, -fr.getStringWidth(this.recordPlaying) / 2, -4,
						l1 + (k1 << 24 & -16777216));
				GL11.glDisable(GL11.GL_BLEND);
				GL11.glPopMatrix();
			}

			this.mc.mcProfiler.endSection();
		}

		ScoreObjective scoreobjective = this.mc.theWorld.getScoreboard().func_96539_a(1);

		if (scoreobjective != null) {
			this.func_96136_a(scoreobjective, h, w, fr);
		}

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		GL11.glPushMatrix();
		GL11.glTranslatef(0.0F, h - 48, 0.0F);
		this.mc.mcProfiler.startSection("chat");
		this.persistantChatGUI.drawChat(this.updateCounter);
		this.mc.mcProfiler.endSection();
		GL11.glPopMatrix();
		scoreobjective = this.mc.theWorld.getScoreboard().func_96539_a(0);

		if (this.mc.gameSettings.keyBindPlayerList.getIsKeyPressed()
				&& (!this.mc.isIntegratedServerRunning() || scoreobjective != null)) {
			this.mc.mcProfiler.startSection("playerList");
			NetHandlerPlayClient nethandlerplayclient = this.mc.thePlayer.sendQueue;
			List list = nethandlerplayclient.playerInfoList;
			i2 = nethandlerplayclient.currentServerMaxPlayers;
			j2 = i2;

			for (k2 = 1; j2 > 20; j2 = (i2 + k2 - 1) / k2) {
				++k2;
			}

			int i6 = 300 / k2;

			if (i6 > 150) {
				i6 = 150;
			}

			int l2 = (w - k2 * i6) / 2;
			byte b0 = 10;
			drawRect(l2 - 1, b0 - 1, l2 + i6 * k2, b0 + 9 * j2, Integer.MIN_VALUE);

			for (i3 = 0; i3 < i2; ++i3) {
				j3 = l2 + i3 % k2 * i6;
				k3 = b0 + i3 / k2 * 9;
				drawRect(j3, k3, j3 + i6 - 1, k3 + 8, 553648127);
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				GL11.glEnable(GL11.GL_ALPHA_TEST);

				if (i3 < list.size()) {
					GuiPlayerInfo guiplayerinfo = (GuiPlayerInfo) list.get(i3);
					ScorePlayerTeam scoreplayerteam = this.mc.theWorld.getScoreboard()
							.getPlayersTeam(guiplayerinfo.name);
					String s4 = ScorePlayerTeam.formatPlayerName(scoreplayerteam, guiplayerinfo.name);
					fr.drawStringWithShadow(s4, j3, k3, 16777215);

					if (scoreobjective != null) {
						int j4 = j3 + fr.getStringWidth(s4) + 5;
						int k4 = j3 + i6 - 12 - 5;

						if (k4 - j4 > 5) {
							Score score = scoreobjective.getScoreboard().func_96529_a(guiplayerinfo.name,
									scoreobjective);
							String s1 = EnumChatFormatting.YELLOW + "" + score.getScorePoints();
							fr.drawStringWithShadow(s1, k4 - fr.getStringWidth(s1), k3, 16777215);
						}
					}

					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					this.mc.getTextureManager().bindTexture(icons);
					byte b1 = 0;
					boolean flag3 = false;
					byte b2;

					if (guiplayerinfo.responseTime < 0) {
						b2 = 5;
					} else if (guiplayerinfo.responseTime < 150) {
						b2 = 0;
					} else if (guiplayerinfo.responseTime < 300) {
						b2 = 1;
					} else if (guiplayerinfo.responseTime < 600) {
						b2 = 2;
					} else if (guiplayerinfo.responseTime < 1000) {
						b2 = 3;
					} else {
						b2 = 4;
					}

					this.zLevel += 100.0F;
					this.drawTexturedModalRect(j3 + i6 - 12, k3, 0 + b1 * 10, 176 + b2 * 8, 10, 8);
					this.zLevel -= 100.0F;
				}
			}
		}

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		GL11.glDisable(GL11.GL_BLEND);

	}

	private final void renderEffects(final DAPlayerData dap) {

		DARenderFont font = DAFontHandler.Instance.getFont(EnumFont.Verdana_15);

		int posY = 100;
		String s = KString.EMPTY;

		for (final Entry<EnumEffect, PlayerEffect> pair : dap.effects.map.entrySet()) {
			final PlayerEffect effect = pair.getValue();

			if (!effect.isActive()) {
				continue;
			}

			final String duration = String.format("%.1f сек", ((float) effect.getDuration()) / 20);
			s = Brush.YELLOW + effect.getName().localized + KString.SPACE;

			switch (effect.getName()) {
			case Heal:
				s += String.format("%.1f", effect.getEfficiency()) + " ЕЗ/сек: " + duration;
				break;
			case Resistance:
				s += (int) (effect.getEfficiency() * 100) + "%: " + duration;
				break;
			case Strength:
				s += "+" + (int) ((effect.getEfficiency() - 1) * 100) + "%: " + duration;
				break;
			case NightVision:
				s += ": " + duration;
				break;
			case WaterBreathing:
				s += ": " + duration;
				break;
			case SecondBreath:
				s += "+" + (int) ((effect.getEfficiency() - 1) * 100) + "% ЕЭ/сек: " + duration;
				break;
			case MoveSpeed:
				s += ": " + duration;
				break;
			}

			font.drawStringWithShadow(s, 22, posY, KColors.HUD_COLOR);

			posY += (font.getStringHeight() + 2);
		}
	}

	private final void renderSign(final DAPlayerData dap) {
		final int sign = dap.signs.getCurrent();

		if (sign == 5)
			return;

		GL11.glPushMatrix();
		GL11.glScalef(0.4F, 0.4F, 0.4F);
		KGL.bindTexture(DAResources.Texture_Signs);
		KGL.texturedRect(154, 128, 5, 36 * sign, 47, 36, 36);
		GL11.glPopMatrix();
	}

	private final void renderCurrentSwordMaterial(final FontRenderer fr) {

		DARenderFont font = DAFontHandler.Instance.getFont(EnumFont.Verdana_15);

		final ItemStack current = mc.thePlayer.inventory.getCurrentItem();
		if (current != null && current.getItem() instanceof Sword) {
			switch (((Sword) current.getItem()).getType()) {
			case SILVER:
				font.drawStringWithShadow(desc_sword_silver, w - 4 - font.getStringWidth(desc_sword_silver), h - 11,
						KColors.HUD_COLOR);
				break;
			case STEEL:
				font.drawStringWithShadow(desc_sword_steel, w - 4 - font.getStringWidth(desc_sword_steel), h - 11,
						KColors.HUD_COLOR);
				break;
			}
		}
	}

	private final void renderHotSlotItem(final float partialTicks) {
		if (KUtils.PROTECT_CLIENT) {
			final byte a = 15;
			final int height = (int) (h * 1.4F);
			final IInventory inventory = mc.thePlayer.inventory;

			GL11.glPushMatrix();
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glScalef(0.7F, 0.7F, 1.0F);
			KGL.bindTexture(mc, hudTexture);
			if (inventory.getStackInSlot(8) != null) {
				KGui.drawGradientRect(-10 + a, height - 98, 117, 23, 40, 30);
			}

			if (inventory.getStackInSlot(9) != null) {
				KGui.drawGradientRect(-6 + a, height - 74, 117, 23, 40, 30);
			}

			if (inventory.getStackInSlot(10) != null) {
				KGui.drawGradientRect(-2 + a, height - 50, 117, 23, 40, 30);
			}

			if (inventory.getStackInSlot(11) != null) {
				KGui.drawGradientRect(2 + a, height - 26, 117, 23, 40, 30);
			}

			RenderHelper.enableGUIStandardItemLighting();
			renderInventorySlot(8, 4 + a, height - 92, partialTicks);
			renderInventorySlot(9, 8 + a, height - 67, partialTicks);
			renderInventorySlot(10, 12 + a, height - 45, partialTicks);
			renderInventorySlot(11, 16 + a, height - 20, partialTicks);
			RenderHelper.disableStandardItemLighting();
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glPopMatrix();
		}
	}

	private final float getHealth(final Minecraft mc, final DAPlayerData dap, final float time) {
		final float healthReal = dap.health.get();

		if ((healthReal <= 0)) {
			final float value = (float) (18 - mc.thePlayer.deathTime) / 18;

			if (value <= 0) {
				smoothHealth = -1;
			}
			return smoothHealth * value;
		} else if (Math.round(smoothHealth) != Math.round(healthReal)) {
			smoothHealth = smoothHealth
					+ (healthReal - smoothHealth) * (animationDelay(mc, time) * BAR_ANIMATION_FACTOR);
		} else {
			smoothHealth = healthReal;
		}

		return smoothHealth;
	}

	private final float getIntox(final Minecraft mc, final DAPlayerData dap, final float time) {
		final float intoxReal = dap.intox.get();

		if ((intoxReal <= 0))
			return smoothIntox = intoxReal;
		else if (Math.round(smoothIntox) != Math.round(intoxReal)) {
			smoothIntox += (intoxReal - smoothIntox) * (animationDelay(mc, time) * BAR_ANIMATION_FACTOR);
		} else {
			smoothIntox = intoxReal;
		}

		return smoothIntox;
	}

	private final float getEnergy(final Minecraft mc, final DAPlayerData dap, final float time) {
		final float energyReal = dap.energy.get();

		if ((energyReal <= 0))
			return smoothEnergy = energyReal;
		else if (Math.round(smoothEnergy) != Math.round(energyReal)) {
			smoothEnergy += (energyReal - smoothEnergy) * (animationDelay(mc, time) * BAR_ANIMATION_FACTOR);
		} else {
			smoothEnergy = energyReal;
		}

		return smoothEnergy;
	}

	private final float animationDelay(final Minecraft mc, final float time) {
		if (time >= 0F)
			return time;
		else
			return BAR_ANIMATION_FRAME / KClient.gameFPS(mc);
	}
}
