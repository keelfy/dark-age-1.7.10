package keelfy.witcher.client.render;

import org.lwjgl.opengl.GL11;

import api.player.render.RenderPlayerAPI;
import api.player.render.RenderPlayerBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.witcher.client.models.armor.BelhavenaBrigantine.BelhavenaBrigantineBody;
import keelfy.witcher.client.models.armor.BelhavenaBrigantine.BelhavenaBrigantineHand;
import keelfy.witcher.client.models.armor.BelhavenaBrigantine.BelhavenaBrigantineLeg;
import keelfy.witcher.client.models.armor.HindarsfjallHeavyArmor.HindarsfjallHeavyArmorBody;
import keelfy.witcher.client.models.armor.HindarsfjallHeavyArmor.HindarsfjallHeavyArmorHand;
import keelfy.witcher.client.models.armor.HindarsfjallHeavyArmor.HindarsfjallHeavyArmorLeg;
import keelfy.witcher.client.models.armor.RedanianHalebardist.RedanianHalebardistAddon;
import keelfy.witcher.client.models.armor.RedanianHalebardist.RedanianHalebardistArmor;
import keelfy.witcher.client.models.armor.RedanianHalebardist.RedanianHalebardistLeg;
import keelfy.witcher.client.models.armor.Shirt.ShirtAddon;
import keelfy.witcher.client.models.armor.Shirt.ShirtHand;
import keelfy.witcher.client.models.armor.Shirt.ShirtMody;
import keelfy.witcher.client.models.armor.bear1.BearSchoolArmor;
import keelfy.witcher.client.models.armor.bear1.BearSchoolHand;
import keelfy.witcher.client.models.armor.cat1.CatSchoolArm;
import keelfy.witcher.client.models.armor.cat1.CatSchoolArmor;
import keelfy.witcher.client.models.armor.cat1.CatSchoolBoot;
import keelfy.witcher.client.models.armor.cat1.CatSchoolGlove;
import keelfy.witcher.client.models.armor.cat1.CatSchoolLeg;
import keelfy.witcher.client.models.armor.cidaris.CidarisLeg;
import keelfy.witcher.client.models.armor.common.Sign;
import keelfy.witcher.client.models.armor.common.WitcherLuriskyPants;
import keelfy.witcher.client.models.armor.common.WitcherPathfinderLegs;
import keelfy.witcher.client.models.armor.common.WitcherTopornikPants;
import keelfy.witcher.client.models.armor.griffin.GriffinArmor;
import keelfy.witcher.client.models.armor.griffin.GriffinGlove;
import keelfy.witcher.client.models.armor.griffin.GriffinHand;
import keelfy.witcher.client.models.armor.griffin.GriffinLeg;
import keelfy.witcher.client.models.armor.kaermorhen.KaerMorhenArmor;
import keelfy.witcher.client.models.armor.kaermorhen.KaerMorhenHand;
import keelfy.witcher.client.models.armor.kaermorhen.KaerMorhenHandAddon;
import keelfy.witcher.client.models.armor.magdeithra.MagDeithraArmor;
import keelfy.witcher.client.models.armor.magdeithra.MagDeithraHand;
import keelfy.witcher.client.models.armor.magdeithra.MagDeithraLeftLeg;
import keelfy.witcher.client.models.armor.magdeithra.MagDeithraLegAddon;
import keelfy.witcher.client.models.armor.magdeithra.MagDeithraRightLeg;
import keelfy.witcher.client.models.armor.nilfgaard.NilfgaardArmor;
import keelfy.witcher.client.models.armor.nilfgaard.NilfgaardArmorAddon;
import keelfy.witcher.client.models.armor.nilfgaard.NilfgaardGlove;
import keelfy.witcher.client.models.armor.nilfgaard.NilfgaardHead;
import keelfy.witcher.client.models.armor.nilfgaard.NilfgaardLeg;
import keelfy.witcher.client.models.armor.talgar.TalgarBody;
import keelfy.witcher.client.models.armor.talgar.TalgarHand;
import keelfy.witcher.client.models.armor.talgar.TalgarLeg;
import keelfy.witcher.client.models.armor.witchunter.WitchunterArmorBody;
import keelfy.witcher.client.models.armor.witchunter.WitchunterArmorHand;
import keelfy.witcher.client.models.armor.witchunter.WitchunterArmorLeg;
import keelfy.witcher.client.models.armor.wolf.WolfArmor;
import keelfy.witcher.client.models.armor.wolf.WolfGlove;
import keelfy.witcher.client.models.armor.wolf.WolfHand;
import keelfy.witcher.client.models.armor.wolf.WolfLeg;
import keelfy.witcher.client.models.entity.player.ModelBody;
import keelfy.witcher.client.models.entity.player.ModelBoot;
import keelfy.witcher.client.models.entity.player.ModelGlove;
import keelfy.witcher.client.models.entity.player.ModelLeg;
import keelfy.witcher.client.models.entity.player.WitcherLeftLeg;
import keelfy.witcher.client.models.entity.player.WitcherRightLeg;
import keelfy.witcher.client.models.entity.sign.ModelSignKven;
import keelfy.witcher.entity.player.DAPlayer;
import keelfy.witcher.handler.client.ResourceHandler.Model;
import keelfy.witcher.handler.client.ResourceHandler.Model.WCM;
import keelfy.witcher.handler.client.ResourceHandler.Texture;
import keelfy.witcher.handler.client.ResourceHandler.Texture.WCT;
import keelfy.witcher.handler.registers.ArmorRegister;
import keelfy.witcher.inventory.player.DAInventory;
import keelfy.witcher.item.Melee;
import keelfy.witcher.util.DAUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelCustom;

/**
 * @author keelfy
 */
@SideOnly(Side.CLIENT)
public class RenderDAPlayer extends RenderPlayerBase {

	private final Minecraft mc = Minecraft.getMinecraft();
	
