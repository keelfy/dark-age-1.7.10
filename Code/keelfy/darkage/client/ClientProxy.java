/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.client;

import api.player.model.ModelPlayerAPI;
import api.player.render.RenderPlayerAPI;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import keelfy.darkage.CommonProxy;
import keelfy.darkage.client.render.RenderModelPlayer;
import keelfy.darkage.client.render.RenderPlayerArmor;
import keelfy.darkage.client.render.RenderSwordBehind;
import keelfy.darkage.client.renderer.RendererRegister;
import keelfy.darkage.events.client.ClientEventListener;
import keelfy.darkage.events.client.ClientTickListener;
import keelfy.darkage.handlers.client.FontHandler;
import keelfy.darkage.handlers.client.KeyboardHandler;
import keelfy.darkage.handlers.client.ModelHandler;
import keelfytools.KeelfyUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

/**
 * @author keelfy
 */
public class ClientProxy extends CommonProxy {
	
	@Override
	public void preInit(FMLPreInitializationEvent event) {
		if(KeelfyUtils.isClientSide()) {
			FontHandler.Instance.init();
			
			new ModelHandler();
		}
	}

	@Override
	public void init(FMLInitializationEvent event) {
		if(KeelfyUtils.isClientSide()) {
			RenderPlayerAPI.register("DARenderPlayer", RenderPlayerArmor.class);
			RenderPlayerAPI.register("DARenderSwordBehind", RenderSwordBehind.class);
			ModelPlayerAPI.register("DAModelPlayer", RenderModelPlayer.class);
			
			new ClientTickListener();
			new KeyboardHandler();
			new RendererRegister();
			new ClientEventListener();
		}
	}
	
	@Override
	public void postInit(FMLPostInitializationEvent event) {
		if(KeelfyUtils.isClientSide()) {
			
		}
	}
	
	@Override
	public EntityPlayer getEntityPlayer(MessageContext ctx) {
		return ctx.side.isClient() ? Minecraft.getMinecraft().thePlayer : super.getEntityPlayer(ctx);
	}
}
