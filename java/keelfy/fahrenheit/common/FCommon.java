/*
 *  Copyright (c) 2016-2017, Rubedo
 */

package keelfy.fahrenheit.common;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import keelfy.fahrenheit.network.FNetwork;
import keelfy.fahrenheit.server.FServer;
import keelfyutils.KUtils;

/**
 * @author keelfy
 * @created 18 сент. 2017 г.
 */
public class FCommon {

	public void preInit(FMLPreInitializationEvent event) {

		FRegisterer.register();

		if (KUtils.SINGLEPLAYER) {
			FServer.preInit();
		}
	}

	public void init(FMLInitializationEvent event) {

		FNetwork.register();
		FGuiHandler.register();
		FCommonEvents.INSTANCE.register();

		if (KUtils.SINGLEPLAYER) {
			FServer.init();
		}

	}

	public void serverStarting(FMLServerStartingEvent event) {

		if (KUtils.SINGLEPLAYER) {
			FServer.serverStarting();
		}

	}

	public void serverStarted(FMLServerStartedEvent event) {

		if (KUtils.SINGLEPLAYER) {
			FServer.serverStarted();
		}
	}

}
