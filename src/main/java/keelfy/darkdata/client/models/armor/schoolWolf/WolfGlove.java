/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.client.models.armor.schoolWolf;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkdata.client.models.DAModelBase;
import keelfy.darkdata.client.models.DAModelRenderer;

@SideOnly(Side.CLIENT)
public final class WolfGlove extends DAModelBase {

	private final DAModelRenderer Shape27;
	private final DAModelRenderer Shape28;
	private final DAModelRenderer Shape29;
	private final DAModelRenderer Shape30;
	private final DAModelRenderer Shape31;
	private final DAModelRenderer Shape32;
	private final DAModelRenderer Shape33;
	private final DAModelRenderer Shape34;
	private final DAModelRenderer Shape35;
	private final DAModelRenderer Shape36;
	private final DAModelRenderer Shape37;
	private final DAModelRenderer Shape38;
	private final DAModelRenderer Shape39;

	public WolfGlove() {
		this.Shape27 = new DAModelRenderer(this, 54, 13);
		this.Shape27.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1);
		this.Shape27.setRotationPoint(4.0F, 7.0F, -2.5F);
		this.setRotation(this.Shape27, 0.0872665F, 0.0F, 0.0F);
		this.Shape28 = new DAModelRenderer(this, 19, 10);
		this.Shape28.addBox(0.0F, 0.0F, 0.0F, 4, 2, 1);
		this.Shape28.setRotationPoint(4.0F, 8.0F, -2.3F);
		this.setRotation(this.Shape28, 0.0872665F, 0.0F, 0.0F);
		this.Shape29 = new DAModelRenderer(this, 54, 13);
		this.Shape29.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4);
		this.Shape29.setRotationPoint(7.5F, 6.9F, -2.0F);
		this.setRotation(this.Shape29, 0.0F, 0.0F, 0.0872665F);
		this.Shape30 = new DAModelRenderer(this, 19, 10);
		this.Shape30.addBox(0.0F, 0.0F, 0.0F, 1, 2, 4);
		this.Shape30.setRotationPoint(7.3F, 7.9F, -2.0F);
		this.setRotation(this.Shape30, 0.0F, 0.0F, 0.0872665F);
		this.Shape31 = new DAModelRenderer(this, 54, 13);
		this.Shape31.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1);
		this.Shape31.setRotationPoint(4.0F, 7.0F, 1.5F);
		this.setRotation(this.Shape31, -0.0872665F, 0.0F, 0.0F);
		this.Shape32 = new DAModelRenderer(this, 19, 10);
		this.Shape32.addBox(0.0F, 0.0F, 0.0F, 4, 2, 1);
		this.Shape32.setRotationPoint(4.0F, 8.0F, 1.3F);
		this.setRotation(this.Shape32, -0.0872665F, 0.0F, 0.0F);
		this.Shape33 = new DAModelRenderer(this, 54, 13);
		this.Shape33.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4);
		this.Shape33.setRotationPoint(3.5F, 7.1F, -2.0F);
		this.setRotation(this.Shape33, 0.0F, 0.0F, -0.0872665F);
		this.Shape34 = new DAModelRenderer(this, 19, 10);
		this.Shape34.addBox(0.0F, 0.0F, 0.0F, 1, 2, 4);
		this.Shape34.setRotationPoint(3.7F, 8.0F, -2.0F);
		this.setRotation(this.Shape34, 0.0F, 0.0F, -0.0872665F);
		this.Shape35 = new DAModelRenderer(this, 0, 0);
		this.Shape35.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1);
		this.Shape35.setRotationPoint(4.0F, 10.0F, -2.2F);
		this.setRotation(this.Shape35, 0.0F, 0.0F, 0.0F);
		this.Shape36 = new DAModelRenderer(this, 0, 0);
		this.Shape36.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4);
		this.Shape36.setRotationPoint(7.2F, 10.0F, -2.0F);
		this.setRotation(this.Shape36, 0.0F, 0.0F, 0.0F);
		this.Shape37 = new DAModelRenderer(this, 54, 13);
		this.Shape37.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1);
		this.Shape37.setRotationPoint(4.0F, 10.0F, 1.2F);
		this.setRotation(this.Shape37, 0.0F, 0.0F, 0.0F);
		this.Shape38 = new DAModelRenderer(this, 0, 0);
		this.Shape38.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4);
		this.Shape38.setRotationPoint(3.8F, 10.0F, -2.0F);
		this.setRotation(this.Shape38, 0.0F, 0.0F, 0.0F);
		this.Shape39 = new DAModelRenderer(this, 19, 4);
		this.Shape39.addBox(0.0F, 0.0F, 0.0F, 4, 1, 4);
		this.Shape39.setRotationPoint(4.0F, 11.0F, -2.0F);
		this.setRotation(this.Shape39, 0.0F, 0.0F, 0.0F);
	}

	@Override
	public final void renderAll() {
		this.Shape27.render();
		this.Shape28.render();
		this.Shape29.render();
		this.Shape30.render();
		this.Shape31.render();
		this.Shape32.render();
		this.Shape33.render();
		this.Shape34.render();
		this.Shape35.render();
		this.Shape36.render();
		this.Shape37.render();
		this.Shape38.render();
		this.Shape39.render();
	}
}
