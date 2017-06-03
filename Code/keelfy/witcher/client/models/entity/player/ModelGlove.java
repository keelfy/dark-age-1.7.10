package keelfy.witcher.client.models.entity.player;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * @author keelfy
 */
public class ModelGlove extends ModelBase {

	ModelRenderer Shape1;
	ModelRenderer Shape2;
	ModelRenderer Shape3;
	ModelRenderer Shape31;
	ModelRenderer Shape32;
	ModelRenderer Shape4;
	ModelRenderer Shape41;

	public ModelGlove() {
		this.textureWidth = 64;
		this.textureHeight = 32;
		this.Shape1 = new ModelRenderer(this, 0, 8);
		this.Shape1.addBox(0.0F, 0.0F, 0.0F, 8, 16, 8);
		this.Shape1.setRotationPoint(0.0F, -10.0F, 0.0F);
		this.Shape1.setTextureSize(64, 32);
		this.Shape1.mirror = true;
		this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
		this.Shape2 = new ModelRenderer(this, 40, 7);
		this.Shape2.addBox(0.0F, 0.0F, 0.0F, 2, 2, 8);
		this.Shape2.setRotationPoint(6.533333F, 0.0F, 0.0F);
		this.Shape2.setTextureSize(64, 32);
		this.Shape2.mirror = true;
		this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
		this.Shape3 = new ModelRenderer(this, 35, 0);
		this.Shape3.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
		this.Shape3.setRotationPoint(8.0F, 0.5F, 3.0F);
		this.Shape3.setTextureSize(64, 32);
		this.Shape3.mirror = true;
		this.setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
		this.Shape31 = new ModelRenderer(this, 35, 0);
		this.Shape31.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
		this.Shape31.setRotationPoint(8.0F, 0.5F, 1.0F);
		this.Shape31.setTextureSize(64, 32);
		this.Shape31.mirror = true;
		this.setRotation(this.Shape31, 0.0F, 0.0F, 0.0F);
		this.Shape32 = new ModelRenderer(this, 35, 0);
		this.Shape32.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
		this.Shape32.setRotationPoint(8.0F, 0.5F, 5.0F);
		this.Shape32.setTextureSize(64, 32);
		this.Shape32.mirror = true;
		this.setRotation(this.Shape32, 0.0F, 0.0F, 0.0F);
		this.Shape4 = new ModelRenderer(this, 0, 0);
		this.Shape4.addBox(0.0F, 0.0F, 0.0F, 9, 2, 9);
		this.Shape4.setRotationPoint(-0.5F, -2.933333F, -0.5F);
		this.Shape4.setTextureSize(64, 32);
		this.Shape4.mirror = true;
		this.setRotation(this.Shape4, 0.0F, 0.0F, 0.0F);
		this.Shape41 = new ModelRenderer(this, 0, 0);
		this.Shape41.addBox(0.0F, 0.0F, 0.0F, 9, 2, 9);
		this.Shape41.setRotationPoint(-0.5F, -10.0F, -0.5F);
		this.Shape41.setTextureSize(64, 32);
		this.Shape41.mirror = true;
		this.setRotation(this.Shape41, 0.0F, 0.0F, 0.0F);
	}

	public void render(float f5) {
		this.Shape1.render(f5);
		this.Shape2.render(f5);
		this.Shape3.render(f5);
		this.Shape31.render(f5);
		this.Shape32.render(f5);
		this.Shape4.render(f5);
		this.Shape41.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
	}
}
