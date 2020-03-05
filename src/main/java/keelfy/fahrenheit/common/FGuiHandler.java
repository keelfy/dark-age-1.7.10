/*
 *  Copyright (c) 2016-2017, Rubedo
 */

package keelfy.fahrenheit.common;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import keelfy.fahrenheit.Fahrenheit;
import keelfy.fahrenheit.client.gui.FCampfireGui;
import keelfy.fahrenheit.common.containers.ContainerCampfire;
import keelfy.fahrenheit.common.tileentities.TileEntityCampfire;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * @author keelfy
 * @created 6 окт. 2017 г.
 */
public class FGuiHandler implements IGuiHandler {

	public static final void register() {
		NetworkRegistry.INSTANCE.registerGuiHandler(Fahrenheit.instance, new FGuiHandler());
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
		case 0:
			return new ContainerCampfire(player.inventory, (TileEntityCampfire) world.getTileEntity(x, y, z));
		}

		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
		case 0:
			return new FCampfireGui(player.inventory, (TileEntityCampfire) world.getTileEntity(x, y, z));

		}

		return null;
	}

}
