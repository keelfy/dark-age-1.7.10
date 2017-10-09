package keelfyutils.network;

import java.io.IOException;

import com.google.common.base.Charsets;

import io.netty.buffer.ByteBuf;
import keelfyutils.str.KLog;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTSizeTracker;
import net.minecraft.nbt.NBTTagCompound;

/**
 * @author keelfy
 * @created 24 июл. 2017 г.
 */
public final class KNetwork {

	public static final boolean fillBuffer(final ByteBuf buffer, final Enum enu, final Object... obs)
			throws IOException {
		buffer.writeInt(enu.ordinal());

		for (Object ob : obs) {
			if (ob == null) {
				continue;
			}

			if (ob instanceof Enum) {
				buffer.writeInt(((Enum<?>) ob).ordinal());
			} else if (ob instanceof Integer) {
				buffer.writeInt((Integer) ob);
			} else if (ob instanceof Boolean) {
				buffer.writeBoolean((Boolean) ob);
			} else if (ob instanceof String) {
				writeStringToBuffer(buffer, (String) ob);
			} else if (ob instanceof Float) {
				buffer.writeFloat((Float) ob);
			} else if (ob instanceof Long) {
				buffer.writeLong((Long) ob);
			} else if (ob instanceof Double) {
				buffer.writeDouble((Double) ob);
			} else if (ob instanceof NBTTagCompound) {
				writeNBTToBuffer(buffer, (NBTTagCompound) ob);
			} else if (ob instanceof ItemStack) {
				writeItemStackToBuffer(buffer, (ItemStack) ob);
			}
		}

		if (buffer.array().length >= 32767) {
			KLog.err("Packet " + enu + " was too big to be send");
			return false;
		}
		return true;
	}

	public static final void writeNBTToBuffer(final ByteBuf buffer, final NBTTagCompound compound) {
		try {
			byte[] bytes = CompressedStreamTools.compress(compound);
			buffer.writeShort((short) bytes.length);
			buffer.writeBytes(bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static final NBTTagCompound readNBTFromBuffer(final ByteBuf buffer) {
		try {
			byte[] bytes = new byte[buffer.readShort()];
			buffer.readBytes(bytes);
			return CompressedStreamTools.func_152457_a(bytes, new NBTSizeTracker(2097152L));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static final void writeStringToBuffer(final ByteBuf buffer, final String s) {
		byte[] bytes = s.getBytes(Charsets.UTF_8);
		buffer.writeShort((short) bytes.length);
		buffer.writeBytes(bytes);
	}

	public static final String readStringFromBuffer(final ByteBuf buffer) {
		try {
			byte[] bytes = new byte[buffer.readShort()];
			buffer.readBytes(bytes);
			return new String(bytes, Charsets.UTF_8);
		} catch (IndexOutOfBoundsException ex) {
			return null;
		}
	}

	public static final void writeItemStackToBuffer(final ByteBuf buffer, final ItemStack stack) {
		if (stack == null) {
			buffer.writeShort(-1);
		} else {
			buffer.writeShort(Item.getIdFromItem(stack.getItem()));
			buffer.writeByte(stack.stackSize);
			buffer.writeShort(stack.getItemDamage());
			NBTTagCompound nbttagcompound = null;

			if (stack.getItem().isDamageable() || stack.getItem().getShareTag()) {
				nbttagcompound = stack.stackTagCompound;
			}

			writeNBTToBuffer(buffer, nbttagcompound);
		}
	}

	public static final ItemStack readItemStackFromBuffer(final ByteBuf buffer) {
		ItemStack itemstack = null;
		short short1 = buffer.readShort();

		if (short1 >= 0) {
			byte b0 = buffer.readByte();
			short short2 = buffer.readShort();
			itemstack = new ItemStack(Item.getItemById(short1), b0, short2);
			itemstack.stackTagCompound = readNBTFromBuffer(buffer);
		}

		return itemstack;
	}

}
