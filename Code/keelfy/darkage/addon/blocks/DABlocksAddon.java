package keelfy.darkage.addon.blocks;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import keelfy.api.registry.BlockRegistry;
import keelfy.darkage.addon.blocks.register.BlockRegister;

/**
 * @author keelfy
 */
@Mod(modid = DABlocksAddon.MODID, name = DABlocksAddon.NAME, version = DABlocksAddon.VERSION, dependencies = "required-after:witchercore")
public class DABlocksAddon {
	
	public static final String MODID = "witcherblocks";
	public static final String NAME = "Dark Age: Blocks Addon";
	public static final String VERSION = "Build #1";
	
	@SidedProxy(clientSide = "keelfy.darkage.addon.blocks.client.ClientProxy", serverSide = "keelfy.darkage.addon.blocks.CommonProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		BlockRegistry.setResourceDomain(MODID);
		
		new BlockRegister();
		
		proxy.init();
	}
}
