package keelfy.darkdata.network;

import keelfyutils.bukkit.KPermissionsLoader.Permission;

/**
 * @author keelfy
 * @created 24 июл. 2017 г.
 */
public enum EnumSPackets {

	PlayerClass,
	OpenGui,
	UpdateBook,
	HotSlot,
	UseSign,
	EditSentinel,
	Drop,
	Repair,
	MoneyClick,
	PerformEffect,
	CureEffect;

	public Permission permission = null;

}
