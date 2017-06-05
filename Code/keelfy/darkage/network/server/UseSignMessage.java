package keelfy.darkage.network.server;

import cpw.mods.fml.relauncher.Side;
import keelfy.api.network.AbstractMessage.AbstractServerMessage;
import keelfy.darkage.entity.player.DAPlayer;
import keelfy.darkage.entity.player.PlayerClass;
import keelfy.darkage.entity.sign.ISign.Sign;
import keelfy.darkage.entity.sign.SignAard;
import keelfy.darkage.entity.sign.SignAksi;
import keelfy.darkage.entity.sign.SignIgni;
import keelfy.darkage.entity.sign.SignIrden;
import keelfy.darkage.entity.sign.SignKven;
import keelfy.darkage.util.DAUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;

/**
 * @author keelfy
 */
public class UseSignMessage extends AbstractServerMessage<UseSignMessage> {

	private int entityId = -1;
	
	public UseSignMessage() {}

	public UseSignMessage(int id) {
		if(!DAUtil.SERVER ||  DAUtil.DEBUG_MODE) {
			this.entityId = id;
		}
	}
	
	@Override
	protected void read(PacketBuffer buffer) {
		entityId = buffer.readInt();
	}

	@Override
	protected void write(PacketBuffer buffer) {
		buffer.writeInt(entityId);
	}

	@Override
	public void process(EntityPlayer player, Side side) {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			DAPlayer dap = DAPlayer.get(player);
			if(dap != null && dap.getPlayerClass() == PlayerClass.WITCHER) {
				EntityPlayerMP mp = (EntityPlayerMP) player;
				World world = mp.worldObj;
				Sign id = dap.getWitcherSign();
				
				switch(id) {
				case AARD:  
					SignAard aard = new SignAard(world);
					aard.setOwner(mp);
					aard.handle(entityId);
					break;
				case AKSI:  
					SignAksi aksi = new SignAksi(world);
					aksi.setOwner(mp);
					aksi.handle(entityId); 
					break;
				case IGNI:
					SignIgni igni = new SignIgni(world);
					igni.setOwner(mp);
					igni.handle(entityId); 
					break;
				case IRDEN: 
					SignIrden irden = new SignIrden(world);
					irden.setOwner(mp);
					irden.handle(entityId); 
					break;
				case KVEN: 
					SignKven kven = new SignKven(world);
					kven.setOwner(player);
					kven.handle();
					break;
				case NONE:  
					break;
				}
			}
		}
	}
}