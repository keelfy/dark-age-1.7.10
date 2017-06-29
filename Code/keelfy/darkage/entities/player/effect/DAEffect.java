/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.entities.player.effect;

import keelfy.darkage.utils.DAUtils;
import keelfytools.KeelfyUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

/**
 * @author keelfy
 * TODO: Создать эффект бухла и фисштеха
 */
public class DAEffect implements IExtendedEntityProperties {
	
	private EntityPlayer player;
	
	public static final String NAME = DAUtils.MODID + "_effects";
	private EffectHeal effectHeal;
	private EffectSaturation effectSat;
	private EffectDrunk effectDrunk;
	
	public DAEffect(EntityPlayer player) {
		this.player = player;
		
		this.effectHeal = new EffectHeal(player);
		this.effectSat = new EffectSaturation(player);
		this.effectDrunk = new EffectDrunk(player);
	}
	
	public static final void register(EntityPlayer player) {
		player.registerExtendedProperties(NAME, new DAEffect(player));
	}
	
	public static final DAEffect get(EntityPlayer player) {
		return (DAEffect) player.getExtendedProperties(NAME);
	}
	
	@Override
	public void saveNBTData(NBTTagCompound compound) {
		if(KeelfyUtils.isServerSide()) {
			NBTTagCompound properties = new NBTTagCompound();
			
			properties.setInteger(Effect.HEAL.getNBTDuration(), this.effectHeal.getDuration());
			properties.setInteger(Effect.SATURATION.getNBTDuration(), this.effectSat.getDuration());
			properties.setInteger(Effect.DRUNK.getNBTDuration(), this.effectDrunk.getDuration());
			
			properties.setFloat(Effect.HEAL.getNBTEfficiency(), this.effectHeal.getEfficiency());
			properties.setFloat(Effect.SATURATION.getNBTEfficiency(), this.effectSat.getEfficiency());
			properties.setFloat(Effect.DRUNK.getNBTEfficiency(), this.effectDrunk.getEfficiency());
			
			compound.setTag(NAME, properties);
		}
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
		NBTTagCompound properties = (NBTTagCompound) compound.getTag(NAME);
		
		this.effectHeal.setDuration(properties.getInteger(Effect.HEAL.getNBTDuration()));
		this.effectSat.setDuration(properties.getInteger(Effect.SATURATION.getNBTDuration()));
		this.effectDrunk.setDuration(properties.getInteger(Effect.DRUNK.getNBTDuration()));
		
		this.effectHeal.setEfficiency(properties.getFloat(Effect.HEAL.getNBTEfficiency()));
		this.effectSat.setEfficiency(properties.getFloat(Effect.SATURATION.getNBTEfficiency()));
		this.effectDrunk.setEfficiency(properties.getFloat(Effect.DRUNK.getNBTEfficiency()));
	}

	public final void onUpdate() {
		if(KeelfyUtils.isServerSide()) {
			this.effectHeal.onUpdate();
			this.effectDrunk.onUpdate();
			this.effectSat.onUpdate();
		}
	}
	
	public final void render() {
		if(KeelfyUtils.isClientSide()) {
			this.effectHeal.render();
			this.effectSat.render();
			this.effectDrunk.render();
		}
	}
	
	public EffectDrunk getDrunk() {
		return effectDrunk;
	}
	
	public EffectHeal getHeal() {
		return effectHeal;
	}
	
	public EffectSaturation getSaturation() {
		return effectSat;
	}
	
	public static enum Effect {
		HEAL,
		DRUNK,
		SATURATION;
		
		public String getNBTDuration() {
			return this.name() + "Dur";
		}
		
		public String getNBTEfficiency() {
			return this.name() + "Eff";
		}
	}

	@Override
	public void init(Entity entity, World world) {}
}
