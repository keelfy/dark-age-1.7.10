package keelfy.darkdata.client.gui.buttons;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkdata.client.DAResources;
import keelfy.darkdata.constants.EnumSign;
import keelfyutils.client.KGL;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.OpenGlHelper;

/**
 * @author keelfy
 * @created 26 июл. 2017 г.
 */
@SideOnly(Side.CLIENT)
public final class DASignButton extends GuiButton {

	private final EnumSign sign;

	public DASignButton(final int id, final int x, final int y, final EnumSign sign) {
		super(id, x, y, 32, 32, "");

		this.sign = sign;
	}

	@Override
	public void drawButton(final Minecraft mc, final int mouseX, final int mouseY) {
		KGL.bindTexture(DAResources.Texture_Signs);

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		int k = getHoverState(func_146115_a());
		GL11.glEnable(GL11.GL_BLEND);
		OpenGlHelper.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		GL11.glPushMatrix();
		final float scale = 0.5F;
		KGL.texturedRect(super.xPosition, super.yPosition, (36 * sign.ordinal()), 47, 36, 36);
		GL11.glPopMatrix();
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		GL11.glDisable(GL11.GL_BLEND);
		this.mouseDragged(mc, mouseX, mouseY);
	}

	public final boolean isMouseOverButton() {
		return getHoverState(func_146115_a()) == 2;
	}
}
