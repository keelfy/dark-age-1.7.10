package keelfy.darkfactions;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import keelfy.darkfactions.common.DFCommon;
import keelfyutils.str.KLog;

/**
 * @author keelfy
 * @created 19 ноя. 2017 г.
 */
@Mod(modid = DarkFactions.MOD_ID, name = DarkFactions.MOD_NAME, version = DarkFactions.MOD_VERSION, dependencies = "required-after:darkcore")
public final class DarkFactions {

	public static final String MOD_ID = "darkfactions";
	public static final String MOD_NAME = "Dark Factions";
	public static final String MOD_VERSION = "1.0";

	public static final String PROXY_SERVER = "keelfy.darkfactions.server.DFServer";
	public static final String PROXY_CLIENT = "keelfy.darkfactions.client.DFClient";

	@Instance(DarkFactions.MOD_ID)
	public static DarkFactions instance;

	@SidedProxy(clientSide = DarkFactions.PROXY_CLIENT, serverSide = DarkFactions.PROXY_SERVER)
	public static DFCommon proxy;

	@EventHandler
	public final void preInit(final FMLPreInitializationEvent event) {
		proxy.preInit(event);
	}

	@EventHandler
	public final void init(final FMLInitializationEvent event) {
		proxy.init(event);

		KLog.info(DarkFactions.MOD_NAME + " by keelfy");
		KLog.info("Version: " + DarkFactions.MOD_VERSION);
	}

	@EventHandler
	public final void serverStart(final FMLServerStartingEvent event) {
		proxy.serverStarting(event);
	}
}
