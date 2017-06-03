package keelfy.witcher.client.models.armor.BelhavenaBrigantine;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class BelhavenaBrigantineBody extends ModelBase {

   ModelRenderer Shape0;
   ModelRenderer Shape9;
   ModelRenderer Shape10;
   ModelRenderer Shape11;
   ModelRenderer Shape12;
   ModelRenderer Shape13;
   ModelRenderer Shape14;
   ModelRenderer Shape15;
   ModelRenderer Shape16;
   ModelRenderer Shape17;
   ModelRenderer Shape18;
   ModelRenderer Shape19;
   ModelRenderer Shape20;
   ModelRenderer Shape21;


   public BelhavenaBrigantineBody() {
      this.textureWidth = 64;
      this.textureHeight = 32;
      this.Shape0 = new ModelRenderer(this, 46, 0);
      this.Shape0.addBox(0.0F, 0.0F, 0.0F, 8, 11, 1);
      this.Shape0.setRotationPoint(-4.0F, 0.0F, -2.2F);
      this.Shape0.setTextureSize(64, 32);
      this.Shape0.mirror = true;
      this.setRotation(this.Shape0, 0.0174533F, 0.0F, 0.0F);
      this.Shape9 = new ModelRenderer(this, 27, 0);
      this.Shape9.addBox(0.0F, 0.0F, 0.0F, 8, 11, 1);
      this.Shape9.setRotationPoint(-4.0F, 0.0F, 1.2F);
      this.Shape9.setTextureSize(64, 32);
      this.Shape9.mirror = true;
      this.setRotation(this.Shape9, -0.0174533F, 0.0F, 0.0F);
      this.Shape10 = new ModelRenderer(this, 16, 0);
      this.Shape10.addBox(0.0F, 0.0F, 0.0F, 1, 11, 4);
      this.Shape10.setRotationPoint(-4.0F, 0.0F, -2.0F);
      this.Shape10.setTextureSize(64, 32);
      this.Shape10.mirror = true;
      this.setRotation(this.Shape10, 0.0F, 0.0F, 0.0F);
      this.Shape11 = new ModelRenderer(this, 16, 0);
      this.Shape11.addBox(0.0F, 0.0F, 0.0F, 1, 11, 4);
      this.Shape11.setRotationPoint(3.0F, 0.0F, -2.0F);
      this.Shape11.setTextureSize(64, 32);
      this.Shape11.mirror = true;
      this.setRotation(this.Shape11, 0.0F, 0.0F, 0.0F);
      this.Shape12 = new ModelRenderer(this, 44, 13);
      this.Shape12.addBox(0.0F, 0.0F, 0.0F, 6, 1, 4);
      this.Shape12.setRotationPoint(-3.0F, 0.0F, -2.0F);
      this.Shape12.setTextureSize(64, 32);
      this.Shape12.mirror = true;
      this.setRotation(this.Shape12, 0.0F, 0.0F, 0.0F);
      this.Shape13 = new ModelRenderer(this, 0, 10);
      this.Shape13.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1);
      this.Shape13.setRotationPoint(0.3F, 11.0F, -2.5F);
      this.Shape13.setTextureSize(64, 32);
      this.Shape13.mirror = true;
      this.setRotation(this.Shape13, 0.0F, 0.0F, 0.0F);
      this.Shape14 = new ModelRenderer(this, 0, 10);
      this.Shape14.addBox(0.0F, 0.0F, 0.0F, 5, 1, 1);
      this.Shape14.setRotationPoint(-4.3F, 11.0F, -2.5F);
      this.Shape14.setTextureSize(64, 32);
      this.Shape14.mirror = true;
      this.setRotation(this.Shape14, 0.0F, 0.0F, 0.0F);
      this.Shape15 = new ModelRenderer(this, 0, 10);
      this.Shape15.addBox(0.0F, 0.0F, 0.0F, 5, 1, 1);
      this.Shape15.setRotationPoint(-4.3F, 11.0F, 1.5F);
      this.Shape15.setTextureSize(64, 32);
      this.Shape15.mirror = true;
      this.setRotation(this.Shape15, 0.0F, 0.0F, 0.0F);
      this.Shape16 = new ModelRenderer(this, 0, 10);
      this.Shape16.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1);
      this.Shape16.setRotationPoint(0.3F, 11.0F, 1.5F);
      this.Shape16.setTextureSize(64, 32);
      this.Shape16.mirror = true;
      this.setRotation(this.Shape16, 0.0F, 0.0F, 0.0F);
      this.Shape17 = new ModelRenderer(this, 0, 7);
      this.Shape17.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4);
      this.Shape17.setRotationPoint(-4.3F, 11.0F, -2.0F);
      this.Shape17.setTextureSize(64, 32);
      this.Shape17.mirror = true;
      this.setRotation(this.Shape17, 0.0F, 0.0F, 0.0F);
      this.Shape18 = new ModelRenderer(this, 0, 7);
      this.Shape18.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4);
      this.Shape18.setRotationPoint(3.3F, 11.0F, -2.0F);
      this.Shape18.setTextureSize(64, 32);
      this.Shape18.mirror = true;
      this.setRotation(this.Shape18, 0.0F, 0.0F, 0.0F);
      this.Shape19 = new ModelRenderer(this, 0, 10);
      this.Shape19.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1);
      this.Shape19.setRotationPoint(0.0F, 12.0F, -2.5F);
      this.Shape19.setTextureSize(64, 32);
      this.Shape19.mirror = true;
      this.setRotation(this.Shape19, -0.0698132F, 0.0F, 0.0F);
      this.Shape20 = new ModelRenderer(this, 0, 10);
      this.Shape20.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1);
      this.Shape20.setRotationPoint(0.0F, 12.5F, -2.5F);
      this.Shape20.setTextureSize(64, 32);
      this.Shape20.mirror = true;
      this.setRotation(this.Shape20, 0.0F, 0.0F, -0.6981317F);
      this.Shape21 = new ModelRenderer(this, 0, 10);
      this.Shape21.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1);
      this.Shape21.setRotationPoint(0.0F, 11.1F, -2.5F);
      this.Shape21.setTextureSize(64, 32);
      this.Shape21.mirror = true;
      this.setRotation(this.Shape21, 0.0F, 0.0F, -0.2268928F);
   }

   public void render(float f5) {
      this.Shape0.render(f5);
      this.Shape9.render(f5);
      this.Shape10.render(f5);
      this.Shape11.render(f5);
      this.Shape12.render(f5);
      this.Shape13.render(f5);
      this.Shape14.render(f5);
      this.Shape15.render(f5);
      this.Shape16.render(f5);
      this.Shape17.render(f5);
      this.Shape18.render(f5);
      this.Shape19.render(f5);
      this.Shape20.render(f5);
      this.Shape21.render(f5);
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
