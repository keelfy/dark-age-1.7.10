/*
 *  Copyright (c) 2016-2017, Rubedo
 */

package keelfy.fahrenheit.common;

import keelfy.fahrenheit.Fahrenheit;
import keelfy.fahrenheit.common.blocks.BlockBigCampfire;
import keelfy.fahrenheit.common.blocks.BlockCampfire;
import keelfy.fahrenheit.common.tileentities.TileEntityBigCampfire;
import keelfy.fahrenheit.common.tileentities.TileEntityCampfire;
import keelfyutils.registry.KBlockRegistry;
import keelfyutils.registry.KItemRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

/**
 * @author keelfy
 * @created 6 окт. 2017 г.
 */
public class FRegisterer {

	public static Block campfire;
	public static Block campfireBig;
	public static Block campfireSmall;

	public static Item gas;
	public static Item match;

	public static final void register() {
		KBlockRegistry.setResourceDomain(Fahrenheit.MOD_ID);
		KItemRegistry.setResourceDomain(Fahrenheit.MOD_ID);

		campfire = KBlockRegistry.of("campfireCamp", new BlockCampfire()).register(TileEntityCampfire.class);
		campfireBig = KBlockRegistry.of("campfireBig", new BlockBigCampfire()).register(TileEntityBigCampfire.class);

		gas = KItemRegistry.of("gas", (new Item()).setCreativeTab(FTabs.tabFahrenheit)).register();
		match = KItemRegistry.of("match", (new Item()).setCreativeTab(FTabs.tabFahrenheit)).register();
	}

}
