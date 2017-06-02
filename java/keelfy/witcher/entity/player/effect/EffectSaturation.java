package keelfy.witcher.entity.player.effect;

import keelfy.witcher.entity.player.DAPlayerUtil.Property;
import keelfy.witcher.util.DAUtil;
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
