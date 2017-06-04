package keelfy.darkage.asm;

import gloomyfolken.hooklib.asm.Hook;
import gloomyfolken.hooklib.asm.ReturnCondition;

/**
 * @author keelfy
 * @created 3 июн. 2017 г.
 */
public class DATransformer {
	
	@Hook(injectOnExit = true, targetMethod = "net.minecraft.entity.player.InventoryPlayer.getTotalArmorValue", returnCondition = ReturnCondition.ALWAYS)
    public static int getTotalArmorValue() {
        return 0;
    }
}
