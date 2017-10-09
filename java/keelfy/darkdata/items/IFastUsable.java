/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author keelfy
 */
public interface IFastUsable {
	public ItemStack use(ItemStack is, World world, EntityPlayer player, int slot);
}
