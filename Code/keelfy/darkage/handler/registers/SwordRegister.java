package keelfy.darkage.handler.registers;

import keelfy.api.registry.ItemRegistry;
import keelfy.darkage.item.DAItem.ItemRarity;
import keelfy.darkage.item.Sword;
import keelfy.darkage.item.Sword.SwordType;
import net.minecraft.item.Item;

/**
 * @author keelfy
 */
public class SwordRegister {

	//Silver
	public static Item addanDeidth;
	public static Item deithwen;
	public static Item bearSilver1, bearSilver2, bearSilver3, bearSilver4;
	public static Item zirael;
	public static Item wolfSilver1, wolfSilver2, wolfSilver3, wolfSilver4;
	public static Item gvennel;
	public static Item disglair;
	public static Item tlareg;
	public static Item witcherySilver;
	public static Item catSilver1, catSilver2, catSilver3, catSilver4;
	public static Item griffinSilver1, griffinSilver2, griffinSilver3, griffinSilver4;
	public static Item silver1, silver2;
	public static Item erlichrad;
	public static Item emmental;
	public static Item garhwal;
	public static Item gvihir;
	public static Item phoenix;
	public static Item azureAnger;
	public static Item claw;
	
	//Steel
	public static Item hardened;  
	public static Item rusty;
	public static Item devine;
	public static Item oldestElvin;
	public static Item oldest;
	public static Item wolfSteel1, wolfSteel2, wolfSteel3, wolfSteel4;
	public static Item temerSteel1, temerSteel2, temerSteel3;
	public static Item nilfgaardSteel1, nilfgaardSteel2;
	public static Item vikovaro;
	public static Item nazair;
	public static Item witcherySteel;
	public static Item novigrad1, novigrad2, novigrad3;
	public static Item rustyElvin;
	public static Item ofir;
	public static Item ellander;
	public static Item irony;
	public static Item maribor;
	public static Item aedirn;
	public static Item vengerberg;
	public static Item griffinSteel1, griffinSteel2, griffinSteel3, griffinSteel4;
	public static Item bearSteel1, bearSteel2, bearSteel3, bearSteel4;
	public static Item elvin;
	public static Item mahakam1, mahakam2;

	public SwordRegister() {
		initSilver();
		initSteel();
	}

