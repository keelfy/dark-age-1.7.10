/*
 *  Copyright (c) 2016-2017, Rubedo
 */

package keelfy.darkcore.client;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import keelfy.darkcore.common.DCCommon;
import keelfy.darkdata.network.DACPackets;
import keelfyutils.KUtils;

/**
 * @author keelfy
 * @created 3 окт. 2017 г.
 */
public class DCClient extends DCCommon {

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

			DACPackets.register();
		}
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		if (KUtils.PROTECT_CLIENT) {
			super.postInit(event);
		}
	}

}
