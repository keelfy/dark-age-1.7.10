/*
 *  Copyright (c) 2016-2017, Rubedo
 */

package keelfy.darkcore.server;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import keelfy.darkcore.common.player.DADataManager;
import keelfyutils.KUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

/**
 * @author keelfy
 * @created 3 окт. 2017 г.
 */
public enum DCServerEvents {
	Instance;

	public final void register() {
		if (KUtils.PROTECT_SERVER) {
			MinecraftForge.EVENT_BUS.register(this);
			FMLCommonHandler.instance().bus().register(this);
		}
	}

	@SubscribeEvent
	public final void onClonePlayer(final PlayerEvent.Clone event) {
		if (KUtils.PROTECT_SERVER) {
			DADataManager.clone(event.entityPlayer, event.original);
		}
	}

	@SubscribeEvent
	public final void onJoinWorld(final EntityJoinWorldEvent event) {
		if (KUtils.PROTECT_SERVER) {
			if (!event.entity.worldObj.isRemote) {
				if (event.entity instanceof EntityPlayer) {
					final EntityPlayerMP player = (EntityPlayerMP) event.entity;
					DADataManager.getPlayer(player).sync();
				}
			}
		}
	}

	@SubscribeEvent
	public final void onUpdate(final TickEvent.PlayerTickEvent event) {
		if (KUtils.PROTECT_SERVER) {
			if (!event.player.worldObj.isRemote) {
				if (event.phase != Phase.END) {
					return;
				}

				DADataManager.update(event.player);
			}

		}
	}

}
