package keelfy.witcher.client.models.armor.magdeithra;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class MagDeithraHand extends ModelBase {

   ModelRenderer Shape30;
   ModelRenderer Shape31;
   ModelRenderer Shape32;
   ModelRenderer Shape33;
   ModelRenderer Shape34;
   ModelRenderer Shape35;
   ModelRenderer Shape36;
   ModelRenderer Shape61;
   ModelRenderer Shape62;
   ModelRenderer Shape63;
   ModelRenderer Shape64;
   ModelRenderer Shape65;
   ModelRenderer Shape66;
   ModelRenderer Shape67;
   ModelRenderer Shape68;
   ModelRenderer Shape69;


   public MagDeithraHand() {
      this.textureWidth = 64;
      this.textureHeight = 32;
      this.Shape30 = new ModelRenderer(this, 55, 0);
      this.Shape30.addBox(0.0F, 0.0F, 0.0F, 3, 2, 2);
      this.Shape30.setRotationPoint(4.0F, 0.0F, -2.2F);
      this.Shape30.setTextureSize(64, 32);
      this.Shape30.mirror = true;
      this.setRotation(this.Shape30, 0.0F, 0.0F, 0.0F);
      this.Shape31 = new ModelRenderer(this, 56, 0);
      this.Shape31.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2);
      this.Shape31.setRotationPoint(6.2F, 0.0F, -2.2F);
      this.Shape31.setTextureSize(64, 32);
      this.Shape31.mirror = true;
      this.setRotation(this.Shape31, 0.0F, 0.0F, 0.0F);
      this.Shape32 = new ModelRenderer(this, 55, 0);
      this.Shape32.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1);
      this.Shape32.setRotationPoint(4.0F, 0.0F, -0.5F);
      this.Shape32.setTextureSize(64, 32);
      this.Shape32.mirror = true;
      this.setRotation(this.Shape32, 0.0F, 0.0F, 0.0F);
      this.Shape33 = new ModelRenderer(this, 55, 0);
      this.Shape33.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1);
      this.Shape33.setRotationPoint(6.2F, 0.0F, -0.5F);
      this.Shape33.setTextureSize(64, 32);
      this.Shape33.mirror = true;
      this.setRotation(this.Shape33, 0.0F, 0.0F, 0.0F);
      this.Shape34 = new ModelRenderer(this, 55, 0);
      this.Shape34.addBox(0.0F, 0.0F, 0.0F, 3, 2, 2);
      this.Shape34.setRotationPoint(4.0F, 0.0F, 0.0F);
      this.Shape34.setTextureSize(64, 32);
      this.Shape34.mirror = true;
      this.setRotation(this.Shape34, 0.0F, 0.0F, 0.0F);
      this.Shape35 = new ModelRenderer(this, 56, 0);
      this.Shape35.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2);
      this.Shape35.setRotationPoint(6.2F, 0.0F, 0.0F);
      this.Shape35.setTextureSize(64, 32);
      this.Shape35.mirror = true;
      this.setRotation(this.Shape35, 0.0F, 0.0F, 0.0F);
      this.Shape36 = new ModelRenderer(this, 38, 0);
      this.Shape36.addBox(0.0F, 0.0F, 0.0F, 4, 5, 4);
      this.Shape36.setRotationPoint(4.0F, 2.0F, -2.0F);
      this.Shape36.setTextureSize(64, 32);
      this.Shape36.mirror = true;
      this.setRotation(this.Shape36, 0.0F, 0.0F, 0.0F);
      this.Shape61 = new ModelRenderer(this, 0, 4);
      this.Shape61.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
      this.Shape61.setRotationPoint(4.2F, 2.0F, -2.2F);
      this.Shape61.setTextureSize(64, 32);
      this.Shape61.mirror = true;
      this.setRotation(this.Shape61, 0.0F, 0.0F, 0.0F);
      this.Shape62 = new ModelRenderer(this, 0, 4);
      this.Shape62.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
      this.Shape62.setRotationPoint(5.7F, 2.0F, -2.2F);
      this.Shape62.setTextureSize(64, 32);
      this.Shape62.mirror = true;
      this.setRotation(this.Shape62, 0.0F, 0.0F, 0.0F);
      this.Shape63 = new ModelRenderer(this, 0, 4);
      this.Shape63.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
      this.Shape63.setRotationPoint(7.0F, 2.0F, -2.2F);
      this.Shape63.setTextureSize(64, 32);
      this.Shape63.mirror = true;
      this.setRotation(this.Shape63, 0.0F, 0.0F, 0.0F);
      this.Shape64 = new ModelRenderer(this, 0, 4);
      this.Shape64.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
      this.Shape64.setRotationPoint(7.2F, 2.0F, -1.8F);
      this.Shape64.setTextureSize(64, 32);
      this.Shape64.mirror = true;
      this.setRotation(this.Shape64, 0.0F, 0.0F, 0.0F);
      this.Shape65 = new ModelRenderer(this, 0, 4);
      this.Shape65.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
      this.Shape65.setRotationPoint(7.2F, 2.0F, -0.5F);
      this.Shape65.setTextureSize(64, 32);
      this.Shape65.mirror = true;
      this.setRotation(this.Shape65, 0.0F, 0.0F, 0.0F);
      this.Shape66 = new ModelRenderer(this, 0, 4);
      this.Shape66.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
      this.Shape66.setRotationPoint(7.2F, 2.0F, 1.0F);
      this.Shape66.setTextureSize(64, 32);
      this.Shape66.mirror = true;
      this.setRotation(this.Shape66, 0.0F, 0.0F, 0.0F);
      this.Shape67 = new ModelRenderer(this, 0, 4);
      this.Shape67.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
      this.Shape67.setRotationPoint(7.0F, 2.0F, 1.2F);
      this.Shape67.setTextureSize(64, 32);
      this.Shape67.mirror = true;
      this.setRotation(this.Shape67, 0.0F, 0.0F, 0.0F);
      this.Shape68 = new ModelRenderer(this, 0, 4);
      this.Shape68.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
      this.Shape68.setRotationPoint(5.6F, 2.0F, 1.2F);
      this.Shape68.setTextureSize(64, 32);
      this.Shape68.mirror = true;
      this.setRotation(this.Shape68, 0.0F, 0.0F, 0.0F);
      this.Shape69 = new ModelRenderer(this, 0, 4);
      this.Shape69.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
      this.Shape69.setRotationPoint(4.2F, 2.0F, 1.2F);
      this.Shape69.setTextureSize(64, 32);
      this.Shape69.mirror = true;
      this.setRotation(this.Shape69, 0.0F, 0.0F, 0.0F);
   }

   public void render(float f5) {
      this.Shape30.render(f5);
      this.Shape31.render(f5);
      this.Shape32.render(f5);
      this.Shape33.render(f5);
      this.Shape34.render(f5);
      this.Shape35.render(f5);
      this.Shape36.render(f5);
      this.Shape61.render(f5);
      this.Shape62.render(f5);
      this.Shape63.render(f5);
      this.Shape64.render(f5);
      this.Shape65.render(f5);
      this.Shape66.render(f5);
      this.Shape67.render(f5);
      this.Shape68.render(f5);
      this.Shape69.render(f5);
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
