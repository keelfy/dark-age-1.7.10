package keelfy.darkage.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import keelfy.api.network.PacketDispatcher;
import keelfy.darkage.entity.player.DAPlayer;
import keelfy.darkage.entity.player.DAPlayerUtil;
import keelfy.darkage.entity.player.DAPlayerUtil.Property;
import keelfy.darkage.entity.player.effect.DAEffect;
import keelfy.darkage.event.custom.NPCAttackEntityEvent;
import keelfy.darkage.event.custom.PlayerJumpEvent;
import keelfy.darkage.item.Armor;
import keelfy.darkage.network.client.SyncEffectsMessage;
import keelfy.darkage.network.client.SyncPlayerMessage;
import keelfy.darkage.util.DAUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.DamageSource;
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
public class EventListener {
	
	public EventListener() {
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
		DAPlayer oldPlayer = DAPlayer.get(event.original);
		
		newPlayer.copy(oldPlayer);
	}
	
	@SubscribeEvent
	public void onDeathPlayer(LivingDeathEvent event) {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			if(event.entityLiving instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer)event.entityLiving;
				DAPlayer dap = DAPlayer.get(player);
				
				if(dap != null) {
					dap.inventory.dropAllItems((EntityPlayer)event.entityLiving);
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onPlayerJump(PlayerJumpEvent event) {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			DAPlayer dap = DAPlayer.get(event.player);
			
			if(dap != null) {
				dap.changeEnergy(-7);
			}
		}
	}
	
	@SubscribeEvent
	public void pickup(EntityItemPickupEvent event) {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			if(event.entity instanceof EntityPlayer) {
				EntityPlayer player = event.entityPlayer;
				
				if(!player.capabilities.isCreativeMode) {
					event.setCanceled(true);
					DAPlayer dap = DAPlayer.get(player);
					
					if(dap != null && dap.inventory.addItemStackToInventory(event.item.getEntityItem())) {
						player.worldObj.playSoundAtEntity(player, "random.pop", 1.0F, 1.0F);
						return;
					}
				}
			}
		}
	}
	
	@SubscribeEvent
	public void npcAttackEntity(NPCAttackEntityEvent event) {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			if(event.entity instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) event.entity;
				DAPlayer dap = DAPlayer.get(player);
				
				if(dap != null) {
					event.setCanceled(true);
					float newDamage = -event.damage;
					dap.changeHealth(newDamage);
					player.attackEntityFrom(event.damageSource, 1);
					player.setHealth(player.getMaxHealth());
				}
			}
		}
	}
	
	@SubscribeEvent
	public void hurt(LivingHurtEvent event) {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			if(event.entity instanceof EntityPlayer) {
				if(!(event.source == DamageSource.outOfWorld)) {
					EntityPlayer player = (EntityPlayer)event.entityLiving;
					DAPlayer dap = DAPlayer.get(player);
					
					if(dap != null) {
						event.setCanceled(true);
						float ra = -DAPlayerUtil.getReceivedDamage(player, event.ammount);
						
						if(dap.get(Property.HEALTH) + ra > 0) {
							if(event.source == DamageSource.fall) 
								dap.changeHealth(ra * 30);
							else dap.changeHealth(ra);
						} else dap.update(Property.HEALTH, 0);
					}
					
					if(player.inventory.armorInventory[0] != null && player.inventory.armorInventory[0].getItem() instanceof Armor) {
						((Armor)player.inventory.armorInventory[0].getItem()).damage(player.inventory.armorInventory[0], 1);
					} 
					
					if(player.inventory.armorInventory[1] != null && player.inventory.armorInventory[1].getItem() instanceof Armor) {
						((Armor)player.inventory.armorInventory[1].getItem()).damage(player.inventory.armorInventory[1], 1);
					}
					
					if(player.inventory.armorInventory[2] != null && player.inventory.armorInventory[2].getItem() instanceof Armor) {
						((Armor)player.inventory.armorInventory[2].getItem()).damage(player.inventory.armorInventory[2], 1);
					}
					
					if(player.inventory.armorInventory[3] != null && player.inventory.armorInventory[3].getItem() instanceof Armor) {
						((Armor)player.inventory.armorInventory[3].getItem()).damage(player.inventory.armorInventory[3], 1);
					}
				}
				
				if(event.source == DamageSource.inFire) {
					EntityPlayer player = (EntityPlayer)event.entityLiving;
					DAPlayer dap = DAPlayer.get(player);
					
					if(dap != null) {
						event.setCanceled(true);
						
						if(dap.get(Property.HEALTH) - 50 > 0) {
							dap.changeHealth(-50);
						} else dap.update(Property.HEALTH, 0);
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void onJoinWorld(EntityJoinWorldEvent event) {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			if (event.entity instanceof EntityPlayer && !event.entity.worldObj.isRemote) {
				EntityPlayer player = (EntityPlayer) event.entity;
				DAPlayer wcp = DAPlayer.get(player);
				DAEffect wce = DAEffect.get(player);
				
				if(wcp != null)
					PacketDispatcher.getInstance().sendTo(new SyncPlayerMessage(player), (EntityPlayerMP)player);
				
				if(wce != null)
					PacketDispatcher.getInstance().sendTo(new SyncEffectsMessage(player), (EntityPlayerMP)player);
			}
		}
	}
}
