package keelfy.darkdata.client.gui;

import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfyutils.client.KGL;
import keelfyutils.client.KGui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.ResourceLocation;

/**
 * @author keelfy
 * @created 12 июл. 2017 г.
 */
@SideOnly(Side.CLIENT)
public abstract class DAScreenGui extends GuiScreen {

	protected List<GuiTextField> textFields = new ArrayList();

	protected final void addTextField(final GuiTextField textField, final int maxStringLenght) {
		textField.setMaxStringLength(maxStringLenght);
		textFields.add(textField);
	}

	@Override
	public void updateScreen() {
		for (final GuiTextField tf : textFields) {
			tf.updateCursorCounter();
		}
	}

	@Override
	public void initGui() {
		Keyboard.enableRepeatEvents(true);
		this.buttonList.clear();
	}

	@Override
	public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
		if (hasBlackBackground()) {
			drawBlackBackground();
		}
		for (final GuiTextField tf : textFields) {
			tf.drawTextBox();
		}
		this.drawForeground(mouseX, mouseY, partialTicks);
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	protected abstract void drawForeground(final int mouseX, final int mouseY, final float partialTicks);

	@Override
	protected void keyTyped(final char key, final int keyCode) {
		if (keyCode == Keyboard.KEY_ESCAPE) {
			KGui.closeGui();
		}

		for (final GuiTextField tf : textFields) {
			tf.textboxKeyTyped(key, keyCode);
		}
	}

	@Override
	public void mouseClicked(final int mouseX, final int mouseY, final int state) {
		for (final GuiTextField tf : textFields) {
			tf.mouseClicked(mouseX, mouseY, state);
		}
		super.mouseClicked(mouseX, mouseY, state);
	}

	@Override
	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);
	}

	@Override
	public final boolean doesGuiPauseGame() {
		return false;
	}

	protected ResourceLocation getBackgroundTexture() {
		return DAContainerGui.backgroundTexture;
	}

	protected boolean hasBlackBackground() {
		return false;
	}

	private final void drawBlackBackground() {
		glPushMatrix();
		glEnable(GL_ALPHA_TEST);
		glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		KGL.bindTexture(super.mc, getBackgroundTexture());
		KGL.drawFullscreenTexturedRectZ(super.width, super.height, -1);
		glDisable(GL_ALPHA_TEST);
		glPopMatrix();
	}
}
