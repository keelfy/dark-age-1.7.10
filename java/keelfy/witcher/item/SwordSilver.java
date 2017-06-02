package keelfy.witcher.item;

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
import noppes.npcs.entity.EntityCustomNpc;
import noppes.npcs.entity.EntityNPCInterface;

/**
 * @author keelfy
 */
public class SwordSilver extends Melee {
	
	public SwordSilver(int maxUses, float damage, ItemRarity rarity, float weight, String... addInfo) {
		super(maxUses, damage, rarity, weight, "Серебро", addInfo);
		
		this.setCreativeTab(DATab.wcSwordSilver);
	}
	
	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
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
