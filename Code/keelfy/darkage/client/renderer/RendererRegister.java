/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.client.renderer;

import cpw.mods.fml.client.registry.RenderingRegistry;
import keelfy.darkage.constants.EnumSign;
import keelfy.darkage.entities.sign.SignAard;
import keelfy.darkage.entities.sign.SignAksi;
import keelfy.darkage.entities.sign.SignIgni;
import keelfy.darkage.entities.sign.SignIrden;
import keelfy.darkage.handlers.registers.SwordRegister;
import keelfytools.KeelfyUtils;

/**
 * @author keelfy
 */
public class RendererRegister {
	   
	public RendererRegister() {
		if(KeelfyUtils.isClientSide()) {
			registerSilverSwords();
			registerSteelSwords();
			registerEntity();
		}
	}
	
	private void registerEntity() {
		if(KeelfyUtils.isClientSide()) {
			RenderingRegistry.registerEntityRenderingHandler(SignIrden.class, new RendererSign(EnumSign.IRDEN));
			RenderingRegistry.registerEntityRenderingHandler(SignAksi.class, new RendererSign(EnumSign.AKSI));
			RenderingRegistry.registerEntityRenderingHandler(SignAard.class, new RendererSign(EnumSign.AARD));
			RenderingRegistry.registerEntityRenderingHandler(SignIgni.class, new RendererSign(EnumSign.IGNI));
		}
	}

