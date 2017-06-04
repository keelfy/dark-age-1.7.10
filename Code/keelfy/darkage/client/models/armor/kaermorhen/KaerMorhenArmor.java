package keelfy.darkage.client.models.armor.kaermorhen;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class KaerMorhenArmor  extends ModelBase {

   ModelRenderer Shape0;
   ModelRenderer Shape1;
   ModelRenderer Shape2;
   ModelRenderer Shape3;
   ModelRenderer Shape4;
   ModelRenderer Shape13;
   ModelRenderer Shape14;
   ModelRenderer Shape15;
   ModelRenderer Shape16;


   public KaerMorhenArmor() {
      this.textureWidth = 64;
      this.textureHeight = 32;
      this.Shape0 = new ModelRenderer(this, 0, 25);
      this.Shape0.addBox(0.0F, 0.0F, 0.0F, 4, 6, 1);
      this.Shape0.setRotationPoint(-2.0F, 6.0F, -2.3F);
      this.Shape0.setTextureSize(64, 32);
      this.Shape0.mirror = true;
      this.setRotation(this.Shape0, 0.0F, 0.0F, 0.0F);
      this.Shape1 = new ModelRenderer(this, 0, 12);
      this.Shape1.addBox(0.0F, 0.0F, 0.0F, 2, 6, 5);
      this.Shape1.setRotationPoint(2.0F, 6.0F, -2.5F);
      this.Shape1.setTextureSize(64, 32);
      this.Shape1.mirror = true;
      this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
      this.Shape2 = new ModelRenderer(this, 0, 12);
      this.Shape2.addBox(0.0F, 0.0F, 0.0F, 2, 6, 5);
      this.Shape2.setRotationPoint(-4.0F, 6.0F, -2.5F);
      this.Shape2.setTextureSize(64, 32);
      this.Shape2.mirror = true;
      this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
      this.Shape3 = new ModelRenderer(this, 38, 0);
      this.Shape3.addBox(0.0F, 0.0F, 0.0F, 8, 6, 5);
      this.Shape3.setRotationPoint(-4.0F, 0.0F, -2.5F);
      this.Shape3.setTextureSize(64, 32);
      this.Shape3.mirror = true;
      this.setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
      this.Shape4 = new ModelRenderer(this, 0, 25);
      this.Shape4.addBox(0.0F, 0.0F, 0.0F, 4, 6, 1);
      this.Shape4.setRotationPoint(-2.0F, 6.0F, 1.1F);
      this.Shape4.setTextureSize(64, 32);
      this.Shape4.mirror = true;
      this.setRotation(this.Shape4, 0.0F, 0.0F, 0.0F);
      this.Shape13 = new ModelRenderer(this, 15, 15);
      this.Shape13.addBox(0.0F, 0.0F, 0.0F, 10, 1, 1);
      this.Shape13.setRotationPoint(-4.0F, 0.0F, -2.8F);
      this.Shape13.setTextureSize(64, 32);
      this.Shape13.mirror = true;
      this.setRotation(this.Shape13, 0.0F, 0.0F, 0.5576792F);
      this.Shape14 = new ModelRenderer(this, 15, 15);
      this.Shape14.addBox(0.0F, 0.0F, 0.0F, 10, 1, 1);
      this.Shape14.setRotationPoint(4.4F, 0.7F, -2.8F);
      this.Shape14.setTextureSize(64, 32);
      this.Shape14.mirror = true;
      this.setRotation(this.Shape14, 0.0F, 0.0F, 2.585049F);
      this.Shape15 = new ModelRenderer(this, 11, 26);
      this.Shape15.addBox(0.0F, 0.0F, 0.0F, 9, 1, 5);
      this.Shape15.setRotationPoint(-4.5F, 10.3F, -2.8F);
      this.Shape15.setTextureSize(64, 32);
      this.Shape15.mirror = true;
      this.setRotation(this.Shape15, 0.0F, 0.0F, 0.0F);
      this.Shape16 = new ModelRenderer(this, 0, 5);
      this.Shape16.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1);
      this.Shape16.setRotationPoint(-1.0F, 10.0F, -2.9F);
      this.Shape16.setTextureSize(64, 32);
      this.Shape16.mirror = true;
      this.setRotation(this.Shape16, 0.0F, 0.0F, 0.0F);
   }

   public void render(float f5) {
      this.Shape0.render(f5);
      this.Shape1.render(f5);
      this.Shape2.render(f5);
      this.Shape3.render(f5);
      this.Shape4.render(f5);
      this.Shape13.render(f5);
      this.Shape14.render(f5);
      this.Shape15.render(f5);
      this.Shape16.render(f5);
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
