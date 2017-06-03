package keelfy.witcher.client.models.armor.wolf;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class WolfLeg extends ModelBase {

   ModelRenderer Shape44;
   ModelRenderer Shape45;
   ModelRenderer Shape46;
   ModelRenderer Shape47;


   public WolfLeg() {
      this.textureWidth = 64;
      this.textureHeight = 32;
      this.Shape44 = new ModelRenderer(this, 54, 0);
      this.Shape44.addBox(0.0F, 0.0F, 0.0F, 4, 8, 1);
      this.Shape44.setRotationPoint(0.0F, 12.0F, -2.1F);
      this.Shape44.setTextureSize(64, 32);
      this.Shape44.mirror = true;
      this.setRotation(this.Shape44, 0.0F, 0.0F, 0.0F);
      this.Shape45 = new ModelRenderer(this, 54, 20);
      this.Shape45.addBox(0.0F, 0.0F, 0.0F, 1, 8, 4);
      this.Shape45.setRotationPoint(3.1F, 12.0F, -2.0F);
      this.Shape45.setTextureSize(64, 32);
      this.Shape45.mirror = true;
      this.setRotation(this.Shape45, 0.0F, 0.0F, 0.0F);
      this.Shape46 = new ModelRenderer(this, 54, 0);
      this.Shape46.addBox(0.0F, 0.0F, 0.0F, 4, 8, 1);
      this.Shape46.setRotationPoint(0.0F, 12.0F, 1.1F);
      this.Shape46.setTextureSize(64, 32);
      this.Shape46.mirror = true;
      this.setRotation(this.Shape46, 0.0F, 0.0F, 0.0F);
      this.Shape47 = new ModelRenderer(this, 54, 20);
      this.Shape47.addBox(0.0F, 0.0F, 0.0F, 1, 8, 4);
      this.Shape47.setRotationPoint(-0.1F, 12.0F, -2.0F);
      this.Shape47.setTextureSize(64, 32);
      this.Shape47.mirror = true;
      this.setRotation(this.Shape47, 0.0F, 0.0F, 0.0F);
   }

   public void render(float f5) {
      this.Shape44.render(f5);
      this.Shape45.render(f5);
      this.Shape46.render(f5);
      this.Shape47.render(f5);
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
