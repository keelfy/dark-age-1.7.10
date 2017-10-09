/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkcore.common.player;

import keelfyutils.KUtils;
import keelfyutils.network.KDataWatcher;
import net.minecraft.nbt.NBTTagCompound;

/**
 * @author keelfy
 * @created 15 июл. 2017 г.
 */
public final class EnergyManager extends DAStatManager {

	private final float playerMaxEnergy;
	private final float playerMinEnergy;
	private static final int ENERGY = 24;
	private static final String NBTNAME = String.valueOf("PlayerEnergy");

	public EnergyManager(final DAPlayerData dap) {
		super(dap);

		this.playerMaxEnergy = 100.0F;
		this.playerMinEnergy = 7F;
		KDataWatcher.addf(player, ENERGY, playerMaxEnergy);
	}

	public float getMax() {
		return playerMaxEnergy;
	}

	public float getMin() {
		return playerMinEnergy;
	}

	public final float get() {
		return KDataWatcher.getf(player, ENERGY);
	}

	public final void set(final float value) {
		if (KUtils.PROTECT_SERVER) {
			if (value >= getMin() && value <= getMax()) {
				KDataWatcher.updatef(player, ENERGY, value);
			} else if (value < getMin()) {
				KDataWatcher.updatef(player, ENERGY, getMin());
			} else if (value > getMax()) {
				KDataWatcher.updatef(player, ENERGY, getMax());
			}
		}
	}

	public final void tire() {
		if (KUtils.PROTECT_SERVER) {
			this.set(getMin());
		}
	}

	public final void change(final float amount) {
		if (KUtils.PROTECT_SERVER) {
			if (!player.worldObj.isRemote) {
				this.set(get() + amount);
			}
		}
	}

	public final void restore() {
		if (KUtils.PROTECT_SERVER) {
			if (!player.worldObj.isRemote) {
				set(getMax());
			}
		}
	}

	@Override
	public final void saveNBTData(final NBTTagCompound compound) {
		if (KUtils.PROTECT_SERVER) {
			compound.setFloat(NBTNAME, get());
		}
	}

	@Override
	public final void loadNBTData(final NBTTagCompound compound) {
		KDataWatcher.updatef(player, ENERGY, compound.getFloat(NBTNAME));
	}

}
