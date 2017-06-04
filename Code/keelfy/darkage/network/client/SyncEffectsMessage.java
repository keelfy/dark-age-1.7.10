package keelfy.darkage.network.client;

import java.io.IOException;

import cpw.mods.fml.relauncher.Side;
import keelfy.api.network.AbstractMessage.AbstractClientMessage;
import keelfy.darkage.entity.player.effect.DAEffect;
import keelfy.darkage.util.DAUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;

/**
 * @author keelfy
 */
public class SyncEffectsMessage extends AbstractClientMessage<SyncEffectsMessage> {
	private NBTTagCompound data;

	public SyncEffectsMessage() {}

	public SyncEffectsMessage(EntityPlayer player) {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			data = new NBTTagCompound();
			DAEffect.get(player).saveNBTData(data);
		}
	}

	@Override
	public void read(PacketBuffer buffer) throws IOException {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE)
			data = buffer.readNBTTagCompoundFromBuffer();
	}

	@Override
	public void write(PacketBuffer buffer) throws IOException {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE)
			buffer.writeNBTTagCompoundToBuffer(data);
	}

	@Override
	public void process(EntityPlayer player, Side side) {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			if(DAEffect.get(player) != null) {
				DAEffect.get(player).loadNBTData(data);
			}
		}
	}
}