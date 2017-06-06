package keelfy.darkage.client.models.entity.player;

import keelfy.darkage.client.render.RenderDAPlayer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.player.EntityPlayer;

/**
 * @author keelfy
 */
public class ModelHand  extends ModelBase {

	private ModelRenderer rightarm;
	private ModelRenderer leftarm;
	private final Minecraft mc = Minecraft.getMinecraft();
	
	public ModelHand() {
		this.textureWidth = 64;
		this.textureHeight = 32;
		this.rightarm = new ModelRenderer(this, 40, 16);
		this.rightarm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4);
		this.rightarm.setRotationPoint(-5.0F, 2.0F, 0.0F);
		this.rightarm.setTextureSize(64, 32);
		this.rightarm.mirror = true;
		this.setRotation(this.rightarm, -1.570796F, 0.0F, 0.0F);
		this.leftarm = new ModelRenderer(this, 40, 16);
		this.leftarm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4);
		this.leftarm.setRotationPoint(5.0F, 2.0F, 0.0F);
		this.leftarm.setTextureSize(64, 32);
		this.leftarm.mirror = true;
		this.setRotation(this.leftarm, -1.570796F, 0.0F, 0.0F);
	}

	public void render(EntityPlayer p, int mode) {
//		mc.getTextureManager().bindTexture(mc.thePlayer.getLocationSkin());
		mc.getTextureManager().bindTexture(RenderDAPlayer.skinWitcher);
		if(mode == 1) this.rightarm.render(0.0625F);
		if(mode == 2) this.leftarm.render(0.0525F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}
