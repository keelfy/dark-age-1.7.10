/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.client.render;

import org.lwjgl.opengl.GL11;

import api.player.render.RenderPlayerAPI;
import api.player.render.RenderPlayerBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkcore.common.player.DADataManager;
import keelfy.darkdata.client.DAResources;
import keelfy.darkdata.client.DAResources.Model;
import keelfy.darkdata.client.DAResources.Texture;
import keelfy.darkdata.client.models.armor.BelhavenaBrigantine.BelhavenaBrigantineBody;
import keelfy.darkdata.client.models.armor.BelhavenaBrigantine.BelhavenaBrigantineHand;
import keelfy.darkdata.client.models.armor.BelhavenaBrigantine.BelhavenaBrigantineLeg;
import keelfy.darkdata.client.models.armor.HindarsfjallHeavyArmor.HindarsfjallHeavyArmorBody;
import keelfy.darkdata.client.models.armor.HindarsfjallHeavyArmor.HindarsfjallHeavyArmorHand;
import keelfy.darkdata.client.models.armor.HindarsfjallHeavyArmor.HindarsfjallHeavyArmorLeg;
import keelfy.darkdata.client.models.armor.RedanianHalebardist.RedanianHalebardistAddon;
import keelfy.darkdata.client.models.armor.RedanianHalebardist.RedanianHalebardistArmor;
import keelfy.darkdata.client.models.armor.RedanianHalebardist.RedanianHalebardistLeg;
import keelfy.darkdata.client.models.armor.Shirt.ShirtAddon;
import keelfy.darkdata.client.models.armor.Shirt.ShirtHand;
import keelfy.darkdata.client.models.armor.Shirt.ShirtMody;
import keelfy.darkdata.client.models.armor.boots.ModelWitcherBoots;
import keelfy.darkdata.client.models.armor.chestplate.ModelSkirt;
import keelfy.darkdata.client.models.armor.cidaris.CidarisLeg;
import keelfy.darkdata.client.models.armor.kaermorhen.KaerMorhenArmor;
import keelfy.darkdata.client.models.armor.kaermorhen.KaerMorhenHand;
import keelfy.darkdata.client.models.armor.kaermorhen.KaerMorhenHandAddon;
import keelfy.darkdata.client.models.armor.magdeithra.MagDeithraArmor;
import keelfy.darkdata.client.models.armor.magdeithra.MagDeithraHand;
import keelfy.darkdata.client.models.armor.magdeithra.MagDeithraLeftLeg;
import keelfy.darkdata.client.models.armor.magdeithra.MagDeithraLegAddon;
import keelfy.darkdata.client.models.armor.magdeithra.MagDeithraRightLeg;
import keelfy.darkdata.client.models.armor.nilfgaard.NilfgaardAddon;
import keelfy.darkdata.client.models.armor.nilfgaard.NilfgaardArmor;
import keelfy.darkdata.client.models.armor.nilfgaard.NilfgaardGlove;
import keelfy.darkdata.client.models.armor.nilfgaard.NilfgaardHead;
import keelfy.darkdata.client.models.armor.nilfgaard.NilfgaardLeg;
import keelfy.darkdata.client.models.armor.pants.ModelWitcherPants;
import keelfy.darkdata.client.models.armor.pants.WitcherLegs;
import keelfy.darkdata.client.models.armor.schoolBear.BearSchoolArmor;
import keelfy.darkdata.client.models.armor.schoolBear.BearSchoolHand;
import keelfy.darkdata.client.models.armor.schoolCat.CatSchoolArm;
import keelfy.darkdata.client.models.armor.schoolCat.CatSchoolArmor;
import keelfy.darkdata.client.models.armor.schoolCat.CatSchoolBoot;
import keelfy.darkdata.client.models.armor.schoolCat.CatSchoolGlove;
import keelfy.darkdata.client.models.armor.schoolCat.CatSchoolLeg;
import keelfy.darkdata.client.models.armor.schoolGriffin.GriffinArmor;
import keelfy.darkdata.client.models.armor.schoolGriffin.GriffinGlove;
import keelfy.darkdata.client.models.armor.schoolGriffin.GriffinHand;
import keelfy.darkdata.client.models.armor.schoolGriffin.GriffinLeg;
import keelfy.darkdata.client.models.armor.schoolWolf.WolfArmor;
import keelfy.darkdata.client.models.armor.schoolWolf.WolfGlove;
import keelfy.darkdata.client.models.armor.schoolWolf.WolfHand;
import keelfy.darkdata.client.models.armor.schoolWolf.WolfLeg;
import keelfy.darkdata.client.models.armor.single.WitcherLuriskyPants;
import keelfy.darkdata.client.models.armor.single.WitcherPathfinderLegs;
import keelfy.darkdata.client.models.armor.single.WitcherTopornikPants;
import keelfy.darkdata.client.models.armor.talgar.TalgarBody;
import keelfy.darkdata.client.models.armor.talgar.TalgarHand;
import keelfy.darkdata.client.models.armor.talgar.TalgarLeg;
import keelfy.darkdata.client.models.armor.talgar.TalgarSign;
import keelfy.darkdata.client.models.armor.witchunter.WitchunterArmorBody;
import keelfy.darkdata.client.models.armor.witchunter.WitchunterArmorHand;
import keelfy.darkdata.client.models.armor.witchunter.WitchunterArmorLeg;
import keelfy.darkdata.client.models.entity.player.ModelGlove;
import keelfy.darkdata.client.models.entity.sign.ModelSignKven;
import keelfy.darkdata.constants.EnumModelPath;
import keelfy.darkdata.constants.EnumTexturePath;
import keelfy.darkdata.handlers.registerers.ArmorRegister;
import keelfy.darkdata.items.Armor;
import keelfyutils.KUtils;
import keelfyutils.client.DisplayListWrapper;
import keelfyutils.client.KGL;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import noppes.npcs.entity.EntityCustomNpc;

/**
 * @author keelfy
 */
@SideOnly(Side.CLIENT)
public final class DARenderArmor extends RenderPlayerBase {

	public static final Minecraft MC = KGL.mc();

	public static final ModelSignKven kven = new ModelSignKven();
	public static final ResourceLocation textureKven = Texture.get(EnumTexturePath.Particle, "kven");

	public static final TalgarSign TalgarSign = new TalgarSign();

	// Pants
	public static final ModelWitcherPants WitcherLeg = new ModelWitcherPants();
	public static final WitcherLegs WitcherLegs = new WitcherLegs();

	// Boots
	public static final ModelWitcherBoots WitcherModelBoot = new ModelWitcherBoots();

	// Chestplate
	public static final ModelSkirt ModelSkirt = new ModelSkirt();

