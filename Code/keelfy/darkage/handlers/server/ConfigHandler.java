/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.handlers.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import keelfy.darkage.DarkAge;
import keelfy.darkage.handlers.FileHandler;
import keelfytools.KeelfyUtils;
import keelfytools.log.KeelfyLog;

/**
 * @author keelfy
 * @created 21 июн. 2017 г.
 */
public class ConfigHandler {
	private FileHandler fileHandler;
	private GsonBuilder gsonBuilder = new GsonBuilder();

	private String serverSideConfig = "server-side";

	public List<String> allowedCNPCSCommands = new ArrayList();

	public ConfigHandler() {
		this.fileHandler = DarkAge.instance.fileHandler;

		if (KeelfyUtils.isServerSide()) {
			this.scanServerSideConfig();
		}
	}

	private void scanServerSideConfig() {
		if (KeelfyUtils.isServerSide()) {
			File path = new File(fileHandler.getDACFolderPath(), serverSideConfig + fileHandler.dacFile);
			try {
				gsonBuilder.registerTypeAdapter(ArrayList.class, new ConfigDeserializer(EnumConfigSide.SERVER));
				Gson gson = gsonBuilder.create();
				Reader reader = new InputStreamReader(new FileInputStream(path));
				gson.fromJson(reader, ArrayList.class);
			} catch (FileNotFoundException e) {
				KeelfyLog.fileNotFound(path);
				e.printStackTrace();
			}
		}
	}

	private class ConfigDeserializer implements JsonDeserializer<ArrayList> {

		private EnumConfigSide type;

		public ConfigDeserializer(EnumConfigSide side) {
			this.type = side;
		}

		@Override
		public ArrayList deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			if (type == EnumConfigSide.SERVER) {
				if (KeelfyUtils.isServerSide()) {
					JsonObject config = json.getAsJsonObject();

					JsonArray allowedCNPCSCommandsArr = config.get("allowedCNPCSCommands").getAsJsonArray();

					for (int i = 0; i < allowedCNPCSCommandsArr.size(); i++) {
						allowedCNPCSCommands.add(allowedCNPCSCommandsArr.get(i).getAsString());
					}
				}
			}
			return null;
		}
	}

	private enum EnumConfigSide {
		SERVER, BOTH, CLIENT;
	}
}
