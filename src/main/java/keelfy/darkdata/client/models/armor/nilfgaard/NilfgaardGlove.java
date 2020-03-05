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
public final class NilfgaardGlove extends DAModelBase {

	private final DAModelRenderer Shape18;
	private final DAModelRenderer Shape19;
	private final DAModelRenderer Shape20;
	private final DAModelRenderer Shape21;
	private final DAModelRenderer Shape22;
	private final DAModelRenderer Shape23;
	private final DAModelRenderer Shape24;
	private final DAModelRenderer Shape26;
	private final DAModelRenderer Shape27;
	private final DAModelRenderer Shape28;
	private final DAModelRenderer Shape29;
	private final DAModelRenderer Shape30;
	private final DAModelRenderer Shape31;

	public NilfgaardGlove() {
		this.Shape18 = new DAModelRenderer(this, 32, 21);
		this.Shape18.addBox(0.0F, 0.0F, 0.0F, 4, 3, 1);
		this.Shape18.setRotationPoint(4.0F, 8.0F, -2.4F);
		this.setRotation(this.Shape18, 0.122173F, 0.0F, 0.0F);
		this.Shape19 = new DAModelRenderer(this, 32, 21);
		this.Shape19.addBox(0.0F, 0.0F, 0.0F, 4, 3, 1);
		this.Shape19.setRotationPoint(4.0F, 8.0F, 1.4F);
		this.setRotation(this.Shape19, -0.122173F, 0.0F, 0.0F);
		this.Shape20 = new DAModelRenderer(this, 33, 18);
		this.Shape20.addBox(0.0F, 0.0F, 0.0F, 1, 3, 4);
		this.Shape20.setRotationPoint(7.6F, 7.9F, -2.0F);
		this.setRotation(this.Shape20, 0.0F, 0.0F, 0.1745329F);
		this.Shape21 = new DAModelRenderer(this, 37, 0);
		this.Shape21.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1);
		this.Shape21.setRotationPoint(4.0F, 11.0F, -2.2F);
		this.setRotation(this.Shape21, 0.0F, 0.0F, 0.0F);
		this.Shape22 = new DAModelRenderer(this, 36, 0);
		this.Shape22.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1);
		this.Shape22.setRotationPoint(4.0F, 11.0F, 1.2F);
		this.setRotation(this.Shape22, 0.0F, 0.0F, 0.0F);
		this.Shape23 = new DAModelRenderer(this, 36, 0);
		this.Shape23.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4);
		this.Shape23.setRotationPoint(7.2F, 11.0F, -2.0F);
		this.setRotation(this.Shape23, 0.0F, 0.0F, 0.0F);
		this.Shape24 = new DAModelRenderer(this, 36, 0);
		this.Shape24.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4);
		this.Shape24.setRotationPoint(3.8F, 11.0F, -2.0F);
		this.setRotation(this.Shape24, 0.0F, 0.0F, 0.0F);
		this.Shape26 = new DAModelRenderer(this, 36, 0);
		this.Shape26.addBox(0.0F, 0.0F, 0.0F, 4, 1, 4);
		this.Shape26.setRotationPoint(4.0F, 11.2F, -2.0F);
		this.setRotation(this.Shape26, 0.0F, 0.0F, 0.0F);
		this.Shape27 = new DAModelRenderer(this, 19, 11);
		this.Shape27.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
		this.Shape27.setRotationPoint(6.3F, 11.0F, -2.4F);
		this.setRotation(this.Shape27, 0.0F, 0.0F, 0.0F);
		this.Shape28 = new DAModelRenderer(this, 19, 11);
		this.Shape28.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
		this.Shape28.setRotationPoint(7.4F, 11.0F, -1.7F);
		this.setRotation(this.Shape28, 0.0F, 0.0F, 0.0F);
		this.Shape29 = new DAModelRenderer(this, 19, 11);
		this.Shape29.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
		this.Shape29.setRotationPoint(7.4F, 11.0F, -0.5F);
		this.setRotation(this.Shape29, 0.0F, 0.0F, 0.0F);
		this.Shape30 = new DAModelRenderer(this, 19, 11);
		this.Shape30.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
		this.Shape30.setRotationPoint(7.4F, 11.0F, 0.8F);
		this.setRotation(this.Shape30, 0.0F, 0.0F, 0.0F);
		this.Shape31 = new DAModelRenderer(this, 19, 11);
		this.Shape31.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
		this.Shape31.setRotationPoint(6.6F, 11.0F, 1.4F);
		this.setRotation(this.Shape31, 0.0F, 0.0F, 0.0F);
	}

	@Override
	public final void renderAll() {
		this.Shape18.render();
		this.Shape19.render();
		this.Shape20.render();
		this.Shape21.render();
		this.Shape22.render();
		this.Shape23.render();
		this.Shape24.render();
		this.Shape26.render();
		this.Shape27.render();
		this.Shape28.render();
		this.Shape29.render();
		this.Shape30.render();
		this.Shape31.render();
	}
}
