/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.handlers;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import keelfy.darkage.DarkAge;
import keelfy.darkage.blocks.tileentity.TileEntityLootBag;
import keelfy.darkage.client.gui.DAGuiInventory;
import keelfy.darkage.client.gui.GuiSelectClass;
import keelfy.darkage.client.gui.GuiStoryBook;
import keelfy.darkage.constants.EnumGui;
import keelfy.darkage.entities.player.DAPlayer;
import keelfy.darkage.inventory.lootbag.ContainerLootBag;
import keelfy.darkage.inventory.player.DAContainerPlayerInventory;
import keelfy.darkage.items.storybook.StoryBook;
import keelfytools.KeelfyUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * @author keelfy
 */
public enum GuiHandler implements IGuiHandler {
	Instance;
	
	public final void register() {
		NetworkRegistry.INSTANCE.registerGuiHandler(DarkAge.instance, this);
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(KeelfyUtils.isServerSide()) {
			if (ID == EnumGui.INVENTORY.ordinal()) {
				return new DAContainerPlayerInventory(player, DAPlayer.get(player).inventory);
			} else if (ID == EnumGui.LOOTBAG.ordinal()) {
				return new ContainerLootBag((TileEntityLootBag) world.getTileEntity(x, y, z));
			}
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(KeelfyUtils.isClientSide()) {
			EnumGui selectedGui = EnumGui.values() [ID];
			
			switch(selectedGui) {
			case INVENTORY:
				return new DAGuiInventory(player, DAPlayer.get(player).inventory, DAPlayer.get(player).inventoryContainer, new DAContainerPlayerInventory(player, DAPlayer.get(player).inventory));
			case LOOTBAG:
				return new DAGuiInventory(player, DAPlayer.get(player).inventory, DAPlayer.get(player).inventoryContainer, new DAContainerPlayerInventory(player, DAPlayer.get(player).inventory));
			case SELECTCLASS:
				return new GuiSelectClass();
			case STORYBOOK:
				if(player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() instanceof StoryBook) {
					return new GuiStoryBook(player, player.getCurrentEquippedItem());
				}
			}
		}
		return null;
	}
	
	public static void openOnPlayer(EnumGui gui, EntityPlayer player) {
		player.openGui(DarkAge.instance, gui.ordinal(), player.worldObj, (int) player.posX, (int) player.posY, (int) player.posZ);
	}
}
