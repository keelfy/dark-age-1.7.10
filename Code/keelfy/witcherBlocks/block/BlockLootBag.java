package keelfy.witcherBlocks.block;

import keelfy.darkage.DarkAge;
import keelfy.darkage.handler.GuiHandler.GUI;
import keelfy.darkage.util.DATab;
import keelfy.darkage.util.DAUtil;
import keelfy.witcherBlocks.tileentity.TileEntityLootBag;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

/**
 * @author keelfy
 */
public class BlockLootBag extends BlockContainer {

	public int renderId = -1;

	public BlockLootBag() {
		super(Material.cloth);
		
        setBlockBounds(0.3F, 0.0F, 0.3F, 0.7F, 0.6F, 0.7F);
		setCreativeTab(DATab.tabBlock);
		setBlockUnbreakable();
	}
    
    @Override    
    public boolean onBlockActivated(World par1World, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
    	if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
    		player.openGui(DarkAge.instance, GUI.LOOTBAG.get(), par1World, x, y, z);
    	}
		return false;
    }
    
    @Override   
    public void onBlockPlacedBy(World par1World, int x, int y, int z, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack){
    	if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
	    	((TileEntityLootBag)par1World.getTileEntity(x, y, z)).setInventorySlotContents(0, new ItemStack(Items.apple));
	    	((TileEntityLootBag)par1World.getTileEntity(x, y, z)).setInventorySlotContents(1, new ItemStack(Items.book, 64));
	    	((TileEntityLootBag)par1World.getTileEntity(x, y, z)).setInventorySlotContents(2, new ItemStack(Items.book, 64));
	    	((TileEntityLootBag)par1World.getTileEntity(x, y, z)).setInventorySlotContents(3, new ItemStack(Items.book, 64));
	    	((TileEntityLootBag)par1World.getTileEntity(x, y, z)).setInventorySlotContents(4, new ItemStack(Items.book, 64));
	    	((TileEntityLootBag)par1World.getTileEntity(x, y, z)).setInventorySlotContents(5, new ItemStack(Items.book, 64));
	    	((TileEntityLootBag)par1World.getTileEntity(x, y, z)).setInventorySlotContents(6, new ItemStack(Items.book, 64));
	    	((TileEntityLootBag)par1World.getTileEntity(x, y, z)).setInventorySlotContents(7, new ItemStack(Items.book, 64));
	    	((TileEntityLootBag)par1World.getTileEntity(x, y, z)).setInventorySlotContents(8, new ItemStack(Items.book, 64));
	    	((TileEntityLootBag)par1World.getTileEntity(x, y, z)).setInventorySlotContents(9, new ItemStack(Items.book, 64));
	    	((TileEntityLootBag)par1World.getTileEntity(x, y, z)).setInventorySlotContents(10, new ItemStack(Items.book, 64));
	    	((TileEntityLootBag)par1World.getTileEntity(x, y, z)).setInventorySlotContents(11, new ItemStack(Items.book, 64));
	    	((TileEntityLootBag)par1World.getTileEntity(x, y, z)).setInventorySlotContents(12, new ItemStack(Items.book, 64));
	    	((TileEntityLootBag)par1World.getTileEntity(x, y, z)).setInventorySlotContents(13, new ItemStack(Items.book, 64));
	    	
	    	
	        int l = MathHelper.floor_double((double)(par5EntityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
	        l %= 4;
	        TileEntityLootBag tile = (TileEntityLootBag) par1World.getTileEntity(x, y, z);
	    	tile.rotation = l;
    	}
    }
    
    private TileEntityLootBag getTile(World world, int x, int y, int z){
    	if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
	    	TileEntity tile = world.getTileEntity(x, y, z);
	    	if(tile != null && tile instanceof TileEntityLootBag)
	    		return (TileEntityLootBag) tile;
    	}
    	return null;
    }
    
    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_) {
        return null;
    }
    
    @Override   
	public int getRenderType(){
		return renderId; 	
	}

    @Override   
	public boolean isOpaqueCube(){
		return false;
	}

    @Override   
	public boolean renderAsNormalBlock(){
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityLootBag();
	}
}
