/*
 *  Copyright (c) 2016-2017, Rubedo
 */

package keelfy.darkcore.common.player.skills;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import keelfy.darkcore.common.player.DAPlayerData;
import keelfyutils.KUtils;
import net.minecraft.nbt.NBTTagCompound;

/**
 * @author keelfy
 * @created 4 янв. 2018 г.
 */
public class Skill {

	private DAPlayerData data;
	public String name = "";
	private Map<String, Ability> abilities = new HashMap<String, Ability>();

	public Skill(DAPlayerData data, String name) {
		this.name = name;
	}

	public void upgrade(String abname, float amount) {

		data.skills.sync();
	}

	public void saveToNBT(NBTTagCompound compound) {
		if (KUtils.PROTECT_SERVER) {
			NBTTagCompound abilitiesTag = new NBTTagCompound();

			for (Entry<String, Ability> entry : abilities.entrySet()) {
				abilitiesTag.setBoolean(entry.getKey(), entry.getValue().status);
			}

			compound.setTag("Abilities", abilitiesTag);
		}
	}

	public void loadFromNBT(NBTTagCompound compound) {
		NBTTagCompound abilitiesTag = (NBTTagCompound) compound.getTag("Abilities");

		for (Entry<String, Ability> entry : abilities.entrySet()) {
			if (abilitiesTag.hasKey(entry.getKey())) {
				abilities.get(entry.getKey()).status = abilitiesTag.getBoolean(entry.getKey());
			}
		}

	}

}
