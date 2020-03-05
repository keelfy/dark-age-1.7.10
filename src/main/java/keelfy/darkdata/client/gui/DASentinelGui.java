/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.client.gui;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkcore.network.DACNetwork;
import keelfy.darkdata.blocks.BlockSentinel.TileEntitySentinel;
import keelfy.darkdata.network.EnumSPackets;
import keelfyutils.KUtils;
import keelfyutils.blocks.KBlocks;
import keelfyutils.blocks.Point3D;
import keelfyutils.client.KGui;
import keelfyutils.str.Brush;
import keelfyutils.str.KString;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.tileentity.TileEntity;

/**
 * @author keelfy
 * @created 12 июл. 2017 г.
 */
@SideOnly(Side.CLIENT)
public final class DASentinelGui extends DAScreenGui {

	private GuiTextField radius;
	private double radiusValue;

	private GuiTextField factionId;
	private int factionIdValue;

	private GuiButton buttonDone;

	private Point3D blockPos;

	private TileEntitySentinel sentinel;

	private boolean error;

	public DASentinelGui(final Point3D pos) {
		if (KUtils.PROTECT_CLIENT) {
			this.blockPos = pos;
			this.error = false;
		}
	}

	@Override
	public final void initGui() {
		if (KUtils.PROTECT_CLIENT) {
			super.initGui();

			addTextField(radius = new GuiTextField(fontRendererObj, 50, 150, 20, 18), 3);
			addTextField(factionId = new GuiTextField(fontRendererObj, 50, 100, 20, 18), 3);

			final TileEntity te = KBlocks.getTileEntity(mc.theWorld, blockPos);

			this.buttonList.add(buttonDone = new GuiButton(1, width - 80, height - 80, 50, 18, "Готово"));
		}
	}

	@Override
	protected final void drawForeground(final int mouseX, final int mouseY, final float partialTicks) {
		if (KUtils.PROTECT_CLIENT) {
			GL11.glPushMatrix();
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			fontRendererObj.drawString(Brush.YELLOW + "= Радиус для реакции", 75, 154, Integer.MAX_VALUE);
			fontRendererObj.drawString(Brush.YELLOW + "= ID фракции, за которую блок отвечает", 75, 104,
					Integer.MAX_VALUE);
			if (error) {
				fontRendererObj.drawString(Brush.RED + "Введены некорректные данные!", 75, 20, Integer.MAX_VALUE);
			}
			GL11.glPopMatrix();
			;
		}
	}

	@Override
	public final void updateScreen() {
		if (KUtils.PROTECT_CLIENT) {
			super.updateScreen();

			final String s1 = radius.getText().trim();
			final String s2 = factionId.getText().trim();

			try {
				if (!KString.isEmpty(s1)) {
					radiusValue = Double.parseDouble(s1);
				} else {
					radiusValue = 0;
				}

				if (!KString.isEmpty(s2)) {
					factionIdValue = Integer.parseInt(s2);
				} else {
					factionIdValue = -1;
				}
				error = false;
			} catch (final NumberFormatException e) {
				error = true;
			}
		}
	}

	@Override
	protected final void actionPerformed(final GuiButton button) {
		if (KUtils.PROTECT_CLIENT) {
			switch (button.id) {
			case 1:
				if (!error) {
					DACNetwork.sendToServer(EnumSPackets.EditSentinel, blockPos, radiusValue, factionIdValue);
					KGui.closeGui();
				}
				break;
			}
		}
	}

	@Override
	protected final boolean hasBlackBackground() {
		return true;
	}
}
