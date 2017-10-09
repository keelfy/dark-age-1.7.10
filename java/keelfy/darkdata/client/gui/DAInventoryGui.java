/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.client.gui;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkcore.common.player.DADataManager;
import keelfy.darkcore.common.player.DAPlayerData;
import keelfy.darkcore.common.player.EffectsManager.PlayerEffect;
import keelfy.darkcore.network.DACNetwork;
import keelfy.darkdata.client.DAFontHandler;
import keelfy.darkdata.client.DAResources.Texture;
import keelfy.darkdata.client.render.DARenderFont;
import keelfy.darkdata.constants.EnumEffect;
import keelfy.darkdata.constants.EnumFont;
import keelfy.darkdata.constants.EnumRepairKit;
import keelfy.darkdata.constants.EnumTexturePath;
import keelfy.darkdata.inventory.player.DAContainerPlayerInventory;
import keelfy.darkdata.inventory.player.DAPlayerInventory;
import keelfy.darkdata.inventory.slot.DASlot;
import keelfy.darkdata.inventory.slot.DASlotIcon;
import keelfy.darkdata.items.Armor;
import keelfy.darkdata.items.Money;
import keelfy.darkdata.items.RepairKit;
import keelfy.darkdata.network.EnumSPackets;
import keelfy.darkdata.utils.DALocalization;
import keelfyutils.KUtils;
import keelfyutils.client.KColors;
import keelfyutils.client.KGL;
import keelfyutils.client.KGui;
import keelfyutils.client.KLocalization;
import keelfyutils.str.Brush;
import keelfyutils.str.KString;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

/**
 * @author keelfy
 */
@SideOnly(Side.CLIENT)
public final class DAInventoryGui extends DAContainerGui {

	private static final ResourceLocation Texture_Slot = Texture.get(EnumTexturePath.Inventory, "slot");
	private static final ResourceLocation Texture_Background = Texture.get(EnumTexturePath.Inventory, "invbackground");
	private static final ResourceLocation Texture_Slots = Texture.get(EnumTexturePath.Inventory, "slots");
	private static final ResourceLocation Texture_Inventory = Texture.get(EnumTexturePath.Inventory, "newInventory");

	private DAPlayerInventory inventory;
	public static EntityPlayer player;

	private boolean repairing;
	private int repairingPercent;
	private int repairKitSlot;
	private EnumRepairKit repairingType;
	private ItemStack repairKit;

	private static final String gui_nickname = KLocalization.localize(DALocalization.Gui_Nickname);
	private String gui_health, gui_armor, gui_repairing_1, gui_repairing_2;

	private Slot hoverSlot = null;

	public DAInventoryGui(final EntityPlayer player, final DAPlayerInventory inventory) {
		super(new DAContainerPlayerInventory(player, inventory));

		if (KUtils.PROTECT_CLIENT) {
			this.player = player;
			this.inventory = inventory;

			gui_health = KLocalization.localize(DALocalization.Gui_Health);
			gui_armor = KLocalization.localize(DALocalization.Gui_Armor);
			gui_repairing_1 = KLocalization.localize(DALocalization.Gui_Repairing_1);
			gui_repairing_2 = KLocalization.localize(DALocalization.Gui_Repairing_2);
		}
	}

	@Override
	public final void initGui() {
		if (KUtils.PROTECT_CLIENT) {
			super.initGui();

			this.xSize = 512;
			this.ySize = 256;
			this.repairing = false;
		}
	}

	@Override
	protected boolean isHasBlackBackground() {
		return true;
	}

	@Override
	protected boolean isLightingOfActiveSlotEnabled() {
		return false;
	}

