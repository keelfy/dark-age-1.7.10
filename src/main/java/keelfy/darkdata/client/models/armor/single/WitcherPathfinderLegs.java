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
public final class WitcherPathfinderLegs extends DAModelBase {

	private final DAModelRenderer Shape1;
	private final DAModelRenderer Shape2;
	private final DAModelRenderer Shape3;
	private final DAModelRenderer Shape4;
	private final DAModelRenderer Shape5;
	private final DAModelRenderer Shape6;
	private final DAModelRenderer Shape7;

	public WitcherPathfinderLegs() {
		this.Shape1 = new DAModelRenderer(this, 22, 0);
		this.Shape1.addBox(0.0F, 0.0F, 0.0F, 1, 6, 4);
		this.Shape1.setRotationPoint(3.0F, 12.0F, -2.0F);
		this.setRotation(this.Shape1, 0.0F, 0.0F, -0.1047198F);
		this.Shape2 = new DAModelRenderer(this, 0, 0);
		this.Shape2.addBox(0.0F, 0.0F, 0.0F, 1, 3, 4);
		this.Shape2.setRotationPoint(3.4F, 17.8F, -2.0F);
		this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0698132F);
		this.Shape3 = new DAModelRenderer(this, 11, 0);
		this.Shape3.addBox(0.0F, 0.0F, 0.0F, 4, 6, 1);
		this.Shape3.setRotationPoint(0.0F, 12.0F, -2.0F);
		this.setRotation(this.Shape3, -0.1047198F, 0.0F, 0.0F);
		this.Shape4 = new DAModelRenderer(this, 0, 8);
		this.Shape4.addBox(0.0F, 0.0F, 0.0F, 4, 3, 1);
		this.Shape4.setRotationPoint(0.0F, 18.0F, -2.4F);
		this.setRotation(this.Shape4, 0.0698132F, 0.0F, 0.0F);
		this.Shape5 = new DAModelRenderer(this, 33, 0);
		this.Shape5.addBox(0.0F, 0.0F, 0.0F, 4, 6, 1);
		this.Shape5.setRotationPoint(0.0F, 12.0F, 1.0F);
		this.setRotation(this.Shape5, 0.1047198F, 0.0F, 0.0F);
		this.Shape6 = new DAModelRenderer(this, 0, 13);
		this.Shape6.addBox(0.0F, 0.0F, 0.0F, 4, 3, 1);
		this.Shape6.setRotationPoint(0.0F, 17.8F, 1.4F);
		this.setRotation(this.Shape6, -0.0698132F, 0.0F, 0.0F);
		this.Shape7 = new DAModelRenderer(this, 0, 19);
		this.Shape7.addBox(0.0F, 0.0F, 0.0F, 1, 9, 4);
		this.Shape7.setRotationPoint(0.0F, 12.0F, -2.0F);
		this.setRotation(this.Shape7, 0.0F, 0.0F, 0.0F);
	}

	@Override
	public final void renderAll() {
		this.Shape1.render();
		this.Shape2.render();
		this.Shape3.render();
		this.Shape4.render();
		this.Shape5.render();
		this.Shape6.render();
		this.Shape7.render();
	}
}
