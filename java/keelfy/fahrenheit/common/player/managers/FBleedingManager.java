/*
 *  Copyright (c) 2016-2017, Rubedo
 */

package keelfy.fahrenheit.common.player.managers;

import keelfy.fahrenheit.common.player.FPlayerData;
import keelfy.fahrenheit.network.FEnumCPackets;
import keelfy.fahrenheit.network.FSNetwork;
import keelfyutils.KUtils;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;

/**
 * @author keelfy
 * @created 21 сент. 2017 г.
 */
public class FBleedingManager extends FDataManager {

	private static final String NBT_Name = "FBleeding";

	private float current;

	public static final float MAX = 100;
	public static final float MIN = 0;

	public FBleedingManager(FPlayerData fpd) {
		super(fpd);

		this.current = MIN;
	}

	public void set(float amount) {
		if (KUtils.PROTECT_SERVER) {
			if (get() + amount > MAX) {
				current = MAX;
			} else if (get() + amount < MIN) {
				current = MIN;
			} else {
				current = amount;
			}

			sync();
		}
	}

	public void change(float amount) {
		if (KUtils.PROTECT_SERVER) {
			this.set(get() + amount);
		}
	}

	public float get() {
		return current;
	}

	@Override
	public void saveNBTData(NBTTagCompound compound) {
		if (KUtils.PROTECT_SERVER) {
			compound.setFloat(NBT_Name, current);
		}
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
		this.current = compound.getFloat(NBT_Name);
	}

	public void sync() {
		if (KUtils.PROTECT_SERVER) {
			if (!player.worldObj.isRemote) {
				final EntityPlayerMP mp = (EntityPlayerMP) this.player;
				final NBTTagCompound data = new NBTTagCompound();
				this.saveNBTData(data);
				FSNetwork.sendTo(mp, FEnumCPackets.SyncBleeding, data);
			}
		}
	}
}
