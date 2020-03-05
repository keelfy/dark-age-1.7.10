/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.client.renderer;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfyutils.KUtils;
import keelfyutils.client.KGL;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;

/**
 * @author keelfy
 */
@SideOnly(Side.CLIENT)
public abstract class DARendererBlock extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler {

	@Override
	public boolean renderWorldBlock(final IBlockAccess world, final int x, final int y, final int z, final Block block,
			final int modelId, final RenderBlocks renderer) {
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory(final int modelId) {
		return false;
	}

	public boolean playerTooFar(final TileEntity tile) {
		if (KUtils.PROTECT_CLIENT) {
			Minecraft mc = KGL.mc();
			double d6 = mc.renderViewEntity.posX - tile.xCoord;
			double d7 = mc.renderViewEntity.posY - tile.yCoord;
			double d8 = mc.renderViewEntity.posZ - tile.zCoord;
			return d6 * d6 + d7 * d7 + d8 * d8 > specialRenderDistance() * specialRenderDistance();
		} else
			return false;
	}

	public int specialRenderDistance() {
		return 20;
	}
}
