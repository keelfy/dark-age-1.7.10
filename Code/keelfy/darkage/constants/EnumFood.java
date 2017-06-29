/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.constants;

import net.minecraft.util.StatCollector;

/**
 * @author keelfy
 * @created 6 июн. 2017 г.
 */
public enum EnumFood {
	RAW(5, StatCollector.translateToLocal("food.raw")),
	NOSH(15, StatCollector.translateToLocal("food.nosh")),
	NUTRITIOUS(30, StatCollector.translateToLocal("food.nutritious")),
	NOURISHING(50, StatCollector.translateToLocal("food.nourishing"));
	
	private float amount;
	private String name;
	private EnumFood(float amount, String name) {
		this.amount = amount;
		this.name = name;
	}
	
	public String getName() {
		return name.replace('&', '§');
	}
	
	public float getAmount() {
		return amount;
	}
}