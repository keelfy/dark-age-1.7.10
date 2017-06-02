package keelfy.witcherBlocks.register;

import keelfy.api.registry.BlockRegistry;
import keelfy.witcherBlocks.WitcherBlocks;
import keelfy.witcherBlocks.block.BlockClassSelector;
import keelfy.witcherBlocks.block.BlockLight;
import keelfy.witcherBlocks.block.BlockLootBag;
import keelfy.witcherBlocks.block.BlockSpecial;
import keelfy.witcherBlocks.tileentity.TileEntityLootBag;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * @author keelfy
 */
public class BlockRegister {
	public static Block classSelector;
	public static Block light;
	public static Block track;
	public static Block smell;
	public static Block lootBag;
	
	public BlockRegister() {
		new LightingRegister("lighting");
		
		classSelector = BlockRegistry.of("blockClassSelector", new BlockClassSelector()).register();
		light = BlockRegistry.of("Свет", new BlockLight(10000)).registerWithoutTexture().setBlockTextureName(WitcherBlocks.MODID + ":special/null").setLightLevel(3F);
		track = BlockRegistry.of("След", new BlockSpecial(Material.iron, 0)).registerWithoutTexture().setBlockTextureName(WitcherBlocks.MODID + ":special/null");
		smell = BlockRegistry.of("Запах", new BlockSpecial(Material.iron, 1)).registerWithoutTexture().setBlockTextureName(WitcherBlocks.MODID + ":special/null");
		lootBag = BlockRegistry.of("LootBag", new BlockLootBag()).register("LootBag", TileEntityLootBag.class);
	}	
}
