/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.client.models.armor.BelhavenaBrigantine;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkdata.client.models.DAModelBase;
import keelfy.darkdata.client.models.DAModelRenderer;

@SideOnly(Side.CLIENT)
public final class BelhavenaBrigantineBody extends DAModelBase {

	private final DAModelRenderer Shape0;
	private final DAModelRenderer Shape9;
	private final DAModelRenderer Shape10;
	private final DAModelRenderer Shape11;
	private final DAModelRenderer Shape12;
	private final DAModelRenderer Shape13;
	private final DAModelRenderer Shape14;
	private final DAModelRenderer Shape15;
	private final DAModelRenderer Shape16;
	private final DAModelRenderer Shape17;
	private final DAModelRenderer Shape18;
	private final DAModelRenderer Shape19;
	private final DAModelRenderer Shape20;
	private final DAModelRenderer Shape21;

	public BelhavenaBrigantineBody() {
		this.Shape0 = new DAModelRenderer(this, 46, 0);
		this.Shape0.addBox(0.0F, 0.0F, 0.0F, 8, 11, 1);
		this.Shape0.setRotationPoint(-4.0F, 0.0F, -2.2F);
		this.setRotation(this.Shape0, 0.0174533F, 0.0F, 0.0F);
		this.Shape9 = new DAModelRenderer(this, 27, 0);
		this.Shape9.addBox(0.0F, 0.0F, 0.0F, 8, 11, 1);
		this.Shape9.setRotationPoint(-4.0F, 0.0F, 1.2F);
		this.setRotation(this.Shape9, -0.0174533F, 0.0F, 0.0F);
		this.Shape10 = new DAModelRenderer(this, 16, 0);
		this.Shape10.addBox(0.0F, 0.0F, 0.0F, 1, 11, 4);
		this.Shape10.setRotationPoint(-4.0F, 0.0F, -2.0F);
		this.setRotation(this.Shape10, 0.0F, 0.0F, 0.0F);
		this.Shape11 = new DAModelRenderer(this, 16, 0);
		this.Shape11.addBox(0.0F, 0.0F, 0.0F, 1, 11, 4);
		this.Shape11.setRotationPoint(3.0F, 0.0F, -2.0F);
		this.setRotation(this.Shape11, 0.0F, 0.0F, 0.0F);
		this.Shape12 = new DAModelRenderer(this, 44, 13);
		this.Shape12.addBox(0.0F, 0.0F, 0.0F, 6, 1, 4);
		this.Shape12.setRotationPoint(-3.0F, 0.0F, -2.0F);
		this.setRotation(this.Shape12, 0.0F, 0.0F, 0.0F);
		this.Shape13 = new DAModelRenderer(this, 0, 10);
		this.Shape13.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1);
		this.Shape13.setRotationPoint(0.3F, 11.0F, -2.5F);
		this.setRotation(this.Shape13, 0.0F, 0.0F, 0.0F);
		this.Shape14 = new DAModelRenderer(this, 0, 10);
		this.Shape14.addBox(0.0F, 0.0F, 0.0F, 5, 1, 1);
		this.Shape14.setRotationPoint(-4.3F, 11.0F, -2.5F);
		this.setRotation(this.Shape14, 0.0F, 0.0F, 0.0F);
		this.Shape15 = new DAModelRenderer(this, 0, 10);
		this.Shape15.addBox(0.0F, 0.0F, 0.0F, 5, 1, 1);
		this.Shape15.setRotationPoint(-4.3F, 11.0F, 1.5F);
		this.setRotation(this.Shape15, 0.0F, 0.0F, 0.0F);
		this.Shape16 = new DAModelRenderer(this, 0, 10);
		this.Shape16.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1);
		this.Shape16.setRotationPoint(0.3F, 11.0F, 1.5F);
		this.setRotation(this.Shape16, 0.0F, 0.0F, 0.0F);
		this.Shape17 = new DAModelRenderer(this, 0, 7);
		this.Shape17.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4);
		this.Shape17.setRotationPoint(-4.3F, 11.0F, -2.0F);
		this.setRotation(this.Shape17, 0.0F, 0.0F, 0.0F);
		this.Shape18 = new DAModelRenderer(this, 0, 7);
		this.Shape18.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4);
		this.Shape18.setRotationPoint(3.3F, 11.0F, -2.0F);
		this.setRotation(this.Shape18, 0.0F, 0.0F, 0.0F);
		this.Shape19 = new DAModelRenderer(this, 0, 10);
		this.Shape19.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1);
		this.Shape19.setRotationPoint(0.0F, 12.0F, -2.5F);
		this.setRotation(this.Shape19, -0.0698132F, 0.0F, 0.0F);
		this.Shape20 = new DAModelRenderer(this, 0, 10);
		this.Shape20.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1);
		this.Shape20.setRotationPoint(0.0F, 12.5F, -2.5F);
		this.setRotation(this.Shape20, 0.0F, 0.0F, -0.6981317F);
		this.Shape21 = new DAModelRenderer(this, 0, 10);
		this.Shape21.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1);
		this.Shape21.setRotationPoint(0.0F, 11.1F, -2.5F);
		this.setRotation(this.Shape21, 0.0F, 0.0F, -0.2268928F);
	}

	@Override
	public final void renderAll() {
		this.Shape0.render();
		this.Shape9.render();
		this.Shape10.render();
		this.Shape11.render();
		this.Shape12.render();
		this.Shape13.render();
		this.Shape14.render();
		this.Shape15.render();
		this.Shape16.render();
		this.Shape17.render();
		this.Shape18.render();
		this.Shape19.render();
		this.Shape20.render();
		this.Shape21.render();
	}
}
