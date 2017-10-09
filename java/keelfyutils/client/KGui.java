package keelfyutils.client;

import static org.lwjgl.opengl.GL11.GL_ALPHA_TEST;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_MATERIAL;
import static org.lwjgl.opengl.GL11.GL_FLAT;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SMOOTH;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glScalef;
import static org.lwjgl.opengl.GL11.glShadeModel;
import static org.lwjgl.opengl.GL11.glTranslatef;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfyutils.KUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

/**
 * @author keelfy
 */
@SideOnly(Side.CLIENT)
public final class KGui {

	private static final Minecraft MC = KGL.mc();
	private static final RenderManager renderManager = RenderManager.instance;

	public static final void closeGui() {
		MC.displayGuiScreen(null);
		MC.setIngameFocus();
	}

	public static final void drawItemStack(final RenderItem itemRenderer, final ItemStack itemstack, final int x,
			final int y, final float ticks) {
		if (KUtils.PROTECT_CLIENT) {
			if (itemstack != null) {
				final float f1 = itemstack.animationsToGo - ticks;
				if (f1 > 0.0F) {
					glPushMatrix();
					final float f2 = 1.0F + f1 / 5.0F;
					glTranslatef(x + 8, y + 12, 0.0F);
					glScalef(1.0F / f2, (f2 + 1.0F) / 2.0F, 1.0F);
					glTranslatef((-(x + 8)), (-(y + 12)), 0.0F);
				}

				itemRenderer.renderItemAndEffectIntoGUI(MC.fontRenderer, MC.getTextureManager(), itemstack, x, y);
				if (f1 > 0.0F) {
					glPopMatrix();
				}
				itemRenderer.renderItemOverlayIntoGUI(MC.fontRenderer, MC.getTextureManager(), itemstack, x, y);
			}
		}
	}

	public static final void drawItemStack(final RenderItem itemRender, final ItemStack itemstack, final int x,
			final int y, final float z, final String altText) {
		if (KUtils.PROTECT_CLIENT) {
			glPushMatrix();
			glTranslatef(0.0F, 0.0F, 32.0F);
			final float posZ = 200F;
			itemRender.zLevel = z;
			FontRenderer font = null;

			if (itemstack != null) {
				font = itemstack.getItem().getFontRenderer(itemstack);
			}
			if (font == null) {
				font = MC.fontRenderer;
			}

			itemRender.renderItemAndEffectIntoGUI(font, MC.getTextureManager(), itemstack, x, y);
			itemRender.renderItemOverlayIntoGUI(font, MC.getTextureManager(), itemstack, x, y, altText);
			itemRender.zLevel = 0.0F;
			glPopMatrix();
		}
	}

	public static final void renderLabel(final TileEntity tileEntity, final String text, final int visibleDistance,
			final double x, final double y, final double z, final int textWidth, final int lines) {
		if (KUtils.PROTECT_CLIENT) {
			final EntityLivingBase player = renderManager.livingPlayer;
			final double distanceToEntity = tileEntity.getDistanceFrom(player.posX, player.posY, player.posZ);

			if (distanceToEntity <= visibleDistance) {
				final float size = 0.016666668F;
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
				final Tessellator tess = Tessellator.instance;
				final byte topCoord = 0;
				final int mysteryNumber = 120;

				GL11.glDisable(GL11.GL_TEXTURE_2D);
				tess.startDrawingQuads();
				final int var17 = textWidth;
				tess.setColorRGBA_F(0.0F, 0.0F, 0.0F, 0.33F);

				final double x1 = -var17 - 1;
				final double x2 = var17 + 1;
				final double y1 = -1 + topCoord;
				final double y2 = 2 + 8 * lines + topCoord;
				tess.addVertex(x1, y1, 0.0D);
				tess.addVertex(x1, y2, 0.0D);
				tess.addVertex(x2, y2, 0.0D);
				tess.addVertex(x2, y1, 0.0D);
				tess.draw();

				GL11.glEnable(GL11.GL_TEXTURE_2D);
				final int leftCoord = -textWidth;
				final int grayColor = 553648127;
				MC.fontRenderer.drawSplitString(text, leftCoord, topCoord, mysteryNumber, -1);
				GL11.glEnable(GL11.GL_DEPTH_TEST);
				GL11.glDepthMask(true);
				MC.fontRenderer.drawSplitString(text, leftCoord, topCoord, mysteryNumber, -1);
				GL11.glEnable(GL11.GL_LIGHTING);
				GL11.glDisable(GL11.GL_BLEND);
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				GL11.glPopMatrix();
			}
		}
	}

