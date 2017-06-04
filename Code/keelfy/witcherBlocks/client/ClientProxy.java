package keelfy.witcherBlocks.client;

import cpw.mods.fml.client.registry.ClientRegistry;
import keelfy.darkage.handler.client.ResourceHandler;
import keelfy.darkage.util.DAUtil;
import keelfy.witcherBlocks.CommonProxy;
import keelfy.witcherBlocks.WitcherBlocks;
import keelfy.witcherBlocks.client.renderer.RendererLootBag;
import keelfy.witcherBlocks.tileentity.TileEntityLootBag;

/**
 * @author keelfy
 */
public class ClientProxy extends CommonProxy {
	
	public void init() {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLootBag.class, new RendererLootBag());
		}
	}
}
