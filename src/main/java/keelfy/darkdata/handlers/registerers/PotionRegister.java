/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.handlers.registerers;

import keelfy.darkdata.constants.EnumElexir;
import keelfy.darkdata.constants.EnumRarity;
import keelfy.darkdata.items.Elexir;
import keelfyutils.registry.KItemRegistry;
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
	public static Item owl;

	private static String path = "drink";

	public static void init() {
		whiteHoney = KItemRegistry.of("whiteHoney".toUpperCase(), new Elexir(EnumRarity.RARE, EnumElexir.WhiteHoney).setAddInfo("Снимает все эффекты, снижает интоксикацию")).register(path);
		swallow = KItemRegistry.of("swallow".toUpperCase(), new Elexir(EnumRarity.RARE, EnumElexir.Swallow).setAddInfo("Восстановление здоровья")).register(path);
		grampus = KItemRegistry.of("grampus".toUpperCase(), new Elexir(EnumRarity.RARE, EnumElexir.Grampus).setAddInfo("Позволят на больший срок задерживать дыхание")).register(path);
		cat = KItemRegistry.of("cat".toUpperCase(), new Elexir(EnumRarity.RARE, EnumElexir.Cat).setAddInfo("Позволят лучше видеть в темноте")).register(path);
		thunder = KItemRegistry.of("thunder".toUpperCase(), new Elexir(EnumRarity.RARE, EnumElexir.Thunder).setAddInfo("Увеличивает наносимый урон")).register(path);
		owl = KItemRegistry.of("owl", new Elexir(EnumRarity.RARE, EnumElexir.Owl).setAddInfo("Ускоряет восстановление энергии на 5 минут")).register(path);
	}
}
