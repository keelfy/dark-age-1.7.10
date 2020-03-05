/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.client.models.armor.schoolGriffin;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkdata.client.models.DAModelBase;
import keelfy.darkdata.client.models.DAModelRenderer;

@SideOnly(Side.CLIENT)
public final class GriffinGlove extends DAModelBase {

	private final DAModelRenderer Shape23;
	private final DAModelRenderer Shape24;
	private final DAModelRenderer Shape25;
	private final DAModelRenderer Shape26;
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
	private final DAModelRenderer Shape60;

	public GriffinGlove() {
		this.Shape23 = new DAModelRenderer(this, 52, 26);
		this.Shape23.addBox(0.0F, 0.0F, 0.0F, 1, 1, 5);
		this.Shape23.setRotationPoint(7.2F, 6.0F, -2.5F);
		this.setRotation(this.Shape23, 0.0F, 0.0F, 0.0F);
		this.Shape24 = new DAModelRenderer(this, 47, 26);
		this.Shape24.addBox(0.0F, 0.0F, 0.0F, 1, 5, 1);
		this.Shape24.setRotationPoint(6.2F, 6.0F, -2.5F);
		this.setRotation(this.Shape24, 0.0F, 0.0F, 0.0F);
		this.Shape25 = new DAModelRenderer(this, 47, 26);
		this.Shape25.addBox(0.0F, 0.0F, 0.0F, 1, 5, 1);
		this.Shape25.setRotationPoint(6.2F, 6.0F, 1.5F);
		this.setRotation(this.Shape25, 0.0F, 0.0F, 0.0F);
		this.Shape26 = new DAModelRenderer(this, 52, 26);
		this.Shape26.addBox(0.0F, 0.0F, 0.0F, 1, 1, 5);
		this.Shape26.setRotationPoint(7.2F, 10.0F, -2.5F);
		this.setRotation(this.Shape26, 0.0F, 0.0F, 0.0F);
		this.Shape27 = new DAModelRenderer(this, 43, 15);
		this.Shape27.addBox(0.0F, 0.0F, 0.0F, 1, 3, 4);
		this.Shape27.setRotationPoint(7.1F, 7.0F, -2.3F);
		this.setRotation(this.Shape27, 0.0F, 0.0F, 0.0F);
		this.Shape28 = new DAModelRenderer(this, 43, 15);
		this.Shape28.addBox(0.0F, 0.0F, 0.0F, 1, 3, 4);
		this.Shape28.setRotationPoint(7.1F, 7.0F, -1.6F);
		this.setRotation(this.Shape28, 0.0F, 0.0F, 0.0F);
		this.Shape29 = new DAModelRenderer(this, 47, 27);
		this.Shape29.addBox(0.0F, 0.0F, 0.0F, 1, 4, 1);
		this.Shape29.setRotationPoint(5.2F, 6.5F, -2.5F);
		this.setRotation(this.Shape29, 0.0F, 0.0F, 0.0F);
		this.Shape30 = new DAModelRenderer(this, 47, 27);
		this.Shape30.addBox(0.0F, 0.0F, 0.0F, 1, 4, 1);
		this.Shape30.setRotationPoint(5.2F, 6.5F, 1.5F);
		this.setRotation(this.Shape30, 0.0F, 0.0F, 0.0F);
		this.Shape31 = new DAModelRenderer(this, 40, 30);
		this.Shape31.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1);
		this.Shape31.setRotationPoint(4.0F, 6.5F, -2.5F);
		this.setRotation(this.Shape31, 0.0F, 0.0F, 0.0F);
		this.Shape32 = new DAModelRenderer(this, 40, 30);
		this.Shape32.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1);
		this.Shape32.setRotationPoint(4.0F, 9.5F, -2.5F);
		this.setRotation(this.Shape32, 0.0F, 0.0F, 0.0F);
		this.Shape33 = new DAModelRenderer(this, 40, 30);
		this.Shape33.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1);
		this.Shape33.setRotationPoint(4.0F, 6.5F, 1.5F);
		this.setRotation(this.Shape33, 0.0F, 0.0F, 0.0F);
		this.Shape34 = new DAModelRenderer(this, 40, 30);
		this.Shape34.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1);
		this.Shape34.setRotationPoint(4.0F, 9.5F, 1.5F);
		this.setRotation(this.Shape34, 0.0F, 0.0F, 0.0F);
		this.Shape35 = new DAModelRenderer(this, 47, 28);
		this.Shape35.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1);
		this.Shape35.setRotationPoint(4.0F, 7.0F, -2.5F);
		this.setRotation(this.Shape35, 0.0F, 0.0F, 0.0F);
		this.Shape36 = new DAModelRenderer(this, 47, 28);
		this.Shape36.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1);
		this.Shape36.setRotationPoint(4.0F, 7.0F, 1.5F);
		this.setRotation(this.Shape36, 0.0F, 0.0F, 0.0F);
		this.Shape37 = new DAModelRenderer(this, 43, 18);
		this.Shape37.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1);
		this.Shape37.setRotationPoint(4.5F, 7.0F, -2.4F);
		this.setRotation(this.Shape37, 0.0F, 0.0F, 0.0F);
		this.Shape38 = new DAModelRenderer(this, 43, 18);
		this.Shape38.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1);
		this.Shape38.setRotationPoint(4.5F, 7.0F, 1.4F);
		this.setRotation(this.Shape38, 0.0F, 0.0F, 0.0F);
		this.Shape60 = new DAModelRenderer(this, 43, 15);
		this.Shape60.addBox(0.0F, 0.0F, 0.0F, 1, 3, 4);
		this.Shape60.setRotationPoint(4.0F, 7.0F, -2.0F);
		this.setRotation(this.Shape60, 0.0F, 0.0F, 0.0F);
	}

	@Override
	public final void renderAll() {
		this.Shape23.render();
		this.Shape24.render();
		this.Shape25.render();
		this.Shape26.render();
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
		this.Shape60.render();
	}
}
