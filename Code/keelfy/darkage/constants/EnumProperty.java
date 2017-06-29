/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.constants;

/**
 * @author keelfy
 * @created 6 июн. 2017 г.
 */
public enum EnumProperty {
	HEALTH(13, EnumPropertyObject.FLOAT),
	INTOXICATION(27, EnumPropertyObject.FLOAT),
	ENERGY(20, 100F, EnumPropertyObject.FLOAT),
	SATURATION(22, 100, EnumPropertyObject.FLOAT),
	WEIGHT(23, EnumPropertyObject.FLOAT),
	CURRENT_SIGN(15, 5, EnumPropertyObject.INTEGER);
	
	private int dataWatcherID;
	private float maxValue;
	private EnumPropertyObject type;
	
	private EnumProperty(int dwId, float maxValue, EnumPropertyObject type) {
		
		this.type = type;
		this.dataWatcherID = dwId;
		this.maxValue = maxValue;
	}
	
	private EnumProperty(int dwId, EnumPropertyObject type) {
		this(dwId, -1, type);
	}
	
	public EnumPropertyObject getType() {
		return type;
	}
	
	public int id() {
		return dataWatcherID;
	}
	
	public float getMaxValue() {
		return maxValue;
	}
}