/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.network;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;

import keelfy.darkage.DarkAge;
import keelfy.darkage.constants.EnumClientPacket;
import keelfy.darkage.constants.EnumGui;
import keelfy.darkage.constants.EnumPlayerClass;
import keelfy.darkage.constants.EnumProperty;
import keelfy.darkage.constants.EnumRepairKit;
import keelfy.darkage.constants.EnumServerPacket;
import keelfy.darkage.constants.EnumSign;
import keelfy.darkage.entities.player.DAPlayer;
import keelfy.darkage.entities.player.effect.DAEffect;
import keelfy.darkage.entities.player.effect.DAEffect.Effect;
import keelfy.darkage.entities.sign.SignAard;
import keelfy.darkage.entities.sign.SignAksi;
import keelfy.darkage.entities.sign.SignIgni;
import keelfy.darkage.entities.sign.SignIrden;
import keelfy.darkage.entities.sign.SignKven;
import keelfy.darkage.events.custom.PlayerJumpEvent;
import keelfy.darkage.handlers.server.BookHandler;
import keelfy.darkage.items.DAItem;
import keelfy.darkage.items.IFastUsable;
import keelfy.darkage.items.IRepairable;
import keelfy.darkage.items.Money;
import keelfy.darkage.items.storybook.AbstractStoryBook;
import keelfy.darkage.items.storybook.StoryBook;
import keelfy.darkage.utils.DAUtils;
import keelfytools.KeelfyUtils;
import keelfytools.log.KeelfyLog;
import keelfytools.network.PacketDispatcher;
import keelfytools.network.client.CustomClientMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.RegistryNamespaced;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

/**
 * @author keelfy
 * @created 5 июн. 2017 г.
 */
public enum ServerPacketHandler {
	Instance;
	
	public static void sendTo(EnumClientPacket packet, EntityPlayerMP mp, Object... objects) {
		if (KeelfyUtils.isServerSide()) {
			PacketDispatcher.getInstance().sendTo(new CustomClientMessage(packet, objects), mp);
		}
	}

	public static void syncEffects(EntityPlayerMP mp) {
		if (KeelfyUtils.isServerSide()) {
			DAEffect dae = DAEffect.get(mp);

			if (dae != null) {
				NBTTagCompound data = new NBTTagCompound();
				dae.saveNBTData(data);
				sendTo(EnumClientPacket.SYNCEFFETCS, mp, data);
			}
		}
	}

	public static void syncPlayer(EntityPlayerMP mp) {
		if (KeelfyUtils.isServerSide()) {
			DAPlayer dap = DAPlayer.get(mp);

			if (dap != null) {
				NBTTagCompound data = new NBTTagCompound();
				dap.saveNBTData(data);
				sendTo(EnumClientPacket.SYNCPLAYER, mp, data);
			}
		}
	}

	public static void handleOnServer(EnumServerPacket type, EntityPlayer player, Object[] args) {
		if (KeelfyUtils.isServerSide()) {
			DAPlayer dap = DAPlayer.get(player);

			if (dap != null && !player.worldObj.isRemote) {
				switch (type) {
				case CHANGESIGN:
					handleChangeSign(player, dap, args);
					break;
				case CLASS:
					handleClass(player, dap, args);
					break;
				case SMGUI:
					handleSMGui(player, dap, args);
					break;
				case GIVEBOOK:
					handleGiveBook(player, dap, args);
					break;
				case HOTSLOT:
					handleHotSlot(player, dap, args);
					break;
				case IIT:
					handleIIT(player, dap, args);
					break;
				case MAXHEALTH:
					handleMaxHealth(player, dap, args);
					break;
				case MONEYCLICK:
					handleMoneyClick(player, dap, args);
					break;
				case OPENGUI:
					handleOpenGUI(player, dap, args);
					break;
				case PERFORMEGGECT:
					handlePerformEffect(player, dap, args);
					break;
				case PLAYERJUMP:
					handlePlayerJump(player, dap, args);
					break;
				case REPAIRITEM:
					handleRepairItem(player, dap, args);
					break;
				case USESIGN:
					handleUseSign(player, dap, args);
					break;
				case WEIGHT:
					handleWeight(player, dap, args);
					break;
				case DROPITEM:
					handleDropItem(player, dap, args);
					break;
				case UPDATEBOOK:
					handleUpdateBook(player, dap, args);
					break;
				default:
					break;
				}
			}
		}
	}

