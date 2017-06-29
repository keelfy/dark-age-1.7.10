/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import keelfy.darkage.entities.player.DAPlayer;
import keelfy.darkage.entities.player.effect.DAEffect;
import keelfy.darkage.events.custom.NPCAttackEntityEvent;
import keelfy.darkage.events.custom.PlayerJumpEvent;
import keelfy.darkage.network.ServerPacketHandler;
import keelfytools.KeelfyUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.DamageSource;
import net.minecraft.world.GameRules;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

/**
 * @author keelfy
 */
public enum EventListener {
	Instance;
	
	public final void register() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event) {
		if (event.entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entity;

			if (DAPlayer.get(player) == null) {
				DAPlayer.register(player);
			}

			if (DAEffect.get(player) == null) {
				DAEffect.register(player);
			}
		}
	}

	@SubscribeEvent
	public void onClonePlayer(PlayerEvent.Clone event) {
		DAPlayer newPlayer = DAPlayer.get(event.entityPlayer);

		newPlayer.copy(event.original);
	}

	@SubscribeEvent
	public void onDeathPlayer(LivingDeathEvent event) {
		if (KeelfyUtils.isServerSide()) {
			if (event.entityLiving instanceof EntityPlayer) {
				DAPlayer dap = DAPlayer.get((EntityPlayer) event.entityLiving);
				
				if(dap != null) {
					dap.inventory.dropAllItems();
				}
			}
		}
	}

	@SubscribeEvent
	public void onPlayerJump(PlayerJumpEvent event) {
		if (KeelfyUtils.isServerSide()) {
			DAPlayer dap = DAPlayer.get(event.player);

			if (dap != null) {
				dap.changeEnergy(-7);
			}
		}
	}

	@SubscribeEvent
	public void pickup(EntityItemPickupEvent event) {
		if (KeelfyUtils.isServerSide()) {
			if (event.entity instanceof EntityPlayer) {
				EntityPlayer player = event.entityPlayer;

				if (!player.capabilities.isCreativeMode) {
					event.setCanceled(true);
					DAPlayer dap = DAPlayer.get(player);

					if (dap != null && dap.inventory.addItemStackToInventory(event.item.getEntityItem())) {
						player.worldObj.playSoundAtEntity(player, "random.pop", 1.0F, 1.0F);
						return;
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void npcAttackEntity(NPCAttackEntityEvent event) {
		if (KeelfyUtils.isServerSide()) {
			if (event.entity instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) event.entity;
				DAPlayer dap = DAPlayer.get(player);

				if (dap != null) {
					event.setCanceled(true);
					dap.attackPlayer(event.damage, true);
				}
			}
		}
	}

	@SubscribeEvent
	public void hurt(LivingHurtEvent event) {
		if (KeelfyUtils.isServerSide()) {
			if (!event.entityLiving.worldObj.isRemote) {
				if (event.entity instanceof EntityPlayer) {
					if (!(event.source == DamageSource.outOfWorld)) {
						EntityPlayer player = (EntityPlayer) event.entityLiving;
						DAPlayer dap = DAPlayer.get(player);

						if (dap != null) {
							event.setCanceled(true);

							if (event.source == DamageSource.fall) {
								dap.attackPlayer(event.ammount * 30, false);
							} else if (event.source == DamageSource.inFire) {
								dap.attackPlayer(50, false);
							} else {
								dap.attackPlayer(event.ammount, true);
							}
						}
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void onJoinWorld(EntityJoinWorldEvent event) {
		if (KeelfyUtils.isServerSide()) {
			if (event.entity instanceof EntityPlayerMP && !event.entity.worldObj.isRemote) {
				EntityPlayerMP player = (EntityPlayerMP) event.entity;

				ServerPacketHandler.syncPlayer(player);
				ServerPacketHandler.syncEffects(player);
			}
			
			final GameRules gameRules = event.world.getGameRules();
			
			if(!gameRules.getGameRuleBooleanValue("keepInventory")) {
				gameRules.setOrCreateGameRule("keepInventory", Boolean.toString(true));
			}
			
			if (event.entity instanceof EntityLivingBase) {
				EntityLivingBase living = (EntityLivingBase) event.entity;

				living.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.knockbackResistance)
						.setBaseValue(1f);
				;
			}
		}
	}
}
