package keelfyutils.str;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author keelfy
 * @created 21 июн. 2017 г.
 */
public enum KLog {
	Instance;

	public static final Logger Logger = LogManager.getLogger();

	public static final void info(final Object object) {
		Logger.info(object);
	}

	public static final void err(final Object object) {
		Logger.error(object);
	}

	public static final void debug(final Object object) {
		Logger.debug(object);
	}

	public static final void fileNotFound(final File path) {
		err("Файл не найден! Путь -> ");
		err(path.getAbsolutePath());
	}

	public static final void readErr(final File path) {
		err("Ошибка чтения файла! Путь -> ");
		err(path.getAbsolutePath());
	}
}
