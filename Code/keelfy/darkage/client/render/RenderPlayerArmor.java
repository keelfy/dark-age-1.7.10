/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.client.render;

import org.lwjgl.opengl.GL11;

import api.player.render.RenderPlayerAPI;
import api.player.render.RenderPlayerBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkage.client.models.armor.BelhavenaBrigantine.BelhavenaBrigantineBody;
import keelfy.darkage.client.models.armor.BelhavenaBrigantine.BelhavenaBrigantineHand;
import keelfy.darkage.client.models.armor.BelhavenaBrigantine.BelhavenaBrigantineLeg;
import keelfy.darkage.client.models.armor.HindarsfjallHeavyArmor.HindarsfjallHeavyArmorBody;
import keelfy.darkage.client.models.armor.HindarsfjallHeavyArmor.HindarsfjallHeavyArmorHand;
import keelfy.darkage.client.models.armor.HindarsfjallHeavyArmor.HindarsfjallHeavyArmorLeg;
import keelfy.darkage.client.models.armor.RedanianHalebardist.RedanianHalebardistAddon;
import keelfy.darkage.client.models.armor.RedanianHalebardist.RedanianHalebardistArmor;
import keelfy.darkage.client.models.armor.RedanianHalebardist.RedanianHalebardistLeg;
import keelfy.darkage.client.models.armor.Shirt.ShirtAddon;
import keelfy.darkage.client.models.armor.Shirt.ShirtHand;
import keelfy.darkage.client.models.armor.Shirt.ShirtMody;
import keelfy.darkage.client.models.armor.bear1.BearSchoolArmor;
import keelfy.darkage.client.models.armor.bear1.BearSchoolHand;
import keelfy.darkage.client.models.armor.cat1.CatSchoolArm;
import keelfy.darkage.client.models.armor.cat1.CatSchoolArmor;
import keelfy.darkage.client.models.armor.cat1.CatSchoolBoot;
import keelfy.darkage.client.models.armor.cat1.CatSchoolGlove;
import keelfy.darkage.client.models.armor.cat1.CatSchoolLeg;
import keelfy.darkage.client.models.armor.cidaris.CidarisLeg;
import keelfy.darkage.client.models.armor.common.Sign;
import keelfy.darkage.client.models.armor.common.WitcherLuriskyPants;
import keelfy.darkage.client.models.armor.common.WitcherPathfinderLegs;
import keelfy.darkage.client.models.armor.common.WitcherTopornikPants;
import keelfy.darkage.client.models.armor.griffin.GriffinArmor;
import keelfy.darkage.client.models.armor.griffin.GriffinGlove;
import keelfy.darkage.client.models.armor.griffin.GriffinHand;
import keelfy.darkage.client.models.armor.griffin.GriffinLeg;
import keelfy.darkage.client.models.armor.kaermorhen.KaerMorhenArmor;
import keelfy.darkage.client.models.armor.kaermorhen.KaerMorhenHand;
import keelfy.darkage.client.models.armor.kaermorhen.KaerMorhenHandAddon;
import keelfy.darkage.client.models.armor.magdeithra.MagDeithraArmor;
import keelfy.darkage.client.models.armor.magdeithra.MagDeithraHand;
import keelfy.darkage.client.models.armor.magdeithra.MagDeithraLeftLeg;
import keelfy.darkage.client.models.armor.magdeithra.MagDeithraLegAddon;
import keelfy.darkage.client.models.armor.magdeithra.MagDeithraRightLeg;
import keelfy.darkage.client.models.armor.nilfgaard.NilfgaardArmor;
import keelfy.darkage.client.models.armor.nilfgaard.NilfgaardArmorAddon;
import keelfy.darkage.client.models.armor.nilfgaard.NilfgaardGlove;
import keelfy.darkage.client.models.armor.nilfgaard.NilfgaardHead;
import keelfy.darkage.client.models.armor.nilfgaard.NilfgaardLeg;
import keelfy.darkage.client.models.armor.talgar.TalgarBody;
import keelfy.darkage.client.models.armor.talgar.TalgarHand;
import keelfy.darkage.client.models.armor.talgar.TalgarLeg;
import keelfy.darkage.client.models.armor.witchunter.WitchunterArmorBody;
import keelfy.darkage.client.models.armor.witchunter.WitchunterArmorHand;
import keelfy.darkage.client.models.armor.witchunter.WitchunterArmorLeg;
import keelfy.darkage.client.models.armor.wolf.WolfArmor;
import keelfy.darkage.client.models.armor.wolf.WolfGlove;
import keelfy.darkage.client.models.armor.wolf.WolfHand;
import keelfy.darkage.client.models.armor.wolf.WolfLeg;
import keelfy.darkage.client.models.entity.player.ModelBody;
import keelfy.darkage.client.models.entity.player.ModelBoot;
import keelfy.darkage.client.models.entity.player.ModelGlove;
import keelfy.darkage.client.models.entity.player.ModelLeg;
import keelfy.darkage.client.models.entity.player.WitcherLeftLeg;
import keelfy.darkage.client.models.entity.player.WitcherRightLeg;
import keelfy.darkage.client.models.entity.sign.ModelSignKven;
import keelfy.darkage.constants.EnumModelPath;
import keelfy.darkage.constants.EnumTexturePath;
import keelfy.darkage.entities.player.DAPlayer;
import keelfy.darkage.handlers.client.ResourceHandler.Model;
import keelfy.darkage.handlers.client.ResourceHandler.Texture;
import keelfy.darkage.handlers.registers.ArmorRegister;
import keelfy.darkage.items.Sword;
import keelfytools.KeelfyUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelCustom;

/**
 * @author keelfy
 */
@SideOnly(Side.CLIENT)
public class RenderPlayerArmor extends RenderPlayerBase {

	public static final Minecraft mc = Minecraft.getMinecraft();

