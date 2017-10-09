/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.blocks.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

/**
 * @author keelfy
 */
public class DATileEntity extends TileEntity {

	public int rotation;
	public boolean rotate;

	public DATileEntity(final boolean needToRotate) {
		this.rotate = needToRotate;
	}

	@Override
	public void readFromNBT(final NBTTagCompound compound) {
		super.readFromNBT(compound);

		if (rotate) {
			rotation = compound.getInteger("rotation");
		}
	}

	@Override
	public void writeToNBT(final NBTTagCompound compound) {
		super.writeToNBT(compound);

		if (rotate) {
			compound.setInteger("rotation", rotation);
		}
	}

	protected final void setTileEntity(final TileEntity te) {
		super.getWorldObj().setTileEntity(xCoord, yCoord, zCoord, te);
	}

	protected final void setBlockToAir() {
		super.getWorldObj().setBlockToAir(xCoord, yCoord, zCoord);
	}

	@Override
	public void onDataPacket(final NetworkManager net, final S35PacketUpdateTileEntity pkt) {
		final NBTTagCompound compound = pkt.func_148857_g();
		readFromNBT(compound);
	}
}
