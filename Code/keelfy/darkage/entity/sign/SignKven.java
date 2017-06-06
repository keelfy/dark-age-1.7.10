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
				DAPlayer dap = DAPlayer.get(owner);
				if (dap != null && dap.get(Property.ENERGY) > dap.getPlayerMaxEnergy() - 10) {
					ISign.useSign("kven", this.worldObj, owner);
					owner.addPotionEffect(new PotionEffect(Potion.resistance.getId(), 1200, 10));
					dap.update(Property.ENERGY, dap.getPlayerMinEnergy());
				}
			}
		}
	}
}
