/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfyutils.client.shaders;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.lwjgl.opengl.GL20;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfyutils.str.KLog;

/**
 * @author keelfy
 * @created 10 июн. 2017 г.
 */
@SideOnly(Side.CLIENT)
public enum KShaderLoader {
	Instance;

	public static final int loadShader(final File vertexShaderLocation, final File fragmentShaderLocation) {
		final int shaderProgram = glCreateProgram();
		final int vertexShader = glCreateShader(GL_VERTEX_SHADER);
		final int fragmentShader = glCreateShader(GL_FRAGMENT_SHADER);
		final StringBuilder vertexShaderSource = new StringBuilder();
		final StringBuilder fragmentShaderSource = new StringBuilder();
		BufferedReader vertexShaderFileReader = null;

		try {
			vertexShaderFileReader = new BufferedReader(new FileReader(vertexShaderLocation));
			String line;
			while ((line = vertexShaderFileReader.readLine()) != null) {
				vertexShaderSource.append(line).append('\n');
			}
		} catch (final IOException e) {
			e.printStackTrace();
			return -1;
		} finally {
			if (vertexShaderFileReader != null) {
				try {
					vertexShaderFileReader.close();
				} catch (final IOException e) {
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
		} catch (final IOException e) {
			e.printStackTrace();
			return -1;
		} finally {
			if (fragmentShaderFileReader != null) {
				try {
					fragmentShaderFileReader.close();
				} catch (final IOException e) {
					e.printStackTrace();
				}
			}
		}

		glShaderSource(vertexShader, vertexShaderSource);
		glCompileShader(vertexShader);
		if (GL20.glGetShaderi(vertexShader, GL_COMPILE_STATUS) == GL_FALSE) {
			KLog.err("Вертексный шейдер не возможно скомпилировать. Ошибка ->");
			KLog.err(glGetShaderInfoLog(vertexShader, 1024));
			return -1;
		}

		glShaderSource(fragmentShader, fragmentShaderSource);
		glCompileShader(fragmentShader);
		if (GL20.glGetShaderi(fragmentShader, GL_COMPILE_STATUS) == GL_FALSE) {
			KLog.err("Фрагментный шейдер не возможно скомпилировать. Ошибка ->");
			KLog.err(glGetShaderInfoLog(fragmentShader, 1024));
		}

		glAttachShader(shaderProgram, vertexShader);
		glAttachShader(shaderProgram, fragmentShader);
		glLinkProgram(shaderProgram);
		if (GL20.glGetShaderi(shaderProgram, GL_LINK_STATUS) == GL_FALSE) {
			KLog.err("Некоректные пути до шейдеров. Ошибка ->");
			KLog.err(glGetProgramInfoLog(shaderProgram, 1024));
			return -1;
		}

		glDeleteShader(vertexShader);
		glDeleteShader(fragmentShader);
		return shaderProgram;
	}
}
