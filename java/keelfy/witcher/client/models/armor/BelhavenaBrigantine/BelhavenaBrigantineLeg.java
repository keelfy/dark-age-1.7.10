package keelfy.witcher.client.models.armor.BelhavenaBrigantine;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class BelhavenaBrigantineLeg extends ModelBase {

   ModelRenderer Shape1;
   ModelRenderer Shape2;
   ModelRenderer Shape3;


   public BelhavenaBrigantineLeg() {
      this.textureWidth = 64;
      this.textureHeight = 32;
      this.Shape1 = new ModelRenderer(this, 16, 16);
      this.Shape1.addBox(0.0F, 0.0F, 0.0F, 3, 4, 1);
      this.Shape1.setRotationPoint(1.0F, 12.0F, -2.2F);
      this.Shape1.setTextureSize(64, 32);
      this.Shape1.mirror = true;
      this.setRotation(this.Shape1, -0.0698132F, 0.0F, 0.0F);
      this.Shape2 = new ModelRenderer(this, 36, 19);
      this.Shape2.addBox(0.0F, 0.0F, 0.0F, 4, 4, 1);
      this.Shape2.setRotationPoint(0.0F, 12.0F, 1.2F);
      this.Shape2.setTextureSize(64, 32);
      this.Shape2.mirror = true;
      this.setRotation(this.Shape2, 0.0523599F, 0.0F, 0.0F);
      this.Shape3 = new ModelRenderer(this, 25, 19);
      this.Shape3.addBox(0.0F, 0.0F, 0.0F, 1, 4, 4);
      this.Shape3.setRotationPoint(3.2F, 12.0F, -2.0F);
      this.Shape3.setTextureSize(64, 32);
      this.Shape3.mirror = true;
      this.setRotation(this.Shape3, 0.0F, 0.0F, -0.0523599F);
   }

   public void render(float f5) {
      this.Shape1.render(f5);
      this.Shape2.render(f5);
      this.Shape3.render(f5);
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
