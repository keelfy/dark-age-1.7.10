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
public final class ShirtAddon extends DAModelBase {

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

	public ShirtAddon() {
		this.Shape12 = new DAModelRenderer(this, 0, 20);
		this.Shape12.addBox(0.0F, 0.0F, 0.0F, 1, 4, 1);
		this.Shape12.setRotationPoint(3.0F, 0.0F, -2.2F);
		this.setRotation(this.Shape12, 0.0F, 0.0F, 0.0698132F);
		this.Shape13 = new DAModelRenderer(this, 0, 20);
		this.Shape13.addBox(0.0F, 0.0F, 0.0F, 1, 11, 1);
		this.Shape13.setRotationPoint(2.9F, 3.4F, -2.2F);
		this.setRotation(this.Shape13, 0.0F, 0.0F, 0.715585F);
		this.Shape14 = new DAModelRenderer(this, 0, 20);
		this.Shape14.addBox(0.0F, 0.0F, 0.0F, 1, 4, 1);
		this.Shape14.setRotationPoint(-4.0F, 0.0F, -2.2F);
		this.setRotation(this.Shape14, 0.0F, 0.0F, -0.0698132F);
		this.Shape15 = new DAModelRenderer(this, 0, 20);
		this.Shape15.addBox(0.0F, 0.0F, 0.0F, 1, 11, 1);
		this.Shape15.setRotationPoint(-3.6F, 4.0F, -2.2F);
		this.setRotation(this.Shape15, 0.0F, 0.0F, -0.7190757F);
		this.Shape16 = new DAModelRenderer(this, 0, 20);
		this.Shape16.addBox(0.0F, 0.0F, 0.0F, 1, 4, 1);
		this.Shape16.setRotationPoint(3.0F, 0.0F, 1.2F);
		this.setRotation(this.Shape16, 0.0F, 0.0F, 0.0698132F);
		this.Shape17 = new DAModelRenderer(this, 0, 20);
		this.Shape17.addBox(0.0F, 0.0F, 0.0F, 1, 11, 1);
		this.Shape17.setRotationPoint(2.9F, 3.4F, 1.2F);
		this.setRotation(this.Shape17, 0.0F, 0.0F, 0.715585F);
		this.Shape18 = new DAModelRenderer(this, 0, 20);
		this.Shape18.addBox(0.0F, 0.0F, 0.0F, 1, 11, 1);
		this.Shape18.setRotationPoint(-3.6F, 4.0F, 1.2F);
		this.setRotation(this.Shape18, 0.0F, 0.0F, -0.7190757F);
		this.Shape19 = new DAModelRenderer(this, 0, 20);
		this.Shape19.addBox(0.0F, 0.0F, 0.0F, 1, 4, 1);
		this.Shape19.setRotationPoint(-4.0F, 0.0F, 1.2F);
		this.setRotation(this.Shape19, 0.0F, 0.0F, -0.0698132F);
		this.Shape20 = new DAModelRenderer(this, 5, 23);
		this.Shape20.addBox(0.0F, 0.0F, 0.0F, 1, 1, 3);
		this.Shape20.setRotationPoint(-3.7F, 11.0F, -2.2F);
		this.setRotation(this.Shape20, 0.0F, 0.0F, 0.715585F);
		this.Shape21 = new DAModelRenderer(this, 5, 23);
		this.Shape21.addBox(0.0F, 0.0F, 0.0F, 1, 1, 3);
		this.Shape21.setRotationPoint(-3.7F, 11.0F, -0.8F);
		this.setRotation(this.Shape21, 0.0F, 0.0F, 0.715585F);
		this.Shape22 = new DAModelRenderer(this, 5, 23);
		this.Shape22.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4);
		this.Shape22.setRotationPoint(3.0F, 11.6F, -2.0F);
		this.setRotation(this.Shape22, 0.0F, 0.0F, -0.715585F);
	}

	public final void renderAll() {
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
	}
}
