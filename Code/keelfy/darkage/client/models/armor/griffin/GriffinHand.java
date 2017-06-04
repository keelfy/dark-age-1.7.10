package keelfy.darkage.client.models.armor.griffin;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class GriffinHand  extends ModelBase {

   ModelRenderer Shape17;
   ModelRenderer Shape18;
   ModelRenderer Shape19;
   ModelRenderer Shape20;
   ModelRenderer Shape21;
   ModelRenderer Shape22;


   public GriffinHand() {
      this.textureWidth = 64;
      this.textureHeight = 32;
      this.Shape17 = new ModelRenderer(this, 17, 27);
      this.Shape17.addBox(0.0F, 0.0F, 0.0F, 4, 1, 4);
      this.Shape17.setRotationPoint(4.0F, -0.1F, -2.0F);
      this.Shape17.setTextureSize(64, 32);
      this.Shape17.mirror = true;
      this.setRotation(this.Shape17, 0.0F, 0.0F, 0.0F);
      this.Shape18 = new ModelRenderer(this, 0, 14);
      this.Shape18.addBox(0.0F, 0.0F, 0.0F, 1, 3, 4);
      this.Shape18.setRotationPoint(7.1F, 0.0F, -2.0F);
      this.Shape18.setTextureSize(64, 32);
      this.Shape18.mirror = true;
      this.setRotation(this.Shape18, 0.0F, 0.0F, 0.0F);
      this.Shape19 = new ModelRenderer(this, 0, 17);
      this.Shape19.addBox(0.0F, 0.0F, 0.0F, 4, 3, 1);
      this.Shape19.setRotationPoint(4.0F, 0.0F, -2.1F);
      this.Shape19.setTextureSize(64, 32);
      this.Shape19.mirror = true;
      this.setRotation(this.Shape19, 0.0F, 0.0F, 0.0F);
      this.Shape20 = new ModelRenderer(this, 0, 17);
      this.Shape20.addBox(0.0F, 0.0F, 0.0F, 4, 3, 1);
      this.Shape20.setRotationPoint(4.0F, 0.0F, 1.1F);
      this.Shape20.setTextureSize(64, 32);
      this.Shape20.mirror = true;
      this.setRotation(this.Shape20, 0.0F, 0.0F, 0.0F);
      this.Shape21 = new ModelRenderer(this, 22, 0);
      this.Shape21.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1);
      this.Shape21.setRotationPoint(7.2F, -0.3F, -0.5F);
      this.Shape21.setTextureSize(64, 32);
      this.Shape21.mirror = true;
      this.setRotation(this.Shape21, 0.0F, 0.0F, 0.0F);
      this.Shape22 = new ModelRenderer(this, 0, 22);
      this.Shape22.addBox(0.0F, 0.0F, 0.0F, 4, 6, 4);
      this.Shape22.setRotationPoint(4.0F, 3.0F, -2.0F);
      this.Shape22.setTextureSize(64, 32);
      this.Shape22.mirror = true;
      this.setRotation(this.Shape22, 0.0F, 0.0F, 0.0F);
   }

   public void render(float f5) {
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
