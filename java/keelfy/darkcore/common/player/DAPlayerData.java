/*
 *  Copyright (c) 2016-2017, Rubedo
 */

package keelfy.darkcore.common.player;

import keelfy.darkcore.network.DASNetwork;
import keelfy.darkdata.DarkData;
import keelfy.darkdata.inventory.player.DAPlayerInventory;
import keelfy.darkdata.network.EnumCPackets;
import keelfy.darkdata.server.player.DAPlayerDataHandler;
import keelfyutils.KUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

/**
 * @author keelfy
 * @created 3 окт. 2017 г.
 */
public final class DAPlayerData implements IExtendedEntityProperties {

	public static final String NBT_Name = DarkData.MOD_ID + "_player";

	public final EntityPlayer player;

	public SignsManager signs;
	public IntoxicationManager intox;
	public HealthManager health;
	public EnergyManager energy;
	public WeightManager weight;
	public SaturationManager saturation;
	public EffectsManager effects;
	public PlayerClassManager playerClass;
	public DAPlayerInventory inventory;

	public DAPlayerData(final EntityPlayer player) {
		this.player = player;

		this.inventory = new DAPlayerInventory(player);
		this.signs = new SignsManager(this);
		this.intox = new IntoxicationManager(this);
		this.health = new HealthManager(this);
		this.energy = new EnergyManager(this);
		this.weight = new WeightManager(this);
		this.saturation = new SaturationManager(this);
		this.playerClass = new PlayerClassManager(this);
		this.effects = new EffectsManager(this);
	}

	@Override
	public final void saveNBTData(final NBTTagCompound compound) {
		if (KUtils.PROTECT_SERVER) {
			final NBTTagCompound properties = new NBTTagCompound();

			inventory.writeToNBT(properties);
			intox.saveNBTData(properties);
			health.saveNBTData(properties);
			energy.saveNBTData(properties);
			weight.saveNBTData(properties);
			saturation.saveNBTData(properties);
			playerClass.saveNBTData(properties);
			effects.saveNBTData(properties);

			compound.setTag(NBT_Name, properties);
		}
	}

	@Override
	public void loadNBTData(final NBTTagCompound compound) {
		final NBTTagCompound properties = (NBTTagCompound) compound.getTag(NBT_Name);

		this.inventory.readFromNBT(properties);
		this.intox.loadNBTData(properties);
		this.health.loadNBTData(properties);
		this.energy.loadNBTData(properties);
		this.weight.loadNBTData(properties);
		this.saturation.loadNBTData(properties);
		this.playerClass.loadNBTData(properties);
		this.effects.loadNBTData(properties);
	}

	public final void copy(final EntityPlayer oldPlayer) {
		if (KUtils.PROTECT_SERVER) {
			DAPlayerDataHandler.Instance.copy(this, oldPlayer);
		}
	}

	public final void onUpdate() {
		if (KUtils.PROTECT_SERVER) {
			DAPlayerDataHandler.Instance.onUpdate(this);
		}
	}

	public final void sync() {
		if (KUtils.PROTECT_SERVER) {
			if (!player.worldObj.isRemote) {
				final EntityPlayerMP mp = (EntityPlayerMP) this.player;
				final NBTTagCompound data = new NBTTagCompound();
				this.saveNBTData(data);
				DASNetwork.sendTo(mp, EnumCPackets.SyncData, data);
			}
		}
	}

	@Override
	public void init(final Entity entity, final World world) {}
}
