package keelfy.darkage.event.custom;

import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;

/**
 * @author keelfy
 */
@Cancelable
public class NPCAttackEntityEvent extends Event {
	
	public Entity entity;
	public DamageSource damageSource;
	public float damage;
	public NPCAttackEntityEvent(Entity entity, DamageSource damageSource, float damage) {
		this.damage = damage;
		this.damageSource = damageSource;
		this.entity = entity;
	}
}
