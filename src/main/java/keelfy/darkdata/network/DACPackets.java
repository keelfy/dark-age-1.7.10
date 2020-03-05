package keelfy.darkdata.network;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import keelfy.darkcore.common.player.DADataManager;
import keelfy.darkcore.common.player.DAPlayerData;
import keelfy.darkcore.common.player.managers.EffectsManager.PlayerEffect;
import keelfy.darkcore.network.DANetwork;
import keelfy.darkdata.client.gui.DAIngameGui;
import keelfy.darkdata.client.gui.DAInventoryGui;
import keelfy.darkdata.constants.EnumEffect;
import keelfyutils.client.KGL;
import keelfyutils.network.KNetwork;
import keelfyutils.str.KLog;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

/**
 * @author keelfy
 * @created 24 июл. 2017 г.
 */
@SideOnly(Side.CLIENT)
public final class DACPackets {

	public static void register() {
		DANetwork.channel.register(new DACPackets());
	}

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void onClientPacket(final FMLNetworkEvent.ClientCustomPacketEvent event) {
		final Minecraft mc = KGL.mc();
		final EntityPlayer player = mc.thePlayer;
		final ByteBuf buffer = event.packet.payload();

		if (player == null)
			return;

		final DAPlayerData data = DADataManager.getPlayer(player);

		final EnumCPackets packetType = EnumCPackets.values()[buffer.readInt()];

		switch (packetType) {
		case Loaded:
			if (mc.ingameGUI != null && mc.ingameGUI instanceof DAIngameGui) {
				((DAIngameGui) mc.ingameGUI).loaded = buffer.readBoolean();
			}
			break;
		case Repair:
			if (mc.currentScreen instanceof DAInventoryGui) {
				final DAInventoryGui gui = (DAInventoryGui) mc.currentScreen;
				gui.disableRepairing();
			}
			break;
		case SyncEffects:
			final NBTTagCompound tagEffects = KNetwork.readNBTFromBuffer(buffer);
			if (tagEffects == null) {
				KLog.err("Cannot synchronize player's effects. Player " + player.getCommandSenderName());
				return;
			}
			data.effects.loadNBTData(tagEffects);
			break;
		case SyncPlayerClass:
			final NBTTagCompound tagPlayerClass = KNetwork.readNBTFromBuffer(buffer);
			if (tagPlayerClass == null) {
				KLog.err("Cannot synchronize player's class. Player " + player.getCommandSenderName());
				return;
			}
			data.playerClass.loadNBTData(tagPlayerClass);
			break;
		case SyncData:
			final NBTTagCompound tagPlayer = KNetwork.readNBTFromBuffer(buffer);
			if (tagPlayer == null) {
				KLog.err("Cannot synchronize player's data. Player " + player.getCommandSenderName());
				return;
			}
			data.loadNBTData(tagPlayer);
			break;
		case SyncSkills:
			final NBTTagCompound tagPlayerSkills = KNetwork.readNBTFromBuffer(buffer);
			if (tagPlayerSkills == null) {
				KLog.err("Cannot synchronize player's skills. Player " + player.getCommandSenderName());
				return;
			}
			data.skills.loadNBTData(tagPlayerSkills);
			break;
		case EffectDuration:
			final PlayerEffect effect = data.effects.get(EnumEffect.values()[buffer.readInt()]);
			final int effectDur = buffer.readInt();

			if (effect == null)
				return;

			effect.changeDuration(effectDur);

			break;

		}
	}
}
