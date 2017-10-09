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
public final class BelhavenaBrigantineHand extends DAModelBase {

	private final DAModelRenderer Shape4;
	private final DAModelRenderer Shape5;
	private final DAModelRenderer Shape6;
	private final DAModelRenderer Shape7;
	private final DAModelRenderer Shape8;

	public BelhavenaBrigantineHand() {
		this.Shape4 = new DAModelRenderer(this, 0, 25);
		this.Shape4.addBox(0.0F, 0.0F, 0.0F, 4, 6, 1);
		this.Shape4.setRotationPoint(4.0F, 0.0F, -2.2F);
		this.setRotation(this.Shape4, 0.0174533F, 0.0F, 0.0F);
		this.Shape5 = new DAModelRenderer(this, 0, 14);
		this.Shape5.addBox(0.0F, 0.0F, 0.0F, 1, 6, 4);
		this.Shape5.setRotationPoint(7.2F, 0.0F, -2.0F);
		this.setRotation(this.Shape5, 0.0F, 0.0F, 0.0174533F);
		this.Shape6 = new DAModelRenderer(this, 0, 25);
		this.Shape6.addBox(0.0F, 0.0F, 0.0F, 4, 6, 1);
		this.Shape6.setRotationPoint(4.0F, 0.0F, 1.2F);
		this.setRotation(this.Shape6, -0.0174533F, 0.0F, 0.0F);
		this.Shape7 = new DAModelRenderer(this, 0, 14);
		this.Shape7.addBox(0.0F, 0.0F, 0.0F, 1, 6, 4);
		this.Shape7.setRotationPoint(3.8F, 0.0F, -2.0F);
		this.setRotation(this.Shape7, 0.0F, 0.0F, -0.0174533F);
		this.Shape8 = new DAModelRenderer(this, 27, 13);
		this.Shape8.addBox(0.0F, 0.0F, 0.0F, 4, 1, 4);
		this.Shape8.setRotationPoint(4.0F, -0.1F, -2.0F);
		this.setRotation(this.Shape8, 0.0F, 0.0F, 0.0F);
	}

	@Override
	public final void renderAll() {
		this.Shape4.render();
		this.Shape5.render();
		this.Shape6.render();
		this.Shape7.render();
		this.Shape8.render();
	}
}
