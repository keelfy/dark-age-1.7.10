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
import net.minecraft.util.DamageSource;

/**
 * @author keelfy
 * @created 18 сент. 2017 г.
 */
public class FTemperatureManager extends FDataManager {

	private static final String NBT_Name = "FTemperature";

	public static final DamageSource hypothermia = (new DamageSource("hypothermia")).setDamageIsAbsolute()
			.setDamageBypassesArmor();

	private float normal;
	private float current;
	private float min;
	private float max;

	public FTemperatureManager(FPlayerData fpd) {
		super(fpd);

		this.normal = player.worldObj.rand.nextFloat() * (37F - 35.5F) + 35.5F;
		this.current = normal;
	}

	public void set(float amount) {

		if (KUtils.PROTECT_SERVER) {

			if (amount <= 0) {
				this.current = 0;
			} else {
				this.current = amount;
			}
			sync();
		}

	}

	public void change(float amount) {
		if (KUtils.PROTECT_SERVER) {
			if (amount == 0) {
				return;
			}

			this.set(get() + amount);
		}

	}

	public float getNormal() {
		return normal;
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
				FSNetwork.sendTo(mp, FEnumCPackets.SyncTemperature, data);
			}
		}
	}
}
