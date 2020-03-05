/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfyutils.client.shaders;

import static org.lwjgl.opengl.ARBShaderObjects.*;

import java.io.File;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author keelfy
 * @created 10 июн. 2017 г.
 */
@SideOnly(Side.CLIENT)
public final class KShaderHelper {

	public KShader shader;
	public KShaderLoader shaderloader = KShaderLoader.Instance;
	public int currentProgram;

	public KShaderHelper(final KShader shader) {
		this.shader = shader;
		currentProgram = shader.shaderProgramm;
	}

	public static float alphaValue = 1.0f;

	public final void setProgramUniform1i(final String name, final int x) {
		if (currentProgram == 0)
			return;
		final int uniform = glGetUniformLocationARB(currentProgram, name);
		glUniform1iARB(uniform, x);
	}

	public final void setProgramUniform1f(final String name, final float x) {
		if (currentProgram == 0)
			return;
		final int uniform = glGetUniformLocationARB(currentProgram, name);
		glUniform1fARB(uniform, x);
	}

	public final void setProgramUniform2f(final String name, final float x, final float y) {
		if (currentProgram == 0)
			return;
		final int uniform = glGetUniformLocationARB(currentProgram, name);
		glUniform2fARB(uniform, x, y);
	}

	public final void setProgramUniform3f(final String name, final float x, final float y, final float z) {
		if (currentProgram == 0)
			return;
		final int uniform = glGetUniformLocationARB(currentProgram, name);
		glUniform3fARB(uniform, x, y, z);
	}

	public void reloadShaderProgram(int shaderprogram, final File vert, final File frag) {
		shaderprogram = shaderloader.loadShader(vert, frag);
	}

}
