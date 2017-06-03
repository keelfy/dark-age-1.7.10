package keelfy.witcher.util;

import java.util.Timer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author keelfy
 */
public final class DAUtil {
	public static final String MODID = "witchercore";
	public static final String MODNAME = "Dark Age";
	public static final String MODVER = "v12.3";
	
	public static final String PROXY_SERVER = "keelfy.witcher.CommonProxy";
	public static final String PROXY_CLIENT = "keelfy.witcher.client.ClientProxy";
	
	public static final String NETWORK_CHANNEL = MODID + "_channel";
	
	public static final Timer timer = new Timer();
	public static final Logger logger = LogManager.getLogger(MODNAME);
	
	public static final boolean DEBUG_MODE = true;
	public static final boolean SERVER = false;
}
