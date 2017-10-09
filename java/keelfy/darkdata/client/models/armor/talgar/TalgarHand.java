/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.client.models.armor.talgar;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkdata.client.models.DAModelBase;
import keelfy.darkdata.client.models.DAModelRenderer;

@SideOnly(Side.CLIENT)
public final class TalgarHand extends DAModelBase {

	private final DAModelRenderer Shape0;
	private final DAModelRenderer Shape1;
	private final DAModelRenderer Shape2;
	private final DAModelRenderer Shape3;
	private final DAModelRenderer Shape4;
	private final DAModelRenderer Shape5;
	private final DAModelRenderer Shape6;
	private final DAModelRenderer Shape7;
	private final DAModelRenderer Shape42;
	private final DAModelRenderer Shape43;
	private final DAModelRenderer Shape44;
	private final DAModelRenderer Shape45;
	private final DAModelRenderer Shape46;
	private final DAModelRenderer Shape47;
	private final DAModelRenderer Shape48;
	private final DAModelRenderer Shape49;
	private final DAModelRenderer Shape50;

	public TalgarHand() {
		this.Shape0 = new DAModelRenderer(this, 23, 0);
		this.Shape0.addBox(0.0F, 0.0F, 0.0F, 4, 8, 1);
		this.Shape0.setRotationPoint(4.0F, 1.0F, -2.2F);
		this.setRotation(this.Shape0, 0.0174533F, 0.0F, 0.0F);
		this.Shape1 = new DAModelRenderer(this, 23, 0);
		this.Shape1.addBox(0.0F, 0.0F, 0.0F, 4, 8, 1);
		this.Shape1.setRotationPoint(4.0F, 1.0F, 1.2F);
		this.setRotation(this.Shape1, -0.0174533F, 0.0F, 0.0F);
		this.Shape2 = new DAModelRenderer(this, 23, 0);
		this.Shape2.addBox(0.0F, 0.0F, 0.0F, 4, 8, 1);
		this.Shape2.setRotationPoint(7.2F, 1.0F, 2.0F);
		this.setRotation(this.Shape2, -0.0174533F, 1.570796F, 0.0F);
		this.Shape3 = new DAModelRenderer(this, 23, 0);
		this.Shape3.addBox(0.0F, 0.0F, 0.0F, 4, 8, 1);
		this.Shape3.setRotationPoint(3.9F, 1.0F, 2.0F);
		this.setRotation(this.Shape3, 0.0F, 1.570796F, 0.0F);
		this.Shape4 = new DAModelRenderer(this, 19, 10);
		this.Shape4.addBox(0.0F, 0.0F, 0.0F, 4, 1, 4);
		this.Shape4.setRotationPoint(4.0F, -1.0F, -2.0F);
		this.setRotation(this.Shape4, 0.0F, 0.0F, 0.2443461F);
		this.Shape5 = new DAModelRenderer(this, 22, 13);
		this.Shape5.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1);
		this.Shape5.setRotationPoint(4.0F, 0.0F, -2.0F);
		this.setRotation(this.Shape5, -0.3665191F, 0.0F, 0.0F);
		this.Shape6 = new DAModelRenderer(this, 22, 13);
		this.Shape6.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1);
		this.Shape6.setRotationPoint(4.0F, 0.3F, 1.1F);
		this.setRotation(this.Shape6, 0.3665191F, 0.0F, 0.0F);
		this.Shape7 = new DAModelRenderer(this, 22, 13);
		this.Shape7.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1);
		this.Shape7.setRotationPoint(7.0F, 0.3F, 2.0F);
		this.setRotation(this.Shape7, 0.3665191F, 1.570796F, 0.0F);
		this.Shape42 = new DAModelRenderer(this, 0, 3);
		this.Shape42.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1);
		this.Shape42.setRotationPoint(4.0F, 8.0F, -2.3F);
		this.setRotation(this.Shape42, 0.0F, 0.0F, 0.0F);
		this.Shape43 = new DAModelRenderer(this, 0, 3);
		this.Shape43.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1);
		this.Shape43.setRotationPoint(4.0F, 8.0F, 1.3F);
		this.setRotation(this.Shape43, 0.0F, 0.0F, 0.0F);
		this.Shape44 = new DAModelRenderer(this, 0, 3);
		this.Shape44.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1);
		this.Shape44.setRotationPoint(7.3F, 8.0F, 2.0F);
		this.setRotation(this.Shape44, 0.0F, 1.570796F, 0.0F);
		this.Shape45 = new DAModelRenderer(this, 0, 3);
		this.Shape45.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1);
		this.Shape45.setRotationPoint(3.7F, 8.0F, 2.0F);
		this.setRotation(this.Shape45, 0.0F, 1.570796F, 0.0F);
		this.Shape46 = new DAModelRenderer(this, 0, 19);
		this.Shape46.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1);
		this.Shape46.setRotationPoint(4.0F, 1.2F, -2.2F);
		this.setRotation(this.Shape46, -0.0349066F, 0.0F, -0.5410521F);
		this.Shape47 = new DAModelRenderer(this, 0, 19);
		this.Shape47.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1);
		this.Shape47.setRotationPoint(5.1F, 0.9F, -2.2F);
		this.setRotation(this.Shape47, -0.0349066F, 0.0F, 0.0F);
		this.Shape48 = new DAModelRenderer(this, 0, 19);
		this.Shape48.addBox(0.0F, 0.0F, 0.0F, 4, 2, 1);
		this.Shape48.setRotationPoint(7.2F, 1.0F, 2.0F);
		this.setRotation(this.Shape48, 0.0349066F, 1.570796F, 0.0F);
		this.Shape49 = new DAModelRenderer(this, 0, 19);
		this.Shape49.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1);
		this.Shape49.setRotationPoint(4.0F, 1.2F, 1.3F);
		this.setRotation(this.Shape49, -0.0349066F, 0.0F, -0.5410521F);
		this.Shape50 = new DAModelRenderer(this, 0, 19);
		this.Shape50.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1);
		this.Shape50.setRotationPoint(5.1F, 0.9F, 1.3F);
		this.setRotation(this.Shape50, -0.0349066F, 0.0F, 0.0F);
	}

	@Override
	public final void renderAll() {
		this.Shape0.render();
		this.Shape1.render();
		this.Shape2.render();
		this.Shape3.render();
		this.Shape4.render();
		this.Shape5.render();
		this.Shape6.render();
		this.Shape7.render();
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
