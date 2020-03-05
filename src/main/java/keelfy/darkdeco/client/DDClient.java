/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdeco.client;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import keelfy.darkdeco.DDCommon;
import keelfy.darkdeco.blocks.BlockCampfire.TileEntityCampfire;
import keelfy.darkdeco.client.render.RendererCampfire;

/**
 * @author keelfy
 * @created 6 сент. 2017 г.
 */
public class DDClient extends DDCommon {

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
	
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCampfire.class, new RendererCampfire());
	}
	
	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);
		
	}
	
}
