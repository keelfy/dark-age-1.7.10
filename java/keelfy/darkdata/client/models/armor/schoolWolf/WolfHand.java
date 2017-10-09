/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.client.models.armor.schoolWolf;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkdata.client.models.DAModelBase;
import keelfy.darkdata.client.models.DAModelRenderer;

@SideOnly(Side.CLIENT)
public final class WolfHand extends DAModelBase {

	private final DAModelRenderer Shape9;
	private final DAModelRenderer Shape10;
	private final DAModelRenderer Shape11;
	private final DAModelRenderer Shape12;
	private final DAModelRenderer Shape48;

	public WolfHand() {
		this.Shape9 = new DAModelRenderer(this, 36, 4);
		this.Shape9.addBox(0.0F, 0.0F, 0.0F, 4, 6, 1);
		this.Shape9.setRotationPoint(4.0F, 0.0F, -2.1F);
		this.setRotation(this.Shape9, 0.0F, 0.0F, 0.0F);
		this.Shape10 = new DAModelRenderer(this, 36, 4);
		this.Shape10.addBox(0.0F, 0.0F, 0.0F, 1, 6, 4);
		this.Shape10.setRotationPoint(7.1F, 0.0F, -2.0F);
		this.setRotation(this.Shape10, 0.0F, 0.0F, 0.0F);
		this.Shape11 = new DAModelRenderer(this, 36, 4);
		this.Shape11.addBox(0.0F, 0.0F, 0.0F, 4, 6, 1);
		this.Shape11.setRotationPoint(4.0F, 0.0F, 1.1F);
		this.setRotation(this.Shape11, 0.0F, 0.0F, 0.0F);
		this.Shape12 = new DAModelRenderer(this, 36, 4);
		this.Shape12.addBox(0.0F, 0.0F, 0.0F, 1, 6, 4);
		this.Shape12.setRotationPoint(3.9F, 0.0F, -2.0F);
		this.setRotation(this.Shape12, 0.0F, 0.0F, 0.0F);
		this.Shape48 = new DAModelRenderer(this, 36, 4);
		this.Shape48.addBox(0.0F, 0.0F, 0.0F, 4, 1, 4);
		this.Shape48.setRotationPoint(4.0F, 0.0F, -2.0F);
		this.setRotation(this.Shape48, 0.0F, 0.0F, 0.0F);
	}

	@Override
	public final void renderAll() {
		this.Shape9.render();
		this.Shape10.render();
		this.Shape11.render();
		this.Shape12.render();
		this.Shape48.render();
	}
}
