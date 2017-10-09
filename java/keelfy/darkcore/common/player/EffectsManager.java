/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkcore.common.player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import keelfy.darkcore.network.DASNetwork;
import keelfy.darkdata.constants.EnumEffect;
import keelfy.darkdata.network.EnumCPackets;
import keelfyutils.KUtils;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;

/**
 * @author keelfy
 */
public final class EffectsManager extends DAStatManager {

	public final Map<EnumEffect, PlayerEffect> map = new HashMap();

	private static final String NBT_Name = String.valueOf("DAPlayerEffects");

	public EffectsManager(final DAPlayerData data) {
		super(data);

		map.clear();

		map.put(EnumEffect.Heal, new PlayerEffect(data, EnumEffect.Heal));
		map.put(EnumEffect.Resistance, new PlayerEffect(data, EnumEffect.Resistance));
		map.put(EnumEffect.Strength, new PlayerEffect(data, EnumEffect.Strength));
		map.put(EnumEffect.NightVision, new PlayerEffect(data, EnumEffect.NightVision));
		map.put(EnumEffect.WaterBreathing, new PlayerEffect(data, EnumEffect.WaterBreathing));
		map.put(EnumEffect.SecondBreath, new PlayerEffect(data, EnumEffect.SecondBreath));
		map.put(EnumEffect.MoveSpeed, new PlayerEffect(data, EnumEffect.MoveSpeed));
	}

	public final void onUpdate() {
		if (KUtils.PROTECT_SERVER) {
			for (Entry<EnumEffect, PlayerEffect> pair : map.entrySet()) {
				final EnumEffect name = pair.getKey();
				final PlayerEffect effect = pair.getValue();

				if (effect.isActive()) {
					switch (name) {
					case Heal:
						data.health.change(effect.getEfficiency());
						break;
					default:
						break;
					}
				}

				effect.updateDuration();
			}
		}
	}

	public final void restore() {
		if (KUtils.PROTECT_SERVER) {
			for (PlayerEffect effect : getActiveList()) {
				effect.cure();
			}

			data.player.clearActivePotions();
		}
	}

	public final void perform(final EnumEffect name, final int duration, final float efficiency, final boolean sum) {
		if (KUtils.PROTECT_SERVER) {
			map.get(name).perform(duration, efficiency, sum);
		}
	}

	public final List<PlayerEffect> getActiveList() {
		final List<PlayerEffect> activeList = new ArrayList();
		for (final Entry<EnumEffect, PlayerEffect> pair : map.entrySet()) {
			final PlayerEffect pe = pair.getValue();

			if (pe.getDuration() > 0) {
				activeList.add(pe);
			}
		}
		return activeList;
	}

	public final PlayerEffect get(final EnumEffect name) {
		for (final Entry<EnumEffect, PlayerEffect> pair : map.entrySet()) {
			if (name.equals(pair.getKey()))
				return pair.getValue();
		}
		return null;
	}

	@Override
	public final void saveNBTData(final NBTTagCompound compound) {
		if (KUtils.PROTECT_SERVER) {
			final NBTTagCompound properties = new NBTTagCompound();

			for (Entry<EnumEffect, PlayerEffect> pair : map.entrySet()) {
				final EnumEffect name = pair.getKey();
				final PlayerEffect effect = pair.getValue();

				properties.setBoolean(effect.getName().name() + "_isActive", effect.isActive());
				properties.setFloat(effect.getName().name() + "_efficiency", effect.getEfficiency());
				properties.setInteger(effect.getName().name() + "_duration", effect.getDuration());
			}

			compound.setTag(NBT_Name, properties);
		}
	}

	@Override
	public final void loadNBTData(final NBTTagCompound compound) {
		final NBTTagCompound properties = (NBTTagCompound) compound.getTag(NBT_Name);

		for (Entry<EnumEffect, PlayerEffect> pair : map.entrySet()) {
			final EnumEffect name = pair.getKey();
			final PlayerEffect effect = pair.getValue();

			effect.isActive = properties.getBoolean(effect.getName().name() + "_isActive");
			effect.efficiency = properties.getFloat(effect.getName().name() + "_efficiency");
			effect.duration = properties.getInteger(effect.getName().name() + "_duration");
		}
	}

	public final void sync() {
		if (KUtils.PROTECT_SERVER) {
			final NBTTagCompound data = new NBTTagCompound();
			this.saveNBTData(data);
			DASNetwork.sendTo((EntityPlayerMP) player, EnumCPackets.SyncEffects, data);
		}
	}

	public static class PlayerEffect {

		private int duration;
		private float efficiency;

		protected DAPlayerData data;

		private boolean isActive;

		private EnumEffect type;

		public PlayerEffect(final DAPlayerData data, final EnumEffect enu) {
			this.data = data;
			this.type = enu;
			this.isActive = false;
			this.efficiency = 0F;
			this.duration = 0;
		}

		public final EnumEffect getName() {
			return type;
		}

		public int getDuration() {
			return duration;
		}

		public void setDuration(final int i) {
			duration = i;

		}

		public void changeDuration(final int i) {
			duration += i;
		}

		public float getEfficiency() {
			return efficiency;
		}

		public void setEfficiency(final float f) {
			efficiency = f;
		}

		public void changeEfficiency(final float f) {
			efficiency += f;
		}

		public final void perform(final int dur, final float eff, final boolean sum) {
			if (KUtils.PROTECT_SERVER) {
				if (sum) {
					this.changeEfficiency(eff);
					this.changeDuration(dur);
				} else {
					this.setEfficiency(eff);
					if (this.getDuration() < dur) {
						this.setDuration(dur);
					}
				}
				if (!isActive()) {
					this.onStart();
					this.setIsActive(true);
				}
				data.effects.sync();
			}
		}

		public final void cure() {
			if (KUtils.PROTECT_SERVER) {
				this.onFinish();
				this.setEfficiency(0F);
				this.changeDuration(0);
				this.setIsActive(false);
				data.effects.sync();
			}
		}

		public final boolean isActive() {
			return isActive;
		}

		public final void setIsActive(final boolean value) {
			this.isActive = value;
		}

		public final void onFinish() {}

		public final void onStart() {}

		public final void updateDuration() {
			if (KUtils.PROTECT_SERVER) {
				if (getDuration() > 0) {
					this.changeDuration(-1);

					if (!isActive()) {
						this.setIsActive(true);
					}
				}

				if (getDuration() <= 0 && isActive()) {
					cure();
				}
			}
		}

		public final void updateDurationClient() {
			if (KUtils.PROTECT_CLIENT) {

				if (getDuration() > 0) {
					this.changeDuration(-1);

					if (!isActive()) {
						this.setIsActive(true);
					}
				}

				if (getDuration() <= 0 && isActive()) {
					this.setIsActive(false);
				}

			}
		}
	}
}