	public static final TalgarBody TalgarBody = new TalgarBody();
	public static final TalgarLeg TalgarLeg = new TalgarLeg();
	public static final TalgarHand TalgarHand = new TalgarHand();
	public static final ResourceLocation TalgarT = Texture.get(EnumTexturePath.Armor, "Talgar/Talgar");
	public static final ResourceLocation TalgarSignT = Texture.get(EnumTexturePath.Armor, "Talgar/TalgarSign");
	public static final WitcherTopornikPants WitcherTopornikPants = new WitcherTopornikPants();
	public static final ResourceLocation WitcherTopornikPantsT = Texture.get(EnumTexturePath.Armor,
			"Pants/TopornikPants");
	public static final WitcherPathfinderLegs WitcherPathfinderLegs = new WitcherPathfinderLegs();
	public static final ResourceLocation WitcherPathfinderPantsT = Texture.get(EnumTexturePath.Armor,
			"Pants/PathfinderLegs");
	public static final WitcherLuriskyPants WitcherLuriskyPants = new WitcherLuriskyPants();
	public static final ResourceLocation WitcherLuriskyPantsT = Texture.get(EnumTexturePath.Armor,
			"Pants/LuriskyPants");
	public static final ResourceLocation CidarisCombezT = Texture.get(EnumTexturePath.Armor, "Cidaris/CidarisCombez");
	public static final BelhavenaBrigantineBody BelhavenaBrigantineBody = new BelhavenaBrigantineBody();
	public static final BelhavenaBrigantineHand BelhavenaBrigantineHand = new BelhavenaBrigantineHand();
	public static final BelhavenaBrigantineLeg BelhavenaBrigantineLeg = new BelhavenaBrigantineLeg();
	public static final ResourceLocation BelhavenaBrigantineT = Texture.get(EnumTexturePath.Armor,
			"BelhavenaBrigantine/BelhavenaBrigantine");
	public static final CidarisLeg CidarisLeg = new CidarisLeg();
	public static final ResourceLocation CidarisT = Texture.get(EnumTexturePath.Armor, "Cidaris/Cidaris");
	public static final WitchunterArmorBody WitchunterArmorBody = new WitchunterArmorBody();
	public static final WitchunterArmorHand WitchunterArmorHand = new WitchunterArmorHand();
	public static final WitchunterArmorLeg WitchunterArmorLeg = new WitchunterArmorLeg();
	public static final ResourceLocation WitchunterArmorT = Texture.get(EnumTexturePath.Armor,
			"WitchunterArmor/WitchunterArmorT");
	public static final HindarsfjallHeavyArmorBody HindarsfjallHeavyArmorBody = new HindarsfjallHeavyArmorBody();
	public static final HindarsfjallHeavyArmorHand HindarsfjallHeavyArmorHand = new HindarsfjallHeavyArmorHand();
	public static final HindarsfjallHeavyArmorLeg HindarsfjallHeavyArmorLeg = new HindarsfjallHeavyArmorLeg();
	public static final ResourceLocation HindarsfjallHeavyArmorT = Texture.get(EnumTexturePath.Armor,
			"HindarsfjallHeavyArmor/HindarsfjallHeavyArmor");
	public static final BearSchoolHand BearSchoolHand = new BearSchoolHand();
	public static final BearSchoolArmor BearSchoolArmor = new BearSchoolArmor();
	public static final ResourceLocation BearSchoolT = Texture.get(EnumTexturePath.Armor, "bearSchool/Armor");
	public static final ResourceLocation BearSchoolBootT = Texture.get(EnumTexturePath.Armor, "bearSchool/Boot");
	public static final ResourceLocation BearSchoolSkirtT = Texture.get(EnumTexturePath.Armor, "bearSchool/Skirt");
	public static final ResourceLocation BearSchoolT2 = Texture.get(EnumTexturePath.Armor, "bearSchool/2/Armor");
	public static final ResourceLocation BearSchoolSkirtT2 = Texture.get(EnumTexturePath.Armor, "bearSchool/2/Skirt");
	public static final CatSchoolArmor CatSchoolArmor = new CatSchoolArmor();
	public static final CatSchoolLeg CatSchoolLeg = new CatSchoolLeg();
	public static final CatSchoolGlove CatSchoolGlove = new CatSchoolGlove();
	public static final CatSchoolArm CatSchoolSleeve = new CatSchoolArm();
	public static final CatSchoolBoot CatSchoolBoot = new CatSchoolBoot();
	public static final ResourceLocation CatPantsT = Texture.get(EnumTexturePath.Armor, "catSchool/Pants");
	public static final ResourceLocation CatSchoolArmorT = Texture.get(EnumTexturePath.Armor, "catSchool/Armor");
	public static final GriffinArmor GriffinArmor = new GriffinArmor();
	public static final GriffinHand GriffinHand = new GriffinHand();
	public static final GriffinLeg GriffinLeg = new GriffinLeg();
	public static final GriffinGlove GriffinGlove = new GriffinGlove();
	public static final ResourceLocation GriffinArmorT = Texture.get(EnumTexturePath.Armor,
			"griffinSchool/GriffinArmor");
	public static final ResourceLocation GriffinPantsT = Texture.get(EnumTexturePath.Armor, "griffinSchool/Pants");
	public static final ResourceLocation GriffinBootsT = Texture.get(EnumTexturePath.Armor, "griffinSchool/Boot");
	public static final KaerMorhenArmor KaerMorhenArmor = new KaerMorhenArmor();
	public static final KaerMorhenHand KaerMorhenHand = new KaerMorhenHand();
	public static final KaerMorhenHandAddon KaerMorhenHandAddon = new KaerMorhenHandAddon();
	public static final ModelGlove WitcherModelGlove = new ModelGlove();
	public static final ResourceLocation KaerMorhenArmorT = Texture.get(EnumTexturePath.Armor, "KaerMorhen/Armor");
	public static final ResourceLocation KaerMorhenArmorHandT = Texture.get(EnumTexturePath.Armor, "KaerMorhen/Hands");
	public static final ResourceLocation KaerMorhenArmorPantsT = Texture.get(EnumTexturePath.Armor, "KaerMorhen/Pants");
	public static final ResourceLocation KaerMorhenArmorBootsT = Texture.get(EnumTexturePath.Armor, "KaerMorhen/Boots");
	public static final WolfArmor WolfArmor = new WolfArmor();
	public static final WolfHand WolfHand = new WolfHand();
	public static final WolfGlove WolfGlove = new WolfGlove();
	public static final WolfLeg WolfLeg = new WolfLeg();
	public static final ResourceLocation WolfT = Texture.get(EnumTexturePath.Armor, "wolfSchool/WolfSchool");
	public static final ResourceLocation WolfBootT = Texture.get(EnumTexturePath.Armor, "wolfSchool/WolfSchoolBoot");
	public static final RedanianHalebardistArmor RedanianHalebardistArmor = new RedanianHalebardistArmor();
	public static final RedanianHalebardistLeg RedanianHalebardistLeg = new RedanianHalebardistLeg();
	public static final RedanianHalebardistAddon RedanianHalebardistAddon = new RedanianHalebardistAddon();
	public static final ResourceLocation RedanianHalebardistArmorT = Texture.get(EnumTexturePath.Armor,
			"RedanianHalebardistArmor/Armor");
	public static final NilfgaardArmor NilfgaardArmor = new NilfgaardArmor();
	public static final NilfgaardAddon NilfgaardAddon = new NilfgaardAddon();
	public static final NilfgaardHead NilfgaardHead = new NilfgaardHead();
	public static final NilfgaardGlove NilfgaardGlove = new NilfgaardGlove();
	public static final NilfgaardLeg NilfgaardLeg = new NilfgaardLeg();
	public static final ResourceLocation NilfgaardArmorT = Texture.get(EnumTexturePath.Armor, "Nilfgaard/Armor");
	public static final ResourceLocation NilfgaardArmorLegT = Texture.get(EnumTexturePath.Armor, "Nilfgaard/Leg");
	public static final ResourceLocation NilfgaardArmorBootT = Texture.get(EnumTexturePath.Armor, "Nilfgaard/Boot");
	public static final MagDeithraArmor MagDeithraArmor = new MagDeithraArmor();
	public static final MagDeithraHand MagDeithraHand = new MagDeithraHand();
	public static final MagDeithraLeftLeg MagDeithraLeftLeg = new MagDeithraLeftLeg();
	public static final MagDeithraRightLeg MagDeithraRightLeg = new MagDeithraRightLeg();
	public static final MagDeithraLegAddon MagDeithraLegAddon = new MagDeithraLegAddon();
	public static final ResourceLocation MagDeithraArmorT = Texture.get(EnumTexturePath.Armor, "MagDeithraArmor/Armor");
	public static final ResourceLocation ViperArmorT = Texture.get(EnumTexturePath.Armor, "viperSchool/Armor");
	public static final ResourceLocation ViperArmorHandT = Texture.get(EnumTexturePath.Armor, "viperSchool/Hands");
	public static final ResourceLocation ViperArmorPantsT = Texture.get(EnumTexturePath.Armor, "viperSchool/Pants");
	public static final ResourceLocation ViperArmorBootsT = Texture.get(EnumTexturePath.Armor, "viperSchool/Boots");
	public static final ShirtMody ShirtBody = new ShirtMody();
	public static final ShirtHand ShirtHand = new ShirtHand();
	public static final ShirtAddon ShirtAddon = new ShirtAddon();
	public static final ResourceLocation ShirtT = Texture.get(EnumTexturePath.Armor, "Shirt/WitcherShirt");
	public static final DisplayListWrapper CatMedialion = Model.getWithDL(EnumModelPath.Medalion, "CatMedallion");
	public static final ResourceLocation CatMedialionT = Texture.get(EnumTexturePath.Medalion, "CatMedallion");
	public static final DisplayListWrapper WolfMedialion = Model.getWithDL(EnumModelPath.Medalion, "WolfMedallion");
	public static final ResourceLocation WolfMedialionT = Texture.get(EnumTexturePath.Medalion, "WolfMedallion");
	public static final DisplayListWrapper GriffinMedialion = Model.getWithDL(EnumModelPath.Medalion,
			"GriffinMedallion");
	public static final ResourceLocation GriffinMedialionT = Texture.get(EnumTexturePath.Armor,
			"school/Griffin/1/texlow");
	public static final DisplayListWrapper BearMedialion = Model.getWithDL(EnumModelPath.Medalion, "BearMedallion");
	public static final ResourceLocation BearMedialionT = Texture.get(EnumTexturePath.Armor, "school/Bear/1/texlow");

