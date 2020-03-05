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
public final class ModelGlove extends DAModelBase {

	private final DAModelRenderer Shape1;
	private final DAModelRenderer Shape2;
	private final DAModelRenderer Shape3;
	private final DAModelRenderer Shape31;
	private final DAModelRenderer Shape32;
	private final DAModelRenderer Shape4;
	private final DAModelRenderer Shape41;

	public ModelGlove() {
		this.Shape1 = new DAModelRenderer(this, 0, 8);
		this.Shape1.addBox(0.0F, 0.0F, 0.0F, 8, 16, 8);
		this.Shape1.setRotationPoint(0.0F, -10.0F, 0.0F);
		this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
		this.Shape2 = new DAModelRenderer(this, 40, 7);
		this.Shape2.addBox(0.0F, 0.0F, 0.0F, 2, 2, 8);
		this.Shape2.setRotationPoint(6.533333F, 0.0F, 0.0F);
		this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
		this.Shape3 = new DAModelRenderer(this, 35, 0);
		this.Shape3.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
		this.Shape3.setRotationPoint(8.0F, 0.5F, 3.0F);
		this.setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
		this.Shape31 = new DAModelRenderer(this, 35, 0);
		this.Shape31.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
		this.Shape31.setRotationPoint(8.0F, 0.5F, 1.0F);
		this.setRotation(this.Shape31, 0.0F, 0.0F, 0.0F);
		this.Shape32 = new DAModelRenderer(this, 35, 0);
		this.Shape32.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
		this.Shape32.setRotationPoint(8.0F, 0.5F, 5.0F);
		this.setRotation(this.Shape32, 0.0F, 0.0F, 0.0F);
		this.Shape4 = new DAModelRenderer(this, 0, 0);
		this.Shape4.addBox(0.0F, 0.0F, 0.0F, 9, 2, 9);
		this.Shape4.setRotationPoint(-0.5F, -2.933333F, -0.5F);
		this.setRotation(this.Shape4, 0.0F, 0.0F, 0.0F);
		this.Shape41 = new DAModelRenderer(this, 0, 0);
		this.Shape41.addBox(0.0F, 0.0F, 0.0F, 9, 2, 9);
		this.Shape41.setRotationPoint(-0.5F, -10.0F, -0.5F);
		this.setRotation(this.Shape41, 0.0F, 0.0F, 0.0F);
	}

	@Override
	public final void renderAll() {
		this.Shape1.render();
		this.Shape2.render();
		this.Shape3.render();
		this.Shape31.render();
		this.Shape32.render();
		this.Shape4.render();
		this.Shape41.render();
	}
}
