/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.client.renderer;

import keelfytools.KeelfyUtils;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

/**
 * @author keelfy
 */
public class RendererPotion implements IItemRenderer {

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		if(KeelfyUtils.isClientSide()) {
			switch (type) {
			case INVENTORY:
				return true;
			default:
				return false;
			}
		} else 
			return false;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		if(KeelfyUtils.isClientSide()) {
			switch (type) {
			case INVENTORY:
				RendererRarity.renderPotion(item);
				break;
			default:
				break;
			}
		}
	}
}
