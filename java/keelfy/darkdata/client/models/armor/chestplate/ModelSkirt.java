/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.client.models.armor.chestplate;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkdata.client.models.DAModelBase;
import keelfy.darkdata.client.models.DAModelRenderer;

@SideOnly(Side.CLIENT)
public final class ModelSkirt extends DAModelBase {

	private final DAModelRenderer Shape19;
	private final DAModelRenderer Shape21;
	private final DAModelRenderer Shape18;

	public ModelSkirt() {
		this.Shape19 = new DAModelRenderer(this, 35, 4);
		this.Shape19.addBox(0.0F, 0.0F, 0.0F, 1, 7, 4);
		this.Shape19.setRotationPoint(3.0F, 12.0F, -2.0F);
		this.setRotation(this.Shape19, 0.0F, 0.0F, -0.0743572F);
		this.Shape21 = new DAModelRenderer(this, 46, 24);
		this.Shape21.addBox(0.0F, 0.0F, 0.0F, 4, 7, 1);
		this.Shape21.setRotationPoint(0.0F, 12.0F, 1.0F);
		this.setRotation(this.Shape21, 0.1487144F, 0.0F, 0.0F);
		this.Shape18 = new DAModelRenderer(this, 35, 4);
		this.Shape18.addBox(0.0F, 0.0F, 0.0F, 1, 7, 4);
		this.Shape18.setRotationPoint(0.0F, 12.0F, -2.0F);
		this.setRotation(this.Shape18, 0.0F, 0.0F, 0.0F);
	}

	@Override
	public final void renderAll() {
		this.Shape19.render();
		this.Shape21.render();
		this.Shape18.render();
	}
}
