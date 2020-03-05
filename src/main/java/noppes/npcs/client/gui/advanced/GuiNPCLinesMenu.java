package noppes.npcs.client.gui.advanced;

import net.minecraft.client.gui.GuiButton;
import noppes.npcs.client.NoppesUtil;
import noppes.npcs.client.gui.GuiNPCLinesEdit;
import noppes.npcs.client.gui.util.GuiNPCInterface2;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.entity.EntityNPCInterface;

public class GuiNPCLinesMenu extends GuiNPCInterface2
{
    public GuiNPCLinesMenu(EntityNPCInterface npc)
    {
    	super(npc);
    }

    public void initGui()
    {
        super.initGui();
    	this.addButton(new GuiNpcButton(0, guiLeft + 85, guiTop + 20, "World Lines"));
    	this.addButton(new GuiNpcButton(1, guiLeft + 85, guiTop + 43, "Attack Lines"));
    	this.addButton(new GuiNpcButton(2, guiLeft + 85, guiTop + 66, "Interact Lines"));
    	this.addButton(new GuiNpcButton(5, guiLeft + 85, guiTop + 89, "Killed Lines"));
    	this.addButton(new GuiNpcButton(6, guiLeft + 85, guiTop + 112, "Kill Lines"));
        
    }

	protected void actionPerformed(GuiButton guibutton)
    {
		int id = guibutton.id;
        if(id == 0)
        {
        	NoppesUtil.openGUI(player, new GuiNPCLinesEdit(npc, npc.advanced.worldLines));
        }
        if(id == 1)
        {
        	NoppesUtil.openGUI(player, new GuiNPCLinesEdit(npc, npc.advanced.attackLines));
        }
        if(id == 2)
        {
        	NoppesUtil.openGUI(player, new GuiNPCLinesEdit(npc, npc.advanced.interactLines));
        }
        if(id == 5)
        {
        	NoppesUtil.openGUI(player, new GuiNPCLinesEdit(npc, npc.advanced.killedLines));
        }
        if(id == 6)
        {
        	NoppesUtil.openGUI(player, new GuiNPCLinesEdit(npc, npc.advanced.killLines));
        }
    }
	public void save() {
	}


}
