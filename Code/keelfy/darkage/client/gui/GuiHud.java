/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.client.gui;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkage.constants.EnumPlayerClass;
import keelfy.darkage.constants.EnumProperty;
import keelfy.darkage.constants.EnumServerPacket;
import keelfy.darkage.constants.EnumSwordMaterial;
import keelfy.darkage.constants.EnumTexturePath;
import keelfy.darkage.entities.player.DAPlayer;
import keelfy.darkage.handlers.client.ResourceHandler.Texture;
import keelfy.darkage.items.Sword;
import keelfy.darkage.network.ClientPacketHandler;
import keelfy.darkage.utils.DAUtils;
import keelfytools.KeelfyUtils;
import keelfytools.LocalizationUtils;
import keelfytools.gui.KeelfyUtilsGui;
import keelfytools.log.Brush;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

/**
 * @author keelfy
 */
@SideOnly(Side.CLIENT)
public class GuiHud {
	private Minecraft mc;
	private ResourceLocation hud;
	private ResourceLocation[] signs;
	private RenderItem itemRenderer;
	
	private String desc_sword_silver, desc_sword_steel, gui_saturation;
	
	public GuiHud() {
		if(KeelfyUtils.isClientSide()) {
			mc = Minecraft.getMinecraft();
			hud = Texture.get(EnumTexturePath.GUI, "HUD");
			signs = new ResourceLocation[5];
			for(int i = 0; i < 5; i++) signs[i] = Texture.get(EnumTexturePath.SIGN, String.valueOf(i));
			itemRenderer = new RenderItem();
			
			desc_sword_silver = LocalizationUtils.localize(LocalizationUtils.desc_sword_silver);
			desc_sword_steel = LocalizationUtils.localize(LocalizationUtils.desc_sword_steel);
			gui_saturation = LocalizationUtils.localize(LocalizationUtils.gui_saturation);
		}
	}

	private ScaledResolution sr;
	int k, l;
	
	private void updateResolution() {
		if(KeelfyUtils.isClientSide()) {
			sr = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
			k = sr.getScaledWidth();
			l = sr.getScaledHeight();
		}
	}
	
