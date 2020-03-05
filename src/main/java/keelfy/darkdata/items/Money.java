/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkdata.constants.EnumRarity;
import keelfy.darkdata.handlers.DATabsHandler;
import keelfy.darkdata.utils.DALocalization;
import keelfyutils.KUtils;
import keelfyutils.client.KLocalization;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

/**
 * @author keelfy
 */
public final class Money extends DAItem {

	private float valueInOrenes;

	private String desc_money_key1;
	private String desc_money_key2;

	public Money() {
		super();
		
		this.setRarity(EnumRarity.NONE);
		this.setCreativeTab(DATabsHandler.money);
		this.setWeight(0F);
		this.setMaxStackSize(50);
	}

	@Override
	protected void initLocalization() {
		if (KUtils.PROTECT_CLIENT) {
			super.initLocalization();

			desc_money_key1 = KLocalization.localize(DALocalization.Desc_Money_Key1);
			desc_money_key2 = KLocalization.localize(DALocalization.Desc_Money_Key2);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public final void addInformation(final ItemStack is, final EntityPlayer player, final List list,
			final boolean bool) {
		list.add(desc_money_key1);
		list.add(desc_money_key2);
		next(list);

		addDescriptionToInformation(is, list);
	}

	public final void setValueInOrenes(final float oren) {
		this.valueInOrenes = oren;
	}

	public final float getValueInOrenes() {
		return valueInOrenes;
	}
}
