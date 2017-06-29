/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.blocks.block;

import keelfy.darkage.constants.EnumGui;
import keelfy.darkage.constants.EnumTabs;
import keelfy.darkage.handlers.GuiHandler;
import keelfytools.KeelfyUtils;
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

        this.setCreativeTab(EnumTabs.BLOCK.getCreativeTab());
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9){
    	if(KeelfyUtils.isClientSide()) {
            GuiHandler.openOnPlayer(EnumGui.SELECTCLASS, player);
        }
        return true;
    }
}