package keelfy.witcher.client.renderer;

import cpw.mods.fml.client.registry.RenderingRegistry;
import keelfy.witcher.entity.sign.ISign.Sign;
import keelfy.witcher.entity.sign.SignAard;
import keelfy.witcher.entity.sign.SignAksi;
import keelfy.witcher.entity.sign.SignIgni;
import keelfy.witcher.entity.sign.SignIrden;
import keelfy.witcher.handler.registers.SwordRegister;
import keelfy.witcher.util.DAUtil;
import net.minecraftforge.client.MinecraftForgeClient;

/**
 * @author keelfy
 */
public class RendererRegister {
	   
	public RendererRegister() {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			registerSilverSwords();
			registerSteelSwords();
			registerEntity();
		}
	}
	
	private void registerEntity() {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			RenderingRegistry.registerEntityRenderingHandler(SignIrden.class, new RendererSign(Sign.IRDEN));
			RenderingRegistry.registerEntityRenderingHandler(SignAksi.class, new RendererSign(Sign.AKSI));
			RenderingRegistry.registerEntityRenderingHandler(SignAard.class, new RendererSign(Sign.AARD));
			RenderingRegistry.registerEntityRenderingHandler(SignIgni.class, new RendererSign(Sign.IGNI));
		}
	}

	private void registerSteelSwords() {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			RendererSword oldestElvin = new RendererSword(SwordRegister.oldestElvin);
			oldestElvin.setEFPRender(2.3F, -3.85F, -0.64F, 2.0515F);
			MinecraftForgeClient.registerItemRenderer(SwordRegister.oldestElvin, oldestElvin);
			
			RendererSword mahakam = new RendererSword(SwordRegister.mahakam1);
			mahakam.setEFPRender(2.0F, -3.55F, -0.6F, 2.0515F);
			MinecraftForgeClient.registerItemRenderer(SwordRegister.mahakam1, mahakam);
			MinecraftForgeClient.registerItemRenderer(SwordRegister.mahakam2, mahakam);
			
			RendererSword ellander = new RendererSword(SwordRegister.ellander);
			ellander.setEFPRender(2.3F, -3.75F, -0.6F, 2.0515F);
			MinecraftForgeClient.registerItemRenderer(SwordRegister.ellander, ellander);
			
			RendererSword irony = new RendererSword(SwordRegister.irony);
			irony.setEFPRender(2.1F, -3.8F, -0.64F, 2.0515F);
			MinecraftForgeClient.registerItemRenderer(SwordRegister.irony, irony);
			
			RendererSword rusty = new RendererSword(SwordRegister.rusty);
			rusty.setEntityRender(0F, 0F, 0F, 0.0415F);
			rusty.setEquippedRender(-0.07F, 0.3F, 0.1F, 0.0215F);
			rusty.setEFPRender(2.2F, -2.65F, -0.33F, 0.0515F);
			MinecraftForgeClient.registerItemRenderer(SwordRegister.rusty, rusty);
			
			RendererSword rustyElvin = new RendererSword(SwordRegister.rustyElvin);
			rustyElvin.setEFPRender(2.3F, -3.85F, -0.64F, 2.0515F);
			MinecraftForgeClient.registerItemRenderer(SwordRegister.rustyElvin, rustyElvin);
			
			RendererSword devine = new RendererSword(SwordRegister.devine);
			devine.setEFPRender(2.3F, -3.85F, -0.64F, 2.0515F);
			MinecraftForgeClient.registerItemRenderer(SwordRegister.devine, devine);
			MinecraftForgeClient.registerItemRenderer(SwordRegister.elvin, devine);
			
			RendererSword nilfgaardSteel1 = new RendererSword(SwordRegister.nilfgaardSteel1);
			nilfgaardSteel1.setEFPRender(2.1F, -3.8F, -0.64F, 2.0515F);
			MinecraftForgeClient.registerItemRenderer(SwordRegister.nilfgaardSteel1, nilfgaardSteel1);
			
			RendererSword nilfgaardSteel2 = new RendererSword(SwordRegister.nilfgaardSteel2);
			nilfgaardSteel2.setEFPRender(2.1F, -3.8F, -0.64F, 2.0515F);
			MinecraftForgeClient.registerItemRenderer(SwordRegister.nilfgaardSteel2, nilfgaardSteel2);
			
			RendererSword vikovaro = new RendererSword(SwordRegister.vikovaro);
			vikovaro.setEFPRender(2.1F, -3.8F, -0.64F, 2.0515F);
			MinecraftForgeClient.registerItemRenderer(SwordRegister.vikovaro, vikovaro);
			
			RendererSword nazair = new RendererSword(SwordRegister.nazair);
			nazair.setEFPRender(2.1F, -3.8F, -0.64F, 2.0515F);
			MinecraftForgeClient.registerItemRenderer(SwordRegister.nazair, nazair);
			
			RendererSword ofir = new RendererSword(SwordRegister.ofir);
			ofir.setEFPRender(2.3F, -3.85F, -0.64F, 2.0515F);
			MinecraftForgeClient.registerItemRenderer(SwordRegister.ofir, ofir);
			
			RendererSword oldest = new RendererSword(SwordRegister.oldest);
			oldest.setEFPRender(2.3F, -3.85F, -0.64F, 2.0515F);
			MinecraftForgeClient.registerItemRenderer(SwordRegister.oldest, oldest);
			
			RendererSword witcherySteel = new RendererSword(SwordRegister.witcherySteel);
			witcherySteel.setEFPRender(2.2F, -3.75F, -0.64F, 2.0515F);
			MinecraftForgeClient.registerItemRenderer(SwordRegister.witcherySteel, witcherySteel);
			
			RendererSword vengerberg = new RendererSword(SwordRegister.vengerberg);
			vengerberg.setEFPRender(2.1F, -3.8F, -0.64F, 2.0515F);
			MinecraftForgeClient.registerItemRenderer(SwordRegister.maribor, vengerberg);
			MinecraftForgeClient.registerItemRenderer(SwordRegister.vengerberg, vengerberg);
			
			RendererSword aedirn = new RendererSword(SwordRegister.aedirn);
			aedirn.setEFPRender(2.1F, -3.8F, -0.64F, 2.0515F);
			MinecraftForgeClient.registerItemRenderer(SwordRegister.aedirn, aedirn);
			
			RendererSword temerSteel = new RendererSword(SwordRegister.temerSteel1);
			temerSteel.setEFPRender(2.3F, -3.85F, -0.64F, 2.0515F);
			MinecraftForgeClient.registerItemRenderer(SwordRegister.temerSteel1, temerSteel);
			MinecraftForgeClient.registerItemRenderer(SwordRegister.temerSteel2, temerSteel);
			MinecraftForgeClient.registerItemRenderer(SwordRegister.temerSteel3, temerSteel);
			
			RendererSword novigrad = new RendererSword(SwordRegister.novigrad1);
			novigrad.setEFPRender(2.17F, -3.65F, -0.84F, 1.9515F);
			MinecraftForgeClient.registerItemRenderer(SwordRegister.novigrad1, novigrad);
			MinecraftForgeClient.registerItemRenderer(SwordRegister.novigrad2, novigrad);
			MinecraftForgeClient.registerItemRenderer(SwordRegister.novigrad3, novigrad);
			
			RendererSword hardened = new RendererSword(SwordRegister.hardened);
			hardened.setEFPRender(2.2F, -3.55F, -0.64F, 2.0515F);
			MinecraftForgeClient.registerItemRenderer(SwordRegister.hardened, hardened);
			
			
			RendererSword bearSteel1 = new RendererSword(SwordRegister.bearSteel1);
			bearSteel1.setEFPRender(2.1F, -3.8F, -0.64F, 2.0515F);
			MinecraftForgeClient.registerItemRenderer(SwordRegister.bearSteel1, bearSteel1);
			
			RendererSword bearSteel2 = new RendererSword(SwordRegister.bearSteel2);
			bearSteel2.setEFPRender(2.1F, -3.8F, -0.64F, 2.0515F);
			MinecraftForgeClient.registerItemRenderer(SwordRegister.bearSteel2, bearSteel2);
			
			RendererSword bearSteel3 = new RendererSword(SwordRegister.bearSteel3);
			bearSteel3.setEFPRender(2.1F, -3.85F, -0.64F, 2.0515F);
			MinecraftForgeClient.registerItemRenderer(SwordRegister.bearSteel3, bearSteel3);
			
			RendererSword bearSteel4 = new RendererSword(SwordRegister.bearSteel4);
			bearSteel4.setEFPRender(2.1F, -3.85F, -0.64F, 2.0515F);
			MinecraftForgeClient.registerItemRenderer(SwordRegister.bearSteel4, bearSteel4);
			
			
			RendererSword wolfSteel1 = new RendererSword(SwordRegister.wolfSteel1);
			wolfSteel1.setEFPRender(2.3F, -3.85F, -0.64F, 2.0515F);
			MinecraftForgeClient.registerItemRenderer(SwordRegister.wolfSteel1, wolfSteel1);
			
			RendererSword wolfSteel2 = new RendererSword(SwordRegister.wolfSteel2);
			wolfSteel2.setEFPRender(2.3F, -3.85F, -0.64F, 2.0515F);
			MinecraftForgeClient.registerItemRenderer(SwordRegister.wolfSteel2, wolfSteel2);
			
			RendererSword wolfSteel3 = new RendererSword(SwordRegister.wolfSteel3);
			wolfSteel3.setEFPRender(2.3F, -3.85F, -0.64F, 2.0515F);
			MinecraftForgeClient.registerItemRenderer(SwordRegister.wolfSteel3, wolfSteel3);
			
			RendererSword wolfSteel4 = new RendererSword(SwordRegister.wolfSteel4);
			wolfSteel4.setEFPRender(2.3F, -3.85F, -0.64F, 2.0515F);
			MinecraftForgeClient.registerItemRenderer(SwordRegister.wolfSteel4, wolfSteel4);
			
			
			RendererSword griffinSteel1 = new RendererSword(SwordRegister.griffinSteel1);
			griffinSteel1.setEFPRender(2.15F, -3.55F, -0.6F, 2.0515F);
			MinecraftForgeClient.registerItemRenderer(SwordRegister.griffinSteel1, griffinSteel1);
			
			RendererSword griffinSteel2 = new RendererSword(SwordRegister.griffinSteel2);
			griffinSteel2.setEFPRender(2.15F, -3.55F, -0.6F, 2.0515F);
			MinecraftForgeClient.registerItemRenderer(SwordRegister.griffinSteel2, griffinSteel2);
			
			RendererSword griffinSteel3 = new RendererSword(SwordRegister.griffinSteel3);
			griffinSteel3.setEFPRender(2.15F, -3.55F, -0.6F, 2.0515F);
			MinecraftForgeClient.registerItemRenderer(SwordRegister.griffinSteel3, griffinSteel3);
			
			RendererSword griffinSteel4 = new RendererSword(SwordRegister.griffinSteel4);
			griffinSteel4.setEFPRender(2.15F, -3.55F, -0.6F, 2.0515F);
			MinecraftForgeClient.registerItemRenderer(SwordRegister.griffinSteel4, griffinSteel4);
		}
	}

	private void registerSilverSwords() {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			RendererSword addanDeidth = new RendererSword(SwordRegister.addanDeidth);
			addanDeidth.setEFPRender(2.1F, -3.55F, -0.64F, 2.0515F);
			MinecraftForgeClient.registerItemRenderer(SwordRegister.addanDeidth, addanDeidth);
			
			RendererSword deithwen = new RendererSword(SwordRegister.deithwen);
			deithwen.setEFPRender(2.1F, -3.55F, -0.64F, 2.0515F);
			MinecraftForgeClient.registerItemRenderer(SwordRegister.deithwen, deithwen);
			
			RendererSword gvennel = new RendererSword(SwordRegister.gvennel);
			gvennel.setEFPRender(2.2F, -3.85F, -0.64F, 2.0515F);
			MinecraftForgeClient.registerItemRenderer(SwordRegister.gvennel, gvennel);
			
			RendererSword disglair = new RendererSword(SwordRegister.disglair);
			disglair.setEFPRender(2.2F, -3.75F, -0.64F, 2.0515F);
			MinecraftForgeClient.registerItemRenderer(SwordRegister.disglair, disglair);
			
			RendererSword silver = new RendererSword(SwordRegister.silver1);
			silver.setEFPRender(2.15F, -3.8F, -0.67F, 2.0515F);
			MinecraftForgeClient.registerItemRenderer(SwordRegister.silver1, silver);
			
			RendererSword erlichrad = new RendererSword(SwordRegister.erlichrad);
			erlichrad.setEFPRender(2.15F, -3.8F, -0.67F, 2.0515F);
			MinecraftForgeClient.registerItemRenderer(SwordRegister.erlichrad, erlichrad);
			
			RendererSword emmental = new RendererSword(SwordRegister.emmental);
			emmental.setEFPRender(2.35F, -3.9F, -0.68F, 2.0515F);
			MinecraftForgeClient.registerItemRenderer(SwordRegister.emmental, emmental);
			
			RendererSword garhwal = new RendererSword(SwordRegister.garhwal);
			garhwal.setEFPRender(2.35F, -3.9F, -0.64F, 2.0515F);
			MinecraftForgeClient.registerItemRenderer(SwordRegister.garhwal, garhwal);
			
			RendererSword gvihir = new RendererSword(SwordRegister.gvihir);
			gvihir.setEFPRender(2.35F, -3.9F, -0.78F, 2.0515F);
			MinecraftForgeClient.registerItemRenderer(SwordRegister.gvihir, gvihir);
			
			RendererSword phoenix = new RendererSword(SwordRegister.phoenix);
			phoenix.setEFPRender(2.4F, -3.9F, -0.75F, 2.0515F);
			MinecraftForgeClient.registerItemRenderer(SwordRegister.phoenix, phoenix);
			
			RendererSword azureAnger = new RendererSword(SwordRegister.azureAnger);
			azureAnger.setEFPRender(2.35F, -3.9F, -0.67F, 2.0515F);
			MinecraftForgeClient.registerItemRenderer(SwordRegister.azureAnger, azureAnger);
			MinecraftForgeClient.registerItemRenderer(SwordRegister.silver2, azureAnger);
			
			RendererSword claw = new RendererSword(SwordRegister.claw);
			claw.setEFPRender(2.35F, -4.0F, -0.67F, 2.0515F);
			MinecraftForgeClient.registerItemRenderer(SwordRegister.claw, claw);
			
			RendererSword zirael = new RendererSword(SwordRegister.zirael);
			zirael.setEFPRender(2.3F, -3.85F, -0.64F, 2.0515F);
			MinecraftForgeClient.registerItemRenderer(SwordRegister.zirael, zirael);
			
			RendererSword tlareg = new RendererSword(SwordRegister.tlareg);
			tlareg.setEFPRender(2.08F, -3.65F, -0.64F, 2.0515F);
			MinecraftForgeClient.registerItemRenderer(SwordRegister.tlareg, tlareg);
			
			RendererSword witcherySilver = new RendererSword(SwordRegister.witcherySilver);
			witcherySilver.setEFPRender(2.08F, -3.65F, -0.64F, 2.0515F);
			MinecraftForgeClient.registerItemRenderer(SwordRegister.witcherySilver, witcherySilver);
			
			
			RendererSword bearSilver1 = new RendererSword(SwordRegister.bearSilver1);
			bearSilver1.setEFPRender(2.1F, -3.75F, -0.73F, 2.0515F);
			MinecraftForgeClient.registerItemRenderer(SwordRegister.bearSilver1, bearSilver1);
			
			RendererSword bearSilver2 = new RendererSword(SwordRegister.bearSilver2);
			bearSilver2.setEFPRender(2.3F, -3.75F, -0.73F, 2.0515F);
			MinecraftForgeClient.registerItemRenderer(SwordRegister.bearSilver2, bearSilver2);
			
			RendererSword bearSilver3 = new RendererSword(SwordRegister.bearSilver3);
			bearSilver3.setEFPRender(2.35F, -3.75F, -0.74F, 2.0515F);
			MinecraftForgeClient.registerItemRenderer(SwordRegister.bearSilver3, bearSilver3);
			
			RendererSword bearSilver4 = new RendererSword(SwordRegister.bearSilver4);
			bearSilver4.setEFPRender(2.4F, -3.85F, -0.63F, 2.0515F);
			MinecraftForgeClient.registerItemRenderer(SwordRegister.bearSilver4, bearSilver4);
			
			
			RendererSword catSilver1 = new RendererSword(SwordRegister.catSilver1);
			catSilver1.setEFPRender(2.15F, -3.85F, -0.64F, 2.0515F);
			MinecraftForgeClient.registerItemRenderer(SwordRegister.catSilver1, catSilver1);
			
			RendererSword catSilver2 = new RendererSword(SwordRegister.catSilver2);
			catSilver2.setEFPRender(2.15F, -3.85F, -0.64F, 2.0515F);
			MinecraftForgeClient.registerItemRenderer(SwordRegister.catSilver2, catSilver2);
			
			RendererSword catSilver3  = new RendererSword(SwordRegister.catSilver3);
			catSilver3.setEFPRender(2.3F, -3.75F, -0.8F, 2.0515F);
			MinecraftForgeClient.registerItemRenderer(SwordRegister.catSilver3, catSilver3);
			
			RendererSword catSilver4 = new RendererSword(SwordRegister.catSilver4);
			catSilver4.setEFPRender(2.3F, -3.75F, -0.64F, 2.0515F);
			MinecraftForgeClient.registerItemRenderer(SwordRegister.catSilver4, catSilver4);
	
	
			RendererSword griffinSilver1 = new RendererSword(SwordRegister.griffinSilver1);
			griffinSilver1.setEFPRender(2.2F, -3.92F, -0.67F, 2.0515F);
			MinecraftForgeClient.registerItemRenderer(SwordRegister.griffinSilver1, griffinSilver1);
			
			RendererSword griffinSilver2 = new RendererSword(SwordRegister.griffinSilver2);
			griffinSilver2.setEFPRender(2.25F, -3.8F, -0.64F, 2.0515F);
			MinecraftForgeClient.registerItemRenderer(SwordRegister.griffinSilver2, griffinSilver2);
			
			RendererSword griffinSilver3 = new RendererSword(SwordRegister.griffinSilver3);
			griffinSilver3.setEFPRender(2.3F, -3.65F, -0.74F, 2.0515F);
			MinecraftForgeClient.registerItemRenderer(SwordRegister.griffinSilver3, griffinSilver3);
			
			RendererSword griffinSilver4 = new RendererSword(SwordRegister.griffinSilver4);
			griffinSilver4.setEFPRender(2.35F, -3.75F, -0.64F, 2.0515F);
			MinecraftForgeClient.registerItemRenderer(SwordRegister.griffinSilver4, griffinSilver4);
			
			
			RendererSword wolfSilver1 = new RendererSword(SwordRegister.wolfSilver1);
			wolfSilver1.setEFPRender(2.3F, -3.85F, -0.64F, 2.0515F);
			MinecraftForgeClient.registerItemRenderer(SwordRegister.wolfSilver1, wolfSilver1);
			
			RendererSword wolfSilver2 = new RendererSword(SwordRegister.wolfSilver2);
			wolfSilver2.setEFPRender(2.3F, -3.85F, -0.64F, 2.0515F);
			MinecraftForgeClient.registerItemRenderer(SwordRegister.wolfSilver2, wolfSilver2);
			
			RendererSword wolfSilver3 = new RendererSword(SwordRegister.wolfSilver3);
			wolfSilver3.setEFPRender(2.3F, -3.85F, -0.64F, 2.0515F);
			MinecraftForgeClient.registerItemRenderer(SwordRegister.wolfSilver3, wolfSilver3);
			
			RendererSword wolfSilver4 = new RendererSword(SwordRegister.wolfSilver4);
			wolfSilver4.setEFPRender(2.3F, -3.85F, -0.64F, 2.0515F);
			MinecraftForgeClient.registerItemRenderer(SwordRegister.wolfSilver4, wolfSilver4);
		}
	}
}
