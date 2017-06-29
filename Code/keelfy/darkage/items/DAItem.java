/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.items;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkage.constants.EnumRarity;
import keelfy.darkage.constants.EnumTabs;
import keelfytools.KeelfyUtils;
import keelfytools.LocalizationUtils;
import keelfytools.log.Brush;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StringUtils;
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
	
	public DAItem(EnumRarity rarity, float weight) {
		this.setRarity(rarity);
		this.setWeight(weight);
		this.setFull3D();
		
		if(KeelfyUtils.isClientSide()) {
			this.desc_durability = LocalizationUtils.localize(LocalizationUtils.desc_durability);
			this.desc_weight = LocalizationUtils.localize(LocalizationUtils.desc_weight);
		}
	}

	/**DAI FILE*/
	public DAItem() {
		this(EnumRarity.NONE, 1F);
		
		this.setFull3D();
		if(KeelfyUtils.isClientSide()) {
			this.desc_durability = LocalizationUtils.localize(LocalizationUtils.desc_durability);
			this.desc_weight = LocalizationUtils.localize(LocalizationUtils.desc_weight);
		}
	}
	
	public Item setCreativeTab(EnumTabs tab) {
		return this.setCreativeTab(tab.getCreativeTab());
	}
	
	@Override
	public Item setUnlocalizedName(String name) {
		unlocalizedName = name;
		return this;
	}
	
	@Override
	public String getUnlocalizedName() {
		return unlocalizedName;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack is) {
		return getUnlocalizedName();
	}
	
	@Override
	public Item setTextureName(String textureName) {
		if(KeelfyUtils.isClientSide()) {
			iconString = textureName;
		} return this;
	}
	
	public static void setQuestOnly(ItemStack is) {
		if(!is.hasTagCompound()) {
			is.setTagCompound(new NBTTagCompound());
		}
		
		is.getTagCompound().setBoolean("QuestOnly", true);
	}
	
	public static boolean isQuestOnly(ItemStack is) {
		if(!is.hasTagCompound() || (is.hasTagCompound() && !is.getTagCompound().hasKey("QuestOnly"))) {
			return false;
		}
		
		return is.getTagCompound().getBoolean("QuestOnly");
	}
	
	@Override
	public void onUpdate(ItemStack is, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
		if(!is.hasTagCompound()) {
			is.setTagCompound(new NBTTagCompound());
		}
		
		if(!is.getTagCompound().hasKey("QuestOnly"))
			is.getTagCompound().setBoolean("QuestOnly", false);
		
		if(this instanceof IRepairable) {
			hasDurability(is);
			if(getDurability(is) > getMaxDurability()) {
				setDurability(is, (int)getMaxDurability());
			} else if(getDurability(is) <= 0) {
				is.stackSize--;
			}
		}
	}
	
	@SideOnly(Side.CLIENT)
	private void toItemDescription(String addInfo2) {
		if(KeelfyUtils.isClientSide()) {
			String arrWords[] = addInfo2.split(" ");
			ArrayList<String> arrPhrases = new ArrayList();
	
			StringBuilder stringBuffer = new StringBuilder();
			int cnt = 0;
			int index = 0;
			int length = arrWords.length;
	
			while (index != length) {
			  if (cnt + arrWords[index].length() <= 50) {
			    cnt += arrWords[index].length() + 1;
			    stringBuffer.append(arrWords[index]).append(" ");
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
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack is, EntityPlayer player, List list, boolean bool) {
		if(KeelfyUtils.isClientSide()) {
			if(addDescriptionToInformation(list)) next(list);
			addWeightToInformation(list);
			addRarityToInformation(list);
		}
	}
	
	@SideOnly(Side.CLIENT)
	protected boolean addDurabilityToInformation(ItemStack is, List list) {
		if(KeelfyUtils.isClientSide()) {
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
				return true;
			} 
		}
		return false;
	}
	
	@SideOnly(Side.CLIENT)
	protected boolean addDescriptionToInformation(List list) {
		if(KeelfyUtils.isClientSide()) {
			if(this.addInfo != null && this.addInfo.size() > 0) {
				for(int i = 0; i < addInfo.size(); i++) {
					list.add(Brush.WHITE + addInfo.get(i).toString());
				}
				return true;
			}
		}
		return false;
	}
	
	@SideOnly(Side.CLIENT)
	protected void addWeightToInformation(List list) {
		if(KeelfyUtils.isClientSide()) {
			list.add(desc_weight + Brush.RESET + " " + String.format("%.1f кг", getWeight()));
		}
	}
	
	@SideOnly(Side.CLIENT)
	protected void addRarityToInformation(List list) {
		if(KeelfyUtils.isClientSide()) {
			if(getRarity() != EnumRarity.NONE) {
				next(list);
				list.add(getRarity().getLocalizedName());
			}
		}
	}
	
	protected List next(List list) {
		if(KeelfyUtils.isClientSide()) {
			list.add("");
			return list;
		}
		return new ArrayList();
	}
	
	public Item setWeight(float weight) {
		this.weight = weight;
		return this;
	}
	
	public Item setRarity(EnumRarity rarity) {
		this.rarity = rarity;
		return this;
	}
	
	public Item setAddInfo(String addInfo) {
		if(KeelfyUtils.isClientSide()) {
			if(!StringUtils.isNullOrEmpty(addInfo)) 
				toItemDescription(addInfo);
		}
		return this;
	}
	
	@Override
    public float func_150893_a(ItemStack is, Block block) {
    	return 0;
    }
	
	public void setDurability(ItemStack stack, int damage) {
		hasDurability(stack);
		stack.getTagCompound().setFloat(IRepairable.DURABILITY, damage);
	}
	
	public void repair(ItemStack stack, int damage) {
		setDurability(stack, (int)getDurability(stack) + damage);
	}
	
	public void damage(ItemStack stack, int damage) {
		setDurability(stack, (int)getDurability(stack) - damage);
	}
	
	public float getDurability(ItemStack stack) {
		hasDurability(stack);
		return stack.getTagCompound().getFloat(IRepairable.DURABILITY);
	}
	
	private void hasDurability(ItemStack stack) {
		if(!stack.getTagCompound().hasKey(IRepairable.DURABILITY))
			stack.getTagCompound().setFloat(IRepairable.DURABILITY, getMaxDurability());
	}
	
	public Item setMaxDurability(int max) {
		this.maxDurability = max;
		return this;
	}
	
	public float getMaxDurability() {	
		return maxDurability;
	}
	
	public EnumRarity getRarity() {
		return rarity;
	}

	public float getWeight() {
		return weight;
	}
}
