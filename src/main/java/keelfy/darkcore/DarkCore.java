/*
 *  Copyright (c) 2016-2017, Rubedo
 */

package keelfy.darkcore;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import keelfy.darkcore.common.DCCommon;

/**
 * @author keelfy
 * @created 3 окт. 2017 г.
 */
@Mod(modid = DarkCore.MOD_ID, name = DarkCore.MOD_NAME, version = DarkCore.MOD_VERSION, dependencies = "required-after:keelfyutils")
public class DarkCore {

	public static final String MOD_ID = "darkcore";
	public static final String MOD_NAME = "Dark Core";
	public static final String MOD_VERSION = "14.1";

	public static final String MOD_CHANNEL = "darkcore_channel";

	@SidedProxy(clientSide = "keelfy.darkcore.client.DCClient", serverSide = "keelfy.darkcore.server.DCServer")
	public static DCCommon proxy;

	@EventHandler
	public final void preInit(FMLPreInitializationEvent event) {
		proxy.preInit(event);
	}

	@EventHandler
	public final void init(FMLInitializationEvent event) {
		proxy.init(event);
	}

	@EventHandler
	public final void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
	}
}
