package keelfy.witcher.network.server;

import cpw.mods.fml.relauncher.Side;
import keelfy.api.network.AbstractMessage.AbstractServerMessage;
import keelfy.witcher.entity.player.PlayerClass;
import keelfy.witcher.entity.player.DAPlayer;
import keelfy.witcher.util.DAUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;

/**
 * @author keelfy
 */
public class ClassMessage extends AbstractServerMessage<ClassMessage> {
	private int id;

	public ClassMessage() {}

	public ClassMessage(int id) {
		this.id = id;
	}

	@Override
	protected void read(PacketBuffer buffer) {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE)
			id = buffer.readInt();
	}

	@Override
	protected void write(PacketBuffer buffer) {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE)
			buffer.writeInt(id);
	}

	@Override
	public void process(EntityPlayer player, Side side) {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE)
			DAPlayer.get(player).setPlayerClass(PlayerClass.values()[id]);
	}
}