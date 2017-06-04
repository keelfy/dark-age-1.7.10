package keelfy.darkage.network.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import keelfy.api.network.AbstractMessage.AbstractServerMessage;
import keelfy.darkage.entity.player.DAPlayer;
import keelfy.darkage.handler.registers.ItemRegister;
import keelfy.darkage.item.smartlib.SLBook;
import keelfy.darkage.item.smartlib.SLLine;
import keelfy.darkage.item.smartlib.SLPage;
import keelfy.darkage.util.DAUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.network.PacketBuffer;

/**
 * @author keelfy
 * @created 4 июн. 2017 г.
 */
public class GiveBookMessage extends AbstractServerMessage<GiveBookMessage> {

	private int amount;
	private NBTTagCompound data;
	private String author;
	private String title;
	private int id;
	
	public GiveBookMessage() {}
	
	public GiveBookMessage(int amount, SLBook slBook) {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			this.amount = amount;
			
			data = new NBTTagCompound();
			NBTTagList bookPages = new NBTTagList();
			
			List<NBTTagString> pages = new ArrayList(slBook.pages.size());
			for(SLPage page : slBook.pages) {
				String text = "";
				for(SLLine line : page.lines) {
					text += line.text;
				}
				
				pages.add(new NBTTagString(text));
			}
			
			for(NBTTagString page : pages) {
				bookPages.appendTag(page);
			}
			
			data.setTag("pages", bookPages);
			
			this.author = slBook.author;
			this.title = slBook.title;
			this.id = slBook.id;
		}
	}
	
	@Override
	protected void read(PacketBuffer buffer) throws IOException {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			amount = buffer.readInt();
			author = readString(buffer);
			title = readString(buffer);
			id = buffer.readInt();
			data = buffer.readNBTTagCompoundFromBuffer();
		}
	}

	@Override
	protected void write(PacketBuffer buffer) throws IOException {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			buffer.writeInt(amount);
			this.writeString(buffer, author);
			this.writeString(buffer, title);
			buffer.writeInt(id);
			buffer.writeNBTTagCompoundToBuffer(data);
		}
	}

	@Override
	public void process(EntityPlayer player, Side side) {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {	
			data.setString("author", author);
			data.setString("title", title);
			data.setInteger("id", id);
			
			ItemStack book = new ItemStack(ItemRegister.writtenBook);
			book.stackSize = amount;
			book.setTagCompound(data);
			
			if(player.capabilities.isCreativeMode) 
				player.inventory.addItemStackToInventory(book.copy());
			else
				DAPlayer.get(player).inventory.addItemStackToInventory(book.copy());
		}
	}
}
