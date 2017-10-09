/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.server;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import keelfy.darkcore.common.player.DADataManager;
import keelfy.darkcore.common.player.DAPlayerData;
import keelfy.darkdata.constants.EnumPlayerClass;
import keelfy.darkdata.handlers.registerers.ItemRegister;
import keelfy.darkdata.items.DAItem;
import keelfy.darkdata.items.strorybook.AbstractStoryBook;
import keelfyutils.KUtils;
import keelfyutils.bukkit.KPermissionsLoader;
import keelfyutils.client.KLocalization;
import keelfyutils.commands.KCommand;
import keelfyutils.server.KServer;
import keelfyutils.str.Brush;
import keelfyutils.str.KLog;
import keelfyutils.str.KString;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.roles.RoleTrader;

/**
 * @author keelfy
 * @created 6 июл. 2017 г.
 */
public final class DACommands {

	@KCommand(usage = "Помощь по командам Dark Age", onlyByPlayer = true)
	public static final void dahelp(final EntityPlayer player, final String[] args) {
		if (KUtils.PROTECT_SERVER) {
			if (!KPermissionsLoader.has(player, DAServer.ADMIN)) {
				KServer.sendErrMessage(player, "У вас нет прав для выполнения этой команды!");
				return;
			}

			KServer.sendFineMessage(player, "/twp" + Brush.GRAY + " - Телепортация игрока в мир.");
			KServer.sendFineMessage(player, "    Аргументы:" + Brush.GRAY + " [x] [y] [z] [ник] [название мира / ID]");

			KServer.sendFineMessage(player, "/booklist" + Brush.GRAY + " - Список книг.");
			KServer.sendFineMessage(player, "/booknamelist" + Brush.GRAY + " - Список книг с названиями.");
			KServer.sendFineMessage(player, "/setname" + Brush.GRAY + " - Устанавливает название у предмета в руке.");
			KServer.sendFineMessage(player, "/updatebooks" + Brush.GRAY + " - Обновляет все книги на сервере.");
			KServer.sendFineMessage(player, "/givebook" + Brush.GRAY + " - Выдает книгу.");
			KServer.sendFineMessage(player, "    Аргументы:" + Brush.GRAY + " [название файла / ID] [кол-во]");
			KServer.sendFineMessage(player, "/questonly" + Brush.GRAY + " - Устанавливает квестовый статус для пердмета в руке.");
			KServer.sendFineMessage(player, "/worldlist" + Brush.GRAY + " - Список загруженных миров.");

		}
	}

	@KCommand(onlyByPlayer = true)
	public static final void killconc(final EntityPlayer player, final String[] args) {
		if (KUtils.PROTECT_SERVER) {
			if (!KPermissionsLoader.has(player, DAServer.ADMIN)) {
				KServer.sendErrMessage(player, "У вас нет прав для выполнения этой команды!");
				return;
			}

			if (args.length != 1) {
				return;
			}

			final int id = Integer.parseInt(args[0]);

			Entity n = player.worldObj.getEntityByID(id);

			if (n != null) {
				n.setDead();
				KServer.sendInfoMessage(player, Brush.DARK_AQUA + "[Dark Age] " + Brush.GREEN + "Существо по координатам X:" + n.posX + ", Y:" + n.posY + ", Z:" + n.posZ + " уничтожено!");
			} else {
				KServer.sendInfoMessage(player, Brush.DARK_AQUA + "[Dark Age] " + Brush.YELLOW + "Существо с таким ID не найдено в текущем мире.");
			}
		}
	}

