/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkcore.common.player;

import keelfy.darkdata.constants.EnumVanillaItemWeight;
import keelfy.darkdata.items.Armor;
import keelfy.darkdata.items.DAItem;
import keelfy.darkdata.items.Sword;
import keelfyutils.KUtils;
import keelfyutils.network.KDataWatcher;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * @author keelfy
 */
public final class WeightManager extends DAStatManager {

	private static final int WEIGHT = 22;
	private static final String NBTNAME = String.valueOf("PlayerWeight");

	public WeightManager(final DAPlayerData dap) {
		super(dap);

		KDataWatcher.addf(player, WEIGHT, 0);
	}

	public final float get() {
		return KDataWatcher.getf(player, WEIGHT);
	}

	public final boolean isOverweight() {
		return get() > 60.0F;
	}

	public final void set(final float value) {
		if (KUtils.PROTECT_SERVER) {
			if (value >= 0) {
				KDataWatcher.updatef(player, WEIGHT, value);
			} else if (value < 0) {
				KDataWatcher.updatef(player, WEIGHT, 0);
			}
		}
	}

	private final float getVanilaItem(final Item item, final int metadata) {
		if (KUtils.PROTECT_SERVER) {
			if (item == EnumVanillaItemWeight.Bow.getItem())
				return EnumVanillaItemWeight.Bow.getWeight();
			return 1.0F;
		}
		return 1.0F;
	}

	public final float inventory() {
		if (KUtils.PROTECT_SERVER) {
			float weight = 0.0F;

			for (final ItemStack slot : player.inventory.mainInventory) {
				weight += check(slot);
			}

			for (final ItemStack slot : data.inventory.inventoryStacks) {
				weight += check(slot);
			}

			for (int i = 0; i < 4; i++) {
				ItemStack stack = player.getCurrentArmor(i);
				if (stack != null && stack.getItem() instanceof Armor) {
					weight += ((Armor) stack.getItem()).getWeight();
				}
			}
			return weight;
		} else
			return 0;
	}

	private final float check(final ItemStack slot) {
		if (KUtils.PROTECT_SERVER) {
			float weighting = 0.0F;
			if (slot != null) {
				final Item item = slot.getItem();

				if (item instanceof DAItem) {
					weighting += ((DAItem) item).getWeight() * slot.stackSize;
				} else if (item instanceof Sword) {
					weighting += ((Sword) item).getWeight() * slot.stackSize;
				} else if (getVanilaItem(item, slot.getItemDamage()) != 0.0F) {
					weighting += getVanilaItem(item, slot.getItemDamage()) * slot.stackSize;
				}
			}
			return weighting >= 0f ? weighting : 0f;
		} else
			return 0;
	}

	@Override
	public final void saveNBTData(final NBTTagCompound compound) {
		if (KUtils.PROTECT_SERVER) {
			compound.setFloat(NBTNAME, get());
		}
	}

	@Override
	public final void loadNBTData(final NBTTagCompound compound) {
		KDataWatcher.updatef(player, WEIGHT, compound.getFloat(NBTNAME));
	}
}
