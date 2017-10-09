/*
 *  Copyright (c) 2016-2017, Rubedo
 */

package keelfy.fahrenheit.common.blocks;

import keelfy.fahrenheit.Fahrenheit;
import keelfy.fahrenheit.common.FTabs;
import keelfy.fahrenheit.common.tileentities.TileEntityCampfire;
import keelfyutils.KUtils;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * @author keelfy
 * @created 6 окт. 2017 г.
 */
public class BlockCampfire extends BlockContainer {

	public BlockCampfire() {
		super(Material.rock);

		this.setCreativeTab(FTabs.tabFahrenheit);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float subX, float subY, float subZ) {
		if (KUtils.PROTECT_SERVER) {
			if (!world.isRemote) {
				player.openGui(Fahrenheit.instance, 0, world, x, y, z);
			}
		}
		return super.onBlockActivated(world, x, y, z, player, meta, subX, subY, subZ);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityCampfire();
	}

}
