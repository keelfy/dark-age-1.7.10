/*
 *  Copyright (c) 2016-2017, Rubedo
 */

package keelfy.fahrenheit.network;

import cpw.mods.fml.common.network.FMLEventChannel;
import cpw.mods.fml.common.network.NetworkRegistry;
import keelfy.fahrenheit.Fahrenheit;

/**
 * @author keelfy
 * @created 18 сент. 2017 г.
 */
public class FNetwork {

	public static FMLEventChannel channel;

	public static final void register() {
		channel = NetworkRegistry.INSTANCE.newEventDrivenChannel(Fahrenheit.MOD_CHANNEL);
	}

}
