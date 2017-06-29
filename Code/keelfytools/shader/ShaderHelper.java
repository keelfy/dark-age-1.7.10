/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfytools.shader;

import static org.lwjgl.opengl.ARBShaderObjects.glGetUniformLocationARB;
import static org.lwjgl.opengl.ARBShaderObjects.glUniform1fARB;
import static org.lwjgl.opengl.ARBShaderObjects.glUniform1iARB;
import static org.lwjgl.opengl.ARBShaderObjects.glUniform2fARB;
import static org.lwjgl.opengl.ARBShaderObjects.glUniform3fARB;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author keelfy
 * @created 10 июн. 2017 г.
 */
@SideOnly(Side.CLIENT)
public class ShaderHelper {

	public Shader shader;
	public ShaderLoader shaderloader;
	public int currentProgram;

	public ShaderHelper(Shader shader) {
		this.shader = shader;
		currentProgram = shader.shaderProgramm;
		shaderloader = new ShaderLoader();
	}

	public static float alphaValue = 1.0f;

	public void setProgramUniform1i(String name, int x) {
		if (currentProgram == 0) {
			return;
		}
		int uniform = glGetUniformLocationARB(currentProgram, name);
		glUniform1iARB(uniform, x);
	}

	public void setProgramUniform1f(String name, float x) {
		if (currentProgram == 0) {
			return;
		}
		int uniform = glGetUniformLocationARB(currentProgram, name);
		glUniform1fARB(uniform, x);
	}

	public void setProgramUniform2f(String name, float x, float y) {
		if (currentProgram == 0) {
			return;
		}
		int uniform = glGetUniformLocationARB(currentProgram, name);
		glUniform2fARB(uniform, x, y);
	}

	public void setProgramUniform3f(String name, float x, float y, float z) {
		if (currentProgram == 0) {
			return;
		}
		int uniform = glGetUniformLocationARB(currentProgram, name);
		glUniform3fARB(uniform, x, y, z);
	}

	public void reloadShaderProgram(int shaderprogram, String vert, String frag) {
		shaderprogram = shaderloader.loadShaderPair(vert, frag);
	}

}
