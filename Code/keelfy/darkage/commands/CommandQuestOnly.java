/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.commands;

import keelfy.darkage.items.DAItem;
import keelfytools.KeelfyUtils;
import keelfytools.KeelfyUtilsServer;
import net.minecraft.entity.player.EntityPlayer;

/**
 * @author keelfy
 * @created 11 июн. 2017 г.
 */
public class CommandQuestOnly extends DAAdminCommand {

	public CommandQuestOnly() {
		super("questonly", "Дает предмету в руках квестовый статус");
	}
	
	@Override
	public void processCommandByPlayer(EntityPlayer player, String[] string) {
		if(KeelfyUtils.isServerSide()) {
			if(player.getCurrentEquippedItem() != null) {
				DAItem.setQuestOnly(player.getCurrentEquippedItem());
				
				KeelfyUtilsServer.sendFineMessage(player, "Для предмета в руках установлен квестовый статус");
			} else {
				KeelfyUtilsServer.sendErrMessage(player, "У вас нет предмета в руках");
			}
		}
	}
}

