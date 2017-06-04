package keelfy.darkage.client.models.armor.magdeithra;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class MagDeithraLegAddon  extends ModelBase {

   ModelRenderer Shape39;
   ModelRenderer Shape40;
   ModelRenderer Shape41;
   ModelRenderer Shape42;
   ModelRenderer Shape51;
   ModelRenderer Shape52;
   ModelRenderer Shape53;
   ModelRenderer Shape54;
   ModelRenderer Shape55;
   ModelRenderer Shape56;


   public MagDeithraLegAddon() {
      this.textureWidth = 64;
      this.textureHeight = 32;
      this.Shape39 = new ModelRenderer(this, 27, 26);
      this.Shape39.addBox(0.0F, 0.0F, 0.0F, 5, 1, 5);
      this.Shape39.setRotationPoint(-4.4F, 10.7F, -2.5F);
      this.Shape39.setTextureSize(64, 32);
      this.Shape39.mirror = true;
      this.setRotation(this.Shape39, 0.0F, 0.0F, 0.1396263F);
      this.Shape40 = new ModelRenderer(this, 0, 0);
      this.Shape40.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
      this.Shape40.setRotationPoint(-4.0F, 10.8F, -3.0F);
      this.Shape40.setTextureSize(64, 32);
      this.Shape40.mirror = true;
      this.setRotation(this.Shape40, 0.0F, 0.0F, 0.1396263F);
      this.Shape41 = new ModelRenderer(this, 0, 0);
      this.Shape41.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
      this.Shape41.setRotationPoint(-2.5F, 11.0F, -3.0F);
      this.Shape41.setTextureSize(64, 32);
      this.Shape41.mirror = true;
      this.setRotation(this.Shape41, 0.0F, 0.0F, 0.1396263F);
      this.Shape42 = new ModelRenderer(this, 0, 0);
      this.Shape42.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
      this.Shape42.setRotationPoint(-0.8F, 11.2F, -3.0F);
      this.Shape42.setTextureSize(64, 32);
      this.Shape42.mirror = true;
      this.setRotation(this.Shape42, 0.0F, 0.0F, 0.1396263F);
      this.Shape51 = new ModelRenderer(this, 0, 0);
      this.Shape51.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
      this.Shape51.setRotationPoint(-0.8F, 11.2F, 2.0F);
      this.Shape51.setTextureSize(64, 32);
      this.Shape51.mirror = true;
      this.setRotation(this.Shape51, 0.0F, 0.0F, 0.1396263F);
      this.Shape52 = new ModelRenderer(this, 0, 0);
      this.Shape52.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
      this.Shape52.setRotationPoint(-2.5F, 11.0F, 2.0F);
      this.Shape52.setTextureSize(64, 32);
      this.Shape52.mirror = true;
      this.setRotation(this.Shape52, 0.0F, 0.0F, 0.1396263F);
      this.Shape53 = new ModelRenderer(this, 0, 0);
      this.Shape53.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
      this.Shape53.setRotationPoint(-4.0F, 10.8F, 2.0F);
      this.Shape53.setTextureSize(64, 32);
      this.Shape53.mirror = true;
      this.setRotation(this.Shape53, 0.0F, 0.0F, 0.1396263F);
      this.Shape54 = new ModelRenderer(this, 0, 0);
      this.Shape54.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
      this.Shape54.setRotationPoint(-5.0F, 10.6F, -2.0F);
      this.Shape54.setTextureSize(64, 32);
      this.Shape54.mirror = true;
      this.setRotation(this.Shape54, 0.0F, 0.0F, 0.1396263F);
      this.Shape55 = new ModelRenderer(this, 0, 0);
      this.Shape55.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
      this.Shape55.setRotationPoint(-5.0F, 10.6F, -0.5F);
      this.Shape55.setTextureSize(64, 32);
      this.Shape55.mirror = true;
      this.setRotation(this.Shape55, 0.0F, 0.0F, 0.1396263F);
      this.Shape56 = new ModelRenderer(this, 0, 0);
      this.Shape56.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
      this.Shape56.setRotationPoint(-5.0F, 10.6F, 1.0F);
      this.Shape56.setTextureSize(64, 32);
      this.Shape56.mirror = true;
      this.setRotation(this.Shape56, 0.0F, 0.0F, 0.1396263F);
   }

   public void render(float f5) {
      this.Shape39.render(f5);
      this.Shape40.render(f5);
      this.Shape41.render(f5);
      this.Shape42.render(f5);
      this.Shape51.render(f5);
      this.Shape52.render(f5);
      this.Shape53.render(f5);
      this.Shape54.render(f5);
      this.Shape55.render(f5);
      this.Shape56.render(f5);
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