	public DARenderArmor(final RenderPlayerAPI renderPlayerAPI) {
		super(renderPlayerAPI);
	}

	public static final void register() {
		if (KUtils.PROTECT_CLIENT) {
			RenderPlayerAPI.register("DARenderPlayer", DARenderArmor.class);
		}
	}

	@Override
	public void loadTextureOfEntity(final Entity entity) {
		if (KUtils.PROTECT_CLIENT) {
			if (entity instanceof EntityPlayer) {
				this.loadTextureOfPlayer((EntityPlayer) entity);
			}
		}
	}

	public void loadTextureOfPlayer(final EntityPlayer player) {
		if (KUtils.PROTECT_CLIENT) {
			if (DADataManager.getPlayer(player).playerClass.isWitcher()) {
				KGL.bindTexture(DAResources.Texture_Skin_Witcher);
			} else {
				super.loadTextureOfEntity(player);
			}
		}
	}

	@Override
	public void renderFirstPersonArm(final EntityPlayer player) {
		if (DADataManager.getPlayer(player).playerClass.isWitcher()) {
			KGL.bindTexture(DAResources.Texture_Skin_Witcher);
		}
		super.renderFirstPersonArm(player);
	}

	private float r;

	@Override
	public void renderSpecials(final AbstractClientPlayer player, final float par2) {
		if (KUtils.PROTECT_CLIENT) {
			super.renderSpecials(player, par2);

			renderAllArmor(player, super.renderPlayer.modelBipedMain);

			// if (player.isPotionActive(Potion.resistance)) {
			// GL11.glPushMatrix();
			// postBody(super.renderPlayer.modelBipedMain);
			//
			// if (r < 361.0F) {
			// r += 9.0F;
			// } else {
			// r = 0.0F;
			// }
			//
			// KGL.bindTexture(textureKven);
			// GL11.glRotatef(r, 0.0F, 1.0F, 0.0F);
			// GL11.glTranslatef(-0.5F, 0.5F, 0.0F);
			// kven.renderAll();
			// GL11.glPopMatrix();
			// }
		}
	}

	public static final void renderAllArmor(final EntityLivingBase living, final ModelBiped mbm) {
		if (living instanceof EntityPlayer) {
			final EntityPlayer player = (EntityPlayer) living;

			final ItemStack boots = player.getCurrentArmor(0);
			if (boots != null && boots.getItem() instanceof Armor) {
				renderBoots(boots.getItem(), mbm);
			}

			final ItemStack pants = player.getCurrentArmor(1);
			if (pants != null && pants.getItem() instanceof Armor) {
				renderPants(pants.getItem(), mbm);
			}

			final ItemStack plate = player.getCurrentArmor(2);
			if (plate != null && plate.getItem() instanceof Armor) {
				renderChestplate(plate.getItem(), mbm);
			}

			final ItemStack gloves = player.getCurrentArmor(3);
			if (gloves != null && gloves.getItem() instanceof Armor) {
				renderGloves(gloves.getItem(), mbm);
			}

		} else if (living instanceof EntityCustomNpc) {
			final EntityCustomNpc npc = (EntityCustomNpc) living;

			final ItemStack plate = npc.inventory.armorItemInSlot(0);
			if (plate != null && plate.getItem() instanceof Armor) {
				renderChestplate(plate.getItem(), mbm);
			}

			final ItemStack gloves = npc.inventory.armorItemInSlot(1);
			if (gloves != null && gloves.getItem() instanceof Armor) {
				renderGloves(gloves.getItem(), mbm);
			}

			final ItemStack pants = npc.inventory.armorItemInSlot(2);
			if (pants != null && pants.getItem() instanceof Armor) {
				renderPants(pants.getItem(), mbm);
			}

			final ItemStack boots = npc.inventory.armorItemInSlot(3);
			if (boots != null && boots.getItem() instanceof Armor) {
				renderBoots(boots.getItem(), mbm);
			}
		}
	}

