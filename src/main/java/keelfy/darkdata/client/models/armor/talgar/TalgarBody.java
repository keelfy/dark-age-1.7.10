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
public final class TalgarBody extends DAModelBase {

	private final DAModelRenderer Shape28;
	private final DAModelRenderer Shape29;
	private final DAModelRenderer Shape30;
	private final DAModelRenderer Shape31;
	private final DAModelRenderer Shape32;
	private final DAModelRenderer Shape33;
	private final DAModelRenderer Shape34;
	private final DAModelRenderer Shape35;

	public TalgarBody() {
		this.Shape28 = new DAModelRenderer(this, 46, 0);
		this.Shape28.addBox(0.0F, 0.0F, 0.0F, 8, 4, 1);
		this.Shape28.setRotationPoint(-4.0F, 0.0F, -2.0F);
		this.setRotation(this.Shape28, -0.1570796F, 0.0F, 0.0F);
		this.Shape29 = new DAModelRenderer(this, 46, 0);
		this.Shape29.addBox(0.0F, 0.0F, 0.0F, 8, 4, 1);
		this.Shape29.setRotationPoint(-4.0F, 7.9F, 1.6F);
		this.setRotation(this.Shape29, -0.1396263F, 0.0F, 0.0F);
		this.Shape30 = new DAModelRenderer(this, 46, 0);
		this.Shape30.addBox(0.0F, 0.0F, 0.0F, 8, 4, 1);
		this.Shape30.setRotationPoint(-4.0F, 8.0F, -2.6F);
		this.setRotation(this.Shape30, 0.1570796F, 0.0F, 0.0F);
		this.Shape31 = new DAModelRenderer(this, 46, 0);
		this.Shape31.addBox(0.0F, 0.0F, 0.0F, 8, 4, 1);
		this.Shape31.setRotationPoint(-4.0F, 0.2F, 1.0F);
		this.setRotation(this.Shape31, 0.1570796F, 0.0F, 0.0F);
		this.Shape32 = new DAModelRenderer(this, 46, 0);
		this.Shape32.addBox(0.0F, 0.0F, 0.0F, 8, 4, 1);
		this.Shape32.setRotationPoint(-4.0F, 4.0F, -2.6F);
		this.setRotation(this.Shape32, 0.0F, 0.0F, 0.0F);
		this.Shape33 = new DAModelRenderer(this, 46, 0);
		this.Shape33.addBox(0.0F, 0.0F, 0.0F, 8, 4, 1);
		this.Shape33.setRotationPoint(-4.0F, 4.0F, 1.6F);
		this.setRotation(this.Shape33, 0.0F, 0.0F, 0.0F);
		this.Shape34 = new DAModelRenderer(this, 35, 0);
		this.Shape34.addBox(0.0F, 0.0F, 0.0F, 1, 12, 4);
		this.Shape34.setRotationPoint(3.0F, 0.0F, -2.0F);
		this.setRotation(this.Shape34, 0.0F, 0.0F, 0.0F);
		this.Shape35 = new DAModelRenderer(this, 35, 0);
		this.Shape35.addBox(0.0F, 0.0F, 0.0F, 1, 12, 4);
		this.Shape35.setRotationPoint(-4.0F, 0.0F, -2.0F);
		this.setRotation(this.Shape35, 0.0F, 0.0F, 0.0F);
	}

	@Override
	public final void renderAll() {
		this.Shape28.render();
		this.Shape29.render();
		this.Shape30.render();
		this.Shape31.render();
		this.Shape32.render();
		this.Shape33.render();
		this.Shape34.render();
		this.Shape35.render();
	}
}
