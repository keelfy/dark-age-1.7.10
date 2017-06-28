package noppes.npcs.client.renderer;

import static net.minecraftforge.client.IItemRenderer.ItemRenderType.EQUIPPED;
import static net.minecraftforge.client.IItemRenderer.ItemRendererHelper.BLOCK_3D;

import org.lwjgl.opengl.GL11;

import keelfy.darkage.client.render.RenderPlayerArmor;
import keelfy.darkage.handlers.registers.ArmorRegister;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import noppes.npcs.client.model.ModelNPCMale;
import noppes.npcs.constants.EnumAnimation;
import noppes.npcs.entity.EntityCustomNpc;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.items.ItemClaw;
import noppes.npcs.items.ItemShield;

public class RenderNPCHumanMale extends RenderNPCInterface {

	private ModelNPCMale modelBipedMain;
	protected ModelNPCMale modelArmorChestplate;
	protected ModelNPCMale modelArmor;

	public RenderNPCHumanMale(ModelNPCMale mainmodel, ModelNPCMale armorChest, ModelNPCMale armor) {
		super(mainmodel, 0.5F);
		modelBipedMain = mainmodel;
		modelArmorChestplate = armorChest;
		modelArmor = armor;
	}

	protected int func_130006_a(EntityLiving par1EntityLiving, int par2, float par3) {
		ItemStack itemstack = par1EntityLiving.func_130225_q(3 - par2);

		if (itemstack != null) {
			Item item = itemstack.getItem();

			if (item instanceof ItemArmor) {
				ItemArmor itemarmor = (ItemArmor) item;
				this.bindTexture(RenderBiped.getArmorResource(par1EntityLiving, itemstack, par2, null));
				ModelBiped modelbiped = par2 == 2 ? this.modelArmor : this.modelArmorChestplate;
				modelbiped.bipedHead.showModel = par2 == 0;
				modelbiped.bipedHeadwear.showModel = par2 == 0;
				modelbiped.bipedBody.showModel = par2 == 1 || par2 == 2;
				modelbiped.bipedRightArm.showModel = par2 == 1;
				modelbiped.bipedLeftArm.showModel = par2 == 1;
				modelbiped.bipedRightLeg.showModel = par2 == 2 || par2 == 3;
				modelbiped.bipedLeftLeg.showModel = par2 == 2 || par2 == 3;
				modelbiped = ForgeHooksClient.getArmorModel(par1EntityLiving, itemstack, par2, modelbiped);
				this.setRenderPassModel(modelbiped);
				modelbiped.onGround = this.mainModel.onGround;
				modelbiped.isRiding = this.mainModel.isRiding;
				modelbiped.isChild = this.mainModel.isChild;
				float f1 = 1.0F;

				// Move out of if to allow for more then just CLOTH to have
				// color
				int j = itemarmor.getColor(itemstack);
				if (j != -1) {
					float f2 = (j >> 16 & 255) / 255.0F;
					float f3 = (j >> 8 & 255) / 255.0F;
					float f4 = (j & 255) / 255.0F;
					GL11.glColor3f(f1 * f2, f1 * f3, f1 * f4);

					if (itemstack.isItemEnchanted()) {
						return 31;
					}

					return 16;
				}

				GL11.glColor3f(f1, f1, f1);

				if (itemstack.isItemEnchanted()) {
					return 15;
				}

				return 1;
			}
		}

		return -1;
	}

