package keelfy.darkage.client.gui;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.api.Brush;
import keelfy.api.client.GuiUtil;
import keelfy.api.network.PacketDispatcher;
import keelfy.darkage.entity.player.DAPlayer;
import keelfy.darkage.entity.player.DAPlayerUtil.Property;
import keelfy.darkage.entity.player.PlayerClass;
import keelfy.darkage.handler.client.ResourceHandler.Texture;
import keelfy.darkage.handler.client.ResourceHandler.Texture.WCT;
import keelfy.darkage.item.Sword;
import keelfy.darkage.item.Sword.SwordType;
import keelfy.darkage.network.server.ChangeSignMessage;
import keelfy.darkage.util.DAUtil;
import keelfy.darkage.util.LanguageUtil;
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
	private GuiUtil tess;
	
	private String desc_sword_silver, desc_sword_steel, gui_saturation;
	
	public GuiHud() {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			mc = Minecraft.getMinecraft();
			hud = Texture.get(WCT.GUI, "HUD");
			signs = new ResourceLocation[] { Texture.get(WCT.SIGN, "0"), Texture.get(WCT.SIGN, "1"),  Texture.get(WCT.SIGN, "2"),  Texture.get(WCT.SIGN, "3"), Texture.get(WCT.SIGN, "4") };
			itemRenderer = new RenderItem();
			tess = new GuiUtil();
			
			desc_sword_silver = LanguageUtil.localize(LanguageUtil.desc_sword_silver);
			desc_sword_steel = LanguageUtil.localize(LanguageUtil.desc_sword_steel);
			gui_saturation = LanguageUtil.localize(LanguageUtil.gui_saturation);
		}
	}

	private ScaledResolution sr;
	int k, l;
	
	private void updateResolution() {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			sr = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
			k = sr.getScaledWidth();
			l = sr.getScaledHeight();
		}
	}
	
	// TODO: Отображение голода
	public void renderGameOverlay() {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			updateResolution();
			DAPlayer wcp = DAPlayer.get(mc.thePlayer);
			
			if(wcp != null) {
				FontRenderer fr = mc.fontRenderer;
				int e = Integer.MAX_VALUE;
				String s;
				
				s = String.valueOf(Brush.YELLOW + DAUtil.MODNAME + " - " + DAUtil.MODVER);
				fr.drawStringWithShadow(s, Integer.valueOf(k - fr.getStringWidth(s) - 10), 10, e);
					
				s = String.valueOf(Brush.YELLOW + "by keelfy & RedSokol");
				fr.drawStringWithShadow(s, Integer.valueOf(k - fr.getStringWidth(s) - 10), Integer.valueOf(15 + fr.FONT_HEIGHT), e);
					
				if(!mc.thePlayer.capabilities.isCreativeMode) {
					s = String.valueOf(gui_saturation + " " + (int)wcp.get(Property.SATURATION) + "/" + (int)Property.SATURATION.getMaxValue());
					fr.drawStringWithShadow(s, k - fr.getStringWidth(s) - 10, Integer.valueOf(l - 20 + fr.FONT_HEIGHT), e);
						
					GL11.glPushMatrix();
					GL11.glEnable(GL11.GL_BLEND);
					mc.getTextureManager().bindTexture(hud);
					byte b = 4, c = 4;
					GL11.glColor4f(1F, 1F, 1F, 1F);
					tess.drawTexturedModalRectZ(b, c, 0, 0, 75, 50, 2);
					tess.drawTexturedModalRectZ(b, c, 0, 50, 75, 50, 1);
						
					GL11.glDisable(GL11.GL_ALPHA_TEST);
					tess.drawTexturedModalRectZ(b - 2, 42 + c, 0, 120, 21 + (int)(wcp.get(Property.ENERGY) / wcp.getPlayerMaxEnergy() * 38), 20, 1);
					if(wcp.getPlayerClass() == PlayerClass.WITCHER)
						tess.drawTexturedModalRectZ(b - 2, 44 + c, 0, 100, 100, 20, 0);
						
						
					int health = (int)(wcp.get(Property.HEALTH) / wcp.getPlayerMaxHealth() * 141.0f);
					tess.drawTexturedModalRectZ(b + 65, 19 - c, 110, 29, health, 20, 1);
					tess.drawTexturedModalRectZ(b + 65, 16 - c, 110, 0, 141, 25, 0);
						
					if(wcp.getPlayerClass() == PlayerClass.WITCHER) {
						tess.drawTexturedModalRectZ(b + 96, 47 - c, 163, 72, (int)wcp.get(Property.INTOXICATION), 10, 1);
						tess.drawTexturedModalRectZ(b + 80, 42 - c, 132, 62, 18, 20, 2);
						tess.drawTexturedModalRectZ(b + 96, 47 - c, 163, 60, 100, 10, 0);
					}
						
					GL11.glEnable(GL11.GL_ALPHA_TEST);
					GL11.glDisable(GL11.GL_BLEND);	
					GL11.glPopMatrix();
						
					if(mc.thePlayer.inventory.getCurrentItem() != null && mc.thePlayer.inventory.getCurrentItem().getItem() instanceof Sword) {
						Sword item = (Sword) mc.thePlayer.inventory.getCurrentItem().getItem();
						if(item.getType() == SwordType.STEEL) {
							drawCenteredString(fr, desc_sword_steel, k - 40, l - fr.FONT_HEIGHT - 11, e);
						} else if(item.getType() == SwordType.SILVER) {
							drawCenteredString(fr, desc_sword_silver, k - 40, l - fr.FONT_HEIGHT - 11, e);
						}
					}
			            
					renderHotSlotItem(4.0F, sr);
			            
					if(wcp.getWitcherSign() != null && wcp.getPlayerClass() == PlayerClass.WITCHER && wcp.getWitcherSign().ordinal() < 5 && wcp.getWitcherSign().ordinal() > 0) {
						GL11.glPushMatrix();
						RenderHelper.enableGUIStandardItemLighting();
						GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
						mc.getTextureManager().bindTexture(signs[wcp.getWitcherSign().ordinal()]);
						tess.drawTexturedModalRectZ(64, 54, 0, 0, 64, 64, 5);
						GL11.glPopMatrix();
					} else if(wcp.getWitcherSign().ordinal() > 5 || wcp.getWitcherSign().ordinal() < 0) {
						PacketDispatcher.getInstance().sendToServer(new ChangeSignMessage());
					}
				}
			}
		}
	}

	private void renderHotSlotItem(float f, ScaledResolution sr) {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
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
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
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
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
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
    	if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
    		fontRenderer.drawStringWithShadow(s, x - fontRenderer.getStringWidth(s) / 2, y, color);
    	}
    }

	
	private void drawGradientRect(int x, int y, int width, int heigth, float red1, float blue1, float green1, float alpha1, float red2, float blue2, float green2, float alpha2) {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
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
