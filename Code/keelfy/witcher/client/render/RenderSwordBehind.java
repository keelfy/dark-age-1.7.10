package keelfy.witcher.client.render;

import org.lwjgl.opengl.GL11;

import api.player.render.RenderPlayerAPI;
import api.player.render.RenderPlayerBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.witcher.handler.client.ResourceHandler.Model;
import keelfy.witcher.handler.client.ResourceHandler.Model.WCM;
import keelfy.witcher.handler.client.ResourceHandler.Texture;
import keelfy.witcher.handler.client.ResourceHandler.Texture.WCT;
import keelfy.witcher.handler.registers.SwordRegister;
import keelfy.witcher.util.DAUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelCustom;

/**
 * @author keelfy
 */
@SideOnly(Side.CLIENT)
public class RenderSwordBehind extends RenderPlayerBase {

	private final IModelCustom AddanDeit = Model.getSword(SwordRegister.addanDeidth);
	private final ResourceLocation AddanDeitT = Texture.getSword(SwordRegister.addanDeidth, "texlow");
	private final IModelCustom Gvennel = Model.getSword(SwordRegister.gvennel);
	private final ResourceLocation GvennelT = Texture.getSword(SwordRegister.gvennel, "texlow");
	private final IModelCustom Disglair = Model.getSword(SwordRegister.disglair);
	private final ResourceLocation DisglairT = Texture.getSword(SwordRegister.disglair, "texlow");
	private final IModelCustom bear1 = Model.getSword(SwordRegister.bearSilver1);
	private final ResourceLocation bear1t = Texture.getSword(SwordRegister.bearSilver1, "texlow");
	private final IModelCustom bear2 = Model.getSword(SwordRegister.bearSilver2);
	private final ResourceLocation bear2t = Texture.getSword(SwordRegister.bearSilver2, "texlow");
	private final IModelCustom bear3 = Model.getSword(SwordRegister.bearSilver3);
	private final ResourceLocation bear3t = Texture.getSword(SwordRegister.bearSilver3, "texlow");
	private final IModelCustom bear4 = Model.getSword(SwordRegister.bearSilver4);
	private final ResourceLocation bear4t = Texture.getSword(SwordRegister.bearSilver4, "texlow");
	private final IModelCustom cat1 = Model.getSword(SwordRegister.catSilver1);
	private final ResourceLocation cat1t = Texture.getSword(SwordRegister.catSilver1, "texlow");
	private final IModelCustom cat2 = Model.getSword(SwordRegister.catSilver2);
	private final ResourceLocation cat2t = Texture.getSword(SwordRegister.catSilver2, "texlow");
	private final IModelCustom cat3 = Model.getSword(SwordRegister.catSilver3);
	private final ResourceLocation cat3t = Texture.getSword(SwordRegister.catSilver3, "texlow");
	private final IModelCustom cat4 = Model.getSword(SwordRegister.catSilver4);
	private final ResourceLocation cat4t = Texture.getSword(SwordRegister.catSilver4, "texlow");
	private final IModelCustom Gryphon1 = Model.getSword(SwordRegister.griffinSilver1);
	private final ResourceLocation Gryphon1t = Texture.getSword(SwordRegister.griffinSilver1, "texlow");
	private final IModelCustom Gryphon2 = Model.getSword(SwordRegister.griffinSilver2);
	private final ResourceLocation Gryphon2t = Texture.getSword(SwordRegister.griffinSilver2, "texlow");
	private final IModelCustom Gryphon3 = Model.getSword(SwordRegister.griffinSilver3);
	private final ResourceLocation Gryphon3t = Texture.getSword(SwordRegister.griffinSilver3, "texlow");
	private final IModelCustom Gryphon4 = Model.getSword(SwordRegister.griffinSilver4);
	private final ResourceLocation Gryphon4t = Texture.getSword(SwordRegister.griffinSilver4, "texlow");
	private final IModelCustom Silver1 = Model.getSword(SwordRegister.silver1);
	private final ResourceLocation Silver1t = Texture.getSword(SwordRegister.silver1, "texlow");
	private final IModelCustom Silver2 = Model.getSword(SwordRegister.erlichrad);
	private final ResourceLocation Silver2t = Texture.getSword(SwordRegister.erlichrad, "texlow");
	private final IModelCustom Silver3 = Model.getSword(SwordRegister.emmental);
	private final ResourceLocation Silver3t = Texture.getSword(SwordRegister.emmental, "texlow");
	private final IModelCustom Silver4 = Model.getSword(SwordRegister.garhwal);
	private final ResourceLocation Silver4t = Texture.getSword(SwordRegister.garhwal, "texlow");
	private final IModelCustom Silver5 = Model.getSword(SwordRegister.gvihir);
	private final ResourceLocation Silver5t = Texture.getSword(SwordRegister.gvihir, "texlow");
	private final IModelCustom Silver6 = Model.getSword(SwordRegister.phoenix);
	private final ResourceLocation Silver6t = Texture.getSword(SwordRegister.phoenix, "texlow");
	private final IModelCustom Silver7 = Model.getSword(SwordRegister.azureAnger);
	private final ResourceLocation Silver7t = Texture.getSword(SwordRegister.azureAnger, "texlow");
	private final IModelCustom Silver8 = Model.getSword(SwordRegister.claw);
	private final ResourceLocation Silver8t = Texture.getSword(SwordRegister.claw, "texlow");
	private final IModelCustom Wolf1 = Model.getSword(SwordRegister.wolfSilver1);
	private final ResourceLocation Wolf1t = Texture.getSword(SwordRegister.wolfSilver1, "texlow");
	private final IModelCustom Wolf2 = Model.getSword(SwordRegister.wolfSilver2);
	private final ResourceLocation Wolf2t = Texture.getSword(SwordRegister.wolfSilver2, "texlow");
	private final IModelCustom Wolf3 = Model.getSword(SwordRegister.wolfSilver3);
	private final ResourceLocation Wolf3t = Texture.getSword(SwordRegister.wolfSilver3, "texlow");
	private final IModelCustom Wolf4 = Model.getSword(SwordRegister.wolfSilver4);
	private final ResourceLocation Wolf4t = Texture.getSword(SwordRegister.wolfSilver4, "texlow");
	private final IModelCustom Start = Model.getSword(SwordRegister.witcherySilver);
	private final ResourceLocation StartT = Texture.getSword(SwordRegister.witcherySilver, "texlow");
	private final IModelCustom Tlareg = Model.getSword(SwordRegister.tlareg);
	private final ResourceLocation TlaregT = Texture.getSword(SwordRegister.tlareg, "texlow");
	private final IModelCustom Zirael = Model.getSword(SwordRegister.zirael);
	private final ResourceLocation ZiraelT = Texture.getSword(SwordRegister.zirael, "texlow");

