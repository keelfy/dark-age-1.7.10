package keelfy.witcher.item;

import java.util.List;

import cpw.mods.fml.common.FMLCommonHandler;
import keelfy.witcher.client.renderer.RendererDraft;
import keelfy.witcher.util.DAUtil;
import net.minecraftforge.client.MinecraftForgeClient;

/**
 * @author keelfy
 */
public class Draft extends DAItem {

	public Draft(ItemRarity rarity, float weight, String... addInfo) {
		super(rarity, weight, addInfo);
		
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			MinecraftForgeClient.registerItemRenderer(this, new RendererDraft());
		}
	}
}
