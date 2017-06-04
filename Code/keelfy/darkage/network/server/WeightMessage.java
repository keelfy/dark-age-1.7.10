package keelfy.darkage.network.server;

import java.io.IOException;

import cpw.mods.fml.relauncher.Side;
import keelfy.api.network.AbstractMessage.AbstractServerMessage;
import keelfy.darkage.entity.player.DAPlayer;
import keelfy.darkage.entity.player.DAPlayerUtil.Property;
import keelfy.darkage.util.DAUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;

/**
 * @author keelfy
 */
public class WeightMessage extends AbstractServerMessage<WeightMessage> {
	private float amount;

	public WeightMessage() {}

	public WeightMessage(float amount) {
		this.amount = amount;
	}

	@Override
	protected void write(PacketBuffer buffer) {
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
			DAPlayer.get(player).update(Property.WEIGHT, amount);
	}
}