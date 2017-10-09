/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkcore.common.player.DADataManager;
import keelfy.darkdata.client.renderer.DARendererItem;
import keelfy.darkdata.constants.EnumFood;
import keelfy.darkdata.constants.EnumItemType;
import keelfy.darkdata.constants.EnumRarity;
import keelfy.darkdata.handlers.DATabsHandler;
import keelfyutils.KUtils;
import keelfyutils.str.Brush;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

/**
 * @author keelfy
 */
public final class Food extends DAItem implements IFastUsable, IItemWithRenderer {

	private PotionEffect[] effects = null;
	private float saturation = 0;
	private EnumFood satLvl = null;

	public Food() {
		super();

		this.setRarity(EnumRarity.USUAL);
		this.setCreativeTab(DATabsHandler.foods);
		this.setSaturation(0);
		this.setMaxStackSize(3);
	}

	@Override
	public final void initRenderer() {
		if (KUtils.PROTECT_CLIENT) {
			DARendererItem.register(this, EnumItemType.FOOD);
		}
	}

	public final void setSaturationLevel(final EnumFood satLvl) {
		this.satLvl = satLvl;
		this.saturation = satLvl.getAmount();
	}

	public final void setSaturation(final float saturation) {
		this.saturation = saturation;
	}

	public final void setEffects(final PotionEffect[] effects) {
		this.effects = effects;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public final void addInformation(final ItemStack is, final EntityPlayer player, final List list,
			final boolean bool) {
		if (satLvl != null) {
			list.add(Brush.GREEN + satLvl.getName());
			next(list);
		}

		super.addInformation(is, player, list, bool);
	}

	@Override
	public final ItemStack use(final ItemStack is, final World world, final EntityPlayer player, final int slot) {
		if (KUtils.PROTECT_SERVER) {
			world.playSoundAtEntity(player, "random.eat", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
			if (!world.isRemote) {
				if (!player.capabilities.isCreativeMode) {
					if (effects != null) {
						for (final PotionEffect effect : effects) {
							player.addPotionEffect(effect);
						}
					}

					DADataManager.getPlayer(player).saturation.change(saturation);

					--player.inventory.mainInventory[slot].stackSize;
					if (player.inventory.mainInventory[slot].stackSize <= 0) {
						player.inventory.mainInventory[slot] = null;
					}
				}
			}
		}
		return is;
	}
}
