package gloomyfolken.hooklib.example;

public class AnnotationHooks {

	/**
	 * Цель: при каждом ресайзе окна выводить в консоль новый размер
	 */
	// @Hook
	// public static void resize(final Minecraft mc, final int x, final int y) {
	// System.out.println("Resize, x=" + x + ", y=" + y);
	// }

	/**
	 * Цель: уменьшить вдвое показатели брони у всех игроков. P.S: фордж
	 * перехватывает получение показателя брони, ну а мы перехватим перехватчик :D
	 */
	// @Hook(injectOnExit = true, returnCondition = ReturnCondition.ALWAYS)
	// public static int getTotalArmorValue(final ForgeHooks fh, final EntityPlayer
	// player,
	// @ReturnValue final int returnValue) {
	// return 0;
	// }

	/**
	 * Цель: запретить возможность телепортироваться в ад и обратно чаще, чем раз в
	 * пять секунд.
	 */
	// @Hook(returnCondition = ReturnCondition.ON_TRUE, intReturnConstant = 100)
	// public static boolean getPortalCooldown(final EntityPlayer player) {
	// return player.dimension == 0;
	// }

	/**
	 * Цель: уменьшить вдвое яркость сущностей, которые выше полутора блоков.
	 * Проверка на высоту в одном методе, пересчёт яркости - в другом.
	 */
	// @Hook(injectOnExit = true, returnCondition = ReturnCondition.ON_TRUE,
	// returnAnotherMethod = "getBrightness")
	// public static boolean getBrightnessForRender(final Entity entity, final float
	// f) {
	// return entity.height > 1.5f;
	// }
	//
	// public static int getBrightness(final Entity entity, final float f) {
	// int oldValue = 0;
	// int j = ((oldValue >> 20) & 15) / 2;
	// int k = ((oldValue >> 4) & 15) / 2;
	// return j << 20 | k << 4;
	// }
}
