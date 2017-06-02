package keelfy.witcher.client.models.armor.griffin;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class GriffinLeg extends ModelBase {

   ModelRenderer Shape41;
   ModelRenderer Shape42;
   ModelRenderer Shape43;
   ModelRenderer Shape44;
   ModelRenderer Shape45;
   ModelRenderer Shape46;
   ModelRenderer Shape47;
   ModelRenderer Shape48;
   ModelRenderer Shape49;
   ModelRenderer Shape50;


   public GriffinLeg() {
      this.textureWidth = 64;
      this.textureHeight = 32;
      this.Shape41 = new ModelRenderer(this, 20, 14);
      this.Shape41.addBox(0.0F, 0.0F, 0.0F, 2, 5, 1);
      this.Shape41.setRotationPoint(2.0F, 13.0F, -2.1F);
      this.Shape41.setTextureSize(64, 32);
      this.Shape41.mirror = true;
      this.setRotation(this.Shape41, 0.0F, 0.0F, 0.0F);
      this.Shape42 = new ModelRenderer(this, 20, 14);
      this.Shape42.addBox(0.0F, 0.0F, 0.0F, 2, 5, 1);
      this.Shape42.setRotationPoint(2.0F, 13.0F, 1.1F);
      this.Shape42.setTextureSize(64, 32);
      this.Shape42.mirror = true;
      this.setRotation(this.Shape42, 0.0F, 0.0F, 0.0F);
      this.Shape43 = new ModelRenderer(this, 0, 0);
      this.Shape43.addBox(0.0F, 0.0F, 0.0F, 1, 5, 4);
      this.Shape43.setRotationPoint(3.1F, 13.0F, -2.0F);
      this.Shape43.setTextureSize(64, 32);
      this.Shape43.mirror = true;
      this.setRotation(this.Shape43, 0.0F, 0.0F, 0.0F);
      this.Shape44 = new ModelRenderer(this, 20, 15);
      this.Shape44.addBox(0.0F, 0.0F, 0.0F, 2, 4, 1);
      this.Shape44.setRotationPoint(0.0F, 14.0F, -2.1F);
      this.Shape44.setTextureSize(64, 32);
      this.Shape44.mirror = true;
      this.setRotation(this.Shape44, 0.0F, 0.0F, 0.0F);
      this.Shape45 = new ModelRenderer(this, 20, 15);
      this.Shape45.addBox(0.0F, 0.0F, 0.0F, 2, 4, 1);
      this.Shape45.setRotationPoint(0.0F, 14.0F, 1.1F);
      this.Shape45.setTextureSize(64, 32);
      this.Shape45.mirror = true;
      this.setRotation(this.Shape45, 0.0F, 0.0F, 0.0F);
      this.Shape46 = new ModelRenderer(this, 16, 6);
      this.Shape46.addBox(0.0F, 0.0F, 0.0F, 1, 4, 4);
      this.Shape46.setRotationPoint(-0.1F, 14.0F, -2.0F);
      this.Shape46.setTextureSize(64, 32);
      this.Shape46.mirror = true;
      this.setRotation(this.Shape46, 0.0F, 0.0F, 0.0F);
      this.Shape47 = new ModelRenderer(this, 16, 12);
      this.Shape47.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1);
      this.Shape47.setRotationPoint(0.1F, 13.5F, -2.1F);
      this.Shape47.setTextureSize(64, 32);
      this.Shape47.mirror = true;
      this.setRotation(this.Shape47, 0.0F, 0.0F, -0.2617994F);
      this.Shape48 = new ModelRenderer(this, 16, 12);
      this.Shape48.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1);
      this.Shape48.setRotationPoint(0.1F, 13.5F, 1.1F);
      this.Shape48.setTextureSize(64, 32);
      this.Shape48.mirror = true;
      this.setRotation(this.Shape48, 0.0F, 0.0F, -0.2617994F);
      this.Shape49 = new ModelRenderer(this, 16, 10);
      this.Shape49.addBox(0.0F, 0.0F, 0.0F, 1, 1, 3);
      this.Shape49.setRotationPoint(-0.3F, 13.6F, -2.1F);
      this.Shape49.setTextureSize(64, 32);
      this.Shape49.mirror = true;
      this.setRotation(this.Shape49, 0.0F, 0.0F, -0.2617994F);
      this.Shape50 = new ModelRenderer(this, 16, 10);
      this.Shape50.addBox(0.0F, 0.0F, 0.0F, 1, 1, 3);
      this.Shape50.setRotationPoint(-0.3F, 13.6F, -0.9F);
      this.Shape50.setTextureSize(64, 32);
      this.Shape50.mirror = true;
      this.setRotation(this.Shape50, 0.0F, 0.0F, -0.2617994F);
   }

   public void render(float f5) {
      this.Shape41.render(f5);
      this.Shape42.render(f5);
      this.Shape43.render(f5);
      this.Shape44.render(f5);
      this.Shape45.render(f5);
      this.Shape46.render(f5);
      this.Shape47.render(f5);
      this.Shape48.render(f5);
      this.Shape49.render(f5);
      this.Shape50.render(f5);
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
