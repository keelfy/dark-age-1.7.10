/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.client.renderer;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkdata.client.DAResources.Texture;
import keelfy.darkdata.client.gui.DAInventoryGui;
import keelfy.darkdata.constants.EnumRarity;
import keelfy.darkdata.constants.EnumSwordMaterial;
import keelfy.darkdata.constants.EnumTexturePath;
import keelfy.darkdata.items.Armor;
import keelfy.darkdata.items.Elexir;
import keelfy.darkdata.items.Food;
import keelfy.darkdata.items.Sword;
import keelfyutils.KUtils;
import keelfyutils.client.KGL;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

/**
 * @author keelfy
 */
@SideOnly(Side.CLIENT)
public final class DARendererRarity {

	private static final Minecraft MC = KGL.mc();

	public static final ResourceLocation Texture_Rarity = Texture.get(EnumTexturePath.Inventory, "rarity");

	public static final ResourceLocation Texture_Usual = Texture.get(EnumTexturePath.Rarity, "1");
	public static final ResourceLocation Texture_Common = Texture.get(EnumTexturePath.Rarity, "2");
	public static final ResourceLocation Texture_Rare = Texture.get(EnumTexturePath.Rarity, "3");
	public static final ResourceLocation Texture_Legendary = Texture.get(EnumTexturePath.Rarity, "4");

	static final void renderSword(final ItemStack sword) {
		if (KUtils.PROTECT_CLIENT) {
			Sword item = (Sword) sword.getItem();

			bindRarityTexture(item.getRarity(), sword, item.getType() == EnumSwordMaterial.SILVER ? 2 : 1);
		}
	}

	static final void renderPotion(final ItemStack potion) {
		if (KUtils.PROTECT_CLIENT) {
			final Elexir item = (Elexir) potion.getItem();

			bindRarityTexture(EnumRarity.RARE, potion, 0);
		}
	}

	static final void renderArmor(final ItemStack armor) {
		if (KUtils.PROTECT_CLIENT) {
			final Armor item = (Armor) armor.getItem();

			bindRarityTexture(item.getRarity(), armor, ((Armor) armor.getItem()).getPart());
		}
	}

	static final void renderFood(final ItemStack food) {
		if (KUtils.PROTECT_CLIENT) {
			final Food item = (Food) food.getItem();

			bindRarityTexture(item.getRarity(), food, 10);
		}
	}

	private static final void bindRarityTexture(final EnumRarity rarity, final ItemStack stack, final int slot) {
		if (KUtils.PROTECT_CLIENT) {
			if (!MC.thePlayer.capabilities.isCreativeMode && MC.thePlayer.inventory.hasItemStack(stack)
					&& MC.currentScreen instanceof DAInventoryGui) {
				GL11.glPushMatrix();

				// KGL.bindTexture(Texture_Rarity);

				switch (rarity) {
				case USUAL:
					KGL.bindTexture(Texture_Usual);
					break;
				case UNCOMMON:
					KGL.bindTexture(Texture_Common);
					break;
				case RARE:
					KGL.bindTexture(Texture_Rare);
					break;
				case LEGENDARY:
					KGL.bindTexture(Texture_Legendary);
					break;
				default:
					break;
				}

				GL11.glEnable(GL11.GL_BLEND);
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				GL11.glScalef(0.07F, 0.07F, 1.0F);

				if (!stack.hasTagCompound()) {
					stack.stackTagCompound = new NBTTagCompound();
				}

				int posY = stack.stackTagCompound.getBoolean("inSlot") ? 100 : -10;
				KGL.texturedRect(-10, posY, -2, 0, 0, 256, 256);

				GL11.glDisable(GL11.GL_BLEND);
				GL11.glPopMatrix();
			}
			
			RenderItem.getInstance().renderItemIntoGUI(MC.fontRenderer, MC.getTextureManager(), stack, 0, 0);
		}
	}
}
