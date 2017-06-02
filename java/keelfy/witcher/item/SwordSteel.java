package keelfy.witcher.item;

import java.util.UUID;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.witcher.item.DAItem.ItemRarity;
import keelfy.witcher.util.DAUtil;
import keelfy.witcher.util.DATab;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import noppes.npcs.entity.EntityNPCInterface;

/**
 * @author keelfy
 */
public class SwordSteel extends Melee {
	
	public SwordSteel(int maxUses, float damageVsEntity, ItemRarity rarity, float weight, String... addInfo) {
		super(maxUses, damageVsEntity, rarity, weight, "Сталь", addInfo);
		
		this.setCreativeTab(DATab.wcSwordSteel);
	}
	
	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		if (entity instanceof EntityPlayer) {
			return false;
		} else if (entity instanceof EntityNPCInterface) {
			EntityNPCInterface npcs = (EntityNPCInterface) entity;
			EnumCreatureAttribute var10001 = npcs.stats.creatureType;
			return npcs.stats.creatureType == EnumCreatureAttribute.UNDEAD;
		} else {
			return true;
		}
	}
}
