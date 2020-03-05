/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.client.models.armor.schoolGriffin;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkdata.client.models.DAModelBase;
import keelfy.darkdata.client.models.DAModelRenderer;

@SideOnly(Side.CLIENT)
public final class GriffinLeg extends DAModelBase {

	private final DAModelRenderer Shape41;
	private final DAModelRenderer Shape42;
	private final DAModelRenderer Shape43;
	private final DAModelRenderer Shape44;
	private final DAModelRenderer Shape45;
	private final DAModelRenderer Shape46;
	private final DAModelRenderer Shape47;
	private final DAModelRenderer Shape48;
	private final DAModelRenderer Shape49;
	private final DAModelRenderer Shape50;

	public GriffinLeg() {
		this.Shape41 = new DAModelRenderer(this, 20, 14);
		this.Shape41.addBox(0.0F, 0.0F, 0.0F, 2, 5, 1);
		this.Shape41.setRotationPoint(2.0F, 13.0F, -2.1F);
		this.setRotation(this.Shape41, 0.0F, 0.0F, 0.0F);
		this.Shape42 = new DAModelRenderer(this, 20, 14);
		this.Shape42.addBox(0.0F, 0.0F, 0.0F, 2, 5, 1);
		this.Shape42.setRotationPoint(2.0F, 13.0F, 1.1F);
		this.setRotation(this.Shape42, 0.0F, 0.0F, 0.0F);
		this.Shape43 = new DAModelRenderer(this, 0, 0);
		this.Shape43.addBox(0.0F, 0.0F, 0.0F, 1, 5, 4);
		this.Shape43.setRotationPoint(3.1F, 13.0F, -2.0F);
		this.setRotation(this.Shape43, 0.0F, 0.0F, 0.0F);
		this.Shape44 = new DAModelRenderer(this, 20, 15);
		this.Shape44.addBox(0.0F, 0.0F, 0.0F, 2, 4, 1);
		this.Shape44.setRotationPoint(0.0F, 14.0F, -2.1F);
		this.setRotation(this.Shape44, 0.0F, 0.0F, 0.0F);
		this.Shape45 = new DAModelRenderer(this, 20, 15);
		this.Shape45.addBox(0.0F, 0.0F, 0.0F, 2, 4, 1);
		this.Shape45.setRotationPoint(0.0F, 14.0F, 1.1F);
		this.setRotation(this.Shape45, 0.0F, 0.0F, 0.0F);
		this.Shape46 = new DAModelRenderer(this, 16, 6);
		this.Shape46.addBox(0.0F, 0.0F, 0.0F, 1, 4, 4);
		this.Shape46.setRotationPoint(-0.1F, 14.0F, -2.0F);
		this.setRotation(this.Shape46, 0.0F, 0.0F, 0.0F);
		this.Shape47 = new DAModelRenderer(this, 16, 12);
		this.Shape47.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1);
		this.Shape47.setRotationPoint(0.1F, 13.5F, -2.1F);
		this.setRotation(this.Shape47, 0.0F, 0.0F, -0.2617994F);
		this.Shape48 = new DAModelRenderer(this, 16, 12);
		this.Shape48.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1);
		this.Shape48.setRotationPoint(0.1F, 13.5F, 1.1F);
		this.setRotation(this.Shape48, 0.0F, 0.0F, -0.2617994F);
		this.Shape49 = new DAModelRenderer(this, 16, 10);
		this.Shape49.addBox(0.0F, 0.0F, 0.0F, 1, 1, 3);
		this.Shape49.setRotationPoint(-0.3F, 13.6F, -2.1F);
		this.setRotation(this.Shape49, 0.0F, 0.0F, -0.2617994F);
		this.Shape50 = new DAModelRenderer(this, 16, 10);
		this.Shape50.addBox(0.0F, 0.0F, 0.0F, 1, 1, 3);
		this.Shape50.setRotationPoint(-0.3F, 13.6F, -0.9F);
		this.setRotation(this.Shape50, 0.0F, 0.0F, -0.2617994F);
	}

	@Override
	public final void renderAll() {
		this.Shape41.render();
		this.Shape42.render();
		this.Shape43.render();
		this.Shape44.render();
		this.Shape45.render();
		this.Shape46.render();
		this.Shape47.render();
		this.Shape48.render();
		this.Shape49.render();
		this.Shape50.render();
	}
}
