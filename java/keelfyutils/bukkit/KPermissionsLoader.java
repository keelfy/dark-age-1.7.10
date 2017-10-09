package keelfyutils.bukkit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import keelfyutils.KUtils;
import keelfyutils.str.KLog;
import net.minecraft.entity.player.EntityPlayer;

/**
 * @author Noppes & keelfy
 */
public enum KPermissionsLoader {
	Instance;

	public final void load() {
		if (KUtils.PROTECT_SERVER) {
			try {
				KLog.info("[KeelfyTools] Bukkit permissions enabled.");
				KLog.info("[KeelfyTools] Permissions available:");
				Collections.sort(Permission.permissions, String.CASE_INSENSITIVE_ORDER);
				for (final String p : Permission.permissions) {
					KLog.info(p);
				}
			} catch (final SecurityException e) {
				e.printStackTrace();
			}
		}
	}

	public static final boolean has(final EntityPlayer player, final Permission permission) {
		if (KUtils.PROTECT_SERVER) {
			if (KBukkitHandler.isBukkitEnabled())
				return KBukkitHandler.getBukkitPlayer(player).hasPermission(permission.name);
		}
		return true;
	}

	public static final class Permission {
		private static final List<String> permissions = new ArrayList();
		public String name;

		public Permission(final String name) {
			if (KUtils.PROTECT_SERVER) {
				this.name = name;
				if (!permissions.contains(name)) {
					permissions.add(name);
				}
			}
		}
	}

	public static final boolean has(final EntityPlayer player, final String permission) {
		if (KUtils.PROTECT_SERVER) {
			if (KBukkitHandler.isBukkitEnabled())
				return KBukkitHandler.getBukkitPlayer(player).hasPermission(permission);
		}
		return true;
	}
}
