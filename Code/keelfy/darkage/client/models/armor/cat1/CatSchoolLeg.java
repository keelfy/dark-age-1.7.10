package keelfy.darkage.client.models.armor.cat1;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class CatSchoolLeg  extends ModelBase {

   ModelRenderer Shape42;
   ModelRenderer Shape43;
   ModelRenderer Shape44;
   ModelRenderer Shape45;


   public CatSchoolLeg() {
      this.textureWidth = 64;
      this.textureHeight = 32;
      this.Shape42 = new ModelRenderer(this, 11, 19);
      this.Shape42.addBox(0.0F, 0.0F, 0.0F, 4, 12, 1);
      this.Shape42.setRotationPoint(0.0F, 12.0F, -2.2F);
      this.Shape42.setTextureSize(64, 32);
      this.Shape42.mirror = true;
      this.setRotation(this.Shape42, 0.0F, 0.0F, 0.0F);
      this.Shape43 = new ModelRenderer(this, 0, 16);
      this.Shape43.addBox(0.0F, 0.0F, 0.0F, 1, 12, 4);
      this.Shape43.setRotationPoint(3.2F, 12.0F, -2.0F);
      this.Shape43.setTextureSize(64, 32);
      this.Shape43.mirror = true;
      this.setRotation(this.Shape43, 0.0F, 0.0F, 0.0F);
      this.Shape44 = new ModelRenderer(this, 11, 19);
      this.Shape44.addBox(0.0F, 0.0F, 0.0F, 4, 12, 1);
      this.Shape44.setRotationPoint(0.0F, 12.0F, 1.2F);
      this.Shape44.setTextureSize(64, 32);
      this.Shape44.mirror = true;
      this.setRotation(this.Shape44, 0.0F, 0.0F, 0.0F);
      this.Shape45 = new ModelRenderer(this, 0, 16);
      this.Shape45.addBox(0.0F, 0.0F, 0.0F, 1, 12, 4);
      this.Shape45.setRotationPoint(-0.2F, 12.0F, -2.0F);
      this.Shape45.setTextureSize(64, 32);
      this.Shape45.mirror = true;
      this.setRotation(this.Shape45, 0.0F, 0.0F, 0.0F);
   }

   public void render(float f5) {
      this.Shape42.render(f5);
      this.Shape43.render(f5);
      this.Shape44.render(f5);
      this.Shape45.render(f5);
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
