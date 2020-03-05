/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.client.models.armor.RedanianHalebardist;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkdata.client.models.DAModelBase;
import keelfy.darkdata.client.models.DAModelRenderer;

@SideOnly(Side.CLIENT)
public final class RedanianHalebardistLeg extends DAModelBase {

	private final DAModelRenderer Shape32;
	private final DAModelRenderer Shape33;
	private final DAModelRenderer Shape34;
	private final DAModelRenderer Shape35;

	public RedanianHalebardistLeg() {
		this.Shape32 = new DAModelRenderer(this, 54, 0);
		this.Shape32.addBox(0.0F, 0.0F, 0.0F, 1, 9, 4);
		this.Shape32.setRotationPoint(3.0F, 12.0F, -2.0F);
		this.setRotation(this.Shape32, 0.0F, 0.0F, -0.0523599F);
		this.Shape33 = new DAModelRenderer(this, 11, 26);
		this.Shape33.addBox(0.0F, 0.0F, 0.0F, 1, 2, 4);
		this.Shape33.setRotationPoint(3.6F, 21.0F, -2.0F);
		this.setRotation(this.Shape33, 0.0F, 0.0F, -0.0523599F);
		this.Shape34 = new DAModelRenderer(this, 54, 0);
		this.Shape34.addBox(0.0F, 0.0F, 0.0F, 4, 9, 1);
		this.Shape34.setRotationPoint(0.0F, 12.0F, 1.0F);
		this.setRotation(this.Shape34, 0.0523599F, 0.0F, 0.0F);
		this.Shape35 = new DAModelRenderer(this, 0, 29);
		this.Shape35.addBox(0.0F, 0.0F, 0.0F, 4, 2, 1);
		this.Shape35.setRotationPoint(0.0F, 21.0F, 1.6F);
		this.setRotation(this.Shape35, 0.0523599F, 0.0F, 0.0F);
	}

	@Override
	public final void renderAll() {
		this.Shape32.render();
		this.Shape33.render();
		this.Shape34.render();
		this.Shape35.render();
	}
}
