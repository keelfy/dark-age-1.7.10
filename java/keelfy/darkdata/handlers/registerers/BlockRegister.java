/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.handlers.registerers;

import keelfy.darkdata.DarkData;
import keelfy.darkdata.blocks.BlockLight;
import keelfy.darkdata.blocks.BlockLootBag;
import keelfy.darkdata.blocks.BlockSentinel;
import keelfy.darkdata.blocks.BlockSpecial;
import keelfy.darkdata.blocks.DABlock;
import keelfy.darkdata.blocks.BlockSentinel.TileEntitySentinel;
import keelfy.darkdata.blocks.tileentities.TileEntityLoot;
import keelfy.darkdata.constants.EnumGui;
import keelfyutils.registry.KBlockRegistry;
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
	public static Block sentinel;

	public final void init() {
		new LightingRegister("lighting");

		classSelector = KBlockRegistry.of("blockClassSelector", new DABlock(Material.iron, false) {
			@Override
			public EnumGui getGuiToOpen() {
				return EnumGui.SelectClass;
			}
		}).register();

		light = KBlockRegistry.of("Свет", new BlockLight()).registerWithoutTexture()
				.setBlockTextureName(DarkData.MOD_ID + ":special/null").setLightLevel(3F);

		track = KBlockRegistry.of("След", new BlockSpecial(Material.iron, 0)).registerWithoutTexture()
				.setBlockTextureName(DarkData.MOD_ID + ":special/null");
		smell = KBlockRegistry.of("Запах", new BlockSpecial(Material.iron, 1)).registerWithoutTexture()
				.setBlockTextureName(DarkData.MOD_ID + ":special/null");
		lootBag = KBlockRegistry.of("LootBag", new BlockLootBag()).register("LootBag", TileEntityLoot.class);

		sentinel = KBlockRegistry.of("Sentinel", new BlockSentinel()).register(TileEntitySentinel.class);
	}
}
