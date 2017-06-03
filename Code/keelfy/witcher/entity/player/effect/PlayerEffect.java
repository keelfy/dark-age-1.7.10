package keelfy.witcher.entity.player.effect;

import keelfy.api.network.PacketDispatcher;
import keelfy.witcher.entity.player.DAPlayer;
import keelfy.witcher.network.client.SyncEffectsMessage;
import keelfy.witcher.util.DAUtil;
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
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			duration = i;
		}
	}
	
	public void addDuration(int i) {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			duration += i;
		}
	}
	
	public float getEfficiency() {
		return efficiency;
	}
	
	public void setEfficiency(float f) {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			efficiency = f;
		}
	}
	
	public void addEfficiency(float f) {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
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
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			if(getDuration() > 0) this.addDuration(-1);
			if(getDuration() <= 0) this.cure();
		}
	}
	
	public void render() {
		
	}
	
	protected final void sync() {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			if(!player.worldObj.isRemote) {
				PacketDispatcher.getInstance().sendTo(new SyncEffectsMessage(player), (EntityPlayerMP)player);
			}
		}
	}
}
