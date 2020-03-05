/*
 *  Copyright (c) 2016-2017, Rubedo
 */

package keelfy.fahrenheit.common.tileentities;

import java.util.List;

import keelfy.fahrenheit.common.player.managers.FPlayerManager;
import keelfyutils.KUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

/**
 * @author keelfy
 * @created 4 окт. 2017 г.
 */
public class TileEntityBigCampfire extends TileEntity {

	private double range;
	private float temperature;

	public TileEntityBigCampfire(double range, float temperature) {
		this.range = range;
		this.temperature = temperature / 60 / 20;
	}

	@Override
	public void updateEntity() {
		if (KUtils.PROTECT_SERVER) {
			super.updateEntity();

			AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(xCoord - range, yCoord - range, zCoord - range,
					xCoord + range, yCoord + range, zCoord + range);
			List<EntityPlayer> players = worldObj.getEntitiesWithinAABB(EntityPlayer.class, aabb);

			for (EntityPlayer player : players)
				FPlayerManager.getPlayer(player).temperature.change(temperature);
		}
	}

}