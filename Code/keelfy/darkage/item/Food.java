package keelfy.darkage.item;

import java.util.List;

import keelfy.api.Brush;
import keelfy.darkage.client.renderer.RendererFood;
import keelfy.darkage.entity.player.DAPlayer;
import keelfy.darkage.util.DATab;
import keelfy.darkage.util.DAUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;

/**
 * @author keelfy
 */
public class Food extends DAItem implements IFastUsable {

	private PotionEffect[] effects = null;
	private float saturation = 0;
	private SaturationLevel satLvl = null;
	
	public Food(float weight, float saturation) {
		super(ItemRarity.USUAL, weight);
		this.saturation = saturation;
		
		this.setCreativeTab(DATab.tabFood);
		this.setMaxStackSize(3);
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			MinecraftForgeClient.registerItemRenderer(this, new RendererFood());
		}
	}
	
	public Food(float weight, SaturationLevel saturation) {
		super(ItemRarity.USUAL, weight);
		this.saturation = saturation.getAmount();
		this.satLvl = saturation;
		
		this.setCreativeTab(DATab.tabFood);
		this.setMaxStackSize(3);
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			MinecraftForgeClient.registerItemRenderer(this, new RendererFood());
		}
	}

	public Food(float weight, PotionEffect... potionEffects) {
		super(ItemRarity.USUAL, weight);
		this.effects = potionEffects;
		this.saturation = 0;
		
		this.setCreativeTab(DATab.tabFood);
		this.setMaxStackSize(3);
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			MinecraftForgeClient.registerItemRenderer(this, new RendererFood());
		}
	}
	
	public Food() {
		
		this.setRarity(ItemRarity.USUAL);
		this.setCreativeTab(DATab.tabFood);
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			MinecraftForgeClient.registerItemRenderer(this, new RendererFood());
		}
	}
	
	public Food(float weight, float saturation, PotionEffect... potionEffects) {
		this(weight, saturation);
		this.effects = potionEffects;
	}
	
	public Food(float weight, SaturationLevel saturation, PotionEffect... potionEffects) {
		this(weight, saturation);
		this.effects = potionEffects;
	}
	
	public void setSaturationLevel(SaturationLevel satLvl) {
		this.satLvl = satLvl;
		this.saturation = satLvl.getAmount();
	}
	
	public void setSaturation(float saturation) {
		this.saturation = saturation;
	}
	
	public void setEffects(PotionEffect[] effects) {
		this.effects = effects;
	}
	
	@Override
	public void addInformation(ItemStack is, EntityPlayer player, List list, boolean bool) {
		if(satLvl != null) {
			list.add(Brush.GREEN + satLvl.getName());
			next(list);
		}
		super.addInformation(is, player, list, bool);
	}
	
	public Item setTextureName(int i) {
		return this.setTextureName(String.valueOf(i));
	}
	
	@Override
	public Item setTextureName(String textureName) {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			iconString = DAUtil.MODID + ":food/" + textureName;
		} return this;
	}
	
	@Override
	public ItemStack use(ItemStack is, World world, EntityPlayer player, int slot) {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			world.playSoundAtEntity(player, "random.eat", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
			if (!world.isRemote) {
				if (!player.capabilities.isCreativeMode) {
					if(effects != null) {
						for(int i = 0; i < effects.length; i++) {
							player.addPotionEffect(effects[i]);
						}
					}
					
					DAPlayer.get(player).changeSaturation(saturation);
						
					--player.inventory.mainInventory[slot].stackSize;
					if (player.inventory.mainInventory[slot].stackSize <= 0) {
						player.inventory.mainInventory[slot] = null;
					}
				}
			}
		}
		return is;
	}
	
	public static enum SaturationLevel {
		RAW(5, StatCollector.translateToLocal("food.raw")),
		NOSH(15, StatCollector.translateToLocal("food.nosh")),
		NUTRITIOUS(30, StatCollector.translateToLocal("food.nutritious")),
		NOURISHING(50, StatCollector.translateToLocal("food.nourishing"));
		
		private float amount;
		private String name;
		private SaturationLevel(float amount, String name) {
			this.amount = amount;
			this.name = name;
		}
		
		public String getName() {
			return name.replace('&', '§');
		}
		
		public float getAmount() {
			return amount;
		}
	}
}