	@Override
	protected final void drawGuiContainerBackgroundLayer(final float partialTicks, final int mouseX, final int mouseY) {
		if (KUtils.PROTECT_CLIENT) {
			final FontRenderer fr = super.fontRendererObj;
			final DAPlayerData dap = DADataManager.getPlayer(mc.thePlayer);
			float scale;

			guiLeft = (this.width - this.xSize) / 2;
			guiTop = (this.height - this.ySize) / 2;

			int posX = super.width / 2 - 58;
			int posY = super.height / 2 - 128;

			// Background start
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glPushMatrix();
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

			KGL.bindTexture(Texture_Inventory);
			scale = 1.3F;
			GL11.glScalef(scale, scale, scale);
			KGL.texturedRect((int) (posX / scale) + 9, (int) (posY / scale) + 10, 10, 0, 84, 2);
			KGL.texturedRect((int) (posX / scale) + 9, (int) (posY / scale) + 140, 10, 2, 84, 2);

			GL11.glDisable(GL11.GL_BLEND);
			GL11.glPopMatrix();
			// Background end

			// Slots start
			String s;
			final DARenderFont trebuchet15 = DAFontHandler.Instance.getFont(EnumFont.TrebuchetMS_15);
			final DARenderFont trebuchet10 = DAFontHandler.Instance.getFont(EnumFont.TrebuchetMS_10);

			int brown = 0x68401F;

			int descOffsetX = posX + 21;
			int descOffsetY = posY + 80;

			s = String.valueOf("Стальное");
			trebuchet10.drawCenteredString(s, descOffsetX, descOffsetY, brown);
			s = String.valueOf("оружие");
			trebuchet10.drawCenteredString(s, descOffsetX, descOffsetY + getSH(trebuchet10), brown);

			descOffsetX += 29;

			s = String.valueOf("Серебряный");
			trebuchet10.drawCenteredString(s, descOffsetX, descOffsetY, brown);
			s = String.valueOf("меч");
			trebuchet10.drawCenteredString(s, descOffsetX, descOffsetY + getSH(trebuchet10), brown);

			descOffsetX += 31;

			s = String.valueOf("Корпус");
			trebuchet10.drawCenteredString(s, descOffsetX, descOffsetY, brown);

			descOffsetX += 27;

			s = String.valueOf("Перчатки");
			trebuchet10.drawCenteredString(s, descOffsetX, descOffsetY, brown);

			descOffsetX = posX + 37;
			descOffsetY += 53;

			s = String.valueOf("Дополнительное снаряжение");
			trebuchet10.drawCenteredString(s, descOffsetX, descOffsetY, brown);

			descOffsetX += 44;

			s = String.valueOf("Ноги");
			trebuchet10.drawCenteredString(s, descOffsetX, descOffsetY, brown);

			descOffsetX += 27;

			s = String.valueOf("Обувь");
			trebuchet10.drawCenteredString(s, descOffsetX, descOffsetY, brown);

			descOffsetX = posX + 65;
			descOffsetY += 37;

			s = String.valueOf("Эликсиры и еда");
			trebuchet10.drawCenteredString(s, descOffsetX, descOffsetY, brown);
			// Slots end

			// Weight start
			int weightOffsetX = posX + 50;
			int weightOffsetY = posY + 22;

			KGL.bindTexture(Texture_Inventory);
			GL11.glColor4f(1, 1, 1, 1);
			scale = 1.3F;
			GL11.glPushMatrix();
			GL11.glScalef(scale, scale, scale);
			KGL.texturedRect((int) (weightOffsetX / scale), (int) (weightOffsetY / scale), 0, 0, 7, 7);
			GL11.glPopMatrix();

			s = String.valueOf(String.format("%.1f кг", dap.weight.get()));
			trebuchet15.drawString(s, weightOffsetX + 10, weightOffsetY + 2, KColors.WHITE_COLOR);
			// Weight end

			// Character start
			int characterOffsetX = posX + 168;
			int characterOffsetY = posY + 198;

			KGui.drawEntityLivingBase(characterOffsetX + 4, characterOffsetY, 110, 0.0F, 0.0F, player, -25.0F);

			s = String.valueOf(gui_nickname + KString.SPACE + player.getDisplayName());
			trebuchet15.drawString(s, characterOffsetX, characterOffsetY + 10, KColors.WHITE_COLOR);

			s = String.valueOf("Класс: " + dap.playerClass.get().getLocalizedName());
			trebuchet15.drawString(s, characterOffsetX, characterOffsetY + 20, KColors.WHITE_COLOR);

			s = String.valueOf(gui_health + KString.SPACE + String.format("%.1f", dap.health.get()) + "/"
					+ (int) dap.health.getMax() + " ед.");
			trebuchet15.drawString(s, characterOffsetX, characterOffsetY + 34, KColors.WHITE_COLOR);

			s = String.valueOf(gui_armor + KString.SPACE + getTotalBlockingPercent());
			trebuchet15.drawString(s, characterOffsetX, characterOffsetY + 44, KColors.WHITE_COLOR);

			s = String.valueOf("Интоксикация: "
					+ (dap.intox.get() == 0 ? "0,0" : String.format("%.1f", dap.intox.get() / dap.intox.getMax() * 100))
					+ "%");
			trebuchet15.drawString(s, characterOffsetX, characterOffsetY + 54, KColors.WHITE_COLOR);

			s = String.valueOf("Энергия: " + String.format("%.1f",
					(dap.energy.get() - dap.energy.getMin()) / (dap.energy.getMax() - dap.energy.getMin()) * 100)
					+ "%");
			trebuchet15.drawString(s, characterOffsetX, characterOffsetY + 64, KColors.WHITE_COLOR);
			// Character end

			// Repairing start
			if (repairing) {
				s = String.valueOf(gui_repairing_2);
				trebuchet15.drawString(s, this.width - KGL.stringWidth(fr, s),
						this.height - (10 + KGL.stringHeight(fr)), KColors.WHITE_COLOR);
				s = String.valueOf(gui_repairing_1 + KString.SPACE + Brush.UNDERLINE + repairingPercent + "%");
				trebuchet15.drawString(s, this.width - (5 + KGL.stringWidth(fr, s)),
						this.height - (20 + KGL.stringHeight(fr)), KColors.WHITE_COLOR);
			}
			// Repairing end

			hoverSlot = getSlotAtPosition(mouseX, mouseY);
		}
	}

