package keelfy.witcher.client.models.armor.common;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class WitcherTopornikPants extends ModelBase {

   ModelRenderer Shape0;
   ModelRenderer Shape1;
   ModelRenderer Shape2;
   ModelRenderer Shape3;
   ModelRenderer Shape4;
   ModelRenderer Shape5;
   ModelRenderer Shape6;
   ModelRenderer Shape7;
   ModelRenderer Shape8;
   ModelRenderer Shape9;
   ModelRenderer Shape10;
   ModelRenderer Shape11;
   ModelRenderer Shape12;


   public WitcherTopornikPants() {
      this.textureWidth = 64;
      this.textureHeight = 32;
      this.Shape0 = new ModelRenderer(this, 0, 16);
      this.Shape0.addBox(0.0F, 0.0F, 0.0F, 4, 8, 1);
      this.Shape0.setRotationPoint(5.0E-11F, 14.0F, -2.2F);
      this.Shape0.setTextureSize(64, 32);
      this.Shape0.mirror = true;
      this.setRotation(this.Shape0, 0.0F, 0.0F, 0.0F);
      this.Shape1 = new ModelRenderer(this, 0, 12);
      this.Shape1.addBox(0.0F, 0.0F, 0.0F, 1, 10, 3);
      this.Shape1.setRotationPoint(3.2F, 12.0F, -2.2F);
      this.Shape1.setTextureSize(64, 32);
      this.Shape1.mirror = true;
      this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
      this.Shape2 = new ModelRenderer(this, 0, 0);
      this.Shape2.addBox(0.0F, 0.0F, 0.0F, 4, 8, 1);
      this.Shape2.setRotationPoint(5.0E-11F, 14.0F, 1.2F);
      this.Shape2.setTextureSize(64, 32);
      this.Shape2.mirror = true;
      this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
      this.Shape3 = new ModelRenderer(this, 0, 12);
      this.Shape3.addBox(0.0F, 0.0F, 0.0F, 1, 8, 3);
      this.Shape3.setRotationPoint(-0.1F, 14.0F, -2.2F);
      this.Shape3.setTextureSize(64, 32);
      this.Shape3.mirror = true;
      this.setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
      this.Shape4 = new ModelRenderer(this, 0, 12);
      this.Shape4.addBox(0.0F, 0.0F, 0.0F, 1, 10, 3);
      this.Shape4.setRotationPoint(3.2F, 12.0F, -0.8F);
      this.Shape4.setTextureSize(64, 32);
      this.Shape4.mirror = true;
      this.setRotation(this.Shape4, 0.0F, 0.0F, 0.0F);
      this.Shape5 = new ModelRenderer(this, 0, 12);
      this.Shape5.addBox(0.0F, 0.0F, 0.0F, 1, 8, 3);
      this.Shape5.setRotationPoint(-0.1F, 14.0F, -0.8F);
      this.Shape5.setTextureSize(64, 32);
      this.Shape5.mirror = true;
      this.setRotation(this.Shape5, 0.0F, 0.0F, 0.0F);
      this.Shape6 = new ModelRenderer(this, 0, 29);
      this.Shape6.addBox(0.0F, 0.0F, 0.0F, 4, 2, 1);
      this.Shape6.setRotationPoint(5.0E-11F, 12.0F, -2.1F);
      this.Shape6.setTextureSize(64, 32);
      this.Shape6.mirror = true;
      this.setRotation(this.Shape6, 0.0F, 0.0F, 0.0F);
      this.Shape7 = new ModelRenderer(this, 0, 29);
      this.Shape7.addBox(0.0F, 0.0F, 0.0F, 4, 2, 1);
      this.Shape7.setRotationPoint(5.0E-11F, 12.0F, 1.1F);
      this.Shape7.setTextureSize(64, 32);
      this.Shape7.mirror = true;
      this.setRotation(this.Shape7, 0.0F, 0.0F, 0.0F);
      this.Shape8 = new ModelRenderer(this, 0, 26);
      this.Shape8.addBox(0.0F, 0.0F, 0.0F, 1, 2, 4);
      this.Shape8.setRotationPoint(-0.1F, 12.0F, -2.0F);
      this.Shape8.setTextureSize(64, 32);
      this.Shape8.mirror = true;
      this.setRotation(this.Shape8, 0.0F, 0.0F, 0.0F);
      this.Shape9 = new ModelRenderer(this, 11, 0);
      this.Shape9.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1);
      this.Shape9.setRotationPoint(-0.1F, 13.5F, -2.2F);
      this.Shape9.setTextureSize(64, 32);
      this.Shape9.mirror = true;
      this.setRotation(this.Shape9, 0.0F, 0.0F, -0.3839724F);
      this.Shape10 = new ModelRenderer(this, 11, 0);
      this.Shape10.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1);
      this.Shape10.setRotationPoint(1.1F, 13.0F, -2.2F);
      this.Shape10.setTextureSize(64, 32);
      this.Shape10.mirror = true;
      this.setRotation(this.Shape10, 0.0F, 0.0F, 0.0F);
      this.Shape11 = new ModelRenderer(this, 11, 0);
      this.Shape11.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1);
      this.Shape11.setRotationPoint(-0.1F, 13.5F, 1.2F);
      this.Shape11.setTextureSize(64, 32);
      this.Shape11.mirror = true;
      this.setRotation(this.Shape11, 0.0F, 0.0F, -0.3839724F);
      this.Shape12 = new ModelRenderer(this, 11, 0);
      this.Shape12.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1);
      this.Shape12.setRotationPoint(1.1F, 13.0F, 1.2F);
      this.Shape12.setTextureSize(64, 32);
      this.Shape12.mirror = true;
      this.setRotation(this.Shape12, 0.0F, 0.0F, 0.0F);
   }

   public void render(float f5) {
      this.Shape0.render(f5);
      this.Shape1.render(f5);
      this.Shape2.render(f5);
      this.Shape3.render(f5);
      this.Shape4.render(f5);
      this.Shape5.render(f5);
      this.Shape6.render(f5);
      this.Shape7.render(f5);
      this.Shape8.render(f5);
      this.Shape9.render(f5);
      this.Shape10.render(f5);
      this.Shape11.render(f5);
      this.Shape12.render(f5);
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
