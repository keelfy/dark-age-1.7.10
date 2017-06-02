package keelfy.witcher.client.models.armor.witchunter;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class WitchunterArmorHand extends ModelBase {

   ModelRenderer Shape18;
   ModelRenderer Shape19;
   ModelRenderer Shape20;
   ModelRenderer Shape21;
   ModelRenderer Shape22;
   ModelRenderer Shape23;
   ModelRenderer Shape24;
   ModelRenderer Shape25;
   ModelRenderer Shape26;
   ModelRenderer Shape27;
   ModelRenderer Shape28;
   ModelRenderer Shape29;
   ModelRenderer Shape30;
   ModelRenderer Shape31;
   ModelRenderer Shape32;
   ModelRenderer Shape45;


   public WitchunterArmorHand() {
      this.textureWidth = 64;
      this.textureHeight = 32;
      this.Shape18 = new ModelRenderer(this, 13, 0);
      this.Shape18.addBox(0.0F, 0.0F, 0.0F, 4, 5, 1);
      this.Shape18.setRotationPoint(-8.0F, 0.0F, 1.2F);
      this.Shape18.setTextureSize(64, 32);
      this.Shape18.mirror = true;
      this.setRotation(this.Shape18, 0.0F, 0.0F, 0.0F);
      this.Shape19 = new ModelRenderer(this, 13, 0);
      this.Shape19.addBox(0.0F, 0.0F, 0.0F, 4, 5, 1);
      this.Shape19.setRotationPoint(-8.0F, 0.0F, -2.2F);
      this.Shape19.setTextureSize(64, 32);
      this.Shape19.mirror = true;
      this.setRotation(this.Shape19, 0.0F, 0.0F, 0.0F);
      this.Shape20 = new ModelRenderer(this, 13, 7);
      this.Shape20.addBox(0.0F, 0.0F, 0.0F, 1, 5, 4);
      this.Shape20.setRotationPoint(-8.2F, 0.0F, -2.0F);
      this.Shape20.setTextureSize(64, 32);
      this.Shape20.mirror = true;
      this.setRotation(this.Shape20, 0.0F, 0.0F, 0.0F);
      this.Shape21 = new ModelRenderer(this, 13, 7);
      this.Shape21.addBox(0.0F, 0.0F, 0.0F, 1, 5, 4);
      this.Shape21.setRotationPoint(-4.8F, 0.0F, -2.0F);
      this.Shape21.setTextureSize(64, 32);
      this.Shape21.mirror = true;
      this.setRotation(this.Shape21, 0.0F, 0.0F, 0.0F);
      this.Shape22 = new ModelRenderer(this, 0, 30);
      this.Shape22.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1);
      this.Shape22.setRotationPoint(-8.0F, 5.6F, 1.2F);
      this.Shape22.setTextureSize(64, 32);
      this.Shape22.mirror = true;
      this.setRotation(this.Shape22, 0.7853982F, 0.0F, 0.0F);
      this.Shape23 = new ModelRenderer(this, 0, 30);
      this.Shape23.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1);
      this.Shape23.setRotationPoint(-8.0F, 6.8F, 1.2F);
      this.Shape23.setTextureSize(64, 32);
      this.Shape23.mirror = true;
      this.setRotation(this.Shape23, 0.7853982F, 0.0F, 0.0F);
      this.Shape24 = new ModelRenderer(this, 0, 25);
      this.Shape24.addBox(0.0F, 0.0F, 0.0F, 1, 1, 3);
      this.Shape24.setRotationPoint(-8.0F, 4.8F, -1.0F);
      this.Shape24.setTextureSize(64, 32);
      this.Shape24.mirror = true;
      this.setRotation(this.Shape24, 0.0F, 0.0F, 0.7853982F);
      this.Shape25 = new ModelRenderer(this, 0, 25);
      this.Shape25.addBox(0.0F, 0.0F, 0.0F, 1, 1, 3);
      this.Shape25.setRotationPoint(-8.0F, 6.0F, -1.0F);
      this.Shape25.setTextureSize(64, 32);
      this.Shape25.mirror = true;
      this.setRotation(this.Shape25, 0.0F, 0.0F, 0.7853982F);
      this.Shape26 = new ModelRenderer(this, 0, 25);
      this.Shape26.addBox(0.0F, 0.0F, 0.0F, 1, 1, 3);
      this.Shape26.setRotationPoint(-4.0F, 4.8F, -1.0F);
      this.Shape26.setTextureSize(64, 32);
      this.Shape26.mirror = true;
      this.setRotation(this.Shape26, 0.0F, 0.0F, 0.7853982F);
      this.Shape27 = new ModelRenderer(this, 0, 25);
      this.Shape27.addBox(0.0F, 0.0F, 0.0F, 1, 1, 3);
      this.Shape27.setRotationPoint(-4.0F, 6.0F, -1.0F);
      this.Shape27.setTextureSize(64, 32);
      this.Shape27.mirror = true;
      this.setRotation(this.Shape27, 0.0F, 0.0F, 0.7853982F);
      this.Shape28 = new ModelRenderer(this, 31, 30);
      this.Shape28.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
      this.Shape28.setRotationPoint(-8.2F, 5.7F, -2.0F);
      this.Shape28.setTextureSize(64, 32);
      this.Shape28.mirror = true;
      this.setRotation(this.Shape28, 0.0F, 0.0F, 0.0F);
      this.Shape29 = new ModelRenderer(this, 31, 30);
      this.Shape29.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
      this.Shape29.setRotationPoint(-4.8F, 5.7F, -2.0F);
      this.Shape29.setTextureSize(64, 32);
      this.Shape29.mirror = true;
      this.setRotation(this.Shape29, 0.0F, 0.0F, 0.0F);
      this.Shape30 = new ModelRenderer(this, 32, 30);
      this.Shape30.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
      this.Shape30.setRotationPoint(-4.8F, 5.7F, -2.3F);
      this.Shape30.setTextureSize(64, 32);
      this.Shape30.mirror = true;
      this.setRotation(this.Shape30, 0.0F, 0.0F, 0.0F);
      this.Shape31 = new ModelRenderer(this, 31, 30);
      this.Shape31.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
      this.Shape31.setRotationPoint(-8.2F, 5.7F, -2.3F);
      this.Shape31.setTextureSize(64, 32);
      this.Shape31.mirror = true;
      this.setRotation(this.Shape31, 0.0F, 0.0F, 0.0F);
      this.Shape32 = new ModelRenderer(this, 29, 30);
      this.Shape32.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1);
      this.Shape32.setRotationPoint(-8.0F, 5.7F, -2.3F);
      this.Shape32.setTextureSize(64, 32);
      this.Shape32.mirror = true;
      this.setRotation(this.Shape32, 0.0F, 0.0F, 0.0F);
      this.Shape45 = new ModelRenderer(this, 48, 27);
      this.Shape45.addBox(0.0F, 0.0F, 0.0F, 4, 1, 4);
      this.Shape45.setRotationPoint(-8.0F, 0.0F, -2.0F);
      this.Shape45.setTextureSize(64, 32);
      this.Shape45.mirror = true;
      this.setRotation(this.Shape45, 0.0F, 0.0F, 0.0F);
   }

   public void render(float f5) {
      this.Shape18.render(f5);
      this.Shape19.render(f5);
      this.Shape20.render(f5);
      this.Shape21.render(f5);
      this.Shape22.render(f5);
      this.Shape23.render(f5);
      this.Shape24.render(f5);
      this.Shape25.render(f5);
      this.Shape26.render(f5);
      this.Shape27.render(f5);
      this.Shape28.render(f5);
      this.Shape29.render(f5);
      this.Shape30.render(f5);
      this.Shape31.render(f5);
      this.Shape32.render(f5);
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
