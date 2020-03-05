/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.client.gui;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkcore.network.DACNetwork;
import keelfy.darkdata.client.DAResources.Texture;
import keelfy.darkdata.constants.EnumPlayerClass;
import keelfy.darkdata.constants.EnumTexturePath;
import keelfy.darkdata.network.EnumSPackets;
import keelfyutils.KUtils;
import keelfyutils.client.KGL;
import keelfyutils.client.KGui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;

/**
 * @author keelfy
 */
@SideOnly(Side.CLIENT)
public final class DASelectClassGui extends DAScreenGui {

	private static final ResourceLocation backgroundTexture = Texture.get(EnumTexturePath.GUI, "sc/bg");
	private static final ResourceLocation witcherBtnTexture = Texture.get(EnumTexturePath.GUI, "sc/wa");
	private static final ResourceLocation witcherBtnIdleTexture = Texture.get(EnumTexturePath.GUI, "sc/wi");
	private static final ResourceLocation numanBtnTexture = Texture.get(EnumTexturePath.GUI, "sc/ha");
	private static final ResourceLocation humanBtnIdleTexture = Texture.get(EnumTexturePath.GUI, "sc/hi");

	private GuiButton humanButton, witcherButton;

	@Override
	protected boolean hasBlackBackground() {
		return true;
	}

	@Override
	protected ResourceLocation getBackgroundTexture() {
		return backgroundTexture;
	}

	@Override
	public final void drawForeground(final int mouseX, final int mouseY, final float partialTicks) {
		if (KUtils.PROTECT_CLIENT) {
			final int w = super.width;
			final int h = super.height;

			GL11.glPushMatrix();
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			if (mouseX > 0 && mouseX < w / 2) {
				if (mouseY > 0 && mouseY < h) {
					KGL.bindTexture(mc, numanBtnTexture);
				} else {
					KGL.bindTexture(mc, humanBtnIdleTexture);
				}
			} else {
				KGL.bindTexture(mc, humanBtnIdleTexture);
			}

			final Tessellator tess = Tessellator.instance;
			tess.startDrawingQuads();
			tess.addVertexWithUV(w / 2 - 210, h / 2 + 125, 0, 0, 1);
			tess.addVertexWithUV(w / 2 + 10, h / 2 + 125, 0, 1, 1);
			tess.addVertexWithUV(w / 2 + 10, h / 2 - 145, 0, 1, 0);
			tess.addVertexWithUV(w / 2 - 210, h / 2 - 145, 0, 0, 0);
			tess.draw();

			if (mouseX < w && mouseX > w / 2) {
				if (mouseY > 0 && mouseY < h) {
					KGL.bindTexture(mc, witcherBtnTexture);
				} else {
					KGL.bindTexture(mc, witcherBtnIdleTexture);
				}
			} else {
				KGL.bindTexture(mc, witcherBtnIdleTexture);
			}

			tess.startDrawingQuads();
			tess.addVertexWithUV(w / 2 - 10, h / 2 + 125, 0, 0, 1);
			tess.addVertexWithUV(w / 2 + 210, h / 2 + 125, 0, 1, 1);
			tess.addVertexWithUV(w / 2 + 210, h / 2 - 145, 0, 1, 0);
			tess.addVertexWithUV(w / 2 - 10, h / 2 - 145, 0, 0, 0);
			tess.draw();

			if (mouseX > 0 && mouseX < w / 2) {
				if (mouseY > 0 && mouseY < h) {

					final List list = new ArrayList();
					list.add("    для игры в компании   ");
					list.add("мирное или боевое развитие");
					list.add("     социальная игра      ");
					list.add("      свобода выбора      ");
					this.drawHoveringText(list, mouseX, mouseY, mc.fontRenderer);
					if (Mouse.getEventButton() == 0) {
						KGui.closeGui();
						DACNetwork.sendToServer(EnumSPackets.PlayerClass, EnumPlayerClass.HUMAN);
					}
				}
			}

			if (mouseX < w && mouseX > w / 2) {
				if (mouseY > 0 && mouseY < h) {

					final List list = new ArrayList();
					list.add("          для игры одному         ");
					list.add("           отличный боец          ");
					list.add("  мастерский охотник на монстров  ");
					list.add("использует знаки, эликсиры и масла");
					this.drawHoveringText(list, mouseX, mouseY, mc.fontRenderer);
					if (Mouse.getEventButton() == 0) {
						KGui.closeGui();
						DACNetwork.sendToServer(EnumSPackets.PlayerClass, EnumPlayerClass.WITCHER);
					}
				}
			}
			GL11.glDisable(GL11.GL_BLEND);
			;
			GL11.glPopMatrix();
			;

		}
	}
}
