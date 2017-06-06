package keelfy.darkage.asm;

import gloomyfolken.hooklib.asm.Hook;
import gloomyfolken.hooklib.asm.ReturnCondition;
import keelfy.darkage.item.Armor;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

/**
 * @author keelfy
 * @created 3 июн. 2017 г.
 */
public class DATransformer {
	
	@Hook(injectOnExit = true, targetMethod = "net.minecraft.entity.player.InventoryPlayer.getTotalArmorValue", returnCondition = ReturnCondition.ALWAYS)
    public static int getTotalArmorValue() {
        return 0;
    }
	
	@Hook()
	public static boolean isValidArmor(ItemStack stack, int armorType, Entity entity) {
        if(stack.getItem() instanceof Armor) {
        	return ((Armor)stack.getItem()).getPart() == armorType;
        }
        
        if (stack.getItem() instanceof ItemArmor) {
            return ((ItemArmor)stack.getItem()).armorType == armorType;
        }

        if (armorType == 0) {
            return stack.getItem() == Item.getItemFromBlock(Blocks.pumpkin) || stack.getItem() == Items.skull;
        }

		return false;
    }
}
