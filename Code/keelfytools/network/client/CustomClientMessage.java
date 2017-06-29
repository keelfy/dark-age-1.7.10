/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfytools.network.client;

import java.io.IOException;

import cpw.mods.fml.relauncher.Side;
import keelfy.darkage.constants.EnumClientPacket;
import keelfy.darkage.network.ClientPacketHandler;
import keelfytools.KeelfyUtils;
import keelfytools.network.AbstractMessage;
import keelfytools.network.AbstractMessage.AbstractClientMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;

/**
 * @author keelfy
 * @created 5 июн. 2017 г.
 */
public class CustomClientMessage extends AbstractClientMessage<CustomClientMessage> {

	private Object[] args;
	private int length;
	private EnumClientPacket packetType;

	public CustomClientMessage() {
	}

	public CustomClientMessage(EnumClientPacket packetType, Object[] args) {
		if (KeelfyUtils.isServerSide()) {
			this.args = args;
			this.length = args.length;
			this.packetType = packetType;
		}
	}

	@Override
	protected void read(PacketBuffer buffer) throws IOException {
		if (KeelfyUtils.isClientSide()) {
			packetType = EnumClientPacket.values()[buffer.readInt()];
			length = buffer.readInt();
			Object[] args = new Object[length];

			if (length > 0) {
				for (int i = 0; i < length; i++) {
					int j = buffer.readByte();

					switch (j) {
					case 1:
						args[i] = buffer.readInt();
						break;
					case 2:
						args[i] = readString(buffer);
						break;
					case 3:
						args[i] = buffer.readNBTTagCompoundFromBuffer();
						break;
					case 4:
						args[i] = buffer.readItemStackFromBuffer();
						break;
					case 5:
						args[i] = buffer.readFloat();
						break;
					case 6:
						args[i] = buffer.readBoolean();
						break;
					case 7:
						args[i] = buffer.readByte();
						break;
					}
				}
			}
			this.args = args;
		}
	}

	@Override
	protected void write(PacketBuffer buffer) throws IOException {
		if (KeelfyUtils.isServerSide()) {
			buffer.writeInt(packetType.ordinal());
			buffer.writeInt(length);
			if (length > 0) {
				for (Object obj : args) {

					if (obj instanceof Integer) {
						buffer.writeByte(1);
						buffer.writeInt((Integer) obj);
					} else if (obj instanceof String) {
						buffer.writeByte(2);
						writeString(buffer, String.valueOf(obj));
					} else if (obj instanceof NBTTagCompound) {
						buffer.writeByte(3);
						buffer.writeNBTTagCompoundToBuffer((NBTTagCompound) obj);
					} else if (obj instanceof ItemStack) {
						buffer.writeByte(4);
						buffer.writeItemStackToBuffer((ItemStack) obj);
					} else if (obj instanceof Float) {
						buffer.writeByte(5);
						buffer.writeFloat((Float) obj);
					} else if (obj instanceof Boolean) {
						buffer.writeByte(6);
						buffer.writeBoolean((Boolean) obj);
					} else if (obj instanceof Byte) {
						buffer.writeByte(7);
						buffer.writeByte((Byte) obj);
					}
				}
			}
		}
	}

	@Override
	public void process(EntityPlayer player, Side side) {
		if (KeelfyUtils.isClientSide()) {
			ClientPacketHandler.handleOnClient(packetType, player, args);
		}
	}
}
