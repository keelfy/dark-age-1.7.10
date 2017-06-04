package keelfy.darkage.client.models.entity.player;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

/**
 * @author keelfy
 */
public class ModelBody  extends ModelBase {

	ModelRenderer Shape19;
	ModelRenderer Shape21;
	ModelRenderer Shape18;

	public ModelBody() {
		this.textureWidth = 64;
		this.textureHeight = 32;
		this.Shape19 = new ModelRenderer(this, 35, 4);
		this.Shape19.addBox(0.0F, 0.0F, 0.0F, 1, 7, 4);
		this.Shape19.setRotationPoint(3.0F, 12.0F, -2.0F);
		this.Shape19.setTextureSize(64, 32);
		this.Shape19.mirror = true;
		this.setRotation(this.Shape19, 0.0F, 0.0F, -0.0743572F);
		this.Shape21 = new ModelRenderer(this, 46, 24);
		this.Shape21.addBox(0.0F, 0.0F, 0.0F, 4, 7, 1);
		this.Shape21.setRotationPoint(0.0F, 12.0F, 1.0F);
		this.Shape21.setTextureSize(64, 32);
		this.Shape21.mirror = true;
		this.setRotation(this.Shape21, 0.1487144F, 0.0F, 0.0F);
		this.Shape18 = new ModelRenderer(this, 35, 4);
		this.Shape18.addBox(0.0F, 0.0F, 0.0F, 1, 7, 4);
		this.Shape18.setRotationPoint(0.0F, 12.0F, -2.0F);
		this.Shape18.setTextureSize(64, 32);
		this.Shape18.mirror = true;
		this.setRotation(this.Shape18, 0.0F, 0.0F, 0.0F);
	}

	public void render(float f5) {
		this.Shape19.render(f5);
		this.Shape21.render(f5);
		this.Shape18.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}
