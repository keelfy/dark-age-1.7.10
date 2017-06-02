package keelfy.witcher.client.renderer;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import keelfy.witcher.util.DAUtil;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;

/**
 * @author keelfy
 */
public abstract class RendererBlock extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler {
	
	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return false;
	}
	
	public boolean playerTooFar(TileEntity tile) {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			Minecraft mc = Minecraft.getMinecraft();
	        double d6 = mc.renderViewEntity.posX - tile.xCoord;
	        double d7 = mc.renderViewEntity.posY - tile.yCoord;
	        double d8 = mc.renderViewEntity.posZ - tile.zCoord;;
	
	        return d6 * d6 + d7 * d7 + d8 * d8 > specialRenderDistance() * specialRenderDistance();
		} else 
			return false;
	}
	
	public int specialRenderDistance(){
		return 20;
	}
}

