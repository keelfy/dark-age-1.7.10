/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.commands;

import keelfy.darkage.DarkAge;
import keelfy.darkage.entities.player.DAPlayer;
import keelfy.darkage.handlers.registers.ItemRegister;
import keelfy.darkage.handlers.server.BookHandler;
import keelfy.darkage.items.storybook.AbstractStoryBook;
import keelfytools.KeelfyUtils;
import keelfytools.KeelfyUtilsServer;
import keelfytools.LocalizationUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * @author keelfy
 * @created 11 июн. 2017 г.
 */
public class CommandGiveBook extends DAAdminCommand {

	public CommandGiveBook() {
		super("givebook", "Выдает книгу из смарт-библиотеки");
	}
	
	@Override
	public void processCommandByPlayer(EntityPlayer player, String[] string) {
		if(KeelfyUtils.isServerSide()) {
			if(string.length > 1) {
				int id = Integer.MIN_VALUE;
				int amount = 0;
				String fileName = null;
				
				BookHandler bookHandler = DarkAge.instance.bookHandler;
				
				try {
					id = Integer.parseInt(string[0]);
				} catch (NumberFormatException e) {
					fileName = string[0];
				}
				
				try {
					amount = Integer.parseInt(string[1]);
				} catch(NumberFormatException e) {
					KeelfyUtilsServer.sendErrMessage(player, "Неккоректное число книг.");
				}
				
				AbstractStoryBook slBook = null;
				
				if(id != Integer.MIN_VALUE) {
					slBook = bookHandler.getBookByID(id);
				} else if(fileName != null) {
					slBook = bookHandler.loadBookFromDABFile(fileName);
					System.out.println(slBook.author);
					System.out.println(slBook.title);
				}
				
				if(slBook == null) {
					KeelfyUtilsServer.sendErrMessage(player, "Неверное название файла книги или ID.");
					return;
				}
				
				if(amount != 0) {
					ItemStack book = new ItemStack(ItemRegister.writtenBook);
					book.stackSize = amount;
					
					NBTTagCompound data = new NBTTagCompound();
					data.setTag("pages", AbstractStoryBook.writePagesToNBT(slBook.pages));
					data.setString("author", LocalizationUtils.withColors(slBook.getAuthor()));
					data.setString("title", LocalizationUtils.withColors(slBook.getTitle()));
					data.setInteger("id", slBook.id);
					book.setTagCompound(data);
					
					if(player.capabilities.isCreativeMode) 
						player.inventory.addItemStackToInventory(book.copy());
					else DAPlayer.get(player).inventory.addItemStackToInventory(book.copy());
				}
				
			} else {
				KeelfyUtilsServer.sendErrMessage(player, "Укажите ID книги первым и количество книг вторым параметрами.");
			}
		}
	}
}

