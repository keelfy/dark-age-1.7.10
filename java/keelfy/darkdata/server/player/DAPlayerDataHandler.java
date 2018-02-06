/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.server.player;

import java.util.Map.Entry;

import keelfy.darkcore.common.player.DADataManager;
import keelfy.darkcore.common.player.DAPlayerData;
import keelfy.darkcore.common.player.managers.EffectsManager.PlayerEffect;
import keelfy.darkdata.constants.EnumArmorClass;
import keelfy.darkdata.constants.EnumEffect;
import keelfy.darkdata.constants.EnumPlayerClass;
import keelfy.darkdata.items.Armor;
import keelfy.darkdata.server.DAServerConfig;
import keelfy.darkdata.server.DAServerEvents;
import keelfyutils.KUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import noppes.npcs.controllers.PlayerDataController;
import noppes.npcs.controllers.PlayerFactionData;

/**
 * @author keelfy
 */
public enum DAPlayerDataHandler {
	Instance;

	public final void copy(DAPlayerData data, final EntityPlayer oldPlayer) {
		if (KUtils.PROTECT_SERVER) {
			final DAPlayerData oldData = DADataManager.getPlayer(oldPlayer);

			data.playerClass.set(oldData.playerClass.get());
			data.player.inventory.copyInventory(oldPlayer.inventory);
			data.health.restore();
			data.intox.restore();
			data.energy.restore();
			data.effects.restore();
		}
	}

	public int sprint = 0, factionTimer = 0, afterDamage = 0, moneyTimer = 0, repairTimer = 0;
	public DamageSource lastSource;

	public final void onUpdate(DAPlayerData data) {
		if (KUtils.PROTECT_SERVER) {
			if (data.player.worldObj.isRemote)
				return;

			if (DAServerEvents.Instance.timer > 0) {
				DAServerEvents.Instance.timer--;
			}

			if (data.health.get() <= 0.0F) {
				final String s = "game.player.die";
				final float soundPitch = (data.player.worldObj.rand.nextFloat() - data.player.worldObj.rand.nextFloat()) * 0.2F + 1.0F;
				data.player.playSound(s, 1.0F, soundPitch);

				data.player.setHealth(0);

				lastSource = null;
			}

			if (factionTimer == 0) {
				final PlayerFactionData factionData = PlayerDataController.instance.getPlayerData(data.player).factionData;
				final int carrerasId = DAServerConfig.Instance.carrerasFactionId;

				for (Entry<Integer, Integer> pair : factionData.factionData.entrySet()) {
					final int key = pair.getKey();
					final int value = pair.getValue();

					if (key == carrerasId && value != -1 && value < 10010) {
						factionData.increasePoints(key, 1);
					}
				}

				PlayerDataController.instance.getPlayerData(data.player).saveNBTData(null);
				factionTimer = 1200;
			}

			data.effects.onUpdate();

			if (!data.player.capabilities.isCreativeMode) {

				data.saturation.set(checkSaturation(data));

				final float newWeight = data.weight.inventory();
				if (data.weight.get() != newWeight) {
					data.weight.set(newWeight);
				}

				if (afterDamage == 0) {
					data.health.change(checkHeal(data));
				}

				float currentIntox = data.intox.get();
				if (data.playerClass.get() == EnumPlayerClass.WITCHER) {
					data.intox.change(-0.005F);
				} else if (currentIntox != 0) {
					data.intox.set(0);
				}

				if (currentIntox > 50) {
					data.health.change(-((float) Math.pow(Math.E, (currentIntox - 50) / 31)) / 3);
				}

				if (data.player.isBurning()) {
					playerBurning(data);
				}

				if (data.player.isSprinting()) {
					playerSprinting(data, checkEnergyLose(data));
				} else if (sprint == 0) {
					PlayerEffect secondBreath = data.effects.get(EnumEffect.SecondBreath);
					float energyRegen = 0.2F;

					data.energy.change(secondBreath.isActive() ? energyRegen * secondBreath.getEfficiency() : energyRegen);
				}
			}

			updateTimers(data);
		}
	}

