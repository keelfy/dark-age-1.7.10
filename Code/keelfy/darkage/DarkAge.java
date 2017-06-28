/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import keelfy.darkage.blocks.register.BlockRegister;
import keelfy.darkage.commands.CommandBookList;
import keelfy.darkage.commands.CommandBookNameList;
import keelfy.darkage.commands.CommandChangeName;
import keelfy.darkage.commands.CommandGiveBook;
import keelfy.darkage.commands.CommandQuestOnly;
import keelfy.darkage.commands.CommandUpdateBooks;
import keelfy.darkage.events.EventListener;
import keelfy.darkage.events.TickListener;
import keelfy.darkage.handlers.FileHandler;
import keelfy.darkage.handlers.GuiHandler;
import keelfy.darkage.handlers.TabsRegister;
import keelfy.darkage.handlers.registers.EntityRegister;
import keelfy.darkage.handlers.registers.ItemRegister;
import keelfy.darkage.handlers.registers.PacketRegister;
import keelfy.darkage.handlers.server.BookHandler;
import keelfy.darkage.handlers.server.ConfigHandler;
import keelfy.darkage.utils.DAUtils;
import keelfytools.KeelfyUtils;
import keelfytools.log.KeelfyLog;
import keelfytools.registry.BlockRegistry;
import keelfytools.registry.ItemRegistry;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.server.MinecraftServer;

/**
 * @author keelfy
 * @created 30 фев. 2019 г.
 */
@Mod(modid = DAUtils.MODID, name = DAUtils.MODNAME, version = DAUtils.MODVER)
public class DarkAge {

	@Instance(DAUtils.MODID)
	public static DarkAge instance;

	@SidedProxy(clientSide = DAUtils.PROXY_CLIENT, serverSide = DAUtils.PROXY_SERVER)
	public static CommonProxy proxy;

	public FileHandler fileHandler;
	public BookHandler bookHandler;
	public ConfigHandler configHandler;
	public ServerCommandManager manager;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ItemRegistry.setResourceDomain(DAUtils.MODID);
		BlockRegistry.setResourceDomain(DAUtils.MODID);
		
		fileHandler = new FileHandler(event.getModConfigurationDirectory());
		bookHandler = new BookHandler();
		configHandler = new ConfigHandler();

		ItemRegister.Instance.init();
		BlockRegister.Instance.init();
		EntityRegister.Instance.init();
		PacketRegister.Instance.init();
		TabsRegister.Instance.init();

		proxy.preInit(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		EventListener.Instance.register();
		TickListener.Instance.register();
		GuiHandler.Instance.register();

		proxy.init(event);

		KeelfyLog.info("Авторы мода: keelfy & RedSokol");
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
	}

	@EventHandler
	public void serverStart(FMLServerStartingEvent event) {
		if (KeelfyUtils.isServerSide()) {
			manager = (ServerCommandManager) MinecraftServer.getServer().getCommandManager();

			new CommandChangeName();
			new CommandQuestOnly();
			new CommandGiveBook();
			new CommandBookList();
			new CommandBookNameList();
			new CommandUpdateBooks();
		}
	}
}