	private final IModelCustom Breath = Model.getSword(SwordRegister.hardened);
	private final ResourceLocation BreathT = Texture.getSword(SwordRegister.hardened, "texlow");
	private final IModelCustom Gnomish = Model.getSword(SwordRegister.mahakam1);
	private final ResourceLocation GnomishT = Texture.getSword(SwordRegister.mahakam1, "texlow");
	private final IModelCustom Villen = Model.getSword(SwordRegister.ellander);
	private final ResourceLocation VillenT = Texture.getSword(SwordRegister.ellander, "texlow");
	private final IModelCustom Aedirn = Model.getSword(SwordRegister.aedirn);
	private final ResourceLocation AedirnT = Texture.getSword(SwordRegister.aedirn, "texlow");
	private final IModelCustom Sbear1 = Model.getSword(SwordRegister.bearSteel1);
	private final ResourceLocation Sbear1t = Texture.getSword(SwordRegister.bearSteel1, "texlow");
	private final IModelCustom Sbear2 = Model.getSword(SwordRegister.bearSteel2);
	private final ResourceLocation Sbear2t = Texture.getSword(SwordRegister.bearSteel2, "texlow");
	private final IModelCustom Sbear3 = Model.getSword(SwordRegister.bearSteel3);
	private final ResourceLocation Sbear3t = Texture.getSword(SwordRegister.bearSteel3, "texlow");
	private final IModelCustom Sbear4 = Model.getSword(SwordRegister.bearSteel4);
	private final ResourceLocation Sbear4t = Texture.getSword(SwordRegister.bearSteel4, "texlow");
	private final IModelCustom carabella = Model.getSword(SwordRegister.rustyElvin);
	private final ResourceLocation carabellaT = Texture.getSword(SwordRegister.rustyElvin, "texlow");
	private final IModelCustom Deviam = Model.getSword(SwordRegister.devine);
	private final ResourceLocation Deviamt = Texture.getSword(SwordRegister.devine, "texlow");
	private final IModelCustom Temerian = Model.getSword(SwordRegister.rusty);
	private final ResourceLocation TemerianT = Texture.getSword(SwordRegister.rusty, "texlow");
	private final IModelCustom SGryphon = Model.getSword(SwordRegister.griffinSteel1);
	private final ResourceLocation SGryphonT = Texture.getSword(SwordRegister.griffinSteel1, "texlow");
	private final IModelCustom SGryphon2 = Model.getSword(SwordRegister.griffinSteel2);
	private final ResourceLocation SGryphon2T = Texture.getSword(SwordRegister.griffinSteel2, "texlow");
	private final IModelCustom SGryphon3 = Model.getSword(SwordRegister.griffinSteel3);
	private final ResourceLocation SGryphon3T = Texture.getSword(SwordRegister.griffinSteel3, "texlow");
	private final IModelCustom SGryphon4 = Model.getSword(SwordRegister.griffinSteel4);
	private final ResourceLocation SGryphon4T = Texture.getSword(SwordRegister.griffinSteel4, "texlow");
	private final IModelCustom nilf1 = Model.getSword(SwordRegister.nilfgaardSteel2);
	private final ResourceLocation nilf1t = Texture.getSword(SwordRegister.nilfgaardSteel2, "texlow");
	private final IModelCustom nilf2 = Model.getSword(SwordRegister.vikovaro);
	private final ResourceLocation nilf2t = Texture.getSword(SwordRegister.vikovaro, "texlow");
	private final IModelCustom nilf3 = Model.getSword(SwordRegister.nazair);
	private final ResourceLocation nilf3t = Texture.getSword(SwordRegister.nazair, "texlow");
	private final IModelCustom nilf4 = Model.getSword(SwordRegister.nilfgaardSteel1);
	private final ResourceLocation nilf4t = Texture.getSword(SwordRegister.nilfgaardSteel1, "texlow");
	private final IModelCustom Novigrad = Model.getSword(SwordRegister.novigrad1);
	private final ResourceLocation NovigradT = Texture.getSword(SwordRegister.novigrad1, "texlow");
	private final IModelCustom ofir = Model.getSword(SwordRegister.ofir);
	private final ResourceLocation ofirT = Texture.getSword(SwordRegister.ofir, "texlow");
	private final IModelCustom Velen = Model.getSword(SwordRegister.temerSteel1);
	private final ResourceLocation VelenT = Texture.getSword(SwordRegister.temerSteel1, "texlow");
	private final IModelCustom Vengerberg = Model.getSword(SwordRegister.vengerberg);
	private final ResourceLocation VengerbergT = Texture.getSword(SwordRegister.vengerberg, "texlow");
	private final IModelCustom WildHunt = Model.getSword(SwordRegister.oldest);
	private final ResourceLocation WildHuntT = Texture.getSword(SwordRegister.oldest, "texlow");
	private final IModelCustom WitcherSteel = Model.getSword(SwordRegister.witcherySteel);
	private final ResourceLocation WitcherSteelT = Texture.getSword(SwordRegister.witcherySteel, "texlow");
	private final IModelCustom SWolf1 = Model.getSword(SwordRegister.wolfSteel1);
	private final ResourceLocation SWolf1t = Texture.getSword(SwordRegister.wolfSteel1, "texlow");
	private final IModelCustom SWolf2 = Model.getSword(SwordRegister.wolfSteel2);
	private final ResourceLocation SWolf2T = Texture.getSword(SwordRegister.wolfSteel2, "texlow");
	private final IModelCustom SWolf3 = Model.getSword(SwordRegister.wolfSteel3);
	private final ResourceLocation SWolf3T = Texture.getSword(SwordRegister.wolfSteel3, "texlow");
	private final IModelCustom SWolf4 = Model.getSword(SwordRegister.wolfSteel4);
	private final ResourceLocation SWolf4T = Texture.getSword(SwordRegister.wolfSteel4, "texlow");
	private final IModelCustom scabbard = Model.get(WCM.SCABBARD, "model");
	private final ResourceLocation scabbardT = Texture.get(WCT.SCABBARD, "tex");
	private final Minecraft mc = Minecraft.getMinecraft();
	protected static float tposX = 0.75F;
	protected static float tposY = 0.5F;
	protected static float tposZ = 0.0F;
	protected static float tposSX = 0.75F;
	protected static float tposSY = 0.5F;
	protected static float tposSZ = 0.0F;
	protected static float tposSXS = 0.75F;
	private EntityPlayer player;

