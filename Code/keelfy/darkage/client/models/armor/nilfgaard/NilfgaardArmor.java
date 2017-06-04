package keelfy.darkage.client.models.armor.nilfgaard;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class NilfgaardArmor  extends ModelBase {

   ModelRenderer Shape24;
   ModelRenderer Shape53;
   ModelRenderer Shape54;
   ModelRenderer Shape55;
   ModelRenderer Shape56;
   ModelRenderer Shape60;
   ModelRenderer Shape61;
   ModelRenderer Shape62;
   ModelRenderer Shape63;
   ModelRenderer Shape64;
   ModelRenderer Shape65;
   ModelRenderer Shape66;
   ModelRenderer Shape67;
   ModelRenderer Shape68;


   public NilfgaardArmor() {
      this.textureWidth = 64;
      this.textureHeight = 32;
      this.Shape24 = new ModelRenderer(this, 36, 0);
      this.Shape24.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4);
      this.Shape24.setRotationPoint(3.8F, 11.0F, -2.0F);
      this.Shape24.setTextureSize(64, 32);
      this.Shape24.mirror = true;
      this.setRotation(this.Shape24, 0.0F, 0.0F, 0.0F);
      this.Shape53 = new ModelRenderer(this, 46, 20);
      this.Shape53.addBox(0.0F, 0.0F, 0.0F, 8, 11, 1);
      this.Shape53.setRotationPoint(-4.0F, 0.0F, -3.0F);
      this.Shape53.setTextureSize(64, 32);
      this.Shape53.mirror = true;
      this.setRotation(this.Shape53, 0.0F, 0.0F, 0.0F);
      this.Shape54 = new ModelRenderer(this, 36, 0);
      this.Shape54.addBox(0.0F, 0.0F, 0.0F, 8, 11, 1);
      this.Shape54.setRotationPoint(-4.0F, 0.0F, 2.0F);
      this.Shape54.setTextureSize(64, 32);
      this.Shape54.mirror = true;
      this.setRotation(this.Shape54, 0.0F, 0.0F, 0.0F);
      this.Shape55 = new ModelRenderer(this, 55, 0);
      this.Shape55.addBox(0.0F, 0.0F, 0.0F, 1, 11, 4);
      this.Shape55.setRotationPoint(-4.0F, 0.0F, -2.0F);
      this.Shape55.setTextureSize(64, 32);
      this.Shape55.mirror = true;
      this.setRotation(this.Shape55, 0.0F, 0.0F, 0.0F);
      this.Shape56 = new ModelRenderer(this, 55, 0);
      this.Shape56.addBox(0.0F, 0.0F, 0.0F, 1, 11, 4);
      this.Shape56.setRotationPoint(3.0F, 0.0F, -2.0F);
      this.Shape56.setTextureSize(64, 32);
      this.Shape56.mirror = true;
      this.setRotation(this.Shape56, 0.0F, 0.0F, 0.0F);
      this.Shape60 = new ModelRenderer(this, 21, 0);
      this.Shape60.addBox(0.0F, 0.0F, 0.0F, 6, 2, 1);
      this.Shape60.setRotationPoint(-3.0F, -0.6F, -4.4F);
      this.Shape60.setTextureSize(64, 32);
      this.Shape60.mirror = true;
      this.setRotation(this.Shape60, 0.5759587F, 0.0F, 0.0F);
      this.Shape61 = new ModelRenderer(this, 21, 1);
      this.Shape61.addBox(0.0F, 0.0F, 0.0F, 6, 1, 1);
      this.Shape61.setRotationPoint(-3.0F, -1.5F, -4.3F);
      this.Shape61.setTextureSize(64, 32);
      this.Shape61.mirror = true;
      this.setRotation(this.Shape61, 0.0F, 0.0F, 0.0F);
      this.Shape62 = new ModelRenderer(this, 13, 30);
      this.Shape62.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1);
      this.Shape62.setRotationPoint(-3.6F, 0.0F, -3.3F);
      this.Shape62.setTextureSize(64, 32);
      this.Shape62.mirror = true;
      this.setRotation(this.Shape62, 0.0F, 0.0F, 0.5061455F);
      this.Shape63 = new ModelRenderer(this, 13, 30);
      this.Shape63.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1);
      this.Shape63.setRotationPoint(0.0F, 1.9F, -3.3F);
      this.Shape63.setTextureSize(64, 32);
      this.Shape63.mirror = true;
      this.setRotation(this.Shape63, 0.0F, 0.0F, -0.4363323F);
      this.Shape64 = new ModelRenderer(this, 16, 30);
      this.Shape64.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
      this.Shape64.setRotationPoint(-0.6F, 1.8F, -3.3F);
      this.Shape64.setTextureSize(64, 32);
      this.Shape64.mirror = true;
      this.setRotation(this.Shape64, 0.0F, 0.0F, 0.0F);
      this.Shape65 = new ModelRenderer(this, 36, 2);
      this.Shape65.addBox(0.0F, 0.0F, 0.0F, 8, 1, 1);
      this.Shape65.setRotationPoint(-4.0F, 10.6F, -2.8F);
      this.Shape65.setTextureSize(64, 32);
      this.Shape65.mirror = true;
      this.setRotation(this.Shape65, -0.3490659F, 0.0F, 0.0F);
      this.Shape66 = new ModelRenderer(this, 36, 2);
      this.Shape66.addBox(0.0F, 0.0F, 0.0F, 8, 1, 1);
      this.Shape66.setRotationPoint(-4.0F, 11.0F, 2.0F);
      this.Shape66.setTextureSize(64, 32);
      this.Shape66.mirror = true;
      this.setRotation(this.Shape66, 0.3490659F, 0.0F, 0.0F);
      this.Shape67 = new ModelRenderer(this, 36, 0);
      this.Shape67.addBox(0.0F, 0.0F, 0.0F, 1, 1, 5);
      this.Shape67.setRotationPoint(-4.0F, 10.5F, -2.5F);
      this.Shape67.setTextureSize(64, 32);
      this.Shape67.mirror = true;
      this.setRotation(this.Shape67, 0.0F, 0.0F, 0.6981317F);
      this.Shape68 = new ModelRenderer(this, 36, 0);
      this.Shape68.addBox(0.0F, 0.0F, 0.0F, 1, 1, 5);
      this.Shape68.setRotationPoint(4.0F, 10.5F, -2.5F);
      this.Shape68.setTextureSize(64, 32);
      this.Shape68.mirror = true;
      this.setRotation(this.Shape68, 0.0F, 0.0F, 0.6981317F);
   }

   public void render(float f5) {
      this.Shape24.render(f5);
      this.Shape53.render(f5);
      this.Shape54.render(f5);
      this.Shape55.render(f5);
      this.Shape56.render(f5);
      this.Shape60.render(f5);
      this.Shape61.render(f5);
      this.Shape62.render(f5);
      this.Shape63.render(f5);
      this.Shape64.render(f5);
      this.Shape65.render(f5);
      this.Shape66.render(f5);
      this.Shape67.render(f5);
      this.Shape68.render(f5);
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
