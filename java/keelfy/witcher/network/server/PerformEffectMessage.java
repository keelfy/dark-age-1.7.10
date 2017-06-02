package keelfy.witcher.network.server;

import cpw.mods.fml.relauncher.Side;
import keelfy.api.network.AbstractMessage.AbstractServerMessage;
import keelfy.witcher.entity.player.effect.DAEffect;
import keelfy.witcher.entity.player.effect.DAEffect.Effect;
import keelfy.witcher.item.IFastUsable;
import keelfy.witcher.util.DAUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;

/**
 * @author keelfy
 */
public class PerformEffectMessage extends AbstractServerMessage<PerformEffectMessage> {
	private Effect effect;
	private int duration;
	private float efficiency;
	
	public PerformEffectMessage() {}

	public PerformEffectMessage(Effect eff, int duration, float par3) {
		this.effect = eff;
		this.duration = duration;
		this.efficiency = par3;
	}

	@Override
	protected void read(PacketBuffer buffer) {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			effect = Effect.values() [buffer.readInt()];
			duration = buffer.readInt();
			efficiency = buffer.readFloat();
		}
	}

	@Override
	protected void write(PacketBuffer buffer) {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			buffer.writeInt(effect.ordinal());
			buffer.writeInt(duration);
			buffer.writeFloat(efficiency);
		}
	}

	@Override
	public void process(EntityPlayer player, Side side) {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			DAEffect wce = DAEffect.get(player);
			
			switch(effect) {
			case DRUNK:	wce.getDrunk().perform(duration, efficiency);
				break;
			case HEAL: wce.getHeal().perform(duration, efficiency);
				break;
			case SATURATION: wce.getSaturation().perform(duration, efficiency);
				break;
			default:
				break;
			
			}
		}
	}
}