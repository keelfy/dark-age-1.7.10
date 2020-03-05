package keelfy.darkcore.network;

import java.io.IOException;

import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import keelfy.darkcore.DarkCore;
import keelfy.darkdata.network.EnumSPackets;
import keelfyutils.network.KNetwork;

/**
 * @author keelfy
 * @created 24 июл. 2017 г.
 */
public final class DACNetwork {

	public static void sendToServer(final EnumSPackets enu, final Object... obs) {
		ByteBuf buffer = Unpooled.buffer();
		try {
			if (!KNetwork.fillBuffer(buffer, enu, obs))
				return;

			DANetwork.channel.sendToServer(new FMLProxyPacket(buffer, DarkCore.MOD_CHANNEL));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
