/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.items;

import keelfy.darkdata.DarkData;
import keelfy.darkdata.constants.EnumModelFormat;
import keelfy.darkdata.constants.EnumRarity;
import keelfyutils.KUtils;
import keelfyutils.str.KString;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

/**
 * @author keelfy
 * @created 6 июл. 2017 г.
 */
public abstract class ItemWithRender extends DAItem {

	private String set = KString.EMPTY;
	private String textureName = KString.EMPTY;
	private String objModel = KString.EMPTY;
	private ModelBase techneModel = null;
	private String iconName = KString.EMPTY;
	private String path = KString.EMPTY;
	public IItemRenderer renderer = null;
	private EnumModelFormat modelType;

	public ItemWithRender(final String path, final EnumRarity rarity, final float weight) {
		super(rarity, weight);

		if (KUtils.PROTECT_CLIENT) {
			if (KUtils.isClient()) {
				this.setResourcePath(path);
			}
		}
	}

	public ItemWithRender(final EnumRarity rarity, final float weight) {
		super(rarity, weight);
	}

	public final void setRenderer(final IItemRenderer renderer) {
		if (KUtils.PROTECT_CLIENT) {
			this.renderer = renderer;
			MinecraftForgeClient.registerItemRenderer(this, this.renderer);
		}
	}

	public final void setResourcePath(final String path) {
		this.path = path + "/";
	}

	@Override
	public final void registerIcons(final IIconRegister iiconRegister) {
		if (KUtils.PROTECT_CLIENT) {
			String texture = getIconName();

			if (KString.isEmpty(texture)) {
				texture = getTextureName();
			}

			this.itemIcon = iiconRegister.registerIcon(DarkData.MOD_ID + ":" + path + getSet() + "/" + texture);
		}
	}

	public final Item setTextureName(final String set, final String textureName) {
		if (KUtils.PROTECT_CLIENT) {
			this.set = set;
			this.textureName = textureName;
			return setTextureName(DarkData.MOD_ID + ":" + path + getSet() + "/" + getTextureName());
		}
		return this;
	}

	public final void setSet(final String set) {
		this.set = set;
	}

	public final String getSet() {
		return set;
	}

	public final Item setIconName(final String iconName) {
		this.iconName = iconName;
		return this;
	}

	public final String getIconName() {
		return iconName;
	}

	public final Item setModel(final EnumModelFormat format, String model) {
		if (KUtils.PROTECT_CLIENT) {
			this.modelType = format;

			switch (format) {
				case KA:
					break;
				case OBJ:
					objModel = model;
					break;
				case Techne:
					try {
						techneModel = (ModelBase) Class.forName("keelfy.darkage.client.models." + model).newInstance();
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
					break;
			}
		}
		return this;
	}

	public final Item setModelName(final String modelName) {
		this.objModel = modelName;
		this.modelType = EnumModelFormat.OBJ;
		return this;
	}

	public final EnumModelFormat getFormatOfModel() {
		return modelType;
	}

	public final String getOBJModel() {
		return objModel;
	}

	public final String getTextureName() {
		return textureName;
	}
}
