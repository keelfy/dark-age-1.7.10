package keelfy.api.registry;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.item.Item;

/**
 * @author keelfy
 */
public class ItemRegistry<T extends Item> {
    private static String resourceDomain = "";
    
    public static void setResourceDomain(String resourceDomain) {
    	ItemRegistry.resourceDomain = resourceDomain;
    }

    public static <T extends Item> ItemRegistry<T> of(String name, T item) {
        return new ItemRegistry(name, item);
    }

    T item;
    String uniqueName;
    String resourceName;

    private ItemRegistry(String name, T item) {
        this.item = item;
        this.uniqueName = name;
        this.resourceName = name;
    }

    public ItemRegistry<T> withResource(String name) {
        this.resourceName = name;
        return this;
    }

    private T gameRegister() {
    	GameRegistry.registerItem(item, uniqueName);
    	return item;
    }
    
    private T registerLocalizedName(String name) {
    	LanguageRegistry.addName(item, name);
    	return item;
    }
    
    private T registerLocalizedName() {
    	LanguageRegistry.instance().addStringLocalization(uniqueName, "");
    	return item;
    }
    
    private T registerTextureName(String additional) {
    	item.setTextureName(resourceDomain + ":" + additional + "/" + resourceName);
		return item;
    }
    
    private T registerTextureName() {
    	item.setTextureName(resourceDomain + ":" + resourceName);
		return item;
    }
    
    public T register() {
        item.setUnlocalizedName(resourceDomain + "_" + uniqueName);
        registerWithoutName();
        return item;
    }
    
    public T registerWithoutName() {
    	registerTextureName();
        gameRegister();
        return item;
    }
  
    public T registerWithoutNameWithLocName(String locName) {
    	registerWithoutName();
    	registerLocalizedName(locName);
        return item;
    }
    
    public T register(String additional) {
        item.setUnlocalizedName(resourceDomain + "_" + uniqueName);
        registerTextureName(additional);
        registerLocalizedName();
        gameRegister();
        return item;
    }
    
    public T registerWithLocalizedName(String locName) {
    	register();
    	registerLocalizedName(locName);
    	return item;
    }
    
    public T registerWithoutName(String additional) {
    	registerTextureName(additional);
        gameRegister();
        return item;
    }
    
    public T registerWithoutName(String additional, String locName) {
    	registerWithoutName();
    	registerLocalizedName(locName);
        return item;
    }
    
    public T registerWithoutTexture() {
    	item.setUnlocalizedName(resourceDomain + "_" + resourceName);
    	registerLocalizedName();
    	gameRegister();
    	return item;
    }
    
    public T registerWithoutTexture(String locName) {
    	registerWithoutTexture();
    	registerLocalizedName(locName);
    	return item;
    }
}
