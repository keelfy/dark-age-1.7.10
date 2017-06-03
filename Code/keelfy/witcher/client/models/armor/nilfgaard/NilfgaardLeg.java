package keelfy.witcher.client.models.armor.nilfgaard;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class NilfgaardLeg extends ModelBase {

   ModelRenderer Shape69;
   ModelRenderer Shape70;
   ModelRenderer Shape71;
   ModelRenderer Shape72;
   ModelRenderer Shape73;
   ModelRenderer Shape74;
   ModelRenderer Shape75;
   ModelRenderer Shape76;
   ModelRenderer Shape77;
   ModelRenderer Shape78;
   ModelRenderer Shape79;
   ModelRenderer Shape80;


   public NilfgaardLeg() {
      this.textureWidth = 64;
      this.textureHeight = 32;
      this.Shape69 = new ModelRenderer(this, 0, 30);
      this.Shape69.addBox(0.0F, 0.0F, 0.0F, 5, 1, 1);
      this.Shape69.setRotationPoint(-0.5F, 13.0F, -2.5F);
      this.Shape69.setTextureSize(64, 32);
      this.Shape69.mirror = true;
      this.setRotation(this.Shape69, 0.0F, 0.0F, 0.0F);
      this.Shape70 = new ModelRenderer(this, 0, 30);
      this.Shape70.addBox(0.0F, 0.0F, 0.0F, 5, 1, 1);
      this.Shape70.setRotationPoint(-0.5F, 13.0F, 1.5F);
      this.Shape70.setTextureSize(64, 32);
      this.Shape70.mirror = true;
      this.setRotation(this.Shape70, 0.0F, 0.0F, 0.0F);
      this.Shape71 = new ModelRenderer(this, 0, 26);
      this.Shape71.addBox(0.0F, 0.0F, 0.0F, 1, 1, 5);
      this.Shape71.setRotationPoint(-0.5F, 13.0F, -2.5F);
      this.Shape71.setTextureSize(64, 32);
      this.Shape71.mirror = true;
      this.setRotation(this.Shape71, 0.0F, 0.0F, 0.0F);
      this.Shape72 = new ModelRenderer(this, 0, 26);
      this.Shape72.addBox(0.0F, 0.0F, 0.0F, 1, 1, 5);
      this.Shape72.setRotationPoint(3.5F, 13.0F, -2.5F);
      this.Shape72.setTextureSize(64, 32);
      this.Shape72.mirror = true;
      this.setRotation(this.Shape72, 0.0F, 0.0F, 0.0F);
      this.Shape73 = new ModelRenderer(this, 0, 30);
      this.Shape73.addBox(0.0F, 0.0F, 0.0F, 5, 1, 1);
      this.Shape73.setRotationPoint(-0.5F, 15.0F, -2.5F);
      this.Shape73.setTextureSize(64, 32);
      this.Shape73.mirror = true;
      this.setRotation(this.Shape73, 0.0F, 0.0F, 0.0F);
      this.Shape74 = new ModelRenderer(this, 0, 30);
      this.Shape74.addBox(0.0F, 0.0F, 0.0F, 5, 1, 1);
      this.Shape74.setRotationPoint(-0.5F, 15.0F, 1.5F);
      this.Shape74.setTextureSize(64, 32);
      this.Shape74.mirror = true;
      this.setRotation(this.Shape74, 0.0F, 0.0F, 0.0F);
      this.Shape75 = new ModelRenderer(this, 0, 26);
      this.Shape75.addBox(0.0F, 0.0F, 0.0F, 1, 1, 5);
      this.Shape75.setRotationPoint(-0.5F, 15.0F, -2.5F);
      this.Shape75.setTextureSize(64, 32);
      this.Shape75.mirror = true;
      this.setRotation(this.Shape75, 0.0F, 0.0F, 0.0F);
      this.Shape76 = new ModelRenderer(this, 0, 26);
      this.Shape76.addBox(0.0F, 0.0F, 0.0F, 1, 1, 5);
      this.Shape76.setRotationPoint(3.5F, 15.0F, -2.5F);
      this.Shape76.setTextureSize(64, 32);
      this.Shape76.mirror = true;
      this.setRotation(this.Shape76, 0.0F, 0.0F, 0.0F);
      this.Shape77 = new ModelRenderer(this, 24, 28);
      this.Shape77.addBox(0.0F, 0.0F, 0.0F, 4, 3, 1);
      this.Shape77.setRotationPoint(0.0F, 17.0F, -2.7F);
      this.Shape77.setTextureSize(64, 32);
      this.Shape77.mirror = true;
      this.setRotation(this.Shape77, 0.1919862F, 0.0F, 0.0F);
      this.Shape78 = new ModelRenderer(this, 35, 28);
      this.Shape78.addBox(0.0F, 0.0F, 0.0F, 3, 3, 1);
      this.Shape78.setRotationPoint(0.5F, 18.0F, -3.0F);
      this.Shape78.setTextureSize(64, 32);
      this.Shape78.mirror = true;
      this.setRotation(this.Shape78, 0.3316126F, 0.0F, 0.0F);
      this.Shape79 = new ModelRenderer(this, 35, 29);
      this.Shape79.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1);
      this.Shape79.setRotationPoint(0.5F, 19.0F, -2.0F);
      this.Shape79.setTextureSize(64, 32);
      this.Shape79.mirror = true;
      this.setRotation(this.Shape79, -0.1570796F, 0.0F, 0.0F);
      this.Shape80 = new ModelRenderer(this, 35, 29);
      this.Shape80.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1);
      this.Shape80.setRotationPoint(0.5F, 20.0F, -2.0F);
      this.Shape80.setTextureSize(64, 32);
      this.Shape80.mirror = true;
      this.setRotation(this.Shape80, -0.1570796F, 0.0F, 0.0F);
   }

   public void render(float f5) {
      this.Shape69.render(f5);
      this.Shape70.render(f5);
      this.Shape71.render(f5);
      this.Shape72.render(f5);
      this.Shape73.render(f5);
      this.Shape74.render(f5);
      this.Shape75.render(f5);
      this.Shape76.render(f5);
      this.Shape77.render(f5);
      this.Shape78.render(f5);
      this.Shape79.render(f5);
      this.Shape80.render(f5);
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
