/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.client.gui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkcore.common.player.DADataManager;
import keelfy.darkdata.client.DAResources.Texture;
import keelfy.darkdata.constants.EnumTexturePath;
import keelfyutils.KUtils;
import keelfyutils.client.KGL;
import keelfyutils.client.KGui;
import keelfyutils.str.Brush;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

/**
 * @author keelfy
 */
@SideOnly(Side.CLIENT)
public abstract class DAContainerGui extends GuiContainer {

	protected Slot theSlot;
	protected Slot clickedSlot;
	protected boolean isRightMouseClick;
	protected ItemStack draggedStack;
	protected int xDisplayPosition;
	protected int yDisplayPosition;
	protected Slot returningStackDestSlot;
	protected long returningStackTime;
	protected ItemStack returningStack;
	protected int dragSplittingLimit;
	protected int dragSplittingRemnant;

	public static final ResourceLocation backgroundTexture = Texture.get(EnumTexturePath.Inventory, "invbackground");

	public DAContainerGui(final Container container) {
		super(container);

		if (KUtils.PROTECT_CLIENT) {
			this.inventorySlots = container;
		}
	}

	protected boolean isLightingOfActiveSlotEnabled() {
		return true;
	}

	@Override
	public final void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
		if (KUtils.PROTECT_CLIENT) {
			final int k = this.guiLeft;
			final int l = this.guiTop;

			if (isHasBlackBackground()) {
				GL11.glPushMatrix();
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				KGL.bindTexture(backgroundTexture);
				KGL.drawFullscreenTexturedRectZ(super.width, super.height, -1);
				GL11.glPopMatrix();
			}

			this.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
			RenderHelper.disableStandardItemLighting();
			GL11.glDisable(GL11.GL_DEPTH_TEST);
			GL11.glPushMatrix();
			GL11.glTranslatef(k, l, 0.0F);
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			this.theSlot = null;
			final short short1 = 240;
			final short short2 = 240;
			OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, short1 / 1.0F, short2 / 1.0F);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			int k1;
			RenderHelper.enableStandardItemLighting();
			for (int i1 = 0; i1 < this.inventorySlots.inventorySlots.size(); ++i1) {
				final Slot slot = (Slot) this.inventorySlots.inventorySlots.get(i1);
				this.drawSlot(slot);

				if (this.isMouseOverSlot(slot, mouseX, mouseY)) {
					this.theSlot = slot;
					if (isLightingOfActiveSlotEnabled()) {
						final int j1 = slot.xDisplayPosition;
						k1 = slot.yDisplayPosition;
						GL11.glColorMask(true, true, true, false);
						this.drawGradientRect(j1, k1, j1 + 16, k1 + 16, -2130706433, -2130706433);
						GL11.glColorMask(true, true, true, true);
					}
				}
			}
			RenderHelper.disableStandardItemLighting();
			this.drawGuiContainerForegroundLayer(mouseX, mouseY);
			GL11.glEnable(GL11.GL_LIGHTING);
			final InventoryPlayer inventoryplayer = this.mc.thePlayer.inventory;
			ItemStack itemstack = this.draggedStack == null ? inventoryplayer.getItemStack() : this.draggedStack;

			if (itemstack != null) {
				final byte b0 = 8;
				k1 = this.draggedStack == null ? 8 : 16;
				String s = null;

				if (this.draggedStack != null && this.isRightMouseClick) {
					itemstack = itemstack.copy();
					itemstack.stackSize = MathHelper.ceiling_float_int(itemstack.stackSize / 2.0F);
				} else if (this.field_147007_t && this.field_147008_s.size() > 1) {
					itemstack = itemstack.copy();
					itemstack.stackSize = this.dragSplittingRemnant;

					if (itemstack.stackSize == 0) {
						s = String.valueOf(Brush.YELLOW + String.valueOf(0));
					}
				}

				KGui.drawItemStack(itemRender, itemstack, mouseX - k - b0, mouseY - l - k1, 0, s);
			}

			if (this.returningStack != null) {
				float f1 = (Minecraft.getSystemTime() - this.returningStackTime) / 100.0F;

				if (f1 >= 1.0F) {
					f1 = 1.0F;
					this.returningStack = null;
				}

				k1 = this.returningStackDestSlot.xDisplayPosition - this.xDisplayPosition;
				final int j2 = this.returningStackDestSlot.yDisplayPosition - this.yDisplayPosition;
				final int l1 = this.xDisplayPosition + (int) (k1 * f1);
				final int i2 = this.yDisplayPosition + (int) (j2 * f1);
				KGui.drawItemStack(itemRender, this.returningStack, l1, i2, 2, (String) null);
			}

			GL11.glPopMatrix();

			if (inventoryplayer.getItemStack() == null && this.theSlot != null && this.theSlot.getHasStack()) {
				final ItemStack itemstack1 = this.theSlot.getStack();
				this.renderToolTip(itemstack1, mouseX, mouseY);
			}

