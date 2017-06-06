package keelfy.darkage.network.server;

import java.io.IOException;

import cpw.mods.fml.relauncher.Side;
import keelfy.api.network.AbstractMessage.AbstractServerMessage;
import keelfy.darkage.util.DAUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;

/**
 * @author keelfy
 * @created 5 июн. 2017 г.
 */
public class CustomServerMessage extends AbstractServerMessage<CustomServerMessage> {
	
	private Object[] args;
	private int length;
	private PacketForServer packetType;
	
	public CustomServerMessage() {}
	
	public CustomServerMessage(PacketForServer packetType, Object[] args) {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			this.args = args;
			this.length = args.length;
			this.packetType = packetType;
		}
	}
	
	@Override
	protected void read(PacketBuffer buffer) throws IOException {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			packetType = PacketForServer.values() [buffer.readInt()];
			length = buffer.readInt();
			Object[] args = new Object[length];
			
			if(length > 0) {
				for(int i = 0; i < length; i++) {
					int j = buffer.readByte();
					if(j == 1) {
						args[i] = buffer.readInt();
					} else if(j == 2) {
						args[i] = readString(buffer);
					} else if(j == 3) {
						args[i] = buffer.readNBTTagCompoundFromBuffer();
					} else if(j == 4) {
						args[i] = buffer.readItemStackFromBuffer();
					} else if(j == 5) {
						args[i] = buffer.readFloat();
					} else if(j == 6) {
						args[i] = buffer.readBoolean();
					} else if(j == 7) {
						args[i] = buffer.readByte();
					}
				}
			}
			this.args = args;
		}
	}

	@Override
	protected void write(PacketBuffer buffer) throws IOException {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			buffer.writeInt(packetType.ordinal());
			buffer.writeInt(length);
			if(length > 0) {
				for(Object obj : args) {
					if(obj instanceof Integer) {
						buffer.writeByte(1);
						buffer.writeInt((Integer) obj);
					} else if(obj instanceof String) {
						buffer.writeByte(2);
						writeString(buffer, String.valueOf(obj));
					} else if(obj instanceof NBTTagCompound) {
						buffer.writeByte(3);
						buffer.writeNBTTagCompoundToBuffer((NBTTagCompound) obj);
					} else if(obj instanceof ItemStack) {
						buffer.writeByte(4);
						buffer.writeItemStackToBuffer((ItemStack) obj);
					} else if(obj instanceof Float) {
						buffer.writeByte(5);
						buffer.writeFloat((Float) obj);
					} else if(obj instanceof Boolean) {
						buffer.writeByte(6);
						buffer.writeBoolean((Boolean) obj);
					} else if(obj instanceof Byte) {
						buffer.writeByte(7);
						buffer.writeByte((Byte) obj);
					}
				}
			}
		}
	}

	@Override
	public void process(EntityPlayer player, Side side) {
		if(DAUtil.SERVER ||  DAUtil.DEBUG_MODE) {
			if(!player.worldObj.isRemote)
				ServerPacketHandler.handleOnServer(packetType, player, args);
		}
	}
	
	/**
	 * @author keelfy
	 * @created 5 июн. 2017 г.
	 */
	public enum PacketForServer {
		CHANGESIGN, CLASS, GIVEBOOK, MONEYCLICK, HOTSLOT, IIT, MAXHEALTH, MAXINTOX, OPENGUI, PERFORMEGGECT, PLAYERJUMP, REPAIRITEM, USESIGN, WEIGHT;
	}
}
