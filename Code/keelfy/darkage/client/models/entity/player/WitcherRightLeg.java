package keelfy.darkage.client.models.entity.player;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class WitcherRightLeg  extends ModelBase {

   ModelRenderer rightleg;


   public WitcherRightLeg() {
      this.textureWidth = 64;
      this.textureHeight = 32;
      this.rightleg = new ModelRenderer(this, 0, 16);
      this.rightleg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4);
      this.rightleg.setRotationPoint(-2.0F, 12.0F, 0.0F);
      this.rightleg.setTextureSize(64, 32);
      this.rightleg.mirror = true;
      this.setRotation(this.rightleg, 0.0F, 0.0F, 0.0F);
   }

   public void render(float f5) {
      this.rightleg.render(f5);
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
