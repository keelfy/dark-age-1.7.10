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
public final class NilfgaardHead extends DAModelBase {

	private final DAModelRenderer Shape32;
	private final DAModelRenderer Shape33;
	private final DAModelRenderer Shape34;
	private final DAModelRenderer Shape35;
	private final DAModelRenderer Shape36;
	private final DAModelRenderer Shape37;
	private final DAModelRenderer Shape38;
	private final DAModelRenderer Shape39;
	private final DAModelRenderer Shape40;
	private final DAModelRenderer Shape41;
	private final DAModelRenderer Shape42;
	private final DAModelRenderer Shape43;
	private final DAModelRenderer Shape44;
	private final DAModelRenderer Shape45;
	private final DAModelRenderer Shape46;
	private final DAModelRenderer Shape47;
	private final DAModelRenderer Shape48;
	private final DAModelRenderer Shape49;
	private final DAModelRenderer Shape50;
	private final DAModelRenderer Shape51;
	private final DAModelRenderer Shape52;
	private final DAModelRenderer Shape57;
	private final DAModelRenderer Shape58;
	private final DAModelRenderer Shape59;
	private final DAModelRenderer Shape81;
	private final DAModelRenderer Shape82;
	private final DAModelRenderer Shape83;
	private final DAModelRenderer Shape84;
	private final DAModelRenderer Shape85;
	private final DAModelRenderer Shape86;
	private final DAModelRenderer Shape87;
	private final DAModelRenderer Shape88;
	private final DAModelRenderer Shape89;
	private final DAModelRenderer Shape90;
	private final DAModelRenderer Shape91;
	private final DAModelRenderer Shape92;
	private final DAModelRenderer Shape93;

