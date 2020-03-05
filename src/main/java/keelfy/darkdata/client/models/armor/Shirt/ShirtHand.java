/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.client.models.armor.Shirt;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkdata.client.models.DAModelBase;
import keelfy.darkdata.client.models.DAModelRenderer;

@SideOnly(Side.CLIENT)
public final class ShirtHand extends DAModelBase {

	private final DAModelRenderer Shape2;
	private final DAModelRenderer Shape3;
	private final DAModelRenderer Shape4;
	private final DAModelRenderer Shape5;
	private final DAModelRenderer Shape6;
	private final DAModelRenderer Shape11;

	public ShirtHand() {
		this.Shape2 = new DAModelRenderer(this, 46, 5);
		this.Shape2.addBox(0.0F, 0.0F, 0.0F, 4, 7, 1);
		this.Shape2.setRotationPoint(4.0F, 0.0F, -2.1F);
		this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
		this.Shape3 = new DAModelRenderer(this, 46, 0);
		this.Shape3.addBox(0.0F, 0.0F, 0.0F, 1, 7, 4);
		this.Shape3.setRotationPoint(7.1F, 0.0F, -2.0F);
		this.setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
		this.Shape4 = new DAModelRenderer(this, 46, 5);
		this.Shape4.addBox(0.0F, 0.0F, 0.0F, 4, 7, 1);
		this.Shape4.setRotationPoint(4.0F, 0.0F, 1.1F);
		this.setRotation(this.Shape4, 0.0F, 0.0F, 0.0F);
		this.Shape5 = new DAModelRenderer(this, 46, 0);
		this.Shape5.addBox(0.0F, 0.0F, 0.0F, 1, 7, 4);
		this.Shape5.setRotationPoint(3.9F, 0.0F, -2.0F);
		this.setRotation(this.Shape5, 0.0F, 0.0F, 0.0F);
		this.Shape6 = new DAModelRenderer(this, 48, 8);
		this.Shape6.addBox(0.0F, 0.0F, 0.0F, 4, 1, 4);
		this.Shape6.setRotationPoint(4.0F, -0.1F, -2.0F);
		this.setRotation(this.Shape6, 0.0F, 0.0F, 0.0F);
		this.Shape11 = new DAModelRenderer(this, 0, 13);
		this.Shape11.addBox(0.0F, 0.0F, 0.0F, 1, 1, 5);
		this.Shape11.setRotationPoint(4.0F, -0.4F, -2.5F);
		this.setRotation(this.Shape11, 0.0F, 0.0F, 0.0F);
	}

	@Override
	public final void renderAll() {
		this.Shape2.render();
		this.Shape3.render();
		this.Shape4.render();
		this.Shape5.render();
		this.Shape6.render();
		this.Shape11.render();
	}
}
