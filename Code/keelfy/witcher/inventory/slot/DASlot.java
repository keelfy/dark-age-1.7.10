package keelfy.witcher.inventory.slot;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.witcher.util.DAUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;

/**
 * @author keelfy
 */
public class DASlot extends Slot {

	private int limit;
	public DASlot(IInventory inventory, int id, int posX, int posY, int stackLimit) {
		super(inventory, id, posX, posY);
		limit = stackLimit;
	}
    
    @Override
	public int getSlotStackLimit() {
		return limit;
	}

    @Override
    public void putStack(ItemStack stack) {
    	if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
	    	if(stack != null) {
	    		if(!stack.hasTagCompound()) stack.stackTagCompound = new NBTTagCompound();
	    		stack.stackTagCompound.setBoolean("inSlot", true);
	    	}
    	}
    	super.putStack(stack);
    }
    
    @Override
    public void onPickupFromSlot(EntityPlayer player, ItemStack stack) {
    	if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
	    	if(stack != null) {
	    		if(!stack.hasTagCompound()) stack.stackTagCompound = new NBTTagCompound();
	    		stack.stackTagCompound.setBoolean("inSlot", false);
	    	}
    	}
    	super.onPickupFromSlot(player, stack);
    }
    
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getBackgroundIconIndex() {
		return null;
	}
}
