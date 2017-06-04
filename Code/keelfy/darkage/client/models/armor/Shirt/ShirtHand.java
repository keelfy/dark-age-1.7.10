package keelfy.darkage.client.models.armor.Shirt;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ShirtHand  extends ModelBase {

   ModelRenderer Shape2;
   ModelRenderer Shape3;
   ModelRenderer Shape4;
   ModelRenderer Shape5;
   ModelRenderer Shape6;
   ModelRenderer Shape11;


   public ShirtHand() {
      this.textureWidth = 64;
      this.textureHeight = 32;
      this.Shape2 = new ModelRenderer(this, 46, 5);
      this.Shape2.addBox(0.0F, 0.0F, 0.0F, 4, 7, 1);
      this.Shape2.setRotationPoint(4.0F, 0.0F, -2.1F);
      this.Shape2.setTextureSize(64, 32);
      this.Shape2.mirror = true;
      this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
      this.Shape3 = new ModelRenderer(this, 46, 0);
      this.Shape3.addBox(0.0F, 0.0F, 0.0F, 1, 7, 4);
      this.Shape3.setRotationPoint(7.1F, 0.0F, -2.0F);
      this.Shape3.setTextureSize(64, 32);
      this.Shape3.mirror = true;
      this.setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
      this.Shape4 = new ModelRenderer(this, 46, 5);
      this.Shape4.addBox(0.0F, 0.0F, 0.0F, 4, 7, 1);
      this.Shape4.setRotationPoint(4.0F, 0.0F, 1.1F);
      this.Shape4.setTextureSize(64, 32);
      this.Shape4.mirror = true;
      this.setRotation(this.Shape4, 0.0F, 0.0F, 0.0F);
      this.Shape5 = new ModelRenderer(this, 46, 0);
      this.Shape5.addBox(0.0F, 0.0F, 0.0F, 1, 7, 4);
      this.Shape5.setRotationPoint(3.9F, 0.0F, -2.0F);
      this.Shape5.setTextureSize(64, 32);
      this.Shape5.mirror = true;
      this.setRotation(this.Shape5, 0.0F, 0.0F, 0.0F);
      this.Shape6 = new ModelRenderer(this, 48, 8);
      this.Shape6.addBox(0.0F, 0.0F, 0.0F, 4, 1, 4);
      this.Shape6.setRotationPoint(4.0F, -0.1F, -2.0F);
      this.Shape6.setTextureSize(64, 32);
      this.Shape6.mirror = true;
      this.setRotation(this.Shape6, 0.0F, 0.0F, 0.0F);
      this.Shape11 = new ModelRenderer(this, 0, 13);
      this.Shape11.addBox(0.0F, 0.0F, 0.0F, 1, 1, 5);
      this.Shape11.setRotationPoint(4.0F, -0.4F, -2.5F);
      this.Shape11.setTextureSize(64, 32);
      this.Shape11.mirror = true;
      this.setRotation(this.Shape11, 0.0F, 0.0F, 0.0F);
   }

   public void render(float f5) {
      this.Shape2.render(f5);
      this.Shape3.render(f5);
      this.Shape4.render(f5);
      this.Shape5.render(f5);
      this.Shape6.render(f5);
      this.Shape11.render(f5);
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
