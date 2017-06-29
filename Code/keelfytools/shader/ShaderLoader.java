/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfytools.shader;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.GL_COMPILE_STATUS;
import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_LINK_STATUS;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;
import static org.lwjgl.opengl.GL20.glAttachShader;
import static org.lwjgl.opengl.GL20.glCompileShader;
import static org.lwjgl.opengl.GL20.glCreateProgram;
import static org.lwjgl.opengl.GL20.glCreateShader;
import static org.lwjgl.opengl.GL20.glDeleteShader;
import static org.lwjgl.opengl.GL20.glGetProgramInfoLog;
import static org.lwjgl.opengl.GL20.glGetShaderInfoLog;
import static org.lwjgl.opengl.GL20.glLinkProgram;
import static org.lwjgl.opengl.GL20.glShaderSource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.lwjgl.opengl.GL20;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfytools.log.KeelfyLog;

/**
 * @author keelfy
 * @created 10 июн. 2017 г.
 */
@SideOnly(Side.CLIENT)
public class ShaderLoader {

	public static int loadShaderPair(String vertexShaderLocation, String fragmentShaderLocation) {
		int shaderProgram = glCreateProgram();
		int vertexShader = glCreateShader(GL_VERTEX_SHADER);
		int fragmentShader = glCreateShader(GL_FRAGMENT_SHADER);
		StringBuilder vertexShaderSource = new StringBuilder();
		StringBuilder fragmentShaderSource = new StringBuilder();
		BufferedReader vertexShaderFileReader = null;

		try {
			vertexShaderFileReader = new BufferedReader(new FileReader(vertexShaderLocation));
			String line;
			while ((line = vertexShaderFileReader.readLine()) != null) {
				vertexShaderSource.append(line).append('\n');
			}
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		} finally {
			if (vertexShaderFileReader != null) {
				try {
					vertexShaderFileReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		BufferedReader fragmentShaderFileReader = null;
		try {
			fragmentShaderFileReader = new BufferedReader(new FileReader(fragmentShaderLocation));
			String line;
			while ((line = fragmentShaderFileReader.readLine()) != null) {
				fragmentShaderSource.append(line).append('\n');
			}
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		} finally {
			if (fragmentShaderFileReader != null) {
				try {
					fragmentShaderFileReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		glShaderSource(vertexShader, vertexShaderSource);
		glCompileShader(vertexShader);
		if (GL20.glGetShader(vertexShader, GL_COMPILE_STATUS) == GL_FALSE) {
			KeelfyLog.err("Вертексный шейдер не возможно скомпилировать. Ошибка ->");
			KeelfyLog.err(glGetShaderInfoLog(vertexShader, 1024));
			return -1;
		}

		glShaderSource(fragmentShader, fragmentShaderSource);
		glCompileShader(fragmentShader);
		if (GL20.glGetShader(fragmentShader, GL_COMPILE_STATUS) == GL_FALSE) {
			KeelfyLog.err("Фрагментный шейдер не возможно скомпилировать. Ошибка ->");
			KeelfyLog.err(glGetShaderInfoLog(fragmentShader, 1024));
		}

		glAttachShader(shaderProgram, vertexShader);
		glAttachShader(shaderProgram, fragmentShader);
		glLinkProgram(shaderProgram);
		if (GL20.glGetShader(shaderProgram, GL_LINK_STATUS) == GL_FALSE) {
			KeelfyLog.err("Некоректные пути до шейдеров. Ошибка ->");
			KeelfyLog.err(glGetProgramInfoLog(shaderProgram, 1024));
			return -1;
		}

		glDeleteShader(vertexShader);
		glDeleteShader(fragmentShader);
		return shaderProgram;
	}
}