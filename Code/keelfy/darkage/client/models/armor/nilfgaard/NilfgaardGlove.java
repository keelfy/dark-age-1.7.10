package keelfy.darkage.client.models.armor.nilfgaard;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class NilfgaardGlove  extends ModelBase {

   ModelRenderer Shape18;
   ModelRenderer Shape19;
   ModelRenderer Shape20;
   ModelRenderer Shape21;
   ModelRenderer Shape22;
   ModelRenderer Shape23;
   ModelRenderer Shape24;
   ModelRenderer Shape26;
   ModelRenderer Shape27;
   ModelRenderer Shape28;
   ModelRenderer Shape29;
   ModelRenderer Shape30;
   ModelRenderer Shape31;


   public NilfgaardGlove() {
      this.textureWidth = 64;
      this.textureHeight = 32;
      this.Shape18 = new ModelRenderer(this, 32, 21);
      this.Shape18.addBox(0.0F, 0.0F, 0.0F, 4, 3, 1);
      this.Shape18.setRotationPoint(4.0F, 8.0F, -2.4F);
      this.Shape18.setTextureSize(64, 32);
      this.Shape18.mirror = true;
      this.setRotation(this.Shape18, 0.122173F, 0.0F, 0.0F);
      this.Shape19 = new ModelRenderer(this, 32, 21);
      this.Shape19.addBox(0.0F, 0.0F, 0.0F, 4, 3, 1);
      this.Shape19.setRotationPoint(4.0F, 8.0F, 1.4F);
      this.Shape19.setTextureSize(64, 32);
      this.Shape19.mirror = true;
      this.setRotation(this.Shape19, -0.122173F, 0.0F, 0.0F);
      this.Shape20 = new ModelRenderer(this, 33, 18);
      this.Shape20.addBox(0.0F, 0.0F, 0.0F, 1, 3, 4);
      this.Shape20.setRotationPoint(7.6F, 7.9F, -2.0F);
      this.Shape20.setTextureSize(64, 32);
      this.Shape20.mirror = true;
      this.setRotation(this.Shape20, 0.0F, 0.0F, 0.1745329F);
      this.Shape21 = new ModelRenderer(this, 37, 0);
      this.Shape21.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1);
      this.Shape21.setRotationPoint(4.0F, 11.0F, -2.2F);
      this.Shape21.setTextureSize(64, 32);
      this.Shape21.mirror = true;
      this.setRotation(this.Shape21, 0.0F, 0.0F, 0.0F);
      this.Shape22 = new ModelRenderer(this, 36, 0);
      this.Shape22.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1);
      this.Shape22.setRotationPoint(4.0F, 11.0F, 1.2F);
      this.Shape22.setTextureSize(64, 32);
      this.Shape22.mirror = true;
      this.setRotation(this.Shape22, 0.0F, 0.0F, 0.0F);
      this.Shape23 = new ModelRenderer(this, 36, 0);
      this.Shape23.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4);
      this.Shape23.setRotationPoint(7.2F, 11.0F, -2.0F);
      this.Shape23.setTextureSize(64, 32);
      this.Shape23.mirror = true;
      this.setRotation(this.Shape23, 0.0F, 0.0F, 0.0F);
      this.Shape24 = new ModelRenderer(this, 36, 0);
      this.Shape24.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4);
      this.Shape24.setRotationPoint(3.8F, 11.0F, -2.0F);
      this.Shape24.setTextureSize(64, 32);
      this.Shape24.mirror = true;
      this.setRotation(this.Shape24, 0.0F, 0.0F, 0.0F);
      this.Shape26 = new ModelRenderer(this, 36, 0);
      this.Shape26.addBox(0.0F, 0.0F, 0.0F, 4, 1, 4);
      this.Shape26.setRotationPoint(4.0F, 11.2F, -2.0F);
      this.Shape26.setTextureSize(64, 32);
      this.Shape26.mirror = true;
      this.setRotation(this.Shape26, 0.0F, 0.0F, 0.0F);
      this.Shape27 = new ModelRenderer(this, 19, 11);
      this.Shape27.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
      this.Shape27.setRotationPoint(6.3F, 11.0F, -2.4F);
      this.Shape27.setTextureSize(64, 32);
      this.Shape27.mirror = true;
      this.setRotation(this.Shape27, 0.0F, 0.0F, 0.0F);
      this.Shape28 = new ModelRenderer(this, 19, 11);
      this.Shape28.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
      this.Shape28.setRotationPoint(7.4F, 11.0F, -1.7F);
      this.Shape28.setTextureSize(64, 32);
      this.Shape28.mirror = true;
      this.setRotation(this.Shape28, 0.0F, 0.0F, 0.0F);
      this.Shape29 = new ModelRenderer(this, 19, 11);
      this.Shape29.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
      this.Shape29.setRotationPoint(7.4F, 11.0F, -0.5F);
      this.Shape29.setTextureSize(64, 32);
      this.Shape29.mirror = true;
      this.setRotation(this.Shape29, 0.0F, 0.0F, 0.0F);
      this.Shape30 = new ModelRenderer(this, 19, 11);
      this.Shape30.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
      this.Shape30.setRotationPoint(7.4F, 11.0F, 0.8F);
      this.Shape30.setTextureSize(64, 32);
      this.Shape30.mirror = true;
      this.setRotation(this.Shape30, 0.0F, 0.0F, 0.0F);
      this.Shape31 = new ModelRenderer(this, 19, 11);
      this.Shape31.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
      this.Shape31.setRotationPoint(6.6F, 11.0F, 1.4F);
      this.Shape31.setTextureSize(64, 32);
      this.Shape31.mirror = true;
      this.setRotation(this.Shape31, 0.0F, 0.0F, 0.0F);
   }

   public void render(float f5) {
      this.Shape18.render(f5);
      this.Shape19.render(f5);
      this.Shape20.render(f5);
      this.Shape21.render(f5);
      this.Shape22.render(f5);
      this.Shape23.render(f5);
      this.Shape24.render(f5);
      this.Shape26.render(f5);
      this.Shape27.render(f5);
      this.Shape28.render(f5);
      this.Shape29.render(f5);
      this.Shape30.render(f5);
      this.Shape31.render(f5);
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
