/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.client.models.armor.nilfgaard;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkdata.client.models.DAModelBase;
import keelfy.darkdata.client.models.DAModelRenderer;

@SideOnly(Side.CLIENT)
public final class NilfgaardLeg extends DAModelBase {

	private final DAModelRenderer Shape69;
	private final DAModelRenderer Shape70;
	private final DAModelRenderer Shape71;
	private final DAModelRenderer Shape72;
	private final DAModelRenderer Shape73;
	private final DAModelRenderer Shape74;
	private final DAModelRenderer Shape75;
	private final DAModelRenderer Shape76;
	private final DAModelRenderer Shape77;
	private final DAModelRenderer Shape78;
	private final DAModelRenderer Shape79;
	private final DAModelRenderer Shape80;

	public NilfgaardLeg() {
		this.Shape69 = new DAModelRenderer(this, 0, 30);
		this.Shape69.addBox(0.0F, 0.0F, 0.0F, 5, 1, 1);
		this.Shape69.setRotationPoint(-0.5F, 13.0F, -2.5F);
		this.setRotation(this.Shape69, 0.0F, 0.0F, 0.0F);
		this.Shape70 = new DAModelRenderer(this, 0, 30);
		this.Shape70.addBox(0.0F, 0.0F, 0.0F, 5, 1, 1);
		this.Shape70.setRotationPoint(-0.5F, 13.0F, 1.5F);
		this.setRotation(this.Shape70, 0.0F, 0.0F, 0.0F);
		this.Shape71 = new DAModelRenderer(this, 0, 26);
		this.Shape71.addBox(0.0F, 0.0F, 0.0F, 1, 1, 5);
		this.Shape71.setRotationPoint(-0.5F, 13.0F, -2.5F);
		this.setRotation(this.Shape71, 0.0F, 0.0F, 0.0F);
		this.Shape72 = new DAModelRenderer(this, 0, 26);
		this.Shape72.addBox(0.0F, 0.0F, 0.0F, 1, 1, 5);
		this.Shape72.setRotationPoint(3.5F, 13.0F, -2.5F);
		this.setRotation(this.Shape72, 0.0F, 0.0F, 0.0F);
		this.Shape73 = new DAModelRenderer(this, 0, 30);
		this.Shape73.addBox(0.0F, 0.0F, 0.0F, 5, 1, 1);
		this.Shape73.setRotationPoint(-0.5F, 15.0F, -2.5F);
		this.setRotation(this.Shape73, 0.0F, 0.0F, 0.0F);
		this.Shape74 = new DAModelRenderer(this, 0, 30);
		this.Shape74.addBox(0.0F, 0.0F, 0.0F, 5, 1, 1);
		this.Shape74.setRotationPoint(-0.5F, 15.0F, 1.5F);
		this.setRotation(this.Shape74, 0.0F, 0.0F, 0.0F);
		this.Shape75 = new DAModelRenderer(this, 0, 26);
		this.Shape75.addBox(0.0F, 0.0F, 0.0F, 1, 1, 5);
		this.Shape75.setRotationPoint(-0.5F, 15.0F, -2.5F);
		this.setRotation(this.Shape75, 0.0F, 0.0F, 0.0F);
		this.Shape76 = new DAModelRenderer(this, 0, 26);
		this.Shape76.addBox(0.0F, 0.0F, 0.0F, 1, 1, 5);
		this.Shape76.setRotationPoint(3.5F, 15.0F, -2.5F);
		this.setRotation(this.Shape76, 0.0F, 0.0F, 0.0F);
		this.Shape77 = new DAModelRenderer(this, 24, 28);
		this.Shape77.addBox(0.0F, 0.0F, 0.0F, 4, 3, 1);
		this.Shape77.setRotationPoint(0.0F, 17.0F, -2.7F);
		this.setRotation(this.Shape77, 0.1919862F, 0.0F, 0.0F);
		this.Shape78 = new DAModelRenderer(this, 35, 28);
		this.Shape78.addBox(0.0F, 0.0F, 0.0F, 3, 3, 1);
		this.Shape78.setRotationPoint(0.5F, 18.0F, -3.0F);
		this.setRotation(this.Shape78, 0.3316126F, 0.0F, 0.0F);
		this.Shape79 = new DAModelRenderer(this, 35, 29);
		this.Shape79.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1);
		this.Shape79.setRotationPoint(0.5F, 19.0F, -2.0F);
		this.setRotation(this.Shape79, -0.1570796F, 0.0F, 0.0F);
		this.Shape80 = new DAModelRenderer(this, 35, 29);
		this.Shape80.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1);
		this.Shape80.setRotationPoint(0.5F, 20.0F, -2.0F);
		this.setRotation(this.Shape80, -0.1570796F, 0.0F, 0.0F);
	}

	@Override
	public final void renderAll() {
		this.Shape69.render();
		this.Shape70.render();
		this.Shape71.render();
		this.Shape72.render();
		this.Shape73.render();
		this.Shape74.render();
		this.Shape75.render();
		this.Shape76.render();
		this.Shape77.render();
		this.Shape78.render();
		this.Shape79.render();
		this.Shape80.render();
	}
}
