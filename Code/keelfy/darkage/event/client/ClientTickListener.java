package keelfy.darkage.event.client;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkage.entity.player.effect.DAEffect;
import keelfy.darkage.item.Sword;
import keelfy.darkage.util.DAUtil;
import net.minecraft.client.Minecraft;

/**
 * @author keelfy
 */
@SideOnly(Side.CLIENT)
public class ClientTickListener {
	
	private final Minecraft mc = Minecraft.getMinecraft();
	
	public ClientTickListener() {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) 
			FMLCommonHandler.instance().bus().register(this);
	}

	@SubscribeEvent
	public void onClientTick(TickEvent.ClientTickEvent event) {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			if(mc.thePlayer != null && mc.thePlayer.getCurrentEquippedItem() != null && mc.thePlayer.getCurrentEquippedItem().getItem() instanceof Sword) {
				Sword sword = (Sword) mc.thePlayer.getCurrentEquippedItem().getItem();
				
				if(sword.renderer != null) {
					sword.renderer.moveObjects();
				}
			}
		}
	}

	@SubscribeEvent
	public void onRenderTick(TickEvent.RenderTickEvent event) {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			if(mc.inGameHasFocus && DAEffect.get(mc.thePlayer) != null)
				DAEffect.get(mc.thePlayer).render();
		}
	}
}
