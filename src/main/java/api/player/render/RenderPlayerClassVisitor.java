package api.player.render;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Stack;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import keelfyutils.str.KString;

public final class RenderPlayerClassVisitor extends ClassVisitor {
	public static final String targetClassName = "net.minecraft.client.renderer.entity.RenderPlayer";

	private boolean hadLocalDoRenderLabel;
	private boolean hadLocalDoRenderShadowAndFire;
	private boolean hadLocalGetColorMultiplier;
	private boolean hadLocalGetDeathMaxRotation;
	private boolean hadLocalGetFontRendererFromRenderManager;
	private boolean hadLocalGetResourceLocationFromPlayer;
	private boolean hadLocalHandleRotationFloat;
	private boolean hadLocalInheritRenderPass;
	private boolean hadLocalLoadTexture;
	private boolean hadLocalLoadTextureOfEntity;
	private boolean hadLocalPassSpecialRender;
	private boolean hadLocalPerformStaticEntityRebuild;
	private boolean hadLocalRenderArrowsStuckInEntity;
	private boolean hadLocalRenderFirstPersonArm;
	private boolean hadLocalRenderLivingLabel;
	private boolean hadLocalRenderModel;
	private boolean hadLocalRenderPlayer;
	private boolean hadLocalRenderPlayerNameAndScoreLabel;
	private boolean hadLocalRenderPlayerScale;
	private boolean hadLocalRenderPlayerSleep;
	private boolean hadLocalRenderSpecials;
	private boolean hadLocalRenderSwingProgress;
	private boolean hadLocalRotatePlayer;
	private boolean hadLocalSetArmorModel;
	private boolean hadLocalSetPassArmorModel;
	private boolean hadLocalSetRenderManager;
	private boolean hadLocalSetRenderPassModel;
	private boolean hadLocalUpdateIcons;

	public static byte[] transform(byte[] bytes, boolean isObfuscated,
			Map<String, Stack<String>> constructorReplacements) {
		try {
			ByteArrayInputStream in = new ByteArrayInputStream(bytes);
			ClassReader cr = new ClassReader(in);
			ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
			RenderPlayerClassVisitor p = new RenderPlayerClassVisitor(cw, isObfuscated, constructorReplacements);

			cr.accept(p, 0);

			byte[] result = cw.toByteArray();
			in.close();
			return result;
		} catch (IOException ioe) {
			throw new RuntimeException(ioe);
		}
	}

	private final boolean isObfuscated;
	private final Map<String, Stack<String>> constructorReplacements;

	public RenderPlayerClassVisitor(ClassVisitor classVisitor, boolean isObfuscated,
			Map<String, Stack<String>> constructorReplacements) {
		super(262144, classVisitor);
		this.isObfuscated = isObfuscated;
		this.constructorReplacements = constructorReplacements;
	}

