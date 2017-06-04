package keelfy.darkage.handler.registers;

import cpw.mods.fml.common.registry.EntityRegistry;
import keelfy.darkage.DarkAge;
import keelfy.darkage.entity.sign.SignAard;
import keelfy.darkage.entity.sign.SignAksi;
import keelfy.darkage.entity.sign.SignIgni;
import keelfy.darkage.entity.sign.SignIrden;

/**
 * @author keelfy
 */
public class EntityRegister {
	public EntityRegister() {
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
