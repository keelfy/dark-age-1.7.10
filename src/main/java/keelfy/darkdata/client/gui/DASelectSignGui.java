package keelfy.darkdata.client.gui;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkcore.common.player.DADataManager;
import keelfy.darkdata.client.DAKeyHandler;
import keelfy.darkdata.client.DAResources.Texture;
import keelfy.darkdata.client.gui.buttons.DASignButton;
import keelfy.darkdata.constants.EnumSign;
import keelfy.darkdata.constants.EnumTexturePath;
import keelfyutils.client.KGL;
import keelfyutils.client.KGui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

/**
 * @author keelfy
 * @created 26 июл. 2017 г.
 */
@SideOnly(Side.CLIENT)
public final class DASelectSignGui extends GuiScreen {

	private static final ResourceLocation Texture_SelectSign = Texture.get(EnumTexturePath.GUI, "SelectSign");

	private static final int sizeX = 162;
	private static final int sizeY = 81;

	private DASignButton aard, aksi, igni, irden, kven;

	@Override
	public final void initGui() {
		super.buttonList.add(aard = new DASignButton(0, super.width / 2 + 55, super.height / 2 - 10, EnumSign.Aard));
		super.buttonList.add(aksi = new DASignButton(1, super.width / 2 + 27, super.height / 2 - 38, EnumSign.Aksi));
		super.buttonList.add(igni = new DASignButton(2, super.width / 2 - 14, super.height / 2 - 50, EnumSign.Igni));
		super.buttonList.add(irden = new DASignButton(3, super.width / 2 - 83, super.height / 2 - 10, EnumSign.Irden));
		super.buttonList.add(kven = new DASignButton(4, super.width / 2 - 55, super.height / 2 - 38, EnumSign.Kven));
	}

	@Override
	public final void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
		super.drawDefaultBackground();
		KGL.bindTexture(Texture_SelectSign);

		GL11.glEnable(GL11.GL_BLEND);
		// GL11.glPushMatrix();
		// GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		// GL11.glTranslatef(super.width / 2, super.height / 2, 0.0F);
		// GL11.glRotatef(-180.0F, 0.0F, 0.0F, 1.0F);
		// GL11.glRotatef(x / 2, 0.0F, 0.0F, 1.0F);
		// KGL.texturedRect(0, -29, 233, 3, 23, 59);
		// GL11.glPopMatrix();
		GL11.glPushMatrix();
		GL11.glColor4f(1, 1, 1, 1);

		// if (aard.isMouseOverButton()) {
		// KGL.texturedRect(super.width / 2, super.height / 2, 0, 90, 20, 25);
		// } else if (aksi.isMouseOverButton()) {
		// KGL.texturedRect(super.width / 2, super.height / 2, 26, 90, 28, 16);
		// } else if (igni.isMouseOverButton()) {
		// KGL.texturedRect(super.width / 2, super.height / 2, 42, 90, 32, 10);
		// } else if (irden.isMouseOverButton()) {
		// KGL.texturedRect(super.width / 2, super.height / 2, 42, 90, 32, 10);
		// } else if (kven.isMouseOverButton()) {
		// KGL.texturedRect(super.width / 2, super.height / 2, 42, 90, 32, 10);
		// }
		GL11.glPushMatrix();
		KGL.texturedRect(super.width / 2 - sizeX / 2, super.height / 2 - sizeY / 2, 5, 4, sizeX, sizeY);
		GL11.glPopMatrix();
		GL11.glDisable(GL11.GL_BLEND);

		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	private final boolean isMouseOver(final int mouseX, final int mouseY, final int x, final int maxX, final int y,
			final int maxY) {
		return (mouseX > super.width / 2 + x && mouseX < super.width / 2 + maxX)
				&& (mouseY > super.height / 2 + y && mouseY < super.height / 2 + maxY);
	}

	@Override
	protected final void keyTyped(final char key, final int keyCode) {
		super.keyTyped(key, keyCode);

		if (keyCode == DAKeyHandler.Instance.keys[10].getKeyCode()) {
			KGui.closeGui();
		}
	}

	@Override
	protected final void actionPerformed(final GuiButton button) {
		DADataManager.getPlayer(mc.thePlayer).signs.setCurrent(button.id);
		KGui.closeGui();
	}
}
