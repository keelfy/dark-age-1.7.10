package noppes.npcs.client.gui;

import net.minecraft.client.gui.GuiButton;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.SubGuiInterface;
import noppes.npcs.constants.EnumOptionType;
import noppes.npcs.controllers.Dialog;
import noppes.npcs.controllers.DialogOption;

public class SubGuiNpcDialogOptions extends SubGuiInterface {
	private Dialog dialog;

	public SubGuiNpcDialogOptions(final Dialog dialog) {
		this.dialog = dialog;
		setBackground("menubg.png");
		xSize = 256;
		ySize = 216;
		closeOnEsc = true;
	}

	@Override
	public void initGui() {
		super.initGui();
		this.addLabel(new GuiNpcLabel(66, "dialog.options", guiLeft, guiTop + 4));
		this.getLabel(66).center(xSize);

		for (int i = 0; i < 6; i++) {
			String optionString = "";
			DialogOption option = dialog.options.get(i);
			if (option != null && option.optionType != EnumOptionType.Disabled) {
				optionString += option.title;
			}

			this.addLabel(new GuiNpcLabel(i + 10, i + 1 + ": ", guiLeft + 4, guiTop + 16 + i * 32));
			this.addLabel(new GuiNpcLabel(i, optionString, guiLeft + 14, guiTop + 12 + i * 32));
			this.addButton(new GuiNpcButton(i, guiLeft + 13, guiTop + 21 + i * 32, 60, 20, "selectServer.edit"));

		}

		this.addButton(new GuiNpcButton(66, guiLeft + 82, guiTop + 194, 98, 20, "gui.done"));

	}

	@Override
	protected void actionPerformed(final GuiButton guibutton) {
		int id = guibutton.id;
		if (id < 6) {
			if (!dialog.options.containsKey(id)) {
				dialog.options.put(id, new DialogOption());
			}
			this.setSubGui(new SubGuiNpcDialogOption(dialog.options.get(id)));
		}
		if (id == 66) {
			close();
		}
	}
}
