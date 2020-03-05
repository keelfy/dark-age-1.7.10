/*
 *  Copyright (c) 2016-2017, Rubedo
 */

package keelfy.darkdata.client.gui;

import java.util.Map.Entry;

import org.lwjgl.opengl.GL11;

import keelfy.darkcore.common.player.DADataManager;
import keelfy.darkcore.common.player.DAPlayerData;
import keelfy.darkcore.common.player.skills.Skill;
import keelfyutils.client.KColors;
import keelfyutils.client.KGL;
import keelfyutils.client.KGui;

/**
 * @author keelfy
 * @created 9 янв. 2018 г.
 */
public class DASkillsGui extends DAScreenGui {

	private DAPlayerData data;

	private int currentCategory = -1;
	private int categoryWidth = 0;

	@Override
	public void initGui() {
		super.initGui();

		this.data = DADataManager.getPlayer(mc.thePlayer);

	}

	@Override
	protected boolean hasBlackBackground() {
		return true;
	}

	@Override
	protected void drawForeground(int mouseX, int mouseY, float partialTicks) {
		categoryWidth = width / data.skills.getCategories().size();
		String s = "";

		KGui.drawGradientRect(0, 0, width, height / 6, 0x00ffffff, 0x00ffff);

		for (int i = 0; i < data.skills.getCategories().size(); i++) {
			GL11.glPushMatrix();
			s = data.skills.getCategories().get(i).getName();
			KGL.string(s, i * categoryWidth + categoryWidth / 2 - KGL.stringWidth(s) / 2, height / 12 - KGL.stringHeight() / 2, KColors.WHITE_COLOR);
			GL11.glPopMatrix();
		}

		if (currentCategory != -1) {
			KGui.drawGradientRect(0, height / 6, width / 4, height, 1, 1, 0, 1, 1, 1, 0, 1);

			s = data.skills.getCategories().get(currentCategory).getName();
			KGL.string(s, width / 8 - KGL.stringWidth(s) / 2, height / 6 + 2, KColors.HUD_COLOR);

			int k = 0;
			for (Entry<String, Skill> sk : data.skills.getCategories().get(currentCategory).getSkills().entrySet()) {
				KGL.string("Навык: " + sk.getValue().name, 10, height / 6 + 15 * k + 20);
				k++;
			}
		}
	}

	@Override
	protected void mouseClickMove(int mouseX, int mouseY, int lastButtonClicked, long timeSinceClick) {
		super.mouseClickMove(mouseX, mouseY, lastButtonClicked, timeSinceClick);
	}

	@Override
	public void mouseClicked(int mouseX, int mouseY, int state) {

		if (mouseY > 0 && mouseY < height / 6) {
			for (int i = 0; i < data.skills.getCategories().size(); i++) {
				if (mouseX > i * categoryWidth && mouseX < (i + 1) * categoryWidth) {
					currentCategory = i;
				}
			}
		}

		super.mouseClicked(mouseX, mouseY, state);
	}

}
