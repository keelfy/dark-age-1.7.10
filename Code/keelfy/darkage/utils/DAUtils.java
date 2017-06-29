/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.utils;

/**
 * @author keelfy
 */
public enum DAUtils {
	Instance;
	
	public static final String MODID = "witchercore";
	public static final String MODNAME = "Dark Age";
	public static final String MODVER = "v12.8";
	
	public static final String PROXY_SERVER = "keelfy.darkage.CommonProxy";
	public static final String PROXY_CLIENT = "keelfy.darkage.client.ClientProxy";
	
	public static final String HOOK_CONTAINER = "keelfy.darkage.asm.DATransformer";
	
	public static final String NETWORK_CHANNEL = MODID + "_channel";
	
//	public static final boolean DEBUG_MODE = true;
//	public static final boolean SERVER = false;
	
	public static final boolean enableShaderEffects = true;
}
