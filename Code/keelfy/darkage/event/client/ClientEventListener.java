package keelfy.darkage.event.client;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkage.client.gui.GuiLootBag;
import keelfy.darkage.client.gui.GuiHud;
import keelfy.darkage.entity.player.DAPlayer;
import keelfy.darkage.handler.GuiHandler.GUI;
import keelfy.darkage.network.client.ClientPacketHandler;
import keelfy.darkage.network.server.CustomServerMessage.PacketForServer;
import keelfy.darkage.util.DAUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.common.MinecraftForge;

/**
 * @author keelfy
 */
@SideOnly(Side.CLIENT)
public class ClientEventListener {

	private final Minecraft mc = Minecraft.getMinecraft();
	private DAPlayer wcp;
	
	private GuiHud guiHud;
	
	public ClientEventListener() {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			MinecraftForge.EVENT_BUS.register(this);
			guiHud = new GuiHud();
			mc.gameSettings.heldItemTooltips = false;
		}
	}
	
	@SubscribeEvent
	public void onMouseEvent(MouseEvent event) {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			if(event.dwheel != 0 && !mc.thePlayer.capabilities.isCreativeMode) {
				ClientPacketHandler.sendToServer(PacketForServer.CHANGESIGN);
				event.setCanceled(true);
			}
		}
	}
	
	@SubscribeEvent
	public void onBlockHighlight(DrawBlockHighlightEvent event) {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			if(!mc.thePlayer.capabilities.isCreativeMode) {
				event.setCanceled(true);
			}
		}
	}
	
	@SubscribeEvent
	public void openGui(GuiOpenEvent event) {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			if(event.gui instanceof GuiInventory && !mc.thePlayer.capabilities.isCreativeMode) {
				event.setCanceled(true);
				ClientPacketHandler.openGUI(GUI.INVENTORY);
			}
//			else if(event.gui instanceof GuiChest && !mc.thePlayer.capabilities.isCreativeMode) {
//				event.setCanceled(true);
//				ClientPacketHandler.openGUI(GUI.CHEST);
//			}
		}
	}
	
	@SubscribeEvent
	public void overlay(RenderGameOverlayEvent event) {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			if(!mc.thePlayer.capabilities.isCreativeMode && event.type == ElementType.HOTBAR) 
				event.setCanceled(true);
			
			if(event.type == ElementType.EXPERIENCE
					|| event.type == ElementType.HEALTH 
					|| event.type == ElementType.FOOD 
					|| event.type == ElementType.HEALTHMOUNT
					|| event.type == ElementType.ARMOR 
					|| event.type == ElementType.BOSSHEALTH
					)
				event.setCanceled(true);
			
			if((mc.currentScreen != null && mc.currentScreen instanceof GuiLootBag) || !mc.gameSettings.showDebugInfo)
				guiHud.renderGameOverlay();
		}
	}
}
