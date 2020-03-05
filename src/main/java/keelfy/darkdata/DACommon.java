/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import keelfy.darkdata.handlers.DACommonFiles;
import keelfy.darkdata.handlers.DAGuiHandler;
import keelfy.darkdata.handlers.registerers.BlockRegister;
import keelfy.darkdata.handlers.registerers.ItemRegister;
import keelfy.darkdata.server.DAServer;
import keelfy.darkdata.server.signs.DASignHandler;
import keelfyutils.KUtils;
import keelfyutils.registry.KBlockRegistry;
import keelfyutils.registry.KItemRegistry;
import net.minecraft.entity.player.EntityPlayer;

/**
 * @author keelfy
 */
public class DACommon {

	public void preInit(final FMLPreInitializationEvent event) {
		KItemRegistry.setResourceDomain(DarkData.MOD_ID);
		KBlockRegistry.setResourceDomain(DarkData.MOD_ID);

		DACommonFiles.Instance.init(event.getModConfigurationDirectory());

		ItemRegister.Instance.init();
		BlockRegister.Instance.init();

		DASignHandler.registerSigns();

		if (KUtils.SINGLEPLAYER) {
			DAServer.preInit();
		}
	}

	public void init(final FMLInitializationEvent event) {
		DAGuiHandler.Instance.register();

		if (KUtils.SINGLEPLAYER) {
			DAServer.init();
		}
	}

	public void serverStarting(final FMLServerStartingEvent event) {
		if (KUtils.SINGLEPLAYER) {
			DAServer.serverStarting();
		}
	}

	public EntityPlayer getEntityPlayer(final MessageContext ctx) {
		return ctx.getServerHandler().playerEntity;
	}
}
