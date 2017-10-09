/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.client.models.armor.nilfgaard;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkdata.client.models.DAModelBase;
import keelfy.darkdata.client.models.DAModelRenderer;

@SideOnly(Side.CLIENT)
public final class NilfgaardAddon extends DAModelBase {

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
	private final DAModelRenderer Shape13;
	private final DAModelRenderer Shape14;
	private final DAModelRenderer Shape15;
	private final DAModelRenderer Shape16;
	private final DAModelRenderer Shape17;
	private final DAModelRenderer Shape25;

	public NilfgaardAddon() {
		this.Shape0 = new DAModelRenderer(this, 37, 0);
		this.Shape0.addBox(0.0F, 0.0F, 0.0F, 1, 2, 3);
		this.Shape0.setRotationPoint(4.0F, -2.0F, -1.5F);
		this.setRotation(this.Shape0, 0.0F, 0.0F, 0.0F);
		this.Shape1 = new DAModelRenderer(this, 40, 0);
		this.Shape1.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1);
		this.Shape1.setRotationPoint(4.0F, -2.0F, -1.5F);
		this.setRotation(this.Shape1, -0.7853982F, 0.0F, 0.0F);
		this.Shape2 = new DAModelRenderer(this, 38, 0);
		this.Shape2.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1);
		this.Shape2.setRotationPoint(4.0F, -1.3F, 0.8F);
		this.setRotation(this.Shape2, 0.7853982F, 0.0F, 0.0F);
		this.Shape3 = new DAModelRenderer(this, 38, 0);
		this.Shape3.addBox(0.0F, 0.0F, 0.0F, 2, 1, 5);
		this.Shape3.setRotationPoint(5.0F, -1.0F, -2.5F);
		this.setRotation(this.Shape3, 0.0F, 0.0F, 0.1396263F);
		this.Shape4 = new DAModelRenderer(this, 37, 0);
		this.Shape4.addBox(0.0F, 0.0F, 0.0F, 1, 3, 3);
		this.Shape4.setRotationPoint(4.1F, -1.6F, -1.5F);
		this.setRotation(this.Shape4, 0.0F, 0.0F, -0.3839724F);
		this.Shape5 = new DAModelRenderer(this, 36, 0);
		this.Shape5.addBox(0.0F, 0.0F, 0.0F, 2, 1, 5);
		this.Shape5.setRotationPoint(7.9F, -0.3F, -2.5F);
		this.setRotation(this.Shape5, 0.0F, 0.0F, 1.204277F);
		this.Shape6 = new DAModelRenderer(this, 37, 0);
		this.Shape6.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1);
		this.Shape6.setRotationPoint(4.4F, 0.0F, -3.0F);
		this.setRotation(this.Shape6, 0.0F, 0.0F, 0.2094395F);
		this.Shape7 = new DAModelRenderer(this, 36, 0);
		this.Shape7.addBox(0.0F, 0.0F, 0.0F, 1, 1, 5);
		this.Shape7.setRotationPoint(7.0F, -0.4F, -2.5F);
		this.setRotation(this.Shape7, 0.0F, 0.0F, 0.1396263F);
		this.Shape8 = new DAModelRenderer(this, 36, 0);
		this.Shape8.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1);
		this.Shape8.setRotationPoint(4.4F, 0.0F, 2.0F);
		this.setRotation(this.Shape8, 0.0F, 0.0F, 0.2094395F);
		this.Shape9 = new DAModelRenderer(this, 21, 4);
		this.Shape9.addBox(0.0F, 0.0F, 0.0F, 1, 2, 4);
		this.Shape9.setRotationPoint(7.3F, 1.7F, -2.0F);
		this.setRotation(this.Shape9, 0.0F, 0.0F, -0.122173F);
		this.Shape10 = new DAModelRenderer(this, 21, 4);
		this.Shape10.addBox(0.0F, 0.0F, 0.0F, 1, 2, 4);
		this.Shape10.setRotationPoint(7.3F, 3.8F, -2.0F);
		this.setRotation(this.Shape10, 0.0F, 0.0F, -0.122173F);
		this.Shape11 = new DAModelRenderer(this, 21, 4);
		this.Shape11.addBox(0.0F, 0.0F, 0.0F, 4, 2, 1);
		this.Shape11.setRotationPoint(4.0F, 1.7F, -2.0F);
		this.setRotation(this.Shape11, -0.2443461F, 0.0F, 0.0F);
		this.Shape12 = new DAModelRenderer(this, 21, 4);
		this.Shape12.addBox(0.0F, 0.0F, 0.0F, 4, 2, 1);
		this.Shape12.setRotationPoint(4.0F, 3.7F, -2.0F);
		this.setRotation(this.Shape12, -0.2443461F, 0.0F, 0.0F);
		this.Shape13 = new DAModelRenderer(this, 21, 4);
		this.Shape13.addBox(0.0F, 0.0F, 0.0F, 4, 2, 1);
		this.Shape13.setRotationPoint(4.0F, 1.8F, 1.0F);
		this.setRotation(this.Shape13, 0.2443461F, 0.0F, 0.0F);
		this.Shape14 = new DAModelRenderer(this, 21, 4);
		this.Shape14.addBox(0.0F, 0.0F, 0.0F, 4, 2, 1);
		this.Shape14.setRotationPoint(4.0F, 3.7F, 1.0F);
		this.setRotation(this.Shape14, 0.2443461F, 0.0F, 0.0F);
		this.Shape15 = new DAModelRenderer(this, 0, 30);
		this.Shape15.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1);
		this.Shape15.setRotationPoint(4.0F, 5.8F, -2.3F);
		this.setRotation(this.Shape15, 0.0F, 0.0F, 0.0F);
		this.Shape16 = new DAModelRenderer(this, 0, 27);
		this.Shape16.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4);
		this.Shape16.setRotationPoint(7.3F, 5.8F, -2.0F);
		this.setRotation(this.Shape16, 0.0F, 0.0F, 0.0F);
		this.Shape17 = new DAModelRenderer(this, 0, 30);
		this.Shape17.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1);
		this.Shape17.setRotationPoint(4.0F, 5.8F, 1.3F);
		this.setRotation(this.Shape17, 0.0F, 0.0F, 0.0F);
		this.Shape25 = new DAModelRenderer(this, 37, 6);
		this.Shape25.addBox(0.0F, 0.0F, 0.0F, 4, 2, 4);
		this.Shape25.setRotationPoint(4.0F, 6.5F, -2.0F);
		this.setRotation(this.Shape25, 0.0F, 0.0F, 0.0F);
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
		this.Shape13.render();
		this.Shape14.render();
		this.Shape15.render();
		this.Shape16.render();
		this.Shape17.render();
		this.Shape25.render();
	}

}
