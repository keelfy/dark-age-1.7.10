/*
 *  Copyright (c) 2016-2017, Rubedo
 */

package keelfy.darkcore.common;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import keelfy.darkcore.network.DANetwork;
import keelfy.darkcore.server.DCServer;
import keelfyutils.KUtils;

/**
 * @author keelfy
 * @created 3 окт. 2017 г.
 */
public class DCCommon {

	public void preInit(FMLPreInitializationEvent event) {
		DANetwork.register();

		if (KUtils.SINGLEPLAYER) {
			DCServer.preInit();
		}
	}

	public void init(FMLInitializationEvent event) {
		DCCommonEvents.Instance.register();

		if (KUtils.SINGLEPLAYER) {
			DCServer.init();
		}
	}

	public void postInit(FMLPostInitializationEvent event) {
		if (KUtils.SINGLEPLAYER) {
			DCServer.postInit();
		}
	}
}
