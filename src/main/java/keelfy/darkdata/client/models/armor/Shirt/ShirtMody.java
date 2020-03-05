/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.client.models.armor.Shirt;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkdata.client.models.DAModelBase;
import keelfy.darkdata.client.models.DAModelRenderer;

@SideOnly(Side.CLIENT)
public final class ShirtMody extends DAModelBase {

	private final DAModelRenderer Shape0;
	private final DAModelRenderer Shape1;
	private final DAModelRenderer Shape7;
	private final DAModelRenderer Shape8;
	private final DAModelRenderer Shape9;
	private final DAModelRenderer Shape10;
	private final DAModelRenderer Shape23;
	private final DAModelRenderer Shape24;

	public ShirtMody() {
		this.Shape0 = new DAModelRenderer(this, 46, 0);
		this.Shape0.addBox(0.0F, 0.0F, 0.0F, 8, 12, 1);
		this.Shape0.setRotationPoint(-4.0F, 0.0F, -2.1F);
		this.setRotation(this.Shape0, 0.0F, 0.0F, 0.0F);
		this.Shape1 = new DAModelRenderer(this, 46, 0);
		this.Shape1.addBox(0.0F, 0.0F, 0.0F, 8, 12, 1);
		this.Shape1.setRotationPoint(-4.0F, 0.0F, 1.1F);
		this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
		this.Shape7 = new DAModelRenderer(this, 35, 0);
		this.Shape7.addBox(0.0F, 0.0F, 0.0F, 1, 12, 4);
		this.Shape7.setRotationPoint(3.0F, 0.0F, -2.0F);
		this.setRotation(this.Shape7, 0.0F, 0.0F, 0.0F);
		this.Shape8 = new DAModelRenderer(this, 35, 0);
		this.Shape8.addBox(0.0F, 0.0F, 0.0F, 1, 12, 4);
		this.Shape8.setRotationPoint(-4.0F, 0.0F, -2.0F);
		this.setRotation(this.Shape8, 0.0F, 0.0F, 0.0F);
		this.Shape9 = new DAModelRenderer(this, 5, 30);
		this.Shape9.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
		this.Shape9.setRotationPoint(2.0F, 0.0F, -2.2F);
		this.setRotation(this.Shape9, 0.0F, 0.0F, 0.0F);
		this.Shape10 = new DAModelRenderer(this, 5, 30);
		this.Shape10.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
		this.Shape10.setRotationPoint(-3.0F, 0.0F, -2.2F);
		this.setRotation(this.Shape10, 0.0F, 0.0F, 0.0F);
		this.Shape23 = new DAModelRenderer(this, 0, 0);
		this.Shape23.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1);
		this.Shape23.setRotationPoint(-3.0F, 0.0F, -2.3F);
		this.setRotation(this.Shape23, 0.0F, 0.0F, -0.0523599F);
		this.Shape24 = new DAModelRenderer(this, 0, 0);
		this.Shape24.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1);
		this.Shape24.setRotationPoint(2.0F, 0.0F, -2.3F);
		this.setRotation(this.Shape24, 0.0F, 0.0F, 0.0523599F);
	}

	@Override
	public final void renderAll() {
		this.Shape0.render();
		this.Shape1.render();
		this.Shape7.render();
		this.Shape8.render();
		this.Shape9.render();
		this.Shape10.render();
		this.Shape23.render();
		this.Shape24.render();
	}
}