	private final void updateTimers(DAPlayerData data) {
		if (KUtils.PROTECT_SERVER) {
			if (!data.player.worldObj.isRemote) {
				if (sprint > 0) {
					sprint--;
				}

				if (afterDamage > 0) {
					afterDamage--;
				}

				if (factionTimer <= 1200 && factionTimer > 0) {
					factionTimer--;
				}

				if (moneyTimer > 0) {
					moneyTimer--;
				}

				if (repairTimer > 0) {
					repairTimer--;
				}
			}
		}
	}

	private void playerBurning(DAPlayerData data) {
		if (KUtils.PROTECT_SERVER) {
			if (!data.player.worldObj.isRemote) {
				data.health.change(-2F);
				afterDamage = 100;
			}
		}
	}

	private final void playerSprinting(DAPlayerData data, final float playerEnergyLose) {
		if (KUtils.PROTECT_SERVER) {
			if (!data.player.worldObj.isRemote) {
				final float cure = data.energy.get();
				if (data.weight.isOverweight() || cure <= data.energy.getMin()) {
					data.player.setSprinting(false);
				}

				data.energy.change(playerEnergyLose);
				setEnergyTimer(data.player, 100);
			}
		}
	}

	public void setRepairTimer(EntityPlayer player, int repairTimer) {
		if (KUtils.PROTECT_SERVER) {
			if (!player.worldObj.isRemote) {
				this.repairTimer = repairTimer;
			}
		}
	}

	public void setMoneyTimer(EntityPlayer player, int moneyTimer) {
		if (KUtils.PROTECT_SERVER) {
			if (!player.worldObj.isRemote) {
				this.moneyTimer = moneyTimer;
			}
		}
	}

	public final void setWitcherHealingTimer(EntityPlayer player, final int t) {
		if (KUtils.PROTECT_SERVER) {
			if (!player.worldObj.isRemote) {
				this.afterDamage = t;
			}
		}
	}

	public final void setEnergyTimer(EntityPlayer player, final int t) {
		if (KUtils.PROTECT_SERVER) {
			if (!player.worldObj.isRemote) {
				this.sprint = t;
			}
		}
	}

	private final float checkSaturation(DAPlayerData data) {
		if (KUtils.PROTECT_SERVER) {
			if (!data.player.worldObj.isRemote) {
				final float sat = data.saturation.get();

				if (data.player.getFoodStats().getFoodLevel() != 20) {
					data.player.getFoodStats().addStats(20, 1);
				}

				if (sat < 0)
					return 0;
				else if (sat > 100)
					return 100;

				if (sat == 0) {
					data.health.change(-0.03F);
				}

				return sat + data.saturation.getLose();
			}
		}
		return 0;
	}

	private final float checkHeal(DAPlayerData data) {
		if (KUtils.PROTECT_SERVER) {
			if (!data.player.worldObj.isRemote) {
				final float sat = data.saturation.get();
				final float intox = data.intox.get();

				if (data.player.isPotionActive(Potion.regeneration))
					return 1.5F * data.player.getActivePotionEffect(Potion.regeneration).getAmplifier();

				if ((data.playerClass.get() == EnumPlayerClass.HUMAN || ((sat < 40 || intox > 50) && data.playerClass.get() == EnumPlayerClass.WITCHER)))
					return 0;

				if (sat > 40 && intox < 50 && data.playerClass.get() == EnumPlayerClass.WITCHER)
					return 0.05F;
			}
		}
		return 0;
	}

	private final float checkEnergyLose(DAPlayerData data) {
		if (KUtils.PROTECT_SERVER) {
			if (!data.player.worldObj.isRemote) {
				if (data.saturation.get() < 15)
					return 0;

				final float empty = 0.01F;
				final float light = 0.0125F;
				final float medium = 0.02F;
				final float heavy = 0.03F;
				float result = 0;

				for (int i = 0; i < 4; ++i) {
					if (data.player.getCurrentArmor(i) != null) {
						final Item cura = data.player.getCurrentArmor(i).getItem();

						if (cura instanceof Armor && ((Armor) cura).getArmorType() == EnumArmorClass.HEAVY) {
							result += heavy;
						} else if (cura instanceof Armor && ((Armor) cura).getArmorType() == EnumArmorClass.LIGHT) {
							result += light;
						} else if (cura instanceof Armor && ((Armor) cura).getArmorType() == EnumArmorClass.MIDDLE) {
							result += medium;
						} else {
							result += light;
						}
					} else {
						result += empty;
					}
				}
				return -result;
			}
		}
		return 0;
	}
}
