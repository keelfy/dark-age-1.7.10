package keelfy.darkdata.client.gui.tabbed;

import cpw.mods.fml.common.registry.GameData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkdata.DarkData;
import keelfy.darkdata.constants.EnumGui;
import keelfy.darkdata.handlers.DAGuiHandler;
import keelfyutils.client.KGL;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

@SideOnly(Side.CLIENT)
public final class InventoryTabQuests extends AbstractTab {

	private final Minecraft mc = KGL.mc();

	public InventoryTabQuests() {
		super(0, 0, 0, new ItemStack(GameData.getItemRegistry().getObject(DarkData.MOD_ID + ":OREN")));
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
				DAGuiHandler.openGui(EnumGui.Quests);
			}
		};
		t.start();
	}

	@Override
	public boolean shouldAddToList() {
		return true;
	}
}
