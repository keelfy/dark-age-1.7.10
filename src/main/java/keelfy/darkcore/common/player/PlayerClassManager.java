package keelfy.darkcore.common.player;

import keelfy.darkcore.network.DASNetwork;
import keelfy.darkdata.constants.EnumPlayerClass;
import keelfy.darkdata.constants.EnumSign;
import keelfy.darkdata.network.EnumCPackets;
import keelfyutils.KUtils;
import keelfyutils.network.KDataWatcher;
import keelfyutils.server.KServer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;

/**
 * @author keelfy
 * @created 25 июл. 2017 г.
 */
public final class PlayerClassManager extends DAStatManager {

	private static final int DWID = 21;
	private static final String NBT_Name = String.valueOf("PlayerClass");

	public PlayerClassManager(final DAPlayerData dap) {
		super(dap);

		KDataWatcher.addi(player, DWID, EnumPlayerClass.WITCHER.ordinal());
	}

	public final EnumPlayerClass get() {
		return EnumPlayerClass.values()[KDataWatcher.geti(player, DWID)];
	}

	public boolean isWitcher() {
		return get() == EnumPlayerClass.WITCHER;
	}

	public boolean isHuman() {
		return get() == EnumPlayerClass.HUMAN;
	}

	public final boolean equals(final EnumPlayerClass value) {
		return get() == value;
	}

	public final void set(final EnumPlayerClass value) {
		if (KUtils.PROTECT_SERVER) {
			KDataWatcher.updatei(player, DWID, value.ordinal());
		}
	}

	public final void change(final EnumPlayerClass newClass) {
		if (KUtils.PROTECT_SERVER) {
			if (get() != newClass) {
				set(newClass);

				if (newClass == EnumPlayerClass.WITCHER) {
					data.health.setMax(1200F);
					data.signs.setCurrent(0);
				}

				if (newClass == EnumPlayerClass.HUMAN) {
					data.health.setMax(1000F);
					data.intox.restore();
					data.signs.setCurrent(EnumSign.None);
				}
				KServer.sendFineMessage(player, String.valueOf("Ваш новый класс: " + newClass.getLocalizedName()));
			} else {
				KServer.sendErrMessage(player, String.valueOf("Вы не можете сменить класс!"));
			}
		}
	}

	@Override
	public void saveNBTData(final NBTTagCompound compound) {
		if (KUtils.PROTECT_SERVER) {
			compound.setInteger(NBT_Name, get().ordinal());
		}
	}

	@Override
	public void loadNBTData(final NBTTagCompound compound) {
		KDataWatcher.updatei(player, DWID, compound.getInteger(NBT_Name));

	}

	public final void sync() {
		if (KUtils.PROTECT_SERVER) {
			final NBTTagCompound data = new NBTTagCompound();
			this.saveNBTData(data);
			DASNetwork.sendTo((EntityPlayerMP) player, EnumCPackets.SyncPlayerClass, data);
		}
	}

}
