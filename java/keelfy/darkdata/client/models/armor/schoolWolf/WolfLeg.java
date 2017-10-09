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
public final class WolfLeg extends DAModelBase {

	private final DAModelRenderer Shape44;
	private final DAModelRenderer Shape45;
	private final DAModelRenderer Shape46;
	private final DAModelRenderer Shape47;

	public WolfLeg() {
		this.Shape44 = new DAModelRenderer(this, 54, 0);
		this.Shape44.addBox(0.0F, 0.0F, 0.0F, 4, 8, 1);
		this.Shape44.setRotationPoint(0.0F, 12.0F, -2.1F);
		this.setRotation(this.Shape44, 0.0F, 0.0F, 0.0F);
		this.Shape45 = new DAModelRenderer(this, 54, 20);
		this.Shape45.addBox(0.0F, 0.0F, 0.0F, 1, 8, 4);
		this.Shape45.setRotationPoint(3.1F, 12.0F, -2.0F);
		this.setRotation(this.Shape45, 0.0F, 0.0F, 0.0F);
		this.Shape46 = new DAModelRenderer(this, 54, 0);
		this.Shape46.addBox(0.0F, 0.0F, 0.0F, 4, 8, 1);
		this.Shape46.setRotationPoint(0.0F, 12.0F, 1.1F);
		this.setRotation(this.Shape46, 0.0F, 0.0F, 0.0F);
		this.Shape47 = new DAModelRenderer(this, 54, 20);
		this.Shape47.addBox(0.0F, 0.0F, 0.0F, 1, 8, 4);
		this.Shape47.setRotationPoint(-0.1F, 12.0F, -2.0F);
		this.setRotation(this.Shape47, 0.0F, 0.0F, 0.0F);
	}

	public final void renderAll() {
		this.Shape44.render();
		this.Shape45.render();
		this.Shape46.render();
		this.Shape47.render();
	}
}
