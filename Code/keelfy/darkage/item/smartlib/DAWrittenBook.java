package keelfy.darkage.item.smartlib;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkage.DarkAge;
import keelfy.darkage.handler.GuiHandler.GUI;
import keelfy.darkage.util.DAUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StringUtils;
import net.minecraft.world.World;

/**
 * @author keelfy
 */
public class DAWrittenBook extends Item {

    public DAWrittenBook() {
        setMaxStackSize(1);
        
        if(!DAUtil.SERVER || DAUtil.DEBUG_MODE)
        	setTextureName("book_written");
    }

    @Override
    public String getItemStackDisplayName(ItemStack is) {
    	if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
	        if (is.hasTagCompound()) {
	            NBTTagCompound nbttagcompound = is.getTagCompound();
	            String s = nbttagcompound.getString("title");
	            return s;
	        }
    	}
        return super.getItemStackDisplayName(is);
    }

    @Override
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
    	if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
	        if (p_77624_1_.hasTagCompound()) {
	            NBTTagCompound nbttagcompound = p_77624_1_.getTagCompound();
	            String s = nbttagcompound.getString("author");
	
	            if (!StringUtils.isNullOrEmpty(s))
	                p_77624_3_.add(EnumChatFormatting.GRAY + "by " + s);
	        }
    	}
    }

    @Override
    public ItemStack onItemRightClick(ItemStack is, World world, EntityPlayer player) {
    	if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
		    player.openGui(DarkAge.instance, GUI.WRITTENBOOK.get(), world, (int)player.posX, (int)player.posY, (int)player.posZ);  
	    	
		    if(world.isRemote) {
		    	if(is.hasTagCompound()) {
		    		SLBook slBook = DarkAge.instance.fileHandler.getBookByID(is.stackTagCompound.getInteger("id"));
		    		is.stackTagCompound.setString("title", slBook.title);
		    		is.stackTagCompound.setString("author", slBook.author);
		    	}
		    }
    	}
        return is;
    }

    @Override
	public boolean getShareTag() {
        return true;
    }

    @Override
	@SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack p_77636_1_) {
        return true;
    }
}
