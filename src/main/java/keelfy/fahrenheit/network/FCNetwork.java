/*
 *  Copyright (c) 2016-2017, Rubedo
 */

package keelfy.fahrenheit.network;

import java.io.IOException;

import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import keelfy.fahrenheit.Fahrenheit;
import keelfyutils.network.KNetwork;

/**
 * @author keelfy
 * @created 18 сент. 2017 г.
 */
public class FCNetwork {

	public static void sendToServer(final FEnumSPackets enu, final Object... obs) {
		ByteBuf buffer = Unpooled.buffer();
		try {
			if (!KNetwork.fillBuffer(buffer, enu, obs))
				return;

			FNetwork.channel.sendToServer(new FMLProxyPacket(buffer, Fahrenheit.MOD_CHANNEL));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
