package keelfy.api.client;

import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glScalef;
import static org.lwjgl.opengl.GL11.glTranslatef;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

/**
 * @author keelfy
 */
public class GuiUtil {
	
	private static final Minecraft mc = Minecraft.getMinecraft();
	private static final RenderManager renderManager = RenderManager.instance;
	
	/**
	 * @param par1 - posX on screen 
	 * @param par2 - posY on screen
	 * @param par3 - posX on texture
	 * @param par4 - posY on texture 
	 * @param par5 - allocation size by X
	 * @param par6 - allocation size by Y
	 */
	@SideOnly(Side.CLIENT)
	public static void drawTexturedModalRect(int par1, int par2, int par3, int par4, int par5, int par6) {
		glPushMatrix();
        float f = 0.00390625F;
        float f1 = 0.00390625F;
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(par1 + 0, par2 + par6, 0, (par3 + 0) * f, (par4 + par6) * f1);
        tessellator.addVertexWithUV(par1 + par5, par2 + par6, 0, (par3 + par5) * f, (par4 + par6) * f1);
        tessellator.addVertexWithUV(par1 + par5, par2 + 0, 0, (par3 + par5) * f, (par4 + 0) * f1);
        tessellator.addVertexWithUV(par1 + 0, par2 + 0, 0, (par3 + 0) * f, (par4 + 0) * f1);
        tessellator.draw();
        glPopMatrix();
    }
	
	@SideOnly(Side.CLIENT)
	public static void drawDrawFullscreenImage() {
		glPushMatrix();
		ScaledResolution sr = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(0.0D, sr.getScaledHeight(), 0, 0.0D, 1.0D);
        tessellator.addVertexWithUV(sr.getScaledWidth(), sr.getScaledHeight(), 0, 1.0D, 1.0D);
        tessellator.addVertexWithUV(sr.getScaledWidth(), 0.0D, 0, 1.0D, 0.0D);
        tessellator.addVertexWithUV(0.0D, 0.0D, 0, 0.0D, 0.0D);
        tessellator.draw();
        glPopMatrix();
    }
	
	@SideOnly(Side.CLIENT)
	public static void drawTexturedModalRectZ(int par1, int par2, int par3, int par4, int par5, int par6, float zLevel) {
		glPushMatrix();
        float f = 0.00390625F;
        float f1 = 0.00390625F;
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(par1 + 0, par2 + par6, zLevel, (par3 + 0) * f, (par4 + par6) * f1);
        tessellator.addVertexWithUV(par1 + par5, par2 + par6, zLevel, (par3 + par5) * f, (par4 + par6) * f1);
        tessellator.addVertexWithUV(par1 + par5, par2 + 0, zLevel, (par3 + par5) * f, (par4 + 0) * f1);
        tessellator.addVertexWithUV(par1 + 0, par2 + 0, zLevel, (par3 + 0) * f, (par4 + 0) * f1);
        tessellator.draw();
        glPopMatrix();
    }
	
	/**
	 * Draws ItemStack with effects into gui
	 * @param itemRenderer - Instance of RenderItem.class
	 * @param itemstack - ItemStack to render
	 * @param x - X position on Screen
	 * @param y - Y position on Screen
	 */
	@SideOnly(Side.CLIENT)
	public static void drawItemStack(RenderItem itemRenderer, ItemStack itemstack, int x, int y, float ticks) {
		if(itemstack != null) {
			float f1 = itemstack.animationsToGo - ticks;
			if(f1 > 0.0F) {
				glPushMatrix();
				float f2 = 1.0F + f1 / 5.0F;
	            glTranslatef(x + 8, y + 12, 0.0F);
	            glScalef(1.0F / f2, (f2 + 1.0F) / 2.0F, 1.0F);
	            glTranslatef((-(x + 8)), (-(y + 12)), 0.0F);
			}

			itemRenderer.renderItemAndEffectIntoGUI(mc.fontRenderer, mc.getTextureManager(), itemstack, x, y);
			if(f1 > 0.0F) glPopMatrix();
			itemRenderer.renderItemOverlayIntoGUI(mc.fontRenderer, mc.getTextureManager(), itemstack, x, y);
		}
	}

	private static FontRenderer getFontRendererFromRenderManager() {
		return renderManager.getFontRenderer();
	}

	/**
	 * Draws living label
	 * @param tileEntity - TileEntity of block
	 * @param text - Label that rendering
	 * @param visibleDistance - Visible distance
	 * @param x - X Position of label
	 * @param y - Y Position of label
	 * @param z - Z Position of label
	 * @param textWidth - Width of string
	 * @param lines - Number of lines
	 */
	@SideOnly(Side.CLIENT)
	public static void renderLabel(TileEntity tileEntity, String text, int visibleDistance, double x, double y, double z, int textWidth, int lines) {
		EntityLivingBase player = renderManager.livingPlayer;
		double distanceToEntity = tileEntity.getDistanceFrom(player.posX, player.posY, player.posZ);

		if (distanceToEntity <= visibleDistance) {
			FontRenderer fontRenderer = getFontRendererFromRenderManager();
			float var13 = 1f;
			float size = 0.016666668F * var13;
			glPushMatrix();
			glTranslatef((float) x + 0.5F, (float) y + 1.0F, (float) z + 0.5F);
			GL11.glNormal3f(0.0F, 1.0F, 0.0F);
			GL11.glRotatef(-renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
			GL11.glScalef(-size, -size, size);
			GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glDepthMask(false);
			GL11.glDisable(GL11.GL_DEPTH_TEST);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			Tessellator var15 = Tessellator.instance;
			byte topCoord = 0;
			int mysteryNumber = 120;

			GL11.glDisable(GL11.GL_TEXTURE_2D);
			var15.startDrawingQuads();
			int var17 = textWidth;
			var15.setColorRGBA_F(0.0F, 0.0F, 0.0F, 0.33F);

			double x1 = -var17 - 1;
			double x2 = var17 + 1;
			double y1 = -1 + topCoord;
			double y2 = 2 + 8 * lines + topCoord;
			var15.addVertex(x1, y1, 0.0D);
			var15.addVertex(x1, y2, 0.0D);
			var15.addVertex(x2, y2, 0.0D);
			var15.addVertex(x2, y1, 0.0D);
			var15.draw();

			GL11.glEnable(GL11.GL_TEXTURE_2D);
			int leftCoord = -textWidth;
			int grayColor = 553648127;
			fontRenderer.drawSplitString(text, leftCoord, topCoord, mysteryNumber, -1);
			GL11.glEnable(GL11.GL_DEPTH_TEST);
			GL11.glDepthMask(true);
			fontRenderer.drawSplitString(text, leftCoord, topCoord, mysteryNumber, -1);
			GL11.glEnable(GL11.GL_LIGHTING);
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glPopMatrix();
		}
	}
}
