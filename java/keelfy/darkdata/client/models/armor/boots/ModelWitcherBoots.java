/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.client.models.armor.boots;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkdata.client.models.DAModelBase;
import keelfy.darkdata.client.models.DAModelRenderer;

@SideOnly(Side.CLIENT)
public final class ModelWitcherBoots extends DAModelBase {

	private final DAModelRenderer Shape1;
	private final DAModelRenderer Shape2;
	private final DAModelRenderer Shape3;
	private final DAModelRenderer Shape4;
	private final DAModelRenderer Shape5;
	private final DAModelRenderer Shape6;
	private final DAModelRenderer Shape7;

	public ModelWitcherBoots() {
		this.Shape1 = new DAModelRenderer(this, 0, 0);
		this.Shape1.addBox(0.0F, 0.0F, 0.0F, 5, 8, 5);
		this.Shape1.setRotationPoint(-0.5F, 16.0F, -2.5F);
		this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
		this.Shape2 = new DAModelRenderer(this, 0, 23);
		this.Shape2.addBox(0.0F, 0.0F, 0.0F, 2, 1, 3);
		this.Shape2.setRotationPoint(-2.2F, 23.0F, -1.5F);
		this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
		this.Shape3 = new DAModelRenderer(this, 0, 20);
		this.Shape3.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1);
		this.Shape3.setRotationPoint(-2.18F, 23.0F, -1.45F);
		this.setRotation(this.Shape3, 0.0F, 0.5690393F, 0.0F);
		this.Shape4 = new DAModelRenderer(this, 0, 20);
		this.Shape4.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1);
		this.Shape4.setRotationPoint(-1.7F, 23.0F, 0.6F);
		this.setRotation(this.Shape4, 0.0F, -0.5515939F, 0.0F);
		this.Shape5 = new DAModelRenderer(this, 0, 15);
		this.Shape5.addBox(0.0F, 0.0F, 0.0F, 3, 1, 3);
		this.Shape5.setRotationPoint(-2.2F, 23.0F, -1.5F);
		this.setRotation(this.Shape5, 0.0F, 0.0F, -0.7610255F);
		this.Shape6 = new DAModelRenderer(this, 21, 0);
		this.Shape6.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1);
		this.Shape6.setRotationPoint(1.0F, 17.0F, -3.0F);
		this.setRotation(this.Shape6, 0.0F, 0.0F, 0.0F);
		this.Shape7 = new DAModelRenderer(this, 21, 0);
		this.Shape7.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1);
		this.Shape7.setRotationPoint(1.0F, 19.0F, -3.0F);
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
