/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.blocks.register;

import keelfy.darkage.blocks.block.BlockLightMushroom;
import keelfytools.registry.BlockRegistry;
import net.minecraft.block.Block;

/**
 * @author keelfy
 */
public class LightingRegister {
	
	public static Block lightMushroom1, lightMushroom2;
	
	public LightingRegister(String path) {
		lightMushroom1 = BlockRegistry.of("LightMushroom1", new BlockLightMushroom()).register(path);
		lightMushroom2 = BlockRegistry.of("LightMushroom2", new BlockLightMushroom()).register(path);
	}
}