	private void registerSteelSwords() {
		if(KeelfyUtils.isClientSide()) {
			RendererSword oldestElvin = new RendererSword(SwordRegister.oldestElvin);
			oldestElvin.setEFPRender(2.3F, -3.85F, -0.64F, 2.0515F);
			SwordRegister.oldestElvin.setRenderer(oldestElvin);
			
			RendererSword mahakam = new RendererSword(SwordRegister.mahakam1);
			mahakam.setEFPRender(2.0F, -3.55F, -0.6F, 2.0515F);
			SwordRegister.mahakam1.setRenderer(mahakam);
			SwordRegister.mahakam2.setRenderer(mahakam);
			
			RendererSword ellander = new RendererSword(SwordRegister.ellander);
			ellander.setEFPRender(2.3F, -3.75F, -0.6F, 2.0515F);
			SwordRegister.ellander.setRenderer(ellander);
			
			RendererSword irony = new RendererSword(SwordRegister.irony);
			irony.setEFPRender(2.1F, -3.8F, -0.64F, 2.0515F);
			SwordRegister.irony.setRenderer(irony);
			
			RendererSword rusty = new RendererSword(SwordRegister.rusty);
			rusty.setEntityRender(0F, 0F, 0F, 0.0415F);
			rusty.setEquippedRender(-0.07F, 0.3F, 0.1F, 0.0215F);
			rusty.setEFPRender(2.2F, -2.65F, -0.33F, 0.0515F);
			SwordRegister.rusty.setRenderer(rusty);
			
			RendererSword rustyElvin = new RendererSword(SwordRegister.rustyElvin);
			rustyElvin.setEFPRender(2.3F, -3.85F, -0.64F, 2.0515F);
			SwordRegister.rustyElvin.setRenderer(rustyElvin);
			
			RendererSword devine = new RendererSword(SwordRegister.devine);
			devine.setEFPRender(2.3F, -3.85F, -0.64F, 2.0515F);
			SwordRegister.devine.setRenderer(devine);
			SwordRegister.elvin.setRenderer(devine);
			
			RendererSword nilfgaardSteel1 = new RendererSword(SwordRegister.nilfgaardSteel1);
			nilfgaardSteel1.setEFPRender(2.1F, -3.8F, -0.64F, 2.0515F);
			SwordRegister.nilfgaardSteel1.setRenderer(nilfgaardSteel1);
			
			RendererSword nilfgaardSteel2 = new RendererSword(SwordRegister.nilfgaardSteel2);
			nilfgaardSteel2.setEFPRender(2.1F, -3.8F, -0.64F, 2.0515F);
			SwordRegister.nilfgaardSteel2.setRenderer(nilfgaardSteel2);
			
			RendererSword vikovaro = new RendererSword(SwordRegister.vikovaro);
			vikovaro.setEFPRender(2.1F, -3.8F, -0.64F, 2.0515F);
			SwordRegister.vikovaro.setRenderer(vikovaro);
			
			RendererSword nazair = new RendererSword(SwordRegister.nazair);
			nazair.setEFPRender(2.1F, -3.8F, -0.64F, 2.0515F);
			SwordRegister.nazair.setRenderer(nazair);
			
			RendererSword ofir = new RendererSword(SwordRegister.ofir);
			ofir.setEFPRender(2.3F, -3.85F, -0.64F, 2.0515F);
			SwordRegister.ofir.setRenderer(ofir);
			
			RendererSword oldest = new RendererSword(SwordRegister.oldest);
			oldest.setEFPRender(2.3F, -3.85F, -0.64F, 2.0515F);
			SwordRegister.oldest.setRenderer(oldest);
			
			RendererSword witcherySteel = new RendererSword(SwordRegister.witcherySteel);
			witcherySteel.setEFPRender(2.2F, -3.75F, -0.64F, 2.0515F);
			SwordRegister.witcherySteel.setRenderer(witcherySteel);
			
			RendererSword vengerberg = new RendererSword(SwordRegister.vengerberg);
			vengerberg.setEFPRender(2.1F, -3.8F, -0.64F, 2.0515F);
			SwordRegister.maribor.setRenderer(vengerberg);
			SwordRegister.vengerberg.setRenderer(vengerberg);
			
			RendererSword aedirn = new RendererSword(SwordRegister.aedirn);
			aedirn.setEFPRender(2.1F, -3.8F, -0.64F, 2.0515F);
			SwordRegister.aedirn.setRenderer(aedirn);
			
			RendererSword temerSteel = new RendererSword(SwordRegister.temerSteel1);
			temerSteel.setEFPRender(2.3F, -3.85F, -0.64F, 2.0515F);
			SwordRegister.temerSteel1.setRenderer(temerSteel);
			SwordRegister.temerSteel2.setRenderer(temerSteel);
			SwordRegister.temerSteel3.setRenderer(temerSteel);
			
			RendererSword novigrad = new RendererSword(SwordRegister.novigrad1);
			novigrad.setEFPRender(2.17F, -3.65F, -0.84F, 1.9515F);
			SwordRegister.novigrad1.setRenderer(novigrad);
			SwordRegister.novigrad2.setRenderer(novigrad);
			SwordRegister.novigrad3.setRenderer(novigrad);
			
			RendererSword hardened = new RendererSword(SwordRegister.hardened);
			hardened.setEFPRender(2.2F, -3.55F, -0.64F, 2.0515F);
			SwordRegister.hardened.setRenderer(hardened);
			
			
			RendererSword bearSteel1 = new RendererSword(SwordRegister.bearSteel1);
			bearSteel1.setEFPRender(2.1F, -3.8F, -0.64F, 2.0515F);
			SwordRegister.bearSteel1.setRenderer(bearSteel1);
			
			RendererSword bearSteel2 = new RendererSword(SwordRegister.bearSteel2);
			bearSteel2.setEFPRender(2.1F, -3.8F, -0.64F, 2.0515F);
			SwordRegister.bearSteel2.setRenderer(bearSteel2);
			
			RendererSword bearSteel3 = new RendererSword(SwordRegister.bearSteel3);
			bearSteel3.setEFPRender(2.1F, -3.85F, -0.64F, 2.0515F);
			SwordRegister.bearSteel3.setRenderer(bearSteel3);
			
			RendererSword bearSteel4 = new RendererSword(SwordRegister.bearSteel4);
			bearSteel4.setEFPRender(2.1F, -3.85F, -0.64F, 2.0515F);
			SwordRegister.bearSteel4.setRenderer(bearSteel4);
			
			
			RendererSword wolfSteel1 = new RendererSword(SwordRegister.wolfSteel1);
			wolfSteel1.setEFPRender(2.3F, -3.85F, -0.64F, 2.0515F);
			SwordRegister.wolfSteel1.setRenderer(wolfSteel1);
			
			RendererSword wolfSteel2 = new RendererSword(SwordRegister.wolfSteel2);
			wolfSteel2.setEFPRender(2.3F, -3.85F, -0.64F, 2.0515F);
			SwordRegister.wolfSteel2.setRenderer(wolfSteel2);
			
			RendererSword wolfSteel3 = new RendererSword(SwordRegister.wolfSteel3);
			wolfSteel3.setEFPRender(2.3F, -3.85F, -0.64F, 2.0515F);
			SwordRegister.wolfSteel3.setRenderer(wolfSteel3);
			
			RendererSword wolfSteel4 = new RendererSword(SwordRegister.wolfSteel4);
			wolfSteel4.setEFPRender(2.3F, -3.85F, -0.64F, 2.0515F);
			SwordRegister.wolfSteel4.setRenderer(wolfSteel4);
			
			
			RendererSword griffinSteel1 = new RendererSword(SwordRegister.griffinSteel1);
			griffinSteel1.setEFPRender(2.15F, -3.55F, -0.6F, 2.0515F);
			SwordRegister.griffinSteel1.setRenderer(griffinSteel1);
			
			RendererSword griffinSteel2 = new RendererSword(SwordRegister.griffinSteel2);
			griffinSteel2.setEFPRender(2.15F, -3.55F, -0.6F, 2.0515F);
			SwordRegister.griffinSteel2.setRenderer(griffinSteel2);
			
			RendererSword griffinSteel3 = new RendererSword(SwordRegister.griffinSteel3);
			griffinSteel3.setEFPRender(2.15F, -3.55F, -0.6F, 2.0515F);
			SwordRegister.griffinSteel3.setRenderer(griffinSteel3);
			
			RendererSword griffinSteel4 = new RendererSword(SwordRegister.griffinSteel4);
			griffinSteel4.setEFPRender(2.15F, -3.55F, -0.6F, 2.0515F);
			SwordRegister.griffinSteel4.setRenderer(griffinSteel4);
		}
	}

