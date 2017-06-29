/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.utils;

import keelfy.darkage.items.Armor;
import keelfytools.KeelfyUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * @author keelfy
 * @created 21 июн. 2017 г.
 */
public enum DAEntityUtils {
	Instance;
	
	public static float getTotalBlockingPercent(final EntityPlayer player) {
		float r = 0;

		for (int i = 0; i < 4; i++) {
			if (player.getCurrentArmor(i) != null && player.getCurrentArmor(i).getItem() instanceof Armor)
				r += ((Armor) player.getCurrentArmor(i).getItem()).getBlockingPercent();
		}
		return r;
	}

	public static float getReceivedDamage(final EntityPlayer player, float damage) {
		if (KeelfyUtils.isServerSide()) {
			float resultDamage = damage;
			float r1 = resultDamage / 100;

			for (int i = 0; i < 4; i++) {
				if (player.getCurrentArmor(i) != null && player.getCurrentArmor(i).getItem() instanceof Armor)
					resultDamage -= r1 * ((Armor) player.getCurrentArmor(i).getItem()).getBlockingPercent();
			}

			return resultDamage;
		}
		return 0;
	}

	public static void useSign(String s, World world, EntityPlayer sender) {
		if (KeelfyUtils.isServerSide()) {
			world.playSoundAtEntity(sender, "customnpcs:signs." + s, 1.0F, 1.0F);
		}
	}
}
