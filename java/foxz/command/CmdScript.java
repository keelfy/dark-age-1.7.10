package foxz.command;

import foxz.commandhelper.ChMcLogger;
import foxz.commandhelper.annotations.Command;
import foxz.commandhelper.annotations.SubCommand;
import foxz.commandhelper.permissions.OpOnly;
import noppes.npcs.controllers.ScriptController;

@Command(name = "script", desc = "Операции со скриптами.")
public final class CmdScript extends ChMcLogger {

	public CmdScript(final Object sender) {
		super(sender);
	}

	@SubCommand(desc = "Перезагружает и сохраняет информацию из папки, где сохранены скрипты.", permissions = {
			OpOnly.class })
	public final Boolean reload(final String args[]) {
		if (ScriptController.Instance.loadStoredData()) {
			info("Перезагрузка завершена.");
		} else {
			error("Ошибка перезагрузки сохраненной информации!");
		}
		return true;
	}
}