	private static final void renderChestplate(final Item current, final ModelBiped mbm) {
		if (current == ArmorRegister.cidarianCavalrymanChestplate) {
			GL11.glPushMatrix();
			KGL.bindTexture(MC, CidarisT);
			GL11.glPushMatrix();
			postBody(mbm);
			GL11.glTranslatef(0.0F, -0.05F, 0.0F);
			GL11.glScalef(1.1F, 1.1F, 1.1F);
			RedanianHalebardistArmor.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postLeftArm(mbm);
			GL11.glTranslatef(-0.33F, -0.14F, 0.0F);
			GL11.glScalef(1.05F, 1.05F, 1.05F);
			RedanianHalebardistAddon.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postRightArm(mbm);
			GL11.glTranslatef(0.33F, -0.14F, 0.0F);
			GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
			GL11.glScalef(1.05F, 1.05F, 1.05F);
			RedanianHalebardistAddon.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postRightLeg(mbm);
			GL11.glTranslatef(0.15F, -0.95F, 0.0F);
			GL11.glScalef(-1.15F, 1.15F, 1.15F);
			CidarisLeg.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postLeftLeg(mbm);
			GL11.glTranslatef(-0.15F, -0.95F, 0.0F);
			GL11.glScalef(1.15F, 1.15F, 1.15F);
			CidarisLeg.renderAll();
			GL11.glPopMatrix();
			GL11.glPopMatrix();
		} else if (current == ArmorRegister.cidarianChestplate) {
			GL11.glPushMatrix();
			KGL.bindTexture(MC, CidarisCombezT);
			GL11.glPushMatrix();
			postBody(mbm);
			GL11.glTranslatef(0.0F, -0.05F, 0.0F);
			GL11.glScalef(1.1F, 1.1F, 1.1F);
			BelhavenaBrigantineBody.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postLeftArm(mbm);
			GL11.glTranslatef(-0.33F, -0.14F, 0.0F);
			GL11.glScalef(1.05F, 1.05F, 1.05F);
			BelhavenaBrigantineHand.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postRightArm(mbm);
			GL11.glTranslatef(0.33F, -0.14F, 0.0F);
			GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
			GL11.glScalef(1.05F, 1.05F, 1.05F);
			BelhavenaBrigantineHand.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postRightLeg(mbm);
			GL11.glTranslatef(0.15F, -0.89F, 0.0F);
			GL11.glScalef(-1.15F, 1.15F, 1.15F);
			BelhavenaBrigantineLeg.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postLeftLeg(mbm);
			GL11.glTranslatef(-0.15F, -0.89F, 0.0F);
			GL11.glScalef(1.15F, 1.15F, 1.15F);
			BelhavenaBrigantineLeg.renderAll();
			GL11.glPopMatrix();
			GL11.glPopMatrix();
		} else if (current == ArmorRegister.cloth) {
			GL11.glPushMatrix();
			KGL.bindTexture(MC, ShirtT);
			GL11.glPushMatrix();
			postBody(mbm);
			GL11.glTranslatef(0.0F, -0.05F, 0.0F);
			GL11.glScalef(1.0F, 1.07F, 1.0F);
			ShirtBody.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postBody(mbm);
			GL11.glTranslatef(0.0F, -0.05F, 0.0F);
			GL11.glScalef(0.96F, 0.96F, 0.96F);
			ShirtAddon.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postLeftArm(mbm);
			GL11.glTranslatef(-0.315F, -0.14F, 0.0F);
			GL11.glScalef(1.0F, 1.0F, 1.0F);
			ShirtHand.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postRightArm(mbm);
			GL11.glTranslatef(0.315F, -0.14F, 0.0F);
			GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
			GL11.glScalef(1.0F, 1.0F, 1.0F);
			ShirtHand.renderAll();
			GL11.glPopMatrix();
			GL11.glPopMatrix();
		} else if (current == ArmorRegister.infantryChestplate) {
			GL11.glPushMatrix();
			KGL.bindTexture(MC, BelhavenaBrigantineT);
			GL11.glPushMatrix();
			postBody(mbm);
			GL11.glTranslatef(0.0F, -0.05F, 0.0F);
			GL11.glScalef(1.1F, 1.1F, 1.1F);
			BelhavenaBrigantineBody.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postLeftArm(mbm);
			GL11.glTranslatef(-0.33F, -0.14F, 0.0F);
			GL11.glScalef(1.05F, 1.05F, 1.05F);
			BelhavenaBrigantineHand.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postRightArm(mbm);
			GL11.glTranslatef(0.33F, -0.14F, 0.0F);
			GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
			GL11.glScalef(1.05F, 1.05F, 1.05F);
			BelhavenaBrigantineHand.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postRightLeg(mbm);
			GL11.glTranslatef(0.15F, -0.89F, 0.0F);
			GL11.glScalef(-1.15F, 1.15F, 1.15F);
			BelhavenaBrigantineLeg.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postLeftLeg(mbm);
			GL11.glTranslatef(-0.15F, -0.89F, 0.0F);
			GL11.glScalef(1.15F, 1.15F, 1.15F);
			BelhavenaBrigantineLeg.renderAll();
			GL11.glPopMatrix();
			GL11.glPopMatrix();
		} else if (current == ArmorRegister.alebardistChestplate) {
			GL11.glPushMatrix();
			KGL.bindTexture(MC, RedanianHalebardistArmorT);
			GL11.glPushMatrix();
			postBody(mbm);
			GL11.glTranslatef(0.0F, -0.05F, 0.0F);
			GL11.glScalef(1.1F, 1.1F, 1.1F);
			RedanianHalebardistArmor.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postLeftArm(mbm);
			GL11.glTranslatef(-0.33F, -0.14F, 0.0F);
			GL11.glScalef(1.05F, 1.05F, 1.05F);
			RedanianHalebardistAddon.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postRightArm(mbm);
			GL11.glTranslatef(0.33F, -0.14F, 0.0F);
			GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
			GL11.glScalef(1.05F, 1.05F, 1.05F);
			RedanianHalebardistAddon.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postRightLeg(mbm);
			GL11.glTranslatef(0.15F, -0.95F, 0.0F);
			GL11.glScalef(-1.15F, 1.15F, 1.15F);
			RedanianHalebardistLeg.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postLeftLeg(mbm);
			GL11.glTranslatef(-0.15F, -0.95F, 0.0F);
			GL11.glScalef(1.15F, 1.15F, 1.15F);
			RedanianHalebardistLeg.renderAll();
			GL11.glPopMatrix();
			GL11.glPopMatrix();
		} else if (current == ArmorRegister.hindarsfjallChestplate) {
			GL11.glPushMatrix();
			KGL.bindTexture(MC, HindarsfjallHeavyArmorT);
			GL11.glPushMatrix();
			postBody(mbm);
			GL11.glTranslatef(0.0F, -0.05F, 0.0F);
			GL11.glScalef(1.1F, 1.1F, 1.1F);
			HindarsfjallHeavyArmorBody.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postLeftArm(mbm);
			GL11.glTranslatef(-0.33F, -0.14F, 0.0F);
			GL11.glScalef(1.05F, 1.05F, 1.05F);
			HindarsfjallHeavyArmorHand.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postRightArm(mbm);
			GL11.glTranslatef(0.33F, -0.14F, 0.0F);
			GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
			GL11.glScalef(1.05F, 1.05F, 1.05F);
			HindarsfjallHeavyArmorHand.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postRightLeg(mbm);
			GL11.glTranslatef(0.13F, -0.76F, 0.0F);
			GL11.glScalef(-1.15F, 1.05F, 1.15F);
			HindarsfjallHeavyArmorLeg.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postLeftLeg(mbm);
			GL11.glTranslatef(-0.13F, -0.76F, 0.0F);
			GL11.glScalef(1.15F, 1.05F, 1.15F);
			HindarsfjallHeavyArmorLeg.renderAll();
			GL11.glPopMatrix();
			GL11.glPopMatrix();
		} else if (current == ArmorRegister.redanCavalrymanChestplate) {
			GL11.glPushMatrix();
			KGL.bindTexture(MC, WitchunterArmorT);
			GL11.glPushMatrix();
			postBody(mbm);
			GL11.glTranslatef(0.0F, -0.05F, 0.0F);
			GL11.glScalef(1.1F, 1.1F, 1.1F);
			WitchunterArmorBody.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postRightArm(mbm);
			GL11.glTranslatef(0.33F, -0.14F, 0.0F);
			GL11.glScalef(1.05F, 1.05F, 1.05F);
			WitchunterArmorHand.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postLeftArm(mbm);
			GL11.glTranslatef(0.46F, -0.14F, 0.0F);
			GL11.glScalef(1.05F, 1.05F, 1.05F);
			WitchunterArmorHand.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postLeftLeg(mbm);
			GL11.glTranslatef(-0.13F, -0.76F, 0.0F);
			GL11.glScalef(-1.15F, 1.05F, 1.15F);
			WitchunterArmorLeg.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postRightLeg(mbm);
			GL11.glTranslatef(0.13F, -0.76F, 0.0F);
			GL11.glScalef(1.15F, 1.05F, 1.15F);
			WitchunterArmorLeg.renderAll();
			GL11.glPopMatrix();
			GL11.glPopMatrix();
		} else if (current == ArmorRegister.ellanderChestplate) {
			GL11.glPushMatrix();
			KGL.bindTexture(MC, MagDeithraArmorT);
			GL11.glPushMatrix();
			postBody(mbm);
			GL11.glTranslatef(0.0F, -0.05F, 0.0F);
			GL11.glScalef(1.1F, 1.1F, 1.1F);
			MagDeithraArmor.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postLeftArm(mbm);
			GL11.glTranslatef(-0.33F, -0.14F, 0.0F);
			GL11.glScalef(1.05F, 1.05F, 1.05F);
			MagDeithraHand.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postRightArm(mbm);
			GL11.glTranslatef(0.33F, -0.14F, 0.0F);
			GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
			GL11.glScalef(1.05F, 1.05F, 1.05F);
			MagDeithraHand.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postRightLeg(mbm);
			GL11.glTranslatef(0.15F, -0.95F, 0.0F);
			GL11.glScalef(-1.15F, 1.15F, 1.15F);
			MagDeithraRightLeg.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postLeftLeg(mbm);
			GL11.glTranslatef(-0.13F, -0.95F, 0.0F);
			GL11.glScalef(1.15F, 1.15F, 1.15F);
			MagDeithraRightLeg.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postRightLeg(mbm);
			GL11.glTranslatef(0.15F, -0.9F, -0.0F);
			GL11.glScalef(1.4F, 1.3F, -1.4F);
			MagDeithraLegAddon.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postLeftLeg(mbm);
			GL11.glTranslatef(0.23F, -0.85F, -0.01F);
			GL11.glScalef(1.4F, 1.3F, -1.4F);
			MagDeithraLegAddon.renderAll();
			GL11.glPopMatrix();
			GL11.glPopMatrix();
		} else if (current == ArmorRegister.temerChestplate) {
			GL11.glPushMatrix();
			KGL.bindTexture(MC, NilfgaardArmorT);
			GL11.glPushMatrix();
			postBody(mbm);
			GL11.glTranslatef(0.0F, -0.05F, 0.0F);
			GL11.glScalef(1.1F, 1.1F, 1.1F);
			NilfgaardArmor.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postHead(mbm);
			GL11.glTranslatef(0.0F, -0.05F, 0.0F);
			GL11.glScalef(1.1F, 1.1F, 1.1F);
			NilfgaardHead.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postLeftArm(mbm);
			GL11.glTranslatef(-0.33F, -0.14F, 0.0F);
			GL11.glScalef(1.05F, 1.05F, 1.05F);
			NilfgaardAddon.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postRightArm(mbm);
			GL11.glTranslatef(0.33F, -0.14F, 0.0F);
			GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
			GL11.glScalef(1.05F, 1.05F, 1.05F);
			NilfgaardAddon.renderAll();
			GL11.glPopMatrix();
			GL11.glPopMatrix();
		} else if (current == ArmorRegister.bearSchoolChestplate) {
			GL11.glPushMatrix();
			KGL.bindTexture(MC, BearSchoolT);
			GL11.glPushMatrix();
			postBody(mbm);
			GL11.glTranslatef(0.0F, -0.05F, 0.0F);
			GL11.glScalef(1.05F, 1.05F, 1.05F);
			BearSchoolArmor.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postLeftArm(mbm);
			GL11.glTranslatef(-0.33F, -0.14F, 0.0F);
			GL11.glScalef(1.05F, 1.05F, 1.05F);
			BearSchoolHand.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postRightArm(mbm);
			GL11.glTranslatef(0.33F, -0.14F, 0.0F);
			GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
			GL11.glScalef(1.05F, 1.05F, 1.05F);
			BearSchoolHand.renderAll();
			GL11.glPopMatrix();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			KGL.bindTexture(MC, BearSchoolSkirtT);
			GL11.glPushMatrix();
			postRightLeg(mbm);
			GL11.glTranslatef(0.15F, -0.95F, 0.0F);
			GL11.glScalef(-1.25F, 1.25F, 1.25F);
			ModelSkirt.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postLeftLeg(mbm);
			GL11.glTranslatef(-0.15F, -0.95F, 0.0F);
			GL11.glScalef(1.25F, 1.25F, 1.25F);
			ModelSkirt.renderAll();
			GL11.glPopMatrix();
			GL11.glPopMatrix();
		} else if (current == ArmorRegister.bearSchoolChestplate2) {
			GL11.glPushMatrix();
			KGL.bindTexture(MC, BearSchoolT2);
			GL11.glPushMatrix();
			postBody(mbm);
			GL11.glTranslatef(0.0F, -0.05F, 0.0F);
			GL11.glScalef(1.05F, 1.05F, 1.05F);
			BearSchoolArmor.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postLeftArm(mbm);
			GL11.glTranslatef(-0.33F, -0.14F, 0.0F);
			GL11.glScalef(1.05F, 1.05F, 1.05F);
			BearSchoolHand.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postRightArm(mbm);
			GL11.glTranslatef(0.33F, -0.14F, 0.0F);
			GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
			GL11.glScalef(1.05F, 1.05F, 1.05F);
			BearSchoolHand.renderAll();
			GL11.glPopMatrix();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			KGL.bindTexture(MC, BearSchoolSkirtT2);
			GL11.glPushMatrix();
			postRightLeg(mbm);
			GL11.glTranslatef(0.15F, -0.95F, 0.0F);
			GL11.glScalef(-1.25F, 1.25F, 1.25F);
			ModelSkirt.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postLeftLeg(mbm);
			GL11.glTranslatef(-0.15F, -0.95F, 0.0F);
			GL11.glScalef(1.25F, 1.25F, 1.25F);
			ModelSkirt.renderAll();
			GL11.glPopMatrix();
			GL11.glPopMatrix();
		} else if (current == ArmorRegister.catSchoolChestplate) {
			GL11.glPushMatrix();
			KGL.bindTexture(MC, CatSchoolArmorT);
			GL11.glPushMatrix();
			postBody(mbm);
			GL11.glTranslatef(0.0F, -0.05F, 0.0F);
			GL11.glScalef(1.05F, 1.05F, 1.05F);
			CatSchoolArmor.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postLeftArm(mbm);
			GL11.glTranslatef(-0.33F, -0.14F, 0.0F);
			GL11.glScalef(1.05F, 1.05F, 1.05F);
			CatSchoolSleeve.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postRightArm(mbm);
			GL11.glTranslatef(0.33F, -0.14F, 0.0F);
			GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
			GL11.glScalef(1.05F, 1.05F, 1.05F);
			CatSchoolSleeve.renderAll();
			GL11.glPopMatrix();
			GL11.glPopMatrix();
		} else if (current == ArmorRegister.talgarChestplate) {
			GL11.glPushMatrix();
			KGL.bindTexture(MC, TalgarT);
			GL11.glPushMatrix();
			postBody(mbm);
			GL11.glTranslatef(0.0F, -0.05F, 0.0F);
			GL11.glScalef(1.05F, 1.05F, 1.05F);
			TalgarBody.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postLeftArm(mbm);
			GL11.glTranslatef(-0.33F, -0.14F, 0.0F);
			GL11.glScalef(1.05F, 1.05F, 1.05F);
			TalgarHand.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postRightArm(mbm);
			GL11.glTranslatef(0.33F, -0.14F, 0.0F);
			GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
			GL11.glScalef(1.05F, 1.05F, 1.05F);
			TalgarHand.renderAll();
			GL11.glPopMatrix();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			KGL.bindTexture(MC, TalgarSignT);
			postBody(mbm);
			GL11.glTranslatef(0.03F, 0.1F, -0.2F);
			GL11.glScalef(0.2F, 0.2F, 0.2F);
			TalgarSign.renderAll();
			GL11.glPopMatrix();
		} else if (current == ArmorRegister.wolfSchoolChestplate) {
			GL11.glPushMatrix();
			KGL.bindTexture(MC, WolfT);
			GL11.glPushMatrix();
			postBody(mbm);
			GL11.glTranslatef(0.0F, -0.05F, 0.0F);
			GL11.glScalef(1.05F, 1.05F, 1.05F);
			WolfArmor.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postLeftArm(mbm);
			GL11.glTranslatef(-0.33F, -0.14F, 0.0F);
			GL11.glScalef(1.05F, 1.05F, 1.05F);
			WolfHand.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postRightArm(mbm);
			GL11.glTranslatef(0.33F, -0.14F, 0.0F);
			GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
			GL11.glScalef(1.05F, 1.05F, 1.05F);
			WolfHand.renderAll();
			GL11.glPopMatrix();
			GL11.glPopMatrix();
		} else if (current == ArmorRegister.griffinSchoolChestplate) {
			GL11.glPushMatrix();
			KGL.bindTexture(MC, GriffinArmorT);
			GL11.glPushMatrix();
			postBody(mbm);
			GL11.glTranslatef(0.0F, -0.03F, 0.0F);
			GL11.glScalef(1.1F, 1.0F, 1.1F);
			GriffinArmor.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postLeftArm(mbm);
			GL11.glTranslatef(-0.33F, -0.19F, -0.0F);
			GL11.glScalef(1.05F, 1.05F, 1.05F);
			GriffinHand.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postRightArm(mbm);
			GL11.glTranslatef(0.35F, -0.19F, -0.0F);
			GL11.glScalef(-1.1F, 1.05F, -1.05F);
			GriffinHand.renderAll();
			GL11.glPopMatrix();
			GL11.glPopMatrix();
		} else if (current == ArmorRegister.viperSchoolChestplate) {
			GL11.glPushMatrix();
			KGL.bindTexture(MC, ViperArmorT);
			GL11.glPushMatrix();
			postBody(mbm);
			GL11.glTranslatef(0.0F, 0.0F, 0.0F);
			GL11.glScalef(1.1F, 1.1F, 1.1F);
			KaerMorhenArmor.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postLeftArm(mbm);
			GL11.glTranslatef(-0.3F, -0.12F, 0.0F);
			GL11.glScalef(1.0F, 1.0F, 0.9F);
			KaerMorhenHand.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postRightArm(mbm);
			GL11.glTranslatef(0.3F, -0.12F, 0.0F);
			GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
			GL11.glScalef(1.0F, 1.0F, 0.9F);
			KaerMorhenHand.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postLeftArm(mbm);
			GL11.glTranslatef(-0.27F, 0.12F, 0.0F);
			GL11.glScalef(0.9F, 0.9F, 0.8F);
			KaerMorhenHandAddon.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postRightArm(mbm);
			GL11.glTranslatef(0.27F, 0.12F, 0.0F);
			GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
			GL11.glScalef(0.9F, 0.9F, 0.8F);
			KaerMorhenHandAddon.renderAll();
			GL11.glPopMatrix();
			GL11.glPopMatrix();
		} else if (current == ArmorRegister.hunterChestplate) {
			GL11.glPushMatrix();
			KGL.bindTexture(MC, KaerMorhenArmorT);
			GL11.glPushMatrix();
			postBody(mbm);
			GL11.glTranslatef(0.0F, 0.0F, 0.0F);
			GL11.glScalef(1.1F, 1.1F, 1.1F);
			KaerMorhenArmor.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postLeftArm(mbm);
			GL11.glTranslatef(-0.3F, -0.12F, 0.0F);
			GL11.glScalef(1.0F, 1.0F, 0.9F);
			KaerMorhenHand.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postRightArm(mbm);
			GL11.glTranslatef(0.3F, -0.12F, 0.0F);
			GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
			GL11.glScalef(1.0F, 1.0F, 0.9F);
			KaerMorhenHand.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postLeftArm(mbm);
			GL11.glTranslatef(-0.27F, 0.12F, 0.0F);
			GL11.glScalef(0.9F, 0.9F, 0.9F);
			KaerMorhenHandAddon.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postRightArm(mbm);
			GL11.glTranslatef(0.27F, 0.12F, 0.0F);
			GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
			GL11.glScalef(0.9F, 0.9F, 0.9F);
			KaerMorhenHandAddon.renderAll();
			GL11.glPopMatrix();
			GL11.glPopMatrix();
		}
	}

