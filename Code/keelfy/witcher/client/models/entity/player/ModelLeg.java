package keelfy.witcher.client.models.entity.player;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * @author keelfy
 */
public class ModelLeg extends ModelBase {

	ModelRenderer leg;

	public ModelLeg() {
		this.textureWidth = 64;
		this.textureHeight = 32;
		this.leg = new ModelRenderer(this, 17, 20);
		this.leg.addBox(0.0F, 0.0F, 0.0F, 4, 8, 4);
		this.leg.setRotationPoint(0.0F, 12.0F, -2.0F);
		this.leg.setTextureSize(64, 32);
		this.leg.mirror = true;
		this.setRotation(this.leg, 0.0F, 0.0F, 0.0F);
	}

	public void render(float f5) {
		this.leg.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}
