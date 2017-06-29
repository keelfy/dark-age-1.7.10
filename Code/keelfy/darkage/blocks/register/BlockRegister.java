/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.blocks.register;

import keelfy.darkage.blocks.block.BlockClassSelector;
import keelfy.darkage.blocks.block.BlockLight;
import keelfy.darkage.blocks.block.BlockLootBag;
import keelfy.darkage.blocks.block.BlockSpecial;
import keelfy.darkage.blocks.tileentity.TileEntityLootBag;
import keelfy.darkage.utils.DAUtils;
import keelfytools.registry.BlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * @author keelfy
 */
public enum BlockRegister {
	Instance;

	public static Block classSelector;
	public static Block light;
	public static Block track;
	public static Block smell;
	public static Block lootBag;

	public final void init() {
		new LightingRegister("lighting");

		classSelector = BlockRegistry.of("blockClassSelector", new BlockClassSelector()).register();
		light = BlockRegistry.of("Свет", new BlockLight(10000)).registerWithoutTexture()
				.setBlockTextureName(DAUtils.MODID + ":special/null").setLightLevel(3F);
		track = BlockRegistry.of("След", new BlockSpecial(Material.iron, 0)).registerWithoutTexture()
				.setBlockTextureName(DAUtils.MODID + ":special/null");
		smell = BlockRegistry.of("Запах", new BlockSpecial(Material.iron, 1)).registerWithoutTexture()
				.setBlockTextureName(DAUtils.MODID + ":special/null");
		lootBag = BlockRegistry.of("LootBag", new BlockLootBag()).register("LootBag", TileEntityLootBag.class);
	}
}
