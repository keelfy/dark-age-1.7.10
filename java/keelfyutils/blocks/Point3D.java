package keelfyutils.blocks;

import io.netty.buffer.ByteBuf;

/**
 * @author keelfy
 * @created 13 июл. 2017 г.
 */
public final class Point3D {
	public int x;
	public int y;
	public int z;

	public Point3D(final int x, final int y, final int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public static final Point3D from(final int x, final int y, final int z) {
		return new Point3D(x, y, z);
	}

	@Override
	public String toString() {
		return "X: " + x + ", Y: " + y + ", Z: " + z;
	}

	public static final void writeToBuffer(final ByteBuf buffer, final Point3D pos) {
		buffer.writeInt(pos.x);
		buffer.writeInt(pos.y);
		buffer.writeInt(pos.z);
	}

	public static final Point3D readFromBuffer(final ByteBuf buffer) {
		return new Point3D(buffer.readInt(), buffer.readInt(), buffer.readInt());
	}
}
