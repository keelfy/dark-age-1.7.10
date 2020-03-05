/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.client.models.entity.sign;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkdata.client.models.DAModelBase;
import keelfy.darkdata.client.models.DAModelRenderer;

@SideOnly(Side.CLIENT)
public final class ModelSignKven extends DAModelBase {

	private final DAModelRenderer Shape1;

	public ModelSignKven() {
		this.Shape1 = new DAModelRenderer(this, 0, 0);
		this.Shape1.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
	}

	@Override
	public final void renderAll() {
		this.Shape1.render();
	}
}
