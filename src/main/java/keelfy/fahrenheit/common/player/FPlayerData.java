/*
 *  Copyright (c) 2016-2017, Rubedo
 */

package keelfy.fahrenheit.common.player;

import keelfy.fahrenheit.common.player.managers.FBleedingManager;
import keelfy.fahrenheit.common.player.managers.FTemperatureManager;
import keelfy.fahrenheit.common.player.managers.FThristManager;
import keelfy.fahrenheit.network.FEnumCPackets;
import keelfy.fahrenheit.network.FSNetwork;
import keelfyutils.KUtils;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.IExtendedEntityProperties;

/**
 * @author keelfy
 * @created 18 сент. 2017 г.
 */
public class FPlayerData implements IExtendedEntityProperties {

	public static final String NBT_Name = "FPlayerData";

	public final EntityPlayer player;

	public final FTemperatureManager temperature;
	public final FThristManager thrist;
	public final FBleedingManager bleeding;

	private static final float warming = 0.000208F;
	private static final float snowModifier = -0.000415F;
	private static final float snowfallModifier = -0.000625F;
	private static final float snowMofifierNight = -0.00208F;
	private static final float snowfallModifierNight = -0.0025F;

	public FPlayerData(EntityPlayer player) {
		this.player = player;

		this.temperature = new FTemperatureManager(this);
		this.thrist = new FThristManager(this);
		this.bleeding = new FBleedingManager(this);
	}

	public final void copy(EntityPlayer oldPlayer) {}

	int timerTempDamage = 0;

	public final void onUpdate() {
		if (KUtils.PROTECT_SERVER) {

			this.temperature.change(checkTemperature());

			if (this.temperature.get() <= 32F) {

				if (this.temperature.get() <= 30.5F) {

					if (this.temperature.get() <= 29.5F) {
						this.player.attackEntityFrom(FTemperatureManager.hypothermia, this.player.getMaxHealth());
					}

					if (timerTempDamage == 0) {
						this.player.attackEntityFrom(FTemperatureManager.hypothermia, 1);
						timerTempDamage = 100;
					}

					player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 5, 1));

				}

				if (player.getFoodStats().getFoodLevel() > 6)
					player.getFoodStats().setFoodLevel(6);

				player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 5, 0));

			}

			updateTimers();
		}
	}

	private final void updateTimers() {
		if (KUtils.PROTECT_SERVER) {
			if (timerTempDamage > 0) {
				timerTempDamage--;
			}
		}
	}

	private final float checkTemperature() {
		float result = 0;
		if (KUtils.PROTECT_SERVER) {

			Block walkOn = player.worldObj.getBlock((int) (player.posX), (int) (player.posY - 1), (int) (player.posZ));
			BiomeGenBase biome = player.worldObj.getBiomeGenForCoords((int) player.posX, (int) player.posZ);

			if (walkOn == Blocks.snow_layer || walkOn == Blocks.snow || walkOn == Blocks.ice) {
				result = snowModifier;
			}

			if (player.worldObj.isRaining() && biome.getEnableSnow()) {
				if (result > 0) {
					result = snowfallModifier;
				} else {
					result = snowModifier;
				}
			}

			if (result == 0 && this.temperature.get() < this.temperature.getNormal()) {
				result = warming;
			}
		}
		return result;
	}

	@Override
	public void saveNBTData(NBTTagCompound compound) {
		if (KUtils.PROTECT_SERVER) {
			NBTTagCompound properties = new NBTTagCompound();

			this.temperature.saveNBTData(properties);
			this.thrist.saveNBTData(properties);
			this.bleeding.saveNBTData(properties);

			compound.setTag(NBT_Name, properties);
		}
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
		NBTTagCompound properties = compound.getCompoundTag(NBT_Name);

		this.temperature.loadNBTData(properties);
		this.thrist.loadNBTData(properties);
		this.bleeding.loadNBTData(properties);

	}

	public void sync() {
		if (KUtils.PROTECT_SERVER) {
			if (!player.worldObj.isRemote) {
				final EntityPlayerMP mp = (EntityPlayerMP) this.player;
				final NBTTagCompound data = new NBTTagCompound();
				this.saveNBTData(data);
				FSNetwork.sendTo(mp, FEnumCPackets.SyncData, data);
			}
		}
	}

	@Override
	public void init(Entity entity, World world) {}

}
