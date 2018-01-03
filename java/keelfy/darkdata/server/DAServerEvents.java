/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.server;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import keelfy.darkcore.common.player.DADataManager;
import keelfy.darkcore.common.player.DAPlayerData;
import keelfy.darkcore.common.player.EffectsManager.PlayerEffect;
import keelfy.darkdata.constants.EnumEffect;
import keelfy.darkdata.items.Sword;
import keelfyutils.KUtils;
import keelfyutils.client.KLocalization;
import keelfyutils.server.KServer;
import keelfyutils.str.Brush;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import noppes.npcs.controllers.Faction;
import noppes.npcs.controllers.FactionController;
import noppes.npcs.controllers.PlayerData;
import noppes.npcs.controllers.PlayerDataController;
import noppes.npcs.entity.EntityNPCInterface;

/**
 * @author keelfy
 * @created 12 июл. 2017 г.
 */
public enum DAServerEvents {
	Instance;

	public final void register() {
		if (KUtils.PROTECT_SERVER) {
			FMLCommonHandler.instance().bus().register(this);
			MinecraftForge.EVENT_BUS.register(this);
		}
	}

	public int timer = 0;

	@SubscribeEvent
	public final void attackEntityWithCurrentItem(AttackEntityEvent event) {
		if (KUtils.PROTECT_SERVER) {
			EntityPlayer player = event.entityPlayer;

			if (player.worldObj.isRemote)
				return;

			Entity target = event.target;
			ItemStack stack = player.getCurrentEquippedItem();

			if (timer > 0) {
				return;
			}

			if (player.capabilities.isCreativeMode && target != null && target instanceof EntityPlayer
					&& !((EntityPlayer) target).capabilities.isCreativeMode) {
				KServer.sendMessage(player, Brush.DARK_AQUA + "[Dark Age] " + Brush.RED
						+ "Вы не можете бить игрока, находясь в творческом режиме!");
				event.setCanceled(true);
			}

			if (stack != null && stack.getItem() instanceof Sword && !target.hitByEntity(player)) {

				if (!stack.hasTagCompound()) {
					stack.setTagCompound(new NBTTagCompound());
				}

				float f = (float) player.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
				boolean flag3 = false;
				int i = 0;
				float f1 = 0.0F;
				Sword sword = (Sword) stack.getItem();

				switch (sword.getType()) {
				case STEEL:
					if (!(target instanceof EntityPlayer)) {
						if (target instanceof EntityNPCInterface) {
							EnumCreatureAttribute creature = ((EntityNPCInterface) target).stats.creatureType;
							flag3 = !(creature == EnumCreatureAttribute.ARTHROPOD
									|| creature == EnumCreatureAttribute.UNDEFINED);
						} else {
							flag3 = true;
						}
					}
					break;
				case SILVER:
					if (target instanceof EntityPlayer) {
						flag3 = true;
					} else if (target instanceof EntityNPCInterface) {
						EnumCreatureAttribute creature = ((EntityNPCInterface) target).stats.creatureType;
						flag3 = (creature == EnumCreatureAttribute.ARTHROPOD
								|| creature == EnumCreatureAttribute.UNDEFINED);
					}
					break;

				}

				if (target instanceof EntityLivingBase) {
					f1 = EnchantmentHelper.getEnchantmentModifierLiving(player, (EntityLivingBase) target);
					i += EnchantmentHelper.getKnockbackModifier(player, (EntityLivingBase) target);
				}

				if (player.isSprinting()) {
					++i;
				}

				if (f > 0.0F || f1 > 0.0F) {
					boolean flag = player.fallDistance > 0.0F && !player.onGround && !player.isOnLadder()
							&& !player.isInWater() && !player.isPotionActive(Potion.blindness)
							&& player.ridingEntity == null && target instanceof EntityLivingBase;

					if (flag && f > 0.0F) {
						f *= 1.5F;
					}

					f += f1;
					boolean flag1 = false;
					int j = EnchantmentHelper.getFireAspectModifier(player);

					if (target instanceof EntityLivingBase && j > 0 && !target.isBurning()) {
						flag1 = true;
						target.setFire(1);
					}

					float finalDamage = flag3 ? (f * 0.4F) : f;

					DAPlayerData data = DADataManager.getPlayer(player);
					PlayerEffect strength = data.effects.get(EnumEffect.Strength);

					if (strength.isActive()) {
						finalDamage = finalDamage * strength.getEfficiency();
					}

					boolean flag2 = false;
					flag2 = target.attackEntityFrom(DamageSource.causePlayerDamage(player), finalDamage);

					timer = 8;

					if (target instanceof EntityPlayer || target instanceof EntityNPCInterface) {

						Set<Integer> factions = new HashSet<Integer>();
						int decr = 120;

						if (target instanceof EntityNPCInterface) {
							EntityNPCInterface npc = (EntityNPCInterface) target;
							decr = 60;
						}

						if (((EntityLivingBase) target).getHealth() <= 0) {
							decr = 2000;
						}

						final double r = DAServerConfig.Instance.radiusOfReputationDecreasing;
						final List<Integer> guards = DAServerConfig.Instance.guardFactionIds;
						List<EntityNPCInterface> npcs = player.worldObj.getEntitiesWithinAABB(EntityNPCInterface.class,
								player.boundingBox.expand(r, r, r));

						for (EntityNPCInterface npc : npcs) {
							if (npc.faction == null) {
								continue;
							} else if(((EntityNPCInterface)target).faction.id != npc.faction.id) {
								continue;
							}
							
							if (guards.contains(npc.faction.id)) {
								npc.setLastAttacker(player);
								npc.onAttack(player);
								factions.add(npc.faction.id);
							}
						}
						int counter = 0;
						StringBuilder sb = new StringBuilder();
						String factionsInfo = "";
						final PlayerData cdata = PlayerDataController.instance.getPlayerData(player);
						for (Integer factionId : factions) {
							final Faction faction = FactionController.getInstance().factions.get(factionId);
							cdata.factionData.increasePoints(factionId, -decr);
							sb.append(faction.name + ", ");
							counter++;
						}

						if (counter != 0) {
							cdata.saveNBTData(null);
							factionsInfo = KLocalization.replaceLastChars(sb.toString(), "", 2);
							KServer.sendMessage(player,
									Brush.AQUA + "[Dark Age] " + Brush.RED + "Отношение с фракци"
											+ (counter == 1 ? "ей" : "ями") + " " + factionsInfo + " понижено на "
											+ decr + "!");
						}
					}

					if (flag2) {

						if (i > 0) {
							target.addVelocity(
									-MathHelper.sin(player.rotationYaw * (float) Math.PI / 180.0F) * i * 0.5F, 0.1D,
									MathHelper.cos(player.rotationYaw * (float) Math.PI / 180.0F) * i * 0.5F);
							player.motionX *= 0.6D;
							player.motionZ *= 0.6D;
							player.setSprinting(false);
						}

						if (flag) {
							player.onCriticalHit(target);
						}

						if (f1 > 0.0F) {
							player.onEnchantmentCritical(target);
						}

						if (f >= 18.0F) {
							player.triggerAchievement(AchievementList.overkill);
						}

						player.setLastAttacker(target);

						if (target instanceof EntityLivingBase) {
							EnchantmentHelper.func_151384_a((EntityLivingBase) target, player);
						}

						EnchantmentHelper.func_151385_b(player, target);
						ItemStack itemstack = player.getCurrentEquippedItem();
						Object object = target;

						if (target instanceof EntityDragonPart) {
							IEntityMultiPart ientitymultipart = ((EntityDragonPart) target).entityDragonObj;

							if (ientitymultipart != null && ientitymultipart instanceof EntityLivingBase) {
								object = ientitymultipart;
							}
						}

						if (itemstack != null && object instanceof EntityLivingBase) {
							itemstack.hitEntity((EntityLivingBase) object, player);

							if (itemstack.stackSize <= 0) {
								player.destroyCurrentEquippedItem();
							}
						}

						if (target instanceof EntityLivingBase) {
							player.addStat(StatList.damageDealtStat, Math.round(f * 10.0F));

							if (j > 0) {
								target.setFire(j * 4);
							}
						}

					} else if (flag1) {
						target.extinguish();
					}
				}
				event.setCanceled(true);
			}
		}
	}

	@SubscribeEvent
	public final void pickup(final EntityItemPickupEvent event) {
		if (KUtils.PROTECT_SERVER) {
			if (event.entity instanceof EntityPlayer) {
				final EntityPlayer player = event.entityPlayer;

				if (!player.capabilities.isCreativeMode) {
					event.setCanceled(true);
					if (DADataManager.getPlayer(player).inventory.addItemStackToInventory(event.item.getEntityItem())) {
						player.worldObj.playSoundAtEntity(player, "random.pop", 1.0F, 1.0F);
						return;
					}
				}
			}
		}
	}

	@SubscribeEvent
	public final void onJoinWorld(final EntityJoinWorldEvent event) {
		if (KUtils.PROTECT_SERVER) {
			if (!event.entity.worldObj.isRemote) {
				if (event.entity.getEntityId() == 4) {

				}

				if (event.entity instanceof EntityLivingBase) {
					final EntityLivingBase living = (EntityLivingBase) event.entity;

					living.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.knockbackResistance)
							.setBaseValue(1f);

					if (living instanceof EntitySlime) {
						event.setCanceled(true);
					}
				}
			}
		}
	}
}
