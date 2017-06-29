/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.constants;

import net.minecraft.potion.Potion;

/**
 * @author keelfy
 * @created 2 июн. 2017 г.
 */
public enum EnumPotion {
	MOVESPEED(Potion.moveSpeed),
	MOVESLOWDOWN(Potion.moveSlowdown),
	DIGSPEED(Potion.digSpeed),
	DIGSLOWDOWN(Potion.digSlowdown),
	DAMAGEBOOST(Potion.damageBoost),
	HEAL(Potion.heal),
	HARM(Potion.harm),
	JUMP(Potion.jump),
	CONFUSION(Potion.confusion),
	REGENERATION(Potion.regeneration),
	RESISTANCE(Potion.resistance),
	FIRERESISTANCE(Potion.fireResistance),
	WATERBREATHING(Potion.waterBreathing),
	INVISIBILITY(Potion.invisibility),
	BLINDNESS(Potion.blindness),
	NIGHTVISION(Potion.nightVision),
	HUNGER(Potion.hunger),
	WEAKNESS(Potion.weakness),
	POSION(Potion.poison),
	WITHER(Potion.wither),
	ANSORPTION(Potion.field_76444_x),
	HEALTHBOOST(Potion.field_76434_w);
	
	private Potion effect;
	private EnumPotion(Potion effect) {
		this.effect = effect;
	}
	
	public Potion getEffect() {
		return effect;
	}
}
