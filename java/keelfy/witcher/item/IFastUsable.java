package keelfy.witcher.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author keelfy
 */
public interface IFastUsable {
	public ItemStack use(ItemStack is, World world, EntityPlayer player, int slot);
}
