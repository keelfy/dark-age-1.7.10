package keelfy.darkdata.client.player;

import api.player.client.ClientPlayerAPI;
import api.player.client.ClientPlayerBase;
import keelfy.darkdata.DarkData;
import keelfy.darkdata.client.gui.DAChestGui;
import net.minecraft.client.Minecraft;
import net.minecraft.inventory.IInventory;

/**
 * @author keelfy
 * @created 30 июл. 2017 г.
 */
public final class DAEntityPlayerSP extends ClientPlayerBase {

	public DAEntityPlayerSP(final ClientPlayerAPI playerAPI) {
		super(playerAPI);
	}

	public static final void register() {
		ClientPlayerAPI.register(DarkData.MOD_ID, DAEntityPlayerSP.class);
	}

	@Override
	public void displayGUIChest(final IInventory inv) {
		Minecraft.getMinecraft().displayGuiScreen(new DAChestGui(player.inventory, inv));
	}

}
