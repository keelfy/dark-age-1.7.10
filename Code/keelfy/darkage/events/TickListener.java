/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.events;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import keelfy.darkage.constants.EnumProperty;
import keelfy.darkage.entities.player.DAPlayer;
import keelfy.darkage.entities.player.effect.DAEffect;
import keelfytools.KeelfyUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;

/**
 * @author keelfy
 */
public enum TickListener {
	Instance;
	
	public final void register() {
		if(KeelfyUtils.isServerSide()) {
			FMLCommonHandler.instance().bus().register(this);
		}
	}
	
	@SubscribeEvent
	public void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if(KeelfyUtils.isServerSide()) {
			if(event.player instanceof EntityPlayer) {
				if(!event.player.worldObj.isRemote) {
					DAPlayer dap = DAPlayer.get(event.player);
					DAEffect dae = DAEffect.get(event.player);
					
					if(dap.get(EnumProperty.HEALTH) <= 0) {
						event.player.attackEntityFrom(DamageSource.outOfWorld, Float.MAX_VALUE);
					}
					
					if(dap != null) 
						dap.onUpdate();
					
					if(dae != null)
						dae.onUpdate();
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onServerTick(TickEvent.ServerTickEvent event) {}
	 
	@SubscribeEvent
	public void onWorldTick(TickEvent.WorldTickEvent event) {}
}
