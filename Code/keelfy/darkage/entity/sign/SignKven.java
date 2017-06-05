package keelfy.darkage.entity.sign;

import keelfy.darkage.entity.player.DAPlayer;
import keelfy.darkage.entity.player.DAPlayerUtil.Property;
import keelfy.darkage.util.DAUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

/**
 * @author keelfy
 * @created 30 мая 2017 г.
 */
public class SignKven {

	private World worldObj;
	private EntityPlayer owner;
	
	public SignKven(World world) {
		this.worldObj = world;
	}
	
	public void setOwner(EntityPlayer owner) {
		this.owner = owner;
	}
	
	public void handle() {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			if (!this.worldObj.isRemote) {
				DAPlayer wcp = DAPlayer.get(owner);
				if (wcp != null && wcp.get(Property.ENERGY) > wcp.getPlayerMaxEnergy() - 7) {
					ISign.useSign("customnpcs:signs.kven", this.worldObj, owner);
					owner.addPotionEffect(new PotionEffect(Potion.resistance.getId(), 1200, 10));
					wcp.update(Property.ENERGY, 7F);
				}
			}
		}
	}
}
