package keelfy.darkage.entity.player;

/**
 * @author keelfy
 */
public enum PlayerClass {
	WITCHER("Ведьмак"),
	HUMAN("Человек");
	
	private String localized;
	private PlayerClass(String loc) {
		localized = loc;
	}
	
	public String getLocalizedName() {
		return localized;
	}
}
