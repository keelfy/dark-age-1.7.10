package keelfy.witcher.network.server;

import cpw.mods.fml.relauncher.Side;
import keelfy.api.network.AbstractMessage.AbstractServerMessage;
import keelfy.witcher.entity.player.PlayerClass;
import keelfy.witcher.entity.player.DAPlayer;
import keelfy.witcher.entity.sign.ISign.Sign;
import keelfy.witcher.entity.sign.SignAard;
import keelfy.witcher.entity.sign.SignAksi;
import keelfy.witcher.entity.sign.SignIgni;
import keelfy.witcher.entity.sign.SignIrden;
import keelfy.witcher.entity.sign.SignKven;
import keelfy.witcher.util.DAUtil;
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