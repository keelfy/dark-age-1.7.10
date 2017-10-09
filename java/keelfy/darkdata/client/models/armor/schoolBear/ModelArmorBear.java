/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.client.models.armor.schoolBear;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkdata.client.models.DAModelBase;
import keelfy.darkdata.client.models.DAModelRenderer;

@SideOnly(Side.CLIENT)
public final class ModelArmorBear extends DAModelBase {

	private final DAModelRenderer Shape1;
	private final DAModelRenderer Shape2;
	private final DAModelRenderer Shape3;
	private final DAModelRenderer Shape4;
	private final DAModelRenderer Shape5;
	private final DAModelRenderer Shape6;
	private final DAModelRenderer Shape8;
	private final DAModelRenderer Shape9;
	private final DAModelRenderer Shape10;
	private final DAModelRenderer Shape11;
	private final DAModelRenderer Shape12;
	private final DAModelRenderer Shape13;
	private final DAModelRenderer Shape14;
	private final DAModelRenderer Shape15;
	private final DAModelRenderer Shape16;
	private final DAModelRenderer Shape33;
	private final DAModelRenderer Shape34;

	public ModelArmorBear() {
		this.Shape1 = new DAModelRenderer(this, 46, 8);
		this.Shape1.addBox(0.0F, 0.0F, 0.0F, 8, 6, 1);
		this.Shape1.setRotationPoint(-4.0F, 0.0F, -2.6F);
		this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
		this.Shape2 = new DAModelRenderer(this, 46, 0);
		this.Shape2.addBox(0.0F, 0.0F, 0.0F, 8, 6, 1);
		this.Shape2.setRotationPoint(-4.0F, 6.0F, -2.4F);
		this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
		this.Shape3 = new DAModelRenderer(this, 46, 8);
		this.Shape3.addBox(0.0F, 0.0F, 0.0F, 8, 6, 1);
		this.Shape3.setRotationPoint(-4.0F, 0.0F, 1.6F);
		this.setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
		this.Shape4 = new DAModelRenderer(this, 46, 0);
		this.Shape4.addBox(0.0F, 0.0F, 0.0F, 8, 6, 1);
		this.Shape4.setRotationPoint(-4.0F, 6.0F, 1.3F);
		this.setRotation(this.Shape4, 0.0F, 0.0F, 0.0F);
		this.Shape5 = new DAModelRenderer(this, 14, 5);
		this.Shape5.addBox(0.0F, 0.0F, 0.0F, 1, 6, 1);
		this.Shape5.setRotationPoint(-2.6F, 6.0F, -2.5F);
		this.setRotation(this.Shape5, 0.0F, 0.0F, -0.0698132F);
		this.Shape6 = new DAModelRenderer(this, 7, 10);
		this.Shape6.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1);
		this.Shape6.setRotationPoint(-3.1F, 8.0F, -2.6F);
		this.setRotation(this.Shape6, 0.0174533F, 0.0F, 0.1047198F);
		this.Shape8 = new DAModelRenderer(this, 49, -3);
		this.Shape8.addBox(0.0F, 0.0F, 0.0F, 1, 6, 4);
		this.Shape8.setRotationPoint(-4.0F, 6.0F, -2.0F);
		this.setRotation(this.Shape8, 0.0F, 0.0F, 0.0F);
		this.Shape9 = new DAModelRenderer(this, 53, -3);
		this.Shape9.addBox(0.0F, 0.0F, 0.0F, 1, 6, 4);
		this.Shape9.setRotationPoint(3.0F, 6.0F, -2.0F);
		this.setRotation(this.Shape9, 0.0F, 0.0F, 0.0F);
		this.Shape10 = new DAModelRenderer(this, 0, 6);
		this.Shape10.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1);
		this.Shape10.setRotationPoint(-1.5F, 1.0F, -2.8F);
		this.setRotation(this.Shape10, 0.0F, 0.0F, 0.0F);
		this.Shape11 = new DAModelRenderer(this, 0, 6);
		this.Shape11.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1);
		this.Shape11.setRotationPoint(-1.5F, 3.0F, -2.8F);
		this.setRotation(this.Shape11, 0.0F, 0.0F, 0.0F);
		this.Shape12 = new DAModelRenderer(this, 0, 13);
		this.Shape12.addBox(0.0F, 0.0F, 0.0F, 5, 1, 5);
		this.Shape12.setRotationPoint(-4.2F, 9.5F, -2.6F);
		this.setRotation(this.Shape12, 0.0F, 0.0F, 0.0F);
		this.Shape13 = new DAModelRenderer(this, 0, 13);
		this.Shape13.addBox(0.0F, 0.0F, 0.0F, 5, 1, 5);
		this.Shape13.setRotationPoint(-0.7F, 9.5F, -2.6F);
		this.setRotation(this.Shape13, 0.0F, 0.0F, 0.0F);
		this.Shape14 = new DAModelRenderer(this, 0, 13);
		this.Shape14.addBox(0.0F, 0.0F, 0.0F, 7, 1, 5);
		this.Shape14.setRotationPoint(-4.4F, 11.0F, -2.6F);
		this.setRotation(this.Shape14, 0.0F, 0.0F, -0.2094395F);
		this.Shape15 = new DAModelRenderer(this, 5, 15);
		this.Shape15.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1);
		this.Shape15.setRotationPoint(1.0F, 10.4F, -2.6F);
		this.setRotation(this.Shape15, 0.0F, 0.0F, -0.122173F);
		this.Shape16 = new DAModelRenderer(this, 5, 15);
		this.Shape16.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1);
		this.Shape16.setRotationPoint(0.3F, 9.8F, -2.7F);
		this.setRotation(this.Shape16, 0.0F, 0.0F, -0.5410521F);
		this.Shape33 = new DAModelRenderer(this, 35, 5);
		this.Shape33.addBox(0.0F, 0.0F, 0.0F, 1, 6, 4);
		this.Shape33.setRotationPoint(-4.0F, 0.0F, -2.0F);
		this.setRotation(this.Shape33, 0.0F, 0.0F, 0.0F);
		this.Shape34 = new DAModelRenderer(this, 35, 5);
		this.Shape34.addBox(0.0F, 0.0F, 0.0F, 1, 6, 4);
		this.Shape34.setRotationPoint(3.0F, 0.0F, -2.0F);
		this.setRotation(this.Shape34, 0.0F, 0.0F, 0.0F);

	}

	@Override
	public final void renderAll() {
		this.Shape1.render();
		this.Shape2.render();
		this.Shape3.render();
		this.Shape4.render();
		this.Shape5.render();
		this.Shape6.render();
		this.Shape8.render();
		this.Shape9.render();
		this.Shape10.render();
		this.Shape11.render();
		this.Shape12.render();
		this.Shape13.render();
		this.Shape14.render();
		this.Shape15.render();
		this.Shape16.render();
		this.Shape33.render();
		this.Shape34.render();
	}
}
