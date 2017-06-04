package keelfy.darkage.client.models.entity.player;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class WitcherLeftLeg  extends ModelBase {

   ModelRenderer leftleg;


   public WitcherLeftLeg() {
      this.textureWidth = 64;
      this.textureHeight = 32;
      this.leftleg = new ModelRenderer(this, 0, 16);
      this.leftleg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4);
      this.leftleg.setRotationPoint(2.0F, 12.0F, 0.0F);
      this.leftleg.setTextureSize(64, 32);
      this.leftleg.mirror = true;
      this.setRotation(this.leftleg, 0.0F, 0.0F, 0.0F);
   }

   public void render(float f5) {
      this.leftleg.render(f5);
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
