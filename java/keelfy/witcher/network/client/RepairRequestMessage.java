package keelfy.witcher.network.client;

import java.io.IOException;

import cpw.mods.fml.relauncher.Side;
import keelfy.api.network.AbstractMessage.AbstractClientMessage;
import keelfy.witcher.client.gui.GuiDAInventory;
import keelfy.witcher.entity.player.DAPlayer;
import keelfy.witcher.entity.player.effect.DAEffect;
import keelfy.witcher.util.DAUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;

/**
 * @author keelfy
 */
public class RepairRequestMessage extends AbstractClientMessage<RepairRequestMessage> {
	public RepairRequestMessage() {}

	@Override
	public void read(PacketBuffer buffer) {}

	@Override
	public void write(PacketBuffer buffer) {}

	@Override
	public void process(EntityPlayer player, Side side) {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			Minecraft mc = Minecraft.getMinecraft();
			if(mc.currentScreen instanceof GuiDAInventory) {
				GuiDAInventory gui = (GuiDAInventory)mc.currentScreen;
				gui.disableRepairing();
			}
		}
	}
}