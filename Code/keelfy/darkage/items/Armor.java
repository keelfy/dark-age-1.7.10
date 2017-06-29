/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkage.client.renderer.RendererArmor;
import keelfy.darkage.constants.EnumArmorClass;
import keelfy.darkage.constants.EnumRarity;
import keelfy.darkage.constants.EnumRepairKit;
import keelfy.darkage.constants.EnumTabs;
import keelfy.darkage.utils.DAUtils;
import keelfytools.KeelfyUtils;
import keelfytools.log.Brush;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.MinecraftForgeClient;

/**
 * @author keelfy
 */
public class Armor extends DAItem implements IRepairable {
	
	private EnumArmorClass type;
	private int partNumber;
	private float blockingPercent;
	
	public Armor(EnumArmorClass armorType, EnumRarity rarity, float weight, int partNumber, float blockingPercent, int durability) {
		super(rarity, weight);
		
		this.setType(armorType);
		this.setPart(partNumber);
		this.setBlockingPercent(blockingPercent);
		this.setMaxDurability(durability);
		this.setCreativeTab(EnumTabs.ARMOR);
		
		if(KeelfyUtils.isClientSide()) {
			if(KeelfyUtils.isClient())
				MinecraftForgeClient.registerItemRenderer(this, new RendererArmor());
		}
	}
	
	public Item setTextureName(String set, String textureName) {
		if(KeelfyUtils.isClientSide())
			return setTextureName(DAUtils.MODID + ":armor/" + set + "/" + textureName);
		return this;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack is, EntityPlayer player, List list, boolean bool) {
		if(KeelfyUtils.isClientSide()) {
			if(addDescriptionToInformation(list)) 
				next(list);
			addWeightToInformation(list);
			addDurabilityToInformation(is, list);
			addRarityToInformation(list);
			
			if(blockingPercent != 0) {
				next(list);
				list.add(Brush.BLUE + "Процент защиты: " + blockingPercent + "%");
			}
		}
	}
	
	@Override
	public Item setTextureName(String textureName) {
		if(KeelfyUtils.isClientSide()) {
			this.iconString = textureName;
		}
		return this;
	}
	
	public void setBlockingPercent(float blockingPercent) {
		this.blockingPercent = blockingPercent;
	}
	
	public float getBlockingPercent() {
		return blockingPercent;
	}
	
	public void setPart(int partNumber) {
		this.partNumber = partNumber;
	}
	
	public int getPart() {
		return partNumber;
	}
	
	public void setType(EnumArmorClass type) {
		this.type = type;
	}
	
	public EnumArmorClass getArmorType() {
		return type;
	}
	
	@Override
	public EnumRepairKit getRepairKitType() {
		return EnumRepairKit.ARMOR;
	}
}