	private void initSilver() {
		//Bear School
		String set = "9801";
		bearSilver1 = ItemRegistry.of(set + "Silver1".toUpperCase(), new Sword(630, 175, ItemRarity.UNCOMMON, 4, SwordType.SILVER).setTextureName(set, "SL1")).registerWithoutTexture();
		bearSilver2 = ItemRegistry.of(set + "Silver2".toUpperCase(), new Sword(650, 190, ItemRarity.UNCOMMON, 3.5F, SwordType.SILVER).setTextureName(set, "SL2")).registerWithoutTexture();
		bearSilver3 = ItemRegistry.of(set + "Silver3".toUpperCase(), new Sword(680, 210, ItemRarity.RARE, 3, SwordType.SILVER).setTextureName(set, "SL3")).registerWithoutTexture();
		bearSilver4 = ItemRegistry.of(set + "Silver4".toUpperCase(), new Sword(690, 235, ItemRarity.RARE, 2.8F, SwordType.SILVER).setTextureName(set, "SL4")).registerWithoutTexture();
		
		//Cat School
		set = "9802";
		catSilver1 = ItemRegistry.of(set + "Silver1".toUpperCase(), new Sword(0, 0, ItemRarity.NOTENTERED, 0, SwordType.SILVER).setTextureName(set, "SL1")).registerWithoutTexture();
		catSilver2 = ItemRegistry.of(set + "Silver2".toUpperCase(), new Sword(0, 0, ItemRarity.NOTENTERED, 0, SwordType.SILVER).setTextureName(set, "SL2")).registerWithoutTexture();
		catSilver3 = ItemRegistry.of(set + "Silver3".toUpperCase(), new Sword(0, 0, ItemRarity.NOTENTERED, 0, SwordType.SILVER).setTextureName(set, "SL3")).registerWithoutTexture();
		catSilver4 = ItemRegistry.of(set + "Silver4".toUpperCase(), new Sword(0, 0, ItemRarity.NOTENTERED, 0, SwordType.SILVER).setTextureName(set, "SL4")).registerWithoutTexture();
		
		//Griffin School
		set = "9803";
		griffinSilver1 = ItemRegistry.of(set + "Silver1".toUpperCase(), new Sword(750, 155, ItemRarity.UNCOMMON, 3, SwordType.SILVER).setTextureName(set, "SL1")).registerWithoutTexture();
		griffinSilver2 = ItemRegistry.of(set + "Silver2".toUpperCase(), new Sword(770, 170, ItemRarity.UNCOMMON, 2, SwordType.SILVER).setTextureName(set, "SL2")).registerWithoutTexture();
		griffinSilver3 = ItemRegistry.of(set + "Silver3".toUpperCase(), new Sword(800, 190, ItemRarity.RARE, 1.5F, SwordType.SILVER).setTextureName(set, "SL3")).registerWithoutTexture();
		griffinSilver4 = ItemRegistry.of(set + "Silver4".toUpperCase(), new Sword(800, 210, ItemRarity.RARE, 1, SwordType.SILVER).setTextureName(set, "SL4")).registerWithoutTexture();
		
		//Wolf School
		set = "9804";
		wolfSilver1 = ItemRegistry.of(set + "Silver1".toUpperCase(), new Sword(700, 165, ItemRarity.UNCOMMON, 3.4F, SwordType.SILVER).setTextureName(set, "SL1")).registerWithoutTexture();
		wolfSilver2 = ItemRegistry.of(set + "Silver2".toUpperCase(), new Sword(700, 180, ItemRarity.UNCOMMON, 3, SwordType.SILVER).setTextureName(set, "SL2")).registerWithoutTexture();
		wolfSilver3 = ItemRegistry.of(set + "Silver3".toUpperCase(), new Sword(740, 200, ItemRarity.RARE, 2.6F, SwordType.SILVER).setTextureName(set, "SL3")).registerWithoutTexture();
		wolfSilver4 = ItemRegistry.of(set + "Silver4".toUpperCase(), new Sword(760, 225, ItemRarity.RARE, 1.8F, SwordType.SILVER).setTextureName(set, "SL4")).registerWithoutTexture();
		
		//Single
		set = "9800";
		addanDeidth = ItemRegistry.of("addanDeidth".toUpperCase(), new Sword(390, 195, ItemRarity.RARE, 3.8F, SwordType.SILVER).setTextureName(set, "SL01")).registerWithoutTexture();
		zirael = ItemRegistry.of("zirael".toUpperCase(), new Sword(500, 250, ItemRarity.LEGENDARY, 3.5F, SwordType.SILVER).setTextureName(set, "SL02")).registerWithoutTexture();
		gvennel = ItemRegistry.of("gvennel".toUpperCase(), new Sword(390, 135, ItemRarity.UNCOMMON, 2.7F, SwordType.SILVER).setTextureName(set, "SL03")).registerWithoutTexture();
		disglair = ItemRegistry.of("disglair".toUpperCase(), new Sword(350, 150, ItemRarity.UNCOMMON, 3.2F, SwordType.SILVER).setTextureName(set, "SL04")).registerWithoutTexture();
		tlareg = ItemRegistry.of("tlareg".toUpperCase(), new Sword(900, 245, ItemRarity.LEGENDARY, 2, SwordType.SILVER).setTextureName(set, "SL05")).registerWithoutTexture();
		witcherySilver = ItemRegistry.of("witcherySilver".toUpperCase(), new Sword(380, 140, ItemRarity.USUAL, 3, SwordType.SILVER).setTextureName(set, "SL06")).registerWithoutTexture();
		silver1 = ItemRegistry.of("silver1".toUpperCase(), new Sword(400, 125, ItemRarity.USUAL, 2.8F, SwordType.SILVER).setTextureName(set, "SL07")).registerWithoutTexture();
		erlichrad = ItemRegistry.of("erlichrad".toUpperCase(), new Sword(370, 140, ItemRarity.RARE, 3, SwordType.SILVER).setTextureName(set, "SL08")).registerWithoutTexture();
		emmental = ItemRegistry.of("emmental".toUpperCase(), new Sword(0, 0, ItemRarity.NOTENTERED, 0, SwordType.SILVER).setTextureName(set, "SL09")).registerWithoutTexture();
		garhwal = ItemRegistry.of("garhwal".toUpperCase(), new Sword(360, 165, ItemRarity.RARE, 3.5F, SwordType.SILVER).setTextureName(set, "SL10")).registerWithoutTexture();
		gvihir = ItemRegistry.of("gvihir".toUpperCase(), new Sword(440, 185, ItemRarity.UNCOMMON, 4, SwordType.SILVER).setTextureName(set, "SL11")).registerWithoutTexture();
		phoenix = ItemRegistry.of("phoenix".toUpperCase(), new Sword(425, 215, ItemRarity.RARE, 3.4F, SwordType.SILVER).setTextureName(set, "SL12")).registerWithoutTexture();
		azureAnger = ItemRegistry.of("azureAnger".toUpperCase(), new Sword(495, 240, ItemRarity.LEGENDARY, 3.2F, SwordType.SILVER).setTextureName(set, "SL13")).registerWithoutTexture();
		claw = ItemRegistry.of("claw".toUpperCase(), new Sword(600, 255, ItemRarity.LEGENDARY, 3.4F, SwordType.SILVER).setTextureName(set, "SL14")).registerWithoutTexture();
		silver2 = ItemRegistry.of("silver2".toUpperCase(), new Sword(480, 230, ItemRarity.RARE, 2.6F, SwordType.SILVER).setTextureName(set, "SL15")).registerWithoutTexture();
		deithwen = ItemRegistry.of("deithwen".toUpperCase(), new Sword(400, 205, ItemRarity.UNCOMMON, 3.9F, SwordType.SILVER).setTextureName(set, "SL16")).registerWithoutTexture();
	}
	
