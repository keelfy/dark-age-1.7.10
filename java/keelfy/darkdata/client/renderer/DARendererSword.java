/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.client.renderer;

import java.util.Random;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkdata.client.DAResources;
import keelfy.darkdata.client.DAResources.Model;
import keelfy.darkdata.client.DAResources.Texture;
import keelfy.darkdata.client.models.entity.player.ModelHand;
import keelfy.darkdata.constants.EnumModelPath;
import keelfy.darkdata.constants.EnumTexturePath;
import keelfy.darkdata.items.Sword;
import keelfyutils.KUtils;
import keelfyutils.client.DisplayListWrapper;
import keelfyutils.client.KGL;
import keelfyutils.str.KString;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

/**
 * @author keelfy
 */
@SideOnly(Side.CLIENT)
public final class DARendererSword implements IItemRenderer {

	private final Minecraft MC = KGL.mc();

	public DisplayListWrapper model;
	public ResourceLocation texture;
	public ResourceLocation textureLow;

	private final ModelHand modelHand = new ModelHand();

	private boolean FinishIdleAnimation = false;

	private float handPosX = -0.124F, handPosY = 0.1F, handPosZ = -0.55F;
	private float handRotX = 64.25F, handRotY = 294.0F, handRotZ = -111.0F;
	private float rotiX, rotiY, rotiZ;

	private ItemRenderType currentRender;

	public final void genResources(final Sword sword) {
		String path = sword.getSet() + "/models/"
				+ (KString.isEmpty(sword.getOBJModel()) ? sword.getTextureName() : sword.getOBJModel()) + ".";

		model = Model.getWithDL(EnumModelPath.Sword, path + "model");
		texture = Texture.get(EnumTexturePath.Sword, path + "tex");
		textureLow = Texture.get(EnumTexturePath.Sword, path + "texlow");
	}

	public float behindTPosX = 0F, behindTPosY = 0F, behindTPosZ = 0F;
	public float behindScale = 0.55F;
	public float behindRotX = 270F, behindRotY = 35F, behindRotZ = 0F;

	public float entTPosX = -0.32F, entTPosY = -1.18F, entTPosZ = 1.08F;
	public float entScale = 1.1215F;

	public float eqTPosX = 0.82F, eqTPosY = -0.93F, eqTPosZ = -0.18F;
	public float eqScale = 1.1215F;

	public float efpTPosX = 0F, efpTPosY = 0F, efpTPosZ = 0F;
	public float efpScale = 2.0515F;

	public final void renderLow() {
		model.renderAll(textureLow);
	}

	public final void renderFull() {
		model.renderAll(texture);
	}

	@Override
	public final boolean handleRenderType(final ItemStack item, final ItemRenderType type) {
		if (KUtils.PROTECT_CLIENT) {
			switch (type) {
			case ENTITY:
				return true;
			case EQUIPPED:
				return true;
			case EQUIPPED_FIRST_PERSON:
				return true;
			case INVENTORY:
				return true;
			default:
				return false;
			}
		}
		return false;
	}

	@Override
	public final boolean shouldUseRenderHelper(final ItemRenderType type, final ItemStack item,
			final ItemRendererHelper helper) {
		return false;
	}

