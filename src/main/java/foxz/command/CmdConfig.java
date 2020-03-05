package foxz.command;

import java.util.Set;

import foxz.commandhelper.ChMcLogger;
import foxz.commandhelper.annotations.Command;
import foxz.commandhelper.annotations.SubCommand;
import foxz.commandhelper.permissions.OpOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockIce;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.BlockVine;
import noppes.npcs.CustomNpcs;
import noppes.npcs.controllers.ChunkController;

@Command(name = "config", desc = "Некоторые операции с конфигом", usage = "")
public final class CmdConfig extends ChMcLogger {

	public CmdConfig(final Object sender) {
		super(sender);
	}

	@SubCommand(desc = "Выключение/включение разложения листьев", usage = "<true/false>", permissions = {
			OpOnly.class }, hasEmptyCall = true)
	public boolean leavesdecay(final String[] args) {
		if (args.length == 0) {
			info("Расложение листьев: " + CustomNpcs.LeavesDecayEnabled);
		} else {
			CustomNpcs.LeavesDecayEnabled = Boolean.parseBoolean(args[0]);
			CustomNpcs.Config.updateConfig();
			final Set<String> names = Block.blockRegistry.getKeys();
			for (final String name : names) {
				final Block block = (Block) Block.blockRegistry.getObject(name);
				if (block instanceof BlockLeavesBase) {
					block.setTickRandomly(CustomNpcs.LeavesDecayEnabled);
				}
			}
			info("Разложение листьев: " + CustomNpcs.LeavesDecayEnabled);
		}
		return true;
	}

	@SubCommand(desc = "Выключает/включает рост лозы", usage = "<true/false>", permissions = {
			OpOnly.class }, hasEmptyCall = true)
	public boolean vinegrowth(final String[] args) {
		if (args.length == 0) {
			info("Рост лозы: " + CustomNpcs.VineGrowthEnabled);
		} else {
			CustomNpcs.VineGrowthEnabled = Boolean.parseBoolean(args[0]);
			CustomNpcs.Config.updateConfig();
			final Set<String> names = Block.blockRegistry.getKeys();
			for (final String name : names) {
				final Block block = (Block) Block.blockRegistry.getObject(name);
				if (block instanceof BlockVine) {
					block.setTickRandomly(CustomNpcs.VineGrowthEnabled);
				}
			}
			info("Рост лозы: " + CustomNpcs.VineGrowthEnabled);
		}
		return true;
	}

	@SubCommand(desc = "Выыключает/включает таяние льда", usage = "<true/false>", permissions = {
			OpOnly.class }, hasEmptyCall = true)
	public boolean icemelts(final String[] args) {
		if (args.length == 0) {
			info("Таяние льда: " + CustomNpcs.IceMeltsEnabled);
		} else {
			CustomNpcs.IceMeltsEnabled = Boolean.parseBoolean(args[0]);
			CustomNpcs.Config.updateConfig();
			final Set<String> names = Block.blockRegistry.getKeys();
			for (final String name : names) {
				final Block block = (Block) Block.blockRegistry.getObject(name);
				if (block instanceof BlockIce) {
					block.setTickRandomly(CustomNpcs.IceMeltsEnabled);
				}
			}
			info("Таяние льда: " + CustomNpcs.IceMeltsEnabled);
		}
		return true;
	}

	@SubCommand(desc = "Выключает/включает стрельбу из огнестрела", usage = "<true/false>", permissions = {
			OpOnly.class }, hasEmptyCall = true)
	public boolean guns(final String[] args) {
		if (args.length == 0) {
			info("Стрельба: " + CustomNpcs.GunsEnabled);
		} else {
			CustomNpcs.GunsEnabled = Boolean.parseBoolean(args[0]);
			CustomNpcs.Config.updateConfig();
			info("Стрельба: " + CustomNpcs.GunsEnabled);
		}
		return true;
	}

	@SubCommand(desc = "Замораживает/размораживает НПС", usage = "<true/false>", permissions = {
			OpOnly.class }, hasEmptyCall = true)
	public boolean freezenpcs(final String[] args) {
		if (args.length == 0) {
			info("Заморозка НПС: " + CustomNpcs.FreezeNPCs);
		} else {
			CustomNpcs.FreezeNPCs = Boolean.parseBoolean(args[0]);
			info("Заморозка НПС: " + CustomNpcs.FreezeNPCs);
		}
		return true;
	}

	@SubCommand(desc = "Устаналивает максимальное количество одновременно-работающих подгрузчиков чанков", usage = "<number>", permissions = {
			OpOnly.class }, hasEmptyCall = true)
	public boolean chunkloaders(final String[] args) {
		if (args.length == 0) {
			info("Подгрузчики чанков: " + ChunkController.instance.size() + "/" + CustomNpcs.ChuckLoaders);
		} else {
			try {
				CustomNpcs.ChuckLoaders = Integer.parseInt(args[0]);
			} catch (final NumberFormatException ex) {
				error("Вы не ввели число!");
				return false;
			}
			CustomNpcs.Config.updateConfig();

			final int size = ChunkController.instance.size();
			if (size > CustomNpcs.ChuckLoaders) {
				ChunkController.instance.unload(size - CustomNpcs.ChuckLoaders);
				info(size - CustomNpcs.ChuckLoaders + " подгрузчиков чанков выгружено.");
			}
			info("Подгрузчики чанком: " + ChunkController.instance.size() + "/" + CustomNpcs.ChuckLoaders);
		}
		return true;
	}

}
