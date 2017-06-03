package keelfy.witcherBlocks;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import keelfy.api.registry.BlockRegistry;
import keelfy.witcherBlocks.register.BlockRegister;

/**
 * @author keelfy
 */
@Mod(modid = WitcherBlocks.MODID, name = WitcherBlocks.NAME, version = WitcherBlocks.VERSION, dependencies = "required-after:witchercore")
public class WitcherBlocks {
	
	public static final String MODID = "witcherblocks";
	public static final String NAME = "The Witcher Blocks";
	public static final String VERSION = "Build #1";
	
	@SidedProxy(clientSide = "keelfy.witcherBlocks.client.ClientProxy", serverSide = "keelfy.witcherBlocks.CommonProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		BlockRegistry.setResourceDomain(MODID);
		
		new BlockRegister();
		
		proxy.init();
	}
}
