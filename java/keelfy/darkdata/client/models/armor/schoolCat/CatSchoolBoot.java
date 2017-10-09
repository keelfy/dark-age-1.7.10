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
public final class CatSchoolBoot extends DAModelBase {

	private final DAModelRenderer Shape50;
	private final DAModelRenderer Shape51;
	private final DAModelRenderer Shape52;
	private final DAModelRenderer Shape53;
	private final DAModelRenderer Shape54;
	private final DAModelRenderer Shape55;
	private final DAModelRenderer Shape56;
	private final DAModelRenderer Shape57;
	private final DAModelRenderer Shape58;
	private final DAModelRenderer Shape67;
	private final DAModelRenderer Shape68;
	private final DAModelRenderer Shape69;
	private final DAModelRenderer Shape70;
	private final DAModelRenderer Shape71;
	private final DAModelRenderer Shape72;

	public CatSchoolBoot() {
		this.Shape50 = new DAModelRenderer(this, 11, 25);
		this.Shape50.addBox(0.0F, 0.0F, 0.0F, 4, 6, 1);
		this.Shape50.setRotationPoint(0.0F, 18.0F, -2.2F);
		this.setRotation(this.Shape50, 0.0F, 0.0F, 0.0F);
		this.Shape51 = new DAModelRenderer(this, 11, 25);
		this.Shape51.addBox(0.0F, 0.0F, 0.0F, 4, 6, 1);
		this.Shape51.setRotationPoint(0.0F, 18.0F, 1.2F);
		this.setRotation(this.Shape51, 0.0F, 0.0F, 0.0F);
		this.Shape52 = new DAModelRenderer(this, 11, 25);
		this.Shape52.addBox(0.0F, 0.0F, 0.0F, 4, 6, 1);
		this.Shape52.setRotationPoint(3.2F, 18.0F, 2.0F);
		this.setRotation(this.Shape52, 0.0F, 1.58825F, 0.0F);
		this.Shape53 = new DAModelRenderer(this, 11, 25);
		this.Shape53.addBox(0.0F, 0.0F, 0.0F, 4, 6, 1);
		this.Shape53.setRotationPoint(-0.2F, 18.0F, 2.0F);
		this.setRotation(this.Shape53, 0.0F, 1.58825F, 0.0F);
		this.Shape54 = new DAModelRenderer(this, 22, 29);
		this.Shape54.addBox(0.0F, 0.0F, 0.0F, 3, 1, 2);
		this.Shape54.setRotationPoint(0.5F, 23.0F, -3.5F);
		this.setRotation(this.Shape54, 0.0F, 0.0F, 0.0F);
		this.Shape55 = new DAModelRenderer(this, 22, 29);
		this.Shape55.addBox(0.0F, 0.0F, 0.0F, 3, 1, 2);
		this.Shape55.setRotationPoint(0.5F, 23.0F, -3.5F);
		this.setRotation(this.Shape55, 0.7679449F, 0.0F, 0.0F);
		this.Shape56 = new DAModelRenderer(this, 0, 0);
		this.Shape56.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2);
		this.Shape56.setRotationPoint(1.5F, 23.0F, -3.7F);
		this.setRotation(this.Shape56, 0.7679449F, 0.0F, 0.0F);
		this.Shape57 = new DAModelRenderer(this, 0, 0);
		this.Shape57.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1);
		this.Shape57.setRotationPoint(1.5F, 18.8F, -2.3F);
		this.setRotation(this.Shape57, 0.0F, 0.0F, 0.0F);
		this.Shape58 = new DAModelRenderer(this, 0, 0);
		this.Shape58.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
		this.Shape58.setRotationPoint(1.5F, 18.5F, -2.7F);
		this.setRotation(this.Shape58, 0.4363323F, 0.0F, 0.0F);
		this.Shape67 = new DAModelRenderer(this, 7, 0);
		this.Shape67.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2);
		this.Shape67.setRotationPoint(3.5F, 19.0F, 0.4F);
		this.setRotation(this.Shape67, 0.0F, 0.1396263F, 0.0F);
		this.Shape68 = new DAModelRenderer(this, 7, 0);
		this.Shape68.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2);
		this.Shape68.setRotationPoint(3.5F, 21.0F, 0.4F);
		this.setRotation(this.Shape68, 0.0F, 0.1396263F, 0.0F);
		this.Shape69 = new DAModelRenderer(this, 0, 4);
		this.Shape69.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1);
		this.Shape69.setRotationPoint(0.0F, 19.0F, -2.4F);
		this.setRotation(this.Shape69, 0.7853982F, 0.0F, 0.0F);
		this.Shape70 = new DAModelRenderer(this, 0, 4);
		this.Shape70.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1);
		this.Shape70.setRotationPoint(3.0F, 19.0F, 2.0F);
		this.setRotation(this.Shape70, 0.7853982F, 1.58825F, 0.0F);
		this.Shape71 = new DAModelRenderer(this, 0, 4);
		this.Shape71.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1);
		this.Shape71.setRotationPoint(-0.4F, 19.0F, 2.0F);
		this.setRotation(this.Shape71, 0.7853982F, 1.58825F, 0.0F);
		this.Shape72 = new DAModelRenderer(this, 0, 4);
		this.Shape72.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1);
		this.Shape72.setRotationPoint(0.0F, 19.0F, 1.0F);
		this.setRotation(this.Shape72, 0.7853982F, 0.0F, 0.0F);
	}

	@Override
	public final void renderAll() {
		this.Shape50.render();
		this.Shape51.render();
		this.Shape52.render();
		this.Shape53.render();
		this.Shape54.render();
		this.Shape55.render();
		this.Shape56.render();
		this.Shape57.render();
		this.Shape58.render();
		this.Shape67.render();
		this.Shape68.render();
		this.Shape69.render();
		this.Shape70.render();
		this.Shape71.render();
		this.Shape72.render();
	}
}
