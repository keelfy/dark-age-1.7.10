/*
 *  Copyright (c) 2016-2017, Rubedo
 */

package keelfy.fahrenheit.server;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import keelfy.fahrenheit.common.player.managers.FPlayerManager;
import keelfyutils.KUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

/**
 * @author keelfy
 * @created 18 сент. 2017 г.
 */
public enum FServerEvents {
	INSTANCE;

	public void register() {
		if (KUtils.PROTECT_SERVER) {
			MinecraftForge.EVENT_BUS.register(this);
			FMLCommonHandler.instance().bus().register(this);
		}
	}

	@SubscribeEvent
	public final void onClone(final PlayerEvent.Clone event) {
		if (KUtils.PROTECT_SERVER) {
			FPlayerManager.clone(event.entityPlayer, event.original);
		}
	}

	@SubscribeEvent
	public final void onUpdate(final TickEvent.PlayerTickEvent event) {
		if (KUtils.PROTECT_SERVER) {
			if (event.phase != Phase.END) {
				return;
			}

			FPlayerManager.update(event.player);
		}
	}

	@SubscribeEvent
	public final void onJoinWorld(final EntityJoinWorldEvent event) {
		if (KUtils.PROTECT_SERVER) {
			if (!event.entity.worldObj.isRemote) {
				if (event.entity instanceof EntityPlayer) {
					final EntityPlayerMP player = (EntityPlayerMP) event.entity;
					FPlayerManager.getPlayer(player).sync();
				}
			}
		}
	}

}
