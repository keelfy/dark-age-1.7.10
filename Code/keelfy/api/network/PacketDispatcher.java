package keelfy.api.network;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

/**
 * @author keelfy
 */
public class PacketDispatcher {
	private byte packetId = 0;
	private static PacketDispatcher instance;
	private List packets = new ArrayList();
	
	public static PacketDispatcher getInstance() {
		if(instance == null) {
			instance = new PacketDispatcher();
		} 
		return instance;
	}
	
	private SimpleNetworkWrapper dispatcher;
	
	public final void registerChannel(String name) {
		dispatcher = NetworkRegistry.INSTANCE.newSimpleChannel(name);
	}
	
	public final <T extends AbstractMessage<T> & IMessageHandler<T, IMessage>> void registerMessage(Class<T> clazz) {

		if (AbstractMessage.AbstractClientMessage.class.isAssignableFrom(clazz)) {
			PacketDispatcher.getInstance().dispatcher.registerMessage(clazz, clazz, packetId++, Side.CLIENT);
		} else if (AbstractMessage.AbstractServerMessage.class.isAssignableFrom(clazz)) {
			PacketDispatcher.getInstance().dispatcher.registerMessage(clazz, clazz, packetId++, Side.SERVER);
		} else {
			PacketDispatcher.getInstance().dispatcher.registerMessage(clazz, clazz, packetId, Side.CLIENT);
			PacketDispatcher.getInstance().dispatcher.registerMessage(clazz, clazz, packetId++, Side.SERVER);
		}
	}
	
	public final void sendTo(IMessage message, EntityPlayerMP player) {
		PacketDispatcher.getInstance().dispatcher.sendTo(message, player);
	}

	public void sendToAll(IMessage message) {
		PacketDispatcher.getInstance().dispatcher.sendToAll(message);
	}

	public final void sendToAllAround(IMessage message, NetworkRegistry.TargetPoint point) {
		PacketDispatcher.getInstance().dispatcher.sendToAllAround(message, point);
	}

	public final void sendToAllAround(IMessage message, int dimension, double x, double y, double z, double range) {
		PacketDispatcher.getInstance().sendToAllAround(message, new NetworkRegistry.TargetPoint(dimension, x, y, z, range));
	}

	public final void sendToAllAround(IMessage message, EntityPlayer player, double range) {
		PacketDispatcher.getInstance().sendToAllAround(message, player.worldObj.provider.dimensionId, player.posX, player.posY, player.posZ, range);
	}

	public final void sendToDimension(IMessage message, int dimensionId) {
		PacketDispatcher.getInstance().dispatcher.sendToDimension(message, dimensionId);
	}

	public final void sendToServer(IMessage message) {
		PacketDispatcher.getInstance().dispatcher.sendToServer(message);
	}
}