	private final Sign Sign = new Sign();
	private final ModelSignKven kven = new ModelSignKven();
	private final ResourceLocation textureKven = Texture.get(WCT.PARTICLE, "kven");
	private final ModelLeg WitcherLeg = new ModelLeg();
	private final ModelBoot WitcherModelBoot = new ModelBoot();
	private final ModelBody ModelSkirt = new ModelBody();
	private final WitcherLeftLeg WitcherLeftLeg = new WitcherLeftLeg();
	private final WitcherRightLeg WitcherRightLeg = new WitcherRightLeg();
	private final TalgarBody TalgarBody = new TalgarBody();
	private final TalgarLeg TalgarLeg = new TalgarLeg();
	private final TalgarHand TalgarHand = new TalgarHand();
	private final ResourceLocation TalgarT = Texture.get(WCT.ARMOR, "Talgar/Talgar");
	private final ResourceLocation TalgarSignT = Texture.get(WCT.ARMOR, "Talgar/TalgarSign");
	private final WitcherTopornikPants WitcherTopornikPants = new WitcherTopornikPants();
	private final ResourceLocation WitcherTopornikPantsT = Texture.get(WCT.ARMOR, "Pants/TopornikPants");
	private final WitcherPathfinderLegs WitcherPathfinderLegs = new WitcherPathfinderLegs();
	private final ResourceLocation WitcherPathfinderPantsT = Texture.get(WCT.ARMOR, "Pants/PathfinderLegs");
	private final WitcherLuriskyPants WitcherLuriskyPants = new WitcherLuriskyPants();
	private final ResourceLocation WitcherLuriskyPantsT = Texture.get(WCT.ARMOR, "Pants/LuriskyPants");
	private final ResourceLocation CidarisCombezT = Texture.get(WCT.ARMOR, "Cidaris/CidarisCombez");
	private final BelhavenaBrigantineBody BelhavenaBrigantineBody = new BelhavenaBrigantineBody();
	private final BelhavenaBrigantineHand BelhavenaBrigantineHand = new BelhavenaBrigantineHand();
	private final BelhavenaBrigantineLeg BelhavenaBrigantineLeg = new BelhavenaBrigantineLeg();
	private final ResourceLocation BelhavenaBrigantineT = Texture.get(WCT.ARMOR, "BelhavenaBrigantine/BelhavenaBrigantine");
	private final CidarisLeg CidarisLeg = new CidarisLeg();
	private final ResourceLocation CidarisT = Texture.get(WCT.ARMOR, "Cidaris/Cidaris");
	private final WitchunterArmorBody WitchunterArmorBody = new WitchunterArmorBody();
	private final WitchunterArmorHand WitchunterArmorHand = new WitchunterArmorHand();
	private final WitchunterArmorLeg WitchunterArmorLeg = new WitchunterArmorLeg();
	private final ResourceLocation WitchunterArmorT = Texture.get(WCT.ARMOR, "WitchunterArmor/WitchunterArmorT");
	private final HindarsfjallHeavyArmorBody HindarsfjallHeavyArmorBody = new HindarsfjallHeavyArmorBody();
	private final HindarsfjallHeavyArmorHand HindarsfjallHeavyArmorHand = new HindarsfjallHeavyArmorHand();
	private final HindarsfjallHeavyArmorLeg HindarsfjallHeavyArmorLeg = new HindarsfjallHeavyArmorLeg();
	private final ResourceLocation HindarsfjallHeavyArmorT = Texture.get(WCT.ARMOR, "HindarsfjallHeavyArmor/HindarsfjallHeavyArmor");
	private final BearSchoolHand BearSchoolHand = new BearSchoolHand();
	private final BearSchoolArmor BearSchoolArmor = new BearSchoolArmor();
	private final ResourceLocation BearSchoolT = Texture.get(WCT.ARMOR, "bearSchool/Armor");
	private final ResourceLocation BearSchoolBootT = Texture.get(WCT.ARMOR, "bearSchool/Boot");
	private final ResourceLocation BearSchoolSkirtT = Texture.get(WCT.ARMOR, "bearSchool/Skirt");
	private final ResourceLocation BearSchoolT2 = Texture.get(WCT.ARMOR, "bearSchool/2/Armor");
	private final ResourceLocation BearSchoolSkirtT2 = Texture.get(WCT.ARMOR, "bearSchool/2/Skirt");
	private final CatSchoolArmor CatSchoolArmor = new CatSchoolArmor();
	private final CatSchoolLeg CatSchoolLeg = new CatSchoolLeg();
	private final CatSchoolGlove CatSchoolGlove = new CatSchoolGlove();
	private final CatSchoolArm CatSchoolSleeve = new CatSchoolArm();
	private final CatSchoolBoot CatSchoolBoot = new CatSchoolBoot();
	private final ResourceLocation CatPantsT = Texture.get(WCT.ARMOR, "catSchool/Pants");
	private final ResourceLocation CatSchoolArmorT = Texture.get(WCT.ARMOR, "catSchool/Armor");
	private final GriffinArmor GriffinArmor = new GriffinArmor();
	private final GriffinHand GriffinHand = new GriffinHand();
	private final GriffinLeg GriffinLeg = new GriffinLeg();
	private final GriffinGlove GriffinGlove = new GriffinGlove();
	private final ResourceLocation GriffinArmorT = Texture.get(WCT.ARMOR, "griffinSchool/GriffinArmor");
	private final ResourceLocation GriffinPantsT = Texture.get(WCT.ARMOR, "griffinSchool/Pants");
	private final ResourceLocation GriffinBootsT = Texture.get(WCT.ARMOR, "griffinSchool/Boot");
	private final KaerMorhenArmor KaerMorhenArmor = new KaerMorhenArmor();
	private final KaerMorhenHand KaerMorhenHand = new KaerMorhenHand();
	private final KaerMorhenHandAddon KaerMorhenHandAddon = new KaerMorhenHandAddon();
	private final ModelGlove WitcherModelGlove = new ModelGlove();
	private final ResourceLocation KaerMorhenArmorT = Texture.get(WCT.ARMOR, "KaerMorhen/Armor");
	private final ResourceLocation KaerMorhenArmorHandT = Texture.get(WCT.ARMOR, "KaerMorhen/Hands");
	private final ResourceLocation KaerMorhenArmorPantsT = Texture.get(WCT.ARMOR, "KaerMorhen/Pants");
	private final ResourceLocation KaerMorhenArmorBootsT = Texture.get(WCT.ARMOR, "KaerMorhen/Boots");
	private final WolfArmor WolfArmor = new WolfArmor();
	private final WolfHand WolfHand = new WolfHand();
	private final WolfGlove WolfGlove = new WolfGlove();
	private final WolfLeg WolfLeg = new WolfLeg();
	private final ResourceLocation WolfT = Texture.get(WCT.ARMOR, "wolfSchool/WolfSchool");
	private final ResourceLocation WolfBootT = Texture.get(WCT.ARMOR, "wolfSchool/WolfSchoolBoot");
	private final RedanianHalebardistArmor RedanianHalebardistArmor = new RedanianHalebardistArmor();
	private final RedanianHalebardistLeg RedanianHalebardistLeg = new RedanianHalebardistLeg();
	private final RedanianHalebardistAddon RedanianHalebardistAddon = new RedanianHalebardistAddon();
	private final ResourceLocation RedanianHalebardistArmorT = Texture.get(WCT.ARMOR, "RedanianHalebardistArmor/Armor");
	private final NilfgaardArmor NilfgaardArmor = new NilfgaardArmor();
	private final NilfgaardHead NilfgaardHead = new NilfgaardHead();
	private final NilfgaardArmorAddon NilfgaardArmorAddon = new NilfgaardArmorAddon();
	private final NilfgaardGlove NilfgaardGlove = new NilfgaardGlove();
	private final NilfgaardLeg NilfgaardLeg = new NilfgaardLeg();
	private final ResourceLocation NilfgaardArmorT = Texture.get(WCT.ARMOR, "Nilfgaard/Armor");
	private final ResourceLocation NilfgaardArmorLegT = Texture.get(WCT.ARMOR, "Nilfgaard/Leg");
	private final ResourceLocation NilfgaardArmorBootT = Texture.get(WCT.ARMOR, "Nilfgaard/Boot");
	private final MagDeithraArmor MagDeithraArmor = new MagDeithraArmor();
	private final MagDeithraHand MagDeithraHand = new MagDeithraHand();
	private final MagDeithraLeftLeg MagDeithraLeftLeg = new MagDeithraLeftLeg();
	private final MagDeithraRightLeg MagDeithraRightLeg = new MagDeithraRightLeg();
	private final MagDeithraLegAddon MagDeithraLegAddon = new MagDeithraLegAddon();
	private final ResourceLocation MagDeithraArmorT = Texture.get(WCT.ARMOR, "MagDeithraArmor/Armor");
	private final ResourceLocation ViperArmorT = Texture.get(WCT.ARMOR, "viperSchool/Armor");
	private final ResourceLocation ViperArmorHandT = Texture.get(WCT.ARMOR, "viperSchool/Hands");
	private final ResourceLocation ViperArmorPantsT = Texture.get(WCT.ARMOR, "viperSchool/Pants");
	private final ResourceLocation ViperArmorBootsT = Texture.get(WCT.ARMOR, "viperSchool/Boots");
	private final ShirtMody ShirtBody = new ShirtMody();
	private final ShirtHand ShirtHand = new ShirtHand();
	private final ShirtAddon ShirtAddon = new ShirtAddon();
	private final ResourceLocation ShirtT = Texture.get(WCT.ARMOR, "Shirt/WitcherShirt");
	private final IModelCustom CatMedialion = Model.get(WCM.MEDALION, "CatMedallion");
	private final ResourceLocation CatMedialionT = Texture.get(WCT.MEDALION, "CatMedallion");
	private final IModelCustom WolfMedialion = Model.get(WCM.MEDALION, "WolfMedallion");
	private final ResourceLocation WolfMedialionT = Texture.get(WCT.MEDALION, "WolfMedallion");
	private final IModelCustom GriffinMedialion = Model.get(WCM.MEDALION, "GriffinMedallion");
	private final ResourceLocation GriffinMedialionT = Texture.get(WCT.ARMOR, "school/Griffin/1/texlow");
	private final IModelCustom BearMedialion = Model.get(WCM.MEDALION, "BearMedallion");
	private final ResourceLocation BearMedialionT = Texture.get(WCT.ARMOR, "school/Bear/1/texlow");
	
	private final ResourceLocation skinHuman = Texture.get(WCT.SKIN, "Human");
	private final ResourceLocation skinWitcher = Texture.get(WCT.SKIN, "Witcher");
	
	public RenderDAPlayer(RenderPlayerAPI renderPlayerAPI) {
		super(renderPlayerAPI);
	}
	
