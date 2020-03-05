package keelfyutils.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

/**
 * @author keelfy
 */
public class KBaseCommand extends CommandBase {

	private String name;
	private String usage;

	public boolean isOnlyByPlayer;

	public KBaseCommand(final boolean obp) {
		this.isOnlyByPlayer = obp;
	}

	public final void setName(final String name) {
		this.name = name;
	}

	public final void setUsage(final String usage) {
		this.usage = usage;
	}

	@Override
	public final String getCommandName() {
		return name;
	}

	@Override
	public final String getCommandUsage(final ICommandSender icommandsender) {
		return usage;
	}

	@Override
	public void processCommand(final ICommandSender icommandsender, final String[] astring) {}
}
