package keelfy.darkdata.client.gui.tabbed;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import keelfyutils.client.KGL;
import keelfyutils.str.KString;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public abstract class AbstractTab extends GuiButton {

	private final ResourceLocation tabTexture = new ResourceLocation(
			"textures/gui/container/creative_inventory/tabs.png");
	protected ItemStack renderStack;
	private final RenderItem itemRender = new RenderItem();

	public AbstractTab(final int id, final int posX, final int posY, final ItemStack renderStack) {
		super(id, posX, posY, 28, 32, KString.EMPTY);

		this.renderStack = renderStack;
	}

	@Override
	public final void drawButton(final Minecraft mc, final int mouseX, final int mouseY) {
		if (this.visible) {
			glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			final int yTexPos = this.enabled ? 3 : 32;
			final int ySize = this.enabled ? 25 : 32;
			final int xOffset = this.id == 2 ? 0 : 1;
			final int yPos = this.yPosition + (this.enabled ? 3 : 0);
			KGL.bindTexture(tabTexture);
			glColor3f(0, 0, 0);
			this.drawTexturedModalRect(this.xPosition, yPos, xOffset * 28, yTexPos, 28, ySize);
			RenderHelper.enableGUIStandardItemLighting();
			this.zLevel = 100.0F;
			this.itemRender.zLevel = 100.0F;
			glEnable(GL_LIGHTING);
			glEnable(GL12.GL_RESCALE_NORMAL);
			this.itemRender.renderItemAndEffectIntoGUI(mc.fontRenderer, mc.renderEngine, renderStack, xPosition + 6,
					yPosition + 8);
			this.itemRender.renderItemOverlayIntoGUI(mc.fontRenderer, mc.renderEngine, renderStack, xPosition + 6,
					yPosition + 8);
			glDisable(GL11.GL_LIGHTING);
			glEnable(GL11.GL_BLEND);
			this.itemRender.zLevel = 0.0F;
			this.zLevel = 0.0F;
			RenderHelper.disableStandardItemLighting();
		}
	}

	@Override
	public final boolean mousePressed(final Minecraft mc, final int mouseX, final int mouseY) {
		final boolean inWindow = this.enabled && this.visible && mouseX >= this.xPosition && mouseY >= this.yPosition
				&& mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
		if (inWindow) {
			this.onTabClicked();
		}
		return inWindow;
	}

	public abstract void onTabClicked();

	public abstract boolean shouldAddToList();
}
