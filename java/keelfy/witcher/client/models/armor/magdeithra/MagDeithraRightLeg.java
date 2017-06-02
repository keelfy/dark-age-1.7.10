package keelfy.witcher.client.models.armor.magdeithra;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class MagDeithraRightLeg extends ModelBase {

   ModelRenderer Shape57;
   ModelRenderer Shape58;
   ModelRenderer Shape59;
   ModelRenderer Shape60;


   public MagDeithraRightLeg() {
      this.textureWidth = 64;
      this.textureHeight = 32;
      this.Shape57 = new ModelRenderer(this, 11, 23);
      this.Shape57.addBox(0.0F, 0.0F, 0.0F, 4, 8, 1);
      this.Shape57.setRotationPoint(0.5F, 12.0F, -2.7F);
      this.Shape57.setTextureSize(64, 32);
      this.Shape57.mirror = true;
      this.setRotation(this.Shape57, -0.0698132F, 0.0F, 0.0F);
      this.Shape58 = new ModelRenderer(this, 11, 23);
      this.Shape58.addBox(0.0F, 0.0F, 0.0F, 4, 8, 1);
      this.Shape58.setRotationPoint(0.0F, 12.0F, 1.7F);
      this.Shape58.setTextureSize(64, 32);
      this.Shape58.mirror = true;
      this.setRotation(this.Shape58, 0.0698132F, 0.0F, 0.0F);
      this.Shape59 = new ModelRenderer(this, 11, 20);
      this.Shape59.addBox(0.0F, 0.0F, 0.0F, 1, 8, 4);
      this.Shape59.setRotationPoint(3.6F, 12.0F, -2.0F);
      this.Shape59.setTextureSize(64, 32);
      this.Shape59.mirror = true;
      this.setRotation(this.Shape59, 0.0F, 0.0F, -0.0698132F);
      this.Shape60 = new ModelRenderer(this, 22, 23);
      this.Shape60.addBox(0.0F, 0.0F, 0.0F, 1, 8, 1);
      this.Shape60.setRotationPoint(0.0F, 12.0F, -2.4F);
      this.Shape60.setTextureSize(64, 32);
      this.Shape60.mirror = true;
      this.setRotation(this.Shape60, -0.0698132F, 0.0F, 0.0F);
   }

   public void render(float f5) {
      this.Shape57.render(f5);
      this.Shape58.render(f5);
      this.Shape59.render(f5);
      this.Shape60.render(f5);
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
