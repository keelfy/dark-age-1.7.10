/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.client.gui;

import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkdata.items.StoryBook;
import keelfy.darkdata.items.strorybook.AbstractStoryBook;
import keelfy.darkdata.items.strorybook.SBPage;
import keelfyutils.KUtils;
import keelfyutils.client.KGL;
import keelfyutils.client.KGui;
import keelfyutils.str.KString;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

/**
 * @author keelfy
 */
@SideOnly(Side.CLIENT)
public final class DAStoryBookGui extends GuiScreen {

	private static final ResourceLocation bookGuiTextures = new ResourceLocation("textures/gui/book.png");
	private final EntityPlayer player;
	private boolean bookIsModified;
	private boolean bookGettingSigned;
	private int updateCount;
	private final int bookImageWidth = 192;
	private final int bookImageHeight = 192;
	private int bookTotalPages = 1;
	private int currPage;
	private List<SBPage> bookPages;
	private String bookTitle = KString.EMPTY;
	private DAStoryBookGui.NextPageButton buttonNextPage;
	private DAStoryBookGui.NextPageButton buttonPreviousPage;
	private GuiButton buttonDone;
	private GuiButton buttonSign;
	private GuiButton buttonFinalize;
	private GuiButton buttonCancel;

	public DAStoryBookGui() {
		this.player = Minecraft.getMinecraft().thePlayer;

		if (KUtils.PROTECT_CLIENT) {
			final ItemStack bookStack = player.getCurrentEquippedItem();

			if (bookStack == null) {
				mc.displayGuiScreen(null);
				mc.setIngameFocus();
				return;
			} else if (!(bookStack.getItem() instanceof StoryBook) || !bookStack.hasTagCompound() || !bookStack.getTagCompound().hasKey("pages")) {
				mc.displayGuiScreen(null);
				mc.setIngameFocus();
				return;
			}

			bookPages = AbstractStoryBook.readPagesFromNBT(bookStack.getTagCompound());

			if (this.bookPages != null) {
				this.bookTotalPages = this.bookPages.size();

				if (this.bookTotalPages < 1) {
					this.bookTotalPages = 1;
				}
			}
		}
	}

	@Override
	public final void updateScreen() {
		if (KUtils.PROTECT_CLIENT) {
			super.updateScreen();
			++this.updateCount;
		}
	}

	@Override
	public final void initGui() {
		if (KUtils.PROTECT_CLIENT) {
			this.buttonList.clear();
			Keyboard.enableRepeatEvents(true);
			this.buttonList.add(this.buttonDone = new GuiButton(0, this.width / 2 - 100, 4 + this.bookImageHeight, 200, 20, I18n.format("gui.done", new Object[0])));

			final int i = (this.width - this.bookImageWidth) / 2;
			final byte b0 = 2;
			this.buttonList.add(this.buttonNextPage = new DAStoryBookGui.NextPageButton(1, i + 120, b0 + 154, true));
			this.buttonList.add(this.buttonPreviousPage = new DAStoryBookGui.NextPageButton(2, i + 38, b0 + 154, false));
			this.updateButtons();
		}
	}

	@Override
	public final void onGuiClosed() {}

	private final void updateButtons() {
		if (KUtils.PROTECT_CLIENT) {
			this.buttonNextPage.visible = !this.bookGettingSigned && (this.currPage < this.bookTotalPages - 1);
			this.buttonPreviousPage.visible = !this.bookGettingSigned && this.currPage > 0;
			this.buttonDone.visible = !this.bookGettingSigned;
		}
	}

	@Override
	protected final void actionPerformed(final GuiButton button) {
		if (KUtils.PROTECT_CLIENT) {
			if (button.enabled) {
				if (button.id == 0) {
					KGui.closeGui();
				} else if (button.id == 1) {
					if (this.currPage < this.bookTotalPages - 1) {
						++this.currPage;
					}
				} else if (button.id == 2) {
					if (this.currPage > 0) {
						--this.currPage;
					}
				}
				this.updateButtons();
			}
		}
	}

	@Override
	public final void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
		if (KUtils.PROTECT_CLIENT) {
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			KGL.bindTexture(mc, bookGuiTextures);
			final int k = (this.width - this.bookImageWidth) / 2;
			final byte b0 = 2;
			this.drawTexturedModalRect(k, b0, 0, 0, this.bookImageWidth, this.bookImageHeight);
			String s;
			String s1;
			int l;

			s = I18n.format("book.pageIndicator", new Object[] { Integer.valueOf(this.currPage + 1), Integer.valueOf(this.bookTotalPages) });
			s1 = KString.EMPTY;

			if (this.bookPages != null && this.currPage >= 0 && this.currPage < this.bookPages.size()) {
				s1 = bookPages.get(currPage).asString();
			}

			l = this.fontRendererObj.getStringWidth(s);
			this.fontRendererObj.drawString(s, k - l + this.bookImageWidth - 44, b0 + 16, 0);
			this.fontRendererObj.drawSplitString(s1, k + 36, b0 + 16 + 16, 116, 0);

			super.drawScreen(mouseX, mouseY, partialTicks);
		}
	}

	@SideOnly(Side.CLIENT)
	static final class NextPageButton extends GuiButton {
		private boolean direction;

		public NextPageButton(final int id, final int posX, final int posY, final boolean direction) {
			super(id, posX, posY, 23, 13, KString.EMPTY);

			if (KUtils.PROTECT_CLIENT) {
				this.direction = direction;
			}
		}

		@Override
		public final void drawButton(final Minecraft mc, final int mouseX, final int mouseY) {
			if (KUtils.PROTECT_CLIENT) {
				if (this.visible) {
					final boolean flag = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					KGL.bindTexture(mc, bookGuiTextures);
					int k = 0;
					int l = 192;

					if (flag) {
						k += 23;
					}

					if (!this.direction) {
						l += 13;
					}

					this.drawTexturedModalRect(this.xPosition, this.yPosition, k, l, 23, 13);
				}
			}
		}
	}
}
