// (c)SJFN@ CommandHelper v14.03.21 by FoxZ@free.fr
// licence : cc-by-nc+no gov,mil usage
package foxz.commandhelper;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import foxz.commandhelper.annotations.Command;
import foxz.commandhelper.annotations.SubCommand;
import foxz.commandhelper.permissions.AbstractPermission;
import keelfyutils.str.KString;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerSelector;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import noppes.npcs.controllers.PlayerData;
import noppes.npcs.controllers.PlayerDataController;

public abstract class AbstractCommandHelper extends CommandHelper {

	public Object ctorParm;
	public ICommandSender pcParam;
	public Object xParam;
	public AbstractCommandHelper parentCmdHelper;
	public AbstractCommandHelper rootCmdHelper;

	public AbstractCommandHelper(final Object sender) {
		this.ctorParm = sender;
		ctor();
	}

	public final void ctor() {
		this.commandHelper.name = this.getClass().getAnnotation(Command.class).name();
		this.commandHelper.usage = this.getClass().getAnnotation(Command.class).usage();
		this.commandHelper.desc = this.getClass().getAnnotation(Command.class).desc();
		// sub
		for (final Class c : this.getClass().getAnnotation(Command.class).sub()) {
			try {
				final String name = ((Command) c.getAnnotation(Command.class)).name().toUpperCase();
				final Constructor<AbstractCommandHelper> ctor = c.getConstructor(Object.class);
				ctor.setAccessible(true);
				final AbstractCommandHelper sc = ctor.newInstance(ctorParm);
				commands.put(name, sc);
			} catch (final Exception ex) {
				Logger.getLogger(AbstractCommandHelper.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		// intern
		for (final Method m : this.getClass().getDeclaredMethods()) {
			final SubCommand sc = m.getAnnotation(SubCommand.class);
			if (sc != null) {
				String name = sc.name();
				if (name.equals(KString.EMPTY)) {
					name = m.getName();
				}
				commands.put(name.toUpperCase(), new MethodSubCmd(this, m));
			}
		}
	}

	public Map<String, CommandHelper> commands = new HashMap<String, CommandHelper>();

	public abstract void help(String cmd, String desc, String usa);

	public abstract void cmdError(String cmd);

	public abstract void error(String err);

	public abstract void info(String info);

	public abstract void playerError(String playername);

	protected class MethodSubCmd extends CommandHelper {

		public List<AbstractPermission> permissions = new ArrayList<AbstractPermission>();

		public MethodSubCmd(final AbstractCommandHelper ch, final Method m) {
			final SubCommand s = m.getAnnotation(SubCommand.class);
			commandHelper.name = s.name();
			if (commandHelper.name.equals(KString.EMPTY)) {
				commandHelper.name = m.getName();
			}
			commandHelper.usage = s.usage();
			commandHelper.desc = s.desc();
			commandHelper.hasEmptyCall = s.hasEmptyCall();

			method = m;
			for (final Class c : s.permissions()) {
				try {
					final Constructor<AbstractPermission> ctor = c.getDeclaredConstructor();
					ctor.setAccessible(true);
					final AbstractPermission i = ctor.newInstance();
					permissions.add(i);
				} catch (final Exception ex) {
					Logger.getLogger(AbstractCommandHelper.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}

		@Override
		public final List addTabCompletion(final ICommandSender par1, final String[] args) {
			final String[] np = currentHelper.usage.split(KString.SPACE);
			if (np.length < args.length)
				return null;
			final String parameter = np[args.length - 1];
			if (parameter.equals("<player>"))
				return CommandBase.getListOfStringsMatchingLastWord(args,
						MinecraftServer.getServer().getAllUsernames());

			return null;
		}

		public Method method;

	}

	public final void allHelp() {
		for (final CommandHelper cur : commands.values()) {
			help(cur.commandHelper.name, cur.commandHelper.desc, KString.EMPTY);
		}
	}

	public Helper currentHelper;

	public Boolean processCommand(final ICommandSender param, String[] args) {
		pcParam = param;
		if (parentCmdHelper == null) {
			rootCmdHelper = this;
		}
		if (args.length == 0) {
			allHelp();
			return true;
		}

		final String cmd = args[0].toUpperCase();
		args = Arrays.copyOfRange(args, 1, args.length);

		if ((cmd.equals("HELP") || args.length == 0) && doHelp(param, args, cmd))
			return true;

		final CommandHelper ch = commands.get(cmd);
		if (ch == null) {
			cmdError(cmd);
			return false;
		}
		if (ch instanceof AbstractCommandHelper) {
			final AbstractCommandHelper f = (AbstractCommandHelper) ch;
			f.parentCmdHelper = this;
			f.rootCmdHelper = this.rootCmdHelper;
			return f.processCommand(param, args);
		} else if (ch instanceof MethodSubCmd) {
			final MethodSubCmd m = (MethodSubCmd) ch;
			m.method.setAccessible(true);
			currentHelper = ch.commandHelper;
			try {
				for (final AbstractPermission p : m.permissions) {
					if (!p.delegate(this, args)) {
						error(p.errorMsg());
						return false;
					}
				}
				return (Boolean) m.method.invoke(this, (Object) args);
			} catch (final Exception ex) {
				Logger.getLogger(AbstractCommandHelper.class.getName()).log(Level.SEVERE, m.commandHelper.name, ex);
			}
			return true;
		} else {
			cmdError(cmd);
		}
		return false;
	}

	private boolean doHelp(final ICommandSender param, final String[] args, String cmd) {
		final boolean isHelp = cmd.equals("HELP");
		if (args.length > 0) {
			cmd = args[0];
		}
		final CommandHelper ch = commands.get(cmd.toUpperCase());
		if (ch != null) {
			if (ch.commandHelper.hasEmptyCall && !isHelp)
				return false;
			if (ch instanceof AbstractCommandHelper) {
				((AbstractCommandHelper) ch).pcParam = param;
				((AbstractCommandHelper) ch).allHelp();
			} else if (ch instanceof MethodSubCmd && ((MethodSubCmd) ch).commandHelper.usage.isEmpty())
				return false;
			else {
				help(ch.commandHelper.name, ch.commandHelper.desc, ch.commandHelper.usage);
			}
		} else {
			allHelp();
		}
		return true;
	}

	@Override
	public List addTabCompletion(final ICommandSender par1, String[] args) {
		if (args.length <= 1) {
			final List<String> list = new ArrayList<String>();
			for (final String command : commands.keySet()) {
				list.add(command.toLowerCase());
			}
			list.add("help");
			return CommandBase.getListOfStringsMatchingLastWord(args, list.toArray(new String[list.size()]));
		}
		final CommandHelper ch = commands.get(args[0].toUpperCase());
		if (ch == null)
			return null;
		args = Arrays.copyOfRange(args, 1, args.length);
		currentHelper = ch.commandHelper;
		return ch.addTabCompletion(par1, args);
	}

	public List<PlayerData> getPlayersData(final String username) {
		final ArrayList<PlayerData> list = new ArrayList<PlayerData>();
		final EntityPlayerMP[] players = PlayerSelector.matchPlayers(pcParam, username);
		if (players == null || players.length == 0) {
			final PlayerData data = PlayerDataController.instance.getDataFromUsername(username);
			if (data != null) {
				list.add(data);
			}
		} else {
			for (final EntityPlayer player : players) {
				list.add(PlayerDataController.instance.getPlayerData(player));
			}
		}

		return list;
	}

	public <T> List<T> getNearbeEntityFromPlayer(final Class<? extends T> cls, final World world, final int x,
			final int y, final int z, final int range) {
		final AxisAlignedBB bb = AxisAlignedBB.getBoundingBox(x, y, z, x + 1, y + 1, z + 1).expand(range, range, range);
		final List<T> list = world.getEntitiesWithinAABB(cls, bb);
		return list;
	}
}
