package keelfy.witcher.network.server;

import cpw.mods.fml.relauncher.Side;
import keelfy.api.network.AbstractMessage.AbstractServerMessage;
import keelfy.witcher.entity.player.PlayerClass;
import keelfy.witcher.entity.player.DAPlayer;
import keelfy.witcher.entity.player.DAPlayerUtil.Property;
import keelfy.witcher.entity.sign.ISign.Sign;
import keelfy.witcher.util.DAUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;

/**
 * @author keelfy
 */
public class ChangeSignMessage extends AbstractServerMessage<ChangeSignMessage> {

	public ChangeSignMessage() {}

	@Override
	protected void read(PacketBuffer buffer) {}

	@Override
	protected void write(PacketBuffer buffer) {}

	@Override
	public void process(EntityPlayer player, Side side) {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			DAPlayer wcp = DAPlayer.get(player);
			
			if(wcp != null) {
				if(wcp.getPlayerClass() == PlayerClass.WITCHER) {
					if(wcp.getWitcherSign().ordinal() < 4) {
						Sign newSign = Sign.values() [wcp.getWitcherSign().ordinal() + 1];
						wcp.update(Property.CURRENT_SIGN, newSign.ordinal());	
					} else if(wcp.getWitcherSign().ordinal() == 4) {
						wcp.update(Property.CURRENT_SIGN, 0);
					}
					
					if(wcp.getWitcherSign().ordinal() > 4 || wcp.getWitcherSign().ordinal() < 0) {
						wcp.update(Property.CURRENT_SIGN, 0);
					}
				} else {
					if(wcp.getWitcherSign() != Sign.NONE) {
						wcp.update(Property.CURRENT_SIGN, Sign.NONE.ordinal());
					}
				}
			}
		}
	}
}