	// TODO: Отображение голода
	public void renderGameOverlay() {
		if(KeelfyUtils.isClientSide()) {
			updateResolution();
			DAPlayer wcp = DAPlayer.get(mc.thePlayer);
			
			if(wcp != null) {
				
				FontRenderer fr = mc.fontRenderer;
				int e = Integer.MAX_VALUE;
				String s;
				
				s = String.valueOf(Brush.YELLOW + DAUtils.MODNAME + " - " + DAUtils.MODVER);
				fr.drawStringWithShadow(s, Integer.valueOf(k - fr.getStringWidth(s) - 10), 10, e);
					
				s = String.valueOf(Brush.YELLOW + "by keelfy & RedSokol");
				fr.drawStringWithShadow(s, Integer.valueOf(k - fr.getStringWidth(s) - 10), Integer.valueOf(15 + fr.FONT_HEIGHT), e);
					
				if(!mc.thePlayer.capabilities.isCreativeMode) {
					s = String.valueOf(gui_saturation + " " + (int)wcp.get(EnumProperty.SATURATION) + "/" + (int)EnumProperty.SATURATION.getMaxValue());
					fr.drawStringWithShadow(s, k - fr.getStringWidth(s) - 10, Integer.valueOf(l - 20 + fr.FONT_HEIGHT), e);
						
					GL11.glPushMatrix();
					GL11.glEnable(GL11.GL_BLEND);
					mc.getTextureManager().bindTexture(hud);
					byte b = 4, c = 4;
					GL11.glColor4f(1F, 1F, 1F, 1F);
					KeelfyUtilsGui.drawTexturedModalRectZ(b, c, 0, 0, 75, 50, 2);
					KeelfyUtilsGui.drawTexturedModalRectZ(b, c, 0, 50, 75, 50, 1);
						
					GL11.glDisable(GL11.GL_ALPHA_TEST);
					KeelfyUtilsGui.drawTexturedModalRectZ(b - 2, 42 + c, 0, 120, 21 + Math.round((wcp.get(EnumProperty.ENERGY) / wcp.getPlayerMaxEnergy() * 38)), 20, 1);
					if(wcp.getPlayerClass() == EnumPlayerClass.WITCHER)
						KeelfyUtilsGui.drawTexturedModalRectZ(b - 2, 44 + c, 0, 100, 100, 20, 0);
						
						
					int health = (int)(wcp.get(EnumProperty.HEALTH) / wcp.getPlayerMaxHealth() * 141.0f);
					KeelfyUtilsGui.drawTexturedModalRectZ(b + 65, 19 - c, 110, 29, health, 20, 1);
					KeelfyUtilsGui.drawTexturedModalRectZ(b + 65, 16 - c, 110, 0, 141, 25, 0);
						
					if(wcp.getPlayerClass() == EnumPlayerClass.WITCHER) {
						KeelfyUtilsGui.drawTexturedModalRectZ(b + 96, 47 - c, 163, 72, (int)wcp.get(EnumProperty.INTOXICATION), 10, 1);
						KeelfyUtilsGui.drawTexturedModalRectZ(b + 80, 42 - c, 132, 62, 18, 20, 2);
						KeelfyUtilsGui.drawTexturedModalRectZ(b + 96, 47 - c, 163, 60, 100, 10, 0);
					}
						
					GL11.glEnable(GL11.GL_ALPHA_TEST);
					GL11.glDisable(GL11.GL_BLEND);	
					GL11.glPopMatrix();
						
					if(mc.thePlayer.inventory.getCurrentItem() != null && mc.thePlayer.inventory.getCurrentItem().getItem() instanceof Sword) {
						Sword item = (Sword) mc.thePlayer.inventory.getCurrentItem().getItem();
						if(item.getType() == EnumSwordMaterial.STEEL) {
							drawCenteredString(fr, desc_sword_steel, k - 40, l - fr.FONT_HEIGHT - 11, e);
						} else if(item.getType() == EnumSwordMaterial.SILVER) {
							drawCenteredString(fr, desc_sword_silver, k - 40, l - fr.FONT_HEIGHT - 11, e);
						}
					}
			            
					renderHotSlotItem(4.0F, sr);
			            
					if(wcp.getWitcherSign() != null && wcp.getPlayerClass() == EnumPlayerClass.WITCHER && wcp.getWitcherSign().ordinal() < 5 && wcp.getWitcherSign().ordinal() >= 0) {
						GL11.glPushMatrix();
						RenderHelper.enableGUIStandardItemLighting();
						GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
						mc.getTextureManager().bindTexture(signs[wcp.getWitcherSign().ordinal()]);
						KeelfyUtilsGui.drawTexturedModalRectZ(64, 54, 0, 0, 64, 64, 5);
						GL11.glPopMatrix();
					} else if(wcp.getWitcherSign().ordinal() > 5 || wcp.getWitcherSign().ordinal() < 0) {
						ClientPacketHandler.sendToServer(EnumServerPacket.CHANGESIGN);
					}
				}
			}
		}
	}

	private void renderHotSlotItem(float f, ScaledResolution sr) {
		if(KeelfyUtils.isClientSide()) {
			byte e = 0;
			byte a = 15;
			int height = (int)(l * 1.4D);
			IInventory inventory = mc.thePlayer.inventory;
			GL11.glPushMatrix();
			GL11.glDisable(3042);
			GL11.glScaled(0.7D, 0.7D, 1.0D);
			if(inventory.getStackInSlot(8) != null) {
				mc.renderEngine.bindTexture(hud);
				drawGradientRect(-10 + a, height - 98 + e, 117, 23, 40, 30);
			}
	
			if(inventory.getStackInSlot(9) != null) {
				mc.renderEngine.bindTexture(hud);
				drawGradientRect(-6 + a, height - 74 + e, 117, 23, 40, 30);
			}
	
			if(inventory.getStackInSlot(10) != null) {
				mc.renderEngine.bindTexture(hud);
				drawGradientRect(-2 + a, height - 50 + e, 117, 23, 40, 30);
			}
	
			if(inventory.getStackInSlot(11) != null) {
				mc.renderEngine.bindTexture(hud);
				drawGradientRect(2 + a, height - 26 + e, 117, 23, 40, 30);
			}
	
			GL11.glDisable(3042);
			renderInventorySlot(8, 4 + a, height - 92 + e, f);
			renderInventorySlot(9, 8 + a, height - 67 + e, f);
			renderInventorySlot(10, 12 + a, height - 45 + e, f);
			renderInventorySlot(11, 16 + a, height - 20 + e, f);
			RenderHelper.disableStandardItemLighting();
			GL11.glEnable(3042);
			GL11.glPopMatrix();
		}
	}
	
