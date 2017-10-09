/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.handlers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.compress.utils.CharsetNames;

import keelfy.darkdata.constants.EnumDAFileType;
import keelfyutils.KUtils;
import keelfyutils.str.KLog;

/**
 * @author keelfy
 * @created 17 июн. 2017 г.
 */
public enum DACommonFiles {
	Instance;

	private File daiFolderPath;

	public String daiFile = ".dai";

	private File configDir;

	public final void init(final File configDir) {
		this.configDir = configDir;

		this.daiFolderPath = new File(getDataFolderPath(), "Items");
	}

	public final File getConfigurationDirectory() {
		return configDir;
	}

	public final File getDAIFolderPath() {
		return daiFolderPath;
	}

	public final File getDataFolderPath() {
		String path = configDir.getParent() + File.separator + "mods";

		if (!KUtils.SERVER) {
			path += File.separator + "1.7.10";
		}

		if (path.endsWith(".")) {
			path = path.substring(0, path.length() - 2);
		}
		return new File(path, "DarkAge");
	}

	public final File getDAIFile(final String name) {
		return new File(daiFolderPath, name + daiFile);
	}

	public final List<String> readFile(final EnumDAFileType type, final String file) {
		switch (type) {
			case DAI:
				final File path = new File(getDAIFolderPath(), file + daiFile);
				return readFile(path);
			default:
				break;
		}
		return null;
	}

	public final List<String> readFile(final File path) {
		final List<String> out = new ArrayList();
		BufferedReader br = null;

		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(path), CharsetNames.UTF_8));
		} catch (final FileNotFoundException e) {
			KLog.fileNotFound(path);
			return null;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		try {
			String line = br.readLine();
			while (line != null) {
				out.add(line.replace("\u00C2\u00A7", "\u00A7"));
				line = br.readLine();
			}
		} catch (final IOException e) {
			e.printStackTrace();
			KLog.readErr(path);
			return null;
		} finally {
			try {
				br.close();
			} catch (final IOException e) {
				e.printStackTrace();
			}
		}

		return out;
	}
}
