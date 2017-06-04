package keelfy.darkage.entity.player.effect;

import keelfy.darkage.entity.player.DAPlayerUtil.Property;
import keelfy.darkage.util.DAUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

/**
 * @author keelfy
 */
public class EffectHeal extends PlayerEffect {
	
	public EffectHeal(EntityPlayer player) {
		super(player);
	}

	@Override
	public void perform(int dur, float eff) {
		super.perform(dur, eff);
		
		player.addPotionEffect(new PotionEffect(Potion.regeneration.id, dur));
	}
	
	@Override
	public void onUpdate() {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			if(isActive()) {
				if(wcp.get(Property.HEALTH) + 3 * getEfficiency() < wcp.getPlayerMaxHealth()) {
					wcp.changeHealth(3 * getEfficiency());
				} else {
					wcp.restoreHealth();
				}
			}
			
			super.onUpdate();
		}
	}
	
	@Override
	public void cure() {
		player.removePotionEffect(Potion.regeneration.id);
		super.cure();
	}
}
