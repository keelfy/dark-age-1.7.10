package keelfy.darkage.client.models.armor.BelhavenaBrigantine;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class BelhavenaBrigantineHand  extends ModelBase {

   ModelRenderer Shape4;
   ModelRenderer Shape5;
   ModelRenderer Shape6;
   ModelRenderer Shape7;
   ModelRenderer Shape8;


   public BelhavenaBrigantineHand() {
      this.textureWidth = 64;
      this.textureHeight = 32;
      this.Shape4 = new ModelRenderer(this, 0, 25);
      this.Shape4.addBox(0.0F, 0.0F, 0.0F, 4, 6, 1);
      this.Shape4.setRotationPoint(4.0F, 0.0F, -2.2F);
      this.Shape4.setTextureSize(64, 32);
      this.Shape4.mirror = true;
      this.setRotation(this.Shape4, 0.0174533F, 0.0F, 0.0F);
      this.Shape5 = new ModelRenderer(this, 0, 14);
      this.Shape5.addBox(0.0F, 0.0F, 0.0F, 1, 6, 4);
      this.Shape5.setRotationPoint(7.2F, 0.0F, -2.0F);
      this.Shape5.setTextureSize(64, 32);
      this.Shape5.mirror = true;
      this.setRotation(this.Shape5, 0.0F, 0.0F, 0.0174533F);
      this.Shape6 = new ModelRenderer(this, 0, 25);
      this.Shape6.addBox(0.0F, 0.0F, 0.0F, 4, 6, 1);
      this.Shape6.setRotationPoint(4.0F, 0.0F, 1.2F);
      this.Shape6.setTextureSize(64, 32);
      this.Shape6.mirror = true;
      this.setRotation(this.Shape6, -0.0174533F, 0.0F, 0.0F);
      this.Shape7 = new ModelRenderer(this, 0, 14);
      this.Shape7.addBox(0.0F, 0.0F, 0.0F, 1, 6, 4);
      this.Shape7.setRotationPoint(3.8F, 0.0F, -2.0F);
      this.Shape7.setTextureSize(64, 32);
      this.Shape7.mirror = true;
      this.setRotation(this.Shape7, 0.0F, 0.0F, -0.0174533F);
      this.Shape8 = new ModelRenderer(this, 27, 13);
      this.Shape8.addBox(0.0F, 0.0F, 0.0F, 4, 1, 4);
      this.Shape8.setRotationPoint(4.0F, -0.1F, -2.0F);
      this.Shape8.setTextureSize(64, 32);
      this.Shape8.mirror = true;
      this.setRotation(this.Shape8, 0.0F, 0.0F, 0.0F);
   }

   public void render(float f5) {
      this.Shape4.render(f5);
      this.Shape5.render(f5);
      this.Shape6.render(f5);
      this.Shape7.render(f5);
      this.Shape8.render(f5);
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