	@KCommand(usage = "Список загруженных миров.")
	public static final void worldlist(final ICommandSender sender, final String[] agrs) {
		if (KUtils.PROTECT_SERVER) {
			if (sender instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) sender;
				if (!KPermissionsLoader.has(player, DAServer.TPW) && !KPermissionsLoader.has(player, DAServer.ADMIN)) {
					KServer.sendErrMessage(player, "У вас нет прав для выполнения этой команды!");
					return;
				}
			}

			final List<String> worlds = new ArrayList();
			final List<Integer> ids = new ArrayList();
			final Integer[] worldIds = DimensionManager.getIDs(true);
			for (final int id : worldIds) {
				WorldServer world = DimensionManager.getWorld(id);

				if (world == null) {
					DimensionManager.initDimension(id);
					world = DimensionManager.getWorld(id);
				}

				worlds.add(world.getWorldInfo().getWorldName());
				ids.add(id);
			}
			final StringBuilder sb = new StringBuilder();
			sb.append("Доступные миры: ");
			for (int i = 0; i < worlds.size(); i++) {
				sb.append(worlds.get(i) + " (ID: " + ids.get(i) + "), ");
			}
			final String s = KLocalization.replaceLastChars(sb.toString(), ".", 2);

			KServer.sendInfoMessage(sender, s);
		}
	}

	@KCommand(usage = "Изменение класса игрока")
	public static final void setclass(final ICommandSender sender, final String[] strings) {
		if (KUtils.PROTECT_SERVER) {
			if (sender instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) sender;
				if (!KPermissionsLoader.has(player, DAServer.CLASS) && !KPermissionsLoader.has(player, DAServer.ADMIN)) {
					KServer.sendErrMessage(player, "У вас нет прав для выполнения этой команды!");
					return;
				}
			}

			EntityPlayer tochange;
			EnumPlayerClass clazz;

			if (strings.length < 2 || strings.length > 2) {
				if (sender instanceof EntityPlayer && ("WITCHER".equals(strings[0].toUpperCase()) || "HUMAN".equals(strings[0].toUpperCase())) && strings.length < 2) {
					tochange = (EntityPlayer) sender;
					clazz = EnumPlayerClass.valueOf(strings[0].toUpperCase());

					DADataManager.getPlayer(tochange).playerClass.change(clazz);

				} else {
					if (sender instanceof EntityPlayer) {
						KServer.sendErrMessage(sender, "Используйте /setclass <player> <witcher/human> или /setclass <witcher/human>");
					} else {
						KLog.err("Use /setclass <player> <witcher/human>");
					}
				}
				return;
			}

			tochange = KServer.getPlayerByUsername(strings[0]);
			clazz = EnumPlayerClass.valueOf(strings[1].toUpperCase());
			DAPlayerData data = DADataManager.getPlayer(tochange);

			if (data.playerClass.equals(clazz)) {
				if (sender instanceof EntityPlayer) {
					KServer.sendErrMessage(sender, "Игрок " + tochange.getCommandSenderName() + " уже этого класса!");
				} else {
					KLog.err("Игрок " + tochange.getCommandSenderName() + " уже этого класса!");
				}
			} else {
				data.playerClass.change(clazz);

				if (sender instanceof EntityPlayer) {
					KServer.sendFineMessage(sender, "Класс игрока " + tochange.getCommandSenderName() + " изменен на " + clazz.getLocalizedName());
				} else {
					KLog.info("Класс игрока " + tochange.getCommandSenderName() + " изменен на " + clazz.getLocalizedName());
				}
			}
		}
	}

	@KCommand(usage = "Телепортация игрока в другой мир")
	public static final void tpw(final ICommandSender sender, final String[] strings) {
		if (KUtils.PROTECT_SERVER) {
			if (sender instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) sender;
				if (!KPermissionsLoader.has(player, DAServer.TPW) && !KPermissionsLoader.has(player, DAServer.ADMIN)) {
					KServer.sendErrMessage(player, "У вас нет прав для выполнения этой команды!");
					return;
				}
			}

			int x;
			int y;
			int z;
			String worldName = KString.EMPTY;
			int worldId = -1000;
			String nickname = KString.EMPTY;

			try {
				x = Integer.parseInt(strings[0]);
				y = Integer.parseInt(strings[1]);
				z = Integer.parseInt(strings[2]);
				if (strings.length > 3) {
					nickname = strings[3];

					if (strings.length > 4) {
						try {
							worldId = Integer.parseInt(strings[4]);
						} catch (final NumberFormatException e) {
							int i;
							final StringBuilder sb = new StringBuilder();
							for (i = 4; i < strings.length; i++) {
								sb.append(strings[i]);
								if (i + 1 != strings.length) {
									sb.append(" ");
								}
							}
							worldName = sb.toString();
						}
					}
				}
			} catch (final Exception e) {
				KServer.sendErrMessage(sender, "Неверные данные для телепортации!");
				KServer.sendInfoMessage(sender, "/tpw [x] [y] [z] [ник] [название мира / ID]");
				return;
			}

			int tpWorldId = -1000;
			EntityPlayer tpPlayer = KServer.getPlayerByUsername(nickname);

			if (tpPlayer == null) {
				KServer.sendErrMessage(sender, "Неизвестный игрок");
				return;
			}

			Integer[] worldIds = DimensionManager.getIDs(true);
			for (int id : worldIds) {
				WorldServer world = DimensionManager.getWorld(id);

				if (world == null) {
					DimensionManager.initDimension(id);
					world = DimensionManager.getWorld(id);
				}

				if (worldId != -1000 && worldId == id) {
					tpWorldId = worldId;
					break;
				}

				if (KString.EMPTY.equals(worldName) && worldName.equals(world.getWorldInfo().getWorldName())) {
					tpWorldId = id;
				}
			}

			if (tpWorldId == -1000) {
				KServer.sendErrMessage(sender, "Неизвестный мир! (Либо не загруженный)");
				return;
			}

			KServer.teleport((EntityPlayerMP) tpPlayer, (double) x, (double) y, (double) z, tpPlayer.rotationYaw, tpPlayer.rotationPitch, tpWorldId);

		}
	}

	@KCommand(usage = "Список DAB книг")
	public static final void bookList(final ICommandSender sender, final String[] strings) {
		if (KUtils.PROTECT_SERVER) {
			if (sender instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) sender;
				if (!KPermissionsLoader.has(player, DAServer.BOOKS) && !KPermissionsLoader.has(player, DAServer.ADMIN)) {
					KServer.sendErrMessage(player, "У вас нет прав для выполнения этой команды!");
					return;
				}
			}

			final List<File> books = DABooks.Instance.listFiles();

			String list = StringUtils.EMPTY;
			final StringBuilder sb = new StringBuilder();
			int count = 0;

			for (final File book : books) {
				sb.append(book.getName().replaceAll(".dab", StringUtils.EMPTY));
				sb.append(Brush.GRAY + ", ");
				count++;
			}

			list = sb.toString();

			if (count == 0) {
				KServer.sendInfoMessage(sender, "Книги отсутствуют.");
				return;
			}

			KServer.sendFineMessage(sender, "Найдено " + count + " DAB файлов:");
			KServer.sendInfoMessage(sender, KLocalization.replaceLastChars(list, ".", 2));
		}
	}

	@KCommand(usage = "Список DAB книг с названиями")
	public static final void bookNameList(final ICommandSender sender, final String[] string) {
		if (KUtils.PROTECT_SERVER) {
			if (sender instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) sender;
				if (!KPermissionsLoader.has(player, DAServer.BOOKS) && !KPermissionsLoader.has(player, DAServer.ADMIN)) {
					KServer.sendErrMessage(player, "У вас нет прав для выполнения этой команды!");
					return;
				}
			}

			final List<File> books = DABooks.Instance.listFiles();

			String list = StringUtils.EMPTY;
			final StringBuilder sb = new StringBuilder();
			int count = 0;

			for (final File book : books) {
				sb.append(Brush.GRAY + "\'");
				sb.append(Brush.GRAY + DABooks.Instance.loadBookFromDABFile(FilenameUtils.removeExtension(book.getName())).getTitle());
				sb.append(Brush.GRAY + "\', ");
				count++;
			}

			list = sb.toString();

			if (count == 0) {
				KServer.sendInfoMessage(sender, "Книги отсутствуют.");
				return;
			}

			KServer.sendFineMessage(sender, "Найдено " + count + " DAB книг:");
			KServer.sendInfoMessage(sender, KLocalization.replaceLastChars(list, ".", 2));
		}
	}

	@KCommand(usage = "Меняет имя у предмета в руках", onlyByPlayer = true)
	public static final void setName(final EntityPlayer player, final String[] strings) {
		if (KUtils.PROTECT_SERVER) {
			if (!KPermissionsLoader.has(player, DAServer.SETNAME) && !KPermissionsLoader.has(player, DAServer.ADMIN)) {
				KServer.sendErrMessage(player, "У вас нет прав для выполнения этой команды!");
				return;
			}

			final StringBuilder sb = new StringBuilder();

			for (final String str : strings) {
				sb.append(str);
			}

			final String name = sb.toString().replace('&', '§');

			if (player.getCurrentEquippedItem() != null) {
				final ItemStack stack = player.getCurrentEquippedItem();
				stack.setStackDisplayName(name);

				KServer.sendFineMessage(player, "Новое имя предмета: " + name);
			} else {
				KServer.sendErrMessage(player, "У вас нет предмета в руках");
			}
		}
	}

	@KCommand(usage = "Обновляет все DAB книги на сервере")
	public static final void updateBooks(final ICommandSender sender, final String[] string) {
		if (KUtils.PROTECT_SERVER) {
			if (sender instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) sender;
				if (!KPermissionsLoader.has(player, DAServer.BOOKS) && !KPermissionsLoader.has(player, DAServer.ADMIN)) {
					KServer.sendErrMessage(player, "У вас нет прав для выполнения этой команды!");
					return;
				}
			}

			final DABooks bh = DABooks.Instance;

			final List<Entity> entities = sender.getEntityWorld().loadedEntityList;

			KServer.sendInfoMessage(sender, "Обновление началось...");
			int count = 0;
			for (final Entity entity : entities) {
				if (entity instanceof EntityPlayer) {
					final EntityPlayer player1 = (EntityPlayer) entity;
					if (player1.inventory.hasItem(ItemRegister.writtenBook)) {
						for (final ItemStack stack : player1.inventory.mainInventory) {
							if (bh.updateBook(stack)) {
								count++;
							}
						}
					}
				} else if (entity instanceof EntityNPCInterface) {
					final EntityNPCInterface npc = (EntityNPCInterface) entity;

					if (npc.roleInterface instanceof RoleTrader) {
						final RoleTrader trader = (RoleTrader) npc.roleInterface;

						if (trader.hasStoryBook()) {
							for (final ItemStack stack : trader.inventorySold.items.values()) {
								if (bh.updateBook(stack)) {
									count++;
								}
							}

							for (final ItemStack stack : trader.inventoryCurrency.items.values()) {
								if (bh.updateBook(stack)) {
									count++;
								}
							}
						}
					}
				}
			}
			KServer.sendFineMessage(sender, "Успешно обновлено " + count + " книг!");
		}
	}

	@KCommand(usage = "Выдает указанную DAB книгу", onlyByPlayer = true)
	public static final void giveBook(final EntityPlayer player, final String[] string) {
		if (KUtils.PROTECT_SERVER) {
			if (!KPermissionsLoader.has(player, DAServer.BOOKS) && !KPermissionsLoader.has(player, DAServer.ADMIN)) {
				KServer.sendErrMessage(player, "У вас нет прав для выполнения этой команды!");
				return;
			}

			if (string.length > 1) {
				int id = -1;
				int amount = 0;
				String fileName = null;

				final DABooks bh = DABooks.Instance;

				try {
					id = Integer.parseInt(string[0]);
				} catch (final NumberFormatException e) {
					fileName = string[0];
				}

				try {
					amount = Integer.parseInt(string[1]);
				} catch (final NumberFormatException e) {
					KServer.sendErrMessage(player, "Неккоректное число книг.");
				}

				AbstractStoryBook slBook = null;

				if (id != -1) {
					slBook = bh.getBookByID(id);
				} else if (fileName != null && !KString.isEmpty(fileName)) {
					slBook = bh.loadBookFromDABFile(fileName);
				}

				if (slBook == null) {
					KServer.sendErrMessage(player, "Неверное название файла книги или ID.");
					return;
				}

				if (amount != 0) {
					final ItemStack book = new ItemStack(ItemRegister.writtenBook);
					book.stackSize = amount;

					final NBTTagCompound data = new NBTTagCompound();
					data.setTag("pages", slBook.writePagesToNBT());
					data.setString("author", slBook.getAuthor());
					data.setString("title", slBook.getTitle());
					data.setInteger("id", slBook.getId());
					book.setTagCompound(data);

					if (player.capabilities.isCreativeMode) {
						player.inventory.addItemStackToInventory(book.copy());
					} else {
						final DAPlayerData dap = DADataManager.getPlayer(player);

						if (dap != null) {
							dap.inventory.addItemStackToInventory(book.copy());
						}
					}
				}

			} else {
				KServer.sendErrMessage(player, "Укажите ID книги первым и количество книг вторым параметрами.");
			}
		}
	}

	@KCommand(usage = "Дает предмету в руках квестовый статус", onlyByPlayer = true)
	public static final void questOnly(final EntityPlayer player, final String[] string) {
		if (KUtils.PROTECT_SERVER) {
			if (!KPermissionsLoader.has(player, DAServer.QUESTONLY) && !KPermissionsLoader.has(player, DAServer.ADMIN)) {
				KServer.sendErrMessage(player, "У вас нет прав для выполнения этой команды!");
				return;
			}

			if (player.getCurrentEquippedItem() != null) {
				DAItem.setQuestOnly(player.getCurrentEquippedItem());

				KServer.sendFineMessage(player, "Для предмета в руках установлен квестовый статус");
			} else {
				KServer.sendErrMessage(player, "У вас нет предмета в руках");
			}
		}
	}
}
