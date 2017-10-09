/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.client.models.armor.pants;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkdata.client.models.DAModelBase;
import keelfy.darkdata.client.models.DAModelRenderer;

@SideOnly(Side.CLIENT)
public final class ModelWitcherPants extends DAModelBase {

	private final DAModelRenderer leg;

	public ModelWitcherPants() {

		this.leg = new DAModelRenderer(this, 17, 20);
		this.leg.addBox(0.0F, 0.0F, 0.0F, 4, 8, 4);
		this.leg.setRotationPoint(0.0F, 12.0F, -2.0F);
		this.leg.setTextureSize(64, 32);
		this.leg.mirror = true;
		this.setRotation(this.leg, 0.0F, 0.0F, 0.0F);
	}

	@Override
	public final void renderAll() {
		this.leg.render();
	}
}
