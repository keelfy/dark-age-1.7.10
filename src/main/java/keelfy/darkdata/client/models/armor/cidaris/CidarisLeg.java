/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.client.models.armor.cidaris;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkdata.client.models.DAModelBase;
import keelfy.darkdata.client.models.DAModelRenderer;

@SideOnly(Side.CLIENT)
public final class CidarisLeg extends DAModelBase {

	private final DAModelRenderer Shape1;
	private final DAModelRenderer Shape2;
	private final DAModelRenderer Shape3;

	public CidarisLeg() {
		this.Shape1 = new DAModelRenderer(this, 54, 0);
		this.Shape1.addBox(0.0F, 0.0F, 0.0F, 1, 4, 4);
		this.Shape1.setRotationPoint(3.0F, 12.0F, -2.0F);
		this.setRotation(this.Shape1, 0.0F, 0.0F, -0.0523599F);
		this.Shape2 = new DAModelRenderer(this, 54, 0);
		this.Shape2.addBox(0.0F, 0.0F, 0.0F, 4, 4, 1);
		this.Shape2.setRotationPoint(0.0F, 12.0F, 1.0F);
		this.setRotation(this.Shape2, 0.0523599F, 0.0F, 0.0F);
		this.Shape3 = new DAModelRenderer(this, 54, 0);
		this.Shape3.addBox(0.0F, 0.0F, 0.0F, 3, 4, 1);
		this.Shape3.setRotationPoint(1.0F, 12.0F, -2.5F);
		this.setRotation(this.Shape3, -0.0523599F, 0.0F, 0.0F);
	}

	@Override
	public final void renderAll() {
		this.Shape1.render();
		this.Shape2.render();
		this.Shape3.render();
	}
}