	private static final void renderPants(final Item current, final ModelBiped mbm) {
		if (current == ArmorRegister.cidarianArmyPants) {
			GL11.glPushMatrix();
			KGL.bindTexture(MC, WitcherLuriskyPantsT);
			GL11.glPushMatrix();
			postLeftLeg(mbm);
			GL11.glTranslatef(-0.135F, -0.8F, 0.0F);
			GL11.glScalef(1.05F, 1.05F, 1.05F);
			WitcherLuriskyPants.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postRightLeg(mbm);
			GL11.glTranslatef(0.135F, -0.8F, 0.0F);
			GL11.glScalef(-1.05F, 1.05F, 1.05F);
			WitcherLuriskyPants.renderAll();
			GL11.glPopMatrix();
			GL11.glPopMatrix();
		} else if (current == ArmorRegister.talgarPants) {
			GL11.glPushMatrix();
			KGL.bindTexture(MC, TalgarT);
			GL11.glPushMatrix();
			postLeftLeg(mbm);
			GL11.glTranslatef(-0.135F, -0.8F, 0.0F);
			GL11.glScalef(1.05F, 1.05F, 1.05F);
			TalgarLeg.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postRightLeg(mbm);
			GL11.glTranslatef(0.135F, -0.8F, 0.0F);
			GL11.glScalef(-1.05F, 1.05F, 1.05F);
			TalgarLeg.renderAll();
			GL11.glPopMatrix();
			GL11.glPopMatrix();
		} else if (current == ArmorRegister.leatherPants) {
			GL11.glPushMatrix();
			KGL.bindTexture(MC, WitcherPathfinderPantsT);
			GL11.glPushMatrix();
			postLeftLeg(mbm);
			GL11.glTranslatef(-0.135F, -0.8F, 0.0F);
			GL11.glScalef(1.0F, 1.0F, 1.0F);
			WitcherPathfinderLegs.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postRightLeg(mbm);
			GL11.glTranslatef(0.135F, -0.8F, 0.0F);
			GL11.glScalef(-1.0F, 1.0F, -1.0F);
			WitcherPathfinderLegs.renderAll();
			GL11.glPopMatrix();
			GL11.glPopMatrix();
			;
		} else if (current == ArmorRegister.temerArmyPants) {
			GL11.glPushMatrix();
			KGL.bindTexture(MC, WitcherTopornikPantsT);
			GL11.glPushMatrix();
			postLeftLeg(mbm);
			GL11.glTranslatef(-0.15F, -0.8F, 0.0F);
			GL11.glScalef(1.05F, 1.05F, 1.05F);
			WitcherTopornikPants.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postRightLeg(mbm);
			GL11.glTranslatef(0.15F, -0.8F, 0.0F);
			GL11.glScalef(-1.05F, 1.05F, -1.05F);
			WitcherTopornikPants.renderAll();
			GL11.glPopMatrix();
			GL11.glPopMatrix();
		} else if (current == ArmorRegister.bearSchoolPants2) {
			GL11.glPushMatrix();
			KGL.bindTexture(MC, BearSchoolT);
			GL11.glPushMatrix();
			postRightLeg(mbm);
			GL11.glTranslatef(-0.15F, -0.85F, 0.0F);
			GL11.glScalef(1.15F, 1.1F, 1.15F);
			WitcherLeg.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postLeftLeg(mbm);
			GL11.glTranslatef(0.15F, -0.85F, 0.0F);
			GL11.glScalef(-1.15F, 1.1F, -1.15F);
			WitcherLeg.renderAll();
			GL11.glPopMatrix();
			GL11.glPopMatrix();
		} else if (current == ArmorRegister.bearSchoolPants) {
			GL11.glPushMatrix();
			KGL.bindTexture(MC, BearSchoolT);
			GL11.glPushMatrix();
			postRightLeg(mbm);
			GL11.glTranslatef(-0.15F, -0.85F, 0.0F);
			GL11.glScalef(1.15F, 1.1F, 1.15F);
			WitcherLeg.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postLeftLeg(mbm);
			GL11.glTranslatef(0.15F, -0.85F, 0.0F);
			GL11.glScalef(-1.15F, 1.1F, -1.15F);
			WitcherLeg.renderAll();
			GL11.glPopMatrix();
			GL11.glPopMatrix();
		} else if (current == ArmorRegister.wolfSchoolPants) {
			GL11.glPushMatrix();
			KGL.bindTexture(MC, WolfT);
			GL11.glPushMatrix();
			postRightLeg(mbm);
			GL11.glTranslatef(-0.15F, -0.85F, 0.0F);
			GL11.glScalef(1.15F, 1.1F, 1.15F);
			WitcherLeg.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postLeftLeg(mbm);
			GL11.glTranslatef(0.15F, -0.85F, 0.0F);
			GL11.glScalef(-1.15F, 1.1F, -1.15F);
			WitcherLeg.renderAll();
			GL11.glPopMatrix();
			GL11.glPopMatrix();
		} else if (current == ArmorRegister.temerPants) {
			GL11.glPushMatrix();
			KGL.bindTexture(MC, NilfgaardArmorT);
			GL11.glPushMatrix();
			postRightLeg(mbm);
			GL11.glTranslatef(-0.15F, -0.9F, 0.0F);
			GL11.glScalef(1.15F, 1.1F, 1.15F);
			NilfgaardLeg.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postLeftLeg(mbm);
			GL11.glTranslatef(0.15F, -0.9F, 0.0F);
			GL11.glScalef(-1.15F, 1.1F, 1.15F);
			NilfgaardLeg.renderAll();
			GL11.glPopMatrix();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			KGL.bindTexture(MC, NilfgaardArmorLegT);
			GL11.glPushMatrix();
			postRightLeg(mbm);
			GL11.glTranslatef(-0.15F, -0.85F, 0.0F);
			GL11.glScalef(1.15F, 1.1F, 1.15F);
			WitcherLeg.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postLeftLeg(mbm);
			GL11.glTranslatef(0.15F, -0.85F, 0.0F);
			GL11.glScalef(-1.15F, 1.1F, -1.15F);
			WitcherLeg.renderAll();
			GL11.glPopMatrix();
			GL11.glPopMatrix();
		} else if (current == ArmorRegister.catSchoolPants) {
			GL11.glPushMatrix();
			KGL.bindTexture(MC, CatPantsT);
			GL11.glPushMatrix();
			postRightLeg(mbm);
			GL11.glTranslatef(-0.15F, -0.85F, 0.0F);
			GL11.glScalef(1.05F, 1.05F, 1.05F);
			CatSchoolLeg.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postLeftLeg(mbm);
			GL11.glTranslatef(0.15F, -0.85F, 0.0F);
			GL11.glScalef(-1.05F, 1.051F, -1.05F);
			CatSchoolLeg.renderAll();
			GL11.glPopMatrix();
			GL11.glPopMatrix();
		} else if (current == ArmorRegister.griffinSchoolPants) {
			GL11.glPushMatrix();
			KGL.bindTexture(MC, GriffinPantsT);
			GL11.glPushMatrix();
			postRightLeg(mbm);
			GL11.glTranslatef(0.15F, -0.85F, 0.0F);
			GL11.glScalef(1.15F, 1.1F, 1.15F);
			WitcherLegs.renderRightLeg();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postLeftLeg(mbm);
			GL11.glTranslatef(-0.15F, -0.85F, 0.0F);
			GL11.glScalef(1.15F, 1.1F, 1.15F);
			WitcherLegs.renderLeftLeg();
			GL11.glPopMatrix();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			KGL.bindTexture(MC, GriffinArmorT);
			GL11.glPushMatrix();
			postRightLeg(mbm);
			GL11.glTranslatef(0.13F, -0.85F, 0.0F);
			GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
			GL11.glScalef(1.1F, 1.1F, 1.1F);
			GriffinLeg.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postLeftLeg(mbm);
			GL11.glTranslatef(-0.15F, -0.85F, 0.0F);
			GL11.glScalef(1.1F, 1.1F, 1.1F);
			GriffinLeg.renderAll();
			GL11.glPopMatrix();
			GL11.glPopMatrix();
		} else if (current == ArmorRegister.hunterPants) {
			GL11.glPushMatrix();
			KGL.bindTexture(MC, KaerMorhenArmorPantsT);
			GL11.glPushMatrix();
			postRightLeg(mbm);
			GL11.glTranslatef(0.15F, -0.85F, 0.0F);
			GL11.glScalef(1.15F, 1.1F, 1.15F);
			WitcherLegs.renderRightLeg();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postLeftLeg(mbm);
			GL11.glTranslatef(-0.15F, -0.85F, 0.0F);
			GL11.glScalef(1.15F, 1.1F, 1.15F);
			WitcherLegs.renderLeftLeg();
			GL11.glPopMatrix();
			GL11.glPopMatrix();
		} else if (current == ArmorRegister.viperSchoolPants) {
			GL11.glPushMatrix();
			KGL.bindTexture(MC, ViperArmorPantsT);
			GL11.glPushMatrix();
			postRightLeg(mbm);
			GL11.glTranslatef(0.15F, -0.85F, 0.0F);
			GL11.glScalef(1.15F, 1.1F, 1.15F);
			WitcherLegs.renderRightLeg();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postLeftLeg(mbm);
			GL11.glTranslatef(-0.15F, -0.85F, 0.0F);
			GL11.glScalef(1.15F, 1.1F, 1.15F);
			WitcherLegs.renderLeftLeg();
			GL11.glPopMatrix();
			GL11.glPopMatrix();
		}
	}

