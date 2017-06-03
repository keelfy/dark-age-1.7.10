package keelfy.witcher.entity.player.effect;

import keelfy.witcher.util.DAUtil;
import net.minecraft.entity.player.EntityPlayer;

/**
 * @author keelfy
 */
public class EffectDrunk extends PlayerEffect {

	public EffectDrunk(EntityPlayer player) {
		super(player);
	}

	@Override
	public void onUpdate() {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			super.onUpdate();
		}
	}
}
