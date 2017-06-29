/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.events.custom;

import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.entity.player.EntityPlayer;

/**
 * @author keelfy
 */
public class PlayerJumpEvent extends Event {
	
	public EntityPlayer player;
	public PlayerJumpEvent(EntityPlayer player) {
		this.player = player;
	}
}
