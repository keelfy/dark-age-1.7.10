/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.client.gui;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkage.constants.EnumPlayerClass;
import keelfy.darkage.constants.EnumServerPacket;
import keelfy.darkage.network.ClientPacketHandler;
import keelfy.darkage.utils.DAUtils;
import keelfytools.KeelfyUtils;
import keelfytools.gui.KeelfyUtilsGui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;

/**
 * @author keelfy
 */
@SideOnly(Side.CLIENT)
public class GuiSelectClass extends GuiScreen {
	
	private ResourceLocation bg;
	private ResourceLocation witcherbutton;
	private ResourceLocation witcherbutton_idle;
	private ResourceLocation humanbutton;
	private ResourceLocation humanbutton_idle;
	
	private KeelfyUtilsGui helper;
	private GuiButton humanButton, witcherButton;
	
	public GuiSelectClass() {
		if(KeelfyUtils.isClientSide()) {
			bg = new ResourceLocation(DAUtils.MODID + ":textures/gui/SELECT_CLASS_BG.png");
			witcherbutton = new ResourceLocation(DAUtils.MODID + ":textures/gui/WITCHER_ACTIVE.png");
			witcherbutton_idle = new ResourceLocation(DAUtils.MODID + ":textures/gui/WITCHER_IDLE.png");
			humanbutton = new ResourceLocation(DAUtils.MODID + ":textures/gui/HUMAN_ACTIVE.png");
			humanbutton_idle = new ResourceLocation(DAUtils.MODID + ":textures/gui/HUMAN_IDLE.png");
		}
	}
	
	@Override
	public void initGui() {}
    
    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
    
    @Override
    public void drawScreen(int i, int j, float f) {
    	if(KeelfyUtils.isClientSide()) {
	    	drawDefaultBackground();
	    	
	    	GL11.glDisable(GL11.GL_DEPTH_TEST);
	        GL11.glDepthMask(false);
	        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	        GL11.glDisable(GL11.GL_ALPHA_TEST);
	        mc.getTextureManager().bindTexture(bg);
	        helper.drawDrawFullscreenImage();
	        GL11.glDepthMask(true);
	        GL11.glEnable(GL11.GL_DEPTH_TEST);
	        GL11.glEnable(GL11.GL_ALPHA_TEST);	
	        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	        
	        drawClasses(i, j);
	        
	        super.drawScreen(i, j, f);
    	}
    }

    private void drawClasses(float mouseX, float mouseY) {
    	if(KeelfyUtils.isClientSide()) {
    		ScaledResolution sr = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
	    	int width = sr.getScaledWidth();
	    	int height = sr.getScaledHeight();
	    	
	    	if (mouseX > 0 && mouseX < width / 2) {
		    	if (mouseY > 0 && mouseY < height) {
			    	mc.getTextureManager().bindTexture(humanbutton);
		    	} else {
		    		mc.getTextureManager().bindTexture(humanbutton_idle);
		    	}
	    	} else {
	    		mc.getTextureManager().bindTexture(humanbutton_idle);
	    	}
	    	
	    	GL11.glEnable(GL11.GL_BLEND);
	    	OpenGlHelper.glBlendFunc(770, 771, 1, 0);
	    	GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	    	Tessellator tessellator = Tessellator.instance;
	    	GL11.glColor4f(1f, 1f, 1f, 1f);
	    	tessellator.startDrawingQuads();
	    	tessellator.addVertexWithUV(width / 2 - 210, height / 2 + 125, 0, 0, 1);
	    	tessellator.addVertexWithUV(width / 2 + 10, height / 2 + 125, 0, 1, 1);
	    	tessellator.addVertexWithUV(width  / 2 + 10, height / 2 - 145, 0, 1, 0);
	    	tessellator.addVertexWithUV(width  / 2 - 210, height / 2 - 145, 0, 0, 0);
	    	tessellator.draw();
	    	
	    	if (mouseX < width && mouseX > width / 2) {
		    	if (mouseY > 0 && mouseY < height) {
		    		mc.getTextureManager().bindTexture(witcherbutton);
		    	} else {
		    		mc.getTextureManager().bindTexture(witcherbutton_idle);
		    	}
	    	} else {
	    		mc.getTextureManager().bindTexture(witcherbutton_idle);
	    	}
	    	
	    	GL11.glEnable(GL11.GL_BLEND);
	    	OpenGlHelper.glBlendFunc(770, 771, 1, 0);
	    	GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	    	GL11.glColor4f(1f, 1f, 1f, 1f);
	    	tessellator.startDrawingQuads();
	    	tessellator.addVertexWithUV(width / 2 - 10, height / 2 + 125, 0, 0, 1);
	    	tessellator.addVertexWithUV(width / 2 + 210, height / 2 + 125, 0, 1, 1);
	    	tessellator.addVertexWithUV(width  / 2 + 210, height / 2 - 145, 0, 1, 0);
	    	tessellator.addVertexWithUV(width  / 2 - 10, height / 2 - 145, 0, 0, 0);
	    	tessellator.draw();
	    	
	    	if (mouseX > 0 && mouseX < width / 2) {
		    	if (mouseY > 0 && mouseY < height) {
			    	
			    	List list = new ArrayList();
		    		list.add("    для игры в компании   ");
		    		list.add("мирное или боевое развитие");
		    		list.add("     социальная игра      ");
		    		list.add("      свобода выбора      ");
		    		this.drawHoveringText(list, (int)mouseX, (int)mouseY, mc.fontRenderer);
			    	if(Mouse.getEventButton() == 0) {
			    		ClientPacketHandler.sendToServer(EnumServerPacket.CLASS, EnumPlayerClass.HUMAN.ordinal());
			    		mc.displayGuiScreen(null);
			    	}
		    	}
		    }
	    	
	    	if (mouseX < width && mouseX > width / 2) {
		    	if (mouseY > 0 && mouseY < height) {
			    
		    		List list = new ArrayList();
		    		list.add("          для игры одному         ");
		    		list.add("           отличный боец          ");
		    		list.add("  мастерский охотник на монстров  ");
		    		list.add("использует знаки, эликсиры и масла ");
			    	this.drawHoveringText(list, (int)mouseX, (int)mouseY, mc.fontRenderer);
			    	if(Mouse.getEventButton() == 0) {
			    		ClientPacketHandler.sendToServer(EnumServerPacket.CLASS, EnumPlayerClass.WITCHER.ordinal());
			    		mc.displayGuiScreen(null);
			    	}
		    	}
	    	}
    	}
    }
}