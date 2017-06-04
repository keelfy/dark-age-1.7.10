package keelfy.darkage.client.models.entity.player;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * @author keelfy
 */
public class ModelBoot  extends ModelBase {

	ModelRenderer Shape1;
	ModelRenderer Shape2;
	ModelRenderer Shape3;
	ModelRenderer Shape4;
	ModelRenderer Shape5;
	ModelRenderer Shape6;
	ModelRenderer Shape7;

	public ModelBoot() {
		this.textureWidth = 64;
		this.textureHeight = 32;
		this.Shape1 = new ModelRenderer(this, 0, 0);
		this.Shape1.addBox(0.0F, 0.0F, 0.0F, 5, 8, 5);
		this.Shape1.setRotationPoint(-0.5F, 16.0F, -2.5F);
		this.Shape1.setTextureSize(64, 32);
		this.Shape1.mirror = true;
		this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
		this.Shape2 = new ModelRenderer(this, 0, 23);
		this.Shape2.addBox(0.0F, 0.0F, 0.0F, 2, 1, 3);
		this.Shape2.setRotationPoint(-2.2F, 23.0F, -1.5F);
		this.Shape2.setTextureSize(64, 32);
		this.Shape2.mirror = true;
		this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
		this.Shape3 = new ModelRenderer(this, 0, 20);
		this.Shape3.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1);
		this.Shape3.setRotationPoint(-2.18F, 23.0F, -1.45F);
		this.Shape3.setTextureSize(64, 32);
		this.Shape3.mirror = true;
		this.setRotation(this.Shape3, 0.0F, 0.5690393F, 0.0F);
		this.Shape4 = new ModelRenderer(this, 0, 20);
		this.Shape4.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1);
		this.Shape4.setRotationPoint(-1.7F, 23.0F, 0.6F);
		this.Shape4.setTextureSize(64, 32);
		this.Shape4.mirror = true;
		this.setRotation(this.Shape4, 0.0F, -0.5515939F, 0.0F);
		this.Shape5 = new ModelRenderer(this, 0, 15);
		this.Shape5.addBox(0.0F, 0.0F, 0.0F, 3, 1, 3);
		this.Shape5.setRotationPoint(-2.2F, 23.0F, -1.5F);
		this.Shape5.setTextureSize(64, 32);
		this.Shape5.mirror = true;
		this.setRotation(this.Shape5, 0.0F, 0.0F, -0.7610255F);
		this.Shape6 = new ModelRenderer(this, 21, 0);
		this.Shape6.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1);
		this.Shape6.setRotationPoint(1.0F, 17.0F, -3.0F);
		this.Shape6.setTextureSize(64, 32);
		this.Shape6.mirror = true;
		this.setRotation(this.Shape6, 0.0F, 0.0F, 0.0F);
		this.Shape7 = new ModelRenderer(this, 21, 0);
		this.Shape7.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1);
		this.Shape7.setRotationPoint(1.0F, 19.0F, -3.0F);
		this.Shape7.setTextureSize(64, 32);
		this.Shape7.mirror = true;
		this.setRotation(this.Shape7, 0.0F, 0.0F, 0.0F);
	}

	public void render(float f5) {
		this.Shape1.render(f5);
		this.Shape2.render(f5);
		this.Shape3.render(f5);
		this.Shape4.render(f5);
		this.Shape5.render(f5);
		this.Shape6.render(f5);
		this.Shape7.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}
