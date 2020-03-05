package noppes.npcs.client.gui.player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import org.lwjgl.opengl.GL11;

import keelfy.darkdata.client.DAFontHandler;
import keelfy.darkdata.client.gui.tabbed.InventoryTabQuests;
import keelfy.darkdata.client.gui.tabbed.TabRegistry;
import keelfy.darkdata.constants.EnumFont;
import keelfyutils.str.Brush;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.NPCGuiHelper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import noppes.npcs.NoppesUtilPlayer;
import noppes.npcs.QuestLogData;
import noppes.npcs.client.CustomNpcResourceListener;
import noppes.npcs.client.TextBlockClient;
import noppes.npcs.client.gui.util.GuiButtonNextPage;
import noppes.npcs.client.gui.util.GuiCustomScroll;
import noppes.npcs.client.gui.util.GuiMenuSideButton;
import noppes.npcs.client.gui.util.GuiNPCInterface;
import noppes.npcs.client.gui.util.ICustomScrollListener;
import noppes.npcs.client.gui.util.IGuiData;
import noppes.npcs.client.gui.util.ITopButtonListener;
import noppes.npcs.constants.EnumPlayerPacket;
import noppes.npcs.controllers.PlayerQuestController;
import noppes.npcs.controllers.Quest;

public class GuiQuestLog extends GuiNPCInterface implements ITopButtonListener, ICustomScrollListener, IGuiData {

	private final ResourceLocation resource = new ResourceLocation("customnpcs", "textures/gui/standardbg.png");

	private GuiCustomScroll scroll;
	// private Map<Integer, GuiMenuSideButton> sideButtons = new HashMap();
	private static final LinkedList<GuiMenuSideButton> sideButtons = new LinkedList();
	private QuestLogData data = new QuestLogData();
	private boolean noQuests = false;

	protected Minecraft mc = Minecraft.getMinecraft();

	public GuiQuestLog() {
		xSize = 280;
		ySize = 180;
		NoppesUtilPlayer.sendData(EnumPlayerPacket.QuestLog);
		drawDefaultBackground = false;
	}

	@Override
	public void initGui() {
		super.initGui();
		sideButtons.clear();
		guiTop += 10;

		TabRegistry.addTabsToList(buttonList);
		TabRegistry.updateTabValues(guiLeft, guiTop, InventoryTabQuests.class);

		noQuests = false;

		if (data.categories.isEmpty()) {
			noQuests = true;
			return;
		}
		List<String> categories = new ArrayList();
		categories.addAll(data.categories.keySet());
		Collections.sort(categories, String.CASE_INSENSITIVE_ORDER);
		int i = 0;
		for (String category : categories) {
			if (data.selectedCategory.isEmpty()) {
				data.selectedCategory = category;
			}
			sideButtons.add(i, new GuiMenuSideButton(i, guiLeft - 69, this.guiTop + 2 + i * 21, 70, 22, category));
			i++;
		}

		sideButtons.get(categories.indexOf(data.selectedCategory)).active = true;

		if (scroll == null) {
			scroll = new GuiCustomScroll(this, 0);
		}

		scroll.setList(data.categories.get(data.selectedCategory));
		scroll.setSize(134, 174);
		scroll.guiLeft = guiLeft + 5;
		scroll.guiTop = guiTop + 15;
		addScroll(scroll);
	}

	private final void updateGuiData() {
		super.initGui();
		guiTop += 10;

		TabRegistry.addTabsToList(buttonList);
		TabRegistry.updateTabValues(guiLeft, guiTop, InventoryTabQuests.class);

		noQuests = false;

		if (data.categories.isEmpty()) {
			noQuests = true;
			return;
		}
		List<String> categories = new ArrayList();
		categories.addAll(data.categories.keySet());
		Collections.sort(categories, String.CASE_INSENSITIVE_ORDER);

		for (String category : categories) {
			if (data.selectedCategory.isEmpty()) {
				data.selectedCategory = category;
			}

			if (!data.selectedCategory.equals(category) && sideButtons.get(categories.indexOf(category)).active) {
				sideButtons.get(categories.indexOf(category)).active = false;
			}
		}

		sideButtons.get(categories.indexOf(data.selectedCategory)).active = true;

		if (scroll == null) {
			scroll = new GuiCustomScroll(this, 0);
		}

		scroll.setList(data.categories.get(data.selectedCategory));
		scroll.setSize(134, 174);
		scroll.guiLeft = guiLeft + 5;
		scroll.guiTop = guiTop + 15;
		addScroll(scroll);
	}

