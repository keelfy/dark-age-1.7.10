/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfytools;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkage.client.render.RenderCustomFont;
import keelfy.darkage.constants.EnumFont;
import keelfy.darkage.handlers.client.FontHandler;
import keelfy.darkage.utils.DAUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MovingObjectPosition;

/**
 * @author keelfy
 * @created 8 июн. 2017 г.
 */
public enum KeelfyUtilsClient {
	Instance;
	
	@SideOnly(Side.CLIENT)
	public static final void print(String str) {
		if(KeelfyUtils.isClientSide()) {
			Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(str));
		}
	}
	
	@SideOnly(Side.CLIENT)
	public static EntityLiving getLookingEntity() {
		if(KeelfyUtils.isClientSide()) {
			Minecraft mc = Minecraft.getMinecraft();
			if(mc.objectMouseOver != null && mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY && mc.objectMouseOver.entityHit instanceof EntityLiving) {
				return (EntityLiving) mc.objectMouseOver.entityHit;
			}
		}
		return null;
	}
	
	@SideOnly(Side.CLIENT)
	public static void drawString(EnumFont twmFont, String text, int x, int y, int color) {
		if(KeelfyUtils.isClientSide()) {
			RenderCustomFont font = FontHandler.Instance.getFont(twmFont.getFontName());
			
			if(font == null) {
				Minecraft.getMinecraft().fontRenderer.drawString(text, x, y, color);
			} else {
				font.drawString(text, x, y, color);
			}
		}
	}
	
	@SideOnly(Side.CLIENT)
	public static float getStringWidth(EnumFont twmFont, String text) {
		if(KeelfyUtils.isClientSide()) {
			RenderCustomFont font = FontHandler.Instance.getFont(twmFont.getFontName());
			
			if(font == null) {
				return Minecraft.getMinecraft().fontRenderer.getStringWidth(text);
			} else {
				return font.getStringWidth(text);
			}
		}
		return 0;
	}
	
	@SideOnly(Side.CLIENT)
	public static float getStringHeight(EnumFont twmFont, String text) {
		if(KeelfyUtils.isClientSide()) {
			RenderCustomFont font = FontHandler.Instance.getFont(twmFont.getFontName());
			
			if(font == null) {
				return Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT;
			} else {
				return font.getStringHeight();
			}
		}
		return 0;
	}
}
