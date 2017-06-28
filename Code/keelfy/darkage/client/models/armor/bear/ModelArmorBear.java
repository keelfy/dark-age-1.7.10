/**
 * 
 */
package keelfy.darkage.client.models.armor.bear;

import keelfytools.KeelfyUtils;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

/**
 * @author keelfy
 */
public class ModelArmorBear  extends ModelBase {

	ModelRenderer Shape1;
	ModelRenderer Shape2;
	ModelRenderer Shape3;
	ModelRenderer Shape4;
	ModelRenderer Shape5;
	ModelRenderer Shape6;
	ModelRenderer Shape8;
	ModelRenderer Shape9;
	ModelRenderer Shape10;
	ModelRenderer Shape11;
	ModelRenderer Shape12;
	ModelRenderer Shape13;
	ModelRenderer Shape14;
	ModelRenderer Shape15;
	ModelRenderer Shape16;
	ModelRenderer Shape33;
	ModelRenderer Shape34;

	public ModelArmorBear() {
		if(KeelfyUtils.isClientSide()) {
			this.textureWidth = 64;
			this.textureHeight = 32;
			this.Shape1 = new ModelRenderer(this, 46, 8);
			this.Shape1.addBox(0.0F, 0.0F, 0.0F, 8, 6, 1);
			this.Shape1.setRotationPoint(-4.0F, 0.0F, -2.6F);
			this.Shape1.setTextureSize(64, 32);
			this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
			this.Shape2 = new ModelRenderer(this, 46, 0);
			this.Shape2.addBox(0.0F, 0.0F, 0.0F, 8, 6, 1);
			this.Shape2.setRotationPoint(-4.0F, 6.0F, -2.4F);
			this.Shape2.setTextureSize(64, 32);
			this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
			this.Shape3 = new ModelRenderer(this, 46, 8);
			this.Shape3.addBox(0.0F, 0.0F, 0.0F, 8, 6, 1);
			this.Shape3.setRotationPoint(-4.0F, 0.0F, 1.6F);
			this.Shape3.setTextureSize(64, 32);
			this.setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
			this.Shape4 = new ModelRenderer(this, 46, 0);
			this.Shape4.addBox(0.0F, 0.0F, 0.0F, 8, 6, 1);
			this.Shape4.setRotationPoint(-4.0F, 6.0F, 1.3F);
			this.Shape4.setTextureSize(64, 32);
			this.setRotation(this.Shape4, 0.0F, 0.0F, 0.0F);
			this.Shape5 = new ModelRenderer(this, 14, 5);
			this.Shape5.addBox(0.0F, 0.0F, 0.0F, 1, 6, 1);
			this.Shape5.setRotationPoint(-2.6F, 6.0F, -2.5F);
			this.Shape5.setTextureSize(64, 32);
			this.setRotation(this.Shape5, 0.0F, 0.0F, -0.0698132F);
			this.Shape6 = new ModelRenderer(this, 7, 10);
			this.Shape6.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1);
			this.Shape6.setRotationPoint(-3.1F, 8.0F, -2.6F);
			this.Shape6.setTextureSize(64, 32);
			this.setRotation(this.Shape6, 0.0174533F, 0.0F, 0.1047198F);
			this.Shape8 = new ModelRenderer(this, 49, -3);
			this.Shape8.addBox(0.0F, 0.0F, 0.0F, 1, 6, 4);
			this.Shape8.setRotationPoint(-4.0F, 6.0F, -2.0F);
			this.Shape8.setTextureSize(64, 32);
			this.setRotation(this.Shape8, 0.0F, 0.0F, 0.0F);
			this.Shape9 = new ModelRenderer(this, 53, -3);
			this.Shape9.addBox(0.0F, 0.0F, 0.0F, 1, 6, 4);
			this.Shape9.setRotationPoint(3.0F, 6.0F, -2.0F);
			this.Shape9.setTextureSize(64, 32);
			this.setRotation(this.Shape9, 0.0F, 0.0F, 0.0F);
			this.Shape10 = new ModelRenderer(this, 0, 6);
			this.Shape10.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1);
			this.Shape10.setRotationPoint(-1.5F, 1.0F, -2.8F);
			this.Shape10.setTextureSize(64, 32);
			this.setRotation(this.Shape10, 0.0F, 0.0F, 0.0F);
			this.Shape11 = new ModelRenderer(this, 0, 6);
			this.Shape11.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1);
			this.Shape11.setRotationPoint(-1.5F, 3.0F, -2.8F);
			this.Shape11.setTextureSize(64, 32);
			this.setRotation(this.Shape11, 0.0F, 0.0F, 0.0F);
			this.Shape12 = new ModelRenderer(this, 0, 13);
			this.Shape12.addBox(0.0F, 0.0F, 0.0F, 5, 1, 5);
			this.Shape12.setRotationPoint(-4.2F, 9.5F, -2.6F);
			this.Shape12.setTextureSize(64, 32);
			this.setRotation(this.Shape12, 0.0F, 0.0F, 0.0F);
			this.Shape13 = new ModelRenderer(this, 0, 13);
			this.Shape13.addBox(0.0F, 0.0F, 0.0F, 5, 1, 5);
			this.Shape13.setRotationPoint(-0.7F, 9.5F, -2.6F);
			this.Shape13.setTextureSize(64, 32);
			this.setRotation(this.Shape13, 0.0F, 0.0F, 0.0F);
			this.Shape14 = new ModelRenderer(this, 0, 13);
			this.Shape14.addBox(0.0F, 0.0F, 0.0F, 7, 1, 5);
			this.Shape14.setRotationPoint(-4.4F, 11.0F, -2.6F);
			this.Shape14.setTextureSize(64, 32);
			this.setRotation(this.Shape14, 0.0F, 0.0F, -0.2094395F);
			this.Shape15 = new ModelRenderer(this, 5, 15);
			this.Shape15.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1);
			this.Shape15.setRotationPoint(1.0F, 10.4F, -2.6F);
			this.Shape15.setTextureSize(64, 32);
			this.setRotation(this.Shape15, 0.0F, 0.0F, -0.122173F);
			this.Shape16 = new ModelRenderer(this, 5, 15);
			this.Shape16.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1);
			this.Shape16.setRotationPoint(0.3F, 9.8F, -2.7F);
			this.Shape16.setTextureSize(64, 32);
			this.setRotation(this.Shape16, 0.0F, 0.0F, -0.5410521F);
			this.Shape33 = new ModelRenderer(this, 35, 5);
			this.Shape33.addBox(0.0F, 0.0F, 0.0F, 1, 6, 4);
			this.Shape33.setRotationPoint(-4.0F, 0.0F, -2.0F);
			this.Shape33.setTextureSize(64, 32);
			this.setRotation(this.Shape33, 0.0F, 0.0F, 0.0F);
			this.Shape34 = new ModelRenderer(this, 35, 5);
			this.Shape34.addBox(0.0F, 0.0F, 0.0F, 1, 6, 4);
			this.Shape34.setRotationPoint(3.0F, 0.0F, -2.0F);
			this.Shape34.setTextureSize(64, 32);
			this.setRotation(this.Shape34, 0.0F, 0.0F, 0.0F);
		}
	}

	public void render(float f5) {
		if(KeelfyUtils.isClientSide()) {
			this.Shape1.render(f5);
			this.Shape2.render(f5);
			this.Shape3.render(f5);
			this.Shape4.render(f5);
			this.Shape5.render(f5);
			this.Shape6.render(f5);
			this.Shape8.render(f5);
			this.Shape9.render(f5);
			this.Shape10.render(f5);
			this.Shape11.render(f5);
			this.Shape12.render(f5);
			this.Shape13.render(f5);
			this.Shape14.render(f5);
			this.Shape15.render(f5);
			this.Shape16.render(f5);
			this.Shape33.render(f5);
			this.Shape34.render(f5);
		}
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		if(KeelfyUtils.isClientSide()) {
			model.rotateAngleX = x;
			model.rotateAngleY = y;
			model.rotateAngleZ = z;
		}
	}
}
