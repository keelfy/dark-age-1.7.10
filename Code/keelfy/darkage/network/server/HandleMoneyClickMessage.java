package keelfy.darkage.network.server;

import cpw.mods.fml.relauncher.Side;
import keelfy.api.network.AbstractMessage.AbstractServerMessage;
import keelfy.darkage.entity.player.DAPlayer;
import keelfy.darkage.item.Money;
import keelfy.darkage.util.DAUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.RegistryNamespaced;

/**
 * @author keelfy
 * @created 4 июн. 2017 г.
 */
public class HandleMoneyClickMessage extends AbstractServerMessage<HandleMoneyClickMessage> {

	private boolean isShiftPressed;
	private boolean isCtrlPressed;
	private float amount;
	
	public HandleMoneyClickMessage() {}

	public HandleMoneyClickMessage(boolean isCtrlPressed, boolean isShiftPressed, float amount) {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			this.isCtrlPressed = isCtrlPressed;
			this.isShiftPressed = isShiftPressed;
			this.amount = amount;
		}
	}

	@Override
	protected void read(PacketBuffer buffer) {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			isCtrlPressed = buffer.readBoolean();
			isShiftPressed = buffer.readBoolean();
			amount = buffer.readFloat();
		}
	}

	@Override
	protected void write(PacketBuffer buffer) {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			buffer.writeBoolean(isCtrlPressed);
			buffer.writeBoolean(isShiftPressed);
			buffer.writeFloat(amount);
		}
	}

	@Override
	public void process(EntityPlayer player, Side side) {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			RegistryNamespaced rn = Item.itemRegistry;
			DAPlayer dap = DAPlayer.get(player);
			
			Money one = (Money) rn.getObject(DAUtil.MODID + ":OREN");
			Money hundred = (Money) rn.getObject(DAUtil.MODID + ":oren100");
			Money thousand = (Money) rn.getObject(DAUtil.MODID + ":oren1000");
			
			if(amount == one.getValueInOrenes()) {
				if(isShiftPressed) {
					int result = 0;
					for (ItemStack stack : player.inventory.mainInventory) {
						if(stack != null && stack.getItem() instanceof Money && stack.getItem() == one) {
							if(result >= 100)
								break;
							result += stack.stackSize;
						}
					}
					
					if(result >= 100) {
						for (int i = 0; i < 100; i++) {
							player.inventory.consumeInventoryItem(one);
						}
						dap.inventory.addItemStackToInventory(new ItemStack(hundred));
					}
				}
			} else if(amount == hundred.getValueInOrenes()) {
				if(isCtrlPressed) {
					if(canGive(player, 2)) {
						ItemStack toGive = new ItemStack(one);
						toGive.stackSize = 50;
						dap.inventory.addItemStackToInventory(toGive.copy());
						dap.inventory.addItemStackToInventory(toGive.copy());						
						player.inventory.consumeInventoryItem(hundred);
					}
				} else if(isShiftPressed) {
					int result = 0;
					for (ItemStack stack : player.inventory.mainInventory) {
						if(stack != null && stack.getItem() instanceof Money && stack.getItem() == hundred) {
							if(result >= 10)
								break;
							result += stack.stackSize;
						}
					}
					
					if(result >= 10) {
						for (int i = 0; i < 10; i++) {
							player.inventory.consumeInventoryItem(hundred);
						}
						dap.inventory.addItemStackToInventory(new ItemStack(thousand));
					}
				}
			} else if(amount == thousand.getValueInOrenes()) {
				if(isCtrlPressed) {
					if(canAdd(player, hundred)) {
						ItemStack toGive = new ItemStack(hundred);
						toGive.stackSize = 10;
						dap.inventory.addItemStackToInventory(toGive);					
						player.inventory.consumeInventoryItem(thousand);
					}
				}
			}
		}
	}
	
	private boolean canAdd(EntityPlayer player, Item toAdd) {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			int j = 0;
			for (int i = 12; i < player.inventory.mainInventory.length; i++) {
				ItemStack stack = player.inventory.mainInventory[i];
				
				if(stack != null && stack.getItem() == toAdd && stack.stackSize < stack.getMaxStackSize()) {
					return true;
				}
			}
			return canGive(player, 1);
		}
		return false;
	}
	
	private boolean canGive(EntityPlayer player, int slots) {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			int j = 0;
			for (int i = 12; i < player.inventory.mainInventory.length; i++) {
				ItemStack stack = player.inventory.mainInventory[i];
				if(j >= slots) return true;
					
				if(stack == null) {
					j += 1;
				}
			}
		}
		return false;
	}
}