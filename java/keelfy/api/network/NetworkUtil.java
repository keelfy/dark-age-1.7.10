package keelfy.api.network;

import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import keelfy.api.KUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

/**
 * @author keelfy
 * @created 24 мая 2017 г.
 */
public enum NetworkUtil {
	INSTANCE;
	
	public static final EntityPlayer getPlayerEntity(final MessageContext ctx) {
		if(KUtil.isSever())
			return ctx.getServerHandler().playerEntity;
		else return Minecraft.getMinecraft().thePlayer;
	}
}
