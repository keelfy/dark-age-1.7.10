/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.entities.player.effect;

import keelfy.darkage.constants.EnumProperty;
import keelfytools.KeelfyUtils;
import net.minecraft.entity.player.EntityPlayer;

/**
 * @author keelfy
 */
public class EffectSaturation extends PlayerEffect {

	public EffectSaturation(EntityPlayer player) {
		super(player);
	}

	@Override
	public void onUpdate() {
		if(KeelfyUtils.isServerSide()) {
			super.onUpdate();
			
			if(wcp.get(EnumProperty.HEALTH) < wcp.getPlayerMaxHealth() && wcp.get(EnumProperty.HEALTH) + 0.1F < wcp.getPlayerMaxHealth()) {
				
			}
		}
	}
}
