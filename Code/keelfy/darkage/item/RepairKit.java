package keelfy.darkage.item;

import java.util.List;

import keelfy.darkage.util.DATab;
import keelfy.darkage.util.LanguageUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

/**
 * @author keelfy
 */
public class RepairKit extends DAItem {

	private float repairPercent;
	private RepairKitType type;
	
	private String desc_repairing_menu; 
	
	/**DAI File*/
	public RepairKit() {
		this.desc_repairing_menu = LanguageUtil.localize(LanguageUtil.desc_repairing_menu);
		
		this.setCreativeTab(DATab.tabRepairKit);
	}
	
	public void setRepairPercent(float repairPercent) {
		this.repairPercent = repairPercent;
	}
	
	public void setType(RepairKitType type) {
		this.type = type;
	}
	
	@Override
	public void addInformation(ItemStack is, EntityPlayer player, List list, boolean bool) {
		list.add(desc_repairing_menu);
		next(list);
		
		super.addInformation(is, player, list, bool);
	}
	
	public float getRepairPercent() {
		return repairPercent;
	}
	
	public RepairKitType getType() {
		return type;
	}
	
	public static enum RepairKitType {
		ARMOR, WEAPON;
	}
}
