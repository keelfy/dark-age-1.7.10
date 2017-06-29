/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfytools.network;

import java.io.IOException;

import com.google.common.base.Charsets;
import com.google.common.base.Throwables;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import io.netty.buffer.ByteBuf;
import keelfy.darkage.DarkAge;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;

/**
 * @author keelfy
 */
public abstract class AbstractMessage<T extends AbstractMessage<T>> implements IMessage, IMessageHandler <T, IMessage> {
	
	protected abstract void read(PacketBuffer buffer) throws IOException;
	protected abstract void write(PacketBuffer buffer) throws IOException;
	public abstract void process(EntityPlayer player, Side side);

	protected boolean isValidOnSide(final Side side) {
		return true; 
	}

	@Override
	public void fromBytes(final ByteBuf buffer) {
		try {
			read(new PacketBuffer(buffer));
		} catch (IOException e) {
			throw Throwables.propagate(e);
		}
	}

	@Override
	public void toBytes(final ByteBuf buffer) {
		try {
			write(new PacketBuffer(buffer));
		} catch (IOException e) {
			throw Throwables.propagate(e);
		}
	}
	
	public static void writeString(final ByteBuf buffer, String s){
        byte[] bytes = s.getBytes(Charsets.UTF_8);
		buffer.writeShort((short)bytes.length);
		buffer.writeBytes(bytes);
	}
	
	public static String readString(final ByteBuf buffer){
		try{
			byte[] bytes = new byte[buffer.readShort()];
			buffer.readBytes(bytes);
			return new String(bytes, Charsets.UTF_8);
		}
		catch(IndexOutOfBoundsException ex){
			return null;
		}
	}
	
	@Override
	public final IMessage onMessage(final T msg, final MessageContext ctx) {
		if (!msg.isValidOnSide(ctx.side)) {
			throw new RuntimeException("Неверная сторона " + ctx.side.name() + " для " + msg.getClass().getSimpleName());
		} else {
			msg.process(DarkAge.instance.proxy.getEntityPlayer(ctx), ctx.side);
		}
		return null;
	}

	public static abstract class AbstractClientMessage<T extends AbstractMessage<T>> extends AbstractMessage<T> {
		
		@Override
		protected final boolean isValidOnSide(Side side) {
			return side.isClient();
		}
	}
	
	public static abstract class AbstractServerMessage<T extends AbstractMessage<T>> extends AbstractMessage<T> {
		
		@Override
		protected final boolean isValidOnSide(Side side) {
			return side.isServer();
		}
	}
}