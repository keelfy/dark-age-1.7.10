package keelfy.darkage.entity.sign;

import keelfy.darkage.entity.player.DAPlayer;
import keelfy.darkage.entity.player.DAPlayerUtil.Property;
import keelfy.darkage.util.DAUtil;
import keelfy.witcherBlocks.register.BlockRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class SignIgni extends EntityLivingBase {

   public SignIgni(World world) {
      super(world);
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
   protected void entityInit() {
      super.entityInit();
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

	public void handle(EntityPlayerMP sender) {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			if (!this.worldObj.isRemote) {
				double rad = 5.0D;
				EntityLiving e = (EntityLiving) ISign.getLookingEntity(rad, sender);
				DAPlayer wcp = DAPlayer.get(sender);
				
				if (wcp != null && e != null && wcp.get(Property.ENERGY) > wcp.getPlayerMaxEnergy() - 7) {
					if (sender.experienceLevel >= 10) {
						rad += sender.experienceLevel / 10;
					}
					
					setPosition(sender.posX, sender.posY, sender.posZ);
					this.worldObj.spawnEntityInWorld(this);
					double motion = 1.0D;
					double y = 0.15000000596046448D;
					motionX = sender.getLookVec().xCoord * motion;
					motionY = sender.getLookVec().zCoord * motion;
					motionZ = y;
					ISign.useSign("customnpcs:signs.igni", this.worldObj, sender);
					sender.worldObj.setBlock((int) sender.posX, (int) sender.posY + 2, (int) sender.posZ, BlockRegister.light, 0, 3);
					e.setFire(3 + this.worldObj.rand.nextInt(10));
					wcp.update(Property.ENERGY, 0F);
				}
			}
		}
	}
}
