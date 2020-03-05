/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.server;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import keelfy.darkdata.DACommon;
import keelfy.darkdata.server.player.DAEntityPlayerMP;
import keelfyutils.KUtils;
import keelfyutils.bukkit.KPermissionsLoader.Permission;

/**
 * @author keelfy
 * @created 12 июл. 2017 г.
 */
public final class DAServer extends DACommon {

	public static final Permission ADMIN = new Permission("darkage.admin");
	public static final Permission BOOKS = new Permission("darkage.books");
	public static final Permission SETNAME = new Permission("darkage.setname");
	public static final Permission QUESTONLY = new Permission("darkage.questonly");
	public static final Permission TPW = new Permission("darkage.tpw");
	public static final Permission CLASS = new Permission("darkage.class");

	@Override
	public final void preInit(final FMLPreInitializationEvent event) {
		if (KUtils.PROTECT_SERVER) {
			super.preInit(event);

			this.preInit();
		}
	}

	public static final void preInit() {
		if (KUtils.PROTECT_SERVER) {
			DAServerFiles.Instance.init();
			DAServerConfig.Instance.init();
			DABooks.Instance.init();
		}
	}

	@Override
	public final void init(final FMLInitializationEvent event) {
		if (KUtils.PROTECT_SERVER) {
			super.init(event);

			this.init();
		}
	}

	public static final void init() {
		if (KUtils.PROTECT_SERVER) {
			DAEntityPlayerMP.register();
			DAServerEvents.Instance.register();
		}
	}

	@Override
	public final void serverStarting(final FMLServerStartingEvent event) {
		if (KUtils.PROTECT_SERVER) {
			this.serverStarting();
		}
	}

	public static final void serverStarting() {
		if (KUtils.PROTECT_SERVER) {
			KUtils.instance.registerCommandHandler(DACommands.class);
		}
	}
}