	private static void handleUpdateBook(EntityPlayer player, DAPlayer dap, Object[] args) {
		if (KeelfyUtils.isServerSide()) {
			if (player.getCurrentEquippedItem() != null
					&& player.getCurrentEquippedItem().getItem() instanceof StoryBook) {
				ItemStack is = player.getCurrentEquippedItem();
				AbstractStoryBook slBook = DarkAge.instance.bookHandler.getContent(is);
	
				if (slBook != null) {
					is.getTagCompound().setTag("pages", AbstractStoryBook.writePagesToNBT(slBook.pages));
					is.getTagCompound().setString("title", slBook.getTitle());
					is.getTagCompound().setString("author", slBook.getAuthor());
				}
			}
		}
	}

	private static void handleSMGui(EntityPlayer player, DAPlayer dap, Object[] args) {
		if (KeelfyUtils.isServerSide()) {
			BookHandler bh = DarkAge.instance.bookHandler;
			List<File> dabs = bh.listFiles(DarkAge.instance.fileHandler.getDABFolderPath());
			List<AbstractStoryBook> slBooks = new ArrayList();

			for (File book : dabs) {
				if (book.getAbsolutePath().contains(".dab")) {
					slBooks.add(bh.loadBookFromDABFile(FilenameUtils.removeExtension(book.getName())));
				} else {
					KeelfyLog.err("Неизвестный формат книги, путь ->");
					KeelfyLog.err(book.getAbsolutePath());
				}
			}
		}
	}

	private static void handleDropItem(EntityPlayer player, DAPlayer dap, Object[] args) {
		if (KeelfyUtils.isServerSide()) {
			int slotNumber = (Integer) args[0];
			boolean isShiftPressed = (Boolean) args[1];
			ItemStack stack = player.inventory.getStackInSlot(slotNumber);

			if (stack != null) {
				if (isShiftPressed) {
					player.dropPlayerItemWithRandomChoice(stack, true);
					player.inventory.setInventorySlotContents(slotNumber, (ItemStack) null);
				} else {
					ItemStack toDrop = player.inventory.getStackInSlot(slotNumber);
					toDrop.stackSize = 1;
					player.dropPlayerItemWithRandomChoice(toDrop, true);
					player.inventory.getStackInSlot(slotNumber).stackSize--;
				}
			}
		}
	}

	private static void handleWeight(EntityPlayer player, DAPlayer dap, Object[] args) {
		if (KeelfyUtils.isServerSide()) {
			float amount = (Float) args[0];

			dap.update(EnumProperty.WEIGHT, amount);
		}
	}

	private static void handleRepairItem(EntityPlayer player, DAPlayer dap, Object[] args) {
		if (KeelfyUtils.isServerSide()) {
			float repairingPercent = (Float) args[0];
			EnumRepairKit type = EnumRepairKit.values()[(Byte) args[1]];
			int clickedNumber = ((Integer) args[2]) + 1;
			ItemStack rk = (ItemStack) args[3];

			if (player.inventory.getStackInSlot(clickedNumber) != null) {
				ItemStack clickedItemStack = player.inventory.getStackInSlot(clickedNumber);
				Item clickedItem1 = clickedItemStack.getItem();

				if (clickedItem1 instanceof IRepairable && ((IRepairable) clickedItem1).getRepairKitType() == type) {
					NBTTagCompound tag = clickedItemStack.getTagCompound();
					DAItem clickedItem = (DAItem) clickedItem1;
					float maxDamage = clickedItem.getMaxDurability();
					float repairAmount = maxDamage / 100 * repairingPercent;
					float damage = clickedItem.getDurability(clickedItemStack);

					if (tag.hasKey(IRepairable.DURABILITY)) {
						tag.setFloat(IRepairable.DURABILITY,
								tag.getFloat(IRepairable.DURABILITY) + Math.round(repairAmount));

						player.inventory.getStackInSlot(clickedNumber).setTagCompound(tag);

						player.inventory.consumeInventoryItem(rk.getItem());
					}

					sendTo(EnumClientPacket.REPAIRREQUEST, (EntityPlayerMP) player);
				}
			}
		}
	}

	private static void handlePlayerJump(EntityPlayer player, DAPlayer dap, Object[] args) {
		if (KeelfyUtils.isServerSide()) {
			MinecraftForge.EVENT_BUS.post(new PlayerJumpEvent(player));
		}
	}

