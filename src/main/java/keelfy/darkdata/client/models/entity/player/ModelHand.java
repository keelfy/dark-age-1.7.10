/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.client.models.entity.player;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkdata.client.models.DAModelBase;
import keelfy.darkdata.client.models.DAModelRenderer;

@SideOnly(Side.CLIENT)
public final class ModelHand extends DAModelBase {

	private final DAModelRenderer rightarm;
	private final DAModelRenderer leftarm;

	public ModelHand() {

		this.rightarm = new DAModelRenderer(this, 40, 16);
		this.rightarm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4);
		this.rightarm.setRotationPoint(-5.0F, 2.0F, 0.0F);
		this.rightarm.setTextureSize(64, 32);
		this.rightarm.mirror = true;
		this.setRotation(this.rightarm, -1.570796F, 0.0F, 0.0F);
		this.leftarm = new DAModelRenderer(this, 40, 16);
		this.leftarm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4);
		this.leftarm.setRotationPoint(5.0F, 2.0F, 0.0F);
		this.leftarm.setTextureSize(64, 32);
		this.leftarm.mirror = true;
		this.setRotation(this.leftarm, -1.570796F, 0.0F, 0.0F);
	}

	public final void renderLeft() {
		this.leftarm.render(0.0525F);
	}

	public final void renderRight() {
		this.rightarm.render(0.0625F);
	}

	@Override
	public final void renderAll() {}
}
