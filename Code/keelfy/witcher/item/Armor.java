package keelfy.witcher.item;

import java.util.List;

import keelfy.api.Brush;
import keelfy.api.KUtil;
import keelfy.witcher.client.renderer.RendererArmor;
import keelfy.witcher.item.RepairKit.RepairKitType;
import keelfy.witcher.util.DATab;
import keelfy.witcher.util.DAUtil;
import keelfy.witcher.util.LanguageUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.MinecraftForgeClient;

/**
 * @author keelfy
 */
public class Armor extends DAItem implements IRepairable {
	
	private ArmorType type;
	private int partNumber;
	private float blockingPercent;
	
	private String desc_durability;
	
	public Armor(ArmorType armorType, ItemRarity rarity, float weight, int partNumber, float blockingPercent, int durability, String... addInfo) {
		super(rarity, weight, addInfo);
		this.type = armorType;
		this.partNumber = partNumber;
		this.blockingPercent = blockingPercent;
		this.desc_durability = LanguageUtil.localize(LanguageUtil.desc_durability);
		
		this.setMaxDurability(durability);
		this.setCreativeTab(DATab.wcArmor);
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			if(KUtil.isClient())
				MinecraftForgeClient.registerItemRenderer(this, new RendererArmor());
		}
	}
	
	public Item setTextureName(String set, String textureName) {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE)
			return setTextureName(DAUtil.MODID + ":armor/" + set + "/" + textureName);
		return this;
	}
	
	@Override
	public void addInformation(ItemStack is, EntityPlayer player, List list, boolean bool) {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			if(this.getAdditionalInformation().size() > 0) {
				for(int i = 0; i < getAdditionalInformation().size(); i++) {
					list.add(getAdditionalInformation().get(i));
				}
				next(list);
			}
			list.add(desc_weight + Brush.RESET + " " + String.format("%.1f кг", getWeight()));
			
			if(getMaxDurability() > 0 && is.stackTagCompound != null && is.stackTagCompound.hasKey(IRepairable.DURABILITY)) {
				float ix = (getDurability(is) / getMaxDurability()) * 100;
				
				String s = String.valueOf((int)ix) + "%" + Brush.GRAY + " (" + (int)getDurability(is) + "/" + (int)getMaxDurability() + ")";
				String s1 = desc_durability + Brush.RESET + " ";
				
				if(ix <= 70 && ix > 30) {
					list.add(s1 + Brush.YELLOW + s);
				} else if(ix <= 30) {
					list.add(s1 + Brush.RED + s);
				} else {
					list.add(s1 + Brush.GREEN + s);
				}
			}
			
			if(getRarity() != ItemRarity.NONE) {
				next(list);
				list.add(getRarity().getLocalizedName());
			}
		}
	}
	
	@Override
	public Item setTextureName(String textureName) {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			this.iconString = textureName;
		}
		return this;
	}
	
	public float getBlockingPercent() {
		return blockingPercent;
	}
	
	public int getPart() {
		return partNumber;
	}
	
	public ArmorType getArmorType() {
		return type;
	}
	
	@Override
	public RepairKitType getRepairKitType() {
		return RepairKitType.ARMOR;
	}
	
	public static enum ArmorType {
		HEAVY,
		MIDDLE,
		LIGHT;
	}
}

