package noppes.npcs.config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedList;

import noppes.npcs.LogWriter;

public class ConfigLoader {

	private boolean updateFile = false;
	private File dir;
	private String fileName;
	private Class<?> configClass;
	private LinkedList<Field> configFields;

	public ConfigLoader(final Class<?> clss, final File dir, final String fileName) {
		if (!dir.exists()) {
			dir.mkdir();
		}

		this.dir = dir;
		configClass = clss;
		configFields = new LinkedList();
		this.fileName = fileName + ".cfg";
		Field fields[] = configClass.getDeclaredFields();
		for (Field field : fields) {
			if (field.isAnnotationPresent(ConfigProp.class)) {
				configFields.add(field);
			}
		}
	}

	public void loadConfig() {
		try {
			File configFile = new File(dir, fileName);
			HashMap<String, Field> types = new HashMap();
			for (Field field : configFields) {
				ConfigProp prop = field.getAnnotation(ConfigProp.class);
				types.put(!prop.name().isEmpty() ? prop.name() : field.getName(), field);
			}
			if (configFile.exists()) {
				HashMap<String, Object> properties = parseConfig(configFile, types);
				for (String prop : properties.keySet()) {
					Field field = types.get(prop);
					Object obj = properties.get(prop);
					if (!obj.equals(field.get(null))) {
						field.set(null, obj);
					}
				}
				for (String type : types.keySet())
					if (!properties.containsKey(type)) {
						updateFile = true;
					}
			} else {
				updateFile = true;
			}
		} catch (Exception e) {
			updateFile = true;
			LogWriter.except(e);
		}
		if (updateFile) {
			updateConfig();
		}
		updateFile = false;
	}

	private HashMap<String, Object> parseConfig(final File file, final HashMap<String, Field> types) throws Exception {
		HashMap<String, Object> config = new HashMap();
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String strLine;
		while ((strLine = reader.readLine()) != null) {
			if (strLine.startsWith("#") || strLine.length() == 0) {
				continue;
			}
			int index = strLine.indexOf("=");
			if (index <= 0 || index == strLine.length()) {
				updateFile = true;
				continue;
			}
			String name = strLine.substring(0, index);
			String prop = strLine.substring(index + 1);
			if (!types.containsKey(name)) {
				updateFile = true;
				continue;
			}
			Object obj = null;
			Class<?> class2 = types.get(name).getType();
			if (class2.isAssignableFrom(java.lang.String.class)) {
				obj = prop;
			} else if (class2.isAssignableFrom(Integer.TYPE)) {
				obj = Integer.valueOf(Integer.parseInt(prop));
			} else if (class2.isAssignableFrom(Short.TYPE)) {
				obj = Short.valueOf(Short.parseShort(prop));
			} else if (class2.isAssignableFrom(Byte.TYPE)) {
				obj = Byte.valueOf(Byte.parseByte(prop));
			} else if (class2.isAssignableFrom(Boolean.TYPE)) {
				obj = Boolean.valueOf(Boolean.parseBoolean(prop));
			} else if (class2.isAssignableFrom(Float.TYPE)) {
				obj = Float.valueOf(Float.parseFloat(prop));
			} else if (class2.isAssignableFrom(Double.TYPE)) {
				obj = Double.valueOf(Double.parseDouble(prop));
			}
			if (obj != null) {
				config.put(name, obj);
			}
		}
		reader.close();
		return config;
	}

	public void updateConfig() {
		File file = new File(dir, fileName);
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			BufferedWriter out = new BufferedWriter(new FileWriter(file));
			for (Field field : configFields) {
				ConfigProp prop = field.getAnnotation(ConfigProp.class);
				if (prop.info().length() != 0) {
					out.write("#" + prop.info() + System.getProperty("line.separator"));
				}
				String name = !prop.name().isEmpty() ? prop.name() : field.getName();
				try {
					out.write(name + "=" + field.get(null).toString() + System.getProperty("line.separator"));
					out.write(System.getProperty("line.separator"));
				} catch (IllegalArgumentException e) {

					e.printStackTrace();
				} catch (IllegalAccessException e) {

					e.printStackTrace();
				}
			}
			out.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
