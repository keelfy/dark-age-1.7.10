package foxz.commandhelper;

import keelfyutils.str.Brush;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

public class ChMcLogger extends AbstractCommandHelper {

	public ChMcLogger(final Object sender) {
		super(sender);
	}

	public static final String PREFIX = Brush.GRAY + "[CustomNPCs] ";

	public final void sendMessage(final String msg) {
		final ICommandSender sender = pcParam;
		sender.addChatMessage(new ChatComponentText(PREFIX + msg));
	}

	@Override
	public final void info(final String info) {
		sendMessage(Brush.GREEN + info);
	}

	@Override
	public final void help(final String cmd, final String desc, final String usa) {
		if (usa.isEmpty()) {
			sendMessage(String.format("%s = %s", cmd, desc));
		} else {
			sendMessage(String.format("%s %s = %s", cmd, usa, desc));
		}
	}

	@Override
	public final void playerError(final String playername) {
		error(String.format("Неизвестный игрок '%s'!", playername));
	}

	@Override
	public final void cmdError(final String cmd) {
		error(String.format("Неизвестная команда '%s'!", cmd));
	}

	@Override
	public final void error(final String err) {
		sendMessage(Brush.RED + String.format("Ошибка: %s", err));
	}

}
