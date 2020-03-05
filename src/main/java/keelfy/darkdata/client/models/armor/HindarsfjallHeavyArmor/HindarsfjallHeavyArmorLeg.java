/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.client.models.armor.HindarsfjallHeavyArmor;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkdata.client.models.DAModelBase;
import keelfy.darkdata.client.models.DAModelRenderer;

@SideOnly(Side.CLIENT)
public final class HindarsfjallHeavyArmorLeg extends DAModelBase {

	private final DAModelRenderer Shape5;
	private final DAModelRenderer Shape6;
	private final DAModelRenderer Shape7;
	private final DAModelRenderer Shape8;
	private final DAModelRenderer Shape9;
	private final DAModelRenderer Shape10;
	private final DAModelRenderer Shape11;

	public HindarsfjallHeavyArmorLeg() {
		

		this.Shape5 = new DAModelRenderer(this, 12, 19);
		this.Shape5.addBox(0.0F, 0.0F, 0.0F, 1, 5, 1);
		this.Shape5.setRotationPoint(0.0F, 12.0F, -2.2F);
		this.setRotation(this.Shape5, -0.0698132F, 0.0F, 0.0F);
		this.Shape6 = new DAModelRenderer(this, 18, 19);
		this.Shape6.addBox(0.0F, 0.0F, 0.0F, 3, 5, 1);
		this.Shape6.setRotationPoint(1.0F, 12.0F, -2.4F);
		this.setRotation(this.Shape6, -0.0698132F, 0.0F, 0.0F);
		this.Shape7 = new DAModelRenderer(this, 0, 9);
		this.Shape7.addBox(0.0F, 0.0F, 0.0F, 4, 2, 1);
		this.Shape7.setRotationPoint(0.0F, 17.0F, -2.6F);
		this.setRotation(this.Shape7, -0.0698132F, 0.0F, 0.0F);
		this.Shape8 = new DAModelRenderer(this, 28, 16);
		this.Shape8.addBox(0.0F, 0.0F, 0.0F, 1, 5, 4);
		this.Shape8.setRotationPoint(3.1F, 12.0F, -2.0F);
		this.setRotation(this.Shape8, 0.0F, 0.0F, -0.0698132F);
		this.Shape9 = new DAModelRenderer(this, 39, 14);
		this.Shape9.addBox(0.0F, 0.0F, 0.0F, 1, 2, 4);
		this.Shape9.setRotationPoint(3.4F, 17.0F, -2.0F);
		this.setRotation(this.Shape9, 0.0F, 0.0F, -0.0698132F);
		this.Shape10 = new DAModelRenderer(this, 17, 19);
		this.Shape10.addBox(0.0F, 0.0F, 0.0F, 4, 5, 1);
		this.Shape10.setRotationPoint(0.0F, 12.0F, 1.4F);
		this.setRotation(this.Shape10, 0.0698132F, 0.0F, 0.0F);
		this.Shape11 = new DAModelRenderer(this, 0, 9);
		this.Shape11.addBox(0.0F, 0.0F, 0.0F, 4, 2, 1);
		this.Shape11.setRotationPoint(0.0F, 17.0F, 1.6F);
		this.setRotation(this.Shape11, 0.0698132F, 0.0F, 0.0F);
	}

	@Override
	public final void renderAll() {
		this.Shape5.render();
		this.Shape6.render();
		this.Shape7.render();
		this.Shape8.render();
		this.Shape9.render();
		this.Shape10.render();
		this.Shape11.render();
	}
}
