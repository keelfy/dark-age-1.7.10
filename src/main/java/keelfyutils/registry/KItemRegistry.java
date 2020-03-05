package keelfyutils.registry;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import keelfyutils.str.KString;
import net.minecraft.item.Item;

/**
 * @author keelfy
 */
public final class KItemRegistry<T extends Item> {
	private static String resourceDomain = KString.EMPTY;

	public static final void setResourceDomain(final String resourceDomain) {
		KItemRegistry.resourceDomain = resourceDomain;
	}

	public static final <T extends Item> KItemRegistry<T> of(final String name, final T item) {
		return new KItemRegistry(name, item);
	}

	T item;
	String uniqueName;
	String resourceName;

	private KItemRegistry(final String name, final T item) {
		this.item = item;
		this.uniqueName = name;
		this.resourceName = name;
	}

	public KItemRegistry<T> withResource(final String name) {
		this.resourceName = name;
		return this;
	}

	private final T gameRegister() {
		GameRegistry.registerItem(item, uniqueName);
		return item;
	}

	public final T registerLocalizedName(final String name) {
		LanguageRegistry.addName(item, name);
		return item;
	}

	private final T registerLocalizedName() {
		LanguageRegistry.instance().addStringLocalization(uniqueName, KString.EMPTY);
		return item;
	}

	private final T registerTextureName(final String additional) {
		item.setTextureName(resourceDomain + ":" + additional + "/" + resourceName);
		return item;
	}

	private final T registerTextureName() {
		item.setTextureName(resourceDomain + ":" + resourceName);
		return item;
	}

	public final T register() {
		item.setUnlocalizedName(resourceDomain + "_" + uniqueName);
		registerWithoutName();
		return item;
	}

	public final T registerWithoutName() {
		registerTextureName();
		gameRegister();
		return item;
	}

	public final T registerWithoutNameWithLocName(final String locName) {
		registerWithoutName();
		registerLocalizedName(locName);
		return item;
	}

	public final T register(final String additional) {
		item.setUnlocalizedName(resourceDomain + "_" + uniqueName);
		registerTextureName(additional);
		registerLocalizedName();
		gameRegister();
		return item;
	}

	public final T registerWithLocalizedName(final String locName) {
		register();
		registerLocalizedName(locName);
		return item;
	}

	public final T registerWithoutName(final String additional) {
		registerTextureName(additional);
		gameRegister();
		return item;
	}

	public final T registerWithoutName(final String additional, final String locName) {
		registerWithoutName();
		registerLocalizedName(locName);
		return item;
	}

	public final T registerWithoutTexture() {
		item.setUnlocalizedName(resourceDomain + "_" + resourceName);
		registerLocalizedName();
		gameRegister();
		return item;
	}

	public final T registerWithoutTexture(final String locName) {
		registerWithoutTexture();
		registerLocalizedName(locName);
		return item;
	}
}
