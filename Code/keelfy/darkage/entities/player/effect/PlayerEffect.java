/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.entities.player.effect;

import keelfy.darkage.entities.player.DAPlayer;
import keelfy.darkage.network.ServerPacketHandler;
import keelfytools.KeelfyUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

/**
 * @author keelfy
 */
public class PlayerEffect {
	
	private int duration;
	private float efficiency;
	protected EntityPlayer player;
	protected DAPlayer wcp;
	
	public PlayerEffect(EntityPlayer player) {
		this.player = player;
		this.wcp = DAPlayer.get(player);
		
		duration = 0;
		efficiency = 1.0F;
	}
	
	public int getDuration() {
		return duration;
	}
	
	public void setDuration(int i) {
		if(KeelfyUtils.isServerSide()) {
			duration = i;
		}
	}
	
	public void addDuration(int i) {
		if(KeelfyUtils.isServerSide()) {
			duration += i;
		}
	}
	
	public float getEfficiency() {
		return efficiency;
	}
	
	public void setEfficiency(float f) {
		if(KeelfyUtils.isServerSide()) {
			efficiency = f;
		}
	}
	
	public void addEfficiency(float f) {
		if(KeelfyUtils.isServerSide()) {
			efficiency += f;
		}
	}
	
	public void perform(int dur, float eff) {
		addDuration(dur);
		addEfficiency(eff);
		sync();
	}
	
	public void cure() {
		setDuration(0);
		setEfficiency(1.0F);
		sync();
	}
	
	public boolean isActive() {
		return getDuration() > 0;
	}
	
	public void onUpdate() {
		if(KeelfyUtils.isServerSide()) {
			if(getDuration() > 0) this.addDuration(-1);
			if(getDuration() <= 0) this.cure();
		}
	}
	
	public void render() {
		
	}
	
	protected final void sync() {
		if(KeelfyUtils.isServerSide()) {
			if(!player.worldObj.isRemote) {
				ServerPacketHandler.syncEffects((EntityPlayerMP)player);
			}
		}
	}
}
