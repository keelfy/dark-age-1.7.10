/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.handlers.registerers;

import keelfy.darkdata.blocks.BlockLightMushroom;
import keelfyutils.registry.KBlockRegistry;
import net.minecraft.block.Block;

/**
 * @author keelfy
 */
public class LightingRegister {
	
	public static Block lightMushroom1, lightMushroom2;
	
	public LightingRegister(String path) {
		lightMushroom1 = KBlockRegistry.of("LightMushroom1", new BlockLightMushroom()).register(path);
		lightMushroom2 = KBlockRegistry.of("LightMushroom2", new BlockLightMushroom()).register(path);
	}
}
