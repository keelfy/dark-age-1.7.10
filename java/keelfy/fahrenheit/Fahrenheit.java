/*
 *  Copyright (c) 2016-2017, Rubedo
 */

package keelfy.fahrenheit;

import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import keelfy.fahrenheit.common.FCommon;
import keelfyutils.str.Brush;

/**
 * @author keelfy
 * @created 18 сент. 2017 г.
 */
// @Mod(modid = Fahrenheit.MOD_ID, name = Fahrenheit.MOD_NAME, version =
// Fahrenheit.MOD_VERSION, dependencies = KUtils.DEPENDENCY)
public class Fahrenheit {

	public static final String MOD_ID = "fahrenheit";
	public static final String MOD_NAME = "Fahrenheit";
	public static final String MOD_VERSION = "Build 1";
	public static final String MOD_CHANNEL = "fahrenheit_channel";
	public static final String MOD_PREFIX = Brush.DARK_AQUA + "[Fahrenheit] ";

	@Instance(Fahrenheit.MOD_ID)
	public static Fahrenheit instance;

	@SidedProxy(serverSide = "keelfy.fahrenheit.server.FServer", clientSide = "keelfy.fahrenheit.client.FClient")
	public static FCommon proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.preInit(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init(event);
	}

	@EventHandler
	public void serverStarting(FMLServerStartingEvent event) {
		proxy.serverStarting(event);
	}

	@EventHandler
	public void serverStarted(FMLServerStartedEvent event) {
		proxy.serverStarted(event);
	}

}
