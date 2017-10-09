/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.constants;

import net.minecraft.init.Items;
import net.minecraft.item.Item;

/**
 * @author keelfy
 */
public enum EnumVanillaItemWeight {

	Bow(Items.bow, 2.0F);

	private float weight = 1.0F;
	private Item item;

	private EnumVanillaItemWeight(Item item, final float weight) {
		this.weight = weight;
		this.item = item;
	}

	public Item getItem() {
		return item;
	}
	
	public float getWeight() {
		return weight;
	}
}
