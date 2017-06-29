/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.entities.player.effect;

import keelfytools.KeelfyUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

/**
 * @author keelfy
 */
public class EffectHeal extends PlayerEffect {
	
	public EffectHeal(EntityPlayer player) {
		super(player);
	}

	@Override
	public void perform(int dur, float eff) {
		super.perform(dur, eff);
		
		player.addPotionEffect(new PotionEffect(Potion.regeneration.id, dur));
	}
	
	@Override
	public void onUpdate() {
		if(KeelfyUtils.isServerSide()) {
			if(isActive()) {
				wcp.changeHealth(3 * getEfficiency());
			}
			
			super.onUpdate();
		}
	}
	
	@Override
	public void cure() {
		player.removePotionEffect(Potion.regeneration.id);
		super.cure();
	}
}
