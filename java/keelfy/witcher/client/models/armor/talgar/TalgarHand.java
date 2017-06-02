package keelfy.witcher.client.models.armor.talgar;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class TalgarHand extends ModelBase {

   ModelRenderer Shape0;
   ModelRenderer Shape1;
   ModelRenderer Shape2;
   ModelRenderer Shape3;
   ModelRenderer Shape4;
   ModelRenderer Shape5;
   ModelRenderer Shape6;
   ModelRenderer Shape7;
   ModelRenderer Shape42;
   ModelRenderer Shape43;
   ModelRenderer Shape44;
   ModelRenderer Shape45;
   ModelRenderer Shape46;
   ModelRenderer Shape47;
   ModelRenderer Shape48;
   ModelRenderer Shape49;
   ModelRenderer Shape50;


   public TalgarHand() {
      this.textureWidth = 64;
      this.textureHeight = 32;
      this.Shape0 = new ModelRenderer(this, 23, 0);
      this.Shape0.addBox(0.0F, 0.0F, 0.0F, 4, 8, 1);
      this.Shape0.setRotationPoint(4.0F, 1.0F, -2.2F);
      this.Shape0.setTextureSize(64, 32);
      this.Shape0.mirror = true;
      this.setRotation(this.Shape0, 0.0174533F, 0.0F, 0.0F);
      this.Shape1 = new ModelRenderer(this, 23, 0);
      this.Shape1.addBox(0.0F, 0.0F, 0.0F, 4, 8, 1);
      this.Shape1.setRotationPoint(4.0F, 1.0F, 1.2F);
      this.Shape1.setTextureSize(64, 32);
      this.Shape1.mirror = true;
      this.setRotation(this.Shape1, -0.0174533F, 0.0F, 0.0F);
      this.Shape2 = new ModelRenderer(this, 23, 0);
      this.Shape2.addBox(0.0F, 0.0F, 0.0F, 4, 8, 1);
      this.Shape2.setRotationPoint(7.2F, 1.0F, 2.0F);
      this.Shape2.setTextureSize(64, 32);
      this.Shape2.mirror = true;
      this.setRotation(this.Shape2, -0.0174533F, 1.570796F, 0.0F);
      this.Shape3 = new ModelRenderer(this, 23, 0);
      this.Shape3.addBox(0.0F, 0.0F, 0.0F, 4, 8, 1);
      this.Shape3.setRotationPoint(3.9F, 1.0F, 2.0F);
      this.Shape3.setTextureSize(64, 32);
      this.Shape3.mirror = true;
      this.setRotation(this.Shape3, 0.0F, 1.570796F, 0.0F);
      this.Shape4 = new ModelRenderer(this, 19, 10);
      this.Shape4.addBox(0.0F, 0.0F, 0.0F, 4, 1, 4);
      this.Shape4.setRotationPoint(4.0F, -1.0F, -2.0F);
      this.Shape4.setTextureSize(64, 32);
      this.Shape4.mirror = true;
      this.setRotation(this.Shape4, 0.0F, 0.0F, 0.2443461F);
      this.Shape5 = new ModelRenderer(this, 22, 13);
      this.Shape5.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1);
      this.Shape5.setRotationPoint(4.0F, 0.0F, -2.0F);
      this.Shape5.setTextureSize(64, 32);
      this.Shape5.mirror = true;
      this.setRotation(this.Shape5, -0.3665191F, 0.0F, 0.0F);
      this.Shape6 = new ModelRenderer(this, 22, 13);
      this.Shape6.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1);
      this.Shape6.setRotationPoint(4.0F, 0.3F, 1.1F);
      this.Shape6.setTextureSize(64, 32);
      this.Shape6.mirror = true;
      this.setRotation(this.Shape6, 0.3665191F, 0.0F, 0.0F);
      this.Shape7 = new ModelRenderer(this, 22, 13);
      this.Shape7.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1);
      this.Shape7.setRotationPoint(7.0F, 0.3F, 2.0F);
      this.Shape7.setTextureSize(64, 32);
      this.Shape7.mirror = true;
      this.setRotation(this.Shape7, 0.3665191F, 1.570796F, 0.0F);
      this.Shape42 = new ModelRenderer(this, 0, 3);
      this.Shape42.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1);
      this.Shape42.setRotationPoint(4.0F, 8.0F, -2.3F);
      this.Shape42.setTextureSize(64, 32);
      this.Shape42.mirror = true;
      this.setRotation(this.Shape42, 0.0F, 0.0F, 0.0F);
      this.Shape43 = new ModelRenderer(this, 0, 3);
      this.Shape43.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1);
      this.Shape43.setRotationPoint(4.0F, 8.0F, 1.3F);
      this.Shape43.setTextureSize(64, 32);
      this.Shape43.mirror = true;
      this.setRotation(this.Shape43, 0.0F, 0.0F, 0.0F);
      this.Shape44 = new ModelRenderer(this, 0, 3);
      this.Shape44.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1);
      this.Shape44.setRotationPoint(7.3F, 8.0F, 2.0F);
      this.Shape44.setTextureSize(64, 32);
      this.Shape44.mirror = true;
      this.setRotation(this.Shape44, 0.0F, 1.570796F, 0.0F);
      this.Shape45 = new ModelRenderer(this, 0, 3);
      this.Shape45.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1);
      this.Shape45.setRotationPoint(3.7F, 8.0F, 2.0F);
      this.Shape45.setTextureSize(64, 32);
      this.Shape45.mirror = true;
      this.setRotation(this.Shape45, 0.0F, 1.570796F, 0.0F);
      this.Shape46 = new ModelRenderer(this, 0, 19);
      this.Shape46.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1);
      this.Shape46.setRotationPoint(4.0F, 1.2F, -2.2F);
      this.Shape46.setTextureSize(64, 32);
      this.Shape46.mirror = true;
      this.setRotation(this.Shape46, -0.0349066F, 0.0F, -0.5410521F);
      this.Shape47 = new ModelRenderer(this, 0, 19);
      this.Shape47.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1);
      this.Shape47.setRotationPoint(5.1F, 0.9F, -2.2F);
      this.Shape47.setTextureSize(64, 32);
      this.Shape47.mirror = true;
      this.setRotation(this.Shape47, -0.0349066F, 0.0F, 0.0F);
      this.Shape48 = new ModelRenderer(this, 0, 19);
      this.Shape48.addBox(0.0F, 0.0F, 0.0F, 4, 2, 1);
      this.Shape48.setRotationPoint(7.2F, 1.0F, 2.0F);
      this.Shape48.setTextureSize(64, 32);
      this.Shape48.mirror = true;
      this.setRotation(this.Shape48, 0.0349066F, 1.570796F, 0.0F);
      this.Shape49 = new ModelRenderer(this, 0, 19);
      this.Shape49.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1);
      this.Shape49.setRotationPoint(4.0F, 1.2F, 1.3F);
      this.Shape49.setTextureSize(64, 32);
      this.Shape49.mirror = true;
      this.setRotation(this.Shape49, -0.0349066F, 0.0F, -0.5410521F);
      this.Shape50 = new ModelRenderer(this, 0, 19);
      this.Shape50.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1);
      this.Shape50.setRotationPoint(5.1F, 0.9F, 1.3F);
      this.Shape50.setTextureSize(64, 32);
      this.Shape50.mirror = true;
      this.setRotation(this.Shape50, -0.0349066F, 0.0F, 0.0F);
   }

   public void render(float f5) {
      this.Shape0.render(f5);
      this.Shape1.render(f5);
      this.Shape2.render(f5);
      this.Shape3.render(f5);
      this.Shape4.render(f5);
      this.Shape5.render(f5);
      this.Shape6.render(f5);
      this.Shape7.render(f5);
      this.Shape42.render(f5);
      this.Shape43.render(f5);
      this.Shape44.render(f5);
      this.Shape45.render(f5);
      this.Shape46.render(f5);
      this.Shape47.render(f5);
      this.Shape48.render(f5);
      this.Shape49.render(f5);
      this.Shape50.render(f5);
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
