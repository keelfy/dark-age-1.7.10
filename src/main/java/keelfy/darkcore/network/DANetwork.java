package keelfy.darkcore.network;

import cpw.mods.fml.common.network.FMLEventChannel;
import cpw.mods.fml.common.network.NetworkRegistry;
import keelfy.darkcore.DarkCore;

/**
 * @author keelfy
 * @created 25 июл. 2017 г.
 */
public final class DANetwork {

	public static FMLEventChannel channel;

	public static final void register() {
		channel = NetworkRegistry.INSTANCE.newEventDrivenChannel(DarkCore.MOD_CHANNEL);
	}
}
