package keelfy.darkage.entity.sign;

import keelfy.darkage.entity.player.DAPlayer;
import keelfy.darkage.entity.player.DAPlayerUtil.Property;
import keelfy.darkage.util.DAUtil;
import keelfy.witcherBlocks.register.BlockRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class SignIgni extends EntityLivingBase {

	private EntityPlayer owner;
	
	public SignIgni(World world) {
		super(world);
	}
	
	public void setOwner(EntityPlayer owner) {
		this.owner = owner;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		
		if(this.ticksExisted > 15) {
			this.setDead();
		}
		
		this.worldObj.spawnParticle("lava", this.posX, this.posY + 0.5D, this.posZ, 1.0D, 1.0D, 1.0D);
	}

	@Override
	protected void applyEntityAttributes() {
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.maxHealth);
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.knockbackResistance);
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.movementSpeed);
		if(!this.isAIEnabled()) {
			this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.0D);
		}
	}	

	@Override
	public ItemStack getHeldItem() {
		return null;
	}
	
	@Override
	public ItemStack getEquipmentInSlot(int p_71124_1_) {
		return null;
	}
	
	@Override
	public void setCurrentItemOrArmor(int slot, ItemStack stack) {}
	
	@Override
	public ItemStack[] getLastActiveItems() {
		return null;
	}

	public void handle(int entityId) {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			if (!this.worldObj.isRemote && entityId != -1) {
				Entity e1 = owner.worldObj.getEntityByID(entityId);
				DAPlayer wcp = DAPlayer.get(owner);
				
				if (wcp != null && e1 != null && e1 instanceof EntityLiving && wcp.get(Property.ENERGY) > wcp.getPlayerMaxEnergy() - 7) {
					EntityLiving e = (EntityLiving) e1;
					setPosition(owner.posX, owner.posY, owner.posZ);
					this.worldObj.spawnEntityInWorld(this);
					double motion = 1.0D;
					double y = 0.15000000596046448D;
					motionX = owner.getLookVec().xCoord * motion;
					motionY = owner.getLookVec().zCoord * motion;
					motionZ = y;
					ISign.useSign("customnpcs:signs.igni", this.worldObj, owner);
					owner.worldObj.setBlock((int) owner.posX, (int) owner.posY + 2, (int) owner.posZ, BlockRegister.light, 0, 3);
					e.setFire(3 + this.worldObj.rand.nextInt(10));
					wcp.update(Property.ENERGY, 0F);
				}
			}
		}
	}
}
