/*
 *  Copyright (c) 2016-2017, Rubedo
 */

package keelfy.darkcore.common.player.skills;

import java.util.HashMap;
import java.util.Map.Entry;

import keelfy.darkcore.common.player.DAPlayerData;
import keelfyutils.KUtils;
import net.minecraft.nbt.NBTTagCompound;

/**
 * @author keelfy
 * @created 4 янв. 2018 г.
 */
public class SkillCategory {

	private DAPlayerData data;
	private String catName = "";
	private HashMap<String, Skill> skills = new HashMap<String, Skill>();

	public SkillCategory(DAPlayerData data, String name) {
		this.data = data;
		this.catName = name;
	}

	public String getName() {
		return catName;
	}

	public HashMap<String, Skill> getSkills() {
		return skills;
	}

	public void addSkill(String id, Skill skill) {
		this.skills.put(id, skill);
	}

	public void saveToNBT(NBTTagCompound compound) {
		if (KUtils.PROTECT_SERVER) {
			NBTTagCompound skillsTag = new NBTTagCompound();

			for (Entry<String, Skill> entry : skills.entrySet()) {
				entry.getValue().saveToNBT(skillsTag);
			}

			compound.setTag("Skills", skillsTag);
		}
	}

	public void loadFromNBT(NBTTagCompound compound) {
		NBTTagCompound skillsTag = (NBTTagCompound) compound.getTag("Skills");

		for (Entry<String, Skill> entry : skills.entrySet()) {
			entry.getValue().loadFromNBT(compound);
		}
	}

}
