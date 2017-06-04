package keelfy.darkage.client.models.armor.wolf;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class WolfHand  extends ModelBase {

   ModelRenderer Shape9;
   ModelRenderer Shape10;
   ModelRenderer Shape11;
   ModelRenderer Shape12;
   ModelRenderer Shape48;


   public WolfHand() {
      this.textureWidth = 64;
      this.textureHeight = 32;
      this.Shape9 = new ModelRenderer(this, 36, 4);
      this.Shape9.addBox(0.0F, 0.0F, 0.0F, 4, 6, 1);
      this.Shape9.setRotationPoint(4.0F, 0.0F, -2.1F);
      this.Shape9.setTextureSize(64, 32);
      this.Shape9.mirror = true;
      this.setRotation(this.Shape9, 0.0F, 0.0F, 0.0F);
      this.Shape10 = new ModelRenderer(this, 36, 4);
      this.Shape10.addBox(0.0F, 0.0F, 0.0F, 1, 6, 4);
      this.Shape10.setRotationPoint(7.1F, 0.0F, -2.0F);
      this.Shape10.setTextureSize(64, 32);
      this.Shape10.mirror = true;
      this.setRotation(this.Shape10, 0.0F, 0.0F, 0.0F);
      this.Shape11 = new ModelRenderer(this, 36, 4);
      this.Shape11.addBox(0.0F, 0.0F, 0.0F, 4, 6, 1);
      this.Shape11.setRotationPoint(4.0F, 0.0F, 1.1F);
      this.Shape11.setTextureSize(64, 32);
      this.Shape11.mirror = true;
      this.setRotation(this.Shape11, 0.0F, 0.0F, 0.0F);
      this.Shape12 = new ModelRenderer(this, 36, 4);
      this.Shape12.addBox(0.0F, 0.0F, 0.0F, 1, 6, 4);
      this.Shape12.setRotationPoint(3.9F, 0.0F, -2.0F);
      this.Shape12.setTextureSize(64, 32);
      this.Shape12.mirror = true;
      this.setRotation(this.Shape12, 0.0F, 0.0F, 0.0F);
      this.Shape48 = new ModelRenderer(this, 36, 4);
      this.Shape48.addBox(0.0F, 0.0F, 0.0F, 4, 1, 4);
      this.Shape48.setRotationPoint(4.0F, 0.0F, -2.0F);
      this.Shape48.setTextureSize(64, 32);
      this.Shape48.mirror = true;
      this.setRotation(this.Shape48, 0.0F, 0.0F, 0.0F);
   }

   public void render(float f5) {
      this.Shape9.render(f5);
      this.Shape10.render(f5);
      this.Shape11.render(f5);
      this.Shape12.render(f5);
      this.Shape48.render(f5);
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
