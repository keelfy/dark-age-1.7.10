package noppes.npcs.scripted;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTBase.NBTPrimitive;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;

public class ScriptItemStack {
	protected ItemStack item;
	
	public ScriptItemStack(ItemStack item){
		this.item = item;
	}
	
	/**
	 * @return The minecraft name for this item
	 */
	public String getName(){
		return Item.itemRegistry.getNameForObject(item.getItem());
	}
	
	/**
	 * @return Returns the stacksize
	 */
	public int getStackSize(){
		return item.stackSize;
	}
	
	/**
	 * @return Return whether or not the item has a custom name
	 */
	public boolean hasCustomName(){
		return item.hasDisplayName();
	}
	
	/**
	 * @param name The custom name this item will get
	 */
	public void setCustomName(String name){
		item.setStackDisplayName(name);
	}
		
	/**
	 * @return Return the ingame displayed name. This is either the item name or the custom name if it has one.
	 */
	public String getDisplayName(){
		return item.getDisplayName();
	}
	
	/**
	 * @return Get the items ingame name. Use this incase the item ingame has custom name and you want the original name.
	 */
	public String getItemName(){
		return item.getItem().getItemStackDisplayName(item);
	}
	
	/**
	 * @param size The size of the itemstack. A number between 1 and 64
	 */
	public void setStackSize(int size){
		if(size < 0)
			size = 1;
		else if(size > 64)
			size = 64;
		item.stackSize = size;
	}
	
	/**
	 * @return Returns the item damage of this item. For tools this is the durability for other items the color and more.
	 */
	public int getItemDamage(){
		return item.getItemDamage();
	}
	
	/**
	 * @param value The value to be set as item damage. For tools this is the durability for other items the color and more.
	 */
	public void setItemDamage(int value){
		item.setItemDamage(value);
	}
	
	/**
	 * @param key The key of this NBTTag
	 * @param value The value to be stored. Can be a Number or String
	 */
	public void setTag(String key, Object value){
		if(value instanceof Number)
			getTag().setDouble(key, ((Number) value).doubleValue());
		else if(value instanceof String)
			getTag().setString(key, (String) value);
			
	}
	
	/**
	 * @param key The key of the NBTTag
	 * @return Returns whether or not the key exists
	 */
	public boolean hasTag(String key){
		return getTag().hasKey(key);
	}
	
	/**
	 * @param key The key of the NBTTag
	 * @return Returns the value associated with the key. Returns null of it doesnt exist
	 */
	public Object getTag(String key){
		NBTBase tag = getTag().getTag(key);
		if(tag == null)
			return null;
		if(tag instanceof NBTPrimitive)
			return ((NBTPrimitive)tag).func_150286_g();
		if(tag instanceof NBTTagString)
			return ((NBTTagString)tag).func_150285_a_();
		return tag;
	}
	
	public boolean isEnchanted(){
		return item.isItemEnchanted();
	}
	
	private NBTTagCompound getTag(){
		if(item.stackTagCompound == null)
			item.stackTagCompound = new NBTTagCompound();
		return item.stackTagCompound;
	}
	
	/**
	 * @return Returns whether or not this item is a block
	 */
	public boolean isBlock(){
		Block block = Block.getBlockFromItem(item.getItem());
		if(block == null || block == Blocks.air)
			return false;
		return true;
	}

	/**
	 * No support is given for this method. Dont use if you dont know what you are doing.
	 * @return Minecraft ItemStack
	 */
	public ItemStack getMCItemStack() {
		return item;
	}
}
