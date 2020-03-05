/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkdata.constants.EnumRarity;
import keelfy.darkdata.constants.EnumRepairKit;
import keelfy.darkdata.handlers.DATabsHandler;
import keelfy.darkdata.utils.DALocalization;
import keelfyutils.KUtils;
import keelfyutils.client.KLocalization;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

/**
 * @author keelfy
 */
public final class RepairKit extends DAItem {

	private float repairPercent;
	private EnumRepairKit type;

	@SideOnly(Side.CLIENT)
	private String desc_repairing_menu;

	/** DAI File */
	public RepairKit() {
		super();
		
		setCreativeTab(DATabsHandler.repairKits);
		setWeight(1F);
		setMaxStackSize(3);
		setRarity(EnumRarity.NONE);
	}

	@Override
	protected void initLocalization() {
		if (KUtils.PROTECT_CLIENT) {
			super.initLocalization();

			this.desc_repairing_menu = KLocalization.localize(DALocalization.Desc_Repairing_Menu);
		}
	}

	public final void setRepairPercent(final float repairPercent) {
		this.repairPercent = repairPercent;
	}

	public final void setType(final EnumRepairKit type) {
		this.type = type;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public final void addInformation(final ItemStack is, final EntityPlayer player, final List list,
			final boolean bool) {
		list.add(desc_repairing_menu);
		next(list);

		super.addInformation(is, player, list, bool);
	}

	public final float getRepairPercent() {
		return repairPercent;
	}

	public final EnumRepairKit getType() {
		return type;
	}
}
