package keelfy.witcher.client.renderer;

import keelfy.witcher.util.DAUtil;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

/**
 * @author keelfy
 */
public class RendererDraft implements IItemRenderer {

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
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
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			switch (type) {
			case INVENTORY:
				RendererRarity.renderDraft(item);
				break;
			default:
				break;
			}
		}
	}
}
