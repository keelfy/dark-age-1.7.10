/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkcore.common.player.managers;

import keelfy.darkcore.common.player.DAPlayerData;
import keelfyutils.KUtils;
import keelfyutils.network.KDataWatcher;
import net.minecraft.nbt.NBTTagCompound;

/**
 * @author keelfy
 * @created 15 июл. 2017 г.
 */
public final class SaturationManager extends DAStatManager {

	private final float lose;
	private final float max;

	private static final int SATURATION = 23;
	private static final String NBTNAME = String.valueOf("PlayerSaturation");

	public SaturationManager(final DAPlayerData dap) {
		super(dap);

		this.max = 100.0F;
		this.lose = -0.00104166666F;
		KDataWatcher.addf(player, SATURATION, max);
	}

	public final float get() {
		return KDataWatcher.getf(player, SATURATION);
	}

	public final void set(final float value) {
		if (KUtils.PROTECT_SERVER) {
			if (value >= 0 && value <= getMax()) {
				KDataWatcher.updatef(player, SATURATION, value);
			} else if (value < 0) {
				KDataWatcher.updatef(player, SATURATION, 0);
			} else if (value > getMax()) {
				KDataWatcher.updatef(player, SATURATION, getMax());
			}
		}
	}

	public final void change(final float amount) {
		if (KUtils.PROTECT_SERVER) {
			set(get() + amount);
		}
	}

	public final float getLose() {
		return lose;
	}

	public final float getMax() {
		return max;
	}

	@Override
	public void saveNBTData(final NBTTagCompound compound) {
		if (KUtils.PROTECT_SERVER) {
			compound.setFloat(NBTNAME, get());
		}
	}

	@Override
	public void loadNBTData(final NBTTagCompound compound) {
		KDataWatcher.updatef(player, SATURATION, compound.getFloat(NBTNAME));
	}

}
