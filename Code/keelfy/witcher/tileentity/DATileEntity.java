package keelfy.witcher.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

/**
 * @author keelfy
 */
public class DATileEntity extends TileEntity {
	
	public int rotation;

    public void readFromNBT(NBTTagCompound compound){
        super.readFromNBT(compound);
        rotation = compound.getInteger("rotation");
    }

    public void writeToNBT(NBTTagCompound compound){
    	super.writeToNBT(compound);
    	compound.setInteger("rotation", rotation);
    }
	
    public boolean canUpdate(){
        return false;
    }

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

