package keelfy.darkage.client.models.armor.talgar;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class TalgarBody  extends ModelBase {

   ModelRenderer Shape28;
   ModelRenderer Shape29;
   ModelRenderer Shape30;
   ModelRenderer Shape31;
   ModelRenderer Shape32;
   ModelRenderer Shape33;
   ModelRenderer Shape34;
   ModelRenderer Shape35;


   public TalgarBody() {
      this.textureWidth = 64;
      this.textureHeight = 32;
      this.Shape28 = new ModelRenderer(this, 46, 0);
      this.Shape28.addBox(0.0F, 0.0F, 0.0F, 8, 4, 1);
      this.Shape28.setRotationPoint(-4.0F, 0.0F, -2.0F);
      this.Shape28.setTextureSize(64, 32);
      this.Shape28.mirror = true;
      this.setRotation(this.Shape28, -0.1570796F, 0.0F, 0.0F);
      this.Shape29 = new ModelRenderer(this, 46, 0);
      this.Shape29.addBox(0.0F, 0.0F, 0.0F, 8, 4, 1);
      this.Shape29.setRotationPoint(-4.0F, 7.9F, 1.6F);
      this.Shape29.setTextureSize(64, 32);
      this.Shape29.mirror = true;
      this.setRotation(this.Shape29, -0.1396263F, 0.0F, 0.0F);
      this.Shape30 = new ModelRenderer(this, 46, 0);
      this.Shape30.addBox(0.0F, 0.0F, 0.0F, 8, 4, 1);
      this.Shape30.setRotationPoint(-4.0F, 8.0F, -2.6F);
      this.Shape30.setTextureSize(64, 32);
      this.Shape30.mirror = true;
      this.setRotation(this.Shape30, 0.1570796F, 0.0F, 0.0F);
      this.Shape31 = new ModelRenderer(this, 46, 0);
      this.Shape31.addBox(0.0F, 0.0F, 0.0F, 8, 4, 1);
      this.Shape31.setRotationPoint(-4.0F, 0.2F, 1.0F);
      this.Shape31.setTextureSize(64, 32);
      this.Shape31.mirror = true;
      this.setRotation(this.Shape31, 0.1570796F, 0.0F, 0.0F);
      this.Shape32 = new ModelRenderer(this, 46, 0);
      this.Shape32.addBox(0.0F, 0.0F, 0.0F, 8, 4, 1);
      this.Shape32.setRotationPoint(-4.0F, 4.0F, -2.6F);
      this.Shape32.setTextureSize(64, 32);
      this.Shape32.mirror = true;
      this.setRotation(this.Shape32, 0.0F, 0.0F, 0.0F);
      this.Shape33 = new ModelRenderer(this, 46, 0);
      this.Shape33.addBox(0.0F, 0.0F, 0.0F, 8, 4, 1);
      this.Shape33.setRotationPoint(-4.0F, 4.0F, 1.6F);
      this.Shape33.setTextureSize(64, 32);
      this.Shape33.mirror = true;
      this.setRotation(this.Shape33, 0.0F, 0.0F, 0.0F);
      this.Shape34 = new ModelRenderer(this, 35, 0);
      this.Shape34.addBox(0.0F, 0.0F, 0.0F, 1, 12, 4);
      this.Shape34.setRotationPoint(3.0F, 0.0F, -2.0F);
      this.Shape34.setTextureSize(64, 32);
      this.Shape34.mirror = true;
      this.setRotation(this.Shape34, 0.0F, 0.0F, 0.0F);
      this.Shape35 = new ModelRenderer(this, 35, 0);
      this.Shape35.addBox(0.0F, 0.0F, 0.0F, 1, 12, 4);
      this.Shape35.setRotationPoint(-4.0F, 0.0F, -2.0F);
      this.Shape35.setTextureSize(64, 32);
      this.Shape35.mirror = true;
      this.setRotation(this.Shape35, 0.0F, 0.0F, 0.0F);
   }

   public void render(float f5) {
      this.Shape28.render(f5);
      this.Shape29.render(f5);
      this.Shape30.render(f5);
      this.Shape31.render(f5);
      this.Shape32.render(f5);
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
