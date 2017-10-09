package noppes.npcs.roles;

import java.util.logging.Level;
import java.util.logging.Logger;

import foxz.utils.Market;
import keelfy.darkdata.items.StoryBook;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.NoppesUtilPlayer;
import noppes.npcs.NoppesUtilServer;
import noppes.npcs.NpcMiscInventory;
import noppes.npcs.constants.EnumGuiType;
import noppes.npcs.entity.EntityNPCInterface;

public class RoleTrader extends RoleInterface {

	public String marketName = "";
	public NpcMiscInventory inventoryCurrency;
	public NpcMiscInventory inventorySold;
	public boolean ignoreDamage = false;
	public boolean ignoreNBT = false;

	public boolean toSave = false;

	public RoleTrader(final EntityNPCInterface npc) {
		super(npc);
		inventoryCurrency = new NpcMiscInventory(54);
		inventorySold = new NpcMiscInventory(18);
	}

	@Override
	public NBTTagCompound writeToNBT(final NBTTagCompound nbttagcompound) {
		nbttagcompound.setString("TraderMarket", marketName);
		writeNBT(nbttagcompound);
		if (toSave && !npc.isRemote()) {
			Market.save(this, marketName);
		}
		toSave = false;
		return nbttagcompound;
	}

	public NBTTagCompound writeNBT(final NBTTagCompound nbttagcompound) {
		nbttagcompound.setTag("TraderCurrency", inventoryCurrency.getToNBT());
		nbttagcompound.setTag("TraderSold", inventorySold.getToNBT());
		nbttagcompound.setBoolean("TraderIgnoreDamage", ignoreDamage);
		nbttagcompound.setBoolean("TraderIgnoreNBT", ignoreNBT);
		return nbttagcompound;
	}

	@Override
	public void readFromNBT(final NBTTagCompound nbttagcompound) {
		marketName = nbttagcompound.getString("TraderMarket");
		readNBT(nbttagcompound);

		try {
			Market.load(this, marketName);
		} catch (Exception ex) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);

		}
	}

	public void readNBT(final NBTTagCompound nbttagcompound) {
		inventoryCurrency.setFromNBT(nbttagcompound.getCompoundTag("TraderCurrency"));
		inventorySold.setFromNBT(nbttagcompound.getCompoundTag("TraderSold"));
		ignoreDamage = nbttagcompound.getBoolean("TraderIgnoreDamage");
		ignoreNBT = nbttagcompound.getBoolean("TraderIgnoreNBT");

	}

	@Override
	public void interact(final EntityPlayer player) {
		npc.say(player, npc.advanced.getInteractLine());
		try {
			Market.load(this, marketName);
		} catch (Exception ex) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
		}
		NoppesUtilServer.sendOpenGui(player, EnumGuiType.PlayerTrader, npc);
	}

	public boolean hasCurrency(final ItemStack itemstack) {
		if (itemstack == null)
			return false;
		for (ItemStack item : inventoryCurrency.items.values()) {
			if (item != null && NoppesUtilPlayer.compareItems(item, itemstack, ignoreDamage, ignoreNBT))
				return true;
		}
		return false;
	}

	public boolean hasStoryBook() {
		for (ItemStack item : inventorySold.items.values()) {
			if (item != null && item.getItem() instanceof StoryBook)
				return true;
		}

		for (ItemStack item : inventoryCurrency.items.values()) {
			if (item != null && item.getItem() instanceof StoryBook)
				return true;
		}
		return false;
	}
}
