package foxz.command;

import java.util.List;

import foxz.commandhelper.ChMcLogger;
import foxz.commandhelper.annotations.Command;
import foxz.commandhelper.annotations.SubCommand;
import foxz.commandhelper.permissions.OpOnly;
import foxz.commandhelper.permissions.ParamCheck;
import noppes.npcs.controllers.DialogController;
import noppes.npcs.controllers.PlayerData;
import noppes.npcs.controllers.Quest;
import noppes.npcs.controllers.QuestController;
import noppes.npcs.controllers.QuestData;

@Command(name = "quest", usage = "help", desc = "Операции с квестами")
public final class CmdQuest extends ChMcLogger {

	public CmdQuest(final Object sender) {
		super(sender);
	}

	@SubCommand(desc = "Начинает квест", usage = "<player> <quest>", permissions = { OpOnly.class, ParamCheck.class })
	public Boolean start(final String[] args) {
		final String playername = args[0];
		int questid;
		try {
			questid = Integer.parseInt(args[1]);
		} catch (final NumberFormatException ex) {
			intError();
			return false;
		}
		final List<PlayerData> data = getPlayersData(playername);
		if (data.isEmpty()) {
			playerError(playername);
			return false;
		}
		final Quest quest = QuestController.instance.quests.get(questid);
		if (quest == null) {
			idError();
			return false;
		}
		for (final PlayerData playerdata : data) {
			final QuestData questdata = new QuestData(quest);
			playerdata.questData.activeQuests.put(questid, questdata);
			playerdata.saveNBTData(null);
		}
		return true;
	}

	@SubCommand(desc = "Завершает квест", usage = "<player> <quest>", permissions = { OpOnly.class, ParamCheck.class })
	public Boolean finish(final String args[]) {
		final String playername = args[0];
		int questid;
		try {
			questid = Integer.parseInt(args[1]);
		} catch (final NumberFormatException ex) {
			intError();
			return false;
		}
		final List<PlayerData> data = getPlayersData(playername);
		if (data.isEmpty()) {
			playerError(playername);
			return false;
		}

		final Quest quest = QuestController.instance.quests.get(questid);
		if (quest == null) {
			idError();
			return false;
		}
		for (final PlayerData playerdata : data) {
			playerdata.questData.finishedQuests.put(questid, System.currentTimeMillis());
			playerdata.saveNBTData(null);
		}
		return true;
	}

	@SubCommand(desc = "Останавливает начатый квест", usage = "<player> <quest>", permissions = { OpOnly.class,
			ParamCheck.class })
	public Boolean stop(final String[] args) {
		final String playername = args[0];
		int questid;
		try {
			questid = Integer.parseInt(args[1]);
		} catch (final NumberFormatException ex) {
			intError();
			return false;
		}
		final List<PlayerData> data = getPlayersData(playername);
		if (data.isEmpty()) {
			playerError(playername);
			return false;
		}
		final Quest quest = QuestController.instance.quests.get(questid);
		if (quest == null) {
			idError();
			return false;
		}
		for (final PlayerData playerdata : data) {
			playerdata.questData.activeQuests.remove(questid);
			playerdata.saveNBTData(null);
		}
		return true;
	}

	@SubCommand(desc = "Удаляет квест из активных и завершенных квестов", usage = "<player> <quest>", permissions = {
			OpOnly.class, ParamCheck.class })
	public Boolean remove(final String[] args) {
		final String playername = args[0];
		int questid;
		try {
			questid = Integer.parseInt(args[1]);
		} catch (final NumberFormatException ex) {
			intError();
			return false;
		}
		final List<PlayerData> data = getPlayersData(playername);
		if (data.isEmpty()) {
			playerError(playername);
			return false;
		}
		final Quest quest = QuestController.instance.quests.get(questid);
		if (quest == null) {
			idError();
			return false;
		}
		for (final PlayerData playerdata : data) {
			playerdata.questData.activeQuests.remove(questid);
			playerdata.questData.finishedQuests.remove(questid);
			playerdata.saveNBTData(null);
		}
		return true;
	}

	@SubCommand(desc = "Перезагружает квесты с диска", permissions = { OpOnly.class })
	public boolean reload(final String args[]) {
		new DialogController();
		return true;
	}

	private final void intError() {
		error("ID квеста должен быть в числовом виде!");
	}

	private final void idError() {
		error("Неизвестный ID квеста!");
	}
}
