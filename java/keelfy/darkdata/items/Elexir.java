/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.items;

import keelfy.darkcore.common.player.DADataManager;
import keelfy.darkcore.common.player.DAPlayerData;
import keelfy.darkdata.client.renderer.DARendererItem;
import keelfy.darkdata.constants.EnumEffect;
import keelfy.darkdata.constants.EnumElexir;
import keelfy.darkdata.constants.EnumItemType;
import keelfy.darkdata.constants.EnumRarity;
import keelfy.darkdata.handlers.DATabsHandler;
import keelfyutils.KUtils;
import keelfyutils.server.KServer;
import keelfyutils.str.Brush;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

/**
 * @author keelfy
 */
public final class Elexir extends DAItem implements IFastUsable, IItemWithRenderer {

	private EnumElexir effect;

	public Elexir(final EnumRarity rarity, final EnumElexir effect) {
		super(rarity, 0.5F);

		this.setType(effect);
		this.setCreativeTab(DATabsHandler.elixirs);
		this.setMaxStackSize(3);
	}

	@Override
	public final void initRenderer() {
		if (KUtils.PROTECT_CLIENT) {
			DARendererItem.register(this, EnumItemType.ELIXIR);
		}
	}

	@Override
	public final ItemStack use(final ItemStack is, final World world, final EntityPlayer player, final int slot) {
		if (KUtils.PROTECT_SERVER) {
			if (!world.isRemote) {
				final DAPlayerData data = DADataManager.getPlayer(player);

				if (data.playerClass.isHuman()) {
					KServer.sendMessage(player, Brush.AQUA + "[Dark Age] " + Brush.RED + "Вы не можете пить эликсиры, если не прошли мутацию!");
					return is;
				}

				if (data.intox.get() + 25F > data.intox.getMax() && effect != EnumElexir.WhiteHoney)
					return is;

				if (effect != EnumElexir.WhiteHoney) {
					data.intox.change(25.0F);
				}

				switch (effect) {
				case WhiteHoney:
					data.intox.restore();
					break;
				case Thunder:
					data.effects.perform(EnumEffect.Strength, 180, 1.35F, false);
					break;
				case Grampus:
					addEffect(player, Potion.waterBreathing, 1800, 1);
					data.effects.perform(EnumEffect.WaterBreathing, 1800, 1, false);
					break;
				case Cat:
					addEffect(player, Potion.nightVision, 6000, 1);
					data.effects.perform(EnumEffect.NightVision, 6000, 1, false);
					break;
				case Swallow:
					data.effects.perform(EnumEffect.Heal, 400, 1.5F, false);
					break;
				case Blizzard:
					addEffect(player, Potion.moveSpeed, 180, 1);
					data.effects.perform(EnumEffect.MoveSpeed, 180, 1, false);
					break;
				case Owl:
					data.effects.perform(EnumEffect.SecondBreath, 6000, 2F, false);
					break;
				default:
					return is;
				}

				world.playSoundAtEntity(player, "random.drink", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

				if (!player.capabilities.isCreativeMode) {
					if (slot != -1) {
						--player.inventory.mainInventory[slot].stackSize;
						if (player.inventory.mainInventory[slot].stackSize <= 0) {
							player.inventory.mainInventory[slot] = null;
						}
					}
				}
			}
		}
		return is;
	}

	private final boolean addEffect(final EntityPlayer player, final Potion effect, final int time, final int eff) {
		if (KUtils.PROTECT_SERVER) {
			if (!player.isPotionActive(effect)) {
				player.addPotionEffect(new PotionEffect(effect.id, time, eff));
				return true;
			}
			KServer.sendErrMessage(player, "На вас уже действует эффект от этого эликсира!");
		}
		return false;
	}

	public final void setType(final EnumElexir effect) {
		this.effect = effect;
	}
}
