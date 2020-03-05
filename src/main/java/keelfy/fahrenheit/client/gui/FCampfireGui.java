/*
 *  Copyright (c) 2016-2017, Rubedo
 */

package keelfy.fahrenheit.client.gui;

import org.lwjgl.opengl.GL11;

import keelfy.fahrenheit.Fahrenheit;
import keelfy.fahrenheit.common.FRegisterer;
import keelfy.fahrenheit.common.containers.ContainerCampfire;
import keelfy.fahrenheit.common.tileentities.TileEntityCampfire;
import keelfy.fahrenheit.network.FCNetwork;
import keelfy.fahrenheit.network.FEnumSPackets;
import keelfyutils.client.KGL;
import keelfyutils.str.Brush;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

/**
 * @author keelfy
 * @created 6 окт. 2017 г.
 */
public class FCampfireGui extends GuiContainer {

	private static final ResourceLocation furnaceGuiTextures = new ResourceLocation(Fahrenheit.MOD_ID, "textures/gui/campfire.png");
	private static final ResourceLocation slotTexture = new ResourceLocation(Fahrenheit.MOD_ID, "textures/gui/slot.png");
	private TileEntityCampfire tile;

	private static final int StartFireButtonId = 0;
	private String text = "";
	public String additional = "";

	public FCampfireGui(InventoryPlayer inventory, TileEntityCampfire tileentity) {
		super(new ContainerCampfire(inventory, tileentity));

		this.tile = tileentity;
	}

	@Override
	public void initGui() {
		super.initGui();

		this.buttonList.clear();

		this.buttonList.add(new GuiButton(StartFireButtonId, guiLeft + 120, guiTop + 6, 50, 20, "Разжечь"));
	}

	@Override
	protected void actionPerformed(GuiButton button) {
		switch (button.id) {
		case StartFireButtonId:
			if (!tile.isBurning()) {
				FCNetwork.sendToServer(FEnumSPackets.StartFire, tile.xCoord, tile.yCoord, tile.zCoord);
			}
			break;
		}
	}

	public int timer = 60;

	@Override
	public void updateScreen() {
		super.updateScreen();

		if (this.buttonList.size() > 0) {
			GuiButton btn = ((GuiButton) this.buttonList.get(0));

			if (tile.getStackInSlot(1) == null && btn.enabled) {
				btn.enabled = false;
			} else if (tile.getStackInSlot(1) != null && !btn.enabled) {
				btn.enabled = true;
			}

			if (tile.isBurning() && btn.enabled) {
				btn.enabled = false;
			}

			if (timer < 60 && btn.enabled) {
				btn.enabled = false;
			}

			if (timer == 60 && !tile.isBurning() && tile.getStackInSlot(1) != null && !mc.thePlayer.inventory.hasItem(FRegisterer.match)) {
				btn.enabled = false;
			}
		}

		if ("".equals(additional)) {
			if (timer != 60) {
				timer = 60;
			}
		} else if (timer < 60) {
			timer++;
		}

		if (timer == 60 && !"".equals(additional)) {
			additional = "";
		}
	}

	/**
	 * Draw the foreground layer for the GuiContainer (everything in front of the
	 * items)
	 */
	@Override
	protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_) {
		String s = this.tile.hasCustomInventoryName() ? this.tile.getInventoryName() : I18n.format(this.tile.getInventoryName(), new Object[0]);
		this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
		this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		KGL.bindTexture(slotTexture);

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		KGL.texturedRect(guiLeft + 40, guiTop + 16, 1, 0, 0, 18, 18);

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		KGL.texturedRect(guiLeft + 40, guiTop + 52, 1, 0, 0, 18, 18);

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		KGL.texturedRect(guiLeft + 100, guiTop + 34, 1, 0, 0, 18, 18);

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		KGL.texturedRect(guiLeft + 10, guiTop + 34, 1, 0, 0, 18, 18);

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		KGL.bindTexture(furnaceGuiTextures);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		KGL.texturedRect(k, l, 0, 0, 0, this.xSize, this.ySize);
		KGL.texturedRect(k + 42, l + 35, 1, 190, 0, 14, 14);
		KGL.texturedRect(k + 65, l + 35, 1, 176, 31, 24, 16);

		if (this.tile.isBurning()) {
			int i1 = this.tile.getBurnTimeRemainingScaled(13);
			KGL.texturedRect(k + 42, l + 35 + 12 - i1, 2, 176, 12 - i1, 14, i1 + 1);
			i1 = this.tile.getCookProgressScaled(24);
			KGL.texturedRect(k + 65, l + 35, 2, 176, 14, i1 + 1, 16);
		}

		if (timer < 60) {
			KGL.string(additional, guiLeft + 100, guiTop + 62, 0x000000, true);
		} else {
			text = tile.getStackInSlot(1) == null ? tile.isBurning() ? Brush.GREEN + "Костер разведен!" : Brush.RED + "Нет топлива!"
					: tile.isBurning() ? Brush.GREEN + "Костер разведен!" : (Brush.DARK_AQUA + "Шанс успеха - " + (tile.getStackInSlot(3) != null ? "100%" : "20%"));
			if (!tile.isBurning() && tile.getStackInSlot(1) != null && !mc.thePlayer.inventory.hasItem(FRegisterer.match)) {
				text = Brush.RED + "Нет спичек!";
			}
			KGL.string(text, guiLeft + 100, guiTop + 62, 0x000000, true);
		}
	}
}
