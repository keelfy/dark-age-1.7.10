package keelfy.darkfactions.client;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import keelfy.darkfactions.common.DFCommon;
import keelfyutils.KUtils;

/**
 * @author keelfy
 * @created 19 ноя. 2017 г.
 */
public class DFClient extends DFCommon {

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
		}
	}

}
