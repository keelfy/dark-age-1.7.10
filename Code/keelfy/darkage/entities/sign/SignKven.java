/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.entities.sign;

import keelfy.darkage.constants.EnumProperty;
import keelfy.darkage.entities.player.DAPlayer;
import keelfy.darkage.utils.DAEntityUtils;
import keelfytools.KeelfyUtils;
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
		if(KeelfyUtils.isServerSide()) {
			if (!this.worldObj.isRemote) {
				DAPlayer dap = DAPlayer.get(owner);
				if (dap != null && dap.get(EnumProperty.ENERGY) > dap.getPlayerMaxEnergy() - 10) {
					DAEntityUtils.useSign("kven", this.worldObj, owner);
					owner.addPotionEffect(new PotionEffect(Potion.resistance.getId(), 1200, 10));
					dap.update(EnumProperty.ENERGY, dap.getPlayerMinEnergy());
				}
			}
		}
	}
}
