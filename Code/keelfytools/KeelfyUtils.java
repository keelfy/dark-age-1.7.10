/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfytools;

import cpw.mods.fml.common.FMLCommonHandler;

/**
 * @author keelfy
 * @created 21 июн. 2017 г.
 */
public enum KeelfyUtils {
	Instance;
	
	public static final boolean SERVER_SIDE = false;
	public static final boolean SIDED_RPOTECT = true;
	public static final boolean DEBUG_MODE = true;
	
	public static final boolean isServerSide() {
		return (SIDED_RPOTECT && SERVER_SIDE) || DEBUG_MODE;
	}
	
	public static final boolean isClientSide() {
		return (SIDED_RPOTECT && !SERVER_SIDE) || DEBUG_MODE;
	}
	
	public static boolean isClient() {
		return FMLCommonHandler.instance().getSide().isClient();
	}
	
	public static boolean isSever() {
		return FMLCommonHandler.instance().getSide().isServer();
	}
}
