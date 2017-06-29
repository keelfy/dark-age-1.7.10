/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkage.client.renderer.RendererFood;
import keelfy.darkage.constants.EnumFood;
import keelfy.darkage.constants.EnumRarity;
import keelfy.darkage.constants.EnumTabs;
import keelfy.darkage.entities.player.DAPlayer;
import keelfytools.KeelfyUtils;
import keelfytools.log.Brush;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;

/**
 * @author keelfy
 */
public class Food extends DAItem implements IFastUsable {

	private PotionEffect[] effects = null;
	private float saturation = 0;
	private EnumFood satLvl = null;
	
	public Food() {
		this.setRarity(EnumRarity.USUAL);
		this.setCreativeTab(EnumTabs.FOOD);
		this.setSaturation(0);
		this.setMaxStackSize(3);
		
		if(KeelfyUtils.isClientSide()) {
			MinecraftForgeClient.registerItemRenderer(this, new RendererFood());
		}
	}
	
	public void setSaturationLevel(EnumFood satLvl) {
		this.satLvl = satLvl;
		this.saturation = satLvl.getAmount();
	}
	
	public void setSaturation(float saturation) {
		this.saturation = saturation;
	}
	
	public void setEffects(PotionEffect[] effects) {
		this.effects = effects;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack is, EntityPlayer player, List list, boolean bool) {
		if(satLvl != null) {
			list.add(Brush.GREEN + satLvl.getName());
			next(list);
		}
		
		super.addInformation(is, player, list, bool);
	}
	
	@Override
	public ItemStack use(ItemStack is, World world, EntityPlayer player, int slot) {
		if(KeelfyUtils.isServerSide()) {
			
			world.playSoundAtEntity(player, "random.eat", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
			if (!world.isRemote) {
				if (!player.capabilities.isCreativeMode) {
					if(effects != null) {
						for(int i = 0; i < effects.length; i++) {
							player.addPotionEffect(effects[i]);
						}
					}
					
					DAPlayer.get(player).changeSaturation(saturation);
						
					--player.inventory.mainInventory[slot].stackSize;
					if (player.inventory.mainInventory[slot].stackSize <= 0) {
						player.inventory.mainInventory[slot] = null;
					}
				}
			}
		}
		return is;
	}
}
