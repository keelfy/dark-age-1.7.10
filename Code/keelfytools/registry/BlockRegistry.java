/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfytools.registry;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;

/**
 * @author keelfy
 */
public class BlockRegistry<T extends Block> {
    private static String resourceDomain = "";
    
    public static void setResourceDomain(String resourceDomain) {
    	BlockRegistry.resourceDomain = resourceDomain;
    }

    public static <T extends Block> BlockRegistry<T> of(String name, T block) {
        return new BlockRegistry(name, block);
    }

    T block;
    String uniqueName;
    String resourceName;

    private BlockRegistry(String name, T block) {
        this.block = block;
        this.uniqueName = name;
        this.resourceName = name;
    }

    public BlockRegistry<T> withResource(String name) {
        this.resourceName = name;
        return this;
    }
    
    private T gameRegister() {
    	GameRegistry.registerBlock(block, uniqueName);
		return block;
    }
    
    private T registerLocalizedName() {
    	LanguageRegistry.instance().addStringLocalization(uniqueName, "");
    	return block;
    }
    
    public T register()  {
        block.setBlockName(resourceDomain + resourceName);
        block.setBlockTextureName(resourceDomain + ":" + resourceName);
        gameRegister();
        registerLocalizedName();
        return block;
    }
    
    public T register(String additional) {
        block.setBlockName(resourceDomain + "_" + resourceName);
        block.setBlockTextureName(resourceDomain + ":" + additional + "/" + resourceName);
        gameRegister();
        registerLocalizedName();
        return block;
    }
    
    public T register(String additional, Class<? extends TileEntity> te) {
        register(additional);
        registerTileEntity(te);
        return block;
    }
    
    public T registerTileEntity(Class<? extends TileEntity> te) {
    	GameRegistry.registerTileEntity(te, "TileEntity" + uniqueName);
    	return block;
    }
    
    public T registerWithoutTexture() {
    	block.setBlockName(resourceDomain + "_" + resourceName);
    	gameRegister();
    	registerLocalizedName();
        return block;
    }
}

