package keelfy.darkage.item;

import keelfy.darkage.item.RepairKit.RepairKitType;

/**
 * @author keelfy
 */
public interface IRepairable {
	public static final String DURABILITY = "durability".toLowerCase();
	
	public RepairKitType getRepairKitType();
}
