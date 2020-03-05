/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.asm;

import gloomyfolken.hooklib.minecraft.HookLoader;
import gloomyfolken.hooklib.minecraft.PrimaryClassTransformer;

/**
 * @author keelfy
 * @created 3 июн. 2017 г.
 */
public final class DAPlugin extends HookLoader {

	@Override
	public String[] getASMTransformerClass() {
		return new String[] { PrimaryClassTransformer.class.getName() };
	}

	@Override
	protected void registerHooks() {
		registerHookContainer("keelfy.darkdata.asm.DATransformer");
	}
}
