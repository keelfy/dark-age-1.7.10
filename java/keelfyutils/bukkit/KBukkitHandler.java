package keelfyutils.bukkit;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

import keelfyutils.KUtils;
import keelfyutils.str.KLog;
import net.minecraft.entity.player.EntityPlayer;

/**
 * @author keelfy
 * @created 29 июл. 2017 г.
 */
public enum KBukkitHandler {
	Instance;

	private Class<?> bukkit = null;

	public final void load() {
		if (KUtils.PROTECT_SERVER) {

			if (!KUtils.DEBUG) {
				try {
					bukkit = Class.forName("org.bukkit.Bukkit");
					KLog.info("[KeelfyTools] Bukkit detected!");
				} catch (final ClassNotFoundException e) {
					KLog.info("[KeelfyTools] Bukkit not found.");
					return;
				} catch (final SecurityException e) {
					e.printStackTrace();
				}
			}
			KPermissionsLoader.Instance.load();
		}
	}

	public static final boolean isBukkitEnabled() {
		if (KUtils.PROTECT_SERVER)
			return Instance.bukkit != null;
		return false;
	}

	public static final Player getBukkitPlayer(final EntityPlayer player) {
		if (KUtils.PROTECT_SERVER)
			return Bukkit.getPlayer(player.getCommandSenderName());
		return null;
	}

	public static final Player getBukkitPlayer(final String username) {
		if (KUtils.PROTECT_SERVER)
			return Bukkit.getPlayer(username);
		return null;
	}

	public static final World getBukkitWorld(final net.minecraft.world.World world) {
		if (KUtils.PROTECT_SERVER)
			return Bukkit.getWorld(world.getWorldInfo().getWorldName());
		return null;
	}
}
