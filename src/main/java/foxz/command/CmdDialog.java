package foxz.command;

import java.util.List;

import foxz.commandhelper.ChMcLogger;
import foxz.commandhelper.annotations.Command;
import foxz.commandhelper.annotations.SubCommand;
import foxz.commandhelper.permissions.OpOnly;
import foxz.commandhelper.permissions.ParamCheck;
import noppes.npcs.controllers.DialogController;
import noppes.npcs.controllers.PlayerData;

@Command(name = "dialog", desc = "Операции с диалогами", usage = "help")
public final class CmdDialog extends ChMcLogger {

	public CmdDialog(final Object sender) {
		super(sender);
	}

	@SubCommand(desc = "Усиленно прочесть", usage = "<player> <dialog>", permissions = { OpOnly.class,
			ParamCheck.class })
	public boolean read(final String args[]) {
		final String playername = args[0];
		int diagid;
		try {
			diagid = Integer.parseInt(args[1]);
		} catch (final NumberFormatException ex) {
			intError();
			return false;
		}
		final List<PlayerData> data = getPlayersData(playername);
		if (data.isEmpty()) {
			playerError(playername);
			return false;
		}
		for (final PlayerData playerdata : data) {
			playerdata.dialogData.dialogsRead.add(diagid);
			playerdata.saveNBTData(null);
		}
		return true;
	}

	@SubCommand(desc = "Усиленно перестать читать", usage = "<player> <dialog>", permissions = { OpOnly.class,
			ParamCheck.class })
	public boolean unread(final String args[]) {
		final String playername = args[0];
		int diagid;
		try {
			diagid = Integer.parseInt(args[1]);
		} catch (final NumberFormatException ex) {
			intError();
			return false;
		}
		final List<PlayerData> data = getPlayersData(playername);
		if (data.isEmpty()) {
			playerError(playername);
			return false;
		}
		for (final PlayerData playerdata : data) {
			playerdata.dialogData.dialogsRead.remove(diagid);
			playerdata.saveNBTData(null);
		}
		return true;
	}

	@SubCommand(desc = "Перезагрузить диалоги с диска", permissions = { OpOnly.class })
	public boolean reload(final String args[]) {
		new DialogController();
		return true;
	}

	private final void intError() {
		error("ID диалога должно быть задано числом!");
	}
}
