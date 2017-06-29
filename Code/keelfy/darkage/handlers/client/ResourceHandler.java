/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.handlers.client;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkage.constants.EnumModelFormat;
import keelfy.darkage.constants.EnumModelPath;
import keelfy.darkage.constants.EnumResourcePath;
import keelfy.darkage.constants.EnumTexturePath;
import keelfy.darkage.items.Sword;
import keelfy.darkage.utils.DAUtils;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

/**
 * @author keelfy
 */
@SideOnly(Side.CLIENT)
public class ResourceHandler {
	
	private static String curModid = DAUtils.MODID;
	
	public static final ResourceLocation getSound(EnumResourcePath type, String resName) {
		return new ResourceLocation(curModid, type.getPath() + resName + ".ogg");
	}
	
	@SideOnly(Side.CLIENT)
	public static class Texture {
		public static final ResourceLocation get(EnumTexturePath type, String texName) {
			return new ResourceLocation(curModid, type.getPath() + texName + ".png");
		}
		
		public static final ResourceLocation getSword(Item item, String str) {
			if(item instanceof Sword) {
				Sword sword = (Sword) item;
				return new ResourceLocation(curModid, EnumTexturePath.SWORD.getPath() + sword.getSet() + "/models/" + sword.getTextureName() + "." + str + ".png");
			}
			return null;
		}
	}
	
	@SideOnly(Side.CLIENT)
	public static class Model {
		
		public static final IModelCustom get(EnumModelPath type, String mName) {
			return AdvancedModelLoader.loadModel(new ResourceLocation(curModid, type.getPath() + mName + ".obj"));
		}
		
		public static final IModelCustom getSword(Item item) {
			if(item instanceof Sword) {
				Sword sword = (Sword) item;
				return AdvancedModelLoader.loadModel(new ResourceLocation(curModid, EnumModelPath.SWORD.getPath() + sword.getSet() + "/models/" + sword.getTextureName() + ".model.obj"));
			}
			return null;
		}

		public static final IModelCustom getSword(EnumModelFormat mType, Item item) {
			if(item instanceof Sword) {
				Sword sword = (Sword) item;
				switch(mType) {
				case OBJ: return AdvancedModelLoader.loadModel(new ResourceLocation(curModid, EnumModelPath.SWORD.getPath() + sword.getSet() + "/models/" + sword.getTextureName() + ".model." + mType.type()));
				case KA: return AdvancedModelLoader.loadModel(new ResourceLocation(curModid, EnumModelPath.SWORD.getPath() + sword.getSet() + "/models/" + sword.getTextureName() + ".model." + mType.type()));
				}
			}
			return null;
		}
		
		public static final IModelCustom get(EnumModelFormat mType, EnumModelPath type, String mName) {
			switch(mType) {
			case OBJ: return AdvancedModelLoader.loadModel(new ResourceLocation(curModid, type.getPath() + mName + "." + mType.type()));
			case KA: return AdvancedModelLoader.loadModel(new ResourceLocation(curModid, type.getPath() + mName + "." + mType.type()));
			}
			return null;
		}
	}
}
