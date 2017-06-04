package keelfy.darkage.util;

import java.util.Timer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author keelfy
 */
public final class DAUtil {
	public static final String MODID = "witchercore";
	public static final String MODNAME = "Dark Age";
	public static final String MODVER = "v12.5";
	
	public static final String PROXY_SERVER = "keelfy.darkage.CommonProxy";
	public static final String PROXY_CLIENT = "keelfy.darkage.client.ClientProxy";
	
	public static final String HOOK_CONTAINER = "keelfy.darkage.asm.DATransformer";
	
	public static final String NETWORK_CHANNEL = MODID + "_channel";
	
	public static final Timer TIMER = new Timer();
	public static final Logger LOGGER = LogManager.getLogger(MODNAME);
	
	public static final boolean DEBUG_MODE = false;
	public static final boolean SERVER = false;
}
