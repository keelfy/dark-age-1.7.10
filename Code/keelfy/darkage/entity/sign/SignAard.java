package keelfy.darkage.entity.sign;

import keelfy.darkage.addon.blocks.register.BlockRegister;
import keelfy.darkage.entity.player.DAPlayer;
import keelfy.darkage.entity.player.DAPlayerUtil.Property;
import keelfy.darkage.util.DAUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class SignAard extends EntityLivingBase {

	private EntityPlayer owner;
	
	public SignAard(World world) {
		super(world);
	}

	public void setOwner(EntityPlayer owner) {
		this.owner = owner;
	}
	
	@Override
	public void onUpdate() {
		if(this != null) {
			super.onUpdate();
			
			if(this.ticksExisted > 120) 
				this.setDead();
			
			if(this.ticksExisted < 10)
				this.worldObj.spawnParticle("largeexplode", this.posX, this.posY + 0.5D, this.posZ, 1.0D, 0.0D, 0.0D);
		}
	}

	@Override
	protected void applyEntityAttributes() {
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.maxHealth);
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.knockbackResistance);
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.movementSpeed);
	      
		if(!this.isAIEnabled()) 
			this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.0D);
	}

	@Override
	public ItemStack getHeldItem() {
		return null;
	}
	
	@Override
	public ItemStack getEquipmentInSlot(int slot) {
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
				DAPlayer dap = DAPlayer.get(owner);
	
				if (dap != null && e1 != null && e1 instanceof EntityLiving && dap.get(Property.ENERGY) > dap.getPlayerMaxEnergy() - 10F) {
					EntityLiving e = (EntityLiving) e1;
					double motion = 1.0D;
					double y = 0.15000000596046448D;
//					if (owner.experienceLevel >= 7) {
//						motion = 1.5D;
//					} else if (owner.experienceLevel >= 80) {
//						motion = 2000.0D;
//						y = 100.0D;
//					} else {
						motion = 1.0D;
						y = 0.15000000596046448D;
//					}
	
					setPosition(owner.posX, owner.posY, owner.posZ);
					this.worldObj.spawnEntityInWorld(this);
					motionX = owner.getLookVec().xCoord * motion;
					motionY = owner.getLookVec().zCoord * motion;
					motionZ = y;
					e.motionX = owner.getLookVec().xCoord * motion;
					e.motionZ = owner.getLookVec().zCoord * motion;
					e.motionY = y;
					owner.worldObj.setBlock((int) owner.posX, (int) owner.posY + 2, (int) owner.posZ, BlockRegister.light, 0, 3);
					ISign.useSign("aard", this.worldObj, owner);
					e.addPotionEffect(new PotionEffect(Potion.moveSlowdown.getId(), 60, 10));
					dap.update(Property.ENERGY, dap.getPlayerMinEnergy());
				}
			}
		}
	}
}
