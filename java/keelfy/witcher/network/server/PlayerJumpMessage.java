package keelfy.witcher.network.server;

import cpw.mods.fml.relauncher.Side;
import keelfy.api.network.AbstractMessage.AbstractServerMessage;
import keelfy.witcher.entity.player.PlayerClass;
import keelfy.witcher.entity.player.DAPlayer;
import keelfy.witcher.event.custom.PlayerJumpEvent;
import keelfy.witcher.item.IFastUsable;
import keelfy.witcher.util.DAUtil;
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