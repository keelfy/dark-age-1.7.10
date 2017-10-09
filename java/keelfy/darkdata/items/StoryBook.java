/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkcore.network.DACNetwork;
import keelfy.darkdata.constants.EnumGui;
import keelfy.darkdata.handlers.DAGuiHandler;
import keelfy.darkdata.network.EnumSPackets;
import keelfyutils.KUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StringUtils;
import net.minecraft.world.World;

/**
 * @author keelfy
 */
public final class StoryBook extends Item {

	public StoryBook() {
		setMaxStackSize(1);

		if (KUtils.PROTECT_CLIENT) {
			setTextureName("book_written");
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getItemStackDisplayName(final ItemStack is) {
		if (KUtils.PROTECT_CLIENT)
			return (is.hasTagCompound() && is.getTagCompound().hasKey("title")) ? is.getTagCompound().getString("title") : super.getItemStackDisplayName(is);
		return super.getItemStackDisplayName(is);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(final ItemStack is, final EntityPlayer player, final List list, final boolean p_77624_4_) {
		if (KUtils.PROTECT_CLIENT) {
			if (is.hasTagCompound() && is.getTagCompound().hasKey("author")) {
				final String s = is.getTagCompound().getString("author");

				if (!StringUtils.isNullOrEmpty(s)) {
					list.add(EnumChatFormatting.GRAY + "написал " + s);
				}
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ItemStack onItemRightClick(final ItemStack is, final World world, final EntityPlayer player) {
		if (KUtils.PROTECT_CLIENT) {
			DACNetwork.sendToServer(EnumSPackets.UpdateBook);
			DAGuiHandler.openGui(EnumGui.StoryBook);
		}
		return is;
	}

	@Override
	public boolean getShareTag() {
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(final ItemStack is) {
		return true;
	}
}