	public NilfgaardHead() {
		this.Shape32 = new DAModelRenderer(this, 0, 16);
		this.Shape32.addBox(0.0F, 0.0F, 0.0F, 8, 1, 8);
		this.Shape32.setRotationPoint(-4.0F, -8.2F, -4.0F);
		this.setRotation(this.Shape32, 0.0F, 0.0F, 0.0F);
		this.Shape33 = new DAModelRenderer(this, 36, 0);
		this.Shape33.addBox(0.0F, 0.0F, 0.0F, 1, 6, 8);
		this.Shape33.setRotationPoint(-4.2F, -8.0F, -4.0F);
		this.setRotation(this.Shape33, 0.0F, 0.0F, 0.0F);
		this.Shape34 = new DAModelRenderer(this, 36, 0);
		this.Shape34.addBox(0.0F, 0.0F, 0.0F, 1, 6, 8);
		this.Shape34.setRotationPoint(3.2F, -8.0F, -4.0F);
		this.setRotation(this.Shape34, 0.0F, 0.0F, 0.0F);
		this.Shape35 = new DAModelRenderer(this, 36, 0);
		this.Shape35.addBox(0.0F, 0.0F, 0.0F, 8, 8, 1);
		this.Shape35.setRotationPoint(-4.0F, -8.0F, 3.2F);
		this.setRotation(this.Shape35, 0.0F, 0.0F, 0.0F);
		this.Shape36 = new DAModelRenderer(this, 11, 16);
		this.Shape36.addBox(0.0F, 0.0F, 0.0F, 1, 1, 8);
		this.Shape36.setRotationPoint(-4.2F, -3.0F, -4.0F);
		this.setRotation(this.Shape36, -0.2617994F, 0.0F, 0.0F);
		this.Shape37 = new DAModelRenderer(this, 8, 20);
		this.Shape37.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4);
		this.Shape37.setRotationPoint(-4.2F, -2.0F, -0.1F);
		this.setRotation(this.Shape37, 0.0F, 0.0F, 0.0F);
		this.Shape38 = new DAModelRenderer(this, 8, 20);
		this.Shape38.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4);
		this.Shape38.setRotationPoint(3.2F, -2.0F, -0.1F);
		this.setRotation(this.Shape38, 0.0F, 0.0F, 0.0F);
		this.Shape39 = new DAModelRenderer(this, 11, 16);
		this.Shape39.addBox(0.0F, 0.0F, 0.0F, 1, 1, 8);
		this.Shape39.setRotationPoint(3.2F, -3.0F, -4.0F);
		this.setRotation(this.Shape39, -0.2617994F, 0.0F, 0.0F);
		this.Shape40 = new DAModelRenderer(this, 36, 0);
		this.Shape40.addBox(0.0F, 0.0F, 0.0F, 8, 2, 1);
		this.Shape40.setRotationPoint(-4.0F, -8.0F, -4.4F);
		this.setRotation(this.Shape40, 0.0F, 0.0F, 0.0F);
		this.Shape41 = new DAModelRenderer(this, 27, 11);
		this.Shape41.addBox(0.0F, 0.0F, 0.0F, 1, 1, 3);
		this.Shape41.setRotationPoint(4.0F, -7.5F, -4.0F);
		this.setRotation(this.Shape41, 0.0F, 0.0F, -0.3316126F);
		this.Shape42 = new DAModelRenderer(this, 27, 11);
		this.Shape42.addBox(0.0F, 0.0F, 0.0F, 1, 1, 3);
		this.Shape42.setRotationPoint(4.0F, -7.8F, -1.0F);
		this.setRotation(this.Shape42, 0.0F, 0.0F, 0.3316126F);
		this.Shape43 = new DAModelRenderer(this, 27, 11);
		this.Shape43.addBox(0.0F, 0.0F, 0.0F, 1, 1, 3);
		this.Shape43.setRotationPoint(4.0F, -7.5F, 2.0F);
		this.setRotation(this.Shape43, 0.0F, 0.0F, -0.3316126F);
		this.Shape44 = new DAModelRenderer(this, 27, 11);
		this.Shape44.addBox(0.0F, 0.0F, 0.0F, 1, 1, 3);
		this.Shape44.setRotationPoint(-5.0F, -7.5F, -4.0F);
		this.setRotation(this.Shape44, 0.0F, 0.0F, -0.3316126F);
		this.Shape45 = new DAModelRenderer(this, 27, 11);
		this.Shape45.addBox(0.0F, 0.0F, 0.0F, 1, 1, 3);
		this.Shape45.setRotationPoint(-5.0F, -7.5F, 2.0F);
		this.setRotation(this.Shape45, 0.0F, 0.0F, -0.3316126F);
		this.Shape46 = new DAModelRenderer(this, 27, 11);
		this.Shape46.addBox(0.0F, 0.0F, 0.0F, 1, 1, 3);
		this.Shape46.setRotationPoint(-5.0F, -7.8F, -1.0F);
		this.setRotation(this.Shape46, 0.0F, 0.0F, 0.3316126F);
		this.Shape47 = new DAModelRenderer(this, 27, 11);
		this.Shape47.addBox(0.0F, 0.0F, 0.0F, 1, 1, 3);
		this.Shape47.setRotationPoint(-4.5F, -7.5F, -4.0F);
		this.setRotation(this.Shape47, 0.0F, 1.570796F, 0.3316126F);
		this.Shape48 = new DAModelRenderer(this, 27, 11);
		this.Shape48.addBox(0.0F, 0.0F, 0.0F, 1, 1, 3);
		this.Shape48.setRotationPoint(1.5F, -7.5F, -4.0F);
		this.setRotation(this.Shape48, 0.0F, 1.570796F, 0.3316126F);
		this.Shape49 = new DAModelRenderer(this, 27, 11);
		this.Shape49.addBox(0.0F, 0.0F, 0.0F, 1, 1, 3);
		this.Shape49.setRotationPoint(-4.5F, -7.5F, 5.0F);
		this.setRotation(this.Shape49, 0.0F, 1.570796F, 0.3316126F);
		this.Shape50 = new DAModelRenderer(this, 27, 11);
		this.Shape50.addBox(0.0F, 0.0F, 0.0F, 1, 1, 3);
		this.Shape50.setRotationPoint(1.5F, -7.5F, 5.0F);
		this.setRotation(this.Shape50, 0.0F, 1.570796F, 0.3316126F);
		this.Shape51 = new DAModelRenderer(this, 27, 11);
		this.Shape51.addBox(0.0F, 0.0F, 0.0F, 1, 1, 3);
		this.Shape51.setRotationPoint(-1.5F, -7.5F, -4.5F);
		this.setRotation(this.Shape51, 0.0F, 1.570796F, 0.3316126F);
		this.Shape52 = new DAModelRenderer(this, 27, 11);
		this.Shape52.addBox(0.0F, 0.0F, 0.0F, 1, 1, 3);
		this.Shape52.setRotationPoint(-1.5F, -7.5F, 4.5F);
		this.setRotation(this.Shape52, 0.0F, 1.570796F, 0.3316126F);
		this.Shape57 = new DAModelRenderer(this, 7, 14);
		this.Shape57.addBox(0.0F, 0.0F, 0.0F, 1, 3, 8);
		this.Shape57.setRotationPoint(-4.0F, -6.0F, -4.0F);
		this.setRotation(this.Shape57, 0.0F, 0.0F, 0.2443461F);
		this.Shape58 = new DAModelRenderer(this, 7, 14);
		this.Shape58.addBox(0.0F, 0.0F, 0.0F, 1, 3, 8);
		this.Shape58.setRotationPoint(3.0F, -6.0F, -4.0F);
		this.setRotation(this.Shape58, 0.0F, 0.0F, -0.2443461F);
		this.Shape59 = new DAModelRenderer(this, 9, 14);
		this.Shape59.addBox(0.0F, 0.0F, 0.0F, 1, 3, 8);
		this.Shape59.setRotationPoint(-4.0F, -6.0F, 4.0F);
		this.setRotation(this.Shape59, 0.0F, 1.570796F, 0.2443461F);
		this.Shape81 = new DAModelRenderer(this, 39, 0);
		this.Shape81.addBox(0.0F, 0.0F, 0.0F, 1, 7, 1);
		this.Shape81.setRotationPoint(3.5F, -14.0F, -1.0F);
		this.setRotation(this.Shape81, 0.0F, 0.0F, 0.0F);
		this.Shape82 = new DAModelRenderer(this, 39, 0);
		this.Shape82.addBox(0.0F, 0.0F, 0.0F, 1, 7, 1);
		this.Shape82.setRotationPoint(-4.5F, -14.0F, -1.0F);
		this.setRotation(this.Shape82, 0.0F, 0.0F, 0.0F);
		this.Shape83 = new DAModelRenderer(this, 38, 0);
		this.Shape83.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
		this.Shape83.setRotationPoint(-4.5F, -14.3F, -1.3F);
		this.setRotation(this.Shape83, 0.4886922F, 0.0F, 0.0F);
		this.Shape84 = new DAModelRenderer(this, 38, 0);
		this.Shape84.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
		this.Shape84.setRotationPoint(3.5F, -14.3F, -1.3F);
		this.setRotation(this.Shape84, 0.4886922F, 0.0F, 0.0F);
		this.Shape85 = new DAModelRenderer(this, 37, 0);
		this.Shape85.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4);
		this.Shape85.setRotationPoint(3.5F, -14.0F, -1.0F);
		this.setRotation(this.Shape85, 0.2792527F, 0.0F, 0.0F);
		this.Shape86 = new DAModelRenderer(this, 37, 0);
		this.Shape86.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4);
		this.Shape86.setRotationPoint(3.5F, -13.0F, -1.0F);
		this.setRotation(this.Shape86, 0.2792527F, 0.0F, 0.0F);
		this.Shape87 = new DAModelRenderer(this, 37, 0);
		this.Shape87.addBox(0.0F, 0.0F, 0.0F, 1, 1, 3);
		this.Shape87.setRotationPoint(3.5F, -12.0F, -1.0F);
		this.setRotation(this.Shape87, 0.2792527F, 0.0F, 0.0F);
		this.Shape88 = new DAModelRenderer(this, 37, 0);
		this.Shape88.addBox(0.0F, 0.0F, 0.0F, 1, 1, 3);
		this.Shape88.setRotationPoint(3.5F, -11.0F, -1.0F);
		this.setRotation(this.Shape88, 0.2792527F, 0.0F, 0.0F);
		this.Shape89 = new DAModelRenderer(this, 37, 0);
		this.Shape89.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
		this.Shape89.setRotationPoint(3.5F, -10.2F, -0.4F);
		this.setRotation(this.Shape89, 0.2792527F, 0.0F, 0.0F);
		this.Shape90 = new DAModelRenderer(this, 37, 0);
		this.Shape90.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4);
		this.Shape90.setRotationPoint(-4.5F, -14.0F, -1.0F);
		this.setRotation(this.Shape90, 0.2792527F, 0.0F, 0.0F);
		this.Shape91 = new DAModelRenderer(this, 37, 0);
		this.Shape91.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4);
		this.Shape91.setRotationPoint(-4.5F, -13.0F, -1.0F);
		this.setRotation(this.Shape91, 0.2792527F, 0.0F, 0.0F);
		this.Shape92 = new DAModelRenderer(this, 37, 0);
		this.Shape92.addBox(0.0F, 0.0F, 0.0F, 1, 1, 3);
		this.Shape92.setRotationPoint(-4.5F, -12.0F, -1.0F);
		this.setRotation(this.Shape92, 0.2792527F, 0.0F, 0.0F);
		this.Shape93 = new DAModelRenderer(this, 37, 0);
		this.Shape93.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
		this.Shape93.setRotationPoint(-4.5F, -11.2F, -0.4F);
		this.setRotation(this.Shape93, 0.2792527F, 0.0F, 0.0F);
	}

	@Override
	public final void renderAll() {
		this.Shape32.render();
		this.Shape33.render();
		this.Shape34.render();
		this.Shape35.render();
		this.Shape36.render();
		this.Shape37.render();
		this.Shape38.render();
		this.Shape39.render();
		this.Shape40.render();
		this.Shape41.render();
		this.Shape42.render();
		this.Shape43.render();
		this.Shape44.render();
		this.Shape45.render();
		this.Shape46.render();
		this.Shape47.render();
		this.Shape48.render();
		this.Shape49.render();
		this.Shape50.render();
		this.Shape51.render();
		this.Shape52.render();
		this.Shape57.render();
		this.Shape58.render();
		this.Shape59.render();
		this.Shape81.render();
		this.Shape82.render();
		this.Shape83.render();
		this.Shape84.render();
		this.Shape85.render();
		this.Shape86.render();
		this.Shape87.render();
		this.Shape88.render();
		this.Shape89.render();
		this.Shape90.render();
		this.Shape91.render();
		this.Shape92.render();
		this.Shape93.render();
	}
}