			GL11.glEnable(GL11.GL_DEPTH_TEST);
			RenderHelper.enableStandardItemLighting();
		}
	}

	@Override
	protected void renderToolTip(final ItemStack itemstack, final int mouseX, final int mouseY) {
		List list = getTooltip(itemstack, this.mc.thePlayer, false);

		for (int i = 0; i < list.size(); ++i) {
			if (i == 0) {
				list.set(i, itemstack.getRarity().rarityColor + (String) list.get(i));
			} else {
				list.set(i, EnumChatFormatting.GRAY + (String) list.get(i));
			}
		}

		FontRenderer font = itemstack.getItem().getFontRenderer(itemstack);
		drawHoveringText(list, mouseX, mouseY, (font == null ? fontRendererObj : font));
	}

	public List getTooltip(final ItemStack itemstack, final EntityPlayer player, final boolean advancedTooltip) {
		ArrayList<String> arraylist = new ArrayList();
		String s = itemstack.getDisplayName();

		if (itemstack.hasDisplayName()) {
			s = Brush.ITALIC + s + Brush.RESET;
		}

		int i;

		arraylist.add(s);
		itemstack.getItem().addInformation(itemstack, player, arraylist, advancedTooltip);

		if (itemstack.hasTagCompound()) {
			NBTTagList nbttaglist = itemstack.getEnchantmentTagList();

			if (nbttaglist != null) {
				for (i = 0; i < nbttaglist.tagCount(); ++i) {
					short short1 = nbttaglist.getCompoundTagAt(i).getShort("id");
					short short2 = nbttaglist.getCompoundTagAt(i).getShort("lvl");

					if (Enchantment.enchantmentsList[short1] != null) {
						arraylist.add(Enchantment.enchantmentsList[short1].getTranslatedName(short2));
					}
				}
			}

			if (itemstack.getTagCompound().hasKey("display", 10)) {
				NBTTagCompound nbttagcompound = itemstack.getTagCompound().getCompoundTag("display");

				if (nbttagcompound.hasKey("color", 3)) {
					if (advancedTooltip) {
						arraylist.add(
								"Color: #" + Integer.toHexString(nbttagcompound.getInteger("color")).toUpperCase());
					} else {
						arraylist.add(Brush.ITALIC + StatCollector.translateToLocal("item.dyed"));
					}
				}

				if (nbttagcompound.func_150299_b("Lore") == 9) {
					NBTTagList nbttaglist1 = nbttagcompound.getTagList("Lore", 8);

					if (nbttaglist1.tagCount() > 0) {
						for (int j = 0; j < nbttaglist1.tagCount(); ++j) {
							arraylist.add(Brush.PURPLE + "" + Brush.ITALIC + nbttaglist1.getStringTagAt(j));
						}
					}
				}
			}
		}

		return arraylist;
	}

	protected void drawSlot(final Slot slot) {
		if (KUtils.PROTECT_CLIENT) {
			final int i = slot.xDisplayPosition;
			final int j = slot.yDisplayPosition;
			ItemStack itemstack = slot.getStack();
			boolean flag = false;
			boolean flag1 = slot == this.clickedSlot && this.draggedStack != null && !this.isRightMouseClick;
			final ItemStack itemstack1 = this.mc.thePlayer.inventory.getItemStack();
			String s = null;

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
					drawRect(i, j, i + 16, j + 16, -2130706433);
				}

				GL11.glEnable(GL11.GL_DEPTH_TEST);
				itemRender.renderItemAndEffectIntoGUI(this.fontRendererObj, this.mc.getTextureManager(), itemstack, i,
						j);
				itemRender.renderItemOverlayIntoGUI(this.fontRendererObj, this.mc.getTextureManager(), itemstack, i, j,
						s);
			}

			itemRender.zLevel = 0.0F;
			this.zLevel = 0.0F;
		}
	}

	protected void updateDragSplitting() {
		if (KUtils.PROTECT_CLIENT) {
			final ItemStack itemstack = this.mc.thePlayer.inventory.getItemStack();

			if (itemstack != null && this.field_147007_t) {
				this.dragSplittingRemnant = itemstack.stackSize;
				ItemStack itemstack1;
				int i;

				for (final Iterator iterator = this.field_147008_s.iterator(); iterator
						.hasNext(); this.dragSplittingRemnant -= itemstack1.stackSize - i) {
					final Slot slot = (Slot) iterator.next();
					itemstack1 = itemstack.copy();
					i = slot.getStack() == null ? 0 : slot.getStack().stackSize;
					Container.func_94525_a(this.field_147008_s, this.dragSplittingLimit, itemstack1, i);

					if (itemstack1.stackSize > itemstack1.getMaxStackSize()) {
						itemstack1.stackSize = itemstack1.getMaxStackSize();
					}

					if (itemstack1.stackSize > slot.getSlotStackLimit()) {
						itemstack1.stackSize = slot.getSlotStackLimit();
					}
				}
			}
		}
	}

	@Override
	public void onGuiClosed() {
		super.onGuiClosed();

		if (draggedStack != null) {
			DADataManager.getPlayer(mc.thePlayer).inventory.addItemStackToInventory(draggedStack);
			draggedStack = null;
		}
	}

	protected boolean isHasBlackBackground() {
		return false;
	}

	protected final boolean isMouseOverSlot(final Slot slot, final int mouseX, final int mouseY) {
		return this.func_146978_c(slot.xDisplayPosition, slot.yDisplayPosition, 16, 16, mouseX, mouseY);
	}
}
