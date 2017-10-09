/*
 *  Copyright (c) 2016-2017, Rubedo
 */

package keelfy.fahrenheit.server;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import keelfy.fahrenheit.common.FCommon;
import keelfy.fahrenheit.network.FSPackets;
import keelfyutils.KUtils;

/**
 * @author keelfy
 * @created 18 сент. 2017 г.
 */
public class FServer extends FCommon {

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		if (KUtils.PROTECT_SERVER) {
			super.preInit(event);

			preInit();
		}
	}

	public static void preInit() {
		if (KUtils.PROTECT_SERVER) {

		}
	}

	@Override
	public void init(FMLInitializationEvent event) {
		if (KUtils.PROTECT_SERVER) {
			super.init(event);

			init();
		}
	}

	public static void init() {
		if (KUtils.PROTECT_SERVER) {
			FSPackets.register();
			FServerEvents.INSTANCE.register();
		}

	}

	@Override
	public void serverStarting(FMLServerStartingEvent event) {
		if (KUtils.PROTECT_SERVER) {
			super.serverStarting(event);

			serverStarting();
		}
	}

	public static void serverStarting() {
		if (KUtils.PROTECT_SERVER) {
			KUtils.instance.registerCommandHandler(FCommands.class);
		}
	}

	@Override
	public void serverStarted(FMLServerStartedEvent event) {
		if (KUtils.PROTECT_SERVER) {
			super.serverStarted(event);

			serverStarted();
		}
	}

	public static void serverStarted() {
		if (KUtils.PROTECT_SERVER) {
			KUtils.instance.registerCommandHandler(FCommands.class);
		}
	}

}
