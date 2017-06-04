package keelfy.darkage.network.server;

import cpw.mods.fml.relauncher.Side;
import keelfy.api.network.AbstractMessage.AbstractServerMessage;
import keelfy.darkage.entity.player.DAPlayer;
import keelfy.darkage.entity.player.PlayerClass;
import keelfy.darkage.entity.sign.SignAard;
import keelfy.darkage.entity.sign.SignAksi;
import keelfy.darkage.entity.sign.SignIgni;
import keelfy.darkage.entity.sign.SignIrden;
import keelfy.darkage.entity.sign.SignKven;
import keelfy.darkage.entity.sign.ISign.Sign;
import keelfy.darkage.util.DAUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;

/**
 * @author keelfy
 */
public class UseSignMessage extends AbstractServerMessage<UseSignMessage> {

	public UseSignMessage() {}

	@Override
	protected void read(PacketBuffer buffer) {}

	@Override
	protected void write(PacketBuffer buffer) {}

	@Override
	public void process(EntityPlayer player, Side side) {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			DAPlayer wcp = DAPlayer.get(player);
			if(wcp != null && wcp.getPlayerClass() == PlayerClass.WITCHER) {
				EntityPlayerMP mp = (EntityPlayerMP) player;
				World world = player.worldObj;
				Sign id = wcp.getWitcherSign();
				
				switch(id) {
				case AARD: new SignAard(world).handle(mp); break;
				case AKSI: new SignAksi(world).handle(mp); break;
				case IGNI: new SignIgni(world).handle(mp); break;
				case IRDEN: new SignIrden(world).handle(mp); break;
				case KVEN: new SignKven(world).handle(mp); break;
				case NONE: break;
				}
			}
		}
	}
}