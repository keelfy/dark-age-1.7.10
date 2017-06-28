/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.client.renderer;

import org.lwjgl.opengl.GL11;

import keelfy.darkage.client.models.entity.sign.ModelSignIrden;
import keelfy.darkage.constants.EnumSign;
import keelfy.darkage.constants.EnumTexturePath;
import keelfy.darkage.handlers.client.ResourceHandler.Texture;
import keelfytools.KeelfyUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

/**
 * @author keelfy
 */
public class RendererSign extends Render {

	private ResourceLocation texture;
	private ModelSignIrden model;

	private ResourceLocation textureIrden;
	private ModelSignIrden modelIrden;
	
	private EnumSign type;
	public RendererSign(EnumSign renderType) {
		if(KeelfyUtils.isClientSide()) {
			texture = Texture.get(EnumTexturePath.ENTITY, "null");
			textureIrden = Texture.get(EnumTexturePath.ENTITY, "irden");
			model = new ModelSignIrden();
			modelIrden = new ModelSignIrden();
			type = renderType;
		}
	}
	
	@Override
	public void doRender(Entity entity, double x, double y, double z, float par5, float par6) {
		if(KeelfyUtils.isClientSide()) {
			if(type != EnumSign.IRDEN) {
				GL11.glPushMatrix();
				GL11.glEnable(3042);
				Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
				GL11.glTranslated(x, y - 0.6000000238418579D, z);
				GL11.glScalef(3.0F, 1.0F, 3.0F);
				model.render(0.0625F);
				GL11.glDisable(3042);
				GL11.glPopMatrix();
			} else {
				GL11.glPushMatrix();
				GL11.glEnable(3042);
				GL11.glBlendFunc(770, 771);
				Minecraft.getMinecraft().getTextureManager().bindTexture(textureIrden);
				GL11.glTranslated(x, y - 0.6000000238418579D, z);
				GL11.glScalef(3.0F, 1.0F, 3.0F);
				model.render(0.0625F);
				GL11.glDisable(3042);
				GL11.glPopMatrix();
			}
		}
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
		if(KeelfyUtils.isClientSide()) {
			if(type != EnumSign.IRDEN) {
				return texture;
			} else {
				return textureIrden;
			}
		} else 
			return null;
	}
}
