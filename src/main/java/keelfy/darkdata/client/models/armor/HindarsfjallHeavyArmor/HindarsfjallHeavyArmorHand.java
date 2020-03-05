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
public final class HindarsfjallHeavyArmorHand extends DAModelBase {

	private final DAModelRenderer Shape12;
	private final DAModelRenderer Shape13;
	private final DAModelRenderer Shape14;
	private final DAModelRenderer Shape15;
	private final DAModelRenderer Shape16;
	private final DAModelRenderer Shape17;
	private final DAModelRenderer Shape18;
	private final DAModelRenderer Shape36;

	public HindarsfjallHeavyArmorHand() {
		

		this.Shape12 = new DAModelRenderer(this, 54, 24);
		this.Shape12.addBox(0.0F, 0.0F, 0.0F, 4, 7, 1);
		this.Shape12.setRotationPoint(4.0F, 0.0F, -2.2F);
		this.setRotation(this.Shape12, 0.0F, 0.0F, 0.0F);
		this.Shape13 = new DAModelRenderer(this, 43, 21);
		this.Shape13.addBox(0.0F, 0.0F, 0.0F, 1, 7, 4);
		this.Shape13.setRotationPoint(7.2F, 0.0F, -2.0F);
		this.setRotation(this.Shape13, 0.0F, 0.0F, 0.0F);
		this.Shape14 = new DAModelRenderer(this, 54, 24);
		this.Shape14.addBox(0.0F, 0.0F, 0.0F, 4, 7, 1);
		this.Shape14.setRotationPoint(4.0F, 0.0F, 1.2F);
		this.setRotation(this.Shape14, 0.0F, 0.0F, 0.0F);
		this.Shape15 = new DAModelRenderer(this, 43, 21);
		this.Shape15.addBox(0.0F, 0.0F, 0.0F, 1, 7, 4);
		this.Shape15.setRotationPoint(3.8F, 0.0F, -2.0F);
		this.setRotation(this.Shape15, 0.0F, 0.0F, 0.0F);
		this.Shape16 = new DAModelRenderer(this, 30, 27);
		this.Shape16.addBox(0.0F, 0.0F, 0.0F, 2, 1, 4);
		this.Shape16.setRotationPoint(6.0F, -0.4F, -2.0F);
		this.setRotation(this.Shape16, 0.0F, 0.0F, 0.0F);
		this.Shape17 = new DAModelRenderer(this, 19, 26);
		this.Shape17.addBox(0.0F, 0.0F, 0.0F, 1, 2, 4);
		this.Shape17.setRotationPoint(7.3F, 0.0F, -2.0F);
		this.setRotation(this.Shape17, 0.0F, 0.0F, -0.0698132F);
		this.Shape18 = new DAModelRenderer(this, 19, 26);
		this.Shape18.addBox(0.0F, 0.0F, 0.0F, 1, 2, 4);
		this.Shape18.setRotationPoint(7.3F, 2.0F, -2.0F);
		this.setRotation(this.Shape18, 0.0F, 0.0F, -0.0698132F);
		this.Shape36 = new DAModelRenderer(this, 50, 15);
		this.Shape36.addBox(0.0F, 0.0F, 0.0F, 3, 1, 4);
		this.Shape36.setRotationPoint(4.0F, 0.0F, -2.0F);
		this.setRotation(this.Shape36, 0.0F, 0.0F, 0.0F);
	}

	@Override
	public final void renderAll() {
		this.Shape12.render();
		this.Shape13.render();
		this.Shape14.render();
		this.Shape15.render();
		this.Shape16.render();
		this.Shape17.render();
		this.Shape18.render();
		this.Shape36.render();
	}
}