	@Override
	public final void renderItem(final ItemRenderType type, final ItemStack item, final Object... data) {
		if (KUtils.PROTECT_CLIENT) {

			switch (type) {
			case INVENTORY:
				DARendererRarity.renderSword(item);
				break;
			case ENTITY:
				currentRender = ItemRenderType.ENTITY;
				GL11.glPushMatrix();
				GL11.glTranslatef(0.75F + entTPosX, 0.5F + entTPosY, entTPosZ);
				KGL.bindTexture(textureLow);
				GL11.glScalef(entScale, entScale, entScale);
				GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(40.0F, 0.0F, 0.0F, 1.0F);

				if (!((EntityItem) data[1]).getEntityItem().isOnItemFrame()) {
					if (((Entity) data[1]).rotationPitch == 0.0F) {
						final Random rnd = new Random();
						((Entity) data[1]).rotationPitch = rnd.nextFloat() * 360.0F;
					}

					GL11.glRotatef(((Entity) data[1]).rotationPitch, 0.0F, 1.0F, 0.0F);
				} else {
					GL11.glTranslatef(0.1F, 0.5F, 0.0F);
					GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
				}

				this.model.renderAll();
				GL11.glPopMatrix();
				break;
			case EQUIPPED:
				currentRender = ItemRenderType.EQUIPPED;
				GL11.glPushMatrix();
				GL11.glScalef(eqScale, eqScale, eqScale);
				GL11.glRotatef(100.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(190.0F, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(100, 0, 0, 1);
				GL11.glTranslatef(-0.6F + eqTPosX, 1.2F + eqTPosY, -0.33F + eqTPosZ);
				this.renderLow();
				GL11.glPopMatrix();
				break;
			case EQUIPPED_FIRST_PERSON:
				currentRender = ItemRenderType.EQUIPPED_FIRST_PERSON;

				handPosX = 0.3F;
				handPosY = -0.8F;
				handPosZ = 0.6F;
				handRotX = -100.0F;
				handRotY = 230.0F;
				handRotZ = -270.0F;
				GL11.glPushMatrix();
				GL11.glRotatef(rotiX, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(rotiY, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(rotiZ, 0.0F, 0.0F, 1.0F);

				GL11.glPushMatrix();
				GL11.glTranslatef(-0.1F + efpTPosX, 3.0F + efpTPosY, 1.4F + efpTPosZ);
				GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(210.0F, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
				GL11.glScalef(efpScale, efpScale, efpScale);
				this.renderFull();
				GL11.glPopMatrix();

				if (Mouse.isButtonDown(1)) {
					GL11.glRotatef(45.0F, 1.0F, 0.0F, 0.0F);
					GL11.glRotatef(0.0F, 1.0F, 0.0F, 0.0F);
					GL11.glRotatef(45.0F, 1.0F, 0.0F, 0.0F);
				}

				KGL.bindTexture(DAResources.Texture_Skin_Witcher);

				GL11.glPushMatrix();
				GL11.glTranslatef(handPosX + 0.4F, handPosY + 0.35F, handPosZ);
				GL11.glRotatef(handRotX + 70.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(handRotY - 20.0F, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(handRotZ - 95.0F, 0.0F, 0.0F, 1.0F);
				GL11.glScalef(2F, 2F, 3F);
				modelHand.renderRight();
				GL11.glPopMatrix();

				GL11.glPushMatrix();
				GL11.glTranslatef(handPosX, handPosY, handPosZ);
				GL11.glRotatef(handRotX, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(handRotY, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(handRotZ, 0.0F, 0.0F, 1.0F);
				GL11.glScalef(2F, 2F, 3.5F);
				modelHand.renderRight();
				GL11.glPopMatrix();
				GL11.glPopMatrix();
			default:
				break;
			}
		}
	}

	public final void moveObjects() {
		if (currentRender == null)
			return;

		switch (currentRender) {
		case EQUIPPED_FIRST_PERSON:
			if (MC.thePlayer.moveForward == 0.0F && MC.thePlayer.moveStrafing == 0.0F && this.rotiY < 2.0F) {
				if (!this.FinishIdleAnimation) {
					this.rotiX += 0.01F * 3;
					this.rotiY += 0.01F * 3;
					this.rotiZ += 0.01F * 3;
					if (this.rotiY >= 1.8D) {
						this.FinishIdleAnimation = true;
					}
				} else {
					if (this.rotiY >= 0.0F) {
						this.rotiX -= 0.01F * 3;
						this.rotiY -= 0.01F * 3;
						this.rotiZ -= 0.01F * 3;
					}

					if (this.rotiY <= 0.1D) {
						this.FinishIdleAnimation = false;
					}
				}
			}
			break;
		default:
			break;
		}
	}
}
