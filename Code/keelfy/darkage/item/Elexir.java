package keelfy.darkage.item;

import keelfy.darkage.client.renderer.RendererPotion;
import keelfy.darkage.entity.player.DAPlayer;
import keelfy.darkage.entity.player.DAPlayerUtil.Property;
import keelfy.darkage.entity.player.effect.DAEffect;
import keelfy.darkage.util.DATab;
import keelfy.darkage.util.DAUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;

/**
 * @author keelfy
 */
public class Elexir extends DAItem implements IFastUsable {

	private Effect effect;

	public Elexir(ItemRarity rarity, Effect effect, String... addInfo) {
		super(rarity, 0.5F, addInfo);
		this.effect = effect;

		this.setCreativeTab(DATab.tabPotion);
		this.setMaxStackSize(3);
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			MinecraftForgeClient.registerItemRenderer(this, new RendererPotion());
		}
	}

	@Override
	// TODO: Разобраться с наложением эффектов друг на друга
	public ItemStack use(ItemStack is, World world, EntityPlayer player, int slot) {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			if(!world.isRemote) {
				DAPlayer wcp = DAPlayer.get(player);
				
				if(wcp != null && wcp.get(Property.INTOXICATION) != wcp.getWitcherMaxIntox()) {
					world.playSoundAtEntity(player, "random.drink", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
					
					DAEffect wce = DAEffect.get(player);
					if(wce != null && wcp.get(Property.INTOXICATION) + 25F <= wcp.getWitcherMaxIntox()) {
						if(effect != Effect.БЕЛЫЙ_МЕД) wcp.changeIntox(25F);
						
						if(effect == Effect.БЕЛЫЙ_МЕД) {
							wcp.resetWitcherIntox();
						} else if(effect == Effect.ЛАСТОЧКА) {
							wce.getHeal().perform(180, 2);
						} else if (effect == Effect.КОСАТКА) {
							player.addPotionEffect(new PotionEffect(Potion.waterBreathing.getId(), 180, 2));
						} else if (effect == Effect.КОШКА) {
							player.addPotionEffect(new PotionEffect(Potion.nightVision.getId(), 180, 2));
						} else if (effect == Effect.ПУРГА) {
							player.addPotionEffect(new PotionEffect(Potion.moveSpeed.getId(), 180, 2));
						} else if (effect == Effect.ГРОМ) {
							player.addPotionEffect(new PotionEffect(Potion.damageBoost.getId(), 180, 2));
						}
					}
					
					if (!player.capabilities.isCreativeMode) {
						--player.inventory.mainInventory[slot].stackSize;
						if (player.inventory.mainInventory[slot].stackSize <= 0) {
							player.inventory.mainInventory[slot] = null;
						}
					}
				}
			}
		}
		return is;
	}

	public static enum Effect {
		БЕЛЫЙ_МЕД, ЛАСТОЧКА, КОСАТКА, КОШКА, ПУРГА, ГРОМ;
	}
}
