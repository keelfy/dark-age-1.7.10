package keelfy.darkdata.client.gui;

import org.lwjgl.opengl.GL11;

import keelfy.darkdata.inventory.loot.DAContainerChest;
import keelfyutils.client.KColors;
import keelfyutils.client.KGL;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

/**
 * @author keelfy
 * @created 30 июл. 2017 г.
 */
public final class DAChestGui extends DAContainerGui {

	private static final Minecraft mc = KGL.mc();
	private static final ResourceLocation Texture = new ResourceLocation("textures/gui/container/generic_54.png");
	private IInventory upperChestInventory;
	private IInventory lowerChestInventory;
	private int inventoryRows;

	public DAChestGui(final InventoryPlayer playerInv, final IInventory inv) {
		super(new DAContainerChest(playerInv, inv, mc.thePlayer.capabilities.isCreativeMode));

		this.upperChestInventory = playerInv;
		this.lowerChestInventory = inv;
		this.allowUserInput = false;
		short short1 = 222;
		int i = short1 - 108;
		this.inventoryRows = inv.getSizeInventory() / 9;
		this.ySize = i + this.inventoryRows * 18;
	}

	@Override
	protected boolean isLightingOfActiveSlotEnabled() {
		return false;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(final int mouseX, final int mouseY) {
		this.fontRendererObj.drawString(
				this.lowerChestInventory.hasCustomInventoryName() ? this.lowerChestInventory.getInventoryName()
						: I18n.format(this.lowerChestInventory.getInventoryName(), new Object[0]),
				8, 5, KColors.WHITE_COLOR);
		this.fontRendererObj.drawString(
				this.upperChestInventory.hasCustomInventoryName() ? this.upperChestInventory.getInventoryName()
						: I18n.format(this.upperChestInventory.getInventoryName(), new Object[0]),
				8, this.ySize - 96 + 1, KColors.WHITE_COLOR);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(final float partialTicks, final int mouseX, final int mouseY) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		KGL.bindTexture(Texture);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.inventoryRows * 18 + 17);
		this.drawTexturedModalRect(k, l + this.inventoryRows * 18 + 17, 0, 126, this.xSize, 96);
	}
}
