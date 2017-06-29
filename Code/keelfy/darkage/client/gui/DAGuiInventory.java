/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.client.gui;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.config.GuiUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkage.constants.EnumProperty;
import keelfy.darkage.constants.EnumRepairKit;
import keelfy.darkage.constants.EnumServerPacket;
import keelfy.darkage.constants.EnumTexturePath;
import keelfy.darkage.entities.player.DAPlayer;
import keelfy.darkage.handlers.client.ResourceHandler.Texture;
import keelfy.darkage.inventory.player.DAContainerPlayerInventory;
import keelfy.darkage.inventory.player.DAPlayerInventory;
import keelfy.darkage.items.Money;
import keelfy.darkage.items.RepairKit;
import keelfy.darkage.network.ClientPacketHandler;
import keelfy.darkage.utils.DAEntityUtils;
import keelfytools.KeelfyUtils;
import keelfytools.LocalizationUtils;
import keelfytools.gui.KeelfyUtilsGui;
import keelfytools.log.Brush;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

/**
 * @author keelfy
 */
@SideOnly(Side.CLIENT)
public class DAGuiInventory extends DAGuiContainer {

	private float xSize_lo;
	private float ySize_lo;
	private ResourceLocation inv;
	private ResourceLocation slot;
	private ResourceLocation background;
	private DAPlayerInventory inventory;
	private DAContainerPlayerInventory container;
	public static EntityPlayer p;
	
	private boolean repairing;
	private int repairingPercent;
	private int repairKitSlot;
	private EnumRepairKit repairingType;
	private ItemStack repairKit;

	private String gui_nickname, gui_health, gui_armor, gui_repairing_1, gui_repairing_2;
	
	private Slot hoverSlot = null; 
	
	public DAGuiInventory(EntityPlayer player, DAPlayerInventory inventoryCustom, DAContainerPlayerInventory inventoryCustomContainer, DAContainerPlayerInventory container) {
		super(container);
		
		if(KeelfyUtils.isClientSide()) {
			inv = Texture.get(EnumTexturePath.INVENTORY, "inventory");
			slot = Texture.get(EnumTexturePath.INVENTORY, "slot");
			background = Texture.get(EnumTexturePath.INVENTORY, "invbackground");
			this.inventory = inventoryCustom;
			p = player;
			
			gui_nickname = LocalizationUtils.localize(LocalizationUtils.gui_nickname);
			gui_health = LocalizationUtils.localize(LocalizationUtils.gui_health);
			gui_armor = LocalizationUtils.localize(LocalizationUtils.gui_armor);
			gui_repairing_1 = LocalizationUtils.localize(LocalizationUtils.gui_repairing_1);
			gui_repairing_2 = LocalizationUtils.localize(LocalizationUtils.gui_repairing_2);
		}
	}

	@Override
	public void initGui() {
		if(KeelfyUtils.isClientSide()) {
			super.initGui();
		
			this.xSize = 512;
			this.ySize = 256;
			this.repairing = false;
		}
	}
	
	@Override
	public void drawScreen(int par1, int par2, float par3) {
		if(KeelfyUtils.isClientSide()) {
			super.drawScreen(par1, par2, par3);
			this.xSize_lo = par1;
			this.ySize_lo = par2;
		}
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
		if(KeelfyUtils.isClientSide()) {
			guiLeft = (this.width - this.xSize) / 2;
			guiTop = (this.height - this.ySize) / 2;
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glPushMatrix();
			GL11.glDisable(2929);
			GL11.glDepthMask(false);
			GL11.glBlendFunc(770, 771);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glDisable(3008);
			GL11.glEnable(3042);
			super.mc.getTextureManager().bindTexture(background);
			Tessellator tes = Tessellator.instance;
			tes.startDrawingQuads();
			tes.addVertexWithUV(0.0D, super.height, -90.0D, 0.0D, 1.0D);
			tes.addVertexWithUV(super.width, super.height, -90.0D, 1.0D, 1.0D);
			tes.addVertexWithUV(super.width, 0.0D, -90.0D, 1.0D, 0.0D);
			tes.addVertexWithUV(0.0D, 0.0D, -90.0D, 0.0D, 0.0D);
			tes.draw();
			super.mc.getTextureManager().bindTexture(inv);
			GL11.glTexParameterf(3553, 10240, 9729.0F);
			GL11.glTexParameterf(3553, 10241, 9729.0F);
			GL11.glEnable(3553);
			int x = super.width / 2 - 256;
			int y = super.height / 2 - 150;
			boolean u = false;
			boolean v = false;
			short width = 512;
			short height = 512;
			byte zLevel = 0;
			float var7 = 0.00390625F;
			float var8 = 0.00390625F;
			Tessellator tessellator = Tessellator.instance;
			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(x + 0, y + height, zLevel, 0.0D, 1.0D);
			tessellator.addVertexWithUV(x + width, y + height, zLevel, 1.0D, 1.0D);
			tessellator.addVertexWithUV(x + width, y + 0, zLevel, 1.0D, 0.0D);
			tessellator.addVertexWithUV(x + 0, y + 0, zLevel, 0.0D, 0.0D);
			tessellator.draw();
			super.mc.getTextureManager().bindTexture(slot);
	
			for (int player = 0; player < 6; ++player) {
				for (int k = 0; k < 4; ++k) {
					GuiUtils.drawTexturedModalRect(21 * player + x + 29, 21 * k + y + 67, 0, 0, 32, 32, zLevel);
				}
			}
	
			GL11.glDepthMask(true);
			GL11.glEnable(2929);
			GL11.glEnable(3008);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glTexParameterf(3553, 10240, 9728.0F);
			GL11.glTexParameterf(3553, 10241, 9728.0F);
			GL11.glPopMatrix();
			KeelfyUtilsGui.drawPlayerModel(super.width / 2 + 170, super.height / 2 + 70, 110, 0.0F, 0.0F, p);
			DAPlayer wcp = DAPlayer.get(p);
			String s = String.valueOf(gui_nickname + " " + p.getDisplayName());
			super.drawString(this.fontRendererObj, s, this.width / 2 + 110, this.height / 2 + 80, Integer.MAX_VALUE);
			s = String.valueOf(gui_health + " " + (int) wcp.get(EnumProperty.HEALTH));
			super.drawString(this.fontRendererObj, s, this.width / 2 + 110, this.height / 2 + 94, Integer.MAX_VALUE);
			s = String.valueOf(gui_armor + " " + getTotalBlockingPercent());
			super.drawString(this.fontRendererObj, s, this.width / 2 + 110, this.height / 2 + 105, Integer.MAX_VALUE);
			s = String.valueOf(String.format("%.1f кг", wcp.get(EnumProperty.WEIGHT)));
			super.drawString(this.fontRendererObj, s, this.width / 2 + 49 - (mc.fontRenderer.getStringWidth(s) / 3), this.height / 2 - 106, Integer.MAX_VALUE);
			if(repairing) {
				s = String.valueOf(gui_repairing_2);
				super.drawString(this.fontRendererObj, s, this.width - this.fontRendererObj.getStringWidth(s), this.height - (10 + this.fontRendererObj.FONT_HEIGHT), Integer.MAX_VALUE);
				s = String.valueOf(gui_repairing_1 + " " + Brush.UNDERLINE + repairingPercent + "%");
				super.drawString(this.fontRendererObj, s, this.width - (5 + this.fontRendererObj.getStringWidth(s)), this.height - (20 + this.fontRendererObj.FONT_HEIGHT), Integer.MAX_VALUE);
			}
			
			hoverSlot = getSlotAtPosition(par2, par3);
		}
	}

