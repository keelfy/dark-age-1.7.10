package keelfy.darkage.item;

import java.util.Arrays;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.api.Brush;
import keelfy.darkage.util.DAUtil;
import keelfy.darkage.util.LanguageUtil;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

/**
 * @author keelfy
 */
public class DAItem extends Item {
	
	private float weight;
	private ItemRarity rarity;
	private List addInfo = null;
	private float maxDurability;
	protected String unlocalizedName;
	
	protected String desc_weight;
	protected String desc_durability;
	
	public DAItem(ItemRarity rarity, float weight, String... addInfo) {
		this.rarity = rarity;
		this.weight = weight;
		this.setFull3D();
		
		this.desc_durability = LanguageUtil.localize(LanguageUtil.desc_durability);
		this.desc_weight = LanguageUtil.localize(LanguageUtil.desc_weight);
		
		if(addInfo != null) this.addInfo = Arrays.asList(addInfo);
	}
	
	/**DAI FILE*/
	public DAItem() {
		this(ItemRarity.NONE, 1F);
		
		this.setFull3D();
		this.desc_durability = LanguageUtil.localize(LanguageUtil.desc_durability);
		this.desc_weight = LanguageUtil.localize(LanguageUtil.desc_weight);
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
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			iconString = textureName;
		} return this;
	}
	
	@Override
	public void onUpdate(ItemStack is, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
		if(this instanceof IRepairable) {
			if(is.getTagCompound() == null) is.setTagCompound(new NBTTagCompound());
			
			hasDurability(is);
			if(getDurability(is) > getMaxDurability()) {
				setDurability(is, (int)getMaxDurability());
			}
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack is, EntityPlayer player, List list, boolean bool) {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			if(addDescriptionToInformation(list)) next(list);
			addWeightToInformation(list);
			addRarityToInformation(list);
		}
	}

	@SideOnly(Side.CLIENT)
	protected boolean addDurabilityToInformation(ItemStack is, List list) {
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
		return false;
	}
	
	@SideOnly(Side.CLIENT)
	protected boolean addDescriptionToInformation(List list) {
		if(this.addInfo.size() > 0) {
			for(int i = 0; i < addInfo.size(); i++) {
				list.add(Brush.WHITE + addInfo.get(i).toString());
			}
			return true;
		}
		return false;
	}
	
	@SideOnly(Side.CLIENT)
	protected void addWeightToInformation(List list) {
		list.add(desc_weight + Brush.RESET + " " + String.format("%.1f кг", getWeight()));
	}
	
	@SideOnly(Side.CLIENT)
	protected void addRarityToInformation(List list) {
		if(getRarity() != ItemRarity.NONE) {
			next(list);
			list.add(getRarity().getLocalizedName());
		}
	}
	
	protected List next(List list) {
		list.add("");
		return list;
	}
	
	public Item setWeight(float weight) {
		this.weight = weight;
		return this;
	}
	
	public Item setRarity(ItemRarity rarity) {
		this.rarity = rarity;
		return this;
	}
	
	public Item setAddInfo(String... addInfo) {
		if(addInfo != null) this.addInfo = Arrays.asList(addInfo);
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
	
	public ItemRarity getRarity() {
		return rarity;
	}

	public float getWeight() {
		return weight;
	}
	
	public static enum ItemRarity {
		USUAL(StatCollector.translateToLocal(LanguageUtil.rarity_usual)),
		UNCOMMON(StatCollector.translateToLocal(LanguageUtil.rarity_uncommon)),
		RARE(StatCollector.translateToLocal(LanguageUtil.rarity_rare)),
		LEGENDARY(StatCollector.translateToLocal(LanguageUtil.rarity_legendary)),
		STUFF(StatCollector.translateToLocal(LanguageUtil.rarity_stuff)),
		NONE(""),
		NOTENTERED(Brush.RED + "В геймплее не участвует");
		
		private String localized;
		private ItemRarity(String loc) {
			this.localized = loc;
		}
		
		public String getLocalizedName() {
			return localized.replace('&', '§');
		}
	}

}
