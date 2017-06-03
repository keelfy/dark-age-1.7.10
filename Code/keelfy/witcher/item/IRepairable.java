package keelfy.witcher.item;

import keelfy.witcher.item.RepairKit.RepairKitType;

/**
 * @author keelfy
 */
public interface IRepairable {
	public static final String DURABILITY = "durability".toLowerCase();
	
	public RepairKitType getRepairKitType();
}
