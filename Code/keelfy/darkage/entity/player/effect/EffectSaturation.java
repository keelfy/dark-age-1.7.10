package keelfy.darkage.entity.player.effect;

import keelfy.darkage.entity.player.DAPlayerUtil.Property;
import keelfy.darkage.util.DAUtil;
import net.minecraft.entity.player.EntityPlayer;

/**
 * @author keelfy
 */
public class EffectSaturation extends PlayerEffect {

	public EffectSaturation(EntityPlayer player) {
		super(player);
	}

	@Override
	public void onUpdate() {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			super.onUpdate();
			
			if(wcp.get(Property.HEALTH) < wcp.getPlayerMaxHealth() && wcp.get(Property.HEALTH) + 0.1F < wcp.getPlayerMaxHealth()) {
				
			}
		}
	}
}