	@Override
	protected void keyTyped(char key, int id) {
		if(isRepairing()) {
			if(id == Keyboard.KEY_ESCAPE || id == mc.gameSettings.keyBindInventory.getKeyCode()) {
				disableRepairing();
			}
		} else {
			if(id == Keyboard.KEY_Q) {
				if(hoverSlot != null && hoverSlot.getHasStack()) {
					ClientPacketHandler.sendToServer(EnumServerPacket.DROPITEM, hoverSlot.slotNumber + 1, this.isShiftKeyDown());
				}
			}
			
			super.keyTyped(key, id);
		}
	}
	
	@Override
	public void onGuiClosed() {
		if(repairing) this.disableRepairing();
		super.onGuiClosed();
	}
	
	@Override
	protected void handleMouseClick(Slot slot, int id, int y, int z) {	
		if(!isRepairing()) super.handleMouseClick(slot, id, y, z);
	}
	
	@Override
	protected void mouseClicked(int x, int y, int z) {
		if(!isRepairing() && getSlotAtPosition(x, y) != null && getSlotAtPosition(x, y).getHasStack() && isCtrlKeyDown() && getSlotAtPosition(x, y).getStack().getItem() instanceof RepairKit) {
			enableRepairing(getSlotAtPosition(x, y));
		} else if(isRepairing() && isCtrlKeyDown()) {
			disableRepairing();
		} else if(isRepairing()) {
			Slot slot = getSlotAtPosition(x, y);
			if(slot != null && slot.getHasStack()) {
				ClientPacketHandler.sendToServer(EnumServerPacket.REPAIRITEM, repairingPercent, repairingType, slot.slotNumber, repairKit);
			}
		} else if(getSlotAtPosition(x, y) != null && getSlotAtPosition(x, y).getHasStack() && getSlotAtPosition(x, y).getStack().getItem() instanceof Money && (isCtrlKeyDown() || isShiftKeyDown())) {
			if(!(isCtrlKeyDown() && isShiftKeyDown())) {
				Money clickedItem = (Money) getSlotAtPosition(x, y).getStack().getItem();
				ClientPacketHandler.sendToServer(EnumServerPacket.MONEYCLICK, isShiftKeyDown(), isCtrlKeyDown(), clickedItem.getValueInOrenes());
			}
		} else super.mouseClicked(x, y, z);
	}
	
	private void enableRepairing(Slot slot) {
		repairing = true;
		repairKit = slot.getStack();
		repairKitSlot = slot.slotNumber;
		repairingType = ((RepairKit)slot.getStack().getItem()).getType();
		repairingPercent = (int)((RepairKit) slot.getStack().getItem()).getRepairPercent();
	}
	
	private boolean isRepairing() {
		return repairing;
	}
	
	public void disableRepairing() {
		repairing = false;
		repairKitSlot = -1;
		repairingType = null;
		repairingPercent = 0;
	}
	
	private Slot getSlotAtPosition(int x, int y) {
		for (int k = 0; k < this.inventorySlots.inventorySlots.size(); ++k) {
			Slot slot = (Slot)this.inventorySlots.inventorySlots.get(k);

			if (this.isMouseOverSlot(slot, x, y))
	                return slot;
		}
		return null;
	}
	
   private boolean isMouseOverSlot(Slot slot, int x, int y) {
       return func_146978_c(slot.xDisplayPosition, slot.yDisplayPosition, 16, 16, x, y);
   }
	
	private String getTotalBlockingPercent() {
		if(KeelfyUtils.isClientSide()) {
			float r = DAEntityUtils.getTotalBlockingPercent(p);
			
			if(r % 1 == 0) return (int)r + "%";
			else return r + "%";
		} else 
			return null;
	}
}
