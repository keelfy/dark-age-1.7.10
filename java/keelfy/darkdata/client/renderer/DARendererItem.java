package keelfy.darkdata.client.renderer;

import keelfy.darkdata.constants.EnumItemType;
import keelfy.darkdata.items.DAItem;
import keelfyutils.KUtils;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

/**
 * @author keelfy
 * @created 25 июл. 2017 г.
 */
public final class DARendererItem implements IItemRenderer {

	private EnumItemType itemType;

	public DARendererItem(final EnumItemType type) {
		this.itemType = type;
	}

	public static final void register(final DAItem item, final EnumItemType itemType) {
		if (KUtils.PROTECT_CLIENT) {
			MinecraftForgeClient.registerItemRenderer(item, new DARendererItem(itemType));
		}
	}

	@Override
	public boolean shouldUseRenderHelper(final ItemRenderType type, final ItemStack item,
			final ItemRendererHelper helper) {
		return false;
	}

	@Override
	public void renderItem(final ItemRenderType type, final ItemStack item, final Object... data) {
		if (type != ItemRenderType.INVENTORY)
			return;

		switch (itemType) {
			case ARMOR:
				DARendererRarity.renderArmor(item);
				break;
			case ELIXIR:
				DARendererRarity.renderPotion(item);
				break;
			case FOOD:
				DARendererRarity.renderFood(item);
				break;
			default:
				break;
		}
	}

	@Override
	public boolean handleRenderType(final ItemStack item, final ItemRenderType type) {
		if (type == ItemRenderType.INVENTORY)
			return true;
		return false;
	}
}
