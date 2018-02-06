package keelfy.darkfactions.server;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import keelfy.darkfactions.common.DFCommon;
import keelfyutils.KUtils;

/**
 * @author keelfy
 * @created 19 ноя. 2017 г.
 */
public class DFServer extends DFCommon {

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		if (KUtils.PROTECT_SERVER) {
			super.preInit(event);
			if (!KUtils.SINGLEPLAYER) {
				this.init();
			}
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

			if (!KUtils.SINGLEPLAYER) {
				this.init();
			}
		}

	}

	public static final void init() {
		if (KUtils.PROTECT_SERVER) {

		}
	}

	@Override
	public void serverStarting(FMLServerStartingEvent event) {
		if (KUtils.PROTECT_SERVER) {
			super.serverStarting(event);

			if (!KUtils.SINGLEPLAYER) {
				this.serverStarting();
			}
		}
	}

	public static final void serverStarting() {
		if (KUtils.PROTECT_SERVER) {
			KUtils.instance.registerCommandHandler(DFCommands.class);
		}
	}

}
