/*
 *  Copyright (c) 2016-2017, Rubedo
 */

package keelfy.fahrenheit.common;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import keelfy.fahrenheit.common.player.managers.FPlayerManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;

/**
 * @author keelfy
 * @created 18 сент. 2017 г.
 */
public enum FCommonEvents {
	INSTANCE;

	public void register() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void constructing(EntityConstructing event) {

		if (event.entity instanceof EntityPlayer) {
			FPlayerManager.cunstruct((EntityPlayer) event.entity);
		}

	}

}