	private static void handlePerformEffect(EntityPlayer player, DAPlayer dap, Object[] args) {
		if (KeelfyUtils.isServerSide()) {
			DAEffect wce = DAEffect.get(player);
			Effect effect = Effect.values()[(Byte) args[0]];
			int duration = (Integer) args[1];
			int efficiency = (Integer) args[2];

			switch (effect) {
			case DRUNK:
				wce.getDrunk().perform(duration, efficiency);
				break;
			case HEAL:
				wce.getHeal().perform(duration, efficiency);
				break;
			case SATURATION:
				wce.getSaturation().perform(duration, efficiency);
				break;
			}
		}
	}

	private static void handleOpenGUI(EntityPlayer player, DAPlayer dap, Object[] args) {
		if (KeelfyUtils.isServerSide()) {
			EnumGui id = EnumGui.values()[(Byte) args[0]];
			player.openGui(DarkAge.instance, id.ordinal(), player.worldObj, (int) player.posX, (int) player.posY,
					(int) player.posZ);
		}
	}

	private static void handleMaxHealth(EntityPlayer player, DAPlayer dap, Object[] args) {
		if (KeelfyUtils.isServerSide()) {
			float amount = (Float) args[0];

			dap.setPlayerMaxHealth(amount);
		}
	}

	private static void handleIIT(EntityPlayer player, DAPlayer dap, Object[] args) {
		if (KeelfyUtils.isServerSide()) {
			float amount = (Float) args[0];

			dap.setWitcherIIT(amount);
		}
	}

	private static void handleHotSlot(EntityPlayer player, DAPlayer dap, Object[] args) {
		if (KeelfyUtils.isServerSide()) {
			int id = (Integer) args[0];

			if (player.inventory.mainInventory[id] != null) {
				IFastUsable item = (IFastUsable) player.inventory.mainInventory[id].getItem();

				item.use(player.inventory.mainInventory[id], player.worldObj, player, id);
			}
		}
	}

	private static void handleMoneyClick(EntityPlayer player, DAPlayer dap, Object[] args) {
		if (KeelfyUtils.isServerSide()) {
			boolean isShiftPressed = (Boolean) args[0];
			boolean isCtrlPressed = (Boolean) args[1];
			float value = (Float) args[2];

			RegistryNamespaced rn = Item.itemRegistry;

			Money one = (Money) rn.getObject(DAUtils.MODID + ":OREN");
			Money hundred = (Money) rn.getObject(DAUtils.MODID + ":oren100");
			Money thousand = (Money) rn.getObject(DAUtils.MODID + ":oren1000");

			if (value == one.getValueInOrenes()) {
				if (isShiftPressed) {
					int result = 0;
					for (ItemStack stack : player.inventory.mainInventory) {
						if (stack != null && stack.getItem() instanceof Money && stack.getItem() == one) {
							if (result >= 100)
								break;
							result += stack.stackSize;
						}
					}

					if (result >= 100) {
						for (int i = 0; i < 100; i++) {
							player.inventory.consumeInventoryItem(one);
						}
						dap.inventory.addItemStackToInventory(new ItemStack(hundred));
					}
				}
			} else if (value == hundred.getValueInOrenes()) {
				if (isCtrlPressed) {
					if (canGive(player, 2)) {
						ItemStack toGive = new ItemStack(one);
						toGive.stackSize = 50;
						dap.inventory.addItemStackToInventory(toGive.copy());
						dap.inventory.addItemStackToInventory(toGive.copy());
						player.inventory.consumeInventoryItem(hundred);
					}
				} else if (isShiftPressed) {
					int result = 0;
					for (ItemStack stack : player.inventory.mainInventory) {
						if (stack != null && stack.getItem() instanceof Money && stack.getItem() == hundred) {
							if (result >= 10)
								break;
							result += stack.stackSize;
						}
					}

					if (result >= 10) {
						for (int i = 0; i < 10; i++) {
							player.inventory.consumeInventoryItem(hundred);
						}
						dap.inventory.addItemStackToInventory(new ItemStack(thousand));
					}
				}
			} else if (value == thousand.getValueInOrenes()) {
				if (isCtrlPressed) {
					if (canAdd(player, hundred)) {
						ItemStack toGive = new ItemStack(hundred);
						toGive.stackSize = 10;
						dap.inventory.addItemStackToInventory(toGive);
						player.inventory.consumeInventoryItem(thousand);
					}
				}
			}
		}
	}

