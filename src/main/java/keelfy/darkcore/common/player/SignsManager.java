/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkcore.common.player;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkdata.constants.EnumPlayerClass;
import keelfy.darkdata.constants.EnumSign;
import keelfyutils.KUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

/**
 * @author keelfy
 * @created 14 июл. 2017 г.
 */
public final class SignsManager extends DAStatManager {

	private int current;

	public SignsManager(final DAPlayerData dap) {
		super(dap);

		this.current = 5;
	}

	@SideOnly(Side.CLIENT)
	public final int getCurrent() {
		return current;
	}

	@SideOnly(Side.CLIENT)
	public final EnumSign getCurrentSign() {
		return EnumSign.values()[getCurrent()];
	}

	@SideOnly(Side.CLIENT)
	public final void verify() {
		if (KUtils.PROTECT_CLIENT) {
			if (data.playerClass.equals(EnumPlayerClass.WITCHER) && (current < 0 || current > 4)) {
				this.setCurrent(0);
			} else if (data.playerClass.equals(EnumPlayerClass.HUMAN) && current != 5) {
				this.setCurrent(5);
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public final void next() {
		if (KUtils.PROTECT_CLIENT) {
			int sign = this.getCurrent();

			if (data.playerClass.equals(EnumPlayerClass.WITCHER)) {
				if (sign < 4) {
					this.setCurrent(sign + 1);
				} else if (sign == 4) {
					this.setCurrent(0);
				}

				if (sign > 4 || sign < 0) {
					this.setCurrent(0);
				}
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public final void previous() {
		if (KUtils.PROTECT_CLIENT) {
			int sign = this.getCurrent();

			if (data.playerClass.equals(EnumPlayerClass.WITCHER)) {
				if (sign > 0) {
					this.setCurrent(sign - 1);
				} else if (sign == 0) {
					this.setCurrent(4);
				}

				if (sign > 4 || sign < 0) {
					this.setCurrent(0);
				}
			}
		}
	}

	public final void setCurrent(final int currentSign) {
		if (KUtils.PROTECT_CLIENT) {
			this.current = currentSign;
		}
	}

	public final void setCurrent(final EnumSign sign) {
		if (KUtils.PROTECT_CLIENT) {
			this.current = sign.ordinal();
		}
	}

	public static void useSign(final String s, final EntityPlayer owner) {
		if (KUtils.PROTECT_SERVER) {
			// owner.worldObj.setBlock((int) owner.posX, (int) owner.posY + 1, (int)
			// owner.posZ, BlockRegister.light, 0,
			// 3);
			owner.worldObj.playSoundAtEntity(owner, "customnpcs:signs." + s, 1.0F, 1.0F);
		}
	}

	@Override
	public final void saveNBTData(final NBTTagCompound compound) {}

	@Override
	public final void loadNBTData(final NBTTagCompound compound) {}
}
