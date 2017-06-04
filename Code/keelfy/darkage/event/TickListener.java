package keelfy.darkage.event;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import keelfy.darkage.entity.player.DAPlayer;
import keelfy.darkage.entity.player.effect.DAEffect;
import keelfy.darkage.util.DAUtil;
import net.minecraft.entity.player.EntityPlayer;

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
					DAPlayer wcp = DAPlayer.get(event.player);
					DAEffect wce = DAEffect.get(event.player);
					
					if(wcp != null) 
						wcp.onUpdate();
					
					if(wce != null)
						wce.onUpdate();
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onServerTick(TickEvent.ServerTickEvent event) {}
	 
	@SubscribeEvent
	public void onWorldTick(TickEvent.WorldTickEvent event) {}
}
