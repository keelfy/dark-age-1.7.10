/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.asm;

import gloomyfolken.hooklib.asm.Hook;
import gloomyfolken.hooklib.asm.Hook.ReturnValue;
import gloomyfolken.hooklib.asm.ReturnCondition;
import keelfy.darkdata.constants.EnumArmorPart;
import keelfy.darkdata.items.Armor;
import keelfy.darkdata.items.Sword;
import net.minecraft.block.Block;
import net.minecraft.block.BlockMushroom;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * @author keelfy
 * @created 3 июн. 2017 г.
 */
public final class DATransformer {

	@Hook(injectOnExit = true, returnCondition = ReturnCondition.ALWAYS)
	public static boolean canHarvestBlock(final ForgeHooks fh, final Block block, final EntityPlayer player,
			final int metadata, @ReturnValue final boolean returnValue) {
		if (!player.capabilities.isCreativeMode)
			return false;
		return returnValue;

	}

	@Hook(returnCondition = ReturnCondition.ALWAYS)
	public static int getArmSwingAnimationEnd(EntityLivingBase living) {
		return (living instanceof EntityPlayer && living.getHeldItem() != null
				&& living.getHeldItem().getItem() instanceof Sword)
						? 7
						: (living.isPotionActive(Potion.digSpeed)
								? 6 - (1 + living.getActivePotionEffect(Potion.digSpeed).getAmplifier()) * 1
								: (living.isPotionActive(Potion.digSlowdown)
										? 6 + (1 + living.getActivePotionEffect(Potion.digSlowdown).getAmplifier()) * 2
										: 6));
	}

	@Hook(returnCondition = ReturnCondition.ALWAYS)
	public static boolean isValidArmor(final Item item, final ItemStack stack, final int armorType,
			final Entity entity) {
		if (stack.getItem() instanceof Armor) {
			int armorPart = ((Armor) stack.getItem()).getPart();

			return armorPart == EnumArmorPart.getPartByVanillaId(armorType).ordinal();
		}

		if (stack.getItem() instanceof ItemArmor)
			return ((ItemArmor) stack.getItem()).armorType == armorType;

		if (armorType == 0)
			return stack.getItem() == Item.getItemFromBlock(Blocks.pumpkin) || stack.getItem() == Items.skull;

		return false;
	}

	@Hook(returnCondition = ReturnCondition.ALWAYS)
	public static boolean canBlockStay(BlockMushroom parent, World world, int x, int y, int z) {
		return true;
	}
}
