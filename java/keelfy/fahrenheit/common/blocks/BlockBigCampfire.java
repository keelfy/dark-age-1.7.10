/*
 *  Copyright (c) 2016-2017, Rubedo
 */

package keelfy.fahrenheit.common.blocks;

import keelfy.fahrenheit.common.FTabs;
import keelfy.fahrenheit.common.tileentities.TileEntityBigCampfire;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * @author keelfy
 * @created 4 окт. 2017 г.
 */
public class BlockBigCampfire extends BlockContainer {

	public BlockBigCampfire() {
		super(Material.rock);

		this.setCreativeTab(FTabs.tabFahrenheit);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityBigCampfire(5D, 3F);
	}

}