	private int getSH(DARenderFont font) {
		return font.getStringHeight() + 2;
	}

	@Override
	protected void drawSlot(Slot slot) {
		if (slot instanceof DASlot) {
			this.drawSlot((DASlot) slot);
		}
	}

	private void drawSlot(DASlot slot) {
		if (KUtils.PROTECT_CLIENT) {
			final int i = slot.xDisplayPosition;
			final int j = slot.yDisplayPosition;
			ItemStack itemstack = slot.getStack();
			boolean flag = false;
			boolean flag1 = slot == this.clickedSlot && this.draggedStack != null && !this.isRightMouseClick;
			final ItemStack itemstack1 = this.mc.thePlayer.inventory.getItemStack();
			String s = null;

			RenderHelper.disableStandardItemLighting();
			GL11.glPushMatrix();
			KGL.bindTexture(Texture_Slots);
			DASlotIcon ic = slot.getIcon();
			final float scale = 1.16F;
			GL11.glScalef(scale, scale, scale);
			int icX = (int) (i / scale);
			int icY = (int) (j / scale);
			ic.draw(slot, icX, icY);
			GL11.glPopMatrix();
			RenderHelper.enableGUIStandardItemLighting();

			if (slot == this.clickedSlot && this.draggedStack != null && this.isRightMouseClick && itemstack != null) {
				itemstack = itemstack.copy();
				itemstack.stackSize /= 2;
			} else if (this.field_147007_t && this.field_147008_s.contains(slot) && itemstack1 != null) {
				if (this.field_147008_s.size() == 1)
					return;

				if (Container.func_94527_a(slot, itemstack1, true) && this.inventorySlots.canDragIntoSlot(slot)) {
					itemstack = itemstack1.copy();
					flag = true;
					Container.func_94525_a(this.field_147008_s, this.dragSplittingLimit, itemstack,
							slot.getStack() == null ? 0 : slot.getStack().stackSize);

					if (itemstack.stackSize > itemstack.getMaxStackSize()) {
						s = String.valueOf(Brush.YELLOW + String.valueOf(itemstack.getMaxStackSize()));
						itemstack.stackSize = itemstack.getMaxStackSize();
					}

					if (itemstack.stackSize > slot.getSlotStackLimit()) {
						s = String.valueOf(Brush.YELLOW + String.valueOf(slot.getSlotStackLimit()));
						itemstack.stackSize = slot.getSlotStackLimit();
					}
				} else {
					this.field_147008_s.remove(slot);
					this.updateDragSplitting();
				}
			}

			this.zLevel = 2.0F;
			itemRender.zLevel = 2.0F;

			if (itemstack == null) {
				final IIcon iicon = slot.getBackgroundIconIndex();

				if (iicon != null) {
					GL11.glDisable(GL11.GL_LIGHTING);
					GL11.glEnable(GL11.GL_BLEND);
					KGL.bindTexture(TextureMap.locationItemsTexture);
					this.drawTexturedModelRectFromIcon(i, j, iicon, 16, 16);
					GL11.glDisable(GL11.GL_BLEND);
					GL11.glEnable(GL11.GL_LIGHTING);
					flag1 = true;
				}
			}

			if (!flag1) {
				if (flag) {
					// drawRect(i, j, i + 16, j + 16, -2130706433);
				}

				itemRender.renderItemAndEffectIntoGUI(this.fontRendererObj, this.mc.getTextureManager(), itemstack, i,
						j);
				itemRender.renderItemOverlayIntoGUI(this.fontRendererObj, this.mc.getTextureManager(), itemstack, i, j,
						s);
			}

			itemRender.zLevel = 0.0F;
			this.zLevel = 0.0F;
		}
	}

