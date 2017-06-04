package keelfy.darkage.network.server;

import cpw.mods.fml.relauncher.Side;
import keelfy.api.network.AbstractMessage.AbstractServerMessage;
import keelfy.darkage.entity.player.DAPlayer;
import keelfy.darkage.entity.player.PlayerClass;
import keelfy.darkage.event.custom.PlayerJumpEvent;
import keelfy.darkage.item.IFastUsable;
import keelfy.darkage.util.DAUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.common.MinecraftForge;

/**
 * @author keelfy
 */
public class PlayerJumpMessage extends AbstractServerMessage<PlayerJumpMessage> {

	public PlayerJumpMessage() {}

	@Override
	protected void read(PacketBuffer buffer) {}

	@Override
	protected void write(PacketBuffer buffer) {}

	@Override
	public void process(EntityPlayer player, Side side) {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE)
		MinecraftForge.EVENT_BUS.post(new PlayerJumpEvent(player));
	}
}