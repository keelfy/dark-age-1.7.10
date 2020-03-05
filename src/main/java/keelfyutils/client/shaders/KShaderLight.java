/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfyutils.client.shaders;

import java.io.File;

import org.lwjgl.util.vector.Vector3f;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author keelfy
 * @created 10 июн. 2017 г.
 */
@SideOnly(Side.CLIENT)
public final class KShaderLight extends KShader {

	public KShaderLight(final File vert, final File frag) {
		super(vert, frag);
	}

	public Vector3f mAmbient = new Vector3f(0.6f, 0.6f, 0.6f);
	public Vector3f mDiffuse = new Vector3f(0.2f, 0.2f, 0.2f);
	public Vector3f mSpecular = new Vector3f(0.9f, 0.9f, 0.9f);

	public Vector3f lAmbient = new Vector3f(0.6f, 0.6f, 0.6f);
	public Vector3f lDiffuse = new Vector3f(0.2f, 0.2f, 0.2f);
	public Vector3f lSpecular = new Vector3f(0.9f, 0.9f, 0.9f);

	public Vector3f lightPosition = new Vector3f(0, 0, 0);

	public float shininess = 0.5f;

	public void setLightAmbient(final Vector3f ambient) {
		lAmbient = ambient;
	}

	public void setLightDiffuse(final Vector3f diffuse) {
		lDiffuse = diffuse;
	}

	public void setLightSpecular(final Vector3f specular) {
		lSpecular = specular;
	}

	public void setMaterialAmbient(final Vector3f ambient) {
		mAmbient = ambient;
	}

	public void setMaterialDiffuse(final Vector3f diffuse) {
		mDiffuse = diffuse;
	}

	public void setMaterialSpecular(final Vector3f specular) {
		mSpecular = specular;
	}

	public void setShininess(final float shine) {
		shininess = shine;
	}

	public void setPosition(final Vector3f pos) {
		lightPosition = pos;
	}

	public void setLightColorMatrix(final Vector3f vec3) {
		mAmbient = new Vector3f((float) (vec3.x / 0.6), (float) (vec3.y / 0.6), (float) (vec3.z / 0.6));
		mDiffuse = new Vector3f((float) (vec3.x / 0.2), (float) (vec3.y / 0.2), (float) (vec3.z / 0.2));
		mSpecular = new Vector3f((float) (vec3.x / 0.9), (float) (vec3.y / 0.9), (float) (vec3.z / 0.9));
	}

	@Override
	public void setUniforms() {
		helper.setProgramUniform3f("materialAmbient", mAmbient.x, mAmbient.y, mAmbient.z);
		helper.setProgramUniform3f("materialDiffuse", mDiffuse.x, mDiffuse.y, mDiffuse.z);
		helper.setProgramUniform3f("materialSpecular", mSpecular.x, mSpecular.y, mSpecular.z);
		helper.setProgramUniform3f("lightAmbient", lAmbient.x, lAmbient.y, lAmbient.z);
		helper.setProgramUniform3f("lightDiffuse", lDiffuse.x, lDiffuse.y, lDiffuse.z);
		helper.setProgramUniform3f("lightSpecular", lSpecular.x, lSpecular.y, lSpecular.z);
		helper.setProgramUniform3f("lightPosition", lightPosition.x, lightPosition.y, lightPosition.z);
		helper.setProgramUniform1f("shininess", shininess);
	}
}
