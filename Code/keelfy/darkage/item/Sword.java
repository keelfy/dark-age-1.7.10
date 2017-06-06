package keelfy.darkage.item;

import java.util.List;

import com.google.common.collect.Multimap;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.api.KUtil;
import keelfy.darkage.client.renderer.RendererSword;
import keelfy.darkage.item.RepairKit.RepairKitType;
import keelfy.darkage.util.DATab;
import keelfy.darkage.util.DAUtil;
import keelfy.darkage.util.LanguageUtil;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.MinecraftForgeClient;
import noppes.npcs.entity.EntityCustomNpc;
import noppes.npcs.entity.EntityNPCInterface;

/**
 * @author keelfy
 */
public class Sword extends DAItem implements IRepairable {

	private SwordType type;
	private String set = "";
	private String textureName = "";
	private float damageVsEntity;
	
	private String desc_sword_silver, desc_sword_steel;
	
	@SideOnly(Side.CLIENT)
	public RendererSword renderer;
	
	public Sword(int maxUses, float damage, ItemRarity rarity, float weight, SwordType swordType, String... addInfo) {
		super(rarity, weight, addInfo);
		this.type = swordType;
		this.damageVsEntity = damage;
		
		this.desc_sword_silver = LanguageUtil.localize(LanguageUtil.desc_sword_silver);
		this.desc_sword_steel = LanguageUtil.localize(LanguageUtil.desc_sword_steel);
		
		this.setMaxStackSize(1);
		this.setMaxDurability(maxUses);
		
		if(swordType == SwordType.SILVER) {
			setCreativeTab(DATab.tabSwordSilver);
		} else if(swordType == SwordType.STEEL) {
			setCreativeTab(DATab.tabSwordSteel);
		}
	}
	
	@SideOnly(Side.CLIENT)
	public void setRenderer(RendererSword renderer) {
		if(KUtil.isClient()) {
			this.renderer = renderer;
			MinecraftForgeClient.registerItemRenderer(this, this.renderer);
		}
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
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		if(type == SwordType.STEEL) {
			if (entity instanceof EntityPlayer) {
				return false;
			} else if (entity instanceof EntityNPCInterface) {
				EntityNPCInterface npcs = (EntityNPCInterface) entity;
				EnumCreatureAttribute var10001 = npcs.stats.creatureType;
				return npcs.stats.creatureType == EnumCreatureAttribute.UNDEAD;
			} else {
				return true;
			}
		} else if(type == SwordType.SILVER) {
			if (entity instanceof EntityPlayer) {
				return true;
			} else {
				EnumCreatureAttribute var10001;
				if (entity instanceof EntityNPCInterface) {
					EntityNPCInterface npc = (EntityNPCInterface) entity;
					var10001 = npc.stats.creatureType;
					if (npc.stats.creatureType == EnumCreatureAttribute.ARTHROPOD || npc.stats.creatureType == EnumCreatureAttribute.UNDEFINED) {
						return true;
					}
				} else if (entity instanceof EntityCustomNpc) {
					EntityCustomNpc npc1 = (EntityCustomNpc) entity;
					var10001 = npc1.stats.creatureType;
					if (npc1.stats.creatureType == EnumCreatureAttribute.ARTHROPOD || npc1.stats.creatureType == EnumCreatureAttribute.UNDEFINED) {
						return false;
					}
				}
				return false;
			} 
		}
		return false;
	}
	
	public SwordType getType() {
		return type;
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
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack is, EntityPlayer player, List list, boolean bool) {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			if(type == SwordType.SILVER) 
				list.add(desc_sword_silver);
			else if(type == SwordType.STEEL)
				list.add(desc_sword_steel);
			next(list);
			
			if(addDescriptionToInformation(list)) next(list);
			addWeightToInformation(list);
			addDurabilityToInformation(is, list);
			addRarityToInformation(list);
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
	
	public static enum SwordType {
		SILVER, STEEL;
	}
}
