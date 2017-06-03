package keelfy.witcher.network.server;

import java.io.IOException;

import cpw.mods.fml.relauncher.Side;
import keelfy.api.network.AbstractMessage.AbstractServerMessage;
import keelfy.api.network.PacketDispatcher;
import keelfy.witcher.item.IRepairable;
import keelfy.witcher.item.DAItem;
import keelfy.witcher.item.RepairKit.RepairKitType;
import keelfy.witcher.network.client.RepairRequestMessage;
import keelfy.witcher.util.DAUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;

/**
 * @author keelfy
 */
public class RepairItemMessage extends AbstractServerMessage<RepairItemMessage> {

	public RepairItemMessage() {}
	
	private float repairingPercent;
	private RepairKitType repairingType;
	private int clickedNumber;
	private ItemStack rk;
	
	public RepairItemMessage(float repairingPercent, RepairKitType type, ItemStack rk, int clicked) {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			this.repairingPercent = repairingPercent;
			this.repairingType = type;
			this.clickedNumber = clicked + 1;
			this.rk = rk;
		}
	}	
	
	@Override
	protected void read(PacketBuffer buffer) throws IOException {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			this.repairingPercent = buffer.readFloat();
			this.repairingType = RepairKitType.values() [buffer.readInt()];
			this.clickedNumber = buffer.readInt();
			this.rk = buffer.readItemStackFromBuffer();
		}
	}

	@Override
	protected void write(PacketBuffer buffer) throws IOException {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			buffer.writeFloat(repairingPercent);
			buffer.writeInt(repairingType.ordinal());
			buffer.writeInt(clickedNumber);
			buffer.writeItemStackToBuffer(rk);
		}
	}

	@Override
	public void process(EntityPlayer player, Side side) {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			if(!player.worldObj.isRemote && player.inventory.getStackInSlot(clickedNumber) != null) {
				ItemStack clickedItemStack = player.inventory.getStackInSlot(clickedNumber);
				Item clickedItem1 = clickedItemStack.getItem();
					
				if(clickedItem1 instanceof IRepairable && ((IRepairable)clickedItem1).getRepairKitType() == repairingType) {
					NBTTagCompound tag = clickedItemStack.getTagCompound();
					DAItem clickedItem = (DAItem)clickedItem1;
					float maxDamage = clickedItem.getMaxDurability();
					float repairAmount = maxDamage / 100 * repairingPercent;
					float damage = clickedItem.getDurability(clickedItemStack);
					
					if(tag.hasKey(IRepairable.DURABILITY)) {
						tag.setFloat(IRepairable.DURABILITY, tag.getFloat(IRepairable.DURABILITY) + Math.round(repairAmount));
						
						player.inventory.getStackInSlot(clickedNumber).setTagCompound(tag);
						
						player.inventory.consumeInventoryItem(rk.getItem());
					}
					
					PacketDispatcher.getInstance().sendTo(new RepairRequestMessage(), (EntityPlayerMP)player);
				}
			}
		}
	}
}