	@Override
	public void loadTextureOfEntity(Entity paramEntity) {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			if(paramEntity instanceof EntityPlayer && DAPlayer.get(mc.thePlayer) != null) {
				//ONLY IN ZBT
				mc.getTextureManager().bindTexture(skinWitcher);
				
//				if(WCPlayer.get((EntityPlayer)paramEntity).getPlayerClass() == PlayerClass.HUMAN) 
//					mc.getTextureManager().bindTexture(skinHuman);
//				else 
//					mc.getTextureManager().bindTexture(skinWitcher);
			}
		}
	}
	
	private float r;
	@Override
	public void renderSpecials(AbstractClientPlayer player, float par2) {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			DAPlayer wcp = DAPlayer.get(player);
			if(wcp != null) {
				super.renderSpecials(player, par2);

				DAInventory inv = wcp.inventory;
				ItemStack item = player.getHeldItem();
		
		if(item == null || !(item.getItem() instanceof Melee) || !player.isUsingItem()) {
			if(item != null && item.getItem() instanceof Melee) {
				if(!player.isRiding() && player.swingProgress != 0.0F) {
					GL11.glPushMatrix();
					super.renderPlayer.modelBipedMain.bipedLeftArm.offsetX = 0.0F;
					super.renderPlayer.modelBipedMain.bipedLeftArm.offsetY = 0.0F;
					super.renderPlayer.modelBipedMain.bipedLeftArm.offsetZ = 0.0F;
					GL11.glPopMatrix();
				}
			} else if(item != null && item.getItem() instanceof Melee && !player.isUsingItem()) {
				GL11.glPushMatrix();
	            super.renderPlayer.modelBipedMain.bipedLeftArm.postRender(0.0625F);
	            super.renderPlayer.modelBipedMain.bipedLeftArm.offsetX = -0.15F;
	            super.renderPlayer.modelBipedMain.bipedLeftArm.offsetY = -0.05F;
	            super.renderPlayer.modelBipedMain.bipedLeftArm.offsetZ = -0.15F;
	            GL11.glPopMatrix();
			} else {
				GL11.glPushMatrix();
	            super.renderPlayer.modelBipedMain.bipedLeftArm.offsetX = 0.0F;
	            super.renderPlayer.modelBipedMain.bipedLeftArm.offsetY = 0.0F;
	            super.renderPlayer.modelBipedMain.bipedLeftArm.offsetZ = 0.0F;
	            GL11.glPopMatrix();
			}
		}
	     if(player.getCurrentArmor(2) != null) {
	         if(player.getCurrentArmor(2) != null && player.getCurrentArmor(2).getItem() == ArmorRegister.cidarianCavalrymanChestplate) {
	            GL11.glPushMatrix();
	            super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
	            GL11.glTranslatef(0.0F, -0.05F, 0.0F);
	            GL11.glScalef(1.1F, 1.1F, 1.1F);
	            mc.getTextureManager().bindTexture(CidarisT);
	            this.RedanianHalebardistArmor.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(CidarisT);
	            super.renderPlayer.modelBipedMain.bipedLeftArm.postRender(0.0625F);
	            GL11.glTranslatef(-0.33F, -0.14F, 0.0F);
	            GL11.glScalef(1.05F, 1.05F, 1.05F);
	            this.RedanianHalebardistAddon.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(CidarisT);
	            super.renderPlayer.modelBipedMain.bipedRightArm.postRender(0.0625F);
	            GL11.glTranslatef(0.33F, -0.14F, 0.0F);
	            GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
	            GL11.glScalef(1.05F, 1.05F, 1.05F);
	            this.RedanianHalebardistAddon.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(CidarisT);
	            super.renderPlayer.modelBipedMain.bipedRightLeg.postRender(0.0625F);
	            GL11.glTranslatef(0.15F, -0.95F, 0.0F);
	            GL11.glScalef(-1.15F, 1.15F, 1.15F);
	            this.CidarisLeg.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(CidarisT);
	            super.renderPlayer.modelBipedMain.bipedLeftLeg.postRender(0.0625F);
	            GL11.glTranslatef(-0.15F, -0.95F, 0.0F);
	            GL11.glScalef(1.15F, 1.15F, 1.15F);
	            this.CidarisLeg.render(0.0625F);
	            GL11.glPopMatrix();
	         }

	         if(player.getCurrentArmor(2) != null && player.getCurrentArmor(2).getItem() == ArmorRegister.cidarianChestplate) {
	        	 GL11.glPushMatrix();
	            super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
	            GL11.glTranslatef(0.0F, -0.05F, 0.0F);
	            GL11.glScalef(1.1F, 1.1F, 1.1F);
	            mc.getTextureManager().bindTexture(CidarisCombezT);
	            this.BelhavenaBrigantineBody.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(CidarisCombezT);
	            super.renderPlayer.modelBipedMain.bipedLeftArm.postRender(0.0625F);
	            GL11.glTranslatef(-0.33F, -0.14F, 0.0F);
	            GL11.glScalef(1.05F, 1.05F, 1.05F);
	            this.BelhavenaBrigantineHand.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(CidarisCombezT);
	            super.renderPlayer.modelBipedMain.bipedRightArm.postRender(0.0625F);
	            GL11.glTranslatef(0.33F, -0.14F, 0.0F);
	            GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
	            GL11.glScalef(1.05F, 1.05F, 1.05F);
	            this.BelhavenaBrigantineHand.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(CidarisCombezT);
	            super.renderPlayer.modelBipedMain.bipedRightLeg.postRender(0.0625F);
	            GL11.glTranslatef(0.15F, -0.89F, 0.0F);
	            GL11.glScalef(-1.15F, 1.15F, 1.15F);
	            this.BelhavenaBrigantineLeg.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(CidarisCombezT);
	            super.renderPlayer.modelBipedMain.bipedLeftLeg.postRender(0.0625F);
	            GL11.glTranslatef(-0.15F, -0.89F, 0.0F);
	            GL11.glScalef(1.15F, 1.15F, 1.15F);
	            this.BelhavenaBrigantineLeg.render(0.0625F);
	            GL11.glPopMatrix();
	         }

	         if(player.getCurrentArmor(2) != null && player.getCurrentArmor(2).getItem() == ArmorRegister.cloth) {
	            GL11.glPushMatrix();
	            super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
	            GL11.glTranslatef(0.0F, -0.05F, 0.0F);
	            GL11.glScalef(1.0F, 1.07F, 1.0F);
	            mc.getTextureManager().bindTexture(ShirtT);
	            this.ShirtBody.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
	            GL11.glTranslatef(0.0F, -0.05F, 0.0F);
	            GL11.glScalef(0.96F, 0.96F, 0.96F);
	            mc.getTextureManager().bindTexture(ShirtT);
	            this.ShirtAddon.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(ShirtT);
	            super.renderPlayer.modelBipedMain.bipedLeftArm.postRender(0.0625F);
	            GL11.glTranslatef(-0.315F, -0.14F, 0.0F);
	            GL11.glScalef(1.0F, 1.0F, 1.0F);
	            this.ShirtHand.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(ShirtT);
	            super.renderPlayer.modelBipedMain.bipedRightArm.postRender(0.0625F);
	            GL11.glTranslatef(0.315F, -0.14F, 0.0F);
	            GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
	            GL11.glScalef(1.0F, 1.0F, 1.0F);
	            this.ShirtHand.render(0.0625F);
	            GL11.glPopMatrix();
	         }

	         if(player.getCurrentArmor(2) != null && player.getCurrentArmor(2).getItem() == ArmorRegister.infantryChestplate) {
	            GL11.glPushMatrix();
	            super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
	            GL11.glTranslatef(0.0F, -0.05F, 0.0F);
	            GL11.glScalef(1.1F, 1.1F, 1.1F);
	            mc.getTextureManager().bindTexture(BelhavenaBrigantineT);
	            this.BelhavenaBrigantineBody.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(BelhavenaBrigantineT);
	            super.renderPlayer.modelBipedMain.bipedLeftArm.postRender(0.0625F);
	            GL11.glTranslatef(-0.33F, -0.14F, 0.0F);
	            GL11.glScalef(1.05F, 1.05F, 1.05F);
	            this.BelhavenaBrigantineHand.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(BelhavenaBrigantineT);
	            super.renderPlayer.modelBipedMain.bipedRightArm.postRender(0.0625F);
	            GL11.glTranslatef(0.33F, -0.14F, 0.0F);
	            GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
	            GL11.glScalef(1.05F, 1.05F, 1.05F);
	            this.BelhavenaBrigantineHand.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(BelhavenaBrigantineT);
	            super.renderPlayer.modelBipedMain.bipedRightLeg.postRender(0.0625F);
	            GL11.glTranslatef(0.15F, -0.89F, 0.0F);
	            GL11.glScalef(-1.15F, 1.15F, 1.15F);
	            this.BelhavenaBrigantineLeg.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(BelhavenaBrigantineT);
	            super.renderPlayer.modelBipedMain.bipedLeftLeg.postRender(0.0625F);
	            GL11.glTranslatef(-0.15F, -0.89F, 0.0F);
	            GL11.glScalef(1.15F, 1.15F, 1.15F);
	            this.BelhavenaBrigantineLeg.render(0.0625F);
	            GL11.glPopMatrix();
	         }

	         if(player.getCurrentArmor(2) != null && player.getCurrentArmor(2).getItem() == ArmorRegister.alebardistChestplate) {
	            GL11.glPushMatrix();
	            super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
	            GL11.glTranslatef(0.0F, -0.05F, 0.0F);
	            GL11.glScalef(1.1F, 1.1F, 1.1F);
	            mc.getTextureManager().bindTexture(RedanianHalebardistArmorT);
	            this.RedanianHalebardistArmor.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(RedanianHalebardistArmorT);
	            super.renderPlayer.modelBipedMain.bipedLeftArm.postRender(0.0625F);
	            GL11.glTranslatef(-0.33F, -0.14F, 0.0F);
	            GL11.glScalef(1.05F, 1.05F, 1.05F);
	            this.RedanianHalebardistAddon.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(RedanianHalebardistArmorT);
	            super.renderPlayer.modelBipedMain.bipedRightArm.postRender(0.0625F);
	            GL11.glTranslatef(0.33F, -0.14F, 0.0F);
	            GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
	            GL11.glScalef(1.05F, 1.05F, 1.05F);
	            this.RedanianHalebardistAddon.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(RedanianHalebardistArmorT);
	            super.renderPlayer.modelBipedMain.bipedRightLeg.postRender(0.0625F);
	            GL11.glTranslatef(0.15F, -0.95F, 0.0F);
	            GL11.glScalef(-1.15F, 1.15F, 1.15F);
	            this.RedanianHalebardistLeg.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(RedanianHalebardistArmorT);
	            super.renderPlayer.modelBipedMain.bipedLeftLeg.postRender(0.0625F);
	            GL11.glTranslatef(-0.15F, -0.95F, 0.0F);
	            GL11.glScalef(1.15F, 1.15F, 1.15F);
	            this.RedanianHalebardistLeg.render(0.0625F);
	            GL11.glPopMatrix();
	         }

	         if(player.getCurrentArmor(2) != null && player.getCurrentArmor(2).getItem() == ArmorRegister.hindarsfjallChestplate) {
	            GL11.glPushMatrix();
	            super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
	            GL11.glTranslatef(0.0F, -0.05F, 0.0F);
	            GL11.glScalef(1.1F, 1.1F, 1.1F);
	            mc.getTextureManager().bindTexture(HindarsfjallHeavyArmorT);
	            this.HindarsfjallHeavyArmorBody.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(HindarsfjallHeavyArmorT);
	            super.renderPlayer.modelBipedMain.bipedLeftArm.postRender(0.0625F);
	            GL11.glTranslatef(-0.33F, -0.14F, 0.0F);
	            GL11.glScalef(1.05F, 1.05F, 1.05F);
	            this.HindarsfjallHeavyArmorHand.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(HindarsfjallHeavyArmorT);
	            super.renderPlayer.modelBipedMain.bipedRightArm.postRender(0.0625F);
	            GL11.glTranslatef(0.33F, -0.14F, 0.0F);
	            GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
	            GL11.glScalef(1.05F, 1.05F, 1.05F);
	            this.HindarsfjallHeavyArmorHand.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(HindarsfjallHeavyArmorT);
	            super.renderPlayer.modelBipedMain.bipedRightLeg.postRender(0.0625F);
	            GL11.glTranslatef(0.13F, -0.76F, 0.0F);
	            GL11.glScalef(-1.15F, 1.05F, 1.15F);
	            this.HindarsfjallHeavyArmorLeg.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(HindarsfjallHeavyArmorT);
	            super.renderPlayer.modelBipedMain.bipedLeftLeg.postRender(0.0625F);
	            GL11.glTranslatef(-0.13F, -0.76F, 0.0F);
	            GL11.glScalef(1.15F, 1.05F, 1.15F);
	            this.HindarsfjallHeavyArmorLeg.render(0.0625F);
	            GL11.glPopMatrix();
	         }

	         if(player.getCurrentArmor(2) != null && player.getCurrentArmor(2).getItem() == ArmorRegister.redanCavalrymanChestplate) {
	            GL11.glPushMatrix();
	            super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
	            GL11.glTranslatef(0.0F, -0.05F, 0.0F);
	            GL11.glScalef(1.1F, 1.1F, 1.1F);
	            mc.getTextureManager().bindTexture(WitchunterArmorT);
	            this.WitchunterArmorBody.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(WitchunterArmorT);
	            super.renderPlayer.modelBipedMain.bipedRightArm.postRender(0.0625F);
	            GL11.glTranslatef(0.33F, -0.14F, 0.0F);
	            GL11.glScalef(1.05F, 1.05F, 1.05F);
	            this.WitchunterArmorHand.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(WitchunterArmorT);
	            super.renderPlayer.modelBipedMain.bipedLeftArm.postRender(0.0625F);
	            GL11.glTranslatef(0.46F, -0.14F, 0.0F);
	            GL11.glScalef(1.05F, 1.05F, 1.05F);
	            this.WitchunterArmorHand.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(WitchunterArmorT);
	            super.renderPlayer.modelBipedMain.bipedLeftLeg.postRender(0.0625F);
	            GL11.glTranslatef(-0.13F, -0.76F, 0.0F);
	            GL11.glScalef(-1.15F, 1.05F, 1.15F);
	            this.WitchunterArmorLeg.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(WitchunterArmorT);
	            super.renderPlayer.modelBipedMain.bipedRightLeg.postRender(0.0625F);
	            GL11.glTranslatef(0.13F, -0.76F, 0.0F);
	            GL11.glScalef(1.15F, 1.05F, 1.15F);
	            this.WitchunterArmorLeg.render(0.0625F);
	            GL11.glPopMatrix();
	         }

	         if(player.getCurrentArmor(2) != null && player.getCurrentArmor(2).getItem() == ArmorRegister.ellanderChestplate) {
	            GL11.glPushMatrix();
	            super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
	            GL11.glTranslatef(0.0F, -0.05F, 0.0F);
	            GL11.glScalef(1.1F, 1.1F, 1.1F);
	            mc.getTextureManager().bindTexture(MagDeithraArmorT);
	            this.MagDeithraArmor.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(MagDeithraArmorT);
	            super.renderPlayer.modelBipedMain.bipedLeftArm.postRender(0.0625F);
	            GL11.glTranslatef(-0.33F, -0.14F, 0.0F);
	            GL11.glScalef(1.05F, 1.05F, 1.05F);
	            this.MagDeithraHand.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(MagDeithraArmorT);
	            super.renderPlayer.modelBipedMain.bipedRightArm.postRender(0.0625F);
	            GL11.glTranslatef(0.33F, -0.14F, 0.0F);
	            GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
	            GL11.glScalef(1.05F, 1.05F, 1.05F);
	            this.MagDeithraHand.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(MagDeithraArmorT);
	            super.renderPlayer.modelBipedMain.bipedRightLeg.postRender(0.0625F);
	            GL11.glTranslatef(0.15F, -0.95F, 0.0F);
	            GL11.glScalef(-1.15F, 1.15F, 1.15F);
	            this.MagDeithraRightLeg.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(MagDeithraArmorT);
	            super.renderPlayer.modelBipedMain.bipedLeftLeg.postRender(0.0625F);
	            GL11.glTranslatef(-0.13F, -0.95F, 0.0F);
	            GL11.glScalef(1.15F, 1.15F, 1.15F);
	            this.MagDeithraRightLeg.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(MagDeithraArmorT);
	            super.renderPlayer.modelBipedMain.bipedRightLeg.postRender(0.0625F);
	            GL11.glTranslatef(0.15F, -0.9F, -0.0F);
	            GL11.glScalef(1.4F, 1.3F, -1.4F);
	            this.MagDeithraLegAddon.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(MagDeithraArmorT);
	            super.renderPlayer.modelBipedMain.bipedLeftLeg.postRender(0.0625F);
	            GL11.glTranslatef(0.23F, -0.85F, -0.01F);
	            GL11.glScalef(1.4F, 1.3F, -1.4F);
	            this.MagDeithraLegAddon.render(0.0625F);
	            GL11.glPopMatrix();
	         }

	         if(player.getCurrentArmor(2) != null && player.getCurrentArmor(2).getItem() == ArmorRegister.temerChestplate) {
	            GL11.glPushMatrix();
	            super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
	            GL11.glTranslatef(0.0F, -0.05F, 0.0F);
	            GL11.glScalef(1.1F, 1.1F, 1.1F);
	            mc.getTextureManager().bindTexture(NilfgaardArmorT);
	            this.NilfgaardArmor.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            super.renderPlayer.modelBipedMain.bipedHead.postRender(0.0625F);
	            GL11.glTranslatef(0.0F, -0.05F, 0.0F);
	            GL11.glScalef(1.1F, 1.1F, 1.1F);
	            mc.getTextureManager().bindTexture(NilfgaardArmorT);
	            this.NilfgaardHead.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(NilfgaardArmorT);
	            super.renderPlayer.modelBipedMain.bipedLeftArm.postRender(0.0625F);
	            GL11.glTranslatef(-0.33F, -0.14F, 0.0F);
	            GL11.glScalef(1.05F, 1.05F, 1.05F);
	            this.NilfgaardArmorAddon.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(NilfgaardArmorT);
	            super.renderPlayer.modelBipedMain.bipedRightArm.postRender(0.0625F);
	            GL11.glTranslatef(0.33F, -0.14F, 0.0F);
	            GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
	            GL11.glScalef(1.05F, 1.05F, 1.05F);
	            this.NilfgaardArmorAddon.render(0.0625F);
	            GL11.glPopMatrix();
	         }

	         if(player.getCurrentArmor(2) != null && player.getCurrentArmor(2).getItem() == ArmorRegister.bearSchoolChestplate) {
	            GL11.glPushMatrix();
	            super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
	            GL11.glTranslatef(0.0F, -0.05F, 0.0F);
	            GL11.glScalef(1.05F, 1.05F, 1.05F);
	            mc.getTextureManager().bindTexture(BearSchoolT);
	            this.BearSchoolArmor.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(BearSchoolT);
	            super.renderPlayer.modelBipedMain.bipedLeftArm.postRender(0.0625F);
	            GL11.glTranslatef(-0.33F, -0.14F, 0.0F);
	            GL11.glScalef(1.05F, 1.05F, 1.05F);
	            this.BearSchoolHand.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(BearSchoolT);
	            super.renderPlayer.modelBipedMain.bipedRightArm.postRender(0.0625F);
	            GL11.glTranslatef(0.33F, -0.14F, 0.0F);
	            GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
	            GL11.glScalef(1.05F, 1.05F, 1.05F);
	            this.BearSchoolHand.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(BearSchoolSkirtT);
	            super.renderPlayer.modelBipedMain.bipedRightLeg.postRender(0.0625F);
	            GL11.glTranslatef(0.15F, -0.95F, 0.0F);
	            GL11.glScalef(-1.25F, 1.25F, 1.25F);
	            this.ModelSkirt.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(BearSchoolSkirtT);
	            super.renderPlayer.modelBipedMain.bipedLeftLeg.postRender(0.0625F);
	            GL11.glTranslatef(-0.15F, -0.95F, 0.0F);
	            GL11.glScalef(1.25F, 1.25F, 1.25F);
	            this.ModelSkirt.render(0.0625F);
	            GL11.glPopMatrix();
	         }

	         if(player.getCurrentArmor(2) != null && player.getCurrentArmor(2).getItem() == ArmorRegister.bearSchoolChestplate2) {
	            GL11.glPushMatrix();
	            super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
	            GL11.glTranslatef(0.0F, -0.05F, 0.0F);
	            GL11.glScalef(1.05F, 1.05F, 1.05F);
	            mc.getTextureManager().bindTexture(BearSchoolT2);
	            this.BearSchoolArmor.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(BearSchoolT2);
	            super.renderPlayer.modelBipedMain.bipedLeftArm.postRender(0.0625F);
	            GL11.glTranslatef(-0.33F, -0.14F, 0.0F);
	            GL11.glScalef(1.05F, 1.05F, 1.05F);
	            this.BearSchoolHand.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(BearSchoolT2);
	            super.renderPlayer.modelBipedMain.bipedRightArm.postRender(0.0625F);
	            GL11.glTranslatef(0.33F, -0.14F, 0.0F);
	            GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
	            GL11.glScalef(1.05F, 1.05F, 1.05F);
	            this.BearSchoolHand.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(BearSchoolSkirtT2);
	            super.renderPlayer.modelBipedMain.bipedRightLeg.postRender(0.0625F);
	            GL11.glTranslatef(0.15F, -0.95F, 0.0F);
	            GL11.glScalef(-1.25F, 1.25F, 1.25F);
	            this.ModelSkirt.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(BearSchoolSkirtT2);
	            super.renderPlayer.modelBipedMain.bipedLeftLeg.postRender(0.0625F);
	            GL11.glTranslatef(-0.15F, -0.95F, 0.0F);
	            GL11.glScalef(1.25F, 1.25F, 1.25F);
	            this.ModelSkirt.render(0.0625F);
	            GL11.glPopMatrix();
	         }

	         if(player.getCurrentArmor(2) != null && player.getCurrentArmor(2).getItem() == ArmorRegister.catSchoolChestplate) {
	            GL11.glPushMatrix();
	            super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
	            GL11.glTranslatef(0.0F, -0.05F, 0.0F);
	            GL11.glScalef(1.05F, 1.05F, 1.05F);
	            mc.getTextureManager().bindTexture(CatSchoolArmorT);
	            this.CatSchoolArmor.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(CatSchoolArmorT);
	            super.renderPlayer.modelBipedMain.bipedLeftArm.postRender(0.0625F);
	            GL11.glTranslatef(-0.33F, -0.14F, 0.0F);
	            GL11.glScalef(1.05F, 1.05F, 1.05F);
	            this.CatSchoolSleeve.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(CatSchoolArmorT);
	            super.renderPlayer.modelBipedMain.bipedRightArm.postRender(0.0625F);
	            GL11.glTranslatef(0.33F, -0.14F, 0.0F);
	            GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
	            GL11.glScalef(1.05F, 1.05F, 1.05F);
	            this.CatSchoolSleeve.render(0.0625F);
	            GL11.glPopMatrix();
	         }

	         if(player.getCurrentArmor(2) != null && player.getCurrentArmor(2).getItem() == ArmorRegister.talgarChestplate) {
	            GL11.glPushMatrix();
	            super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
	            GL11.glTranslatef(0.0F, -0.05F, 0.0F);
	            GL11.glScalef(1.05F, 1.05F, 1.05F);
	            mc.getTextureManager().bindTexture(TalgarT);
	            this.TalgarBody.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
	            GL11.glTranslatef(0.03F, 0.1F, -0.2F);
	            GL11.glScalef(0.2F, 0.2F, 0.2F);
	            mc.getTextureManager().bindTexture(TalgarSignT);
	            this.Sign.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(TalgarT);
	            super.renderPlayer.modelBipedMain.bipedLeftArm.postRender(0.0625F);
	            GL11.glTranslatef(-0.33F, -0.14F, 0.0F);
	            GL11.glScalef(1.05F, 1.05F, 1.05F);
	            this.TalgarHand.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(TalgarT);
	            super.renderPlayer.modelBipedMain.bipedRightArm.postRender(0.0625F);
	            GL11.glTranslatef(0.33F, -0.14F, 0.0F);
	            GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
	            GL11.glScalef(1.05F, 1.05F, 1.05F);
	            this.TalgarHand.render(0.0625F);
	            GL11.glPopMatrix();
	         }

	         if(player.getCurrentArmor(2) != null && player.getCurrentArmor(2).getItem() == ArmorRegister.wolfSchoolChestplate) {
	            GL11.glPushMatrix();
	            super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
	            GL11.glTranslatef(0.0F, -0.05F, 0.0F);
	            GL11.glScalef(1.05F, 1.05F, 1.05F);
	            mc.getTextureManager().bindTexture(WolfT);
	            this.WolfArmor.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(WolfT);
	            super.renderPlayer.modelBipedMain.bipedLeftArm.postRender(0.0625F);
	            GL11.glTranslatef(-0.33F, -0.14F, 0.0F);
	            GL11.glScalef(1.05F, 1.05F, 1.05F);
	            this.WolfHand.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(WolfT);
	            super.renderPlayer.modelBipedMain.bipedRightArm.postRender(0.0625F);
	            GL11.glTranslatef(0.33F, -0.14F, 0.0F);
	            GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
	            GL11.glScalef(1.05F, 1.05F, 1.05F);
	            this.WolfHand.render(0.0625F);
	            GL11.glPopMatrix();
	         }

	         if(player.getCurrentArmor(2) != null && player.getCurrentArmor(2).getItem() == ArmorRegister.griffinSchoolChestplate) {
	            GL11.glPushMatrix();
	            super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
	            GL11.glTranslatef(0.0F, -0.03F, 0.0F);
	            GL11.glScalef(1.1F, 1.0F, 1.1F);
	            mc.getTextureManager().bindTexture(GriffinArmorT);
	            this.GriffinArmor.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(GriffinArmorT);
	            super.renderPlayer.modelBipedMain.bipedLeftArm.postRender(0.0625F);
	            GL11.glTranslatef(-0.33F, -0.19F, -0.0F);
	            GL11.glScalef(1.05F, 1.05F, 1.05F);
	            this.GriffinHand.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(GriffinArmorT);
	            super.renderPlayer.modelBipedMain.bipedRightArm.postRender(0.0625F);
	            GL11.glTranslatef(0.35F, -0.19F, -0.0F);
	            GL11.glScalef(-1.1F, 1.05F, -1.05F);
	            this.GriffinHand.render(0.0625F);
	            GL11.glPopMatrix();
	         }

	         if(player.getCurrentArmor(2) != null && player.getCurrentArmor(2).getItem() == ArmorRegister.viperSchoolChestplate) {
	            GL11.glPushMatrix();
	            super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
	            GL11.glTranslatef(0.0F, 0.0F, 0.0F);
	            GL11.glScalef(1.1F, 1.1F, 1.1F);
	            mc.getTextureManager().bindTexture(ViperArmorT);
	            this.KaerMorhenArmor.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            super.renderPlayer.modelBipedMain.bipedLeftArm.postRender(0.0625F);
	            GL11.glTranslatef(-0.3F, -0.12F, 0.0F);
	            GL11.glScalef(1.0F, 1.0F, 0.9F);
	            mc.getTextureManager().bindTexture(ViperArmorT);
	            this.KaerMorhenHand.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            super.renderPlayer.modelBipedMain.bipedRightArm.postRender(0.0625F);
	            GL11.glTranslatef(0.3F, -0.12F, 0.0F);
	            GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
	            GL11.glScalef(1.0F, 1.0F, 0.9F);
	            mc.getTextureManager().bindTexture(ViperArmorT);
	            this.KaerMorhenHand.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            super.renderPlayer.modelBipedMain.bipedLeftArm.postRender(0.0625F);
	            GL11.glTranslatef(-0.27F, 0.12F, 0.0F);
	            GL11.glScalef(0.9F, 0.9F, 0.8F);
	            mc.getTextureManager().bindTexture(ViperArmorT);
	            this.KaerMorhenHandAddon.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            super.renderPlayer.modelBipedMain.bipedRightArm.postRender(0.0625F);
	            GL11.glTranslatef(0.27F, 0.12F, 0.0F);
	            GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
	            GL11.glScalef(0.9F, 0.9F, 0.8F);
	            mc.getTextureManager().bindTexture(ViperArmorT);
	            this.KaerMorhenHandAddon.render(0.0625F);
	            GL11.glPopMatrix();
	         }

	         if(player.getCurrentArmor(2) != null && player.getCurrentArmor(2).getItem() == ArmorRegister.hunterChestplate) {
	            GL11.glPushMatrix();
	            super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
	            GL11.glTranslatef(0.0F, 0.0F, 0.0F);
	            GL11.glScalef(1.1F, 1.1F, 1.1F);
	            mc.getTextureManager().bindTexture(KaerMorhenArmorT);
	            this.KaerMorhenArmor.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            super.renderPlayer.modelBipedMain.bipedLeftArm.postRender(0.0625F);
	            GL11.glTranslatef(-0.3F, -0.12F, 0.0F);
	            GL11.glScalef(1.0F, 1.0F, 0.9F);
	            mc.getTextureManager().bindTexture(KaerMorhenArmorT);
	            this.KaerMorhenHand.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            super.renderPlayer.modelBipedMain.bipedRightArm.postRender(0.0625F);
	            GL11.glTranslatef(0.3F, -0.12F, 0.0F);
	            GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
	            GL11.glScalef(1.0F, 1.0F, 0.9F);
	            mc.getTextureManager().bindTexture(KaerMorhenArmorT);
	            this.KaerMorhenHand.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            super.renderPlayer.modelBipedMain.bipedLeftArm.postRender(0.0625F);
	            GL11.glTranslatef(-0.27F, 0.12F, 0.0F);
	            GL11.glScalef(0.9F, 0.9F, 0.9F);
	            mc.getTextureManager().bindTexture(KaerMorhenArmorT);
	            this.KaerMorhenHandAddon.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            super.renderPlayer.modelBipedMain.bipedRightArm.postRender(0.0625F);
	            GL11.glTranslatef(0.27F, 0.12F, 0.0F);
	            GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
	            GL11.glScalef(0.9F, 0.9F, 0.9F);
	            mc.getTextureManager().bindTexture(KaerMorhenArmorT);
	            this.KaerMorhenHandAddon.render(0.0625F);
	            GL11.glPopMatrix();
	         }
	      }

	      if(player.getCurrentArmor(1) != null) {
	         if(player.getCurrentArmor(1) != null && player.getCurrentArmor(1).getItem() == ArmorRegister.cidarianArmyPants) {
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(WitcherLuriskyPantsT);
	            super.renderPlayer.modelBipedMain.bipedLeftLeg.postRender(0.0625F);
	            GL11.glTranslatef(-0.135F, -0.8F, 0.0F);
	            GL11.glScalef(1.05F, 1.05F, 1.05F);
	            this.WitcherLuriskyPants.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(WitcherLuriskyPantsT);
	            super.renderPlayer.modelBipedMain.bipedRightLeg.postRender(0.0625F);
	            GL11.glTranslatef(0.135F, -0.8F, 0.0F);
	            GL11.glScalef(-1.05F, 1.05F, 1.05F);
	            this.WitcherLuriskyPants.render(0.0625F);
	            GL11.glPopMatrix();
	         }

	         if(player.getCurrentArmor(1) != null && player.getCurrentArmor(1).getItem() == ArmorRegister.talgarPants) {
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(TalgarT);
	            super.renderPlayer.modelBipedMain.bipedLeftLeg.postRender(0.0625F);
	            GL11.glTranslatef(-0.135F, -0.8F, 0.0F);
	            GL11.glScalef(1.05F, 1.05F, 1.05F);
	            this.TalgarLeg.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(TalgarT);
	            super.renderPlayer.modelBipedMain.bipedRightLeg.postRender(0.0625F);
	            GL11.glTranslatef(0.135F, -0.8F, 0.0F);
	            GL11.glScalef(-1.05F, 1.05F, 1.05F);
	            this.TalgarLeg.render(0.0625F);
	            GL11.glPopMatrix();
	         }

	         if(player.getCurrentArmor(1) != null && player.getCurrentArmor(1).getItem() == ArmorRegister.leatherPants) {
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(WitcherPathfinderPantsT);
	            super.renderPlayer.modelBipedMain.bipedLeftLeg.postRender(0.0625F);
	            GL11.glTranslatef(-0.135F, -0.8F, 0.0F);
	            GL11.glScalef(1.0F, 1.0F, 1.0F);
	            this.WitcherPathfinderLegs.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(WitcherPathfinderPantsT);
	            super.renderPlayer.modelBipedMain.bipedRightLeg.postRender(0.0625F);
	            GL11.glTranslatef(0.135F, -0.8F, 0.0F);
	            GL11.glScalef(-1.0F, 1.0F, -1.0F);
	            this.WitcherPathfinderLegs.render(0.0625F);
	            GL11.glPopMatrix();
	         }

	         if(player.getCurrentArmor(1) != null && player.getCurrentArmor(1).getItem() == ArmorRegister.temerArmyPants) {
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(WitcherTopornikPantsT);
	            super.renderPlayer.modelBipedMain.bipedLeftLeg.postRender(0.0625F);
	            GL11.glTranslatef(-0.15F, -0.8F, 0.0F);
	            GL11.glScalef(1.05F, 1.05F, 1.05F);
	            this.WitcherTopornikPants.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(WitcherTopornikPantsT);
	            super.renderPlayer.modelBipedMain.bipedRightLeg.postRender(0.0625F);
	            GL11.glTranslatef(0.15F, -0.8F, 0.0F);
	            GL11.glScalef(-1.05F, 1.05F, -1.05F);
	            this.WitcherTopornikPants.render(0.0625F);
	            GL11.glPopMatrix();
	         }

	         if(player.getCurrentArmor(1) != null && player.getCurrentArmor(1).getItem() == ArmorRegister.bearSchoolPants2) {
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(BearSchoolT);
	            super.renderPlayer.modelBipedMain.bipedRightLeg.postRender(0.0625F);
	            GL11.glTranslatef(-0.15F, -0.85F, 0.0F);
	            GL11.glScalef(1.15F, 1.1F, 1.15F);
	            this.WitcherLeg.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(BearSchoolT);
	            super.renderPlayer.modelBipedMain.bipedLeftLeg.postRender(0.0625F);
	            GL11.glTranslatef(0.15F, -0.85F, 0.0F);
	            GL11.glScalef(-1.15F, 1.1F, -1.15F);
	            this.WitcherLeg.render(0.0625F);
	            GL11.glPopMatrix();
	         }

	         if(player.getCurrentArmor(1) != null && player.getCurrentArmor(1).getItem() == ArmorRegister.bearSchoolPants) {
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(BearSchoolT);
	            super.renderPlayer.modelBipedMain.bipedRightLeg.postRender(0.0625F);
	            GL11.glTranslatef(-0.15F, -0.85F, 0.0F);
	            GL11.glScalef(1.15F, 1.1F, 1.15F);
	            this.WitcherLeg.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(BearSchoolT);
	            super.renderPlayer.modelBipedMain.bipedLeftLeg.postRender(0.0625F);
	            GL11.glTranslatef(0.15F, -0.85F, 0.0F);
	            GL11.glScalef(-1.15F, 1.1F, -1.15F);
	            this.WitcherLeg.render(0.0625F);
	            GL11.glPopMatrix();
	         }

	         if(player.getCurrentArmor(1) != null && player.getCurrentArmor(1).getItem() == ArmorRegister.wolfSchoolPants) {
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(WolfT);
	            super.renderPlayer.modelBipedMain.bipedRightLeg.postRender(0.0625F);
	            GL11.glTranslatef(-0.15F, -0.85F, 0.0F);
	            GL11.glScalef(1.15F, 1.1F, 1.15F);
	            this.WitcherLeg.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(WolfT);
	            super.renderPlayer.modelBipedMain.bipedLeftLeg.postRender(0.0625F);
	            GL11.glTranslatef(0.15F, -0.85F, 0.0F);
	            GL11.glScalef(-1.15F, 1.1F, -1.15F);
	            this.WitcherLeg.render(0.0625F);
	            GL11.glPopMatrix();
	         }

	         if(player.getCurrentArmor(1) != null && player.getCurrentArmor(1).getItem() == ArmorRegister.temerPants) {
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(NilfgaardArmorT);
	            super.renderPlayer.modelBipedMain.bipedRightLeg.postRender(0.0625F);
	            GL11.glTranslatef(-0.15F, -0.9F, 0.0F);
	            GL11.glScalef(1.15F, 1.1F, 1.15F);
	            this.NilfgaardLeg.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(NilfgaardArmorT);
	            super.renderPlayer.modelBipedMain.bipedLeftLeg.postRender(0.0625F);
	            GL11.glTranslatef(0.15F, -0.9F, 0.0F);
	            GL11.glScalef(-1.15F, 1.1F, 1.15F);
	            this.NilfgaardLeg.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(NilfgaardArmorLegT);
	            super.renderPlayer.modelBipedMain.bipedRightLeg.postRender(0.0625F);
	            GL11.glTranslatef(-0.15F, -0.85F, 0.0F);
	            GL11.glScalef(1.15F, 1.1F, 1.15F);
	            this.WitcherLeg.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(NilfgaardArmorLegT);
	            super.renderPlayer.modelBipedMain.bipedLeftLeg.postRender(0.0625F);
	            GL11.glTranslatef(0.15F, -0.85F, 0.0F);
	            GL11.glScalef(-1.15F, 1.1F, -1.15F);
	            this.WitcherLeg.render(0.0625F);
	            GL11.glPopMatrix();
	         }

	         if(player.getCurrentArmor(1) != null && player.getCurrentArmor(1).getItem() == ArmorRegister.catSchoolPants) {
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(CatPantsT);
	            super.renderPlayer.modelBipedMain.bipedRightLeg.postRender(0.0625F);
	            GL11.glTranslatef(-0.15F, -0.85F, 0.0F);
	            GL11.glScalef(1.05F, 1.05F, 1.05F);
	            this.CatSchoolLeg.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(CatPantsT);
	            super.renderPlayer.modelBipedMain.bipedLeftLeg.postRender(0.0625F);
	            GL11.glTranslatef(0.15F, -0.85F, 0.0F);
	            GL11.glScalef(-1.05F, 1.051F, -1.05F);
	            this.CatSchoolLeg.render(0.0625F);
	            GL11.glPopMatrix();
	         }

	         if(player.getCurrentArmor(1) != null && player.getCurrentArmor(1).getItem() == ArmorRegister.griffinSchoolPants) {
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(GriffinPantsT);
	            super.renderPlayer.modelBipedMain.bipedRightLeg.postRender(0.0625F);
	            GL11.glTranslatef(0.15F, -0.85F, 0.0F);
	            GL11.glScalef(1.15F, 1.1F, 1.15F);
	            this.WitcherRightLeg.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(GriffinPantsT);
	            super.renderPlayer.modelBipedMain.bipedLeftLeg.postRender(0.0625F);
	            GL11.glTranslatef(-0.15F, -0.85F, 0.0F);
	            GL11.glScalef(1.15F, 1.1F, 1.15F);
	            this.WitcherLeftLeg.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(GriffinArmorT);
	            super.renderPlayer.modelBipedMain.bipedRightLeg.postRender(0.0625F);
	            GL11.glTranslatef(0.13F, -0.85F, 0.0F);
	            GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
	            GL11.glScalef(1.1F, 1.1F, 1.1F);
	            this.GriffinLeg.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(GriffinArmorT);
	            super.renderPlayer.modelBipedMain.bipedLeftLeg.postRender(0.0625F);
	            GL11.glTranslatef(-0.15F, -0.85F, 0.0F);
	            GL11.glScalef(1.1F, 1.1F, 1.1F);
	            this.GriffinLeg.render(0.0625F);
	            GL11.glPopMatrix();
	         }

	         if(player.getCurrentArmor(1) != null && player.getCurrentArmor(1).getItem() == ArmorRegister.hunterPants) {
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(KaerMorhenArmorPantsT);
	            super.renderPlayer.modelBipedMain.bipedRightLeg.postRender(0.0625F);
	            GL11.glTranslatef(0.15F, -0.85F, 0.0F);
	            GL11.glScalef(1.15F, 1.1F, 1.15F);
	            this.WitcherRightLeg.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(KaerMorhenArmorPantsT);
	            super.renderPlayer.modelBipedMain.bipedLeftLeg.postRender(0.0625F);
	            GL11.glTranslatef(-0.15F, -0.85F, 0.0F);
	            GL11.glScalef(1.15F, 1.1F, 1.15F);
	            this.WitcherLeftLeg.render(0.0625F);
	            GL11.glPopMatrix();
	         }

	         if(player.getCurrentArmor(1) != null && player.getCurrentArmor(1).getItem() == ArmorRegister.viperSchoolPants) {
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(ViperArmorPantsT);
	            super.renderPlayer.modelBipedMain.bipedRightLeg.postRender(0.0625F);
	            GL11.glTranslatef(0.15F, -0.85F, 0.0F);
	            GL11.glScalef(1.15F, 1.1F, 1.15F);
	            this.WitcherRightLeg.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(ViperArmorPantsT);
	            super.renderPlayer.modelBipedMain.bipedLeftLeg.postRender(0.0625F);
	            GL11.glTranslatef(-0.15F, -0.85F, 0.0F);
	            GL11.glScalef(1.15F, 1.1F, 1.15F);
	            this.WitcherLeftLeg.render(0.0625F);
	            GL11.glPopMatrix();
	         }
	      }

	      if(player.getCurrentArmor(3) != null) {
	         if(player.getCurrentArmor(3) != null && player.getCurrentArmor(3).getItem() == ArmorRegister.bearSchoolGloves) {
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(BearSchoolT);
	            super.renderPlayer.modelBipedMain.bipedLeftArm.postRender(0.0625F);
	            GL11.glTranslatef(-0.33F, -0.14F, 0.0F);
	            GL11.glScalef(1.05F, 1.05F, 1.05F);
	            this.BearSchoolHand.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(BearSchoolT);
	            super.renderPlayer.modelBipedMain.bipedRightArm.postRender(0.0625F);
	            GL11.glTranslatef(0.33F, -0.14F, 0.0F);
	            GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
	            GL11.glScalef(1.05F, 1.05F, 1.05F);
	            this.BearSchoolHand.render(0.0625F);
	            GL11.glPopMatrix();
	         }

	         if(player.getCurrentArmor(3) != null && player.getCurrentArmor(3).getItem() == ArmorRegister.bearSchoolGloves2) {
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(BearSchoolT2);
	            super.renderPlayer.modelBipedMain.bipedLeftArm.postRender(0.0625F);
	            GL11.glTranslatef(-0.33F, -0.14F, 0.0F);
	            GL11.glScalef(1.05F, 1.05F, 1.05F);
	            this.BearSchoolHand.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(BearSchoolT2);
	            super.renderPlayer.modelBipedMain.bipedRightArm.postRender(0.0625F);
	            GL11.glTranslatef(0.33F, -0.14F, 0.0F);
	            GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
	            GL11.glScalef(1.05F, 1.05F, 1.05F);
	            this.BearSchoolHand.render(0.0625F);
	            GL11.glPopMatrix();
	         }

	         if(player.getCurrentArmor(3) != null && player.getCurrentArmor(3).getItem() == ArmorRegister.wolfSchoolGloves) {
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(WolfT);
	            super.renderPlayer.modelBipedMain.bipedLeftArm.postRender(0.0625F);
	            GL11.glTranslatef(-0.33F, -0.14F, 0.0F);
	            GL11.glScalef(1.05F, 1.05F, 1.05F);
	            this.WolfGlove.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(WolfT);
	            super.renderPlayer.modelBipedMain.bipedRightArm.postRender(0.0625F);
	            GL11.glTranslatef(0.33F, -0.14F, 0.0F);
	            GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
	            GL11.glScalef(1.05F, 1.05F, 1.05F);
	            this.WolfGlove.render(0.0625F);
	            GL11.glPopMatrix();
	         }

	         if(player.getCurrentArmor(3) != null && player.getCurrentArmor(3).getItem() == ArmorRegister.temerGloves) {
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(NilfgaardArmorT);
	            super.renderPlayer.modelBipedMain.bipedLeftArm.postRender(0.0625F);
	            GL11.glTranslatef(-0.33F, -0.14F, 0.0F);
	            GL11.glScalef(1.05F, 1.05F, 1.05F);
	            this.NilfgaardGlove.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(NilfgaardArmorT);
	            super.renderPlayer.modelBipedMain.bipedRightArm.postRender(0.0625F);
	            GL11.glTranslatef(0.33F, -0.14F, 0.0F);
	            GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
	            GL11.glScalef(1.05F, 1.05F, 1.05F);
	            this.NilfgaardGlove.render(0.0625F);
	            GL11.glPopMatrix();
	         }

	         if(player.getCurrentArmor(3) != null && player.getCurrentArmor(3).getItem() == ArmorRegister.catSchoolGloves) {
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(CatSchoolArmorT);
	            super.renderPlayer.modelBipedMain.bipedLeftArm.postRender(0.0625F);
	            GL11.glTranslatef(-0.315F, -0.11F, 0.0F);
	            GL11.glScalef(1.01F, 1.01F, 1.01F);
	            this.CatSchoolGlove.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(CatSchoolArmorT);
	            super.renderPlayer.modelBipedMain.bipedRightArm.postRender(0.0625F);
	            GL11.glTranslatef(0.315F, -0.11F, 0.0F);
	            GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
	            GL11.glScalef(1.01F, 1.01F, 1.01F);
	            this.CatSchoolGlove.render(0.0625F);
	            GL11.glPopMatrix();
	         }

	         if(player.getCurrentArmor(3) != null && player.getCurrentArmor(3).getItem() == ArmorRegister.griffinSchoolGloves) {
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(GriffinArmorT);
	            super.renderPlayer.modelBipedMain.bipedLeftArm.postRender(0.0625F);
	            GL11.glTranslatef(-0.33F, -0.19F, -0.0F);
	            GL11.glScalef(1.05F, 1.05F, 1.05F);
	            this.GriffinGlove.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(GriffinArmorT);
	            super.renderPlayer.modelBipedMain.bipedRightArm.postRender(0.0625F);
	            GL11.glTranslatef(0.35F, -0.19F, -0.0F);
	            GL11.glScalef(-1.1F, 1.05F, -1.05F);
	            this.GriffinGlove.render(0.0625F);
	            GL11.glPopMatrix();
	         }

	         if(player.getCurrentArmor(3) != null && player.getCurrentArmor(3).getItem() == ArmorRegister.viperSchoolGloves) {
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(ViperArmorHandT);
	            super.renderPlayer.modelBipedMain.bipedLeftArm.postRender(0.0625F);
	            GL11.glTranslatef(-0.1F, 0.55F, -0.13F);
	            GL11.glScalef(0.58F, 0.25F, 0.55F);
	            this.WitcherModelGlove.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(ViperArmorHandT);
	            super.renderPlayer.modelBipedMain.bipedRightArm.postRender(0.0625F);
	            GL11.glTranslatef(0.1F, 0.55F, 0.13F);
	            GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
	            GL11.glScalef(0.58F, 0.25F, 0.55F);
	            this.WitcherModelGlove.render(0.0625F);
	            GL11.glPopMatrix();
	         }

	         if(player.getCurrentArmor(3) != null && player.getCurrentArmor(3).getItem() == ArmorRegister.hunterGloves) {
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(KaerMorhenArmorHandT);
	            super.renderPlayer.modelBipedMain.bipedLeftArm.postRender(0.0625F);
	            GL11.glTranslatef(-0.1F, 0.55F, -0.13F);
	            GL11.glScalef(0.58F, 0.25F, 0.55F);
	            this.WitcherModelGlove.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(KaerMorhenArmorHandT);
	            super.renderPlayer.modelBipedMain.bipedRightArm.postRender(0.0625F);
	            GL11.glTranslatef(0.1F, 0.55F, 0.13F);
	            GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
	            GL11.glScalef(0.58F, 0.25F, 0.55F);
	            this.WitcherModelGlove.render(0.0625F);
	            GL11.glPopMatrix();
	         }
	      }

	      if(player.getCurrentArmor(0) != null) {
	         if(player.getCurrentArmor(0) != null && player.getCurrentArmor(0).getItem() == ArmorRegister.bearSchoolBoots2) {
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(BearSchoolBootT);
	            super.renderPlayer.modelBipedMain.bipedLeftLeg.postRender(0.0625F);
	            GL11.glTranslatef(-0.0F, -0.73F, -0.12F);
	            GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
	            GL11.glScalef(1.0F, 1.0F, 1.0F);
	            this.WitcherModelBoot.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(BearSchoolBootT);
	            super.renderPlayer.modelBipedMain.bipedRightLeg.postRender(0.0625F);
	            GL11.glTranslatef(-0.0F, -0.73F, -0.12F);
	            GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
	            GL11.glScalef(1.0F, 1.0F, -1.0F);
	            this.WitcherModelBoot.render(0.0625F);
	            GL11.glPopMatrix();
	         }

	         if(player.getCurrentArmor(0) != null && player.getCurrentArmor(0).getItem() == ArmorRegister.bearSchoolBoots) {
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(BearSchoolBootT);
	            super.renderPlayer.modelBipedMain.bipedLeftLeg.postRender(0.0625F);
	            GL11.glTranslatef(-0.0F, -0.73F, -0.12F);
	            GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
	            GL11.glScalef(1.0F, 1.0F, 1.0F);
	            this.WitcherModelBoot.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(BearSchoolBootT);
	            super.renderPlayer.modelBipedMain.bipedRightLeg.postRender(0.0625F);
	            GL11.glTranslatef(-0.0F, -0.73F, -0.12F);
	            GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
	            GL11.glScalef(1.0F, 1.0F, -1.0F);
	            this.WitcherModelBoot.render(0.0625F);
	            GL11.glPopMatrix();
	         }

	         if(player.getCurrentArmor(0) != null && player.getCurrentArmor(0).getItem() == ArmorRegister.wolfSchoolBoots) {
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(WolfBootT);
	            super.renderPlayer.modelBipedMain.bipedLeftLeg.postRender(0.0625F);
	            GL11.glTranslatef(-0.0F, -0.73F, -0.12F);
	            GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
	            GL11.glScalef(1.0F, 1.0F, 1.0F);
	            this.WitcherModelBoot.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(WolfBootT);
	            super.renderPlayer.modelBipedMain.bipedRightLeg.postRender(0.0625F);
	            GL11.glTranslatef(-0.0F, -0.73F, -0.12F);
	            GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
	            GL11.glScalef(1.0F, 1.0F, -1.0F);
	            this.WitcherModelBoot.render(0.0625F);
	            GL11.glPopMatrix();
	         }

	         if(player.getCurrentArmor(0) != null && player.getCurrentArmor(0).getItem() == ArmorRegister.catSchoolBoots) {
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(CatSchoolArmorT);
	            super.renderPlayer.modelBipedMain.bipedLeftLeg.postRender(0.0625F);
	            GL11.glTranslatef(-0.094F, -0.73F, -0.02F);
	            GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
	            GL11.glScalef(1.01F, 1.01F, 1.01F);
	            this.CatSchoolBoot.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(CatSchoolArmorT);
	            super.renderPlayer.modelBipedMain.bipedRightLeg.postRender(0.0625F);
	            GL11.glTranslatef(0.094F, -0.73F, -0.02F);
	            GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
	            GL11.glScalef(1.01F, 1.01F, -1.01F);
	            this.CatSchoolBoot.render(0.0625F);
	            GL11.glPopMatrix();
	         }

	         if(player.getCurrentArmor(0) != null && player.getCurrentArmor(0).getItem() == ArmorRegister.temerBoots) {
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(NilfgaardArmorBootT);
	            super.renderPlayer.modelBipedMain.bipedLeftLeg.postRender(0.0625F);
	            GL11.glTranslatef(-0.0F, -0.68F, -0.12F);
	            GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
	            GL11.glScalef(1.0F, 1.0F, 1.0F);
	            this.WitcherModelBoot.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(NilfgaardArmorBootT);
	            super.renderPlayer.modelBipedMain.bipedRightLeg.postRender(0.0625F);
	            GL11.glTranslatef(-0.0F, -0.68F, -0.12F);
	            GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
	            GL11.glScalef(1.0F, 1.0F, -1.0F);
	            this.WitcherModelBoot.render(0.0625F);
	            GL11.glPopMatrix();
	         }

	         if(player.getCurrentArmor(0) != null && player.getCurrentArmor(0).getItem() == ArmorRegister.griffinSchoolBoots) {
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(GriffinBootsT);
	            super.renderPlayer.modelBipedMain.bipedRightLeg.postRender(0.0625F);
	            GL11.glTranslatef(-0.0F, -0.69F, -0.12F);
	            GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
	            GL11.glScalef(1.0F, 1.0F, -1.0F);
	            this.WitcherModelBoot.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(GriffinBootsT);
	            super.renderPlayer.modelBipedMain.bipedLeftLeg.postRender(0.0625F);
	            GL11.glTranslatef(-0.0F, -0.69F, -0.12F);
	            GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
	            GL11.glScalef(1.0F, 1.0F, 1.0F);
	            this.WitcherModelBoot.render(0.0625F);
	            GL11.glPopMatrix();
	         }

	         if(player.getCurrentArmor(0) != null && player.getCurrentArmor(0).getItem() == ArmorRegister.hunterBoots) {
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(KaerMorhenArmorBootsT);
	            super.renderPlayer.modelBipedMain.bipedLeftLeg.postRender(0.0625F);
	            GL11.glTranslatef(-0.0F, -0.73F, -0.12F);
	            GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
	            GL11.glScalef(1.0F, 1.0F, 1.0F);
	            this.WitcherModelBoot.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(KaerMorhenArmorBootsT);
	            super.renderPlayer.modelBipedMain.bipedRightLeg.postRender(0.0625F);
	            GL11.glTranslatef(-0.0F, -0.73F, -0.12F);
	            GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
	            GL11.glScalef(1.0F, 1.0F, -1.0F);
	            this.WitcherModelBoot.render(0.0625F);
	            GL11.glPopMatrix();
	         }

	         if(player.getCurrentArmor(0) != null && player.getCurrentArmor(0).getItem() == ArmorRegister.viperSchoolBoots) {
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(ViperArmorBootsT);
	            super.renderPlayer.modelBipedMain.bipedLeftLeg.postRender(0.0625F);
	            GL11.glTranslatef(-0.0F, -0.73F, -0.12F);
	            GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
	            GL11.glScalef(1.0F, 1.0F, 1.0F);
	            this.WitcherModelBoot.render(0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(ViperArmorBootsT);
	            super.renderPlayer.modelBipedMain.bipedRightLeg.postRender(0.0625F);
	            GL11.glTranslatef(-0.0F, -0.73F, -0.12F);
	            GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
	            GL11.glScalef(1.0F, 1.0F, -1.0F);
	            this.WitcherModelBoot.render(0.0625F);
	            GL11.glPopMatrix();
	         }
	      }
	     
	      if(player.isPotionActive(Potion.resistance.getId())) {
	          GL11.glPushMatrix();
	          super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
	          if(r < 361.0F) {
	             r += 9.0F;
	          } else {
	             r = 0.0F;
	          }

	          GL11.glRotatef(this.r, 0.0F, 1.0F, 0.0F);
	          GL11.glTranslatef(-0.5F, 0.5F, 0.0F);
	          GL11.glScalef(1.0F, 1.0F, 1.0F);
	          mc.getTextureManager().bindTexture(textureKven);
	          this.kven.render(0.0625F);
	          GL11.glPopMatrix();
	       }
		}
		}
	}
}
