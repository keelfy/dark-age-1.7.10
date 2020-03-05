/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.client.models.armor.schoolCat;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkdata.client.models.DAModelBase;
import keelfy.darkdata.client.models.DAModelRenderer;

@SideOnly(Side.CLIENT)
public final class CatSchoolLeg extends DAModelBase {

	private final DAModelRenderer Shape42;
	private final DAModelRenderer Shape43;
	private final DAModelRenderer Shape44;
	private final DAModelRenderer Shape45;

	public CatSchoolLeg() {
		this.Shape42 = new DAModelRenderer(this, 11, 19);
		this.Shape42.addBox(0.0F, 0.0F, 0.0F, 4, 12, 1);
		this.Shape42.setRotationPoint(0.0F, 12.0F, -2.2F);
		this.setRotation(this.Shape42, 0.0F, 0.0F, 0.0F);
		this.Shape43 = new DAModelRenderer(this, 0, 16);
		this.Shape43.addBox(0.0F, 0.0F, 0.0F, 1, 12, 4);
		this.Shape43.setRotationPoint(3.2F, 12.0F, -2.0F);
		this.setRotation(this.Shape43, 0.0F, 0.0F, 0.0F);
		this.Shape44 = new DAModelRenderer(this, 11, 19);
		this.Shape44.addBox(0.0F, 0.0F, 0.0F, 4, 12, 1);
		this.Shape44.setRotationPoint(0.0F, 12.0F, 1.2F);
		this.setRotation(this.Shape44, 0.0F, 0.0F, 0.0F);
		this.Shape45 = new DAModelRenderer(this, 0, 16);
		this.Shape45.addBox(0.0F, 0.0F, 0.0F, 1, 12, 4);
		this.Shape45.setRotationPoint(-0.2F, 12.0F, -2.0F);
		this.setRotation(this.Shape45, 0.0F, 0.0F, 0.0F);
	}

	@Override
	public final void renderAll() {
		this.Shape42.render();
		this.Shape43.render();
		this.Shape44.render();
		this.Shape45.render();
	}
}
