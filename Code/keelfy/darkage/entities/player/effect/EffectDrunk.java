/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.entities.player.effect;

import keelfytools.KeelfyUtils;
import net.minecraft.entity.player.EntityPlayer;

/**
 * @author keelfy
 */
public class EffectDrunk extends PlayerEffect {

	public EffectDrunk(EntityPlayer player) {
		super(player);
	}

	@Override
	public void onUpdate() {
		if(KeelfyUtils.isServerSide()) {
			super.onUpdate();
		}
	}
}
