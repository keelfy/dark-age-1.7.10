package keelfy.witcher.client.models.armor.HindarsfjallHeavyArmor;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class HindarsfjallHeavyArmorLeg extends ModelBase {

   ModelRenderer Shape5;
   ModelRenderer Shape6;
   ModelRenderer Shape7;
   ModelRenderer Shape8;
   ModelRenderer Shape9;
   ModelRenderer Shape10;
   ModelRenderer Shape11;


   public HindarsfjallHeavyArmorLeg() {
      this.textureWidth = 64;
      this.textureHeight = 32;
      this.Shape5 = new ModelRenderer(this, 12, 19);
      this.Shape5.addBox(0.0F, 0.0F, 0.0F, 1, 5, 1);
      this.Shape5.setRotationPoint(0.0F, 12.0F, -2.2F);
      this.Shape5.setTextureSize(64, 32);
      this.Shape5.mirror = true;
      this.setRotation(this.Shape5, -0.0698132F, 0.0F, 0.0F);
      this.Shape6 = new ModelRenderer(this, 18, 19);
      this.Shape6.addBox(0.0F, 0.0F, 0.0F, 3, 5, 1);
      this.Shape6.setRotationPoint(1.0F, 12.0F, -2.4F);
      this.Shape6.setTextureSize(64, 32);
      this.Shape6.mirror = true;
      this.setRotation(this.Shape6, -0.0698132F, 0.0F, 0.0F);
      this.Shape7 = new ModelRenderer(this, 0, 9);
      this.Shape7.addBox(0.0F, 0.0F, 0.0F, 4, 2, 1);
      this.Shape7.setRotationPoint(0.0F, 17.0F, -2.6F);
      this.Shape7.setTextureSize(64, 32);
      this.Shape7.mirror = true;
      this.setRotation(this.Shape7, -0.0698132F, 0.0F, 0.0F);
      this.Shape8 = new ModelRenderer(this, 28, 16);
      this.Shape8.addBox(0.0F, 0.0F, 0.0F, 1, 5, 4);
      this.Shape8.setRotationPoint(3.1F, 12.0F, -2.0F);
      this.Shape8.setTextureSize(64, 32);
      this.Shape8.mirror = true;
      this.setRotation(this.Shape8, 0.0F, 0.0F, -0.0698132F);
      this.Shape9 = new ModelRenderer(this, 39, 14);
      this.Shape9.addBox(0.0F, 0.0F, 0.0F, 1, 2, 4);
      this.Shape9.setRotationPoint(3.4F, 17.0F, -2.0F);
      this.Shape9.setTextureSize(64, 32);
      this.Shape9.mirror = true;
      this.setRotation(this.Shape9, 0.0F, 0.0F, -0.0698132F);
      this.Shape10 = new ModelRenderer(this, 17, 19);
      this.Shape10.addBox(0.0F, 0.0F, 0.0F, 4, 5, 1);
      this.Shape10.setRotationPoint(0.0F, 12.0F, 1.4F);
      this.Shape10.setTextureSize(64, 32);
      this.Shape10.mirror = true;
      this.setRotation(this.Shape10, 0.0698132F, 0.0F, 0.0F);
      this.Shape11 = new ModelRenderer(this, 0, 9);
      this.Shape11.addBox(0.0F, 0.0F, 0.0F, 4, 2, 1);
      this.Shape11.setRotationPoint(0.0F, 17.0F, 1.6F);
      this.Shape11.setTextureSize(64, 32);
      this.Shape11.mirror = true;
      this.setRotation(this.Shape11, 0.0698132F, 0.0F, 0.0F);
   }

   public void render(float f5) {
      this.Shape5.render(f5);
      this.Shape6.render(f5);
      this.Shape7.render(f5);
      this.Shape8.render(f5);
      this.Shape9.render(f5);
      this.Shape10.render(f5);
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
