/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.client;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkdata.DarkData;
import keelfy.darkdata.constants.EnumModelPath;
import keelfy.darkdata.constants.EnumResourcePath;
import keelfy.darkdata.constants.EnumTexturePath;
import keelfyutils.client.DisplayListWrapper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import net.minecraftforge.client.model.obj.WavefrontObject;

/**
 * @author keelfy
 */
@SideOnly(Side.CLIENT)
public final class DAResources {

	public static final ResourceLocation Texture_Signs = Texture.get(EnumTexturePath.GUI, "Signs");
	public static final ResourceLocation Texture_Skin_Human = Texture.get(EnumTexturePath.Skin, "Human");
	public static final ResourceLocation Texture_Skin_Witcher = Texture.get(EnumTexturePath.Skin, "Witcher");

	public static final ResourceLocation getSound(final EnumResourcePath type, final String resName) {
		return new ResourceLocation(DarkData.MOD_ID, type.getPath() + resName + ".ogg");
	}

	@SideOnly(Side.CLIENT)
	public static final class Texture {
		public static final ResourceLocation get(final EnumTexturePath type, final String texName) {
			return new ResourceLocation(DarkData.MOD_ID, type.getPath() + texName + ".png");
		}

		public static final ResourceLocation getCN(final String path) {
			return new ResourceLocation("customnpcs", path);
		}

		public static final ResourceLocation getCN(final EnumTexturePath type, final String texName) {
			return new ResourceLocation("customnpcs", type.getPath() + texName + ".png");
		}
	}

	@SideOnly(Side.CLIENT)
	public static final class Model {

		public static final DisplayListWrapper getWithDL(final EnumModelPath type, final String mName) {
			return new DisplayListWrapper((WavefrontObject) AdvancedModelLoader
					.loadModel(new ResourceLocation(DarkData.MOD_ID, type.getPath() + mName + ".obj")));
		}
		
		public static final DisplayListWrapper getWithDL(String modid, String path, final String mName) {
			return new DisplayListWrapper((WavefrontObject) AdvancedModelLoader
					.loadModel(new ResourceLocation(modid, path + "/"  + mName + ".obj")));
		}

		public static final IModelCustom get(final EnumModelPath type, final String mName) {
			return AdvancedModelLoader.loadModel(new ResourceLocation(DarkData.MOD_ID, type.getPath() + mName + ".obj"));
		}
	}
}
