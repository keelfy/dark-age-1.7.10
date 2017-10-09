/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.client.models.armor.kaermorhen;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkdata.client.models.DAModelBase;
import keelfy.darkdata.client.models.DAModelRenderer;

@SideOnly(Side.CLIENT)
public final class KaerMorhenArmor extends DAModelBase {

	private final DAModelRenderer Shape0;
	private final DAModelRenderer Shape1;
	private final DAModelRenderer Shape2;
	private final DAModelRenderer Shape3;
	private final DAModelRenderer Shape4;
	private final DAModelRenderer Shape13;
	private final DAModelRenderer Shape14;
	private final DAModelRenderer Shape15;
	private final DAModelRenderer Shape16;

	public KaerMorhenArmor() {
		this.Shape0 = new DAModelRenderer(this, 0, 25);
		this.Shape0.addBox(0.0F, 0.0F, 0.0F, 4, 6, 1);
		this.Shape0.setRotationPoint(-2.0F, 6.0F, -2.3F);
		this.setRotation(this.Shape0, 0.0F, 0.0F, 0.0F);
		this.Shape1 = new DAModelRenderer(this, 0, 12);
		this.Shape1.addBox(0.0F, 0.0F, 0.0F, 2, 6, 5);
		this.Shape1.setRotationPoint(2.0F, 6.0F, -2.5F);
		this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
		this.Shape2 = new DAModelRenderer(this, 0, 12);
		this.Shape2.addBox(0.0F, 0.0F, 0.0F, 2, 6, 5);
		this.Shape2.setRotationPoint(-4.0F, 6.0F, -2.5F);
		this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
		this.Shape3 = new DAModelRenderer(this, 38, 0);
		this.Shape3.addBox(0.0F, 0.0F, 0.0F, 8, 6, 5);
		this.Shape3.setRotationPoint(-4.0F, 0.0F, -2.5F);
		this.setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
		this.Shape4 = new DAModelRenderer(this, 0, 25);
		this.Shape4.addBox(0.0F, 0.0F, 0.0F, 4, 6, 1);
		this.Shape4.setRotationPoint(-2.0F, 6.0F, 1.1F);
		this.setRotation(this.Shape4, 0.0F, 0.0F, 0.0F);
		this.Shape13 = new DAModelRenderer(this, 15, 15);
		this.Shape13.addBox(0.0F, 0.0F, 0.0F, 10, 1, 1);
		this.Shape13.setRotationPoint(-4.0F, 0.0F, -2.8F);
		this.setRotation(this.Shape13, 0.0F, 0.0F, 0.5576792F);
		this.Shape14 = new DAModelRenderer(this, 15, 15);
		this.Shape14.addBox(0.0F, 0.0F, 0.0F, 10, 1, 1);
		this.Shape14.setRotationPoint(4.4F, 0.7F, -2.8F);
		this.setRotation(this.Shape14, 0.0F, 0.0F, 2.585049F);
		this.Shape15 = new DAModelRenderer(this, 11, 26);
		this.Shape15.addBox(0.0F, 0.0F, 0.0F, 9, 1, 5);
		this.Shape15.setRotationPoint(-4.5F, 10.3F, -2.8F);
		this.setRotation(this.Shape15, 0.0F, 0.0F, 0.0F);
		this.Shape16 = new DAModelRenderer(this, 0, 5);
		this.Shape16.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1);
		this.Shape16.setRotationPoint(-1.0F, 10.0F, -2.9F);
		this.setRotation(this.Shape16, 0.0F, 0.0F, 0.0F);
	}

	@Override
	public final void renderAll() {
		this.Shape0.render();
		this.Shape1.render();
		this.Shape2.render();
		this.Shape3.render();
		this.Shape4.render();
		this.Shape13.render();
		this.Shape14.render();
		this.Shape15.render();
		this.Shape16.render();
	}
}