	private void renderInventorySlot(int slot, int p_73832_2_, int p_73832_3_, float p_73832_4_) {
		if(KeelfyUtils.isClientSide()) {
			ItemStack itemstack = mc.thePlayer.inventory.mainInventory[slot];
			if(itemstack != null) {
				float f1 = itemstack.animationsToGo - p_73832_4_;
				GL11.glPushMatrix();
				if(f1 > 0.0F) {
					float f2 = 1.0F + f1 / 5.0F;
		            GL11.glTranslatef(p_73832_2_ + 8, p_73832_3_ + 12, 0.0F);
		            GL11.glScalef(1.0F / f2, (f2 + 1.0F) / 2.0F, 1.0F);
		            GL11.glTranslatef((-(p_73832_2_ + 8)), (-(p_73832_3_ + 12)), 0.0F);
		         }
	
				itemRenderer.renderItemAndEffectIntoGUI(mc.fontRenderer, mc.getTextureManager(), itemstack, p_73832_2_, p_73832_3_);
				GL11.glPopMatrix();
	
				itemRenderer.renderItemOverlayIntoGUI(mc.fontRenderer, mc.getTextureManager(), itemstack, p_73832_2_, p_73832_3_);
			}
		}
	}
	
	private void drawGradientRect(int p_73733_1_, int p_73733_2_, int p_73733_3_, int p_73733_4_, int p_73733_5_, int p_73733_6_) {
		if(KeelfyUtils.isClientSide()) {
	        float f = (p_73733_5_ >> 24 & 255) / 255.0F;
	        float f1 = (p_73733_5_ >> 16 & 255) / 255.0F;
	        float f2 = (p_73733_5_ >> 8 & 255) / 255.0F;
	        float f3 = (p_73733_5_ & 255) / 255.0F;
	        float f4 = (p_73733_6_ >> 24 & 255) / 255.0F;
	        float f5 = (p_73733_6_ >> 16 & 255) / 255.0F;
	        float f6 = (p_73733_6_ >> 8 & 255) / 255.0F;
	        float f7 = (p_73733_6_ & 255) / 255.0F;
	        GL11.glDisable(GL11.GL_TEXTURE_2D);
	        GL11.glEnable(GL11.GL_BLEND);
	        GL11.glDisable(GL11.GL_ALPHA_TEST);
	        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
	        GL11.glShadeModel(GL11.GL_SMOOTH);
	        Tessellator tessellator = Tessellator.instance;
	        tessellator.startDrawingQuads();
	        tessellator.setColorRGBA_F(f1, f2, f3, f);
	        tessellator.addVertex(p_73733_3_, p_73733_2_, 0);
	        tessellator.addVertex(p_73733_1_, p_73733_2_, 0);
	        tessellator.setColorRGBA_F(f5, f6, f7, f4);
	        tessellator.addVertex(p_73733_1_, p_73733_4_, 0);
	        tessellator.addVertex(p_73733_3_, p_73733_4_, 0);
	        tessellator.draw();
	        GL11.glShadeModel(GL11.GL_FLAT);
	        GL11.glDisable(GL11.GL_BLEND);
	        GL11.glEnable(GL11.GL_ALPHA_TEST);
	        GL11.glEnable(GL11.GL_TEXTURE_2D);
		}
    }

    private void drawCenteredString(FontRenderer fontRenderer, String s, int x, int y, int color) {
    	if(KeelfyUtils.isClientSide()) {
    		fontRenderer.drawStringWithShadow(s, x - fontRenderer.getStringWidth(s) / 2, y, color);
    	}
    }

	
	private void drawGradientRect(int x, int y, int width, int heigth, float red1, float blue1, float green1, float alpha1, float red2, float blue2, float green2, float alpha2) {
		if(KeelfyUtils.isClientSide()) {
			GL11.glDisable(3553);
			GL11.glEnable(3042);
			GL11.glEnable(3042);
			GL11.glDisable(3008);
			GL11.glBlendFunc(770, 771);
			GL11.glShadeModel(7425);
			Tessellator tessellator = Tessellator.instance;
			tessellator.startDrawingQuads();
			tessellator.setColorRGBA_F(red1, blue1, green1, alpha1);
			tessellator.addVertex(width, y, 0);
			tessellator.addVertex(x, y, 0);
			tessellator.setColorRGBA_F(red2, blue2, green2, alpha2);
			tessellator.addVertex(x, heigth, 0);
			tessellator.addVertex(width, heigth, 0);
			tessellator.draw();
			GL11.glShadeModel(7424);
			GL11.glDisable(3042);
			GL11.glEnable(3008);
			GL11.glDisable(3042);
			GL11.glEnable(3553);
		}
	}
}
