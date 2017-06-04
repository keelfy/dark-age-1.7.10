package keelfy.darkage.client.models.armor.wolf;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class WolfGlove  extends ModelBase {

   ModelRenderer Shape27;
   ModelRenderer Shape28;
   ModelRenderer Shape29;
   ModelRenderer Shape30;
   ModelRenderer Shape31;
   ModelRenderer Shape32;
   ModelRenderer Shape33;
   ModelRenderer Shape34;
   ModelRenderer Shape35;
   ModelRenderer Shape36;
   ModelRenderer Shape37;
   ModelRenderer Shape38;
   ModelRenderer Shape39;


   public WolfGlove() {
      this.textureWidth = 64;
      this.textureHeight = 32;
      this.Shape27 = new ModelRenderer(this, 54, 13);
      this.Shape27.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1);
      this.Shape27.setRotationPoint(4.0F, 7.0F, -2.5F);
      this.Shape27.setTextureSize(64, 32);
      this.Shape27.mirror = true;
      this.setRotation(this.Shape27, 0.0872665F, 0.0F, 0.0F);
      this.Shape28 = new ModelRenderer(this, 19, 10);
      this.Shape28.addBox(0.0F, 0.0F, 0.0F, 4, 2, 1);
      this.Shape28.setRotationPoint(4.0F, 8.0F, -2.3F);
      this.Shape28.setTextureSize(64, 32);
      this.Shape28.mirror = true;
      this.setRotation(this.Shape28, 0.0872665F, 0.0F, 0.0F);
      this.Shape29 = new ModelRenderer(this, 54, 13);
      this.Shape29.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4);
      this.Shape29.setRotationPoint(7.5F, 6.9F, -2.0F);
      this.Shape29.setTextureSize(64, 32);
      this.Shape29.mirror = true;
      this.setRotation(this.Shape29, 0.0F, 0.0F, 0.0872665F);
      this.Shape30 = new ModelRenderer(this, 19, 10);
      this.Shape30.addBox(0.0F, 0.0F, 0.0F, 1, 2, 4);
      this.Shape30.setRotationPoint(7.3F, 7.9F, -2.0F);
      this.Shape30.setTextureSize(64, 32);
      this.Shape30.mirror = true;
      this.setRotation(this.Shape30, 0.0F, 0.0F, 0.0872665F);
      this.Shape31 = new ModelRenderer(this, 54, 13);
      this.Shape31.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1);
      this.Shape31.setRotationPoint(4.0F, 7.0F, 1.5F);
      this.Shape31.setTextureSize(64, 32);
      this.Shape31.mirror = true;
      this.setRotation(this.Shape31, -0.0872665F, 0.0F, 0.0F);
      this.Shape32 = new ModelRenderer(this, 19, 10);
      this.Shape32.addBox(0.0F, 0.0F, 0.0F, 4, 2, 1);
      this.Shape32.setRotationPoint(4.0F, 8.0F, 1.3F);
      this.Shape32.setTextureSize(64, 32);
      this.Shape32.mirror = true;
      this.setRotation(this.Shape32, -0.0872665F, 0.0F, 0.0F);
      this.Shape33 = new ModelRenderer(this, 54, 13);
      this.Shape33.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4);
      this.Shape33.setRotationPoint(3.5F, 7.1F, -2.0F);
      this.Shape33.setTextureSize(64, 32);
      this.Shape33.mirror = true;
      this.setRotation(this.Shape33, 0.0F, 0.0F, -0.0872665F);
      this.Shape34 = new ModelRenderer(this, 19, 10);
      this.Shape34.addBox(0.0F, 0.0F, 0.0F, 1, 2, 4);
      this.Shape34.setRotationPoint(3.7F, 8.0F, -2.0F);
      this.Shape34.setTextureSize(64, 32);
      this.Shape34.mirror = true;
      this.setRotation(this.Shape34, 0.0F, 0.0F, -0.0872665F);
      this.Shape35 = new ModelRenderer(this, 0, 0);
      this.Shape35.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1);
      this.Shape35.setRotationPoint(4.0F, 10.0F, -2.2F);
      this.Shape35.setTextureSize(64, 32);
      this.Shape35.mirror = true;
      this.setRotation(this.Shape35, 0.0F, 0.0F, 0.0F);
      this.Shape36 = new ModelRenderer(this, 0, 0);
      this.Shape36.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4);
      this.Shape36.setRotationPoint(7.2F, 10.0F, -2.0F);
      this.Shape36.setTextureSize(64, 32);
      this.Shape36.mirror = true;
      this.setRotation(this.Shape36, 0.0F, 0.0F, 0.0F);
      this.Shape37 = new ModelRenderer(this, 54, 13);
      this.Shape37.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1);
      this.Shape37.setRotationPoint(4.0F, 10.0F, 1.2F);
      this.Shape37.setTextureSize(64, 32);
      this.Shape37.mirror = true;
      this.setRotation(this.Shape37, 0.0F, 0.0F, 0.0F);
      this.Shape38 = new ModelRenderer(this, 0, 0);
      this.Shape38.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4);
      this.Shape38.setRotationPoint(3.8F, 10.0F, -2.0F);
      this.Shape38.setTextureSize(64, 32);
      this.Shape38.mirror = true;
      this.setRotation(this.Shape38, 0.0F, 0.0F, 0.0F);
      this.Shape39 = new ModelRenderer(this, 19, 4);
      this.Shape39.addBox(0.0F, 0.0F, 0.0F, 4, 1, 4);
      this.Shape39.setRotationPoint(4.0F, 11.0F, -2.0F);
      this.Shape39.setTextureSize(64, 32);
      this.Shape39.mirror = true;
      this.setRotation(this.Shape39, 0.0F, 0.0F, 0.0F);
   }

   public void render(float f5) {
      this.Shape27.render(f5);
      this.Shape28.render(f5);
      this.Shape29.render(f5);
      this.Shape30.render(f5);
      this.Shape31.render(f5);
      this.Shape32.render(f5);
      this.Shape33.render(f5);
      this.Shape34.render(f5);
      this.Shape35.render(f5);
      this.Shape36.render(f5);
      this.Shape37.render(f5);
      this.Shape38.render(f5);
      this.Shape39.render(f5);
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
