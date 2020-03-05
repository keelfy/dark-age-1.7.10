/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.client.models;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

/**
 * @author keelfy
 * @created 13 июл. 2017 г.
 */
@SideOnly(Side.CLIENT)
public abstract class DAModelBase extends ModelBase {

	public DAModelBase() {
		this(64, 32);
	}

	public DAModelBase(final int textureWidth, final int textureHeight) {
		this.textureWidth = textureWidth;
		this.textureHeight = textureHeight;
	}

	public abstract void renderAll();

	protected final void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}
