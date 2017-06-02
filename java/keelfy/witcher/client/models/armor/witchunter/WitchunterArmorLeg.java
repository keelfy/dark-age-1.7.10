package keelfy.witcher.client.models.armor.witchunter;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class WitchunterArmorLeg extends ModelBase {

   ModelRenderer Shape33;
   ModelRenderer Shape34;
   ModelRenderer Shape35;


   public WitchunterArmorLeg() {
      this.textureWidth = 64;
      this.textureHeight = 32;
      this.Shape33 = new ModelRenderer(this, 11, 22);
      this.Shape33.addBox(0.0F, 0.0F, 0.0F, 4, 9, 1);
      this.Shape33.setRotationPoint(-4.0F, 12.0F, 1.4F);
      this.Shape33.setTextureSize(64, 32);
      this.Shape33.mirror = true;
      this.setRotation(this.Shape33, 0.0872665F, 0.0F, 0.0F);
      this.Shape34 = new ModelRenderer(this, 11, 19);
      this.Shape34.addBox(0.0F, 0.0F, 0.0F, 1, 9, 4);
      this.Shape34.setRotationPoint(-4.1F, 12.0F, -2.0F);
      this.Shape34.setTextureSize(64, 32);
      this.Shape34.mirror = true;
      this.setRotation(this.Shape34, 0.0F, 0.0F, 0.0872665F);
      this.Shape35 = new ModelRenderer(this, 22, 22);
      this.Shape35.addBox(0.0F, 0.0F, 0.0F, 2, 9, 1);
      this.Shape35.setRotationPoint(-4.0F, 12.0F, -2.4F);
      this.Shape35.setTextureSize(64, 32);
      this.Shape35.mirror = true;
      this.setRotation(this.Shape35, -0.0872665F, 0.0F, 0.0F);
   }

   public void render(float f5) {
      this.Shape33.render(f5);
      this.Shape34.render(f5);
      this.Shape35.render(f5);
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
