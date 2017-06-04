package keelfy.darkage.item;

import java.util.List;

import keelfy.darkage.util.DATab;
import keelfy.darkage.util.LanguageUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

/**
 * @author keelfy
 */
public class Money extends DAItem {

	private float valueInOrenes;
	
	private String desc_money_key1;
	private String desc_money_key2;
	
	public Money() {
		this.setRarity(ItemRarity.NONE);
		this.setCreativeTab(DATab.tabMoney);
		
		desc_money_key1 = LanguageUtil.localize(LanguageUtil.desc_money_key1);
		desc_money_key2 = LanguageUtil.localize(LanguageUtil.desc_money_key2);
	}
	
	@Override
	public void addInformation(ItemStack is, EntityPlayer player, List list, boolean bool) {
		list.add(desc_money_key1);
		list.add(desc_money_key2);
		next(list);
		
		addDescriptionToInformation(list);
	}
	
	public void setValueInOrenes(float oren) {
		this.valueInOrenes = oren;
	}
	
	public float getValueInOrenes() {
		return valueInOrenes;
	}
}