	private void initSteel() {
		//Bear School
		String set = "9801";
		bearSteel1 = ItemRegistry.of(set + "Steel1".toUpperCase(), new Sword(700, 300, ItemRarity.USUAL, 6, SwordType.STEEL).setTextureName(set, "ST1")).registerWithoutTexture();
		bearSteel2 = ItemRegistry.of(set + "Steel2".toUpperCase(), new Sword(700, 330, ItemRarity.UNCOMMON, 6, SwordType.STEEL).setTextureName(set, "ST2")).registerWithoutTexture();
		bearSteel3 = ItemRegistry.of(set + "Steel3".toUpperCase(), new Sword(700, 370, ItemRarity.UNCOMMON, 5, SwordType.STEEL).setTextureName(set, "ST3")).registerWithoutTexture();
		bearSteel4 = ItemRegistry.of(set + "Steel4".toUpperCase(), new Sword(700, 390, ItemRarity.LEGENDARY, 5, SwordType.STEEL).setTextureName(set, "ST4")).registerWithoutTexture();
				
		//Griffin School
		set = "9803";
		griffinSteel1 = ItemRegistry.of(set + "Steel1".toUpperCase(), new Sword(700, 250, ItemRarity.USUAL, 2.6F, SwordType.STEEL).setTextureName(set, "ST1")).registerWithoutTexture();
		griffinSteel2 = ItemRegistry.of(set + "Steel2".toUpperCase(), new Sword(700, 270, ItemRarity.UNCOMMON, 2, SwordType.STEEL).setTextureName(set, "ST2")).registerWithoutTexture();
		griffinSteel3 = ItemRegistry.of(set + "Steel3".toUpperCase(), new Sword(700, 300, ItemRarity.UNCOMMON, 2, SwordType.STEEL).setTextureName(set, "ST3")).registerWithoutTexture();
		griffinSteel4 = ItemRegistry.of(set + "Steel4".toUpperCase(), new Sword(700, 320, ItemRarity.LEGENDARY, 2, SwordType.STEEL).setTextureName(set, "ST4")).registerWithoutTexture();
				
		//Wolf School
		set = "9804";		
		wolfSteel1 = ItemRegistry.of(set + "Steel1".toUpperCase(), new Sword(700, 275, ItemRarity.USUAL, 4, SwordType.STEEL).setTextureName(set, "ST1")).registerWithoutTexture();
		wolfSteel2 = ItemRegistry.of(set + "Steel2".toUpperCase(), new Sword(700, 310, ItemRarity.UNCOMMON, 3.6F, SwordType.STEEL).setTextureName(set, "ST2")).registerWithoutTexture();
		wolfSteel3 = ItemRegistry.of(set + "Steel3".toUpperCase(), new Sword(700, 340, ItemRarity.UNCOMMON, 3, SwordType.STEEL).setTextureName(set, "ST3")).registerWithoutTexture();
		wolfSteel4 = ItemRegistry.of(set + "Steel4".toUpperCase(), new Sword(700, 360, ItemRarity.LEGENDARY, 3, SwordType.STEEL).setTextureName(set, "ST4")).registerWithoutTexture();
				
		//Temer
		set = "9805";
		temerSteel1 = ItemRegistry.of(set + "Steel1".toUpperCase(), new Sword(402, 175, ItemRarity.USUAL, 4F, SwordType.STEEL).setTextureName(set, "ST1")).registerWithoutTexture();
		temerSteel2 = ItemRegistry.of(set + "Steel2".toUpperCase(), new Sword(620, 240, ItemRarity.USUAL, 5.2F, SwordType.STEEL).setTextureName(set, "ST2")).registerWithoutTexture();
		temerSteel3 = ItemRegistry.of(set + "Steel3".toUpperCase(), new Sword(450, 310, ItemRarity.USUAL, 9, SwordType.STEEL).setTextureName(set, "ST3")).registerWithoutTexture();
				
		//Nilfgaard
		set = "9809";
		nilfgaardSteel1 = ItemRegistry.of(set + "Steel1".toUpperCase(), new Sword(620, 240, ItemRarity.USUAL, 4.5F, SwordType.STEEL).setTextureName(set, "ST1")).registerWithoutTexture();
		nilfgaardSteel2 = ItemRegistry.of(set + "Steel2".toUpperCase(), new Sword(670, 295, ItemRarity.RARE, 4, SwordType.STEEL).setTextureName(set, "ST2")).registerWithoutTexture();
		
		//Single
		set = "9800";		
		hardened = ItemRegistry.of("hardened".toUpperCase(), new Sword(470, 200, ItemRarity.USUAL, 4, SwordType.STEEL).setTextureName(set, "ST01")).registerWithoutTexture();
		oldestElvin = ItemRegistry.of("oldestElvin".toUpperCase(), new Sword(850, 385, ItemRarity.LEGENDARY, 3, SwordType.STEEL).setTextureName(set, "ST02")).registerWithoutTexture();
		devine = ItemRegistry.of("devine".toUpperCase(), new Sword(0, 0, ItemRarity.NOTENTERED, 0, SwordType.STEEL).setTextureName(set, "ST03")).registerWithoutTexture();
		rusty = ItemRegistry.of("rusty".toUpperCase(), new Sword(250, 125, ItemRarity.USUAL, 4, SwordType.STEEL).setTextureName(set, "ST04")).registerWithoutTexture();
		oldest = ItemRegistry.of("oldest".toUpperCase(), new Sword(0, 0, ItemRarity.NOTENTERED, 0, SwordType.STEEL).setTextureName(set, "ST05")).registerWithoutTexture();
		vikovaro = ItemRegistry.of("vikovaro".toUpperCase(), new Sword(535, 265, ItemRarity.UNCOMMON, 4.5F, SwordType.STEEL).setTextureName(set, "ST06")).registerWithoutTexture();
		nazair = ItemRegistry.of("nazair".toUpperCase(), new Sword(490, 250, ItemRarity.USUAL, 4.2F, SwordType.STEEL).setTextureName(set, "ST07")).registerWithoutTexture();
		witcherySteel = ItemRegistry.of("witcherySteel".toUpperCase(), new Sword(300, 260, ItemRarity.USUAL, 2, SwordType.STEEL).setTextureName(set, "ST08")).registerWithoutTexture();
		rustyElvin = ItemRegistry.of("rustyElvin".toUpperCase(), new Sword(520, 225, ItemRarity.USUAL, 3, SwordType.STEEL).setTextureName(set, "ST09")).registerWithoutTexture();
		ofir = ItemRegistry.of("ofir".toUpperCase(), new Sword(900, 360, ItemRarity.RARE, 2, SwordType.STEEL).setTextureName(set, "ST10")).registerWithoutTexture();
		ellander = ItemRegistry.of("ellander".toUpperCase(), new Sword(580, 235, ItemRarity.USUAL, 4, SwordType.STEEL).setTextureName(set, "ST11")).registerWithoutTexture();
		novigrad1 = ItemRegistry.of("novigrad1".toUpperCase(), new Sword(630, 270, ItemRarity.USUAL, 4.5F, SwordType.STEEL).setTextureName(set, "ST12")).registerWithoutTexture();
		novigrad2 = ItemRegistry.of("novigrad2".toUpperCase(), new Sword(415, 335, ItemRarity.USUAL, 9, SwordType.STEEL).setTextureName(set, "ST13")).registerWithoutTexture();
		novigrad3 = ItemRegistry.of("novigrad3".toUpperCase(), new Sword(540, 245, ItemRarity.USUAL, 5, SwordType.STEEL).setTextureName(set, "ST14")).registerWithoutTexture();
		irony = ItemRegistry.of("irony".toUpperCase(), new Sword(380, 150, ItemRarity.USUAL, 3.5F, SwordType.STEEL).setTextureName(set, "ST15")).registerWithoutTexture();
		maribor = ItemRegistry.of("maribor".toUpperCase(), new Sword(500, 260, ItemRarity.USUAL, 5, SwordType.STEEL).setTextureName(set, "ST16")).registerWithoutTexture();
		aedirn = ItemRegistry.of("aedirn".toUpperCase(), new Sword(400, 330, ItemRarity.USUAL, 11, SwordType.STEEL).setTextureName(set, "ST17")).registerWithoutTexture();
		vengerberg = ItemRegistry.of("vengerberg".toUpperCase(), new Sword(400, 340, ItemRarity.USUAL, 8, SwordType.STEEL).setTextureName(set, "ST18")).registerWithoutTexture();
		elvin = ItemRegistry.of("elvin".toUpperCase(), new Sword(730, 360, ItemRarity.RARE, 3, SwordType.STEEL).setTextureName(set, "ST19")).registerWithoutTexture();
		mahakam1 = ItemRegistry.of("mahakam1".toUpperCase(), new Sword(780, 370, ItemRarity.RARE, 2.5F, SwordType.STEEL).setTextureName(set, "ST20")).registerWithoutTexture();
		mahakam2 = ItemRegistry.of("mahakam2".toUpperCase(), new Sword(900, 400, ItemRarity.LEGENDARY, 3, SwordType.STEEL).setTextureName(set, "ST21")).registerWithoutTexture();
	}
}
