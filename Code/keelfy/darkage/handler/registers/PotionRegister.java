package keelfy.darkage.handler.registers;

import keelfy.api.registry.ItemRegistry;
import keelfy.darkage.item.DAItem.ItemRarity;
import keelfy.darkage.item.Elexir;
import keelfy.darkage.item.Elexir.ElexirType;
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
		whiteHoney = ItemRegistry.of("whiteHoney".toUpperCase(), new Elexir(ItemRarity.RARE, ElexirType.БЕЛЫЙ_МЕД, "Снимает все эффекты, снижает интоксикацию")).register(path);
		swallow = ItemRegistry.of("swallow".toUpperCase(), new Elexir(ItemRarity.RARE, ElexirType.ЛАСТОЧКА, "Восстановление здоровья")).register(path);
		grampus = ItemRegistry.of("grampus".toUpperCase(), new Elexir(ItemRarity.RARE, ElexirType.КОСАТКА, "Позволят на больший срок задерживать дыхание")).register(path);
		cat = ItemRegistry.of("cat".toUpperCase(), new Elexir(ItemRarity.RARE, ElexirType.КОШКА, "Позволят лучше видеть в темноте")).register(path);
		thunder = ItemRegistry.of("thunder".toUpperCase(), new Elexir(ItemRarity.RARE, ElexirType.ГРОМ, "Увеличивает наносимый урон")).register(path);
	}
}
