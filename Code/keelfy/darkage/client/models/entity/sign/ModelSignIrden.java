package keelfy.darkage.client.models.entity.sign;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSignIrden  extends ModelBase {

	ModelRenderer Shape1;
	ModelRenderer Shape2;
	ModelRenderer Shape3;
	ModelRenderer Shape4;
	ModelRenderer Shape5;
	ModelRenderer Shape6;

	public ModelSignIrden() {
		this.textureWidth = 32;
		this.textureHeight = 32;
		this.Shape1 = new ModelRenderer(this, -4, 0);
		this.Shape1.addBox(0.0F, 0.0F, 0.0F, 4, 1, 4);
		this.Shape1.setRotationPoint(-5.0F, 17.0F, -11.0F);
		this.Shape1.setTextureSize(32, 32);
		this.Shape1.mirror = true;
		this.setRotation(this.Shape1, 0.0F, 0.4833219F, 0.0F);
		this.Shape2 = new ModelRenderer(this, -4, 0);
		this.Shape2.addBox(0.0F, 0.0F, 0.0F, 4, 1, 4);
		this.Shape2.setRotationPoint(6.0F, 17.0F, -12.0F);
		this.Shape2.setTextureSize(32, 32);
		this.Shape2.mirror = true;
		this.setRotation(this.Shape2, 0.0F, -0.4833166F, 0.0F);
		this.Shape3 = new ModelRenderer(this, -4, 0);
		this.Shape3.addBox(0.0F, 0.0F, 0.0F, 4, 1, 4);
		this.Shape3.setRotationPoint(4.0F, 17.0F, 4.0F);
		this.Shape3.setTextureSize(32, 32);
		this.Shape3.mirror = true;
		this.setRotation(this.Shape3, 0.0F, 0.4833166F, 0.0F);
		this.Shape4 = new ModelRenderer(this, -4, 0);
		this.Shape4.addBox(0.0F, 0.0F, 0.0F, 4, 1, 4);
		this.Shape4.setRotationPoint(-4.0F, 17.0F, 2.0F);
		this.Shape4.setTextureSize(32, 32);
		this.Shape4.mirror = true;
		this.setRotation(this.Shape4, 0.0F, -0.4833166F, 0.0F);
		this.Shape5 = new ModelRenderer(this, -4, 0);
		this.Shape5.addBox(0.0F, 0.0F, 0.0F, 4, 1, 4);
		this.Shape5.setRotationPoint(-10.0F, 17.0F, 0.0F);
		this.Shape5.setTextureSize(32, 32);
		this.Shape5.mirror = true;
		this.setRotation(this.Shape5, 0.0F, 1.570796F, 0.0F);
		this.Shape6 = new ModelRenderer(this, -4, 0);
		this.Shape6.addBox(0.0F, 0.0F, 0.0F, 4, 1, 4);
		this.Shape6.setRotationPoint(8.0F, 17.0F, 0.0F);
		this.Shape6.setTextureSize(32, 32);
		this.Shape6.mirror = true;
		this.setRotation(this.Shape6, 0.0F, 1.570796F, 0.0F);
	}

	public void render(float f5) {
		this.Shape1.render(f5);
		this.Shape2.render(f5);
		this.Shape3.render(f5);
		this.Shape4.render(f5);
		this.Shape5.render(f5);
		this.Shape6.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}
