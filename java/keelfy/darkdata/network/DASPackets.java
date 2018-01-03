package keelfy.darkdata.network;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent.ServerCustomPacketEvent;
import io.netty.buffer.ByteBuf;
import keelfy.darkcore.common.player.DADataManager;
import keelfy.darkcore.common.player.DAPlayerData;
import keelfy.darkcore.common.player.EffectsManager.PlayerEffect;
import keelfy.darkcore.network.DANetwork;
import keelfy.darkcore.network.DASNetwork;
import keelfy.darkdata.DarkData;
import keelfy.darkdata.blocks.BlockSentinel.TileEntitySentinel;
import keelfy.darkdata.constants.EnumEffect;
import keelfy.darkdata.constants.EnumPlayerClass;
import keelfy.darkdata.constants.EnumRepairKit;
import keelfy.darkdata.constants.EnumSign;
import keelfy.darkdata.inventory.player.DAPlayerInventory;
import keelfy.darkdata.items.DAItem;
import keelfy.darkdata.items.IFastUsable;
import keelfy.darkdata.items.IRepairable;
import keelfy.darkdata.items.Money;
import keelfy.darkdata.items.strorybook.AbstractStoryBook;
import keelfy.darkdata.server.DABooks;
import keelfy.darkdata.server.player.DAPlayerDataHandler;
import keelfy.darkdata.server.signs.DASignHandler;
import keelfyutils.KUtils;
import keelfyutils.blocks.KBlocks;
import keelfyutils.blocks.Point3D;
import keelfyutils.bukkit.KPermissionsLoader;
import keelfyutils.network.KNetwork;
import keelfyutils.server.KServer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.RegistryNamespaced;

/**
 * @author keelfy
 * @created 24 июл. 2017 г.
 */
public class DASPackets {

