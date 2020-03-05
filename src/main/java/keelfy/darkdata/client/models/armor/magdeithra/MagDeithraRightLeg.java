/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.client.models.armor.magdeithra;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkdata.client.models.DAModelBase;
import keelfy.darkdata.client.models.DAModelRenderer;

@SideOnly(Side.CLIENT)
public final class MagDeithraRightLeg extends DAModelBase {

	private final DAModelRenderer Shape57;
	private final DAModelRenderer Shape58;
	private final DAModelRenderer Shape59;
	private final DAModelRenderer Shape60;

	public MagDeithraRightLeg() {
		this.Shape57 = new DAModelRenderer(this, 11, 23);
		this.Shape57.addBox(0.0F, 0.0F, 0.0F, 4, 8, 1);
		this.Shape57.setRotationPoint(0.5F, 12.0F, -2.7F);
		this.setRotation(this.Shape57, -0.0698132F, 0.0F, 0.0F);
		this.Shape58 = new DAModelRenderer(this, 11, 23);
		this.Shape58.addBox(0.0F, 0.0F, 0.0F, 4, 8, 1);
		this.Shape58.setRotationPoint(0.0F, 12.0F, 1.7F);
		this.setRotation(this.Shape58, 0.0698132F, 0.0F, 0.0F);
		this.Shape59 = new DAModelRenderer(this, 11, 20);
		this.Shape59.addBox(0.0F, 0.0F, 0.0F, 1, 8, 4);
		this.Shape59.setRotationPoint(3.6F, 12.0F, -2.0F);
		this.setRotation(this.Shape59, 0.0F, 0.0F, -0.0698132F);
		this.Shape60 = new DAModelRenderer(this, 22, 23);
		this.Shape60.addBox(0.0F, 0.0F, 0.0F, 1, 8, 1);
		this.Shape60.setRotationPoint(0.0F, 12.0F, -2.4F);
		this.setRotation(this.Shape60, -0.0698132F, 0.0F, 0.0F);
	}

	@Override
	public final void renderAll() {
		this.Shape57.render();
		this.Shape58.render();
		this.Shape59.render();
		this.Shape60.render();
	}
}
