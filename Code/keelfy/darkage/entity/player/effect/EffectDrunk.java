package keelfy.darkage.entity.player.effect;

import keelfy.darkage.util.DAUtil;
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
