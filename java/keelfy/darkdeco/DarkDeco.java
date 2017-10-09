/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdeco;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import keelfyutils.str.KLog;

/**
 * @author keelfy
 * @created 6 сент. 2017 г.
 */
@Mod(modid = DarkDeco.MOD_ID, name = DarkDeco.MOD_NAME, version = DarkDeco.MOD_VERSION, dependencies = "required-after:witchercore")
public final class DarkDeco {

	public static final String MOD_ID = "darkdeco";
	public static final String MOD_NAME = "Dark Deco";
	public static final String MOD_VERSION = "1.0b";

	public static final String PROXY_CLIENT = "keelfy.darkdeco.client.DDClient";
	public static final String PROXY_SERVER = "keelfy.darkdeco.server.DDServer";

	@Instance(DarkDeco.MOD_ID)
	public static DarkDeco instance;

	@SidedProxy(clientSide = DarkDeco.PROXY_CLIENT, serverSide = DarkDeco.PROXY_SERVER)
	public static DDCommon proxy;

	@EventHandler
	public final void preInit(final FMLPreInitializationEvent event) {
		proxy.preInit(event);
	}

	@EventHandler
	public final void init(final FMLInitializationEvent event) {
		proxy.init(event);

		KLog.info(MOD_NAME + " by keelfy");
		KLog.info("Version: " + DarkDeco.MOD_VERSION);
	}
}
