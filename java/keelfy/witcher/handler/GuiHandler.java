package keelfy.witcher.handler;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import keelfy.witcher.DarkAge;
import keelfy.witcher.client.gui.GuiLootBag;
import keelfy.witcher.client.gui.GuiSelectClass;
import keelfy.witcher.client.gui.GuiSmartLibrary;
import keelfy.witcher.client.gui.GuiDAInventory;
import keelfy.witcher.client.gui.GuiWrittenBook;
import keelfy.witcher.entity.player.DAPlayer;
import keelfy.witcher.inventory.lootbag.ContainerLootBag;
import keelfy.witcher.inventory.player.ContainerDAInventory;
import keelfy.witcher.item.smartlib.DAWrittenBook;
import keelfy.witcher.util.DAUtil;
import keelfy.witcherBlocks.tileentity.TileEntityLootBag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * @author keelfy
 */
public class GuiHandler implements IGuiHandler {

	public GuiHandler() {
		NetworkRegistry.INSTANCE.registerGuiHandler(DarkAge.instance, this);
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			if (ID == GUI.INVENTORY.get()) {
				return new ContainerDAInventory(player, DAPlayer.get(player).inventory);
			} else if (ID == GUI.LOOTBAG.get()) {
				return new ContainerLootBag((TileEntityLootBag) world.getTileEntity(x, y, z));
			}
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			if (ID == GUI.SELECTCLASS.get()) {
				return new GuiSelectClass();
			} else if (ID == GUI.INVENTORY.get()) {
				return new GuiDAInventory(player, DAPlayer.get(player).inventory, DAPlayer.get(player).inventoryContainer, new ContainerDAInventory(player, DAPlayer.get(player).inventory));
			} else if (ID == GUI.WRITTENBOOK.get()) {
				if (player.getCurrentEquippedItem().getItem() instanceof DAWrittenBook)
					return new GuiWrittenBook(player, player.getCurrentEquippedItem());
			} else if (ID == GUI.SMARTLIBRARY.get()) {
				return new GuiSmartLibrary();
			} else if (ID == GUI.LOOTBAG.get()) {
				return new GuiLootBag(new ContainerLootBag((TileEntityLootBag) world.getTileEntity(x, y, z)));
			}
		}
		return null;
	}
	
	public static enum GUI {
		 SELECTCLASS(0),
		 INVENTORY(1),
		 WRITTENBOOK(2),
		 SMARTLIBRARY(3),
		 LOOTBAG(4),
		 CHEST(5);
		
		private int var1;
		private GUI(int par1) {
			var1 = par1;
		}
		
		public int get() {
			return var1;
		}
	}
}
