/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.client.models.armor.BelhavenaBrigantine;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkdata.client.models.DAModelBase;
import keelfy.darkdata.client.models.DAModelRenderer;

@SideOnly(Side.CLIENT)
public final class BelhavenaBrigantineLeg extends DAModelBase {

	private final DAModelRenderer Shape1;
	private final DAModelRenderer Shape2;
	private final DAModelRenderer Shape3;

	public BelhavenaBrigantineLeg() {
		this.Shape1 = new DAModelRenderer(this, 16, 16);
		this.Shape1.addBox(0.0F, 0.0F, 0.0F, 3, 4, 1);
		this.Shape1.setRotationPoint(1.0F, 12.0F, -2.2F);
		this.setRotation(this.Shape1, -0.0698132F, 0.0F, 0.0F);
		this.Shape2 = new DAModelRenderer(this, 36, 19);
		this.Shape2.addBox(0.0F, 0.0F, 0.0F, 4, 4, 1);
		this.Shape2.setRotationPoint(0.0F, 12.0F, 1.2F);
		this.setRotation(this.Shape2, 0.0523599F, 0.0F, 0.0F);
		this.Shape3 = new DAModelRenderer(this, 25, 19);
		this.Shape3.addBox(0.0F, 0.0F, 0.0F, 1, 4, 4);
		this.Shape3.setRotationPoint(3.2F, 12.0F, -2.0F);
		this.setRotation(this.Shape3, 0.0F, 0.0F, -0.0523599F);
	}

	@Override
	public final void renderAll() {
		this.Shape1.render();
		this.Shape2.render();
		this.Shape3.render();
	}
}
