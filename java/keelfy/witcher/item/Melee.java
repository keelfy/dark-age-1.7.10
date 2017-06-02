package keelfy.witcher.item;

import java.util.List;

import com.google.common.collect.Multimap;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.api.Brush;
import keelfy.witcher.item.RepairKit.RepairKitType;
import keelfy.witcher.util.DAUtil;
import keelfy.witcher.util.LanguageUtil;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author keelfy
 */
public class Melee extends DAItem implements IRepairable {

	private String material;
	private String set = "";
	private String textureName = "";
	private float damageVsEntity;
	
	private String desc_sword_silver, desc_sword_steel, desc_durability;
	
	public Melee(int maxUses, float damage, ItemRarity rarity, float weight,  String materialName, String... addInfo) {
		super(rarity, weight, addInfo);
		this.material = materialName;
		this.damageVsEntity = damage;
		this.desc_sword_silver = LanguageUtil.localize(LanguageUtil.desc_sword_silver);
		this.desc_sword_steel = LanguageUtil.localize(LanguageUtil.desc_sword_steel);
		this.desc_durability = LanguageUtil.localize(LanguageUtil.desc_durability);
		
		this.setMaxStackSize(1);
		this.setMaxDurability(maxUses);
	}
	
	@Override
	public void registerIcons(IIconRegister iiconRegister) {
		this.itemIcon = iiconRegister.registerIcon(DAUtil.MODID + ":melee/" + getSet() + "/" + getTextureName());
	}
	
	public Item setTextureName(String set, String textureName) {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			this.set = set;
			this.textureName = textureName;
			return setTextureName(DAUtil.MODID + ":melee/" + getSet() + "/" + getTextureName());
		}
		return this;
	}
	
	@Override
    public boolean hitEntity(ItemStack is, EntityLivingBase entity, EntityLivingBase entity1) {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			damage(is, 1);
		}
        return true;
    }
	
	@Override
	public EnumAction getItemUseAction(ItemStack is) {
        return EnumAction.none;
    }

	@Override
    public int getMaxItemUseDuration(ItemStack is) {
        return 72000;
	}
	
	@Override
    public ItemStack onItemRightClick(ItemStack is, World world, EntityPlayer player) {
        return is;
    }
	
	@Override
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack is, EntityPlayer player, List list, boolean bool) {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			if("Серебро".equals(material)) 
				list.add(desc_sword_silver);
			else if("Сталь".equals(material))
				list.add(desc_sword_steel);
			
			next(list);
			if(getAdditionalInformation().size() > 0) {
				for(int i = 0; i < getAdditionalInformation().size(); i++) {
					list.add(getAdditionalInformation().get(i));
				}
				next(list);
			}
			
			list.add(desc_weight + Brush.RESET + String.format(" %.1f кг", getWeight()));
			
			if(getMaxDurability() > 0 && is.stackTagCompound != null && is.stackTagCompound.hasKey(IRepairable.DURABILITY)) {
				float ix = (getDurability(is) / getMaxDurability()) * 100;
				
				String s = String.valueOf((int)ix) + "%" + String.valueOf(Brush.GRAY + " (" + (int)getDurability(is) + "/" + (int)getMaxDurability() + ")");
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
    public Multimap getItemAttributeModifiers() {
		Multimap multimap = super.getItemAttributeModifiers();
		multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", this.getDamageVsEntity(), 0));
		return multimap;
    }
	
	public double getDamageVsEntity() {
		return damageVsEntity;
	}
	
	public String getSet() {
		return set;
	}
	
	@Override
	public RepairKitType getRepairKitType() {
		return RepairKitType.WEAPON;
	}
	
	public String getTextureName() {
		return textureName;
	}
}
