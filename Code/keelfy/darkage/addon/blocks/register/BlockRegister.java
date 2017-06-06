package keelfy.darkage.addon.blocks.register;

import keelfy.api.registry.BlockRegistry;
import keelfy.darkage.addon.blocks.DABlocksAddon;
import keelfy.darkage.addon.blocks.block.BlockClassSelector;
import keelfy.darkage.addon.blocks.block.BlockLight;
import keelfy.darkage.addon.blocks.block.BlockLootBag;
import keelfy.darkage.addon.blocks.block.BlockSpecial;
import keelfy.darkage.addon.blocks.tileentity.TileEntityLootBag;
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
		light = BlockRegistry.of("Свет", new BlockLight(10000)).registerWithoutTexture().setBlockTextureName(DABlocksAddon.MODID + ":special/null").setLightLevel(3F);
		track = BlockRegistry.of("След", new BlockSpecial(Material.iron, 0)).registerWithoutTexture().setBlockTextureName(DABlocksAddon.MODID + ":special/null");
		smell = BlockRegistry.of("Запах", new BlockSpecial(Material.iron, 1)).registerWithoutTexture().setBlockTextureName(DABlocksAddon.MODID + ":special/null");
		lootBag = BlockRegistry.of("LootBag", new BlockLootBag()).register("LootBag", TileEntityLootBag.class);
	}	
}
