/*
 *  Copyright (c) 2016-2017, Rubedo
 */

package keelfy.darkfactions.server.factions;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import keelfyutils.KUtils;
import net.minecraft.entity.player.EntityPlayer;

/**
 * @author keelfy
 * @created 20 нояб. 2017 г.
 */
public class Faction {

	private String name;
	private String id;
	private Map<UUID, EnumRang> members = new HashMap<UUID, EnumRang>();

	public Faction(EntityPlayer creator, String name, String id) {
		if (KUtils.PROTECT_SERVER) {
			this.name = name;
			this.id = id;
			this.members.put(creator.getUniqueID(), EnumRang.LEADER);
		}
	}

	public boolean addMember(EntityPlayer member) {
		if (KUtils.PROTECT_SERVER) {
			if (!this.members.containsKey(member.getUniqueID())) {
				this.members.put(member.getUniqueID(), EnumRang.MEMBER);
				return true;
			}
		}
		return false;
	}

	public boolean removeMember(EntityPlayer member) {
		if (KUtils.PROTECT_SERVER) {
			if (this.members.containsKey(member.getUniqueID())) {
				this.members.remove(member.getUniqueID());
				return true;
			}
		}
		return false;
	}

	public boolean promoteMember(EntityPlayer member) {
		if (KUtils.PROTECT_SERVER) {
			if (!this.members.containsKey(member.getUniqueID())) {
				return false;
			}

			switch (members.get(member.getUniqueID())) {
			case LEADER:
				return false;
			case MEMBER:
				members.put(member.getUniqueID(), EnumRang.OFFICER);
				return true;
			case OFFICER:
				return false;
			}
		}
		return false;
	}

}
