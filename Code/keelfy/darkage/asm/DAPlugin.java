/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.asm;

import gloomyfolken.hooklib.minecraft.HookLoader;
import keelfy.darkage.utils.DAUtils;

/**
 * @author keelfy
 * @created 3 июн. 2017 г.
 */
public class DAPlugin extends HookLoader {

	@Override
	protected void registerHooks() {
		registerHookContainer(DAUtils.HOOK_CONTAINER);
	}
}
