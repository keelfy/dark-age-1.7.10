package keelfy.darkage.handler;

import keelfy.api.network.PacketDispatcher;
import keelfy.darkage.network.client.RepairRequestMessage;
import keelfy.darkage.network.client.SyncEffectsMessage;
import keelfy.darkage.network.client.SyncPlayerMessage;
import keelfy.darkage.network.server.ChangeSignMessage;
import keelfy.darkage.network.server.ClassMessage;
import keelfy.darkage.network.server.GiveBookMessage;
import keelfy.darkage.network.server.HandleMoneyClickMessage;
import keelfy.darkage.network.server.HotSlotMessage;
import keelfy.darkage.network.server.IITMessage;
import keelfy.darkage.network.server.MaxHealthMessage;
import keelfy.darkage.network.server.MaxIntoxMessage;
import keelfy.darkage.network.server.OpenGuiMessage;
import keelfy.darkage.network.server.PerformEffectMessage;
import keelfy.darkage.network.server.PlayerJumpMessage;
import keelfy.darkage.network.server.RepairItemMessage;
import keelfy.darkage.network.server.UseSignMessage;
import keelfy.darkage.network.server.WeightMessage;
import keelfy.darkage.util.DAUtil;

/**
 * @author keelfy
 */
public class PacketHandler {
	// TODO: Переписать систему пакетов, избавиться от классов
	public PacketHandler() {
		PacketDispatcher dis = PacketDispatcher.getInstance();
		
		//Channel
		dis.registerChannel(DAUtil.NETWORK_CHANNEL);
		
		//Client
		dis.registerMessage(SyncPlayerMessage.class);
		dis.registerMessage(SyncEffectsMessage.class);
		dis.registerMessage(RepairRequestMessage.class);
		
		//Server
		dis.registerMessage(ClassMessage.class);
		dis.registerMessage(OpenGuiMessage.class);
		dis.registerMessage(HotSlotMessage.class);
		dis.registerMessage(UseSignMessage.class);
		dis.registerMessage(ChangeSignMessage.class);
		dis.registerMessage(PlayerJumpMessage.class);
		dis.registerMessage(MaxHealthMessage.class);
		dis.registerMessage(MaxIntoxMessage.class);
		dis.registerMessage(IITMessage.class);
		dis.registerMessage(WeightMessage.class);
		dis.registerMessage(PerformEffectMessage.class);
		dis.registerMessage(RepairItemMessage.class);
		dis.registerMessage(HandleMoneyClickMessage.class);
		dis.registerMessage(GiveBookMessage.class);
	}
}
