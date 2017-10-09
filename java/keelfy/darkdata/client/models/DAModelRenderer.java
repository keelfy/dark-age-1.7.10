/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.client.models;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelRenderer;

/**
 * @author keelfy
 * @created 13 июл. 2017 г.
 */
@SideOnly(Side.CLIENT)
public final class DAModelRenderer extends ModelRenderer {

	public DAModelRenderer(final DAModelBase model, final int textureOffsetX, final int textureOffsetY) {
		super(model, textureOffsetX, textureOffsetY);

		this.setTextureSize(64, 32);
		this.mirror = true;
	}

	public DAModelRenderer(final DAModelBiped model, final int textureOffsetX, final int textureOffsetY) {
		super(model, textureOffsetX, textureOffsetY);

		this.setTextureSize(64, 32);
		this.mirror = true;
	}

	public final void render() {
		super.render(0.0625F);
	}
}