	public static final void drawEntityLivingBase(final int x, final int y, final float scale, final float par3,
			final float par4, final EntityLivingBase entity, float par6) {
		if (KUtils.PROTECT_CLIENT) {
			glEnable(GL11.GL_COLOR_MATERIAL);
	        glPushMatrix();
	        glTranslatef(x - par6, y, 50.0F);
	        glScalef(-scale, scale, scale);
	        glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
	        glRotatef(par6, 0, 1, 0);
	        float f2 = entity.renderYawOffset;
	        float f3 = entity.rotationYaw;
	        float f4 = entity.rotationPitch;
	        float f5 = entity.prevRotationYawHead;
	        float f6 = entity.rotationYawHead;
	        glRotatef(135.0F, 0.0F, 1.0F, 0.0F);
	        RenderHelper.enableStandardItemLighting();
	        glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
	        glRotatef(-((float)Math.atan((double)(par4 / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
	        entity.renderYawOffset = (float)Math.atan((double)(par3 / 40.0F)) * 20.0F;
	        entity.rotationYaw = (float)Math.atan((double)(par3 / 40.0F)) * 40.0F;
	        entity.rotationPitch = -((float)Math.atan((double)(par4 / 40.0F))) * 20.0F;
	        entity.rotationYawHead = entity.rotationYaw;
	        entity.prevRotationYawHead = entity.rotationYaw;
	        glTranslatef(0.0F, entity.yOffset, 0.0F);
	        RenderManager.instance.playerViewY = 180.0F;
	        RenderManager.instance.renderEntityWithPosYaw(entity, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
	        entity.renderYawOffset = f2;
	        entity.rotationYaw = f3;
	        entity.rotationPitch = f4;
	        entity.prevRotationYawHead = f5;
	        entity.rotationYawHead = f6;
	        glPopMatrix();
	        RenderHelper.disableStandardItemLighting();
	        glDisable(GL12.GL_RESCALE_NORMAL);
	        OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
	        glDisable(GL_TEXTURE_2D);
	        OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
		}
	}

	public static final void drawGradientRect(final int x, final int y, final int width, final int heigth,
			final float red1, final float blue1, final float green1, final float alpha1, final float red2,
			final float blue2, final float green2, final float alpha2) {
		if (KUtils.PROTECT_CLIENT) {
			glDisable(GL_TEXTURE_2D);
			glEnable(GL_BLEND);
			glDisable(GL_ALPHA_TEST);
			OpenGlHelper.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA, 1, 0);
			glShadeModel(GL_SMOOTH);
			final Tessellator tessellator = Tessellator.instance;
			tessellator.startDrawingQuads();
			tessellator.setColorRGBA_F(red1, blue1, green1, alpha1);
			tessellator.addVertex(width, y, 0);
			tessellator.addVertex(x, y, 0);
			tessellator.setColorRGBA_F(red2, blue2, green2, alpha2);
			tessellator.addVertex(x, heigth, 0);
			tessellator.addVertex(width, heigth, 0);
			tessellator.draw();
			glShadeModel(GL_FLAT);
			glDisable(GL_BLEND);
			glEnable(GL_ALPHA_TEST);
			glEnable(GL_TEXTURE_2D);
		}
	}

	public static final void drawGradientRect(final int x, final int y, final int width, final int height,
			final int p_73733_5_, final int p_73733_6_) {
		if (KUtils.PROTECT_CLIENT) {
			final float f = (p_73733_5_ >> 24 & 255) / 255.0F;
			final float f1 = (p_73733_5_ >> 16 & 255) / 255.0F;
			final float f2 = (p_73733_5_ >> 8 & 255) / 255.0F;
			final float f3 = (p_73733_5_ & 255) / 255.0F;
			final float f4 = (p_73733_6_ >> 24 & 255) / 255.0F;
			final float f5 = (p_73733_6_ >> 16 & 255) / 255.0F;
			final float f6 = (p_73733_6_ >> 8 & 255) / 255.0F;
			final float f7 = (p_73733_6_ & 255) / 255.0F;
			glDisable(GL_TEXTURE_2D);
			glEnable(GL_BLEND);
			glDisable(GL_ALPHA_TEST);
			OpenGlHelper.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA, 1, 0);
			glShadeModel(GL_SMOOTH);
			final Tessellator tessellator = Tessellator.instance;
			tessellator.startDrawingQuads();
			tessellator.setColorRGBA_F(f1, f2, f3, f);
			tessellator.addVertex(width, y, 0);
			tessellator.addVertex(x, y, 0);
			tessellator.setColorRGBA_F(f5, f6, f7, f4);
			tessellator.addVertex(x, height, 0);
			tessellator.addVertex(width, height, 0);
			tessellator.draw();
			glShadeModel(GL_FLAT);
			glDisable(GL_BLEND);
			glEnable(GL_ALPHA_TEST);
			glEnable(GL_TEXTURE_2D);
		}
	}
}
