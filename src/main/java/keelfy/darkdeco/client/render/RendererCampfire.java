/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdeco.client.render;

import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTranslatef;

import org.lwjgl.opengl.*;

import cpw.mods.fml.relauncher.*;
import keelfy.darkdata.client.DAResources.*;
import keelfy.darkdeco.*;
import keelfyutils.client.*;
import net.minecraft.block.*;
import net.minecraft.client.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.tileentity.*;
import net.minecraft.tileentity.*;
import net.minecraft.util.*;
import net.minecraft.world.*;

/**
 * @author keelfy
 * @created 6 сент. 2017 г.
 */
@SideOnly(Side.CLIENT)
public class RendererCampfire extends TileEntitySpecialRenderer {

	private final DisplayListWrapper model;
	private final ResourceLocation textureLocation = new ResourceLocation(DarkDeco.MOD_ID, "blocks/textures/campfire.png");

	public RendererCampfire() {
		this.model = Model.getWithDL(DarkDeco.MOD_ID, "blocks/models", "campfire");
	}

	private void adjustRotatePivotViaMeta(World world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		glPushMatrix();
		glRotatef(meta * (-90), 0.0F, 0.0F, 1.0F);
		glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale) {
		
		glPushMatrix();
		glTranslatef((float) x + 0.5F, (float) y, (float) z - 0.17F);
		
		bindTexture(textureLocation);
		glPushMatrix();
		
//		int rotation = 0;
//		switch (te.getBlockMetadata()) {
//		case 0:
//			rotation = 270;
//			break;
//		case 1:
//			rotation = 180;
//			break;
//		case 2:
//			rotation = 90;
//			break;
//		case 3:
//			rotation = 0;
//			break;
//		}
		GL11.glScalef(0.015F, 0.015F, 0.015F);
//		GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
		
		Minecraft.getMinecraft().entityRenderer.disableLightmap(0);
		model.renderAll();
		Minecraft.getMinecraft().entityRenderer.enableLightmap(0);
		
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}

	private void adjustLightFixture(World world, int i, int j, int k, Block block) {
		Tessellator tess = Tessellator.instance;
		float brightness = block.getLightOpacity(world, i, j, k);
		int skyLight = world.getLightBrightnessForSkyBlocks(i, j, k, 0);
		int modulousModifier = skyLight % 65536;
		int divModifier = skyLight / 65536;
		tess.setColorOpaque_F(brightness, brightness, brightness);
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) modulousModifier, divModifier);
	}

}