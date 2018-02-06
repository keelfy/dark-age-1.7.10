/*
 *  Copyright (c) 2016-2017, Rubedo
 */

package keelfy.fahrenheit.network;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent.ServerCustomPacketEvent;
import io.netty.buffer.ByteBuf;
import keelfy.fahrenheit.common.FRegisterer;
import keelfy.fahrenheit.common.tileentities.TileEntityCampfire;
import keelfyutils.KUtils;
import keelfyutils.blocks.KBlocks;
import keelfyutils.blocks.Point3D;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.tileentity.TileEntity;

/**
 * @author keelfy
 * @created 18 сент. 2017 г.
 */
public class FSPackets {

	public static void register() {
		if (KUtils.PROTECT_SERVER) {
			FNetwork.channel.register(new FSPackets());
		}
	}

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public final void onServerPacket(final ServerCustomPacketEvent event) {
		if (KUtils.PROTECT_SERVER) {

			final EntityPlayerMP player = ((NetHandlerPlayServer) event.handler).playerEntity;

			final ByteBuf buffer = event.packet.payload();

			final FEnumSPackets packetType = FEnumSPackets.values()[buffer.readInt()];

			switch (packetType) {
			case StartFire:
				final Point3D pos = Point3D.readFromBuffer(buffer);
				TileEntity te = KBlocks.getTileEntity(player.worldObj, pos);
				if (te == null || !(te instanceof TileEntityCampfire)) {
					return;
				}
				TileEntityCampfire campfire = (TileEntityCampfire) te;
				int randomized = player.worldObj.rand.nextInt(100);

				if (!player.inventory.hasItem(FRegisterer.match)) {
					FSNetwork.sendTo(player, FEnumCPackets.StartFireResult, 2);
					return;
				}

				if (campfire.getStackInSlot(1) == null) {
					FSNetwork.sendTo(player, FEnumCPackets.StartFireResult, 0);
					return;
				}

				if (campfire.getStackInSlot(3) != null || player.worldObj.rand.nextInt(100) <= 20) {
					if (campfire.getStackInSlot(3) != null) {
						campfire.setInventorySlotContents(3, null);
					}
					player.inventory.consumeInventoryItem(FRegisterer.match);
					campfire.furnaceStarted = true;
					FSNetwork.sendTo(player, FEnumCPackets.StartFireResult, 1);
				} else {
					FSNetwork.sendTo(player, FEnumCPackets.StartFireResult, 0);
				}

				break;
			}
		}
	}

}