	public static void register() {
		DANetwork.channel.register(new DASPackets());
	}

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public final void onServerPacket(final ServerCustomPacketEvent event) {
		if (KUtils.PROTECT_SERVER) {

			final EntityPlayerMP player = ((NetHandlerPlayServer) event.handler).playerEntity;

			final ByteBuf buffer = event.packet.payload();

			final EnumSPackets packetType = EnumSPackets.values()[buffer.readInt()];

			if (packetType.permission != null && !KPermissionsLoader.has(player, packetType.permission)) {
				KServer.sendErrMessage(player, "У вас нет прав для выполнения этого действия!");
				return;
			}

			final DAPlayerData dap = DADataManager.getPlayer(player);

			switch (packetType) {
			case Drop:
				final int slotNumber = buffer.readInt() + 1;
				final boolean isShiftPressed = buffer.readBoolean();
				final ItemStack stack = player.inventory.getStackInSlot(slotNumber);

				if (stack != null) {
					if (isShiftPressed) {
						player.dropPlayerItemWithRandomChoice(stack, true);
						player.inventory.setInventorySlotContents(slotNumber, (ItemStack) null);
					} else {
						final ItemStack toDrop = player.inventory.getStackInSlot(slotNumber);
						toDrop.stackSize = 1;
						player.dropPlayerItemWithRandomChoice(toDrop, true);
						player.inventory.getStackInSlot(slotNumber).stackSize--;
					}
				}

				break;
			case EditSentinel:
				final Point3D pos = Point3D.readFromBuffer(buffer);
				final double radius = buffer.readDouble();
				final int factionId = buffer.readInt();

				final TileEntity te = KBlocks.getTileEntity(player.worldObj, pos);

				if (te != null && te instanceof TileEntitySentinel) {
					final TileEntitySentinel sentinel = (TileEntitySentinel) te;
					sentinel.radius = radius;
					sentinel.factionId = factionId;
					KServer.sendFineMessage(player, "Установлены значения: Радиус = " + sentinel.getRadius() + ", ID фракции = " + sentinel.getFactionId());
				}

				break;
			case HotSlot:
				final int number = buffer.readInt() + 1;

				final ItemStack hot = player.inventory.mainInventory[number];
				if (hot != null && hot.getItem() instanceof IFastUsable) {
					final IFastUsable item = (IFastUsable) player.inventory.mainInventory[number].getItem();

					item.use(player.inventory.mainInventory[number], player.worldObj, player, number);
				}

				break;
			case MoneyClick:
				final boolean isShiftPressed2 = buffer.readBoolean();
				final boolean isCtrlPressed = buffer.readBoolean();
				final float value = buffer.readFloat();

				if (DAPlayerDataHandler.Instance.moneyTimer > 0) {
					return;
				}

				DAPlayerDataHandler.Instance.setMoneyTimer(player, 100);

				final RegistryNamespaced rn = Item.itemRegistry;
				final Money one = (Money) rn.getObject(DarkData.MOD_ID + ":OREN");
				final Money hundred = (Money) rn.getObject(DarkData.MOD_ID + ":oren100");
				final Money thousand = (Money) rn.getObject(DarkData.MOD_ID + ":oren1000");

				if (value == one.getValueInOrenes()) {
					if (isShiftPressed2) {
						int result = 0;
						for (final ItemStack stack2 : player.inventory.mainInventory) {
							if (stack2 != null && stack2.getItem() instanceof Money && stack2.getItem() == one) {
								if (result >= 100) {
									break;
								}
								result += stack2.stackSize;
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
						if (DAPlayerInventory.canGive(player, 2)) {
							final ItemStack toGive = new ItemStack(one);
							toGive.stackSize = 50;
							dap.inventory.addItemStackToInventory(toGive.copy());
							dap.inventory.addItemStackToInventory(toGive.copy());
							player.inventory.consumeInventoryItem(hundred);
						}
					} else if (isShiftPressed2) {
						int result = 0;
						for (final ItemStack stack1 : player.inventory.mainInventory) {
							if (stack1 != null && stack1.getItem() instanceof Money && stack1.getItem() == hundred) {
								if (result >= 10) {
									break;
								}
								result += stack1.stackSize;
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
						if (DAPlayerInventory.canAdd(player, hundred)) {
							final ItemStack toGive = new ItemStack(hundred);
							toGive.stackSize = 10;
							dap.inventory.addItemStackToInventory(toGive);
							player.inventory.consumeInventoryItem(thousand);
						}
					}

				}
				break;
			case OpenGui:
				player.openGui(DarkData.instance, buffer.readInt(), player.worldObj, (int) player.posX, (int) player.posY, (int) player.posZ);
				break;
			case PlayerClass:
				dap.playerClass.change(EnumPlayerClass.values()[buffer.readInt()]);
				break;
			case Repair:
				final float repairingPercent = buffer.readInt();
				final EnumRepairKit repairKitType = EnumRepairKit.values()[buffer.readInt()];
				int clickedSlotId = buffer.readInt();
				final ItemStack rk = KNetwork.readItemStackFromBuffer(buffer);

				if (DAPlayerDataHandler.Instance.repairTimer > 0) {
					DASNetwork.sendTo(player, EnumCPackets.Repair);
					break;
				}

				ItemStack clickedItemStack = null;

				clickedItemStack = player.inventory.getStackInSlot(clickedSlotId);

				if (clickedItemStack != null) {
					final Item clickedItem1 = clickedItemStack.getItem();

					if (clickedItem1 instanceof IRepairable && ((IRepairable) clickedItem1).getRepairKitType() == repairKitType) {
						final DAItem clickedItem = (DAItem) clickedItem1;
						final float maxDamage = clickedItem.getMaxDurability();
						final float repairAmount = maxDamage / 100 * repairingPercent;

						clickedItem.repair(clickedItemStack, repairAmount);

						player.inventory.consumeInventoryItem(rk.getItem());

						DAPlayerDataHandler.Instance.setRepairTimer(player, 20);

						DASNetwork.sendTo(player, EnumCPackets.Repair);
					}
				}

				break;
			case UpdateBook:
				final ItemStack currentStack = player.getCurrentEquippedItem();
				final AbstractStoryBook slBook = DABooks.Instance.getContent(currentStack);

				if (slBook == null)
					return;

				slBook.fillNBT(currentStack.getTagCompound());

				break;
			case UseSign:
				final int entityId = buffer.readInt();
				final int signId = buffer.readInt();

				if (dap.playerClass.equals(EnumPlayerClass.HUMAN) || signId < 0 || signId > 4 || signId == EnumSign.Irden.ordinal())
					return;

				final EnumSign id = EnumSign.values()[signId];

				DASignHandler.handle(player, id, entityId);

				break;
			case PerformEffect:
				final EnumEffect name = EnumEffect.values()[buffer.readInt()];
				final int duration = buffer.readInt();
				final float efficiency = buffer.readFloat();
				final boolean sum = buffer.readBoolean();
				dap.effects.perform(name, duration, efficiency, sum);

				break;
			case CureEffect:
				final PlayerEffect eff = dap.effects.map.get(EnumEffect.values()[buffer.readInt()]);

				if (eff == null)
					return;

				eff.cure();

				break;
			}
		}
	}
}
