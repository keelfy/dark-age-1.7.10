package keelfy.darkage.client;

import org.apache.commons.lang3.ArrayUtils;

import api.player.model.ModelPlayerAPI;
import api.player.render.RenderPlayerAPI;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import keelfy.darkage.CommonProxy;
import keelfy.darkage.client.render.RenderDAPlayer;
import keelfy.darkage.client.render.RenderModelPlayer;
import keelfy.darkage.client.render.RenderSwordBehind;
import keelfy.darkage.client.renderer.RendererRegister;
import keelfy.darkage.event.client.ClientEventListener;
import keelfy.darkage.event.client.ClientTickListener;
import keelfy.darkage.handler.client.KeyboardHandler;
import keelfy.darkage.handler.client.ModelHandler;
import keelfy.darkage.util.DAUtil;
import keelfy.darkage.util.font.FontManager;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

/**
 * @author keelfy
 */
public class ClientProxy extends CommonProxy {
	
	@Override
	public void preInit(FMLPreInitializationEvent event) {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			FontManager.getInstance().registerFonts();
			new ModelHandler();
		}
	}

	@Override
	public void init(FMLInitializationEvent event) {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			RenderPlayerAPI.register("WCRenderPlayer", RenderDAPlayer.class);
			RenderPlayerAPI.register("WCRenderSwordBehind", RenderSwordBehind.class);
			ModelPlayerAPI.register("WCModelPlayer", RenderModelPlayer.class);
			
			new ClientTickListener();
			new KeyboardHandler();
			new RendererRegister();
			new ClientEventListener();
		}
	}
	
	@Override
	public void postInit(FMLPostInitializationEvent event) {
		Minecraft mc = Minecraft.getMinecraft();
		
		mc.gameSettings.keyBindings = ArrayUtils.removeElement(mc.gameSettings.keyBindings, mc.gameSettings.keyBindDrop);
		
//		for (int i = 0; i < 6; i++) {
//            mc.gameSettings.keyBindsHotbar = ArrayUtils.remove(mc.gameSettings.keyBindsHotbar, 3 + i);
//        }
		
	}
	
	@Override
	public EntityPlayer getEntityPlayer(MessageContext ctx) {
		return ctx.side.isClient() ? Minecraft.getMinecraft().thePlayer : super.getEntityPlayer(ctx);
	}
}
