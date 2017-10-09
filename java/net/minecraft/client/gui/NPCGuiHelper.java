package net.minecraft.client.gui;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.util.ResourceLocation;
import noppes.npcs.client.EntityUtil;

public class NPCGuiHelper {
	public static List<GuiButton> getButtonList(GuiScreen gui){
		return gui.buttonList;
	}

	public static void clickSound() {
        Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));    	
	}
}
