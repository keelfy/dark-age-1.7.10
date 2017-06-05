package keelfy.darkage.entity.sign;

import keelfy.darkage.entity.player.DAPlayer;
import keelfy.darkage.entity.player.DAPlayerUtil.Property;
import keelfy.darkage.util.DAUtil;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

/**
 * @author keelfy
 * @created 30 мая 2017 г.
 */
public class SignKven {

	private World worldObj;
	
	public SignKven(World world) {
		this.worldObj = world;
	}
	
	public void handle(EntityPlayerMP sender) {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			if (!this.worldObj.isRemote) {
				DAPlayer wcp = DAPlayer.get(sender);
				if (wcp != null && wcp.get(Property.ENERGY) > wcp.getPlayerMaxEnergy() - 7) {
					ISign.useSign("customnpcs:signs.kven", this.worldObj, sender);
					sender.addPotionEffect(new PotionEffect(Potion.resistance.getId(), 1200, 10));
					wcp.update(Property.ENERGY, 0F);
				}
			}
		}
	}
}