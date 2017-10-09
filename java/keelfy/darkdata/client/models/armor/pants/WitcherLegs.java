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
public final class WitcherLegs extends DAModelBase {

	private final DAModelRenderer leftleg;
	private final DAModelRenderer rightleg;

	public WitcherLegs() {
		this.leftleg = new DAModelRenderer(this, 0, 16);
		this.leftleg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4);
		this.leftleg.setRotationPoint(2.0F, 12.0F, 0.0F);
		this.leftleg.setTextureSize(64, 32);
		this.leftleg.mirror = true;
		this.setRotation(this.leftleg, 0.0F, 0.0F, 0.0F);
		this.rightleg = new DAModelRenderer(this, 0, 16);
		this.rightleg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4);
		this.rightleg.setRotationPoint(-2.0F, 12.0F, 0.0F);
		this.rightleg.setTextureSize(64, 32);
		this.rightleg.mirror = true;
		this.setRotation(this.rightleg, 0.0F, 0.0F, 0.0F);
	}

	public final void renderLeftLeg() {
		this.leftleg.render(0.0625F);
	}

	public final void renderRightLeg() {
		this.rightleg.render(0.0625F);
	}

	@Override
	public void renderAll() {}
}
