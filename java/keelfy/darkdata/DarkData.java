/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import keelfyutils.KUtils;
import keelfyutils.str.KLog;

/**
 * @author keelfy
 */
@Mod(modid = DarkData.MOD_ID, name = DarkData.MOD_NAME, version = DarkData.MOD_VERSION, dependencies = "required-after:darkcore")
public final class DarkData {

	public static final String MOD_ID = "witchercore";
	public static final String MOD_NAME = "Dark Data";
	public static final String MOD_VERSION = "14.1";

	public static final String PROXY_SERVER = "keelfy.darkdata.server.DAServer";
	public static final String PROXY_CLIENT = "keelfy.darkdata.client.DAClient";

	public static final boolean IS_SHADERS_ENABLED = true;

	@Instance(DarkData.MOD_ID)
	public static DarkData instance;

	@SidedProxy(clientSide = DarkData.PROXY_CLIENT, serverSide = DarkData.PROXY_SERVER)
	public static DACommon proxy;

	@EventHandler
	public final void preInit(final FMLPreInitializationEvent event) {
		proxy.preInit(event);
	}

	@EventHandler
	public final void init(final FMLInitializationEvent event) {
		proxy.init(event);

		KLog.info(DarkData.MOD_NAME + " by keelfy & RedSokol");
		KLog.info("Version: " + DarkData.MOD_VERSION);
		KLog.info("Run Mode: " + (KUtils.DEBUG ? "Debug" : (KUtils.SERVER ? "Server side" : "Client side")));
	}

	@EventHandler
	public final void serverStart(final FMLServerStartingEvent event) {
		proxy.serverStarting(event);
	}
}
