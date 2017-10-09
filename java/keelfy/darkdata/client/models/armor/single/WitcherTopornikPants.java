/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.client.models.armor.single;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkdata.client.models.DAModelBase;
import keelfy.darkdata.client.models.DAModelRenderer;

@SideOnly(Side.CLIENT)
public final class WitcherTopornikPants extends DAModelBase {

	private final DAModelRenderer Shape0;
	private final DAModelRenderer Shape1;
	private final DAModelRenderer Shape2;
	private final DAModelRenderer Shape3;
	private final DAModelRenderer Shape4;
	private final DAModelRenderer Shape5;
	private final DAModelRenderer Shape6;
	private final DAModelRenderer Shape7;
	private final DAModelRenderer Shape8;
	private final DAModelRenderer Shape9;
	private final DAModelRenderer Shape10;
	private final DAModelRenderer Shape11;
	private final DAModelRenderer Shape12;

	public WitcherTopornikPants() {
		this.Shape0 = new DAModelRenderer(this, 0, 16);
		this.Shape0.addBox(0.0F, 0.0F, 0.0F, 4, 8, 1);
		this.Shape0.setRotationPoint(5.0E-11F, 14.0F, -2.2F);
		this.setRotation(this.Shape0, 0.0F, 0.0F, 0.0F);
		this.Shape1 = new DAModelRenderer(this, 0, 12);
		this.Shape1.addBox(0.0F, 0.0F, 0.0F, 1, 10, 3);
		this.Shape1.setRotationPoint(3.2F, 12.0F, -2.2F);
		this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
		this.Shape2 = new DAModelRenderer(this, 0, 0);
		this.Shape2.addBox(0.0F, 0.0F, 0.0F, 4, 8, 1);
		this.Shape2.setRotationPoint(5.0E-11F, 14.0F, 1.2F);
		this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
		this.Shape3 = new DAModelRenderer(this, 0, 12);
		this.Shape3.addBox(0.0F, 0.0F, 0.0F, 1, 8, 3);
		this.Shape3.setRotationPoint(-0.1F, 14.0F, -2.2F);
		this.setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
		this.Shape4 = new DAModelRenderer(this, 0, 12);
		this.Shape4.addBox(0.0F, 0.0F, 0.0F, 1, 10, 3);
		this.Shape4.setRotationPoint(3.2F, 12.0F, -0.8F);
		this.setRotation(this.Shape4, 0.0F, 0.0F, 0.0F);
		this.Shape5 = new DAModelRenderer(this, 0, 12);
		this.Shape5.addBox(0.0F, 0.0F, 0.0F, 1, 8, 3);
		this.Shape5.setRotationPoint(-0.1F, 14.0F, -0.8F);
		this.setRotation(this.Shape5, 0.0F, 0.0F, 0.0F);
		this.Shape6 = new DAModelRenderer(this, 0, 29);
		this.Shape6.addBox(0.0F, 0.0F, 0.0F, 4, 2, 1);
		this.Shape6.setRotationPoint(5.0E-11F, 12.0F, -2.1F);
		this.setRotation(this.Shape6, 0.0F, 0.0F, 0.0F);
		this.Shape7 = new DAModelRenderer(this, 0, 29);
		this.Shape7.addBox(0.0F, 0.0F, 0.0F, 4, 2, 1);
		this.Shape7.setRotationPoint(5.0E-11F, 12.0F, 1.1F);
		this.setRotation(this.Shape7, 0.0F, 0.0F, 0.0F);
		this.Shape8 = new DAModelRenderer(this, 0, 26);
		this.Shape8.addBox(0.0F, 0.0F, 0.0F, 1, 2, 4);
		this.Shape8.setRotationPoint(-0.1F, 12.0F, -2.0F);
		this.setRotation(this.Shape8, 0.0F, 0.0F, 0.0F);
		this.Shape9 = new DAModelRenderer(this, 11, 0);
		this.Shape9.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1);
		this.Shape9.setRotationPoint(-0.1F, 13.5F, -2.2F);
		this.setRotation(this.Shape9, 0.0F, 0.0F, -0.3839724F);
		this.Shape10 = new DAModelRenderer(this, 11, 0);
		this.Shape10.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1);
		this.Shape10.setRotationPoint(1.1F, 13.0F, -2.2F);
		this.setRotation(this.Shape10, 0.0F, 0.0F, 0.0F);
		this.Shape11 = new DAModelRenderer(this, 11, 0);
		this.Shape11.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1);
		this.Shape11.setRotationPoint(-0.1F, 13.5F, 1.2F);
		this.setRotation(this.Shape11, 0.0F, 0.0F, -0.3839724F);
		this.Shape12 = new DAModelRenderer(this, 11, 0);
		this.Shape12.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1);
		this.Shape12.setRotationPoint(1.1F, 13.0F, 1.2F);
		this.setRotation(this.Shape12, 0.0F, 0.0F, 0.0F);
	}

	@Override
	public final void renderAll() {
		this.Shape0.render();
		this.Shape1.render();
		this.Shape2.render();
		this.Shape3.render();
		this.Shape4.render();
		this.Shape5.render();
		this.Shape6.render();
		this.Shape7.render();
		this.Shape8.render();
		this.Shape9.render();
		this.Shape10.render();
		this.Shape11.render();
		this.Shape12.render();
	}
}