	@Override
	protected void actionPerformed(final GuiButton guibutton) {
		if (!(guibutton instanceof GuiButtonNextPage))
			return;
	}

	@Override
	public void drawScreen(final int i, final int j, final float f) {
		if (scroll != null) {
			scroll.visible = !noQuests;
		}
		drawDefaultBackground();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(resource);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, 252, 195);
		drawTexturedModalRect(guiLeft + 252, guiTop, 188, 0, 67, 195);
		super.drawScreen(i, j, f);

		if (noQuests) {
			mc.fontRenderer.drawString(StatCollector.translateToLocal("quest.noquests"), guiLeft + 84, guiTop + 80, CustomNpcResourceListener.DefaultTextColor);
			return;
		}

		LinkedList<GuiMenuSideButton> sideB = sideButtons;
		for (GuiMenuSideButton button : sideB.toArray(new GuiMenuSideButton[sideB.size()])) {
			button.drawButton(mc, i, j);
		}

		mc.fontRenderer.drawString(data.selectedCategory, guiLeft + 5, guiTop + 5, CustomNpcResourceListener.DefaultTextColor);

		if (!data.hasSelectedQuest())
			return;

		drawQuestText();

		Quest selectedQuest = null;
		Vector<Quest> activeQuests = PlayerQuestController.getActiveQuests(player);

		if (activeQuests != null) {
			for (Quest quest : activeQuests) {
				if (quest.title.equals(data.selectedQuest)) {
					selectedQuest = quest;
				}
			}
		}

		if (selectedQuest != null && selectedQuest.questInterface.isCompleted(player)) {
			String title = String.valueOf(Brush.YELLOW + StatCollector.translateToLocal("quest.objectives").replaceAll("<npcname>", selectedQuest.completerNpc));
			DAFontHandler.Instance.getFont(EnumFont.TrebuchetMS_18).drawString(title, guiLeft + 140, guiTop + 179, 0x000000);
		}

		GL11.glPushMatrix();
		GL11.glTranslatef(guiLeft + 148, guiTop, 0);
		GL11.glScalef(1.24f, 1.24f, 1.24f);
		fontRendererObj.drawString(data.selectedQuest, (130 - fontRendererObj.getStringWidth(data.selectedQuest)) / 2, 4, CustomNpcResourceListener.DefaultTextColor);
		GL11.glPopMatrix();
		drawHorizontalLine(guiLeft + 142, guiLeft + 312, guiTop + 17, +0xFF000000 + CustomNpcResourceListener.DefaultTextColor);
	}

	private void drawQuestText() {
		TextBlockClient block = new TextBlockClient(data.getQuestText(), 174, player);
		int yoffset = guiTop + 5;
		for (int i = 0; i < block.lines.size(); i++) {
			String text = block.lines.get(i).getFormattedText();
			fontRendererObj.drawString(text, guiLeft + 142, guiTop + 20 + (i * fontRendererObj.FONT_HEIGHT), CustomNpcResourceListener.DefaultTextColor);
		}
	}

	protected void drawGuiContainerBackgroundLayer(final float f, final int i, final int j) {}

	@Override
	public void mouseClicked(final int i, final int j, final int k) {
		super.mouseClicked(i, j, k);
		if (k == 0) {
			if (scroll != null) {
				scroll.mouseClicked(i, j, k);
			}

			for (GuiMenuSideButton button : sideButtons) {
				if (button.mousePressed(mc, i, j)) {
					sideButtonPressed(button);
				}
			}
		}
	}

	private void sideButtonPressed(final GuiMenuSideButton button) {
		if (button.active)
			return;
		NPCGuiHelper.clickSound();
		data.selectedCategory = button.displayString;
		data.selectedQuest = "";
		this.updateGuiData();
	}

	@Override
	public void customScrollClicked(final int i, final int j, final int k, final GuiCustomScroll scroll) {
		if (!scroll.hasSelected())
			return;
		data.selectedQuest = scroll.getSelected();
		initGui();
	}

	@Override
	public void keyTyped(final char c, final int i) {
		if (i == 1 || i == mc.gameSettings.keyBindInventory.getKeyCode()) // inventory
																			// key
		{
			mc.displayGuiScreen(null);
			mc.setIngameFocus();
		}
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	@Override
	public void setGuiData(final NBTTagCompound compound) {
		QuestLogData data = new QuestLogData();
		data.readNBT(compound);
		this.data = data;
		initGui();
	}

	@Override
	public void save() {

	}

}
