/*
 *  Copyright (c) 2016-2017, Rubedo
 */

package keelfy.darkcore.common.player.managers;

import java.util.ArrayList;

import keelfy.darkcore.common.player.DAPlayerData;
import keelfy.darkcore.common.player.skills.Skill;
import keelfy.darkcore.common.player.skills.SkillCategory;
import keelfy.darkcore.network.DASNetwork;
import keelfy.darkdata.network.EnumCPackets;
import keelfyutils.KUtils;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;

/**
 * @author keelfy
 * @created 4 янв. 2018 г.
 */
public class SkillsManager extends DAStatManager {

	private final ArrayList<SkillCategory> skills = new ArrayList<SkillCategory>();

	private static final String NBTNAME = String.valueOf("Skills");

	public SkillsManager(final DAPlayerData dap) {
		super(dap);

		final SkillCategory a = new SkillCategory(dap, "Категория A");
		a.addSkill("a-a", new Skill(dap, "A-A"));
		a.addSkill("a-b", new Skill(dap, "A-B"));
		skills.add(a);

		final SkillCategory b = new SkillCategory(data, "Категория B");
		b.addSkill("b-a", new Skill(dap, "B-A"));
		b.addSkill("b-b", new Skill(dap, "B-B"));
		skills.add(b);

		final SkillCategory c = new SkillCategory(data, "Категория C");
		c.addSkill("c-a", new Skill(dap, "C-A"));
		c.addSkill("c-b", new Skill(dap, "C-B"));
		skills.add(c);
	}

	public ArrayList<SkillCategory> getCategories() {
		return skills;
	}

	@Override
	public final void saveNBTData(final NBTTagCompound compound) {
		if (KUtils.PROTECT_SERVER) {
			for (SkillCategory cat : skills) {
				cat.saveToNBT(compound);
			}
		}
	}

	@Override
	public final void loadNBTData(final NBTTagCompound compound) {
		for (SkillCategory cat : skills) {
			cat.loadFromNBT(compound);
		}
	}

	public final void sync() {
		if (KUtils.PROTECT_SERVER) {
			final NBTTagCompound data = new NBTTagCompound();
			this.saveNBTData(data);
			DASNetwork.sendTo((EntityPlayerMP) player, EnumCPackets.SyncSkills, data);
		}
	}

}
