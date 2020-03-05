package foxz.command;

import java.util.List;

import foxz.commandhelper.ChMcLogger;
import foxz.commandhelper.annotations.Command;
import foxz.commandhelper.annotations.SubCommand;
import foxz.commandhelper.permissions.OpOnly;
import foxz.commandhelper.permissions.ParamCheck;
import keelfyutils.str.KString;
import noppes.npcs.controllers.Faction;
import noppes.npcs.controllers.PlayerData;
import noppes.npcs.controllers.PlayerFactionData;

@Command(name = "faction", desc = "Операции отвечающие за отношения между игроком и фракциями")
public class CmdFaction extends ChMcLogger {

	public String playername;

	public Faction selectedFaction;
	public List<PlayerData> data;

	public CmdFaction(final Object sender) {
		super(sender);
	}

	@SubCommand(desc = "Добавляет очки", usage = "<points>", permissions = { OpOnly.class, ParamCheck.class })
	public Boolean add(final String[] args) {
		int points;
		try {
			points = Integer.parseInt(args[0]);
		} catch (final NumberFormatException ex) {
			intError();
			return false;
		}
		final int factionid = this.selectedFaction.id;

		for (final PlayerData playerdata : data) {
			final PlayerFactionData playerfactiondata = playerdata.factionData;
			playerfactiondata.increasePoints(factionid, points);
		}
		return true;
	}

	@SubCommand(desc = "Вычитает очки", usage = "<points>", permissions = { OpOnly.class, ParamCheck.class })
	public Boolean subtract(final String[] args) {
		int points;
		try {
			points = Integer.parseInt(args[0]);
		} catch (final NumberFormatException ex) {
			intError();
			return false;
		}
		final int factionid = this.selectedFaction.id;
		for (final PlayerData playerdata : data) {
			final PlayerFactionData playerfactiondata = playerdata.factionData;
			playerfactiondata.increasePoints(factionid, -points);
		}
		return true;
	}

	@SubCommand(desc = "Устанавливает очки по умолчанию", usage = KString.EMPTY, permissions = { OpOnly.class })
	public Boolean reset(final String[] args) {
		for (final PlayerData playerdata : data) {
			playerdata.factionData.factionData.put(this.selectedFaction.id, this.selectedFaction.defaultPoints);
		}
		return true;
	}

	@SubCommand(desc = "Устанавливает очки", usage = "<points>", permissions = { OpOnly.class, ParamCheck.class })
	public Boolean set(final String[] args) {
		int points;
		try {
			points = Integer.parseInt(args[0]);
		} catch (final NumberFormatException ex) {
			intError();
			return false;
		}
		for (final PlayerData playerdata : data) {
			final PlayerFactionData playerfactiondata = playerdata.factionData;
			playerfactiondata.factionData.put(this.selectedFaction.id, points);
		}
		return true;
	}

	@SubCommand(desc = "Расторгает отношения", usage = KString.EMPTY, permissions = { OpOnly.class })
	public Boolean drop(final String[] args) {
		for (final PlayerData playerdata : data) {
			playerdata.factionData.factionData.remove(this.selectedFaction.id);
		}
		return true;
	}

	private final void intError() {
		error("Очки должны быть в числовом формате!");
	}
}
