package keelfy.witcherBlocks.block;

import keelfy.witcher.DarkAge;
import keelfy.witcher.handler.GuiHandler.GUI;
import keelfy.witcher.util.DAUtil;
import keelfy.witcher.util.DATab;
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

        this.setCreativeTab(DATab.wcMain);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9){
    	if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
            player.openGui(DarkAge.instance, GUI.SELECTCLASS.get(), world, x, y, z);
        }
        return true;
    }
}