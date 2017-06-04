package keelfy.darkage.client.gui;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.api.client.GuiUtil;
import keelfy.darkage.handler.client.ResourceHandler.Texture;
import keelfy.darkage.handler.client.ResourceHandler.Texture.WCT;
import keelfy.darkage.inventory.lootbag.ContainerLootBag;
import keelfy.darkage.util.DAUtil;
import keelfy.witcherBlocks.tileentity.TileEntityLootBag;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

/**
 * @author keelfy
 */
@SideOnly(Side.CLIENT)
public class GuiLootBag extends GuiDAContainer {
	
	private Minecraft mc;
	private TileEntityLootBag te;
	private RenderItem itemRenderer;
	private ResourceLocation selector;
	private int selectedStackNumber;
	private ItemStack[] inventoryStacks;
	
	public GuiLootBag(ContainerLootBag container) {
		super(container);
	
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			this.te = container.inventory;
			this.selector = Texture.get(WCT.GUI, "lootBagSelector");
			itemRenderer = new RenderItem();
			mc = Minecraft.getMinecraft();
		}
	}
	
	@Override
	public void initGui() {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			selectedStackNumber = 0;
			inventoryStacks = new ItemStack[te.getSizeInventory()];
			
			int count = 0;
			for(ItemStack stack : te.inventoryStacks) {
				if(stack != null) {
					count++;
					inventoryStacks[count] = stack;
				}
			}
			
			super.initGui();
		}
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
	
	@Override
    protected void keyTyped(char par1, int par2){
     
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
	        ItemStack selectedStack = null;
	        if(par1 == Keyboard.KEY_H) {
	        	selectedStackNumber++;
	        }
	        
	        if(par2 != 28 || par2 != 57 || par2 !=Keyboard.KEY_H || par2 != Keyboard.KEY_RIGHT || par2 != Keyboard.KEY_DOWN || par2 != Keyboard.KEY_UP) 
	        	mc.displayGuiScreen((GuiScreen)null);
		}
    }
	
	@Override
	public void drawDefaultBackground() {}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y) {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			ScaledResolution sr = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
			int width = sr.getScaledWidth();
			int height = sr.getScaledHeight();
			
			int stackCount = 0, stackCount1 = 0, height1 = 0, width1 = 0;
			for(int i = 0; i < inventoryStacks.length; i++) {
				if(inventoryStacks[i] != null) {
					stackCount++;
					stackCount1++;
					if(height1 != stackCount / 10) stackCount1 = 1;
					height1 = stackCount % 10 == 0 ? stackCount / 10 : height1;
					
					GL11.glPushMatrix();
					if(1 == i) GL11.glScalef(0.7F, 0.7F, 0.7F);
					GuiUtil.drawItemStack(itemRenderer, inventoryStacks[i], width / 2 + 85 - (18 * stackCount1), height / 2 + 60 - (18 * height1), 4F);
					GL11.glPopMatrix();
				}
			}
		}
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {}
	@Override
	protected void mouseClicked(int x, int y, int z) {}
	@Override
	protected void mouseClickMove(int x, int y, int z, long par4) {}
	@Override
	protected void mouseMovedOrUp(int x, int y, int z) {}
}
