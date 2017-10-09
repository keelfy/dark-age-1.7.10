/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.items;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkdata.constants.EnumRarity;
import keelfy.darkdata.utils.DALocalization;
import keelfyutils.KUtils;
import keelfyutils.client.KLocalization;
import keelfyutils.str.Brush;
import keelfyutils.str.KString;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

/**
 * @author keelfy
 */
public class DAItem extends Item {

	private float weight;
	private EnumRarity rarity;
	private List addInfo = null;
	private float maxDurability;
	protected String unlocalizedName;

	protected String desc_weight;
	protected String desc_durability;

	public DAItem(final EnumRarity rarity, final float weight) {
		this.setRarity(rarity);
		this.setWeight(weight);
		this.setMaxDurability(1.0F);

		if (KUtils.PROTECT_CLIENT) {
			if (KUtils.isClient()) {
				initLocalization();

				if (this instanceof IItemWithRenderer) {
					((IItemWithRenderer) this).initRenderer();
				}
			}
		}
	}

	/** DAI FILE */
	public DAItem() {
		this(EnumRarity.NONE, 1F);

		if (KUtils.PROTECT_CLIENT) {
			if (KUtils.isClient()) {
				initLocalization();
			}
		}
	}

	@SideOnly(Side.CLIENT)
	protected void initLocalization() {
		if (KUtils.PROTECT_CLIENT) {
			this.desc_durability = KLocalization.localize(DALocalization.Desc_Durability);
			this.desc_weight = KLocalization.localize(DALocalization.Desc_Weight);
		}
	}

	@Override
	public Item setUnlocalizedName(final String name) {
		unlocalizedName = name;
		return this;
	}

	@Override
	public final String getUnlocalizedName() {
		return unlocalizedName;
	}

	@Override
	public final String getUnlocalizedName(final ItemStack is) {
		return getUnlocalizedName();
	}

	@Override

	public final Item setTextureName(final String textureName) {
		if (KUtils.PROTECT_CLIENT) {
			iconString = textureName;
		}
		return this;
	}

	public static final void setQuestOnly(final ItemStack is) {
		if (!is.hasTagCompound()) {
			is.setTagCompound(new NBTTagCompound());
		}

		is.getTagCompound().setBoolean("QuestOnly", true);
	}

	public static final boolean isQuestOnly(final ItemStack is) {
		if (!is.hasTagCompound() || (is.hasTagCompound() && !is.getTagCompound().hasKey("QuestOnly")))
			return false;

		return is.getTagCompound().getBoolean("QuestOnly");
	}

	@Override
	public void onUpdate(final ItemStack is, final World world, final Entity entity, final int partialTicks,
			final boolean p_77663_5_) {
		if (KUtils.PROTECT_SERVER) {
			if (world.isRemote) {
				return;
			}

			if (!is.hasTagCompound()) {
				is.setTagCompound(new NBTTagCompound());
			}

			if (!is.getTagCompound().hasKey("QuestOnly")) {
				is.getTagCompound().setBoolean("QuestOnly", false);
			}

			hasDurability(is);

			if (getDurability(is) > getMaxDurability()) {
				setDurability(is, getMaxDurability());
			}

			if (getDurability(is) <= 0.0F) {
				is.stackSize--;
			}
		}
	}

	private final void toItemDescription(final String addInfo2) {
		if (KUtils.PROTECT_CLIENT) {
			final String arrWords[] = addInfo2.split(KString.SPACE);
			final ArrayList<String> arrPhrases = new ArrayList();

			StringBuilder stringBuffer = new StringBuilder();
			int cnt = 0;
			int index = 0;
			final int length = arrWords.length;

			while (index != length) {
				if (cnt + arrWords[index].length() <= 50) {
					cnt += arrWords[index].length() + 1;
					stringBuffer.append(arrWords[index]).append(KString.SPACE);
					index++;
				} else {
					arrPhrases.add(stringBuffer.toString());
					stringBuffer = new StringBuilder();
					cnt = 0;
				}
			}

			if (stringBuffer.length() > 0) {
				arrPhrases.add(stringBuffer.toString());
			}

			this.addInfo = arrPhrases;
		}
	}

	@Override
	public void addInformation(final ItemStack is, final EntityPlayer player, final List list, final boolean bool) {
		if (KUtils.PROTECT_CLIENT) {
			if (addDescriptionToInformation(is, list)) {
				next(list);
			}
			addWeightToInformation(list);
			addRarityToInformation(list);

			if (KUtils.DEBUG) {
				next(list);
				list.add(Brush.RED + "NBT Tags:");
				addNBTToInformation(is, list);
			}
		}
	}

