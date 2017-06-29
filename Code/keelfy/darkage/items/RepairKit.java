/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkage.constants.EnumRarity;
import keelfy.darkage.constants.EnumRepairKit;
import keelfy.darkage.constants.EnumTabs;
import keelfytools.KeelfyUtils;
import keelfytools.LocalizationUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

/**
 * @author keelfy
 */
public class RepairKit extends DAItem {

	private float repairPercent;
	private EnumRepairKit type;
	
	private String desc_repairing_menu; 
	
	/**DAI File*/
	public RepairKit() {
		this.setCreativeTab(EnumTabs.REPAIRKIT);
		setWeight(1F);
		setMaxStackSize(3);
		setRarity(EnumRarity.NONE);
		
		if(KeelfyUtils.isClientSide()) {
			this.desc_repairing_menu = LocalizationUtils.localize(LocalizationUtils.desc_repairing_menu);
		}
	}
	
	public void setRepairPercent(float repairPercent) {
		this.repairPercent = repairPercent;
	}
	
	public void setType(EnumRepairKit type) {
		this.type = type;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack is, EntityPlayer player, List list, boolean bool) {
		list.add(desc_repairing_menu);
		next(list);
		
		super.addInformation(is, player, list, bool);
	}
	
	public float getRepairPercent() {
		return repairPercent;
	}
	
	public EnumRepairKit getType() {
		return type;
	}
}