	private static void handleGiveBook(EntityPlayer player, DAPlayer dap, Object[] args) {
		if (KeelfyUtils.isServerSide()) {
			// NBTTagCompound data = (NBTTagCompound) args[0];
			// String author = (String) args[1];
			// String title = (String) args[2];
			// int id = (Integer) args[3];
			// int amount = (Integer) args[4];
			//
			// data.setString("author", author);
			// data.setString("title", title);
			// data.setInteger("id", id);
			//
			// ItemStack book = new ItemStack(ItemRegister.writtenBook);
			// book.stackSize = amount;
			// book.setTagCompound(data);
			//
			// if(player.capabilities.isCreativeMode)
			// player.inventory.addItemStackToInventory(book.copy());
			// else
			// dap.inventory.addItemStackToInventory(book.copy());
		}
	}

	private static void handleChangeSign(EntityPlayer player, DAPlayer dap, Object[] args) {
		if (KeelfyUtils.isServerSide()) {
			if (dap.getPlayerClass() == EnumPlayerClass.WITCHER) {
				if (dap.getWitcherSign().ordinal() < 4) {
					EnumSign newSign = EnumSign.values()[dap.getWitcherSign().ordinal() + 1];
					dap.update(EnumProperty.CURRENT_SIGN, newSign.ordinal());
				} else if (dap.getWitcherSign().ordinal() == 4) {
					dap.update(EnumProperty.CURRENT_SIGN, 0);
				}

				if (dap.getWitcherSign().ordinal() > 4 || dap.getWitcherSign().ordinal() < 0) {
					dap.update(EnumProperty.CURRENT_SIGN, 0);
				}
			} else {
				if (dap.getWitcherSign() != EnumSign.NONE) {
					dap.update(EnumProperty.CURRENT_SIGN, EnumSign.NONE.ordinal());
				}
			}
		}
	}

	private static void handleClass(EntityPlayer player, DAPlayer dap, Object[] args) {
		if (KeelfyUtils.isServerSide()) {
			dap.setPlayerClass(EnumPlayerClass.values()[(Integer) args[0]]);
		}
	}

	private static final void handleUseSign(EntityPlayer player, DAPlayer dap, Object[] args) {
		if (KeelfyUtils.isServerSide()) {
			if (dap.getPlayerClass() == EnumPlayerClass.WITCHER) {
				EntityPlayerMP mp = (EntityPlayerMP) player;
				World world = mp.worldObj;
				EnumSign id = dap.getWitcherSign();
				int entityId = (Integer) args[0];

				switch (id) {
				case AARD:
					SignAard aard = new SignAard(world);
					aard.setOwner(mp);
					aard.handle(entityId);
					break;
				case AKSI:
					SignAksi aksi = new SignAksi(world);
					aksi.setOwner(mp);
					aksi.handle(entityId);
					break;
				case IGNI:
					SignIgni igni = new SignIgni(world);
					igni.setOwner(mp);
					igni.handle(entityId);
					break;
				case IRDEN:
					SignIrden irden = new SignIrden(world);
					irden.setOwner(mp);
					irden.handle();
					break;
				case KVEN:
					SignKven kven = new SignKven(world);
					kven.setOwner(player);
					kven.handle();
					break;
				case NONE:
					break;
				}
			}
		}
	}

	private static boolean canAdd(EntityPlayer player, Item toAdd) {
		if (KeelfyUtils.isServerSide()) {
			int j = 0;
			for (int i = 12; i < player.inventory.mainInventory.length; i++) {
				ItemStack stack = player.inventory.mainInventory[i];

				if (stack != null && stack.getItem() == toAdd && stack.stackSize < stack.getMaxStackSize()) {
					return true;
				}
			}
			return canGive(player, 1);
		}
		return false;
	}

	private static boolean canGive(EntityPlayer player, int slots) {
		if (KeelfyUtils.isServerSide()) {
			int j = 0;
			for (int i = 12; i < player.inventory.mainInventory.length; i++) {
				ItemStack stack = player.inventory.mainInventory[i];
				if (j >= slots)
					return true;

				if (stack == null) {
					j += 1;
				}
			}
		}
		return false;
	}
}
