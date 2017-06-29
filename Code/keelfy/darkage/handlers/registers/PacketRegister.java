/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.handlers.registers;

import keelfy.darkage.utils.DAUtils;
import keelfytools.network.PacketDispatcher;
import keelfytools.network.client.CustomClientMessage;
import keelfytools.network.server.CustomServerMessage;

/**
 * @author keelfy
 * @created 15 июн. 2017 г.
 */
public enum PacketRegister {
	Instance;
	
	public final void init() {
		PacketDispatcher dis = PacketDispatcher.getInstance();
		
		dis.registerChannel(DAUtils.NETWORK_CHANNEL);
		
		dis.registerMessage(CustomClientMessage.class);
		dis.registerMessage(CustomServerMessage.class);
	}
}
