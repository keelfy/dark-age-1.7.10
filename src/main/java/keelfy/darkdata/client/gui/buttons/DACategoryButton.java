/*
 *  Copyright (c) 2016-2017, Rubedo
 */

package keelfy.darkdata.client.gui.buttons;

import org.lwjgl.opengl.GL11;

import keelfyutils.client.KGL;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.OpenGlHelper;

/**
 * @author keelfy
 * @created 10 янв. 2018 г.
 */
public class DACategoryButton extends GuiButton {

	public int hoverState = 0;
	public boolean isActivated = false;
	public boolean isMouseOverButton = false;

	public DACategoryButton(int id, int x, int y, int width, int height, String text) {
		super(id, x, y, width, height, text);
	}

	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY) {
		if (this.visible) {
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			this.isMouseOverButton = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
			int k = this.getHoverState(this.isMouseOverButton);
			GL11.glEnable(GL11.GL_BLEND);
			OpenGlHelper.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			this.drawRect(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, 0x00ffffff);
			this.mouseDragged(mc, mouseX, mouseY);
			int l = 14737632;

			switch (k) {
			case 0:
				l = 10526880;
				break;
			case 2:
				l = 16777120;
				break;
			case 3:
				l = 16777120;
				break;
			}

			KGL.string(displayString, this.xPosition + (this.width - KGL.stringWidth(displayString)) / 2, this.yPosition + (this.height - KGL.stringHeight()) / 2, l);
		}
	}

	@Override
	public int getHoverState(boolean isMouseOverButton) {
		byte hs = 1;

		if (!this.enabled) {
			hs = 0;
		} else if (isMouseOverButton) {
			hs = 2;
		} else if (this.isActivated) {
			hs = 3;
		}

		return hs;
	}

	public boolean isMouseOverButton() {
		return isMouseOverButton;
	}

}
