package keelfy.darkage.network.server;

import cpw.mods.fml.relauncher.Side;
import keelfy.api.network.AbstractMessage.AbstractServerMessage;
import keelfy.darkage.item.IFastUsable;
import keelfy.darkage.util.DAUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;

/**
 * @author keelfy
 */
public class HotSlotMessage extends AbstractServerMessage<HotSlotMessage> {
	private int id;

	public HotSlotMessage() {}

	public HotSlotMessage(int id) {
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
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			if(player.inventory.mainInventory[id] != null) {
				IFastUsable item = (IFastUsable) player.inventory.mainInventory[id].getItem();
				
				item.use(player.inventory.mainInventory[id], player.worldObj, player, id);
			}
		}
	}
}