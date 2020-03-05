/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdeco;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import keelfy.darkdeco.blocks.BlockCampfire;
import keelfy.darkdeco.blocks.BlockCampfire.TileEntityCampfire;
import keelfyutils.registry.KBlockRegistry;

/**
 * @author keelfy
 * @created 6 сент. 2017 г.
 */
public class DDCommon {

	public void preInit(final FMLPreInitializationEvent event) {

		KBlockRegistry.setResourceDomain(DarkDeco.MOD_ID);

		KBlockRegistry.of("Campfire", new BlockCampfire()).registerWithoutTexture();
		GameRegistry.registerTileEntity(TileEntityCampfire.class, "TileEntityCampfire");

	}

	public void init(final FMLInitializationEvent event) {

	}

}
