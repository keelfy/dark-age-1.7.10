package keelfy.witcher.client.models.armor.cidaris;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class CidarisLeg extends ModelBase {

   ModelRenderer Shape32;
   ModelRenderer Shape33;
   ModelRenderer Shape34;


   public CidarisLeg() {
      this.textureWidth = 64;
      this.textureHeight = 32;
      this.Shape32 = new ModelRenderer(this, 54, 0);
      this.Shape32.addBox(0.0F, 0.0F, 0.0F, 1, 4, 4);
      this.Shape32.setRotationPoint(3.0F, 12.0F, -2.0F);
      this.Shape32.setTextureSize(64, 32);
      this.Shape32.mirror = true;
      this.setRotation(this.Shape32, 0.0F, 0.0F, -0.0523599F);
      this.Shape33 = new ModelRenderer(this, 54, 0);
      this.Shape33.addBox(0.0F, 0.0F, 0.0F, 4, 4, 1);
      this.Shape33.setRotationPoint(0.0F, 12.0F, 1.0F);
      this.Shape33.setTextureSize(64, 32);
      this.Shape33.mirror = true;
      this.setRotation(this.Shape33, 0.0523599F, 0.0F, 0.0F);
      this.Shape34 = new ModelRenderer(this, 54, 0);
      this.Shape34.addBox(0.0F, 0.0F, 0.0F, 3, 4, 1);
      this.Shape34.setRotationPoint(1.0F, 12.0F, -2.5F);
      this.Shape34.setTextureSize(64, 32);
      this.Shape34.mirror = true;
      this.setRotation(this.Shape34, -0.0523599F, 0.0F, 0.0F);
   }

   public void render(float f5) {
      this.Shape32.render(f5);
      this.Shape33.render(f5);
      this.Shape34.render(f5);
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
