/*
 *  Copyright (c) 2016-2017, Rubedo
 */

package keelfy.fahrenheit.network;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent.ClientCustomPacketEvent;
import io.netty.buffer.ByteBuf;
import keelfy.fahrenheit.client.gui.FCampfireGui;
import keelfy.fahrenheit.common.player.FPlayerData;
import keelfy.fahrenheit.common.player.managers.FPlayerManager;
import keelfyutils.KUtils;
import keelfyutils.network.KNetwork;
import keelfyutils.str.Brush;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

/**
 * @author keelfy
 * @created 18 сент. 2017 г.
 */
public class FCPackets {

	public static final void register() {
		FNetwork.channel.register(new FCPackets());
	}

	@SubscribeEvent
	public final void onClientPacket(final ClientCustomPacketEvent event) {
		if (KUtils.PROTECT_CLIENT) {
			final Minecraft mc = Minecraft.getMinecraft();
			final EntityPlayer player = mc.thePlayer;
			final ByteBuf buffer = event.packet.payload();

			if (mc == null || player == null)
				return;

			final FPlayerData fpd = FPlayerManager.getPlayer(player);

			final FEnumCPackets packetType = FEnumCPackets.values()[buffer.readInt()];

			switch (packetType) {
			case SyncData:
				final NBTTagCompound data = KNetwork.readNBTFromBuffer(buffer);
				if (data == null)
					return;
				fpd.loadNBTData(data);
				break;
			case SyncTemperature:
				final NBTTagCompound temp = KNetwork.readNBTFromBuffer(buffer);
				if (temp == null)
					return;
				fpd.temperature.loadNBTData(temp);
				break;
			case SyncThrist:
				final NBTTagCompound thrist = KNetwork.readNBTFromBuffer(buffer);
				if (thrist == null)
					return;
				fpd.temperature.loadNBTData(thrist);
				break;
			case SyncBleeding:
				final NBTTagCompound bleeding = KNetwork.readNBTFromBuffer(buffer);
				if (bleeding == null)
					return;
				fpd.temperature.loadNBTData(bleeding);
				break;
			case StartFireResult:
				int result = buffer.readInt();

				if (mc.currentScreen != null && mc.currentScreen instanceof FCampfireGui) {
					FCampfireGui gui = (FCampfireGui) mc.currentScreen;

					gui.timer = 0;
					gui.additional = result == 1 ? Brush.GREEN + "Успех!" : result == 2 ? Brush.RED + "Нет спичек!" : Brush.RED + "Неудача!";
				}

				break;
			}
		}
	}

}
