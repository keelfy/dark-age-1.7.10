/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkdata.client.renderer.DARendererItem;
import keelfy.darkdata.constants.EnumArmorClass;
import keelfy.darkdata.constants.EnumArmorPart;
import keelfy.darkdata.constants.EnumItemType;
import keelfy.darkdata.constants.EnumRarity;
import keelfy.darkdata.constants.EnumRepairKit;
import keelfy.darkdata.handlers.DATabsHandler;
import keelfyutils.KUtils;
import keelfyutils.str.Brush;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

/**
 * @author keelfy
 */
public final class Armor extends ItemWithRender implements IRepairable, IItemWithRenderer {

	private EnumArmorClass type;
	private int partNumber;
	private float blockingPercent;
	private float persistence;

	public Armor(final EnumArmorClass armorType, final EnumRarity rarity, final float weight, final int partNumber,
			final float blockingPercent, final int durability) {
		super("armor", rarity, weight);

		this.setType(armorType);
		this.setPart(partNumber);
		this.setBlockingPercent(blockingPercent);
		this.setMaxDurability(durability);
		this.setCreativeTab(DATabsHandler.armors);

		if (KUtils.PROTECT_SERVER) {
			this.setPersistence(1);
		}
	}

	/** DAI File */
	public Armor() {
		super("armor", EnumRarity.NONE, 0F);

		this.setBlockingPercent(0);
		this.setMaxDurability(0);
		this.setCreativeTab(DATabsHandler.armors);

		if (KUtils.PROTECT_SERVER) {
			this.setPersistence(1);
		}
	}

	@Override
	public final void initRenderer() {
		if (KUtils.PROTECT_CLIENT) {
			DARendererItem.register(this, EnumItemType.ARMOR);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public final void addInformation(final ItemStack is, final EntityPlayer player, final List list,
			final boolean bool) {
		if (KUtils.PROTECT_CLIENT) {
			if (addDescriptionToInformation(is, list)) {
				next(list);
			}
			addWeightToInformation(list);
			addDurabilityToInformation(is, list);
			addRarityToInformation(list);

			if (blockingPercent != 0) {
				next(list);
				list.add(Brush.BLUE + "Процент защиты: " + blockingPercent + "%");
			}
		}
	}

	public final void setPersistence(final float persistence) {
		this.persistence = persistence;
	}

	public final float getPersistence() {
		return persistence;
	}

	public final void setBlockingPercent(final float blockingPercent) {
		this.blockingPercent = blockingPercent;
	}

	public final float getBlockingPercent() {
		return blockingPercent;
	}

	public final void setPart(final int partNumber) {
		this.partNumber = partNumber;
	}

	public final void setPart(final EnumArmorPart part) {
		this.partNumber = part.ordinal();
	}

	public final int getPart() {
		return partNumber;
	}

	public final EnumArmorPart getPartEnum() {
		return EnumArmorPart.values()[partNumber];
	}

	public final void setType(final EnumArmorClass type) {
		this.type = type;
	}

	public final EnumArmorClass getArmorType() {
		return type;
	}

	@Override
	public final EnumRepairKit getRepairKitType() {
		return EnumRepairKit.ARMOR;
	}
}