	private static final void renderGloves(final Item current, final ModelBiped mbm) {
		if (current == ArmorRegister.bearSchoolGloves) {
			GL11.glPushMatrix();
			KGL.bindTexture(MC, BearSchoolT);
			GL11.glPushMatrix();
			postLeftArm(mbm);
			GL11.glTranslatef(-0.33F, -0.14F, 0.0F);
			GL11.glScalef(1.05F, 1.05F, 1.05F);
			BearSchoolHand.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postRightArm(mbm);
			GL11.glTranslatef(0.33F, -0.14F, 0.0F);
			GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
			GL11.glScalef(1.05F, 1.05F, 1.05F);
			BearSchoolHand.renderAll();
			GL11.glPopMatrix();
			GL11.glPopMatrix();
		} else if (current == ArmorRegister.bearSchoolGloves2) {
			GL11.glPushMatrix();
			KGL.bindTexture(MC, BearSchoolT2);
			GL11.glPushMatrix();
			postLeftArm(mbm);
			GL11.glTranslatef(-0.33F, -0.14F, 0.0F);
			GL11.glScalef(1.05F, 1.05F, 1.05F);
			BearSchoolHand.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postRightArm(mbm);
			GL11.glTranslatef(0.33F, -0.14F, 0.0F);
			GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
			GL11.glScalef(1.05F, 1.05F, 1.05F);
			BearSchoolHand.renderAll();
			GL11.glPopMatrix();
			GL11.glPopMatrix();
		} else if (current == ArmorRegister.wolfSchoolGloves) {
			GL11.glPushMatrix();
			KGL.bindTexture(MC, WolfT);
			GL11.glPushMatrix();
			postLeftArm(mbm);
			GL11.glTranslatef(-0.33F, -0.14F, 0.0F);
			GL11.glScalef(1.05F, 1.05F, 1.05F);
			WolfGlove.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postRightArm(mbm);
			GL11.glTranslatef(0.33F, -0.14F, 0.0F);
			GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
			GL11.glScalef(1.05F, 1.05F, 1.05F);
			WolfGlove.renderAll();
			GL11.glPopMatrix();
			GL11.glPopMatrix();
		} else if (current == ArmorRegister.temerGloves) {
			GL11.glPushMatrix();
			KGL.bindTexture(MC, NilfgaardArmorT);
			GL11.glPushMatrix();
			postLeftArm(mbm);
			GL11.glTranslatef(-0.33F, -0.14F, 0.0F);
			GL11.glScalef(1.05F, 1.05F, 1.05F);
			NilfgaardGlove.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postRightArm(mbm);
			GL11.glTranslatef(0.33F, -0.14F, 0.0F);
			GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
			GL11.glScalef(1.05F, 1.05F, 1.05F);
			NilfgaardGlove.renderAll();
			GL11.glPopMatrix();
			GL11.glPopMatrix();
		} else if (current == ArmorRegister.catSchoolGloves) {
			GL11.glPushMatrix();
			KGL.bindTexture(MC, CatSchoolArmorT);
			GL11.glPushMatrix();
			postLeftArm(mbm);
			GL11.glTranslatef(-0.315F, -0.11F, 0.0F);
			GL11.glScalef(1.01F, 1.01F, 1.01F);
			CatSchoolGlove.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postRightArm(mbm);
			GL11.glTranslatef(0.315F, -0.11F, 0.0F);
			GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
			GL11.glScalef(1.01F, 1.01F, 1.01F);
			CatSchoolGlove.renderAll();
			GL11.glPopMatrix();
			GL11.glPopMatrix();
		} else if (current == ArmorRegister.griffinSchoolGloves) {
			GL11.glPushMatrix();
			KGL.bindTexture(MC, GriffinArmorT);
			GL11.glPushMatrix();
			postLeftArm(mbm);
			GL11.glTranslatef(-0.33F, -0.19F, -0.0F);
			GL11.glScalef(1.05F, 1.05F, 1.05F);
			GriffinGlove.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postRightArm(mbm);
			GL11.glTranslatef(0.35F, -0.19F, -0.0F);
			GL11.glScalef(-1.1F, 1.05F, -1.05F);
			GriffinGlove.renderAll();
			GL11.glPopMatrix();
			GL11.glPopMatrix();
		} else if (current == ArmorRegister.viperSchoolGloves) {
			GL11.glPushMatrix();
			KGL.bindTexture(MC, ViperArmorHandT);
			GL11.glPushMatrix();
			postLeftArm(mbm);
			GL11.glTranslatef(-0.1F, 0.55F, -0.13F);
			GL11.glScalef(0.58F, 0.25F, 0.55F);
			WitcherModelGlove.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postRightArm(mbm);
			GL11.glTranslatef(0.1F, 0.55F, 0.13F);
			GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
			GL11.glScalef(0.58F, 0.25F, 0.55F);
			WitcherModelGlove.renderAll();
			GL11.glPopMatrix();
			GL11.glPopMatrix();
		} else if (current == ArmorRegister.hunterGloves) {
			GL11.glPushMatrix();
			KGL.bindTexture(MC, KaerMorhenArmorHandT);
			GL11.glPushMatrix();
			postLeftArm(mbm);
			GL11.glTranslatef(-0.1F, 0.55F, -0.13F);
			GL11.glScalef(0.58F, 0.25F, 0.55F);
			WitcherModelGlove.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postRightArm(mbm);
			GL11.glTranslatef(0.1F, 0.55F, 0.13F);
			GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
			GL11.glScalef(0.58F, 0.25F, 0.55F);
			WitcherModelGlove.renderAll();
			GL11.glPopMatrix();
			GL11.glPopMatrix();
		}
	}

