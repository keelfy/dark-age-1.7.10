/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkcore.common.player;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

/**
 * @author keelfy
 * @created 15 июл. 2017 г.
 */
public abstract class DAStatManager {

	protected DAPlayerData data;
	protected EntityPlayer player;

	public DAStatManager(final DAPlayerData data) {
		this.data = data;
		this.player = data.player;
	}

	public abstract void saveNBTData(NBTTagCompound compound);

	public abstract void loadNBTData(NBTTagCompound compound);
}
