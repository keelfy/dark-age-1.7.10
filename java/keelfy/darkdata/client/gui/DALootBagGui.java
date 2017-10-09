/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.client.gui;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkdata.blocks.tileentities.TileEntityLoot;
import keelfy.darkdata.client.DAResources.Texture;
import keelfy.darkdata.constants.EnumTexturePath;
import keelfy.darkdata.inventory.loot.ContainerLoot;
import keelfyutils.KUtils;
import keelfyutils.client.KGL;
import keelfyutils.client.KGui;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

/**
 * @author keelfy
 */
@SideOnly(Side.CLIENT)
public class DALootBagGui extends GuiContainer {

	private static final Minecraft MC = KGL.mc();
	private TileEntityLoot inventory;
	private static final RenderItem itemRenderer = new RenderItem();
	private static final ResourceLocation selector = Texture.get(EnumTexturePath.GUI, "lootBagSelector");
	private int selectedStackNumber;

	public DALootBagGui(final TileEntityLoot tileentity) {
		super(new ContainerLoot(tileentity));

		if (KUtils.PROTECT_CLIENT) {
			this.inventory = tileentity;
		}
	}

	@Override
	public void initGui() {
		if (KUtils.PROTECT_CLIENT) {
			selectedStackNumber = 0;
			super.initGui();
		}
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	@Override
	protected void keyTyped(final char typedChar, final int keyCode) {

		if (KUtils.PROTECT_CLIENT) {
			final ItemStack selectedStack = null;
			if (typedChar == Keyboard.KEY_H) {
				selectedStackNumber++;
			}

			if (keyCode != Keyboard.KEY_RETURN || keyCode != Keyboard.KEY_SPACE || keyCode != Keyboard.KEY_H
					|| keyCode != Keyboard.KEY_RIGHT || keyCode != Keyboard.KEY_DOWN || keyCode != Keyboard.KEY_UP) {
				KGui.closeGui();
			}
		}
	}

	@Override
	public void drawDefaultBackground() {}

	@Override
	protected void drawGuiContainerForegroundLayer(final int x, final int y) {
		if (KUtils.PROTECT_CLIENT) {
			final int w = super.width;
			final int h = super.height;

			int stackCount = 0, stackCount1 = 0, height1 = 0;
			final int width1 = 0;
			for (int i = 0; i < inventory.inventoryStacks.length; i++) {
				if (inventory.inventoryStacks[i] != null) {
					stackCount++;
					stackCount1++;
					if (height1 != stackCount / 10) {
						stackCount1 = 1;
					}
					height1 = stackCount % 10 == 0 ? stackCount / 10 : height1;

					glPushMatrix();
					if (1 == i) {
						glScalef(0.7F, 0.7F, 0.7F);
					}
					KGui.drawItemStack(itemRenderer, inventory.inventoryStacks[i], w / 2 + 85 - (18 * stackCount1),
							h / 2 + 60 - (18 * height1), 4F);
					glPopMatrix();
				}
			}
		}
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(final float partialTicks, final int mouseX, final int mouseY) {}

	@Override
	protected void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) {}

	@Override
	protected void mouseClickMove(final int mouseX, final int mouseY, final int lastButtonClicked,
			final long timeSinceMouseClick) {}

	@Override
	protected void mouseMovedOrUp(final int mouseX, final int mouseY, final int which) {}
}
