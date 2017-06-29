/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.blocks.tileentity;

import keelfy.darkage.tileentity.DATileEntity;
import keelfytools.KeelfyUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

/**
 * @author keelfy
 */
public class TileEntityLootBag extends DATileEntity implements IInventory {
	
	public ItemStack[] inventoryStacks;
	private final int SIZE = 50; 
	private final String NAME = "Loot Bag";
	
	public TileEntityLootBag() {
		inventoryStacks = new ItemStack[SIZE];
	}
	
	public void clear() {
		if(KeelfyUtils.isServerSide()) {
			for (int i = 0; i < getSizeInventory(); i++) setInventorySlotContents(i, null);
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		if(KeelfyUtils.isServerSide()) {
			super.writeToNBT(nbt);
		    NBTTagList list = new NBTTagList();
		    for (int i = 0; i < this.getSizeInventory(); ++i) {
		        if (this.getStackInSlot(i) != null) {
		            NBTTagCompound stackTag = new NBTTagCompound();
		            stackTag.setByte("Slot", (byte) i);
		            this.getStackInSlot(i).writeToNBT(stackTag);
		            list.appendTag(stackTag);
		        }
		    }
		    nbt.setTag("Items", list);
	    }
	}


	@Override
	public void readFromNBT(NBTTagCompound nbt) {
	    super.readFromNBT(nbt);

	    NBTTagList list = nbt.getTagList("Items", 10);
	    for (int i = 0; i < list.tagCount(); ++i) {
	        NBTTagCompound stackTag = list.getCompoundTagAt(i);
	        int slot = stackTag.getByte("Slot") & 255;
	        this.setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(stackTag));
	    }
	}
	
	@Override
	public ItemStack getStackInSlot(int index) {
		if(KeelfyUtils.isServerSide()) {
		    if (index < 0 || index >= this.getSizeInventory())
		        return null;
		    return this.inventoryStacks[index];
		} else return null;
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		if(KeelfyUtils.isServerSide()) {
		    if (this.getStackInSlot(index) != null) {
		        ItemStack itemstack;
	
		        if (this.getStackInSlot(index).stackSize <= count) {
		            itemstack = this.getStackInSlot(index);
		            this.setInventorySlotContents(index, null);
		            this.markDirty();
		            return itemstack;
		        } else {
		            itemstack = this.getStackInSlot(index).splitStack(count);
	
		            if (this.getStackInSlot(index).stackSize <= 0) {
		                this.setInventorySlotContents(index, null);
		            } else {
		                this.setInventorySlotContents(index, this.getStackInSlot(index));
		            }
	
		            this.markDirty();
		            return itemstack;
		        }
		    } else {
		        return null;
		    }
		} else return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int index) {
		if(KeelfyUtils.isServerSide()) {
		    ItemStack stack = this.getStackInSlot(index);
		    this.setInventorySlotContents(index, null);
		    return stack;
		} else return null;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		if(KeelfyUtils.isServerSide()) {
		    if (index < 0 || index >= this.getSizeInventory())
		        return;
	
		    if (stack != null && stack.stackSize > this.getInventoryStackLimit())
		        stack.stackSize = this.getInventoryStackLimit();
		        
		    if (stack != null && stack.stackSize == 0)
		        stack = null;
	
		    this.inventoryStacks[index] = stack;
		    this.markDirty();
		} 
	}

	
	@Override
	public int getSizeInventory() { return SIZE; }
	@Override
	public String getInventoryName() { return NAME; }
	@Override
	public boolean hasCustomInventoryName() { return true; }
	@Override
	public int getInventoryStackLimit() { return 64; }
	@Override
	public boolean isUseableByPlayer(EntityPlayer player) { return true; }
	@Override
	public void openInventory() {}
	@Override
	public void closeInventory() {}
	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) { return true; }
}