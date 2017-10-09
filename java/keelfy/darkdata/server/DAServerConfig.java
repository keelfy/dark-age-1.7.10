/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.server;

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

import keelfyutils.KUtils;
import keelfyutils.str.KLog;

/**
 * @author keelfy
 * @created 12 июл. 2017 г.
 */
public enum DAServerConfig {
	Instance;

	private GsonBuilder gsonBuilder = new GsonBuilder();

	private String serverSideConfig = "server-side";

	public List<String> allowedCNPCSCommands = new ArrayList<String>();
	public int carrerasFactionId = 24;
	public List<Integer> guardFactionIds = new ArrayList<Integer>();
	public double radiusOfReputationDecreasing = 0;

	public final void init() {
		if (KUtils.PROTECT_SERVER) {
			scanServerSideConfig();
		}
	}

	private void scanServerSideConfig() {
		if (KUtils.PROTECT_SERVER) {
			File path = new File(DAServerFiles.Instance.getDACFolderPath(), serverSideConfig + DAServerFiles.Instance.dacFile);
			try {
				gsonBuilder.registerTypeAdapter(ArrayList.class, new ConfigDeserializer(EnumConfigSide.SERVER));
				Gson gson = gsonBuilder.create();
				Reader reader = new InputStreamReader(new FileInputStream(path));
				gson.fromJson(reader, ArrayList.class);
			} catch (FileNotFoundException e) {
				KLog.fileNotFound(path);
				e.printStackTrace();
			}
		}
	}

	private class ConfigDeserializer implements JsonDeserializer<ArrayList> {

		private EnumConfigSide type;

		public ConfigDeserializer(final EnumConfigSide side) {
			this.type = side;
		}

		@Override
		public ArrayList deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context) throws JsonParseException {
			if (type == EnumConfigSide.SERVER) {
				if (KUtils.PROTECT_SERVER) {
					JsonObject config = json.getAsJsonObject();
					int i;

					JsonArray allowedCNPCSCommandsArr = config.get("Allowed CNPCS Commands").getAsJsonArray();
					for (i = 0; i < allowedCNPCSCommandsArr.size(); i++) {
						allowedCNPCSCommands.add(allowedCNPCSCommandsArr.get(i).getAsString());
					}

					JsonArray guardFactionIdsArr = config.get("Guard Faction Ids").getAsJsonArray();
					for (i = 0; i < guardFactionIdsArr.size(); i++) {
						guardFactionIds.add(guardFactionIdsArr.get(i).getAsInt());
					}

					carrerasFactionId = config.get("Carreras Faction ID").getAsInt();
					radiusOfReputationDecreasing = config.get("Radius Of Reputation Decreasing").getAsDouble();
				}
			}
			return null;
		}
	}

	private enum EnumConfigSide {
		SERVER, BOTH, CLIENT;
	}
}
