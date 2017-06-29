/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.items;

import keelfy.darkage.client.renderer.RendererPotion;
import keelfy.darkage.constants.EnumElexir;
import keelfy.darkage.constants.EnumProperty;
import keelfy.darkage.constants.EnumRarity;
import keelfy.darkage.constants.EnumTabs;
import keelfy.darkage.entities.player.DAPlayer;
import keelfy.darkage.entities.player.effect.DAEffect;
import keelfytools.KeelfyUtils;
import keelfytools.KeelfyUtilsServer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;

/**
 * @author keelfy
 */
public class Elexir extends DAItem implements IFastUsable {

	private EnumElexir effect;

	public Elexir(EnumRarity rarity, EnumElexir effect) {
		super(rarity, 0.5F);
		
		this.setType(effect);
		this.setCreativeTab(EnumTabs.POTION);
		this.setMaxStackSize(3);
		
		if(KeelfyUtils.isClientSide()) {
			MinecraftForgeClient.registerItemRenderer(this, new RendererPotion());
		}
	}
	
	@Override
	// TODO: Разобраться с наложением эффектов друг на друга
	public ItemStack use(ItemStack is, World world, EntityPlayer player, int slot) {
		if(KeelfyUtils.isServerSide()) {
			if(!world.isRemote) {
				
				DAPlayer wcp = DAPlayer.get(player);
				DAEffect wce = DAEffect.get(player);
				
				if(wcp != null && wce != null && wcp.get(EnumProperty.INTOXICATION) != wcp.getWitcherMaxIntox()) {
					if(wcp.get(EnumProperty.INTOXICATION) + 25F <= wcp.getWitcherMaxIntox()) {
						boolean r = false; 
						
						if(effect != EnumElexir.БЕЛЫЙ_МЕД) 
							wcp.changeIntox(25F);
						
						if(effect == EnumElexir.БЕЛЫЙ_МЕД) {
							wcp.resetWitcherIntox();
							r = true;
						} else if(effect == EnumElexir.ЛАСТОЧКА) {
							r = addEffect(player, Potion.regeneration, 180, 1);
						} else if (effect == EnumElexir.КОСАТКА) {
							r = addEffect(player, Potion.waterBreathing, 1800, 1);
						} else if (effect == EnumElexir.КОШКА) {
							r = addEffect(player, Potion.nightVision, 180, 1);
						} else if (effect == EnumElexir.ПУРГА) {
							r = addEffect(player, Potion.moveSpeed, 180, 1);
						} else if (effect == EnumElexir.ГРОМ) {
							r = addEffect(player, Potion.damageBoost, 180, 1);
						}
						
						if(r) {
							world.playSoundAtEntity(player, "random.drink", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
							
							if (!player.capabilities.isCreativeMode) {
								if(slot != -1) {
									--player.inventory.mainInventory[slot].stackSize;
									if (player.inventory.mainInventory[slot].stackSize <= 0) {
										player.inventory.mainInventory[slot] = null;
									}
								}
							}
						}
					}
				}
			}
		}
		return is;
	}

	private final boolean addEffect(EntityPlayer player, Potion effect, int time, int eff) {
		if(KeelfyUtils.isServerSide()) {
			if(!player.isPotionActive(effect)) {
				player.addPotionEffect(new PotionEffect(effect.id, time, eff));
				return true;
			}
			KeelfyUtilsServer.sendErrMessage(player, "На вас уже действует эффект от этого эликсира!");
		}
		return false;
	}
	
	public void setType(EnumElexir effect) {
		this.effect = effect;
	}
}
