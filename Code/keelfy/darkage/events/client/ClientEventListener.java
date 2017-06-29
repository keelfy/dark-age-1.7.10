/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.events.client;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkage.client.gui.GuiHud;
import keelfy.darkage.client.gui.GuiLootBag;
import keelfy.darkage.constants.EnumGui;
import keelfy.darkage.constants.EnumServerPacket;
import keelfy.darkage.entities.player.DAPlayer;
import keelfy.darkage.network.ClientPacketHandler;
import keelfytools.KeelfyUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.client.event.EntityViewRenderEvent.RenderFogEvent;
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
		if (KeelfyUtils.isClientSide()) {
			MinecraftForge.EVENT_BUS.register(this);
			guiHud = new GuiHud();
			mc.gameSettings.heldItemTooltips = false;
		}
	}

	@SubscribeEvent
	public void renderFog(RenderFogEvent event) {
//		GL11.glFog(GL11.GL_FOG_COLOR, createBuffer(0, 20, 0, 1));
//		GL11.glFogf(GL11.GL_FOG_START, 0);
//		GL11.glFogf(GL11.GL_FOG_END, 10f);
	}
	
	private FloatBuffer createBuffer(float par1, float par2, float par3, float par4) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(16);
		buffer.put(par1).put(par2).put(par3).put(par4);
		buffer.flip();
		return buffer;
	}
	
	@SubscribeEvent
	public void onMouseEvent(MouseEvent event) {
		if (KeelfyUtils.isClientSide()) {
			if (event.dwheel != 0 && !mc.thePlayer.capabilities.isCreativeMode) {
				ClientPacketHandler.sendToServer(EnumServerPacket.CHANGESIGN);
				event.setCanceled(true);
			}
		}
	}

	@SubscribeEvent
	public void onBlockHighlight(DrawBlockHighlightEvent event) {
		if (KeelfyUtils.isClientSide()) {
			if (!mc.thePlayer.capabilities.isCreativeMode) {
				event.setCanceled(true);
			}
		}
	}

	@SubscribeEvent
	public void openGui(GuiOpenEvent event) {
		if (KeelfyUtils.isClientSide()) {
			if (event.gui instanceof GuiInventory && !mc.thePlayer.capabilities.isCreativeMode) {
				event.setCanceled(true);
				ClientPacketHandler.openGUI(EnumGui.INVENTORY);
			}
			// else if(event.gui instanceof GuiChest &&
			// !mc.thePlayer.capabilities.isCreativeMode) {
			// event.setCanceled(true);
			// ClientPacketHandler.openGUI(GUI.CHEST);
			// }
		}
	}

	@SubscribeEvent
	public void overlay(RenderGameOverlayEvent event) {
		if (KeelfyUtils.isClientSide()) {
			if (!mc.thePlayer.capabilities.isCreativeMode && event.type == ElementType.HOTBAR)
				event.setCanceled(true);

			if (event.type == ElementType.EXPERIENCE || event.type == ElementType.HEALTH
					|| event.type == ElementType.FOOD || event.type == ElementType.HEALTHMOUNT
					|| event.type == ElementType.ARMOR || event.type == ElementType.BOSSHEALTH)
				event.setCanceled(true);

			if ((mc.currentScreen != null && mc.currentScreen instanceof GuiLootBag) || !mc.gameSettings.showDebugInfo)
				guiHud.renderGameOverlay();
		}
	}
}
