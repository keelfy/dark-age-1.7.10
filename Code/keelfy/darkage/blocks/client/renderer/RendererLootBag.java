/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.blocks.client.renderer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.client.registry.RenderingRegistry;
import keelfy.darkage.blocks.block.BlockLootBag;
import keelfy.darkage.blocks.register.BlockRegister;
import keelfy.darkage.client.renderer.RendererBlock;
import keelfy.darkage.tileentity.DATileEntity;
import keelfy.darkage.utils.DAUtils;
import keelfytools.KeelfyUtils;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

/**
 * @author keelfy
 */
public class RendererLootBag extends RendererBlock {

	private final IModelCustom model = AdvancedModelLoader.loadModel(new ResourceLocation(DAUtils.MODID, "models/e324151.obj"));
	private final ResourceLocation texture = new ResourceLocation(DAUtils.MODID, "textures/models/e324151.png");
	
    public RendererLootBag() {
    	if(KeelfyUtils.isClientSide()) {
			((BlockLootBag)BlockRegister.lootBag).renderId = RenderingRegistry.getNextAvailableRenderId();
			RenderingRegistry.registerBlockHandler(this);
    	}
    }

	@Override
	public void renderTileEntityAt(TileEntity var1, double var2, double var4, double var6, float var8) {
		if(KeelfyUtils.isClientSide()) {
			DATileEntity tile = (DATileEntity) var1;
	        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
	        GL11.glEnable(GL11.GL_LIGHTING);
	        GL11.glPushMatrix();
	        GL11.glTranslatef((float)var2 + 0.5f, (float)var4 , (float)var6 + 0.5f);
	        GL11.glScalef(0.009F, 0.009F, 0.009F);
	        GL11.glRotatef(90F, 0, 0, 1);
	        GL11.glRotatef(90F, 0, 1, 0);
	        //GL11.glRotatef(90 * tile.rotation, 0, 1, 0);
	        GL11.glColor3f(1F, 1F, 1F);
	        
	        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
	        GL11.glPushMatrix();
	    	model.renderAll();
	    	GL11.glPopMatrix();
	
			GL11.glPopMatrix();
		}
	}

	@Override
	public int getRenderId() {
		return BlockRegister.lootBag.getRenderType();
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {}
}

