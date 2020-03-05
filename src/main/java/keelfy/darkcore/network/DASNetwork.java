/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkcore.network;

import java.io.IOException;

import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import keelfy.darkcore.DarkCore;
import keelfy.darkdata.network.EnumCPackets;
import keelfyutils.KUtils;
import keelfyutils.network.KNetwork;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

/**
 * @author keelfy
 * @created 12 июл. 2017 г.
 */
public final class DASNetwork {

	public static final boolean sendTo(final EntityPlayerMP player, final EnumCPackets enu, final Object... obs) {
		if (KUtils.PROTECT_SERVER) {
			ByteBuf buffer = Unpooled.buffer();
			try {
				if (!KNetwork.fillBuffer(buffer, enu, obs))
					return false;

				DANetwork.channel.sendTo(new FMLProxyPacket(buffer, DarkCore.MOD_CHANNEL), player);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	public static final void sendToAllAround(final Entity entity, final EnumCPackets enu, final Object... obs) {
		if (KUtils.PROTECT_SERVER) {
			ByteBuf buffer = Unpooled.buffer();
			try {
				if (!KNetwork.fillBuffer(buffer, enu, obs))
					return;
				TargetPoint point = new TargetPoint(entity.dimension, entity.posX, entity.posY, entity.posZ, 60);
				DANetwork.channel.sendToAllAround(new FMLProxyPacket(buffer, DarkCore.MOD_CHANNEL), point);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static final void sendToAll(final EnumCPackets enu, final Object... obs) {
		if (KUtils.PROTECT_SERVER) {
			ByteBuf buffer = Unpooled.buffer();
			try {
				if (!KNetwork.fillBuffer(buffer, enu, obs))
					return;
				DANetwork.channel.sendToAll(new FMLProxyPacket(buffer, DarkCore.MOD_CHANNEL));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
