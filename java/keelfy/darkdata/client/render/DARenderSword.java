package keelfy.darkdata.client.render;

import org.lwjgl.opengl.GL11;

import api.player.render.RenderPlayerAPI;
import api.player.render.RenderPlayerBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkcore.common.player.DADataManager;
import keelfy.darkcore.common.player.DAPlayerData;
import keelfy.darkdata.client.DAResources.Model;
import keelfy.darkdata.client.DAResources.Texture;
import keelfy.darkdata.client.renderer.DARendererSword;
import keelfy.darkdata.constants.EnumModelPath;
import keelfy.darkdata.constants.EnumSwordMaterial;
import keelfy.darkdata.constants.EnumTexturePath;
import keelfy.darkdata.items.Sword;
import keelfyutils.KUtils;
import keelfyutils.client.DisplayListWrapper;
import keelfyutils.client.KGL;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

/**
 * @author keelfy
 */
@SideOnly(Side.CLIENT)
public final class DARenderSword extends RenderPlayerBase {

	private final Minecraft MC = KGL.mc();

	private AbstractClientPlayer player;

	private DisplayListWrapper scabbardModel;
	private ResourceLocation scabbardTexture;

	private final float posXSteel = 0.75F;
	private final float posYSteel = 0.5F;
	private final float posZSteel = 0.0F;

	private final float posXSilver = 0.75F;
	private final float posYSilver = 0.5F;
	private final float posZSilver = 0.0F;

	private final float posXScabbard = 0.41F;
	private final float posYScabbard = 0.89F;
	private final float posZScabbard = 0.22F;

	public DARenderSword(final RenderPlayerAPI renderPlayerAPI) {
		super(renderPlayerAPI);

		if (KUtils.PROTECT_CLIENT) {
			scabbardModel = Model.getWithDL(EnumModelPath.Scabbard, "model");
			scabbardTexture = Texture.get(EnumTexturePath.Scabbard, "tex");
		}
	}

	public static final void register() {
		if (KUtils.PROTECT_CLIENT) {
			RenderPlayerAPI.register("DARenderSwordBehind", DARenderSword.class);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void renderSpecials(final AbstractClientPlayer player, final float partialTicks) {
		if (KUtils.PROTECT_CLIENT) {
			super.renderSpecials(player, partialTicks);

			this.player = player;

			final DAPlayerData dap = DADataManager.getPlayer(player);

			ItemStack stack;
			DARendererSword renderer;

			GL11.glDisable(GL11.GL_BLEND);
			GL11.glDisable(GL11.GL_LIGHTING);

			stack = player.inventory.mainInventory[2];
			if (isNeedToRender(stack)) {
				renderer = ((Sword) stack.getItem()).getRenderer();

				GL11.glPushMatrix();
				GL11.glTranslatef(posXSilver + renderer.behindTPosX, posYSilver + renderer.behindTPosY,
						posZSilver + renderer.behindTPosZ);
				super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glScalef(renderer.behindScale, renderer.behindScale, renderer.behindScale);
				GL11.glRotatef(renderer.behindRotX, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(renderer.behindRotY, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(renderer.behindRotZ, 0.0F, 0.0F, 1.0F);
				renderer.renderLow();
				GL11.glPopMatrix();
			}

			if (stack != null)
				renderScabbard(EnumSwordMaterial.SILVER);

			stack = player.inventory.mainInventory[1];
			if (isNeedToRender(stack)) {
				renderer = ((Sword) stack.getItem()).getRenderer();

				GL11.glPushMatrix();
				GL11.glTranslatef(posXSteel + renderer.behindTPosX, posYSteel + renderer.behindTPosY,
						posZSteel + renderer.behindTPosZ);
				super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glScalef(renderer.behindScale, renderer.behindScale, renderer.behindScale);
				GL11.glRotatef(renderer.behindRotX, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(renderer.behindRotY, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(renderer.behindRotZ, 0.0F, 0.0F, 1.0F);
				renderer.renderLow();
				GL11.glPopMatrix();
			}

			if (stack != null)
				renderScabbard(EnumSwordMaterial.STEEL);

			GL11.glEnable(GL11.GL_LIGHTING);
			GL11.glEnable(GL11.GL_BLEND);
		}

	}

	private void renderScabbard(EnumSwordMaterial sword) {
		if (KUtils.PROTECT_CLIENT) {
			GL11.glPushMatrix();
			final float posX = (sword == EnumSwordMaterial.SILVER ? -0.18F : 0F);
			GL11.glTranslatef(posXScabbard + posX, posYScabbard, posZScabbard + 0.04F);
			super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
			GL11.glScalef(0.55F, 0.55F, 0.55F);
			GL11.glRotatef(272.0F, 1.0F, 0.0F, 0.0F);
			GL11.glRotatef(239.0F, 0.0F, 1.0F, 0.0F);
			this.scabbardModel.renderAll(scabbardTexture);
			GL11.glPopMatrix();
		}
	}

	private boolean isNeedToRender(ItemStack stack) {
		return stack != null && stack.getItem() instanceof Sword && isNotCurrent(stack.getItem());
	}

	private boolean isNotCurrent(final Item item) {
		return player.getCurrentEquippedItem() == null || player.getCurrentEquippedItem().getItem() != item;
	}
}
