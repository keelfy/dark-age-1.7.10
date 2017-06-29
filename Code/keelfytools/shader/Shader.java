/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfytools.shader;

import static org.lwjgl.opengl.GL20.glDeleteProgram;

import java.util.Random;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Vector3f;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author keelfy
 * @created 10 июн. 2017 г.
 */
@SideOnly(Side.CLIENT)
public class Shader {
	public final String VERTEX_SHADER_LOCATION;
	public final String FRAGMENT_SHADER_LOCATION;

	public int shaderProgramm = 0;
	public ShaderHelper helper;
	public Random random = new Random();

	private float alpha;
	private Vector3f colorRGB;
	private Vector3f vecPosition;
	private long time;
	private int randomSeed;

	private boolean useAlpha;
	private boolean useColor;
	private boolean useTime;
	private boolean useRandomSeed;
	private boolean useVectorAsPosition;
	private boolean use0;
	private boolean use1;
	private boolean use2;
	private boolean use3;
	private boolean use4;
	private boolean use5;
	private boolean use6;
	private boolean use7;

	public Shader(String vert, String frag) {
		VERTEX_SHADER_LOCATION = vert;
		FRAGMENT_SHADER_LOCATION = frag;
		linkShaderProgramm();
		helper = new ShaderHelper(this);
	}

	public void linkShaderProgramm() {
		shaderProgramm = ShaderLoader.loadShaderPair(VERTEX_SHADER_LOCATION, FRAGMENT_SHADER_LOCATION);
	}

	public void useShader() {

		GL20.glUseProgram(shaderProgramm);
		if (useAlpha) {
			int alphaValue = GL20.glGetUniformLocation(shaderProgramm, "colorA");
			GL20.glUniform1f(alphaValue, alpha);
		}

		if (useColor) {
			int color = GL20.glGetUniformLocation(shaderProgramm, "colorRGB");
			GL20.glUniform3f(color, colorRGB.x, colorRGB.y, colorRGB.z);
		}

		if (useTime) {
			int currentTime = GL20.glGetUniformLocation(shaderProgramm, "systemTime");
			time = System.currentTimeMillis();
			GL20.glUniform1f(currentTime, time);
		}

		if (useRandomSeed) {
			int seed = GL20.glGetUniformLocation(shaderProgramm, "random16Seed");
			randomSeed = random.nextInt(99999999) + 900000000;
			GL20.glUniform1f(seed, randomSeed);
		}

		if (useVectorAsPosition) {
			int pos = GL20.glGetUniformLocation(shaderProgramm, "vecPosition");
			GL20.glUniform3f(pos, vecPosition.x, vecPosition.y, vecPosition.z);
		}

		if (use0) {
			int tex0 = GL20.glGetUniformLocation(shaderProgramm, "texture0");
			GL20.glUniform1i(tex0, 0);
		}

		if (use1) {
			int tex1 = GL20.glGetUniformLocation(shaderProgramm, "texture1");
			GL20.glUniform1i(tex1, 1);
		}

		if (use2) {
			int tex2 = GL20.glGetUniformLocation(shaderProgramm, "texture2");
			GL20.glUniform1i(tex2, 2);
		}

		if (use3) {
			int tex3 = GL20.glGetUniformLocation(shaderProgramm, "texture3");
			GL20.glUniform1i(tex3, 3);
		}

		if (use4) {
			int tex4 = GL20.glGetUniformLocation(shaderProgramm, "texture4");
			GL20.glUniform1i(tex4, 4);
		}

		if (use5) {
			int tex5 = GL20.glGetUniformLocation(shaderProgramm, "texture5");
			GL20.glUniform1i(tex5, 5);
		}

		if (use6) {
			int tex6 = GL20.glGetUniformLocation(shaderProgramm, "texture6");
			GL20.glUniform1i(tex6, 6);
		}
		if (use7) {
			int tex7 = GL20.glGetUniformLocation(shaderProgramm, "texture7");
			GL20.glUniform1i(tex7, 7);
		}
		setUniforms();
	}

	public void setUniforms() {}

	public void unuseShader() {
		GL20.glUseProgram(0);
	}

	private void cleanUp() {
		glDeleteProgram(shaderProgramm);
		Display.destroy();
	}

	public Shader setUseTime(boolean value) {
		useTime = value;
		return this;
	}

	public Shader setAlpha(float value) {
		useAlpha = true;
		alpha = value;
		return this;
	}

	public Shader setColor(Vector3f value) {
		useColor = true;
		colorRGB = value;
		return this;
	}

	public Shader setRandomSeed(int value) {
		useRandomSeed = true;
		randomSeed = value;
		return this;
	}

	public Shader setCustomVector3f(Vector3f value) {
		useVectorAsPosition = true;
		vecPosition = value;
		return this;
	}

	public void useTexture(int img0to7) {
		switch (img0to7) {
		case 0:
			use0 = true;
			break;
		case 1:
			use1 = true;
			break;
		case 2:
			use2 = true;
			break;
		case 3:
			use3 = true;
			break;
		case 4:
			use4 = true;
			break;
		case 5:
			use5 = true;
			break;
		case 6:
			use6 = true;
			break;
		case 7:
			use7 = true;
			break;
		}
	}

	public void activeTexture(int img0to7, int texture) {
		GL13.glActiveTexture(GL13.GL_TEXTURE0 + img0to7);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
	}
}
