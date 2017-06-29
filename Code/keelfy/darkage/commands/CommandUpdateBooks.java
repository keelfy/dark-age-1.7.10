/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.commands;

import java.util.List;

import keelfy.darkage.DarkAge;
import keelfy.darkage.handlers.registers.ItemRegister;
import keelfy.darkage.items.storybook.AbstractStoryBook;
import keelfy.darkage.items.storybook.StoryBook;
import keelfytools.KeelfyUtils;
import keelfytools.KeelfyUtilsServer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.roles.RoleTrader;

/**
 * @author keelfy
 * @created 11 июн. 2017 г.
 */
public class CommandUpdateBooks extends DAAdminCommand {

	public CommandUpdateBooks() {
		super("updatebooks", "Обновляет книги у всех игроков на сервере");
	}
	
	@Override
	public void processCommandByPlayer(EntityPlayer sender, String[] string) {
		if(KeelfyUtils.isServerSide()) {
			List<Entity> entities = sender.worldObj.loadedEntityList;
			
			KeelfyUtilsServer.sendInfoMessage(sender, "Обновление началось...");
			int count = 0;
			for (Entity entity : entities) {
				if(entity instanceof EntityPlayer) {
					EntityPlayer player = (EntityPlayer) entity;
					if(player.inventory.hasItem(ItemRegister.writtenBook)) {
						for (ItemStack stack : player.inventory.mainInventory) {
							if(updateBook(stack)) 
								count++;
						}
					}
				} else if(entity instanceof EntityNPCInterface) {
					EntityNPCInterface npc = (EntityNPCInterface) entity;
					
					if(npc.roleInterface instanceof RoleTrader) {
						RoleTrader trader = (RoleTrader) npc.roleInterface;
						
						if(trader.hasStoryBook()) {
							for (ItemStack stack : trader.inventorySold.items.values()) {
								if(updateBook(stack)) 
									count++;
							}
							
							for (ItemStack stack : trader.inventoryCurrency.items.values()) {
								if(updateBook(stack)) 
									count++;
							}
						}
					}
				}
			}
			KeelfyUtilsServer.sendFineMessage(sender, "Успешно обновлено " + count + " книг!");
		}
	}
	
	private final boolean updateBook(ItemStack stack) {
		if(KeelfyUtils.isServerSide()) {
			if(stack != null && stack.getItem() instanceof StoryBook && stack.hasTagCompound() && stack.getTagCompound().hasKey("id")) {
				AbstractStoryBook slBook = DarkAge.instance.bookHandler.getContent(stack);
				
				if (slBook != null) {
					stack.getTagCompound().setTag("pages", AbstractStoryBook.writePagesToNBT(slBook.pages));
					stack.getTagCompound().setString("title", slBook.getTitle());
					stack.getTagCompound().setString("author", slBook.getAuthor());
					return true;
				}
			}
		}
		return false;
	}
}

