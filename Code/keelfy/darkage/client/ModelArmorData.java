/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.client;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelCustom;

/**
 * @author keelfy
 * @created 2 июн. 2017 г.
 */
@SideOnly(Side.CLIENT)
// TODO: Закончить новую систему рендера
public class ModelArmorData {
	
	public ChestplateModel chestplate;
	public PantsModel pants;
	public GlovesModel gloves;
	public BootsModel boots;

	private final Minecraft mc = Minecraft.getMinecraft();
	
	public ModelArmorData(ChestplateModel cm, PantsModel pm, GlovesModel gm, BootsModel bm) {
		this.chestplate = cm;
		this.pants = pm;
		this.gloves = gm;
		this.boots = bm;
	}
	
	public void renderChestplateBody(ModelRenderer bipedBody) {
		GL11.glPushMatrix();
		bipedBody.postRender(0.0625F);
		GL11.glTranslatef(0.0F, -0.05F, 0.0F);
		GL11.glScalef(1.1F, 1.1F, 1.1F);
		mc.getTextureManager().bindTexture(chestplate.texture);
//			chestplate.bodyModel.render(0.0625F);
		GL11.glPopMatrix();
	}
	
	public static class ChestplateModel {
		public float 
			bodyTranslateX = 0, 
			bodyTranslateY = 0, 
			bodyTranslateZ = 0,
			bodyRotateX = 0,
			bodyRotateY = 0,
			bodyRotateZ = 0,
			bodyScaleX = 0,
			bodyScaleY = 0,
			bodyScaleZ = 0;
		
		public ModelBase bodyModel = null;
		public IModelCustom bodyOBJModel = null;
		
		public float 
			rightArmTranslateX = 0, 
			rightArmTranslateY = 0, 
			rightArmTranslateZ = 0,
			rightArmRotateX = 0,
			rightArmRotateY = 0,
			rightArmRotateZ = 0,
			rightArmScaleX = 0,
			rightArmScaleY = 0,
			rightArmScaleZ = 0;
	
		public float 
			leftArmTranslateX = 0, 
			leftArmTranslateY = 0, 
			leftArmTranslateZ = 0,
			leftArmRotateX = 0,
			leftArmRotateY = 0,
			leftArmRotateZ = 0,
			leftArmScaleX = 0,
			leftArmScaleY = 0,
			leftArmScaleZ = 0;
		
		public ModelBase armModel = null;
		public IModelCustom armOBJModel = null;
		
		public float 
			rightLegTranslateX = 0, 
			rightLegTranslateY = 0, 
			rightLegTranslateZ = 0,
			rightLegRotateX = 0,
			rightLegRotateY = 0,
			rightLegRotateZ = 0,
			rightLegScaleX = 0,
			rightLegScaleY = 0,
			rightLegScaleZ = 0;
	
		public float 
			leftLegTranslateX = 0, 
			leftLegTranslateY = 0, 
			leftLegTranslateZ = 0,
			leftLegRotateX = 0,
			leftLegRotateY = 0,
			leftLegRotateZ = 0,
			leftLegScaleX = 0,
			leftLegScaleY = 0,
			leftLegScaleZ = 0;
	
		public ModelBase legModel = null;
		public IModelCustom legOBJModel = null;
		
		public ResourceLocation texture;
	}
	
	public static class PantsModel {
		public float 
			rightLegTranslateX = 0, 
			rightLegTranslateY = 0, 
			rightLegTranslateZ = 0,
			rightLegRotateX = 0,
			rightLegRotateY = 0,
			rightLegRotateZ = 0,
			rightLegScaleX = 0,
			rightLegScaleY = 0,
			rightLegScaleZ = 0;

		public float 
			leftLegTranslateX = 0, 
			leftLegTranslateY = 0, 
			leftLegTranslateZ = 0,
			leftLegRotateX = 0,
			leftLegRotateY = 0,
			leftLegRotateZ = 0,
			leftLegScaleX = 0,
			leftLegScaleY = 0,
			leftLegScaleZ = 0;

		public ModelBase legModel = null;
		public IModelCustom legOBJModel = null;
		
		public ResourceLocation texture;
	}
	
	public static class GlovesModel {
		public float 
			rightArmTranslateX = 0, 
			rightArmTranslateY = 0, 
			rightArmTranslateZ = 0,
			rightArmRotateX = 0,
			rightArmRotateY = 0,
			rightArmRotateZ = 0,
			rightArmScaleX = 0,
			rightArmScaleY = 0,
			rightArmScaleZ = 0;

		public float 
			leftArmTranslateX = 0, 
			leftArmTranslateY = 0, 
			leftArmTranslateZ = 0,
			leftArmRotateX = 0,
			leftArmRotateY = 0,
			leftArmRotateZ = 0,
			leftArmScaleX = 0,
			leftArmScaleY = 0,
			leftArmScaleZ = 0;
		
		public ModelBase armModel = null;
		public IModelCustom armOBJModel = null;
		
		public ResourceLocation texture;
	}
	
	public static class BootsModel {
		public float 
			rightLegTranslateX = 0, 
			rightLegTranslateY = 0, 
			rightLegTranslateZ = 0,
			rightLegRotateX = 0,
			rightLegRotateY = 0,
			rightLegRotateZ = 0,
			rightLegScaleX = 0,
			rightLegScaleY = 0,
			rightLegScaleZ = 0;
	
		public float 
			leftLegTranslateX = 0, 
			leftLegTranslateY = 0, 
			leftLegTranslateZ = 0,
			leftLegRotateX = 0,
			leftLegRotateY = 0,
			leftLegRotateZ = 0,
			leftLegScaleX = 0,
			leftLegScaleY = 0,
			leftLegScaleZ = 0;
	
		public ModelBase legModel = null;
		public IModelCustom legOBJModel = null;
		
		public ResourceLocation texture;
	}
}