	@Override
	protected final void keyTyped(final char typedChar, final int keyCode) {
		if (KUtils.PROTECT_CLIENT) {
			if (isRepairing()) {
				if (keyCode == Keyboard.KEY_ESCAPE || keyCode == mc.gameSettings.keyBindInventory.getKeyCode()) {
					disableRepairing();
				}
			} else {
				if (keyCode == Keyboard.KEY_Q) {
					if (hoverSlot != null && hoverSlot.getHasStack()) {
						DACNetwork.sendToServer(EnumSPackets.Drop, hoverSlot.slotNumber, this.isShiftKeyDown());
					}
				}

				super.keyTyped(typedChar, keyCode);
			}
		}
	}

	@Override
	public final void onGuiClosed() {
		if (KUtils.PROTECT_CLIENT) {
			if (repairing) {
				this.disableRepairing();
			}
			super.onGuiClosed();
		}
	}

	@Override
	protected final void handleMouseClick(final Slot slotIn, final int slotId, final int clickedButton,
			final int clickType) {
		if (KUtils.PROTECT_CLIENT) {
			if (!isRepairing()) {
				super.handleMouseClick(slotIn, slotId, clickedButton, clickType);
			}
		}
	}

	@Override
	protected final void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) {
		if (KUtils.PROTECT_CLIENT) {
			Slot slot = getSlotAtPosition(mouseX, mouseY);

			if (!isRepairing() && getSlotAtPosition(mouseX, mouseY) != null
					&& getSlotAtPosition(mouseX, mouseY).getHasStack() && isCtrlKeyDown()
					&& getSlotAtPosition(mouseX, mouseY).getStack().getItem() instanceof RepairKit) {
				enableRepairing(getSlotAtPosition(mouseX, mouseY));
			} else if (isRepairing() && isCtrlKeyDown()) {
				disableRepairing();
			} else if (isRepairing()) {
				if (slot != null && slot.getHasStack()) {
					System.out.println("Sent: " + slot.getSlotIndex());
					DACNetwork.sendToServer(EnumSPackets.Repair, repairingPercent, repairingType, slot.getSlotIndex(),
							repairKit);
					return;
				}
			} else if (getSlotAtPosition(mouseX, mouseY) != null && getSlotAtPosition(mouseX, mouseY).getHasStack()
					&& getSlotAtPosition(mouseX, mouseY).getStack().getItem() instanceof Money
					&& (isCtrlKeyDown() || isShiftKeyDown())) {
				if (!(isCtrlKeyDown() && isShiftKeyDown())) {
					final Money clickedItem = (Money) getSlotAtPosition(mouseX, mouseY).getStack().getItem();
					DACNetwork.sendToServer(EnumSPackets.MoneyClick, isShiftKeyDown(), isCtrlKeyDown(),
							clickedItem.getValueInOrenes());
				}
			} else {
				super.mouseClicked(mouseX, mouseY, mouseButton);
			}
		}
	}

	private final void enableRepairing(final Slot slot) {
		if (KUtils.PROTECT_CLIENT) {

			repairing = true;
			repairKit = slot.getStack();
			repairKitSlot = slot.getSlotIndex();
			repairingType = ((RepairKit) slot.getStack().getItem()).getType();
			repairingPercent = (int) ((RepairKit) slot.getStack().getItem()).getRepairPercent();
		}
	}

	private final boolean isRepairing() {
		return repairing;
	}

	public final void disableRepairing() {
		if (KUtils.PROTECT_CLIENT) {
			repairing = false;
			repairKitSlot = -1;
			repairingType = null;
			repairingPercent = 0;
		}
	}

	private final Slot getSlotAtPosition(final int mouseX, final int mouseY) {
		if (KUtils.PROTECT_CLIENT) {
			for (int k = 0; k < this.inventorySlots.inventorySlots.size(); ++k) {
				final Slot slot = (Slot) this.inventorySlots.inventorySlots.get(k);

				if (this.isMouseOverSlot(slot, mouseX, mouseY))
					return slot;
			}
		}
		return null;
	}

	private final String getTotalBlockingPercent() {
		if (KUtils.PROTECT_CLIENT) {
			float r = 0;

			final DAPlayerData dap = DADataManager.getPlayer(player);
			final PlayerEffect resistance = dap.effects.get(EnumEffect.Resistance);

			if (resistance.isActive()) {
				r = resistance.getEfficiency() * 100;
			} else {
				for (int i = 0; i < 4; i++) {
					if (player.getCurrentArmor(i) != null && player.getCurrentArmor(i).getItem() instanceof Armor) {
						r += ((Armor) player.getCurrentArmor(i).getItem()).getBlockingPercent();
					}
				}
			}

			return String.format("%.1f", r) + "%";
		}
		return KString.EMPTY;
	}
}
