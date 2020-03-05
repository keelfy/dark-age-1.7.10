/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.client.models.armor.nilfgaard;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkdata.client.models.DAModelBase;
import keelfy.darkdata.client.models.DAModelRenderer;

@SideOnly(Side.CLIENT)
public final class NilfgaardArmor extends DAModelBase {

	private final DAModelRenderer Shape24;
	private final DAModelRenderer Shape53;
	private final DAModelRenderer Shape54;
	private final DAModelRenderer Shape55;
	private final DAModelRenderer Shape56;
	private final DAModelRenderer Shape60;
	private final DAModelRenderer Shape61;
	private final DAModelRenderer Shape62;
	private final DAModelRenderer Shape63;
	private final DAModelRenderer Shape64;
	private final DAModelRenderer Shape65;
	private final DAModelRenderer Shape66;
	private final DAModelRenderer Shape67;
	private final DAModelRenderer Shape68;

	public NilfgaardArmor() {
		this.Shape24 = new DAModelRenderer(this, 36, 0);
		this.Shape24.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4);
		this.Shape24.setRotationPoint(3.8F, 11.0F, -2.0F);
		this.setRotation(this.Shape24, 0.0F, 0.0F, 0.0F);
		this.Shape53 = new DAModelRenderer(this, 46, 20);
		this.Shape53.addBox(0.0F, 0.0F, 0.0F, 8, 11, 1);
		this.Shape53.setRotationPoint(-4.0F, 0.0F, -3.0F);
		this.setRotation(this.Shape53, 0.0F, 0.0F, 0.0F);
		this.Shape54 = new DAModelRenderer(this, 36, 0);
		this.Shape54.addBox(0.0F, 0.0F, 0.0F, 8, 11, 1);
		this.Shape54.setRotationPoint(-4.0F, 0.0F, 2.0F);
		this.setRotation(this.Shape54, 0.0F, 0.0F, 0.0F);
		this.Shape55 = new DAModelRenderer(this, 55, 0);
		this.Shape55.addBox(0.0F, 0.0F, 0.0F, 1, 11, 4);
		this.Shape55.setRotationPoint(-4.0F, 0.0F, -2.0F);
		this.setRotation(this.Shape55, 0.0F, 0.0F, 0.0F);
		this.Shape56 = new DAModelRenderer(this, 55, 0);
		this.Shape56.addBox(0.0F, 0.0F, 0.0F, 1, 11, 4);
		this.Shape56.setRotationPoint(3.0F, 0.0F, -2.0F);
		this.setRotation(this.Shape56, 0.0F, 0.0F, 0.0F);
		this.Shape60 = new DAModelRenderer(this, 21, 0);
		this.Shape60.addBox(0.0F, 0.0F, 0.0F, 6, 2, 1);
		this.Shape60.setRotationPoint(-3.0F, -0.6F, -4.4F);
		this.setRotation(this.Shape60, 0.5759587F, 0.0F, 0.0F);
		this.Shape61 = new DAModelRenderer(this, 21, 1);
		this.Shape61.addBox(0.0F, 0.0F, 0.0F, 6, 1, 1);
		this.Shape61.setRotationPoint(-3.0F, -1.5F, -4.3F);
		this.setRotation(this.Shape61, 0.0F, 0.0F, 0.0F);
		this.Shape62 = new DAModelRenderer(this, 13, 30);
		this.Shape62.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1);
		this.Shape62.setRotationPoint(-3.6F, 0.0F, -3.3F);
		this.setRotation(this.Shape62, 0.0F, 0.0F, 0.5061455F);
		this.Shape63 = new DAModelRenderer(this, 13, 30);
		this.Shape63.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1);
		this.Shape63.setRotationPoint(0.0F, 1.9F, -3.3F);
		this.setRotation(this.Shape63, 0.0F, 0.0F, -0.4363323F);
		this.Shape64 = new DAModelRenderer(this, 16, 30);
		this.Shape64.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
		this.Shape64.setRotationPoint(-0.6F, 1.8F, -3.3F);
		this.setRotation(this.Shape64, 0.0F, 0.0F, 0.0F);
		this.Shape65 = new DAModelRenderer(this, 36, 2);
		this.Shape65.addBox(0.0F, 0.0F, 0.0F, 8, 1, 1);
		this.Shape65.setRotationPoint(-4.0F, 10.6F, -2.8F);
		this.setRotation(this.Shape65, -0.3490659F, 0.0F, 0.0F);
		this.Shape66 = new DAModelRenderer(this, 36, 2);
		this.Shape66.addBox(0.0F, 0.0F, 0.0F, 8, 1, 1);
		this.Shape66.setRotationPoint(-4.0F, 11.0F, 2.0F);
		this.setRotation(this.Shape66, 0.3490659F, 0.0F, 0.0F);
		this.Shape67 = new DAModelRenderer(this, 36, 0);
		this.Shape67.addBox(0.0F, 0.0F, 0.0F, 1, 1, 5);
		this.Shape67.setRotationPoint(-4.0F, 10.5F, -2.5F);
		this.setRotation(this.Shape67, 0.0F, 0.0F, 0.6981317F);
		this.Shape68 = new DAModelRenderer(this, 36, 0);
		this.Shape68.addBox(0.0F, 0.0F, 0.0F, 1, 1, 5);
		this.Shape68.setRotationPoint(4.0F, 10.5F, -2.5F);
		this.setRotation(this.Shape68, 0.0F, 0.0F, 0.6981317F);
	}

	@Override
	public final void renderAll() {
		this.Shape24.render();
		this.Shape53.render();
		this.Shape54.render();
		this.Shape55.render();
		this.Shape56.render();
		this.Shape60.render();
		this.Shape61.render();
		this.Shape62.render();
		this.Shape63.render();
		this.Shape64.render();
		this.Shape65.render();
		this.Shape66.render();
		this.Shape67.render();
		this.Shape68.render();
	}
}
