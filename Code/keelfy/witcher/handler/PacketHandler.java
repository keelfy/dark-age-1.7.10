package keelfy.witcher.handler;

import keelfy.api.network.PacketDispatcher;
import keelfy.witcher.network.client.RepairRequestMessage;
import keelfy.witcher.network.client.SyncEffectsMessage;
import keelfy.witcher.network.client.SyncPlayerMessage;
import keelfy.witcher.network.server.ChangeSignMessage;
import keelfy.witcher.network.server.ClassMessage;
import keelfy.witcher.network.server.HotSlotMessage;
import keelfy.witcher.network.server.IITMessage;
import keelfy.witcher.network.server.MaxHealthMessage;
import keelfy.witcher.network.server.MaxIntoxMessage;
import keelfy.witcher.network.server.MoneyMessage;
import keelfy.witcher.network.server.OpenGuiMessage;
import keelfy.witcher.network.server.PerformEffectMessage;
import keelfy.witcher.network.server.PlayerJumpMessage;
import keelfy.witcher.network.server.RepairItemMessage;
import keelfy.witcher.network.server.UseSignMessage;
import keelfy.witcher.network.server.WeightMessage;
import keelfy.witcher.util.DAUtil;

/**
 * @author keelfy
 */
public class PacketHandler {
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
		dis.registerMessage(MoneyMessage.class);
		dis.registerMessage(MaxHealthMessage.class);
		dis.registerMessage(MaxIntoxMessage.class);
		dis.registerMessage(IITMessage.class);
		dis.registerMessage(WeightMessage.class);
		dis.registerMessage(PerformEffectMessage.class);
		dis.registerMessage(RepairItemMessage.class);
	}
}
