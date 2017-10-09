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
public final class HealthManager extends DAStatManager {

	private float max;

	private static final int HEALTH = 13;
	private static final String NBTNAME = String.valueOf("PlayerHealth");
	private static final String NBTNAME_MAX = String.valueOf("PlayerHealthMax");

	public HealthManager(final DAPlayerData dap) {
		super(dap);

		this.max = 1200F;
		KDataWatcher.addf(player, HEALTH, max);
	}

	public final float get() {
		return KDataWatcher.getf(player, HEALTH);
	}

	public final void set(final float amount) {
		if (KUtils.PROTECT_SERVER) {
			if (amount > 0 && amount <= getMax()) {
				KDataWatcher.updatef(player, HEALTH, amount);
			} else if (amount <= 0) {
				KDataWatcher.updatef(player, HEALTH, 0.0F);
			} else if (amount > getMax()) {
				KDataWatcher.updatef(player, HEALTH, getMax());
			}
		}
	}

	public final void change(final float amount) {
		if (KUtils.PROTECT_SERVER) {
			if (!player.worldObj.isRemote) {
				set(get() + amount);
			}
		}
	}

	public final void restore() {
		if (KUtils.PROTECT_SERVER) {
			set(getMax());
		}
	}

	public final float getMax() {
		return max;
	}

	public final void setMax(final float amount) {
		if (KUtils.PROTECT_SERVER) {
			if (amount > 0.0F) {
				max = amount;
				data.sync();
			}
		}
	}

	public final void changeMax(final float amount) {
		if (KUtils.PROTECT_SERVER) {
			if (getMax() + amount > 0) {
				this.setMax(getMax() + amount);
			}
		}
	}

	@Override
	public void saveNBTData(final NBTTagCompound compound) {
		if (KUtils.PROTECT_SERVER) {
			compound.setFloat(NBTNAME, get());
			compound.setFloat(NBTNAME_MAX, getMax());
		}
	}

	@Override
	public void loadNBTData(final NBTTagCompound compound) {
		KDataWatcher.updatef(player, HEALTH, compound.getFloat(NBTNAME));
		max = compound.getFloat(NBTNAME_MAX);
	}

}
