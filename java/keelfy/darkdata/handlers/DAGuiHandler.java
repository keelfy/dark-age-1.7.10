/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.handlers;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkcore.common.player.DADataManager;
import keelfy.darkcore.network.DACNetwork;
import keelfy.darkdata.DarkData;
import keelfy.darkdata.blocks.tileentities.TileEntityLoot;
import keelfy.darkdata.client.gui.DAInventoryGui;
import keelfy.darkdata.client.gui.DALootBagGui;
import keelfy.darkdata.client.gui.DASelectClassGui;
import keelfy.darkdata.client.gui.DASelectSignGui;
import keelfy.darkdata.client.gui.DASentinelGui;
import keelfy.darkdata.client.gui.DASkillsGui;
import keelfy.darkdata.client.gui.DAStoryBookGui;
import keelfy.darkdata.constants.EnumGui;
import keelfy.darkdata.inventory.loot.ContainerLoot;
import keelfy.darkdata.inventory.player.DAContainerPlayerInventory;
import keelfy.darkdata.network.EnumSPackets;
import keelfyutils.KUtils;
import keelfyutils.blocks.Point3D;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import noppes.npcs.client.gui.player.GuiFaction;
import noppes.npcs.client.gui.player.GuiQuestLog;

/**
 * @author keelfy
 */
public enum DAGuiHandler implements IGuiHandler {
	Instance;

	public final void register() {
		NetworkRegistry.INSTANCE.registerGuiHandler(DarkData.instance, this);
	}

	@Override
	public Object getServerGuiElement(final int ID, final EntityPlayer player, final World world, final int x, final int y, final int z) {
		if (KUtils.PROTECT_SERVER) {
			final EnumGui selectedGui = EnumGui.values()[ID];

			switch (selectedGui) {
			case Inventory:
				return new DAContainerPlayerInventory(player, DADataManager.getPlayer(player).inventory);
			case LootBag:
				return new ContainerLoot((TileEntityLoot) world.getTileEntity(x, y, z));
			default:
				break;

			}
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(final int ID, final EntityPlayer player, final World world, final int x, final int y, final int z) {
		if (KUtils.PROTECT_CLIENT) {
			final EnumGui selectedGui = EnumGui.values()[ID];

			switch (selectedGui) {
			case Inventory:
				return new DAInventoryGui(player, DADataManager.getPlayer(player).inventory);
			case LootBag:
				return new DALootBagGui((TileEntityLoot) world.getTileEntity(x, y, z));
			default:
				break;
			}
		}
		return null;
	}

	@SideOnly(Side.CLIENT)
	public static final void openGui(final EnumGui gui, final Object... objects) {
		final Minecraft mc = Minecraft.getMinecraft();

		switch (gui) {
		case Factions:
			mc.displayGuiScreen(new GuiFaction());
			break;
		case Quests:
			mc.displayGuiScreen(new GuiQuestLog());
			break;
		case SelectClass:
			mc.displayGuiScreen(new DASelectClassGui());
			break;
		case Sentinel:
			mc.displayGuiScreen(new DASentinelGui((Point3D) objects[0]));
			break;
		case StoryBook:
			mc.displayGuiScreen(new DAStoryBookGui());
			break;
		case SelectSign:
			mc.displayGuiScreen(new DASelectSignGui());
			break;
		case Skills:
			mc.displayGuiScreen(new DASkillsGui());
			break;
		default:
			DACNetwork.sendToServer(EnumSPackets.OpenGui, gui);
			break;

		}
	}
}
