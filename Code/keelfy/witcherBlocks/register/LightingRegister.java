package keelfy.witcherBlocks.register;

import keelfy.api.registry.BlockRegistry;
import keelfy.witcherBlocks.block.BlockLightMushroom;
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
