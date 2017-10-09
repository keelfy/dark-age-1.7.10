/*
 *  Copyright (c) 2016-2017, Rubedo
 */

package keelfy.fahrenheit.common.player.managers;

import keelfy.fahrenheit.common.player.FPlayerData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

/**
 * @author keelfy
 * @created 18 сент. 2017 г.
 */
public abstract class FDataManager {

	protected FPlayerData fpd;
	protected EntityPlayer player;

	public FDataManager(final FPlayerData fpd) {
		this.fpd = fpd;
		this.player = fpd.player;
	}

	public abstract void saveNBTData(NBTTagCompound compound);

	public abstract void loadNBTData(NBTTagCompound compound);

}
