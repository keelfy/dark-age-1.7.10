package keelfy.witcher.client;

import api.player.model.ModelPlayerAPI;
import api.player.render.RenderPlayerAPI;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import keelfy.witcher.CommonProxy;
import keelfy.witcher.client.render.RenderModelPlayer;
import keelfy.witcher.client.render.RenderSwordBehind;
import keelfy.witcher.client.render.RenderDAPlayer;
import keelfy.witcher.client.renderer.RendererRegister;
import keelfy.witcher.event.client.ClientEventListener;
import keelfy.witcher.event.client.ClientTickListener;
import keelfy.witcher.handler.client.KeyboardHandler;
import keelfy.witcher.handler.client.ModelHandler;
import keelfy.witcher.util.DAUtil;
import keelfy.witcher.util.font.FontManager;
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
	public EntityPlayer getEntityPlayer(MessageContext ctx) {
		return ctx.side.isClient() ? Minecraft.getMinecraft().thePlayer : super.getEntityPlayer(ctx);
	}
}