	protected final void addNBTToInformation(final ItemStack stack, List list) {
		if (KUtils.PROTECT_CLIENT) {
			if (KUtils.DEBUG) {
				if (!stack.hasTagCompound()) {
					return;
				}

				Set<String> nbtKeys = stack.getTagCompound().func_150296_c();

				if (nbtKeys.isEmpty()) {
					return;
				}

				for (String key : nbtKeys) {
					list.add("Key: " + key + ", Value: " + stack.getTagCompound().getTag(key).toString());
				}
			}
		}
	}

	protected final boolean addDurabilityToInformation(final ItemStack is, final List list) {
		if (KUtils.PROTECT_CLIENT) {
			if (getMaxDurability() > 0 && is.stackTagCompound != null
					&& is.stackTagCompound.hasKey(IRepairable.DURABILITY)) {
				final float ix = (getDurability(is) / getMaxDurability()) * 100;

				final String s = String.valueOf((int) ix) + "%" + Brush.GRAY + " (" + (int) getDurability(is) + "/"
						+ (int) getMaxDurability() + ")";
				final String s1 = desc_durability + Brush.RESET + KString.SPACE;

				if (ix <= 70 && ix > 30) {
					list.add(s1 + Brush.YELLOW + s);
				} else if (ix <= 30) {
					list.add(s1 + Brush.RED + s);
				} else {
					list.add(s1 + Brush.GREEN + s);
				}
				return true;
			}
		}
		return false;
	}

	protected final boolean addDescriptionToInformation(final ItemStack stack, final List list) {
		if (KUtils.PROTECT_CLIENT) {
			if (stack.hasTagCompound() && stack.getTagCompound().hasKey("QuestOnly")
					&& stack.getTagCompound().getBoolean("QuestOnly")) {
				list.add(Brush.AQUA + "Квестовый предмет");

				if (this.addInfo != null && this.addInfo.size() > 0) {
					next(list);
				} else
					return true;
			}

			if (this.addInfo != null && this.addInfo.size() > 0) {
				for (int i = 0; i < addInfo.size(); i++) {
					list.add(Brush.WHITE + addInfo.get(i).toString());
				}
				return true;
			}
		}
		return false;
	}

	protected final void addWeightToInformation(final List list) {
		if (KUtils.PROTECT_CLIENT) {
			list.add(desc_weight + Brush.RESET + KString.SPACE + String.format("%.1f кг", getWeight()));
		}
	}

	protected final void addRarityToInformation(final List list) {
		if (KUtils.PROTECT_CLIENT) {
			if (getRarity() != EnumRarity.NONE) {
				next(list);
				list.add(getRarity().getLocalizedName());
			}
		}
	}

	protected final List next(final List list) {
		if (KUtils.PROTECT_CLIENT) {
			list.add(KString.EMPTY);
			return list;
		}
		return new ArrayList();
	}

	public final Item setWeight(final float weight) {
		this.weight = weight;
		return this;
	}

	public final Item setRarity(final EnumRarity rarity) {
		this.rarity = rarity;
		return this;
	}

	public final Item setAddInfo(final String addInfo) {
		if (KUtils.PROTECT_CLIENT) {
			if (!net.minecraft.util.StringUtils.isNullOrEmpty(addInfo)) {
				toItemDescription(addInfo);
			}
		}
		return this;
	}

	// getStrVsBlock
	@Override
	public float func_150893_a(final ItemStack is, final Block block) {
		return 0;
	}

	public final void setDurability(final ItemStack stack, final float damage) {
		hasDurability(stack);
		stack.getTagCompound().setFloat(IRepairable.DURABILITY, damage);
	}

	public final void repair(final ItemStack stack, final float damage) {
		float dur = getDurability(stack);

		if (dur + damage <= getMaxDurability()) {
			setDurability(stack, (int) getDurability(stack) + damage);
		} else {
			setDurability(stack, getMaxDurability());
		}

	}

	public final void damage(final ItemStack stack, final float damage) {
		setDurability(stack, getDurability(stack) - damage);
	}

	public final float getDurability(final ItemStack stack) {
		hasDurability(stack);
		return stack.getTagCompound().getFloat(IRepairable.DURABILITY);
	}

	private final void hasDurability(final ItemStack stack) {
		if (!stack.getTagCompound().hasKey(IRepairable.DURABILITY)) {
			stack.getTagCompound().setFloat(IRepairable.DURABILITY, getMaxDurability());
		}
	}

	public final Item setMaxDurability(final float max) {
		this.maxDurability = max;
		return this;
	}

	public final float getMaxDurability() {
		return maxDurability;
	}

	public final EnumRarity getRarity() {
		return rarity;
	}

	public final float getWeight() {
		return weight;
	}
}
