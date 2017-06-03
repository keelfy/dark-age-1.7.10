package keelfy.witcher.client.renderer;

import org.lwjgl.opengl.GL11;

import keelfy.witcher.client.models.entity.sign.ModelSignIrden;
import keelfy.witcher.entity.sign.ISign.Sign;
import keelfy.witcher.handler.client.ResourceHandler.Texture;
import keelfy.witcher.handler.client.ResourceHandler.Texture.WCT;
import keelfy.witcher.util.DAUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

/**
 * @author keelfy
 */
public class RendererSign extends Render {

	private ResourceLocation texture;
	private ModelSignIrden model;

	private ResourceLocation textureIrden;
	private ModelSignIrden modelIrden;
	
	private Sign type;
	public RendererSign(Sign renderType) {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			texture = Texture.get(WCT.ENTITY, "null");
			textureIrden = Texture.get(WCT.ENTITY, "irden");
			model = new ModelSignIrden();
			modelIrden = new ModelSignIrden();
			type = renderType;
		}
	}
	
	@Override
	public void doRender(Entity entity, double x, double y, double z, float par5, float par6) {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			if(type != Sign.IRDEN) {
				GL11.glPushMatrix();
				GL11.glEnable(3042);
				Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
				GL11.glTranslated(x, y - 0.6000000238418579D, z);
				GL11.glScalef(3.0F, 1.0F, 3.0F);
				model.render(0.0625F);
				GL11.glDisable(3042);
				GL11.glPopMatrix();
			} else {
				GL11.glPushMatrix();
				GL11.glEnable(3042);
				GL11.glBlendFunc(770, 771);
				Minecraft.getMinecraft().getTextureManager().bindTexture(textureIrden);
				GL11.glTranslated(x, y - 0.6000000238418579D, z);
				GL11.glScalef(3.0F, 1.0F, 3.0F);
				model.render(0.0625F);
				GL11.glDisable(3042);
				GL11.glPopMatrix();
			}
		}
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			if(type != Sign.IRDEN) {
				return texture;
			} else {
				return textureIrden;
			}
		} else 
			return null;
	}
}
