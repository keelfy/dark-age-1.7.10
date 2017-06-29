/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.handlers.registers;

import keelfy.darkage.constants.EnumElexir;
import keelfy.darkage.constants.EnumRarity;
import keelfy.darkage.items.Elexir;
import keelfytools.registry.ItemRegistry;
import net.minecraft.item.Item;

/**
 * @author keelfy
 */
public enum PotionRegister {
	Instance;
	
	public static Item whiteHoney;
	public static Item swallow;
	public static Item grampus;
	public static Item cat;
	public static Item thunder;

	private static String path = "drink";
	
	public static void init() {
		whiteHoney = ItemRegistry.of("whiteHoney".toUpperCase(), new Elexir(EnumRarity.RARE, EnumElexir.БЕЛЫЙ_МЕД).setAddInfo("Снимает все эффекты, снижает интоксикацию")).register(path);
		swallow = ItemRegistry.of("swallow".toUpperCase(), new Elexir(EnumRarity.RARE, EnumElexir.ЛАСТОЧКА).setAddInfo("Восстановление здоровья")).register(path);
		grampus = ItemRegistry.of("grampus".toUpperCase(), new Elexir(EnumRarity.RARE, EnumElexir.КОСАТКА).setAddInfo("Позволят на больший срок задерживать дыхание")).register(path);
		cat = ItemRegistry.of("cat".toUpperCase(), new Elexir(EnumRarity.RARE, EnumElexir.КОШКА).setAddInfo("Позволят лучше видеть в темноте")).register(path);
		thunder = ItemRegistry.of("thunder".toUpperCase(), new Elexir(EnumRarity.RARE, EnumElexir.ГРОМ).setAddInfo("Увеличивает наносимый урон")).register(path);
	}
}
