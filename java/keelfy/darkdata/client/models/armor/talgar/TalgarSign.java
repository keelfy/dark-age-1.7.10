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
public final class TalgarSign extends DAModelBase {

	private final DAModelRenderer Shape1;

	public TalgarSign() {
		this.textureWidth = 32;

		this.Shape1 = new DAModelRenderer(this, 0, 0);
		this.Shape1.addBox(0.0F, 0.0F, 0.0F, 13, 26, 1);
		this.Shape1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
	}

	@Override
	public final void renderAll() {
		this.Shape1.render();
	}
}