	private static final void renderBoots(final Item current, final ModelBiped mbm) {
		if (current == ArmorRegister.bearSchoolBoots2) {
			GL11.glPushMatrix();
			KGL.bindTexture(MC, BearSchoolBootT);
			GL11.glPushMatrix();
			postLeftLeg(mbm);
			GL11.glTranslatef(-0.0F, -0.73F, -0.12F);
			GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
			GL11.glScalef(1.0F, 1.0F, 1.0F);
			WitcherModelBoot.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postRightLeg(mbm);
			GL11.glTranslatef(-0.0F, -0.73F, -0.12F);
			GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
			GL11.glScalef(1.0F, 1.0F, -1.0F);
			WitcherModelBoot.renderAll();
			GL11.glPopMatrix();
			GL11.glPopMatrix();
		} else if (current == ArmorRegister.bearSchoolBoots) {
			GL11.glPushMatrix();
			KGL.bindTexture(MC, BearSchoolBootT);
			GL11.glPushMatrix();
			postLeftLeg(mbm);
			GL11.glTranslatef(-0.0F, -0.73F, -0.12F);
			GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
			GL11.glScalef(1.0F, 1.0F, 1.0F);
			WitcherModelBoot.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postRightLeg(mbm);
			GL11.glTranslatef(-0.0F, -0.73F, -0.12F);
			GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
			GL11.glScalef(1.0F, 1.0F, -1.0F);
			WitcherModelBoot.renderAll();
			GL11.glPopMatrix();
			GL11.glPopMatrix();
		} else if (current == ArmorRegister.wolfSchoolBoots) {
			GL11.glPushMatrix();
			KGL.bindTexture(MC, WolfBootT);
			GL11.glPushMatrix();
			postLeftLeg(mbm);
			GL11.glTranslatef(-0.0F, -0.73F, -0.12F);
			GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
			GL11.glScalef(1.0F, 1.0F, 1.0F);
			WitcherModelBoot.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postRightLeg(mbm);
			GL11.glTranslatef(-0.0F, -0.73F, -0.12F);
			GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
			GL11.glScalef(1.0F, 1.0F, -1.0F);
			WitcherModelBoot.renderAll();
			GL11.glPopMatrix();
			GL11.glPopMatrix();
		} else if (current == ArmorRegister.catSchoolBoots) {
			GL11.glPushMatrix();
			KGL.bindTexture(MC, CatSchoolArmorT);
			GL11.glPushMatrix();
			postLeftLeg(mbm);
			GL11.glTranslatef(-0.094F, -0.73F, -0.02F);
			GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
			GL11.glScalef(1.01F, 1.01F, 1.01F);
			CatSchoolBoot.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postRightLeg(mbm);
			GL11.glTranslatef(0.094F, -0.73F, -0.02F);
			GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
			GL11.glScalef(1.01F, 1.01F, -1.01F);
			CatSchoolBoot.renderAll();
			GL11.glPopMatrix();
			GL11.glPopMatrix();
		} else if (current == ArmorRegister.temerBoots) {
			GL11.glPushMatrix();
			KGL.bindTexture(MC, NilfgaardArmorBootT);
			GL11.glPushMatrix();
			postLeftLeg(mbm);
			GL11.glTranslatef(-0.0F, -0.68F, -0.12F);
			GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
			GL11.glScalef(1.0F, 1.0F, 1.0F);
			WitcherModelBoot.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postRightLeg(mbm);
			GL11.glTranslatef(-0.0F, -0.68F, -0.12F);
			GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
			GL11.glScalef(1.0F, 1.0F, -1.0F);
			WitcherModelBoot.renderAll();
			GL11.glPopMatrix();
			GL11.glPopMatrix();
		} else if (current == ArmorRegister.griffinSchoolBoots) {
			GL11.glPushMatrix();
			KGL.bindTexture(MC, GriffinBootsT);
			GL11.glPushMatrix();
			postRightLeg(mbm);
			GL11.glTranslatef(-0.0F, -0.69F, -0.12F);
			GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
			GL11.glScalef(1.0F, 1.0F, -1.0F);
			WitcherModelBoot.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postLeftLeg(mbm);
			GL11.glTranslatef(-0.0F, -0.69F, -0.12F);
			GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
			GL11.glScalef(1.0F, 1.0F, 1.0F);
			WitcherModelBoot.renderAll();
			GL11.glPopMatrix();
			GL11.glPopMatrix();
		} else if (current == ArmorRegister.hunterBoots) {
			GL11.glPushMatrix();
			KGL.bindTexture(MC, KaerMorhenArmorBootsT);
			GL11.glPushMatrix();
			postLeftLeg(mbm);
			GL11.glTranslatef(-0.0F, -0.73F, -0.12F);
			GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
			GL11.glScalef(1.0F, 1.0F, 1.0F);
			WitcherModelBoot.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postRightLeg(mbm);
			GL11.glTranslatef(-0.0F, -0.73F, -0.12F);
			GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
			GL11.glScalef(1.0F, 1.0F, -1.0F);
			WitcherModelBoot.renderAll();
			GL11.glPopMatrix();
			GL11.glPopMatrix();
		} else if (current == ArmorRegister.viperSchoolBoots) {
			GL11.glPushMatrix();
			KGL.bindTexture(MC, ViperArmorBootsT);
			GL11.glPushMatrix();
			postLeftLeg(mbm);
			GL11.glTranslatef(-0.0F, -0.73F, -0.12F);
			GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
			GL11.glScalef(1.0F, 1.0F, 1.0F);
			WitcherModelBoot.renderAll();
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			postRightLeg(mbm);
			GL11.glTranslatef(-0.0F, -0.73F, -0.12F);
			GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
			GL11.glScalef(1.0F, 1.0F, -1.0F);
			WitcherModelBoot.renderAll();
			GL11.glPopMatrix();
			GL11.glPopMatrix();
		}
	}

	private static final void postHead(final ModelBiped modelBipedMain) {
		modelBipedMain.bipedHead.postRender(0.0625F);
	}

	private static final void postBody(final ModelBiped modelBipedMain) {
		modelBipedMain.bipedBody.postRender(0.0625F);
	}

	private static final void postLeftArm(final ModelBiped modelBipedMain) {
		modelBipedMain.bipedLeftArm.postRender(0.0625F);
	}

	private static final void postRightArm(final ModelBiped modelBipedMain) {
		modelBipedMain.bipedRightArm.postRender(0.0625F);
	}

	private static final void postLeftLeg(final ModelBiped modelBipedMain) {
		modelBipedMain.bipedLeftLeg.postRender(0.0625F);
	}

	private static final void postRightLeg(final ModelBiped modelBipedMain) {
		modelBipedMain.bipedRightLeg.postRender(0.0625F);
	}
}
