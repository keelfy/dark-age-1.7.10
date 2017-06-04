package keelfy.darkage.network.server;

import java.io.IOException;

import cpw.mods.fml.relauncher.Side;
import keelfy.api.network.AbstractMessage.AbstractServerMessage;
import keelfy.darkage.entity.player.DAPlayer;
import keelfy.darkage.util.DAUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;

/**
 * @author keelfy
 */
public class MaxIntoxMessage extends AbstractServerMessage<MaxIntoxMessage>{
	private float amount;
	
	public MaxIntoxMessage() {}
	
	public MaxIntoxMessage(float amount) {
		this.amount = amount;
	}
	
	@Override
	protected void write(PacketBuffer buffer) throws IOException {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE)
		buffer.writeFloat(amount);
	}

	@Override
	protected void read(PacketBuffer buffer) throws IOException {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE)
		amount = buffer.readFloat();
	}

	@Override
	public void process(EntityPlayer player, Side side) {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE)
		DAPlayer.get(player).setWitcherMaxIntox(amount);
	}
}
