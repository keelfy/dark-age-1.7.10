package keelfy.darkage;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import keelfy.api.network.PacketDispatcher;
import keelfy.api.registry.BlockRegistry;
import keelfy.api.registry.ItemRegistry;
import keelfy.darkage.command.CommandDebug;
import keelfy.darkage.event.EventListener;
import keelfy.darkage.event.TickListener;
import keelfy.darkage.handler.GuiHandler;
import keelfy.darkage.handler.client.FileHandler;
import keelfy.darkage.handler.registers.EntityRegister;
import keelfy.darkage.handler.registers.ItemRegister;
import keelfy.darkage.network.client.CustomClientMessage;
import keelfy.darkage.network.server.CustomServerMessage;
import keelfy.darkage.util.DATab;
import keelfy.darkage.util.DAUtil;
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
		
		packetSystemInit();
		
		new DATab();
		proxy.preInit(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		new EventListener();
		new TickListener();
		new GuiHandler();
		
		proxy.init(event);
		
		DAUtil.LOGGER.info("Авторы мода: keelfy & RedSokol");
	}

	@EventHandler
	public void init(FMLPostInitializationEvent event) {
		proxy.postInit(event);
	}
	
	@EventHandler
	public void serverStart(FMLServerStartingEvent event) {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			manager = (ServerCommandManager) MinecraftServer.getServer().getCommandManager();
			new CommandDebug(manager);
		}
	}
	
	private void packetSystemInit() {
		PacketDispatcher dis = PacketDispatcher.getInstance();
		dis.registerChannel(DAUtil.NETWORK_CHANNEL);
		dis.registerMessage(CustomClientMessage.class);
		dis.registerMessage(CustomServerMessage.class);
	}
}