	public RenderSwordBehind(RenderPlayerAPI renderPlayerAPI) {
		super(renderPlayerAPI);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void renderSpecials(AbstractClientPlayer par1AbstractClientPlayer, float par2) {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
		super.renderSpecials(par1AbstractClientPlayer, par2);
		this.player = par1AbstractClientPlayer;
		
		if (this.check(2, SwordRegister.addanDeidth)) {
			if(check2(SwordRegister.addanDeidth)) {
				GL11.glPushMatrix();
				GL11.glTranslatef(tposSX - 1.27F, tposSY - 0.925F, tposSZ + 0.227F);
				super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glScalef(0.55F, 0.55F, 0.55F);
				GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
				RenderManager.instance.renderEngine.bindTexture(this.AddanDeitT);
				this.AddanDeit.renderAll();
				GL11.glPopMatrix(); 
			}
			renderScabbardSteel();
		}

		if (this.check(2, SwordRegister.hardened)) {
			if(check2(SwordRegister.hardened)) {
				GL11.glPushMatrix();
				GL11.glTranslatef(tposSX - 1.315F, tposSY - 0.96F, tposSZ + 0.22F);
				super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glScalef(0.55F, 0.55F, 0.55F);
				GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
				RenderManager.instance.renderEngine.bindTexture(this.BreathT);
				this.Breath.renderAll();
				GL11.glPopMatrix();
			}
			renderScabbardSteel();
		}

		if (this.check(2, SwordRegister.gvennel)) {
			if(check2(SwordRegister.gvennel)) {
				GL11.glPushMatrix();
				GL11.glTranslatef(tposSX - 1.29F, tposSY - 1.02F, tposSZ + 0.23F);
				super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glScalef(0.55F, 0.55F, 0.55F);
				GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
				RenderManager.instance.renderEngine.bindTexture(this.GvennelT);
				this.Gvennel.renderAll();
				GL11.glPopMatrix();
			}
			renderScabbardSteel();
		}

		if (this.check(2, SwordRegister.disglair)) {
			if(check2(SwordRegister.disglair)) {
				GL11.glPushMatrix();
				GL11.glTranslatef(tposSX - 1.3F, tposSY - 0.99F, tposSZ + 0.23F);
				super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glScalef(0.55F, 0.55F, 0.55F);
				GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
				RenderManager.instance.renderEngine.bindTexture(this.DisglairT);
				this.Disglair.renderAll();
				GL11.glPopMatrix();
			}
			renderScabbardSteel();
		}

		if (this.check(2, SwordRegister.bearSilver1)) {
			if(check2(SwordRegister.bearSilver1)) {
				GL11.glPushMatrix();
				GL11.glTranslatef(tposSX - 1.235F, tposSY - 0.945F, tposSZ + 0.227F);
				super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glScalef(0.55F, 0.55F, 0.55F);
				GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
				RenderManager.instance.renderEngine.bindTexture(this.bear1t);
				this.bear1.renderAll();
				GL11.glPopMatrix();
			}
			renderScabbardSteel();
		}

		if (this.check(2, SwordRegister.bearSilver2)) {
			if(check2(SwordRegister.bearSilver2)) {
				GL11.glPushMatrix();
				GL11.glTranslatef(tposSX - 1.33F, tposSY - 1.02F, tposSZ + 0.238F);
				super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glScalef(0.55F, 0.55F, 0.55F);
				GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
				RenderManager.instance.renderEngine.bindTexture(this.bear2t);
				this.bear2.renderAll();
				GL11.glPopMatrix();
			}
			renderScabbardSteel();
		}

		if (this.check(2, SwordRegister.bearSilver3)) {
			if(check2(SwordRegister.bearSilver3)) {
				GL11.glPushMatrix();
				GL11.glTranslatef(tposSX - 1.35F, tposSY - 1.02F, tposSZ + 0.263F);
				super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glScalef(0.55F, 0.55F, 0.55F);
				GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
				RenderManager.instance.renderEngine.bindTexture(this.bear3t);
				this.bear3.renderAll();
				GL11.glPopMatrix(); 
			}
			renderScabbardSteel();
		}

		if (this.check(2, SwordRegister.bearSilver4)) {
			if(check2(SwordRegister.bearSilver4)) {
				GL11.glPushMatrix();
				GL11.glTranslatef(tposSX - 1.37F, tposSY - 1.02F, tposSZ + 0.24F);
				super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glScalef(0.55F, 0.55F, 0.55F);
				GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
				RenderManager.instance.renderEngine.bindTexture(this.bear4t);
				this.bear4.renderAll();
				GL11.glPopMatrix(); 
			}
			renderScabbardSteel();
		}

		if (this.check(2, SwordRegister.catSilver1)) {
			if(check2(SwordRegister.catSilver1)) {
				GL11.glPushMatrix();
				GL11.glTranslatef(tposSX - 1.29F, tposSY - 1.02F, tposSZ + 0.23F);
				super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glScalef(0.55F, 0.55F, 0.55F);
				GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
				RenderManager.instance.renderEngine.bindTexture(this.cat1t);
				this.cat1.renderAll();
				GL11.glPopMatrix(); 
			}
			renderScabbardSteel();
		}

		if (this.check(2, SwordRegister.catSilver2)) {
			if(check2(SwordRegister.catSilver2)) {
				GL11.glPushMatrix();
				GL11.glTranslatef(tposSX - 1.305F, tposSY - 0.99F, tposSZ + 0.23F);
				super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glScalef(0.55F, 0.55F, 0.55F);
				GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
				RenderManager.instance.renderEngine.bindTexture(this.cat2t);
				this.cat2.renderAll();
				GL11.glPopMatrix(); 
			}
			renderScabbardSteel();
		}

		if (this.check(2, SwordRegister.catSilver3)) {
			if(check2(SwordRegister.catSilver3)) {
				GL11.glPushMatrix();
				GL11.glTranslatef(tposSX - 1.315F, tposSY - 0.98F, tposSZ + 0.26F);
				super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glScalef(0.55F, 0.55F, 0.55F);
				GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
				RenderManager.instance.renderEngine.bindTexture(this.cat3t);
				this.cat3.renderAll();
				GL11.glPopMatrix();
			}
			renderScabbardSteel();
		}

		if (this.check(2, SwordRegister.catSilver4)) {
			if(check2(SwordRegister.catSilver4)) {
				GL11.glPushMatrix();
				GL11.glTranslatef(tposSX - 1.339F, tposSY - 1.0F, tposSZ + 0.23F);
				super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glScalef(0.55F, 0.55F, 0.55F);
				GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
				RenderManager.instance.renderEngine.bindTexture(this.cat4t);
				this.cat4.renderAll();
				GL11.glPopMatrix(); 
			}
			renderScabbardSteel();
		}

		if (this.check(2, SwordRegister.griffinSilver1)) {
			if(check2(SwordRegister.griffinSilver1)) {
				GL11.glPushMatrix();
				GL11.glTranslatef(tposSX - 1.29F, tposSY - 1.03F, tposSZ + 0.23F);
				super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glScalef(0.55F, 0.55F, 0.55F);
				GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
				RenderManager.instance.renderEngine.bindTexture(this.Gryphon1t);
				this.Gryphon1.renderAll();
				GL11.glPopMatrix(); 
			}
			renderScabbardSteel();
		}

		if (this.check(2, SwordRegister.griffinSilver2)) {
			if(check2(SwordRegister.griffinSilver2)) {
				GL11.glPushMatrix();
				GL11.glTranslatef(tposSX - 1.3F, tposSY - 0.98F, tposSZ + 0.23F);
				super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glScalef(0.55F, 0.55F, 0.55F);
				GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
				RenderManager.instance.renderEngine.bindTexture(this.Gryphon2t);
				this.Gryphon2.renderAll();
				GL11.glPopMatrix(); 
			}
			renderScabbardSteel();
		}

		if (this.check(2, SwordRegister.griffinSilver3)) {
			if(check2(SwordRegister.griffinSilver3)) {
				GL11.glPushMatrix();
				GL11.glTranslatef(tposSX - 1.31F, tposSY - 0.98F, tposSZ + 0.26F);
				super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glScalef(0.55F, 0.55F, 0.55F);
				GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
				RenderManager.instance.renderEngine.bindTexture(this.Gryphon3t);
				this.Gryphon3.renderAll();
				GL11.glPopMatrix();
			}
			renderScabbardSteel();
		}

		if (this.check(2, SwordRegister.griffinSilver4)) {
			if(check2(SwordRegister.griffinSilver4)) {
				GL11.glPushMatrix();
				GL11.glTranslatef(tposSX - 1.33F, tposSY - 0.98F, tposSZ + 0.23F);
				super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glScalef(0.55F, 0.55F, 0.55F);
				GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
				RenderManager.instance.renderEngine.bindTexture(this.Gryphon4t);
				this.Gryphon4.renderAll();
				GL11.glPopMatrix();
			}
			renderScabbardSteel();
		}

		if (this.check(2, SwordRegister.silver1)) {
			if(check2(SwordRegister.silver1)) {
				GL11.glPushMatrix();
				GL11.glTranslatef(tposSX - 1.29F, tposSY - 1.01F, tposSZ + 0.22F);
				super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glScalef(0.55F, 0.55F, 0.55F);
				GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
				RenderManager.instance.renderEngine.bindTexture(this.Silver1t);
				this.Silver1.renderAll();
				GL11.glPopMatrix();
			}
			renderScabbardSteel();
		}

		if (this.check(2, SwordRegister.erlichrad)) {
			if(check2(SwordRegister.erlichrad)) {
				GL11.glPushMatrix();
				GL11.glTranslatef(tposSX - 1.29F, tposSY - 1.01F, tposSZ + 0.22F);
				super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glScalef(0.55F, 0.55F, 0.55F);
				GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
				RenderManager.instance.renderEngine.bindTexture(this.Silver2t);
				this.Silver2.renderAll();
				GL11.glPopMatrix();
			}
			renderScabbardSteel();
		}

		if (this.check(2, SwordRegister.emmental)) {
			if(check2(SwordRegister.emmental)) {
				GL11.glPushMatrix();
				GL11.glTranslatef(tposSX - 1.34F, tposSY - 1.04F, tposSZ + 0.22F);
				super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glScalef(0.55F, 0.55F, 0.55F);
				GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
				RenderManager.instance.renderEngine.bindTexture(this.Silver3t);
				this.Silver3.renderAll();
				GL11.glPopMatrix();
			}
			renderScabbardSteel();
		}

		if (this.check(2, SwordRegister.garhwal)) {
			if(check2(SwordRegister.garhwal)) {
				GL11.glPushMatrix();
				GL11.glTranslatef(tposSX - 1.357F, tposSY - 1.06F, tposSZ + 0.222F);
				super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glScalef(0.55F, 0.55F, 0.55F);
				GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
				RenderManager.instance.renderEngine.bindTexture(this.Silver4t);
				this.Silver4.renderAll();
				GL11.glPopMatrix();
			}
			renderScabbardSteel();
		}

		if (this.check(2, SwordRegister.gvihir)) {
			if(check2(SwordRegister.gvihir)) {
				GL11.glPushMatrix();
				GL11.glTranslatef(tposSX - 1.36F, tposSY - 1.04F, tposSZ + 0.263F);
				super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glScalef(0.55F, 0.55F, 0.55F);
				GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
				RenderManager.instance.renderEngine.bindTexture(this.Silver5t);
				this.Silver5.renderAll();
				GL11.glPopMatrix();
			}
			renderScabbardSteel();
		}

		if (this.check(2, SwordRegister.phoenix)) {
			if(check2(SwordRegister.phoenix)) {
				GL11.glPushMatrix();
				GL11.glTranslatef(tposSX - 1.36F, tposSY - 1.04F, tposSZ + 0.263F);
				super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glScalef(0.55F, 0.55F, 0.55F);
				GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
				RenderManager.instance.renderEngine.bindTexture(this.Silver6t);
				this.Silver6.renderAll();
				GL11.glPopMatrix();
			}
			renderScabbardSteel();
		}

		if (this.check(2, SwordRegister.azureAnger)) {
			if(check2(SwordRegister.azureAnger)) {
				GL11.glPushMatrix();
				GL11.glTranslatef(tposSX - 1.37F, tposSY - 1.03F, tposSZ + 0.233F);
				super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glScalef(0.55F, 0.55F, 0.55F);
				GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
				RenderManager.instance.renderEngine.bindTexture(this.Silver7t);
				this.Silver7.renderAll();
				GL11.glPopMatrix();
			}
			renderScabbardSteel();
		}

		if (this.check(2, SwordRegister.claw)) {
			if(check2(SwordRegister.claw)) {
				GL11.glPushMatrix();
				GL11.glTranslatef(tposSX - 1.37F, tposSY - 1.03F, tposSZ + 0.233F);
				super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glScalef(0.55F, 0.55F, 0.55F);
				GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
				RenderManager.instance.renderEngine.bindTexture(this.Silver8t);
				this.Silver8.renderAll();
				GL11.glPopMatrix();
			}
			renderScabbardSteel();
		}

		if (this.check(2, SwordRegister.wolfSilver1)) {
			if(check2(SwordRegister.wolfSilver1)) {
				GL11.glPushMatrix();
				GL11.glTranslatef(tposSX - 1.29F, tposSY - 1.024F, tposSZ + 0.23F);
				super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glScalef(0.55F, 0.55F, 0.55F);
				GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
				RenderManager.instance.renderEngine.bindTexture(this.Wolf1t);
				this.Wolf1.renderAll();
				GL11.glPopMatrix();
			}
			renderScabbardSteel();
		}

		if (this.check(2, SwordRegister.wolfSilver2)) {
			if(check2(SwordRegister.wolfSilver2)) {
				GL11.glPushMatrix();
				GL11.glTranslatef(tposSX - 1.33F, tposSY - 1.024F, tposSZ + 0.23F);
				super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glScalef(0.55F, 0.55F, 0.55F);
				GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
				RenderManager.instance.renderEngine.bindTexture(this.Wolf2t);
				this.Wolf2.renderAll();
				GL11.glPopMatrix();
			}
			renderScabbardSteel();
		}

		if (this.check(2, SwordRegister.wolfSilver3)) {
			if(check2(SwordRegister.wolfSilver3)) {
				GL11.glPushMatrix();
				GL11.glTranslatef(tposSX - 1.35F, tposSY - 1.02F, tposSZ + 0.263F);
				super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glScalef(0.55F, 0.55F, 0.55F);
				GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
				RenderManager.instance.renderEngine.bindTexture(this.Wolf3t);
				this.Wolf3.renderAll();
				GL11.glPopMatrix();
			}
			renderScabbardSteel();
		}

		if (this.check(2, SwordRegister.wolfSilver4)) {
			if(check2(SwordRegister.wolfSilver4)) {
				GL11.glPushMatrix();
				GL11.glTranslatef(tposSX - 1.365F, tposSY - 1.02F, tposSZ + 0.23F);
				super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glScalef(0.55F, 0.55F, 0.55F);
				GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
				RenderManager.instance.renderEngine.bindTexture(this.Wolf4t);
				this.Wolf4.renderAll();
				GL11.glPopMatrix();
			}
			renderScabbardSteel();
		}

		if (this.check(2, SwordRegister.witcherySilver)) {
			if(check2(SwordRegister.witcherySilver)) {
				GL11.glPushMatrix();
				GL11.glTranslatef(tposSX - 1.315F, tposSY - 0.96F, tposSZ + 0.21F);
				super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glScalef(0.55F, 0.55F, 0.55F);
				GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
				RenderManager.instance.renderEngine.bindTexture(this.StartT);
				this.Start.renderAll();
				GL11.glPopMatrix();
			}
			renderScabbardSteel();
		}

		if (this.check(2, SwordRegister.tlareg)) {
			if(check2(SwordRegister.tlareg)) {
				GL11.glPushMatrix();
				GL11.glTranslatef(tposSX - 1.31F, tposSY - 0.925F, tposSZ + 0.221F);
				super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glScalef(0.55F, 0.55F, 0.55F);
				GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
				RenderManager.instance.renderEngine.bindTexture(this.TlaregT);
				this.Tlareg.renderAll();
				GL11.glPopMatrix();
			}
			renderScabbardSteel();
		}

		if (this.check(2, SwordRegister.zirael)) {
			if(check2(SwordRegister.zirael)) {
				GL11.glPushMatrix();
				GL11.glTranslatef(tposSX - 1.27F, tposSY - 0.925F, tposSZ + 0.227F);
				super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glScalef(0.55F, 0.55F, 0.55F);
				GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
				RenderManager.instance.renderEngine.bindTexture(this.ZiraelT);
				this.Zirael.renderAll();
				GL11.glPopMatrix();
			}
			renderScabbardSteel();
		}

		if (this.check(1, SwordRegister.mahakam1)) {
			if(check2(SwordRegister.mahakam1)) {
				GL11.glPushMatrix();
				GL11.glTranslatef(tposSXS - 1.1F, tposSY - 0.96F, tposSZ + 0.22F);
				super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glScalef(0.55F, 0.55F, 0.55F);
				GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
				RenderManager.instance.renderEngine.bindTexture(this.GnomishT);
				this.Gnomish.renderAll();
				GL11.glPopMatrix();
			}
			renderScabbardSilver();
		}

		if (this.check(1, SwordRegister.mahakam2)) {
			if(check2(SwordRegister.mahakam2)) {
				GL11.glPushMatrix();
				GL11.glTranslatef(tposSXS - 1.1F, tposSY - 0.96F, tposSZ + 0.22F);
				super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glScalef(0.55F, 0.55F, 0.55F);
				GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
				RenderManager.instance.renderEngine.bindTexture(this.GnomishT);
				this.Gnomish.renderAll();
				GL11.glPopMatrix();
			}
			renderScabbardSilver();
		}

		if (this.check(1, SwordRegister.ellander)) {
			if(check2(SwordRegister.ellander)) {
				GL11.glPushMatrix();
				GL11.glTranslatef(tposSXS - 1.11F, tposSY - 0.98F, tposSZ + 0.22F);
				super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glScalef(0.55F, 0.55F, 0.55F);
				GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
				RenderManager.instance.renderEngine.bindTexture(this.GnomishT);
				this.Gnomish.renderAll();
				GL11.glPopMatrix();
			}
			renderScabbardSilver();
		}

		if (this.check(1, SwordRegister.irony)) {
			if(check2(SwordRegister.irony)) {
				GL11.glPushMatrix();
				GL11.glTranslatef(tposSXS - 1.13F, tposSY - 1.02F, tposSZ + 0.22F);
				super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glScalef(0.55F, 0.55F, 0.55F);
				GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
				RenderManager.instance.renderEngine.bindTexture(this.AedirnT);
				this.Aedirn.renderAll();
				GL11.glPopMatrix();
			}
			renderScabbardSilver();
		}

		if (this.check(1, SwordRegister.aedirn)) {
			if(check2(SwordRegister.aedirn)) {
				GL11.glPushMatrix();
				GL11.glTranslatef(tposSXS - 1.13F, tposSY - 1.02F, tposSZ + 0.22F);
				super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glScalef(0.55F, 0.55F, 0.55F);
				GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
				RenderManager.instance.renderEngine.bindTexture(this.AedirnT);
				this.Aedirn.renderAll();
				GL11.glPopMatrix();
			}
			renderScabbardSilver();
		}

		if (this.check(1, SwordRegister.bearSteel1)) {
			if(check2(SwordRegister.bearSteel1)) {
				GL11.glPushMatrix();
				GL11.glTranslatef(tposSXS - 1.13F, tposSY - 1.02F, tposSZ + 0.22F);
				super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glScalef(0.55F, 0.55F, 0.55F);
				GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
				RenderManager.instance.renderEngine.bindTexture(this.Sbear1t);
				this.Sbear1.renderAll();
				GL11.glPopMatrix();
			}
			renderScabbardSilver();
		}

		if (this.check(1, SwordRegister.bearSteel2)) {
			if(check2(SwordRegister.bearSteel2)) {
				GL11.glPushMatrix();
				GL11.glTranslatef(tposSXS - 1.13F, tposSY - 1.02F, tposSZ + 0.22F);
				super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glScalef(0.55F, 0.55F, 0.55F);
				GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
				RenderManager.instance.renderEngine.bindTexture(this.Sbear2t);
				this.Sbear2.renderAll();
				GL11.glPopMatrix();
			}
			renderScabbardSilver();
		}

		if (this.check(1, SwordRegister.bearSteel3)) {
			if(check2(SwordRegister.bearSteel3)) {
				GL11.glPushMatrix();
				GL11.glTranslatef(tposSXS - 1.17F, tposSY - 1.02F, tposSZ + 0.255F);
				super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glScalef(0.55F, 0.55F, 0.55F);
				GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
				RenderManager.instance.renderEngine.bindTexture(this.Sbear3t);
				this.Sbear3.renderAll();
				GL11.glPopMatrix();
			}
			renderScabbardSilver();
		}

		if (this.check(1, SwordRegister.bearSteel4)) {
			if(check2(SwordRegister.bearSteel4)) {
				GL11.glPushMatrix();
				GL11.glTranslatef(tposSXS - 1.16F, tposSY - 1.02F, tposSZ + 0.235F);
				super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glScalef(0.55F, 0.55F, 0.55F);
				GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
				RenderManager.instance.renderEngine.bindTexture(this.Sbear4t);
				this.Sbear4.renderAll();
				GL11.glPopMatrix();
			}
			renderScabbardSilver();
		}

		if (this.check(1, SwordRegister.rustyElvin)) {
			if(check2(SwordRegister.rustyElvin)) {
				GL11.glPushMatrix();
				GL11.glTranslatef(tposSXS - 1.05F, tposSY - 0.32F, tposSZ - 0.42F);
				super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glScalef(0.55F, 0.55F, 0.55F);
				GL11.glRotatef(320.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(-90.0F, 0.0F, 0.0F, 1.0F);
				RenderManager.instance.renderEngine.bindTexture(this.carabellaT);
				this.carabella.renderAll();
				GL11.glPopMatrix();
			}
			renderScabbardSilver();
		}

		if (this.check(1, SwordRegister.elvin)) {
			if(check2(SwordRegister.elvin)) {
				GL11.glPushMatrix();
				GL11.glTranslatef(tposSXS - 1.18F, tposSY - 1.02F, tposSZ + 0.24F);
				super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glScalef(0.55F, 0.55F, 0.55F);
				GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
				RenderManager.instance.renderEngine.bindTexture(this.Deviamt);
				this.Deviam.renderAll();
				GL11.glPopMatrix();
			}
			renderScabbardSilver();
		}

		if (this.check(1, SwordRegister.rusty)) {
			if(check2(SwordRegister.rusty)) {
				GL11.glPushMatrix();
				GL11.glTranslatef(-0.34F, -0.2F, 0.2F);
				super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glScalef(0.0175F, 0.0175F, 0.0175F);
				GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
				RenderManager.instance.renderEngine.bindTexture(this.TemerianT);
				this.Temerian.renderAll();
				GL11.glPopMatrix();
			}
			renderScabbardSilver();
		}

		if (this.check(1, SwordRegister.griffinSteel1)) {
			if(check2(SwordRegister.griffinSteel1)) {
				GL11.glPushMatrix();
				GL11.glTranslatef(tposSXS - 1.14F, tposSY - 0.96F, tposSZ + 0.22F);
				super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glScalef(0.55F, 0.55F, 0.55F);
				GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
				RenderManager.instance.renderEngine.bindTexture(this.SGryphonT);
				this.SGryphon.renderAll();
				GL11.glPopMatrix();
			}
			renderScabbardSilver();
		}

		if (this.check(1, SwordRegister.griffinSteel2)) {
			if(check2(SwordRegister.griffinSteel2)) {
				GL11.glPushMatrix();
				GL11.glTranslatef(tposSXS - 1.14F, tposSY - 0.96F, tposSZ + 0.22F);
				super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glScalef(0.55F, 0.55F, 0.55F);
				GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
				RenderManager.instance.renderEngine.bindTexture(this.SGryphon2T);
				this.SGryphon2.renderAll();
				GL11.glPopMatrix();
			}
			renderScabbardSilver();
		}

		if (this.check(1, SwordRegister.griffinSteel3)) {
			if(check2(SwordRegister.griffinSteel3)) {
				GL11.glPushMatrix();
				GL11.glTranslatef(tposSXS - 1.14F, tposSY - 0.96F, tposSZ + 0.22F);
				super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glScalef(0.55F, 0.55F, 0.55F);
				GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
				RenderManager.instance.renderEngine.bindTexture(this.SGryphon3T);
				this.SGryphon3.renderAll();
				GL11.glPopMatrix();
			}
			renderScabbardSilver();
		}

		if (this.check(1, SwordRegister.griffinSteel4)) {
			if(check2(SwordRegister.griffinSteel4)) {
				GL11.glPushMatrix();
				GL11.glTranslatef(tposSXS - 1.14F, tposSY - 0.96F, tposSZ + 0.22F);
				super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glScalef(0.55F, 0.55F, 0.55F);
				GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
				RenderManager.instance.renderEngine.bindTexture(this.SGryphon4T);
				this.SGryphon4.renderAll();
				GL11.glPopMatrix();
			}
			renderScabbardSilver();
		}

		if (this.check(1, SwordRegister.nilfgaardSteel1)) {
			if(check2(SwordRegister.nilfgaardSteel1)) {
				GL11.glPushMatrix();
				GL11.glTranslatef(tposSXS - 1.13F, tposSY - 1.02F, tposSZ + 0.22F);
				super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glScalef(0.55F, 0.55F, 0.55F);
				GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
				RenderManager.instance.renderEngine.bindTexture(this.nilf1t);
				this.nilf1.renderAll();
				GL11.glPopMatrix();
			}
			renderScabbardSilver();
		}

		if (this.check(1, SwordRegister.vikovaro)) {
			if(check2(SwordRegister.vikovaro)) {
				GL11.glPushMatrix();
				GL11.glTranslatef(tposSXS - 1.13F, tposSY - 1.02F, tposSZ + 0.22F);
				super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glScalef(0.55F, 0.55F, 0.55F);
				GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
				RenderManager.instance.renderEngine.bindTexture(this.nilf2t);
				this.nilf2.renderAll();
				GL11.glPopMatrix();
			}
			renderScabbardSilver();
		}

		if (this.check(1, SwordRegister.nazair)) {
			if(check2(SwordRegister.nazair)) {
				GL11.glPushMatrix();
				GL11.glTranslatef(tposSXS - 1.13F, tposSY - 1.02F, tposSZ + 0.22F);
				super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glScalef(0.55F, 0.55F, 0.55F);
				GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
				RenderManager.instance.renderEngine.bindTexture(this.nilf3t);
				this.nilf3.renderAll();
				GL11.glPopMatrix();
			}
			renderScabbardSilver();
		}

		if (this.check(1, SwordRegister.nilfgaardSteel2)) {
			if(check2(SwordRegister.nilfgaardSteel2)) {
				GL11.glPushMatrix();
				GL11.glTranslatef(tposSXS - 1.13F, tposSY - 1.02F, tposSZ + 0.22F);
				super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glScalef(0.55F, 0.55F, 0.55F);
				GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
				RenderManager.instance.renderEngine.bindTexture(this.nilf4t);
				this.nilf4.renderAll();
				GL11.glPopMatrix();
			}
			renderScabbardSilver();
		}

		if (this.check(1, SwordRegister.novigrad1)) {
			if(check2(SwordRegister.novigrad1)) {
				GL11.glPushMatrix();
				GL11.glTranslatef(tposSXS - 1.13F, tposSY - 1.02F, tposSZ + 0.22F);
				super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glScalef(0.55F, 0.55F, 0.55F);
				GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
				RenderManager.instance.renderEngine.bindTexture(this.NovigradT);
				this.Novigrad.renderAll();
				GL11.glPopMatrix();
			}
			renderScabbardSilver();
		}

		if (this.check(1, SwordRegister.novigrad2)) {
			if(check2(SwordRegister.novigrad2)) {
				GL11.glPushMatrix();
				GL11.glTranslatef(tposSXS - 1.13F, tposSY - 1.02F, tposSZ + 0.22F);
				super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glScalef(0.55F, 0.55F, 0.55F);
				GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
				RenderManager.instance.renderEngine.bindTexture(this.NovigradT);
				this.Novigrad.renderAll();
				GL11.glPopMatrix();
			}
			renderScabbardSilver();
		}

		if (this.check(1, SwordRegister.novigrad3)) {
			if(check2(SwordRegister.novigrad3)) {
				GL11.glPushMatrix();
				GL11.glTranslatef(tposSXS - 1.13F, tposSY - 1.02F, tposSZ + 0.22F);
				super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glScalef(0.55F, 0.55F, 0.55F);
				GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
				RenderManager.instance.renderEngine.bindTexture(this.NovigradT);
				this.Novigrad.renderAll();
				GL11.glPopMatrix();
			}
			renderScabbardSilver();
		}

		if (this.check(1, SwordRegister.ofir)) {
			if(check2(SwordRegister.ofir)) {
				GL11.glPushMatrix();
				GL11.glTranslatef(tposSXS - 1.13F, tposSY - 1.02F, tposSZ + 0.22F);
				super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glScalef(0.55F, 0.55F, 0.55F);
				GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
				RenderManager.instance.renderEngine.bindTexture(this.ofirT);
				this.ofir.renderAll();
				GL11.glPopMatrix();
			}
			renderScabbardSilver();
		}

		if (this.check(1, SwordRegister.temerSteel1)) {
			if(check2(SwordRegister.temerSteel1)) {
				GL11.glPushMatrix();
				GL11.glTranslatef(tposSXS - 1.15F, tposSY - 1.06F, tposSZ + 0.22F);
				super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glScalef(0.55F, 0.55F, 0.55F);
				GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
				RenderManager.instance.renderEngine.bindTexture(this.VelenT);
				this.Velen.renderAll();
				GL11.glPopMatrix();
			}
			renderScabbardSilver();
		}

		if (this.check(1, SwordRegister.temerSteel2)) {
			if(check2(SwordRegister.temerSteel2)) {
				GL11.glPushMatrix();
				GL11.glTranslatef(tposSXS - 1.15F, tposSY - 1.06F, tposSZ + 0.22F);
				super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glScalef(0.55F, 0.55F, 0.55F);
				GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
				RenderManager.instance.renderEngine.bindTexture(this.VelenT);
				this.Velen.renderAll();
				GL11.glPopMatrix();
			}
			renderScabbardSilver();
		}

		if (this.check(1, SwordRegister.temerSteel3)) {
			if(check2(SwordRegister.temerSteel3)) {
				GL11.glPushMatrix();
				GL11.glTranslatef(tposSXS - 1.15F, tposSY - 1.06F, tposSZ + 0.22F);
				super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glScalef(0.55F, 0.55F, 0.55F);
				GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
				RenderManager.instance.renderEngine.bindTexture(this.VelenT);
				this.Velen.renderAll();
				GL11.glPopMatrix();
			}
			renderScabbardSilver();
		}

		if (this.check(1, SwordRegister.maribor)) {
			if(check2(SwordRegister.maribor)) {
				GL11.glPushMatrix();
				GL11.glTranslatef(tposSXS - 1.13F, tposSY - 1.02F, tposSZ + 0.22F);
				super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glScalef(0.55F, 0.55F, 0.55F);
				GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
				RenderManager.instance.renderEngine.bindTexture(this.VengerbergT);
				this.Vengerberg.renderAll();
				GL11.glPopMatrix();
			}
			renderScabbardSilver();
		}

		if (this.check(1, SwordRegister.vengerberg)) {
			if(check2(SwordRegister.vengerberg)) {
				GL11.glPushMatrix();
				GL11.glTranslatef(tposSXS - 1.13F, tposSY - 1.02F, tposSZ + 0.22F);
				super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glScalef(0.55F, 0.55F, 0.55F);
				GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
				RenderManager.instance.renderEngine.bindTexture(this.VengerbergT);
				this.Vengerberg.renderAll();
				GL11.glPopMatrix();
			}
			renderScabbardSilver();
		}

		if (this.check(1, SwordRegister.oldest)) {
			if(check2(SwordRegister.oldest)) {
				GL11.glPushMatrix();
				GL11.glTranslatef(tposSXS - 1.13F, tposSY - 1.02F, tposSZ + 0.22F);
				super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glScalef(0.55F, 0.55F, 0.55F);
				GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
				RenderManager.instance.renderEngine.bindTexture(this.WildHuntT);
				this.WildHunt.renderAll();
				GL11.glPopMatrix();
			}
			renderScabbardSilver();
		}

		if (this.check(1, SwordRegister.wolfSteel1)) {
			if(check2(SwordRegister.wolfSteel1)) {
				GL11.glPushMatrix();
				GL11.glTranslatef(tposSXS - 1.16F, tposSY - 1.04F, tposSZ + 0.22F);
				super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glScalef(0.5F, 0.5F, 0.5F);
				GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
				RenderManager.instance.renderEngine.bindTexture(this.SWolf1t);
				this.SWolf1.renderAll();
				GL11.glPopMatrix();
			}
			renderScabbardSilver();
		}

		if (this.check(1, SwordRegister.wolfSteel2)) {
			if(check2(SwordRegister.wolfSteel2)) {
				GL11.glPushMatrix();
				GL11.glTranslatef(tposSXS - 1.14F, tposSY - 1.04F, tposSZ + 0.22F);
				super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glScalef(0.55F, 0.55F, 0.55F);
				GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
				RenderManager.instance.renderEngine.bindTexture(this.SWolf2T);
				this.SWolf2.renderAll();
				GL11.glPopMatrix();
			}
			renderScabbardSilver();
		}

		if (this.check(1, SwordRegister.wolfSteel3)) {
			if(check2(SwordRegister.wolfSteel3)) {
				GL11.glPushMatrix();
				GL11.glTranslatef(tposSXS - 1.16F, tposSY - 1.04F, tposSZ + 0.22F);
				super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glScalef(0.55F, 0.55F, 0.55F);
				GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
				RenderManager.instance.renderEngine.bindTexture(this.SWolf3T);
				this.SWolf3.renderAll();
				GL11.glPopMatrix();
			}
			renderScabbardSilver();
		}

		if (this.check(1, SwordRegister.wolfSteel4)) {
			if(check2(SwordRegister.wolfSteel4)) {
				GL11.glPushMatrix();
				GL11.glTranslatef(tposSXS - 1.17F, tposSY - 1.08F, tposSZ + 0.222F);
				super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glScalef(0.55F, 0.55F, 0.55F);
				GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
				RenderManager.instance.renderEngine.bindTexture(this.SWolf4T);
				this.SWolf4.renderAll();
				GL11.glPopMatrix();
			}
			renderScabbardSilver();
		}

		if (this.check(2, SwordRegister.silver2)) {
			if(check2(SwordRegister.silver2)) {
				GL11.glPushMatrix();
				GL11.glTranslatef(tposSX - 1.37F, tposSY - 1.03F, tposSZ + 0.233F);
				super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glScalef(0.55F, 0.55F, 0.55F);
				GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
				RenderManager.instance.renderEngine.bindTexture(this.Silver7t);
				this.Silver7.renderAll();
				GL11.glPopMatrix();
			}
			renderScabbardSteel();
		}

		
		if (this.check(1, SwordRegister.devine)) {
			if(check2(SwordRegister.devine)) {
				GL11.glPushMatrix();
				GL11.glTranslatef(tposSXS - 0.98F, tposSY - 0.02F, tposSZ - 0.54F);
				super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
				GL11.glScalef(0.55F, 0.55F, 0.55F);
				GL11.glRotatef(320.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
				RenderManager.instance.renderEngine.bindTexture(this.Deviamt);
				this.Deviam.renderAll();
				GL11.glPopMatrix();
			}
			renderScabbardSilver();
		}
		}
	}

	private void renderScabbardSteel() {
		GL11.glPushMatrix();
		GL11.glTranslatef(tposSX - 0.52F, tposSY + 0.39F, tposSZ + 0.225F);
		super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
		GL11.glScalef(0.55F, 0.55F, 0.55F);
		GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef(239.0F, 0.0F, 1.0F, 0.0F);
		RenderManager.instance.renderEngine.bindTexture(this.scabbardT);
		this.scabbard.renderAll();
		GL11.glPopMatrix();
	}
	
	private void renderScabbardSilver() {
		GL11.glPushMatrix();
		GL11.glTranslatef(tposSX - 0.34F, tposSY + 0.39F, tposSZ + 0.225F);
		super.renderPlayer.modelBipedMain.bipedBody.postRender(0.0625F);
		GL11.glScalef(0.55F, 0.55F, 0.55F);
		GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef(239.0F, 0.0F, 1.0F, 0.0F);
		RenderManager.instance.renderEngine.bindTexture(this.scabbardT);
		this.scabbard.renderAll();
		GL11.glPopMatrix();
	}
	
	public boolean check(int slot, Item item) {
		return player.inventory.getStackInSlot(slot) != null && player.inventory.getStackInSlot(slot).getItem() == item;
	}
	
	public boolean check2(Item item) {
		return player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() != item || player.getCurrentEquippedItem() == null;
	}
}
