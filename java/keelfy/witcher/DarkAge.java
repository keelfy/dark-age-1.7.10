package keelfy.witcher;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import keelfy.api.registry.BlockRegistry;
import keelfy.api.registry.ItemRegistry;
import keelfy.witcher.command.CommandDebug;
import keelfy.witcher.event.EventListener;
import keelfy.witcher.event.TickListener;
import keelfy.witcher.handler.GuiHandler;
import keelfy.witcher.handler.PacketHandler;
import keelfy.witcher.handler.client.FileHandler;
import keelfy.witcher.handler.registers.EntityRegister;
import keelfy.witcher.handler.registers.ItemRegister;
import keelfy.witcher.util.DATab;
import keelfy.witcher.util.DAUtil;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.server.MinecraftServer;

/**
 * @author keelfy
 */
@Mod(modid = DAUtil.MODID, name = DAUtil.MODNAME, version = DAUtil.MODVER)
public class DarkAge {

	@Instance(DAUtil.MODID)
	public static DarkAge instance;

	@SidedProxy(clientSide = DAUtil.PROXY_CLIENT, serverSide = DAUtil.PROXY_SERVER)
	public static CommonProxy proxy;
	
	public FileHandler fileHandler;
	public ServerCommandManager manager;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ItemRegistry.setResourceDomain(DAUtil.MODID);
		BlockRegistry.setResourceDomain(DAUtil.MODID);
		
		fileHandler = new FileHandler(event.getModConfigurationDirectory());
		
		new ItemRegister();
		new EntityRegister();
		
		new PacketHandler();
		
		new DATab();
		proxy.preInit(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		new EventListener();
		new TickListener();
		new GuiHandler();
		
		proxy.init(event);
		
		DAUtil.logger.info("Авторы мода: keelfy & RedSokol");
	}

	@EventHandler
	public void serverStart(FMLServerStartingEvent event) {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			manager = (ServerCommandManager) MinecraftServer.getServer().getCommandManager();
			new CommandDebug(manager);
		}
	}
}
