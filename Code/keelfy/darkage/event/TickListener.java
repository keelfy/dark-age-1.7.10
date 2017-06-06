package keelfy.darkage.event;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import keelfy.darkage.entity.player.DAPlayer;
import keelfy.darkage.entity.player.DAPlayerUtil.Property;
import keelfy.darkage.entity.player.effect.DAEffect;
import keelfy.darkage.util.DAUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;

/**
 * @author keelfy
 */
public class TickListener {
	
	public TickListener() {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			FMLCommonHandler.instance().bus().register(this);
		}
	}
	
	@SubscribeEvent
	public void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			if(event.player instanceof EntityPlayer) {
				if(!event.player.worldObj.isRemote) {
					DAPlayer dap = DAPlayer.get(event.player);
					DAEffect dae = DAEffect.get(event.player);
					
					if(dap.get(Property.HEALTH) <= 0) {
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
