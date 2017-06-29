/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.commands;

import java.io.File;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import keelfy.darkage.DarkAge;
import keelfy.darkage.handlers.server.BookHandler;
import keelfytools.KeelfyUtils;
import keelfytools.KeelfyUtilsServer;
import keelfytools.LocalizationUtils;
import keelfytools.log.Brush;
import net.minecraft.entity.player.EntityPlayer;

/**
 * @author keelfy
 * @created 11 июн. 2017 г.
 */
public class CommandBookList extends DAAdminCommand {

	public CommandBookList() {
		super("booklist", "Список DAB книг");
	}

	@Override
	public void processCommandByPlayer(EntityPlayer player, String[] string) {
		if (KeelfyUtils.isServerSide()) {
			BookHandler bh = DarkAge.instance.bookHandler;
			List<File> books = bh.listFiles(DarkAge.instance.fileHandler.getDABFolderPath());

			String list = StringUtils.EMPTY;
			StringBuilder sb = new StringBuilder();
			int count = 0;
			
			for (File book : books) {
				sb.append(book.getName().replaceAll(".dab", StringUtils.EMPTY));
				sb.append(Brush.GRAY + ", ");
				count++;
			}

			list = sb.toString();
			
			if(count == 0) {
				KeelfyUtilsServer.sendInfoMessage(player, "Книги отсутствуют.");
				return;
			}
			
			KeelfyUtilsServer.sendFineMessage(player, "Найдено " + count + " DAB файлов:");
			KeelfyUtilsServer.sendInfoMessage(player, LocalizationUtils.replaceLastChars(list, ".", 2));
		}
	}
	
	public static String replaceLastTwoChars(String s) {
		if (KeelfyUtils.isServerSide()) {
		    int length = s.length();
		    if (length < 2) 
		    	return StringUtils.EMPTY;
		    return s.substring(0, length - 2) + ".";
		}
		return StringUtils.EMPTY;
	}
}
