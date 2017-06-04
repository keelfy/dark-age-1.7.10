package keelfy.darkage.command;

import keelfy.api.Brush;
import keelfy.api.KUtil;
import keelfy.api.common.BaseCommand;
import keelfy.darkage.entity.player.DAPlayer;
import keelfy.darkage.entity.player.DAPlayerUtil.Property;
import keelfy.darkage.util.DAUtil;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;

/**
 * @author keelfy
 */
public class CommandDebug extends BaseCommand {

	public CommandDebug(ServerCommandManager manager) {
		super("wcdebug", "", manager);
	}
	
	@Override
	public void processCommandByPlayer(EntityPlayer sender, String[] string) {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			if(KUtil.isAdmin(sender)) {
				KUtil.send(sender, "----On Server Side----");
				KUtil.send(sender, (int)DAPlayer.get(sender).get(Property.HEALTH) + "/" + (int)DAPlayer.get(sender).getPlayerMaxHealth() + " HP +" + String.format("%.1f", DAPlayer.get(sender).get(Property.HEALTH_IN_TICK) * 20) + "/sec");
				KUtil.send(sender, "Class - " + DAPlayer.get(sender).getPlayerClass().name());
			} else {
				KUtil.send(sender, Brush.RED + "У вас нет прав использовать эту команду!");
			}
		}
	}
}

