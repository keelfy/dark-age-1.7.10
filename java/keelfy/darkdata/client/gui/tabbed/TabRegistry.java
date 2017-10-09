package keelfy.darkdata.client.gui.tabbed;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfyutils.client.KGL;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.GuiScreenEvent;

@SideOnly(Side.CLIENT)
public final class TabRegistry {

	private static final ArrayList<AbstractTab> tabList = new ArrayList();

	public static final void registerTab(final AbstractTab tab) {
		tabList.add(tab);
	}

	public static final ArrayList<AbstractTab> getTabList() {
		return tabList;
	}

	@SubscribeEvent
	public final void guiPostInit(final GuiScreenEvent.InitGuiEvent.Post event) {
		// if ((event.gui instanceof GuiInventory)) {
		// final int xSize = 176;
		// final int ySize = 166;
		// int guiLeft = (event.gui.width - xSize) / 2;
		// final int guiTop = (event.gui.height - ySize) / 2;
		// guiLeft += getPotionOffset();
		// addTabsToList(NPCGuiHelper.getButtonList(event.gui));
		// }
	}

	private final static Minecraft MC = KGL.mc();

	public static final void updateTabValues(final int cornerX, final int cornerY, final Class<?> selectedButton) {
		int count = 2;
		for (int i = 0; i < tabList.size(); i++) {
			final AbstractTab t = tabList.get(i);
			if (t.shouldAddToList()) {
				t.id = count;
				t.xPosition = cornerX + (count - 2) * 28;
				t.yPosition = cornerY - 28;
				t.enabled = !t.getClass().equals(selectedButton);
				count++;
			}
		}
	}

	public static final void addTabsToList(final List buttonList) {
		for (final AbstractTab tab : tabList) {
			if (tab.shouldAddToList()) {
				buttonList.add(tab);
			}
		}
	}

	public static final int getPotionOffset() {
		// If at least one potion is active...
		if (!MC.thePlayer.getActivePotionEffects().isEmpty()) {
			if (Loader.isModLoaded("NotEnoughItems")) {
				try {
					// Check whether NEI is hidden and enabled
					final Class<?> c = Class.forName("codechicken.nei.NEIClientConfig");
					final Object hidden = c.getMethod("isHidden").invoke(null);
					final Object enabled = c.getMethod("isEnabled").invoke(null);
					if (hidden != null && hidden instanceof Boolean && enabled != null && enabled instanceof Boolean) {
						if ((Boolean) hidden || !((Boolean) enabled))
							// If NEI is disabled or hidden, offset the tabs by
							// 60
							return 60;
					}
				} catch (final Exception e) {}
			} else
				// If NEI is not installed, offset the tabs
				return 60;
		}
		// No potions, no offset needed
		return 0;
	}
}
