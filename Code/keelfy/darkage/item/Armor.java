package keelfy.darkage.item;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.api.KUtil;
import keelfy.darkage.client.renderer.RendererArmor;
import keelfy.darkage.item.RepairKit.RepairKitType;
import keelfy.darkage.util.DATab;
import keelfy.darkage.util.DAUtil;
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
	
	public Armor(ArmorType armorType, ItemRarity rarity, float weight, int partNumber, float blockingPercent, int durability, String... addInfo) {
		super(rarity, weight, addInfo);
		this.type = armorType;
		this.partNumber = partNumber;
		this.blockingPercent = blockingPercent;
		
		this.setMaxDurability(durability);
		this.setCreativeTab(DATab.tabArmor);
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
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack is, EntityPlayer player, List list, boolean bool) {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			if(addDescriptionToInformation(list)) next(list);
			addWeightToInformation(list);
			addDurabilityToInformation(is, list);
			addRarityToInformation(list);
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

