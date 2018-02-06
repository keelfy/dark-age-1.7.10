package keelfyutils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLServerAboutToStartEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import keelfyutils.bukkit.KBukkitHandler;
import keelfyutils.commands.KBaseCommand;
import keelfyutils.commands.KCommand;
import keelfyutils.str.KLog;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

/**
 * @author keelfy
 * @created 21 июн. 2017 г.
 */
@Mod(modid = "keelfyutils", name = "Keelfy's Utils")
public final class KUtils {

	public static final boolean SERVER = false;
	public static final boolean DEBUG = true;

	public static final boolean SINGLEPLAYER = true;

	public static final boolean PROTECT_SERVER = SERVER || DEBUG;
	public static final boolean PROTECT_CLIENT = !SERVER || DEBUG;

	public static final String DEPENDENCY = "required-after:keelfyutils";

	public static final boolean isClient() {
		return FMLCommonHandler.instance().getSide().isClient();
	}

	public static final boolean isServer() {
		return FMLCommonHandler.instance().getSide().isServer();
	}

	@Instance("keelfyutils")
	public static KUtils instance;

	private final List<Class<?>> registeredCommandHandlers = new ArrayList();
	public ServerCommandManager manager;

	public final void registerCommandHandler(final Class<?> clazz) {
		if (KUtils.PROTECT_SERVER) {
			if (this.registeredCommandHandlers.contains(clazz)) {
				KLog.err("Ошибка при регистрации обработчика команд");
				return;
			}

			registeredCommandHandlers.add(clazz);
		}
	}

	@EventHandler
	public final void serverStarting(final FMLServerAboutToStartEvent event) {
		if (KUtils.PROTECT_SERVER) {
			manager = (ServerCommandManager) MinecraftServer.getServer().getCommandManager();
		}
	}

	@EventHandler
	public final void serverStart(final FMLServerStartedEvent event) {
		if (KUtils.PROTECT_SERVER) {
			for (final Class<?> clazz : registeredCommandHandlers) {
				for (final Method method : clazz.getMethods()) {
					if (method.isAnnotationPresent(KCommand.class)) {
						final KBaseCommand command = new KBaseCommand(
								method.getAnnotation(KCommand.class).onlyByPlayer()) {
							@Override
							public void processCommand(final ICommandSender sender, final String[] args) {
								if (KUtils.PROTECT_SERVER) {
									try {
										if (isOnlyByPlayer && !(sender instanceof EntityPlayer))
											return;

										method.invoke(clazz.newInstance(), sender, args);
									} catch (IllegalAccessException e) {
										e.printStackTrace();
									} catch (IllegalArgumentException e) {
										e.printStackTrace();
									} catch (InvocationTargetException e) {
										e.printStackTrace();
									} catch (InstantiationException e) {
										e.printStackTrace();
									}
								}
							}
						};

						command.setName(method.getName().toLowerCase());
						command.setUsage(method.getAnnotation(KCommand.class).usage());

						manager.registerCommand(command);
					}
				}
			}

			MinecraftServer server = MinecraftServer.getServer();
			if (server != null && server.isDedicatedServer())
				KBukkitHandler.Instance.load();
		}
	}
}
