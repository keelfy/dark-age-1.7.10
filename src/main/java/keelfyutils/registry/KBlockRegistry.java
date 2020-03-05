package keelfyutils.registry;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import keelfyutils.str.KString;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;

/**
 * @author keelfy
 */
public class KBlockRegistry<T extends Block> {
	private static String resourceDomain = KString.EMPTY;

	public static final void setResourceDomain(final String resourceDomain) {
		KBlockRegistry.resourceDomain = resourceDomain;
	}

	public static final <T extends Block> KBlockRegistry<T> of(final String name, final T block) {
		return new KBlockRegistry(name, block);
	}

	T block;
	String uniqueName;
	String resourceName;

	private KBlockRegistry(final String name, final T block) {
		this.block = block;
		this.uniqueName = name;
		this.resourceName = name;
	}

	public KBlockRegistry<T> withResource(final String name) {
		this.resourceName = name;
		return this;
	}

	private final T gameRegister() {
		GameRegistry.registerBlock(block, uniqueName);
		return block;
	}

	private final T registerLocalizedName() {
		LanguageRegistry.instance().addStringLocalization(uniqueName, KString.EMPTY);
		return block;
	}

	public final T register() {
		block.setBlockName(resourceDomain + resourceName);
		block.setBlockTextureName(resourceDomain + KString.RESOURCE_SPLITTER + resourceName);
		gameRegister();
		registerLocalizedName();
		return block;
	}

	public final T register(final String additional) {
		block.setBlockName(resourceDomain + "_" + resourceName);
		block.setBlockTextureName(resourceDomain + KString.RESOURCE_SPLITTER + additional + "/" + resourceName);
		gameRegister();
		registerLocalizedName();
		return block;
	}

	public final T register(final String additional, final Class<? extends TileEntity> te) {
		register(additional);
		registerTileEntity(te);
		return block;
	}

	public final T register(final Class<? extends TileEntity> te) {
		register();
		registerTileEntity(te);
		return block;
	}

	public final T registerTileEntity(final Class<? extends TileEntity> te) {
		GameRegistry.registerTileEntity(te, "TileEntity" + uniqueName);
		return block;
	}

	public final T registerWithoutTexture() {
		block.setBlockName(resourceDomain + "_" + resourceName);
		gameRegister();
		registerLocalizedName();
		return block;
	}
}
