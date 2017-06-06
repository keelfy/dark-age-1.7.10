package keelfy.darkage.addon.blocks.block;

import keelfy.darkage.DarkAge;
import keelfy.darkage.handler.GuiHandler.GUI;
import keelfy.darkage.util.DATab;
import keelfy.darkage.util.DAUtil;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * @author keelfy
 */
public class BlockClassSelector extends Block {

    public BlockClassSelector() {
        super(Material.iron);

        this.setCreativeTab(DATab.tabDA);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9){
    	if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
            player.openGui(DarkAge.instance, GUI.SELECTCLASS.get(), world, x, y, z);
        }
        return true;
    }
}