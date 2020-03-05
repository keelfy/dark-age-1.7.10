package keelfy.darkdata.client.gui.tabbed;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkdata.constants.EnumGui;
import keelfy.darkdata.handlers.DAGuiHandler;
import keelfyutils.client.KGL;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import noppes.npcs.CustomItems;

@SideOnly(Side.CLIENT)
public final class InventoryTabFactions extends AbstractTab {

	private final Minecraft MC = KGL.mc();

	public InventoryTabFactions() {
		super(0, 0, 0, new ItemStack(CustomItems.wallBanner, 1, 1));
	}

	@Override
	public final void onTabClicked() {
		final Thread t = new Thread() {
			@Override
			public void run() {
				try {
					Thread.sleep(100);
				} catch (final InterruptedException e) {
					e.printStackTrace();
				}
				DAGuiHandler.openGui(EnumGui.Factions);
			}
		};
		t.start();
	}

	@Override
	public boolean shouldAddToList() {
		return true;
	}
}