	@Override
	public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
		String[] newInterfaces = new String[interfaces.length + 1];
		for (int i = 0; i < interfaces.length; i++)
			newInterfaces[i] = interfaces[i];
		newInterfaces[interfaces.length] = "api/player/render/IRenderPlayerAPI";
		super.visit(version, access, name, signature, superName, newInterfaces);
	}

	@Override
	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
		if (name.equals("<init>"))
			return new RenderPlayerConstructorVisitor(super.visitMethod(access, name, desc, signature, exceptions),
					isObfuscated, constructorReplacements);

		if (name.equals(isObfuscated ? "b" : "func_110813_b")
				&& desc.equals(isObfuscated ? "(Lsv;)Z" : "(Lnet/minecraft/entity/EntityLivingBase;)Z")) {
			hadLocalDoRenderLabel = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localDoRenderLabel", desc, signature,
					exceptions);
		}

		if (name.equals(isObfuscated ? "b" : "doRenderShadowAndFire")
				&& desc.equals(isObfuscated ? "(Lsa;DDDFF)V" : "(Lnet/minecraft/entity/Entity;DDDFF)V")) {
			hadLocalDoRenderShadowAndFire = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localDoRenderShadowAndFire", desc,
					signature, exceptions);
		}

		if (name.equals(isObfuscated ? "a" : "getColorMultiplier")
				&& desc.equals(isObfuscated ? "(Lsv;FF)I" : "(Lnet/minecraft/entity/EntityLivingBase;FF)I")) {
			hadLocalGetColorMultiplier = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localGetColorMultiplier", desc, signature,
					exceptions);
		}

		if (name.equals(isObfuscated ? "a" : "getDeathMaxRotation")
				&& desc.equals(isObfuscated ? "(Lsv;)F" : "(Lnet/minecraft/entity/EntityLivingBase;)F")) {
			hadLocalGetDeathMaxRotation = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localGetDeathMaxRotation", desc,
					signature, exceptions);
		}

		if (name.equals(isObfuscated ? "c" : "getFontRendererFromRenderManager")
				&& desc.equals(isObfuscated ? "()Lbbu;" : "()Lnet/minecraft/client/gui/FontRenderer;")) {
			hadLocalGetFontRendererFromRenderManager = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localGetFontRendererFromRenderManager",
					desc, signature, exceptions);
		}

		if (name.equals(isObfuscated ? "a" : "getEntityTexture") && desc.equals(isObfuscated ? "(Lblg;)Lbqx;"
				: "(Lnet/minecraft/client/entity/AbstractClientPlayer;)Lnet/minecraft/util/ResourceLocation;")) {
			hadLocalGetResourceLocationFromPlayer = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localGetResourceLocationFromPlayer", desc,
					signature, exceptions);
		}

		if (name.equals(isObfuscated ? "b" : "handleRotationFloat")
				&& desc.equals(isObfuscated ? "(Lsv;F)F" : "(Lnet/minecraft/entity/EntityLivingBase;F)F")) {
			hadLocalHandleRotationFloat = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localHandleRotationFloat", desc,
					signature, exceptions);
		}

		if (name.equals(isObfuscated ? "b" : "inheritRenderPass")
				&& desc.equals(isObfuscated ? "(Lsv;IF)I" : "(Lnet/minecraft/entity/EntityLivingBase;IF)I")) {
			hadLocalInheritRenderPass = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localInheritRenderPass", desc, signature,
					exceptions);
		}

		if (name.equals(isObfuscated ? "a" : "bindTexture")
				&& desc.equals(isObfuscated ? "(Lbqx;)V" : "(Lnet/minecraft/util/ResourceLocation;)V")) {
			hadLocalLoadTexture = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localLoadTexture", desc, signature,
					exceptions);
		}

		if (name.equals(isObfuscated ? "b" : "bindEntityTexture")
				&& desc.equals(isObfuscated ? "(Lsa;)V" : "(Lnet/minecraft/entity/Entity;)V")) {
			hadLocalLoadTextureOfEntity = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localLoadTextureOfEntity", desc,
					signature, exceptions);
		}

		if (name.equals(isObfuscated ? "b" : "passSpecialRender")
				&& desc.equals(isObfuscated ? "(Lsv;DDD)V" : "(Lnet/minecraft/entity/EntityLivingBase;DDD)V")) {
			hadLocalPassSpecialRender = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localPassSpecialRender", desc, signature,
					exceptions);
		}

		if (name.equals(isObfuscated ? "a" : "isStaticEntity") && desc.equals("()Z")) {
			hadLocalPerformStaticEntityRebuild = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localPerformStaticEntityRebuild", desc,
					signature, exceptions);
		}

		if (name.equals(isObfuscated ? "e" : "renderArrowsStuckInEntity")
				&& desc.equals(isObfuscated ? "(Lsv;F)V" : "(Lnet/minecraft/entity/EntityLivingBase;F)V")) {
			hadLocalRenderArrowsStuckInEntity = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRenderArrowsStuckInEntity", desc,
					signature, exceptions);
		}

		if (name.equals(isObfuscated ? "a" : "renderFirstPersonArm")
				&& desc.equals(isObfuscated ? "(Lyz;)V" : "(Lnet/minecraft/entity/player/EntityPlayer;)V")) {
			hadLocalRenderFirstPersonArm = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRenderFirstPersonArm", desc,
					signature, exceptions);
		}

		if (name.equals(isObfuscated ? "a" : "func_147906_a")
				&& desc.equals(isObfuscated ? "(Lsa;Ljava/lang/String;DDDI)V"
						: "(Lnet/minecraft/entity/Entity;Ljava/lang/String;DDDI)V")) {
			hadLocalRenderLivingLabel = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRenderLivingLabel", desc, signature,
					exceptions);
		}

		if (name.equals(isObfuscated ? "a" : "renderModel")
				&& desc.equals(isObfuscated ? "(Lsv;FFFFFF)V" : "(Lnet/minecraft/entity/EntityLivingBase;FFFFFF)V")) {
			hadLocalRenderModel = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRenderModel", desc, signature,
					exceptions);
		}

		if (name.equals(isObfuscated ? "a" : "doRender") && desc.equals(
				isObfuscated ? "(Lblg;DDDFF)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDFF)V")) {
			hadLocalRenderPlayer = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRenderPlayer", desc, signature,
					exceptions);
		}

		if (name.equals(isObfuscated ? "a" : "func_96449_a")
				&& desc.equals(isObfuscated ? "(Lblg;DDDLjava/lang/String;FD)V"
						: "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDLjava/lang/String;FD)V")) {
			hadLocalRenderPlayerNameAndScoreLabel = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRenderPlayerNameAndScoreLabel", desc,
					signature, exceptions);
		}

		if (name.equals(isObfuscated ? "b" : "preRenderCallback")
				&& desc.equals(isObfuscated ? "(Lblg;F)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;F)V")) {
			hadLocalRenderPlayerScale = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRenderPlayerScale", desc, signature,
					exceptions);
		}

		if (name.equals(isObfuscated ? "a" : "renderLivingAt") && desc
				.equals(isObfuscated ? "(Lblg;DDD)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDD)V")) {
			hadLocalRenderPlayerSleep = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRenderPlayerSleep", desc, signature,
					exceptions);
		}

		if (name.equals(isObfuscated ? "a" : "renderEquippedItems")
				&& desc.equals(isObfuscated ? "(Lblg;F)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;F)V")) {
			hadLocalRenderSpecials = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRenderSpecials", desc, signature,
					exceptions);
		}

		if (name.equals(isObfuscated ? "d" : "renderSwingProgress")
				&& desc.equals(isObfuscated ? "(Lsv;F)F" : "(Lnet/minecraft/entity/EntityLivingBase;F)F")) {
			hadLocalRenderSwingProgress = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRenderSwingProgress", desc,
					signature, exceptions);
		}

		if (name.equals(isObfuscated ? "a" : "rotateCorpse") && desc
				.equals(isObfuscated ? "(Lblg;FFF)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;FFF)V")) {
			hadLocalRotatePlayer = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRotatePlayer", desc, signature,
					exceptions);
		}

		if (name.equals(isObfuscated ? "a" : "shouldRenderPass") && desc
				.equals(isObfuscated ? "(Lblg;IF)I" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;IF)I")) {
			hadLocalSetArmorModel = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localSetArmorModel", desc, signature,
					exceptions);
		}

		if (name.equals(isObfuscated ? "b" : "func_82408_c") && desc
				.equals(isObfuscated ? "(Lblg;IF)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;IF)V")) {
			hadLocalSetPassArmorModel = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localSetPassArmorModel", desc, signature,
					exceptions);
		}

		if (name.equals(isObfuscated ? "a" : "setRenderManager")
				&& desc.equals(isObfuscated ? "(Lbnn;)V" : "(Lnet/minecraft/client/renderer/entity/RenderManager;)V")) {
			hadLocalSetRenderManager = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localSetRenderManager", desc, signature,
					exceptions);
		}

		if (name.equals(isObfuscated ? "a" : "setRenderPassModel")
				&& desc.equals(isObfuscated ? "(Lbhr;)V" : "(Lnet/minecraft/client/model/ModelBase;)V")) {
			hadLocalSetRenderPassModel = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localSetRenderPassModel", desc, signature,
					exceptions);
		}

		if (name.equals(isObfuscated ? "a" : "updateIcons")
				&& desc.equals(isObfuscated ? "(Lrg;)V" : "(Lnet/minecraft/client/renderer/texture/IIconRegister;)V")) {
			hadLocalUpdateIcons = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localUpdateIcons", desc, signature,
					exceptions);
		}

		return super.visitMethod(access, name, desc, signature, exceptions);
	}

	@Override
	public void visitEnd() {
		MethodVisitor mv;

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "b" : "func_110813_b", KString.EMPTY
				+ (isObfuscated ? "(Lsv;)Z" : "(Lnet/minecraft/entity/EntityLivingBase;)Z") + KString.EMPTY, null,
				null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "doRenderLabel",
				"(Lapi/player/render/IRenderPlayerAPI;"
						+ (isObfuscated ? "Lsv;" : "Lnet/minecraft/entity/EntityLivingBase;") + ")Z");
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realDoRenderLabel", KString.EMPTY
				+ (isObfuscated ? "(Lsv;)Z" : "(Lnet/minecraft/entity/EntityLivingBase;)Z") + KString.EMPTY, null,
				null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
				isObfuscated ? "bop" : "net/minecraft/client/renderer/entity/RenderPlayer",
				isObfuscated ? "b" : "func_110813_b",
				KString.EMPTY + (isObfuscated ? "(Lsv;)Z" : "(Lnet/minecraft/entity/EntityLivingBase;)Z")
						+ KString.EMPTY);
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superDoRenderLabel", KString.EMPTY
				+ (isObfuscated ? "(Lsv;)Z" : "(Lnet/minecraft/entity/EntityLivingBase;)Z") + KString.EMPTY, null,
				null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL,
				isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RendererLivingEntity",
				isObfuscated ? "b" : "func_110813_b",
				KString.EMPTY + (isObfuscated ? "(Lsv;)Z" : "(Lnet/minecraft/entity/EntityLivingBase;)Z")
						+ KString.EMPTY);
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if (!hadLocalDoRenderLabel) {
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localDoRenderLabel", KString.EMPTY
					+ (isObfuscated ? "(Lsv;)Z" : "(Lnet/minecraft/entity/EntityLivingBase;)Z") + KString.EMPTY,
					null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL,
					isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RendererLivingEntity",
					isObfuscated ? "b" : "func_110813_b",
					KString.EMPTY + (isObfuscated ? "(Lsv;)Z" : "(Lnet/minecraft/entity/EntityLivingBase;)Z")
							+ KString.EMPTY);
			mv.visitInsn(Opcodes.IRETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "b" : "doRenderShadowAndFire", KString.EMPTY
				+ (isObfuscated ? "(Lsa;DDDFF)V" : "(Lnet/minecraft/entity/Entity;DDDFF)V") + KString.EMPTY, null,
				null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.DLOAD, 2);
		mv.visitVarInsn(Opcodes.DLOAD, 4);
		mv.visitVarInsn(Opcodes.DLOAD, 6);
		mv.visitVarInsn(Opcodes.FLOAD, 8);
		mv.visitVarInsn(Opcodes.FLOAD, 9);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "doRenderShadowAndFire",
				"(Lapi/player/render/IRenderPlayerAPI;"
						+ (isObfuscated ? "Lsa;DDDFF" : "Lnet/minecraft/entity/Entity;DDDFF") + ")V");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realDoRenderShadowAndFire", KString.EMPTY
				+ (isObfuscated ? "(Lsa;DDDFF)V" : "(Lnet/minecraft/entity/Entity;DDDFF)V") + KString.EMPTY, null,
				null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.DLOAD, 2);
		mv.visitVarInsn(Opcodes.DLOAD, 4);
		mv.visitVarInsn(Opcodes.DLOAD, 6);
		mv.visitVarInsn(Opcodes.FLOAD, 8);
		mv.visitVarInsn(Opcodes.FLOAD, 9);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
				isObfuscated ? "bop" : "net/minecraft/client/renderer/entity/RenderPlayer",
				isObfuscated ? "b" : "doRenderShadowAndFire",
				KString.EMPTY + (isObfuscated ? "(Lsa;DDDFF)V" : "(Lnet/minecraft/entity/Entity;DDDFF)V")
						+ KString.EMPTY);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superDoRenderShadowAndFire", KString.EMPTY
				+ (isObfuscated ? "(Lsa;DDDFF)V" : "(Lnet/minecraft/entity/Entity;DDDFF)V") + KString.EMPTY, null,
				null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.DLOAD, 2);
		mv.visitVarInsn(Opcodes.DLOAD, 4);
		mv.visitVarInsn(Opcodes.DLOAD, 6);
		mv.visitVarInsn(Opcodes.FLOAD, 8);
		mv.visitVarInsn(Opcodes.FLOAD, 9);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL,
				isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RendererLivingEntity",
				isObfuscated ? "b" : "doRenderShadowAndFire",
				KString.EMPTY + (isObfuscated ? "(Lsa;DDDFF)V" : "(Lnet/minecraft/entity/Entity;DDDFF)V")
						+ KString.EMPTY);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if (!hadLocalDoRenderShadowAndFire) {
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localDoRenderShadowAndFire", KString.EMPTY
					+ (isObfuscated ? "(Lsa;DDDFF)V" : "(Lnet/minecraft/entity/Entity;DDDFF)V") + KString.EMPTY,
					null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitVarInsn(Opcodes.DLOAD, 2);
			mv.visitVarInsn(Opcodes.DLOAD, 4);
			mv.visitVarInsn(Opcodes.DLOAD, 6);
			mv.visitVarInsn(Opcodes.FLOAD, 8);
			mv.visitVarInsn(Opcodes.FLOAD, 9);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL,
					isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RendererLivingEntity",
					isObfuscated ? "b" : "doRenderShadowAndFire",
					KString.EMPTY + (isObfuscated ? "(Lsa;DDDFF)V" : "(Lnet/minecraft/entity/Entity;DDDFF)V")
							+ KString.EMPTY);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "a" : "getColorMultiplier", KString.EMPTY
				+ (isObfuscated ? "(Lsv;FF)I" : "(Lnet/minecraft/entity/EntityLivingBase;FF)I") + KString.EMPTY,
				null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitVarInsn(Opcodes.FLOAD, 3);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "getColorMultiplier",
				"(Lapi/player/render/IRenderPlayerAPI;"
						+ (isObfuscated ? "Lsv;FF" : "Lnet/minecraft/entity/EntityLivingBase;FF") + ")I");
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realGetColorMultiplier", KString.EMPTY
				+ (isObfuscated ? "(Lsv;FF)I" : "(Lnet/minecraft/entity/EntityLivingBase;FF)I") + KString.EMPTY,
				null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitVarInsn(Opcodes.FLOAD, 3);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
				isObfuscated ? "bop" : "net/minecraft/client/renderer/entity/RenderPlayer",
				isObfuscated ? "a" : "getColorMultiplier",
				KString.EMPTY + (isObfuscated ? "(Lsv;FF)I" : "(Lnet/minecraft/entity/EntityLivingBase;FF)I")
						+ KString.EMPTY);
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superGetColorMultiplier", KString.EMPTY
				+ (isObfuscated ? "(Lsv;FF)I" : "(Lnet/minecraft/entity/EntityLivingBase;FF)I") + KString.EMPTY,
				null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitVarInsn(Opcodes.FLOAD, 3);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL,
				isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RendererLivingEntity",
				isObfuscated ? "a" : "getColorMultiplier",
				KString.EMPTY + (isObfuscated ? "(Lsv;FF)I" : "(Lnet/minecraft/entity/EntityLivingBase;FF)I")
						+ KString.EMPTY);
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if (!hadLocalGetColorMultiplier) {
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localGetColorMultiplier",
					KString.EMPTY + (isObfuscated ? "(Lsv;FF)I" : "(Lnet/minecraft/entity/EntityLivingBase;FF)I")
							+ KString.EMPTY,
					null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitVarInsn(Opcodes.FLOAD, 2);
			mv.visitVarInsn(Opcodes.FLOAD, 3);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL,
					isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RendererLivingEntity",
					isObfuscated ? "a" : "getColorMultiplier",
					KString.EMPTY + (isObfuscated ? "(Lsv;FF)I" : "(Lnet/minecraft/entity/EntityLivingBase;FF)I")
							+ KString.EMPTY);
			mv.visitInsn(Opcodes.IRETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "a" : "getDeathMaxRotation", KString.EMPTY
				+ (isObfuscated ? "(Lsv;)F" : "(Lnet/minecraft/entity/EntityLivingBase;)F") + KString.EMPTY, null,
				null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "getDeathMaxRotation",
				"(Lapi/player/render/IRenderPlayerAPI;"
						+ (isObfuscated ? "Lsv;" : "Lnet/minecraft/entity/EntityLivingBase;") + ")F");
		mv.visitInsn(Opcodes.FRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realGetDeathMaxRotation", KString.EMPTY
				+ (isObfuscated ? "(Lsv;)F" : "(Lnet/minecraft/entity/EntityLivingBase;)F") + KString.EMPTY, null,
				null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
				isObfuscated ? "bop" : "net/minecraft/client/renderer/entity/RenderPlayer",
				isObfuscated ? "a" : "getDeathMaxRotation",
				KString.EMPTY + (isObfuscated ? "(Lsv;)F" : "(Lnet/minecraft/entity/EntityLivingBase;)F")
						+ KString.EMPTY);
		mv.visitInsn(Opcodes.FRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superGetDeathMaxRotation", KString.EMPTY
				+ (isObfuscated ? "(Lsv;)F" : "(Lnet/minecraft/entity/EntityLivingBase;)F") + KString.EMPTY, null,
				null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL,
				isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RendererLivingEntity",
				isObfuscated ? "a" : "getDeathMaxRotation",
				KString.EMPTY + (isObfuscated ? "(Lsv;)F" : "(Lnet/minecraft/entity/EntityLivingBase;)F")
						+ KString.EMPTY);
		mv.visitInsn(Opcodes.FRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if (!hadLocalGetDeathMaxRotation) {
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localGetDeathMaxRotation", KString.EMPTY
					+ (isObfuscated ? "(Lsv;)F" : "(Lnet/minecraft/entity/EntityLivingBase;)F") + KString.EMPTY,
					null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL,
					isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RendererLivingEntity",
					isObfuscated ? "a" : "getDeathMaxRotation",
					KString.EMPTY + (isObfuscated ? "(Lsv;)F" : "(Lnet/minecraft/entity/EntityLivingBase;)F")
							+ KString.EMPTY);
			mv.visitInsn(Opcodes.FRETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(
				Opcodes.ACC_PUBLIC, isObfuscated ? "c" : "getFontRendererFromRenderManager", KString.EMPTY
						+ (isObfuscated ? "()Lbbu;" : "()Lnet/minecraft/client/gui/FontRenderer;") + KString.EMPTY,
				null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI",
				"getFontRendererFromRenderManager", "(Lapi/player/render/IRenderPlayerAPI;)"
						+ (isObfuscated ? "Lbbu;" : "Lnet/minecraft/client/gui/FontRenderer;") + KString.EMPTY);
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(
				Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realGetFontRendererFromRenderManager", KString.EMPTY
						+ (isObfuscated ? "()Lbbu;" : "()Lnet/minecraft/client/gui/FontRenderer;") + KString.EMPTY,
				null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
				isObfuscated ? "bop" : "net/minecraft/client/renderer/entity/RenderPlayer",
				isObfuscated ? "c" : "getFontRendererFromRenderManager",
				KString.EMPTY + (isObfuscated ? "()Lbbu;" : "()Lnet/minecraft/client/gui/FontRenderer;")
						+ KString.EMPTY);
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(
				Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superGetFontRendererFromRenderManager", KString.EMPTY
						+ (isObfuscated ? "()Lbbu;" : "()Lnet/minecraft/client/gui/FontRenderer;") + KString.EMPTY,
				null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL,
				isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RendererLivingEntity",
				isObfuscated ? "c" : "getFontRendererFromRenderManager",
				KString.EMPTY + (isObfuscated ? "()Lbbu;" : "()Lnet/minecraft/client/gui/FontRenderer;")
						+ KString.EMPTY);
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if (!hadLocalGetFontRendererFromRenderManager) {
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localGetFontRendererFromRenderManager",
					KString.EMPTY + (isObfuscated ? "()Lbbu;" : "()Lnet/minecraft/client/gui/FontRenderer;")
							+ KString.EMPTY,
					null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL,
					isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RendererLivingEntity",
					isObfuscated ? "c" : "getFontRendererFromRenderManager",
					KString.EMPTY + (isObfuscated ? "()Lbbu;" : "()Lnet/minecraft/client/gui/FontRenderer;")
							+ KString.EMPTY);
			mv.visitInsn(Opcodes.ARETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "a" : "getEntityTexture",
				KString.EMPTY + (isObfuscated ? "(Lblg;)Lbqx;"
						: "(Lnet/minecraft/client/entity/AbstractClientPlayer;)Lnet/minecraft/util/ResourceLocation;")
						+ KString.EMPTY,
				null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "getResourceLocationFromPlayer",
				"(Lapi/player/render/IRenderPlayerAPI;"
						+ (isObfuscated ? "Lblg;" : "Lnet/minecraft/client/entity/AbstractClientPlayer;") + ")"
						+ (isObfuscated ? "Lbqx;" : "Lnet/minecraft/util/ResourceLocation;") + KString.EMPTY);
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realGetResourceLocationFromPlayer",
				KString.EMPTY + (isObfuscated ? "(Lblg;)Lbqx;"
						: "(Lnet/minecraft/client/entity/AbstractClientPlayer;)Lnet/minecraft/util/ResourceLocation;")
						+ KString.EMPTY,
				null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
				isObfuscated ? "bop" : "net/minecraft/client/renderer/entity/RenderPlayer",
				isObfuscated ? "a" : "getEntityTexture",
				KString.EMPTY + (isObfuscated ? "(Lblg;)Lbqx;"
						: "(Lnet/minecraft/client/entity/AbstractClientPlayer;)Lnet/minecraft/util/ResourceLocation;")
						+ KString.EMPTY);
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superGetResourceLocationFromPlayer",
				KString.EMPTY + (isObfuscated ? "(Lblg;)Lbqx;"
						: "(Lnet/minecraft/client/entity/AbstractClientPlayer;)Lnet/minecraft/util/ResourceLocation;")
						+ KString.EMPTY,
				null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL,
				isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RendererLivingEntity",
				isObfuscated ? "a" : "getEntityTexture",
				KString.EMPTY + (isObfuscated ? "(Lblg;)Lbqx;"
						: "(Lnet/minecraft/client/entity/AbstractClientPlayer;)Lnet/minecraft/util/ResourceLocation;")
						+ KString.EMPTY);
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if (!hadLocalGetResourceLocationFromPlayer) {
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localGetResourceLocationFromPlayer",
					KString.EMPTY + (isObfuscated ? "(Lblg;)Lbqx;"
							: "(Lnet/minecraft/client/entity/AbstractClientPlayer;)Lnet/minecraft/util/ResourceLocation;")
							+ KString.EMPTY,
					null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL,
					isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RendererLivingEntity",
					isObfuscated ? "a" : "getEntityTexture",
					KString.EMPTY + (isObfuscated ? "(Lblg;)Lbqx;"
							: "(Lnet/minecraft/client/entity/AbstractClientPlayer;)Lnet/minecraft/util/ResourceLocation;")
							+ KString.EMPTY);
			mv.visitInsn(Opcodes.ARETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "b" : "handleRotationFloat", KString.EMPTY
				+ (isObfuscated ? "(Lsv;F)F" : "(Lnet/minecraft/entity/EntityLivingBase;F)F") + KString.EMPTY,
				null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "handleRotationFloat",
				"(Lapi/player/render/IRenderPlayerAPI;"
						+ (isObfuscated ? "Lsv;F" : "Lnet/minecraft/entity/EntityLivingBase;F") + ")F");
		mv.visitInsn(Opcodes.FRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realHandleRotationFloat", KString.EMPTY
				+ (isObfuscated ? "(Lsv;F)F" : "(Lnet/minecraft/entity/EntityLivingBase;F)F") + KString.EMPTY,
				null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
				isObfuscated ? "bop" : "net/minecraft/client/renderer/entity/RenderPlayer",
				isObfuscated ? "b" : "handleRotationFloat",
				KString.EMPTY + (isObfuscated ? "(Lsv;F)F" : "(Lnet/minecraft/entity/EntityLivingBase;F)F")
						+ KString.EMPTY);
		mv.visitInsn(Opcodes.FRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superHandleRotationFloat", KString.EMPTY
				+ (isObfuscated ? "(Lsv;F)F" : "(Lnet/minecraft/entity/EntityLivingBase;F)F") + KString.EMPTY,
				null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL,
				isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RendererLivingEntity",
				isObfuscated ? "b" : "handleRotationFloat",
				KString.EMPTY + (isObfuscated ? "(Lsv;F)F" : "(Lnet/minecraft/entity/EntityLivingBase;F)F")
						+ KString.EMPTY);
		mv.visitInsn(Opcodes.FRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if (!hadLocalHandleRotationFloat) {
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localHandleRotationFloat", KString.EMPTY
					+ (isObfuscated ? "(Lsv;F)F" : "(Lnet/minecraft/entity/EntityLivingBase;F)F") + KString.EMPTY,
					null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitVarInsn(Opcodes.FLOAD, 2);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL,
					isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RendererLivingEntity",
					isObfuscated ? "b" : "handleRotationFloat",
					KString.EMPTY + (isObfuscated ? "(Lsv;F)F" : "(Lnet/minecraft/entity/EntityLivingBase;F)F")
							+ KString.EMPTY);
			mv.visitInsn(Opcodes.FRETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "b" : "inheritRenderPass", KString.EMPTY
				+ (isObfuscated ? "(Lsv;IF)I" : "(Lnet/minecraft/entity/EntityLivingBase;IF)I") + KString.EMPTY,
				null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.ILOAD, 2);
		mv.visitVarInsn(Opcodes.FLOAD, 3);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "inheritRenderPass",
				"(Lapi/player/render/IRenderPlayerAPI;"
						+ (isObfuscated ? "Lsv;IF" : "Lnet/minecraft/entity/EntityLivingBase;IF") + ")I");
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realInheritRenderPass", KString.EMPTY
				+ (isObfuscated ? "(Lsv;IF)I" : "(Lnet/minecraft/entity/EntityLivingBase;IF)I") + KString.EMPTY,
				null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.ILOAD, 2);
		mv.visitVarInsn(Opcodes.FLOAD, 3);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
				isObfuscated ? "bop" : "net/minecraft/client/renderer/entity/RenderPlayer",
				isObfuscated ? "b" : "inheritRenderPass",
				KString.EMPTY + (isObfuscated ? "(Lsv;IF)I" : "(Lnet/minecraft/entity/EntityLivingBase;IF)I")
						+ KString.EMPTY);
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superInheritRenderPass", KString.EMPTY
				+ (isObfuscated ? "(Lsv;IF)I" : "(Lnet/minecraft/entity/EntityLivingBase;IF)I") + KString.EMPTY,
				null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.ILOAD, 2);
		mv.visitVarInsn(Opcodes.FLOAD, 3);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL,
				isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RendererLivingEntity",
				isObfuscated ? "b" : "inheritRenderPass",
				KString.EMPTY + (isObfuscated ? "(Lsv;IF)I" : "(Lnet/minecraft/entity/EntityLivingBase;IF)I")
						+ KString.EMPTY);
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if (!hadLocalInheritRenderPass) {
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localInheritRenderPass",
					KString.EMPTY + (isObfuscated ? "(Lsv;IF)I" : "(Lnet/minecraft/entity/EntityLivingBase;IF)I")
							+ KString.EMPTY,
					null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitVarInsn(Opcodes.ILOAD, 2);
			mv.visitVarInsn(Opcodes.FLOAD, 3);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL,
					isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RendererLivingEntity",
					isObfuscated ? "b" : "inheritRenderPass",
					KString.EMPTY + (isObfuscated ? "(Lsv;IF)I" : "(Lnet/minecraft/entity/EntityLivingBase;IF)I")
							+ KString.EMPTY);
			mv.visitInsn(Opcodes.IRETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(
				Opcodes.ACC_PUBLIC, isObfuscated ? "a" : "bindTexture", KString.EMPTY
						+ (isObfuscated ? "(Lbqx;)V" : "(Lnet/minecraft/util/ResourceLocation;)V") + KString.EMPTY,
				null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "loadTexture",
				"(Lapi/player/render/IRenderPlayerAPI;"
						+ (isObfuscated ? "Lbqx;" : "Lnet/minecraft/util/ResourceLocation;") + ")V");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(
				Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realLoadTexture", KString.EMPTY
						+ (isObfuscated ? "(Lbqx;)V" : "(Lnet/minecraft/util/ResourceLocation;)V") + KString.EMPTY,
				null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
				isObfuscated ? "bop" : "net/minecraft/client/renderer/entity/RenderPlayer",
				isObfuscated ? "a" : "bindTexture",
				KString.EMPTY + (isObfuscated ? "(Lbqx;)V" : "(Lnet/minecraft/util/ResourceLocation;)V")
						+ KString.EMPTY);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(
				Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superLoadTexture", KString.EMPTY
						+ (isObfuscated ? "(Lbqx;)V" : "(Lnet/minecraft/util/ResourceLocation;)V") + KString.EMPTY,
				null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL,
				isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RendererLivingEntity",
				isObfuscated ? "a" : "bindTexture",
				KString.EMPTY + (isObfuscated ? "(Lbqx;)V" : "(Lnet/minecraft/util/ResourceLocation;)V")
						+ KString.EMPTY);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if (!hadLocalLoadTexture) {
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localLoadTexture", KString.EMPTY
					+ (isObfuscated ? "(Lbqx;)V" : "(Lnet/minecraft/util/ResourceLocation;)V") + KString.EMPTY,
					null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL,
					isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RendererLivingEntity",
					isObfuscated ? "a" : "bindTexture",
					KString.EMPTY + (isObfuscated ? "(Lbqx;)V" : "(Lnet/minecraft/util/ResourceLocation;)V")
							+ KString.EMPTY);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(
				Opcodes.ACC_PUBLIC, isObfuscated ? "b" : "bindEntityTexture", KString.EMPTY
						+ (isObfuscated ? "(Lsa;)V" : "(Lnet/minecraft/entity/Entity;)V") + KString.EMPTY,
				null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "loadTextureOfEntity",
				"(Lapi/player/render/IRenderPlayerAPI;" + (isObfuscated ? "Lsa;" : "Lnet/minecraft/entity/Entity;")
						+ ")V");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(
				Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realLoadTextureOfEntity", KString.EMPTY
						+ (isObfuscated ? "(Lsa;)V" : "(Lnet/minecraft/entity/Entity;)V") + KString.EMPTY,
				null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
				isObfuscated ? "bop" : "net/minecraft/client/renderer/entity/RenderPlayer",
				isObfuscated ? "b" : "bindEntityTexture", KString.EMPTY
						+ (isObfuscated ? "(Lsa;)V" : "(Lnet/minecraft/entity/Entity;)V") + KString.EMPTY);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(
				Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superLoadTextureOfEntity", KString.EMPTY
						+ (isObfuscated ? "(Lsa;)V" : "(Lnet/minecraft/entity/Entity;)V") + KString.EMPTY,
				null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL,
				isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RendererLivingEntity",
				isObfuscated ? "b" : "bindEntityTexture", KString.EMPTY
						+ (isObfuscated ? "(Lsa;)V" : "(Lnet/minecraft/entity/Entity;)V") + KString.EMPTY);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if (!hadLocalLoadTextureOfEntity) {
			mv = cv.visitMethod(
					Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localLoadTextureOfEntity", KString.EMPTY
							+ (isObfuscated ? "(Lsa;)V" : "(Lnet/minecraft/entity/Entity;)V") + KString.EMPTY,
					null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL,
					isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RendererLivingEntity",
					isObfuscated ? "b" : "bindEntityTexture", KString.EMPTY
							+ (isObfuscated ? "(Lsa;)V" : "(Lnet/minecraft/entity/Entity;)V") + KString.EMPTY);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "b" : "passSpecialRender", KString.EMPTY
				+ (isObfuscated ? "(Lsv;DDD)V" : "(Lnet/minecraft/entity/EntityLivingBase;DDD)V") + KString.EMPTY,
				null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.DLOAD, 2);
		mv.visitVarInsn(Opcodes.DLOAD, 4);
		mv.visitVarInsn(Opcodes.DLOAD, 6);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "passSpecialRender",
				"(Lapi/player/render/IRenderPlayerAPI;"
						+ (isObfuscated ? "Lsv;DDD" : "Lnet/minecraft/entity/EntityLivingBase;DDD") + ")V");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realPassSpecialRender", KString.EMPTY
				+ (isObfuscated ? "(Lsv;DDD)V" : "(Lnet/minecraft/entity/EntityLivingBase;DDD)V") + KString.EMPTY,
				null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.DLOAD, 2);
		mv.visitVarInsn(Opcodes.DLOAD, 4);
		mv.visitVarInsn(Opcodes.DLOAD, 6);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
				isObfuscated ? "bop" : "net/minecraft/client/renderer/entity/RenderPlayer",
				isObfuscated ? "b" : "passSpecialRender",
				KString.EMPTY + (isObfuscated ? "(Lsv;DDD)V" : "(Lnet/minecraft/entity/EntityLivingBase;DDD)V")
						+ KString.EMPTY);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superPassSpecialRender", KString.EMPTY
				+ (isObfuscated ? "(Lsv;DDD)V" : "(Lnet/minecraft/entity/EntityLivingBase;DDD)V") + KString.EMPTY,
				null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.DLOAD, 2);
		mv.visitVarInsn(Opcodes.DLOAD, 4);
		mv.visitVarInsn(Opcodes.DLOAD, 6);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL,
				isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RendererLivingEntity",
				isObfuscated ? "b" : "passSpecialRender",
				KString.EMPTY + (isObfuscated ? "(Lsv;DDD)V" : "(Lnet/minecraft/entity/EntityLivingBase;DDD)V")
						+ KString.EMPTY);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if (!hadLocalPassSpecialRender) {
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localPassSpecialRender",
					KString.EMPTY + (isObfuscated ? "(Lsv;DDD)V" : "(Lnet/minecraft/entity/EntityLivingBase;DDD)V")
							+ KString.EMPTY,
					null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitVarInsn(Opcodes.DLOAD, 2);
			mv.visitVarInsn(Opcodes.DLOAD, 4);
			mv.visitVarInsn(Opcodes.DLOAD, 6);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL,
					isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RendererLivingEntity",
					isObfuscated ? "b" : "passSpecialRender",
					KString.EMPTY + (isObfuscated ? "(Lsv;DDD)V" : "(Lnet/minecraft/entity/EntityLivingBase;DDD)V")
							+ KString.EMPTY);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "a" : "isStaticEntity", "()Z", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "performStaticEntityRebuild",
				"(Lapi/player/render/IRenderPlayerAPI;)Z");
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realPerformStaticEntityRebuild", "()Z", null,
				null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
				isObfuscated ? "bop" : "net/minecraft/client/renderer/entity/RenderPlayer",
				isObfuscated ? "a" : "isStaticEntity", "()Z");
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superPerformStaticEntityRebuild", "()Z", null,
				null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL,
				isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RendererLivingEntity",
				isObfuscated ? "a" : "isStaticEntity", "()Z");
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if (!hadLocalPerformStaticEntityRebuild) {
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localPerformStaticEntityRebuild", "()Z", null,
					null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL,
					isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RendererLivingEntity",
					isObfuscated ? "a" : "isStaticEntity", "()Z");
			mv.visitInsn(Opcodes.IRETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "e" : "renderArrowsStuckInEntity", KString.EMPTY
				+ (isObfuscated ? "(Lsv;F)V" : "(Lnet/minecraft/entity/EntityLivingBase;F)V") + KString.EMPTY,
				null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "renderArrowsStuckInEntity",
				"(Lapi/player/render/IRenderPlayerAPI;"
						+ (isObfuscated ? "Lsv;F" : "Lnet/minecraft/entity/EntityLivingBase;F") + ")V");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realRenderArrowsStuckInEntity", KString.EMPTY
				+ (isObfuscated ? "(Lsv;F)V" : "(Lnet/minecraft/entity/EntityLivingBase;F)V") + KString.EMPTY,
				null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
				isObfuscated ? "bop" : "net/minecraft/client/renderer/entity/RenderPlayer",
				isObfuscated ? "e" : "renderArrowsStuckInEntity",
				KString.EMPTY + (isObfuscated ? "(Lsv;F)V" : "(Lnet/minecraft/entity/EntityLivingBase;F)V")
						+ KString.EMPTY);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superRenderArrowsStuckInEntity", KString.EMPTY
				+ (isObfuscated ? "(Lsv;F)V" : "(Lnet/minecraft/entity/EntityLivingBase;F)V") + KString.EMPTY,
				null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL,
				isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RendererLivingEntity",
				isObfuscated ? "e" : "renderArrowsStuckInEntity",
				KString.EMPTY + (isObfuscated ? "(Lsv;F)V" : "(Lnet/minecraft/entity/EntityLivingBase;F)V")
						+ KString.EMPTY);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if (!hadLocalRenderArrowsStuckInEntity) {
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRenderArrowsStuckInEntity",
					KString.EMPTY + (isObfuscated ? "(Lsv;F)V" : "(Lnet/minecraft/entity/EntityLivingBase;F)V")
							+ KString.EMPTY,
					null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitVarInsn(Opcodes.FLOAD, 2);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL,
					isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RendererLivingEntity",
					isObfuscated ? "e" : "renderArrowsStuckInEntity",
					KString.EMPTY + (isObfuscated ? "(Lsv;F)V" : "(Lnet/minecraft/entity/EntityLivingBase;F)V")
							+ KString.EMPTY);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "a" : "renderFirstPersonArm", KString.EMPTY
				+ (isObfuscated ? "(Lyz;)V" : "(Lnet/minecraft/entity/player/EntityPlayer;)V") + KString.EMPTY,
				null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "renderFirstPersonArm",
				"(Lapi/player/render/IRenderPlayerAPI;"
						+ (isObfuscated ? "Lyz;" : "Lnet/minecraft/entity/player/EntityPlayer;") + ")V");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realRenderFirstPersonArm", KString.EMPTY
				+ (isObfuscated ? "(Lyz;)V" : "(Lnet/minecraft/entity/player/EntityPlayer;)V") + KString.EMPTY,
				null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
				isObfuscated ? "bop" : "net/minecraft/client/renderer/entity/RenderPlayer",
				isObfuscated ? "a" : "renderFirstPersonArm",
				KString.EMPTY + (isObfuscated ? "(Lyz;)V" : "(Lnet/minecraft/entity/player/EntityPlayer;)V")
						+ KString.EMPTY);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superRenderFirstPersonArm", KString.EMPTY
				+ (isObfuscated ? "(Lyz;)V" : "(Lnet/minecraft/entity/player/EntityPlayer;)V") + KString.EMPTY,
				null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL,
				isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RendererLivingEntity",
				isObfuscated ? "a" : "renderFirstPersonArm",
				KString.EMPTY + (isObfuscated ? "(Lyz;)V" : "(Lnet/minecraft/entity/player/EntityPlayer;)V")
						+ KString.EMPTY);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if (!hadLocalRenderFirstPersonArm) {
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRenderFirstPersonArm", KString.EMPTY
					+ (isObfuscated ? "(Lyz;)V" : "(Lnet/minecraft/entity/player/EntityPlayer;)V") + KString.EMPTY,
					null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL,
					isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RendererLivingEntity",
					isObfuscated ? "a" : "renderFirstPersonArm",
					KString.EMPTY + (isObfuscated ? "(Lyz;)V" : "(Lnet/minecraft/entity/player/EntityPlayer;)V")
							+ KString.EMPTY);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "a" : "func_147906_a",
				KString.EMPTY + (isObfuscated ? "(Lsa;Ljava/lang/String;DDDI)V"
						: "(Lnet/minecraft/entity/Entity;Ljava/lang/String;DDDI)V") + KString.EMPTY,
				null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.ALOAD, 2);
		mv.visitVarInsn(Opcodes.DLOAD, 3);
		mv.visitVarInsn(Opcodes.DLOAD, 5);
		mv.visitVarInsn(Opcodes.DLOAD, 7);
		mv.visitVarInsn(Opcodes.ILOAD, 9);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "renderLivingLabel",
				"(Lapi/player/render/IRenderPlayerAPI;" + (isObfuscated ? "Lsa;Ljava/lang/String;DDDI"
						: "Lnet/minecraft/entity/Entity;Ljava/lang/String;DDDI") + ")V");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realRenderLivingLabel",
				KString.EMPTY + (isObfuscated ? "(Lsa;Ljava/lang/String;DDDI)V"
						: "(Lnet/minecraft/entity/Entity;Ljava/lang/String;DDDI)V") + KString.EMPTY,
				null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.ALOAD, 2);
		mv.visitVarInsn(Opcodes.DLOAD, 3);
		mv.visitVarInsn(Opcodes.DLOAD, 5);
		mv.visitVarInsn(Opcodes.DLOAD, 7);
		mv.visitVarInsn(Opcodes.ILOAD, 9);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
				isObfuscated ? "bop" : "net/minecraft/client/renderer/entity/RenderPlayer",
				isObfuscated ? "a" : "func_147906_a",
				KString.EMPTY + (isObfuscated ? "(Lsa;Ljava/lang/String;DDDI)V"
						: "(Lnet/minecraft/entity/Entity;Ljava/lang/String;DDDI)V") + KString.EMPTY);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superRenderLivingLabel",
				KString.EMPTY + (isObfuscated ? "(Lsa;Ljava/lang/String;DDDI)V"
						: "(Lnet/minecraft/entity/Entity;Ljava/lang/String;DDDI)V") + KString.EMPTY,
				null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.ALOAD, 2);
		mv.visitVarInsn(Opcodes.DLOAD, 3);
		mv.visitVarInsn(Opcodes.DLOAD, 5);
		mv.visitVarInsn(Opcodes.DLOAD, 7);
		mv.visitVarInsn(Opcodes.ILOAD, 9);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL,
				isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RendererLivingEntity",
				isObfuscated ? "a" : "func_147906_a",
				KString.EMPTY + (isObfuscated ? "(Lsa;Ljava/lang/String;DDDI)V"
						: "(Lnet/minecraft/entity/Entity;Ljava/lang/String;DDDI)V") + KString.EMPTY);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if (!hadLocalRenderLivingLabel) {
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRenderLivingLabel",
					KString.EMPTY + (isObfuscated ? "(Lsa;Ljava/lang/String;DDDI)V"
							: "(Lnet/minecraft/entity/Entity;Ljava/lang/String;DDDI)V") + KString.EMPTY,
					null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitVarInsn(Opcodes.ALOAD, 2);
			mv.visitVarInsn(Opcodes.DLOAD, 3);
			mv.visitVarInsn(Opcodes.DLOAD, 5);
			mv.visitVarInsn(Opcodes.DLOAD, 7);
			mv.visitVarInsn(Opcodes.ILOAD, 9);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL,
					isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RendererLivingEntity",
					isObfuscated ? "a" : "func_147906_a",
					KString.EMPTY + (isObfuscated ? "(Lsa;Ljava/lang/String;DDDI)V"
							: "(Lnet/minecraft/entity/Entity;Ljava/lang/String;DDDI)V") + KString.EMPTY);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "a" : "renderModel",
				KString.EMPTY
						+ (isObfuscated ? "(Lsv;FFFFFF)V" : "(Lnet/minecraft/entity/EntityLivingBase;FFFFFF)V")
						+ KString.EMPTY,
				null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitVarInsn(Opcodes.FLOAD, 3);
		mv.visitVarInsn(Opcodes.FLOAD, 4);
		mv.visitVarInsn(Opcodes.FLOAD, 5);
		mv.visitVarInsn(Opcodes.FLOAD, 6);
		mv.visitVarInsn(Opcodes.FLOAD, 7);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "renderModel",
				"(Lapi/player/render/IRenderPlayerAPI;"
						+ (isObfuscated ? "Lsv;FFFFFF" : "Lnet/minecraft/entity/EntityLivingBase;FFFFFF") + ")V");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realRenderModel",
				KString.EMPTY
						+ (isObfuscated ? "(Lsv;FFFFFF)V" : "(Lnet/minecraft/entity/EntityLivingBase;FFFFFF)V")
						+ KString.EMPTY,
				null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitVarInsn(Opcodes.FLOAD, 3);
		mv.visitVarInsn(Opcodes.FLOAD, 4);
		mv.visitVarInsn(Opcodes.FLOAD, 5);
		mv.visitVarInsn(Opcodes.FLOAD, 6);
		mv.visitVarInsn(Opcodes.FLOAD, 7);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
				isObfuscated ? "bop" : "net/minecraft/client/renderer/entity/RenderPlayer",
				isObfuscated ? "a" : "renderModel",
				KString.EMPTY
						+ (isObfuscated ? "(Lsv;FFFFFF)V" : "(Lnet/minecraft/entity/EntityLivingBase;FFFFFF)V")
						+ KString.EMPTY);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superRenderModel",
				KString.EMPTY
						+ (isObfuscated ? "(Lsv;FFFFFF)V" : "(Lnet/minecraft/entity/EntityLivingBase;FFFFFF)V")
						+ KString.EMPTY,
				null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitVarInsn(Opcodes.FLOAD, 3);
		mv.visitVarInsn(Opcodes.FLOAD, 4);
		mv.visitVarInsn(Opcodes.FLOAD, 5);
		mv.visitVarInsn(Opcodes.FLOAD, 6);
		mv.visitVarInsn(Opcodes.FLOAD, 7);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL,
				isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RendererLivingEntity",
				isObfuscated ? "a" : "renderModel",
				KString.EMPTY
						+ (isObfuscated ? "(Lsv;FFFFFF)V" : "(Lnet/minecraft/entity/EntityLivingBase;FFFFFF)V")
						+ KString.EMPTY);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if (!hadLocalRenderModel) {
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRenderModel",
					KString.EMPTY
							+ (isObfuscated ? "(Lsv;FFFFFF)V" : "(Lnet/minecraft/entity/EntityLivingBase;FFFFFF)V")
							+ KString.EMPTY,
					null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitVarInsn(Opcodes.FLOAD, 2);
			mv.visitVarInsn(Opcodes.FLOAD, 3);
			mv.visitVarInsn(Opcodes.FLOAD, 4);
			mv.visitVarInsn(Opcodes.FLOAD, 5);
			mv.visitVarInsn(Opcodes.FLOAD, 6);
			mv.visitVarInsn(Opcodes.FLOAD, 7);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL,
					isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RendererLivingEntity",
					isObfuscated ? "a" : "renderModel",
					KString.EMPTY
							+ (isObfuscated ? "(Lsv;FFFFFF)V" : "(Lnet/minecraft/entity/EntityLivingBase;FFFFFF)V")
							+ KString.EMPTY);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "a" : "doRender",
				KString.EMPTY + (isObfuscated ? "(Lblg;DDDFF)V"
						: "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDFF)V") + KString.EMPTY,
				null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.DLOAD, 2);
		mv.visitVarInsn(Opcodes.DLOAD, 4);
		mv.visitVarInsn(Opcodes.DLOAD, 6);
		mv.visitVarInsn(Opcodes.FLOAD, 8);
		mv.visitVarInsn(Opcodes.FLOAD, 9);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "renderPlayer",
				"(Lapi/player/render/IRenderPlayerAPI;"
						+ (isObfuscated ? "Lblg;DDDFF" : "Lnet/minecraft/client/entity/AbstractClientPlayer;DDDFF")
						+ ")V");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realRenderPlayer",
				KString.EMPTY + (isObfuscated ? "(Lblg;DDDFF)V"
						: "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDFF)V") + KString.EMPTY,
				null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.DLOAD, 2);
		mv.visitVarInsn(Opcodes.DLOAD, 4);
		mv.visitVarInsn(Opcodes.DLOAD, 6);
		mv.visitVarInsn(Opcodes.FLOAD, 8);
		mv.visitVarInsn(Opcodes.FLOAD, 9);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
				isObfuscated ? "bop" : "net/minecraft/client/renderer/entity/RenderPlayer",
				isObfuscated ? "a" : "doRender", KString.EMPTY + (isObfuscated ? "(Lblg;DDDFF)V"
						: "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDFF)V") + KString.EMPTY);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superRenderPlayer",
				KString.EMPTY + (isObfuscated ? "(Lblg;DDDFF)V"
						: "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDFF)V") + KString.EMPTY,
				null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.DLOAD, 2);
		mv.visitVarInsn(Opcodes.DLOAD, 4);
		mv.visitVarInsn(Opcodes.DLOAD, 6);
		mv.visitVarInsn(Opcodes.FLOAD, 8);
		mv.visitVarInsn(Opcodes.FLOAD, 9);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL,
				isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RendererLivingEntity",
				isObfuscated ? "a" : "doRender", KString.EMPTY + (isObfuscated ? "(Lblg;DDDFF)V"
						: "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDFF)V") + KString.EMPTY);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if (!hadLocalRenderPlayer) {
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRenderPlayer", KString.EMPTY
					+ (isObfuscated ? "(Lblg;DDDFF)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDFF)V")
					+ KString.EMPTY, null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitVarInsn(Opcodes.DLOAD, 2);
			mv.visitVarInsn(Opcodes.DLOAD, 4);
			mv.visitVarInsn(Opcodes.DLOAD, 6);
			mv.visitVarInsn(Opcodes.FLOAD, 8);
			mv.visitVarInsn(Opcodes.FLOAD, 9);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL,
					isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RendererLivingEntity",
					isObfuscated ? "a" : "doRender",
					KString.EMPTY
							+ (isObfuscated ? "(Lblg;DDDFF)V"
									: "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDFF)V")
							+ KString.EMPTY);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "a" : "func_96449_a",
				KString.EMPTY
						+ (isObfuscated ? "(Lblg;DDDLjava/lang/String;FD)V"
								: "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDLjava/lang/String;FD)V")
						+ KString.EMPTY,
				null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.DLOAD, 2);
		mv.visitVarInsn(Opcodes.DLOAD, 4);
		mv.visitVarInsn(Opcodes.DLOAD, 6);
		mv.visitVarInsn(Opcodes.ALOAD, 8);
		mv.visitVarInsn(Opcodes.FLOAD, 9);
		mv.visitVarInsn(Opcodes.DLOAD, 10);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "renderPlayerNameAndScoreLabel",
				"(Lapi/player/render/IRenderPlayerAPI;" + (isObfuscated ? "Lblg;DDDLjava/lang/String;FD"
						: "Lnet/minecraft/client/entity/AbstractClientPlayer;DDDLjava/lang/String;FD") + ")V");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realRenderPlayerNameAndScoreLabel",
				KString.EMPTY
						+ (isObfuscated ? "(Lblg;DDDLjava/lang/String;FD)V"
								: "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDLjava/lang/String;FD)V")
						+ KString.EMPTY,
				null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.DLOAD, 2);
		mv.visitVarInsn(Opcodes.DLOAD, 4);
		mv.visitVarInsn(Opcodes.DLOAD, 6);
		mv.visitVarInsn(Opcodes.ALOAD, 8);
		mv.visitVarInsn(Opcodes.FLOAD, 9);
		mv.visitVarInsn(Opcodes.DLOAD, 10);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
				isObfuscated ? "bop" : "net/minecraft/client/renderer/entity/RenderPlayer",
				isObfuscated ? "a" : "func_96449_a",
				KString.EMPTY
						+ (isObfuscated ? "(Lblg;DDDLjava/lang/String;FD)V"
								: "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDLjava/lang/String;FD)V")
						+ KString.EMPTY);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superRenderPlayerNameAndScoreLabel",
				KString.EMPTY
						+ (isObfuscated ? "(Lblg;DDDLjava/lang/String;FD)V"
								: "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDLjava/lang/String;FD)V")
						+ KString.EMPTY,
				null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.DLOAD, 2);
		mv.visitVarInsn(Opcodes.DLOAD, 4);
		mv.visitVarInsn(Opcodes.DLOAD, 6);
		mv.visitVarInsn(Opcodes.ALOAD, 8);
		mv.visitVarInsn(Opcodes.FLOAD, 9);
		mv.visitVarInsn(Opcodes.DLOAD, 10);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL,
				isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RendererLivingEntity",
				isObfuscated ? "a" : "func_96449_a",
				KString.EMPTY
						+ (isObfuscated ? "(Lblg;DDDLjava/lang/String;FD)V"
								: "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDLjava/lang/String;FD)V")
						+ KString.EMPTY);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if (!hadLocalRenderPlayerNameAndScoreLabel) {
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRenderPlayerNameAndScoreLabel",
					KString.EMPTY
							+ (isObfuscated ? "(Lblg;DDDLjava/lang/String;FD)V"
									: "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDLjava/lang/String;FD)V")
							+ KString.EMPTY,
					null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitVarInsn(Opcodes.DLOAD, 2);
			mv.visitVarInsn(Opcodes.DLOAD, 4);
			mv.visitVarInsn(Opcodes.DLOAD, 6);
			mv.visitVarInsn(Opcodes.ALOAD, 8);
			mv.visitVarInsn(Opcodes.FLOAD, 9);
			mv.visitVarInsn(Opcodes.DLOAD, 10);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL,
					isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RendererLivingEntity",
					isObfuscated ? "a" : "func_96449_a",
					KString.EMPTY
							+ (isObfuscated ? "(Lblg;DDDLjava/lang/String;FD)V"
									: "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDLjava/lang/String;FD)V")
							+ KString.EMPTY);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "b" : "preRenderCallback",
				KString.EMPTY
						+ (isObfuscated ? "(Lblg;F)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;F)V")
						+ KString.EMPTY,
				null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "renderPlayerScale",
				"(Lapi/player/render/IRenderPlayerAPI;"
						+ (isObfuscated ? "Lblg;F" : "Lnet/minecraft/client/entity/AbstractClientPlayer;F") + ")V");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realRenderPlayerScale",
				KString.EMPTY
						+ (isObfuscated ? "(Lblg;F)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;F)V")
						+ KString.EMPTY,
				null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
				isObfuscated ? "bop" : "net/minecraft/client/renderer/entity/RenderPlayer",
				isObfuscated ? "b" : "preRenderCallback",
				KString.EMPTY
						+ (isObfuscated ? "(Lblg;F)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;F)V")
						+ KString.EMPTY);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superRenderPlayerScale",
				KString.EMPTY
						+ (isObfuscated ? "(Lblg;F)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;F)V")
						+ KString.EMPTY,
				null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL,
				isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RendererLivingEntity",
				isObfuscated ? "b" : "preRenderCallback",
				KString.EMPTY
						+ (isObfuscated ? "(Lblg;F)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;F)V")
						+ KString.EMPTY);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if (!hadLocalRenderPlayerScale) {
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRenderPlayerScale",
					KString.EMPTY
							+ (isObfuscated ? "(Lblg;F)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;F)V")
							+ KString.EMPTY,
					null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitVarInsn(Opcodes.FLOAD, 2);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL,
					isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RendererLivingEntity",
					isObfuscated ? "b" : "preRenderCallback",
					KString.EMPTY
							+ (isObfuscated ? "(Lblg;F)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;F)V")
							+ KString.EMPTY);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "a" : "renderLivingAt",
				KString.EMPTY
						+ (isObfuscated ? "(Lblg;DDD)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDD)V")
						+ KString.EMPTY,
				null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.DLOAD, 2);
		mv.visitVarInsn(Opcodes.DLOAD, 4);
		mv.visitVarInsn(Opcodes.DLOAD, 6);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "renderPlayerSleep",
				"(Lapi/player/render/IRenderPlayerAPI;"
						+ (isObfuscated ? "Lblg;DDD" : "Lnet/minecraft/client/entity/AbstractClientPlayer;DDD") + ")V");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realRenderPlayerSleep",
				KString.EMPTY
						+ (isObfuscated ? "(Lblg;DDD)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDD)V")
						+ KString.EMPTY,
				null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.DLOAD, 2);
		mv.visitVarInsn(Opcodes.DLOAD, 4);
		mv.visitVarInsn(Opcodes.DLOAD, 6);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
				isObfuscated ? "bop" : "net/minecraft/client/renderer/entity/RenderPlayer",
				isObfuscated ? "a" : "renderLivingAt",
				KString.EMPTY
						+ (isObfuscated ? "(Lblg;DDD)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDD)V")
						+ KString.EMPTY);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superRenderPlayerSleep",
				KString.EMPTY
						+ (isObfuscated ? "(Lblg;DDD)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDD)V")
						+ KString.EMPTY,
				null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.DLOAD, 2);
		mv.visitVarInsn(Opcodes.DLOAD, 4);
		mv.visitVarInsn(Opcodes.DLOAD, 6);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL,
				isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RendererLivingEntity",
				isObfuscated ? "a" : "renderLivingAt",
				KString.EMPTY
						+ (isObfuscated ? "(Lblg;DDD)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDD)V")
						+ KString.EMPTY);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if (!hadLocalRenderPlayerSleep) {
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRenderPlayerSleep",
					KString.EMPTY + (isObfuscated ? "(Lblg;DDD)V"
							: "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDD)V") + KString.EMPTY,
					null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitVarInsn(Opcodes.DLOAD, 2);
			mv.visitVarInsn(Opcodes.DLOAD, 4);
			mv.visitVarInsn(Opcodes.DLOAD, 6);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL,
					isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RendererLivingEntity",
					isObfuscated ? "a" : "renderLivingAt", KString.EMPTY + (isObfuscated ? "(Lblg;DDD)V"
							: "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDD)V") + KString.EMPTY);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "a" : "renderEquippedItems",
				KString.EMPTY
						+ (isObfuscated ? "(Lblg;F)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;F)V")
						+ KString.EMPTY,
				null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "renderSpecials",
				"(Lapi/player/render/IRenderPlayerAPI;"
						+ (isObfuscated ? "Lblg;F" : "Lnet/minecraft/client/entity/AbstractClientPlayer;F") + ")V");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realRenderSpecials",
				KString.EMPTY
						+ (isObfuscated ? "(Lblg;F)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;F)V")
						+ KString.EMPTY,
				null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
				isObfuscated ? "bop" : "net/minecraft/client/renderer/entity/RenderPlayer",
				isObfuscated ? "a" : "renderEquippedItems",
				KString.EMPTY
						+ (isObfuscated ? "(Lblg;F)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;F)V")
						+ KString.EMPTY);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superRenderSpecials",
				KString.EMPTY
						+ (isObfuscated ? "(Lblg;F)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;F)V")
						+ KString.EMPTY,
				null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL,
				isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RendererLivingEntity",
				isObfuscated ? "a" : "renderEquippedItems",
				KString.EMPTY
						+ (isObfuscated ? "(Lblg;F)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;F)V")
						+ KString.EMPTY);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if (!hadLocalRenderSpecials) {
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRenderSpecials",
					KString.EMPTY
							+ (isObfuscated ? "(Lblg;F)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;F)V")
							+ KString.EMPTY,
					null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitVarInsn(Opcodes.FLOAD, 2);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL,
					isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RendererLivingEntity",
					isObfuscated ? "a" : "renderEquippedItems",
					KString.EMPTY
							+ (isObfuscated ? "(Lblg;F)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;F)V")
							+ KString.EMPTY);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "d" : "renderSwingProgress", KString.EMPTY
				+ (isObfuscated ? "(Lsv;F)F" : "(Lnet/minecraft/entity/EntityLivingBase;F)F") + KString.EMPTY,
				null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "renderSwingProgress",
				"(Lapi/player/render/IRenderPlayerAPI;"
						+ (isObfuscated ? "Lsv;F" : "Lnet/minecraft/entity/EntityLivingBase;F") + ")F");
		mv.visitInsn(Opcodes.FRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realRenderSwingProgress", KString.EMPTY
				+ (isObfuscated ? "(Lsv;F)F" : "(Lnet/minecraft/entity/EntityLivingBase;F)F") + KString.EMPTY,
				null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
				isObfuscated ? "bop" : "net/minecraft/client/renderer/entity/RenderPlayer",
				isObfuscated ? "d" : "renderSwingProgress",
				KString.EMPTY + (isObfuscated ? "(Lsv;F)F" : "(Lnet/minecraft/entity/EntityLivingBase;F)F")
						+ KString.EMPTY);
		mv.visitInsn(Opcodes.FRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superRenderSwingProgress", KString.EMPTY
				+ (isObfuscated ? "(Lsv;F)F" : "(Lnet/minecraft/entity/EntityLivingBase;F)F") + KString.EMPTY,
				null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL,
				isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RendererLivingEntity",
				isObfuscated ? "d" : "renderSwingProgress",
				KString.EMPTY + (isObfuscated ? "(Lsv;F)F" : "(Lnet/minecraft/entity/EntityLivingBase;F)F")
						+ KString.EMPTY);
		mv.visitInsn(Opcodes.FRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if (!hadLocalRenderSwingProgress) {
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRenderSwingProgress", KString.EMPTY
					+ (isObfuscated ? "(Lsv;F)F" : "(Lnet/minecraft/entity/EntityLivingBase;F)F") + KString.EMPTY,
					null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitVarInsn(Opcodes.FLOAD, 2);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL,
					isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RendererLivingEntity",
					isObfuscated ? "d" : "renderSwingProgress",
					KString.EMPTY + (isObfuscated ? "(Lsv;F)F" : "(Lnet/minecraft/entity/EntityLivingBase;F)F")
							+ KString.EMPTY);
			mv.visitInsn(Opcodes.FRETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "a" : "rotateCorpse",
				KString.EMPTY
						+ (isObfuscated ? "(Lblg;FFF)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;FFF)V")
						+ KString.EMPTY,
				null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitVarInsn(Opcodes.FLOAD, 3);
		mv.visitVarInsn(Opcodes.FLOAD, 4);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "rotatePlayer",
				"(Lapi/player/render/IRenderPlayerAPI;"
						+ (isObfuscated ? "Lblg;FFF" : "Lnet/minecraft/client/entity/AbstractClientPlayer;FFF") + ")V");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realRotatePlayer",
				KString.EMPTY
						+ (isObfuscated ? "(Lblg;FFF)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;FFF)V")
						+ KString.EMPTY,
				null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitVarInsn(Opcodes.FLOAD, 3);
		mv.visitVarInsn(Opcodes.FLOAD, 4);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
				isObfuscated ? "bop" : "net/minecraft/client/renderer/entity/RenderPlayer",
				isObfuscated ? "a" : "rotateCorpse",
				KString.EMPTY
						+ (isObfuscated ? "(Lblg;FFF)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;FFF)V")
						+ KString.EMPTY);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superRotatePlayer",
				KString.EMPTY
						+ (isObfuscated ? "(Lblg;FFF)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;FFF)V")
						+ KString.EMPTY,
				null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitVarInsn(Opcodes.FLOAD, 3);
		mv.visitVarInsn(Opcodes.FLOAD, 4);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL,
				isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RendererLivingEntity",
				isObfuscated ? "a" : "rotateCorpse",
				KString.EMPTY
						+ (isObfuscated ? "(Lblg;FFF)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;FFF)V")
						+ KString.EMPTY);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if (!hadLocalRotatePlayer) {
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRotatePlayer",
					KString.EMPTY + (isObfuscated ? "(Lblg;FFF)V"
							: "(Lnet/minecraft/client/entity/AbstractClientPlayer;FFF)V") + KString.EMPTY,
					null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitVarInsn(Opcodes.FLOAD, 2);
			mv.visitVarInsn(Opcodes.FLOAD, 3);
			mv.visitVarInsn(Opcodes.FLOAD, 4);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL,
					isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RendererLivingEntity",
					isObfuscated ? "a" : "rotateCorpse", KString.EMPTY + (isObfuscated ? "(Lblg;FFF)V"
							: "(Lnet/minecraft/client/entity/AbstractClientPlayer;FFF)V") + KString.EMPTY);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "a" : "shouldRenderPass",
				KString.EMPTY
						+ (isObfuscated ? "(Lblg;IF)I" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;IF)I")
						+ KString.EMPTY,
				null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.ILOAD, 2);
		mv.visitVarInsn(Opcodes.FLOAD, 3);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "setArmorModel",
				"(Lapi/player/render/IRenderPlayerAPI;"
						+ (isObfuscated ? "Lblg;IF" : "Lnet/minecraft/client/entity/AbstractClientPlayer;IF") + ")I");
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realSetArmorModel",
				KString.EMPTY
						+ (isObfuscated ? "(Lblg;IF)I" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;IF)I")
						+ KString.EMPTY,
				null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.ILOAD, 2);
		mv.visitVarInsn(Opcodes.FLOAD, 3);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
				isObfuscated ? "bop" : "net/minecraft/client/renderer/entity/RenderPlayer",
				isObfuscated ? "a" : "shouldRenderPass",
				KString.EMPTY
						+ (isObfuscated ? "(Lblg;IF)I" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;IF)I")
						+ KString.EMPTY);
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superSetArmorModel",
				KString.EMPTY
						+ (isObfuscated ? "(Lblg;IF)I" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;IF)I")
						+ KString.EMPTY,
				null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.ILOAD, 2);
		mv.visitVarInsn(Opcodes.FLOAD, 3);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL,
				isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RendererLivingEntity",
				isObfuscated ? "a" : "shouldRenderPass",
				KString.EMPTY
						+ (isObfuscated ? "(Lblg;IF)I" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;IF)I")
						+ KString.EMPTY);
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if (!hadLocalSetArmorModel) {
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localSetArmorModel",
					KString.EMPTY
							+ (isObfuscated ? "(Lblg;IF)I" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;IF)I")
							+ KString.EMPTY,
					null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitVarInsn(Opcodes.ILOAD, 2);
			mv.visitVarInsn(Opcodes.FLOAD, 3);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL,
					isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RendererLivingEntity",
					isObfuscated ? "a" : "shouldRenderPass",
					KString.EMPTY
							+ (isObfuscated ? "(Lblg;IF)I" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;IF)I")
							+ KString.EMPTY);
			mv.visitInsn(Opcodes.IRETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "b" : "func_82408_c",
				KString.EMPTY
						+ (isObfuscated ? "(Lblg;IF)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;IF)V")
						+ KString.EMPTY,
				null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.ILOAD, 2);
		mv.visitVarInsn(Opcodes.FLOAD, 3);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "setPassArmorModel",
				"(Lapi/player/render/IRenderPlayerAPI;"
						+ (isObfuscated ? "Lblg;IF" : "Lnet/minecraft/client/entity/AbstractClientPlayer;IF") + ")V");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realSetPassArmorModel",
				KString.EMPTY
						+ (isObfuscated ? "(Lblg;IF)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;IF)V")
						+ KString.EMPTY,
				null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.ILOAD, 2);
		mv.visitVarInsn(Opcodes.FLOAD, 3);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
				isObfuscated ? "bop" : "net/minecraft/client/renderer/entity/RenderPlayer",
				isObfuscated ? "b" : "func_82408_c",
				KString.EMPTY
						+ (isObfuscated ? "(Lblg;IF)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;IF)V")
						+ KString.EMPTY);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superSetPassArmorModel",
				KString.EMPTY
						+ (isObfuscated ? "(Lblg;IF)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;IF)V")
						+ KString.EMPTY,
				null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.ILOAD, 2);
		mv.visitVarInsn(Opcodes.FLOAD, 3);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL,
				isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RendererLivingEntity",
				isObfuscated ? "b" : "func_82408_c",
				KString.EMPTY
						+ (isObfuscated ? "(Lblg;IF)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;IF)V")
						+ KString.EMPTY);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if (!hadLocalSetPassArmorModel) {
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localSetPassArmorModel",
					KString.EMPTY
							+ (isObfuscated ? "(Lblg;IF)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;IF)V")
							+ KString.EMPTY,
					null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitVarInsn(Opcodes.ILOAD, 2);
			mv.visitVarInsn(Opcodes.FLOAD, 3);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL,
					isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RendererLivingEntity",
					isObfuscated ? "b" : "func_82408_c",
					KString.EMPTY
							+ (isObfuscated ? "(Lblg;IF)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;IF)V")
							+ KString.EMPTY);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "a" : "setRenderManager",
				KString.EMPTY
						+ (isObfuscated ? "(Lbnn;)V" : "(Lnet/minecraft/client/renderer/entity/RenderManager;)V")
						+ KString.EMPTY,
				null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "setRenderManager",
				"(Lapi/player/render/IRenderPlayerAPI;"
						+ (isObfuscated ? "Lbnn;" : "Lnet/minecraft/client/renderer/entity/RenderManager;") + ")V");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realSetRenderManager",
				KString.EMPTY
						+ (isObfuscated ? "(Lbnn;)V" : "(Lnet/minecraft/client/renderer/entity/RenderManager;)V")
						+ KString.EMPTY,
				null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
				isObfuscated ? "bop" : "net/minecraft/client/renderer/entity/RenderPlayer",
				isObfuscated ? "a" : "setRenderManager",
				KString.EMPTY
						+ (isObfuscated ? "(Lbnn;)V" : "(Lnet/minecraft/client/renderer/entity/RenderManager;)V")
						+ KString.EMPTY);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superSetRenderManager",
				KString.EMPTY
						+ (isObfuscated ? "(Lbnn;)V" : "(Lnet/minecraft/client/renderer/entity/RenderManager;)V")
						+ KString.EMPTY,
				null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL,
				isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RendererLivingEntity",
				isObfuscated ? "a" : "setRenderManager",
				KString.EMPTY
						+ (isObfuscated ? "(Lbnn;)V" : "(Lnet/minecraft/client/renderer/entity/RenderManager;)V")
						+ KString.EMPTY);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if (!hadLocalSetRenderManager) {
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localSetRenderManager",
					KString.EMPTY
							+ (isObfuscated ? "(Lbnn;)V" : "(Lnet/minecraft/client/renderer/entity/RenderManager;)V")
							+ KString.EMPTY,
					null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL,
					isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RendererLivingEntity",
					isObfuscated ? "a" : "setRenderManager",
					KString.EMPTY
							+ (isObfuscated ? "(Lbnn;)V" : "(Lnet/minecraft/client/renderer/entity/RenderManager;)V")
							+ KString.EMPTY);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "a" : "setRenderPassModel", KString.EMPTY
				+ (isObfuscated ? "(Lbhr;)V" : "(Lnet/minecraft/client/model/ModelBase;)V") + KString.EMPTY, null,
				null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "setRenderPassModel",
				"(Lapi/player/render/IRenderPlayerAPI;"
						+ (isObfuscated ? "Lbhr;" : "Lnet/minecraft/client/model/ModelBase;") + ")V");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realSetRenderPassModel", KString.EMPTY
				+ (isObfuscated ? "(Lbhr;)V" : "(Lnet/minecraft/client/model/ModelBase;)V") + KString.EMPTY, null,
				null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
				isObfuscated ? "bop" : "net/minecraft/client/renderer/entity/RenderPlayer",
				isObfuscated ? "a" : "setRenderPassModel",
				KString.EMPTY + (isObfuscated ? "(Lbhr;)V" : "(Lnet/minecraft/client/model/ModelBase;)V")
						+ KString.EMPTY);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superSetRenderPassModel", KString.EMPTY
				+ (isObfuscated ? "(Lbhr;)V" : "(Lnet/minecraft/client/model/ModelBase;)V") + KString.EMPTY, null,
				null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL,
				isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RendererLivingEntity",
				isObfuscated ? "a" : "setRenderPassModel",
				KString.EMPTY + (isObfuscated ? "(Lbhr;)V" : "(Lnet/minecraft/client/model/ModelBase;)V")
						+ KString.EMPTY);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if (!hadLocalSetRenderPassModel) {
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localSetRenderPassModel", KString.EMPTY
					+ (isObfuscated ? "(Lbhr;)V" : "(Lnet/minecraft/client/model/ModelBase;)V") + KString.EMPTY,
					null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL,
					isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RendererLivingEntity",
					isObfuscated ? "a" : "setRenderPassModel",
					KString.EMPTY + (isObfuscated ? "(Lbhr;)V" : "(Lnet/minecraft/client/model/ModelBase;)V")
							+ KString.EMPTY);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "a" : "updateIcons",
				KString.EMPTY
						+ (isObfuscated ? "(Lrg;)V" : "(Lnet/minecraft/client/renderer/texture/IIconRegister;)V")
						+ KString.EMPTY,
				null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "updateIcons",
				"(Lapi/player/render/IRenderPlayerAPI;"
						+ (isObfuscated ? "Lrg;" : "Lnet/minecraft/client/renderer/texture/IIconRegister;") + ")V");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realUpdateIcons",
				KString.EMPTY
						+ (isObfuscated ? "(Lrg;)V" : "(Lnet/minecraft/client/renderer/texture/IIconRegister;)V")
						+ KString.EMPTY,
				null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
				isObfuscated ? "bop" : "net/minecraft/client/renderer/entity/RenderPlayer",
				isObfuscated ? "a" : "updateIcons",
				KString.EMPTY
						+ (isObfuscated ? "(Lrg;)V" : "(Lnet/minecraft/client/renderer/texture/IIconRegister;)V")
						+ KString.EMPTY);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superUpdateIcons",
				KString.EMPTY
						+ (isObfuscated ? "(Lrg;)V" : "(Lnet/minecraft/client/renderer/texture/IIconRegister;)V")
						+ KString.EMPTY,
				null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL,
				isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RendererLivingEntity",
				isObfuscated ? "a" : "updateIcons",
				KString.EMPTY
						+ (isObfuscated ? "(Lrg;)V" : "(Lnet/minecraft/client/renderer/texture/IIconRegister;)V")
						+ KString.EMPTY);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if (!hadLocalUpdateIcons) {
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localUpdateIcons",
					KString.EMPTY
							+ (isObfuscated ? "(Lrg;)V" : "(Lnet/minecraft/client/renderer/texture/IIconRegister;)V")
							+ KString.EMPTY,
					null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL,
					isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RendererLivingEntity",
					isObfuscated ? "a" : "updateIcons",
					KString.EMPTY
							+ (isObfuscated ? "(Lrg;)V" : "(Lnet/minecraft/client/renderer/texture/IIconRegister;)V")
							+ KString.EMPTY);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getMainModelField",
				isObfuscated ? "()Lbhr;" : "()Lnet/minecraft/client/model/ModelBase;", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitFieldInsn(Opcodes.GETFIELD, isObfuscated ? "bop" : "net/minecraft/client/renderer/entity/RenderPlayer",
				isObfuscated ? "i" : "mainModel", isObfuscated ? "Lbhr;" : "Lnet/minecraft/client/model/ModelBase;");
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setMainModelField",
				isObfuscated ? "(Lbhr;)V" : "(Lnet/minecraft/client/model/ModelBase;)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitFieldInsn(Opcodes.PUTFIELD, isObfuscated ? "bop" : "net/minecraft/client/renderer/entity/RenderPlayer",
				isObfuscated ? "i" : "mainModel", isObfuscated ? "Lbhr;" : "Lnet/minecraft/client/model/ModelBase;");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getModelArmorField",
				isObfuscated ? "()Lbhm;" : "()Lnet/minecraft/client/model/ModelBiped;", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitFieldInsn(Opcodes.GETFIELD, isObfuscated ? "bop" : "net/minecraft/client/renderer/entity/RenderPlayer",
				isObfuscated ? "h" : "modelArmor", isObfuscated ? "Lbhm;" : "Lnet/minecraft/client/model/ModelBiped;");
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setModelArmorField",
				isObfuscated ? "(Lbhm;)V" : "(Lnet/minecraft/client/model/ModelBiped;)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitFieldInsn(Opcodes.PUTFIELD, isObfuscated ? "bop" : "net/minecraft/client/renderer/entity/RenderPlayer",
				isObfuscated ? "h" : "modelArmor", isObfuscated ? "Lbhm;" : "Lnet/minecraft/client/model/ModelBiped;");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getModelArmorChestplateField",
				isObfuscated ? "()Lbhm;" : "()Lnet/minecraft/client/model/ModelBiped;", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitFieldInsn(Opcodes.GETFIELD, isObfuscated ? "bop" : "net/minecraft/client/renderer/entity/RenderPlayer",
				isObfuscated ? "g" : "modelArmorChestplate",
				isObfuscated ? "Lbhm;" : "Lnet/minecraft/client/model/ModelBiped;");
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setModelArmorChestplateField",
				isObfuscated ? "(Lbhm;)V" : "(Lnet/minecraft/client/model/ModelBiped;)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitFieldInsn(Opcodes.PUTFIELD, isObfuscated ? "bop" : "net/minecraft/client/renderer/entity/RenderPlayer",
				isObfuscated ? "g" : "modelArmorChestplate",
				isObfuscated ? "Lbhm;" : "Lnet/minecraft/client/model/ModelBiped;");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getModelBipedMainField",
				isObfuscated ? "()Lbhm;" : "()Lnet/minecraft/client/model/ModelBiped;", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitFieldInsn(Opcodes.GETFIELD, isObfuscated ? "bop" : "net/minecraft/client/renderer/entity/RenderPlayer",
				isObfuscated ? "f" : "modelBipedMain",
				isObfuscated ? "Lbhm;" : "Lnet/minecraft/client/model/ModelBiped;");
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setModelBipedMainField",
				isObfuscated ? "(Lbhm;)V" : "(Lnet/minecraft/client/model/ModelBiped;)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitFieldInsn(Opcodes.PUTFIELD, isObfuscated ? "bop" : "net/minecraft/client/renderer/entity/RenderPlayer",
				isObfuscated ? "f" : "modelBipedMain",
				isObfuscated ? "Lbhm;" : "Lnet/minecraft/client/model/ModelBiped;");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getRenderBlocksField",
				isObfuscated ? "()Lblm;" : "()Lnet/minecraft/client/renderer/RenderBlocks;", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitFieldInsn(Opcodes.GETFIELD, isObfuscated ? "bop" : "net/minecraft/client/renderer/entity/RenderPlayer",
				isObfuscated ? "c" : "field_147909_c",
				isObfuscated ? "Lblm;" : "Lnet/minecraft/client/renderer/RenderBlocks;");
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setRenderBlocksField",
				isObfuscated ? "(Lblm;)V" : "(Lnet/minecraft/client/renderer/RenderBlocks;)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitFieldInsn(Opcodes.PUTFIELD, isObfuscated ? "bop" : "net/minecraft/client/renderer/entity/RenderPlayer",
				isObfuscated ? "c" : "field_147909_c",
				isObfuscated ? "Lblm;" : "Lnet/minecraft/client/renderer/RenderBlocks;");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getRenderManagerField",
				isObfuscated ? "()Lbnn;" : "()Lnet/minecraft/client/renderer/entity/RenderManager;", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitFieldInsn(Opcodes.GETFIELD, isObfuscated ? "bop" : "net/minecraft/client/renderer/entity/RenderPlayer",
				isObfuscated ? "b" : "renderManager",
				isObfuscated ? "Lbnn;" : "Lnet/minecraft/client/renderer/entity/RenderManager;");
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setRenderManagerField",
				isObfuscated ? "(Lbnn;)V" : "(Lnet/minecraft/client/renderer/entity/RenderManager;)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitFieldInsn(Opcodes.PUTFIELD, isObfuscated ? "bop" : "net/minecraft/client/renderer/entity/RenderPlayer",
				isObfuscated ? "b" : "renderManager",
				isObfuscated ? "Lbnn;" : "Lnet/minecraft/client/renderer/entity/RenderManager;");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getRenderPassModelField",
				isObfuscated ? "()Lbhr;" : "()Lnet/minecraft/client/model/ModelBase;", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitFieldInsn(Opcodes.GETFIELD, isObfuscated ? "bop" : "net/minecraft/client/renderer/entity/RenderPlayer",
				isObfuscated ? "j" : "renderPassModel",
				isObfuscated ? "Lbhr;" : "Lnet/minecraft/client/model/ModelBase;");
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setRenderPassModelField",
				isObfuscated ? "(Lbhr;)V" : "(Lnet/minecraft/client/model/ModelBase;)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitFieldInsn(Opcodes.PUTFIELD, isObfuscated ? "bop" : "net/minecraft/client/renderer/entity/RenderPlayer",
				isObfuscated ? "j" : "renderPassModel",
				isObfuscated ? "Lbhr;" : "Lnet/minecraft/client/model/ModelBase;");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getShadowOpaqueField", "()F", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitFieldInsn(Opcodes.GETFIELD, isObfuscated ? "bop" : "net/minecraft/client/renderer/entity/RenderPlayer",
				isObfuscated ? "e" : "shadowOpaque", "F");
		mv.visitInsn(Opcodes.FRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setShadowOpaqueField", "(F)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.FLOAD, 1);
		mv.visitFieldInsn(Opcodes.PUTFIELD, isObfuscated ? "bop" : "net/minecraft/client/renderer/entity/RenderPlayer",
				isObfuscated ? "e" : "shadowOpaque", "F");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getShadowSizeField", "()F", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitFieldInsn(Opcodes.GETFIELD, isObfuscated ? "bop" : "net/minecraft/client/renderer/entity/RenderPlayer",
				isObfuscated ? "d" : "shadowSize", "F");
		mv.visitInsn(Opcodes.FRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setShadowSizeField", "(F)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.FLOAD, 1);
		mv.visitFieldInsn(Opcodes.PUTFIELD, isObfuscated ? "bop" : "net/minecraft/client/renderer/entity/RenderPlayer",
				isObfuscated ? "d" : "shadowSize", "F");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getSteveTexturesField",
				isObfuscated ? "()Lbqx;" : "()Lnet/minecraft/util/ResourceLocation;", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitFieldInsn(Opcodes.GETFIELD, isObfuscated ? "bop" : "net/minecraft/client/renderer/entity/RenderPlayer",
				isObfuscated ? "a" : "steveTextures", isObfuscated ? "Lbqx;" : "Lnet/minecraft/util/ResourceLocation;");
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getRenderPlayerBase",
				"(Ljava/lang/String;)Lapi/player/render/RenderPlayerBase;", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "getRenderPlayerBase",
				"(Lapi/player/render/IRenderPlayerAPI;Ljava/lang/String;)Lapi/player/render/RenderPlayerBase;");
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getRenderPlayerBaseIds", "()Ljava/util/Set;", null,
				null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "getRenderPlayerBaseIds",
				"(Lapi/player/render/IRenderPlayerAPI;)Ljava/util/Set;");
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "dynamic",
				"(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.ALOAD, 2);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "dynamic",
				"(Lapi/player/render/IRenderPlayerAPI;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;");
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getRenderPlayerAPI",
				"()Lapi/player/render/RenderPlayerAPI;", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitFieldInsn(Opcodes.GETFIELD, isObfuscated ? "bop" : "net/minecraft/client/renderer/entity/RenderPlayer",
				"renderPlayerAPI", "Lapi/player/render/RenderPlayerAPI;");
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getRenderPlayer",
				isObfuscated ? "()Lbop;" : "()Lnet/minecraft/client/renderer/entity/RenderPlayer;", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC, "getAllInstances",
				isObfuscated ? "()[Lbop;" : "()[Lnet/minecraft/client/renderer/entity/RenderPlayer;", null, null);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "getAllInstances",
				isObfuscated ? "()[Lbop;" : "()[Lnet/minecraft/client/renderer/entity/RenderPlayer;");
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		cv.visitField(Opcodes.ACC_PRIVATE | Opcodes.ACC_FINAL, "renderPlayerAPI", "Lapi/player/render/RenderPlayerAPI;",
				null, null);
	}
}
