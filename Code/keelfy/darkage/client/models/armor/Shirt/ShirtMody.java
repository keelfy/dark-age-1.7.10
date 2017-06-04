package keelfy.darkage.client.models.armor.Shirt;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ShirtMody  extends ModelBase {

   ModelRenderer Shape0;
   ModelRenderer Shape1;
   ModelRenderer Shape7;
   ModelRenderer Shape8;
   ModelRenderer Shape9;
   ModelRenderer Shape10;
   ModelRenderer Shape23;
   ModelRenderer Shape24;


   public ShirtMody() {
      this.textureWidth = 64;
      this.textureHeight = 32;
      this.Shape0 = new ModelRenderer(this, 46, 0);
      this.Shape0.addBox(0.0F, 0.0F, 0.0F, 8, 12, 1);
      this.Shape0.setRotationPoint(-4.0F, 0.0F, -2.1F);
      this.Shape0.setTextureSize(64, 32);
      this.Shape0.mirror = true;
      this.setRotation(this.Shape0, 0.0F, 0.0F, 0.0F);
      this.Shape1 = new ModelRenderer(this, 46, 0);
      this.Shape1.addBox(0.0F, 0.0F, 0.0F, 8, 12, 1);
      this.Shape1.setRotationPoint(-4.0F, 0.0F, 1.1F);
      this.Shape1.setTextureSize(64, 32);
      this.Shape1.mirror = true;
      this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
      this.Shape7 = new ModelRenderer(this, 35, 0);
      this.Shape7.addBox(0.0F, 0.0F, 0.0F, 1, 12, 4);
      this.Shape7.setRotationPoint(3.0F, 0.0F, -2.0F);
      this.Shape7.setTextureSize(64, 32);
      this.Shape7.mirror = true;
      this.setRotation(this.Shape7, 0.0F, 0.0F, 0.0F);
      this.Shape8 = new ModelRenderer(this, 35, 0);
      this.Shape8.addBox(0.0F, 0.0F, 0.0F, 1, 12, 4);
      this.Shape8.setRotationPoint(-4.0F, 0.0F, -2.0F);
      this.Shape8.setTextureSize(64, 32);
      this.Shape8.mirror = true;
      this.setRotation(this.Shape8, 0.0F, 0.0F, 0.0F);
      this.Shape9 = new ModelRenderer(this, 5, 30);
      this.Shape9.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
      this.Shape9.setRotationPoint(2.0F, 0.0F, -2.2F);
      this.Shape9.setTextureSize(64, 32);
      this.Shape9.mirror = true;
      this.setRotation(this.Shape9, 0.0F, 0.0F, 0.0F);
      this.Shape10 = new ModelRenderer(this, 5, 30);
      this.Shape10.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
      this.Shape10.setRotationPoint(-3.0F, 0.0F, -2.2F);
      this.Shape10.setTextureSize(64, 32);
      this.Shape10.mirror = true;
      this.setRotation(this.Shape10, 0.0F, 0.0F, 0.0F);
      this.Shape23 = new ModelRenderer(this, 0, 0);
      this.Shape23.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1);
      this.Shape23.setRotationPoint(-3.0F, 0.0F, -2.3F);
      this.Shape23.setTextureSize(64, 32);
      this.Shape23.mirror = true;
      this.setRotation(this.Shape23, 0.0F, 0.0F, -0.0523599F);
      this.Shape24 = new ModelRenderer(this, 0, 0);
      this.Shape24.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1);
      this.Shape24.setRotationPoint(2.0F, 0.0F, -2.3F);
      this.Shape24.setTextureSize(64, 32);
      this.Shape24.mirror = true;
      this.setRotation(this.Shape24, 0.0F, 0.0F, 0.0523599F);
   }

   public void render(float f5) {
      this.Shape0.render(f5);
      this.Shape1.render(f5);
      this.Shape7.render(f5);
      this.Shape8.render(f5);
      this.Shape9.render(f5);
      this.Shape10.render(f5);
      this.Shape23.render(f5);
      this.Shape24.render(f5);
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
