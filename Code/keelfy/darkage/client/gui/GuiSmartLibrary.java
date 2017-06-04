package keelfy.darkage.client.gui;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkage.DarkAge;
import keelfy.darkage.handler.client.FileHandler;
import keelfy.darkage.handler.registers.ItemRegister;
import keelfy.darkage.item.smartlib.SLBook;
import keelfy.darkage.item.smartlib.SLLine;
import keelfy.darkage.item.smartlib.SLPage;
import keelfy.darkage.util.DAUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;

/**
 * @author keelfy
 * TODO: Полностью пофиксить смарт-библиотеку
 */
@SideOnly(Side.CLIENT)
public class GuiSmartLibrary extends GuiScreen {
	
	private List<File> listItems;
	private GuiSmartLibrary.ScrollList scrollList;
	public int slotSelected = -1;
	
	private static final int BUTTONWIDTH = 60;
	private static final int BUTTONHEIGHT = 20;
	private static final int BTN_LOAD = 0;
	private static final int BTN_CANCEL = 1;
	
	private GuiButton btnLoad;
	private GuiButton btnCancel;
	
	private SLBook tempBook;
	private FileHandler fileHandler;
	
	private String displayPath = "";
	private String previewTitle = "";
	private String previewAuthor = "";
	private String previewPage = "";
	
