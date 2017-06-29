/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkage.constants.EnumRarity;
import keelfy.darkage.constants.EnumTabs;
import keelfytools.KeelfyUtils;
import keelfytools.LocalizationUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

/**
 * @author keelfy
 */
public class Money extends DAItem {

	private float valueInOrenes;
	
	private String desc_money_key1;
	private String desc_money_key2;
	
	public Money() {
		this.setRarity(EnumRarity.NONE);
		this.setCreativeTab(EnumTabs.MONEY);
		this.setWeight(0F);
		this.setMaxStackSize(50);
		
		if(KeelfyUtils.isClientSide()) {	
			desc_money_key1 = LocalizationUtils.localize(LocalizationUtils.desc_money_key1);
			desc_money_key2 = LocalizationUtils.localize(LocalizationUtils.desc_money_key2);
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack is, EntityPlayer player, List list, boolean bool) {
		list.add(desc_money_key1);
		list.add(desc_money_key2);
		next(list);
			
		addDescriptionToInformation(list);
	}
	
	public void setValueInOrenes(float oren) {
		this.valueInOrenes = oren;
	}
	
	public float getValueInOrenes() {
		return valueInOrenes;
	}
}
