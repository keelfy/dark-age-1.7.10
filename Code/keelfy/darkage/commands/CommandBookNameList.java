/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.commands;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
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
public class CommandBookNameList extends DAAdminCommand {

	public CommandBookNameList() {
		super("booknamelist", "Список DAB книг с названиями");
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
				sb.append(Brush.GRAY + "\'");
				sb.append(Brush.GRAY + DarkAge.instance.bookHandler.loadBookFromDABFile(FilenameUtils.removeExtension(book.getName())).title);
				sb.append(Brush.GRAY + "\', ");
				count++;
			}

			list = sb.toString();

			if(count == 0) {
				KeelfyUtilsServer.sendInfoMessage(player, "Книги отсутствуют.");
				return;
			}
			
			KeelfyUtilsServer.sendFineMessage(player, "Найдено " + count + " DAB книг:");
			KeelfyUtilsServer.sendInfoMessage(player, LocalizationUtils.replaceLastChars(list, ".", 2));
		}
	}
}
