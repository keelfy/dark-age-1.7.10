/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.handlers.registers;

import cpw.mods.fml.common.registry.EntityRegistry;
import keelfy.darkage.DarkAge;
import keelfy.darkage.entities.sign.SignAard;
import keelfy.darkage.entities.sign.SignAksi;
import keelfy.darkage.entities.sign.SignIgni;
import keelfy.darkage.entities.sign.SignIrden;

/**
 * @author keelfy
 */
public enum EntityRegister {
	Instance;
	
	public final void init() {
		EntityRegistry.registerGlobalEntityID(SignIrden.class, "SignIrden", EntityRegistry.findGlobalUniqueEntityId(), 30, 300);
		EntityRegistry.registerModEntity(SignIrden.class, "SignIrden", 91, DarkAge.instance, 100, 100, true);
		EntityRegistry.registerGlobalEntityID(SignAksi.class, "SignAksi", EntityRegistry.findGlobalUniqueEntityId(), 30, 300);
		EntityRegistry.registerModEntity(SignAksi.class, "SignAksi", 92, DarkAge.instance, 100, 100, true);
		EntityRegistry.registerGlobalEntityID(SignAard.class, "SingAard", EntityRegistry.findGlobalUniqueEntityId(), 30, 300);
		EntityRegistry.registerModEntity(SignAard.class, "SingAard", 93, DarkAge.instance, 100, 100, true);
		EntityRegistry.registerGlobalEntityID(SignIgni.class, "SingIgni", EntityRegistry.findGlobalUniqueEntityId(), 30, 300);
		EntityRegistry.registerModEntity(SignIgni.class, "SingIgni", 94, DarkAge.instance, 100, 100, true);
	}
}
