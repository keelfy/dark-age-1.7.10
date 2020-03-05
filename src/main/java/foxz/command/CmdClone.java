package foxz.command;

import java.util.List;

import foxz.commandhelper.ChMcLogger;
import foxz.commandhelper.annotations.Command;
import foxz.commandhelper.annotations.SubCommand;
import foxz.commandhelper.permissions.OpOnly;
import foxz.commandhelper.permissions.ParamCheck;
import foxz.commandhelper.permissions.PlayerOnly;
import foxz.utils.Utils;
import keelfyutils.str.KString;
import net.minecraft.command.CommandBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import noppes.npcs.controllers.ServerCloneController;
import noppes.npcs.entity.EntityNPCInterface;

@Command(name = "clone", desc = "Операция клонирования (сервер)")
public final class CmdClone extends ChMcLogger {

	public CmdClone(final Object sender) {
		super(sender);
	}

	@SubCommand(desc = "Добавляет НПС(ов) в базу данных клонов", usage = "<npc> <tab> [clonedname]", permissions = {
			OpOnly.class, PlayerOnly.class, ParamCheck.class })
	public Boolean add(final String[] args) {
		final EntityPlayerMP player = (EntityPlayerMP) pcParam;
		int tab = 0;
		try {
			tab = Integer.parseInt(args[1]);
		} catch (final NumberFormatException ex) {

		}
		final List<EntityNPCInterface> list = Utils.getNearbeEntityFromPlayer(EntityNPCInterface.class, player, 80);
		for (final EntityNPCInterface npc : list) {
			if (npc.display.name.equalsIgnoreCase(args[0])) {
				String name = npc.display.name;
				if (args.length > 2) {
					name = args[2];
				}
				final NBTTagCompound compound = new NBTTagCompound();
				if (!npc.writeToNBTOptional(compound))
					return false;
				ServerCloneController.Instance.addClone(compound, name, tab);
				return true;
			}
		}
		return true;
	}

	@SubCommand(desc = "Список НПС из базы данных клонов", usage = "<tab>", permissions = { OpOnly.class,
			ParamCheck.class })
	public Boolean list(final String[] args) {
		sendMessage("--- Сохраненные НПС --- (сервер)");
		int tab = 0;
		try {
			tab = Integer.parseInt(args[0]);
		} catch (final NumberFormatException ex) {

		}
		for (final String name : ServerCloneController.Instance.getClones(tab)) {
			sendMessage(name);
		}
		sendMessage("------------------------------------");
		return true;
	}

	@SubCommand(desc = "Удаляет НПС из базы данных клонов", usage = "<name> <tab>", permissions = { OpOnly.class,
			ParamCheck.class })
	public Boolean del(final String[] args) {
		final String nametodel = args[0];
		int tab = 0;
		try {
			tab = Integer.parseInt(args[1]);
		} catch (final NumberFormatException ex) {

		}
		boolean deleted = false;
		for (final String name : ServerCloneController.Instance.getClones(tab)) {
			if (nametodel.equalsIgnoreCase(name)) {
				ServerCloneController.Instance.removeClone(name, tab);
				deleted = true;
				break;
			}
		}
		if (!ServerCloneController.Instance.removeClone(nametodel, tab)) {
			error(String.format("НПС '%s' не найден!", nametodel));
			return false;
		}
		;
		return true;
	}

	@SubCommand(desc = "Спавнит клонированного НПС", usage = "<name> <tab> [[world:]x,y,z]] [newname]", permissions = {
			OpOnly.class, ParamCheck.class })
	public boolean spawn(final String[] args) {
		final String name = args[0].replaceAll("%", KString.SPACE); // if name of npc separed by space, user must
																			// use %
		// in place of space
		int tab = 0;
		try {
			tab = Integer.parseInt(args[1]);
		} catch (final NumberFormatException ex) {

		}
		String newname = null;
		final NBTTagCompound compound = ServerCloneController.Instance.getCloneData(this.pcParam, name, tab);
		if (compound == null) {
			error("Неизвестный НПС!");
			return false;
		}
		World world = pcParam.getEntityWorld();
		double posX = pcParam.getPlayerCoordinates().posX;
		double posY = pcParam.getPlayerCoordinates().posY;
		double posZ = pcParam.getPlayerCoordinates().posZ;

		if (args.length > 2) {
			String location = args[2];
			String[] par;
			if (location.contains(":")) {
				par = location.split(":");
				location = par[1];
				world = Utils.getWorld(par[0]);
				if (world == null) {
					error(String.format("Неизвестный мир '%s'!", par[0]));
					return false;
				}
			}

			if (location.contains(",")) {
				par = location.split(",");
				if (par.length != 3) {
					error("Позиция должна быть задана тремя координатами x, y, z!");
					return false;
				}
				try {
					posX = CommandBase.func_110666_a(pcParam, posX, par[0]);
					posY = CommandBase.func_110665_a(pcParam, posY, par[1].trim(), 0, 0);
					posZ = CommandBase.func_110666_a(pcParam, posZ, par[2]);
				} catch (final NumberFormatException ex) {
					error("Позиция должна быть задана цифрами!");
					return false;
				}
				if (args.length > 3) {
					newname = args[3];
				}
			} else {
				newname = location;
			}
		}

		if (posX == 0 && posY == 0 && posZ == 0) {// incase it was called from the console and not pos was given
			error("Неоходимо задать позицию!");
			return false;
		}

		final Entity entity = EntityList.createEntityFromNBT(compound, world);
		entity.setPosition(posX + 0.5, posY + 1, posZ + 0.5);
		if (entity instanceof EntityNPCInterface) {
			final EntityNPCInterface npc = (EntityNPCInterface) entity;
			npc.ai.startPos = new int[] { MathHelper.floor_double(posX), MathHelper.floor_double(posY),
					MathHelper.floor_double(posZ) };
			if (newname != null && !newname.isEmpty()) {
				npc.display.name = newname.replaceAll("%", KString.SPACE); // like name, newname must use % in
																				// place of space to keep a logical way
			}
		}
		world.spawnEntityInWorld(entity);
		return true;
	}
}
