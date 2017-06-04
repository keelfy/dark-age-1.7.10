package keelfy.darkage.client.models.armor.kaermorhen;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class KaerMorhenHandAddon  extends ModelBase {

   ModelRenderer Shape31;
   ModelRenderer Shape33;
   ModelRenderer Shape35;
   ModelRenderer Shape36;
   ModelRenderer Shape41;
   ModelRenderer Shape42;
   ModelRenderer Shape43;
   ModelRenderer Shape44;
   ModelRenderer Shape49;
   ModelRenderer Shape50;
   ModelRenderer Shape53;
   ModelRenderer Shape54;
   ModelRenderer Shape55;
   ModelRenderer Shape57;
   ModelRenderer Shape60;
   ModelRenderer Shape61;
   ModelRenderer Shape64;


   public KaerMorhenHandAddon() {
      this.textureWidth = 64;
      this.textureHeight = 32;
      this.Shape31 = new ModelRenderer(this, 0, 18);
      this.Shape31.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4);
      this.Shape31.setRotationPoint(8.0F, 1.0F, -2.0F);
      this.Shape31.setTextureSize(64, 32);
      this.Shape31.mirror = true;
      this.setRotation(this.Shape31, 0.0F, 0.0F, 0.0F);
      this.Shape33 = new ModelRenderer(this, 0, 18);
      this.Shape33.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4);
      this.Shape33.setRotationPoint(8.0F, 4.0F, -2.0F);
      this.Shape33.setTextureSize(64, 32);
      this.Shape33.mirror = true;
      this.setRotation(this.Shape33, 0.0F, 0.0F, 0.0F);
      this.Shape35 = new ModelRenderer(this, 0, 18);
      this.Shape35.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2);
      this.Shape35.setRotationPoint(7.0F, 1.0F, -3.0F);
      this.Shape35.setTextureSize(64, 32);
      this.Shape35.mirror = true;
      this.setRotation(this.Shape35, 0.0F, 0.6320364F, 0.0F);
      this.Shape36 = new ModelRenderer(this, 0, 18);
      this.Shape36.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2);
      this.Shape36.setRotationPoint(7.0F, 4.0F, -3.0F);
      this.Shape36.setTextureSize(64, 32);
      this.Shape36.mirror = true;
      this.setRotation(this.Shape36, 0.0F, 0.6320364F, 0.0F);
      this.Shape41 = new ModelRenderer(this, 0, 18);
      this.Shape41.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2);
      this.Shape41.setRotationPoint(8.2F, 1.0F, 1.4F);
      this.Shape41.setTextureSize(64, 32);
      this.Shape41.mirror = true;
      this.setRotation(this.Shape41, 0.0F, -0.5667673F, 0.0F);
      this.Shape42 = new ModelRenderer(this, 0, 18);
      this.Shape42.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2);
      this.Shape42.setRotationPoint(8.2F, 4.0F, 1.4F);
      this.Shape42.setTextureSize(64, 32);
      this.Shape42.mirror = true;
      this.setRotation(this.Shape42, 0.0F, -0.5667608F, 0.0F);
      this.Shape43 = new ModelRenderer(this, 0, 18);
      this.Shape43.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4);
      this.Shape43.setRotationPoint(4.0F, 1.0F, -2.6F);
      this.Shape43.setTextureSize(64, 32);
      this.Shape43.mirror = true;
      this.setRotation(this.Shape43, 0.0F, 1.570796F, 0.0F);
      this.Shape44 = new ModelRenderer(this, 0, 18);
      this.Shape44.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4);
      this.Shape44.setRotationPoint(4.0F, 4.0F, -2.6F);
      this.Shape44.setTextureSize(64, 32);
      this.Shape44.mirror = true;
      this.setRotation(this.Shape44, 0.0F, 1.570796F, 0.0F);
      this.Shape49 = new ModelRenderer(this, 0, 18);
      this.Shape49.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4);
      this.Shape49.setRotationPoint(4.0F, 1.0F, 3.6F);
      this.Shape49.setTextureSize(64, 32);
      this.Shape49.mirror = true;
      this.setRotation(this.Shape49, 0.0F, 1.570796F, 0.0F);
      this.Shape50 = new ModelRenderer(this, 0, 18);
      this.Shape50.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4);
      this.Shape50.setRotationPoint(4.0F, 4.0F, 3.6F);
      this.Shape50.setTextureSize(64, 32);
      this.Shape50.mirror = true;
      this.setRotation(this.Shape50, 0.0F, 1.570796F, 0.0F);
      this.Shape53 = new ModelRenderer(this, 0, 18);
      this.Shape53.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4);
      this.Shape53.setRotationPoint(4.0F, 5.0F, -2.6F);
      this.Shape53.setTextureSize(64, 32);
      this.Shape53.mirror = true;
      this.setRotation(this.Shape53, 1.570796F, 1.570796F, 0.0F);
      this.Shape54 = new ModelRenderer(this, 0, 18);
      this.Shape54.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4);
      this.Shape54.setRotationPoint(4.0F, 4.9F, 3.6F);
      this.Shape54.setTextureSize(64, 32);
      this.Shape54.mirror = true;
      this.setRotation(this.Shape54, 1.570796F, 1.570796F, 0.0F);
      this.Shape55 = new ModelRenderer(this, 0, 25);
      this.Shape55.addBox(0.0F, 0.0F, 0.0F, 2, 4, 1);
      this.Shape55.setRotationPoint(8.0F, 2.0F, -2.0F);
      this.Shape55.setTextureSize(64, 32);
      this.Shape55.mirror = true;
      this.setRotation(this.Shape55, 1.570796F, 0.0F, 1.570796F);
      this.Shape57 = new ModelRenderer(this, 0, 25);
      this.Shape57.addBox(0.0F, 0.0F, 0.0F, 2, 3, 1);
      this.Shape57.setRotationPoint(5.0F, 2.0F, -2.6F);
      this.Shape57.setTextureSize(64, 32);
      this.Shape57.mirror = true;
      this.setRotation(this.Shape57, 3.141593F, 0.0F, 1.570796F);
      this.Shape60 = new ModelRenderer(this, 0, 25);
      this.Shape60.addBox(0.0F, 0.0F, 0.0F, 2, 3, 1);
      this.Shape60.setRotationPoint(5.0F, 2.0F, 3.6F);
      this.Shape60.setTextureSize(64, 32);
      this.Shape60.mirror = true;
      this.setRotation(this.Shape60, 3.141593F, 0.0F, 1.570796F);
      this.Shape61 = new ModelRenderer(this, 0, 25);
      this.Shape61.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1);
      this.Shape61.setRotationPoint(7.0F, 2.0F, -2.9F);
      this.Shape61.setTextureSize(64, 32);
      this.Shape61.mirror = true;
      this.setRotation(this.Shape61, 2.249306F, 0.0F, 1.570796F);
      this.Shape64 = new ModelRenderer(this, 0, 25);
      this.Shape64.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1);
      this.Shape64.setRotationPoint(8.1F, 2.0F, 3.5F);
      this.Shape64.setTextureSize(64, 32);
      this.Shape64.mirror = true;
      this.setRotation(this.Shape64, -2.039871F, 0.0F, 1.570796F);
   }

   public void render(float f5) {
      this.Shape31.render(f5);
      this.Shape33.render(f5);
      this.Shape35.render(f5);
      this.Shape36.render(f5);
      this.Shape41.render(f5);
      this.Shape42.render(f5);
      this.Shape43.render(f5);
      this.Shape44.render(f5);
      this.Shape49.render(f5);
      this.Shape50.render(f5);
      this.Shape53.render(f5);
      this.Shape54.render(f5);
      this.Shape55.render(f5);
      this.Shape57.render(f5);
      this.Shape60.render(f5);
      this.Shape61.render(f5);
      this.Shape64.render(f5);
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
