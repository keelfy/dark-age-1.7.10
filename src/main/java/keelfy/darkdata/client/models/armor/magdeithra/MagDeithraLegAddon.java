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
public final class MagDeithraLegAddon extends DAModelBase {

	private final DAModelRenderer Shape39;
	private final DAModelRenderer Shape40;
	private final DAModelRenderer Shape41;
	private final DAModelRenderer Shape42;
	private final DAModelRenderer Shape51;
	private final DAModelRenderer Shape52;
	private final DAModelRenderer Shape53;
	private final DAModelRenderer Shape54;
	private final DAModelRenderer Shape55;
	private final DAModelRenderer Shape56;

	public MagDeithraLegAddon() {
		this.Shape39 = new DAModelRenderer(this, 27, 26);
		this.Shape39.addBox(0.0F, 0.0F, 0.0F, 5, 1, 5);
		this.Shape39.setRotationPoint(-4.4F, 10.7F, -2.5F);
		this.setRotation(this.Shape39, 0.0F, 0.0F, 0.1396263F);
		this.Shape40 = new DAModelRenderer(this, 0, 0);
		this.Shape40.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
		this.Shape40.setRotationPoint(-4.0F, 10.8F, -3.0F);
		this.setRotation(this.Shape40, 0.0F, 0.0F, 0.1396263F);
		this.Shape41 = new DAModelRenderer(this, 0, 0);
		this.Shape41.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
		this.Shape41.setRotationPoint(-2.5F, 11.0F, -3.0F);
		this.setRotation(this.Shape41, 0.0F, 0.0F, 0.1396263F);
		this.Shape42 = new DAModelRenderer(this, 0, 0);
		this.Shape42.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
		this.Shape42.setRotationPoint(-0.8F, 11.2F, -3.0F);
		this.setRotation(this.Shape42, 0.0F, 0.0F, 0.1396263F);
		this.Shape51 = new DAModelRenderer(this, 0, 0);
		this.Shape51.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
		this.Shape51.setRotationPoint(-0.8F, 11.2F, 2.0F);
		this.setRotation(this.Shape51, 0.0F, 0.0F, 0.1396263F);
		this.Shape52 = new DAModelRenderer(this, 0, 0);
		this.Shape52.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
		this.Shape52.setRotationPoint(-2.5F, 11.0F, 2.0F);
		this.setRotation(this.Shape52, 0.0F, 0.0F, 0.1396263F);
		this.Shape53 = new DAModelRenderer(this, 0, 0);
		this.Shape53.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
		this.Shape53.setRotationPoint(-4.0F, 10.8F, 2.0F);
		this.setRotation(this.Shape53, 0.0F, 0.0F, 0.1396263F);
		this.Shape54 = new DAModelRenderer(this, 0, 0);
		this.Shape54.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
		this.Shape54.setRotationPoint(-5.0F, 10.6F, -2.0F);
		this.setRotation(this.Shape54, 0.0F, 0.0F, 0.1396263F);
		this.Shape55 = new DAModelRenderer(this, 0, 0);
		this.Shape55.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
		this.Shape55.setRotationPoint(-5.0F, 10.6F, -0.5F);
		this.setRotation(this.Shape55, 0.0F, 0.0F, 0.1396263F);
		this.Shape56 = new DAModelRenderer(this, 0, 0);
		this.Shape56.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
		this.Shape56.setRotationPoint(-5.0F, 10.6F, 1.0F);
		this.setRotation(this.Shape56, 0.0F, 0.0F, 0.1396263F);
	}

	@Override
	public final void renderAll() {
		this.Shape39.render();
		this.Shape40.render();
		this.Shape41.render();
		this.Shape42.render();
		this.Shape51.render();
		this.Shape52.render();
		this.Shape53.render();
		this.Shape54.render();
		this.Shape55.render();
		this.Shape56.render();
	}
}
