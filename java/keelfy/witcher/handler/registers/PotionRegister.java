package keelfy.witcher.handler.registers;

import keelfy.witcher.item.DAItem.ItemRarity;
import keelfy.api.registry.ItemRegistry;
import keelfy.witcher.item.Elexir;
import keelfy.witcher.item.Elexir.Effect;
import net.minecraft.item.Item;

/**
 * @author keelfy
 */
public class PotionRegister {
	public static Item whiteHoney;
	public static Item swallow;
	public static Item grampus;
	public static Item cat;
	public static Item thunder;

	private String path = "drink";
	public PotionRegister() {
		whiteHoney = ItemRegistry.of("whiteHoney".toUpperCase(), new Elexir(ItemRarity.RARE, Effect.БЕЛЫЙ_МЕД, "Снимает все эффекты, снижает интоксикацию")).register(path);
		swallow = ItemRegistry.of("swallow".toUpperCase(), new Elexir(ItemRarity.RARE, Effect.ЛАСТОЧКА, "Восстановление здоровья")).register(path);
		grampus = ItemRegistry.of("grampus".toUpperCase(), new Elexir(ItemRarity.RARE, Effect.КОСАТКА, "Позволят на больший срок задерживать дыхание")).register(path);
		cat = ItemRegistry.of("cat".toUpperCase(), new Elexir(ItemRarity.RARE, Effect.КОШКА, "Позволят лучше видеть в темноте")).register(path);
		thunder = ItemRegistry.of("thunder".toUpperCase(), new Elexir(ItemRarity.RARE, Effect.ГРОМ, "Увеличивает наносимый урон")).register(path);
	}
}
