package keelfy.darkage.addon.blocks.client;

import cpw.mods.fml.client.registry.ClientRegistry;
import keelfy.darkage.addon.blocks.CommonProxy;
import keelfy.darkage.addon.blocks.DABlocksAddon;
import keelfy.darkage.addon.blocks.client.renderer.RendererLootBag;
import keelfy.darkage.addon.blocks.tileentity.TileEntityLootBag;
import keelfy.darkage.handler.client.ResourceHandler;
import keelfy.darkage.util.DAUtil;

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
