/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.commands;

import keelfy.darkage.DarkAge;
import keelfytools.KeelfyUtils;
import keelfytools.KeelfyUtilsServer;
import keelfytools.log.Brush;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

/**
 * @author keelfy
 */
public class DAAdminCommand extends CommandBase {

	String name;
	String usage;
	
	public DAAdminCommand(String name, String usage) {
		if(KeelfyUtils.isServerSide()) {
			this.name = name;
			this.usage = usage;
			
			DarkAge.instance.manager.registerCommand(this);
		}
	}
	
	@Override
	public String getCommandName() {
		return name;
	}
	
	@Override
	public String getCommandUsage(ICommandSender icommandsender) {
		return usage;
	}
	
	@Override
	public void processCommand(ICommandSender icommandsender, String[] astring) {
		if(KeelfyUtils.isServerSide()) {
			if(icommandsender instanceof EntityPlayer) {
				if(KeelfyUtilsServer.isAdmin(icommandsender) || KeelfyUtils.DEBUG_MODE) {
					this.processCommandByPlayer((EntityPlayer)icommandsender, astring);
				} else 
					KeelfyUtilsServer.sendMessage((EntityPlayer)icommandsender, Brush.RED + "У вас нет прав для выполнения этой команды!");
			}
		}
	}

	public void processCommandByPlayer(EntityPlayer sender, String[] string) {}
}
