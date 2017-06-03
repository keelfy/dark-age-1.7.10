package keelfy.witcher.client.render;

import api.player.model.ModelPlayerAPI;
import api.player.model.ModelPlayerBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.witcher.util.DAUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.MathHelper;

/**
 * @author keelfy
 */
@SideOnly(Side.CLIENT)
public class RenderModelPlayer extends ModelPlayerBase {

	public RenderModelPlayer(ModelPlayerAPI modelPlayerAPI) {
		super(modelPlayerAPI);
	}

	@Override
	public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_) {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			super.modelPlayer.bipedHead.rotateAngleY = p_78087_4_ / 57.295776F;
			super.modelPlayer.bipedHead.rotateAngleX = p_78087_5_ / 57.295776F;
			super.modelPlayer.bipedHeadwear.rotateAngleY = super.modelPlayer.bipedHead.rotateAngleY;
			super.modelPlayer.bipedHeadwear.rotateAngleX = super.modelPlayer.bipedHead.rotateAngleX;
			super.modelPlayer.bipedRightArm.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F + 3.1415927F) * 2.0F * p_78087_2_ * 0.5F;
			super.modelPlayer.bipedLeftArm.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F) * 2.0F * p_78087_2_ * 0.5F;
			super.modelPlayer.bipedRightArm.rotateAngleZ = 0.0F;
			super.modelPlayer.bipedLeftArm.rotateAngleZ = 0.0F;
			super.modelPlayer.bipedRightLeg.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F) * 1.4F * p_78087_2_;
			super.modelPlayer.bipedLeftLeg.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F + 3.1415927F) * 1.4F * p_78087_2_;
			super.modelPlayer.bipedRightLeg.rotateAngleY = 0.0F;
			super.modelPlayer.bipedLeftLeg.rotateAngleY = 0.0F;
			
			if (super.modelPlayer.isRiding) {
				super.modelPlayer.bipedRightArm.rotateAngleX += -0.62831855F;
				super.modelPlayer.bipedLeftArm.rotateAngleX += -0.62831855F;
				super.modelPlayer.bipedRightLeg.rotateAngleX = -0.62831855F;
				super.modelPlayer.bipedLeftLeg.rotateAngleX = -0.62831855F;
				super.modelPlayer.bipedRightLeg.rotateAngleY = 0.62831855F;
				super.modelPlayer.bipedLeftLeg.rotateAngleY = -0.62831855F;
				super.modelPlayer.bipedLeftArm.rotateAngleZ += 0.44879895F;
				
				if (Minecraft.getMinecraft().thePlayer.getCurrentEquippedItem() == null) {
					super.modelPlayer.bipedRightArm.rotateAngleZ += -0.44879895F;
				}
	
				if (Minecraft.getMinecraft().gameSettings.thirdPersonView == 0) {
					super.modelPlayer.bipedRightArm.rotateAngleZ = 0.0F;
					super.modelPlayer.bipedRightArm.rotateAngleX = 0.0F;
				}
			}
	
			if (super.modelPlayer.heldItemLeft != 0) {
				super.modelPlayer.bipedLeftArm.rotateAngleX = super.modelPlayer.bipedLeftArm.rotateAngleX * 0.5F - 0.31415927F * (float) super.modelPlayer.heldItemLeft;
			}
	
			if (super.modelPlayer.heldItemRight != 0) {
				super.modelPlayer.bipedRightArm.rotateAngleX = super.modelPlayer.bipedRightArm.rotateAngleX * 0.5F - 0.31415927F * (float) super.modelPlayer.heldItemRight;
			}
	
			super.modelPlayer.bipedRightArm.rotateAngleY = 0.0F;
			super.modelPlayer.bipedLeftArm.rotateAngleY = 0.0F;
			super.modelPlayer.bipedBody.rotateAngleX = 0.0F;
			super.modelPlayer.bipedRightArm.rotationPointZ = 0.1F;
			super.modelPlayer.bipedLeftArm.rotationPointZ = 0.1F;
			super.modelPlayer.bipedRightLeg.rotationPointY = 12.0F;
			super.modelPlayer.bipedLeftLeg.rotationPointY = 12.0F;
			super.modelPlayer.bipedHead.rotationPointY = 0.0F;
			super.modelPlayer.bipedHeadwear.rotationPointY = 0.0F;
			float f6;
			float f7;
			if (super.modelPlayer.onGround > -9990.0F) {
				f6 = super.modelPlayer.onGround;
				super.modelPlayer.bipedBody.rotateAngleY = MathHelper.sin(MathHelper.sqrt_float(f6) * (float)Math.PI * 2.0F) * 0.2F;
				super.modelPlayer.bipedRightArm.rotationPointZ = MathHelper.sin(super.modelPlayer.bipedBody.rotateAngleY) * 5.0F;
				super.modelPlayer.bipedRightArm.rotationPointX = -MathHelper.cos(super.modelPlayer.bipedBody.rotateAngleY) * 5.0F;
				super.modelPlayer.bipedLeftArm.rotationPointZ = -MathHelper.sin(super.modelPlayer.bipedBody.rotateAngleY) * 5.0F;
				super.modelPlayer.bipedLeftArm.rotationPointX = MathHelper.cos(super.modelPlayer.bipedBody.rotateAngleY) * 5.0F;
				super.modelPlayer.bipedRightArm.rotateAngleY += super.modelPlayer.bipedBody.rotateAngleY;
				super.modelPlayer.bipedLeftArm.rotateAngleY += super.modelPlayer.bipedBody.rotateAngleY;
				super.modelPlayer.bipedLeftArm.rotateAngleX += super.modelPlayer.bipedBody.rotateAngleY;
				f6 = 1.0F - super.modelPlayer.onGround;
				f6 *= f6;
				f6 *= f6;
				f6 = 1.0F - f6;
				f7 = MathHelper.sin(f6 * 3.1415927F);
				float f8 = MathHelper.sin(super.modelPlayer.onGround * 3.1415927F) * -(super.modelPlayer.bipedHead.rotateAngleX - 0.7F) * 0.75F;
				if (!super.modelPlayer.isRiding || Minecraft.getMinecraft().thePlayer.swingProgress != 0.0F) {
					super.modelPlayer.bipedRightArm.rotateAngleX = (float) ((double) super.modelPlayer.bipedRightArm.rotateAngleX
							- ((double) f7 * 1.2D + (double) f8));
					super.modelPlayer.bipedRightArm.rotateAngleY += super.modelPlayer.bipedBody.rotateAngleY * 2.0F;
					super.modelPlayer.bipedRightArm.rotateAngleZ = MathHelper
							.sin(super.modelPlayer.onGround * 3.1415927F) * -0.4F;
				}
			}
	
			super.modelPlayer.bipedRightArm.rotateAngleZ += MathHelper.cos(p_78087_3_ * 0.09F) * 0.05F + 0.05F;
			super.modelPlayer.bipedLeftArm.rotateAngleZ -= MathHelper.cos(p_78087_3_ * 0.09F) * 0.05F + 0.05F;
			super.modelPlayer.bipedRightArm.rotateAngleX += MathHelper.sin(p_78087_3_ * 0.067F) * 0.05F;
			super.modelPlayer.bipedLeftArm.rotateAngleX -= MathHelper.sin(p_78087_3_ * 0.067F) * 0.05F;
			if (super.modelPlayer.aimedBow) {
				f6 = 0.0F;
				f7 = 0.0F;
				super.modelPlayer.bipedRightArm.rotateAngleZ = 0.0F;
				super.modelPlayer.bipedRightArm.rotateAngleY = -(0.1F - f6 * 0.6F) + super.modelPlayer.bipedHead.rotateAngleY;
				super.modelPlayer.bipedRightArm.rotateAngleX = -1.5707964F + super.modelPlayer.bipedHead.rotateAngleX;
				super.modelPlayer.bipedRightArm.rotateAngleX -= f6 * 1.2F - f7 * 0.4F;
				super.modelPlayer.bipedRightArm.rotateAngleZ += MathHelper.cos(p_78087_3_ * 0.09F) * 0.05F + 0.05F;
				super.modelPlayer.bipedRightArm.rotateAngleX += MathHelper.sin(p_78087_3_ * 0.067F) * 0.05F;
			}
		}
	}

	@Override
	public void afterSetRotationAngles(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, Entity paramEntity) {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			EntityPlayer entityPlayer = (EntityPlayer) paramEntity;
			ItemStack item = entityPlayer.getHeldItem();
			
			if (item != null && item.getItem() instanceof ItemSword && entityPlayer.isUsingItem()) {
				if (!super.modelPlayer.isRiding) {
					super.modelPlayer.bipedRightArm.rotateAngleX = -1.4F;
					super.modelPlayer.bipedRightArm.rotateAngleY = -0.1F;
					super.modelPlayer.bipedLeftArm.rotateAngleX = -1.5F;
					super.modelPlayer.bipedLeftArm.rotateAngleY = 0.8F;
				}
			} else if (item != null && item.getItem() instanceof ItemSword) {
				if (!super.modelPlayer.isRiding && entityPlayer.swingProgress == 0.0F) {
					super.modelPlayer.bipedRightArm.rotateAngleX = -0.7F;
					super.modelPlayer.bipedRightArm.rotateAngleY = -0.3F;
					super.modelPlayer.bipedLeftArm.rotateAngleX = -1.0F;
					super.modelPlayer.bipedLeftArm.rotateAngleY = 0.8F;
				}
			} else if (item != null && item.getItem() instanceof ItemSword && !entityPlayer.isUsingItem()) {
				super.modelPlayer.bipedRightArm.rotateAngleX = -1.2F;
				super.modelPlayer.bipedRightArm.rotateAngleY = -0.1F;
				super.modelPlayer.bipedRightArm.rotateAngleZ = 1.5F;
				super.modelPlayer.bipedLeftArm.rotateAngleX = -0.75F;
				super.modelPlayer.bipedLeftArm.rotateAngleY = 0.1F;
				super.modelPlayer.bipedLeftArm.rotateAngleZ = 1.5F;
			}
		}
	}
}
