/*
 *  Copyright (c) 2016-2017, Rubedo
 */

package keelfy.fahrenheit.client;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import keelfy.fahrenheit.common.FCommon;
import keelfy.fahrenheit.common.FTabs;
import keelfy.fahrenheit.network.FCPackets;
import keelfyutils.KUtils;

/**
 * @author keelfy
 * @created 18 сент. 2017 г.
 */
public class FClient extends FCommon {

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		if (KUtils.PROTECT_CLIENT) {
			super.preInit(event);
		}
	}

	@Override
	public void init(FMLInitializationEvent event) {
		if (KUtils.PROTECT_CLIENT) {
			super.init(event);

			FTabs.register();
			FCPackets.register();
			FClientEvents.INSTANCE.register();
		}
	}

}
