package keelfy.witcher.network.server;

import cpw.mods.fml.relauncher.Side;
import keelfy.api.network.AbstractMessage.AbstractServerMessage;
import keelfy.witcher.DarkAge;
import keelfy.witcher.handler.GuiHandler.GUI;
import keelfy.witcher.util.DAUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;

/**
 * @author keelfy
 */
public class OpenGuiMessage extends AbstractServerMessage<OpenGuiMessage> {
	private GUI id;
	private int posX, posY, posZ;
	
	public OpenGuiMessage() {}

	public OpenGuiMessage(GUI id) {
		this.id = id;
		this.posX = Integer.MAX_VALUE;
		this.posY = Integer.MAX_VALUE;
		this.posZ = Integer.MAX_VALUE;
	}
	
	public OpenGuiMessage(GUI id, int x, int y, int z) {
		this.id = id;
		this.posX = x;
		this.posY = y;
		this.posZ = z;
	}

	@Override
	protected void read(PacketBuffer buffer) {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			id = GUI.values() [buffer.readInt()];
			posX = buffer.readInt();
			posY = buffer.readInt();
			posZ = buffer.readInt();
		}
	}

	@Override
	protected void write(PacketBuffer buffer) {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			buffer.writeInt(id.ordinal());
			buffer.writeInt(posX);
			buffer.writeInt(posY);
			buffer.writeInt(posZ);
		}
	}

	@Override
	public void process(EntityPlayer player, Side side) {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			if(id == GUI.CHEST)
				player.openGui(DarkAge.instance, id.get(), player.worldObj, posX, posY, posZ);
			else player.openGui(DarkAge.instance, id.get(), player.worldObj, (int)player.posX, (int)player.posY, (int)player.posZ);
		}
	}
}