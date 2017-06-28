/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.client.renderer;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.config.GuiUtils;
import keelfy.darkage.client.gui.DAGuiInventory;
import keelfy.darkage.constants.EnumRarity;
import keelfy.darkage.constants.EnumSwordMaterial;
import keelfy.darkage.constants.EnumTexturePath;
import keelfy.darkage.handlers.client.ResourceHandler.Texture;
import keelfy.darkage.items.Armor;
import keelfy.darkage.items.Elexir;
import keelfy.darkage.items.Food;
import keelfy.darkage.items.Sword;
import keelfytools.KeelfyUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

/**
 * @author keelfy
 */
public class RendererRarity {
	private static final Minecraft mc = Minecraft.getMinecraft();
	
	public static final ResourceLocation USUAL = Texture.get(EnumTexturePath.RARITY, "1");
	public static final ResourceLocation UNCOMMON = Texture.get(EnumTexturePath.RARITY, "2");
	public static final ResourceLocation RARE = Texture.get(EnumTexturePath.RARITY, "3");
	public static final ResourceLocation LEGENDARY = Texture.get(EnumTexturePath.RARITY, "4");
	
//	public static void renderDraft(ItemStack draft) {
//		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
//			Draft item = (Draft) draft.getItem();
//			RenderItem.getInstance().renderItemIntoGUI(mc.fontRenderer, mc.getTextureManager(), draft, 0, 0);
//			if (item.getRarity() == ItemRarity.SET) {
//				mc.getTextureManager().bindTexture(SET);
//				GL11.glEnable(3042);
//				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
//				GL11.glScalef(0.07F, 0.07F, 1.0F);
//				GuiUtils.drawTexturedModalRect(-10, -10, 0, 0, 256, 256, -1.0F);
//				GL11.glDisable(3042);
//			}
//		}
//	}

	static void renderSword(ItemStack sword) {
		if(KeelfyUtils.isClientSide()) {
			Sword item = (Sword) sword.getItem();
			
			bindRarityTexture(item.getRarity(), sword, item.getType() == EnumSwordMaterial.SILVER ? 1 : 2);
		}
	}

	static void renderPotion(ItemStack potion) {
		if(KeelfyUtils.isClientSide()) {
			Elexir item = (Elexir) potion.getItem();
			
			bindRarityTexture(EnumRarity.RARE, potion, 0);
		}
	}

	public static void renderArmor(ItemStack armor) {
		if(KeelfyUtils.isClientSide()) {
			Armor item = (Armor) armor.getItem();
			
			bindRarityTexture(item.getRarity(), armor, ((Armor)armor.getItem()).getPart());
		}
	}

	public static void renderFood(ItemStack food) {
		if(KeelfyUtils.isClientSide()) {
			Food item = (Food) food.getItem();
			
			bindRarityTexture(item.getRarity(), food, 10);
		}
	}
	
	private static void bindRarityTexture(EnumRarity rarity, ItemStack stack, int slot) {
		if(KeelfyUtils.isClientSide()) {
			RenderItem.getInstance().renderItemIntoGUI(mc.fontRenderer, mc.getTextureManager(), stack, 0, 0);
			
			if(!mc.thePlayer.capabilities.isCreativeMode && mc.thePlayer.inventory.hasItemStack(stack) && mc.currentScreen instanceof DAGuiInventory) {
				switch(rarity) {
				case USUAL: mc.getTextureManager().bindTexture(USUAL);
					break;
				case UNCOMMON: mc.getTextureManager().bindTexture(UNCOMMON);
					break;
				case RARE: mc.getTextureManager().bindTexture(RARE);
					break;
				case LEGENDARY: mc.getTextureManager().bindTexture(LEGENDARY);
					break;
				default:
					break;
				}
				GL11.glEnable(3042);
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				GL11.glScalef(0.07F, 0.07F, 1.0F);
				if(stack.getItem() instanceof Armor) {
					int slot1 = 0; if(slot == 0) slot1 = 2; if(slot == 1) slot1 = 3; if(slot == 2) slot1 = 1; if(slot == 3) slot1 = 0;
					
					if(stack.hasTagCompound() && stack.stackTagCompound.getBoolean("inSlot") && mc.thePlayer.inventory.armorInventory[slot1] != null && ItemStack.areItemStacksEqual(mc.thePlayer.inventory.armorInventory[slot1], stack))
						GuiUtils.drawTexturedModalRect(-10, 100, 0, 0, 256, 256, -1.0F);
					else 
						GuiUtils.drawTexturedModalRect(-10, -10, 0, 0, 256, 256, -1.0F);
				} else if(stack.getItem() instanceof Sword) {
					
					if(stack.hasTagCompound() && stack.stackTagCompound.getBoolean("inSlot") && mc.thePlayer.inventory.mainInventory[slot] != null && ItemStack.areItemStacksEqual(mc.thePlayer.inventory.mainInventory[slot], stack))
						GuiUtils.drawTexturedModalRect(-10, 100, 0, 0, 256, 256, -1.0F);
					else 
						GuiUtils.drawTexturedModalRect(-10, -10, 0, 0, 256, 256, -1.0F);
					
				} else {
					GuiUtils.drawTexturedModalRect(-10, -10, 0, 0, 256, 256, -1.0F);
				}
				GL11.glDisable(3042);
			}
		}
	}
}
