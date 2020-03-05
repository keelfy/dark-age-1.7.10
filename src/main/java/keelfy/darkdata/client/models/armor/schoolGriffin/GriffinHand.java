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
public final class GriffinHand extends DAModelBase {

	private final DAModelRenderer Shape17;
	private final DAModelRenderer Shape18;
	private final DAModelRenderer Shape19;
	private final DAModelRenderer Shape20;
	private final DAModelRenderer Shape21;
	private final DAModelRenderer Shape22;

	public GriffinHand() {
		this.Shape17 = new DAModelRenderer(this, 17, 27);
		this.Shape17.addBox(0.0F, 0.0F, 0.0F, 4, 1, 4);
		this.Shape17.setRotationPoint(4.0F, -0.1F, -2.0F);
		this.setRotation(this.Shape17, 0.0F, 0.0F, 0.0F);
		this.Shape18 = new DAModelRenderer(this, 0, 14);
		this.Shape18.addBox(0.0F, 0.0F, 0.0F, 1, 3, 4);
		this.Shape18.setRotationPoint(7.1F, 0.0F, -2.0F);
		this.setRotation(this.Shape18, 0.0F, 0.0F, 0.0F);
		this.Shape19 = new DAModelRenderer(this, 0, 17);
		this.Shape19.addBox(0.0F, 0.0F, 0.0F, 4, 3, 1);
		this.Shape19.setRotationPoint(4.0F, 0.0F, -2.1F);
		this.setRotation(this.Shape19, 0.0F, 0.0F, 0.0F);
		this.Shape20 = new DAModelRenderer(this, 0, 17);
		this.Shape20.addBox(0.0F, 0.0F, 0.0F, 4, 3, 1);
		this.Shape20.setRotationPoint(4.0F, 0.0F, 1.1F);
		this.setRotation(this.Shape20, 0.0F, 0.0F, 0.0F);
		this.Shape21 = new DAModelRenderer(this, 22, 0);
		this.Shape21.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1);
		this.Shape21.setRotationPoint(7.2F, -0.3F, -0.5F);
		this.setRotation(this.Shape21, 0.0F, 0.0F, 0.0F);
		this.Shape22 = new DAModelRenderer(this, 0, 22);
		this.Shape22.addBox(0.0F, 0.0F, 0.0F, 4, 6, 4);
		this.Shape22.setRotationPoint(4.0F, 3.0F, -2.0F);
		this.setRotation(this.Shape22, 0.0F, 0.0F, 0.0F);
	}

	@Override
	public final void renderAll() {
		this.Shape17.render();
		this.Shape18.render();
		this.Shape19.render();
		this.Shape20.render();
		this.Shape21.render();
		this.Shape22.render();
	}
}
