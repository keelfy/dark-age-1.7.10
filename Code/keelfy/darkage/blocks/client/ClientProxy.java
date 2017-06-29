/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.blocks.client;

import cpw.mods.fml.client.registry.ClientRegistry;
import keelfy.darkage.blocks.CommonProxy;
import keelfy.darkage.blocks.client.renderer.RendererLootBag;
import keelfy.darkage.blocks.tileentity.TileEntityLootBag;
import keelfytools.KeelfyUtils;

/**
 * @author keelfy
 */
public class ClientProxy extends CommonProxy {
	
	@Override
	public void init() {
		if(KeelfyUtils.isClientSide()) {
			ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLootBag.class, new RendererLootBag());
		}
	}
}
