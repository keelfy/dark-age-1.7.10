/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.items;

import java.util.List;

import com.google.common.collect.Multimap;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkage.client.renderer.RendererSword;
import keelfy.darkage.constants.EnumRarity;
import keelfy.darkage.constants.EnumRepairKit;
import keelfy.darkage.constants.EnumSwordMaterial;
import keelfy.darkage.constants.EnumTabs;
import keelfy.darkage.utils.DAUtils;
import keelfytools.KeelfyUtils;
import keelfytools.LocalizationUtils;
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

	private EnumSwordMaterial type;
	private String set = "";
	private String textureName = "";
	private float damageVsEntity;
	
	private String desc_sword_silver, desc_sword_steel;
	
	@SideOnly(Side.CLIENT)
	public RendererSword renderer;
	
	public Sword(int maxUses, float damage, EnumRarity rarity, float weight, EnumSwordMaterial swordType) {
		super(rarity, weight);

		this.setType(swordType);
		this.setDamageVsEntity(damage);
		this.setMaxStackSize(1);
		this.setMaxDurability(maxUses);
		
		if(swordType == EnumSwordMaterial.SILVER) {
			setCreativeTab(EnumTabs.SWORDSILVER);
		} else if(swordType == EnumSwordMaterial.STEEL) {
			setCreativeTab(EnumTabs.SWORDSTEEL);
		}
		
		if(KeelfyUtils.isClientSide()) {
			this.desc_sword_silver = LocalizationUtils.localize(LocalizationUtils.desc_sword_silver);
			this.desc_sword_steel = LocalizationUtils.localize(LocalizationUtils.desc_sword_steel);
		}
	}
	
	@SideOnly(Side.CLIENT)
	public void setRenderer(RendererSword renderer) {
		if(KeelfyUtils.isClientSide()) {
			if(KeelfyUtils.isClient()) {
				this.renderer = renderer;
				MinecraftForgeClient.registerItemRenderer(this, this.renderer);
			}
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iiconRegister) {
		if(KeelfyUtils.isClientSide()) {
			this.itemIcon = iiconRegister.registerIcon(DAUtils.MODID + ":melee/" + getSet() + "/" + getTextureName());
		}
	}
	
	public Item setTextureName(String set, String textureName) {
		if(KeelfyUtils.isClientSide()) {
			this.set = set;
			this.textureName = textureName;
			return setTextureName(DAUtils.MODID + ":melee/" + getSet() + "/" + getTextureName());
		}
		return this;
	}
	
	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		if(KeelfyUtils.isServerSide()) {
			if(!player.worldObj.isRemote) {
				
				if(type == EnumSwordMaterial.STEEL) {
					if (entity instanceof EntityPlayer) {
						return false;
					} else if (entity instanceof EntityNPCInterface) {
						EntityNPCInterface npcs = (EntityNPCInterface) entity;
						EnumCreatureAttribute var10001 = npcs.stats.creatureType;
						return npcs.stats.creatureType == EnumCreatureAttribute.UNDEAD;
					} else {
						return true;
					}
				} else if(type == EnumSwordMaterial.SILVER) {
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
			}
		}
		return false;
	}
	
	public void setType(EnumSwordMaterial type) {
		this.type = type;
	}
	
	public EnumSwordMaterial getType() {
		return type;
	}
	
	@Override
    public boolean hitEntity(ItemStack is, EntityLivingBase entity, EntityLivingBase entity1) {
		if(KeelfyUtils.isServerSide()) {
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
		if(KeelfyUtils.isClientSide()) {
			if(type == EnumSwordMaterial.SILVER) 
				list.add(desc_sword_silver);
			else if(type == EnumSwordMaterial.STEEL)
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
	
	public void setDamageVsEntity(float damageVsEntity) {
		this.damageVsEntity = damageVsEntity;
	}
	
	public double getDamageVsEntity() {
		return damageVsEntity;
	}
	
	public void setSet(String set) {
		this.set = set;
	}
	
	public String getSet() {
		return set;
	}
	
	@Override
	public EnumRepairKit getRepairKitType() {
		return EnumRepairKit.WEAPON;
	}
	
	public String getTextureName() {
		return textureName;
	}
}
