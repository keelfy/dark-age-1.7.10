/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfytools;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.biome.BiomeGenBase;

/**
 * @author keelfy
 */
public enum KEntityUtils {
	Instance;

	public static BiomeGenBase getBiome(final EntityPlayer player) {
		if (KeelfyUtils.isServerSide())
			return player.worldObj.getBiomeGenForCoordsBody((int) player.posX, (int) player.posZ);
		return null;
	}
}
