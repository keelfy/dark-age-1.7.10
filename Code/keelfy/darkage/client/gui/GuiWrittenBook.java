package keelfy.darkage.client.gui;

import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkage.DarkAge;
import keelfy.darkage.handler.client.FileHandler;
import keelfy.darkage.item.smartlib.SLBook;
import keelfy.darkage.item.smartlib.SLPage;
import keelfy.darkage.util.DAUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraft.util.ResourceLocation;

/**
 * @author keelfy
 */
@SideOnly(Side.CLIENT)
public class GuiWrittenBook extends GuiScreen {
	
    private static ResourceLocation bookGuiTextures;
    private final EntityPlayer editingPlayer;
    private final ItemStack bookObj;
    private FileHandler fh;
    private boolean field_146481_r;
    private boolean field_146480_s;
    private int updateCount;
    private int bookImageWidth = 192;
    private int bookImageHeight = 192;
    private int bookTotalPages = 1;
    private int currPage;
    private List<SLPage> bookPages;
    private String bookTitle = "";
    private GuiWrittenBook.NextPageButton buttonNextPage;
    private GuiWrittenBook.NextPageButton buttonPreviousPage;
    private GuiButton buttonDone;
    private GuiButton buttonSign;
    private GuiButton buttonFinalize;
    private GuiButton buttonCancel;

    public GuiWrittenBook(EntityPlayer player, ItemStack is) {
        this.editingPlayer = player;
        this.bookObj = is;
        this.fh = DarkAge.instance.fileHandler;
        
        if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
        	bookGuiTextures = new ResourceLocation("minecraft", "textures/gui/book.png");
        	
	        if (is.hasTagCompound()) {
	            NBTTagCompound nbttagcompound = is.getTagCompound();
	            SLBook slBook = fh.getBookByID(nbttagcompound.getInteger("id"));
	            
	            if(slBook != null) {
	            	bookPages = slBook.pages;
	            	
		            if (this.bookPages != null) {
		                this.bookTotalPages = this.bookPages.size();
		
		                if (this.bookTotalPages < 1)
		                    this.bookTotalPages = 1;
		            }
	            }
	        }
        }
    }

    @Override
	public void updateScreen() {
    	if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
    		super.updateScreen();
    		++this.updateCount;
    	}
    }

    @Override
	public void initGui() {
    	if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
	        this.buttonList.clear();
	        Keyboard.enableRepeatEvents(true);
	        this.buttonList.add(this.buttonDone = new GuiButton(0, this.width / 2 - 100, 4 + this.bookImageHeight, 200, 20, I18n.format("gui.done", new Object[0])));
	
	        int i = (this.width - this.bookImageWidth) / 2;
	        byte b0 = 2;
	        this.buttonList.add(this.buttonNextPage = new GuiWrittenBook.NextPageButton(1, i + 120, b0 + 154, true));
	        this.buttonList.add(this.buttonPreviousPage = new GuiWrittenBook.NextPageButton(2, i + 38, b0 + 154, false));
	        this.updateButtons();
    	}
    }

    @Override
	public void onGuiClosed() {}

    private void updateButtons() {
    	if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
	        this.buttonNextPage.visible = !this.field_146480_s && (this.currPage < this.bookTotalPages - 1);
	        this.buttonPreviousPage.visible = !this.field_146480_s && this.currPage > 0;
	        this.buttonDone.visible = !this.field_146480_s;
    	}
    }

    @Override
	protected void actionPerformed(GuiButton p_146284_1_) {
    	if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
	        if (p_146284_1_.enabled) {
	            if (p_146284_1_.id == 0)
	                this.mc.displayGuiScreen((GuiScreen)null);
	            else if (p_146284_1_.id == 1) {
	                if (this.currPage < this.bookTotalPages - 1)
	                    ++this.currPage;
	            } else if (p_146284_1_.id == 2) {
	                if (this.currPage > 0)
	                    --this.currPage;
	            }
	            this.updateButtons();
	        }
    	}
    }

    private void func_146460_c(char p_146460_1_, int p_146460_2_) {
    	if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
	        switch (p_146460_2_) {
	            case 14:
	                if (!this.bookTitle.isEmpty()) {
	                    this.bookTitle = this.bookTitle.substring(0, this.bookTitle.length() - 1);
	                    this.updateButtons();
	                }
	
	                return;
	            case 28:
	                return;
	            default:
	                if (this.bookTitle.length() < 16 && ChatAllowedCharacters.isAllowedCharacter(p_146460_1_)) {
	                    this.bookTitle = this.bookTitle + Character.toString(p_146460_1_);
	                    this.updateButtons();
	                    this.field_146481_r = true;
	                }
	        }
    	}
    }

    @Override
	public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
    	if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
	        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	        this.mc.getTextureManager().bindTexture(bookGuiTextures);
	        int k = (this.width - this.bookImageWidth) / 2;
	        byte b0 = 2;
	        this.drawTexturedModalRect(k, b0, 0, 0, this.bookImageWidth, this.bookImageHeight);
	        String s;
	        String s1;
	        int l;
	
	        s = I18n.format("book.pageIndicator", new Object[] {Integer.valueOf(this.currPage + 1), Integer.valueOf(this.bookTotalPages)});
	        s1 = "";
	
	        if (this.bookPages != null && this.currPage >= 0 && this.currPage < this.bookPages.size())
	        {
	        	s1 = bookPages.get(currPage).asString();
	        }
	        
	        l = this.fontRendererObj.getStringWidth(s);
	        this.fontRendererObj.drawString(s, k - l + this.bookImageWidth - 44, b0 + 16, 0);
	        this.fontRendererObj.drawSplitString(s1, k + 36, b0 + 16 + 16, 116, 0);
	
	        super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
    	}
    }

    @SideOnly(Side.CLIENT)
    static class NextPageButton extends GuiButton {
    	private boolean field_146151_o;

    	public NextPageButton(int p_i1079_1_, int p_i1079_2_, int p_i1079_3_, boolean p_i1079_4_) {
    		super(p_i1079_1_, p_i1079_2_, p_i1079_3_, 23, 13, "");
    		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
    			this.field_146151_o = p_i1079_4_;
    		}
    	}

    	@Override
		public void drawButton(Minecraft p_146112_1_, int p_146112_2_, int p_146112_3_) {
    		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
	    		if (this.visible) {
	    			boolean flag = p_146112_2_ >= this.xPosition && p_146112_3_ >= this.yPosition && p_146112_2_ < this.xPosition + this.width && p_146112_3_ < this.yPosition + this.height;
	    			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	    			p_146112_1_.getTextureManager().bindTexture(GuiWrittenBook.bookGuiTextures);
	    			int k = 0;
	    			int l = 192;
	
	    			if (flag) k += 23;
	
	    			if (!this.field_146151_o) l += 13;
	    			
	    			this.drawTexturedModalRect(this.xPosition, this.yPosition, k, l, 23, 13);
	    		}
    		}
    	}
    }
}
