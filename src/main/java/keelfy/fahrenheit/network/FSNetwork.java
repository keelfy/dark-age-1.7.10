/*
 *  Copyright (c) 2016-2017, Rubedo
 */

package keelfy.fahrenheit.network;

import java.io.IOException;

import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import keelfy.fahrenheit.Fahrenheit;
import keelfyutils.KUtils;
import keelfyutils.network.KNetwork;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

/**
 * @author keelfy
 * @created 18 сент. 2017 г.
 */
public class FSNetwork {

	public static final boolean sendTo(final EntityPlayerMP player, final FEnumCPackets enu, final Object... obs) {
		if (KUtils.PROTECT_SERVER) {
			ByteBuf buffer = Unpooled.buffer();
			try {
				if (!KNetwork.fillBuffer(buffer, enu, obs))
					return false;

				FNetwork.channel.sendTo(new FMLProxyPacket(buffer, Fahrenheit.MOD_CHANNEL), player);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	public static final void sendToAllAround(final Entity entity, final FEnumCPackets enu, final Object... obs) {
		if (KUtils.PROTECT_SERVER) {
			ByteBuf buffer = Unpooled.buffer();
			try {
				if (!KNetwork.fillBuffer(buffer, enu, obs))
					return;
				TargetPoint point = new TargetPoint(entity.dimension, entity.posX, entity.posY, entity.posZ, 60);
				FNetwork.channel.sendToAllAround(new FMLProxyPacket(buffer, Fahrenheit.MOD_CHANNEL), point);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static final void sendToAll(final FEnumCPackets enu, final Object... obs) {
		if (KUtils.PROTECT_SERVER) {
			ByteBuf buffer = Unpooled.buffer();
			try {
				if (!KNetwork.fillBuffer(buffer, enu, obs))
					return;
				FNetwork.channel.sendToAll(new FMLProxyPacket(buffer, Fahrenheit.MOD_CHANNEL));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
