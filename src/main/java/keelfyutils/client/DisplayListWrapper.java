package keelfyutils.client;

import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.Map;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelCustom;
import net.minecraftforge.client.model.obj.GroupObject;
import net.minecraftforge.client.model.obj.WavefrontObject;

/**
 * @author dahaka ( http://forum.mcmodding.ru )
 */
@SideOnly(Side.CLIENT)
public final class DisplayListWrapper implements IModelCustom {

	private final Map<String, Integer> lists = new HashMap();
	private final IntBuffer bufAll;
	private final String type;

	public DisplayListWrapper(final WavefrontObject model) {
		type = model.getType();
		int list = GL11.glGenLists(model.groupObjects.size());
		for (GroupObject obj : model.groupObjects) {
			GL11.glNewList(list, GL11.GL_COMPILE);
			model.renderPart(obj.name);
			GL11.glEndList();
			lists.put(obj.name, list++);
		}
		bufAll = createIntBuffer();
	}

	private IntBuffer createIntBuffer() {
		IntBuffer buffer = BufferUtils.createIntBuffer(lists.size());
		for (int i : lists.values()) {
			buffer.put(i);
		}
		buffer.flip();
		return buffer;
	}

	@Override
	public String getType() {
		return type;
	}

	public void renderAll(ResourceLocation texture) {
		KGL.bindTexture(texture);
		this.renderAll();
	}

	@Override
	public void renderAll() {
		GL11.glCallLists(bufAll);
	}

	@Override
	public void renderOnly(final String... groupNames) {
		if (groupNames == null || groupNames.length == 0)
			return;

		for (String group : groupNames) {
			renderPart(group);
		}
	}

	@Override
	public void renderPart(final String partName) {
		Integer list = lists.get(partName);
		if (list != null) {
			GL11.glCallList(list);
		}
	}

	@Override
	public void renderAllExcept(String... excludedGroupNames) {}
}
