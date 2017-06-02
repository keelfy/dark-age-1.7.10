package keelfy.witcher.item.smartlib;

import keelfy.witcher.DarkAge;
import keelfy.witcher.handler.GuiHandler.GUI;
import keelfy.witcher.item.DAItem;
import keelfy.witcher.util.DAUtil;
import keelfy.witcher.util.DATab;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author keelfy
 */
public class SmartLibrary extends DAItem {

	public SmartLibrary() {
		super(ItemRarity.NOTENTERED, 0);
		
		this.setCreativeTab(DATab.wcMain);
		this.setMaxStackSize(1);
		this.setTextureName(DAUtil.MODID + ":SmartLibrary");
		this.setUnlocalizedName("smartlibrary");
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack is, World world, EntityPlayer player) {
		player.openGui(DarkAge.instance, GUI.SMARTLIBRARY.get(), world, (int)player.posX, (int)player.posY, (int)player.posZ);
		return is;
	}
}
