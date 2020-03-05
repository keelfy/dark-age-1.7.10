/*
 *  Copyright (c) 2016-2017, Rubedo
 */

package keelfy.darkcore.common;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import keelfy.darkcore.common.player.DADataManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;

/**
 * @author keelfy
 * @created 3 окт. 2017 г.
 */
public enum DCCommonEvents {
	Instance;

	public final void register() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public final void onEntityConstructing(final EntityConstructing event) {
		if (event.entity instanceof EntityPlayer) {
			final EntityPlayer player = (EntityPlayer) event.entity;

			DADataManager.cunstruct(player);
		}
	}
}
