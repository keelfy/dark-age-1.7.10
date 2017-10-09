/*
 *  Copyright (c) 2016-2017, Rubedo
 */

package keelfy.darkcore.server;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import keelfy.darkcore.common.DCCommon;
import keelfy.darkdata.network.DASPackets;
import keelfyutils.KUtils;

/**
 * @author keelfy
 * @created 3 окт. 2017 г.
 */
public class DCServer extends DCCommon {

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		if (KUtils.PROTECT_SERVER) {
			super.preInit(event);

			this.preInit();
		}
	}

	public static final void preInit() {
		if (KUtils.PROTECT_SERVER) {

		}
	}

	@Override
	public void init(FMLInitializationEvent event) {
		if (KUtils.PROTECT_SERVER) {
			super.init(event);

			this.init();
		}
	}

	public static final void init() {
		if (KUtils.PROTECT_SERVER) {
			DCServerEvents.Instance.register();
			DASPackets.register();
		}
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		if (KUtils.PROTECT_SERVER) {
			super.postInit(event);

			this.postInit();
		}
	}

	public static final void postInit() {
		if (KUtils.PROTECT_SERVER) {

		}
	}
}
