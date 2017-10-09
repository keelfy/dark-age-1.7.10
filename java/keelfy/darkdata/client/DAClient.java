/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.client;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import keelfy.darkdata.DACommon;
import keelfy.darkdata.client.player.DAEntityPlayerSP;
import keelfy.darkdata.client.render.DARenderArmor;
import keelfy.darkdata.client.render.DARenderPlayer;
import keelfy.darkdata.client.render.DARenderSword;
import keelfy.darkdata.client.renderer.DARendererIrden;
import keelfy.darkdata.client.renderer.DARendererLootBag;
import keelfy.darkdata.handlers.DATabsHandler;
import keelfyutils.KUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

/**
 * @author keelfy
 */
public final class DAClient extends DACommon {

	@Override
	public final void preInit(final FMLPreInitializationEvent event) {
		if (KUtils.PROTECT_CLIENT) {
			super.preInit(event);

			DATabsHandler.init();

			DAEntityPlayerSP.register();
		}
	}

	@Override
	public final void init(final FMLInitializationEvent event) {
		if (KUtils.PROTECT_CLIENT) {
			super.init(event);

			DAClientEvents.Instance.register();
			DAKeyHandler.Instance.register();
			DAFontHandler.Instance.init();

			DARenderArmor.register();
			DARenderSword.register();
			DARenderPlayer.register();

			DARendererIrden.register();
			DARendererLootBag.register();
		}
	}

	@Override
	public final EntityPlayer getEntityPlayer(final MessageContext ctx) {
		return ctx.side.isClient() ? Minecraft.getMinecraft().thePlayer : super.getEntityPlayer(ctx);
	}
}
