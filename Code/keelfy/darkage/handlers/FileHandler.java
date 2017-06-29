/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.handlers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import keelfytools.KeelfyUtils;
import keelfytools.log.KeelfyLog;

/**
 * @author keelfy
 * @created 17 июн. 2017 г.
 */
public class FileHandler {
	private File daiFolderPath;
	private File dabFolderPath;
	private File dacFolderPath;

	public String daiFile = ".dai";
	public String dabFile = ".dab";
	public String dacFile = ".dac";

	private File configDir;

	public FileHandler(File configDir) {
		this.configDir = configDir;

		this.daiFolderPath = new File(getDataFolderPath(), "Items");
		
		if(KeelfyUtils.isServerSide()) {
			this.dabFolderPath = new File(getDataFolderPath(), "Books");
			this.dacFolderPath = new File(getDataFolderPath(), "Config");
		}
	}

	public File getConfigurationDirectory() {
		return configDir;
	}

	public File getDABFolderPath() {
		return dabFolderPath;
	}

	public File getDAIFolderPath() {
		return daiFolderPath;
	}

	public File getDACFolderPath() {
		return dacFolderPath;
	}

	public File getDataFolderPath() {
		String path = configDir.getParent() + File.separator + "mods";

		if (!KeelfyUtils.SERVER_SIDE)
			path += File.separator + "1.7.10";

		if (path.endsWith("."))
			path = path.substring(0, path.length() - 2);
		return new File(path, "DarkAge");
	}

	public File getDAIFile(String name) {
		return new File(daiFolderPath, name + daiFile);
	}

	public List<String> readFile(EnumDAFileType type, String file) {

		if (type == EnumDAFileType.BOOK) {
			File path = new File(getDABFolderPath(), file + dabFile);
			return readFile(path);
		} else if (type == EnumDAFileType.ITEM) {
			File path = new File(getDAIFolderPath(), file + daiFile);
			return readFile(path);
		} else if (type == EnumDAFileType.CONFIG) {
			File path = new File(getDACFolderPath(), file + dacFile);
			return readFile(path);
		}
		return null;
	}

	public List<String> readFile(File path) {
		List<String> out = new ArrayList();
		BufferedReader br;
		
		try {
			br = new BufferedReader(new FileReader(path));
		} catch (FileNotFoundException e) {
			KeelfyLog.fileNotFound(path);;
			return null;
		}
		
		try {
			String line = br.readLine();
			while (line != null) {
				out.add(line.replace("\u00C2\u00A7", "\u00A7"));
				line = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
			KeelfyLog.readErr(path);
			return null;
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return out;
	}
	
	public static enum EnumDAFileType {
		ITEM, BOOK, CONFIG;
	}
}
