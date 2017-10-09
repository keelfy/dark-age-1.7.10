/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.client.models.entity.sign;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkdata.client.models.DAModelBase;
import keelfy.darkdata.client.models.DAModelRenderer;

@SideOnly(Side.CLIENT)
public final class ModelSignIrden extends DAModelBase {

	private final DAModelRenderer Shape1;
	private final DAModelRenderer Shape2;
	private final DAModelRenderer Shape3;
	private final DAModelRenderer Shape4;
	private final DAModelRenderer Shape5;
	private final DAModelRenderer Shape6;

	public ModelSignIrden() {
		this.textureWidth = 32;

		this.Shape1 = new DAModelRenderer(this, -4, 0);
		this.Shape1.addBox(0.0F, 0.0F, 0.0F, 4, 1, 4);
		this.Shape1.setRotationPoint(-5.0F, 17.0F, -11.0F);
		this.Shape1.setTextureSize(32, 32);
		this.setRotation(this.Shape1, 0.0F, 0.4833219F, 0.0F);
		this.Shape2 = new DAModelRenderer(this, -4, 0);
		this.Shape2.addBox(0.0F, 0.0F, 0.0F, 4, 1, 4);
		this.Shape2.setRotationPoint(6.0F, 17.0F, -12.0F);
		this.Shape2.setTextureSize(32, 32);
		this.setRotation(this.Shape2, 0.0F, -0.4833166F, 0.0F);
		this.Shape3 = new DAModelRenderer(this, -4, 0);
		this.Shape3.addBox(0.0F, 0.0F, 0.0F, 4, 1, 4);
		this.Shape3.setRotationPoint(4.0F, 17.0F, 4.0F);
		this.Shape3.setTextureSize(32, 32);
		this.setRotation(this.Shape3, 0.0F, 0.4833166F, 0.0F);
		this.Shape4 = new DAModelRenderer(this, -4, 0);
		this.Shape4.addBox(0.0F, 0.0F, 0.0F, 4, 1, 4);
		this.Shape4.setRotationPoint(-4.0F, 17.0F, 2.0F);
		this.Shape4.setTextureSize(32, 32);
		this.setRotation(this.Shape4, 0.0F, -0.4833166F, 0.0F);
		this.Shape5 = new DAModelRenderer(this, -4, 0);
		this.Shape5.addBox(0.0F, 0.0F, 0.0F, 4, 1, 4);
		this.Shape5.setRotationPoint(-10.0F, 17.0F, 0.0F);
		this.Shape5.setTextureSize(32, 32);
		this.setRotation(this.Shape5, 0.0F, 1.570796F, 0.0F);
		this.Shape6 = new DAModelRenderer(this, -4, 0);
		this.Shape6.addBox(0.0F, 0.0F, 0.0F, 4, 1, 4);
		this.Shape6.setRotationPoint(8.0F, 17.0F, 0.0F);
		this.Shape6.setTextureSize(32, 32);
		this.setRotation(this.Shape6, 0.0F, 1.570796F, 0.0F);
	}

	@Override
	public final void renderAll() {
		this.Shape1.render();
		this.Shape2.render();
		this.Shape3.render();
		this.Shape4.render();
		this.Shape5.render();
		this.Shape6.render();
	}
}