	public static final Sign Sign = new Sign();
	public static final ModelSignKven kven = new ModelSignKven();
	public static final ResourceLocation textureKven = Texture.get(EnumTexturePath.PARTICLE, "kven");
	public static final ModelLeg WitcherLeg = new ModelLeg();
	public static final ModelBoot WitcherModelBoot = new ModelBoot();
	public static final ModelBody ModelSkirt = new ModelBody();
	public static final WitcherLeftLeg WitcherLeftLeg = new WitcherLeftLeg();
	public static final WitcherRightLeg WitcherRightLeg = new WitcherRightLeg();
	public static final TalgarBody TalgarBody = new TalgarBody();
	public static final TalgarLeg TalgarLeg = new TalgarLeg();
	public static final TalgarHand TalgarHand = new TalgarHand();
	public static final ResourceLocation TalgarT = Texture.get(EnumTexturePath.ARMOR, "Talgar/Talgar");
	public static final ResourceLocation TalgarSignT = Texture.get(EnumTexturePath.ARMOR, "Talgar/TalgarSign");
	public static final WitcherTopornikPants WitcherTopornikPants = new WitcherTopornikPants();
	public static final ResourceLocation WitcherTopornikPantsT = Texture.get(EnumTexturePath.ARMOR, "Pants/TopornikPants");
	public static final WitcherPathfinderLegs WitcherPathfinderLegs = new WitcherPathfinderLegs();
	public static final ResourceLocation WitcherPathfinderPantsT = Texture.get(EnumTexturePath.ARMOR, "Pants/PathfinderLegs");
	public static final WitcherLuriskyPants WitcherLuriskyPants = new WitcherLuriskyPants();
	public static final ResourceLocation WitcherLuriskyPantsT = Texture.get(EnumTexturePath.ARMOR, "Pants/LuriskyPants");
	public static final ResourceLocation CidarisCombezT = Texture.get(EnumTexturePath.ARMOR, "Cidaris/CidarisCombez");
	public static final BelhavenaBrigantineBody BelhavenaBrigantineBody = new BelhavenaBrigantineBody();
	public static final BelhavenaBrigantineHand BelhavenaBrigantineHand = new BelhavenaBrigantineHand();
	public static final BelhavenaBrigantineLeg BelhavenaBrigantineLeg = new BelhavenaBrigantineLeg();
	public static final ResourceLocation BelhavenaBrigantineT = Texture.get(EnumTexturePath.ARMOR, "BelhavenaBrigantine/BelhavenaBrigantine");
	public static final CidarisLeg CidarisLeg = new CidarisLeg();
	public static final ResourceLocation CidarisT = Texture.get(EnumTexturePath.ARMOR, "Cidaris/Cidaris");
	public static final WitchunterArmorBody WitchunterArmorBody = new WitchunterArmorBody();
	public static final WitchunterArmorHand WitchunterArmorHand = new WitchunterArmorHand();
	public static final WitchunterArmorLeg WitchunterArmorLeg = new WitchunterArmorLeg();
	public static final ResourceLocation WitchunterArmorT = Texture.get(EnumTexturePath.ARMOR, "WitchunterArmor/WitchunterArmorT");
	public static final HindarsfjallHeavyArmorBody HindarsfjallHeavyArmorBody = new HindarsfjallHeavyArmorBody();
	public static final HindarsfjallHeavyArmorHand HindarsfjallHeavyArmorHand = new HindarsfjallHeavyArmorHand();
	public static final HindarsfjallHeavyArmorLeg HindarsfjallHeavyArmorLeg = new HindarsfjallHeavyArmorLeg();
	public static final ResourceLocation HindarsfjallHeavyArmorT = Texture.get(EnumTexturePath.ARMOR, "HindarsfjallHeavyArmor/HindarsfjallHeavyArmor");
	public static final BearSchoolHand BearSchoolHand = new BearSchoolHand();
	public static final BearSchoolArmor BearSchoolArmor = new BearSchoolArmor();
	public static final ResourceLocation BearSchoolT = Texture.get(EnumTexturePath.ARMOR, "bearSchool/Armor");
	public static final ResourceLocation BearSchoolBootT = Texture.get(EnumTexturePath.ARMOR, "bearSchool/Boot");
	public static final ResourceLocation BearSchoolSkirtT = Texture.get(EnumTexturePath.ARMOR, "bearSchool/Skirt");
	public static final ResourceLocation BearSchoolT2 = Texture.get(EnumTexturePath.ARMOR, "bearSchool/2/Armor");
	public static final ResourceLocation BearSchoolSkirtT2 = Texture.get(EnumTexturePath.ARMOR, "bearSchool/2/Skirt");
	public static final CatSchoolArmor CatSchoolArmor = new CatSchoolArmor();
	public static final CatSchoolLeg CatSchoolLeg = new CatSchoolLeg();
	public static final CatSchoolGlove CatSchoolGlove = new CatSchoolGlove();
	public static final CatSchoolArm CatSchoolSleeve = new CatSchoolArm();
	public static final CatSchoolBoot CatSchoolBoot = new CatSchoolBoot();
	public static final ResourceLocation CatPantsT = Texture.get(EnumTexturePath.ARMOR, "catSchool/Pants");
	public static final ResourceLocation CatSchoolArmorT = Texture.get(EnumTexturePath.ARMOR, "catSchool/Armor");
	public static final GriffinArmor GriffinArmor = new GriffinArmor();
	public static final GriffinHand GriffinHand = new GriffinHand();
	public static final GriffinLeg GriffinLeg = new GriffinLeg();
	public static final GriffinGlove GriffinGlove = new GriffinGlove();
	public static final ResourceLocation GriffinArmorT = Texture.get(EnumTexturePath.ARMOR, "griffinSchool/GriffinArmor");
	public static final ResourceLocation GriffinPantsT = Texture.get(EnumTexturePath.ARMOR, "griffinSchool/Pants");
	public static final ResourceLocation GriffinBootsT = Texture.get(EnumTexturePath.ARMOR, "griffinSchool/Boot");
	public static final KaerMorhenArmor KaerMorhenArmor = new KaerMorhenArmor();
	public static final KaerMorhenHand KaerMorhenHand = new KaerMorhenHand();
	public static final KaerMorhenHandAddon KaerMorhenHandAddon = new KaerMorhenHandAddon();
	public static final ModelGlove WitcherModelGlove = new ModelGlove();
	public static final ResourceLocation KaerMorhenArmorT = Texture.get(EnumTexturePath.ARMOR, "KaerMorhen/Armor");
	public static final ResourceLocation KaerMorhenArmorHandT = Texture.get(EnumTexturePath.ARMOR, "KaerMorhen/Hands");
	public static final ResourceLocation KaerMorhenArmorPantsT = Texture.get(EnumTexturePath.ARMOR, "KaerMorhen/Pants");
	public static final ResourceLocation KaerMorhenArmorBootsT = Texture.get(EnumTexturePath.ARMOR, "KaerMorhen/Boots");
	public static final WolfArmor WolfArmor = new WolfArmor();
	public static final WolfHand WolfHand = new WolfHand();
	public static final WolfGlove WolfGlove = new WolfGlove();
	public static final WolfLeg WolfLeg = new WolfLeg();
	public static final ResourceLocation WolfT = Texture.get(EnumTexturePath.ARMOR, "wolfSchool/WolfSchool");
	public static final ResourceLocation WolfBootT = Texture.get(EnumTexturePath.ARMOR, "wolfSchool/WolfSchoolBoot");
	public static final RedanianHalebardistArmor RedanianHalebardistArmor = new RedanianHalebardistArmor();
	public static final RedanianHalebardistLeg RedanianHalebardistLeg = new RedanianHalebardistLeg();
	public static final RedanianHalebardistAddon RedanianHalebardistAddon = new RedanianHalebardistAddon();
	public static final ResourceLocation RedanianHalebardistArmorT = Texture.get(EnumTexturePath.ARMOR, "RedanianHalebardistArmor/Armor");
	public static final NilfgaardArmor NilfgaardArmor = new NilfgaardArmor();
	public static final NilfgaardHead NilfgaardHead = new NilfgaardHead();
	public static final NilfgaardArmorAddon NilfgaardArmorAddon = new NilfgaardArmorAddon();
	public static final NilfgaardGlove NilfgaardGlove = new NilfgaardGlove();
	public static final NilfgaardLeg NilfgaardLeg = new NilfgaardLeg();
	public static final ResourceLocation NilfgaardArmorT = Texture.get(EnumTexturePath.ARMOR, "Nilfgaard/Armor");
	public static final ResourceLocation NilfgaardArmorLegT = Texture.get(EnumTexturePath.ARMOR, "Nilfgaard/Leg");
	public static final ResourceLocation NilfgaardArmorBootT = Texture.get(EnumTexturePath.ARMOR, "Nilfgaard/Boot");
	public static final MagDeithraArmor MagDeithraArmor = new MagDeithraArmor();
	public static final MagDeithraHand MagDeithraHand = new MagDeithraHand();
	public static final MagDeithraLeftLeg MagDeithraLeftLeg = new MagDeithraLeftLeg();
	public static final MagDeithraRightLeg MagDeithraRightLeg = new MagDeithraRightLeg();
	public static final MagDeithraLegAddon MagDeithraLegAddon = new MagDeithraLegAddon();
	public static final ResourceLocation MagDeithraArmorT = Texture.get(EnumTexturePath.ARMOR, "MagDeithraArmor/Armor");
	public static final ResourceLocation ViperArmorT = Texture.get(EnumTexturePath.ARMOR, "viperSchool/Armor");
	public static final ResourceLocation ViperArmorHandT = Texture.get(EnumTexturePath.ARMOR, "viperSchool/Hands");
	public static final ResourceLocation ViperArmorPantsT = Texture.get(EnumTexturePath.ARMOR, "viperSchool/Pants");
	public static final ResourceLocation ViperArmorBootsT = Texture.get(EnumTexturePath.ARMOR, "viperSchool/Boots");
	public static final ShirtMody ShirtBody = new ShirtMody();
	public static final ShirtHand ShirtHand = new ShirtHand();
	public static final ShirtAddon ShirtAddon = new ShirtAddon();
	public static final ResourceLocation ShirtT = Texture.get(EnumTexturePath.ARMOR, "Shirt/WitcherShirt");
	public static final IModelCustom CatMedialion = Model.get(EnumModelPath.MEDALION, "CatMedallion");
	public static final ResourceLocation CatMedialionT = Texture.get(EnumTexturePath.MEDALION, "CatMedallion");
	public static final IModelCustom WolfMedialion = Model.get(EnumModelPath.MEDALION, "WolfMedallion");
	public static final ResourceLocation WolfMedialionT = Texture.get(EnumTexturePath.MEDALION, "WolfMedallion");
	public static final IModelCustom GriffinMedialion = Model.get(EnumModelPath.MEDALION, "GriffinMedallion");
	public static final ResourceLocation GriffinMedialionT = Texture.get(EnumTexturePath.ARMOR, "school/Griffin/1/texlow");
	public static final IModelCustom BearMedialion = Model.get(EnumModelPath.MEDALION, "BearMedallion");
	public static final ResourceLocation BearMedialionT = Texture.get(EnumTexturePath.ARMOR, "school/Bear/1/texlow");

	public static final ResourceLocation skinHuman = Texture.get(EnumTexturePath.SKIN, "Human");
	public static final ResourceLocation skinWitcher = Texture.get(EnumTexturePath.SKIN, "Witcher");

	public RenderPlayerArmor(RenderPlayerAPI renderPlayerAPI) {
		super(renderPlayerAPI);
	}

	@Override
	public void loadTextureOfEntity(Entity paramEntity) {
		if (KeelfyUtils.isClientSide()) {
			if (paramEntity instanceof EntityPlayer && DAPlayer.get(mc.thePlayer) != null) {
				mc.getTextureManager().bindTexture(skinWitcher);

				// TODO: Динамичный скин в ОБТ
				// if(WCPlayer.get((EntityPlayer)paramEntity).getPlayerClass()
				// == PlayerClass.HUMAN)
				// textureManager.bindTexture(skinHuman);
				// else
				// textureManager.bindTexture(skinWitcher);
			}
		}
	}

