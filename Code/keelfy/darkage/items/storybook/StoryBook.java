/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.items.storybook;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkage.constants.EnumGui;
import keelfy.darkage.constants.EnumServerPacket;
import keelfy.darkage.handlers.GuiHandler;
import keelfy.darkage.network.ClientPacketHandler;
import keelfytools.KeelfyUtils;
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
public class StoryBook extends Item {

    public StoryBook() {
        setMaxStackSize(1);
        
        if(KeelfyUtils.isClientSide()) {
        	setTextureName("book_written");
        }
    }

    @Override
    public String getItemStackDisplayName(ItemStack is) {
    	if(KeelfyUtils.isClientSide()) {
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
    public void addInformation(ItemStack is, EntityPlayer player, List list, boolean p_77624_4_) {
    	if(KeelfyUtils.isClientSide()) {
	        if (is.hasTagCompound()) {
	            NBTTagCompound nbttagcompound = is.getTagCompound();
	            String s = nbttagcompound.getString("author");
	            
	            if (!StringUtils.isNullOrEmpty(s))
	                list.add(EnumChatFormatting.GRAY + "написал " + s);
	        }
    	}
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ItemStack onItemRightClick(ItemStack is, World world, EntityPlayer player) {
    	if(KeelfyUtils.isClientSide()) {
    		ClientPacketHandler.sendToServer(EnumServerPacket.UPDATEBOOK);
    		GuiHandler.openOnPlayer(EnumGui.STORYBOOK, player);
    	}
        return is;
    }
    
    @Override
	public boolean getShareTag() {
        return true;
    }

    @Override
	@SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack is) {
        return true;
    }
}
