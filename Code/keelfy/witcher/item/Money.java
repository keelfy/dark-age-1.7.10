package keelfy.witcher.item;

import java.util.List;

import keelfy.witcher.util.DATab;

/**
 * @author keelfy
 */
public class Money extends DAItem {

	private float courseInOren, courseInKrone, courseInDucat, courseInSilver, courseInGold;
	
	public Money(String... addInfo) {
		this(ItemRarity.NONE, addInfo);
	}	
	
	public Money(ItemRarity rarity, String... addInfo) {
		super(rarity, 0F, addInfo);
		
		this.setCreativeTab(DATab.wcMoney);
	}	
	
	public void setCourses(float oren, float krone, float ducat, float silver, float gold) {
		this.courseInOren = oren;
		this.courseInKrone = krone;
		this.courseInDucat = ducat;
		this.courseInSilver = silver;
		this.courseInGold = gold;
	}
	
	public float getCourseInOren() {
		return courseInOren;
	}
	
	public float getCourseInKrone() {
		return courseInKrone;
	}
	
	public float getCourseInDucat() {
		return courseInDucat;
	}
	
	public float getCourseInSilver() {
		return courseInSilver;
	}
	
	public float getCourseInGold() {
		return courseInGold;
	}
}
