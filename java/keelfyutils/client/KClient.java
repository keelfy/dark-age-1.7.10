package keelfyutils.client;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkdata.client.DAFontHandler;
import keelfy.darkdata.client.render.DARenderFont;
import keelfy.darkdata.constants.EnumFont;
import keelfyutils.KUtils;
import keelfyutils.str.KString;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.util.MovingObjectPosition;

/**
 * @author keelfy
 * @created 8 июн. 2017 г.
 */
@SideOnly(Side.CLIENT)
public final class KClient {

	public static final void print(final Minecraft mc, final String str) {
		if (KUtils.PROTECT_CLIENT) {
			if (mc != null && mc.thePlayer != null) {
				mc.thePlayer.sendChatMessage(str);
			}
		}
	}

	public static final void removeVanillaKeybind(final GameSettings settings, final KeyBinding keyToRemove) {
		settings.keyBindings = ArrayUtils.removeElement(settings.keyBindings, keyToRemove);
	}

	public static final void unpressKey(final KeyBinding key) {
		KeyBinding.setKeyBindState(key.getKeyCode(), false);
	}

	public static final void unpressKey(final int keyCode) {
		KeyBinding.setKeyBindState(keyCode, false);
	}

	public static final Entity getLookingEntity(final MovingObjectPosition mop) {
		if (KUtils.PROTECT_CLIENT) {
			if (mop != null && mop.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY)
				return mop.entityHit;
		}
		return null;
	}

	public static final float getStringWidth(final Minecraft mc, final EnumFont twmFont, final String text) {
		if (KUtils.PROTECT_CLIENT) {
			final DARenderFont font = DAFontHandler.Instance.getFont(twmFont.getFontName());

			if (font == null)
				return mc.fontRenderer.getStringWidth(text);
			else
				return font.getStringWidth(text);
		}
		return 0;
	}

	public static final int gameFPS(final Minecraft mc) {
		final List<String> output = new ArrayList();

		if (KString.reformat(mc.debug, "%s fps, %s chunk updates", output)) {
			try {
				return Integer.parseInt(output.get(0));
			} catch (NumberFormatException e) {
				return mc.getLimitFramerate();
			}
		} else
			return mc.getLimitFramerate();
	}

	public static final float getStringHeight(final Minecraft mc, final EnumFont twmFont, final String text) {
		if (KUtils.PROTECT_CLIENT) {
			final DARenderFont font = DAFontHandler.Instance.getFont(twmFont.getFontName());

			if (font == null)
				return mc.fontRenderer.FONT_HEIGHT;
			else
				return font.getStringHeight();
		}
		return 0;
	}
}
