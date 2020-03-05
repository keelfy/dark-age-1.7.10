package noppes.npcs.client.gui;

import net.minecraft.client.gui.GuiButton;
import noppes.npcs.NoppesStringUtils;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcTextArea;
import noppes.npcs.client.gui.util.SubGuiInterface;

public class SubGuiNpcTextArea extends SubGuiInterface{
	public String text;

	public SubGuiNpcTextArea(String text){
		this.text = text;
		setBackground("menubg.png");
		xSize = 256;
		ySize = 216;
		closeOnEsc = true;
	}
	
	@Override
	public void initGui(){
		super.initGui();
		this.addTextField(new GuiNpcTextArea(2, this, this.fontRendererObj, guiLeft + 4, guiTop + 4, 186, 208, text));

		
		this.buttonList.add(new GuiNpcButton(102, guiLeft + 196, guiTop + 20, 56, 20, "gui.clear"));
		this.buttonList.add(new GuiNpcButton(101, guiLeft + 196, guiTop + 43, 56, 20, "gui.paste"));
		this.buttonList.add(new GuiNpcButton(100, guiLeft + 196, guiTop + 66, 56, 20, "gui.copy"));

		this.buttonList.add(new GuiNpcButton(0, guiLeft + 196, guiTop + 160, 56, 20, "gui.close"));
	}


	@Override
    public void close(){
		text = getTextField(2).getText();
		super.close();
	}

	@Override
	public void buttonEvent(GuiButton guibutton) {
		int id = guibutton.id;
		if (id == 100) {
			NoppesStringUtils.setClipboardContents(getTextField(2).getText());
		}
		if (id == 101) {
			getTextField(2).setText(NoppesStringUtils.getClipboardContents());
		}
		if (id == 102) {
			getTextField(2).setText("");
		}
		if(id == 0){
			close();
		}
	}
}
