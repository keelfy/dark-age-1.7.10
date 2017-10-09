/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.client.models.armor.witchunter;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkdata.client.models.DAModelBase;
import keelfy.darkdata.client.models.DAModelRenderer;

@SideOnly(Side.CLIENT)
public final class WitchunterArmorHand extends DAModelBase {

	private final DAModelRenderer Shape18;
	private final DAModelRenderer Shape19;
	private final DAModelRenderer Shape20;
	private final DAModelRenderer Shape21;
	private final DAModelRenderer Shape22;
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
	private final DAModelRenderer Shape45;

	public WitchunterArmorHand() {
		this.Shape18 = new DAModelRenderer(this, 13, 0);
		this.Shape18.addBox(0.0F, 0.0F, 0.0F, 4, 5, 1);
		this.Shape18.setRotationPoint(-8.0F, 0.0F, 1.2F);
		this.setRotation(this.Shape18, 0.0F, 0.0F, 0.0F);
		this.Shape19 = new DAModelRenderer(this, 13, 0);
		this.Shape19.addBox(0.0F, 0.0F, 0.0F, 4, 5, 1);
		this.Shape19.setRotationPoint(-8.0F, 0.0F, -2.2F);
		this.setRotation(this.Shape19, 0.0F, 0.0F, 0.0F);
		this.Shape20 = new DAModelRenderer(this, 13, 7);
		this.Shape20.addBox(0.0F, 0.0F, 0.0F, 1, 5, 4);
		this.Shape20.setRotationPoint(-8.2F, 0.0F, -2.0F);
		this.setRotation(this.Shape20, 0.0F, 0.0F, 0.0F);
		this.Shape21 = new DAModelRenderer(this, 13, 7);
		this.Shape21.addBox(0.0F, 0.0F, 0.0F, 1, 5, 4);
		this.Shape21.setRotationPoint(-4.8F, 0.0F, -2.0F);
		this.setRotation(this.Shape21, 0.0F, 0.0F, 0.0F);
		this.Shape22 = new DAModelRenderer(this, 0, 30);
		this.Shape22.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1);
		this.Shape22.setRotationPoint(-8.0F, 5.6F, 1.2F);
		this.setRotation(this.Shape22, 0.7853982F, 0.0F, 0.0F);
		this.Shape23 = new DAModelRenderer(this, 0, 30);
		this.Shape23.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1);
		this.Shape23.setRotationPoint(-8.0F, 6.8F, 1.2F);
		this.setRotation(this.Shape23, 0.7853982F, 0.0F, 0.0F);
		this.Shape24 = new DAModelRenderer(this, 0, 25);
		this.Shape24.addBox(0.0F, 0.0F, 0.0F, 1, 1, 3);
		this.Shape24.setRotationPoint(-8.0F, 4.8F, -1.0F);
		this.setRotation(this.Shape24, 0.0F, 0.0F, 0.7853982F);
		this.Shape25 = new DAModelRenderer(this, 0, 25);
		this.Shape25.addBox(0.0F, 0.0F, 0.0F, 1, 1, 3);
		this.Shape25.setRotationPoint(-8.0F, 6.0F, -1.0F);
		this.setRotation(this.Shape25, 0.0F, 0.0F, 0.7853982F);
		this.Shape26 = new DAModelRenderer(this, 0, 25);
		this.Shape26.addBox(0.0F, 0.0F, 0.0F, 1, 1, 3);
		this.Shape26.setRotationPoint(-4.0F, 4.8F, -1.0F);
		this.setRotation(this.Shape26, 0.0F, 0.0F, 0.7853982F);
		this.Shape27 = new DAModelRenderer(this, 0, 25);
		this.Shape27.addBox(0.0F, 0.0F, 0.0F, 1, 1, 3);
		this.Shape27.setRotationPoint(-4.0F, 6.0F, -1.0F);
		this.setRotation(this.Shape27, 0.0F, 0.0F, 0.7853982F);
		this.Shape28 = new DAModelRenderer(this, 31, 30);
		this.Shape28.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
		this.Shape28.setRotationPoint(-8.2F, 5.7F, -2.0F);
		this.setRotation(this.Shape28, 0.0F, 0.0F, 0.0F);
		this.Shape29 = new DAModelRenderer(this, 31, 30);
		this.Shape29.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
		this.Shape29.setRotationPoint(-4.8F, 5.7F, -2.0F);
		this.setRotation(this.Shape29, 0.0F, 0.0F, 0.0F);
		this.Shape30 = new DAModelRenderer(this, 32, 30);
		this.Shape30.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
		this.Shape30.setRotationPoint(-4.8F, 5.7F, -2.3F);
		this.setRotation(this.Shape30, 0.0F, 0.0F, 0.0F);
		this.Shape31 = new DAModelRenderer(this, 31, 30);
		this.Shape31.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
		this.Shape31.setRotationPoint(-8.2F, 5.7F, -2.3F);
		this.setRotation(this.Shape31, 0.0F, 0.0F, 0.0F);
		this.Shape32 = new DAModelRenderer(this, 29, 30);
		this.Shape32.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1);
		this.Shape32.setRotationPoint(-8.0F, 5.7F, -2.3F);
		this.setRotation(this.Shape32, 0.0F, 0.0F, 0.0F);
		this.Shape45 = new DAModelRenderer(this, 48, 27);
		this.Shape45.addBox(0.0F, 0.0F, 0.0F, 4, 1, 4);
		this.Shape45.setRotationPoint(-8.0F, 0.0F, -2.0F);
		this.setRotation(this.Shape45, 0.0F, 0.0F, 0.0F);
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
		this.Shape25.render();
		this.Shape26.render();
		this.Shape27.render();
		this.Shape28.render();
		this.Shape29.render();
		this.Shape30.render();
		this.Shape31.render();
		this.Shape32.render();
		this.Shape45.render();
	}
}
