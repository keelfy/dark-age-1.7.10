/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.client.renderer;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkdata.blocks.tileentities.DATileEntity;
import keelfy.darkdata.blocks.tileentities.TileEntityLoot;
import keelfyutils.KUtils;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.tileentity.TileEntity;

/**
 * @author keelfy
 */
@SideOnly(Side.CLIENT)
public final class DARendererLootBag extends DARendererBlock {

	// private final IModelCustom model = AdvancedModelLoader
	// .loadModel(new ResourceLocation(DAUtils.MODID, "models/e324151.obj"));
	// private final ResourceLocation texture = new ResourceLocation(DAUtils.MODID,
	// "textures/models/e324151.png");

	public static final void register() {
		if (KUtils.PROTECT_CLIENT) {
			final DARendererLootBag renderer = new DARendererLootBag();
			ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLoot.class, renderer);
			RenderingRegistry.registerBlockHandler(renderer);
		}
	}

	@Override
	public void renderTileEntityAt(final TileEntity var1, final double var2, final double var4, final double var6,
			final float var8) {
		if (KUtils.PROTECT_CLIENT) {
			final DATileEntity tile = (DATileEntity) var1;
			// GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			// GL11.glEnable(GL11.GL_LIGHTING);
			// GL11.glPushMatrix();
			// GL11.glTranslatef((float) var2 + 0.5f, (float) var4, (float) var6 + 0.5f);
			// GL11.glScalef(0.009F, 0.009F, 0.009F);
			// GL11.glRotatef(90F, 0, 0, 1);
			// GL11.glRotatef(90F, 0, 1, 0);
			// // GL11.glRotatef(90 * tile.rotation, 0, 1, 0);
			// GL11.glColor3f(1F, 1F, 1F);
			//
			// Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
			// GL11.glPushMatrix();
			// model.renderAll();
			// GL11.glPopMatrix();

			// GL11.glPopMatrix();
		}
	}

	@Override
	public void renderInventoryBlock(final Block block, final int metadata, final int modelId,
			final RenderBlocks renderer) {}

	@Override
	public int getRenderId() {
		return RenderingRegistry.getNextAvailableRenderId();
	}
}
