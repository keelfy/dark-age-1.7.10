/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.commands;

import keelfytools.KeelfyUtils;
import keelfytools.KeelfyUtilsServer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

/**
 * @author keelfy
 */
public class CommandChangeName extends DAAdminCommand {

	public CommandChangeName() {
		super("setname", "Меняет имя у предмета в руках");
	}
	
	@Override
	public void processCommandByPlayer(EntityPlayer player, String[] string) {
		if(KeelfyUtils.isServerSide()) {
			String name = string[0];
			
			if(player.getCurrentEquippedItem() != null) {
				ItemStack stack = player.getCurrentEquippedItem();
				stack.setStackDisplayName(name.replace('&', '§'));
				
				KeelfyUtilsServer.sendFineMessage(player, "Новое имя предмета: " + name.replace('&', '§'));
			} else {
				KeelfyUtilsServer.sendErrMessage(player, "У вас нет предмета в руках");
			}
		}
	}
}

