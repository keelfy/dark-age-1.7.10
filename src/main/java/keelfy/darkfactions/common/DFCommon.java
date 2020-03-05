package keelfy.darkfactions.common;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import keelfy.darkfactions.server.DFServer;
import keelfyutils.KUtils;

/**
 * @author keelfy
 * @created 19 ноя. 2017 г.
 */
public class DFCommon {

	public void preInit(FMLPreInitializationEvent event) {
		if (KUtils.SINGLEPLAYER) {
			DFServer.preInit();
		}
	}

	public void init(FMLInitializationEvent event) {
		if (KUtils.SINGLEPLAYER) {
			DFServer.init();
		}
	}

	public void serverStarting(FMLServerStartingEvent event) {
		if (KUtils.SINGLEPLAYER) {
			DFServer.serverStarting();
		}
	}
}
