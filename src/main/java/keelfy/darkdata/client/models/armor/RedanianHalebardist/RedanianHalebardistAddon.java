/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.client.models.armor.RedanianHalebardist;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkdata.client.models.DAModelBase;
import keelfy.darkdata.client.models.DAModelRenderer;

@SideOnly(Side.CLIENT)
public final class RedanianHalebardistAddon extends DAModelBase {

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
	private final DAModelRenderer Shape22;
	private final DAModelRenderer Shape23;

	public RedanianHalebardistAddon() {
		this.Shape11 = new DAModelRenderer(this, 20, 11);
		this.Shape11.addBox(0.0F, 0.0F, 0.0F, 4, 1, 4);
		this.Shape11.setRotationPoint(4.0F, -0.5F, -2.0F);
		this.setRotation(this.Shape11, 0.0F, 0.0F, 0.0F);
		this.Shape12 = new DAModelRenderer(this, 9, 7);
		this.Shape12.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4);
		this.Shape12.setRotationPoint(7.0F, 0.0F, -2.0F);
		this.setRotation(this.Shape12, 0.0F, 0.0F, -0.2443461F);
		this.Shape13 = new DAModelRenderer(this, 9, 7);
		this.Shape13.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4);
		this.Shape13.setRotationPoint(7.0F, 1.0F, -2.0F);
		this.setRotation(this.Shape13, 0.0F, 0.0F, -0.2443461F);
		this.Shape14 = new DAModelRenderer(this, 9, 7);
		this.Shape14.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4);
		this.Shape14.setRotationPoint(7.0F, 2.0F, -2.0F);
		this.setRotation(this.Shape14, 0.0F, 0.0F, -0.2443461F);
		this.Shape15 = new DAModelRenderer(this, 9, 7);
		this.Shape15.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4);
		this.Shape15.setRotationPoint(7.0F, 3.0F, -2.0F);
		this.setRotation(this.Shape15, 0.0F, 0.0F, -0.2443461F);
		this.Shape16 = new DAModelRenderer(this, 0, 10);
		this.Shape16.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1);
		this.Shape16.setRotationPoint(5.0F, -0.1F, -2.0F);
		this.setRotation(this.Shape16, -0.2443461F, 0.0F, 0.0F);
		this.Shape17 = new DAModelRenderer(this, 0, 10);
		this.Shape17.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1);
		this.Shape17.setRotationPoint(5.0F, 0.9F, -2.0F);
		this.setRotation(this.Shape17, -0.2443461F, 0.0F, 0.0F);
		this.Shape18 = new DAModelRenderer(this, 0, 10);
		this.Shape18.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1);
		this.Shape18.setRotationPoint(5.0F, 1.9F, -2.0F);
		this.setRotation(this.Shape18, -0.2443461F, 0.0F, 0.0F);
		this.Shape19 = new DAModelRenderer(this, 0, 10);
		this.Shape19.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1);
		this.Shape19.setRotationPoint(5.0F, 2.9F, -2.0F);
		this.setRotation(this.Shape19, -0.2443461F, 0.0F, 0.0F);
		this.Shape20 = new DAModelRenderer(this, 0, 10);
		this.Shape20.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1);
		this.Shape20.setRotationPoint(5.0F, -0.1F, 1.0F);
		this.setRotation(this.Shape20, 0.2443461F, 0.0F, 0.0F);
		this.Shape21 = new DAModelRenderer(this, 0, 10);
		this.Shape21.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1);
		this.Shape21.setRotationPoint(5.0F, 0.9F, 1.0F);
		this.setRotation(this.Shape21, 0.2443461F, 0.0F, 0.0F);
		this.Shape22 = new DAModelRenderer(this, 0, 10);
		this.Shape22.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1);
		this.Shape22.setRotationPoint(5.0F, 1.9F, 1.0F);
		this.setRotation(this.Shape22, 0.2443461F, 0.0F, 0.0F);
		this.Shape23 = new DAModelRenderer(this, 0, 10);
		this.Shape23.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1);
		this.Shape23.setRotationPoint(5.0F, 2.9F, 1.0F);
		this.setRotation(this.Shape23, 0.2443461F, 0.0F, 0.0F);
	}

	@Override
	public final void renderAll() {
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
		this.Shape22.render();
		this.Shape23.render();
	}
}
