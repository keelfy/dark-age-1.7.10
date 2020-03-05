/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.items;

import java.util.List;

import com.google.common.collect.Multimap;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkdata.client.renderer.DARendererSword;
import keelfy.darkdata.constants.EnumRarity;
import keelfy.darkdata.constants.EnumRepairKit;
import keelfy.darkdata.constants.EnumSwordMaterial;
import keelfy.darkdata.utils.DALocalization;
import keelfyutils.KUtils;
import keelfyutils.client.KLocalization;
import keelfyutils.str.Brush;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

/**
 * @author keelfy
 */
public final class Sword extends ItemWithRender implements IRepairable {

	private EnumSwordMaterial type;

	private float damageVsEntity;
	private float persistence;

	private String desc_sword_silver, desc_sword_steel;

	/** DAI File */
	public Sword() {
		super("melee", EnumRarity.NONE, 0f);

		this.setDamageVsEntity(0f);
		this.setMaxStackSize(1);
		this.setMaxDurability(100);
		this.setPersistence(1f);
	}

	@Override
	protected final void initLocalization() {
		if (KUtils.PROTECT_CLIENT) {
			super.initLocalization();

			this.desc_sword_silver = KLocalization.localize(DALocalization.Desc_Sword_Silver);
			this.desc_sword_steel = KLocalization.localize(DALocalization.Desc_Sword_Steel);
		}
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		return super.onLeftClickEntity(stack, player, entity);
	}

	@Override
	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
		if (!stack.hasTagCompound()) {
			stack.setTagCompound(new NBTTagCompound());
		}

		boolean flag = true;
		if (stack.getTagCompound().getInteger("Using") <= 0) {
			flag = false;
			stack.getTagCompound().setInteger("Using", 6);
		}

		return flag;
	}

	public final void setType(final EnumSwordMaterial type) {
		this.type = type;
	}

	public final EnumSwordMaterial getType() {
		return type;
	}

	@Override
	public final boolean hitEntity(final ItemStack is, final EntityLivingBase entity, final EntityLivingBase entity1) {
		if (KUtils.PROTECT_SERVER) {
			damage(is, getPersistence());
		}
		return true;
	}

	@Override
	public final EnumAction getItemUseAction(final ItemStack is) {
		return EnumAction.block;
	}

	@Override
	public final int getMaxItemUseDuration(final ItemStack stack) {
		return 72000;
	}

	@Override
	public ItemStack onItemRightClick(final ItemStack stack, final World worldIn, final EntityPlayer player) {
		player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
		return stack;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public final void addInformation(final ItemStack is, final EntityPlayer player, final List list, final boolean bool) {
		if (KUtils.PROTECT_CLIENT) {
			if (type == EnumSwordMaterial.SILVER) {
				list.add(desc_sword_silver);
			} else if (type == EnumSwordMaterial.STEEL) {
				list.add(desc_sword_steel);
			}
			next(list);

			if (addDescriptionToInformation(is, list)) {
				next(list);
			}
			addWeightToInformation(list);
			addDurabilityToInformation(is, list);
			addRarityToInformation(list);
			if (!player.capabilities.isCreativeMode && getDamageVsEntity() != 0) {
				next(list);
				list.add(Brush.BLUE + "Урон: " + Math.round(getDamageVsEntity()) + " ед.");
			}
		}
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int partialTicks, boolean par5) {
		if (KUtils.PROTECT_SERVER) {

			if (!stack.hasTagCompound()) {
				stack.setTagCompound(new NBTTagCompound());
			}

			if (stack.getTagCompound().getInteger("Using") > 0) {
				stack.getTagCompound().setInteger("Using", stack.getTagCompound().getInteger("Using") - 1);
			}

			super.onUpdate(stack, world, entity, partialTicks, par5);
		}
	}

	@Override
	public final Multimap getItemAttributeModifiers() {
		final Multimap multimap = super.getItemAttributeModifiers();
		multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", this.getDamageVsEntity(), 0));
		return multimap;
	}

	public int getItemUse(ItemStack stack) {
		return stack.hasTagCompound() ? stack.getTagCompound().getInteger("Using") : 0;
	}

	public final DARendererSword getRenderer() {
		return (DARendererSword) renderer;
	}

	public final void setDamageVsEntity(final float damageVsEntity) {
		this.damageVsEntity = damageVsEntity;
	}

	public final double getDamageVsEntity() {
		return damageVsEntity;
	}

	public final void setPersistence(final float persistence) {
		this.persistence = persistence;
	}

	public final float getPersistence() {
		return persistence;
	}

	@Override
	public final EnumRepairKit getRepairKitType() {
		return EnumRepairKit.WEAPON;
	}
}
