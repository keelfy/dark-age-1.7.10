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
public final class MagDeithraHand extends DAModelBase {

	private final DAModelRenderer Shape30;
	private final DAModelRenderer Shape31;
	private final DAModelRenderer Shape32;
	private final DAModelRenderer Shape33;
	private final DAModelRenderer Shape34;
	private final DAModelRenderer Shape35;
	private final DAModelRenderer Shape36;
	private final DAModelRenderer Shape61;
	private final DAModelRenderer Shape62;
	private final DAModelRenderer Shape63;
	private final DAModelRenderer Shape64;
	private final DAModelRenderer Shape65;
	private final DAModelRenderer Shape66;
	private final DAModelRenderer Shape67;
	private final DAModelRenderer Shape68;
	private final DAModelRenderer Shape69;

	public MagDeithraHand() {
		this.Shape30 = new DAModelRenderer(this, 55, 0);
		this.Shape30.addBox(0.0F, 0.0F, 0.0F, 3, 2, 2);
		this.Shape30.setRotationPoint(4.0F, 0.0F, -2.2F);
		this.setRotation(this.Shape30, 0.0F, 0.0F, 0.0F);
		this.Shape31 = new DAModelRenderer(this, 56, 0);
		this.Shape31.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2);
		this.Shape31.setRotationPoint(6.2F, 0.0F, -2.2F);
		this.setRotation(this.Shape31, 0.0F, 0.0F, 0.0F);
		this.Shape32 = new DAModelRenderer(this, 55, 0);
		this.Shape32.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1);
		this.Shape32.setRotationPoint(4.0F, 0.0F, -0.5F);
		this.setRotation(this.Shape32, 0.0F, 0.0F, 0.0F);
		this.Shape33 = new DAModelRenderer(this, 55, 0);
		this.Shape33.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1);
		this.Shape33.setRotationPoint(6.2F, 0.0F, -0.5F);
		this.setRotation(this.Shape33, 0.0F, 0.0F, 0.0F);
		this.Shape34 = new DAModelRenderer(this, 55, 0);
		this.Shape34.addBox(0.0F, 0.0F, 0.0F, 3, 2, 2);
		this.Shape34.setRotationPoint(4.0F, 0.0F, 0.0F);
		this.setRotation(this.Shape34, 0.0F, 0.0F, 0.0F);
		this.Shape35 = new DAModelRenderer(this, 56, 0);
		this.Shape35.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2);
		this.Shape35.setRotationPoint(6.2F, 0.0F, 0.0F);
		this.setRotation(this.Shape35, 0.0F, 0.0F, 0.0F);
		this.Shape36 = new DAModelRenderer(this, 38, 0);
		this.Shape36.addBox(0.0F, 0.0F, 0.0F, 4, 5, 4);
		this.Shape36.setRotationPoint(4.0F, 2.0F, -2.0F);
		this.setRotation(this.Shape36, 0.0F, 0.0F, 0.0F);
		this.Shape61 = new DAModelRenderer(this, 0, 4);
		this.Shape61.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
		this.Shape61.setRotationPoint(4.2F, 2.0F, -2.2F);
		this.setRotation(this.Shape61, 0.0F, 0.0F, 0.0F);
		this.Shape62 = new DAModelRenderer(this, 0, 4);
		this.Shape62.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
		this.Shape62.setRotationPoint(5.7F, 2.0F, -2.2F);
		this.setRotation(this.Shape62, 0.0F, 0.0F, 0.0F);
		this.Shape63 = new DAModelRenderer(this, 0, 4);
		this.Shape63.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
		this.Shape63.setRotationPoint(7.0F, 2.0F, -2.2F);
		this.setRotation(this.Shape63, 0.0F, 0.0F, 0.0F);
		this.Shape64 = new DAModelRenderer(this, 0, 4);
		this.Shape64.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
		this.Shape64.setRotationPoint(7.2F, 2.0F, -1.8F);
		this.setRotation(this.Shape64, 0.0F, 0.0F, 0.0F);
		this.Shape65 = new DAModelRenderer(this, 0, 4);
		this.Shape65.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
		this.Shape65.setRotationPoint(7.2F, 2.0F, -0.5F);
		this.setRotation(this.Shape65, 0.0F, 0.0F, 0.0F);
		this.Shape66 = new DAModelRenderer(this, 0, 4);
		this.Shape66.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
		this.Shape66.setRotationPoint(7.2F, 2.0F, 1.0F);
		this.setRotation(this.Shape66, 0.0F, 0.0F, 0.0F);
		this.Shape67 = new DAModelRenderer(this, 0, 4);
		this.Shape67.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
		this.Shape67.setRotationPoint(7.0F, 2.0F, 1.2F);
		this.setRotation(this.Shape67, 0.0F, 0.0F, 0.0F);
		this.Shape68 = new DAModelRenderer(this, 0, 4);
		this.Shape68.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
		this.Shape68.setRotationPoint(5.6F, 2.0F, 1.2F);
		this.setRotation(this.Shape68, 0.0F, 0.0F, 0.0F);
		this.Shape69 = new DAModelRenderer(this, 0, 4);
		this.Shape69.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
		this.Shape69.setRotationPoint(4.2F, 2.0F, 1.2F);
		this.setRotation(this.Shape69, 0.0F, 0.0F, 0.0F);
	}

	@Override
	public final void renderAll() {
		this.Shape30.render();
		this.Shape31.render();
		this.Shape32.render();
		this.Shape33.render();
		this.Shape34.render();
		this.Shape35.render();
		this.Shape36.render();
		this.Shape61.render();
		this.Shape62.render();
		this.Shape63.render();
		this.Shape64.render();
		this.Shape65.render();
		this.Shape66.render();
		this.Shape67.render();
		this.Shape68.render();
		this.Shape69.render();
	}
}
