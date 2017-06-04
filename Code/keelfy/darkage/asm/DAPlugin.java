package keelfy.darkage.asm;

import gloomyfolken.hooklib.minecraft.HookLoader;
import keelfy.darkage.util.DAUtil;

/**
 * @author keelfy
 * @created 3 июн. 2017 г.
 */
public class DAPlugin extends HookLoader {

	@Override
	protected void registerHooks() {
		registerHookContainer(DAUtil.HOOK_CONTAINER);
	}
}
