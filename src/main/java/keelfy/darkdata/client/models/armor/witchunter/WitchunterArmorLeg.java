/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.client.models.armor.witchunter;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkdata.client.models.DAModelBase;
import keelfy.darkdata.client.models.DAModelRenderer;

@SideOnly(Side.CLIENT)
public final class WitchunterArmorLeg extends DAModelBase {

	private final DAModelRenderer Shape33;
	private final DAModelRenderer Shape34;
	private final DAModelRenderer Shape35;

	public WitchunterArmorLeg() {
		this.Shape33 = new DAModelRenderer(this, 11, 22);
		this.Shape33.addBox(0.0F, 0.0F, 0.0F, 4, 9, 1);
		this.Shape33.setRotationPoint(-4.0F, 12.0F, 1.4F);
		this.setRotation(this.Shape33, 0.0872665F, 0.0F, 0.0F);
		this.Shape34 = new DAModelRenderer(this, 11, 19);
		this.Shape34.addBox(0.0F, 0.0F, 0.0F, 1, 9, 4);
		this.Shape34.setRotationPoint(-4.1F, 12.0F, -2.0F);
		this.setRotation(this.Shape34, 0.0F, 0.0F, 0.0872665F);
		this.Shape35 = new DAModelRenderer(this, 22, 22);
		this.Shape35.addBox(0.0F, 0.0F, 0.0F, 2, 9, 1);
		this.Shape35.setRotationPoint(-4.0F, 12.0F, -2.4F);
		this.setRotation(this.Shape35, -0.0872665F, 0.0F, 0.0F);
	}

	@Override
	public final void renderAll() {
		this.Shape33.render();
		this.Shape34.render();
		this.Shape35.render();
	}
}