	@Override
	public void initGui() {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			this.fileHandler = DarkAge.instance.fileHandler;
			
	        this.fileHandler.currentPath = fileHandler.getDataFolderPath();
	        this.displayPath = fileHandler.currentPath.getAbsolutePath();
	        
	        this.buttonList.add(btnLoad = new GuiButton(BTN_LOAD, this.width-(BUTTONWIDTH+5), this.height-50, BUTTONWIDTH, BUTTONHEIGHT, "Выдать"));
	        this.buttonList.add(btnCancel = new GuiButton(BTN_CANCEL, this.width-(BUTTONWIDTH+5), this.height-25, BUTTONWIDTH, BUTTONHEIGHT, "Отмена"));
	
	        int rootNum = 100;
	        List<File> roots = this.fileHandler.getValidRoots();
	        for (File root : roots){
	        	this.buttonList.add(new GuiButton(rootNum, 5, 35 + 21*(rootNum-100), 50, 20, root.getAbsolutePath()));
	        	rootNum++;
	        }
	        
	        populateFileList();
	        this.scrollList = new ScrollList();
	        this.scrollList.registerScrollButtons(4, 5);
		}
	}
	
	private void populateFileList(){
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			this.listItems = this.fileHandler.listFiles(this.fileHandler.currentPath);
		}
	}
	
	private void loadPreview(File file){
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			this.tempBook = this.fileHandler.loadBook(file);
			if(this.tempBook != null){
				this.previewAuthor = this.tempBook.author;
				this.previewTitle = this.tempBook.title;
				String firstPage = SLBook.removeFormatting(this.tempBook.pages.get(0).asString().replaceAll("\n", " "));
				this.previewPage = SLBook.truncateStringPixels(firstPage, "...", 200, false);
			}
			else{
				this.previewTitle = "";
	    		this.previewAuthor = "";
	    		this.previewPage = "";
			}
		}
	}
	
    @Override
	protected void keyTyped(char par1, int par2) {
    	if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
	        if (par2 == Keyboard.KEY_ESCAPE){
	        	mc.displayGuiScreen((GuiScreen)null);
	        }
    	}
    }
    
    
	@Override
	public void drawScreen(int par1, int par2, float par3){
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			this.displayPath = this.fileHandler.currentPath.getAbsolutePath();
			this.btnLoad.enabled = this.tempBook != null;
			populateFileList();
			this.scrollList.drawScreen(par1, par2, par3);
			super.drawScreen(par1, par2, par3);
			
			this.drawCenteredString(this.fontRendererObj, SLBook.truncateStringPixels(this.displayPath, "...", 200, true), this.width / 2, 20, 0xDDDDDD);
			if (!this.previewAuthor.isEmpty() || !this.previewTitle.isEmpty() || !this.previewPage.isEmpty()){
				this.drawCenteredString(this.fontRendererObj, "Автор: " + this.previewAuthor, this.width / 2, this.height-50, 0xFFFFFF);
				this.drawCenteredString(this.fontRendererObj, "Название: " + this.previewTitle, this.width / 2, this.height-40, 0xFFFFFF);
				this.drawCenteredString(this.fontRendererObj, "Страница 1: " + this.previewPage, this.width / 2, this.height-30, 0xFFFFFF);
			}
		}
    }
	
    @Override
	protected void actionPerformed(GuiButton buttonPressed) {
    	if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
	    	if (!buttonPressed.enabled){return;}
	    	
	    	switch (buttonPressed.id){
	    		case BTN_LOAD:
	    			if (this.tempBook != null){
	    				this.getBooks(1, tempBook);
	    				mc.displayGuiScreen((GuiScreen)null);
	        		}
	    			break;
	    		case BTN_CANCEL:
	    			mc.displayGuiScreen((GuiScreen)null);
	    			break;
	    		default:
	    			break;
	    	}
	    	
	    	if (buttonPressed.id >= 100){
	    		this.fileHandler.currentPath = new File(buttonPressed.displayString);
	    	}
    	}
    }
	
    public void getBooks(int number, SLBook slBook) {
    	if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			ItemStack book = new ItemStack(ItemRegister.writtenBook);
			NBTTagCompound tag = new NBTTagCompound();
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
			
			book.setTagInfo("pages", bookPages);
			book.stackTagCompound.setString("author", slBook.author);
			System.out.println(slBook.title);
			book.stackTagCompound.setString("title", slBook.title);
			book.stackTagCompound.setInteger("id", slBook.id);
			
			for(int i = 0; i < number; i ++)
				Minecraft.getMinecraft().thePlayer.inventory.addItemStackToInventory(book);
    	}
    }
    
    class ScrollList extends GuiSlot {
    	
    	private static final int SLOT_HEIGHT = 12;
    	
    	
        public ScrollList(){
        	super(GuiSmartLibrary.this.mc, GuiSmartLibrary.this.width, GuiSmartLibrary.this.height, 32, GuiSmartLibrary.this.height - 64, SLOT_HEIGHT);
        }

        
        protected int getPaddedSize() {
        	if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
	        	int scrollHeight = GuiSmartLibrary.this.height - 96;
	        	int minSlots = (int)Math.ceil(scrollHeight/SLOT_HEIGHT);
	        	
	        	if (GuiSmartLibrary.this.listItems.size() >= minSlots){
	        		return GuiSmartLibrary.this.listItems.size();
	        	}
	        	else{
	        		return minSlots;
	        	}
        	} else 
        		return 0;
        }
        
        @Override
		protected int getSize(){
            return getPaddedSize();
        }
     
        @Override
		protected void elementClicked(int slotClicked, boolean doubleClicked, int clickXPos, int clickYPos){
        	if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
	        	this.setShowSelectionBox(true);
	            if (doubleClicked){
	            	if (slotClicked == 0){
	                	GuiSmartLibrary.this.fileHandler.navigateUp();
	                	GuiSmartLibrary.this.slotSelected = -1;
	                	this.setShowSelectionBox(false);
	                	return;
	                }
	                else if (slotClicked <= GuiSmartLibrary.this.listItems.size()){
	                	File itemClicked = GuiSmartLibrary.this.listItems.get(slotClicked-1);
	                	if (itemClicked.isDirectory()){
	                		GuiSmartLibrary.this.fileHandler.currentPath = itemClicked;
	                		GuiSmartLibrary.this.slotSelected = -1;
	                		this.setShowSelectionBox(false);
	                		return;
	                	}
	                	else{
	                		if (GuiSmartLibrary.this.tempBook != null){
	                			GuiSmartLibrary.this.getBooks(1, tempBook);
	                			mc.displayGuiScreen((GuiScreen)null);
	                		}
	                	}
	                }
	            }
	            else if (slotClicked > 0 && slotClicked <= GuiSmartLibrary.this.listItems.size()){
	            	File selectedFile = GuiSmartLibrary.this.listItems.get(slotClicked-1);
	            	if (selectedFile.isFile() && !isSelected(slotClicked)){
	            		GuiSmartLibrary.this.loadPreview(selectedFile);
	            	}
	            	else{
	            		GuiSmartLibrary.this.previewTitle = "";
	            		GuiSmartLibrary.this.previewAuthor = "";
	            		GuiSmartLibrary.this.previewPage = "";
	            		GuiSmartLibrary.this.tempBook = null;
	            	}
	            }
	            else{
	        		GuiSmartLibrary.this.previewTitle = "";
	        		GuiSmartLibrary.this.previewAuthor = "";
	        		GuiSmartLibrary.this.previewPage = "";
	        		GuiSmartLibrary.this.tempBook = null;
	        	}
	            GuiSmartLibrary.this.slotSelected = slotClicked;
        	}
        }

        @Override
		protected boolean isSelected(int pos){
            return pos == GuiSmartLibrary.this.slotSelected;
        }

        @Override
		protected int getContentHeight(){
            return getPaddedSize() * SLOT_HEIGHT;
        }

        @Override
		protected void drawBackground(){
        	if(!DAUtil.SERVER || DAUtil.DEBUG_MODE)
        		GuiSmartLibrary.this.drawDefaultBackground();
        }

        @Override
		protected void drawSlot(int slotNum, int p_148126_2_, int p_148126_3_, int p_148126_4_, Tessellator p_148126_5_, int p_148126_6_, int p_148126_7_){
        	if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
	        	List<File> list = GuiSmartLibrary.this.listItems;
	        	if (slotNum > list.size()){return;}
	        	
	        	String slotText = "";
	        	int color = 0xFFFFFF;
	        	
	        	if (slotNum == 0){
	        		slotText = "..";
	        		color = 0x00FF00;
	        	}
	        	else{
	        		slotText = SLBook.truncateStringPixels(list.get(slotNum-1).getName(), "...", 200, false);
	        		if (list.get(slotNum-1).isFile()){
	    				color = 0xFF0000;
	    			}
	    			else{
	    				color = 0x00FF00;
	    			}
	        	}
	        	
	            GuiSmartLibrary.this.drawString(GuiSmartLibrary.this.fontRendererObj, slotText, p_148126_2_ + 2, p_148126_3_ + 1, color);
        	}
        }
    }
}
