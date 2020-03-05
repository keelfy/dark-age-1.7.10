/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import keelfy.darkcore.common.player.DADataManager;
import keelfy.darkcore.common.player.DAPlayerData;
import keelfy.darkcore.common.player.managers.EnergyManager;
import keelfy.darkcore.common.player.managers.HealthManager;
import keelfy.darkcore.common.player.managers.EffectsManager.PlayerEffect;
import keelfy.darkdata.constants.EnumEffect;
import keelfy.darkdata.items.Armor;
import keelfy.darkdata.items.Sword;
import keelfyutils.KUtils;
import keelfyutils.bukkit.KBukkitHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;

/**
 * @author keelfy
 * @created 21 июн. 2017 г.
 */
public final class DAEntityUtils {

	public static final void attack(final EntityPlayer player, final HealthManager health, float damage,
			final boolean witharmor) {
		if (KUtils.PROTECT_SERVER) {
			damage = -(witharmor ? DAEntityUtils.getReceivedDamage(player, damage) : damage);

			health.change(damage);
		}
	}

	public static final boolean isDamageEnabled(final EntityPlayerMP player, final Entity source, final float amount) {
		if (!MinecraftServer.getServer().isDedicatedServer() || !KBukkitHandler.isBukkitEnabled())
			return true;

		if (!KUtils.SINGLEPLAYER) {
			if (source != null && source instanceof EntityPlayer) {
				final Player damager = Bukkit.getPlayer(source.getUniqueID());
				final Player damagee = Bukkit.getPlayer(player.getUniqueID());
				final DamageCause cause = DamageCause.ENTITY_ATTACK;
				final EntityDamageEvent event = new EntityDamageByEntityEvent(damager, damagee, cause, amount);

				Bukkit.getPluginManager().callEvent(event);

				if (event.isCancelled())
					return false;
			}
		}
		return true;
	}

	public static float getTotalBlockingPercent(final EntityPlayer player) {
		float r = 0;

		final DAPlayerData dap = DADataManager.getPlayer(player);
		final PlayerEffect resistance = dap.effects.get(EnumEffect.Resistance);

		if (resistance.isActive())
			return resistance.getEfficiency() * 100;

		for (int i = 0; i < 4; i++) {
			if (player.getCurrentArmor(i) != null && player.getCurrentArmor(i).getItem() instanceof Armor) {
				r += ((Armor) player.getCurrentArmor(i).getItem()).getBlockingPercent();
			}
		}
		return r;
	}

	public static float getReceivedDamage(final EntityPlayer player, final float damage) {
		if (KUtils.PROTECT_SERVER) {
			final DAPlayerData dap = DADataManager.getPlayer(player);
			final PlayerEffect resistance = dap.effects.get(EnumEffect.Resistance);
			if (resistance.isActive())
				return damage - (resistance.getEfficiency() * damage);

			float resultDamage = damage;
			float r1 = resultDamage / 100;

			for (int i = 0; i < 4; i++) {
				if (player.getCurrentArmor(i) != null && player.getCurrentArmor(i).getItem() instanceof Armor) {
					resultDamage -= r1 * ((Armor) player.getCurrentArmor(i).getItem()).getBlockingPercent();
				}
			}

			final ItemStack current = player.getCurrentEquippedItem();
			final EnergyManager energy = DADataManager.getPlayer(player).energy;

			if (energy.get() - 25F >= energy.getMin() && current != null && current.getItem() instanceof Sword
					&& player.isUsingItem()) {
				resultDamage *= 0.2F;
				energy.change(-25F);
			}

			return resultDamage;
		}
		return 0;
	}

	public static final boolean addItemStackToInventory(final EntityPlayer player, final ItemStack stack) {
		if (KUtils.PROTECT_SERVER) {
			if (stack == null)
				return false;

			if (!player.capabilities.isCreativeMode)
				return DADataManager.getPlayer(player).inventory.addItemStackToInventory(stack);
			else
				return player.inventory.addItemStackToInventory(stack);
		}
		return false;
	}
}
