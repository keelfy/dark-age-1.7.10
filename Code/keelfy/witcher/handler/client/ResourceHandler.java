package keelfy.witcher.handler.client;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.witcher.item.Melee;
import keelfy.witcher.util.DAUtil;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

/**
 * @author keelfy
 */
@SideOnly(Side.CLIENT)
public class ResourceHandler {
	
	private static String curModid = DAUtil.MODID;
	
	public static final ResourceLocation getSound(WCR type, String resName) {
		return new ResourceLocation(curModid, type.getPath() + resName + ".ogg");
	}
	
	public static enum WCR {
		MUSIC("sounds/music"),
		SOUND("sounds");
		
		private String path;
		private WCR(String path) {
			this.path = path + "/";
		}
		
		public String getPath() {
			return path;
		}
	}
	
	@SideOnly(Side.CLIENT)
	public static class Texture {
		public static final ResourceLocation get(WCT type, String texName) {
			return new ResourceLocation(curModid, type.getPath() + texName + ".png");
		}
		
		public static final ResourceLocation getSword(Item item, String str) {
			if(item instanceof Melee) {
				Melee sword = (Melee) item;
				return new ResourceLocation(curModid, WCT.SWORD.getPath() + sword.getSet() + "/models/" + sword.getTextureName() + "." + str + ".png");
			}
			return null;
		}
		
		public static enum WCT {
			ARMOR("textures/armor"),
			RARITY("textures/gui/inventory/rarity"),
			SWORD("textures/items/melee"),
			SCABBARD("textures/items/scabbard"),
			SABRE("textures/items/melee/sabre"),
			GUI("textures/gui"),
			INVENTORY("textures/gui/inventory"),
			SIGN("textures/gui/signs/graph"),
			SKIN("textures/models/skins"),
			ENTITY("textures/entity"),
			PARTICLE("textures/particle"),
			MEDALION("textures/models/medalion");
			
			private String path;
			private WCT(String path) {
				this.path = path + "/";
			}
			
			public String getPath() {
				return path;
			}
		}
	}
	
	@SideOnly(Side.CLIENT)
	public static class Model {
		
		public static final IModelCustom get(WCM type, String mName) {
			return AdvancedModelLoader.loadModel(new ResourceLocation(curModid, type.getPath() + mName + ".obj"));
		}
		
		public static final IModelCustom getSword(Item item) {
			if(item instanceof Melee) {
				Melee sword = (Melee) item;
				return AdvancedModelLoader.loadModel(new ResourceLocation(curModid, WCM.SWORD.getPath() + sword.getSet() + "/models/" + sword.getTextureName() + ".model.obj"));
			}
			return null;
		}

		public static final IModelCustom getSword(ModelType mType, Item item) {
			if(item instanceof Melee) {
				Melee sword = (Melee) item;
				switch(mType) {
				case OBJ: return AdvancedModelLoader.loadModel(new ResourceLocation(curModid, WCM.SWORD.getPath() + sword.getSet() + "/models/" + sword.getTextureName() + ".model." + mType.type));
				case KA: return AdvancedModelLoader.loadModel(new ResourceLocation(curModid, WCM.SWORD.getPath() + sword.getSet() + "/models/" + sword.getTextureName() + ".model." + mType.type));
				}
			}
			return null;
		}
		
		public static final IModelCustom get(ModelType mType, WCM type, String mName) {
			switch(mType) {
			case OBJ: return AdvancedModelLoader.loadModel(new ResourceLocation(curModid, type.getPath() + mName + "." + mType.type));
			case KA: return AdvancedModelLoader.loadModel(new ResourceLocation(curModid, type.getPath() + mName + "." + mType.type));
			}
			return null;
		}
		
		public static enum ModelType {
			OBJ("obj"),
			KA("ka");
			
			private String type;
			private ModelType(String type) {
				this.type = type;
			}
			
			public String type() {
				return type;
			}
		}
		
		public static enum WCM {
			SWORD("textures/items/melee"),
			SCABBARD("textures/items/scabbard"),
			SIGN("models/signs"),
			MEDALION("models/medalions");
			
			private String path;
			private WCM(String path) {
				this.path = path + "/";
			}
			
			public String getPath() {
				return path;
			}
		}
	}
}
