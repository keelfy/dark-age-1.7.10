package noppes.npcs.controllers;

import java.util.HashMap;
import java.util.Random;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class Lines {

	private static final Random random = new Random();

	public HashMap<Integer, Line> lines = new HashMap();

	public NBTTagCompound writeToNBT() {
		NBTTagCompound compound = new NBTTagCompound();

		NBTTagList nbttaglist = new NBTTagList();
		for (int slot : lines.keySet()) {
			Line line = lines.get(slot);
			NBTTagCompound nbttagcompound = new NBTTagCompound();
			nbttagcompound.setInteger("Slot", slot);
			nbttagcompound.setString("Line", line.text);
			nbttagcompound.setString("Song", line.sound);

			nbttaglist.appendTag(nbttagcompound);
		}

		compound.setTag("Lines", nbttaglist);
		return compound;
	}

	public void readNBT(final NBTTagCompound compound) {
		NBTTagList nbttaglist = compound.getTagList("Lines", 10);

		HashMap<Integer, Line> map = new HashMap();
		for (int i = 0; i < nbttaglist.tagCount(); i++) {
			NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(i);
			Line line = new Line();
			line.text = nbttagcompound.getString("Line");
			line.sound = nbttagcompound.getString("Song");

			map.put(nbttagcompound.getInteger("Slot"), line);
		}
		lines = map;
	}

	public Line getLine() {
		if (lines.isEmpty())
			return null;
		return lines.get(random.nextInt(lines.size()));
	}

	public boolean isEmpty() {
		return lines.isEmpty();
	}
}
