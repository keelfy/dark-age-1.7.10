/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.server;

import java.io.File;
import java.util.List;

import keelfy.darkdata.constants.EnumDAFileType;
import keelfy.darkdata.handlers.DACommonFiles;
import keelfyutils.KUtils;

/**
 * @author keelfy
 * @created 12 июл. 2017 г.
 */
public enum DAServerFiles {
	Instance;

	private File dabFolderPath;
	private File dacFolderPath;

	public String dabFile = ".dab";
	public String dacFile = ".dac";

	public final void init() {
		if (KUtils.PROTECT_SERVER) {
			final File data = DACommonFiles.Instance.getDataFolderPath();
			this.dabFolderPath = new File(data, "Books");
			this.dacFolderPath = new File(data, "Config");
		}
	}

	public File getDABFolderPath() {
		return dabFolderPath;
	}

	public File getDACFolderPath() {
		return dacFolderPath;
	}

	public List<String> readFile(final EnumDAFileType type, final String file) {
		if (KUtils.PROTECT_SERVER) {
			switch (type) {
				case DAB:
					final File path1 = new File(getDABFolderPath(), file + dabFile);
					return DACommonFiles.Instance.readFile(path1);
				case DAC:
					final File path2 = new File(getDACFolderPath(), file + dacFile);
					return DACommonFiles.Instance.readFile(path2);
				default:
					break;

			}
		}
		return null;
	}
}
