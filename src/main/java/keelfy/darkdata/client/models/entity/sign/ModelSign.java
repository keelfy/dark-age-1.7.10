package keelfy.darkdata.client.models.entity.sign;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkdata.client.models.DAModelBase;
import keelfy.darkdata.client.models.DAModelRenderer;

@SideOnly(Side.CLIENT)
public final class ModelSign extends DAModelBase {

	private final DAModelRenderer Shape1;

	public ModelSign() {

		this.Shape1 = new DAModelRenderer(this, 0, 0);
		this.Shape1.addBox(0.0F, 0.0F, 0.0F, 31, 11, 1);
		this.Shape1.setRotationPoint(-31.0F, 13.0F, -3.3F);

		this.setRotation(this.Shape1, 0.0F, -0.1396263F, 0.0F);
	}

	@Override
	public final void renderAll() {
		this.Shape1.render();
	}
}
