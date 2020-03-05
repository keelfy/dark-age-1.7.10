/*
 *  Copyright (c) 2016-2017, Rubedo
 */

package keelfy.darkcore.common.player.skills;

import java.util.ArrayList;
import java.util.List;

/**
 * @author keelfy
 * @created 9 янв. 2018 г.
 */
public class Ability {

	public String name;
	public List<String> requirements = new ArrayList<String>();
	public boolean status;

	public Ability() {
		this.status = false;
		this.name = "";
	}

	public String getName() {
		return name;
	}

	public void unlock() {
		this.status = true;
	}

	public List<String> getRequirements() {
		return requirements;
	}

	public void addRequirement(String name) {
		this.requirements.add(name);
	}
}
