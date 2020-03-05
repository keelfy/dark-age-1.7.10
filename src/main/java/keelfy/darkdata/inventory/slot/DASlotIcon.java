package keelfy.darkdata.inventory.slot;

import keelfyutils.client.KGL;

/**
 * @author keelfy
 * @created 4 авг. 2017 г.
 */
public enum DASlotIcon {

	SWORD_STEEL(0), SWORD_SILVER(0), CHESTPLATE(0), GLOVES(0), OTHER(0), PANTS(0), BOOTS(0), HOT(1), DEFAULT(1, false);

	public static final int SLOT_SIZE = 16;
	public static final int SLOT_HEIGHT = 29;

	private int bgWidth;
	private int bgHeight;

	private boolean hasBackground;

	private int bgSrcX;
	private int bgSrcY;

	private int transposeX;
	private int transposeY;

	private int srcX;
	private int srcY;
	private int width;
	private int height;

	private DASlotIcon(int type) {
		this.srcX = type * SLOT_SIZE;
		this.srcY = 0;
		this.width = SLOT_SIZE;
		this.height = type == 0 ? SLOT_HEIGHT : SLOT_SIZE;
		this.bgWidth = type == 0 ? 11 : 9;
		this.bgHeight = type == 0 ? 16 : 9;
		this.bgSrcX = ordinal() * bgWidth;
		this.bgSrcY = type == 0 ? 30 : 48;
		this.transposeX = 0;
		this.transposeY = type == 0 ? -8 : 0;
		this.hasBackground = true;
	}

	private DASlotIcon(int type, boolean hasBG) {
		this(type);

		this.hasBackground = hasBG;
	}

	public void draw(DASlot slot, int x, int y) {
		y += transposeY;

		KGL.texturedRect(x, y, srcX, srcY, width, height);

		if (!hasBackground || slot.getHasStack()) {
			return;
		}

		KGL.texturedRect(x + ((width - bgWidth) / 2), y + ((height - bgHeight) / 2), bgSrcX, bgSrcY, bgWidth, bgHeight);
	}

	public boolean isHasBackground() {
		return hasBackground;
	}

	public int getTransposeY() {
		return transposeY;
	}

	public int getTransposeX() {
		return transposeX;
	}

	public int getSrcX() {
		return srcX;
	}

	public int getSrcY() {
		return srcY;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getBGX() {
		return bgSrcX;
	}

	public int getBGY() {
		return bgSrcY;
	}
}
