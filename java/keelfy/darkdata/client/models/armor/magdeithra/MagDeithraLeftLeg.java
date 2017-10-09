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
public final class MagDeithraLeftLeg extends DAModelBase {

	private final DAModelRenderer Shape43;
	private final DAModelRenderer Shape44;
	private final DAModelRenderer Shape45;
	private final DAModelRenderer Shape46;
	private final DAModelRenderer Shape47;
	private final DAModelRenderer Shape48;
	private final DAModelRenderer Shape49;
	private final DAModelRenderer Shape50;
	private final DAModelRenderer Shape57;
	private final DAModelRenderer Shape58;
	private final DAModelRenderer Shape59;
	private final DAModelRenderer Shape60;
	private final DAModelRenderer Shape70;

	public MagDeithraLeftLeg() {
		this.Shape43 = new DAModelRenderer(this, 0, 0);
		this.Shape43.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
		this.Shape43.setRotationPoint(0.8F, 11.45F, -3.0F);
		this.setRotation(this.Shape43, 0.0F, 0.0F, 0.1396263F);
		this.Shape44 = new DAModelRenderer(this, 0, 0);
		this.Shape44.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
		this.Shape44.setRotationPoint(2.5F, 11.7F, -3.0F);
		this.setRotation(this.Shape44, 0.0F, 0.0F, 0.1396263F);
		this.Shape45 = new DAModelRenderer(this, 0, 0);
		this.Shape45.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
		this.Shape45.setRotationPoint(4.0F, 11.9F, -3.0F);
		this.setRotation(this.Shape45, 0.0F, 0.0F, 0.1396263F);
		this.Shape46 = new DAModelRenderer(this, 0, 0);
		this.Shape46.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
		this.Shape46.setRotationPoint(4.0F, 11.9F, -1.5F);
		this.setRotation(this.Shape46, 0.0F, 0.0F, 0.1396263F);
		this.Shape47 = new DAModelRenderer(this, 0, 0);
		this.Shape47.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
		this.Shape47.setRotationPoint(4.0F, 11.9F, 0.4F);
		this.setRotation(this.Shape47, 0.0F, 0.0F, 0.1396263F);
		this.Shape48 = new DAModelRenderer(this, 0, 0);
		this.Shape48.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
		this.Shape48.setRotationPoint(4.0F, 11.9F, 2.0F);
		this.setRotation(this.Shape48, 0.0F, 0.0F, 0.1396263F);
		this.Shape49 = new DAModelRenderer(this, 0, 0);
		this.Shape49.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
		this.Shape49.setRotationPoint(2.5F, 11.7F, 2.0F);
		this.setRotation(this.Shape49, 0.0F, 0.0F, 0.1396263F);
		this.Shape50 = new DAModelRenderer(this, 0, 0);
		this.Shape50.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
		this.Shape50.setRotationPoint(0.8F, 11.5F, 2.0F);
		this.setRotation(this.Shape50, 0.0F, 0.0F, 0.1396263F);
		this.Shape57 = new DAModelRenderer(this, 11, 23);
		this.Shape57.addBox(0.0F, 0.0F, 0.0F, 4, 8, 1);
		this.Shape57.setRotationPoint(0.5F, 12.0F, -2.7F);
		this.setRotation(this.Shape57, -0.0698132F, 0.0F, 0.0F);
		this.Shape58 = new DAModelRenderer(this, 11, 23);
		this.Shape58.addBox(0.0F, 0.0F, 0.0F, 4, 8, 1);
		this.Shape58.setRotationPoint(0.0F, 12.0F, 1.7F);
		this.setRotation(this.Shape58, 0.0698132F, 0.0F, 0.0F);
		this.Shape59 = new DAModelRenderer(this, 11, 20);
		this.Shape59.addBox(0.0F, 0.0F, 0.0F, 1, 8, 4);
		this.Shape59.setRotationPoint(3.6F, 12.0F, -2.0F);
		this.setRotation(this.Shape59, 0.0F, 0.0F, -0.0698132F);
		this.Shape60 = new DAModelRenderer(this, 22, 23);
		this.Shape60.addBox(0.0F, 0.0F, 0.0F, 1, 8, 1);
		this.Shape60.setRotationPoint(0.0F, 12.0F, -2.4F);
		this.setRotation(this.Shape60, -0.0698132F, 0.0F, 0.0F);
		this.Shape70 = new DAModelRenderer(this, 27, 26);
		this.Shape70.addBox(0.0F, 0.0F, 0.0F, 5, 1, 5);
		this.Shape70.setRotationPoint(0.0F, 11.3F, -2.5F);
		this.setRotation(this.Shape70, 0.0F, 0.0F, 0.1396263F);
	}

	@Override
	public final void renderAll() {
		this.Shape43.render();
		this.Shape44.render();
		this.Shape45.render();
		this.Shape46.render();
		this.Shape47.render();
		this.Shape48.render();
		this.Shape49.render();
		this.Shape50.render();
		this.Shape57.render();
		this.Shape58.render();
		this.Shape59.render();
		this.Shape60.render();
		this.Shape70.render();
	}
}
