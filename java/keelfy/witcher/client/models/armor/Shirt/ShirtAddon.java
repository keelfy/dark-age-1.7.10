package keelfy.witcher.client.models.armor.Shirt;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ShirtAddon extends ModelBase {

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
   ModelRenderer Shape22;


   public ShirtAddon() {
      this.textureWidth = 64;
      this.textureHeight = 32;
      this.Shape12 = new ModelRenderer(this, 0, 20);
      this.Shape12.addBox(0.0F, 0.0F, 0.0F, 1, 4, 1);
      this.Shape12.setRotationPoint(3.0F, 0.0F, -2.2F);
      this.Shape12.setTextureSize(64, 32);
      this.Shape12.mirror = true;
      this.setRotation(this.Shape12, 0.0F, 0.0F, 0.0698132F);
      this.Shape13 = new ModelRenderer(this, 0, 20);
      this.Shape13.addBox(0.0F, 0.0F, 0.0F, 1, 11, 1);
      this.Shape13.setRotationPoint(2.9F, 3.4F, -2.2F);
      this.Shape13.setTextureSize(64, 32);
      this.Shape13.mirror = true;
      this.setRotation(this.Shape13, 0.0F, 0.0F, 0.715585F);
      this.Shape14 = new ModelRenderer(this, 0, 20);
      this.Shape14.addBox(0.0F, 0.0F, 0.0F, 1, 4, 1);
      this.Shape14.setRotationPoint(-4.0F, 0.0F, -2.2F);
      this.Shape14.setTextureSize(64, 32);
      this.Shape14.mirror = true;
      this.setRotation(this.Shape14, 0.0F, 0.0F, -0.0698132F);
      this.Shape15 = new ModelRenderer(this, 0, 20);
      this.Shape15.addBox(0.0F, 0.0F, 0.0F, 1, 11, 1);
      this.Shape15.setRotationPoint(-3.6F, 4.0F, -2.2F);
      this.Shape15.setTextureSize(64, 32);
      this.Shape15.mirror = true;
      this.setRotation(this.Shape15, 0.0F, 0.0F, -0.7190757F);
      this.Shape16 = new ModelRenderer(this, 0, 20);
      this.Shape16.addBox(0.0F, 0.0F, 0.0F, 1, 4, 1);
      this.Shape16.setRotationPoint(3.0F, 0.0F, 1.2F);
      this.Shape16.setTextureSize(64, 32);
      this.Shape16.mirror = true;
      this.setRotation(this.Shape16, 0.0F, 0.0F, 0.0698132F);
      this.Shape17 = new ModelRenderer(this, 0, 20);
      this.Shape17.addBox(0.0F, 0.0F, 0.0F, 1, 11, 1);
      this.Shape17.setRotationPoint(2.9F, 3.4F, 1.2F);
      this.Shape17.setTextureSize(64, 32);
      this.Shape17.mirror = true;
      this.setRotation(this.Shape17, 0.0F, 0.0F, 0.715585F);
      this.Shape18 = new ModelRenderer(this, 0, 20);
      this.Shape18.addBox(0.0F, 0.0F, 0.0F, 1, 11, 1);
      this.Shape18.setRotationPoint(-3.6F, 4.0F, 1.2F);
      this.Shape18.setTextureSize(64, 32);
      this.Shape18.mirror = true;
      this.setRotation(this.Shape18, 0.0F, 0.0F, -0.7190757F);
      this.Shape19 = new ModelRenderer(this, 0, 20);
      this.Shape19.addBox(0.0F, 0.0F, 0.0F, 1, 4, 1);
      this.Shape19.setRotationPoint(-4.0F, 0.0F, 1.2F);
      this.Shape19.setTextureSize(64, 32);
      this.Shape19.mirror = true;
      this.setRotation(this.Shape19, 0.0F, 0.0F, -0.0698132F);
      this.Shape20 = new ModelRenderer(this, 5, 23);
      this.Shape20.addBox(0.0F, 0.0F, 0.0F, 1, 1, 3);
      this.Shape20.setRotationPoint(-3.7F, 11.0F, -2.2F);
      this.Shape20.setTextureSize(64, 32);
      this.Shape20.mirror = true;
      this.setRotation(this.Shape20, 0.0F, 0.0F, 0.715585F);
      this.Shape21 = new ModelRenderer(this, 5, 23);
      this.Shape21.addBox(0.0F, 0.0F, 0.0F, 1, 1, 3);
      this.Shape21.setRotationPoint(-3.7F, 11.0F, -0.8F);
      this.Shape21.setTextureSize(64, 32);
      this.Shape21.mirror = true;
      this.setRotation(this.Shape21, 0.0F, 0.0F, 0.715585F);
      this.Shape22 = new ModelRenderer(this, 5, 23);
      this.Shape22.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4);
      this.Shape22.setRotationPoint(3.0F, 11.6F, -2.0F);
      this.Shape22.setTextureSize(64, 32);
      this.Shape22.mirror = true;
      this.setRotation(this.Shape22, 0.0F, 0.0F, -0.715585F);
   }

   public void render(float f5) {
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
      this.Shape22.render(f5);
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
