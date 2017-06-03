package keelfy.witcher.item;

import java.util.List;

import keelfy.api.Brush;
import keelfy.witcher.util.DATab;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

/**
 * @author keelfy
 */
public class RepairKit extends DAItem {

	private float repairPercent = 0;
	private RepairKitType type;
	public RepairKit(ItemRarity rarity, float weight, float percent, RepairKitType type, String... addInfo) {
		super(rarity, weight, addInfo);
		this.repairPercent = percent;
		this.type = type;
		
		this.setMaxStackSize(3);
		this.setCreativeTab(DATab.wcMain);
	}
	
	@Override
	public void addInformation(ItemStack is, EntityPlayer player, List list, boolean bool) {
		list.add(Brush.GRAY + "\'Ctrl + ЛКМ\' для меню ремонта");
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
		ARMOR,
		WEAPON;
	}
}
