package keelfy.api.common;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

/**
 * @author keelfy
 */
public class BaseCommand extends CommandBase {

	String name;
	String usage;
	
	public BaseCommand(String name, String usage, ServerCommandManager manager) {
		this.name = name;
		this.usage = usage;
		
		manager.registerCommand(this);
	}
	
	public String getCommandName() {
		return name;
	}
	
	public String getCommandUsage(ICommandSender icommandsender) {
		return usage;
	}
	
	public void processCommand(ICommandSender icommandsender, String[] astring) {
		if(icommandsender instanceof EntityPlayer) {
			this.processCommandByPlayer((EntityPlayer)icommandsender, astring);
		}
	}
	
	public void processCommandByPlayer(EntityPlayer sender, String[] string) {
		
	}
}