	@Override
	protected int shouldRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3) {
		return this.func_130006_a((EntityLiving) par1EntityLivingBase, par2, par3);
	}

	public void renderPlayer(EntityNPCInterface npc, double d, double d1, double d2, float f, float f1) {
		ItemStack itemstack = npc.getHeldItem();
		modelArmorChestplate.heldItemRight = modelArmor.heldItemRight = modelBipedMain.heldItemRight = itemstack == null ? 0 : npc.hurtResistantTime > 0 ? 3 : 1;

		modelArmorChestplate.heldItemLeft = modelArmor.heldItemLeft = modelBipedMain.heldItemLeft = npc.getOffHand() == null ? 0 : npc.hurtResistantTime > 0 ? 3 : 1;

		modelArmorChestplate.isSneak = modelArmor.isSneak = modelBipedMain.isSneak = npc.isSneaking();

		modelArmorChestplate.isSleeping = modelArmor.isSleeping = modelBipedMain.isSleeping = npc.isPlayerSleeping();
		modelArmorChestplate.isDancing = modelArmor.isDancing = modelBipedMain.isDancing = npc.currentAnimation == EnumAnimation.DANCING;
		modelArmorChestplate.aimedBow = modelArmor.aimedBow = modelBipedMain.aimedBow = npc.currentAnimation == EnumAnimation.AIMING;

		modelArmorChestplate.isRiding = modelArmor.isRiding = modelBipedMain.isRiding = npc.isRiding();

		double d3 = d1 - npc.yOffset;
		if (npc.isSneaking()) {
			d3 -= 0.125D;
		}
		super.doRender(npc, d, d3, d2, f, f1);
		modelArmorChestplate.aimedBow = modelArmor.aimedBow = modelBipedMain.aimedBow = false;
		modelArmorChestplate.isSneak = modelArmor.isSneak = modelBipedMain.isSneak = false;
		modelArmorChestplate.heldItemRight = modelArmor.heldItemRight = modelBipedMain.heldItemRight = 0;
		modelArmorChestplate.heldItemLeft = modelArmor.heldItemLeft = modelBipedMain.heldItemLeft = 0;
	}

	protected void renderSpecials(EntityNPCInterface npc, float f) {
		super.renderEquippedItems(npc, f);
		GL11.glColor3f(1, 1, 1);
		int i = npc.getBrightnessForRender(f);
		int j = i % 65536;
		int k = i / 65536;
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, j / 1.0F, k / 1.0F);
		if (!npc.display.cloakTexture.isEmpty()) {
			if (npc.textureCloakLocation == null) {
				npc.textureCloakLocation = new ResourceLocation(npc.display.cloakTexture);
			}
			bindTexture(npc.textureCloakLocation);
			
			GL11.glPushMatrix();
			GL11.glTranslatef(0.0F, 0.0F, 0.125F);
			double d = (npc.field_20066_r + (npc.field_20063_u - npc.field_20066_r) * f)
					- (npc.prevPosX + (npc.posX - npc.prevPosX) * f);
			double d1 = (npc.field_20065_s + (npc.field_20062_v - npc.field_20065_s) * f)
					- (npc.prevPosY + (npc.posY - npc.prevPosY) * f);
			double d2 = (npc.field_20064_t + (npc.field_20061_w - npc.field_20064_t) * f)
					- (npc.prevPosZ + (npc.posZ - npc.prevPosZ) * f);
			float f11 = npc.prevRenderYawOffset + (npc.renderYawOffset - npc.prevRenderYawOffset) * f;
			double d3 = MathHelper.sin((f11 * 3.141593F) / 180F);
			double d4 = -MathHelper.cos((f11 * 3.141593F) / 180F);
			float f14 = (float) (d * d3 + d2 * d4) * 100F;
			float f15 = (float) (d * d4 - d2 * d3) * 100F;
			if (f14 < 0.0F) {
				f14 = 0.0F;
			}
			float f16 = npc.prevRotationYaw + (npc.rotationYaw - npc.prevRotationYaw) * f;
			// f13 += MathHelper.sin((entityplayer.prevDistanceWalkedModified +
			// (entityplayer.distanceWalkedModified -
			// entityplayer.prevDistanceWalkedModified) * f) * 6F) * 32F * f16;
			float f13 = 5f;
			if (npc.isSneaking()) {
				f13 += 25F;
			}
			// System.out.println(entityplayer.prevDistanceWalkedModified);
			GL11.glRotatef(6F + f14 / 2.0F + f13, 1.0F, 0.0F, 0.0F);
			GL11.glRotatef(f15 / 2.0F, 0.0F, 0.0F, 1.0F);
			GL11.glRotatef(-f15 / 2.0F, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(180F, 0.0F, 1.0F, 0.0F);
			modelBipedMain.renderCloak(0.0625F);

			GL11.glPopMatrix();
		}
		GL11.glColor3f(1, 1, 1);
		ItemStack itemstack = npc.inventory.armorItemInSlot(0);
		if (itemstack != null) {
			GL11.glPushMatrix();
			if (npc instanceof EntityCustomNpc) {
				EntityCustomNpc cnpc = (EntityCustomNpc) npc;
				GL11.glTranslatef(0, cnpc.modelData.getBodyY(), 0);
				this.modelBipedMain.bipedHead.postRender(0.0625F);
				GL11.glScalef(cnpc.modelData.head.scaleX, cnpc.modelData.head.scaleY, cnpc.modelData.head.scaleZ);
			} else
				this.modelBipedMain.bipedHead.postRender(0.0625F);

			IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(itemstack, EQUIPPED);
			boolean is3D = (customRenderer != null
					&& customRenderer.shouldUseRenderHelper(EQUIPPED, itemstack, BLOCK_3D));

			if (itemstack.getItem() instanceof ItemBlock) {
				if (is3D || RenderBlocks.renderItemIn3d(Block.getBlockFromItem(itemstack.getItem()).getRenderType())) {
					float var6 = 0.625F;
					GL11.glTranslatef(0.0F, -0.25F, 0.0F);
					GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
					GL11.glScalef(var6, -var6, -var6);
				}

				this.renderManager.itemRenderer.renderItem(npc, itemstack, 0);
			}

			GL11.glPopMatrix();
		}
		GL11.glColor3f(1, 1, 1);
		ItemStack itemstack2 = npc.getHeldItem();
		if (itemstack2 != null) {
			float var6;
			GL11.glPushMatrix();
			float y = 0;
			float x = 0;
			if (npc instanceof EntityCustomNpc) {
				EntityCustomNpc cnpc = (EntityCustomNpc) npc;
				y = (cnpc.modelData.arms.scaleY - 1) * 0.7f;
				x = (1 - cnpc.modelData.body.scaleX) * 0.28f + (1 - cnpc.modelData.arms.scaleX) * 0.075f;
				GL11.glTranslatef(x, cnpc.modelData.getBodyY(), 0);
			}
			this.modelBipedMain.bipedRightArm.postRender(0.0625F);
			GL11.glTranslatef(-0.0625F, 0.4375F + y, 0.0625F);

			IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(itemstack2, EQUIPPED);
			boolean is3D = (customRenderer != null
					&& customRenderer.shouldUseRenderHelper(EQUIPPED, itemstack2, BLOCK_3D));

			if (itemstack2.getItem() instanceof ItemBlock && (is3D
					|| RenderBlocks.renderItemIn3d(Block.getBlockFromItem(itemstack2.getItem()).getRenderType()))) {
				var6 = 0.5F;
				GL11.glTranslatef(0.0F, 0.1875F, -0.3125F);
				var6 *= 0.75F;
				GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
				GL11.glScalef(-var6, -var6, var6);
			} else if (itemstack2.getItem() == Items.bow) {
				var6 = 0.625F;
				GL11.glTranslatef(0.0F, 0.125F, 0.3125F);
				GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
				GL11.glScalef(var6, -var6, var6);
				GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
			} else if (itemstack2.getItem().isFull3D()) {
				var6 = 0.625F;

				if (itemstack2.getItem().shouldRotateAroundWhenRendering()) {
					GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
					GL11.glTranslatef(0.0F, -0.125F, 0.0F);
				}

				if (npc.hurtResistantTime > 0 && npc.stats.resistances.playermelee > 1f) {
					GL11.glTranslatef(0.05F, 0.0F, -0.1F);
					GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
					GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
					GL11.glRotatef(-60.0F, 0.0F, 0.0F, 1.0F);
				}

				GL11.glTranslatef(0.0F, 0.1875F, 0.0F);
				GL11.glScalef(var6, -var6, var6);
				GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
			} else {
				var6 = 0.375F;
				GL11.glTranslatef(0.25F, 0.1875F, -0.1875F);
				GL11.glScalef(var6, var6, var6);
				GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
				GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
			}

			if (itemstack2.getItem().requiresMultipleRenderPasses()) {
				for (int var25 = 0; var25 < itemstack2.getItem().getRenderPasses(itemstack2.getItemDamage()); ++var25) {
					int var24 = itemstack2.getItem().getColorFromItemStack(itemstack2, var25);
					float var26 = (var24 >> 16 & 255) / 255.0F;
					float var9 = (var24 >> 8 & 255) / 255.0F;
					float var10 = (var24 & 255) / 255.0F;
					GL11.glColor4f(var26, var9, var10, 1.0F);
					this.renderManager.itemRenderer.renderItem(npc, itemstack2, var25);
				}
			} else
				renderManager.itemRenderer.renderItem(npc, itemstack2, 0);

			GL11.glPopMatrix();
		}
		GL11.glColor4f(1, 1, 1, 1.0F);
		itemstack2 = npc.getOffHand();
		if (itemstack2 != null) {
			GL11.glPushMatrix();
			float y = 0;
			float x = 0;
			if (npc instanceof EntityCustomNpc) {
				EntityCustomNpc cnpc = (EntityCustomNpc) npc;
				y = (cnpc.modelData.arms.scaleY - 1) * 0.7f;
				x = (1 - cnpc.modelData.body.scaleX) * 0.28f + (1 - cnpc.modelData.arms.scaleX) * 0.075f;
				GL11.glTranslatef(x, cnpc.modelData.getBodyY(), 0);
			}
			this.modelBipedMain.bipedLeftArm.postRender(0.0625F);
			GL11.glTranslatef(0.0625F, 0.4375F + y, 0.0625F);
			float var6;

			IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(itemstack2, EQUIPPED);
			boolean is3D = (customRenderer != null
					&& customRenderer.shouldUseRenderHelper(EQUIPPED, itemstack2, BLOCK_3D));

			if (itemstack2.getItem() instanceof ItemShield || itemstack2.getItem() instanceof ItemClaw)
				GL11.glTranslatef(0.30f, 0, 0f);

			if (itemstack2.getItem() instanceof ItemBlock && (is3D
					|| RenderBlocks.renderItemIn3d(Block.getBlockFromItem(itemstack2.getItem()).getRenderType()))) {
				var6 = 0.5F;
				GL11.glTranslatef(0.0F, 0.1875F, -0.3125F);
				var6 *= 0.75F;
				GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
				GL11.glScalef(var6, -var6, var6);
			} else if (itemstack2.getItem() == Items.bow) {
				var6 = 0.625F;
				GL11.glTranslatef(0.0F, 0.125F, 0.3125F);
				GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
				GL11.glScalef(var6, -var6, var6);
				GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
			} else if (itemstack2.getItem().isFull3D()) {
				var6 = 0.625F;

				if (itemstack2.getItem().shouldRotateAroundWhenRendering()) {
					GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
					GL11.glTranslatef(0.0F, -0.125F, 0.0F);
				}

				if (npc.hurtResistantTime > 0 && npc.stats.resistances.arrow > 1f) {
					GL11.glTranslatef(0.05F, 0.0F, -0.1F);
					GL11.glRotatef(50.0F, 0.0F, 1.0F, 0.0F);
					GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
					GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
				}

				GL11.glTranslatef(0.0F, 0.1875F, 0.0F);
				GL11.glScalef(var6, -var6, var6);
				GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
			} else {
				var6 = 0.375F;
				GL11.glTranslatef(0.25F, 0.1875F, -0.1875F);
				GL11.glScalef(var6, var6, var6);
				GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
				GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
			}

			if (itemstack2.getItem().requiresMultipleRenderPasses()) {
				for (int var25 = 0; var25 < itemstack2.getItem().getRenderPasses(itemstack2.getItemDamage()); ++var25) {
					int var24 = itemstack2.getItem().getColorFromItemStack(itemstack2, var25);
					float var26 = (var24 >> 16 & 255) / 255.0F;
					float var9 = (var24 >> 8 & 255) / 255.0F;
					float var10 = (var24 & 255) / 255.0F;
					GL11.glColor4f(var26, var9, var10, 1.0F);
					this.renderManager.itemRenderer.renderItem(npc, itemstack2, var25);
				}
			} else {
				renderManager.itemRenderer.renderItem(npc, itemstack2, 0);
			}
			GL11.glPopMatrix();
		}

		ItemStack itemstackHelm = npc.inventory.armorItemInSlot(0);
		ItemStack itemstackPlate = npc.inventory.armorItemInSlot(1);
		ItemStack itemstackLegs = npc.inventory.armorItemInSlot(2);
		ItemStack itemstackBoots = npc.inventory.armorItemInSlot(3);
		
		TextureManager textureManager = Minecraft.getMinecraft().getTextureManager();
		
		if (itemstackHelm != null) {
			Item currentArmor2Item = itemstackHelm.getItem();

			if (currentArmor2Item == ArmorRegister.cidarianCavalrymanChestplate) {
				GL11.glPushMatrix();
				this.modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glTranslatef(0.0F, -0.05F, 0.0F);
				GL11.glScalef(1.1F, 1.1F, 1.1F);
				textureManager.bindTexture(RenderPlayerArmor.CidarisT);
				RenderPlayerArmor.RedanianHalebardistArmor.render(0.0625F);
				GL11.glPopMatrix();
				
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.CidarisT);
				modelBipedMain.bipedLeftArm.postRender(0.0625F);
				GL11.glTranslatef(-0.33F, -0.14F, 0.0F);
				GL11.glScalef(1.05F, 1.05F, 1.05F);
				RenderPlayerArmor.RedanianHalebardistAddon.render(0.0625F);
				GL11.glPopMatrix();
				
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.CidarisT);
				modelBipedMain.bipedRightArm.postRender(0.0625F);
				GL11.glTranslatef(0.33F, -0.14F, 0.0F);
				GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
				GL11.glScalef(1.05F, 1.05F, 1.05F);
				RenderPlayerArmor.RedanianHalebardistAddon.render(0.0625F);
				GL11.glPopMatrix();
				
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.CidarisT);
				modelBipedMain.bipedRightLeg.postRender(0.0625F);
				GL11.glTranslatef(0.15F, -0.95F, 0.0F);
				GL11.glScalef(-1.15F, 1.15F, 1.15F);
				RenderPlayerArmor.CidarisLeg.render(0.0625F);
				GL11.glPopMatrix();
				
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.CidarisT);
				modelBipedMain.bipedLeftLeg.postRender(0.0625F);
				GL11.glTranslatef(-0.15F, -0.95F, 0.0F);
				GL11.glScalef(1.15F, 1.15F, 1.15F);
				RenderPlayerArmor.CidarisLeg.render(0.0625F);
				GL11.glPopMatrix();
			}

			if (currentArmor2Item == ArmorRegister.cidarianChestplate) {
				GL11.glPushMatrix();
				modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glTranslatef(0.0F, -0.05F, 0.0F);
				GL11.glScalef(1.1F, 1.1F, 1.1F);
				textureManager.bindTexture(RenderPlayerArmor.CidarisCombezT);
				RenderPlayerArmor.BelhavenaBrigantineBody.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.CidarisCombezT);
				modelBipedMain.bipedLeftArm.postRender(0.0625F);
				GL11.glTranslatef(-0.33F, -0.14F, 0.0F);
				GL11.glScalef(1.05F, 1.05F, 1.05F);
				RenderPlayerArmor.BelhavenaBrigantineHand.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.CidarisCombezT);
				modelBipedMain.bipedRightArm.postRender(0.0625F);
				GL11.glTranslatef(0.33F, -0.14F, 0.0F);
				GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
				GL11.glScalef(1.05F, 1.05F, 1.05F);
				RenderPlayerArmor.BelhavenaBrigantineHand.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.CidarisCombezT);
				modelBipedMain.bipedRightLeg.postRender(0.0625F);
				GL11.glTranslatef(0.15F, -0.89F, 0.0F);
				GL11.glScalef(-1.15F, 1.15F, 1.15F);
				RenderPlayerArmor.BelhavenaBrigantineLeg.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.CidarisCombezT);
				modelBipedMain.bipedLeftLeg.postRender(0.0625F);
				GL11.glTranslatef(-0.15F, -0.89F, 0.0F);
				GL11.glScalef(1.15F, 1.15F, 1.15F);
				RenderPlayerArmor.BelhavenaBrigantineLeg.render(0.0625F);
				GL11.glPopMatrix();
			}

			if (currentArmor2Item == ArmorRegister.cloth) {
				GL11.glPushMatrix();
				modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glTranslatef(0.0F, -0.05F, 0.0F);
				GL11.glScalef(1.0F, 1.07F, 1.0F);
				textureManager.bindTexture(RenderPlayerArmor.ShirtT);
				RenderPlayerArmor.ShirtBody.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glTranslatef(0.0F, -0.05F, 0.0F);
				GL11.glScalef(0.96F, 0.96F, 0.96F);
				textureManager.bindTexture(RenderPlayerArmor.ShirtT);
				RenderPlayerArmor.ShirtAddon.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.ShirtT);
				modelBipedMain.bipedLeftArm.postRender(0.0625F);
				GL11.glTranslatef(-0.315F, -0.14F, 0.0F);
				GL11.glScalef(1.0F, 1.0F, 1.0F);
				RenderPlayerArmor.ShirtHand.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.ShirtT);
				modelBipedMain.bipedRightArm.postRender(0.0625F);
				GL11.glTranslatef(0.315F, -0.14F, 0.0F);
				GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
				GL11.glScalef(1.0F, 1.0F, 1.0F);
				RenderPlayerArmor.ShirtHand.render(0.0625F);
				GL11.glPopMatrix();
			}

			if (currentArmor2Item == ArmorRegister.infantryChestplate) {
				GL11.glPushMatrix();
				modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glTranslatef(0.0F, -0.05F, 0.0F);
				GL11.glScalef(1.1F, 1.1F, 1.1F);
				textureManager.bindTexture(RenderPlayerArmor.BelhavenaBrigantineT);
				RenderPlayerArmor.BelhavenaBrigantineBody.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.BelhavenaBrigantineT);
				modelBipedMain.bipedLeftArm.postRender(0.0625F);
				GL11.glTranslatef(-0.33F, -0.14F, 0.0F);
				GL11.glScalef(1.05F, 1.05F, 1.05F);
				RenderPlayerArmor.BelhavenaBrigantineHand.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.BelhavenaBrigantineT);
				modelBipedMain.bipedRightArm.postRender(0.0625F);
				GL11.glTranslatef(0.33F, -0.14F, 0.0F);
				GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
				GL11.glScalef(1.05F, 1.05F, 1.05F);
				RenderPlayerArmor.BelhavenaBrigantineHand.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.BelhavenaBrigantineT);
				modelBipedMain.bipedRightLeg.postRender(0.0625F);
				GL11.glTranslatef(0.15F, -0.89F, 0.0F);
				GL11.glScalef(-1.15F, 1.15F, 1.15F);
				RenderPlayerArmor.BelhavenaBrigantineLeg.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.BelhavenaBrigantineT);
				modelBipedMain.bipedLeftLeg.postRender(0.0625F);
				GL11.glTranslatef(-0.15F, -0.89F, 0.0F);
				GL11.glScalef(1.15F, 1.15F, 1.15F);
				RenderPlayerArmor.BelhavenaBrigantineLeg.render(0.0625F);
				GL11.glPopMatrix();
			}

			if (currentArmor2Item == ArmorRegister.alebardistChestplate) {
				GL11.glPushMatrix();
				modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glTranslatef(0.0F, -0.05F, 0.0F);
				GL11.glScalef(1.1F, 1.1F, 1.1F);
				textureManager.bindTexture(RenderPlayerArmor.RedanianHalebardistArmorT);
				RenderPlayerArmor.RedanianHalebardistArmor.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.RedanianHalebardistArmorT);
				modelBipedMain.bipedLeftArm.postRender(0.0625F);
				GL11.glTranslatef(-0.33F, -0.14F, 0.0F);
				GL11.glScalef(1.05F, 1.05F, 1.05F);
				RenderPlayerArmor.RedanianHalebardistAddon.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.RedanianHalebardistArmorT);
				modelBipedMain.bipedRightArm.postRender(0.0625F);
				GL11.glTranslatef(0.33F, -0.14F, 0.0F);
				GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
				GL11.glScalef(1.05F, 1.05F, 1.05F);
				RenderPlayerArmor.RedanianHalebardistAddon.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.RedanianHalebardistArmorT);
				modelBipedMain.bipedRightLeg.postRender(0.0625F);
				GL11.glTranslatef(0.15F, -0.95F, 0.0F);
				GL11.glScalef(-1.15F, 1.15F, 1.15F);
				RenderPlayerArmor.RedanianHalebardistLeg.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.RedanianHalebardistArmorT);
				modelBipedMain.bipedLeftLeg.postRender(0.0625F);
				GL11.glTranslatef(-0.15F, -0.95F, 0.0F);
				GL11.glScalef(1.15F, 1.15F, 1.15F);
				RenderPlayerArmor.RedanianHalebardistLeg.render(0.0625F);
				GL11.glPopMatrix();
			}

			if (currentArmor2Item == ArmorRegister.hindarsfjallChestplate) {
				GL11.glPushMatrix();
				modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glTranslatef(0.0F, -0.05F, 0.0F);
				GL11.glScalef(1.1F, 1.1F, 1.1F);
				textureManager.bindTexture(RenderPlayerArmor.HindarsfjallHeavyArmorT);
				RenderPlayerArmor.HindarsfjallHeavyArmorBody.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.HindarsfjallHeavyArmorT);
				modelBipedMain.bipedLeftArm.postRender(0.0625F);
				GL11.glTranslatef(-0.33F, -0.14F, 0.0F);
				GL11.glScalef(1.05F, 1.05F, 1.05F);
				RenderPlayerArmor.HindarsfjallHeavyArmorHand.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.HindarsfjallHeavyArmorT);
				modelBipedMain.bipedRightArm.postRender(0.0625F);
				GL11.glTranslatef(0.33F, -0.14F, 0.0F);
				GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
				GL11.glScalef(1.05F, 1.05F, 1.05F);
				RenderPlayerArmor.HindarsfjallHeavyArmorHand.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.HindarsfjallHeavyArmorT);
				modelBipedMain.bipedRightLeg.postRender(0.0625F);
				GL11.glTranslatef(0.13F, -0.76F, 0.0F);
				GL11.glScalef(-1.15F, 1.05F, 1.15F);
				RenderPlayerArmor.HindarsfjallHeavyArmorLeg.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.HindarsfjallHeavyArmorT);
				modelBipedMain.bipedLeftLeg.postRender(0.0625F);
				GL11.glTranslatef(-0.13F, -0.76F, 0.0F);
				GL11.glScalef(1.15F, 1.05F, 1.15F);
				RenderPlayerArmor.HindarsfjallHeavyArmorLeg.render(0.0625F);
				GL11.glPopMatrix();
			}

			if (currentArmor2Item == ArmorRegister.redanCavalrymanChestplate) {
				GL11.glPushMatrix();
				modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glTranslatef(0.0F, -0.05F, 0.0F);
				GL11.glScalef(1.1F, 1.1F, 1.1F);
				textureManager.bindTexture(RenderPlayerArmor.WitchunterArmorT);
				RenderPlayerArmor.WitchunterArmorBody.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.WitchunterArmorT);
				modelBipedMain.bipedRightArm.postRender(0.0625F);
				GL11.glTranslatef(0.33F, -0.14F, 0.0F);
				GL11.glScalef(1.05F, 1.05F, 1.05F);
				RenderPlayerArmor.WitchunterArmorHand.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.WitchunterArmorT);
				modelBipedMain.bipedLeftArm.postRender(0.0625F);
				GL11.glTranslatef(0.46F, -0.14F, 0.0F);
				GL11.glScalef(1.05F, 1.05F, 1.05F);
				RenderPlayerArmor.WitchunterArmorHand.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.WitchunterArmorT);
				modelBipedMain.bipedLeftLeg.postRender(0.0625F);
				GL11.glTranslatef(-0.13F, -0.76F, 0.0F);
				GL11.glScalef(-1.15F, 1.05F, 1.15F);
				RenderPlayerArmor.WitchunterArmorLeg.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.WitchunterArmorT);
				modelBipedMain.bipedRightLeg.postRender(0.0625F);
				GL11.glTranslatef(0.13F, -0.76F, 0.0F);
				GL11.glScalef(1.15F, 1.05F, 1.15F);
				RenderPlayerArmor.WitchunterArmorLeg.render(0.0625F);
				GL11.glPopMatrix();
			}

			if (currentArmor2Item == ArmorRegister.ellanderChestplate) {
				GL11.glPushMatrix();
				modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glTranslatef(0.0F, -0.05F, 0.0F);
				GL11.glScalef(1.1F, 1.1F, 1.1F);
				textureManager.bindTexture(RenderPlayerArmor.MagDeithraArmorT);
				RenderPlayerArmor.MagDeithraArmor.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.MagDeithraArmorT);
				modelBipedMain.bipedLeftArm.postRender(0.0625F);
				GL11.glTranslatef(-0.33F, -0.14F, 0.0F);
				GL11.glScalef(1.05F, 1.05F, 1.05F);
				RenderPlayerArmor.MagDeithraHand.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.MagDeithraArmorT);
				modelBipedMain.bipedRightArm.postRender(0.0625F);
				GL11.glTranslatef(0.33F, -0.14F, 0.0F);
				GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
				GL11.glScalef(1.05F, 1.05F, 1.05F);
				RenderPlayerArmor.MagDeithraHand.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.MagDeithraArmorT);
				modelBipedMain.bipedRightLeg.postRender(0.0625F);
				GL11.glTranslatef(0.15F, -0.95F, 0.0F);
				GL11.glScalef(-1.15F, 1.15F, 1.15F);
				RenderPlayerArmor.MagDeithraRightLeg.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.MagDeithraArmorT);
				modelBipedMain.bipedLeftLeg.postRender(0.0625F);
				GL11.glTranslatef(-0.13F, -0.95F, 0.0F);
				GL11.glScalef(1.15F, 1.15F, 1.15F);
				RenderPlayerArmor.MagDeithraRightLeg.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.MagDeithraArmorT);
				modelBipedMain.bipedRightLeg.postRender(0.0625F);
				GL11.glTranslatef(0.15F, -0.9F, -0.0F);
				GL11.glScalef(1.4F, 1.3F, -1.4F);
				RenderPlayerArmor.MagDeithraLegAddon.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.MagDeithraArmorT);
				modelBipedMain.bipedLeftLeg.postRender(0.0625F);
				GL11.glTranslatef(0.23F, -0.85F, -0.01F);
				GL11.glScalef(1.4F, 1.3F, -1.4F);
				RenderPlayerArmor.MagDeithraLegAddon.render(0.0625F);
				GL11.glPopMatrix();
			}

			if (currentArmor2Item == ArmorRegister.temerChestplate) {
				GL11.glPushMatrix();
				modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glTranslatef(0.0F, -0.05F, 0.0F);
				GL11.glScalef(1.1F, 1.1F, 1.1F);
				textureManager.bindTexture(RenderPlayerArmor.NilfgaardArmorT);
				RenderPlayerArmor.NilfgaardArmor.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				modelBipedMain.bipedHead.postRender(0.0625F);
				GL11.glTranslatef(0.0F, -0.05F, 0.0F);
				GL11.glScalef(1.1F, 1.1F, 1.1F);
				textureManager.bindTexture(RenderPlayerArmor.NilfgaardArmorT);
				RenderPlayerArmor.NilfgaardHead.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.NilfgaardArmorT);
				modelBipedMain.bipedLeftArm.postRender(0.0625F);
				GL11.glTranslatef(-0.33F, -0.14F, 0.0F);
				GL11.glScalef(1.05F, 1.05F, 1.05F);
				RenderPlayerArmor.NilfgaardArmorAddon.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.NilfgaardArmorT);
				modelBipedMain.bipedRightArm.postRender(0.0625F);
				GL11.glTranslatef(0.33F, -0.14F, 0.0F);
				GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
				GL11.glScalef(1.05F, 1.05F, 1.05F);
				RenderPlayerArmor.NilfgaardArmorAddon.render(0.0625F);
				GL11.glPopMatrix();
			}

			if (currentArmor2Item == ArmorRegister.bearSchoolChestplate) {
				GL11.glPushMatrix();
				modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glTranslatef(0.0F, -0.05F, 0.0F);
				GL11.glScalef(1.05F, 1.05F, 1.05F);
				textureManager.bindTexture(RenderPlayerArmor.BearSchoolT);
				RenderPlayerArmor.BearSchoolArmor.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.BearSchoolT);
				modelBipedMain.bipedLeftArm.postRender(0.0625F);
				GL11.glTranslatef(-0.33F, -0.14F, 0.0F);
				GL11.glScalef(1.05F, 1.05F, 1.05F);
				RenderPlayerArmor.BearSchoolHand.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.BearSchoolT);
				modelBipedMain.bipedRightArm.postRender(0.0625F);
				GL11.glTranslatef(0.33F, -0.14F, 0.0F);
				GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
				GL11.glScalef(1.05F, 1.05F, 1.05F);
				RenderPlayerArmor.BearSchoolHand.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.BearSchoolSkirtT);
				modelBipedMain.bipedRightLeg.postRender(0.0625F);
				GL11.glTranslatef(0.15F, -0.95F, 0.0F);
				GL11.glScalef(-1.25F, 1.25F, 1.25F);
				RenderPlayerArmor.ModelSkirt.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.BearSchoolSkirtT);
				modelBipedMain.bipedLeftLeg.postRender(0.0625F);
				GL11.glTranslatef(-0.15F, -0.95F, 0.0F);
				GL11.glScalef(1.25F, 1.25F, 1.25F);
				RenderPlayerArmor.ModelSkirt.render(0.0625F);
				GL11.glPopMatrix();
			}

			if (currentArmor2Item == ArmorRegister.bearSchoolChestplate2) {
				GL11.glPushMatrix();
				modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glTranslatef(0.0F, -0.05F, 0.0F);
				GL11.glScalef(1.05F, 1.05F, 1.05F);
				textureManager.bindTexture(RenderPlayerArmor.BearSchoolT2);
				RenderPlayerArmor.BearSchoolArmor.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.BearSchoolT2);
				modelBipedMain.bipedLeftArm.postRender(0.0625F);
				GL11.glTranslatef(-0.33F, -0.14F, 0.0F);
				GL11.glScalef(1.05F, 1.05F, 1.05F);
				RenderPlayerArmor.BearSchoolHand.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.BearSchoolT2);
				modelBipedMain.bipedRightArm.postRender(0.0625F);
				GL11.glTranslatef(0.33F, -0.14F, 0.0F);
				GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
				GL11.glScalef(1.05F, 1.05F, 1.05F);
				RenderPlayerArmor.BearSchoolHand.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.BearSchoolSkirtT2);
				modelBipedMain.bipedRightLeg.postRender(0.0625F);
				GL11.glTranslatef(0.15F, -0.95F, 0.0F);
				GL11.glScalef(-1.25F, 1.25F, 1.25F);
				RenderPlayerArmor.ModelSkirt.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.BearSchoolSkirtT2);
				modelBipedMain.bipedLeftLeg.postRender(0.0625F);
				GL11.glTranslatef(-0.15F, -0.95F, 0.0F);
				GL11.glScalef(1.25F, 1.25F, 1.25F);
				RenderPlayerArmor.ModelSkirt.render(0.0625F);
				GL11.glPopMatrix();
			}

			if (currentArmor2Item == ArmorRegister.catSchoolChestplate) {
				GL11.glPushMatrix();
				modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glTranslatef(0.0F, -0.05F, 0.0F);
				GL11.glScalef(1.05F, 1.05F, 1.05F);
				textureManager.bindTexture(RenderPlayerArmor.CatSchoolArmorT);
				RenderPlayerArmor.CatSchoolArmor.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.CatSchoolArmorT);
				modelBipedMain.bipedLeftArm.postRender(0.0625F);
				GL11.glTranslatef(-0.33F, -0.14F, 0.0F);
				GL11.glScalef(1.05F, 1.05F, 1.05F);
				RenderPlayerArmor.CatSchoolSleeve.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.CatSchoolArmorT);
				modelBipedMain.bipedRightArm.postRender(0.0625F);
				GL11.glTranslatef(0.33F, -0.14F, 0.0F);
				GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
				GL11.glScalef(1.05F, 1.05F, 1.05F);
				RenderPlayerArmor.CatSchoolSleeve.render(0.0625F);
				GL11.glPopMatrix();
			}

			if (currentArmor2Item == ArmorRegister.talgarChestplate) {
				GL11.glPushMatrix();
				modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glTranslatef(0.0F, -0.05F, 0.0F);
				GL11.glScalef(1.05F, 1.05F, 1.05F);
				textureManager.bindTexture(RenderPlayerArmor.TalgarT);
				RenderPlayerArmor.TalgarBody.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glTranslatef(0.03F, 0.1F, -0.2F);
				GL11.glScalef(0.2F, 0.2F, 0.2F);
				textureManager.bindTexture(RenderPlayerArmor.TalgarSignT);
				RenderPlayerArmor.Sign.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.TalgarT);
				modelBipedMain.bipedLeftArm.postRender(0.0625F);
				GL11.glTranslatef(-0.33F, -0.14F, 0.0F);
				GL11.glScalef(1.05F, 1.05F, 1.05F);
				RenderPlayerArmor.TalgarHand.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.TalgarT);
				modelBipedMain.bipedRightArm.postRender(0.0625F);
				GL11.glTranslatef(0.33F, -0.14F, 0.0F);
				GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
				GL11.glScalef(1.05F, 1.05F, 1.05F);
				RenderPlayerArmor.TalgarHand.render(0.0625F);
				GL11.glPopMatrix();
			}

			if (currentArmor2Item == ArmorRegister.wolfSchoolChestplate) {
				GL11.glPushMatrix();
				modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glTranslatef(0.0F, -0.05F, 0.0F);
				GL11.glScalef(1.05F, 1.05F, 1.05F);
				textureManager.bindTexture(RenderPlayerArmor.WolfT);
				RenderPlayerArmor.WolfArmor.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.WolfT);
				modelBipedMain.bipedLeftArm.postRender(0.0625F);
				GL11.glTranslatef(-0.33F, -0.14F, 0.0F);
				GL11.glScalef(1.05F, 1.05F, 1.05F);
				RenderPlayerArmor.WolfHand.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.WolfT);
				modelBipedMain.bipedRightArm.postRender(0.0625F);
				GL11.glTranslatef(0.33F, -0.14F, 0.0F);
				GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
				GL11.glScalef(1.05F, 1.05F, 1.05F);
				RenderPlayerArmor.WolfHand.render(0.0625F);
				GL11.glPopMatrix();
			}

			if (currentArmor2Item == ArmorRegister.griffinSchoolChestplate) {
				GL11.glPushMatrix();
				modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glTranslatef(0.0F, -0.03F, 0.0F);
				GL11.glScalef(1.1F, 1.0F, 1.1F);
				textureManager.bindTexture(RenderPlayerArmor.GriffinArmorT);
				RenderPlayerArmor.GriffinArmor.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.GriffinArmorT);
				modelBipedMain.bipedLeftArm.postRender(0.0625F);
				GL11.glTranslatef(-0.33F, -0.19F, -0.0F);
				GL11.glScalef(1.05F, 1.05F, 1.05F);
				RenderPlayerArmor.GriffinHand.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.GriffinArmorT);
				modelBipedMain.bipedRightArm.postRender(0.0625F);
				GL11.glTranslatef(0.35F, -0.19F, -0.0F);
				GL11.glScalef(-1.1F, 1.05F, -1.05F);
				RenderPlayerArmor.GriffinHand.render(0.0625F);
				GL11.glPopMatrix();
			}

			if (currentArmor2Item == ArmorRegister.viperSchoolChestplate) {
				GL11.glPushMatrix();
				modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glTranslatef(0.0F, 0.0F, 0.0F);
				GL11.glScalef(1.1F, 1.1F, 1.1F);
				textureManager.bindTexture(RenderPlayerArmor.ViperArmorT);
				RenderPlayerArmor.KaerMorhenArmor.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				modelBipedMain.bipedLeftArm.postRender(0.0625F);
				GL11.glTranslatef(-0.3F, -0.12F, 0.0F);
				GL11.glScalef(1.0F, 1.0F, 0.9F);
				textureManager.bindTexture(RenderPlayerArmor.ViperArmorT);
				RenderPlayerArmor.KaerMorhenHand.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				modelBipedMain.bipedRightArm.postRender(0.0625F);
				GL11.glTranslatef(0.3F, -0.12F, 0.0F);
				GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
				GL11.glScalef(1.0F, 1.0F, 0.9F);
				textureManager.bindTexture(RenderPlayerArmor.ViperArmorT);
				RenderPlayerArmor.KaerMorhenHand.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				modelBipedMain.bipedLeftArm.postRender(0.0625F);
				GL11.glTranslatef(-0.27F, 0.12F, 0.0F);
				GL11.glScalef(0.9F, 0.9F, 0.8F);
				textureManager.bindTexture(RenderPlayerArmor.ViperArmorT);
				RenderPlayerArmor.KaerMorhenHandAddon.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				modelBipedMain.bipedRightArm.postRender(0.0625F);
				GL11.glTranslatef(0.27F, 0.12F, 0.0F);
				GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
				GL11.glScalef(0.9F, 0.9F, 0.8F);
				textureManager.bindTexture(RenderPlayerArmor.ViperArmorT);
				RenderPlayerArmor.KaerMorhenHandAddon.render(0.0625F);
				GL11.glPopMatrix();
			}

			if (currentArmor2Item == ArmorRegister.hunterChestplate) {
				GL11.glPushMatrix();
				modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glTranslatef(0.0F, 0.0F, 0.0F);
				GL11.glScalef(1.1F, 1.1F, 1.1F);
				textureManager.bindTexture(RenderPlayerArmor.KaerMorhenArmorT);
				RenderPlayerArmor.KaerMorhenArmor.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				modelBipedMain.bipedLeftArm.postRender(0.0625F);
				GL11.glTranslatef(-0.3F, -0.12F, 0.0F);
				GL11.glScalef(1.0F, 1.0F, 0.9F);
				textureManager.bindTexture(RenderPlayerArmor.KaerMorhenArmorT);
				RenderPlayerArmor.KaerMorhenHand.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				modelBipedMain.bipedRightArm.postRender(0.0625F);
				GL11.glTranslatef(0.3F, -0.12F, 0.0F);
				GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
				GL11.glScalef(1.0F, 1.0F, 0.9F);
				textureManager.bindTexture(RenderPlayerArmor.KaerMorhenArmorT);
				RenderPlayerArmor.KaerMorhenHand.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				modelBipedMain.bipedLeftArm.postRender(0.0625F);
				GL11.glTranslatef(-0.27F, 0.12F, 0.0F);
				GL11.glScalef(0.9F, 0.9F, 0.9F);
				textureManager.bindTexture(RenderPlayerArmor.KaerMorhenArmorT);
				RenderPlayerArmor.KaerMorhenHandAddon.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				modelBipedMain.bipedRightArm.postRender(0.0625F);
				GL11.glTranslatef(0.27F, 0.12F, 0.0F);
				GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
				GL11.glScalef(0.9F, 0.9F, 0.9F);
				textureManager.bindTexture(RenderPlayerArmor.KaerMorhenArmorT);
				RenderPlayerArmor.KaerMorhenHandAddon.render(0.0625F);
				GL11.glPopMatrix();
			}
		}

		if (itemstackLegs != null) {
			Item currentArmor1Item = itemstackLegs.getItem();

			if (currentArmor1Item == ArmorRegister.cidarianArmyPants) {
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.WitcherLuriskyPantsT);
				modelBipedMain.bipedLeftLeg.postRender(0.0625F);
				GL11.glTranslatef(-0.135F, -0.8F, 0.0F);
				GL11.glScalef(1.05F, 1.05F, 1.05F);
				RenderPlayerArmor.WitcherLuriskyPants.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.WitcherLuriskyPantsT);
				modelBipedMain.bipedRightLeg.postRender(0.0625F);
				GL11.glTranslatef(0.135F, -0.8F, 0.0F);
				GL11.glScalef(-1.05F, 1.05F, 1.05F);
				RenderPlayerArmor.WitcherLuriskyPants.render(0.0625F);
				GL11.glPopMatrix();
			}

			if (currentArmor1Item == ArmorRegister.talgarPants) {
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.TalgarT);
				modelBipedMain.bipedLeftLeg.postRender(0.0625F);
				GL11.glTranslatef(-0.135F, -0.8F, 0.0F);
				GL11.glScalef(1.05F, 1.05F, 1.05F);
				RenderPlayerArmor.TalgarLeg.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.TalgarT);
				modelBipedMain.bipedRightLeg.postRender(0.0625F);
				GL11.glTranslatef(0.135F, -0.8F, 0.0F);
				GL11.glScalef(-1.05F, 1.05F, 1.05F);
				RenderPlayerArmor.TalgarLeg.render(0.0625F);
				GL11.glPopMatrix();
			}

			if (currentArmor1Item == ArmorRegister.leatherPants) {
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.WitcherPathfinderPantsT);
				modelBipedMain.bipedLeftLeg.postRender(0.0625F);
				GL11.glTranslatef(-0.135F, -0.8F, 0.0F);
				GL11.glScalef(1.0F, 1.0F, 1.0F);
				RenderPlayerArmor.WitcherPathfinderLegs.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.WitcherPathfinderPantsT);
				modelBipedMain.bipedRightLeg.postRender(0.0625F);
				GL11.glTranslatef(0.135F, -0.8F, 0.0F);
				GL11.glScalef(-1.0F, 1.0F, -1.0F);
				RenderPlayerArmor.WitcherPathfinderLegs.render(0.0625F);
				GL11.glPopMatrix();
			}

			if (currentArmor1Item == ArmorRegister.temerArmyPants) {
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.WitcherTopornikPantsT);
				modelBipedMain.bipedLeftLeg.postRender(0.0625F);
				GL11.glTranslatef(-0.15F, -0.8F, 0.0F);
				GL11.glScalef(1.05F, 1.05F, 1.05F);
				RenderPlayerArmor.WitcherTopornikPants.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.WitcherTopornikPantsT);
				modelBipedMain.bipedRightLeg.postRender(0.0625F);
				GL11.glTranslatef(0.15F, -0.8F, 0.0F);
				GL11.glScalef(-1.05F, 1.05F, -1.05F);
				RenderPlayerArmor.WitcherTopornikPants.render(0.0625F);
				GL11.glPopMatrix();
			}

			if (currentArmor1Item == ArmorRegister.bearSchoolPants2) {
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.BearSchoolT);
				modelBipedMain.bipedRightLeg.postRender(0.0625F);
				GL11.glTranslatef(-0.15F, -0.85F, 0.0F);
				GL11.glScalef(1.15F, 1.1F, 1.15F);
				RenderPlayerArmor.WitcherLeg.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.BearSchoolT);
				modelBipedMain.bipedLeftLeg.postRender(0.0625F);
				GL11.glTranslatef(0.15F, -0.85F, 0.0F);
				GL11.glScalef(-1.15F, 1.1F, -1.15F);
				RenderPlayerArmor.WitcherLeg.render(0.0625F);
				GL11.glPopMatrix();
			}

			if (currentArmor1Item == ArmorRegister.bearSchoolPants) {
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.BearSchoolT);
				modelBipedMain.bipedRightLeg.postRender(0.0625F);
				GL11.glTranslatef(-0.15F, -0.85F, 0.0F);
				GL11.glScalef(1.15F, 1.1F, 1.15F);
				RenderPlayerArmor.WitcherLeg.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.BearSchoolT);
				modelBipedMain.bipedLeftLeg.postRender(0.0625F);
				GL11.glTranslatef(0.15F, -0.85F, 0.0F);
				GL11.glScalef(-1.15F, 1.1F, -1.15F);
				RenderPlayerArmor.WitcherLeg.render(0.0625F);
				GL11.glPopMatrix();
			}

			if (currentArmor1Item == ArmorRegister.wolfSchoolPants) {
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.WolfT);
				modelBipedMain.bipedRightLeg.postRender(0.0625F);
				GL11.glTranslatef(-0.15F, -0.85F, 0.0F);
				GL11.glScalef(1.15F, 1.1F, 1.15F);
				RenderPlayerArmor.WitcherLeg.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.WolfT);
				modelBipedMain.bipedLeftLeg.postRender(0.0625F);
				GL11.glTranslatef(0.15F, -0.85F, 0.0F);
				GL11.glScalef(-1.15F, 1.1F, -1.15F);
				RenderPlayerArmor.WitcherLeg.render(0.0625F);
				GL11.glPopMatrix();
			}

			if (currentArmor1Item == ArmorRegister.temerPants) {
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.NilfgaardArmorT);
				modelBipedMain.bipedRightLeg.postRender(0.0625F);
				GL11.glTranslatef(-0.15F, -0.9F, 0.0F);
				GL11.glScalef(1.15F, 1.1F, 1.15F);
				RenderPlayerArmor.NilfgaardLeg.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.NilfgaardArmorT);
				modelBipedMain.bipedLeftLeg.postRender(0.0625F);
				GL11.glTranslatef(0.15F, -0.9F, 0.0F);
				GL11.glScalef(-1.15F, 1.1F, 1.15F);
				RenderPlayerArmor.NilfgaardLeg.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.NilfgaardArmorLegT);
				modelBipedMain.bipedRightLeg.postRender(0.0625F);
				GL11.glTranslatef(-0.15F, -0.85F, 0.0F);
				GL11.glScalef(1.15F, 1.1F, 1.15F);
				RenderPlayerArmor.WitcherLeg.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.NilfgaardArmorLegT);
				modelBipedMain.bipedLeftLeg.postRender(0.0625F);
				GL11.glTranslatef(0.15F, -0.85F, 0.0F);
				GL11.glScalef(-1.15F, 1.1F, -1.15F);
				RenderPlayerArmor.WitcherLeg.render(0.0625F);
				GL11.glPopMatrix();
			}

			if (currentArmor1Item == ArmorRegister.catSchoolPants) {
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.CatPantsT);
				modelBipedMain.bipedRightLeg.postRender(0.0625F);
				GL11.glTranslatef(-0.15F, -0.85F, 0.0F);
				GL11.glScalef(1.05F, 1.05F, 1.05F);
				RenderPlayerArmor.CatSchoolLeg.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.CatPantsT);
				modelBipedMain.bipedLeftLeg.postRender(0.0625F);
				GL11.glTranslatef(0.15F, -0.85F, 0.0F);
				GL11.glScalef(-1.05F, 1.051F, -1.05F);
				RenderPlayerArmor.CatSchoolLeg.render(0.0625F);
				GL11.glPopMatrix();
			}

			if (currentArmor1Item == ArmorRegister.griffinSchoolPants) {
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.GriffinPantsT);
				modelBipedMain.bipedRightLeg.postRender(0.0625F);
				GL11.glTranslatef(0.15F, -0.85F, 0.0F);
				GL11.glScalef(1.15F, 1.1F, 1.15F);
				RenderPlayerArmor.WitcherRightLeg.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.GriffinPantsT);
				modelBipedMain.bipedLeftLeg.postRender(0.0625F);
				GL11.glTranslatef(-0.15F, -0.85F, 0.0F);
				GL11.glScalef(1.15F, 1.1F, 1.15F);
				RenderPlayerArmor.WitcherLeftLeg.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.GriffinArmorT);
				modelBipedMain.bipedRightLeg.postRender(0.0625F);
				GL11.glTranslatef(0.13F, -0.85F, 0.0F);
				GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
				GL11.glScalef(1.1F, 1.1F, 1.1F);
				RenderPlayerArmor.GriffinLeg.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.GriffinArmorT);
				modelBipedMain.bipedLeftLeg.postRender(0.0625F);
				GL11.glTranslatef(-0.15F, -0.85F, 0.0F);
				GL11.glScalef(1.1F, 1.1F, 1.1F);
				RenderPlayerArmor.GriffinLeg.render(0.0625F);
				GL11.glPopMatrix();
			}

			if (currentArmor1Item == ArmorRegister.hunterPants) {
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.KaerMorhenArmorPantsT);
				modelBipedMain.bipedRightLeg.postRender(0.0625F);
				GL11.glTranslatef(0.15F, -0.85F, 0.0F);
				GL11.glScalef(1.15F, 1.1F, 1.15F);
				RenderPlayerArmor.WitcherRightLeg.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.KaerMorhenArmorPantsT);
				modelBipedMain.bipedLeftLeg.postRender(0.0625F);
				GL11.glTranslatef(-0.15F, -0.85F, 0.0F);
				GL11.glScalef(1.15F, 1.1F, 1.15F);
				RenderPlayerArmor.WitcherLeftLeg.render(0.0625F);
				GL11.glPopMatrix();
			}

			if (currentArmor1Item == ArmorRegister.viperSchoolPants) {
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.ViperArmorPantsT);
				modelBipedMain.bipedRightLeg.postRender(0.0625F);
				GL11.glTranslatef(0.15F, -0.85F, 0.0F);
				GL11.glScalef(1.15F, 1.1F, 1.15F);
				RenderPlayerArmor.WitcherRightLeg.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.ViperArmorPantsT);
				modelBipedMain.bipedLeftLeg.postRender(0.0625F);
				GL11.glTranslatef(-0.15F, -0.85F, 0.0F);
				GL11.glScalef(1.15F, 1.1F, 1.15F);
				RenderPlayerArmor.WitcherLeftLeg.render(0.0625F);
				GL11.glPopMatrix();
			}
		}

		if (itemstackPlate != null) {

			Item currentArmor3Item = itemstackPlate.getItem();

			if (currentArmor3Item == ArmorRegister.bearSchoolGloves) {
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.BearSchoolT);
				modelBipedMain.bipedLeftArm.postRender(0.0625F);
				GL11.glTranslatef(-0.33F, -0.14F, 0.0F);
				GL11.glScalef(1.05F, 1.05F, 1.05F);
				RenderPlayerArmor.BearSchoolHand.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.BearSchoolT);
				modelBipedMain.bipedRightArm.postRender(0.0625F);
				GL11.glTranslatef(0.33F, -0.14F, 0.0F);
				GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
				GL11.glScalef(1.05F, 1.05F, 1.05F);
				RenderPlayerArmor.BearSchoolHand.render(0.0625F);
				GL11.glPopMatrix();
			}

			if (currentArmor3Item == ArmorRegister.bearSchoolGloves2) {
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.BearSchoolT2);
				modelBipedMain.bipedLeftArm.postRender(0.0625F);
				GL11.glTranslatef(-0.33F, -0.14F, 0.0F);
				GL11.glScalef(1.05F, 1.05F, 1.05F);
				RenderPlayerArmor.BearSchoolHand.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.BearSchoolT2);
				modelBipedMain.bipedRightArm.postRender(0.0625F);
				GL11.glTranslatef(0.33F, -0.14F, 0.0F);
				GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
				GL11.glScalef(1.05F, 1.05F, 1.05F);
				RenderPlayerArmor.BearSchoolHand.render(0.0625F);
				GL11.glPopMatrix();
			}

			if (currentArmor3Item == ArmorRegister.wolfSchoolGloves) {
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.WolfT);
				modelBipedMain.bipedLeftArm.postRender(0.0625F);
				GL11.glTranslatef(-0.33F, -0.14F, 0.0F);
				GL11.glScalef(1.05F, 1.05F, 1.05F);
				RenderPlayerArmor.WolfGlove.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.WolfT);
				modelBipedMain.bipedRightArm.postRender(0.0625F);
				GL11.glTranslatef(0.33F, -0.14F, 0.0F);
				GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
				GL11.glScalef(1.05F, 1.05F, 1.05F);
				RenderPlayerArmor.WolfGlove.render(0.0625F);
				GL11.glPopMatrix();
			}

			if (currentArmor3Item == ArmorRegister.temerGloves) {
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.NilfgaardArmorT);
				modelBipedMain.bipedLeftArm.postRender(0.0625F);
				GL11.glTranslatef(-0.33F, -0.14F, 0.0F);
				GL11.glScalef(1.05F, 1.05F, 1.05F);
				RenderPlayerArmor.NilfgaardGlove.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.NilfgaardArmorT);
				modelBipedMain.bipedRightArm.postRender(0.0625F);
				GL11.glTranslatef(0.33F, -0.14F, 0.0F);
				GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
				GL11.glScalef(1.05F, 1.05F, 1.05F);
				RenderPlayerArmor.NilfgaardGlove.render(0.0625F);
				GL11.glPopMatrix();
			}

			if (currentArmor3Item == ArmorRegister.catSchoolGloves) {
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.CatSchoolArmorT);
				modelBipedMain.bipedLeftArm.postRender(0.0625F);
				GL11.glTranslatef(-0.315F, -0.11F, 0.0F);
				GL11.glScalef(1.01F, 1.01F, 1.01F);
				RenderPlayerArmor.CatSchoolGlove.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.CatSchoolArmorT);
				modelBipedMain.bipedRightArm.postRender(0.0625F);
				GL11.glTranslatef(0.315F, -0.11F, 0.0F);
				GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
				GL11.glScalef(1.01F, 1.01F, 1.01F);
				RenderPlayerArmor.CatSchoolGlove.render(0.0625F);
				GL11.glPopMatrix();
			}

			if (currentArmor3Item == ArmorRegister.griffinSchoolGloves) {
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.GriffinArmorT);
				modelBipedMain.bipedLeftArm.postRender(0.0625F);
				GL11.glTranslatef(-0.33F, -0.19F, -0.0F);
				GL11.glScalef(1.05F, 1.05F, 1.05F);
				RenderPlayerArmor.GriffinGlove.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.GriffinArmorT);
				modelBipedMain.bipedRightArm.postRender(0.0625F);
				GL11.glTranslatef(0.35F, -0.19F, -0.0F);
				GL11.glScalef(-1.1F, 1.05F, -1.05F);
				RenderPlayerArmor.GriffinGlove.render(0.0625F);
				GL11.glPopMatrix();
			}

			if (currentArmor3Item == ArmorRegister.viperSchoolGloves) {
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.ViperArmorHandT);
				modelBipedMain.bipedLeftArm.postRender(0.0625F);
				GL11.glTranslatef(-0.1F, 0.55F, -0.13F);
				GL11.glScalef(0.58F, 0.25F, 0.55F);
				RenderPlayerArmor.WitcherModelGlove.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.ViperArmorHandT);
				modelBipedMain.bipedRightArm.postRender(0.0625F);
				GL11.glTranslatef(0.1F, 0.55F, 0.13F);
				GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
				GL11.glScalef(0.58F, 0.25F, 0.55F);
				RenderPlayerArmor.WitcherModelGlove.render(0.0625F);
				GL11.glPopMatrix();
			}

			if (currentArmor3Item == ArmorRegister.hunterGloves) {
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.KaerMorhenArmorHandT);
				modelBipedMain.bipedLeftArm.postRender(0.0625F);
				GL11.glTranslatef(-0.1F, 0.55F, -0.13F);
				GL11.glScalef(0.58F, 0.25F, 0.55F);
				RenderPlayerArmor.WitcherModelGlove.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.KaerMorhenArmorHandT);
				modelBipedMain.bipedRightArm.postRender(0.0625F);
				GL11.glTranslatef(0.1F, 0.55F, 0.13F);
				GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
				GL11.glScalef(0.58F, 0.25F, 0.55F);
				RenderPlayerArmor.WitcherModelGlove.render(0.0625F);
				GL11.glPopMatrix();
			}
		}

		if (itemstackBoots != null) {
			Item currentArmor0Item = itemstackBoots.getItem();

			if (currentArmor0Item == ArmorRegister.bearSchoolBoots2) {
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.BearSchoolBootT);
				modelBipedMain.bipedLeftLeg.postRender(0.0625F);
				GL11.glTranslatef(-0.0F, -0.73F, -0.12F);
				GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
				GL11.glScalef(1.0F, 1.0F, 1.0F);
				RenderPlayerArmor.WitcherModelBoot.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.BearSchoolBootT);
				modelBipedMain.bipedRightLeg.postRender(0.0625F);
				GL11.glTranslatef(-0.0F, -0.73F, -0.12F);
				GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
				GL11.glScalef(1.0F, 1.0F, -1.0F);
				RenderPlayerArmor.WitcherModelBoot.render(0.0625F);
				GL11.glPopMatrix();
			}

			if (currentArmor0Item == ArmorRegister.bearSchoolBoots) {
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.BearSchoolBootT);
				modelBipedMain.bipedLeftLeg.postRender(0.0625F);
				GL11.glTranslatef(-0.0F, -0.73F, -0.12F);
				GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
				GL11.glScalef(1.0F, 1.0F, 1.0F);
				RenderPlayerArmor.WitcherModelBoot.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.BearSchoolBootT);
				modelBipedMain.bipedRightLeg.postRender(0.0625F);
				GL11.glTranslatef(-0.0F, -0.73F, -0.12F);
				GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
				GL11.glScalef(1.0F, 1.0F, -1.0F);
				RenderPlayerArmor.WitcherModelBoot.render(0.0625F);
				GL11.glPopMatrix();
			}

			if (currentArmor0Item == ArmorRegister.wolfSchoolBoots) {
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.WolfBootT);
				modelBipedMain.bipedLeftLeg.postRender(0.0625F);
				GL11.glTranslatef(-0.0F, -0.73F, -0.12F);
				GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
				GL11.glScalef(1.0F, 1.0F, 1.0F);
				RenderPlayerArmor.WitcherModelBoot.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.WolfBootT);
				modelBipedMain.bipedRightLeg.postRender(0.0625F);
				GL11.glTranslatef(-0.0F, -0.73F, -0.12F);
				GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
				GL11.glScalef(1.0F, 1.0F, -1.0F);
				RenderPlayerArmor.WitcherModelBoot.render(0.0625F);
				GL11.glPopMatrix();
			}

			if (currentArmor0Item == ArmorRegister.catSchoolBoots) {
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.CatSchoolArmorT);
				modelBipedMain.bipedLeftLeg.postRender(0.0625F);
				GL11.glTranslatef(-0.094F, -0.73F, -0.02F);
				GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
				GL11.glScalef(1.01F, 1.01F, 1.01F);
				RenderPlayerArmor.CatSchoolBoot.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.CatSchoolArmorT);
				modelBipedMain.bipedRightLeg.postRender(0.0625F);
				GL11.glTranslatef(0.094F, -0.73F, -0.02F);
				GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
				GL11.glScalef(1.01F, 1.01F, -1.01F);
				RenderPlayerArmor.CatSchoolBoot.render(0.0625F);
				GL11.glPopMatrix();
			}

			if (currentArmor0Item == ArmorRegister.temerBoots) {
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.NilfgaardArmorBootT);
				modelBipedMain.bipedLeftLeg.postRender(0.0625F);
				GL11.glTranslatef(-0.0F, -0.68F, -0.12F);
				GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
				GL11.glScalef(1.0F, 1.0F, 1.0F);
				RenderPlayerArmor.WitcherModelBoot.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.NilfgaardArmorBootT);
				modelBipedMain.bipedRightLeg.postRender(0.0625F);
				GL11.glTranslatef(-0.0F, -0.68F, -0.12F);
				GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
				GL11.glScalef(1.0F, 1.0F, -1.0F);
				RenderPlayerArmor.WitcherModelBoot.render(0.0625F);
				GL11.glPopMatrix();
			}

			if (currentArmor0Item == ArmorRegister.griffinSchoolBoots) {
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.GriffinBootsT);
				modelBipedMain.bipedRightLeg.postRender(0.0625F);
				GL11.glTranslatef(-0.0F, -0.69F, -0.12F);
				GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
				GL11.glScalef(1.0F, 1.0F, -1.0F);
				RenderPlayerArmor.WitcherModelBoot.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.GriffinBootsT);
				modelBipedMain.bipedLeftLeg.postRender(0.0625F);
				GL11.glTranslatef(-0.0F, -0.69F, -0.12F);
				GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
				GL11.glScalef(1.0F, 1.0F, 1.0F);
				RenderPlayerArmor.WitcherModelBoot.render(0.0625F);
				GL11.glPopMatrix();
			}

			if (currentArmor0Item == ArmorRegister.hunterBoots) {
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.KaerMorhenArmorBootsT);
				modelBipedMain.bipedLeftLeg.postRender(0.0625F);
				GL11.glTranslatef(-0.0F, -0.73F, -0.12F);
				GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
				GL11.glScalef(1.0F, 1.0F, 1.0F);
				RenderPlayerArmor.WitcherModelBoot.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.KaerMorhenArmorBootsT);
				modelBipedMain.bipedRightLeg.postRender(0.0625F);
				GL11.glTranslatef(-0.0F, -0.73F, -0.12F);
				GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
				GL11.glScalef(1.0F, 1.0F, -1.0F);
				RenderPlayerArmor.WitcherModelBoot.render(0.0625F);
				GL11.glPopMatrix();
			}

			if (currentArmor0Item == ArmorRegister.viperSchoolBoots) {
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.ViperArmorBootsT);
				modelBipedMain.bipedLeftLeg.postRender(0.0625F);
				GL11.glTranslatef(-0.0F, -0.73F, -0.12F);
				GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
				GL11.glScalef(1.0F, 1.0F, 1.0F);
				RenderPlayerArmor.WitcherModelBoot.render(0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				textureManager.bindTexture(RenderPlayerArmor.ViperArmorBootsT);
				modelBipedMain.bipedRightLeg.postRender(0.0625F);
				GL11.glTranslatef(-0.0F, -0.73F, -0.12F);
				GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
				GL11.glScalef(1.0F, 1.0F, -1.0F);
				RenderPlayerArmor.WitcherModelBoot.render(0.0625F);
				GL11.glPopMatrix();
			}
		}
	}

	@Override
	protected void renderEquippedItems(EntityLivingBase entityliving, float f) {
		renderSpecials((EntityNPCInterface) entityliving, f);
	}

	@Override
	public void doRender(EntityLiving entityliving, double d, double d1, double d2, float f, float f1) {
		renderPlayer((EntityNPCInterface) entityliving, d, d1, d2, f, f1);
	}

	@Override
	public void doRender(Entity entity, double d, double d1, double d2, float f, float f1) {
		renderPlayer((EntityNPCInterface) entity, d, d1, d2, f, f1);
	}

}