	private float r;

	@Override
	// TODO: Перписать рендер брони после фикса багов
	public void renderSpecials(AbstractClientPlayer player, float par2) {
		if (KeelfyUtils.isClientSide()) {
			
			super.renderSpecials(player, par2);

			TextureManager textureManager = mc.getTextureManager();
			ItemStack item = player.getHeldItem();
			
			if (item == null || !(item.getItem() instanceof Sword) || !player.isUsingItem()) {
				if (item != null && item.getItem() instanceof Sword) {
					if (!player.isRiding() && player.swingProgress != 0.0F) {
						GL11.glPushMatrix();
						super.renderPlayer.modelBipedMain.bipedLeftArm.offsetX = 0.0F;
						super.renderPlayer.modelBipedMain.bipedLeftArm.offsetY = 0.0F;
						super.renderPlayer.modelBipedMain.bipedLeftArm.offsetZ = 0.0F;
						GL11.glPopMatrix();
					}
				} else if (item != null && item.getItem() instanceof Sword && !player.isUsingItem()) {
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

			if (player.getCurrentArmor(2) != null) {
				Item currentArmor2Item = player.getCurrentArmor(2).getItem();

				if (currentArmor2Item == ArmorRegister.cidarianCavalrymanChestplate) {
					GL11.glPushMatrix();
						super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
						GL11.glTranslatef(0.0F, -0.05F, 0.0F);
						GL11.glScalef(1.1F, 1.1F, 1.1F);
						textureManager.bindTexture(CidarisT);
						this.RedanianHalebardistArmor.render(0.0625F);
					GL11.glPopMatrix();
					
					GL11.glPushMatrix();
						textureManager.bindTexture(CidarisT);
						super.renderPlayer.modelBipedMain.bipedLeftArm.postRender(0.0625F);
						GL11.glTranslatef(-0.33F, -0.14F, 0.0F);
						GL11.glScalef(1.05F, 1.05F, 1.05F);
						this.RedanianHalebardistAddon.render(0.0625F);
					GL11.glPopMatrix();
					
					GL11.glPushMatrix();
						textureManager.bindTexture(CidarisT);
						super.renderPlayer.modelBipedMain.bipedRightArm.postRender(0.0625F);
						GL11.glTranslatef(0.33F, -0.14F, 0.0F);
						GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
						GL11.glScalef(1.05F, 1.05F, 1.05F);
						this.RedanianHalebardistAddon.render(0.0625F);
					GL11.glPopMatrix();
					
					GL11.glPushMatrix();
						textureManager.bindTexture(CidarisT);
						super.renderPlayer.modelBipedMain.bipedRightLeg.postRender(0.0625F);
						GL11.glTranslatef(0.15F, -0.95F, 0.0F);
						GL11.glScalef(-1.15F, 1.15F, 1.15F);
						this.CidarisLeg.render(0.0625F);
					GL11.glPopMatrix();
					
					GL11.glPushMatrix();
						textureManager.bindTexture(CidarisT);
						super.renderPlayer.modelBipedMain.bipedLeftLeg.postRender(0.0625F);
						GL11.glTranslatef(-0.15F, -0.95F, 0.0F);
						GL11.glScalef(1.15F, 1.15F, 1.15F);
						this.CidarisLeg.render(0.0625F);
					GL11.glPopMatrix();
				}

				if (currentArmor2Item == ArmorRegister.cidarianChestplate) {
					GL11.glPushMatrix();
					super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
					GL11.glTranslatef(0.0F, -0.05F, 0.0F);
					GL11.glScalef(1.1F, 1.1F, 1.1F);
					textureManager.bindTexture(CidarisCombezT);
					this.BelhavenaBrigantineBody.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(CidarisCombezT);
					super.renderPlayer.modelBipedMain.bipedLeftArm.postRender(0.0625F);
					GL11.glTranslatef(-0.33F, -0.14F, 0.0F);
					GL11.glScalef(1.05F, 1.05F, 1.05F);
					this.BelhavenaBrigantineHand.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(CidarisCombezT);
					super.renderPlayer.modelBipedMain.bipedRightArm.postRender(0.0625F);
					GL11.glTranslatef(0.33F, -0.14F, 0.0F);
					GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
					GL11.glScalef(1.05F, 1.05F, 1.05F);
					this.BelhavenaBrigantineHand.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(CidarisCombezT);
					super.renderPlayer.modelBipedMain.bipedRightLeg.postRender(0.0625F);
					GL11.glTranslatef(0.15F, -0.89F, 0.0F);
					GL11.glScalef(-1.15F, 1.15F, 1.15F);
					this.BelhavenaBrigantineLeg.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(CidarisCombezT);
					super.renderPlayer.modelBipedMain.bipedLeftLeg.postRender(0.0625F);
					GL11.glTranslatef(-0.15F, -0.89F, 0.0F);
					GL11.glScalef(1.15F, 1.15F, 1.15F);
					this.BelhavenaBrigantineLeg.render(0.0625F);
					GL11.glPopMatrix();
				}

				if (currentArmor2Item == ArmorRegister.cloth) {
					GL11.glPushMatrix();
					super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
					GL11.glTranslatef(0.0F, -0.05F, 0.0F);
					GL11.glScalef(1.0F, 1.07F, 1.0F);
					textureManager.bindTexture(ShirtT);
					this.ShirtBody.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
					GL11.glTranslatef(0.0F, -0.05F, 0.0F);
					GL11.glScalef(0.96F, 0.96F, 0.96F);
					textureManager.bindTexture(ShirtT);
					this.ShirtAddon.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(ShirtT);
					super.renderPlayer.modelBipedMain.bipedLeftArm.postRender(0.0625F);
					GL11.glTranslatef(-0.315F, -0.14F, 0.0F);
					GL11.glScalef(1.0F, 1.0F, 1.0F);
					this.ShirtHand.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(ShirtT);
					super.renderPlayer.modelBipedMain.bipedRightArm.postRender(0.0625F);
					GL11.glTranslatef(0.315F, -0.14F, 0.0F);
					GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
					GL11.glScalef(1.0F, 1.0F, 1.0F);
					this.ShirtHand.render(0.0625F);
					GL11.glPopMatrix();
				}

				if (currentArmor2Item == ArmorRegister.infantryChestplate) {
					GL11.glPushMatrix();
					super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
					GL11.glTranslatef(0.0F, -0.05F, 0.0F);
					GL11.glScalef(1.1F, 1.1F, 1.1F);
					textureManager.bindTexture(BelhavenaBrigantineT);
					this.BelhavenaBrigantineBody.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(BelhavenaBrigantineT);
					super.renderPlayer.modelBipedMain.bipedLeftArm.postRender(0.0625F);
					GL11.glTranslatef(-0.33F, -0.14F, 0.0F);
					GL11.glScalef(1.05F, 1.05F, 1.05F);
					this.BelhavenaBrigantineHand.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(BelhavenaBrigantineT);
					super.renderPlayer.modelBipedMain.bipedRightArm.postRender(0.0625F);
					GL11.glTranslatef(0.33F, -0.14F, 0.0F);
					GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
					GL11.glScalef(1.05F, 1.05F, 1.05F);
					this.BelhavenaBrigantineHand.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(BelhavenaBrigantineT);
					super.renderPlayer.modelBipedMain.bipedRightLeg.postRender(0.0625F);
					GL11.glTranslatef(0.15F, -0.89F, 0.0F);
					GL11.glScalef(-1.15F, 1.15F, 1.15F);
					this.BelhavenaBrigantineLeg.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(BelhavenaBrigantineT);
					super.renderPlayer.modelBipedMain.bipedLeftLeg.postRender(0.0625F);
					GL11.glTranslatef(-0.15F, -0.89F, 0.0F);
					GL11.glScalef(1.15F, 1.15F, 1.15F);
					this.BelhavenaBrigantineLeg.render(0.0625F);
					GL11.glPopMatrix();
				}

				if (currentArmor2Item == ArmorRegister.alebardistChestplate) {
					GL11.glPushMatrix();
					super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
					GL11.glTranslatef(0.0F, -0.05F, 0.0F);
					GL11.glScalef(1.1F, 1.1F, 1.1F);
					textureManager.bindTexture(RedanianHalebardistArmorT);
					this.RedanianHalebardistArmor.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(RedanianHalebardistArmorT);
					super.renderPlayer.modelBipedMain.bipedLeftArm.postRender(0.0625F);
					GL11.glTranslatef(-0.33F, -0.14F, 0.0F);
					GL11.glScalef(1.05F, 1.05F, 1.05F);
					this.RedanianHalebardistAddon.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(RedanianHalebardistArmorT);
					super.renderPlayer.modelBipedMain.bipedRightArm.postRender(0.0625F);
					GL11.glTranslatef(0.33F, -0.14F, 0.0F);
					GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
					GL11.glScalef(1.05F, 1.05F, 1.05F);
					this.RedanianHalebardistAddon.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(RedanianHalebardistArmorT);
					super.renderPlayer.modelBipedMain.bipedRightLeg.postRender(0.0625F);
					GL11.glTranslatef(0.15F, -0.95F, 0.0F);
					GL11.glScalef(-1.15F, 1.15F, 1.15F);
					this.RedanianHalebardistLeg.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(RedanianHalebardistArmorT);
					super.renderPlayer.modelBipedMain.bipedLeftLeg.postRender(0.0625F);
					GL11.glTranslatef(-0.15F, -0.95F, 0.0F);
					GL11.glScalef(1.15F, 1.15F, 1.15F);
					this.RedanianHalebardistLeg.render(0.0625F);
					GL11.glPopMatrix();
				}

				if (currentArmor2Item == ArmorRegister.hindarsfjallChestplate) {
					GL11.glPushMatrix();
					super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
					GL11.glTranslatef(0.0F, -0.05F, 0.0F);
					GL11.glScalef(1.1F, 1.1F, 1.1F);
					textureManager.bindTexture(HindarsfjallHeavyArmorT);
					this.HindarsfjallHeavyArmorBody.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(HindarsfjallHeavyArmorT);
					super.renderPlayer.modelBipedMain.bipedLeftArm.postRender(0.0625F);
					GL11.glTranslatef(-0.33F, -0.14F, 0.0F);
					GL11.glScalef(1.05F, 1.05F, 1.05F);
					this.HindarsfjallHeavyArmorHand.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(HindarsfjallHeavyArmorT);
					super.renderPlayer.modelBipedMain.bipedRightArm.postRender(0.0625F);
					GL11.glTranslatef(0.33F, -0.14F, 0.0F);
					GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
					GL11.glScalef(1.05F, 1.05F, 1.05F);
					this.HindarsfjallHeavyArmorHand.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(HindarsfjallHeavyArmorT);
					super.renderPlayer.modelBipedMain.bipedRightLeg.postRender(0.0625F);
					GL11.glTranslatef(0.13F, -0.76F, 0.0F);
					GL11.glScalef(-1.15F, 1.05F, 1.15F);
					this.HindarsfjallHeavyArmorLeg.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(HindarsfjallHeavyArmorT);
					super.renderPlayer.modelBipedMain.bipedLeftLeg.postRender(0.0625F);
					GL11.glTranslatef(-0.13F, -0.76F, 0.0F);
					GL11.glScalef(1.15F, 1.05F, 1.15F);
					this.HindarsfjallHeavyArmorLeg.render(0.0625F);
					GL11.glPopMatrix();
				}

				if (currentArmor2Item == ArmorRegister.redanCavalrymanChestplate) {
					GL11.glPushMatrix();
					super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
					GL11.glTranslatef(0.0F, -0.05F, 0.0F);
					GL11.glScalef(1.1F, 1.1F, 1.1F);
					textureManager.bindTexture(WitchunterArmorT);
					this.WitchunterArmorBody.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(WitchunterArmorT);
					super.renderPlayer.modelBipedMain.bipedRightArm.postRender(0.0625F);
					GL11.glTranslatef(0.33F, -0.14F, 0.0F);
					GL11.glScalef(1.05F, 1.05F, 1.05F);
					this.WitchunterArmorHand.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(WitchunterArmorT);
					super.renderPlayer.modelBipedMain.bipedLeftArm.postRender(0.0625F);
					GL11.glTranslatef(0.46F, -0.14F, 0.0F);
					GL11.glScalef(1.05F, 1.05F, 1.05F);
					this.WitchunterArmorHand.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(WitchunterArmorT);
					super.renderPlayer.modelBipedMain.bipedLeftLeg.postRender(0.0625F);
					GL11.glTranslatef(-0.13F, -0.76F, 0.0F);
					GL11.glScalef(-1.15F, 1.05F, 1.15F);
					this.WitchunterArmorLeg.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(WitchunterArmorT);
					super.renderPlayer.modelBipedMain.bipedRightLeg.postRender(0.0625F);
					GL11.glTranslatef(0.13F, -0.76F, 0.0F);
					GL11.glScalef(1.15F, 1.05F, 1.15F);
					this.WitchunterArmorLeg.render(0.0625F);
					GL11.glPopMatrix();
				}

				if (currentArmor2Item == ArmorRegister.ellanderChestplate) {
					GL11.glPushMatrix();
					super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
					GL11.glTranslatef(0.0F, -0.05F, 0.0F);
					GL11.glScalef(1.1F, 1.1F, 1.1F);
					textureManager.bindTexture(MagDeithraArmorT);
					this.MagDeithraArmor.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(MagDeithraArmorT);
					super.renderPlayer.modelBipedMain.bipedLeftArm.postRender(0.0625F);
					GL11.glTranslatef(-0.33F, -0.14F, 0.0F);
					GL11.glScalef(1.05F, 1.05F, 1.05F);
					this.MagDeithraHand.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(MagDeithraArmorT);
					super.renderPlayer.modelBipedMain.bipedRightArm.postRender(0.0625F);
					GL11.glTranslatef(0.33F, -0.14F, 0.0F);
					GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
					GL11.glScalef(1.05F, 1.05F, 1.05F);
					this.MagDeithraHand.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(MagDeithraArmorT);
					super.renderPlayer.modelBipedMain.bipedRightLeg.postRender(0.0625F);
					GL11.glTranslatef(0.15F, -0.95F, 0.0F);
					GL11.glScalef(-1.15F, 1.15F, 1.15F);
					this.MagDeithraRightLeg.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(MagDeithraArmorT);
					super.renderPlayer.modelBipedMain.bipedLeftLeg.postRender(0.0625F);
					GL11.glTranslatef(-0.13F, -0.95F, 0.0F);
					GL11.glScalef(1.15F, 1.15F, 1.15F);
					this.MagDeithraRightLeg.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(MagDeithraArmorT);
					super.renderPlayer.modelBipedMain.bipedRightLeg.postRender(0.0625F);
					GL11.glTranslatef(0.15F, -0.9F, -0.0F);
					GL11.glScalef(1.4F, 1.3F, -1.4F);
					this.MagDeithraLegAddon.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(MagDeithraArmorT);
					super.renderPlayer.modelBipedMain.bipedLeftLeg.postRender(0.0625F);
					GL11.glTranslatef(0.23F, -0.85F, -0.01F);
					GL11.glScalef(1.4F, 1.3F, -1.4F);
					this.MagDeithraLegAddon.render(0.0625F);
					GL11.glPopMatrix();
				}

				if (currentArmor2Item == ArmorRegister.temerChestplate) {
					GL11.glPushMatrix();
					super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
					GL11.glTranslatef(0.0F, -0.05F, 0.0F);
					GL11.glScalef(1.1F, 1.1F, 1.1F);
					textureManager.bindTexture(NilfgaardArmorT);
					this.NilfgaardArmor.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					super.renderPlayer.modelBipedMain.bipedHead.postRender(0.0625F);
					GL11.glTranslatef(0.0F, -0.05F, 0.0F);
					GL11.glScalef(1.1F, 1.1F, 1.1F);
					textureManager.bindTexture(NilfgaardArmorT);
					this.NilfgaardHead.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(NilfgaardArmorT);
					super.renderPlayer.modelBipedMain.bipedLeftArm.postRender(0.0625F);
					GL11.glTranslatef(-0.33F, -0.14F, 0.0F);
					GL11.glScalef(1.05F, 1.05F, 1.05F);
					this.NilfgaardArmorAddon.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(NilfgaardArmorT);
					super.renderPlayer.modelBipedMain.bipedRightArm.postRender(0.0625F);
					GL11.glTranslatef(0.33F, -0.14F, 0.0F);
					GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
					GL11.glScalef(1.05F, 1.05F, 1.05F);
					this.NilfgaardArmorAddon.render(0.0625F);
					GL11.glPopMatrix();
				}

				if (currentArmor2Item == ArmorRegister.bearSchoolChestplate) {
					GL11.glPushMatrix();
					super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
					GL11.glTranslatef(0.0F, -0.05F, 0.0F);
					GL11.glScalef(1.05F, 1.05F, 1.05F);
					textureManager.bindTexture(BearSchoolT);
					this.BearSchoolArmor.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(BearSchoolT);
					super.renderPlayer.modelBipedMain.bipedLeftArm.postRender(0.0625F);
					GL11.glTranslatef(-0.33F, -0.14F, 0.0F);
					GL11.glScalef(1.05F, 1.05F, 1.05F);
					this.BearSchoolHand.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(BearSchoolT);
					super.renderPlayer.modelBipedMain.bipedRightArm.postRender(0.0625F);
					GL11.glTranslatef(0.33F, -0.14F, 0.0F);
					GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
					GL11.glScalef(1.05F, 1.05F, 1.05F);
					this.BearSchoolHand.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(BearSchoolSkirtT);
					super.renderPlayer.modelBipedMain.bipedRightLeg.postRender(0.0625F);
					GL11.glTranslatef(0.15F, -0.95F, 0.0F);
					GL11.glScalef(-1.25F, 1.25F, 1.25F);
					this.ModelSkirt.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(BearSchoolSkirtT);
					super.renderPlayer.modelBipedMain.bipedLeftLeg.postRender(0.0625F);
					GL11.glTranslatef(-0.15F, -0.95F, 0.0F);
					GL11.glScalef(1.25F, 1.25F, 1.25F);
					this.ModelSkirt.render(0.0625F);
					GL11.glPopMatrix();
				}

				if (currentArmor2Item == ArmorRegister.bearSchoolChestplate2) {
					GL11.glPushMatrix();
					super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
					GL11.glTranslatef(0.0F, -0.05F, 0.0F);
					GL11.glScalef(1.05F, 1.05F, 1.05F);
					textureManager.bindTexture(BearSchoolT2);
					this.BearSchoolArmor.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(BearSchoolT2);
					super.renderPlayer.modelBipedMain.bipedLeftArm.postRender(0.0625F);
					GL11.glTranslatef(-0.33F, -0.14F, 0.0F);
					GL11.glScalef(1.05F, 1.05F, 1.05F);
					this.BearSchoolHand.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(BearSchoolT2);
					super.renderPlayer.modelBipedMain.bipedRightArm.postRender(0.0625F);
					GL11.glTranslatef(0.33F, -0.14F, 0.0F);
					GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
					GL11.glScalef(1.05F, 1.05F, 1.05F);
					this.BearSchoolHand.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(BearSchoolSkirtT2);
					super.renderPlayer.modelBipedMain.bipedRightLeg.postRender(0.0625F);
					GL11.glTranslatef(0.15F, -0.95F, 0.0F);
					GL11.glScalef(-1.25F, 1.25F, 1.25F);
					this.ModelSkirt.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(BearSchoolSkirtT2);
					super.renderPlayer.modelBipedMain.bipedLeftLeg.postRender(0.0625F);
					GL11.glTranslatef(-0.15F, -0.95F, 0.0F);
					GL11.glScalef(1.25F, 1.25F, 1.25F);
					this.ModelSkirt.render(0.0625F);
					GL11.glPopMatrix();
				}

				if (currentArmor2Item == ArmorRegister.catSchoolChestplate) {
					GL11.glPushMatrix();
					super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
					GL11.glTranslatef(0.0F, -0.05F, 0.0F);
					GL11.glScalef(1.05F, 1.05F, 1.05F);
					textureManager.bindTexture(CatSchoolArmorT);
					this.CatSchoolArmor.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(CatSchoolArmorT);
					super.renderPlayer.modelBipedMain.bipedLeftArm.postRender(0.0625F);
					GL11.glTranslatef(-0.33F, -0.14F, 0.0F);
					GL11.glScalef(1.05F, 1.05F, 1.05F);
					this.CatSchoolSleeve.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(CatSchoolArmorT);
					super.renderPlayer.modelBipedMain.bipedRightArm.postRender(0.0625F);
					GL11.glTranslatef(0.33F, -0.14F, 0.0F);
					GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
					GL11.glScalef(1.05F, 1.05F, 1.05F);
					this.CatSchoolSleeve.render(0.0625F);
					GL11.glPopMatrix();
				}

				if (currentArmor2Item == ArmorRegister.talgarChestplate) {
					GL11.glPushMatrix();
					super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
					GL11.glTranslatef(0.0F, -0.05F, 0.0F);
					GL11.glScalef(1.05F, 1.05F, 1.05F);
					textureManager.bindTexture(TalgarT);
					this.TalgarBody.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
					GL11.glTranslatef(0.03F, 0.1F, -0.2F);
					GL11.glScalef(0.2F, 0.2F, 0.2F);
					textureManager.bindTexture(TalgarSignT);
					this.Sign.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(TalgarT);
					super.renderPlayer.modelBipedMain.bipedLeftArm.postRender(0.0625F);
					GL11.glTranslatef(-0.33F, -0.14F, 0.0F);
					GL11.glScalef(1.05F, 1.05F, 1.05F);
					this.TalgarHand.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(TalgarT);
					super.renderPlayer.modelBipedMain.bipedRightArm.postRender(0.0625F);
					GL11.glTranslatef(0.33F, -0.14F, 0.0F);
					GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
					GL11.glScalef(1.05F, 1.05F, 1.05F);
					this.TalgarHand.render(0.0625F);
					GL11.glPopMatrix();
				}

				if (currentArmor2Item == ArmorRegister.wolfSchoolChestplate) {
					GL11.glPushMatrix();
					super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
					GL11.glTranslatef(0.0F, -0.05F, 0.0F);
					GL11.glScalef(1.05F, 1.05F, 1.05F);
					textureManager.bindTexture(WolfT);
					this.WolfArmor.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(WolfT);
					super.renderPlayer.modelBipedMain.bipedLeftArm.postRender(0.0625F);
					GL11.glTranslatef(-0.33F, -0.14F, 0.0F);
					GL11.glScalef(1.05F, 1.05F, 1.05F);
					this.WolfHand.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(WolfT);
					super.renderPlayer.modelBipedMain.bipedRightArm.postRender(0.0625F);
					GL11.glTranslatef(0.33F, -0.14F, 0.0F);
					GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
					GL11.glScalef(1.05F, 1.05F, 1.05F);
					this.WolfHand.render(0.0625F);
					GL11.glPopMatrix();
				}

				if (currentArmor2Item == ArmorRegister.griffinSchoolChestplate) {
					GL11.glPushMatrix();
					super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
					GL11.glTranslatef(0.0F, -0.03F, 0.0F);
					GL11.glScalef(1.1F, 1.0F, 1.1F);
					textureManager.bindTexture(GriffinArmorT);
					this.GriffinArmor.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(GriffinArmorT);
					super.renderPlayer.modelBipedMain.bipedLeftArm.postRender(0.0625F);
					GL11.glTranslatef(-0.33F, -0.19F, -0.0F);
					GL11.glScalef(1.05F, 1.05F, 1.05F);
					this.GriffinHand.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(GriffinArmorT);
					super.renderPlayer.modelBipedMain.bipedRightArm.postRender(0.0625F);
					GL11.glTranslatef(0.35F, -0.19F, -0.0F);
					GL11.glScalef(-1.1F, 1.05F, -1.05F);
					this.GriffinHand.render(0.0625F);
					GL11.glPopMatrix();
				}

				if (currentArmor2Item == ArmorRegister.viperSchoolChestplate) {
					GL11.glPushMatrix();
					super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
					GL11.glTranslatef(0.0F, 0.0F, 0.0F);
					GL11.glScalef(1.1F, 1.1F, 1.1F);
					textureManager.bindTexture(ViperArmorT);
					this.KaerMorhenArmor.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					super.renderPlayer.modelBipedMain.bipedLeftArm.postRender(0.0625F);
					GL11.glTranslatef(-0.3F, -0.12F, 0.0F);
					GL11.glScalef(1.0F, 1.0F, 0.9F);
					textureManager.bindTexture(ViperArmorT);
					this.KaerMorhenHand.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					super.renderPlayer.modelBipedMain.bipedRightArm.postRender(0.0625F);
					GL11.glTranslatef(0.3F, -0.12F, 0.0F);
					GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
					GL11.glScalef(1.0F, 1.0F, 0.9F);
					textureManager.bindTexture(ViperArmorT);
					this.KaerMorhenHand.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					super.renderPlayer.modelBipedMain.bipedLeftArm.postRender(0.0625F);
					GL11.glTranslatef(-0.27F, 0.12F, 0.0F);
					GL11.glScalef(0.9F, 0.9F, 0.8F);
					textureManager.bindTexture(ViperArmorT);
					this.KaerMorhenHandAddon.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					super.renderPlayer.modelBipedMain.bipedRightArm.postRender(0.0625F);
					GL11.glTranslatef(0.27F, 0.12F, 0.0F);
					GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
					GL11.glScalef(0.9F, 0.9F, 0.8F);
					textureManager.bindTexture(ViperArmorT);
					this.KaerMorhenHandAddon.render(0.0625F);
					GL11.glPopMatrix();
				}

				if (currentArmor2Item == ArmorRegister.hunterChestplate) {
					GL11.glPushMatrix();
					super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
					GL11.glTranslatef(0.0F, 0.0F, 0.0F);
					GL11.glScalef(1.1F, 1.1F, 1.1F);
					textureManager.bindTexture(KaerMorhenArmorT);
					this.KaerMorhenArmor.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					super.renderPlayer.modelBipedMain.bipedLeftArm.postRender(0.0625F);
					GL11.glTranslatef(-0.3F, -0.12F, 0.0F);
					GL11.glScalef(1.0F, 1.0F, 0.9F);
					textureManager.bindTexture(KaerMorhenArmorT);
					this.KaerMorhenHand.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					super.renderPlayer.modelBipedMain.bipedRightArm.postRender(0.0625F);
					GL11.glTranslatef(0.3F, -0.12F, 0.0F);
					GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
					GL11.glScalef(1.0F, 1.0F, 0.9F);
					textureManager.bindTexture(KaerMorhenArmorT);
					this.KaerMorhenHand.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					super.renderPlayer.modelBipedMain.bipedLeftArm.postRender(0.0625F);
					GL11.glTranslatef(-0.27F, 0.12F, 0.0F);
					GL11.glScalef(0.9F, 0.9F, 0.9F);
					textureManager.bindTexture(KaerMorhenArmorT);
					this.KaerMorhenHandAddon.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					super.renderPlayer.modelBipedMain.bipedRightArm.postRender(0.0625F);
					GL11.glTranslatef(0.27F, 0.12F, 0.0F);
					GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
					GL11.glScalef(0.9F, 0.9F, 0.9F);
					textureManager.bindTexture(KaerMorhenArmorT);
					this.KaerMorhenHandAddon.render(0.0625F);
					GL11.glPopMatrix();
				}
			}

			if (player.getCurrentArmor(1) != null) {
				Item currentArmor1Item = player.getCurrentArmor(1).getItem();

				if (currentArmor1Item == ArmorRegister.cidarianArmyPants) {
					GL11.glPushMatrix();
					textureManager.bindTexture(WitcherLuriskyPantsT);
					super.renderPlayer.modelBipedMain.bipedLeftLeg.postRender(0.0625F);
					GL11.glTranslatef(-0.135F, -0.8F, 0.0F);
					GL11.glScalef(1.05F, 1.05F, 1.05F);
					this.WitcherLuriskyPants.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(WitcherLuriskyPantsT);
					super.renderPlayer.modelBipedMain.bipedRightLeg.postRender(0.0625F);
					GL11.glTranslatef(0.135F, -0.8F, 0.0F);
					GL11.glScalef(-1.05F, 1.05F, 1.05F);
					this.WitcherLuriskyPants.render(0.0625F);
					GL11.glPopMatrix();
				}

				if (currentArmor1Item == ArmorRegister.talgarPants) {
					GL11.glPushMatrix();
					textureManager.bindTexture(TalgarT);
					super.renderPlayer.modelBipedMain.bipedLeftLeg.postRender(0.0625F);
					GL11.glTranslatef(-0.135F, -0.8F, 0.0F);
					GL11.glScalef(1.05F, 1.05F, 1.05F);
					this.TalgarLeg.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(TalgarT);
					super.renderPlayer.modelBipedMain.bipedRightLeg.postRender(0.0625F);
					GL11.glTranslatef(0.135F, -0.8F, 0.0F);
					GL11.glScalef(-1.05F, 1.05F, 1.05F);
					this.TalgarLeg.render(0.0625F);
					GL11.glPopMatrix();
				}

				if (currentArmor1Item == ArmorRegister.leatherPants) {
					GL11.glPushMatrix();
					textureManager.bindTexture(WitcherPathfinderPantsT);
					super.renderPlayer.modelBipedMain.bipedLeftLeg.postRender(0.0625F);
					GL11.glTranslatef(-0.135F, -0.8F, 0.0F);
					GL11.glScalef(1.0F, 1.0F, 1.0F);
					this.WitcherPathfinderLegs.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(WitcherPathfinderPantsT);
					super.renderPlayer.modelBipedMain.bipedRightLeg.postRender(0.0625F);
					GL11.glTranslatef(0.135F, -0.8F, 0.0F);
					GL11.glScalef(-1.0F, 1.0F, -1.0F);
					this.WitcherPathfinderLegs.render(0.0625F);
					GL11.glPopMatrix();
				}

				if (currentArmor1Item == ArmorRegister.temerArmyPants) {
					GL11.glPushMatrix();
					textureManager.bindTexture(WitcherTopornikPantsT);
					super.renderPlayer.modelBipedMain.bipedLeftLeg.postRender(0.0625F);
					GL11.glTranslatef(-0.15F, -0.8F, 0.0F);
					GL11.glScalef(1.05F, 1.05F, 1.05F);
					this.WitcherTopornikPants.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(WitcherTopornikPantsT);
					super.renderPlayer.modelBipedMain.bipedRightLeg.postRender(0.0625F);
					GL11.glTranslatef(0.15F, -0.8F, 0.0F);
					GL11.glScalef(-1.05F, 1.05F, -1.05F);
					this.WitcherTopornikPants.render(0.0625F);
					GL11.glPopMatrix();
				}

				if (currentArmor1Item == ArmorRegister.bearSchoolPants2) {
					GL11.glPushMatrix();
					textureManager.bindTexture(BearSchoolT);
					super.renderPlayer.modelBipedMain.bipedRightLeg.postRender(0.0625F);
					GL11.glTranslatef(-0.15F, -0.85F, 0.0F);
					GL11.glScalef(1.15F, 1.1F, 1.15F);
					this.WitcherLeg.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(BearSchoolT);
					super.renderPlayer.modelBipedMain.bipedLeftLeg.postRender(0.0625F);
					GL11.glTranslatef(0.15F, -0.85F, 0.0F);
					GL11.glScalef(-1.15F, 1.1F, -1.15F);
					this.WitcherLeg.render(0.0625F);
					GL11.glPopMatrix();
				}

				if (currentArmor1Item == ArmorRegister.bearSchoolPants) {
					GL11.glPushMatrix();
					textureManager.bindTexture(BearSchoolT);
					super.renderPlayer.modelBipedMain.bipedRightLeg.postRender(0.0625F);
					GL11.glTranslatef(-0.15F, -0.85F, 0.0F);
					GL11.glScalef(1.15F, 1.1F, 1.15F);
					this.WitcherLeg.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(BearSchoolT);
					super.renderPlayer.modelBipedMain.bipedLeftLeg.postRender(0.0625F);
					GL11.glTranslatef(0.15F, -0.85F, 0.0F);
					GL11.glScalef(-1.15F, 1.1F, -1.15F);
					this.WitcherLeg.render(0.0625F);
					GL11.glPopMatrix();
				}

				if (currentArmor1Item == ArmorRegister.wolfSchoolPants) {
					GL11.glPushMatrix();
					textureManager.bindTexture(WolfT);
					super.renderPlayer.modelBipedMain.bipedRightLeg.postRender(0.0625F);
					GL11.glTranslatef(-0.15F, -0.85F, 0.0F);
					GL11.glScalef(1.15F, 1.1F, 1.15F);
					this.WitcherLeg.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(WolfT);
					super.renderPlayer.modelBipedMain.bipedLeftLeg.postRender(0.0625F);
					GL11.glTranslatef(0.15F, -0.85F, 0.0F);
					GL11.glScalef(-1.15F, 1.1F, -1.15F);
					this.WitcherLeg.render(0.0625F);
					GL11.glPopMatrix();
				}

				if (currentArmor1Item == ArmorRegister.temerPants) {
					GL11.glPushMatrix();
					textureManager.bindTexture(NilfgaardArmorT);
					super.renderPlayer.modelBipedMain.bipedRightLeg.postRender(0.0625F);
					GL11.glTranslatef(-0.15F, -0.9F, 0.0F);
					GL11.glScalef(1.15F, 1.1F, 1.15F);
					this.NilfgaardLeg.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(NilfgaardArmorT);
					super.renderPlayer.modelBipedMain.bipedLeftLeg.postRender(0.0625F);
					GL11.glTranslatef(0.15F, -0.9F, 0.0F);
					GL11.glScalef(-1.15F, 1.1F, 1.15F);
					this.NilfgaardLeg.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(NilfgaardArmorLegT);
					super.renderPlayer.modelBipedMain.bipedRightLeg.postRender(0.0625F);
					GL11.glTranslatef(-0.15F, -0.85F, 0.0F);
					GL11.glScalef(1.15F, 1.1F, 1.15F);
					this.WitcherLeg.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(NilfgaardArmorLegT);
					super.renderPlayer.modelBipedMain.bipedLeftLeg.postRender(0.0625F);
					GL11.glTranslatef(0.15F, -0.85F, 0.0F);
					GL11.glScalef(-1.15F, 1.1F, -1.15F);
					this.WitcherLeg.render(0.0625F);
					GL11.glPopMatrix();
				}

				if (currentArmor1Item == ArmorRegister.catSchoolPants) {
					GL11.glPushMatrix();
					textureManager.bindTexture(CatPantsT);
					super.renderPlayer.modelBipedMain.bipedRightLeg.postRender(0.0625F);
					GL11.glTranslatef(-0.15F, -0.85F, 0.0F);
					GL11.glScalef(1.05F, 1.05F, 1.05F);
					this.CatSchoolLeg.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(CatPantsT);
					super.renderPlayer.modelBipedMain.bipedLeftLeg.postRender(0.0625F);
					GL11.glTranslatef(0.15F, -0.85F, 0.0F);
					GL11.glScalef(-1.05F, 1.051F, -1.05F);
					this.CatSchoolLeg.render(0.0625F);
					GL11.glPopMatrix();
				}

				if (currentArmor1Item == ArmorRegister.griffinSchoolPants) {
					GL11.glPushMatrix();
					textureManager.bindTexture(GriffinPantsT);
					super.renderPlayer.modelBipedMain.bipedRightLeg.postRender(0.0625F);
					GL11.glTranslatef(0.15F, -0.85F, 0.0F);
					GL11.glScalef(1.15F, 1.1F, 1.15F);
					this.WitcherRightLeg.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(GriffinPantsT);
					super.renderPlayer.modelBipedMain.bipedLeftLeg.postRender(0.0625F);
					GL11.glTranslatef(-0.15F, -0.85F, 0.0F);
					GL11.glScalef(1.15F, 1.1F, 1.15F);
					this.WitcherLeftLeg.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(GriffinArmorT);
					super.renderPlayer.modelBipedMain.bipedRightLeg.postRender(0.0625F);
					GL11.glTranslatef(0.13F, -0.85F, 0.0F);
					GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
					GL11.glScalef(1.1F, 1.1F, 1.1F);
					this.GriffinLeg.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(GriffinArmorT);
					super.renderPlayer.modelBipedMain.bipedLeftLeg.postRender(0.0625F);
					GL11.glTranslatef(-0.15F, -0.85F, 0.0F);
					GL11.glScalef(1.1F, 1.1F, 1.1F);
					this.GriffinLeg.render(0.0625F);
					GL11.glPopMatrix();
				}

				if (currentArmor1Item == ArmorRegister.hunterPants) {
					GL11.glPushMatrix();
					textureManager.bindTexture(KaerMorhenArmorPantsT);
					super.renderPlayer.modelBipedMain.bipedRightLeg.postRender(0.0625F);
					GL11.glTranslatef(0.15F, -0.85F, 0.0F);
					GL11.glScalef(1.15F, 1.1F, 1.15F);
					this.WitcherRightLeg.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(KaerMorhenArmorPantsT);
					super.renderPlayer.modelBipedMain.bipedLeftLeg.postRender(0.0625F);
					GL11.glTranslatef(-0.15F, -0.85F, 0.0F);
					GL11.glScalef(1.15F, 1.1F, 1.15F);
					this.WitcherLeftLeg.render(0.0625F);
					GL11.glPopMatrix();
				}

				if (currentArmor1Item == ArmorRegister.viperSchoolPants) {
					GL11.glPushMatrix();
					textureManager.bindTexture(ViperArmorPantsT);
					super.renderPlayer.modelBipedMain.bipedRightLeg.postRender(0.0625F);
					GL11.glTranslatef(0.15F, -0.85F, 0.0F);
					GL11.glScalef(1.15F, 1.1F, 1.15F);
					this.WitcherRightLeg.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(ViperArmorPantsT);
					super.renderPlayer.modelBipedMain.bipedLeftLeg.postRender(0.0625F);
					GL11.glTranslatef(-0.15F, -0.85F, 0.0F);
					GL11.glScalef(1.15F, 1.1F, 1.15F);
					this.WitcherLeftLeg.render(0.0625F);
					GL11.glPopMatrix();
				}
			}

			if (player.getCurrentArmor(3) != null) {

				Item currentArmor3Item = player.getCurrentArmor(3).getItem();

				if (currentArmor3Item == ArmorRegister.bearSchoolGloves) {
					GL11.glPushMatrix();
					textureManager.bindTexture(BearSchoolT);
					super.renderPlayer.modelBipedMain.bipedLeftArm.postRender(0.0625F);
					GL11.glTranslatef(-0.33F, -0.14F, 0.0F);
					GL11.glScalef(1.05F, 1.05F, 1.05F);
					this.BearSchoolHand.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(BearSchoolT);
					super.renderPlayer.modelBipedMain.bipedRightArm.postRender(0.0625F);
					GL11.glTranslatef(0.33F, -0.14F, 0.0F);
					GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
					GL11.glScalef(1.05F, 1.05F, 1.05F);
					this.BearSchoolHand.render(0.0625F);
					GL11.glPopMatrix();
				}

				if (currentArmor3Item == ArmorRegister.bearSchoolGloves2) {
					GL11.glPushMatrix();
					textureManager.bindTexture(BearSchoolT2);
					super.renderPlayer.modelBipedMain.bipedLeftArm.postRender(0.0625F);
					GL11.glTranslatef(-0.33F, -0.14F, 0.0F);
					GL11.glScalef(1.05F, 1.05F, 1.05F);
					this.BearSchoolHand.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(BearSchoolT2);
					super.renderPlayer.modelBipedMain.bipedRightArm.postRender(0.0625F);
					GL11.glTranslatef(0.33F, -0.14F, 0.0F);
					GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
					GL11.glScalef(1.05F, 1.05F, 1.05F);
					this.BearSchoolHand.render(0.0625F);
					GL11.glPopMatrix();
				}

				if (currentArmor3Item == ArmorRegister.wolfSchoolGloves) {
					GL11.glPushMatrix();
					textureManager.bindTexture(WolfT);
					super.renderPlayer.modelBipedMain.bipedLeftArm.postRender(0.0625F);
					GL11.glTranslatef(-0.33F, -0.14F, 0.0F);
					GL11.glScalef(1.05F, 1.05F, 1.05F);
					this.WolfGlove.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(WolfT);
					super.renderPlayer.modelBipedMain.bipedRightArm.postRender(0.0625F);
					GL11.glTranslatef(0.33F, -0.14F, 0.0F);
					GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
					GL11.glScalef(1.05F, 1.05F, 1.05F);
					this.WolfGlove.render(0.0625F);
					GL11.glPopMatrix();
				}

				if (currentArmor3Item == ArmorRegister.temerGloves) {
					GL11.glPushMatrix();
					textureManager.bindTexture(NilfgaardArmorT);
					super.renderPlayer.modelBipedMain.bipedLeftArm.postRender(0.0625F);
					GL11.glTranslatef(-0.33F, -0.14F, 0.0F);
					GL11.glScalef(1.05F, 1.05F, 1.05F);
					this.NilfgaardGlove.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(NilfgaardArmorT);
					super.renderPlayer.modelBipedMain.bipedRightArm.postRender(0.0625F);
					GL11.glTranslatef(0.33F, -0.14F, 0.0F);
					GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
					GL11.glScalef(1.05F, 1.05F, 1.05F);
					this.NilfgaardGlove.render(0.0625F);
					GL11.glPopMatrix();
				}

				if (currentArmor3Item == ArmorRegister.catSchoolGloves) {
					GL11.glPushMatrix();
					textureManager.bindTexture(CatSchoolArmorT);
					super.renderPlayer.modelBipedMain.bipedLeftArm.postRender(0.0625F);
					GL11.glTranslatef(-0.315F, -0.11F, 0.0F);
					GL11.glScalef(1.01F, 1.01F, 1.01F);
					this.CatSchoolGlove.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(CatSchoolArmorT);
					super.renderPlayer.modelBipedMain.bipedRightArm.postRender(0.0625F);
					GL11.glTranslatef(0.315F, -0.11F, 0.0F);
					GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
					GL11.glScalef(1.01F, 1.01F, 1.01F);
					this.CatSchoolGlove.render(0.0625F);
					GL11.glPopMatrix();
				}

				if (currentArmor3Item == ArmorRegister.griffinSchoolGloves) {
					GL11.glPushMatrix();
					textureManager.bindTexture(GriffinArmorT);
					super.renderPlayer.modelBipedMain.bipedLeftArm.postRender(0.0625F);
					GL11.glTranslatef(-0.33F, -0.19F, -0.0F);
					GL11.glScalef(1.05F, 1.05F, 1.05F);
					this.GriffinGlove.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(GriffinArmorT);
					super.renderPlayer.modelBipedMain.bipedRightArm.postRender(0.0625F);
					GL11.glTranslatef(0.35F, -0.19F, -0.0F);
					GL11.glScalef(-1.1F, 1.05F, -1.05F);
					this.GriffinGlove.render(0.0625F);
					GL11.glPopMatrix();
				}

				if (currentArmor3Item == ArmorRegister.viperSchoolGloves) {
					GL11.glPushMatrix();
					textureManager.bindTexture(ViperArmorHandT);
					super.renderPlayer.modelBipedMain.bipedLeftArm.postRender(0.0625F);
					GL11.glTranslatef(-0.1F, 0.55F, -0.13F);
					GL11.glScalef(0.58F, 0.25F, 0.55F);
					this.WitcherModelGlove.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(ViperArmorHandT);
					super.renderPlayer.modelBipedMain.bipedRightArm.postRender(0.0625F);
					GL11.glTranslatef(0.1F, 0.55F, 0.13F);
					GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
					GL11.glScalef(0.58F, 0.25F, 0.55F);
					this.WitcherModelGlove.render(0.0625F);
					GL11.glPopMatrix();
				}

				if (currentArmor3Item == ArmorRegister.hunterGloves) {
					GL11.glPushMatrix();
					textureManager.bindTexture(KaerMorhenArmorHandT);
					super.renderPlayer.modelBipedMain.bipedLeftArm.postRender(0.0625F);
					GL11.glTranslatef(-0.1F, 0.55F, -0.13F);
					GL11.glScalef(0.58F, 0.25F, 0.55F);
					this.WitcherModelGlove.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(KaerMorhenArmorHandT);
					super.renderPlayer.modelBipedMain.bipedRightArm.postRender(0.0625F);
					GL11.glTranslatef(0.1F, 0.55F, 0.13F);
					GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
					GL11.glScalef(0.58F, 0.25F, 0.55F);
					this.WitcherModelGlove.render(0.0625F);
					GL11.glPopMatrix();
				}
			}

			if (player.getCurrentArmor(0) != null) {
				Item currentArmor0Item = player.getCurrentArmor(0).getItem();

				if (currentArmor0Item == ArmorRegister.bearSchoolBoots2) {
					GL11.glPushMatrix();
					textureManager.bindTexture(BearSchoolBootT);
					super.renderPlayer.modelBipedMain.bipedLeftLeg.postRender(0.0625F);
					GL11.glTranslatef(-0.0F, -0.73F, -0.12F);
					GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
					GL11.glScalef(1.0F, 1.0F, 1.0F);
					this.WitcherModelBoot.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(BearSchoolBootT);
					super.renderPlayer.modelBipedMain.bipedRightLeg.postRender(0.0625F);
					GL11.glTranslatef(-0.0F, -0.73F, -0.12F);
					GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
					GL11.glScalef(1.0F, 1.0F, -1.0F);
					this.WitcherModelBoot.render(0.0625F);
					GL11.glPopMatrix();
				}

				if (currentArmor0Item == ArmorRegister.bearSchoolBoots) {
					GL11.glPushMatrix();
					textureManager.bindTexture(BearSchoolBootT);
					super.renderPlayer.modelBipedMain.bipedLeftLeg.postRender(0.0625F);
					GL11.glTranslatef(-0.0F, -0.73F, -0.12F);
					GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
					GL11.glScalef(1.0F, 1.0F, 1.0F);
					this.WitcherModelBoot.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(BearSchoolBootT);
					super.renderPlayer.modelBipedMain.bipedRightLeg.postRender(0.0625F);
					GL11.glTranslatef(-0.0F, -0.73F, -0.12F);
					GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
					GL11.glScalef(1.0F, 1.0F, -1.0F);
					this.WitcherModelBoot.render(0.0625F);
					GL11.glPopMatrix();
				}

				if (currentArmor0Item == ArmorRegister.wolfSchoolBoots) {
					GL11.glPushMatrix();
					textureManager.bindTexture(WolfBootT);
					super.renderPlayer.modelBipedMain.bipedLeftLeg.postRender(0.0625F);
					GL11.glTranslatef(-0.0F, -0.73F, -0.12F);
					GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
					GL11.glScalef(1.0F, 1.0F, 1.0F);
					this.WitcherModelBoot.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(WolfBootT);
					super.renderPlayer.modelBipedMain.bipedRightLeg.postRender(0.0625F);
					GL11.glTranslatef(-0.0F, -0.73F, -0.12F);
					GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
					GL11.glScalef(1.0F, 1.0F, -1.0F);
					this.WitcherModelBoot.render(0.0625F);
					GL11.glPopMatrix();
				}

				if (currentArmor0Item == ArmorRegister.catSchoolBoots) {
					GL11.glPushMatrix();
					textureManager.bindTexture(CatSchoolArmorT);
					super.renderPlayer.modelBipedMain.bipedLeftLeg.postRender(0.0625F);
					GL11.glTranslatef(-0.094F, -0.73F, -0.02F);
					GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
					GL11.glScalef(1.01F, 1.01F, 1.01F);
					this.CatSchoolBoot.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(CatSchoolArmorT);
					super.renderPlayer.modelBipedMain.bipedRightLeg.postRender(0.0625F);
					GL11.glTranslatef(0.094F, -0.73F, -0.02F);
					GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
					GL11.glScalef(1.01F, 1.01F, -1.01F);
					this.CatSchoolBoot.render(0.0625F);
					GL11.glPopMatrix();
				}

				if (currentArmor0Item == ArmorRegister.temerBoots) {
					GL11.glPushMatrix();
					textureManager.bindTexture(NilfgaardArmorBootT);
					super.renderPlayer.modelBipedMain.bipedLeftLeg.postRender(0.0625F);
					GL11.glTranslatef(-0.0F, -0.68F, -0.12F);
					GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
					GL11.glScalef(1.0F, 1.0F, 1.0F);
					this.WitcherModelBoot.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(NilfgaardArmorBootT);
					super.renderPlayer.modelBipedMain.bipedRightLeg.postRender(0.0625F);
					GL11.glTranslatef(-0.0F, -0.68F, -0.12F);
					GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
					GL11.glScalef(1.0F, 1.0F, -1.0F);
					this.WitcherModelBoot.render(0.0625F);
					GL11.glPopMatrix();
				}

				if (currentArmor0Item == ArmorRegister.griffinSchoolBoots) {
					GL11.glPushMatrix();
					textureManager.bindTexture(GriffinBootsT);
					super.renderPlayer.modelBipedMain.bipedRightLeg.postRender(0.0625F);
					GL11.glTranslatef(-0.0F, -0.69F, -0.12F);
					GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
					GL11.glScalef(1.0F, 1.0F, -1.0F);
					this.WitcherModelBoot.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(GriffinBootsT);
					super.renderPlayer.modelBipedMain.bipedLeftLeg.postRender(0.0625F);
					GL11.glTranslatef(-0.0F, -0.69F, -0.12F);
					GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
					GL11.glScalef(1.0F, 1.0F, 1.0F);
					this.WitcherModelBoot.render(0.0625F);
					GL11.glPopMatrix();
				}

				if (currentArmor0Item == ArmorRegister.hunterBoots) {
					GL11.glPushMatrix();
					textureManager.bindTexture(KaerMorhenArmorBootsT);
					super.renderPlayer.modelBipedMain.bipedLeftLeg.postRender(0.0625F);
					GL11.glTranslatef(-0.0F, -0.73F, -0.12F);
					GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
					GL11.glScalef(1.0F, 1.0F, 1.0F);
					this.WitcherModelBoot.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(KaerMorhenArmorBootsT);
					super.renderPlayer.modelBipedMain.bipedRightLeg.postRender(0.0625F);
					GL11.glTranslatef(-0.0F, -0.73F, -0.12F);
					GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
					GL11.glScalef(1.0F, 1.0F, -1.0F);
					this.WitcherModelBoot.render(0.0625F);
					GL11.glPopMatrix();
				}

				if (currentArmor0Item == ArmorRegister.viperSchoolBoots) {
					GL11.glPushMatrix();
					textureManager.bindTexture(ViperArmorBootsT);
					super.renderPlayer.modelBipedMain.bipedLeftLeg.postRender(0.0625F);
					GL11.glTranslatef(-0.0F, -0.73F, -0.12F);
					GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
					GL11.glScalef(1.0F, 1.0F, 1.0F);
					this.WitcherModelBoot.render(0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					textureManager.bindTexture(ViperArmorBootsT);
					super.renderPlayer.modelBipedMain.bipedRightLeg.postRender(0.0625F);
					GL11.glTranslatef(-0.0F, -0.73F, -0.12F);
					GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
					GL11.glScalef(1.0F, 1.0F, -1.0F);
					this.WitcherModelBoot.render(0.0625F);
					GL11.glPopMatrix();
				}
			}

			if (player.isPotionActive(Potion.resistance.getId())) {
				GL11.glPushMatrix();
				super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
				if (r < 361.0F) {
					r += 9.0F;
				} else {
					r = 0.0F;
				}

				GL11.glRotatef(this.r, 0.0F, 1.0F, 0.0F);
				GL11.glTranslatef(-0.5F, 0.5F, 0.0F);
				GL11.glScalef(1.0F, 1.0F, 1.0F);
				textureManager.bindTexture(textureKven);
				this.kven.render(0.0625F);
				GL11.glPopMatrix();
			}
		}
	}
}
