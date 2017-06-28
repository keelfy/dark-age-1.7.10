/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

/**
 * @author keelfy
 */
public class DATileEntity extends TileEntity {
	
	public int rotation;

    @Override
	public void readFromNBT(NBTTagCompound compound){
        super.readFromNBT(compound);
        rotation = compound.getInteger("rotation");
    }

    @Override
	public void writeToNBT(NBTTagCompound compound){
    	super.writeToNBT(compound);
    	compound.setInteger("rotation", rotation);
    }
	
    @Override
	public boolean canUpdate(){
        return false;
    }

    @Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt){
    	NBTTagCompound compound = pkt.func_148857_g();
    	readFromNBT(compound);
    }
    
    @Override
    public Packet getDescriptionPacket() {
    	NBTTagCompound compound = new NBTTagCompound();
    	writeToNBT(compound);
    	compound.removeTag("Items");
    	S35PacketUpdateTileEntity packet = new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 0, compound);
    	return packet;
    }
}