	private void registerSilverSwords() {
		if(KeelfyUtils.isClientSide()) {
			RendererSword addanDeidth = new RendererSword(SwordRegister.addanDeidth);
			addanDeidth.setEFPRender(2.1F, -3.55F, -0.64F, 2.0515F);
			SwordRegister.addanDeidth.setRenderer(addanDeidth);
			
			RendererSword deithwen = new RendererSword(SwordRegister.deithwen);
			deithwen.setEFPRender(2.1F, -3.55F, -0.64F, 2.0515F);
			SwordRegister.deithwen.setRenderer(deithwen);
			
			RendererSword gvennel = new RendererSword(SwordRegister.gvennel);
			gvennel.setEFPRender(2.2F, -3.85F, -0.64F, 2.0515F);
			SwordRegister.gvennel.setRenderer(gvennel);
			
			RendererSword disglair = new RendererSword(SwordRegister.disglair);
			disglair.setEFPRender(2.2F, -3.75F, -0.64F, 2.0515F);
			SwordRegister.disglair.setRenderer(disglair);
			
			RendererSword silver = new RendererSword(SwordRegister.silver1);
			silver.setEFPRender(2.15F, -3.8F, -0.67F, 2.0515F);
			SwordRegister.silver1.setRenderer(silver);
			
			RendererSword erlichrad = new RendererSword(SwordRegister.erlichrad);
			erlichrad.setEFPRender(2.15F, -3.8F, -0.67F, 2.0515F);
			SwordRegister.erlichrad.setRenderer(erlichrad);
			
			RendererSword emmental = new RendererSword(SwordRegister.emmental);
			emmental.setEFPRender(2.35F, -3.9F, -0.68F, 2.0515F);
			SwordRegister.emmental.setRenderer(emmental);
			
			RendererSword garhwal = new RendererSword(SwordRegister.garhwal);
			garhwal.setEFPRender(2.35F, -3.9F, -0.64F, 2.0515F);
			SwordRegister.garhwal.setRenderer(garhwal);
			
			RendererSword gvihir = new RendererSword(SwordRegister.gvihir);
			gvihir.setEFPRender(2.35F, -3.9F, -0.78F, 2.0515F);
			SwordRegister.gvihir.setRenderer(gvihir);
			
			RendererSword phoenix = new RendererSword(SwordRegister.phoenix);
			phoenix.setEFPRender(2.4F, -3.9F, -0.75F, 2.0515F);
			SwordRegister.phoenix.setRenderer(phoenix);
			
			RendererSword azureAnger = new RendererSword(SwordRegister.azureAnger);
			azureAnger.setEFPRender(2.35F, -3.9F, -0.67F, 2.0515F);
			SwordRegister.azureAnger.setRenderer(azureAnger);
			SwordRegister.silver2.setRenderer(azureAnger);
			
			RendererSword claw = new RendererSword(SwordRegister.claw);
			claw.setEFPRender(2.35F, -4.0F, -0.67F, 2.0515F);
			SwordRegister.claw.setRenderer(claw);
			
			RendererSword zirael = new RendererSword(SwordRegister.zirael);
			zirael.setEFPRender(2.3F, -3.85F, -0.64F, 2.0515F);
			SwordRegister.zirael.setRenderer(zirael);
			
			RendererSword tlareg = new RendererSword(SwordRegister.tlareg);
			tlareg.setEFPRender(2.08F, -3.65F, -0.64F, 2.0515F);
			SwordRegister.tlareg.setRenderer(tlareg);
			
			RendererSword witcherySilver = new RendererSword(SwordRegister.witcherySilver);
			witcherySilver.setEFPRender(2.08F, -3.65F, -0.64F, 2.0515F);
			SwordRegister.witcherySilver.setRenderer(witcherySilver);
			
			
			RendererSword bearSilver1 = new RendererSword(SwordRegister.bearSilver1);
			bearSilver1.setEFPRender(2.1F, -3.75F, -0.73F, 2.0515F);
			SwordRegister.bearSilver1.setRenderer(bearSilver1);
			
			RendererSword bearSilver2 = new RendererSword(SwordRegister.bearSilver2);
			bearSilver2.setEFPRender(2.3F, -3.75F, -0.73F, 2.0515F);
			SwordRegister.bearSilver2.setRenderer(bearSilver2);
			
			RendererSword bearSilver3 = new RendererSword(SwordRegister.bearSilver3);
			bearSilver3.setEFPRender(2.35F, -3.75F, -0.74F, 2.0515F);
			SwordRegister.bearSilver3.setRenderer(bearSilver3);
			
			RendererSword bearSilver4 = new RendererSword(SwordRegister.bearSilver4);
			bearSilver4.setEFPRender(2.4F, -3.85F, -0.63F, 2.0515F);
			SwordRegister.bearSilver4.setRenderer(bearSilver4);
			
			
			RendererSword catSilver1 = new RendererSword(SwordRegister.catSilver1);
			catSilver1.setEFPRender(2.15F, -3.85F, -0.64F, 2.0515F);
			SwordRegister.catSilver1.setRenderer(catSilver1);
			
			RendererSword catSilver2 = new RendererSword(SwordRegister.catSilver2);
			catSilver2.setEFPRender(2.15F, -3.85F, -0.64F, 2.0515F);
			SwordRegister.catSilver2.setRenderer(catSilver2);
			
			RendererSword catSilver3  = new RendererSword(SwordRegister.catSilver3);
			catSilver3.setEFPRender(2.3F, -3.75F, -0.8F, 2.0515F);
			SwordRegister.catSilver3.setRenderer(catSilver3);
			
			RendererSword catSilver4 = new RendererSword(SwordRegister.catSilver4);
			catSilver4.setEFPRender(2.3F, -3.75F, -0.64F, 2.0515F);
			SwordRegister.catSilver4.setRenderer(catSilver4);
	
	
			RendererSword griffinSilver1 = new RendererSword(SwordRegister.griffinSilver1);
			griffinSilver1.setEFPRender(2.2F, -3.92F, -0.67F, 2.0515F);
			SwordRegister.griffinSilver1.setRenderer(griffinSilver1);
			
			RendererSword griffinSilver2 = new RendererSword(SwordRegister.griffinSilver2);
			griffinSilver2.setEFPRender(2.25F, -3.8F, -0.64F, 2.0515F);
			SwordRegister.griffinSilver2.setRenderer(griffinSilver2);
			
			RendererSword griffinSilver3 = new RendererSword(SwordRegister.griffinSilver3);
			griffinSilver3.setEFPRender(2.3F, -3.65F, -0.74F, 2.0515F);
			SwordRegister.griffinSilver3.setRenderer(griffinSilver3);
			
			RendererSword griffinSilver4 = new RendererSword(SwordRegister.griffinSilver4);
			griffinSilver4.setEFPRender(2.35F, -3.75F, -0.64F, 2.0515F);
			SwordRegister.griffinSilver4.setRenderer(griffinSilver4);
			
			
			RendererSword wolfSilver1 = new RendererSword(SwordRegister.wolfSilver1);
			wolfSilver1.setEFPRender(2.3F, -3.85F, -0.64F, 2.0515F);
			SwordRegister.wolfSilver1.setRenderer(wolfSilver1);
			
			RendererSword wolfSilver2 = new RendererSword(SwordRegister.wolfSilver2);
			wolfSilver2.setEFPRender(2.3F, -3.85F, -0.64F, 2.0515F);
			SwordRegister.wolfSilver2.setRenderer(wolfSilver2);
			
			RendererSword wolfSilver3 = new RendererSword(SwordRegister.wolfSilver3);
			wolfSilver3.setEFPRender(2.3F, -3.85F, -0.64F, 2.0515F);
			SwordRegister.wolfSilver3.setRenderer(wolfSilver3);
			
			RendererSword wolfSilver4 = new RendererSword(SwordRegister.wolfSilver4);
			wolfSilver4.setEFPRender(2.3F, -3.85F, -0.64F, 2.0515F);
			SwordRegister.wolfSilver4.setRenderer(wolfSilver4);
		}
	}
}
