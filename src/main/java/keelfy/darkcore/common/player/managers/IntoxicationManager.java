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
 * @created 14 июл. 2017 г.
 */
public final class IntoxicationManager extends DAStatManager {

	private float max;

	private final int INTOXICATION = 14;
	private static final String NBTNAME = String.valueOf("Intoxication");

	public IntoxicationManager(final DAPlayerData dap) {
		super(dap);

		this.max = 80.0F;
		KDataWatcher.addf(player, INTOXICATION, 0);
	}

	public final float getMax() {
		return max;
	}

	public final float get() {
		return KDataWatcher.getf(player, INTOXICATION);
	}

	public final void set(final float amount) {
		if (KUtils.PROTECT_SERVER) {
			if (!player.worldObj.isRemote) {
				if (amount >= 0F) {
					if (amount > getMax()) {
						KDataWatcher.updatef(player, INTOXICATION, getMax());
					} else {
						KDataWatcher.updatef(player, INTOXICATION, amount);
					}
				} else {
					KDataWatcher.updatef(player, INTOXICATION, 0.0F);
				}
			}
		}
	}

	public final void change(final float amount) {
		if (KUtils.PROTECT_SERVER) {
			if (!player.worldObj.isRemote) {
				final float curi = get();

				if (curi + amount > getMax()) {
					set(getMax());
				} else if (curi + amount > 0F) {
					set(curi + amount);
				} else if (curi + amount < 0F) {
					restore();
				}
			}
		}
	}

	public final void restore() {
		if (KUtils.PROTECT_SERVER) {
			if (!player.worldObj.isRemote) {
				KDataWatcher.updatef(player, INTOXICATION, 0);
			}
		}
	}

	@Override
	public final void saveNBTData(final NBTTagCompound compound) {
		if (KUtils.PROTECT_SERVER) {
			compound.setFloat(NBTNAME, KDataWatcher.getf(player, INTOXICATION));
		}
	}

	@Override
	public final void loadNBTData(final NBTTagCompound compound) {
		KDataWatcher.updatef(player, INTOXICATION, compound.getFloat(NBTNAME));
	}
}
