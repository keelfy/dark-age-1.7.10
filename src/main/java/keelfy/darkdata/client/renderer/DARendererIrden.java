/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.client.renderer;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkdata.client.DAResources.Texture;
import keelfy.darkdata.client.models.entity.sign.ModelSignIrden;
import keelfy.darkdata.constants.EnumTexturePath;
import keelfy.darkdata.entities.EntityIrden;
import keelfyutils.KUtils;
import keelfyutils.client.KGL;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

/**
 * @author keelfy
 */
@SideOnly(Side.CLIENT)
public final class DARendererIrden extends Render {

	private static final ResourceLocation Texture_Irden = Texture.get(EnumTexturePath.Entity, "irden");
	private static final ModelSignIrden Model_Irden = new ModelSignIrden();

	public static final void register() {
		if (KUtils.PROTECT_CLIENT) {
			RenderingRegistry.registerEntityRenderingHandler(EntityIrden.class, new DARendererIrden());
		}
	}

	@Override
	public final void doRender(final Entity entity, final double x, final double y, final double z, final float par5,
			final float par6) {
		this.doRender((EntityIrden) entity, x, y, z, par5);
	}

	public final void doRender(final EntityIrden sign, final double x, final double y, final double z,
			final float par5) {
		GL11.glPushMatrix();
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glTranslated(x, y - 0.6000000238418579D, z);
		GL11.glScalef(3.0F, 1.0F, 3.0F);
		KGL.bindTexture(Texture_Irden);
		Model_Irden.renderAll();
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();
	}

	@Override
	protected final ResourceLocation getEntityTexture(final Entity entity) {
		return Texture_Irden;
	}
}
