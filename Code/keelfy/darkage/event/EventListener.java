
package keelfy.darkage.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkage.entity.player.DAPlayer;
import keelfy.darkage.entity.player.effect.DAEffect;
import keelfy.darkage.event.custom.NPCAttackEntityEvent;
import keelfy.darkage.event.custom.PlayerJumpEvent;
import keelfy.darkage.network.server.ServerPacketHandler;
import keelfy.darkage.util.DAUtil;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
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
	@SideOnly(Side.SERVER)
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
					dap.attackPlayer(event.damage, true);
				}
			}
		}
	}
	
	@SubscribeEvent
	public void hurt(LivingHurtEvent event) {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			if(!event.entityLiving.worldObj.isRemote) {
				if(event.entity instanceof EntityPlayer) {
					if(!(event.source == DamageSource.outOfWorld)) {
						EntityPlayer player = (EntityPlayer)event.entityLiving;
						DAPlayer dap = DAPlayer.get(player);
						
						if(dap != null) {
							event.setCanceled(true);
							
							if(event.source == DamageSource.fall) {
								dap.attackPlayer(event.ammount * 30, false);
							} else if(event.source == DamageSource.inFire) {
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
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			if (event.entity instanceof EntityPlayerMP && !event.entity.worldObj.isRemote) {				
				EntityPlayerMP player = (EntityPlayerMP) event.entity;
				
				ServerPacketHandler.syncPlayer(player);
				ServerPacketHandler.syncEffects(player);
			}
			
			if(event.entity instanceof EntityLiving) {
				EntityLiving living = (EntityLiving) event.entity;
				
				living.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.knockbackResistance).setBaseValue(1f);;
			}
		}
	}
}
