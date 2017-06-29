/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfytools.log;

import java.io.File;
import java.time.Duration;
import java.time.temporal.Temporal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author keelfy
 * @created 21 июн. 2017 г.
 */
public enum KeelfyLog {
	Instance;
	
	public static final Logger Logger = LogManager.getLogger();
	
	public static void info(String str) {
		Logger.info(str);
	}
	
	public static void err(String str) {
		Logger.error(str);
	}
	
	public static void debug(String str) {
		Logger.debug(str);
	}
	
	public static void fileNotFound(File path) {
		err("Файл не найден! Путь -> ");
		err(path.getAbsolutePath());
	}
	
	public static void readErr(File path) {
		err("Ошибка чтения файла! Путь -> ");
		err(path.getAbsolutePath());
	}
	
	public static long getTimeBetween(Temporal start, Temporal end) {
		return Duration.between(start, end).toMillis();
	}
}
