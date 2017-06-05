package keelfy.darkage.client.models.armor.RedanianHalebardist;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class RedanianHalebardistLeg  extends ModelBase {

   ModelRenderer Shape32;
   ModelRenderer Shape33;
   ModelRenderer Shape34;
   ModelRenderer Shape35;


   public RedanianHalebardistLeg() {
      this.textureWidth = 64;
      this.textureHeight = 32;
      this.Shape32 = new ModelRenderer(this, 54, 0);
      this.Shape32.addBox(0.0F, 0.0F, 0.0F, 1, 9, 4);
      this.Shape32.setRotationPoint(3.0F, 12.0F, -2.0F);
      this.Shape32.setTextureSize(64, 32);
      this.Shape32.mirror = true;
      this.setRotation(this.Shape32, 0.0F, 0.0F, -0.0523599F);
      this.Shape33 = new ModelRenderer(this, 11, 26);
      this.Shape33.addBox(0.0F, 0.0F, 0.0F, 1, 2, 4);
      this.Shape33.setRotationPoint(3.6F, 21.0F, -2.0F);
      this.Shape33.setTextureSize(64, 32);
      this.Shape33.mirror = true;
      this.setRotation(this.Shape33, 0.0F, 0.0F, -0.0523599F);
      this.Shape34 = new ModelRenderer(this, 54, 0);
      this.Shape34.addBox(0.0F, 0.0F, 0.0F, 4, 9, 1);
      this.Shape34.setRotationPoint(0.0F, 12.0F, 1.0F);
      this.Shape34.setTextureSize(64, 32);
      this.Shape34.mirror = true;
      this.setRotation(this.Shape34, 0.0523599F, 0.0F, 0.0F);
      this.Shape35 = new ModelRenderer(this, 0, 29);
      this.Shape35.addBox(0.0F, 0.0F, 0.0F, 4, 2, 1);
      this.Shape35.setRotationPoint(0.0F, 21.0F, 1.6F);
      this.Shape35.setTextureSize(64, 32);
      this.Shape35.mirror = true;
      this.setRotation(this.Shape35, 0.0523599F, 0.0F, 0.0F);
   }

   public void render(float f5) {
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
}