package keelfy.witcher.client.models.armor.HindarsfjallHeavyArmor;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class HindarsfjallHeavyArmorHand extends ModelBase {

   ModelRenderer Shape12;
   ModelRenderer Shape13;
   ModelRenderer Shape14;
   ModelRenderer Shape15;
   ModelRenderer Shape16;
   ModelRenderer Shape17;
   ModelRenderer Shape18;
   ModelRenderer Shape36;


   public HindarsfjallHeavyArmorHand() {
      this.textureWidth = 64;
      this.textureHeight = 32;
      this.Shape12 = new ModelRenderer(this, 54, 24);
      this.Shape12.addBox(0.0F, 0.0F, 0.0F, 4, 7, 1);
      this.Shape12.setRotationPoint(4.0F, 0.0F, -2.2F);
      this.Shape12.setTextureSize(64, 32);
      this.Shape12.mirror = true;
      this.setRotation(this.Shape12, 0.0F, 0.0F, 0.0F);
      this.Shape13 = new ModelRenderer(this, 43, 21);
      this.Shape13.addBox(0.0F, 0.0F, 0.0F, 1, 7, 4);
      this.Shape13.setRotationPoint(7.2F, 0.0F, -2.0F);
      this.Shape13.setTextureSize(64, 32);
      this.Shape13.mirror = true;
      this.setRotation(this.Shape13, 0.0F, 0.0F, 0.0F);
      this.Shape14 = new ModelRenderer(this, 54, 24);
      this.Shape14.addBox(0.0F, 0.0F, 0.0F, 4, 7, 1);
      this.Shape14.setRotationPoint(4.0F, 0.0F, 1.2F);
      this.Shape14.setTextureSize(64, 32);
      this.Shape14.mirror = true;
      this.setRotation(this.Shape14, 0.0F, 0.0F, 0.0F);
      this.Shape15 = new ModelRenderer(this, 43, 21);
      this.Shape15.addBox(0.0F, 0.0F, 0.0F, 1, 7, 4);
      this.Shape15.setRotationPoint(3.8F, 0.0F, -2.0F);
      this.Shape15.setTextureSize(64, 32);
      this.Shape15.mirror = true;
      this.setRotation(this.Shape15, 0.0F, 0.0F, 0.0F);
      this.Shape16 = new ModelRenderer(this, 30, 27);
      this.Shape16.addBox(0.0F, 0.0F, 0.0F, 2, 1, 4);
      this.Shape16.setRotationPoint(6.0F, -0.4F, -2.0F);
      this.Shape16.setTextureSize(64, 32);
      this.Shape16.mirror = true;
      this.setRotation(this.Shape16, 0.0F, 0.0F, 0.0F);
      this.Shape17 = new ModelRenderer(this, 19, 26);
      this.Shape17.addBox(0.0F, 0.0F, 0.0F, 1, 2, 4);
      this.Shape17.setRotationPoint(7.3F, 0.0F, -2.0F);
      this.Shape17.setTextureSize(64, 32);
      this.Shape17.mirror = true;
      this.setRotation(this.Shape17, 0.0F, 0.0F, -0.0698132F);
      this.Shape18 = new ModelRenderer(this, 19, 26);
      this.Shape18.addBox(0.0F, 0.0F, 0.0F, 1, 2, 4);
      this.Shape18.setRotationPoint(7.3F, 2.0F, -2.0F);
      this.Shape18.setTextureSize(64, 32);
      this.Shape18.mirror = true;
      this.setRotation(this.Shape18, 0.0F, 0.0F, -0.0698132F);
      this.Shape36 = new ModelRenderer(this, 50, 15);
      this.Shape36.addBox(0.0F, 0.0F, 0.0F, 3, 1, 4);
      this.Shape36.setRotationPoint(4.0F, 0.0F, -2.0F);
      this.Shape36.setTextureSize(64, 32);
      this.Shape36.mirror = true;
      this.setRotation(this.Shape36, 0.0F, 0.0F, 0.0F);
   }

   public void render(float f5) {
      this.Shape12.render(f5);
      this.Shape13.render(f5);
      this.Shape14.render(f5);
      this.Shape15.render(f5);
      this.Shape16.render(f5);
      this.Shape17.render(f5);
      this.Shape18.render(f5);
      this.Shape36.render(f5);
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
