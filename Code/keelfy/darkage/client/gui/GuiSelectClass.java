package keelfy.darkage.client.gui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.api.client.GuiUtil;
import keelfy.api.network.PacketDispatcher;
import keelfy.darkage.entity.player.DAPlayer;
import keelfy.darkage.entity.player.PlayerClass;
import keelfy.darkage.network.server.ClassMessage;
import keelfy.darkage.util.DAUtil;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
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
	
	private GuiUtil helper;
	private GuiButton humanButton, witcherButton;
	
	public GuiSelectClass() {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			bg = new ResourceLocation(DAUtil.MODID + ":textures/gui/SELECT_CLASS_BG.png");
			witcherbutton = new ResourceLocation(DAUtil.MODID + ":textures/gui/WITCHER_ACTIVE.png");
			witcherbutton_idle = new ResourceLocation(DAUtil.MODID + ":textures/gui/WITCHER_IDLE.png");
			humanbutton = new ResourceLocation(DAUtil.MODID + ":textures/gui/HUMAN_ACTIVE.png");
			humanbutton_idle = new ResourceLocation(DAUtil.MODID + ":textures/gui/HUMAN_IDLE.png");
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
    	if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
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
    	if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
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
			    		PacketDispatcher.getInstance().sendToServer(new ClassMessage(PlayerClass.HUMAN.ordinal()));
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
			    		PacketDispatcher.getInstance().sendToServer(new ClassMessage(PlayerClass.WITCHER.ordinal()));
			    		mc.displayGuiScreen(null);
			    	}
		    	}
	    	}
    	}
    }
}